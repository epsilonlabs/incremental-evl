package org.eclipse.epsilon.evl.incremental.trace;

import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

public interface TIn extends TraceGraphEdge {
	
	String TRACE_TYPE = "in";

	@InVertex
	TContext getContext();
	
	@OutVertex
	TConstraint getConstraint();
}
