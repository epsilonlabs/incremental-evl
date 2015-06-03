package org.eclipse.epsilon.evl;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;

/**
 * Implementation of {@link IExecutionListener} that logs property accesses
 * during evaluation of an EOL statement.
 * 
 * @author Jonathan Co
 *
 */
public class ScopeBuilderListener implements IExecutionListener {

	private Collection<String> scopes = new HashSet<String>();
	
	private Object lastResult = null;

	@Override
	public void finishedExecuting(AST ast, Object result, IEolContext ctx) {		
		// Log the name of the property if a call is made
		if (ast instanceof PropertyCallExpression) {
			scopes.add(getElementId(lastResult, ctx)
					+ "."
					+ ((PropertyCallExpression) ast).getPropertyNameExpression().getName());
		}
		
		this.lastResult = result;
	
		// FIXME: Is this needed still? Property access logging IDS only
//		if (result instanceof EObject) {
//		scopes.add(getElementId(result, ctx));
//	} else if (result instanceof EcoreEList) {
//		for (Object o : ((Collection<?>) result)) {
//			scopes.add(getElementId(o, ctx));
//		}
//	} else {
//		scopes.add(((PropertyCallExpression) ast).getPropertyNameExpression().getName());
//	}
	}

	@Override
	public void aboutToExecute(AST ast, IEolContext context) {
		// Do nothing
	}

	@Override
	public void finishedExecutingWithException(AST ast,
			EolRuntimeException exception, IEolContext context) {
		// Do nothing
	}
	
	public Collection<String> getScopes() {
		return scopes;
	}

	private String getElementId(Object o, IEolContext ctx) {
		if (o instanceof EObject) {
			String elementId = ctx.getModelRepository().getOwningModel(o).getElementId(o);
			return elementId;
		}
		return null;
	}
}
