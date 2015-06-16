package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link TOwns} interface represents an edge between an element and a
 * property owned by that element.
 * 
 * @author Jonathan Co
 *
 */
public interface TOwns extends TraceEdge {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "owns";

	/**
	 * @return The property that is owned by the element at
	 *         {@link #getElement()} .
	 */
	@InVertex
	TProperty getProperty();

	/**
	 * @return The element that is owned by the property at
	 *         {@link #getProperty()}.
	 */
	@OutVertex
	TElement getElement();

}
