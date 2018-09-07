package org.eclipse.epsilon.evl.incremental;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex.Builder;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty;
import org.eclipse.epsilon.base.incremental.GremlinBaseFactoryImpl;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlin;

public class EvlTraceGremlinFactory extends GremlinBaseFactoryImpl implements IEvlRootElementsFactory {

	public EvlTraceGremlinFactory() {
		super();
	}

	@Override
	public IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateElement {
		org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty.Builder uriP = DetachedVertexProperty.build();
		uriP.setLabel("uri");
		uriP.setValue(uri);
		Builder vertexBuilder = DetachedVertex.build();
		vertexBuilder.setLabel("EvlModuleTrace");
		vertexBuilder.addProperty(uriP.create());
		Vertex m = vertexBuilder.create();
		EvlModuleTraceGremlin result = new EvlModuleTraceGremlin();
		result.delegate(m);
		return result;
	}

}
