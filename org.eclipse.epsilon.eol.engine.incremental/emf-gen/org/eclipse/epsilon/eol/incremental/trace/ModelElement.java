/**
 */
package org.eclipse.epsilon.eol.incremental.trace;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getElementId <em>Element Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getExecutionContext <em>Execution Context</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getOwns <em>Owns</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModelElement()
 * @model annotation="https://eclipse.org/epsilon/incremental/OrientDbIndex type='NOTUNIQUE_HASH_INDEX'"
 * @generated
 */
public interface ModelElement extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Element Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Id</em>' attribute.
	 * @see #setElementId(String)
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModelElement_ElementId()
	 * @model required="true"
	 * @generated
	 */
	String getElementId();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getElementId <em>Element Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Id</em>' attribute.
	 * @see #getElementId()
	 * @generated
	 */
	void setElementId(String value);

	/**
	 * Returns the value of the '<em><b>Execution Context</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getInvolves <em>Involves</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Context</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Context</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModelElement_ExecutionContext()
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getInvolves
	 * @model opposite="involves"
	 * @generated
	 */
	List<ExecutionContext> getExecutionContext();

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.Trace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getReaches <em>Reaches</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModelElement_Traces()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getReaches
	 * @model opposite="reaches"
	 * @generated
	 */
	List<Trace> getTraces();

	/**
	 * Returns the value of the '<em><b>Owns</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.Property}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Property#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owns</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owns</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getModelElement_Owns()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Property#getModelElement
	 * @model opposite="modelElement"
	 *        annotation="https://eclipse.org/epsilon/incremental/OrientDbGraph edge='true'"
	 * @generated
	 */
	List<Property> getOwns();

} // ModelElement
