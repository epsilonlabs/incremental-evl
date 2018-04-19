package org.eclipse.epsilon.base.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.WeakHashMap;

import org.eclipse.epsilon.base.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
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
 * An {@link IExecutionListener} that monitors property access
 *  
 * @author Horacio Hoyos Rodriguez
 *
 */
public class PropertyAccessExecutionListener implements IExecutionListener {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyAccessExecutionListener.class);
	
	/** Keep track of ModuleElements executing */
	private final Deque<TracedModuleElement<?>> moduleElementStack = new ArrayDeque<>();
	
	/** For property access we need to save the value of the left side expression */
	private final WeakHashMap<ModuleElement, Object> cache = new WeakHashMap<ModuleElement, Object>();


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
		TracedModuleElement tracedModuleElement = moduleElementStack.peekFirst();
		if (tracedModuleElement.equals(ast)) {
			moduleElementStack.pollFirst();
		}
		// When the left-hand side of a point expression has been executed, store the object on 
		// which the point expression was invoked, so that we can pass it to any property access recorders
		if (isLeftHandSideOfPointExpression(ast)) {
			cache.put(ast, result);
		}

		// When a property access is executed, notify the recorders
		if (isPropertyAccessExpression(ast)) {
			PropertyCallExpression propertyCallExpression = (PropertyCallExpression) ast;
			final Object modelElement = cache.get(propertyCallExpression.getTargetExpression());
			final String propertyName = propertyCallExpression.getPropertyNameExpression().getName();
			final IModel model = getModelThatKnowsAboutProperty(modelElement, propertyName, context);
			if (model != null) {				
				try {
					record(tracedModuleElement.getCurrentTrace(), model, modelElement, propertyName, result);
				} catch (EolIncrementalExecutionException e) {
					logger.warn("Unable to create traces for the execution of {}", ast, e);
				}
			}

		}
	}

	private void record(IModuleElementTrace executionTrace, IModel model, Object modelElement,
			String propertyName, Object result) throws EolIncrementalExecutionException {
		
		logger.info("Recording PropertyAccess. model: {}, element: {}, property: {}",
				model.getName(), modelElement, propertyName);
		if (!(model instanceof IIncrementalModel)) {
			logger.warn("Can not trace non-incremental models. Model {} is not an IIncrementalModel",  model);
			throw new EolIncrementalExecutionException("Can not trace non-incremental models");
		}
		IPropertyAccess pa = ((IIncrementalModel)model).getModelTraceFactory().createPropertyAccess(modelElement, propertyName, executionTrace);
		// FIXME We need a smarter serializer depending on the type of object? Probably ask
		// the owning model first, if not the use toString();
		pa.setValue(result.toString());
		executionTrace.accesses().create(pa);
		
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {
		logger.debug("finishedExecutingWithException");
	}
	
	public boolean done() {
		return moduleElementStack.isEmpty();
	}

	private boolean isLeftHandSideOfPointExpression(ModuleElement ast) {
		return ast.getParent() instanceof PropertyCallExpression && ((PropertyCallExpression)ast.getParent()).getTargetExpression() == ast;
	}
	
	private boolean isPropertyAccessExpression(ModuleElement ast) {
		return ast instanceof PropertyCallExpression &&          // AST is a point expression 
		       !isAssignee(ast);                            // AST is not the left-hand side of an assignment
	}
	
	// Determines whether the specified AST is the left-hand side of an assignment expression
	private boolean isAssignee(ModuleElement ast) {
		return ast.getParent() instanceof AssignmentStatement && 
				((AssignmentStatement) ast.getParent()).getTargetExpression() == ast;
	}
	
	private IModel getModelThatKnowsAboutProperty(Object object, String property, IEolContext context) {
		for (IModel model : context.getModelRepository().getModels()) {
			if (model.knowsAboutProperty(object, property)) {
				return model;
			}
		}
		return null;
	}
}
