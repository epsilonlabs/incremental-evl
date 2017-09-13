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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.eol.incremental.trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl#getScriptId <em>Script Id</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl#getModelsIds <em>Models Ids</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl#getFor <em>For</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl#getContains <em>Contains</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionContextImpl#getInvolves <em>Involves</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionContextImpl extends MinimalEObjectImpl.Container implements ExecutionContext {
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
	 * The default value of the '{@link #getScriptId() <em>Script Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptId()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScriptId() <em>Script Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptId()
	 * @generated
	 * @ordered
	 */
	protected String scriptId = SCRIPT_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModelsIds() <em>Models Ids</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelsIds()
	 * @generated
	 * @ordered
	 */
	protected EList<String> modelsIds;

	/**
	 * The cached value of the '{@link #getFor() <em>For</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFor()
	 * @generated
	 * @ordered
	 */
	protected EList<ModuleElement> for_;

	/**
	 * The cached value of the '{@link #getContains() <em>Contains</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContains()
	 * @generated
	 * @ordered
	 */
	protected EList<Trace> contains;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.EXECUTION_CONTEXT;
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
	public String getScriptId() {
		return scriptId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScriptId(String newScriptId) {
		String oldScriptId = scriptId;
		scriptId = newScriptId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.EXECUTION_CONTEXT__SCRIPT_ID, oldScriptId, scriptId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getModelsIds() {
		if (modelsIds == null) {
			modelsIds = new EDataTypeEList<String>(String.class, this, TracePackage.EXECUTION_CONTEXT__MODELS_IDS);
		}
		return modelsIds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ModuleElement> getFor() {
		if (for_ == null) {
			for_ = new EObjectWithInverseResolvingEList.ManyInverse<ModuleElement>(ModuleElement.class, this, TracePackage.EXECUTION_CONTEXT__FOR, TracePackage.MODULE_ELEMENT__EXECUTION_CONTEXTS);
		}
		return for_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Trace> getContains() {
		if (contains == null) {
			contains = new EObjectWithInverseResolvingEList<Trace>(Trace.class, this, TracePackage.EXECUTION_CONTEXT__CONTAINS, TracePackage.TRACE__EXECUTION_CONTEXT);
		}
		return contains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ModelElement> getInvolves() {
		if (involves == null) {
			involves = new EObjectWithInverseResolvingEList.ManyInverse<ModelElement>(ModelElement.class, this, TracePackage.EXECUTION_CONTEXT__INVOLVES, TracePackage.MODEL_ELEMENT__EXECUTION_CONTEXT);
		}
		return involves;
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
			case TracePackage.EXECUTION_CONTEXT__FOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFor()).basicAdd(otherEnd, msgs);
			case TracePackage.EXECUTION_CONTEXT__CONTAINS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContains()).basicAdd(otherEnd, msgs);
			case TracePackage.EXECUTION_CONTEXT__INVOLVES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInvolves()).basicAdd(otherEnd, msgs);
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
			case TracePackage.EXECUTION_CONTEXT__FOR:
				return ((InternalEList<?>)getFor()).basicRemove(otherEnd, msgs);
			case TracePackage.EXECUTION_CONTEXT__CONTAINS:
				return ((InternalEList<?>)getContains()).basicRemove(otherEnd, msgs);
			case TracePackage.EXECUTION_CONTEXT__INVOLVES:
				return ((InternalEList<?>)getInvolves()).basicRemove(otherEnd, msgs);
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
			case TracePackage.EXECUTION_CONTEXT__ID:
				return getId();
			case TracePackage.EXECUTION_CONTEXT__SCRIPT_ID:
				return getScriptId();
			case TracePackage.EXECUTION_CONTEXT__MODELS_IDS:
				return getModelsIds();
			case TracePackage.EXECUTION_CONTEXT__FOR:
				return getFor();
			case TracePackage.EXECUTION_CONTEXT__CONTAINS:
				return getContains();
			case TracePackage.EXECUTION_CONTEXT__INVOLVES:
				return getInvolves();
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
			case TracePackage.EXECUTION_CONTEXT__SCRIPT_ID:
				setScriptId((String)newValue);
				return;
			case TracePackage.EXECUTION_CONTEXT__MODELS_IDS:
				getModelsIds().clear();
				getModelsIds().addAll((Collection<? extends String>)newValue);
				return;
			case TracePackage.EXECUTION_CONTEXT__FOR:
				getFor().clear();
				getFor().addAll((Collection<? extends ModuleElement>)newValue);
				return;
			case TracePackage.EXECUTION_CONTEXT__CONTAINS:
				getContains().clear();
				getContains().addAll((Collection<? extends Trace>)newValue);
				return;
			case TracePackage.EXECUTION_CONTEXT__INVOLVES:
				getInvolves().clear();
				getInvolves().addAll((Collection<? extends ModelElement>)newValue);
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
			case TracePackage.EXECUTION_CONTEXT__SCRIPT_ID:
				setScriptId(SCRIPT_ID_EDEFAULT);
				return;
			case TracePackage.EXECUTION_CONTEXT__MODELS_IDS:
				getModelsIds().clear();
				return;
			case TracePackage.EXECUTION_CONTEXT__FOR:
				getFor().clear();
				return;
			case TracePackage.EXECUTION_CONTEXT__CONTAINS:
				getContains().clear();
				return;
			case TracePackage.EXECUTION_CONTEXT__INVOLVES:
				getInvolves().clear();
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
			case TracePackage.EXECUTION_CONTEXT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case TracePackage.EXECUTION_CONTEXT__SCRIPT_ID:
				return SCRIPT_ID_EDEFAULT == null ? scriptId != null : !SCRIPT_ID_EDEFAULT.equals(scriptId);
			case TracePackage.EXECUTION_CONTEXT__MODELS_IDS:
				return modelsIds != null && !modelsIds.isEmpty();
			case TracePackage.EXECUTION_CONTEXT__FOR:
				return for_ != null && !for_.isEmpty();
			case TracePackage.EXECUTION_CONTEXT__CONTAINS:
				return contains != null && !contains.isEmpty();
			case TracePackage.EXECUTION_CONTEXT__INVOLVES:
				return involves != null && !involves.isEmpty();
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
		result.append(", scriptId: ");
		result.append(scriptId);
		result.append(", modelsIds: ");
		result.append(modelsIds);
		result.append(')');
		return result.toString();
	}

} //ExecutionContextImpl
