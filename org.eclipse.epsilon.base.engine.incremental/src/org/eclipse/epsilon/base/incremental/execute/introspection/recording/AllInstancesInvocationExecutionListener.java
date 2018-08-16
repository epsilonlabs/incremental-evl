package org.eclipse.epsilon.base.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.models.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@link IExecutionListener} that monitors execution of operations that
 * retrieve all elements of a type/kind.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class AllInstancesInvocationExecutionListener<T extends IModuleExecutionTrace, R extends IModuleExecutionTraceRepository<?>, M extends IExecutionTraceManager<?, ?, ?>>
		implements IExecutionListener<IIncrementalBaseContext<T, R, M>> {

	private static final Logger logger = LoggerFactory.getLogger(AllInstancesInvocationExecutionListener.class);

	/** The name of the operations of interest */
	public static final String[] SET_VALUES = new String[] { "all", "allInstances", "allOfKind", "allOfType" };

	/** Static set for quick search */
	public static final Set<String> OPERATION_NAMES = new HashSet<>(Arrays.asList(SET_VALUES));

	/** Keep track of ModuleElements executing */
	private final Deque<TracedModuleElement<?>> moduleElementStack = new ArrayDeque<>();

	public AllInstancesInvocationExecutionListener() {
		super();
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IIncrementalBaseContext<T, R, M> context) {
		logger.debug("aboutToExecute {}", ast);
		if (ast instanceof TracedModuleElement) {
			moduleElementStack.addLast((TracedModuleElement<?>) ast);
		}
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IIncrementalBaseContext<T, R, M> context) {
		logger.debug("finishedExecuting {} for {}", ast, result);
		if (moduleElementStack.isEmpty()) {
			return;
		}
		TracedModuleElement<?> currentAst = moduleElementStack.peekFirst();
		if (currentAst.equals(ast)) {
			moduleElementStack.pollFirst();
		}
		if (ast instanceof OperationCallExpression) {
			OperationCallExpression oce = (OperationCallExpression) ast;
			String operationName = oce.getOperationName();
			if (OPERATION_NAMES.contains(operationName)) {
				// We assume the targetExpression resolved in a type
				String typeName = oce.getTargetExpression().getResolvedType().getName();
				boolean ofKind = !"allOfType".equals(operationName);
				try {
					record(currentAst.getCurrentTrace(), ofKind, typeName, context);
				} catch (EolIncrementalExecutionException e) {
					logger.warn("Unable to create traces for the execution of {}", ast, e);
				} catch (EolRuntimeException e) {
					logger.warn("Unable to create traces for the execution of {}", ast, e);
				}
			}
		}
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception,
			IIncrementalBaseContext<T, R, M> context) {
		logger.debug("finishedExecutingWithException");
	}

	public boolean done() {
		return moduleElementStack.isEmpty();
	}

	// TODO Split this method so we can test it
	private void record(IModuleElementTrace executionTrace, boolean ofKind, String modelAndMetaClass,
			IIncrementalBaseContext<T, R, M> context) throws EolIncrementalExecutionException, EolRuntimeException {

		logger.info("Recording AllInstancesAccess. Type: {}, ofKind: {}", modelAndMetaClass, ofKind);
		String modelName;
		String typeName;
		if (modelAndMetaClass.indexOf("!") > -1) {
			String[] parts = modelAndMetaClass.split("!");
			modelName = parts[0];
			typeName = parts[1];
		} else {
			modelName = "";
			typeName = modelAndMetaClass;
		}
		IModel model;
		try {
			model = context.getModelRepository().getModelByName(modelName);
		} catch (EolModelNotFoundException e) {
			logger.error("Could not find model for type: {}", modelAndMetaClass);
			throw new EolIncrementalExecutionException("Could not find model for type: " + modelAndMetaClass, e);
		}
		if (!(model instanceof IIncrementalModel)) {
			logger.warn("Can not trace non-incremental models. Model {} is not an IIncrementalModel", model);
			throw new EolIncrementalExecutionException(modelName);
		}
		IIncrementalModel incrementalModel = (IIncrementalModel) model;
		IModuleExecutionTraceRepository<?> executionTraceRepository = context.getTraceManager()
				.getExecutionTraceRepository();
		String moduleUri = context.getModule().getUri().toString();
		IModuleExecutionTrace moduleExecutionTrace = executionTraceRepository
				.getModuleExecutionTraceByIdentity(moduleUri);
		if (moduleExecutionTrace == null) {
			throw new EolIncrementalExecutionException(
					"A moduleExecutionTrace was not found for the module under execution. "
							+ "The module execution trace must be created at the begining of the execution of the module.");
		}
		IModelTraceRepository modelTraceRepository = context.getTraceManager().getModelTraceRepository();
		IModelTypeTrace typeTrace = modelTraceRepository.getTypeTraceFor(incrementalModel.getModelUri(), typeName);
		if (typeTrace == null) {
			IModelTrace modelTrace = context.getTraceManager().getModelTraceRepository()
					.getModelTraceByIdentity(moduleUri);
			if (modelTrace == null) {
				try {
					modelTrace = new ModelTrace(incrementalModel.getModelUri());
					modelTraceRepository.add(modelTrace);
				} catch (TraceModelDuplicateRelation e) {
					throw new EolIncrementalExecutionException(String.format(
							"A modelTrace was not found for "
									+ "the model wiht uri %s but there was an error craeting it.",
							incrementalModel.getModelUri()));
				}
			}
			typeTrace = modelTrace.createModelTypeTrace(typeName);
		}
		IAllInstancesAccess allIns = moduleExecutionTrace.createAllInstancesAccess(ofKind, executionTrace, typeTrace);
		executionTrace.accesses().create(allIns);
	}

}
