package org.eclipse.epsilon.eol.incremental.models;

import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;

/**
 * A tracing property getter is responsible for notifying the registered ModuleElement of property access of elements
 * in the model.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface ITracingPropertyGetter extends IPropertyGetter {
	
	/**
	 * Returns list of the module elements associated with this property getter. The associated elements will
	 * receive notifications of property accesses in the model.
	 * 
	 * @return
	 */
	Set<ModuleElement> getTracedModuleElements();
	
	/**
	 * Notify a property access.
	 * @param objectId
	 * @param object
	 * @param propertyName
	 */
	void notify(Object object, String propertyName);
	
	/**
	 * Sets whether this model will deliver notifications to all the managers.
	 * @param tracedConstraint 
	 * @param traceManager 
	 */
	void setDeliver(boolean deliver);
	
	/**
	 * Returns whether this model will deliver notifications to the all managers.
	 *
	 * @return true, if notifications are being delivered.
	 */
	boolean isDelivering();
	
	
	/**
	 * Sets whether this model will deliver notifications to the specific manager. This enables fine grained control
	 * over notifications in order to allow local notifications over execution of single EOL statements.
	 */
	void setDeliver(boolean deliver, ModuleElement element);
	
	/**
	 * Returns whether this model will deliver notifications to the specific manager.
	 *
	 * @return true, if notifications are being delivered.
	 */
	boolean isDelivering(ModuleElement element);

}
