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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.dom.TracedGuardBlock;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceRepository;
import org.eclipse.epsilon.evl.incremental.execute.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.execute.context.TracedEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
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
	
	private boolean onlineExecution;

	protected IEvlModuleTrace evlModuleTrace;

	/** The context. */
	protected IEvlContext context;
	
	public IncrementalEvlModule() {
		super();
		context = new TracedEvlContext();
	}
	
	@Override
	public ModuleElement adapt(AST cst, ModuleElement parentAst) {
		logger.debug("Adapting {} from {}", cst, parentAst);
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
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));
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
		IEvlExecutionTraceManager etManager = ((TracedEvlContext) context).getTraceManager();
		((TracedEvlContext) context).setEvlModuleTrace(evlModuleTrace);
		logger.info("Adding execution listeners");
		context.getExecutorFactory().addExecutionListener(etManager.getAllInstancesAccessListener());
		context.getExecutorFactory().addExecutionListener(etManager.getPropertyAccessListener());
		context.getExecutorFactory().addExecutionListener(etManager.getSatisfiesListener());

		// Perform evaluation
		logger.info("Executing pre{}");
		execute(getPre(), context);
		logger.info("Executing contexts");
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(context);	
		}
		if (fixer != null) {
			logger.info("Executing fixer");
			fixer.fix(this);
		}
		logger.info("Executing post{}");
		execute(getPost(), context);
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
		logger.info("Persisting traces");
		etManager.persistTraceInformation();
		if (onlineExecution) {
			listenToModelChanges(true);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.IEolLibraryModule#getContext()
	 */
	@Override
	public IEvlContext getContext(){
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

	public boolean isOnlineExecution() {
		return onlineExecution;
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
						incrementalModel.setDeliver(listen);
					}
					else {
						logger.debug("Un-resgitering with model {} to recieve notifications.", model.getName());
						incrementalModel.getModules().remove(this);
						//incrementalModel.setDeliver(false);  	// DO NOT disable notifications unless you are 100% no one else is listening
						getTargets().remove(incrementalModel);
					}
				}
			}
		}
	}
	
	@Override
	public void onChange(String modelName, String objectId, Object object, String propertyName) {
		
		logger.info("On Change event for {} with property {}", objectId, propertyName);
		IEvlExecutionTraceRepository repo = ((TracedEvlContext) context).getTraceManager().getExecutionTraceRepository();
		List<IModuleElementTrace> traces = null;
		traces = repo.findPropertyAccessExecutionTraces(objectId, propertyName);
		if (traces != null) {
			executeTraces(traces, object);
		}
	}

	@Override
	public void onCreate(String modelName, Object newElement) {
		
		logger.info("On Craete event for {}", newElement);
		IEvlExecutionTraceManager  etManager = ((TracedEvlContext) context).getTraceManager();
		// Do we need to execute the pre blocks to restore context?
		//logger.info("Executing pre{}");
		//execute(getPre(), context);
		
		for (ConstraintContext conCtx : getConstraintContexts()) {
			 try {
				if (conCtx.appliesTo(newElement, getContext())) {
				    logger.info("Found matching context, executing.");
					for (Constraint constraint : conCtx.getConstraints()) {
					    constraint.check(newElement, getContext());
					}
				 }
			} catch (EolRuntimeException e) {
				logger.error("Error executing contexts for new element", e);
			}
		}
//		if (fixer != null) {
//			logger.info("Executing fixer");
//			try {
//				fixer.fix(this);
//			} catch (EolRuntimeException e) {
//				logger.info("Error executing fixes", e);
//			}
//		}
//		logger.info("Executing post{}");
//		try {
//			execute(getPost(), context);
//		} catch (EolRuntimeException e) {
//			logger.info("Error executing post{}", e);
//		}
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
		logger.info("Persisting traces");
		etManager.persistTraceInformation();
	}

	@Override
	public void onDelete(String modelName, String objectId, Object object) {
		
//		System.out.println("On Delete: " + objectId);
//		Collection<Trace> traces = null;
//		try {
//			traces = etManager.findExecutionTraces(objectId);
//		} catch (EolIncrementalExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (traces != null) {
//			validateTraces(traces, object);
//		}
//		// Traces related to the deleted element should be deleted
//		try {
//			etManager.removeTraceInformation(objectId);
//		} catch (EolIncrementalExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private void executeTraces(List<IModuleElementTrace> traces, Object modelObject) {
		if (traces == null || traces.isEmpty()) {
			return;
		}
		for (IModuleElementTrace t : traces) {
			// We need to be a bit smart, that is, there is a hierarchy of execution, for example:
			// - if we have both a check and a guard trace for the same invariant it will be good to execute
			// the guard first, as if the guard fails then there is no need to execute the check. Actually,
			// if the guard is false, the check trace should be deleted.
			// If the trace is only a message, we can only reexecute that (FIGURE OUT HOW TO UPDATE THE EGL VIEW)
			// - If we have satisfies traces they should only trigger an execution
			// - If the invariant that owns the other evl constructs does not has a modelaccess for 
			// the modelObject, then for each modelaccess of the invariant we need to reexecute it (i.e. the
			// modelObject was not the one being checked by the invariant).
			// Do allInstances access superseed property access for the same object?
			
			// ALL THIS RELATES TO TRIMMING THE TRACE MODEL. READ SOME REALTED LIT ABOUT IT.
			
			// To start, just rexecute the parent invariant if it matches the modelObject, if not do it for
			// all model accesses. Fine granurality comes next!
			
			// Context and Invariants should not have property accesses!
//			if (t instanceof IContextTrace) {
//				executeTrace((IContextTrace) t, modelObject);
//			}
//			else if (t instanceof IInvariantTrace) {
//				executeTrace((IInvariantTrace) t, modelObject);
//			}
			
			// This could be done with runtime dispatch, but having distinct methods might be required?
			if (t instanceof IGuardTrace) {
				executeTrace((IGuardTrace) t, modelObject);
			}
			else if (t instanceof ISatisfiesTrace) {
				executeTrace((ISatisfiesTrace) t, modelObject);
			}
			else if (t instanceof ICheckTrace) {
				executeTrace((ICheckTrace) t, modelObject);
			}
			else if (t instanceof IMessageTrace) {
				executeTrace((IMessageTrace) t, modelObject);
			}
		}
		// Will satisfied constraints be removed? is the fix replacing everything?
		if (fixer != null) {
			try {
				fixer.fix(this);
			} catch (EolRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (UnsatisfiedConstraint uc : getContext().getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
	}
	
	/**
	 * Reexecute the guard, if the result changes from true->false, then all traces of the guarded element
	 * (and its children) must be deleted. If the result changes from false->true, then the guarded element
	 * must be reexecuted. If the results didn't change, then nothing else is executed.
	 * 
	 * Execution of the guard requires that the EVL context knows about self... who is self? It can or not
	 * be the modelObject. We need to save this information in the trace.  
	 * 
	 * @param t the guard trace
	 * @param modelObject
	 */
	private void executeTrace(IGuardTrace t, Object modelObject) {
		// TODO Implement IncrementalEvlModule.executeTrace
		throw new UnsupportedOperationException("Unimplemented Method    IncrementalEvlModule.executeTrace invoked.");
	}
	
	private void executeTrace(IMessageTrace t, Object modelObject) {
		// TODO Implement IncrementalEvlModule.executeTrace
		throw new UnsupportedOperationException("Unimplemented Method    IncrementalEvlModule.executeTrace invoked.");
	}

	private void executeTrace(ICheckTrace t, Object modelObject) {
		// TODO Implement IncrementalEvlModule.executeTrace
		throw new UnsupportedOperationException("Unimplemented Method    IncrementalEvlModule.executeTrace invoked.");
	}

	private void executeTrace(ISatisfiesTrace t, Object modelObject) {
		// TODO Implement IncrementalEvlModule.executeTrace
		throw new UnsupportedOperationException("Unimplemented Method    IncrementalEvlModule.executeTrace invoked.");
	}




	public void setOnlineExecution(boolean onlineExecution) {
		this.onlineExecution = onlineExecution;
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
	
}
