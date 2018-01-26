package org.eclipse.epsilon.evl.execute;

import java.util.List;

import org.eclipse.epsilon.eol.incremental.execute.IRepository;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;


public interface IExecutionTraceRepository extends IRepository<IExecutionTrace> {
	
	
	public interface IContextTraceRepository extends IRepository<IContextTrace> {
		
		/**
		 * Find the ContextTraces for the given type and index, that have been executed for the given
		 * model element. 
		 * 
		 * @param typeName the name of the type of the model element (and hence the Context's type-context)
		 * @param index	the index of the context (multiple context can exist for the same type)
		 * @param modelElement	the model element
		 * @return
		 */
		IContextTrace getContextTraceFor(String typeName, int index, IModelElementTrace modelElement);
		
	}

	
	/**
	 * Gets the property trace for the given property and element. This allows fine grained incremental execution for
	 * models that can notify changes at the property level.
	 * 
	 * @param objectId the model object id
	 * @param propertyName the property name
	 *
	 * @return the property
	 * @throws EolIncrementalExecutionException 
	 */
	List<IExecutionTrace> findExecutionTraces(String objectId, String propertyName);
	
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
