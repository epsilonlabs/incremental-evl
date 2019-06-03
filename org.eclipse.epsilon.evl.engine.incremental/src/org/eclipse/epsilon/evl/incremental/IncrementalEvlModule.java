/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	   Jonathan Co   - Initial API and implementation
 *     Horacio Hoyos - Decoupling and abstraction
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.execute.ExecutionMode;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.parse.EvlParser;
import org.eclipse.epsilon.executors.EpsilonExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * An IncrementalEvlModule.
 */
public class IncrementalEvlModule extends EvlModule implements IEvlModuleIncremental {

	static final Logger logger = LoggerFactory.getLogger(IncrementalEvlModule.class);

	/**
	 * Flag to indicate incremental execution.
	 */
	private boolean incrementalMode = true;

	/**
	 * If onlineExecution, this flags signals when initial execution has finished
	 * and the module is listening to model changes.
	 */
	private boolean live = false;

	/**
	 * The set of models from which the module receives notification.
	 */
	Set<IIncrementalModel> targets;

	private String chksum;
	
	/**
	 * Create a new IncrementalEvlModule using the provided trace manager. The Trace Manager can
	 * be automatically injected with Guice.
	 * @param traceManager
	 */
	@Inject
	public IncrementalEvlModule(IEvlExecutionTraceManager traceManager) {
		super();
		this.context = new IncrementalEvlContext(traceManager);
	}
	
	@Override
	public IncrementalEvlContext getContext() {
		return (IncrementalEvlContext) super.getContext();
	}
	
	@Override
	public ModuleElement adapt(AST cst, ModuleElement parentAst) {
		logger.debug("Adapting {} from {}", cst, parentAst);
		switch (cst.getType()) {
		case EvlParser.MESSAGE:
			return getOrCreateMessageBlock();
		case EvlParser.CHECK:
			return getOrCreateCheckBlock();
		case EvlParser.GUARD:
			if (!(parentAst instanceof Fix)) {
				return getOrCreateGuardBlock();
			}
			break;
		case EvlParser.CONSTRAINT:
		case EvlParser.CRITIQUE:
			if (incrementalMode) {
				return new TracedConstraint();
			} else {
				return new Constraint();
			}
		case EvlParser.CONTEXT:
			if (incrementalMode) {
				return getOrCreateTracedConstraintContext();
			} else {
				return new ConstraintContext();
			}
		}
		return super.adapt(cst, parentAst);
	}
	
	@Override
	public final Set<UnsatisfiedConstraint> executeImpl() throws EolRuntimeException {
		if (!incrementalMode) {
			logger.info("Invoked in non-incremental model. Delegating to normal EVL.");
			return new HashSet<>(super.execute());
		}
		if (isLive()) {
			logger.info("Execution is Live.");
			return Collections.emptySet();
		}
		prepareExecution();
		// IEvlExecutionTraceManager etManager = getContext().getTraceManager();
		String srcChksum = getChksum();
		boolean incremental = determineIncremental(srcChksum);		
		if (incremental) {
			logger.info("Execution is incremental.");
			executeIncremental(srcChksum);
		}
		else {
			logger.info("Execution is normal.");
			createModuleAndModelTraces(srcChksum);
			checkConstraints();
		}
		postExecution();
		for (UnsatisfiedConstraint uc : getContext().getUnsatisfiedConstraints()) {
			logger.warn(uc.getMessage());
		}
		return getContext().getUnsatisfiedConstraints();
	}
	

	@Override
	public String getChksum() {
		return this.chksum;
	}

	@Override
	public Set<IIncrementalModel> getTargets() {
		if (targets == null) {
			targets = new HashSet<>();
		}
		return targets;
	}
	

	public boolean isLive() {
		return live;
	}

	public boolean isOnlineExecution() {
		return getContext().isOnlineExecutionMode();
	}

	@Override
	public void listenToModelChanges(boolean listen) {
		logger.info("Listening to model changes");
		// Attach change listeners to models
		// FIXME I think we only want to attach to the model opened in the editor
		for (IModel model : this.getContext().getModelRepository().getModels()) {
			// FIXME We need to decouple this from the Model
			if (model instanceof IIncrementalModel) {
				logger.debug("Model {} is incremental.", model.getName());
				IIncrementalModel incrementalModel = (IIncrementalModel) model;
				if (incrementalModel.supportsNotifications()) {
					if (listen) {
						logger.info("Resgitering with model {} to recieve notifications.", model.getName());
						incrementalModel.registerModule(this);
						getTargets().add(incrementalModel);
					} else {
						logger.debug("Un-resgitering with model {} to recieve notifications.", model.getName());
						incrementalModel.unregisterModule(this);
						// incrementalModel.setDeliver(false); // DO NOT disable notifications unless
						// you are 100% no one else is listening
						getTargets().remove(incrementalModel);
					}
					incrementalModel.setDeliver(listen);
				}
			}
		}
	}

	@Override
	public void onChange(IIncrementalModel model, Object changedElement, String propertyName) throws EolRuntimeException {

		logger.info("On Change event for {} with property {}", changedElement, propertyName);
		IncrementalEvlExecutionStrategy strategy = new ChangedElementsStrategy(changedElement, model, propertyName, live);
		strategy.execute(getContext(), this);
	}

	@Override
	public void onCreate(IIncrementalModel model, Object newElement) throws EolRuntimeException {
		logger.info("On Craete event for {}", newElement);
		IncrementalEvlExecutionStrategy strategy = new NewElementsStrategy(newElement, model, live);
		strategy.execute(getContext(), this);
	}

	@Override
	public void onDelete(IIncrementalModel model, Object modelElement) throws EolRuntimeException {

		logger.info("On Delete event for {}", modelElement);
		Collection<IModelElementTrace> traces = new HashSet<>();
		Optional<IModelElementTrace> modelElementTrace = getContext().getTraceManager().getModelTraceRepository().getModelElementTraceFor(model.getModelUri(), model.getElementId(modelElement));
		traces.add(modelElementTrace.orElseThrow(() -> new IllegalStateException("Unable to find model element trace for " + modelElement)));
		logger.info("Executing indirect contexts and invariants");
		IncrementalEvlExecutionStrategy strategy = new DeletedElementsStrategy(traces, model, live);
		
		strategy.execute(getContext(), this);
	}

	@Override
	public boolean parse(String code) throws Exception {
		createChksumFromCode(code);
		return super.parse(code);
	}

	@Override
	public boolean parse(URI uri) throws Exception {
		if ("file".equals(uri.getScheme())) {
			createChksumFromFile(new File(uri));	
		}
		else {
		}
		return super.parse(uri);
	}
	

	/**
	 * Gets the constraint.
	 *
	 * @param checkTrace the check trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getTracedConstraint(ICheckTrace checkTrace) {
		IInvariantTrace invariantT = checkTrace.invariant().get();
		ConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantT, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an ICheckTrace. "
				+ "One could not be found for: " + checkTrace.toString());
	}

	/**
	 * Gets the constraint.
	 *
	 * @param messageTrace the message trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getTracedConstraint(IMessageTrace messageTrace) {
		IInvariantTrace invariantT = messageTrace.invariant().get();
		ConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantT, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an IMessageTrace. "
				+ "One could not be found for: " + messageTrace.toString());
	}

	/**
	 * Gets the constraint.
	 *
	 * @param invariantTrace the invariant trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getTracedConstraint(IInvariantTrace invariantTrace) {
		ConstraintContext conCtx = getConstraintContextForTrace(invariantTrace.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantTrace, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an IInvariantTrace. "
				+ "One could not be found for: " + invariantTrace.toString());
	}

	/**
	 * Gets the constraint.
	 *
	 * @param guardTrace the guard trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getTracedConstraint(IGuardTrace guardTrace) {
		IGuardedElementTrace limits = guardTrace.limits().get();
		assert limits instanceof IInvariantTrace;
		IInvariantTrace invariantT = (IInvariantTrace) limits;
		TracedConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantT, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an IGuardTrace. "
				+ "One could not be found for: " + guardTrace.toString());
	}
	
	/**
	 * Gets the constraint context.
	 *
	 * @param guardTrace the guard trace
	 * @return the constraint context
	 */
	@Override
	public TracedConstraintContext getTracedConstraintContext(IGuardTrace guardTrace) {
		IGuardedElementTrace limits = guardTrace.limits().get();
		assert limits instanceof IContextTrace;
		IContextTrace contextT = (IContextTrace) limits;
		TracedConstraintContext conCtx = getConstraintContextForTrace(contextT);
		if (conCtx != null) {
			return conCtx;
		}
		throw new IllegalStateException("A traced context should always be found for an IGuardTrace. "
				+ "One could not be found for: " + guardTrace.toString());
		
	}
	
	/**
	 * Gets the constraint context.
	 *
	 * @param contextTrace the context trace
	 * @return the constraint context
	 */
	@Override
	public TracedConstraintContext getTracedConstraintContext(IContextTrace contextTrace) {
		TracedConstraintContext conCtx = getConstraintContextForTrace(contextTrace);
		if (conCtx != null) {
			return conCtx;
		}
		throw new IllegalStateException("A traced context should always be found for an IContextTrace. "
				+ "One could not be found for: " + contextTrace.toString());
	}
	

	
   	/**
   	 * Switch the module execution between live and off-line. In live mode, the module will only
	 * responds to events received via the {@link #onChange(IIncrementalModel, Object, String)}, 
	 * {@link #onCreate(IIncrementalModel, Object)} and {@link #onDelete(IIncrementalModel, Object)}.
	 * In off-line mode, the complete script/code will be executed against the source models.
   	 * @param onlineExecution
   	 */
   	public void setMode(ExecutionMode mode) {
		getContext().setMode(mode);
	}
	
	/**
	 * Check all constraints in the Evl script/code
	 */
	protected void checkConstraints() throws EolRuntimeException {
		IEvlContext context = getContext();	
		logger.info("Executing ConstraintContexts");
		for (ConstraintContext constraintContext : getConstraintContexts()) {
			constraintContext.execute(preProcessConstraintContext(constraintContext), context);
		}
	}

	/**
	 * Create the traces for the module and the models
	 * @param etManager
	 * @param evlModuleTrace
	 * @param sourceChksum
	 * @throws EolRuntimeException
	 */
	private void createModuleAndModelTraces(String sourceChksum) throws EolRuntimeException {
		IEvlExecutionTraceManager etManager = getContext().getTraceManager();
		IEvlRootElementsFactory factory = etManager.getTraceFactory();
		logger.info("Getting the EvlModuleTrace.");
		IEvlModuleTrace evlModuleTrace = etManager.getExecutionTraceRepository().getEvlModuleTraceByIdentity(sourceChksum);
		if (evlModuleTrace == null) {
			try {
				evlModuleTrace = factory.createModuleTrace(sourceChksum);
				evlModuleTrace = etManager.getExecutionTraceRepository().add(evlModuleTrace);
			} catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
				throw new EolRuntimeException(e.getMessage());
			}
		}
		// Create ModelTraces for all the models
		for (IModel m : context.getModelRepository().getModels()) {
			if (m instanceof IIncrementalModel) {
				IIncrementalModel im = (IIncrementalModel) m;
				IModelTrace mt = etManager.getModelTraceRepository().getModelTraceByIdentity(im.getModelUri());
				if (mt == null) {
					try {
						mt = factory.createModelTrace(im.getModelUri());
						mt = etManager.getModelTraceRepository().add(mt);
					} catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
						throw new EolRuntimeException(e.getMessage());
					}
				}
				try {
					evlModuleTrace.getOrCreateModelAccess(m.getName(), mt);
				} catch (EolIncrementalExecutionException e) {
					logger.error("Error creating model access trace.", e);
					throw new EolRuntimeException("Error creating model access trace.");
				}
			} else {
				throw new EolRuntimeException(String.format(
						"Models used in incremental execution must implement the IIncrementalModel interface. Model %s does not.",
						m));
			}
		}
	}
	
	/**
   	 * Determine if the execution should be incremental. Execution goes into incremental if for
   	 * each of the models, the script has a model access.
	 * @param sourceChecksum 
   	 * @return
   	 * @throws EolRuntimeException 
   	 */
	private boolean determineIncremental(String sourceChecksum) throws EolRuntimeException {
		boolean incremental = true;
		IEvlExecutionTraceManager etManager = getContext().getTraceManager();
		// Need to check if we already have trace information for the (script, models)
		// tuple.
		try {
			IEvlModuleTrace evlModuleTrace = etManager.getExecutionTraceRepository()
					.getEvlModuleTraceByIdentity(sourceChecksum);
			if (evlModuleTrace == null) {
				incremental = false;
			} else {
				Set<IModelTrace> modelTraces = new HashSet<>();
				for (IModel m : context.getModelRepository().getModels()) {
					if (m instanceof IIncrementalModel) {
						IIncrementalModel im = (IIncrementalModel) m;
						IModelTrace mt = etManager.getModelTraceRepository().getModelTraceByIdentity(im.getModelUri());
						if (mt == null) {
							// If no trace information we need to re-execute
							incremental = false;
							break;
						}
						modelTraces.add(mt);
					}
				}
				if (incremental) {
					for (IModelTrace mt : modelTraces) {
						Iterator<IModelAccess> it = evlModuleTrace.models().get();
						boolean found = false;
						while (it.hasNext()) {
							IModelAccess ma = it.next();
							if (ma.modelTrace().get().equals(mt)) {
								found = true;
								break;
							}
						}
						if (!found) {
							incremental = false;
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			throw new EolRuntimeException("Unable to get the source check sum.", e);
		}
		return incremental;
	}
	
//	/**
//	 * Execute a single context against a single model
//	 * @param contextT
//	 * @param modelTrace
//	 */
//	private void executeContextTrace(IContextTrace contextT, IModelTrace modelTrace) {
//		logger.info("executeContextTrace: {}[{}]", contextT.getKind(), contextT.getIndex());
//		Iterator<IExecutionContext> iterator = contextT.executionContext().get();
//		while (iterator.hasNext()) {
//			IExecutionContext ec = iterator.next();
//			IModelElementTrace modelElementTrace = IncrementalUtils.asStream(ec.contextVariables().get())
//					.filter(v -> v.getName().equals("self")).findFirst().get().value().get();
//			ConstraintContext conCtx = getConstraintContextForTrace(contextT);
//			try {
//				String moduleUri = context.getModule().getUri().toString();
//				Object modelElement = getSelf(moduleUri, modelTrace, modelElementTrace);
//				if (conCtx.appliesTo(modelElement, getContext(), false)) {
//					for (ModuleElement me : conCtx.getChildren()) {
//						Constraint constraint = (Constraint) me;
//						constraint.execute(modelElement, getContext());
//					}
//				}
//			} catch (EolRuntimeException e) {
//				logger.info("Error executing ConstraintContext trace", e);
//			}
//		}
//	}
	
	
	/**
	 * Execute an invariant trace against a single model element. The model element is used as the
	 * invariants context
	 *
	 * @param self       the self
	 * @param invariantT the invariant T
	 * @return true, if the invariant changed from true to false
	 * @throws EolRuntimeException the eol runtime exception
	 */
//	private void executeInvariantTrace(Object self, IInvariantTrace invariantT) throws EolRuntimeException {
//
//		logger.info("executeInvariantTrace for {}", invariantT.getName());
//		ConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
//		if (conCtx != null) {
//			// Found the context, now find the invariant
//			// FIXME Need to modify EVL so we can individually modify constraints
//			// (invariants)
//			logger.debug("Found ConstraintContext for invariant.");
//			Constraint inv = conCtx.getConstraints().stream()
//					.filter(c -> c.getName().equals(invariantT.getName()))
//					.findFirst().get();
//			logger.debug("Found invariant for trace.");
//			inv.check(self, getContext());
//			// FIXME Need to execute related satisfies traces if result changed!
//			// FIXME If the result of the invariant changed, we need to re-execute all dependent
//			// (via satisfies) invariants.
//			return;
//		}
//		logger.info("Can not find matching constraint for trace.");
//		throw new IllegalStateException();
//	}
//	
//	/**
//	 * Execute all reexecution traces for the given object.Reexecution traces are created in
//	 * response to onChange, onDelete and onCreate events, or generated by an
//	 * IncrementalEvlExecutionStrategy.
//	 * 
//	 * @param moduleUri
//	 * @param model
//	 * @param traces
//	 * @param modelObject
//	 */
//	void executeTraces(String moduleUri, IIncrementalModel model, Set<TraceReexecution> traces,
//			Object modelObject) {
//		if (traces == null || traces.isEmpty()) {
//			return;
//		}
//		for (TraceReexecution t : traces) {
//			// We need to be a bit smart, that is, there is a hierarchy of execution, for
//			// example:
//			// - if we have both a check and a guard trace for the same invariant it will be
//			// good to execute
//			// the guard first, as if the guard fails then there is no need to execute the
//			// check. Actually,
//			// if the guard is false, the check trace should be deleted.
//			// If the trace is only a message, we can only re-execute that (FIGURE OUT HOW
//			// TO UPDATE THE EGL VIEW)
//			// - If we have satisfies traces they should only trigger an execution
//			// - If the invariant that owns the other evl constructs does not has a
//			// modelaccess for
//			// the modelObject, then for each modelaccess of the invariant we need to
//			// re-execute it (i.e. the
//			// modelObject was not the one being checked by the invariant).
//			// Do allInstances access supersede property access for the same object?
//
//			// ALL THIS RELATES TO TRIMMING THE TRACE MODEL. READ SOME REALTED LIT ABOUT IT.
//
//			// To start, just re-execute the parent invariant if it matches the modelObject,
//			// if not do it for
//			// all model accesses. Fine granularity comes next!
//
//			// This could be done with runtime dispatch, but having distinct methods might
//			// be required?
//			try {
//				t.reexecute(getContext());
////				if (t instanceof ReexecutionGuardTrace) {
////				
////					// processGuardReexecution(moduleUri, model, (ReexecutionGuardTrace) t, modelObject);
////				}
////				else if (t instanceof ReexecutionCheckTrace) {
////					processCheckReexecution(moduleUri, model, (ReexecutionCheckTrace) t, modelObject);
////				}
////				else if (t instanceof ReexecutionMessageTrace) {
////					processMessageReexecution(moduleUri, model, (ReexecutionMessageTrace) t, modelObject);
////				}
////				else if (t instanceof ReexecutionInvariantTrace) {
////					processInvariantReexecution(moduleUri, model, (ReexecutionInvariantTrace) t, modelObject);
////				}
//			} catch (EolRuntimeException e) {
//				logger.error("Error reexecuting trace", e);
//			}
//		}
//		getContext().getConstraintTrace().clear();
//	}
	
	/**
	 * Execute the module in incremental mode. The source models are traversed and changes detected
	 * by comparing the property's values of the models' elements against the stored values.
	 * @param etManager
	 * @param sourceChksum The checksum for the file/code executed.
	 * @throws EolRuntimeException if there is an error accessing information from any of the models.
	 */
	private void executeIncremental(String sourceChksum) throws EolRuntimeException {
		// FIXME This should be injected so we can try different strategies!?
		IncrementalEvlExecutionStrategy strategy = new SimpleStrategy();
		strategy.execute(getContext(), this);
	}
	
	/**
	 * Gets the constraint context for trace.
	 *
	 * @param trace the trace
	 * @return the constraint context for trace
	 */
	// TODO Adding a cache could help a bit
	private TracedConstraintContext getConstraintContextForTrace(IContextTrace trace) {
		for (ConstraintContext conCtx : getConstraintContexts()) {
			TracedConstraintContext tConCtx = (TracedConstraintContext) conCtx;
			if (conCtx.getTypeName().equals(trace.getKind()) && (tConCtx.getIndex() == trace.getIndex())) {
				tConCtx.setModuleElementTrace(trace);
				return tConCtx;
			}
		}
		return null;
	}
	
	/**
	 * Find traced constraint.
	 *
	 * @param invariantTrace the invariant trace
	 * @param conCtx the con ctx
	 * @return the traced constraint
	 */
	// TODO Cache?
	private TracedConstraint findTracedConstraint(IInvariantTrace invariantTrace, ConstraintContext conCtx) {
		TracedConstraint tracedConstraint = (TracedConstraint) conCtx.getConstraints().stream()
				.filter(c -> c.getName().equals(invariantTrace.getName()))
				.findFirst().get();
		tracedConstraint.setModuleElementTrace(invariantTrace);
		return tracedConstraint;
	}

//	private ConstraintContext getConstraintContextForTrace(IContextTrace trace) {
//		int index = 1;
//		for (ConstraintContext conCtx : getConstraintContexts()) {
//			if (conCtx.getTypeName().equals(trace.getKind()) && (index++ == trace.getIndex())) {
//				return conCtx;
//			}
//		}
//		return null;
//	}
	
	/**
	 * Create an MD5 digest of the provided code
	 * @param code
	 * @throws Exception 
	 */
	private void createChksumFromCode(String code) throws Exception {
		MessageDigest complete = null;
		try {
			complete = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// pass, we are using a known algorithm
		}
		complete.update(code.getBytes());
        //Get the hash's bytes
        this.chksum = getSourceChecksum(complete.digest());
	}


	/**
	 * Create an MD5 digest of the the provided file
	 * @param file
	 * @throws Exception 
	 */
	private void createChksumFromFile(File file) throws Exception {
		MessageDigest complete = null;
		try (InputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[1024];
			try {
				complete = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// pass, we are using a known algorithm
			}
			int numRead = -1;
			do {
				numRead = fis.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
		} catch (FileNotFoundException e) {
			throw new EpsilonExecutorException("Source file not ready. Make sure parse() is invoked befor execute().", e);
		} catch (IOException e) {
			throw new EpsilonExecutorException("There was an error reading the source file.", e);
		}
		this.chksum = getSourceChecksum(complete == null ? new byte[0] : complete.digest());
	}

	private ModuleElement getOrCreateCheckBlock() {
		if (incrementalMode) {
			return new TracedExecutableBlock<ICheckTrace, Boolean>(Boolean.class);
		} else {
			return new ExecutableBlock<Boolean>(Boolean.class);
		}
	}

	private ExecutableBlock<Boolean> getOrCreateGuardBlock() {
		if (incrementalMode) {
			return new TracedExecutableBlock<IGuardTrace, Boolean>(Boolean.class);
		} else {
			return new ExecutableBlock<Boolean>(Boolean.class);
		}
	}

	private ModuleElement getOrCreateMessageBlock() {
		if (incrementalMode) {
			return new TracedExecutableBlock<IMessageTrace, String>(String.class);
		} else {
			return new ExecutableBlock<String>(String.class);
		}
	}

	/**
	 * @return
	 */
	private TracedConstraintContext getOrCreateTracedConstraintContext() {
		return new TracedConstraintContext();
	}

	
	private String getSourceChecksum(byte[] digest) throws Exception {
		String result = "";
		for (int i = 0; i < digest.length; i++) {
			result += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
	
	protected void postExecution() throws EolRuntimeException {
		logger.info("Executing fixer");
		super.postExecution();
		logger.info("Persisting traces");
		getContext().getTraceManager().persistTraceInformation();
		if (getContext().isOnlineExecutionMode()) {
			logger.info("Going to live execution.");
//			for (ConstraintContext conCtx : getConstraintContexts()) { 
//				((TracedConstraintContext)conCtx).goOnline();
//			}
			// Clear cache
			getContext().getConstraintTrace().clear();
			listenToModelChanges(true);
			live = true;
		}
	}

	protected void prepareExecution() throws EolRuntimeException {
		logger.info("Executing pre{}");
		super.prepareExecution();
		getContext().setOperationFactory(new EvlOperationFactory());
		getContext().getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));
		logger.info("Adding incremental execution listeners");
		for (IExecutionListener iel : getContext().getIncrementalExecutionListeners()) {
			context.getExecutorFactory().addExecutionListener(iel);
		}
	}


	
//	/**
//	 * Ideal: Re-execute the check and if result changed, execute related invariants (via satisfies)
//	 * 
//	 * Current: The check invariant is re-executed
//	 *
//	 * @param moduleUri the module uri
//	 * @param model the model
//	 * @param trace the t
//	 * @param modelObject the model object
//	 * @throws EolRuntimeException the eol runtime exception
//	 */
//	private void processCheckReexecution(
//		String moduleUri,
//		IIncrementalModel model,
//		ReexecutionCheckTrace trace,
//		Object modelObject)
//		throws EolRuntimeException {
//		logger.info("Re-executing CheckTrace");
//		IModelElementTrace selfTrace = IncrementalUtils.asStream(trace.getExexutionContext().contextVariables().get())
//			.filter(v -> v.getName().equals("self")).findFirst().get().value().get();
//		IModelTrace modelTrace = selfTrace.modelTrace().get();
//		Object self = getSelf(moduleUri, modelTrace, selfTrace);
//		IInvariantTrace invariantT = trace.getModuleElementTrace().invariant().get();
//		executeInvariantTrace(self, invariantT);
//	}
	
//	/**
//	 * Ideal: Reexecute the guard, if the result changes from true->false, then all traces
//	 * of the guarded element (and its children) must be deleted. If the result
//	 * changes from false->true, then the guarded element must be reexecuted. If the
//	 * results didn't change, then nothing else is executed.
//	 * 
//	 * Current: The guarder element is rexecuted
//	 *
//	 * @param moduleUri 			the module uri
//	 * @param model 				the model
//	 * @param trace 					the trace
//	 * @param modelObject 			the model object
//	 * @throws EolRuntimeException 	If there is an exception executing the guard
//	 */
//	private void processGuardReexecution(
//		//String moduleUri,
//		//IIncrementalModel model,
//		ReexecutionGuardTrace trace
//		// Object modelObject
//		)
//		throws EolRuntimeException {
//		
//		logger.info("Re-executing GuardTrace");
//		IExecutionContext ec = trace.getExexutionContext();
//		//IModelElementTrace selfTrace = trace.getSelfTrace();
//		IGuardedElementTrace guardedElement = trace.getGuardedElementTrace();
//		//IModelTrace modelTrace = trace.getModelTrace();
//		Object self = trace.getSelfBindValue(); //moduleUri, modelTrace, selfTrace);
//		
////		IModelElementTrace selfTrace = IncrementalUtils.asStream(ec.contextVariables().get())
////				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();
////		IGuardedElementTrace guardedElement = trace.getModuleElementTrace().limits().get();
////		IModelTrace modelTrace = getContext().getTraceManager().getModelTraceRepository()
////				.getModelTraceForModule(model.getModelUri(), moduleUri);
//		//Object self = getSelf(moduleUri, modelTrace, selfTrace);
//		if (guardedElement instanceof IInvariantTrace) {
//			logger.info("GuardTrace applies to Invariant");
//			IInvariantTrace invariantT = (IInvariantTrace) guardedElement;
//			executeInvariantTrace(self, invariantT);
//		} else {
//			logger.info("GuardTrace applies to Context");
//			IContextTrace contextT = (IContextTrace) guardedElement;
//			// Execute all the invariants in the InvariantContext
//			ConstraintContext conCtx = getConstraintContextForTrace(contextT);
//			if (conCtx.appliesTo(self, getContext())) {
//				for (Constraint constraint : conCtx.getConstraints()) {
//					// Find trace
//					Iterable<IInvariantTrace> iterable2 = () -> contextT.constraints().get();
//					IInvariantTrace invariantT = StreamSupport.stream(iterable2.spliterator(), false)
//							.filter(i -> i.getName().equals(constraint.getName())).findFirst().orElse(null);
//					if (invariantT != null) {
//						constraint.check(self, getContext());
//					}
//				}
//			}
//		}
//	}
	
//	/**
//	 * Reexecute an invariant
//	 * @param moduleUri
//	 * @param model
//	 * @param t
//	 * @param modelObject
//	 * @throws EolRuntimeException
//	 */
//	private void processInvariantReexecution(
//	    String moduleUri,
//	    IIncrementalModel model,
//	    ReexecutionInvariantTrace trace,
//	    Object modelObject)
//		throws EolRuntimeException {
//		logger.info("Re-executing InvariantTrace");
//		IExecutionContext ec = trace.getExexutionContext();
//		IModelTrace modelTrace = getContext().getTraceManager().getModelTraceRepository()
//				.getModelTraceForModule(model.getModelUri(), moduleUri);
//		IModelElementTrace selfTrace = IncrementalUtils.asStream(ec.contextVariables().get())
//				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();
//		IInvariantTrace invariantT = trace.getModuleElementTrace();
//		Object self = getSelf(moduleUri, modelTrace, selfTrace);
//		executeInvariantTrace(self, invariantT);
//		
//	}
	
//	/**
//	 * Ideal: Re-execute the message and update the unsatisfied constraints view.
//	 * 
//	 * Current: The message's invariant is re-executed
//	 *
//	 * @param moduleUri 	the module uri
//	 * @param model 		the model
//	 * @param trace 			the trace
//	 * @param modelObject 	the model object
//	 * @throws EolRuntimeException If there is an exception executing the message
//	 */
//	private void processMessageReexecution(
//		String moduleUri,
//		IIncrementalModel model,
//		ReexecutionMessageTrace trace,
//		Object modelObject)
//		throws EolRuntimeException {
//		logger.info("Re-executing MessageTrace");
//		IExecutionContext ec = trace.getExexutionContext();
//		IModelElementTrace selfTrace = IncrementalUtils.asStream(ec.contextVariables().get())
//			.filter(v -> v.getName().equals("self")).findFirst().get().value().get();
//		IModelTrace modelTrace = getContext().getTraceManager().getModelTraceRepository()
//				.getModelTraceForModule(model.getModelUri(), moduleUri);
//		Object self = getSelf(moduleUri, modelTrace, selfTrace);
//		IInvariantTrace invariantT = trace.getModuleElementTrace().invariant().get();
//		executeInvariantTrace(self, invariantT);
//	}
	

}
