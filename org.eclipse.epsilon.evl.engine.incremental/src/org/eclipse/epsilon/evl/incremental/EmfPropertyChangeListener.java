package org.eclipse.epsilon.evl.incremental;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

/**
 *  the live EVL validation is to be performed.
 * The SET, MOVE, ADD and ADDMANY events trigger the invocation of the {@link #onChange(EObject, EStructuralFeature)}
 * method. The REMOVING_ADAPTER, REMOVE, REMOVE_MANY and UNSET events trigger the {@link #onChange(EObject, EStructuralFeature)}
 * method. The {@link #onCreate(EObject)}
 * @author Jonathan Co
 * @author Horacio Hoyos
 *
 */
public class EmfPropertyChangeListener extends EContentAdapter {
	
	private boolean checkNew = false;
	
	private IModel model;
	private IncrementalEvlModule module;
	
	private Map<EObject, String> idMap = new HashMap<EObject, String>();

	public EmfPropertyChangeListener(EmfModel model, IncrementalEvlModule context) {
		this.model = model;
		this.module = context;
	}

	public void onCreate(EObject notifier) {
		 module.onCreate(notifier);	
	}
	
	public void onChange(EObject notifier, EStructuralFeature feature) {
		if (notifier == null || feature == null) {
			return;
		}
		String elementId = model.getElementId(notifier);
		String propertyName = feature.getName();
		module.onChange(elementId, notifier, propertyName);
	}

	public void onDelete(EObject notifier, EStructuralFeature feature) {
		if (notifier == null || feature == null) {
			return;
		}
		String elementId = model.getElementId(notifier);
		String propertyName = feature.getName();
		module.onDelete(elementId, notifier, propertyName);
	}

	@Override
	protected void setTarget(EObject target) {		
		//this.idMap.put(target, model.getElementId(target));
		super.setTarget(target);
		if (checkNew && !this.idMap.containsKey(target)) {
			onCreate(target);
		}
	}
	
	@Override
	protected void setTarget(Resource target) {
		super.setTarget(target);
		this.checkNew = true;
	}
	
	@Override
	public void notifyChanged(Notification notice) {
		super.notifyChanged(notice);
		
		if (!(notice.getNotifier() instanceof EObject)) {
			return;
		}
		
		final EObject notifier = this.getEObject(notice);		
		final EStructuralFeature feature = this.getFeature(notice);
		
		switch (notice.getEventType()) {
		case Notification.SET:
		case Notification.ADD:
		case Notification.MOVE:
		case Notification.ADD_MANY:
			onChange(notifier, feature);
			break;
		case Notification.REMOVING_ADAPTER:
		case Notification.REMOVE:
		case Notification.REMOVE_MANY:
		case Notification.UNSET:
			onDelete(notifier, feature);
		}
	}

	public EObject getEObject(Notification notice) {
		final Object notifier = notice.getNotifier();
		return (EObject) (notifier instanceof EObject ? notifier : null);
	}
	
	public EStructuralFeature getFeature(Notification notice) {
		Object feature = notice.getFeature();
		return (EStructuralFeature) (feature instanceof EStructuralFeature ? feature : null);
	}
	
	public EObject getElement(TScope scope) {
		return (EObject) this.model.getElementById(scope.getRootElement().getElementId());
	}
	
	public Constraint getConstraint(TScope scope) {
		EObject element = this.getElement(scope);
		if (element == null) {
			return null;
		}
		
		String name = scope.getConstraint().getName();
		try {
			return module.getConstraints().getConstraint(name, element, this.module.getContext());
		} catch (EolRuntimeException e) {
			return null;
		}
	}
	
}
