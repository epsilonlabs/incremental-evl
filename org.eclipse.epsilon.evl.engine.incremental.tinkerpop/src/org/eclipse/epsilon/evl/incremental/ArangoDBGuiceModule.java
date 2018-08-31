package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.TinkerPopEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlinRepositoryImpl;

import com.google.inject.AbstractModule;

public class ArangoDBGuiceModule extends AbstractModule {

	
	@Override
	protected void configure() {
		bind(IEvlModuleTraceRepository.class).to(EvlModuleTraceGremlinRepositoryImpl.class);
		bind(IModelTraceRepository.class).to(ModelTraceGremlinRepositoryImpl.class);
		bind(IEvlTraceFactory.class).to(EvlTraceGremlinFactory.class);
		bind(IEvlExecutionTraceManager.class).to(TinkerPopEvlExecutionTraceManager.class);
	}
	

}
