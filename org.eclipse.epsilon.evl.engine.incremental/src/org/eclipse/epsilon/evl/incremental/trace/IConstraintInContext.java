package org.eclipse.epsilon.evl.incremental.trace;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

// FIXME This might be only specific to OrientDB
public interface IConstraintInContext extends TraceComponent, EdgeFrame {
	
	String TRACE_TYPE = "in";

	@InVertex
	IContextTrace getContext();
	
	@OutVertex
	IConstraintTrace getConstraint();
}
