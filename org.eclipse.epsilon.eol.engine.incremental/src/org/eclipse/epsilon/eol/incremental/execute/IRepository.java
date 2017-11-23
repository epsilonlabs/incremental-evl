package org.eclipse.epsilon.eol.incremental.execute;

/**
 * A generic definition of a repository. A repository provides an illusion of an in-memory collection of objects of 
 * a type. 
 * 
 * The base methods allow objects to be added and removed from the repository. Extending interfaces should provide
 * methods that select objects based on some criteria and return fully instantiated objects.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <T>
 */
public interface IRepository<T> {
	
	// Get Objects
	T get(Object id);
	//void get(Function predicate);
	
	// Add Objects
	void add(T item);

	// Remove Objects
    void remove(T item);
    
}