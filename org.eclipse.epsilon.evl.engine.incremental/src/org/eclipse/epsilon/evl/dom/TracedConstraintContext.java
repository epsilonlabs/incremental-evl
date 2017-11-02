package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.context.IEvlIncrementalContext;

public class TracedConstraintContext extends ConstraintContext {
	
	public void traceAll(IEvlIncrementalContext context) throws EolRuntimeException {
		if (isLazy(context)) {
			return;
		}
		addConstraintTypeTrace(context);
		addTraces(context, this.guardBlock);
		for (Constraint c : constraints.values()) {
			if (c.wasOptimized) {
				addTraces(context, c.checkBlock);
			}
			else {
				addTraces(context, c.guardBlock);
				addTraces(context, c.checkBlock);
				addTraces(context, c.messageBlock);
			}
		}
	}

	private void addConstraintTypeTrace(IEvlIncrementalContext context) {
		context.addTypeAccessTrace(this.typeExpression);
	}

	private void addTraces(IEvlIncrementalContext context, ExecutableBlock<?> checkBlock) {
		context.addPropertyAccessTraces(((TracedExecutableBlock<?>)checkBlock).recorder);
	}

}
