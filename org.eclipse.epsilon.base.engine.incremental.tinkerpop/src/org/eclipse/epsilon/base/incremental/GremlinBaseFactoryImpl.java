package org.eclipse.epsilon.base.incremental;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex.Builder;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertexProperty;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlin;
import org.eclipse.epsilon.base.incremental.trace.util.GremlinUtils;


public abstract class GremlinBaseFactoryImpl implements IBaseRootElementsFactory {
    

	public GremlinBaseFactoryImpl() {
		super();
	}

	@Override
	public IModelTrace createModelTrace(String uri) throws TraceModelDuplicateElement {
		Builder vertexBuilder = DetachedVertex.build();
		vertexBuilder.setLabel("ModelTrace");
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
		ModelTraceGremlin result = new ModelTraceGremlin();
		result.delegate(m);
		return result;
	}

}
