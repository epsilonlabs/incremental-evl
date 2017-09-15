/**
 */
package org.eclipse.epsilon.eol.incremental.trace;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getFor <em>For</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getUses <em>Uses</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext()
 * @model
 * @generated
 */
public interface ExecutionContext extends TraceElement {
	/**
	 * Returns the value of the '<em><b>For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>For</em>' reference.
	 * @see #setFor(Script)
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_For()
	 * @model annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	Script getFor();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getFor <em>For</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For</em>' reference.
	 * @see #getFor()
	 * @generated
	 */
	void setFor(Script value);

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.Trace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getCreatedIn <em>Created In</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_Traces()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getCreatedIn
	 * @model opposite="createdIn"
	 * @generated
	 */
	List<Trace> getTraces();

	/**
	 * Returns the value of the '<em><b>Uses</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.Model}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Model#getExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uses</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_Uses()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Model#getExecutionContext
	 * @model opposite="executionContext"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	List<Model> getUses();

} // ExecutionContext
