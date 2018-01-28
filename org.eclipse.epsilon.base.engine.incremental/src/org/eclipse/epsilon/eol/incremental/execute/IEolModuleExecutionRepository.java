package org.eclipse.epsilon.eol.incremental.execute;


/**
 * A repository for ModuleExecutions
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <T> The specific type of module being traced.
 */
public interface IEolModuleExecutionRepository<T> extends IRepository<T> {
	
	/** Get the Evl Module Exeution for the given file path (script) */
	T getEvlModuleExecutionForSource(String string);

}
