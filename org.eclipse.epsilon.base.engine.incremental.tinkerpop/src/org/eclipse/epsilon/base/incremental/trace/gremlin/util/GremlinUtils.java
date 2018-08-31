package org.eclipse.epsilon.base.incremental.trace.gremlin.util;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.util.TraceFactory;

public class GremlinUtils {

	/**
	 * An iterator that knows how to wrap graph vertices/edges in classes as it
	 * iterates.
	 * 
	 * @author Horacio Hoyos
	 *
	 * @param <I>
	 * @param <W>
	 * @param <E>
	 */
	public static class IncrementalIterator<I, W extends GremlinWrapper<E>, E> implements Iterator<I> {

		private final Iterator<E> delegate;
		private GraphTraversalSource g;
		private Class<W> wrapClazz;

		public IncrementalIterator(Iterator<E> delegate, GraphTraversalSource gts, Class<W> wrapClazz) {
			super();
			this.delegate = delegate;
			this.g = gts;
			this.wrapClazz = wrapClazz;

		}

		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		@SuppressWarnings("unchecked")
		@Override
		public I next() {
			E next = delegate.next();
			W retVal = null;
			try {
				retVal = wrapClazz.newInstance();
			} catch (Exception e) {
				throw new IllegalStateException("Error wrapping iterator element");
			}
			retVal.delegate(next);
			retVal.graphTraversalSource(g);
			return (I) retVal;
		}

	}

	/**
	 * 
	 * @author Horacio Hoyos
	 *
	 * @param <I> The iterator generic
	 * @param <E> The delegate class
	 */
	public static class IncrementalFactoryIterator<I, E extends Element> implements Iterator<I> {

		private final Iterator<E> delegate;
		private GraphTraversalSource g;

		public IncrementalFactoryIterator(Iterator<E> delegate, GraphTraversalSource gts) {
			super();
			this.delegate = delegate;
			this.g = gts;
		}

		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		@SuppressWarnings("unchecked")
		@Override
		public I next() {
			E next = delegate.next();
			I retVal = null;
			try {
				retVal = (I) TraceFactory.createModuleElementTrace(next, g);
			} catch (Exception e) {
				throw new IllegalStateException("Error wrapping iterator element", e);
			}
			return (I) retVal;
		}

	}

}
