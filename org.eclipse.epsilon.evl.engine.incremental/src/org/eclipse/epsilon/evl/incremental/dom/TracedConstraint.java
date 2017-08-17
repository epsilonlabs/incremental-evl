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
package org.eclipse.epsilon.evl.incremental.dom;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.incremental.models.ITracingPropertyGetter;
import org.eclipse.epsilon.eol.incremental.old.IElementProperty;
import org.eclipse.epsilon.eol.incremental.old.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.old.IModelElement;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;

/**
 * Subclass of {@link Constraint} for use with incremental evaluation and
 * traces. This bypasses the default checking of the {@link ConstraintTrace}'s.
 * 
 * @author Jonathan Co
 *
 */
// FIXME this could be merged with the Constraint and use a flag
public class TracedConstraint extends Constraint {
	
	private List<IModel> models;


	public TracedConstraint(IncrementalEvlModule incrementalEvlModule) {
		setModule(incrementalEvlModule);
		this.models = incrementalEvlModule.getContext().getModelRepository().getModels();
		for (IModel model : models) {
			IPropertyGetter pg = model.getPropertyGetter();
			if (pg instanceof ITracingPropertyGetter) {
				ITracingPropertyGetter ipg = (ITracingPropertyGetter) pg;
				ipg.setDeliver(true);
			}
		}
	}


	@Override
	public boolean check(Object self, IEvlContext context, boolean checkType) throws EolRuntimeException {
		
		// First look in the trace
		if (context.getConstraintTrace().isChecked(this,self)){
			return context.getConstraintTrace().isSatisfied(this,self);
		}
		// Return immediately if constraint does not apply
		if (!appliesTo(self, context, checkType))
			return false;
		
		removeOldUnsatisfiedConstraint(self, context);
		final UnsatisfiedConstraint unsatisfiedConstraint = preprocessCheck(self, context);
		// Traces are added via notifications
		enableManagerNotifications(true);
		Boolean result = checkBlock.execute(context, false);
		enableManagerNotifications(false);

		return postprocessCheck(self, context, unsatisfiedConstraint, result);
	}


	private void enableManagerNotifications(boolean deliver) {
		for (IModel model : models) {
			IPropertyGetter pg = model.getPropertyGetter();
			if (pg instanceof ITracingPropertyGetter) {
				ITracingPropertyGetter ipg = (ITracingPropertyGetter) pg;
				ipg.setDeliver(deliver, this);
			}
		}
	}

	
	private void removeOldUnsatisfiedConstraint(Object self, IEvlContext context) {
		Iterator<UnsatisfiedConstraint> it = context.getUnsatisfiedConstraints().iterator();
		while(it.hasNext()) {
			UnsatisfiedConstraint current = it.next();
			if(current.getConstraint().equals(this) && current.getInstance().equals(self)) {
				it.remove();
			}
		}
	}

	
//	private void logScope(TScope scope) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		
//		sb.append("\"context\":\"").append(scope.getConstraint().getContext().getName()).append("\",");
//		sb.append("\"constraint\":\"").append(scope.getConstraint().getName()).append("\",");
//		sb.append("\"root\":\"").append(scope.getRootElement().getElementId()).append("\",");
//		sb.append("\"properties\":{");
//		for (TProperty property : scope.getProperties()) {
//			sb.append("\"").append(property.getName()).append("\",");
//		}
//		sb.append("}");
//		
//		sb.append("]");
//		System.out.println(sb.toString());
//	}
}
