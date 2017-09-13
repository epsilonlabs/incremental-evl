/**
 */
package org.eclipse.epsilon.eol.incremental.trace.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.eol.incremental.trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl#getElementId <em>Element Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl#getExecutionContext <em>Execution Context</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl#getTraces <em>Traces</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ModelElementImpl#getOwns <em>Owns</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelElementImpl extends MinimalEObjectImpl.Container implements ModelElement {
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
	 * The default value of the '{@link #getElementId() <em>Element Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementId()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementId() <em>Element Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementId()
	 * @generated
	 * @ordered
	 */
	protected String elementId = ELEMENT_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExecutionContext() <em>Execution Context</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContext()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionContext> executionContext;

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
	 * The cached value of the '{@link #getOwns() <em>Owns</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwns()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> owns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.MODEL_ELEMENT;
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
	public String getElementId() {
		return elementId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementId(String newElementId) {
		String oldElementId = elementId;
		elementId = newElementId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.MODEL_ELEMENT__ELEMENT_ID, oldElementId, elementId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ExecutionContext> getExecutionContext() {
		if (executionContext == null) {
			executionContext = new EObjectWithInverseResolvingEList.ManyInverse<ExecutionContext>(ExecutionContext.class, this, TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT, TracePackage.EXECUTION_CONTEXT__INVOLVES);
		}
		return executionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Trace> getTraces() {
		if (traces == null) {
			traces = new EObjectWithInverseResolvingEList.ManyInverse<Trace>(Trace.class, this, TracePackage.MODEL_ELEMENT__TRACES, TracePackage.TRACE__REACHES);
		}
		return traces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Property> getOwns() {
		if (owns == null) {
			owns = new EObjectWithInverseResolvingEList<Property>(Property.class, this, TracePackage.MODEL_ELEMENT__OWNS, TracePackage.PROPERTY__MODEL_ELEMENT);
		}
		return owns;
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
			case TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutionContext()).basicAdd(otherEnd, msgs);
			case TracePackage.MODEL_ELEMENT__TRACES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTraces()).basicAdd(otherEnd, msgs);
			case TracePackage.MODEL_ELEMENT__OWNS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwns()).basicAdd(otherEnd, msgs);
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
			case TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT:
				return ((InternalEList<?>)getExecutionContext()).basicRemove(otherEnd, msgs);
			case TracePackage.MODEL_ELEMENT__TRACES:
				return ((InternalEList<?>)getTraces()).basicRemove(otherEnd, msgs);
			case TracePackage.MODEL_ELEMENT__OWNS:
				return ((InternalEList<?>)getOwns()).basicRemove(otherEnd, msgs);
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
			case TracePackage.MODEL_ELEMENT__ID:
				return getId();
			case TracePackage.MODEL_ELEMENT__ELEMENT_ID:
				return getElementId();
			case TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT:
				return getExecutionContext();
			case TracePackage.MODEL_ELEMENT__TRACES:
				return getTraces();
			case TracePackage.MODEL_ELEMENT__OWNS:
				return getOwns();
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
			case TracePackage.MODEL_ELEMENT__ELEMENT_ID:
				setElementId((String)newValue);
				return;
			case TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT:
				getExecutionContext().clear();
				getExecutionContext().addAll((Collection<? extends ExecutionContext>)newValue);
				return;
			case TracePackage.MODEL_ELEMENT__TRACES:
				getTraces().clear();
				getTraces().addAll((Collection<? extends Trace>)newValue);
				return;
			case TracePackage.MODEL_ELEMENT__OWNS:
				getOwns().clear();
				getOwns().addAll((Collection<? extends Property>)newValue);
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
			case TracePackage.MODEL_ELEMENT__ELEMENT_ID:
				setElementId(ELEMENT_ID_EDEFAULT);
				return;
			case TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT:
				getExecutionContext().clear();
				return;
			case TracePackage.MODEL_ELEMENT__TRACES:
				getTraces().clear();
				return;
			case TracePackage.MODEL_ELEMENT__OWNS:
				getOwns().clear();
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
			case TracePackage.MODEL_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case TracePackage.MODEL_ELEMENT__ELEMENT_ID:
				return ELEMENT_ID_EDEFAULT == null ? elementId != null : !ELEMENT_ID_EDEFAULT.equals(elementId);
			case TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT:
				return executionContext != null && !executionContext.isEmpty();
			case TracePackage.MODEL_ELEMENT__TRACES:
				return traces != null && !traces.isEmpty();
			case TracePackage.MODEL_ELEMENT__OWNS:
				return owns != null && !owns.isEmpty();
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
		result.append(", elementId: ");
		result.append(elementId);
		result.append(')');
		return result.toString();
	}

} //ModelElementImpl
