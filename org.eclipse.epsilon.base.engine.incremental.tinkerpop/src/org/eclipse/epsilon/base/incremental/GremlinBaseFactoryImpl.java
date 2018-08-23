package org.eclipse.epsilon.base.incremental;

import java.util.HashMap;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTraceGremlin;


public abstract class GremlinBaseFactoryImpl implements IBaseFactory {
    

	public GremlinBaseFactoryImpl() {
		super();
	}

	@Override
	public IModelTrace createModelTrace(String uri) throws TraceModelDuplicateRelation {
		Map<String, Object> properties = new HashMap<>(1);
		properties.put("uri", uri);
		Vertex m = new DetachedVertex("", "ModelTrace", properties);
		ModelTraceGremlin result = new ModelTraceGremlin();
		result.delegate(m);
		return result;
	}

}
