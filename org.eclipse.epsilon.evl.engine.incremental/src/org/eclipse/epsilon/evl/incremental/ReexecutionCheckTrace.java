package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that should be re-executed
 * @author horacio
 *
 */
public class ReexecutionCheckTrace extends ReexecutionTrace<ICheckTrace> {
	
	
	public ReexecutionCheckTrace(ICheckTrace moduleTrace, IExecutionContext exexutionContext) {
		super(moduleTrace, exexutionContext);
	}

}
