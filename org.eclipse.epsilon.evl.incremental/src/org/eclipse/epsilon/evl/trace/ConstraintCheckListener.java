package org.eclipse.epsilon.evl.trace;

import java.util.Collection;
import java.util.HashSet;

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
public class ConstraintCheckListener implements IExecutionListener {

	private Collection<Scope> scopes = new HashSet<Scope>();
	
	// The last model element accessed
	private Object lastElement = null;
	
	public ConstraintCheckListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void finishedExecuting(AST ast, Object result, IEolContext ctx) {
		// Log the name of the property accessed in the model element
		if (ast instanceof PropertyCallExpression) {
			String id = ctx.getModelRepository().getOwningModel(lastElement).getElementId(lastElement);
			String prop = ((PropertyCallExpression) ast)
					.getPropertyNameExpression().getName();
			scopes.add(new Scope(id, prop));
		}

		// Store the last model element accessed
		this.lastElement = result;
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
	
	public Collection<Scope> getScopes() {
		return scopes;
	}

}
