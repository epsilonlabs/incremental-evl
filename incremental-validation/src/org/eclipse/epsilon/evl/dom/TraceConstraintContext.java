package org.eclipse.epsilon.evl.dom;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.TraceEvlContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.trace.ConstraintCheckListener;
import org.eclipse.epsilon.evl.trace.Scope;
import org.eclipse.epsilon.evl.trace.TConstraint;
import org.eclipse.epsilon.evl.trace.TContext;
import org.eclipse.epsilon.evl.trace.TElement;
import org.eclipse.epsilon.evl.trace.TProperty;
import org.eclipse.epsilon.evl.trace.TScope;
import org.eclipse.epsilon.evl.trace.TraceGraph;

import com.tinkerpop.blueprints.Graph;

public class TraceConstraintContext extends ConstraintContext {

	public TraceConstraintContext() {
		super();
	}

	@Override
	public void checkAll(IEvlContext context) throws EolRuntimeException {
		
		Collection<?> allOfKind = getAllOfSourceKind(context);
		Iterator<?> it = allOfKind.iterator();
		
		while (it.hasNext()){
			Object object = it.next();
			if (appliesTo(object, context)){
				
				Iterator<Constraint> cit = constraints.iterator();
				while (cit.hasNext()){
					Constraint constraint = cit.next();					
					if (!constraint.isLazy(context) && constraint.appliesTo(object,context)){
						// Set a listener to trace property accesses
						ConstraintCheckListener listener = new ConstraintCheckListener();
						context.getExecutorFactory().addExecutionListener(listener);

						// Perform actual checks
						constraint.check(object, context);
						
						addToTrace(context, constraint, object, listener.getScopes());

						// Clean-up the listener
						context.getExecutorFactory().removeExecutionListener(listener);
					}
				}
			}
		}
	}
	
	private void addToTrace(IEvlContext ctx, 
			Constraint constraint, 
			Object modelElement,
			Collection<Scope> scopes) {
		
		// FIXME: Check the cast
		TraceGraph<? extends Graph> graph = ((TraceEvlContext) ctx).getTraceGraph();

		final TContext tContext = graph.getContext(this.getName());
		final TConstraint tConstraint = graph.getConstraint(constraint.getName(), tContext);
		final TElement tElement = graph.getElement(
				ctx.getModelRepository()
				.getOwningModel(modelElement)
				.getElementId(modelElement));
		final TScope tScope = graph.getScope(tConstraint, tElement);
		
		for (Scope s : scopes) {
			final TElement element = graph.getElement(s.getElementId());
			final TProperty property = graph.getProperty(s.getPropertyName(), element);
			tScope.addProperty(property);
		}
	}
	
}
