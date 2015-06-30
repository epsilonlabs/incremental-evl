package org.eclipse.epsilon.evl.trace;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.util.wrappers.WrapperGraph;

/**
 * An constraint evaluation trace represented in graph from.
 * 
 * @author Jonathan Co
 *
 * @param <T>
 *            The type of graph engine used by this {@link TraceGraph}s.
 */
public interface TraceGraph<T extends Graph> extends WrapperGraph<T> {

	TContext getContext(String name);

	Iterable<TContext> getAllContexts();

	/**
	 * Get the constraint by its name. If the constraint does not exist then a
	 * new constraint is returned.
	 * 
	 * @param name
	 *            The name of the constraint to retrieve.
	 * @param context
	 *            The context this constraint is in.
	 * @return A constraint with the given name.
	 */
	TConstraint getConstraint(String name, TContext context);

	/**
	 * @return All the constraints in this trace graph.
	 */
	Iterable<TConstraint> getAllConstraints();

	/**
	 * Get the scope that is evaluated by the given constraint and uses the
	 * given element as its root. If the scope does not exist then a new scope
	 * is returned.
	 * 
	 * @param constraint
	 *            The constraint used to evaluate the scope.
	 * @param element
	 *            The element that is the root of the scope.
	 * @return A scope or {@code null} if it does not exist.
	 */
	TScope getScope(TConstraint constraint, TElement element);

	/**
	 * @return All the scopes in this trace graph.
	 */
	Iterable<TScope> getAllScopes();

	/**
	 * Get the model element by its id. If the element does not exist then a new
	 * element is returned.
	 * 
	 * @param elementId
	 *            The id of the element to retrieve.
	 * @return A element with the given name.
	 */
	TElement getElement(String elementId);

	/**
	 * @return All the elements in the trace graph.
	 */
	Iterable<TElement> getAllElements();

	/**
	 * Get the property by its name and owning element. If the property does not
	 * exist then a new property is returned.
	 * 
	 * @param name
	 *            The name of the property to retrieve.
	 * @param owner
	 *            The owning model element of the property to retrieve.
	 * @return A property with the given name.
	 */
	TProperty getProperty(String name, TElement owner);
	
	TProperty getProperty(String name, String elementId);

	/**
	 * @return All the properties in the trace graph.
	 */
	Iterable<TProperty> getAllProperties();
	
	void removeElement(String elementId);
	
	void removeElement(TElement element);

	/**
	 * @return {@code true} if the underlying database used in the trace graph
	 *         is still in use, {@code false} otherwise.
	 */
	boolean isOpen();
	
	void commit();

	/**
	 * Shutdown the underlying database.
	 */
	void close();

}
