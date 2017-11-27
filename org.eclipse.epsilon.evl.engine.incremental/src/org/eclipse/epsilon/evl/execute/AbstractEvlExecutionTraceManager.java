package org.eclipse.epsilon.evl.execute;


import org.eclipse.epsilon.eol.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.execute.IEolModuleExecutionRepository;
import org.eclipse.epsilon.eol.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.eol.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
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
	
//	private PropertyAccessExecutionListener propertyAccessListener;// = new PropertyAccessExecutionListener();
//	private AllInstancesInvocationExetionListener allInstancesListener;// = new AllInstancesInvocationExetionListener();
	private SatisfiesInvocationExecutionListener satisfiesListener;// = new SatisfiesInvocationExecutionListener();
	
	@Override
	public IEolModuleExecutionRepository<IEvlModuleExecution> moduleExecutionTraces() {
		if (this.moduleExecutionRepository == null) {
			this.moduleExecutionRepository = new EvlModuleExecutionRepository(inParallel);
		}
		return moduleExecutionRepository;
	}

	@Override
	public IContextTraceRepository contextTraces() {
		if (this.contexTraceRepository == null) {
			this.contexTraceRepository = new ContextTraceRepository(inParallel);
		}
		return contexTraceRepository;
	}
	
//	public void setPropertyAccessListener(PropertyAccessExecutionListener propertyAccessListener) {
//		this.propertyAccessListener = propertyAccessListener;
//	}
//
//	public void setAllInstancesListener(AllInstancesInvocationExetionListener allInstancesListener) {
//		this.allInstancesListener = allInstancesListener;
//	}

	@Override
	public void setSatisfiesListener(SatisfiesInvocationExecutionListener satisfiesListener) {
		this.satisfiesListener = satisfiesListener;
	}

//	@Override
//	public PropertyAccessExecutionListener getPropertyAccessListener() {
//		return propertyAccessListener;
//	}
//
//	@Override
//	public AllInstancesInvocationExetionListener getAllInstancesListener() {
//		return allInstancesListener;
//	}

	@Override
	public SatisfiesInvocationExecutionListener getSatisfiesListener() {
		return satisfiesListener;
	}

}
