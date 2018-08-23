package org.eclipse.epsilon.evl.incremental;

import java.util.HashMap;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedVertex;
import org.eclipse.epsilon.base.incremental.GremlinBaseFactoryImpl;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTraceGremlin;

public class GremlinEvlTraceFactory extends GremlinBaseFactoryImpl implements IEvlTraceFactory {

	public GremlinEvlTraceFactory() {
		super();
	}

	@Override
	public IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateRelation {
		Map<String, Object> properties = new HashMap<>(1);
		properties.put("uri", uri);
		Vertex m = new DetachedVertex("", "ModuleTrace", properties);
		EvlModuleTraceGremlin result = new EvlModuleTraceGremlin();
		result.delegate(m);
		return result;
	}

}
