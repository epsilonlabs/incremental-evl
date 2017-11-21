package org.eclipse.epsilon.evl.execute.context;


import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;

public class TracedEvlContext extends EvlContext {
	
	private IEvlExecutionTraceManager traceManager;
	private IEvlModuleExecution evlExecution;
	
	
	public IEvlExecutionTraceManager getTraceManager() {
		return traceManager;
	}
	public void setUnitOfWork(IEvlExecutionTraceManager unitOfWork) {
		this.traceManager = unitOfWork;
	}
	public IEvlModuleExecution getEvlExecution() {
		return evlExecution;
	}
	public void setEvlExecution(IEvlModuleExecution evlExecution) {
		this.evlExecution = evlExecution;
	}

}
