package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

public interface IEvlModuleIncremental<R extends IEvlModuleTraceRepository, F extends IEvlRootElementsFactory, T extends IEvlExecutionTraceManager<R, F>>
		extends IIncrementalModule<IEvlModuleTrace, R, F, T> {

}
