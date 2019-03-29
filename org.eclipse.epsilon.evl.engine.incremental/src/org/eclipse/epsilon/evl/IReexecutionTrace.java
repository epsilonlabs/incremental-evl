package org.eclipse.epsilon.evl;

import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;

public interface IReexecutionTrace {

	IModuleElementTrace getModuleElementTrace();
	
	/**
	 * This is derived from the module element trace
	 */
	IExecutionContext getExexutionContext();
	
	/**
	 * This is derived, to add a dependent trace set its parent
	 * 
	 * @see #setParentTrace(IReexecutionTrace)
	 * @return
	 */
	Set<IReexecutionTrace> getDependentTraces();
	
	IReexecutionTrace getParentTrace();
	
	void setParentTrace(IReexecutionTrace parent);

}