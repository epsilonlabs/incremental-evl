package org.eclipse.epsilon.eol.incremental.models;

import java.util.Collection;

public interface Repository<T> {
	
    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

    void remove(Specification specification);

    Collection<T> query(Specification specification);
}