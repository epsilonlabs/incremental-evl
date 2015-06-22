package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

public interface TIn extends TraceEdge {
	
	String TRACE_TYPE = "in";

	@InVertex
	TContext getContext();
	
	@OutVertex
	TConstraint getConstraint();
}
