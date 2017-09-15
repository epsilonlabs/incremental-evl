/**
 */
package org.eclipse.epsilon.eol.incremental.trace;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getModuleId <em>Module Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getDefinedIn <em>Defined In</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getTraces <em>Traces</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModuleElement()
 * @model annotation="https://eclipse.org/epsilon/incremental/OrientDbIndex type='NOTUNIQUE_HASH_INDEX'"
 * @generated
 */
public interface ModuleElement extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Module Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Id</em>' attribute.
	 * @see #setModuleId(String)
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModuleElement_ModuleId()
	 * @model required="true"
	 * @generated
	 */
	String getModuleId();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getModuleId <em>Module Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Id</em>' attribute.
	 * @see #getModuleId()
	 * @generated
	 */
	void setModuleId(String value);

	/**
	 * Returns the value of the '<em><b>Defined In</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Script#getModuleElements <em>Module Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined In</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined In</em>' reference.
	 * @see #setDefinedIn(Script)
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModuleElement_DefinedIn()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Script#getModuleElements
	 * @model opposite="moduleElements" required="true"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	Script getDefinedIn();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getDefinedIn <em>Defined In</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined In</em>' reference.
	 * @see #getDefinedIn()
	 * @generated
	 */
	void setDefinedIn(Script value);

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.Trace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModuleElement_Traces()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getTraces
	 * @model opposite="traces"
	 * @generated
	 */
	List<Trace> getTraces();

} // ModuleElement
