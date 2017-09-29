/**
 */
package org.eclipse.epsilon.incremental.trace.evl;

import java.util.List;

import org.eclipse.epsilon.incremental.trace.eol.ModuleElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Evl Module Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement#getSatisfies <em>Satisfies</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement#getDependsOn <em>Depends On</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface EvlModuleElement extends ModuleElement {
	/**
	 * Returns the value of the '<em><b>Satisfies</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Satisfies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Satisfies</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement#getDependsOn
	 * @model opposite="dependsOn"
	 * @generated
	 */
	List<EvlModuleElement> getSatisfies();

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement#getSatisfies <em>Satisfies</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depends On</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends On</em>' reference.
	 * @see #setDependsOn(EvlModuleElement)
	 * @see org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement#getSatisfies
	 * @model opposite="satisfies"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	EvlModuleElement getDependsOn();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement#getDependsOn <em>Depends On</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depends On</em>' reference.
	 * @see #getDependsOn()
	 * @generated
	 */
	void setDependsOn(EvlModuleElement value);

} // EvlModuleElement
