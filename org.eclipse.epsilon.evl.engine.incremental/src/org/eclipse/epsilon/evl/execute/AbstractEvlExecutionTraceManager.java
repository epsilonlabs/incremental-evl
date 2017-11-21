package org.eclipse.epsilon.evl.execute;

import org.eclipse.epsilon.eol.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.execute.EolModelRepository;

public class AbstractEvlExecutionTraceManager extends AbstractEolExecutionTraceManager implements IEvlExecutionTraceManager {
	
	protected IEvlModuleExecutionRepository moduleExecutions;
	
	protected IContextTraceRepository contexTraces;
	
	

	public AbstractEvlExecutionTraceManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IEvlModuleExecutionRepository moduleExecutionTraces() {
		if (this.moduleExecutions == null) {
			this.moduleExecutions = new EvlModuleExecutionRepository(inParallel);
		}
		return moduleExecutions;
	}

	@Override
	public IContextTraceRepository contextTraces() {
		if (this.contexTraces == null) {
			this.contexTraces = new ContextTraceRepository(inParallel);
		}
		return contexTraces;
	}

	@Override
	public boolean persistTraceInformation() {
		// TODO Implement IEolExecutionTraceManager.persistTraceInformation
		throw new UnsupportedOperationException("Unimplemented Method    IEolExecutionTraceManager.persistTraceInformation invoked.");
	}

}
