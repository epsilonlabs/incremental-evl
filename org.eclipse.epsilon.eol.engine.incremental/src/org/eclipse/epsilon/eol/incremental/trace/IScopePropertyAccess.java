package org.eclipse.epsilon.eol.incremental.trace;

public interface IScopePropertyAccess {

	/**
	 * Common name of this trace element
	 */
//	String TRACE_TYPE = "accesses";

	/**
	 * @return The scope that is accessing the property at
	 *         {@link #getProperty()}.
	 */
//	@OutVertex
	ITraceScope getScope();

	/**
	 * @return The property that has been accessed at {@link #getScope()}.
	 */
//	@InVertex
	IElementProperty getProperty();
}
