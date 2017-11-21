package org.eclipse.epsilon.eol.incremental;

public interface IRepository<T> {
	
	// Get Objects
	T get(Object id);
	//void get(Function predicate);
	
	// Add Objects
	void add(T item);

	// Remove Objects
    void remove(T item);
    
}