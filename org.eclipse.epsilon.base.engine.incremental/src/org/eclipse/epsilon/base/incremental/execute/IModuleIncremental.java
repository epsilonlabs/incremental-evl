package org.eclipse.epsilon.base.incremental.execute;

import java.util.Set;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

/**
 * Incremental Modules can be attached to incremental models (models that
 * implement IIncrementalModel) in order to be notified of changes in the model
 * and execute specific elements/sections of the module that are related to the
 * objects that changed and their changes.
 * 
 * Implementations need to create an instance of an
 * {@link IExecutionTrace} that will be used to manage the execution
 * traces for the module.
 * 
 * An Incremental Module should not be executed completely all the time. Instead
 * it should be executed completely once in order to create all the trace
 * information. After that, the module should only respond to the changes in the
 * models associated with the initial execution. For this, the module should
 * attach itself as a listener to all the models used during execution.
 * 
 * @author Horacio Hoyos Rodriguez
 */
public interface IModuleIncremental extends IModule {

	/**
	 * Gets the incremental models that are used in this execution
	 * 
	 * @return
	 */
	Set<IIncrementalModel> getTargets();

	/**
	 * We use a checksum as a form of ID so we can recognise the same file/code executed from different
	 * locations.
	 * @return The checksum (MD5) for the code/file being executed
	 */
	String getChksum();

	/**
	 * Enable/disable listening to model changes (in the used incremental models)
	 * 
	 * @param listen
	 * @see #getTargets()
	 */
	void listenToModelChanges(boolean listen);

	/**
	 * Notify the module that there has been a change in a model. Called to notify
	 * the module that the model has changed. Implementations would usually query
	 * the execution trace model/store to test if the given property of the objcet
	 * that changed has a trace. If so, all elements of the module that are related
	 * to the object+property should be re-executed.
	 * 
	 * @param object
	 * @param propertyName
	 * @throws EolIncrementalExecutionException
	 */
	void onChange(IIncrementalModel model, Object object, String propertyName) throws EolRuntimeException;

	/**
	 * Notify the module that a new element has been created in a model Called to
	 * notify the module that a new object has been created in the model.
	 * Implementations would usually find the type of the newly created object and
	 * re-execute any statements of the module that are related to its type.
	 *
	 * @param newElement
	 */
	void onCreate(IIncrementalModel model, Object newElement) throws EolRuntimeException;

	/**
	 * Notify the module that an element has been deleted form a model. Called to
	 * notify the module that an object has been deleted from the model.
	 * Implementations would usually query the execution trace model/store to test
	 * if the given object has a trace. If so, all elements of the module that are
	 * related to the traced properties of the object should be re-executed.
	 * 
	 * @param object
	 */
	void onDelete(IIncrementalModel model, Object object) throws EolRuntimeException;

}