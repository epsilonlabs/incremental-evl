package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

public interface TIn extends TraceComponent, EdgeFrame {
	
	String TRACE_TYPE = "in";

	@InVertex
	TContext getContext();
	
	@OutVertex
	TConstraint getConstraint();
}
