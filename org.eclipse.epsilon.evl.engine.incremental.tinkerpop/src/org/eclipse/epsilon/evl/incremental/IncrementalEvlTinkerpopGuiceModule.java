package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.TinkerPopEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlinRepositoryImpl;

import com.google.inject.TypeLiteral;

public class IncrementalEvlTinkerpopGuiceModule extends AbstractEvlTracelGuiceModule {

	
	@Override
	protected void configure() {
		bind(IEvlModuleTraceRepository.class).to(EvlModuleTraceGremlinRepositoryImpl.class);
		bind(IModelTraceRepository.class).to(ModelTraceGremlinRepositoryImpl.class);
		bind(IEvlRootElementsFactory.class).to(EvlTraceTinkerpopFactory.class);
		bind(new TypeLiteral<IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>>() {})
				.to(TinkerPopEvlExecutionTraceManager.class);
		bind(IEvlModuleIncremental.class).to(IncrementalEvlModule.class);
		bind(TinkerpopGraphProvider.class).to(BitsyGraphProvider.class);
	}
	

}
