package org.eclipse.epsilon.base.incremental.execute;

import org.eclipse.epsilon.base.incremental.trace.IModuleTrace;

/**
 * A repository of model traces.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface IModuleRepository<T extends IModuleTrace> extends IRepository<T> {
	
	/**
	 * Search a model by name.
	 * 
	 * @param name the name of the model
	 * @return
	 */
	T getModuleTraceBySource(String source);

	/**
	 * Get the first module in the repository.
	 * 
	 * This method is useful for ExL executions in which a single model is used and type information is not prepended
	 * with the model name, i.e. model!type vs. type
	 * @return
	 */
	T first();

}
