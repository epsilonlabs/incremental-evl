package org.eclipse.epsilon.evl.incremental.trace;

/**
 * The {@link IScopeConstraintTrace} interface represents an edge between a scope and the
 * constraint used to evaluate it.
 * 
 * @author Jonathan Co
 *
 */
public interface IScopeConstraintTrace extends TraceComponent { //, EdgeFrame {

	/**
	 * Common name of this trace element
	 */
//	String TRACE_TYPE = "evaluates";

	/**
	 * @return The scope that is evaluated by the constraint at
	 *         {@link #getConstraint()}.
	 */
//	@InVertex
	ITraceScope getScope();

	/**
	 * @return The constraint that is used to evaluate the scope at
	 *         {@link #getScope()}.
	 */
//	@OutVertex
	IConstraintTrace getConstraint();

}
