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
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getScriptId <em>Script Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getModelsIds <em>Models Ids</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getFor <em>For</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getContains <em>Contains</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getInvolves <em>Involves</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext()
 * @model annotation="https://eclipse.org/epsilon/incremental/OrientDbIndex type='NOTUNIQUE_HASH_INDEX'"
 * @generated
 */
public interface ExecutionContext extends TraceElement {
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
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_ScriptId()
	 * @model required="true"
	 * @generated
	 */
	String getScriptId();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getScriptId <em>Script Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Id</em>' attribute.
	 * @see #getScriptId()
	 * @generated
	 */
	void setScriptId(String value);

	/**
	 * Returns the value of the '<em><b>Models Ids</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Models Ids</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Models Ids</em>' attribute list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_ModelsIds()
	 * @model unique="false"
	 * @generated
	 */
	List<String> getModelsIds();

	/**
	 * Returns the value of the '<em><b>For</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getExecutionContexts <em>Execution Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>For</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_For()
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getExecutionContexts
	 * @model opposite="executionContexts"
	 *        annotation="https://eclipse.org/epsilon/incremental/OrientDbGraph edge='true'"
	 * @generated
	 */
	List<ModuleElement> getFor();

	/**
	 * Returns the value of the '<em><b>Contains</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.Trace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contains</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_Contains()
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getExecutionContext
	 * @model opposite="executionContext"
	 *        annotation="https://eclipse.org/epsilon/incremental/OrientDbGraph edge='true'"
	 * @generated
	 */
	List<Trace> getContains();

	/**
	 * Returns the value of the '<em><b>Involves</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.incremental.trace.ModelElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involves</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Involves</em>' reference list.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#getExecutionContext_Involves()
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModelElement#getExecutionContext
	 * @model opposite="executionContext"
	 *        annotation="https://eclipse.org/epsilon/incremental/OrientDbGraph edge='true'"
	 * @generated
	 */
	List<ModelElement> getInvolves();

} // ExecutionContext
