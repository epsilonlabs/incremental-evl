package org.eclipse.epsilon.evl.dom;

import java.util.Collection;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.FrameType;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.evl.TraceEvlContext;
import org.eclipse.epsilon.evl.execute.FixInstance;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.trace.ConstraintCheckListener;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;
import org.eclipse.epsilon.evl.trace.Scope;
import org.eclipse.epsilon.evl.trace.TConstraint;
import org.eclipse.epsilon.evl.trace.TContext;
import org.eclipse.epsilon.evl.trace.TElement;
import org.eclipse.epsilon.evl.trace.TProperty;
import org.eclipse.epsilon.evl.trace.TScope;
import org.eclipse.epsilon.evl.trace.TraceGraph;

import com.tinkerpop.blueprints.Graph;

/**
 * Subclass of {@link Constraint} for use with incremental evaluation and
 * traces. This bypasses the default checking of the {@link ConstraintTrace}'s.
 * 
 * @author Jonathan Co
 *
 */
public class TraceConstraint extends Constraint {

	@Override
	public boolean check(Object self, IEvlContext context)
			throws EolRuntimeException {
		// Return immediately if constraint does not apply
		if (!appliesTo(self, context))
			return false;

		// Build Frame
		context.getFrameStack().enterLocal(FrameType.UNPROTECTED,
				checkBlock.getBody());
		context.getFrameStack().put(
				Variable.createReadOnlyVariable("self", self));
		
		// Set a listener to trace property accesses
		ConstraintCheckListener listener = new ConstraintCheckListener();
		context.getExecutorFactory().addExecutionListener(listener);
		
		// Execute and add to trace
		Boolean result = checkBlock.execute(context, false);
		addToTrace(context, self, listener.getScopes());

		// Clean-up the listener
		context.getExecutorFactory().removeExecutionListener(listener);
		
		// Do rest of processing
		if (!result) {
			UnsatisfiedConstraint unsatisfiedConstraint = new UnsatisfiedConstraint();
			unsatisfiedConstraint.setInstance(self);
			unsatisfiedConstraint.setConstraint(this);

			for (Fix fix : fixes) {
				if (!fix.appliesTo(self, context))
					continue;

				FixInstance fixInstance = new FixInstance(context);
				fixInstance.setFix(fix);
				fixInstance.setSelf(self);
				unsatisfiedConstraint.getFixes().add(fixInstance);
			}

			String messageResult = null;

			if (messageBlock != null) {
				messageResult = messageBlock.execute(context, false);
			} else {
				messageResult = "Invariant " + this.getName() + " failed for "
						+ context.getPrettyPrinterManager().toString(self);
			}

			unsatisfiedConstraint.setMessage(messageResult);

			context.getUnsatisfiedConstraints().add(unsatisfiedConstraint);
			// We don't dispose the frame we leave because it may be needed for
			// fix parts
			context.getFrameStack().leaveLocal(checkBlock.getBody(), false);
			return false;
		} else {
			context.getFrameStack().leaveLocal(checkBlock.getBody());
			return true;
		}
	}
	
	private void addToTrace(IEvlContext ctx, 
			Object modelElement,
			Collection<Scope> scopes) {
		
		// FIXME: Check the cast
		TraceGraph<? extends Graph> graph = ((TraceEvlContext) ctx).getTraceGraph();

		final TContext tContext = graph.getContext(this.getName());
		final TConstraint tConstraint = graph.getConstraint(this.getName(), tContext);
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
		
		// FIXME: Move to tracegraph itself
		graph.commit();
	}
	

}
