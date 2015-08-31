package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.TElement;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

public class EmfPropertyChangeListener extends EContentAdapter implements IPropertyChangeListener {
	
	private boolean checkNew = false;
	
	private IModel model;
	private TraceEvlContext context;
	private IPropertyAccessTrace trace;
	
	private Map<EObject, String> idMap = new HashMap<EObject, String>();
	private Map<EObject, String> newIdMap = new HashMap<EObject, String>();

	public EmfPropertyChangeListener(EmfModel model, TraceEvlContext context) {
		this.model = model;
		this.context = context;
		this.trace = context.getTrace();
	}

	@Override
	public void onCreate(Notification notice) {
		final EObject notifier = this.getEObject(notice);
		 for (ConstraintContext conCtx : this.context.getModule().getConstraintContexts()) {
			 try {
				if (conCtx.appliesTo(notifier, this.context)) {
					 for (Constraint constraint : conCtx.getConstraints()) {
						constraint.check(notifier, this.context);
					}
				 }
			} catch (EolRuntimeException e) {
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public Collection<TScope> onChange(Notification notice) {
		final EObject notifier = this.getEObject(notice);
		final EStructuralFeature feature = this.getFeature(notice);
		
		if (notifier == null || feature == null) {
			return null;
		}
				
		TProperty property = this.trace.getProperty(feature.getName(), this.idMap.get(notifier));
		if (property == null) {
			return null;
		}
		
		List<TScope> scopeList = new LinkedList<TScope>();
		Iterator<TScope> it = property.getScopes().iterator();
		while (it.hasNext()) {
			scopeList.add(it.next());
		}
		return scopeList;
	}

	@Override
	public Collection<TScope> onDelete(Notification notice) {
		final EObject notifier = this.getEObject(notice);

		String elementId = null;
		if (notifier.eResource() == null) {
			elementId = this.idMap.remove(notifier);
		} else {
			elementId = this.idMap.get(notifier);
		}
		if (elementId == null) {
			return null;
		}
		
		final TElement element = this.trace.getElement(elementId);
		if (element == null) {
			return null;
		}
		
		Iterable<TScope> scopes = element.getScopes();
		List<TScope> scopeList = new LinkedList<TScope>();
		Iterator<TScope> it = scopes.iterator();
		while (it.hasNext()) {
			scopeList.add(it.next());
		}
		return scopeList;
	}

	@Override
	public void validateScopes(Collection<TScope> scopes) {
		if (scopes == null || scopes.isEmpty()) {
			return;
		}

		for (TScope scope : scopes) {
			final EObject target = this.getElement(scope);
			final Constraint constraint = this.getConstraint(scope);
			if (target == null || constraint == null) {
				continue;
			}

			try {
				constraint.check(target, this.context);
			} catch (EolRuntimeException e) {
				// TODO: Log exception
				continue;
			}
		}
	}

	@Override
	protected void setTarget(EObject target) {		
		this.idMap.put(target, model.getElementId(target));
		super.setTarget(target);
		if (checkNew) {
			this.newIdMap.put(target, model.getElementId(target));
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
		
		Collection<TScope> scopes = null;
		
		switch (notice.getEventType()) {
		case Notification.SET:
		case Notification.ADD:
		case Notification.MOVE:
		case Notification.ADD_MANY:
			scopes = this.onChange(notice);
			break;
		case Notification.REMOVING_ADAPTER:
		case Notification.REMOVE:
		case Notification.REMOVE_MANY:
		case Notification.UNSET:
			scopes = this.onDelete(notice);
			break;
		}
		
		this.validateScopes(scopes);
		
		if (!this.newIdMap.isEmpty()) {
			this.onCreate(notice);
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
			return this.context.getModule().getConstraints().getConstraint(name, element, this.context);
		} catch (EolRuntimeException e) {
			return null;
		}
	}
	
}
