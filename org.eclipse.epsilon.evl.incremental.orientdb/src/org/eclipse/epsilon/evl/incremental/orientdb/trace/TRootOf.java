package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import org.eclipse.epsilon.eol.incremental.trace.IElementRootOfScope;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link TRootOf} interface represents an edge between a scope and its root
 * model element.
 * 
 * @author Jonathan Co
 *
 */
public interface TRootOf extends IElementRootOfScope, EdgeFrame {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "root_of";

	/**
	 * @return The scope that uses the model element at {@link #getProperty()}
	 *         as its root.
	 */
	@InVertex
	ITraceScope getScope();

	/**
	 * @return The model element used as the root of the scope at
	 *         {@link #getScope()}.
	 */
	@OutVertex
	IModelElement getElement();

}
