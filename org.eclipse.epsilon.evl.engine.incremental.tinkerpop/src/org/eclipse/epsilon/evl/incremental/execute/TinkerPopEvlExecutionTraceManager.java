package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.AbstractExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.IEvlTraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

import com.google.inject.Inject;

public class TinkerPopEvlExecutionTraceManager 
	extends AbstractExecutionTraceManager<IEvlModuleTrace, IEvlModuleTraceRepository, IEvlTraceFactory>
	implements IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory> {

	private IEvlTraceFactory evlTraceFactory;
	
	@Inject
	protected TinkerPopEvlExecutionTraceManager(IEvlModuleTraceRepository executionTraceRepository,
			IModelTraceRepository modelTraceRepository,
			IEvlTraceFactory evlTraceFactory) {
		super(executionTraceRepository, modelTraceRepository);
		this.evlTraceFactory = evlTraceFactory;
	}

	@Override
	public boolean persistTraceInformation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IEvlTraceFactory getTraceFactory() {
		return evlTraceFactory;
	}

}
