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
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getCreatedIn <em>Created In</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.TraceImpl#getInvolves <em>Involves</em>}</li>
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
	 * The cached value of the '{@link #getCreatedIn() <em>Created In</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreatedIn()
	 * @generated
	 * @ordered
	 */
	protected ExecutionContext createdIn;

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
	 * The cached value of the '{@link #getInvolves() <em>Involves</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvolves()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElement> involves;

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
	public ExecutionContext getCreatedIn() {
		if (createdIn != null && ((EObject)createdIn).eIsProxy()) {
			InternalEObject oldCreatedIn = (InternalEObject)createdIn;
			createdIn = (ExecutionContext)eResolveProxy(oldCreatedIn);
			if (createdIn != oldCreatedIn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.TRACE__CREATED_IN, oldCreatedIn, createdIn));
			}
		}
		return createdIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext basicGetCreatedIn() {
		return createdIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCreatedIn(ExecutionContext newCreatedIn, NotificationChain msgs) {
		ExecutionContext oldCreatedIn = createdIn;
		createdIn = newCreatedIn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracePackage.TRACE__CREATED_IN, oldCreatedIn, newCreatedIn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreatedIn(ExecutionContext newCreatedIn) {
		if (newCreatedIn != createdIn) {
			NotificationChain msgs = null;
			if (createdIn != null)
				msgs = ((InternalEObject)createdIn).eInverseRemove(this, TracePackage.EXECUTION_CONTEXT__TRACES, ExecutionContext.class, msgs);
			if (newCreatedIn != null)
				msgs = ((InternalEObject)newCreatedIn).eInverseAdd(this, TracePackage.EXECUTION_CONTEXT__TRACES, ExecutionContext.class, msgs);
			msgs = basicSetCreatedIn(newCreatedIn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.TRACE__CREATED_IN, newCreatedIn, newCreatedIn));
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
	public List<ModelElement> getInvolves() {
		if (involves == null) {
			involves = new EObjectWithInverseResolvingEList.ManyInverse<ModelElement>(ModelElement.class, this, TracePackage.TRACE__INVOLVES, TracePackage.MODEL_ELEMENT__TRACES);
		}
		return involves;
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
			case TracePackage.TRACE__CREATED_IN:
				if (createdIn != null)
					msgs = ((InternalEObject)createdIn).eInverseRemove(this, TracePackage.EXECUTION_CONTEXT__TRACES, ExecutionContext.class, msgs);
				return basicSetCreatedIn((ExecutionContext)otherEnd, msgs);
			case TracePackage.TRACE__TRACES:
				if (traces != null)
					msgs = ((InternalEObject)traces).eInverseRemove(this, TracePackage.MODULE_ELEMENT__TRACES, ModuleElement.class, msgs);
				return basicSetTraces((ModuleElement)otherEnd, msgs);
			case TracePackage.TRACE__INVOLVES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInvolves()).basicAdd(otherEnd, msgs);
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
			case TracePackage.TRACE__CREATED_IN:
				return basicSetCreatedIn(null, msgs);
			case TracePackage.TRACE__TRACES:
				return basicSetTraces(null, msgs);
			case TracePackage.TRACE__INVOLVES:
				return ((InternalEList<?>)getInvolves()).basicRemove(otherEnd, msgs);
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
			case TracePackage.TRACE__CREATED_IN:
				if (resolve) return getCreatedIn();
				return basicGetCreatedIn();
			case TracePackage.TRACE__TRACES:
				if (resolve) return getTraces();
				return basicGetTraces();
			case TracePackage.TRACE__INVOLVES:
				return getInvolves();
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
			case TracePackage.TRACE__CREATED_IN:
				setCreatedIn((ExecutionContext)newValue);
				return;
			case TracePackage.TRACE__TRACES:
				setTraces((ModuleElement)newValue);
				return;
			case TracePackage.TRACE__INVOLVES:
				getInvolves().clear();
				getInvolves().addAll((Collection<? extends ModelElement>)newValue);
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
			case TracePackage.TRACE__CREATED_IN:
				setCreatedIn((ExecutionContext)null);
				return;
			case TracePackage.TRACE__TRACES:
				setTraces((ModuleElement)null);
				return;
			case TracePackage.TRACE__INVOLVES:
				getInvolves().clear();
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
			case TracePackage.TRACE__CREATED_IN:
				return createdIn != null;
			case TracePackage.TRACE__TRACES:
				return traces != null;
			case TracePackage.TRACE__INVOLVES:
				return involves != null && !involves.isEmpty();
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
