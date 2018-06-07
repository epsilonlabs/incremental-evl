package org.eclipse.epsilon.evl.incremental.execute.context;

import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalEolContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;

public interface IIncrementalEvlContext<T extends IEvlExecutionTraceManager> extends IIncrementalEolContext<T> {

	SatisfiesInvocationExecutionListener getSatisfiesListener();

}