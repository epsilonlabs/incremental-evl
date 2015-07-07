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

	/*
	 * ========================================================================
	 * CREATE
	 * ========================================================================
	 */
	TContext createContext(String contextName);

	TConstraint createConstraint(String contraintName, String contextName);

	TConstraint createConstraint(String constraintName, TContext context);

	TElement createElement(String elementId);

	TScope createScope(String elementId, String constraintName, String contextName);

	TScope createScope(String elementId, TConstraint constraint);

	TScope createScope(TElement element, String constraintName, String contextName);

	TScope createScope(TElement element, TConstraint constraint);

	TProperty createProperty(String propertyName, String elementId);

	TProperty createProperty(String propertyName, TElement element);

	/*
	 * ========================================================================
	 * READ
	 * ========================================================================
	 */
	TContext getContext(String contextName);

	TConstraint getConstraint(String constraintName, String contextName);

	TConstraint getConstraint(String constraintName, TContext context);

	TElement getElement(String elementId);

	TScope getScope(String elementId, String constraintName, String contextName);

	TScope getScope(String elementId, TConstraint constraint);

	TScope getScope(TElement element, String constraintName, String contextName);

	TScope getScope(TElement element, TConstraint constraint);

	TProperty getProperty(String propertyName, String elementId);

	TProperty getProperty(String propertyName, TElement element);

	/*
	 * ========================================================================
	 * READ - ALL
	 * ========================================================================
	 */
	Iterable<TContext> getAllContexts();

	Iterable<TConstraint> getAllConstraints();

	Iterable<TElement> getAllElements();

	Iterable<TScope> getAllScopes();

	Iterable<TProperty> getAllProperties();

	Iterable<TScope> getScopesIn(TElement element);

	Iterable<TScope> getScopesIn(String elementId);

	/*
	 * ========================================================================
	 * DELETE
	 * ========================================================================
	 */
	void removeContext(TContext context);

	void removeContext(String contextName);

	void removeConstraint(TConstraint constraint);

	void removeConstraint(String constraintName, String contextName);

	void removeConstraint(String constraintName, TContext context);

	void removeElement(TElement element);

	void removeElement(String elementId);

	void removeScope(TScope scope);

	void removeScope(String elementId, String constraintName, String contextName);

	void removeScope(String elementId, TConstraint constraint);

	void removeScope(TElement element, String constraintName, String contextName);

	void removeScope(TElement element, TConstraint constraint);

	void removeProperty(TProperty property);

	void removeProperty(String propertyName, String elementId);

	void removeProperty(String propertyName, TElement element);

	/**
	 * @return {@code true} if the underlying database used in the trace graph
	 *         is still in use, {@code false} otherwise.
	 */
	boolean isOpen();

	void commit();

	/**
	 * Shutdown the underlying database.
	 */
	void shutdown();

}
