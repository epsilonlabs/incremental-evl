package org.eclipse.epsilon.evl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that should be re-executed
 * @author horacio
 *
 */
public abstract class ReexecutionTrace<T extends IModuleElementTrace> implements IReexecutionTrace {
	
	private final T moduleTrace;
	private final IExecutionContext exexutionContext;
	private Set<IReexecutionTrace> children;
	private IReexecutionTrace parent;
	
	public ReexecutionTrace(T moduleTrace, IExecutionContext exexutionContext) {
		super();
		this.moduleTrace = moduleTrace;
		this.exexutionContext = exexutionContext;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.evl.IReexecutionTrace#getModuleTrace()
	 */
	@Override
	public T getModuleElementTrace() {
		return moduleTrace;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.evl.IReexecutionTrace#getExexutionContext()
	 */
	@Override
	public IExecutionContext getExexutionContext() {
		return exexutionContext;
	}

	@Override
	public Set<IReexecutionTrace> getDependentTraces() {
		if (children == null) {
			children = new HashSet<>();
		}
		return Collections.unmodifiableSet(children);
	}

	@Override
	public IReexecutionTrace getParentTrace() {
		return parent;
	}

	@Override
	public void setParentTrace(IReexecutionTrace parent) {
		if (this.parent != null) {
			this.parent.getDependentTraces().remove(this);
		}
		this.parent = parent;
		this.parent.getDependentTraces().add(this);
	}

}
