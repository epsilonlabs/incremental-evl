package org.eclipse.epsilon.evl.incremental.dom;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.PropertyAccess;
import org.eclipse.epsilon.evl.incremental.trace.PropertyAccessListener;
import org.eclipse.epsilon.evl.incremental.trace.TConstraint;
import org.eclipse.epsilon.evl.incremental.trace.TContext;
import org.eclipse.epsilon.evl.incremental.trace.TElement;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TScope;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;

/**
 * Subclass of {@link Constraint} for use with incremental evaluation and
 * traces. This bypasses the default checking of the {@link ConstraintTrace}'s.
 * 
 * @author Jonathan Co
 *
 */
public class TraceConstraint extends Constraint {
		
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
				
		// Set a listener to trace property accesses
		PropertyAccessListener listener = new PropertyAccessListener();
		context.getExecutorFactory().addExecutionListener(listener);
		
		Boolean result = checkBlock.execute(context, false);
		addToTrace((IncrementalEvlModule) getModule(), self, result, listener.getPropertyAccesses());

		// Clean-up the listener
		context.getExecutorFactory().removeExecutionListener(listener);
		return postprocessCheck(self, context, unsatisfiedConstraint, result);
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
	
	public void addToTrace(IncrementalEvlModule ctx, Object element, Boolean result, Collection<PropertyAccess> accesses) {
		
		final IPropertyAccessTrace trace = ctx.getTrace();
		
		final String contextName = this.getConstraintContext().getTypeName();
		final String constraintName = this.getName();
		final String elementId = ctx.getContext().getModelRepository().getOwningModel(element).getElementId(element);
	
		// Clear any existing scope
		trace.removeScope(elementId, constraintName, contextName);
		trace.commit();
		
		// Populate with new trace
		final TContext tContext = trace.createContext(contextName);
		final TConstraint tConstraint = trace.createConstraint(constraintName, tContext);
		final TElement tElement = trace.createElement(elementId);
		final TScope tScope = trace.createScope(tElement, tConstraint);
		tScope.setResult(result);
		
		for (PropertyAccess pa : accesses) {
			final TElement currentElement = trace.createElement(pa.getElementId());
			final TProperty property = trace.createProperty(pa.getPropertyName(), currentElement);
			tScope.addProperty(property);
		}

//		trace.commit();
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
