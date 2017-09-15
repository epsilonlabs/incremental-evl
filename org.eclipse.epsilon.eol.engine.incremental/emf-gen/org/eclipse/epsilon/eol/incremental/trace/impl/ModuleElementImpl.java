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

import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Script;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.eol.incremental.trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl#getModuleId <em>Module Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl#getDefinedIn <em>Defined In</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModuleElementImpl#getTraces <em>Traces</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModuleElementImpl extends MinimalEObjectImpl.Container implements ModuleElement {
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
	 * The default value of the '{@link #getModuleId() <em>Module Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleId()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModuleId() <em>Module Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleId()
	 * @generated
	 * @ordered
	 */
	protected String moduleId = MODULE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefinedIn() <em>Defined In</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinedIn()
	 * @generated
	 * @ordered
	 */
	protected Script definedIn;

	/**
	 * The cached value of the '{@link #getTraces() <em>Traces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraces()
	 * @generated
	 * @ordered
	 */
	protected EList<Trace> traces;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModuleElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.MODULE_ELEMENT;
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
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModuleId(String newModuleId) {
		String oldModuleId = moduleId;
		moduleId = newModuleId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.MODULE_ELEMENT__MODULE_ID, oldModuleId, moduleId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Script getDefinedIn() {
		if (definedIn != null && ((EObject)definedIn).eIsProxy()) {
			InternalEObject oldDefinedIn = (InternalEObject)definedIn;
			definedIn = (Script)eResolveProxy(oldDefinedIn);
			if (definedIn != oldDefinedIn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.MODULE_ELEMENT__DEFINED_IN, oldDefinedIn, definedIn));
			}
		}
		return definedIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Script basicGetDefinedIn() {
		return definedIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefinedIn(Script newDefinedIn, NotificationChain msgs) {
		Script oldDefinedIn = definedIn;
		definedIn = newDefinedIn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracePackage.MODULE_ELEMENT__DEFINED_IN, oldDefinedIn, newDefinedIn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinedIn(Script newDefinedIn) {
		if (newDefinedIn != definedIn) {
			NotificationChain msgs = null;
			if (definedIn != null)
				msgs = ((InternalEObject)definedIn).eInverseRemove(this, TracePackage.SCRIPT__MODULE_ELEMENTS, Script.class, msgs);
			if (newDefinedIn != null)
				msgs = ((InternalEObject)newDefinedIn).eInverseAdd(this, TracePackage.SCRIPT__MODULE_ELEMENTS, Script.class, msgs);
			msgs = basicSetDefinedIn(newDefinedIn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.MODULE_ELEMENT__DEFINED_IN, newDefinedIn, newDefinedIn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Trace> getTraces() {
		if (traces == null) {
			traces = new EObjectWithInverseResolvingEList<Trace>(Trace.class, this, TracePackage.MODULE_ELEMENT__TRACES, TracePackage.TRACE__TRACES);
		}
		return traces;
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
			case TracePackage.MODULE_ELEMENT__DEFINED_IN:
				if (definedIn != null)
					msgs = ((InternalEObject)definedIn).eInverseRemove(this, TracePackage.SCRIPT__MODULE_ELEMENTS, Script.class, msgs);
				return basicSetDefinedIn((Script)otherEnd, msgs);
			case TracePackage.MODULE_ELEMENT__TRACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTraces()).basicAdd(otherEnd, msgs);
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
			case TracePackage.MODULE_ELEMENT__DEFINED_IN:
				return basicSetDefinedIn(null, msgs);
			case TracePackage.MODULE_ELEMENT__TRACES:
				return ((InternalEList<?>)getTraces()).basicRemove(otherEnd, msgs);
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
			case TracePackage.MODULE_ELEMENT__ID:
				return getId();
			case TracePackage.MODULE_ELEMENT__MODULE_ID:
				return getModuleId();
			case TracePackage.MODULE_ELEMENT__DEFINED_IN:
				if (resolve) return getDefinedIn();
				return basicGetDefinedIn();
			case TracePackage.MODULE_ELEMENT__TRACES:
				return getTraces();
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
			case TracePackage.MODULE_ELEMENT__MODULE_ID:
				setModuleId((String)newValue);
				return;
			case TracePackage.MODULE_ELEMENT__DEFINED_IN:
				setDefinedIn((Script)newValue);
				return;
			case TracePackage.MODULE_ELEMENT__TRACES:
				getTraces().clear();
				getTraces().addAll((Collection<? extends Trace>)newValue);
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
			case TracePackage.MODULE_ELEMENT__MODULE_ID:
				setModuleId(MODULE_ID_EDEFAULT);
				return;
			case TracePackage.MODULE_ELEMENT__DEFINED_IN:
				setDefinedIn((Script)null);
				return;
			case TracePackage.MODULE_ELEMENT__TRACES:
				getTraces().clear();
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
			case TracePackage.MODULE_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case TracePackage.MODULE_ELEMENT__MODULE_ID:
				return MODULE_ID_EDEFAULT == null ? moduleId != null : !MODULE_ID_EDEFAULT.equals(moduleId);
			case TracePackage.MODULE_ELEMENT__DEFINED_IN:
				return definedIn != null;
			case TracePackage.MODULE_ELEMENT__TRACES:
				return traces != null && !traces.isEmpty();
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
		result.append(", moduleId: ");
		result.append(moduleId);
		result.append(')');
		return result.toString();
	}

} //ModuleElementImpl
