package org.eclipse.epsilon.evl.incremental.execute;

import java.util.List;

import org.eclipse.epsilon.base.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.execute.IRepository;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

// Having a ModuleTrace repository is not usefull, each module should define
// the repos that are usefull, e.g. in EVL the contexttrave repo is key
public interface IEvlExecutionTraceRepository extends IRepository<IExecutionTrace> {
	
	IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace moduleTrace);
	
	IContextTrace getContextTraceFor(String typeName, int index, IEvlModuleTrace module, IModelElementTrace modelElement);
	
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
