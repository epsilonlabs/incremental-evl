package org.eclipse.epsilon.emc.emf.incremental;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.trace.ITraceScope;
import org.eclipse.epsilon.eol.models.IModel;

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
	private IIncrementalModule module;
	
	private Map<EObject, String> idMap = new HashMap<EObject, String>();

	public EmfPropertyChangeListener(IModel model, IIncrementalModule module) {
		this.model = model;
		this.module = module;
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
		//String propertyName = feature.getName();
		module.onDelete(elementId, notifier);
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
	
	public EObject getElement(ITraceScope scope) {
		return (EObject) this.model.getElementById(scope.getRootElement().getElementId());
	}
	
	public ModuleElement getConstraint(ITraceScope scope) {
//		EObject element = this.getElement(scope);
//		if (element == null) {
//			return null;
//		}
		
		String constraintName = scope.getConstraint().getName();
		String contextName = scope.getConstraint().getContext().getName();
		// FIXME Trace Metamodel needs:
		//	String moduleElementId = scope.getModuleElement().getId(); 
		String moduleElementId = contextName + "." + constraintName;
 		//try {
			return module.getModuleElementById(moduleElementId);
			//return module.getConstraints().getConstraint(name, element, this.module.getContext());
		//} catch (EolRuntimeException e) {
		//	return null;
		//}
	}
	
}
