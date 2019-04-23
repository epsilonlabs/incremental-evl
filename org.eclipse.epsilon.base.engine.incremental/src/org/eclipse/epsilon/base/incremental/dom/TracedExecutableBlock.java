/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.dom;

import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.control.IExecutionListener;


/**
 * An executable block that can be traced during execution
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <ReturnType> The expected type of the execution of the block
 */
public class TracedExecutableBlock<TraceType extends IModuleElementTrace, ReturnType> 
		extends ExecutableBlock<ReturnType>
		implements TracedModuleElement<TraceType> {

	protected TraceType moduleElementTrace;
	private IExecutionContext currentContext;
	
	/**
	 * Instantiates a new traced executable block.
	 *
	 * @param expectedResultClass the expected result class
	 */
	public TracedExecutableBlock(Class<?> expectedResultClass) {
		super(expectedResultClass);
	}

	@Override
	public void setModuleElementTrace(TraceType moduleElementTrace) {
		this.moduleElementTrace = moduleElementTrace;
	}

	@Override
	public TraceType getModuleElementTrace() {
		return moduleElementTrace;
	}
	
	@Override
	public void setCurrentContext(IExecutionContext currentContext) {
		this.currentContext = currentContext;
	}

	@Override
	public IExecutionContext getCurrentContext() {
		return this.currentContext;
	}
	
	@Override
	public Object executeBody(IEolContext context) throws EolRuntimeException {
		assert context instanceof IIncrementalBaseContext;
		IIncrementalBaseContext ic =  (IIncrementalBaseContext) context;
		for (IExecutionListener iel : ic.getIncrementalExecutionListeners()) {
			iel.aboutToExecute(this, context);
		}
		Object result;
		try {
			result = super.executeBody(context);	
			for (IExecutionListener iel : ic.getIncrementalExecutionListeners()) {
				iel.finishedExecuting(this, result, context);
			}
		} catch (EolRuntimeException ex) {
			for (IExecutionListener iel : ic.getIncrementalExecutionListeners()) {
				iel.finishedExecutingWithException(this, ex, context);
			}
			throw ex;
		}
		return result;
	}

}
