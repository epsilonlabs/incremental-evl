package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.AbstractExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.evl.incremental.EvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;

import com.google.inject.Inject;

/**
 * A base implementation of the {@link IEvlExecutionTraceManager}.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class InMemoryEvlExecutionTraceManager
		extends AbstractExecutionTraceManager<IEvlModuleTrace, IEvlModuleTraceRepository, IEvlRootElementsFactory>
		implements IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory> {

	private final EvlRootElementsFactory evlTraceFactory;

	@Inject
	public InMemoryEvlExecutionTraceManager(IEvlModuleTraceRepository executionTraceRepository,
			IModelTraceRepository modelTraceRepository,
			EvlRootElementsFactory evlTraceFactory) {
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
	public IEvlRootElementsFactory getTraceFactory() {
		return evlTraceFactory;
	}

}
