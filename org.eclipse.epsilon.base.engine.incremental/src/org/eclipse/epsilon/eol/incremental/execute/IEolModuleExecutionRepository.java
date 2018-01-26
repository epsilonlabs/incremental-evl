package org.eclipse.epsilon.eol.incremental.execute;

import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;

/**
 * A repository for ModuleExecutions
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <T> The specific type of module being traced.
 */
@Deprecated
public interface IEolModuleExecutionRepository<T> extends IRepository<T> {
	
	/** Get the Evl Module Exeution for the given file path (script) */
	T getEvlModuleExecutionForSource(String string);

	/** Get the property access trace for the given execution trace */
	IPropertyAccess getPropertyAccessFor(IExecutionTrace executionTrace, IPropertyTrace property);

	/** Get the all instances access trace for the given execution trace */
	IAllInstancesAccess getAllInstancesAccessFor(IExecutionTrace executionTrace, IModelTypeTrace modelType);

}
