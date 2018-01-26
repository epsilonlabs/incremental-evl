package org.eclipse.epsilon.evl.execute;


import org.eclipse.epsilon.eol.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.execute.IEolModuleExecutionRepository;
import org.eclipse.epsilon.eol.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.eol.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
import org.eclipse.epsilon.eol.incremental.trace.IModuleExecution;
import org.eclipse.epsilon.evl.execute.introspection.recording.SatisfiesInvocationExecutionListener;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;

/**
 * A base implementation of the {@link IEvlExecutionTraceManager}.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class AbstractEvlExecutionTraceManager
	extends AbstractEolExecutionTraceManager<IEvlModuleExecution> implements IEvlExecutionTraceManager<IEvlModuleExecution> {
	
	/** Repository of module executions */
	protected IEolModuleExecutionRepository<IEvlModuleExecution> moduleExecutionRepository;
	
	/** Repository of context traces */
	protected IContextTraceRepository contexTraceRepository;
	
	private PropertyAccessExecutionListener propertyAccessListener;// = new PropertyAccessExecutionListener();
	private AllInstancesInvocationExecutionListener allAccessListener;// = new AllInstancesInvocationExetionListener();
	private SatisfiesInvocationExecutionListener satisfiesListener;// = new SatisfiesInvocationExecutionListener();
	
	@Override
	public void initExecutionListeners(IModuleExecution evlExecution) {
		this.propertyAccessListener = new PropertyAccessExecutionListener(this, evlExecution);
		this.allAccessListener = new AllInstancesInvocationExecutionListener(this, evlExecution);
		this.satisfiesListener = new SatisfiesInvocationExecutionListener();
	}

	@Override
	public IEolModuleExecutionRepository<IEvlModuleExecution> moduleExecutionTraces() {
		if (this.moduleExecutionRepository == null) {
			this.moduleExecutionRepository = new EvlModuleExecutionRepository(inParallel);
		}
		return moduleExecutionRepository;
		
		/*
		 PropertyAccessExecutionListener proAccessListener = 
		AllInstancesInvocationExecutionListener allInvocListener = 
		SatisfiesInvocationExecutionListener satisfiesListener = 
		
		 */
	}

	@Override
	public IContextTraceRepository getContextTraceRepository() {
		if (this.contexTraceRepository == null) {
			this.contexTraceRepository = new ContextTraceRepository(inParallel);
		}
		return contexTraceRepository;
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
