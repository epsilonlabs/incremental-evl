/**
 */
package org.eclipse.epsilon.eol.incremental.trace.impl;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.eol.incremental.trace.TraceElement;
import org.eclipse.epsilon.eol.incremental.trace.TraceFactory;
import org.eclipse.epsilon.eol.incremental.trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TracePackageImpl extends EPackageImpl implements TracePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceElementEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.epsilon.eol.incremental.trace.TracePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TracePackageImpl() {
		super(eNS_URI, TraceFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TracePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TracePackage init() {
		if (isInited) return (TracePackage)EPackage.Registry.INSTANCE.getEPackage(TracePackage.eNS_URI);

		// Obtain or create and register package
		TracePackageImpl theTracePackage = (TracePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TracePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TracePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTracePackage.createPackageContents();

		// Initialize created meta-data
		theTracePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTracePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TracePackage.eNS_URI, theTracePackage);
		return theTracePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionContext() {
		return executionContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionContext_ScriptId() {
		return (EAttribute)executionContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionContext_ModelsIds() {
		return (EAttribute)executionContextEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContext_For() {
		return (EReference)executionContextEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContext_Contains() {
		return (EReference)executionContextEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContext_Involves() {
		return (EReference)executionContextEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModuleElement() {
		return moduleElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModuleElement_ModuleId() {
		return (EAttribute)moduleElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModuleElement_ExecutionContexts() {
		return (EReference)moduleElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModuleElement_Traces() {
		return (EReference)moduleElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrace() {
		return traceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_ExecutionContext() {
		return (EReference)traceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_Traces() {
		return (EReference)traceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_Reaches() {
		return (EReference)traceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_Accesses() {
		return (EReference)traceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelElement() {
		return modelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElement_ElementId() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_ExecutionContext() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Traces() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Owns() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Name() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_ModelElement() {
		return (EReference)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_Traces() {
		return (EReference)propertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceElement() {
		return traceElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTraceElement_Id() {
		return (EAttribute)traceElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceFactory getTraceFactory() {
		return (TraceFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		executionContextEClass = createEClass(EXECUTION_CONTEXT);
		createEAttribute(executionContextEClass, EXECUTION_CONTEXT__SCRIPT_ID);
		createEAttribute(executionContextEClass, EXECUTION_CONTEXT__MODELS_IDS);
		createEReference(executionContextEClass, EXECUTION_CONTEXT__FOR);
		createEReference(executionContextEClass, EXECUTION_CONTEXT__CONTAINS);
		createEReference(executionContextEClass, EXECUTION_CONTEXT__INVOLVES);

		moduleElementEClass = createEClass(MODULE_ELEMENT);
		createEAttribute(moduleElementEClass, MODULE_ELEMENT__MODULE_ID);
		createEReference(moduleElementEClass, MODULE_ELEMENT__EXECUTION_CONTEXTS);
		createEReference(moduleElementEClass, MODULE_ELEMENT__TRACES);

		traceEClass = createEClass(TRACE);
		createEReference(traceEClass, TRACE__EXECUTION_CONTEXT);
		createEReference(traceEClass, TRACE__TRACES);
		createEReference(traceEClass, TRACE__REACHES);
		createEReference(traceEClass, TRACE__ACCESSES);

		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__ELEMENT_ID);
		createEReference(modelElementEClass, MODEL_ELEMENT__EXECUTION_CONTEXT);
		createEReference(modelElementEClass, MODEL_ELEMENT__TRACES);
		createEReference(modelElementEClass, MODEL_ELEMENT__OWNS);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__NAME);
		createEReference(propertyEClass, PROPERTY__MODEL_ELEMENT);
		createEReference(propertyEClass, PROPERTY__TRACES);

		traceElementEClass = createEClass(TRACE_ELEMENT);
		createEAttribute(traceElementEClass, TRACE_ELEMENT__ID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		executionContextEClass.getESuperTypes().add(this.getTraceElement());
		moduleElementEClass.getESuperTypes().add(this.getTraceElement());
		traceEClass.getESuperTypes().add(this.getTraceElement());
		modelElementEClass.getESuperTypes().add(this.getTraceElement());
		propertyEClass.getESuperTypes().add(this.getTraceElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(executionContextEClass, ExecutionContext.class, "ExecutionContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionContext_ScriptId(), ecorePackage.getEString(), "scriptId", null, 1, 1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionContext_ModelsIds(), ecorePackage.getEString(), "modelsIds", null, 0, -1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionContext_For(), this.getModuleElement(), this.getModuleElement_ExecutionContexts(), "for", null, 0, -1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionContext_Contains(), this.getTrace(), this.getTrace_ExecutionContext(), "contains", null, 0, -1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionContext_Involves(), this.getModelElement(), this.getModelElement_ExecutionContext(), "involves", null, 0, -1, ExecutionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleElementEClass, ModuleElement.class, "ModuleElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModuleElement_ModuleId(), ecorePackage.getEString(), "moduleId", null, 1, 1, ModuleElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModuleElement_ExecutionContexts(), this.getExecutionContext(), this.getExecutionContext_For(), "executionContexts", null, 0, -1, ModuleElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModuleElement_Traces(), this.getTrace(), this.getTrace_Traces(), "traces", null, 0, -1, ModuleElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traceEClass, Trace.class, "Trace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrace_ExecutionContext(), this.getExecutionContext(), this.getExecutionContext_Contains(), "executionContext", null, 0, 1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrace_Traces(), this.getModuleElement(), this.getModuleElement_Traces(), "traces", null, 1, 1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrace_Reaches(), this.getModelElement(), this.getModelElement_Traces(), "reaches", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrace_Accesses(), this.getProperty(), this.getProperty_Traces(), "accesses", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelElementEClass, ModelElement.class, "ModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElement_ElementId(), ecorePackage.getEString(), "elementId", null, 1, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_ExecutionContext(), this.getExecutionContext(), this.getExecutionContext_Involves(), "executionContext", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Traces(), this.getTrace(), this.getTrace_Reaches(), "traces", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Owns(), this.getProperty(), this.getProperty_ModelElement(), "owns", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Name(), ecorePackage.getEString(), "name", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProperty_ModelElement(), this.getModelElement(), this.getModelElement_Owns(), "modelElement", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProperty_Traces(), this.getTrace(), this.getTrace_Accesses(), "traces", null, 0, -1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traceElementEClass, TraceElement.class, "TraceElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTraceElement_Id(), ecorePackage.getEJavaObject(), "id", null, 0, 1, TraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// https://eclipse.org/epsilon/incremental/OrientDbIndex
		createOrientDbIndexAnnotations();
		// https://eclipse.org/epsilon/incremental/OrientDbGraph
		createOrientDbGraphAnnotations();
	}

	/**
	 * Initializes the annotations for <b>https://eclipse.org/epsilon/incremental/OrientDbIndex</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOrientDbIndexAnnotations() {
		String source = "https://eclipse.org/epsilon/incremental/OrientDbIndex";	
		addAnnotation
		  (executionContextEClass, 
		   source, 
		   new String[] {
			 "type", "NOTUNIQUE_HASH_INDEX"
		   },
		   new URI[] {
			 URI.createURI(eNS_URI).appendFragment("//ExecutionContext/scriptId")
		   });	
		addAnnotation
		  (moduleElementEClass, 
		   source, 
		   new String[] {
			 "type", "NOTUNIQUE_HASH_INDEX"
		   },
		   new URI[] {
			 URI.createURI(eNS_URI).appendFragment("//ModuleElement/moduleId")
		   });	
		addAnnotation
		  (modelElementEClass, 
		   source, 
		   new String[] {
			 "type", "NOTUNIQUE_HASH_INDEX"
		   },
		   new URI[] {
			 URI.createURI(eNS_URI).appendFragment("//ModelElement/elementId")
		   });	
		addAnnotation
		  (propertyEClass, 
		   source, 
		   new String[] {
			 "type", "NOTUNIQUE_HASH_INDEX"
		   },
		   new URI[] {
			 URI.createURI(eNS_URI).appendFragment("//Property/name")
		   });
	}

	/**
	 * Initializes the annotations for <b>https://eclipse.org/epsilon/incremental/OrientDbGraph</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOrientDbGraphAnnotations() {
		String source = "https://eclipse.org/epsilon/incremental/OrientDbGraph";	
		addAnnotation
		  (getExecutionContext_For(), 
		   source, 
		   new String[] {
			 "edge", "true"
		   });	
		addAnnotation
		  (getExecutionContext_Contains(), 
		   source, 
		   new String[] {
			 "edge", "true"
		   });	
		addAnnotation
		  (getExecutionContext_Involves(), 
		   source, 
		   new String[] {
			 "edge", "true"
		   });	
		addAnnotation
		  (getTrace_Traces(), 
		   source, 
		   new String[] {
			 "edge", "true"
		   });	
		addAnnotation
		  (getTrace_Reaches(), 
		   source, 
		   new String[] {
			 "edge", "true"
		   });	
		addAnnotation
		  (getTrace_Accesses(), 
		   source, 
		   new String[] {
			 "edge", "true"
		   });	
		addAnnotation
		  (getModelElement_Owns(), 
		   source, 
		   new String[] {
			 "edge", "true"
		   });
	}

} //TracePackageImpl
