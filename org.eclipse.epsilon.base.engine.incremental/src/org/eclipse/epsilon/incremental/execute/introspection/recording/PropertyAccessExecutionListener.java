package org.eclipse.epsilon.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.WeakHashMap;

import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.PropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.impl.PropertyTrace;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.AssignmentStatement;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.incremental.TraceModelDuplicateRelation;
import org.eclipse.epsilon.incremental.dom.TracedExecutableBlock;
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
	private final Deque<TracedExecutableBlock<?>> moduleElementStack = new ArrayDeque<>();
	
	/** For property access we need to save the value of the left side expression */
	private final WeakHashMap<ModuleElement, Object> cache = new WeakHashMap<ModuleElement, Object>();


	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		if (ast instanceof TracedExecutableBlock) {
			moduleElementStack.addLast((TracedExecutableBlock<?>) ast);
		}
	}

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext context) {
		if (moduleElementStack.isEmpty()) {
			return;
		}
		TracedExecutableBlock<?> currentBlock = moduleElementStack.peekFirst();
		if (currentBlock.equals(ast)) {
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
				IPropertyAccess pa;
				try {
					pa = record(currentBlock.getTrace(), model, modelElement, propertyName);
					// FIXME We need a smarter serializer depending on the type of object? Probably ask
					// the owning model first, if not the use toString();
					pa.setValue(result.toString());
				} catch (TraceModelDuplicateRelation e) {
					logger.warn("Unable to create traces for the execution of {}", ast, e);
				}
				
			}

		}
	}

	private IPropertyAccess record(IExecutionTrace executionTrace, IModel model, Object modelElement,
			String propertyName) throws TraceModelDuplicateRelation {
		IModelTrace modelTrace = new ModelTrace(model.getName());
		IModelElementTrace modelElementTrace = new ModelElementTrace(model.getElementId(modelElement), modelTrace);
		IPropertyTrace propertyTrace = new PropertyTrace(propertyName, modelElementTrace);
		IPropertyAccess pa = new PropertyAccess(propertyTrace);
		return pa;
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast, EolRuntimeException exception, IEolContext context) {
		// Pass, executions are not traced
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
