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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.incremental.old.IExecutionTrace;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
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
	 * The trace manager associated to this module.
	 */
	private IExecutionTraceManager etManager = null;
	
	/**
	 * The set of models which the module receives notification.
	 */
	Set<IIncrementalModel> targets;
	
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
		getExecutionTraceManager().setExecutionContext(this.getSourceUri().toString(), modelIds);
		getExecutionTraceManager().executionStarted();
		// Perform evaluation
		execute(getPre(), context);
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(context);	
		}		
		if (fixer != null) {
			fixer.fix(this);
		}
		execute(getPost(), context);
		getExecutionTraceManager().executionFinished();
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
		return null;
	} 
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.evl.EvlModule#adapt(org.eclipse.epsilon.common.parse.AST, org.eclipse.epsilon.common.module.ModuleElement)
	 */
	@Override
	public ModuleElement adapt(AST cst, ModuleElement parentAst) {
		switch (cst.getType()) {
			case EvlParser.FIX: return new Fix();
			case EvlParser.DO: return new ExecutableBlock<Void>(Void.class);
			case EvlParser.TITLE: return new ExecutableBlock<String>(String.class);
			case EvlParser.MESSAGE: return new ExecutableBlock<String>(String.class);
			case EvlParser.CHECK: return new ExecutableBlock<Boolean>(Boolean.class);
			case EvlParser.GUARD: return new ExecutableBlock<Boolean>(Boolean.class);
			case EvlParser.CONSTRAINT:
				if (incrementalMode) {
					return new TracedConstraint(this);
				}
				else {
					return new Constraint();
				}
				
			case EvlParser.CRITIQUE:
				if (incrementalMode) {
					return new TracedConstraint(this);
				}
				else {
					return new Constraint();
				}
			case EvlParser.CONTEXT: return new ConstraintContext();
		}
		return super.adapt(cst, parentAst);
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
						incrementalModel.setDeliver(true);
						getTargets().add(incrementalModel);
					}
					else {
						incrementalModel.getModules().remove(this);
						// incrementalModel.setDeliver(false);  DO NO disable notifications unless you are 100% no one else is listening
						getTargets().remove(incrementalModel);
					}
				}
			}
		}
	}

	@Override
	public void onChange(String objectId, Object object, String propertyName) {
		
		System.out.println("On Change: " + objectId);
		Collection<IExecutionTrace> traces = etManager.getTraces(objectId, propertyName);
		if (traces != null) {
			validateScopes(traces, object);
		}
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
		System.out.println("On Delete: " + objectId);
		Collection<IExecutionTrace> traces = etManager.getTraces(objectId);
		if (traces != null) {
			validateScopes(traces, object);
		}
	}
	
	private void validateScopes(Collection<IExecutionTrace> scopes, Object modelObject) {
		if (scopes == null || scopes.isEmpty()) {
			return;
		}
		for (IExecutionTrace scope : scopes) {
			
			String constraintName = scope.getModuleElement().getId();
			final Constraint constraint;
			try {
				constraint = getConstraints().getConstraint(constraintName, modelObject, getContext());
			} catch (EolRuntimeException e) {
				return;
			}
			try {
				constraint.check(modelObject, getContext());
			} catch (EolRuntimeException e) {
				// TODO: Log exception
				continue;
			}
		}
		for (UnsatisfiedConstraint uc : getContext().getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
	}

	/**
	 * In EVL the module element ID is formed by joining the Context name and the Constraint name. 
	 * <contextName>.<constraintName>
	 */
	@Override
	public String getModuleElementId(ModuleElement moduleElement) throws EolRuntimeException {
		if (!(moduleElement instanceof Constraint)) {
			throw new EolRuntimeException("Can not create ids for module elements that are not Constraints.");
		}
		Constraint constraint = (Constraint) moduleElement;
		String constraintName = constraint.getName();
		String contextName = constraint.getConstraintContext().getTypeName();
		// TODO Check if the getTypeName() returns the model name too
		return contextName + "." + constraintName;
	}

	@Override
	public ModuleElement getModuleElementById(String moduleElementId) {
		String[] names = moduleElementId.split(".");
		ModuleElement result = null;
		for (Constraint constraint : getConstraints()) {
			if (constraint.getName().equals(names[1])
					&& constraint.getConstraintContext().getTypeName().equals(names[0])) {
				result = constraint;
				break;
			}
		}
		return result;
	}

	@Override
	public void setExecutionTraceManager(IExecutionTraceManager manager) {
		this.etManager = manager;
		
	}

	@Override
	public IExecutionTraceManager getExecutionTraceManager() {
		return etManager;
	}

	@Override
	public Set<IIncrementalModel> getTargets() {
		if (targets == null) {
			targets = new HashSet<>();
		}
		return targets;
	}
	
}
