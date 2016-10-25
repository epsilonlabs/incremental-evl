package org.eclipse.epsilon.evl.incremental.trace;

/**
 * The {@link IElementOwnsProperty} interface represents an edge between an element and a
 * property owned by that element.
 * 
 * @author Jonathan Co
 *
 */
//FIXME This might be only specific to OrientDB
public interface IElementOwnsProperty extends TraceComponent { //, EdgeFrame {

	/**
	 * Common name of this trace element
	 */
//	String TRACE_TYPE = "owns";

	/**
	 * @return The property that is owned by the element at
	 *         {@link #getElement()} .
	 */
//	@InVertex
	IElementProperty getProperty();

	/**
	 * @return The element that is owned by the property at
	 *         {@link #getProperty()}.
	 */
//	@OutVertex
	IModelElement getElement();

}
