package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceRepositoryImpl;
import org.eclipse.epsilon.evl.incremental.execute.InMemoryEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceRepositoryImpl;

import com.google.inject.AbstractModule;

public class EvlIncrementalGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IEvlModuleTraceRepository.class).to(EvlModuleTraceRepositoryImpl.class);
		bind(IModelTraceRepository.class).to(ModelTraceRepositoryImpl.class);
		bind(IEvlRootElementsFactory.class).to(EvlRootElementsFactory.class);
		bind(IEvlExecutionTraceManager.class).to(InMemoryEvlExecutionTraceManager.class);
	}

}
