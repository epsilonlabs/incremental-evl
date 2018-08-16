package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.EvlTraceFacotry;
import org.eclipse.epsilon.evl.incremental.IEvlTraceFactory;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

import com.google.inject.Inject;

/**
 * A base implementation of the {@link IEvlExecutionTraceManager}.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class BasicEvlExecutionTraceManager
		extends AbstractEolExecutionTraceManager<IEvlModuleTrace, IEvlModuleTraceRepository, IEvlTraceFactory>
		implements IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory> {

	@Inject
	public BasicEvlExecutionTraceManager(IEvlModuleTraceRepository executionTraceRepository,
			IModelTraceRepository modelTraceRepository) {
		super(executionTraceRepository, modelTraceRepository);
	}

	@Override
	public boolean persistTraceInformation() {
		// The BasicEvlExecutionTraceManager is an in-memory manager and hence no
		// persistence is done
		return true;
	}

	@Override
	public IEvlTraceFactory getTraceFactory() {
		return new EvlTraceFacotry();
	}

}
