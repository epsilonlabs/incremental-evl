/**
 */
package org.eclipse.epsilon.incremental.trace.eol;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.ModelReference#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.ModelReference#getExecutionContext <em>Execution Context</em>}</li>
 *   <li>{@link org.eclipse.epsilon.incremental.trace.eol.ModelReference#getOwns <em>Owns</em>}</li>
 * </ul>
 *
 * @model annotation="https://eclipse.org/epsilon/incremental/OrientDbIndex type='NOTUNIQUE_HASH_INDEX'"
 * @generated
 */
public interface ModelReference extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @model required="true"
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.incremental.trace.eol.ModelReference#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

	/**
	 * Returns the value of the '<em><b>Execution Context</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.eol.ExecutionContext}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.ExecutionContext#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Context</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Context</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.eol.ExecutionContext#getUses
	 * @model opposite="uses"
	 * @generated
	 */
	List<ExecutionContext> getExecutionContext();

	/**
	 * Returns the value of the '<em><b>Owns</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.incremental.trace.eol.ModelElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.incremental.trace.eol.ModelElement#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owns</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owns</em>' reference list.
	 * @see org.eclipse.epsilon.incremental.trace.eol.ModelElement#getOwner
	 * @model opposite="owner"
	 *        annotation="https://eclipse.org/epsilon/incremental/Graph edge='true'"
	 * @generated
	 */
	List<ModelElement> getOwns();

} // ModelReference
