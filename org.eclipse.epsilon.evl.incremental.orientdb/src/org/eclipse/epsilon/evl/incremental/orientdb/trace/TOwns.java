package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import org.eclipse.epsilon.eol.incremental.trace.IElementOwnsProperty;
import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link TOwns} interface represents an edge between an element and a
 * property owned by that element.
 * 
 * @author Jonathan Co
 *
 */
public interface TOwns extends IElementOwnsProperty, EdgeFrame {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "owns";

	/**
	 * @return The property that is owned by the element at
	 *         {@link #getElement()} .
	 */
	@InVertex
	IElementProperty getProperty();

	/**
	 * @return The element that is owned by the property at
	 *         {@link #getProperty()}.
	 */
	@OutVertex
	IModelElement getElement();

}
