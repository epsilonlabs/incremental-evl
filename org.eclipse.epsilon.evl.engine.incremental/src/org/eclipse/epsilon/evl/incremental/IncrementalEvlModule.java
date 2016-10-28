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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IIncrementalTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TraceConstraint;
import org.eclipse.epsilon.evl.incremental.orientdb.OrientPropertyAccessTraceFactory;
import org.eclipse.epsilon.evl.parse.EvlParser;


/**
 * The Class IncrementalEvlModule.
 */
// FIXME This changes should be merged into EVL Moudle and use the incremental execution flag
// to enable the incremental behaviour
// FIXME Some of this API should belong to the base Eol Module (e.g. the trace model API can be shared by all languages
public class IncrementalEvlModule extends EvlModule implements IIncrementalModule {
	
	private boolean incrementalMode = true;
	

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.evl.EvlModule#execute()
	 */
	@Override
	public Object execute() throws EolRuntimeException {
		if (!incrementalMode) {
			return super.execute();
		}
		if (hasTrace()) {
			return null;
		}
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));
		
		// Perform evaluation
		execute(getPre(), context);
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(context);	
		}		
		if (fixer != null) {
			fixer.fix(this);
		}
		execute(getPost(), context);
		getTrace().commit();
		setHasTrace(true);
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
//			case EvlParser.CONSTRAINT: return new Constraint();
//			case EvlParser.CRITIQUE: return new Constraint();
			// Modified to return the appropriate subclasses of Constraint
			case EvlParser.CONSTRAINT: return new TraceConstraint();
			case EvlParser.CRITIQUE: return new TraceConstraint();
			// ----
			case EvlParser.CONTEXT: return new ConstraintContext();
		}
		return super.adapt(cst, parentAst);
	}
	
	// ======================== From Trace Evl Context
	
	private IIncrementalTraceManager trace = null;
	private boolean hasTrace = false;

	public IIncrementalTraceManager getTrace() {
		if (trace == null) {
			this.trace = OrientPropertyAccessTraceFactory.getInstance()
					.getTrace();
		}
		return this.trace;
	}

	public boolean hasTrace() {
		return this.hasTrace;
	}

	public void setHasTrace(boolean trace) {
		this.hasTrace = trace;
	}
	
	public void attachChangeListeners() {
		// Attach change listeners to models
		// FIXME I think we only want to attach to the model opened in the editor
		for (IModel model : this.getContext().getModelRepository().getModels()) {
			// FIXME We need to decouple this from the Model
			if (model instanceof IIncrementalModel) {
				IIncrementalModel incrementalModel = (IIncrementalModel) model;
				if (incrementalModel.supportsNotifications()) {
					incrementalModel.enableNotifications();
				}
			}
		}
	}

	@Override
	public void onChange(String elementId, Object element, String propertyName) {
//		if (notifier == null || feature == null) {
//			return null;
//		}
				
		IElementProperty property = this.trace.getProperty(propertyName, elementId);
		if (property != null) {
		
			List<ITraceScope> scopeList = new LinkedList<ITraceScope>();
			Iterator<ITraceScope> it = property.getScopes().iterator();
			while (it.hasNext()) {
				scopeList.add(it.next());
			}
			validateScopes(scopeList, element);
		}
	}

	@Override
	public void onCreate(Object newElement) {
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
	public void onDelete(String elementId, Object element, String propertyName) {
		// FIXME This thest should be done in the model listener
//		if (notifier.eResource() == null) {
//			elementId = this.idMap.remove(notifier);
//		} else {		
			//onChange(elementId, propertyName);
//		}
		
		final IModelElement telement = trace.getElement(elementId);
		if (telement != null) {
			Set<ITraceScope> scopes = new HashSet<ITraceScope>();
			for (IElementProperty p :  telement.getProperties()) {
				for (ITraceScope scope : p.getScopes()) {
					scopes.add(scope);
				}
			}
			validateScopes(scopes, element);
		}
	}
	
	public void validateScopes(Collection<ITraceScope> scopes, Object element) {
		if (scopes == null || scopes.isEmpty()) {
			return;
		}
		for (ITraceScope scope : scopes) {
			//final EObject target = this.getElement(scope);
			final Constraint constraint;// = this.getConstraint(scope);
			String name = scope.getConstraint().getName();
			try {
				constraint = getConstraints().getConstraint(name, element, getContext());
			} catch (EolRuntimeException e) {
				return;
			}
//			if (target == null || constraint == null) {
//				continue; //
//			}

			try {
				constraint.check(element, getContext());
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
}
