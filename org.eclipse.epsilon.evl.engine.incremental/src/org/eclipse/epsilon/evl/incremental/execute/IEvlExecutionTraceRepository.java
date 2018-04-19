package org.eclipse.epsilon.evl.incremental.execute;

import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.execute.IRepository;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

public interface IEvlExecutionTraceRepository extends IRepository<IModuleElementTrace> {
	
	IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace moduleTrace);
	
	IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace module, IExecutionContext exContext);
	
	/**
	 * Find all execution traces for the given property and element. The returned execution traces will 
	 * correspond to the execution of Contexts, Invariants, Guards, Checks and Messages that have at least
	 * one property access for the given element and property.
	 * 
	 * @param objectId the model object's id
	 * @param propertyName the property name
	 * @throws EolIncrementalExecutionException 
	 */
	List<IModuleElementTrace> findPropertyAccessExecutionTraces(String objectId, String propertyName);
	
	/**
	 * Find all execution traces for the given type. The returned execution traces will correspond to the
	 * execution of Contexts, Invariants, Guards, Checks and Messages that have at least one all instances
	 * access for the given type.
	 * 
	 * @param typeName the name of the type 
	 * @return
	 */
	List<IModuleElementTrace> findAllInstancesExecutionTraces(String typeName);
	
	/**
	 * Find all execution traces that correspond to Invariant executions in which the given invariant trace
	 * was accessed via a satisfies relation.
	 * 
	 * @param invariantTrace
	 * @return
	 */
	List<IModuleElementTrace> findSatisfiesExecutionTraces(IInvariantTrace invariantTrace);
	
	/**
	 * Returns an unmodifiable view of the execution traces
	 * @return
	 */
	Set<IModuleElementTrace> getAllExecutionTraces();
	
	
	/**
	 * Gets the execution traces related to this element. This allows coarse grained incremental execution for
	 * models that can notify changes at the element level.
	 *
	 * @param objectId the model object id
	 * @return the element trace
	 */
	//List<ExecutionTrace> findExecutionTraces(String objectId) throws EolIncrementalExecutionException;


	/**
	 * Remove the trace information related to a specific object. This should be invoked when an element is deleted
	 * from the model.
	 * 
	 * @param objectId the id of the object for which the trace information should be deleted.
	 * @throws EolIncrementalExecutionException
	 */
	//void removeTraceInformation(String objectId) throws EolIncrementalExecutionException;

}
