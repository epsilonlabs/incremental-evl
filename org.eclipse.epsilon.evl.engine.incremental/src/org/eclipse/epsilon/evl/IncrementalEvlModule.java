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
import java.util.stream.Collectors;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.incremental.trace.impl.TraceModelDuplicateRelation;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.dom.TracedConstraint;
import org.eclipse.epsilon.evl.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.dom.TracedExecutableBlock;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.util.ContextTraceUtil;
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
	private IEvlExecutionTraceManager etManager = null;
	
	/**
	 * The set of models which the module receives notification.
	 */
	Set<IIncrementalModel> targets;
	
	private boolean onlineExecution;

	private IEvlModuleExecution evlExecution;
	
	private IEvlModuleTrace evlModule;
	
	@Override
	public Object execute() throws EolRuntimeException {
		if (!incrementalMode) {
			return super.execute();
		}
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));
		List<String> modelIds = this.getContext().getModelRepository().getModels().stream()
				.map(IIncrementalModel.class::cast)
				.map(m -> m.getModelId())
				.collect(Collectors.toList());
		
		// Prepare the Trace context
		String evlScripPath = "String";
		if (this.sourceUri != null) {
			evlScripPath = this.sourceUri.toString();
		}
		evlExecution = etManager.moduleExecutionTraces().getEvlModuleExecutionForSource(evlScripPath);
		evlModule = null;
		if (evlExecution == null) {
			try {
				evlExecution = new EvlModuleExecution();
				evlModule = new EvlModuleTrace(evlScripPath, evlExecution);
			} catch (TraceModelDuplicateRelation e) {
				throw new EolRuntimeException("Error creating the execution trace for this module. " + e.getMessage());
			} finally {
				if (evlExecution != null) {
					etManager.moduleExecutionTraces().add(evlExecution);
				}
			}
		}
		// Perform evaluation
		execute(getPre(), context);
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(context);	
		}
		// Now for each constraint we can get each recorder and create the traces
		
		if (fixer != null) {
			fixer.fix(this);
		}
		execute(getPost(), context);
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
		getExecutionTraceManager().persistTraceInformation();
		if (onlineExecution) {
			listenToModelChanges(true);
		}
		return null;
	} 
	
	@Override
	public ModuleElement adapt(AST cst, ModuleElement parentAst) {
		
		switch (cst.getType()) {
			case EvlParser.FIX: return new Fix();
			case EvlParser.DO: return createExecutableBlock(Void.class);
			case EvlParser.TITLE: return new ExecutableBlock<String>(String.class);
			case EvlParser.MESSAGE: return createExecutableBlock(String.class);
			case EvlParser.CHECK: return createExecutableBlock(Boolean.class);
			case EvlParser.GUARD: return createExecutableBlock(Boolean.class);
			case EvlParser.CONSTRAINT:
				if (incrementalMode) {
					return new TracedConstraint();
				}
				else {
					return new Constraint();
				}
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
	
	@Override
	public ModuleElement createAst(AST cst, ModuleElement parentAst) {
		ModuleElement result = super.createAst(cst, parentAst);
		if (result != null) {
			switch (cst.getType()) {
			case EvlParser.MESSAGE:
				if (incrementalMode) {
				assert result instanceof TracedExecutableBlock;
				try {
					createMessageTrace((TracedExecutableBlock<?>) result);
				} catch (EolIncrementalExecutionException e) {
					throw new IllegalStateException(e);
				}
			}
			case EvlParser.CHECK:
				if (incrementalMode) {
					assert result instanceof TracedExecutableBlock;
					try {
						createCheckTrace((TracedExecutableBlock<?>) result);
					} catch (EolIncrementalExecutionException e) {
						throw new IllegalStateException(e);
					}
				}
			case EvlParser.GUARD:
				if (incrementalMode) {
					assert result instanceof TracedExecutableBlock;
					try {
						createGuardTrace((TracedExecutableBlock<?>) result);
					} catch (EolIncrementalExecutionException e) {
						throw new IllegalStateException(e);
					}
				}
			case EvlParser.CONSTRAINT:
				if (incrementalMode) {
					assert result instanceof TracedConstraint;
					try {
						createConstraintTrace((TracedConstraint) result);
					} catch (EolIncrementalExecutionException e) {
						throw new IllegalStateException(e);
					}
				}
			case EvlParser.CRITIQUE:
				if (incrementalMode) {
					assert result instanceof TracedConstraint;
					try {
						createConstraintTrace((TracedConstraint) result);
					} catch (EolIncrementalExecutionException e) {
						throw new IllegalStateException(e);
					}
				}
			case EvlParser.CONTEXT:
				if (incrementalMode) {
					assert result instanceof TracedConstraintContext;
					try {
						createConstraintContextTrace((TracedConstraintContext) result);
					} catch (EolIncrementalExecutionException e) {
						throw new IllegalStateException(e);
					}
				}
			}	
		}
		return result;
	}

	public IEvlExecutionTraceManager getExecutionTraceManager() {
		return etManager;
	}

	public void setExecutionTraceManager(IEvlExecutionTraceManager etManager) {
		this.etManager = etManager;
	}

	private <T> ExecutableBlock<T> createExecutableBlock(Class<T> clazz) {
		if (incrementalMode) {
			return new TracedExecutableBlock<T>(clazz);
		}
		else {
			return new ExecutableBlock<T>(clazz);
		}
	}
	
	/**
	 * Create a new trace object for a ConstraintContext
	 * @param constraintContext
	 * @throws EolIncrementalExecutionException
	 */
	private void createConstraintContextTrace(TracedConstraintContext constraintContext) throws EolIncrementalExecutionException {
		String typeName = constraintContext.getTypeName();
		IContextTrace trace;
		if (typeName == null) {
			throw new EolIncrementalExecutionException("Can't create ContextTrace for unknown (null type.");
		}
		else {
			// TODO Move this to a setter so unitOfWork, evlModule and evlExecution are parameters?
			trace = etManager.contextTraces().getContextFor(typeName , evlModule);
			if (trace == null) {
				try {
					trace = evlExecution.createContextTrace(typeName);
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException("Can't create ContextTrace for type " + typeName + ".");			
				} finally {
					if (trace != null) {
						etManager.contextTraces().add(trace);
					}
				}
			}
		}
		constraintContext.setTrace(trace);
	}
	
	/**
	 * Create a new trace object for a Constraint (Invariant)
	 * @param tracedConstraint
	 * @throws EolIncrementalExecutionException
	 */
	private void createConstraintTrace(TracedConstraint tracedConstraint) throws EolIncrementalExecutionException {
		ConstraintContext parent = tracedConstraint.getConstraintContext();
		assert parent instanceof TracedConstraintContext;
		IInvariantTrace trace = ContextTraceUtil.getInvariantIn(((TracedConstraintContext) parent).getTrace(),
																	tracedConstraint.getName());
		if (trace == null) {
			try {
				trace = ((TracedConstraintContext) parent).getTrace().createInvariantTrace(tracedConstraint.getName());
			} catch (EolIncrementalExecutionException e) {
				throw new EolIncrementalExecutionException("Can't create InvarianTrace for invariant " + tracedConstraint.getName() + ".");	
			}
		}
		tracedConstraint.setTrace(trace);
	}
	
	/**
	 * Create a new trace object for a guard
	 * @param tracedGuard
	 * @throws EolIncrementalExecutionException
	 */
	private void createGuardTrace(TracedExecutableBlock<?> tracedGuard) throws EolIncrementalExecutionException {
		ModuleElement guardedElement = tracedGuard.getParent();
		IGuardTrace guard = null;
		if (guardedElement instanceof TracedConstraintContext) {
			TracedConstraintContext tracedConstraintContext = (TracedConstraintContext) guardedElement;
			guard = ContextTraceUtil.getGuardFor(tracedConstraintContext.getTrace());
			if (guard == null) {
				try {
					guard = tracedConstraintContext.getTrace().createGuardTrace();
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException("Can't create GuardTrace for Context " + tracedConstraintContext.getTypeName() + ".");	
				}
			}
		}
		else if (guardedElement instanceof TracedConstraint) {
			TracedConstraint tracedConstraint = (TracedConstraint) guardedElement;
			guard = tracedConstraint.getTrace().guard().get();
			if (guard == null) {
				try {
					guard = tracedConstraint.getTrace().createGuardTrace();
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException("Can't create GuardTrace for Invariant " + tracedConstraint.getName() + ".");	
				}
			}
		}
		tracedGuard.setTrace(guard);
	}
	
	/**
	 * Create a new trace object for a check
	 * @param tracedCheck
	 * @throws EolIncrementalExecutionException
	 */
	private void createCheckTrace(TracedExecutableBlock<?> tracedCheck) throws EolIncrementalExecutionException {
		ModuleElement invariant = tracedCheck.getParent();
		ICheckTrace check = null;
		if (invariant instanceof TracedConstraint) {
			TracedConstraint tracedConstraint = (TracedConstraint) invariant;
			check = tracedConstraint.getTrace().check().get();
			if (check == null) {
				try {
					check = tracedConstraint.getTrace().createCheckTrace();
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException("Can't create GuardTrace for Invariant " + tracedConstraint.getName() + ".");	
				}
			}
		}
		tracedCheck.setTrace(check);
	}
	
	/**
	 * Create a new trace object for a Message
	 * @param tracedMessage
	 * @throws EolIncrementalExecutionException
	 */
	private void createMessageTrace(TracedExecutableBlock<?> tracedMessage) throws EolIncrementalExecutionException {
		ModuleElement invariant = tracedMessage.getParent();
		IMessageTrace message = null;
		if (invariant instanceof TracedConstraint) {
			TracedConstraint tracedConstraint = (TracedConstraint) invariant;
			message = tracedConstraint.getTrace().message().get();
			if (message == null) {
				try {
					message = tracedConstraint.getTrace().createMessageTrace();
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException("Can't create MessageTrace for Invariant " + tracedConstraint.getName() + ".");	
				}
			}
		}
		tracedMessage.setTrace(message);
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
		
//		System.out.println("On Create: " + newElement);
//		for (ConstraintContext conCtx : getConstraintContexts()) {
//			 try {
//				if (conCtx.appliesTo(newElement, getContext())) {
//					 for (Constraint constraint : conCtx.getConstraints()) {
//						constraint.check(newElement, getContext());
//					}
//				 }
//			} catch (EolRuntimeException e) {
//				e.printStackTrace();
//			}
//		}	
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
	
}
