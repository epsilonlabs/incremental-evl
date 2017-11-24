package org.eclipse.epsilon.eol.incremental.execute;

import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;

/**
 * A repository of model traces.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface IModelTraceRepository extends IRepository<IModelTrace> {
	
	/**
	 * Search a model by name.
	 * 
	 * @param name the name of the model
	 * @return
	 */
	IModelTrace getModelTraceByName(String name);

	/**
	 * Get the first model in the repository.
	 * 
	 * This method is useful for ExL executions in which a single model is used and type information is not prepended
	 * with the model name, i.e. model!type vs. type
	 * @return
	 */
	IModelTrace first();

}
