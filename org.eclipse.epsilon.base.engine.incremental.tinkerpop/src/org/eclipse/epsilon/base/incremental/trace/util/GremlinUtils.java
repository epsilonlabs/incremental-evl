package org.eclipse.epsilon.base.incremental.trace.util;

import java.io.File;
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
				retVal = (I) f.createTraceElement(next, g);
			} catch (Exception e) {
				throw new IllegalStateException("Error wrapping iterator element", e);
			}
			return (I) retVal;
		}

	}
	
	public static String identityToString(Object... idObjects) {
		StringBuilder sb = new StringBuilder();
    	String separator = "";
    	for (Object o : idObjects) {
    		sb.append(separator);
    		separator = "!";
    		objectToString(sb, o, true);
    	}
    	return sb.length() > 0 ? sb.toString() : null;
	}
	
    public static String identityToStringFull(Object... idObjects) {
    	StringBuilder sb = new StringBuilder();
    	String separator = "";
    	for (Object o : idObjects) {
    		sb.append(separator);
    		separator = "!";
    		objectToString(sb, o, false);
    	}
    	return sb.length() > 0 ? sb.toString() : null;
    }
    
	private static void objectToString(StringBuilder sb, Object o, boolean truncatePaths) {
		if (o.getClass().isPrimitive() || isWrapperType(o.getClass())) {
			sb.append(o);
		}
		else if (o instanceof String) {
			// Only keep last part of paths which are either scripts or models (hopefully unique)
			if (truncatePaths) {
				String val = (String) o;
				if (val.contains(File.separator)) {
					val = val.substring(val.lastIndexOf(File.separator)+1);
				}
				sb.append(val.replaceAll(" ", "_"));
			} else {
				sb.append(o);
			}
		}
		else {
			sb.append(Integer.toHexString(o.hashCode()));
		}
	}
	
	public static boolean isWrapperType(Class<?> clazz) {
	    return clazz.equals(Boolean.class) || 
	        clazz.equals(Integer.class) ||
	        clazz.equals(Character.class) ||
	        clazz.equals(Byte.class) ||
	        clazz.equals(Short.class) ||
	        clazz.equals(Double.class) ||
	        clazz.equals(Long.class) ||
	        clazz.equals(Float.class);
	}
}
