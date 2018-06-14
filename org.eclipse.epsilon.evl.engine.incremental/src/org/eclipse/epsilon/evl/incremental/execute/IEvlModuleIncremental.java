package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

public interface IEvlModuleIncremental<R extends IEvlModuleTraceRepository,
									   T extends IEvlExecutionTraceManager<R>> extends IIncrementalModule<IEvlModuleTrace, R, T> {

}

