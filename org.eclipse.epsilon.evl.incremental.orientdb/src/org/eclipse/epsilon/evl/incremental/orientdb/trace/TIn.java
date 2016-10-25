package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import org.eclipse.epsilon.eol.incremental.trace.IConstraintInContext;
import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IContextTrace;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

public interface TIn extends IConstraintInContext, EdgeFrame {
	
	String TRACE_TYPE = "in";

	@InVertex
	IContextTrace getContext();
	
	@OutVertex
	IConstraintTrace getConstraint();
}
