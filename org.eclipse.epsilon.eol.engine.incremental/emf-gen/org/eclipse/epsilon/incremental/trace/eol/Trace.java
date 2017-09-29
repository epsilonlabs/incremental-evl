/**
 */
package org.eclipse.epsilon.incremental.trace.eol;

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
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getCreatedIn <em>Created In</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getInvolves <em>Involves</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getAccesses <em>Accesses</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface Trace extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Created In</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.ExecutionContext#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Created In</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Created In</em>' reference.
	 * @see #setCreatedIn(ExecutionContext)
	 * @see org.eclipse.epsilon.incremental.trace.eol.ExecutionContext#getTraces
	 * @model opposite="traces" required="true"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	ExecutionContext getCreatedIn();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getCreatedIn <em>Created In</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created In</em>' reference.
	 * @see #getCreatedIn()
	 * @generated
	 */
	void setCreatedIn(ExecutionContext value);

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.ModuleElement#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' reference.
	 * @see #setTraces(ModuleElement)
	 * @see org.eclipse.epsilon.incremental.trace.eol.ModuleElement#getTraces
	 * @model opposite="traces" required="true"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	ModuleElement getTraces();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getTraces <em>Traces</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Traces</em>' reference.
	 * @see #getTraces()
	 * @generated
	 */
	void setTraces(ModuleElement value);

	/**
	 * Returns the value of the '<em><b>Involves</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.eol.ModelElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involves</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Involves</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.eol.ModelElement#getTraces
	 * @model opposite="traces"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	List<ModelElement> getInvolves();

	/**
	 * Returns the value of the '<em><b>Accesses</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.eol.Property}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.Property#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accesses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accesses</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.eol.Property#getTraces
	 * @model opposite="traces"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	List<Property> getAccesses();

} // Trace
