/**
 */
package org.eclipse.epsilon.incremental.trace.eol;

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
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getElementId <em>Element Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getContains <em>Contains</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getOwner <em>Owner</em>}</li>
 * </ul>
 *
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
	 * @model required="true"
	 * @generated
	 */
	String getElementId();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getElementId <em>Element Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Id</em>' attribute.
	 * @see #getElementId()
	 * @generated
	 */
	void setElementId(String value);

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.eol.Trace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getInvolves <em>Involves</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.eol.Trace#getInvolves
	 * @model opposite="involves"
	 * @generated
	 */
	List<Trace> getTraces();

	/**
	 * Returns the value of the '<em><b>Contains</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.eol.Property}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.Property#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contains</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.eol.Property#getModelElement
	 * @model opposite="modelElement"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	List<Property> getContains();

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.ModelReference#getOwns <em>Owns</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' reference.
	 * @see #setOwner(ModelReference)
	 * @see org.eclipse.epsilon.incremental.trace.eol.ModelReference#getOwns
	 * @model opposite="owns" required="true"
	 * @generated
	 */
	ModelReference getOwner();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getOwner <em>Owner</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(ModelReference value);

} // ModelElement
