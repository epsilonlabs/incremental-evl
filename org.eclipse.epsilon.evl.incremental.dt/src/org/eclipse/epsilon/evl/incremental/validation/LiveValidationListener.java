package org.eclipse.epsilon.evl.incremental.validation;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.incremental.TraceEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TScope;

public class LiveValidationListener extends EContentAdapter {
	
	/**
	 * Map to track the IDs of objects this listener has been attached to. Used 
	 * to determine ID of an object after it has been deleted from the model.
	 */
	private final Map<EObject, String> idMap = new HashMap<EObject, String>();
	
	/**
	 * The {@link EditingDomain} that this listener has been attached to
	 */
	private final EditingDomain editingDomain;
	private TraceEvlContext evlContext;
	private boolean enabled;
	
	public LiveValidationListener(EditingDomain editingDomain) {
		this(editingDomain, true);
	}
	
	public LiveValidationListener(EditingDomain editingDomain, boolean isEnabled) {
		super();
		this.enabled = isEnabled;
		this.editingDomain = editingDomain;
		this.evlContext = new TraceEvlContext();
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

		final Set<EObject> objects = this.getIds(notification);
		
		IValidator<Notification> validator = ModelValidationService
				.getInstance().newValidator(EvaluationMode.LIVE);
		validator.addConstraintFilter(new EvlContextInjector(this.evlContext));
		
		for (EObject eObject : objects) {
			validator.validate(new EvlModelConstraintNotification(notification, eObject));
		}
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void enable() {
		this.enabled = true;
		
		// Init the EvlContext and add all the Models
		this.evlContext = new TraceEvlContext();
		for (Resource resource : this.editingDomain.getResourceSet().getResources()) {
			IModel model = new InMemoryEmfModel(resource);
			this.evlContext.getModelRepository().addModel(model);
		}
		
		IBatchValidator validator = ModelValidationService.getInstance()
				.newValidator(EvaluationMode.BATCH);
		validator.setOption(IBatchValidator.OPTION_INCLUDE_LIVE_CONSTRAINTS, true);
		validator.addConstraintFilter(new EvlContextInjector(evlContext));
		
		// Iterator through all objects and evaluate
		// TODO: Should we just use the EvlModule to do this?
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
	
	/**
	 * 
	 * @param notification
	 * @param module
	 * @return
	 */
	private Set<EObject> getIds(Notification notification) {
		final EObject object = (EObject) notification.getNotifier();		
		String id = null;
		Iterable<TScope> scopes = null;
		
		switch (notification.getEventType()) {
		
		// Case the eobject was deleted
		case Notification.REMOVING_ADAPTER:
			id = this.idMap.remove(object);
			if (id == null) {
				return Collections.emptySet();
			}
			scopes = this.evlContext.getTraceGraph().getScopesPartOf(id);
			this.evlContext.getTraceGraph().removeElement(id);
			break;
			
		// Something was changed
		case Notification.SET:
			id = object.eResource().getURIFragment(object);
			EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
			TProperty property = this.evlContext.getTraceGraph().getProperty(feature.getName(), id);
			scopes = property.getScopes();
			if (notification.wasSet() && !object.eIsSet(feature)) {
				this.evlContext.getTraceGraph().removeProperty(property);
			}
			break;
			
		default:
			return Collections.emptySet();
		}
		
		Set<EObject> eObjects = new HashSet<EObject>();
		for (TScope s : scopes) {
			String elementId = s.getRootElement().getElementId();
			for (IModel model : this.evlContext.getModelRepository().getModels()) {
				Object element = model.getElementById(elementId);
				if (element != null) {
					eObjects.add(object);
					continue;
				}
			}
		}
		
		return eObjects;
	}
	
	/**
	 * Wrapper to pass {@link EObject}s to validation framework
	 * 
	 * @author Jonathan Co
	 *
	 */
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
	
	/**
	 * "Filter" for setting the current evl context for constraint checking
	 * 
	 * @author Jonathan Co
	 *
	 */
	class EvlContextInjector implements IConstraintFilter {
		
		private TraceEvlContext evlContext;
		
		EvlContextInjector(TraceEvlContext evlContext) {
			assert evlContext != null;
			this.evlContext = evlContext;
		}

		@Override
		public boolean accept(IConstraintDescriptor constraint, EObject target) {
			if (!(constraint instanceof EvlEmfConstraint)) {
				return false;
			}
			
			final EvlEmfConstraint evlEmfCon = (EvlEmfConstraint) constraint;
			evlEmfCon.setCurrentEvlContext(this.evlContext);
			
			return true;
		}
		
	}

}
