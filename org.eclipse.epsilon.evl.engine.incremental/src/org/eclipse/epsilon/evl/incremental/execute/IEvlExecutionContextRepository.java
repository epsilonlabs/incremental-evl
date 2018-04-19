package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.IRepository;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;

public interface IEvlExecutionContextRepository extends IRepository<IExecutionContext> {
	
	/**
	 * Returns the execution context for the given set of variables
	 * @param selfVariable
	 * @return
	 */
	IExecutionContext getExecutionContextFor(IModelElementVariable... selfVariable);

}
