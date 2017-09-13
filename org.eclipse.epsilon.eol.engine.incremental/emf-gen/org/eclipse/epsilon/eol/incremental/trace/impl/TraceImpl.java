/**
 */
package org.eclipse.epsilon.eol.incremental.trace.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.eol.incremental.trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getExecutionContext <em>Execution Context</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getReaches <em>Reaches</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getAccesses <em>Accesses</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TraceImpl extends MinimalEObjectImpl.Container implements Trace {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final Object ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected Object id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExecutionContext() <em>Execution Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContext()
	 * @generated
	 * @ordered
	 */
	protected ExecutionContext executionContext;

	/**
	 * The cached value of the '{@link #getTraces() <em>Traces</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraces()
	 * @generated
	 * @ordered
	 */
	protected ModuleElement traces;

	/**
	 * The cached value of the '{@link #getReaches() <em>Reaches</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReaches()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElement> reaches;

	/**
	 * The cached value of the '{@link #getAccesses() <em>Accesses</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccesses()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> accesses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext getExecutionContext() {
		if (executionContext != null && ((EObject)executionContext).eIsProxy()) {
			InternalEObject oldExecutionContext = (InternalEObject)executionContext;
			executionContext = (ExecutionContext)eResolveProxy(oldExecutionContext);
			if (executionContext != oldExecutionContext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.TRACE__EXECUTION_CONTEXT, oldExecutionContext, executionContext));
			}
		}
		return executionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext basicGetExecutionContext() {
		return executionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutionContext(ExecutionContext newExecutionContext, NotificationChain msgs) {
		ExecutionContext oldExecutionContext = executionContext;
		executionContext = newExecutionContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracePackage.TRACE__EXECUTION_CONTEXT, oldExecutionContext, newExecutionContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionContext(ExecutionContext newExecutionContext) {
		if (newExecutionContext != executionContext) {
			NotificationChain msgs = null;
			if (executionContext != null)
				msgs = ((InternalEObject)executionContext).eInverseRemove(this, TracePackage.EXECUTION_CONTEXT__CONTAINS, ExecutionContext.class, msgs);
			if (newExecutionContext != null)
				msgs = ((InternalEObject)newExecutionContext).eInverseAdd(this, TracePackage.EXECUTION_CONTEXT__CONTAINS, ExecutionContext.class, msgs);
			msgs = basicSetExecutionContext(newExecutionContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.TRACE__EXECUTION_CONTEXT, newExecutionContext, newExecutionContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModuleElement getTraces() {
		if (traces != null && ((EObject)traces).eIsProxy()) {
			InternalEObject oldTraces = (InternalEObject)traces;
			traces = (ModuleElement)eResolveProxy(oldTraces);
			if (traces != oldTraces) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.TRACE__TRACES, oldTraces, traces));
			}
		}
		return traces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModuleElement basicGetTraces() {
		return traces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTraces(ModuleElement newTraces, NotificationChain msgs) {
		ModuleElement oldTraces = traces;
		traces = newTraces;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracePackage.TRACE__TRACES, oldTraces, newTraces);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTraces(ModuleElement newTraces) {
		if (newTraces != traces) {
			NotificationChain msgs = null;
			if (traces != null)
				msgs = ((InternalEObject)traces).eInverseRemove(this, TracePackage.MODULE_ELEMENT__TRACES, ModuleElement.class, msgs);
			if (newTraces != null)
				msgs = ((InternalEObject)newTraces).eInverseAdd(this, TracePackage.MODULE_ELEMENT__TRACES, ModuleElement.class, msgs);
			msgs = basicSetTraces(newTraces, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.TRACE__TRACES, newTraces, newTraces));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ModelElement> getReaches() {
		if (reaches == null) {
			reaches = new EObjectWithInverseResolvingEList.ManyInverse<ModelElement>(ModelElement.class, this, TracePackage.TRACE__REACHES, TracePackage.MODEL_ELEMENT__TRACES);
		}
		return reaches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Property> getAccesses() {
		if (accesses == null) {
			accesses = new EObjectWithInverseResolvingEList.ManyInverse<Property>(Property.class, this, TracePackage.TRACE__ACCESSES, TracePackage.PROPERTY__TRACES);
		}
		return accesses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracePackage.TRACE__EXECUTION_CONTEXT:
				if (executionContext != null)
					msgs = ((InternalEObject)executionContext).eInverseRemove(this, TracePackage.EXECUTION_CONTEXT__CONTAINS, ExecutionContext.class, msgs);
				return basicSetExecutionContext((ExecutionContext)otherEnd, msgs);
			case TracePackage.TRACE__TRACES:
				if (traces != null)
					msgs = ((InternalEObject)traces).eInverseRemove(this, TracePackage.MODULE_ELEMENT__TRACES, ModuleElement.class, msgs);
				return basicSetTraces((ModuleElement)otherEnd, msgs);
			case TracePackage.TRACE__REACHES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReaches()).basicAdd(otherEnd, msgs);
			case TracePackage.TRACE__ACCESSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAccesses()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracePackage.TRACE__EXECUTION_CONTEXT:
				return basicSetExecutionContext(null, msgs);
			case TracePackage.TRACE__TRACES:
				return basicSetTraces(null, msgs);
			case TracePackage.TRACE__REACHES:
				return ((InternalEList<?>)getReaches()).basicRemove(otherEnd, msgs);
			case TracePackage.TRACE__ACCESSES:
				return ((InternalEList<?>)getAccesses()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracePackage.TRACE__ID:
				return getId();
			case TracePackage.TRACE__EXECUTION_CONTEXT:
				if (resolve) return getExecutionContext();
				return basicGetExecutionContext();
			case TracePackage.TRACE__TRACES:
				if (resolve) return getTraces();
				return basicGetTraces();
			case TracePackage.TRACE__REACHES:
				return getReaches();
			case TracePackage.TRACE__ACCESSES:
				return getAccesses();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracePackage.TRACE__EXECUTION_CONTEXT:
				setExecutionContext((ExecutionContext)newValue);
				return;
			case TracePackage.TRACE__TRACES:
				setTraces((ModuleElement)newValue);
				return;
			case TracePackage.TRACE__REACHES:
				getReaches().clear();
				getReaches().addAll((Collection<? extends ModelElement>)newValue);
				return;
			case TracePackage.TRACE__ACCESSES:
				getAccesses().clear();
				getAccesses().addAll((Collection<? extends Property>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracePackage.TRACE__EXECUTION_CONTEXT:
				setExecutionContext((ExecutionContext)null);
				return;
			case TracePackage.TRACE__TRACES:
				setTraces((ModuleElement)null);
				return;
			case TracePackage.TRACE__REACHES:
				getReaches().clear();
				return;
			case TracePackage.TRACE__ACCESSES:
				getAccesses().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracePackage.TRACE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case TracePackage.TRACE__EXECUTION_CONTEXT:
				return executionContext != null;
			case TracePackage.TRACE__TRACES:
				return traces != null;
			case TracePackage.TRACE__REACHES:
				return reaches != null && !reaches.isEmpty();
			case TracePackage.TRACE__ACCESSES:
				return accesses != null && !accesses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //TraceImpl
