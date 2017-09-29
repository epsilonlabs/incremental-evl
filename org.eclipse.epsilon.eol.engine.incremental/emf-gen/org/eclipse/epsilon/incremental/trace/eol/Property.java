/**
 */
package org.eclipse.epsilon.incremental.trace.eol;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Property#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Property#getModelElement <em>Model Element</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Property#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.Property#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @model annotation="https://eclipse.org/epsilon/incremental/OrientDbIndex type='NOTUNIQUE_HASH_INDEX'"
 * @generated
 */
public interface Property extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.Property#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getContains <em>Contains</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Element</em>' reference.
	 * @see #setModelElement(ModelElement)
	 * @see org.eclipse.epsilon.incremental.trace.eol.ModelElement#getContains
	 * @model opposite="contains" required="true"
	 * @generated
	 */
	ModelElement getModelElement();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.Property#getModelElement <em>Model Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Element</em>' reference.
	 * @see #getModelElement()
	 * @generated
	 */
	void setModelElement(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Traces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.eol.Trace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.Trace#getAccesses <em>Accesses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.eol.Trace#getAccesses
	 * @model opposite="accesses"
	 * @generated
	 */
	List<Trace> getTraces();

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Object)
	 * @model
	 * @generated
	 */
	Object getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.Property#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Object value);

} // Property
