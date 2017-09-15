/**
 */
package org.eclipse.epsilon.eol.incremental.trace;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.Script#getScriptId <em>Script Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.Script#getModuleElements <em>Module Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getScript()
 * @model annotation="https://eclipse.org/epsilon/incremental/OrientDbIndex type='NOTUNIQUE_HASH_INDEX'"
 * @generated
 */
public interface Script extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Script Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Id</em>' attribute.
	 * @see #setScriptId(String)
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getScript_ScriptId()
	 * @model required="true"
	 * @generated
	 */
	String getScriptId();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.Script#getScriptId <em>Script Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Id</em>' attribute.
	 * @see #getScriptId()
	 * @generated
	 */
	void setScriptId(String value);

	/**
	 * Returns the value of the '<em><b>Module Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getDefinedIn <em>Defined In</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Elements</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getScript_ModuleElements()
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getDefinedIn
	 * @model opposite="definedIn"
	 * @generated
	 */
	List<ModuleElement> getModuleElements();

} // Script
