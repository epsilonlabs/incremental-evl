package org.eclipse.epsilon.evl.incremental.validation;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.IValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

public class LiveValidationListener extends EContentAdapter {
	
	/**
	 * Map to track the IDs of objects this listener has been attached to. Used 
	 * to determine ID of an object after it has been deleted from the model.
	 */
	private final Map<EObject, String> idMap = new HashMap<EObject, String>();
	
	private boolean enabled;
	private EditingDomain editingDomain;
	
	public LiveValidationListener(EditingDomain editingDomain) {
		this(editingDomain, true);
	}
	
	public LiveValidationListener(EditingDomain editingDomain, boolean isEnabled) {
		super();
		this.enabled = isEnabled;
		this.editingDomain = editingDomain;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		
		// Live validation has been disabled, do nothing
		if (!enabled) {
			return;
		}
		
		if (!(notification.getNotifier() instanceof EObject)) {
			return;
		}
		
		// Setup the context and get objects that need to be evaluated.
		
		
		final TraceEvlModule module = new TraceEvlModule();
		for (Resource r : editingDomain.getResourceSet().getResources()) {
			module.getContext().getModelRepository().addModel(new InMemoryEmfModel(r));
		}
		final Set<String> ids = this.getIds(notification, module);
		final Set<EObject> eobjects = new HashSet<EObject>();
		
		IValidator<Notification> validator = ModelValidationService
				.getInstance().newValidator(EvaluationMode.LIVE);
	
		validator.addConstraintFilter(new IConstraintFilter() {
			@Override
			public boolean accept(IConstraintDescriptor constraint, EObject target) {
				if (!(constraint instanceof EvlEmfConstraint)) {
					return false;
				}
				
				if (ids.contains(module.getContext().getModelRepository()
						.getOwningModel(target).getElementId(target))) {
					eobjects.add(target);
					return true;
				}
				
				return false;
			}
		});
		
		final List<Notification> list = new LinkedList<Notification>();
		for (EObject eObject : eobjects) {
			list.add(new EvlModelConstraintNotification(notification, eObject));
		}
		
		validator.validate(notification);
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void enable() {
		this.enabled = true;
		IBatchValidator validator = ModelValidationService.getInstance()
				.newValidator(EvaluationMode.BATCH);
			// include live constraints, also, in batch validation
			validator.setOption(IBatchValidator.OPTION_INCLUDE_LIVE_CONSTRAINTS, true);
		for (Resource r : editingDomain.getResourceSet().getResources()) {
			TreeIterator<EObject> it = r.getAllContents();
			while (it.hasNext()) {
				EObject next = it.next();
				validator.validate(next);
			}
		}
		
	}
	
	public void disable() {
		this.enabled = false;
	}
	
	/**
	 * Stores all eResource ID's of adapted objects
	 */
	@Override
	protected void setTarget(EObject target) {
		this.idMap.put(target, target.eResource().getURIFragment(target));
		super.setTarget(target);
	}
	
	private Set<String> getIds(Notification notification, TraceEvlModule module) {
		EObject object = (EObject) notification.getNotifier();
		String id = null;
		Iterable<TScope> scopes = null;
		
		switch (notification.getEventType()) {
		
		// Case the eobject was deleted
		case Notification.REMOVING_ADAPTER:
			id = this.idMap.remove(object);
			if (id == null) {
				return Collections.emptySet();
			}
			scopes = module.getTraceGraph().getScopesPartOf(id);
			module.getTraceGraph().removeElement(id);
			break;
			
		// Something was changed
		case Notification.SET:
			id = object.eResource().getURIFragment(object);
			EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
			TProperty property = module.getTraceGraph().getProperty(feature.getName(), id);
			scopes = property.getScopes();
			if (notification.wasSet() && !object.eIsSet(feature)) {
				module.getTraceGraph().removeProperty(property);
			}
			break;
			
		default:
			return Collections.emptySet();
		}
		
		final Set<String> set = new HashSet<String>();
		for (TScope s : scopes) {
			set.add(s.getRootElement().getElementId());
		}
		
		return set;
	}
	
	class EvlModelConstraintNotification extends NotificationImpl {
		
		private EObject eObject;
		
		public EvlModelConstraintNotification(Notification notification, EObject eObject) {
			super(notification.getEventType(), null, null);
			this.eObject = eObject;
		}
		
		@Override
		public Object getNotifier() {
			return this.eObject;
		}
	}

}
