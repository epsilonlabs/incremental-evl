package org.eclipse.epsilon.base.incremental.trace.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

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

	/**
	 * @param modelElement
	 * @param context
	 * @param model
	 * @param moduleUri
	 * @param moduleExecutionTrace
	 * @return
	 * @throws EolIncrementalExecutionException
	 * @throws EolRuntimeException
	 */
	public static IModelElementTrace getOrCreateModelElementTrace(Object modelElement,
			IIncrementalBaseContext<?, ?, ?> context, final IIncrementalModel model)
			throws EolIncrementalExecutionException, EolRuntimeException {

		IModelTraceRepository modelTraceRepository = context.getTraceManager().getModelTraceRepository();
		IModelElementTrace elementTrace = modelTraceRepository.getModelElementTraceFor(model.getModelUri(),
				model.getElementId(modelElement));
		if (elementTrace == null) {
			IModelTrace modelTrace = modelTraceRepository.getModelTraceByIdentity(model.getModelUri());
			if (modelTrace == null) {
				try {
					modelTrace = new ModelTrace(model.getModelUri());
					modelTraceRepository.add(modelTrace);
				} catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
					throw new EolIncrementalExecutionException(String.format(
							"A modelTrace was not found for "
									+ "the model wiht uri %s but there was an error craeting it.",
							model.getModelUri()));
				}
			}
			String elementType = model.getTypeNameOf(modelElement);
			IModelTypeTrace typeTrace = modelTraceRepository.getTypeTraceFor(model.getModelUri(), elementType);
			if (typeTrace == null) {
				typeTrace = modelTrace.getOrCreateModelTypeTrace(elementType);
			}
			elementTrace = modelTrace.getOrCreateModelElementTrace(model.getElementId(modelElement), typeTrace);
			for (String kind : model.getAllTypeNamesOf(modelElement)) {
				typeTrace = modelTraceRepository.getTypeTraceFor(model.getModelUri(), kind);
				if (typeTrace == null) {
					typeTrace = modelTrace.getOrCreateModelTypeTrace(kind);
				}
				try {
					elementTrace.kind().create(typeTrace);
				} catch (TraceModelConflictRelation e) {
					throw new EolIncrementalExecutionException("There was an error adding the kind information to the Element trace", e);
				}
			}
		}
		return elementTrace;
	}

	public static boolean isLeftHandSideOfPointExpression(ModuleElement ast) {
		return ast.getParent() instanceof PropertyCallExpression
				&& ((PropertyCallExpression) ast.getParent()).getTargetExpression() == ast;
	}

}
