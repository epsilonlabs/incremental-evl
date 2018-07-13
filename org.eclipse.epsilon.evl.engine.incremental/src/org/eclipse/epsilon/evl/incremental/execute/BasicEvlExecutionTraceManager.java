package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

/**
 * A base implementation of the {@link IEvlExecutionTraceManager}.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class BasicEvlExecutionTraceManager
		extends AbstractEolExecutionTraceManager<IEvlModuleTrace, IEvlModuleTraceRepository>
    	implements IEvlExecutionTraceManager<IEvlModuleTraceRepository> {


	public BasicEvlExecutionTraceManager(IEvlModuleTraceRepository executionTraceRepository, IModelTraceRepository modelTraceRepository) {
		super(executionTraceRepository, modelTraceRepository);
	}

	@Override
	public boolean persistTraceInformation() {
		// TODO Implement IExecutionTraceManager<IEvlModuleTrace,IEvlModuleTraceRepository>.persistTraceInformation
		throw new UnsupportedOperationException("Unimplemented Method    IExecutionTraceManager<IEvlModuleTrace,IEvlModuleTraceRepository>.persistTraceInformation invoked.");
	}


	
}
