package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IScopePropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link TAccesses} interface represents an edge between a scope and a
 * single property accessed by that scope in the trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface TAccesses extends IScopePropertyAccess, EdgeFrame {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "accesses";

	/**
	 * @return The scope that is accessing the property at
	 *         {@link #getProperty()}.
	 */
	@OutVertex
	ITraceScope getScope();

	/**
	 * @return The property that has been accessed at {@link #getScope()}.
	 */
	@InVertex
	IElementProperty getProperty();
	
}
