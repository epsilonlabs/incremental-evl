package org.eclipse.epsilon.evl.incremental.trace;

/**
 * The {@link IElementRootOfScope} interface represents an edge between a scope and its root
 * model element.
 * 
 * @author Jonathan Co
 *
 */
public interface IElementRootOfScope extends TraceComponent { //, EdgeFrame {

	/**
	 * Common name of this trace element
	 */
//	String TRACE_TYPE = "root_of";

	/**
	 * @return The scope that uses the model element at {@link #getProperty()}
	 *         as its root.
	 */
//	@InVertex
	ITraceScope getScope();

	/**
	 * @return The model element used as the root of the scope at
	 *         {@link #getScope()}.
	 */
//	@OutVertex
	IModelElement getElement();

}
