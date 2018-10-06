package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.AbstractExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

import com.google.inject.Inject;

public class TinkerPopEvlExecutionTraceManager 
	extends AbstractExecutionTraceManager<IEvlModuleTrace, IEvlModuleTraceRepository, IEvlRootElementsFactory>
	implements IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory> {

	private IEvlRootElementsFactory evlTraceFactory;
	
	@Inject
	protected TinkerPopEvlExecutionTraceManager(IEvlModuleTraceRepository executionTraceRepository,
			IModelTraceRepository modelTraceRepository,
			IEvlRootElementsFactory evlTraceFactory) {
		super(executionTraceRepository, modelTraceRepository);
		this.evlTraceFactory = evlTraceFactory;
	}

	@Override
	public boolean persistTraceInformation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IEvlRootElementsFactory getTraceFactory() {
		return evlTraceFactory;
	}

}
