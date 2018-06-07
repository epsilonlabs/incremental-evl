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
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.base.incremental.trace.IRuleTrace;
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
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.dom.TracedGuardBlock;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceRepository;
import org.eclipse.epsilon.evl.incremental.execute.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;
import org.eclipse.epsilon.evl.parse.EvlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * AnIncrementalEvlModule.
 */
// FIXME This changes should be merged into EVL Module and use the incremental execution flag
// to enable the incremental behaviour
// FIXME Some of this API should belong to the base Module (e.g. the trace model API can be shared by all languages
public class IncrementalEvlModule extends EvlModule implements IEvlModuleIncremental {
	
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
	 * If onlineExecution, this flags signals when initial execution has finished and the module is
	 * listening to model changes.
	 */
	private boolean live = false;

	
	/**
	 * The trace manager associated to this module. The manager should be injected via {@link #setExecutionTraceManager(IEvlExecutionTraceManager)}
	 * after creation of the module (i.e. launch configuration execution). This allows for different
	 * implementations of execution trace managers to be used for execution.
	 */
	// FIXME Get from the TracedEvlContext
	//private IEvlExecutionTraceManager<IEvlModuleExecution> etManager = null;

	/**
	 * The set of models which the module receives notification.
	 */
	Set<IIncrementalModel> targets;
	
	protected IEvlModuleTrace evlModuleTrace;

	/** The context. */
	protected IncrementalEvlContext context;
	
	public IncrementalEvlModule() {
		super();
		context = new IncrementalEvlContext();
	}
	
	@Override
	public ModuleElement adapt(AST cst, ModuleElement parentAst) {
		logger.debug("Adapting {} from {}", cst, parentAst.getUri());
		switch (cst.getType()) {
			case EvlParser.MESSAGE: return createMessageBlock();
			case EvlParser.CHECK: return createCheckBlock();
			case EvlParser.GUARD:
				if (!(parentAst instanceof Fix)) {
					return createGuardBlock();
				}
				break;
			case EvlParser.CONSTRAINT:
			case EvlParser.CRITIQUE:
				if (incrementalMode) {
					return new TracedConstraint();
				}
				else {
					return new Constraint();
				}
			case EvlParser.CONTEXT:
				if (incrementalMode) {
					return createTracedConstraintContext();
				}
				else {
					return new ConstraintContext();
				}
		}
		return super.adapt(cst, parentAst);
	}

	/**
	 * @return
	 */
	private TracedConstraintContext createTracedConstraintContext() {
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
		String evlScripPath = "String";
		if (this.sourceUri != null) {
			evlScripPath = this.sourceUri.toString();
		}
		try {
			logger.info("Creaetig the EvlModuleTrace.");
			evlModuleTrace = new EvlModuleTrace(evlScripPath);
		} catch (TraceModelDuplicateRelation e) {
			throw new EolRuntimeException(e.getMessage());
		}
		IEvlExecutionTraceManager etManager = ((IncrementalEvlContext) context).getTraceManager();
		
		getContext().setEvlModuleTrace(evlModuleTrace);
		logger.info("Adding execution listeners");
		context.getExecutorFactory().addExecutionListener(etManager.getAllInstancesAccessListener());
		context.getExecutorFactory().addExecutionListener(etManager.getPropertyAccessListener());
		context.getExecutorFactory().addExecutionListener(etManager.getSatisfiesListener());

		// Perform evaluation
		logger.info("Executing pre{}");
		execute(getPre(), context);
		logger.info("Executing contexts");
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(getContext());	
		}
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			logger.debug(uc.getMessage());
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
	public IncrementalEvlContext getContext(){
		return context;
	}
	
	@Override
	public IModuleTrace getModuleTrace() {
		return evlModuleTrace;
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
					}
					else {
						logger.debug("Un-resgitering with model {} to recieve notifications.", model.getName());
						incrementalModel.getModules().remove(this);
						//incrementalModel.setDeliver(false);  	// DO NOT disable notifications unless you are 100% no one else is listening
						getTargets().remove(incrementalModel);
					}
					incrementalModel.setDeliver(listen);
				}
			}
		}
	}
	
	@Override
	public void onChange(IIncrementalModel model, Object object, String propertyName) {
		
		logger.info("On Change event for {} with property {}", object, propertyName);
		String elementId = model.getElementId(object);
		IEvlExecutionTraceRepository repo = getContext().getTraceManager().getExecutionTraceRepository();
		List<IModuleElementTrace> traces = null;
		traces = repo.findPropertyAccessExecutionTraces(elementId, propertyName);
		logger.debug("Found {} traces for the model-property pair", traces.size());
		if (traces != null) {
			executeTraces(model, traces, object);
		}
	}

	@Override
	public void onCreate(IIncrementalModel model, Object newElement) {
		
		logger.info("On Craete event for {}", newElement);
		IEvlExecutionTraceManager  etManager = ((IncrementalEvlContext) context).getTraceManager();
		// Do we need to execute the pre blocks to restore context?
		//logger.info("Executing pre{}");
		//execute(getPre(), context);
		
		for (ConstraintContext conCtx : getConstraintContexts()) {
			 try {
				if (conCtx.appliesTo(newElement, getContext())) {
				    logger.info("Found matching context, executing.");
				    conCtx.checkAll(getContext());
				 }
			} catch (EolRuntimeException e) {
				logger.error("Error executing contexts for new element", e);
			}
		}
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			logger.debug(uc.getMessage());
		}
		logger.info("Persisting traces");
		etManager.persistTraceInformation();
		getContext().getConstraintTrace().clear();
	}

	@Override
	public void onDelete(IIncrementalModel model, Object object) {
		
		logger.info("On Delete event for {}", object);
		String elementId = model.getElementId(object);
		List<IModuleElementTrace> traces = null;
		IEvlExecutionTraceRepository repo = getContext().getTraceManager().getExecutionTraceRepository();
		// Re-execute invariants in which the deleted element is referenced but is not the "self" 
		traces = repo.findIndirectExecutionTraces(elementId, object, model);
		traces.stream()
				.filter(IContextTrace.class::isInstance)
				.map(IContextTrace.class::cast)
				.forEach(ct -> executeContextTrace(ct, model));
		traces.stream()
				.filter(IInvariantTrace.class::isInstance)
				.map(IInvariantTrace.class::cast)
				.forEach(it -> {
					try {
						executeTrace(model, it, object);
					} catch (EolRuntimeException e) {
						logger.info("Error executing InvariantTrace trace", e);
					}
				});
		repo.removeTraceInformation(elementId);
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
	
	private void executeContextTrace(IContextTrace contextT, IIncrementalModel model) {
		IModelElementTrace modelElementTrace = contextT.executionContext().get().contextVariables().get().stream()
				.filter(v -> v.getName().equals("self"))
				.findFirst()
				.get()
				.value().get();
		Object self = getSelf(model, null, modelElementTrace);
		ConstraintContext conCtx = getConstraintContextForTrace(contextT);
		try {
			if (conCtx.appliesTo(self, getContext(), false)) {
				for (ModuleElement me : conCtx.getChildren()) {
					Constraint constraint = (Constraint)me;
					if (!constraint.isLazy(getContext()) && constraint.appliesTo(self, getContext(), false)) {
						constraint.check(self, context, false);
					}
				}
			}
		} catch (EolRuntimeException e) {
			logger.info("Error executing ConstraintContext trace for {}", self, e);
		}
	}
	
	private void executeTraces(IIncrementalModel model, List<IModuleElementTrace> traces, Object modelObject) {
		if (traces == null || traces.isEmpty()) {
			return;
		}
		for (IModuleElementTrace t : traces) {
			// We need to be a bit smart, that is, there is a hierarchy of execution, for example:
			// - if we have both a check and a guard trace for the same invariant it will be good to execute
			// the guard first, as if the guard fails then there is no need to execute the check. Actually,
			// if the guard is false, the check trace should be deleted.
			// If the trace is only a message, we can only re-execute that (FIGURE OUT HOW TO UPDATE THE EGL VIEW)
			// - If we have satisfies traces they should only trigger an execution
			// - If the invariant that owns the other evl constructs does not has a modelaccess for 
			// the modelObject, then for each modelaccess of the invariant we need to re-execute it (i.e. the
			// modelObject was not the one being checked by the invariant).
			// Do allInstances access superseed property access for the same object?
			
			// ALL THIS RELATES TO TRIMMING THE TRACE MODEL. READ SOME REALTED LIT ABOUT IT.
			
			// To start, just re-execute the parent invariant if it matches the modelObject, if not do it for
			// all model accesses. Fine granularity comes next!
			
			// This could be done with runtime dispatch, but having distinct methods might be required?
			try {
				if (t instanceof IGuardTrace) {
					executeTrace(model, (IGuardTrace) t, modelObject);
				}
				else if (t instanceof ISatisfiesTrace) {
					executeTrace(model, (ISatisfiesTrace) t, modelObject);
				}
				else if (t instanceof ICheckTrace) {
					executeTrace(model, (ICheckTrace) t, modelObject);
				}
				else if (t instanceof IMessageTrace) {
					executeTrace(model, (IMessageTrace) t, modelObject);
				}
			} catch (EolRuntimeException e) {
				logger.error("Error reexecuting trace", e);
			}
		}
		getContext().getConstraintTrace().clear();
	}
	
	private void executeTrace(IIncrementalModel model, IInvariantTrace t, Object modelObject) throws EolRuntimeException {
		logger.info("Re-executing InvariantTrace");	
		IRuleTrace ruleTrace = t.parentTrace().get();
		IModelElementTrace selfTrace = ruleTrace.executionContext().get().contextVariables().get().stream()
				.filter(v -> v.getName().equals("self"))
				.findFirst()
				.get()
				.value().get();//.getUri();
		Object self = getSelf(model, modelObject, selfTrace);
		executeInvariantTrace(self, t);
	}
	
	/**
	 * Reexecute the guard, if the result changes from true->false, then all traces of the guarded element
	 * (and its children) must be deleted. If the result changes from false->true, then the guarded element
	 * must be reexecuted. If the results didn't change, then nothing else is executed.
	 * @param modelName2 
	 * 
	 * 
	 * @param t the guard trace
	 * @param modelObject
	 * @throws EolRuntimeException 
	 */
	private void executeTrace(IIncrementalModel model, IGuardTrace t, Object modelObject)  throws EolRuntimeException {
		logger.info("Re-executing GuardTrace");				
		IRuleTrace ruleTrace = t.parentTrace().get();
		IGuardedElementTrace guardedElement = t.limits().get();
		IModelElementTrace selfTrace = ruleTrace.executionContext().get().contextVariables().get().stream()
				.filter(v -> v.getName().equals("self"))
				.findFirst()
				.get()
				.value().get();//.getUri();
		Object self = getSelf(model, modelObject, selfTrace);
		if (guardedElement instanceof IInvariantTrace) {
			logger.info("GuardTrace applies to Invariant");				
			IInvariantTrace invariantT = (IInvariantTrace) guardedElement;
			executeInvariantTrace(self, invariantT);
		}
		else {
			logger.info("GuardTrace applies to Context");
			IContextTrace contextT = (IContextTrace) guardedElement;
			// Execute all the invariants in the InvariantContext
			ConstraintContext conCtx = getConstraintContextForTrace(contextT);
			if (conCtx.appliesTo(self, getContext())) {
				for (Constraint constraint : conCtx.getConstraints()) {
					// Find trace
					IInvariantTrace invariantT = contextT.constraints().get().stream()
							.filter(i -> i.getName().equals(constraint.getName()))
							.findFirst()
							.orElse(null);
					if (invariantT != null) {
						constraint.check(self, getContext());
					}
				}
			}			
		}
	}
	
	private void executeTrace(IIncrementalModel model, IMessageTrace t, Object modelObject)  throws EolRuntimeException {
		
		logger.info("Re-executing MessageTrace");	
		IRuleTrace ruleTrace = t.parentTrace().get();
		IModelElementTrace selfTrace = ruleTrace.executionContext().get().contextVariables().get().stream()
				.filter(v -> v.getName().equals("self"))
				.findFirst()
				.get()
				.value().get();//.getUri();
		Object self = getSelf(model, modelObject, selfTrace);
		IInvariantTrace invariantT = t.invariant().get();
		executeInvariantTrace(self, invariantT);
	}

	private void executeTrace(IIncrementalModel model, ICheckTrace t, Object modelObject)  throws EolRuntimeException {
		
		logger.info("Re-executing CheckTrace");	
		IRuleTrace ruleTrace = t.parentTrace().get();
		IModelElementTrace selfTrace = ruleTrace.executionContext().get().contextVariables().get().stream()
				.filter(v -> v.getName().equals("self"))
				.findFirst()
				.get()
				.value().get();//.getUri();
		// If self and modelObject are not the same, we need to retrieve the object from the model
		Object self = getSelf(model, modelObject, selfTrace);
		IInvariantTrace invariantT = t.invariant().get();
		executeInvariantTrace(self, invariantT);
	}

	private void executeTrace(IIncrementalModel model, ISatisfiesTrace t, Object modelObject) throws EolRuntimeException {
		
		logger.info("Re-executing SatisfiesTrace");	
		IRuleTrace ruleTrace = t.parentTrace().get();
		IModelElementTrace selfTrace = ruleTrace.executionContext().get().contextVariables().get().stream()
				.filter(v -> v.getName().equals("self"))
				.findFirst()
				.get()
				.value().get();//.getUri();
		Object self = getSelf(model, modelObject, selfTrace);
		IInvariantTrace invariantT = t.invariant().get();
		executeInvariantTrace(self, invariantT);
	}
	
	/**
	 * Execute invariant trace.
	 *
	 * @param self the self
	 * @param invariantT the invariant T
	 * @return true, if the invariant changed from true to false
	 * @throws EolRuntimeException the eol runtime exception
	 */
	private void executeInvariantTrace(Object self, IInvariantTrace invariantT) throws EolRuntimeException {
		
		logger.info("executeInvariantTrace for {}", invariantT.getName());
		ConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			// Found the context, now find the invariant
			// FIXME	 Need to modify EVL so we can individually modify constraints (invariants)
			logger.debug("Found ConstraintContext for invariant.");
			Constraint inv = conCtx.getModuleElements().stream()
					.map(Constraint.class::cast)
					.filter(c -> c.getName().equals(invariantT.getName()))
					.findFirst()
					.get();
			logger.debug("Found invariant for trace.");
			inv.check(self, getContext());
			return;
		}		
		logger.info("Can not find matching constraint for trace.");
		throw new IllegalStateException();
	}
	
	private ConstraintContext getConstraintContextForTrace(IContextTrace trace) {
		int index = 1;
		for (ConstraintContext conCtx : getConstraintContexts()) {
			if (conCtx.getTypeName().equals(trace.getKind()) &&
					(index == trace.getIndex())) {
				return conCtx;
			}
		}
		return null;
	}
	


	private ModuleElement createMessageBlock() {
		if (incrementalMode) {
			return new TracedExecutableBlock<IMessageTrace, String>(String.class);
		}
		else {
			return new ExecutableBlock<String>(String.class);
		}
	}
	
	private ModuleElement createCheckBlock() {
		if (incrementalMode) {
			return new TracedExecutableBlock<ICheckTrace, Boolean>(Boolean.class);
		}
		else {
			return new ExecutableBlock<Boolean>(Boolean.class);
		}
	}
	
	private ExecutableBlock<Boolean> createGuardBlock() {
		if (incrementalMode) {
			return new TracedGuardBlock(Boolean.class);
		}
		else {
			return new ExecutableBlock<Boolean>(Boolean.class);
		}
	}
	
	/**
	 * Retrieve the object that represents self
	 * @param model the model in which the event was generated
	 * @param modelObject the object that generated the event
	 * @param selfTrace the model element trace that holds the reference to the self element
	 * @param selfModelName the name of the model that owns the self reference
	 * @return
	 */
	private Object getSelf(IIncrementalModel model, Object modelObject, IModelElementTrace selfTrace) {
		
		String selfModelName = selfTrace.model().get().getName();
		IModel selfModel = null;
		try {
			selfModel = getContext().getModelRepository().getModelByName(selfModelName);
		} catch (EolModelNotFoundException e1) {
			logger.error("Error locating model with name {}", selfModelName, e1);
		}
		Object self = null;
		if (selfModelName.equals(model.getName()) && 
				model.getElementId(modelObject).equals(selfTrace.getUri())) {
			self = modelObject;
		}
		if (self == null) {
			self = selfModel.getElementById(selfTrace.getUri());
		}
		return self;
	}
	
}
