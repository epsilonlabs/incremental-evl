package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that should be re-executed
 * @author horacio
 *
 */
public class ReexecutionMessageTrace extends ReexecutionTrace<IMessageTrace> {
	
	
	public ReexecutionMessageTrace(IMessageTrace moduleTrace, IExecutionContext exexutionContext) {
		super(moduleTrace, exexutionContext);
	}

}
