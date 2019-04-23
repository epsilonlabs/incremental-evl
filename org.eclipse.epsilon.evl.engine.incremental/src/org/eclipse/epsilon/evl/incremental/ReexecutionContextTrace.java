package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that should be re-executed
 * @author horacio
 *
 */
public class ReexecutionContextTrace extends ReexecutionTrace<IContextTrace> {
	
	
	public ReexecutionContextTrace(IContextTrace moduleTrace, IExecutionContext exexutionContext) {
		super(moduleTrace, exexutionContext);
	}

}
