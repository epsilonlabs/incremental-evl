package org.eclipse.epsilon.evl.incremental.trace;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.common.module.ModuleElement;
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
// FIXME This can be done by the tracing model, and since the model provides the getter and 
// setter, we get the trace information directly without the overhead of having to loop
// over all the ast again. 
public class PropertyAccessListener implements IExecutionListener {

	private Collection<PropertyAccess> accesses = new HashSet<PropertyAccess>();
	
	// The last model element accessed
	private EObject lastElement = null;

	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext ctx) {
		
		if (result instanceof EObject) {
			this.lastElement = (EObject) result;
		}
		
		// Log the name of the property accessed in the model element
		if (ast instanceof PropertyCallExpression) {
			String id = ctx.getModelRepository().getOwningModel(lastElement).getElementId(lastElement);
			String prop = ((PropertyCallExpression) ast)
					.getPropertyNameExpression().getName();
			accesses.add(new PropertyAccess(id, prop));
		}
		
	}

	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		// Do nothing
	}

	@Override
	public void finishedExecutingWithException(ModuleElement ast,
			EolRuntimeException exception, IEolContext context) {
		// Do nothing
	}
	
	public Collection<PropertyAccess> getPropertyAccesses() {
		return accesses;
	}

}
