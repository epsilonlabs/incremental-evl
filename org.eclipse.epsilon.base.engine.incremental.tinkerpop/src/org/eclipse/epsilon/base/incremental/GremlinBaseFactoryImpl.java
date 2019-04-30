package org.eclipse.epsilon.base.incremental;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlin;
import org.eclipse.epsilon.base.incremental.trace.util.DetachableTinkerpopDelegateFactory;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;


public abstract class GremlinBaseFactoryImpl implements IBaseRootElementsFactory {
    
	protected final TraceFactory factory;
	protected final GraphTraversalSource gts;
	protected final DetachableTinkerpopDelegateFactory detachableFactory = new DetachableTinkerpopDelegateFactory();
	
	public GremlinBaseFactoryImpl(TraceFactory factory, GraphTraversalSource gts) {
		super();
		this.factory = factory;
		this.gts = gts;
	}

	@Override
	public IModelTrace createModelTrace(String uri) throws TraceModelDuplicateElement {
		ModelTraceGremlin result = new ModelTraceGremlin(
				detachableFactory.createDetachedVertex("ModelTrace", "uri", uri),
				gts,
				factory);
		return result;
	}

}
