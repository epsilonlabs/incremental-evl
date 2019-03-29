package org.eclipse.epsilon.evl.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.Expression;
import org.eclipse.epsilon.eol.dom.OperationCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An An {@link IExecutionListener} that listens to OperationCallExpressions
 * that match the "Satisfies" name. To avoid re-executing dynamic parameters,
 * the listeners activates in the
 * {@link #aboutToExecute(ModuleElement, IEolContext)} to keep track of the
 * execution results of the parameter expressions. After all parameters have
 * been processes, the listener waits for the operation to finish executing.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class SatisfiesInvocationExecutionListener<T extends IModuleExecutionTrace, R extends IModuleExecutionTraceRepository<?>, M extends IExecutionTraceManager<?, ?, ?>>
		implements IExecutionListener{

	private static final Logger logger = LoggerFactory.getLogger(SatisfiesInvocationExecutionListener.class);

	public static final String[] SET_VALUES = new String[] { EvlOperationFactory.SATISFIES_OPERATION,
			EvlOperationFactory.SATISFIES_ONE_OPERATION, EvlOperationFactory.SATISFIES_ALL_OPERATION, };
	public static final Set<String> OPERATION_NAMES = new HashSet<>(Arrays.asList(SET_VALUES));

	/** Keep track of ModuleElements executing */
	private final Deque<TracedModuleElement<IInvariantTrace>> moduleElementStack = new ArrayDeque<>();

	/** The invariant we are currently in */
	// private final IInvariantTrace invariant;

	/** Flag to indicate that it is waiting for parameter execution */
	private boolean listening;

	/** The ASTs of the invocation parameters */
	private Queue<Expression> parameters = new ArrayDeque<>();

	/**
	 * The result of executing the invocation parameters, i.e. the final values of
	 * the parameters of the satisfies invocation
	 */
	private Collection<String> parameterValues = new ArrayList<String>();

	/** The initial satisfies operation */
	private OperationCallExpression waitingFor;

	private Expression elementAst;

	private Object modelElement;

	public SatisfiesInvocationExecutionListener() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		logger.debug("aboutToExecute {}", ast);
		if (ast instanceof TracedModuleElement) {
			IModuleElementTrace met = ((TracedModuleElement<?>) ast).getModuleElementTrace();
			if (met instanceof IInvariantTrace) {
				moduleElementStack.addLast((TracedModuleElement<IInvariantTrace>) ast);
			}
		}
		if (!moduleElementStack.isEmpty()) {
			if (ast instanceof OperationCallExpression) {
				OperationCallExpression oce = (OperationCallExpression) ast;
				String operationName = oce.getOperationName();
				if (OPERATION_NAMES.contains(operationName)) {
					listening = true;
					// FIXME 
					elementAst = oce.getTargetExpression();
					parameters.addAll(oce.getParameterExpressions());
					waitingFor = oce;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		logger.debug("finishedExecuting {} for {}", ast, result);
		if (moduleElementStack.isEmpty()) {
			return;
		}
		if (listening) {
			if (ast.equals(elementAst)) {
				modelElement = result;
			}
			if (ast.equals(parameters.peek())) {
				parameterValues.add((String) result);
				parameters.poll();
				listening = !parameters.isEmpty();
			}
		} else {
			TracedModuleElement<IInvariantTrace> currentInvariant = moduleElementStack.peekFirst();
			if (ast.equals(waitingFor)) {
				boolean all = EvlOperationFactory.SATISFIES_ALL_OPERATION.equals(waitingFor.getOperationName());
				final IIncrementalModel model = getModelThatKnowsElement(modelElement, context);
				assert model != null;
				record(all, currentInvariant, (IIncrementalBaseContext<T, R, M>) context, model, model.getElementId(modelElement));
				parameters.clear();
				parameterValues.clear();
				listening = false;
				waitingFor = null;
			} else {
				if (ast instanceof TracedConstraint) {
					TracedConstraint block = (TracedConstraint) ast;
					if (currentInvariant.equals(block)) {
						moduleElementStack.pollFirst();
					}
				}
				// Something went wrong?
				parameters.clear();
				parameterValues.clear();
				listening = false;
				waitingFor = null;
			}
		}
	}
	
	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception,
			IEolContext context) {
		logger.debug("finishedExecutingWithException");
		if (moduleElementStack.isEmpty()) {
			return;
		}
		TracedModuleElement<IInvariantTrace> currentInvariant = moduleElementStack.peekFirst();
		if (ast instanceof TracedConstraint) {
			TracedConstraint block = (TracedConstraint) ast;
			if (currentInvariant.equals(block)) {
				moduleElementStack.pollFirst();
			}
		}
		// Something went wrong?
		parameters.clear();
		parameterValues.clear();
		listening = false;
		waitingFor = null;
	}
	
	public boolean done() {
		return moduleElementStack.isEmpty();
	}

	private void record(
		boolean all,
		TracedModuleElement<IInvariantTrace> invariantTrace,
		IIncrementalBaseContext<T,R,M> context,
		IIncrementalModel model,
		Object modelElementUri) {
		logger.info("Creating SatisfiesTrace. invariant: {}, satisfied: {}, all: {}", invariantTrace.getModuleElementTrace().getName(),
				parameterValues, all);

		IEvlModuleTraceRepository executionTraceRepository = null;
		try {
			executionTraceRepository = (IEvlModuleTraceRepository) context.getTraceManager().getExecutionTraceRepository();
		} catch (EolRuntimeException e1) {
			logger.error("Error getting trace manager", e1);
			throw new IllegalStateException("Error getting trace manager", e1);
		}
		
		// Each parameter should be an Invariant name
		IContextTrace contextTrace = invariantTrace.getModuleElementTrace().invariantContext().get();
		Set<IInvariantTrace> invariants = new HashSet<>();
		for (Object p : parameterValues) {
			assert p instanceof String;
			String invariantName = (String) p;
			IInvariantTrace targetInvariant = executionTraceRepository.findInvariantTraceinContext(contextTrace, invariantName);
			if (targetInvariant == null) {
				try {
					targetInvariant = contextTrace.getOrCreateInvariantTrace((String) p);
				} catch (EolIncrementalExecutionException e) {
					logger.error("Uknown invariant for {}: {}", invariantName, p);
					throw new IllegalStateException(String.format("Uknown invariant for %s: %s", invariantName, p), e);
				}
			}
			invariants.add(targetInvariant);
		}
		
		IModelTraceRepository modelTraceRepository;
		try {
			modelTraceRepository = context.getTraceManager().getModelTraceRepository();
		} catch (EolRuntimeException e1) {
			logger.error("Error retreiving ModelTraceRepository");
			throw new IllegalStateException("Error retreiving ModelTraceRepository", e1);
		}
		IModelElementTrace modelElementTrace = modelTraceRepository.getModelElementTraceFor(model.getModelUri(), (String) modelElementUri);
		
		
		String moduleUri = context.getModule().getUri().toString();
		IModuleExecutionTrace moduleExecutionTrace = executionTraceRepository
				.getModuleExecutionTraceByIdentity(moduleUri);
		
		
				
		ISatisfiesAccess result = null;
		try {
			result = moduleExecutionTrace.getOrCreateAccess(ISatisfiesAccess.class, invariantTrace.getModuleElementTrace(), invariantTrace.getCurrentContext(), modelElementTrace);
		} catch (EolIncrementalExecutionException e) {
			throw new IllegalStateException(e);
		} finally {
			for (IInvariantTrace i : invariants) {
				try {
					result.satisfiedInvariants().create(i);
				} catch (TraceModelConflictRelation e) {
					logger.error("Error created SatsifiesInvariant trace relation", e);
				}
			}
			result.setAll(all);
		}
	}
	
	private IIncrementalModel getModelThatKnowsElement(Object object, IEolContext context) {
		for (IModel model : context.getModelRepository().getModels()) {
			if (model.isModelElement(object)) {
				return (IIncrementalModel) model;
			}
		}
		return null;
	}
	


}
