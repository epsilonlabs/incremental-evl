package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.evl.IEvlModule;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;

public interface IEvlModuleIncremental extends IModuleIncremental, IEvlModule {
	
	IncrementalEvlContext getContext();
	
	TracedConstraint getTracedConstraint(ICheckTrace checkTrace);

	TracedConstraint getTracedConstraint(IMessageTrace messageTrace);

	TracedConstraint getTracedConstraint(IInvariantTrace invariantTrace);

	TracedConstraint getTracedConstraint(IGuardTrace guardTrace);
	
	TracedConstraintContext getTracedConstraintContext(IGuardTrace guardTrace);
	
	TracedConstraintContext getTracedConstraintContext(IContextTrace contextTrace);

}
