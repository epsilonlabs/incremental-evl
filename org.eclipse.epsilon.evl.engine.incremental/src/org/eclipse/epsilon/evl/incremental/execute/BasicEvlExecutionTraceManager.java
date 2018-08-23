package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.EvlTraceFactory;
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

	private final EvlTraceFactory evlTraceFactory;

	@Inject
	public BasicEvlExecutionTraceManager(IEvlModuleTraceRepository executionTraceRepository,
			IModelTraceRepository modelTraceRepository,
			EvlTraceFactory evlTraceFactory) {
		super(executionTraceRepository, modelTraceRepository);
		this.evlTraceFactory = evlTraceFactory;
	}

	@Override
	public boolean persistTraceInformation() {
		// The BasicEvlExecutionTraceManager is an in-memory manager and hence no
		// persistence is done
		return true;
	}

	@Override
	public IEvlTraceFactory getTraceFactory() {
		return evlTraceFactory;
	}

}
