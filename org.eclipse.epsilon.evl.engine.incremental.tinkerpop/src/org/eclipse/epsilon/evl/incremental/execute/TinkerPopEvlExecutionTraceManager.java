package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.AbstractExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

import com.google.inject.Inject;

public class TinkerPopEvlExecutionTraceManager extends AbstractExecutionTraceManager implements IEvlExecutionTraceManager {

	private final IEvlRootElementsFactory evlTraceFactory;
	private final IEvlModuleTraceRepository executionTraceRepository;
	
	@Inject
	protected TinkerPopEvlExecutionTraceManager(IEvlModuleTraceRepository exctnTrcRpstry,
			IModelTraceRepository mdlTrcRpstry,
			IEvlRootElementsFactory evlTrcFctry) {
		super(mdlTrcRpstry);
		evlTraceFactory = evlTrcFctry;
		executionTraceRepository = exctnTrcRpstry;
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

	@Override
	public IEvlModuleTraceRepository getExecutionTraceRepository() {
		return executionTraceRepository;
	}

}
