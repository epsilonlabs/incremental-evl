package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IScopeConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;

import com.tinkerpop.frames.EdgeFrame;
import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

/**
 * The {@link TEvaluates} interface represents an edge between a scope and the
 * constraint used to evaluate it.
 * 
 * @author Jonathan Co
 *
 */
public interface TEvaluates extends IScopeConstraintTrace, EdgeFrame {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "evaluates";

	/**
	 * @return The scope that is evaluated by the constraint at
	 *         {@link #getConstraint()}.
	 */
	@InVertex
	ITraceScope getScope();

	/**
	 * @return The constraint that is used to evaluate the scope at
	 *         {@link #getScope()}.
	 */
	@OutVertex
	IConstraintTrace getConstraint();

}
