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
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IModuleTrace;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.dom.TracedConstraint;
import org.eclipse.epsilon.evl.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.dom.TracedGuardBlock;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.execute.context.TracedEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;
import org.eclipse.epsilon.evl.parse.EvlParser;


/**
 * AnIncrementalEvlModule.
 */
// FIXME This changes should be merged into EVL Module and use the incremental execution flag
// to enable the incremental behaviour
// FIXME Some of this API should belong to the base Eol Module (e.g. the trace model API can be shared by all languages
public class IncrementalEvlModule extends EvlModule implements IIncrementalModule {

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

	protected IEvlModuleExecution evlExecution;
	
	protected IEvlModuleTrace evlModuleTrace;
	
	/** The context. */
	protected IEvlContext context;
	
	
	
	public IncrementalEvlModule() {
		super();
		context = new TracedEvlContext();
	}

	@Override
	public Object execute() throws EolRuntimeException {
		if (!incrementalMode) {
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
			evlModuleTrace = new EvlModuleTrace(evlScripPath, evlExecution);
		} catch (TraceModelDuplicateRelation e) {
			throw new EolRuntimeException(e.getMessage());
		}
		IEvlExecutionTraceManager<IEvlModuleExecution> etManager = ((TracedEvlContext) context).getTraceManager(); 
		context.getExecutorFactory().addExecutionListener(etManager.getAllInstancesAccessListener());
		context.getExecutorFactory().addExecutionListener(etManager.getPropertyAccessListener());
		context.getExecutorFactory().addExecutionListener(etManager.getSatisfiesListener());

		// Perform evaluation
		execute(getPre(), context);
		
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(context);	
		}
		if (fixer != null) {
			fixer.fix(this);
		}
		execute(getPost(), context);
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
		etManager.persistTraceInformation();
		if (onlineExecution) {
			listenToModelChanges(true);
		}
		return null;
	}

	/**
	 * The execution trace must be initialised after the traceManager has been assigned to the context
	 * ({@link TracedEvlContext#setTraceManager(IEvlExecutionTraceManager)}).
	 * 
	 * @throws EolRuntimeException
	 */
	public void prepareExecutionTrace() throws EolRuntimeException {
		
		IEvlExecutionTraceManager<IEvlModuleExecution>  etManager = ((TracedEvlContext) context).getTraceManager();
		//evlExecution = (IEvlModuleExecution) etManager.moduleExecutionTraces().getEvlModuleExecutionForSource(evlScripPath);
		if (evlExecution == null) {
			try {
				evlExecution = new EvlModuleExecution();
				((TracedEvlContext)getContext()).setEvlExecution(evlExecution);
			} catch (TraceModelDuplicateRelation e) {
				throw new EolRuntimeException("Error creating the execution trace for this module. " + e.getMessage());
			} finally {
				if (evlExecution != null) {
					etManager.moduleExecutionTraces().add(evlExecution);
				}
			}
		}
//		else {
//			evlModuleTrace = (IEvlModuleTrace) evlExecution.module().get();
//		}
	} 
	
	@Override
	public ModuleElement adapt(AST cst, ModuleElement parentAst) {
		
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
					return new TracedConstraintContext();
				}
				else {
					return new ConstraintContext();
				}
		}
		return super.adapt(cst, parentAst);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.IEolLibraryModule#getContext()
	 */
	@Override
	public IEvlContext getContext(){
		return context;
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
	
	@Override
	public void listenToModelChanges(boolean listen) {
		// Attach change listeners to models
		// FIXME I think we only want to attach to the model opened in the editor
		for (IModel model : this.getContext().getModelRepository().getModels()) {
			// FIXME We need to decouple this from the Model
			if (model instanceof IIncrementalModel) {
				IIncrementalModel incrementalModel = (IIncrementalModel) model;
				if (incrementalModel.supportsNotifications()) {
					if (listen) {
						incrementalModel.getModules().add(this);
						getTargets().add(incrementalModel);
						incrementalModel.setDeliver(listen);
					}
					else {
						incrementalModel.getModules().remove(this);
						incrementalModel.setDeliver(false);  	// DO NO disable notifications unless you are 100% no one else is listening
						getTargets().remove(incrementalModel);
					}
				}
			}
		}
	}

	@Override
	public void onChange(String objectId, Object object, String propertyName) {
		
//		System.out.println("On Change: " + objectId);
//		Collection<Trace> traces = null;
//		try {
//			traces = etManager.findExecutionTraces(objectId, propertyName);
//		} catch (EolIncrementalExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (traces != null) {
//			validateTraces(traces, object);
//		}
	}

	@Override
	public void onCreate(Object newElement) {
		
		System.out.println("On Create: " + newElement);
		for (ConstraintContext conCtx : getConstraintContexts()) {
			 try {
				if (conCtx.appliesTo(newElement, getContext())) {
					 for (Constraint constraint : conCtx.getConstraints()) {
						constraint.check(newElement, getContext());
					}
				 }
			} catch (EolRuntimeException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void onDelete(String objectId, Object object) {
		
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
	
//	private void validateTraces(Collection<Trace> traces, Object modelObject) {
//		if (traces == null || traces.isEmpty()) {
//			return;
//		}
//		for (Trace t : traces) {
//			
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
//		}
//		// Will satisfied constraints be removed? is the fix replacing everything?
//		if (fixer != null) {
//			try {
//				fixer.fix(this);
//			} catch (EolRuntimeException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		for (UnsatisfiedConstraint uc : getContext().getUnsatisfiedConstraints()) {
//			System.out.println(uc.getMessage());
//		}
//	}

//	/**
//	 * In EVL the module element ID is formed by joining the Context name and the Constraint name. 
//	 * <contextName>.<constraintName>
//	 */
//	@Override
//	public String getModuleElementId(ModuleElement moduleElement) throws EolRuntimeException {
//		if (!(moduleElement instanceof Constraint)) {
//			throw new EolRuntimeException("Can not create ids for module elements that are not Constraints.");
//		}
//		Constraint constraint = (Constraint) moduleElement;
//		String constraintName = constraint.getName();
//		String contextName = constraint.getConstraintContext().getTypeName();
//		// TODO Check if the getTypeName() returns the model name too
//		return contextName + "." + constraintName;
//	}
//
//	@Override
//	public ModuleElement getModuleElementById(String moduleElementId) {
//		String[] names = moduleElementId.split("\\.");
//		ModuleElement result = null;
//		for (Constraint constraint : getConstraints()) {
//			if (constraint.getName().equals(names[1])
//					&& constraint.getConstraintContext().getTypeName().equals(names[0])) {
//				result = constraint;
//				break;
//			}
//		}
//		return result;
//	}

	@Override
	public Set<IIncrementalModel> getTargets() {
		if (targets == null) {
			targets = new HashSet<>();
		}
		return targets;
	}

	@Override
	public IModuleTrace getModuleTrace() {
		return evlModuleTrace;
	}

	@Override
	public IModuleExecution getModuleExecution() {
		return evlExecution;
	}
	
}
