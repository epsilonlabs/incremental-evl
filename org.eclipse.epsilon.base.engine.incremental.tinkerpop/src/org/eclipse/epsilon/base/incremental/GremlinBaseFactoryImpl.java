package org.eclipse.epsilon.base.incremental;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex.Builder;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlin;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.base.incremental.trace.util.TraceFactory;


public abstract class GremlinBaseFactoryImpl implements IBaseRootElementsFactory {
    
	protected final TraceFactory factory;
	protected final GraphTraversalSource gts;
	
	public GremlinBaseFactoryImpl(TraceFactory factory, GraphTraversalSource gts) {
		super();
		this.factory = factory;
		this.gts = gts;
	}

	@Override
	public IModelTrace createModelTrace(String uri) throws TraceModelDuplicateElement {
		Builder vertexBuilder = DetachedVertex.build();
		vertexBuilder.setLabel("ModelTrace");
		vertexBuilder.setId(GremlinUtils.identityToString(uri));
		// Properties
		org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty.Builder pBuilder = DetachedVertexProperty.build();
		pBuilder.setLabel("uri");
		pBuilder.setValue(uri);
		vertexBuilder.addProperty(pBuilder.create());
		Vertex m = vertexBuilder.create();
		ModelTraceGremlin result = new ModelTraceGremlin(m, gts, factory);
		return result;
	}

}
