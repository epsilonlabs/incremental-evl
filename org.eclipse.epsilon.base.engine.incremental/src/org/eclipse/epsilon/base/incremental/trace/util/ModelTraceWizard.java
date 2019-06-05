package org.eclipse.epsilon.base.incremental.trace.util;

import org.eclipse.epsilon.base.incremental.IBaseRootElementsFactory;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;

/**
 * A class that handles all creation of model traces: model, model elements, types, 
 * properties, etc. The methods in this class should be used after a repository search failed.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class ModelTraceWizard {

	private final IBaseRootElementsFactory factory;
	
	public ModelTraceWizard(IBaseRootElementsFactory fctry) {
		super();
		factory = fctry;
	}

	public IPropertyTrace createPropertyTrace(
		IIncrementalModel incrementalModel,
		Object modelElement,
		String propertyName,
		IIncrementalBaseContext context) {
		IModelTraceRepository modelTraceRepository = context.getTraceManager().getModelTraceRepository();
		IModelElementTrace elementTrace = modelTraceRepository
				.getModelElementTraceFor(incrementalModel.getModelUri(), incrementalModel.getElementId(modelElement))
				.orElseGet(() -> createElementTrace(incrementalModel, modelTraceRepository, modelElement));
		try {
			return elementTrace.getOrCreatePropertyTrace(propertyName);
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException("Model element property trace creation should not fail"
					+ "if repository search failed.", e);
		}
	}
	
	public IModelElementTrace createElementTrace(
		IIncrementalModel incrementalModel,
		IModelTraceRepository modelTraceRepository,
		Object modelElement) {
		IModelTrace modelTrace = modelTraceRepository
				.getModelTraceByIdentity(incrementalModel.getModelUri())
				.orElseGet(() -> {
					return createModelTrace(incrementalModel, modelTraceRepository);
			});
		String elementType = incrementalModel.getTypeNameOf(modelElement);
		IModelTypeTrace typeTrace = modelTraceRepository
			.getTypeTraceFor(incrementalModel.getModelUri(), elementType)
			.orElseGet(() -> createTypeTrace(modelTrace, elementType));
		IModelElementTrace elementTrace;
		try {
			elementTrace = modelTrace
					.getOrCreateModelElementTrace(incrementalModel.getElementId(modelElement), typeTrace);
		} catch (EolIncrementalExecutionException e1) {
			throw new IllegalStateException("Model element trace creation should not fail if "
					+ "repository search failed.", e1);
		}
		for (String kind : incrementalModel.getAllTypeNamesOf(modelElement)) {
			typeTrace = modelTraceRepository.getTypeTraceFor(incrementalModel.getModelUri(), kind)
				.orElseGet(() -> createTypeTrace(modelTrace, elementType));
			try {
				elementTrace.kind().create(typeTrace);
			} catch (TraceModelConflictRelation e) {
				// Since we dont know if the element was new or existed, we can ignore these
			}
		}
		return elementTrace;
	}
	
	public IModelTypeTrace createTypeTrace(IModelTrace modelTrace, String elementType) {
		try {
			return modelTrace.getOrCreateModelTypeTrace(elementType);
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException("Model type creation should not fail if repository search failed.", e);
		}
	}
	
	public IModelTypeTrace createTypeTrace(
		IIncrementalModel incrementalModel,
		String elementType,
		IModelTraceRepository modelTraceRepository) {
		IModelTrace modelTrace = modelTraceRepository
				.getModelTraceByIdentity(incrementalModel.getModelUri())
				.orElseGet(() -> {
					return createModelTrace(incrementalModel, modelTraceRepository);
			});
		try {
			return modelTrace.getOrCreateModelTypeTrace(elementType);
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException("Model type creation should not fail if repository search failed.", e);
		}
	}
	
	public IModelTrace createModelTrace(IIncrementalModel incrementalModel, IModelTraceRepository modelTraceRepository) {
		IModelTrace newTrace = null;
		try {
			newTrace = modelTraceRepository.add(factory.createModelTrace(incrementalModel.getModelUri()));
		} catch (TraceModelDuplicateElement | TraceModelConflictRelation e) {
			// Since this should be called after a failed repository lookup, this exception
			// should not happen and implies a big issue
			throw new IllegalStateException("Model creation should not fail if repository search failed.", e);
		}
		return newTrace;
	}
	
}
