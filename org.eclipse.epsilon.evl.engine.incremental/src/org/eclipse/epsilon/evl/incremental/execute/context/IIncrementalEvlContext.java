package org.eclipse.epsilon.evl.incremental.execute.context;

import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

public interface IIncrementalEvlContext<T extends IEvlModuleTrace, 
							            R extends IEvlModuleTraceRepository,
							            M extends IEvlExecutionTraceManager<R>>
		extends IIncrementalBaseContext<T, R, M>, IEvlContext {

	SatisfiesInvocationExecutionListener<T, R, M> getSatisfiesListener();

}