package org.eclipse.epsilon.base.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.WeakHashMap;

import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotSerializableModelException;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.AssignmentStatement;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.models.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@link IExecutionListener} that monitors property access. In a
 * PropertyCallExpression <targetExpression>.<propertyNameExpression>
 * 
 * We need to wait for the targetExpression to be evaluated so the element on
 * with the property is accessed is resolved. Additionally, if the
 * PropertyCallExpression is inside an AssignmentStatement, we are only
 * interested in PropertyCallExpressions that happen in the left hand side
 * (access).
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class PropertyAccessExecutionListener<T extends IModuleExecutionTrace, R extends IModuleExecutionTraceRepository<?>, M extends IExecutionTraceManager<?, ?, ?>>
		implements IExecutionListener {

	private static final Logger logger = LoggerFactory.getLogger(PropertyAccessExecutionListener.class);

	/** Keep track of ModuleElements executing */
	private final Deque<TracedModuleElement<?>> moduleElementStack = new ArrayDeque<>();

	/** For property access we need to save the value of the left side expression */
	private final WeakHashMap<ModuleElement, Object> cache = new WeakHashMap<ModuleElement, Object>();

	public PropertyAccessExecutionListener() {
		super();
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		logger.debug("aboutToExecute {}", ast);
		if (ast instanceof TracedModuleElement) {
			moduleElementStack.addLast((TracedModuleElement<?>) ast);
		}
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		logger.debug("finishedExecuting {} for {}", ast, result);
		if (moduleElementStack.isEmpty()) {
			return;
		}
		TracedModuleElement<?> tracedModuleElement = moduleElementStack.peekFirst();
		if (tracedModuleElement.equals(ast)) {
			moduleElementStack.pollFirst();
		}
		// When the left-hand side of a point expression has been executed, store the
		// object on
		// which the point expression was invoked, so that we can pass it to any
		// property access recorders
		if (IncrementalUtils.isLeftHandSideOfPointExpression(ast)) {
			cache.put(ast, result);
		}

		// When a property access is executed, notify the recorders
		if (isPropertyAccessExpression(ast)) {
			PropertyCallExpression propertyCallExpression = (PropertyCallExpression) ast;
			final Object modelElement = cache.get(propertyCallExpression.getTargetExpression());
			final String propertyName = propertyCallExpression.getPropertyNameExpression().getName();
			final IIncrementalModel model = getModelThatKnowsAboutProperty(modelElement, propertyName,context);
			if (model != null) {
				try {
					record(tracedModuleElement.getModuleElementTrace(), tracedModuleElement.getCurrentContext(), model, modelElement, propertyName, result, (IIncrementalBaseContext<T, R, M>) context);
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
			IEolContext context) {
		logger.debug("finishedExecutingWithException: ", exception);
		if (moduleElementStack.isEmpty()) {
			return;
		}
		TracedModuleElement<?> tracedModuleElement = moduleElementStack.peekFirst();
		if (tracedModuleElement.equals(ast)) {
			moduleElementStack.pollFirst();
		}
	}

	public boolean done() {
		return moduleElementStack.isEmpty();
	}

	/**
	 * Save the property access information as a IPropertyAccess
	 * 
	 * @param executionTrace
	 * @param iExecutionContext 
	 * @param model
	 * @param modelElement
	 * @param propertyName
	 * @param result
	 * @throws EolIncrementalExecutionException
	 * @throws EolRuntimeException
	 */
	private void record(
		IModuleElementTrace executionTrace,
		IExecutionContext currentContext,
		IIncrementalModel model,
		Object modelElement,
		String propertyName,
		Object result,
		IIncrementalBaseContext<T, R, M> context)
		throws EolIncrementalExecutionException, EolRuntimeException {

		logger.info("Recording PropertyAccess. model: {}, element: {}, property: {}, result: {}", model.getName(),
				modelElement, propertyName, result == null ? "Null" : "SomeValue");
		logger.debug("result: {}", result);

		IModuleExecutionTraceRepository<?> executionTraceRepository = context.getTraceManager()
				.getExecutionTraceRepository();
		IModelTraceRepository modelTraceRepository = context.getTraceManager().getModelTraceRepository();
		
		String moduleUri = context.getModule().getUri().toString();
		IModuleExecutionTrace moduleExecutionTrace = executionTraceRepository
				.getModuleExecutionTraceByIdentity(moduleUri);
		if (moduleExecutionTrace == null) {
			throw new EolIncrementalExecutionException(
					"A moduleExecutionTrace was not found for the module under execution. "
							+ "The module execution trace must be created at the begining of the execution of the module.");
		}
		IPropertyTrace propertyTrace = modelTraceRepository.getPropertyTraceFor(model.getModelUri(),
				model.getElementId(modelElement), propertyName);
		if (propertyTrace == null) {
			IModelElementTrace elementTrace = IncrementalUtils.getOrCreateModelElementTrace(modelElement, context,
					model);
			propertyTrace = elementTrace.getOrCreatePropertyTrace(propertyName);
		}
		// FIXME A property access should also generate the matching element access.
		IPropertyAccess pa = moduleExecutionTrace.getOrCreateAccess(IPropertyAccess.class, executionTrace, currentContext, propertyTrace);
		setPropertyAccessValue(model, result, context, pa);
	}
	
	/**
	 * Set the property access value. The result can be a model element or an arbitrary value. For
	 * model elements, we want to store the result via the model's convertToString method.
	 * @param model					The model that owns the target expression object
	 * @param result				The result of the execution
	 * @param context				The execution context
	 * @param pa					The property access
	 * @throws EolIncrementalExecutionException	If there is an error serializing the result value.
	 */
	 // FIXME WE need to deal with collection values
	private void setPropertyAccessValue(
		IIncrementalModel model,
		Object result,
		IIncrementalBaseContext<T, R, M> context,
		IPropertyAccess pa) throws EolIncrementalExecutionException {
		String value = null;
		if (model.owns(result)) {
			try {
				value = model.convertToString(result);
			} catch (NotSerializableModelException e) {
				logger.error(e.getMessage());
				throw new EolIncrementalExecutionException(
						"Error getting serializable value of result" + e.getMessage());
			}
		} else {
			IModel resOwner = context.getModelRepository().getOwningModel(result);
			if (resOwner != null && (resOwner instanceof IIncrementalModel)) {
				try {
					value = ((IIncrementalModel)resOwner).convertToString(result);
				} catch (NotSerializableModelException e) {
					logger.error(e.getMessage());
					throw new EolIncrementalExecutionException(
							"Error getting serializable value of result" + e.getMessage());
				}
			}
			if (result != null) {
				value = result.toString();
			}
		}
		pa.setValue(value);
	}

	/**
	 * Validate that the PropertyCallExpression is in the left hand side of an
	 * AssignmentStatement
	 * 
	 * @param ast
	 * @return
	 */
	private boolean isPropertyAccessExpression(ModuleElement ast) {
		return ast instanceof PropertyCallExpression && // AST is a point expression
				!isAssignee(ast); // AST is not the left-hand side of an assignment
	}

	// Determines whether the specified AST is the left-hand side of an assignment
	// expression
	private boolean isAssignee(ModuleElement ast) {
		return ast.getParent() instanceof AssignmentStatement
				&& ((AssignmentStatement) ast.getParent()).getTargetExpression() == ast;
	}

	private IIncrementalModel getModelThatKnowsAboutProperty(Object object, String property, IEolContext context) {
		for (IModel model : context.getModelRepository().getModels()) {
			if (model.knowsAboutProperty(object, property)) {
				return (IIncrementalModel) model;
			}
		}
		return null;
	}
}
