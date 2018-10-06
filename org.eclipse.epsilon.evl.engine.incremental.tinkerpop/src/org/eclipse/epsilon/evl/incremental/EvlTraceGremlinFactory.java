package org.eclipse.epsilon.evl.incremental;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex.Builder;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty;
import org.eclipse.epsilon.base.incremental.GremlinBaseFactoryImpl;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlin;

public class EvlTraceGremlinFactory extends GremlinBaseFactoryImpl implements IEvlRootElementsFactory {

	public EvlTraceGremlinFactory() {
		super();
	}

	@Override
	public IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateElement {
		
		Builder vertexBuilder = DetachedVertex.build();
		vertexBuilder.setLabel("EvlModuleTrace");
		// Properties
		org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty.Builder pBuilder = DetachedVertexProperty.build();
		pBuilder.setLabel("id");
		pBuilder.setValue(GremlinUtils.identityToString(uri));
		vertexBuilder.addProperty(pBuilder.create());
		pBuilder = DetachedVertexProperty.build();
		pBuilder.setLabel("uri");
		pBuilder.setValue(uri);
		vertexBuilder.addProperty(pBuilder.create());
		Vertex m = vertexBuilder.create();
		EvlModuleTraceGremlin result = new EvlModuleTraceGremlin();
		result.delegate(m);
		return result;
	}

}
