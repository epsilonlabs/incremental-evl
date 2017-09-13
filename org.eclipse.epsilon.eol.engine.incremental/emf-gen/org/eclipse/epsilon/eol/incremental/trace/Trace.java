/**
 */
package org.eclipse.epsilon.eol.incremental.trace;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getExecutionContext <em>Execution Context</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getReaches <em>Reaches</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getAccesses <em>Accesses</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getTrace()
 * @model
 * @generated
 */
public interface Trace extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Execution Context</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getContains <em>Contains</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Context</em>' reference.
	 * @see #setExecutionContext(ExecutionContext)
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getTrace_ExecutionContext()
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getContains
	 * @model opposite="contains"
	 * @generated
	 */
	ExecutionContext getExecutionContext();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getExecutionContext <em>Execution Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Context</em>' reference.
	 * @see #getExecutionContext()
	 * @generated
	 */
	void setExecutionContext(ExecutionContext value);

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' reference.
	 * @see #setTraces(ModuleElement)
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getTrace_Traces()
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getTraces
	 * @model opposite="traces" required="true"
	 *        annotation="https://eclipse.org/epsilon/incremental/OrientDbGraph edge='true'"
	 * @generated
	 */
	ModuleElement getTraces();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getTraces <em>Traces</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Traces</em>' reference.
	 * @see #getTraces()
	 * @generated
	 */
	void setTraces(ModuleElement value);

	/**
	 * Returns the value of the '<em><b>Reaches</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.ModelElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reaches</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reaches</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getTrace_Reaches()
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModelElement#getTraces
	 * @model opposite="traces"
	 *        annotation="https://eclipse.org/epsilon/incremental/OrientDbGraph edge='true'"
	 * @generated
	 */
	List<ModelElement> getReaches();

	/**
	 * Returns the value of the '<em><b>Accesses</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.Property}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Property#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accesses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accesses</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getTrace_Accesses()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Property#getTraces
	 * @model opposite="traces"
	 *        annotation="https://eclipse.org/epsilon/incremental/OrientDbGraph edge='true'"
	 * @generated
	 */
	List<Property> getAccesses();

} // Trace
