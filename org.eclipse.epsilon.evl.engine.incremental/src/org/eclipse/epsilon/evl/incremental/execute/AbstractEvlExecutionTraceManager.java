package org.eclipse.epsilon.evl.incremental.execute;


import org.eclipse.epsilon.eol.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;
import org.eclipse.epsilon.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.incremental.execute.introspection.recording.PropertyAccessExecutionListener;


/**
 * A base implementation of the {@link IEvlExecutionTraceManager}.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class AbstractEvlExecutionTraceManager
		extends AbstractEolExecutionTraceManager<IEvlExecutionTraceRepository>
    	implements IEvlExecutionTraceManager<IEvlExecutionTraceRepository> {
	
	/** Repository of execution traces executions */
	protected IEvlExecutionTraceRepository executionTraceRepository;
	
	private PropertyAccessExecutionListener propertyAccessListener = new PropertyAccessExecutionListener();
	private AllInstancesInvocationExecutionListener allAccessListener = new AllInstancesInvocationExecutionListener();
	private SatisfiesInvocationExecutionListener satisfiesListener = new SatisfiesInvocationExecutionListener();
	

	@Override
	public IEvlExecutionTraceRepository getExecutionTraceRepository() {
		if (executionTraceRepository == null) {
			executionTraceRepository = new EvlModuleExecutionRepository(inParallel);
		}
		return executionTraceRepository;
	}


	@Override
	public PropertyAccessExecutionListener getPropertyAccessListener() {
		return propertyAccessListener;
	}

	@Override
	public AllInstancesInvocationExecutionListener getAllInstancesAccessListener() {
		return allAccessListener;
	}

	@Override
	public SatisfiesInvocationExecutionListener getSatisfiesListener() {
		return satisfiesListener;
	}

}
