package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

/**
 * The IPropertyChangeListener defines the methods used to respond to changes in a model. 
 * 
 */
//FIXME This API should not be coupled to EMF, i.e. the methods should receive a more generic
// set of arguments. For example, Object and String
//FIXME This API should probably be part of Epsilon Core, and each module should provide its own
// implementation. The reason for this is that the response to the change depends on the module
// and not the model. Of course, specilialized versions that couple a module to a model can be 
// provided so, for example, to take advantage of functionality provided by the model infrastructure.
// FIXME The TScope should probably be a generic, so that each module can define their own object-ast
// (context, rule, pattern, operation, etc.) relations. 
public interface IPropertyChangeListener  {
	
	/**
	 * This method is invoked for a change in a property value of a model element. This method
	 * returns a collection of E*L scopes that are related to the model element and the affected
	 * property. Usually, {@link #validateScopes(Collection)} will be invoked next to perform a 
	 * fine grained evaluation of the these scopes.
	 * 
	 * @param notifier The model element
	 * @param feature The name of the property that changed. 
	 * @return
	 */
	public Collection<TScope> onChange(EObject notifier, EStructuralFeature feature);
	
	/**
	 * This method is invoked when an element is deleted from the model? This method
	 * returns a collection of E*L scopes that are related to the model element and the affected
	 * property. Usually, {@link #validateScopes(Collection)} will be invoked next to perform a 
	 * fine grained evaluation of the these scopes.
	 * 
	 * @param notifier
	 * @param feature
	 * @return
	 */
	public Collection<TScope> onDelete(EObject notifier, EStructuralFeature feature);
	
	/**
	 * This method is invoked to execute the appropriate E*L module sections/scopes.
	 * @param scopes
	 */
	public void validateScopes(Collection<TScope> scopes);
	
	/**
	 * This method is invoked when an element is created in the model. Note that element
	 * creation can also be the result of the first time a model is "listened too" (i.e. all
	 * elements are new). 
	 * @param notifier
	 */
	public void onCreate(EObject notifier);
}
