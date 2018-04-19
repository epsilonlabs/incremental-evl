package org.eclipse.epsilon.evl.incremental.dom;


import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;

public class TracedGuardBlock extends TracedExecutableBlock<IGuardTrace, Boolean> {

	public TracedGuardBlock(Class<?> expectedResultClass) {
		super(expectedResultClass);
	}
	
	
	/**
	 * Any chores to be done after execution
	 * @param retVal 
	 */
	@Override
	public void postExecution(Boolean retVal) {
		currentTrace.setResult(retVal);
	}

}
