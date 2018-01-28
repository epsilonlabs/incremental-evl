package org.eclipse.epsilon.eol.incremental.trace.util;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSetQueue<E> extends LinkedHashSet<E> implements Queue<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -287332134393895628L;
	
	final java.util.concurrent.locks.ReentrantLock lock = new ReentrantLock();

    public E remove() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E next = iterator().next();
            remove(next);
            return next;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E element() {
        return iterator().next();
    }

    @Override
    public boolean offer(E arg0) {
        return add(arg0);
    }

    @Override
    public E peek() {
        return element();
    }

    @Override
    public E poll() {
        return remove();
    }

}