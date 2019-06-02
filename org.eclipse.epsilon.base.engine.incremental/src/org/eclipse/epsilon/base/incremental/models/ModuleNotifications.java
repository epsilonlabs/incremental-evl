package org.eclipse.epsilon.base.incremental.models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

/**
 * A Class that handles module registration and notifications. Classes implementing {@link IIncrementalModel}
 * can delegate to an instance of this class the module api.
 * @author Horacio Hoyos Rodriguez
 *
 */
public class ModuleNotifications {

	private final Set<IModuleIncremental> modules;
	private final IIncrementalModel owner;
	
	public ModuleNotifications(IIncrementalModel ownr) {
		this(ownr, Collections.emptySet());
	}
	
	public ModuleNotifications(IIncrementalModel ownr, Collection<IModuleIncremental> mdls) {
		modules = new HashSet<>(mdls);
		owner = ownr;
	}

	public void notifyChange(Object element, String propertyName) {
		for (IModuleIncremental m : modules) {
			try {
				m.onChange(owner, element, propertyName);
			} catch (EolRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void notifyDeletion(Object element) {
		for (IModuleIncremental m : modules) {
			try {
				m.onDelete(owner, element);
			} catch (EolRuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void notifyCreation(Object element) {
		for (IModuleIncremental m : modules) {
			try {
				m.onCreate(owner, element);
			} catch (EolRuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Register the module to receive notifications of changes in the model.
	 *
	 * @return true if this collection changed as a result of the call
	 */
	public boolean registerModule(IModuleIncremental module) {
		return modules.add(module);
	}
	
	/**
	 * Determine if the module is registered with this model to receive notifications
	 * @param module
	 */
	public boolean isRegistered(IModuleIncremental module) {
		return modules.contains(module);
	}
	
	/**
	 * Unregister the module. The module will stop receiving notifications from changes in this
	 * model
	 * @param element
	 * @param propertyName
	 */
	public boolean unregisterModule(IModuleIncremental module) {
		return modules.remove(module);
	
	}

}
