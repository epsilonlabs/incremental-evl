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
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
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
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
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
			case EvlParser.MESSAGE: return createExecutableBlock(String.class);
			case EvlParser.CHECK: return createExecutableBlock(Boolean.class);
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
			logger.info("Craetig the EvlModuleTrace.");
			evlModuleTrace = new EvlModuleTrace(evlScripPath);
		} catch (TraceModelDuplicateRelation e) {
			throw new EolRuntimeException(e.getMessage());
		}
		IEvlExecutionTraceManager<IEvlExecutionTraceRepository> etManager = ((TracedEvlContext) context).getTraceManager();
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
		IEvlExecutionTraceManager<IEvlExecutionTraceRepository> etManager = ((TracedEvlContext) context).getTraceManager();
		IEvlExecutionTraceRepository repo = ((TracedEvlContext) context).getTraceManager().getExecutionTraceRepository();
		Collection<IExecutionTrace> traces = null;
		traces = repo.findExecutionTraces(objectId, propertyName);
		if (traces != null) {
			validateTraces(traces, object);
		}
	}

	@Override
	public void onCreate(String modelName, Object newElement) {
		
		logger.info("On Craete event for {}", newElement);
		IEvlExecutionTraceManager<IEvlExecutionTraceRepository>  etManager = ((TracedEvlContext) context).getTraceManager();
		// Do we need to execute the pre blocks to restore context?
		//logger.info("Executing pre{}");
		//execute(getPre(), context);
		
		for (ConstraintContext conCtx : getConstraintContexts()) {
			 try {
				if (conCtx.appliesTo(newElement, getContext())) {
				    logger.info("Foound matching context, executing.");
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
	
	private void validateTraces(Collection<IExecutionTrace> traces, Object modelObject) {
		if (traces == null || traces.isEmpty()) {
			return;
		}
		for (IExecutionTrace t : traces) {
			
//			String constraintId = t.getTraces().getModuleId();
//			// 
//			final Constraint constraint;
//			constraint = (Constraint) getModuleElementById(constraintId);
//			if (constraint == null) {
//				return;		// FIXME Should throw an exception or stack/log to indicate that an invalid module element was requested.
//			}
//			// FIXME WE need more fine grained control... eg. only validate the traces for which this element is not
//			// the "main" object... we would need to distinguish between main and support elements
//			try {
//				constraint.check(modelObject, getContext());
//			} catch (EolRuntimeException e) {
//				// TODO: Log exception
//				continue;
//			}
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



	public void setOnlineExecution(boolean onlineExecution) {
		this.onlineExecution = onlineExecution;
	}

	private <T> ExecutableBlock<T> createExecutableBlock(Class<T> clazz) {
		if (incrementalMode) {
			return new TracedExecutableBlock<T>(clazz);
		}
		else {
			return new ExecutableBlock<T>(clazz);
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
