package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Recordings<E> extends AbstractCollection<E> implements IRecordings<E> {
	
	private final Collection<E> storage = new LinkedList<E>();

	@Override
	public int size() {
		return storage.size();
	}

	@Override
	public Collection<? extends E> all() {
		return Collections.unmodifiableCollection(storage);
	}

	@Override
	public Set<? extends E> unique() {
		return Collections.unmodifiableSet(new HashSet<>(storage));
	}

	
	@Override
	public boolean add(E e) {
		return storage.add(e);
	}

	@Override
	public Iterator<E> iterator() {
		return storage.iterator();
	}


}
