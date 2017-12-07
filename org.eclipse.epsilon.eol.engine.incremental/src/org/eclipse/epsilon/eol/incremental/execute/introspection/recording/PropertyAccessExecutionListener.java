package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.WeakHashMap;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.AssignmentStatement;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.util.ModelUtil;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * An {@link IExecutionListener} that monitors property access
 *  
 * @author Horacio Hoyos Rodriguez
 *
 */
public class PropertyAccessExecutionListener implements IExecutionListener {
	
	/** Keep track of ModuleElements executing */
	private final Deque<TracedExecutableBlock<?>> moduleElementStack = new ArrayDeque<>();
	
	/** For property access we need to save the value of the left side expression */
	private final WeakHashMap<ModuleElement, Object> cache = new WeakHashMap<ModuleElement, Object>();
	
	/** The trace manager */
	private final IEolExecutionTraceManager<?> traceManager;
	
	/** The Module being executed */
	private final IModuleExecution moduleExecution;
	

	public PropertyAccessExecutionListener(IEolExecutionTraceManager<?> traceManager, IModuleExecution moduleExecution) {
		super();
		this.traceManager = traceManager;
		this.moduleExecution = moduleExecution;
	}

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
				IPropertyAccess pa = record(currentBlock.getTrace(), model, modelElement, propertyName);
				// FIXME We need a smarter serializer depending on the type of object? Probably ask
				// the owning model first, if not the use toString();
				pa.setValue(result.toString());
			}
//			else {
//				// Log warning?
//			}
		}
	}

	private IPropertyAccess record(IExecutionTrace executionTrace, IModel model, Object modelElement, String propertyName) {
		IModelTrace modelTrace = traceManager.modelTraces().getModelTraceByName(model.getName());
		if (modelTrace == null) {
			try {
				modelTrace = moduleExecution.createModelTrace(model.getName());
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			} finally {
				traceManager.modelTraces().add(modelTrace);
			}
		}
		IModelElementTrace modelElementTrace = ModelUtil.findElement(modelTrace, model.getElementId(modelElement));
		if (modelElementTrace == null) {
			try {
				modelElementTrace = modelTrace.createModelElementTrace(model.getElementId(modelElement));
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		IPropertyTrace propertyTrace = ModelUtil.findProperty(modelElementTrace, propertyName);
		if (propertyTrace == null) {
			try {
				propertyTrace = modelElementTrace.createPropertyTrace(propertyName);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
		IPropertyAccess pa = traceManager.moduleExecutionTraces().getPropertyAccessFor(executionTrace, propertyTrace);
		if (pa == null) {
			try {
				pa = executionTrace.createPropertyAccess(propertyTrace);
			} catch (EolIncrementalExecutionException e) {
				throw new IllegalStateException(e);
			}
		}
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
