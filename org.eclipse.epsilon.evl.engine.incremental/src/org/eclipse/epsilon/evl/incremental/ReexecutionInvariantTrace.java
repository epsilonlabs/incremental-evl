package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that should be re-executed
 * @author horacio
 *
 */
public class ReexecutionInvariantTrace extends ReexecutionTrace<IInvariantTrace> {
	
	
	public ReexecutionInvariantTrace(IInvariantTrace moduleTrace, IExecutionContext exexutionContext) {
		super(moduleTrace, exexutionContext);
	}

}
