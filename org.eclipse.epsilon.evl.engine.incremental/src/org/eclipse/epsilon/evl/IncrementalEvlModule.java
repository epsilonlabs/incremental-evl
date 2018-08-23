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
package org.eclipse.epsilon.evl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.IEvlTraceFactory;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.dom.TracedGuardBlock;
import org.eclipse.epsilon.evl.incremental.execute.BasicEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.parse.EvlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * AnIncrementalEvlModule.
 */
public class IncrementalEvlModule extends EvlModule implements
		IEvlModuleIncremental<IEvlModuleTraceRepository, IEvlTraceFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory>> {

	private static final Logger logger = LoggerFactory.getLogger(IncrementalEvlModule.class);

	/**
	 * Flag to indicate incremental execution.
	 */
	private boolean incrementalMode = true;

	/**
	 * Flag to indicate that we are on live mode, i.e. listening to model changes
	 */
	private boolean onlineExecution;

	/**
	 * If onlineExecution, this flags signals when initial execution has finished
	 * and the module is listening to model changes.
	 */
	private boolean live = false;

	/**
	 * The set of models from which the module receives notification.
	 */
	Set<IIncrementalModel> targets;

	/** The context. */
	protected IncrementalEvlContext<IEvlModuleTraceRepository, IEvlTraceFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory>> context;

	public IncrementalEvlModule() {
		super();
		this.context = new IncrementalEvlContext<>();

	}

	public void injectTraceManager(Module incrementalGuiceModule) {
		Injector injector = Guice.createInjector(incrementalGuiceModule);
		IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory> etManager = injector
				.getInstance(BasicEvlExecutionTraceManager.class);
		this.context.setTraceManager(etManager);
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

	/**
	 * @return
	 */
	private TracedConstraintContext getOrCreateTracedConstraintContext() {
		return new TracedConstraintContext();
	}

	@Override
	public Object execute() throws EolRuntimeException {
		if (!incrementalMode) {
			logger.info("Invoked in non-incremental model. Delegating to normal EVL.");
			return super.execute();
		}
		if (isLive()) {
			logger.info("Execution is Live.");
			return null;
		}
		prepareContext(getContext());
		getContext().setOperationFactory(new EvlOperationFactory());
		getContext().getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));

		IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory> etManager = context.getTraceManager();
		IEvlTraceFactory factory = etManager.getTraceFactory();

		String evlScripPath = "String"; // If the module is invoked with a string rather than a file
		if (this.sourceUri != null) {
			evlScripPath = this.sourceUri.toString();
		}
		IEvlModuleTrace evlModuleTrace;
		logger.info("Getting the EvlModuleTrace.");
		evlModuleTrace = etManager.getExecutionTraceRepository().getEvlModuleTraceByIdentity(evlScripPath);
		if (evlModuleTrace == null) {
			try {
				// evlModuleTrace = new EvlModuleTrace(evlScripPath);
				evlModuleTrace = factory.createModuleTrace(evlScripPath);
				etManager.getExecutionTraceRepository().add(evlModuleTrace);
			} catch (TraceModelDuplicateRelation e) {
				throw new EolRuntimeException(e.getMessage());
			}
		}
		// Create ModelTraces for all the models
		for (IModel m : context.getModelRepository().getModels()) {
			if (m instanceof IIncrementalModel) {
				try {
					IModelTrace mt = factory.createModelTrace(((IIncrementalModel) m).getModelUri());
					etManager.getModelTraceRepository().add(mt);
					evlModuleTrace.getOrCreateModelAccess(m.getName(), mt);
				} catch (TraceModelDuplicateRelation | EolIncrementalExecutionException e) {
					throw new EolRuntimeException(e.getMessage());
				}
			} else {
				throw new EolRuntimeException(String.format(
						"Models used in incremental execution must implement the IIncrementalModel interface. Model %s does not.",
						m));
			}
		}

		logger.info("Adding execution listeners");
		context.getExecutorFactory().addExecutionListener(context.getAllInstancesInvocationExecutionListener());
		context.getExecutorFactory().addExecutionListener(context.getPropertyAccessExecutionListener());
		context.getExecutorFactory().addExecutionListener(context.getSatisfiesListener());

		// Perform evaluation
		logger.info("Executing pre{}");
		execute(getPre(), context);
		logger.info("Executing contexts");
		for (ConstraintContext conCtx : getConstraintContexts()) {
			conCtx.checkAll(getContext());
		}
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			logger.warn(uc.getMessage());
		}
		if (fixer != null) {
			logger.info("Executing fixer");
			fixer.fix(this);
		}
		logger.info("Executing post{}");
		execute(getPost(), getContext());
		logger.info("Persisting traces");
		etManager.persistTraceInformation();
		if (onlineExecution) {
			logger.info("Going to live execution.");
//			for (ConstraintContext conCtx : getConstraintContexts()) { 
//				((TracedConstraintContext)conCtx).goOnline();
//			}
			// Clear cache
			getContext().getConstraintTrace().clear();
			listenToModelChanges(true);
			live = true;
		}
		return null;
	}

	@Override
	public IncrementalEvlContext<IEvlModuleTraceRepository, IEvlTraceFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory>> getContext() {
		return context;
	}

	@Override
	public Set<IIncrementalModel> getTargets() {
		if (targets == null) {
			targets = new HashSet<>();
		}
		return targets;
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
						incrementalModel.getModules().add(this);
						getTargets().add(incrementalModel);
					} else {
						logger.debug("Un-resgitering with model {} to recieve notifications.", model.getName());
						incrementalModel.getModules().remove(this);
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
	public void onChange(IIncrementalModel model, Object object, String propertyName) throws EolRuntimeException {

		logger.info("On Change event for {} with property {}", object, propertyName);
		String elementId = model.getElementId(object);
		IEvlModuleTraceRepository repo = getContext().getTraceManager().getExecutionTraceRepository();
		String moduleUri = context.getModule().getUri().toString();
		Set<IModuleElementTrace> traces = repo.findPropertyAccessExecutionTraces(moduleUri, model.getModelUri(),
				elementId, propertyName);
		traces.addAll(repo.findAllInstancesExecutionTraces(moduleUri, model.getTypeNameOf(object)));
		logger.debug("Found {} traces for the element", traces.size());
		executeTraces(moduleUri, model, traces, object);
	}

	@Override
	public void onCreate(IIncrementalModel model, Object newElement) throws EolRuntimeException {

		logger.info("On Craete event for {}", newElement);
		IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory> etManager = context.getTraceManager();
		// Do we need to execute the pre blocks to restore context?
		// logger.info("Executing pre{}");
		// execute(getPre(), context);

		for (ConstraintContext conCtx : getConstraintContexts()) {
			try {
				if (conCtx.appliesTo(newElement, getContext())) {
					logger.info("Found matching context, executing");
					conCtx.checkAll(getContext());
				}
			} catch (EolRuntimeException e) {
				logger.error("Error executing contexts for new element", e);
			}
		}
		IEvlModuleTraceRepository repo = getContext().getTraceManager().getExecutionTraceRepository();
		String moduleUri = context.getModule().getUri().toString();
		Set<IModuleElementTrace> traces = repo.findAllInstancesExecutionTraces(moduleUri,
				model.getTypeNameOf(newElement));
		executeTraces(moduleUri, model, traces, newElement);
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			logger.debug(uc.getMessage());
		}
		logger.info("Persisting traces");
		etManager.persistTraceInformation();
		getContext().getConstraintTrace().clear();
	}

	@Override
	public void onDelete(IIncrementalModel model, Object modelElememt) throws EolRuntimeException {

		logger.info("On Delete event for {}", modelElememt);
		String elementUri = model.getElementId(modelElememt);
		IEvlModuleTraceRepository repo = getContext().getTraceManager().getExecutionTraceRepository();
		String moduleUri = context.getModule().getUri().toString();
		Set<IModuleElementTrace> traces = repo.findIndirectExecutionTraces(moduleUri, elementUri, model.getModelUri());

		IModelTrace modelTrace = repo.getModelTraceForModule(model.getModelUri(), moduleUri);
		logger.info("Executing indirect contexts");
		traces.stream().filter(IContextTrace.class::isInstance).map(IContextTrace.class::cast)
				.forEach(ct -> executeContextTrace(ct, modelTrace));
		logger.info("Executing indirect invariants");
		traces.stream().filter(IInvariantTrace.class::isInstance).map(IInvariantTrace.class::cast).forEach(it -> {
			try {
				executeTrace(moduleUri, model, it, modelElememt);
			} catch (EolRuntimeException e) {
				logger.info("Error executing InvariantTrace trace", e);
			}
		});
		
		// Remove unsatisfied constraints related to the element.
		Iterator<UnsatisfiedConstraint> it = getContext().getUnsatisfiedConstraints().iterator();
		while (it.hasNext()) {
			UnsatisfiedConstraint usc = it.next();
			if (model.getElementId(usc.getInstance()).equals(elementUri)) {
				logger.info("Removing unsatisfied contraint");
				it.remove();
			}
		}
		repo.removeTraceInformation(moduleUri, elementUri, model.getModelUri());
		getContext().getConstraintTrace().clear();
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public void setOnlineExecution(boolean onlineExecution) {
		this.onlineExecution = onlineExecution;
	}

	public boolean isOnlineExecution() {
		return onlineExecution;
	}

	private void executeContextTrace(IContextTrace contextT, IModelTrace modelTrace) {
		logger.info("executeContextTrace: {}[{}]", contextT.getKind(), contextT.getIndex());
		Iterable<IModelElementVariable> iterable = () -> contextT.executionContext().get().contextVariables().get();
		IModelElementTrace modelElementTrace = StreamSupport.stream(iterable.spliterator(), false)
				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();
		ConstraintContext conCtx = getConstraintContextForTrace(contextT);
		try {
			String moduleUri = context.getModule().getUri().toString();
			Object self = getSelf(moduleUri, modelTrace, modelElementTrace);
			if (conCtx.appliesTo(self, getContext(), false)) {
				for (ModuleElement me : conCtx.getChildren()) {
					Constraint constraint = (Constraint) me;
					if (!constraint.isLazy(getContext()) && constraint.appliesTo(self, getContext(), false)) {
						constraint.check(self, context, false);
					}
				}
			}
		} catch (EolRuntimeException e) {
			logger.info("Error executing ConstraintContext trace", e);
		}
	}

	private void executeTraces(String moduleUri, IIncrementalModel model, Set<IModuleElementTrace> traces,
			Object modelObject) {
		if (traces == null || traces.isEmpty()) {
			return;
		}
		for (IModuleElementTrace t : traces) {
			// We need to be a bit smart, that is, there is a hierarchy of execution, for
			// example:
			// - if we have both a check and a guard trace for the same invariant it will be
			// good to execute
			// the guard first, as if the guard fails then there is no need to execute the
			// check. Actually,
			// if the guard is false, the check trace should be deleted.
			// If the trace is only a message, we can only re-execute that (FIGURE OUT HOW
			// TO UPDATE THE EGL VIEW)
			// - If we have satisfies traces they should only trigger an execution
			// - If the invariant that owns the other evl constructs does not has a
			// modelaccess for
			// the modelObject, then for each modelaccess of the invariant we need to
			// re-execute it (i.e. the
			// modelObject was not the one being checked by the invariant).
			// Do allInstances access superseed property access for the same object?

			// ALL THIS RELATES TO TRIMMING THE TRACE MODEL. READ SOME REALTED LIT ABOUT IT.

			// To start, just re-execute the parent invariant if it matches the modelObject,
			// if not do it for
			// all model accesses. Fine granularity comes next!

			// This could be done with runtime dispatch, but having distinct methods might
			// be required?
			try {
				if (t instanceof IGuardTrace) {
					executeTrace(moduleUri, model, (IGuardTrace) t, modelObject);
				} else if (t instanceof ISatisfiesTrace) {
					executeTrace(moduleUri, model, (ISatisfiesTrace) t, modelObject);
				} else if (t instanceof ICheckTrace) {
					executeTrace(moduleUri, model, (ICheckTrace) t, modelObject);
				} else if (t instanceof IMessageTrace) {
					executeTrace(moduleUri, model, (IMessageTrace) t, modelObject);
				}
			} catch (EolRuntimeException e) {
				logger.error("Error reexecuting trace", e);
			}
		}
		getContext().getConstraintTrace().clear();
	}

	private void executeTrace(String moduleUri, IIncrementalModel model, IInvariantTrace t, Object modelObject)
			throws EolRuntimeException {
		logger.info("Re-executing InvariantTrace");
		IContextModuleElementTrace ruleTrace = t.parentTrace().get();
		Iterable<IModelElementVariable> iterable = () -> ruleTrace.executionContext().get().contextVariables().get();
		IModelElementTrace selfTrace = StreamSupport.stream(iterable.spliterator(), false)
				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();// .getUri();
		IModelTrace modelTrace = getContext().getTraceManager().getExecutionTraceRepository()
				.getModelTraceForModule(model.getModelUri(), moduleUri);
		Object self = getSelf(moduleUri, modelTrace, selfTrace);
		executeInvariantTrace(self, t);
	}

	/**
	 * Reexecute the guard, if the result changes from true->false, then all traces
	 * of the guarded element (and its children) must be deleted. If the result
	 * changes from false->true, then the guarded element must be reexecuted. If the
	 * results didn't change, then nothing else is executed.
	 * 
	 * @param modelName2
	 * 
	 * 
	 * @param t           the guard trace
	 * @param modelObject
	 * @throws EolRuntimeException
	 */
	private void executeTrace(String moduleUri, IIncrementalModel model, IGuardTrace t, Object modelObject)
			throws EolRuntimeException {
		logger.info("Re-executing GuardTrace");
		IContextModuleElementTrace ruleTrace = t.parentTrace().get();
		IGuardedElementTrace guardedElement = t.limits().get();
		Iterable<IModelElementVariable> iterable = () -> ruleTrace.executionContext().get().contextVariables().get();
		IModelElementTrace selfTrace = StreamSupport.stream(iterable.spliterator(), false)
				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();

		IModelTrace modelTrace = getContext().getTraceManager().getExecutionTraceRepository()
				.getModelTraceForModule(model.getModelUri(), moduleUri);
		Object self = getSelf(moduleUri, modelTrace, selfTrace);
		if (guardedElement instanceof IInvariantTrace) {
			logger.info("GuardTrace applies to Invariant");
			IInvariantTrace invariantT = (IInvariantTrace) guardedElement;
			executeInvariantTrace(self, invariantT);
		} else {
			logger.info("GuardTrace applies to Context");
			IContextTrace contextT = (IContextTrace) guardedElement;
			// Execute all the invariants in the InvariantContext
			ConstraintContext conCtx = getConstraintContextForTrace(contextT);
			if (conCtx.appliesTo(self, getContext())) {
				for (Constraint constraint : conCtx.getConstraints()) {
					// Find trace
					Iterable<IInvariantTrace> iterable2 = () -> contextT.constraints().get();
					IInvariantTrace invariantT = StreamSupport.stream(iterable2.spliterator(), false)
							.filter(i -> i.getName().equals(constraint.getName())).findFirst().orElse(null);
					if (invariantT != null) {
						constraint.check(self, getContext());
					}
				}
			}
		}
	}

	private void executeTrace(String moduleUri, IIncrementalModel model, IMessageTrace t, Object modelObject)
			throws EolRuntimeException {

		logger.info("Re-executing MessageTrace");
		IContextModuleElementTrace ruleTrace = t.parentTrace().get();
		Iterable<IModelElementVariable> iterable = () -> ruleTrace.executionContext().get().contextVariables().get();
		IModelElementTrace selfTrace = StreamSupport.stream(iterable.spliterator(), false)
				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();
		IModelTrace modelTrace = getContext().getTraceManager().getExecutionTraceRepository()
				.getModelTraceForModule(model.getModelUri(), moduleUri);
		Object self = getSelf(moduleUri, modelTrace, selfTrace);
		IInvariantTrace invariantT = t.invariant().get();
		executeInvariantTrace(self, invariantT);
	}

	private void executeTrace(String moduleUri, IIncrementalModel model, ICheckTrace t, Object modelObject)
			throws EolRuntimeException {

		logger.info("Re-executing CheckTrace");
		IContextModuleElementTrace ruleTrace = t.parentTrace().get();

		IModelElementTrace selfTrace = IncrementalUtils
				.asStream(ruleTrace.executionContext().get().contextVariables().get())
				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();// .getUri();

		IModelTrace modelTrace = getContext().getTraceManager().getExecutionTraceRepository()
				.getModelTraceForModule(model.getModelUri(), moduleUri);
		Object self = getSelf(moduleUri, modelTrace, selfTrace);
		IInvariantTrace invariantT = t.invariant().get();
		executeInvariantTrace(self, invariantT);
	}

	private void executeTrace(String moduleUri, IIncrementalModel model, ISatisfiesTrace t, Object modelObject)
			throws EolRuntimeException {

		logger.info("Re-executing SatisfiesTrace");
		IContextModuleElementTrace ruleTrace = t.parentTrace().get();
		Iterable<IModelElementVariable> iterable = () -> ruleTrace.executionContext().get().contextVariables().get();
		IModelElementTrace selfTrace = StreamSupport.stream(iterable.spliterator(), false)
				.filter(v -> v.getName().equals("self")).findFirst().get().value().get();// .getUri();
		IModelTrace modelTrace = getContext().getTraceManager().getExecutionTraceRepository()
				.getModelTraceForModule(model.getModelUri(), moduleUri);
		Object self = getSelf(moduleUri, modelTrace, selfTrace);
		IInvariantTrace invariantT = t.invariant().get();
		executeInvariantTrace(self, invariantT);
	}

	/**
	 * Execute invariant trace.
	 *
	 * @param self       the self
	 * @param invariantT the invariant T
	 * @return true, if the invariant changed from true to false
	 * @throws EolRuntimeException the eol runtime exception
	 */
	private void executeInvariantTrace(Object self, IInvariantTrace invariantT) throws EolRuntimeException {

		logger.info("executeInvariantTrace for {}", invariantT.getName());
		ConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			// Found the context, now find the invariant
			// FIXME Need to modify EVL so we can individually modify constraints
			// (invariants)
			logger.debug("Found ConstraintContext for invariant.");
			Constraint inv = conCtx.getModuleElements().stream().map(Constraint.class::cast)
					.filter(c -> c.getName().equals(invariantT.getName())).findFirst().get();
			logger.debug("Found invariant for trace.");
			inv.check(self, getContext());
			// FIXME Need to execute related satisfies traces if result changed!
			return;
		}
		logger.info("Can not find matching constraint for trace.");
		throw new IllegalStateException();
	}

	private ConstraintContext getConstraintContextForTrace(IContextTrace trace) {
		int index = 1;
		for (ConstraintContext conCtx : getConstraintContexts()) {
			if (conCtx.getTypeName().equals(trace.getKind()) && (index++ == trace.getIndex())) {
				return conCtx;
			}
		}
		return null;
	}

	private ModuleElement getOrCreateMessageBlock() {
		if (incrementalMode) {
			return new TracedExecutableBlock<IMessageTrace, String>(String.class);
		} else {
			return new ExecutableBlock<String>(String.class);
		}
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
			return new TracedGuardBlock(Boolean.class);
		} else {
			return new ExecutableBlock<Boolean>(Boolean.class);
		}
	}

	/**
	 * Retrieve the object that represents self
	 * 
	 * @param moduleUri     TODO
	 * @param selfTrace     the model element trace that holds the reference to the
	 *                      self element
	 * @param model         the model in which the event was generated
	 * @param modelObject   the object that generated the event
	 * @param selfModelName the name of the model that owns the self reference
	 * 
	 * @return
	 * @throws EolRuntimeException if ther is an error retrieving the element
	 */
	private Object getSelf(String moduleUri, IModelTrace modelTrace, IModelElementTrace selfTrace)
			throws EolRuntimeException {
		logger.info("Resolve self element.");
		IEvlModuleTraceRepository repo = getContext().getTraceManager().getExecutionTraceRepository();
		IEvlModuleTrace moduleTrace = repo.getEvlModuleTraceByIdentity(moduleUri);

		Optional<IModelAccess> ma = IncrementalUtils.asStream(moduleTrace.models().get())
				.filter(m -> m.modelTrace().get().equals(modelTrace)).findFirst();
		if (!ma.isPresent()) {
			throw new EolRuntimeException(
					"No model access information found for " + modelTrace.getUri() + " for the given module.");
		}
		String selfModelName = ma.get().getModelName();
		IModel selfModel = null;
		try {
			selfModel = getContext().getModelRepository().getModelByName(selfModelName);
		} catch (EolModelNotFoundException e1) {
			logger.error(
					"Error locating model with name {} in current execution context. Make sure a model with the given name has been added to the launch configuration or context",
					selfModelName, e1);
			throw new EolRuntimeException("No matching model found in context");
		}
		Object self = selfModel.getElementById(selfTrace.getUri());
		return self;
	}

}
