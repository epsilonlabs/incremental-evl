package org.eclipse.epsilon.evl.incremental.orientdb.trace;

import org.eclipse.epsilon.eol.incremental.trace.IConstraintTrace;
import org.eclipse.epsilon.eol.incremental.trace.IElementProperty;
import org.eclipse.epsilon.eol.incremental.trace.IModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * The {@link TScope} interface represents a constraint scope vertex in the
 * trace graph.
 * 
 * @author Jonathan Co
 *
 */
public interface TScope extends ITraceScope, VertexFrame {

	/**
	 * Common name of this trace element
	 */
	String TRACE_TYPE = "scope";
	
	String RESULT = "result";

	@Property(RESULT)
	void setResult(boolean result);
	
	@Property(RESULT)
	boolean getResult(boolean result);
	
	/**
	 * Get the constraint that is used to evaluate this scope.
	 * 
	 * @return The constraint.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.IN)
	IConstraintTrace getConstraint();

	/**
	 * Set the constraint that is used to evaluate this scope.
	 * 
	 * @param constraint
	 *            the new constraint.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.IN)
	void setConstraint(IConstraintTrace constraint);

	/**
	 * Get the model element that is used as the root of this scope
	 * 
	 * @return The model element.
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.IN)
	IModelElement getRootElement();

	/**
	 * Set the model element that is used as the root of this scope
	 * 
	 * @param element
	 *            the new model element
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.IN)
	void setRootElement(IModelElement element);

	/**
	 * Get the properties accessed by this scope.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	Iterable<IElementProperty> getProperties();

	/**
	 * Add a property that is accessed this scope.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this scope.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	IElementProperty addProperty(IElementProperty property);

	/**
	 * Remove a property that is accessed by this scope.
	 * 
	 * @param property
	 *            The property to remove.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	void removeProperty(IElementProperty property);

}
