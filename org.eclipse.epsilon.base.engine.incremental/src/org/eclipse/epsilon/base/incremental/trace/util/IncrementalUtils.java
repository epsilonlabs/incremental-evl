package org.eclipse.epsilon.base.incremental.trace.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;

/**
 * A collection of helper methods
 * @author Horacio Hoyos Rodriguez
 *
 */
public class IncrementalUtils {

	public static <T> Set<T> asSet(Iterator<T> sourceIterator) {
		final Set<T> s = new HashSet<T>();
		while (sourceIterator.hasNext())
			s.add(sourceIterator.next());
		return s;
	}

	public static <T> List<T> asList(Iterator<T> sourceIterator) {
		final List<T> s = new ArrayList<T>();
		while (sourceIterator.hasNext())
			s.add(sourceIterator.next());
		return s;
	}

	public static <T> Stream<T> asStream(Iterator<T> sourceIterator, boolean parallel) {
		Iterable<T> iterable = () -> sourceIterator;
		return StreamSupport.stream(iterable.spliterator(), parallel);
	}

	public static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
		return asStream(sourceIterator, false);
	}

	public static <T> boolean equalOrderedIterators(Iterator<T> it1, Iterator<T> it2) {
		while (it1.hasNext() && it2.hasNext()) {
			if (!it1.next().equals(it2.next())) {
				return false;
			}
		}
		if (it1.hasNext() || it2.hasNext()) {
			return false;
		}
		return true;
	}

	public static <T> boolean equalUniqueIterators(Iterator<T> it1, Iterator<T> it2) {
		Set<T> set1 = asSet(it1);
		Set<T> set2 = asSet(it2);
		if (set1.size() != set2.size()) {
			return false;
		}
		set1.removeAll(set2);
		if (!set1.isEmpty()) {
			return false;
		}
		return true;
	}

	public static <T> boolean equalIterators(Iterator<T> it1, Iterator<T> it2) {
		List<T> s1 = asList(it1);
		List<T> s2 = asList(it2);
		if (s1.size() != s2.size()) {
			return false;
		}
		if (!s1.containsAll(s2)) {
			return false;
		}
		return true;
	}

	public static <T> int iteratorHashCode(Iterator<T> it) {
		int hashCode = 1;
		while (it.hasNext()) {
			T obj = it.next();
			hashCode = 41 * hashCode + (obj == null ? 0 : obj.hashCode());
		}
		return hashCode;
	}


	public static boolean isLeftHandSideOfPointExpression(ModuleElement ast) {
		return ast.getParent() instanceof PropertyCallExpression
				&& ((PropertyCallExpression) ast.getParent()).getTargetExpression() == ast;
	}

}
