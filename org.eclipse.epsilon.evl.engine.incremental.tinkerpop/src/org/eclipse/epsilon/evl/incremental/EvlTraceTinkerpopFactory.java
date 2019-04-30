package org.eclipse.epsilon.evl.incremental;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.eclipse.epsilon.base.incremental.GremlinBaseFactoryImpl;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlin;

import com.google.inject.Inject;


public class EvlTraceTinkerpopFactory extends GremlinBaseFactoryImpl implements IEvlRootElementsFactory {
	
	@Inject
	public EvlTraceTinkerpopFactory(TraceFactory factory, GraphTraversalSource gts) {
		super(factory, gts);
	}

	@Override
	public IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateElement {
		EvlModuleTraceGremlin result = new EvlModuleTraceGremlin(
				detachableFactory.createDetachedVertex("EvlModuleTrace", "uri", uri),
				gts,
				factory);
		return result;
	}
	
	

}
