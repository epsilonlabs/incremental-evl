package org.eclipse.epsilon.evl.incremental.dom;


import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.incremental.dom.TracedExecutableBlock;

public class TracedGuardBlock extends TracedExecutableBlock<Boolean> {
	
	private IGuardTrace trace;

	public TracedGuardBlock(Class<?> expectedResultClass) {
		super(expectedResultClass);
	}
	
	/**
	 * Gets the trace.
	 *
	 * @return the trace
	 */
	public IExecutionTrace getTrace() {
		return trace;
	}

	/**
	 * Sets the trace.
	 *
	 * @param trace the new trace
	 */
	public void setTrace(IExecutionTrace trace) {
		this.trace = (IGuardTrace) trace;
	}
	
	/**
	 * Any chores to be done after execution
	 * @param retVal 
	 */
	@Override
	public void postExecution(Boolean retVal) {
		trace.setResult(retVal);
	}

}
