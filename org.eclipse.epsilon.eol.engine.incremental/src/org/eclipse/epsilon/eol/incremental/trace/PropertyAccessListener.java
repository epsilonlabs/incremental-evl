/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thanos Zolotas - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.dom.PropertyCallExpression;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;
import org.eclipse.epsilon.eol.models.IModel;

// TODO: Auto-generated Javadoc
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

	/** The accesses. */
	private Collection<PropertyAccess> accesses = new HashSet<PropertyAccess>();
	
	/** The last element. */
	// The last model element accessed
	private Object lastElement = null;

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.execute.control.IExecutionListener#finishedExecuting(org.eclipse.epsilon.common.module.ModuleElement, java.lang.Object, org.eclipse.epsilon.eol.execute.context.IEolContext)
	 */
	@Override
	public void finishedExecuting(ModuleElement ast, Object result, IEolContext ctx) {
		
		// For EMF it is easy to track model elements as we only need to test if the result was an EObject, to support
		// a more generalised EMC approach tracking of the accessed element has to be done more intelligently
		// For the time being we can 1. Find the owning model of the result, if if exists then we can save the
		// reference. However, this seems fragile because if the result of the evaluation is another EOBject, then
		// it will break.
		// The lastElementshould be set not based on the result of execution, but before execution of the ast, or before
		// execution of the ModuleElement (i.e. block of code).
		IModel om = ctx.getModelRepository().getOwningModel(result);
		if (om != null) {
//		if (result instanceof EObject) {
			this.lastElement = result;
//		}
		}
		// Log the name of the property accessed in the model element
		if (ast instanceof PropertyCallExpression) {
			String id = ctx.getModelRepository().getOwningModel(lastElement).getElementId(lastElement);
			String prop = ((PropertyCallExpression) ast)
					.getPropertyNameExpression().getName();
			accesses.add(new PropertyAccess(id, prop));
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.execute.control.IExecutionListener#aboutToExecute(org.eclipse.epsilon.common.module.ModuleElement, org.eclipse.epsilon.eol.execute.context.IEolContext)
	 */
	@Override
	public void aboutToExecute(ModuleElement ast, IEolContext context) {
		// Do nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.eol.execute.control.IExecutionListener#finishedExecutingWithException(org.eclipse.epsilon.common.module.ModuleElement, org.eclipse.epsilon.eol.exceptions.EolRuntimeException, org.eclipse.epsilon.eol.execute.context.IEolContext)
	 */
	@Override
	public void finishedExecutingWithException(ModuleElement ast,
			EolRuntimeException exception, IEolContext context) {
		// Do nothing
	}
	
	/**
	 * Gets the property accesses.
	 *
	 * @return the property accesses
	 */
	public Collection<PropertyAccess> getPropertyAccesses() {
		return accesses;
	}

}
