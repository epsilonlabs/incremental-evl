package org.eclipse.epsilon.evl.incremental;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;

/**
 * A class that represents a ModuleElementTrace-ExecutionContext pair that should be re-executed
 * @author Horacio Hoyos
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exexutionContext == null) ? 0 : exexutionContext.hashCode());
		result = prime * result + ((moduleTrace == null) ? 0 : moduleTrace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReexecutionTrace<? extends IModuleElementTrace> other = (ReexecutionTrace<?>) obj;
		if (exexutionContext == null) {
			if (other.exexutionContext != null)
				return false;
		} else if (!exexutionContext.equals(other.exexutionContext))
			return false;
		if (moduleTrace == null) {
			if (other.moduleTrace != null)
				return false;
		} else if (!moduleTrace.equals(other.moduleTrace))
			return false;
		return true;
	}
	
	

}
