package org.eclipse.epsilon.base.incremental.trace.util;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Element;

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
		private final GraphTraversalSource g;
		private final TraceFactory f;

		public IncrementalFactoryIterator(
				Iterator<E> delegate,
				GraphTraversalSource gts,
				TraceFactory factory) {
			super();
			this.delegate = delegate;
			this.g = gts;
			this.f = factory;
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
				retVal = (I) f.createModuleElementTrace(next, g);
			} catch (Exception e) {
				throw new IllegalStateException("Error wrapping iterator element", e);
			}
			return (I) retVal;
		}

	}

}
