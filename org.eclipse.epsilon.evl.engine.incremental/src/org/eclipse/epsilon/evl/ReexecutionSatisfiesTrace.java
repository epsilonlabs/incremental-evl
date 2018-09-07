package org.eclipse.epsilon.evl;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.ISatisfiesTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that should be re-executed
 * @author horacio
 *
 */
public class ReexecutionSatisfiesTrace extends ReexecutionTrace<ISatisfiesTrace> {
	
	
	public ReexecutionSatisfiesTrace(ISatisfiesTrace moduleTrace, IExecutionContext exexutionContext) {
		super(moduleTrace, exexutionContext);
	}

}
