/**
 */
package org.eclipse.epsilon.eol.incremental.trace;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.epsilon.eol.incremental.trace.TraceFactory
 * @model kind="package"
 * @generated
 */
public interface TracePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "trace";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.epsilon.org/incremental/ExecutionTrace.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "et";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracePackage eINSTANCE = org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.eol.incremental.trace.TraceElement <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.eol.incremental.trace.TraceElement
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getTraceElement()
	 * @generated
	 */
	int TRACE_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT__ID = 0;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl <em>Execution Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getExecutionContext()
	 * @generated
	 */
	int EXECUTION_CONTEXT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__ID = TRACE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Script Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__SCRIPT_ID = TRACE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Models Ids</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__MODELS_IDS = TRACE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__FOR = TRACE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__CONTAINS = TRACE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Involves</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__INVOLVES = TRACE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Execution Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT_FEATURE_COUNT = TRACE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Execution Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT_OPERATION_COUNT = TRACE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl <em>Module Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getModuleElement()
	 * @generated
	 */
	int MODULE_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__ID = TRACE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Module Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__MODULE_ID = TRACE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Contexts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__EXECUTION_CONTEXTS = TRACE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT__TRACES = TRACE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Module Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT_FEATURE_COUNT = TRACE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Module Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_ELEMENT_OPERATION_COUNT = TRACE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__ID = TRACE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Execution Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__EXECUTION_CONTEXT = TRACE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__TRACES = TRACE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reaches</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__REACHES = TRACE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Accesses</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__ACCESSES = TRACE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_FEATURE_COUNT = TRACE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_OPERATION_COUNT = TRACE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__ID = TRACE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Element Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__ELEMENT_ID = TRACE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Context</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__EXECUTION_CONTEXT = TRACE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__TRACES = TRACE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__OWNS = TRACE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = TRACE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_OPERATION_COUNT = TRACE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.PropertyImpl
	 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__ID = TRACE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = TRACE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__MODEL_ELEMENT = TRACE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__TRACES = TRACE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = TRACE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = TRACE_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Context</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext
	 * @generated
	 */
	EClass getExecutionContext();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getScriptId <em>Script Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script Id</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getScriptId()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EAttribute getExecutionContext_ScriptId();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getModelsIds <em>Models Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Models Ids</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getModelsIds()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EAttribute getExecutionContext_ModelsIds();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getFor <em>For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>For</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getFor()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_For();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getContains <em>Contains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contains</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getContains()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_Contains();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getInvolves <em>Involves</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Involves</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ExecutionContext#getInvolves()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_Involves();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement <em>Module Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Element</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModuleElement
	 * @generated
	 */
	EClass getModuleElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getModuleId <em>Module Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Module Id</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getModuleId()
	 * @see #getModuleElement()
	 * @generated
	 */
	EAttribute getModuleElement_ModuleId();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getExecutionContexts <em>Execution Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Execution Contexts</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getExecutionContexts()
	 * @see #getModuleElement()
	 * @generated
	 */
	EReference getModuleElement_ExecutionContexts();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Traces</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModuleElement#getTraces()
	 * @see #getModuleElement()
	 * @generated
	 */
	EReference getModuleElement_Traces();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.eol.incremental.trace.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Execution Context</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getExecutionContext()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_ExecutionContext();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Traces</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getTraces()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_Traces();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getReaches <em>Reaches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reaches</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getReaches()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_Reaches();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.Trace#getAccesses <em>Accesses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Accesses</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Trace#getAccesses()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_Accesses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getElementId <em>Element Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Id</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModelElement#getElementId()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_ElementId();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Execution Context</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModelElement#getExecutionContext()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_ExecutionContext();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Traces</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModelElement#getTraces()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Traces();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.ModelElement#getOwns <em>Owns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Owns</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.ModelElement#getOwns()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Owns();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.eol.incremental.trace.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.eol.incremental.trace.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epsilon.eol.incremental.trace.Property#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model Element</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Property#getModelElement()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_ModelElement();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.eol.incremental.trace.Property#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Traces</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.Property#getTraces()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_Traces();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.eol.incremental.trace.TraceElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TraceElement
	 * @generated
	 */
	EClass getTraceElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.eol.incremental.trace.TraceElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.epsilon.eol.incremental.trace.TraceElement#getId()
	 * @see #getTraceElement()
	 * @generated
	 */
	EAttribute getTraceElement_Id();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TraceFactory getTraceFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl <em>Execution Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getExecutionContext()
		 * @generated
		 */
		EClass EXECUTION_CONTEXT = eINSTANCE.getExecutionContext();

		/**
		 * The meta object literal for the '<em><b>Script Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_CONTEXT__SCRIPT_ID = eINSTANCE.getExecutionContext_ScriptId();

		/**
		 * The meta object literal for the '<em><b>Models Ids</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_CONTEXT__MODELS_IDS = eINSTANCE.getExecutionContext_ModelsIds();

		/**
		 * The meta object literal for the '<em><b>For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__FOR = eINSTANCE.getExecutionContext_For();

		/**
		 * The meta object literal for the '<em><b>Contains</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__CONTAINS = eINSTANCE.getExecutionContext_Contains();

		/**
		 * The meta object literal for the '<em><b>Involves</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__INVOLVES = eINSTANCE.getExecutionContext_Involves();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl <em>Module Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getModuleElement()
		 * @generated
		 */
		EClass MODULE_ELEMENT = eINSTANCE.getModuleElement();

		/**
		 * The meta object literal for the '<em><b>Module Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE_ELEMENT__MODULE_ID = eINSTANCE.getModuleElement_ModuleId();

		/**
		 * The meta object literal for the '<em><b>Execution Contexts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE_ELEMENT__EXECUTION_CONTEXTS = eINSTANCE.getModuleElement_ExecutionContexts();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE_ELEMENT__TRACES = eINSTANCE.getModuleElement_Traces();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Execution Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__EXECUTION_CONTEXT = eINSTANCE.getTrace_ExecutionContext();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__TRACES = eINSTANCE.getTrace_Traces();

		/**
		 * The meta object literal for the '<em><b>Reaches</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__REACHES = eINSTANCE.getTrace_Reaches();

		/**
		 * The meta object literal for the '<em><b>Accesses</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__ACCESSES = eINSTANCE.getTrace_Accesses();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '<em><b>Element Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__ELEMENT_ID = eINSTANCE.getModelElement_ElementId();

		/**
		 * The meta object literal for the '<em><b>Execution Context</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__EXECUTION_CONTEXT = eINSTANCE.getModelElement_ExecutionContext();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__TRACES = eINSTANCE.getModelElement_Traces();

		/**
		 * The meta object literal for the '<em><b>Owns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__OWNS = eINSTANCE.getModelElement_Owns();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.eol.incremental.trace.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.PropertyImpl
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Model Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__MODEL_ELEMENT = eINSTANCE.getProperty_ModelElement();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__TRACES = eINSTANCE.getProperty_Traces();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.eol.incremental.trace.TraceElement <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.eol.incremental.trace.TraceElement
		 * @see org.eclipse.epsilon.eol.incremental.trace.impl.TracePackageImpl#getTraceElement()
		 * @generated
		 */
		EClass TRACE_ELEMENT = eINSTANCE.getTraceElement();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACE_ELEMENT__ID = eINSTANCE.getTraceElement_Id();

	}

} //TracePackage
