package org.eclipse.epsilon.evl.incremental.trace;

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
public interface TScope extends TraceComponent, VertexFrame {

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
	TConstraint getConstraint();

	/**
	 * Set the constraint that is used to evaluate this scope.
	 * 
	 * @param constraint
	 *            the new constraint.
	 */
	@Adjacency(label = TEvaluates.TRACE_TYPE, direction = Direction.IN)
	void setConstraint(TConstraint constraint);

	/**
	 * Get the model element that is used as the root of this scope
	 * 
	 * @return The model element.
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.IN)
	TElement getRootElement();

	/**
	 * Set the model element that is used as the root of this scope
	 * 
	 * @param element
	 *            the new model element
	 */
	@Adjacency(label = TRootOf.TRACE_TYPE, direction = Direction.IN)
	void setRootElement(TElement element);

	/**
	 * Get the properties accessed by this scope.
	 * 
	 * @return An {@link Iterable} containing all the relevant properties.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	Iterable<TProperty> getProperties();

	/**
	 * Add a property that is accessed this scope.
	 * 
	 * @param property
	 *            The property to add.
	 * @return the original property parameter but linked to this scope.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	TProperty addProperty(TProperty property);

	/**
	 * Remove a property that is accessed by this scope.
	 * 
	 * @param property
	 *            The property to remove.
	 */
	@Adjacency(label = TAccesses.TRACE_TYPE, direction = Direction.OUT)
	void removeProperty(TProperty property);

}