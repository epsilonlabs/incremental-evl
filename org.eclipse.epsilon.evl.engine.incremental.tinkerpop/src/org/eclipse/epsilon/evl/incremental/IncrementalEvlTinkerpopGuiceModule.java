package org.eclipse.epsilon.evl.incremental;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.TinkerPopEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlinRepositoryImpl;
import org.eclipse.epsilon.evl.incremental.util.EvlTraceFactory;

public class IncrementalEvlTinkerpopGuiceModule extends AbstractEvlTracelGuiceModule {

	private final GraphTraversalSource traversalSource;
	
	public IncrementalEvlTinkerpopGuiceModule(
		GraphTraversalSource trvrslSrc) {
		super();
		traversalSource = trvrslSrc;
	}

	@Override
	protected void configure() {
		bind(IEvlModuleTraceRepository.class).to(EvlModuleTraceGremlinRepositoryImpl.class);
		bind(IModelTraceRepository.class).to(ModelTraceGremlinRepositoryImpl.class);
		bind(IEvlRootElementsFactory.class).to(EvlTraceTinkerpopFactory.class);
		bind(IEvlExecutionTraceManager.class).to(TinkerPopEvlExecutionTraceManager.class);
		bind(IEvlModuleIncremental.class).to(IncrementalEvlModule.class);		
		bind(GraphTraversalSource.class).toInstance(traversalSource);
		bind(TraceFactory.class).toInstance(new EvlTraceFactory());
	}
	

}
