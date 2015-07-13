package org.eclipse.epsilon.evl.incremental;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.incremental.trace.TProperty;
import org.eclipse.epsilon.evl.incremental.trace.TScope;
import org.eclipse.epsilon.evl.incremental.trace.TraceGraph;

import com.tinkerpop.blueprints.Graph;

public class TraceEvlChangeListener extends EContentAdapter {

	private Map<EObject, String> idMap = new HashMap<EObject, String>();

	private IModel model;
	private TraceEvlModule module;
	private TraceEvlContext context;

	/**
	 * Default Constructor
	 * 
	 * @param model
	 * @param module
	 */
	public TraceEvlChangeListener(IModel model, TraceEvlModule module) {
		this.model = model;
		this.module = module;
		this.context = (TraceEvlContext) module.getContext();
	}

	@Override
	public void notifyChanged(Notification notice) {
		super.notifyChanged(notice);
		
		/*
		 * CASES
		 * - Elements
		 * 	+ removal from containment list
		 *  - removal from reference list
		 *  - removal from containment
		 *  - removal from reference
		 *  - add to containment list
		 *  - add to reference list
		 *  - add to containment
		 *  - add to reference
		 * 	- modify from containment list
		 *  - modify from reference list
		 *  - modify from containment
		 *  - modify from reference
		 * - Attributes
		 * 	- removal from list
		 *  - removal from prop
		 *  - add to list
		 *  - add to prop
		 *  - modify from list
		 *  - modify from prop 
		 */
		
		// Determine if anything needs to be done
		if (!(notice.getNotifier() instanceof EObject)) {
			return;
		}

		// Constraints to be re-run
		Map<EObject, Set<Constraint>> constraints = null;
		
		switch (notice.getEventType()) {
		case Notification.REMOVING_ADAPTER:
			constraints = this.onRemoveAdapter(notice);
			break;
		case Notification.SET:
			constraints = this.onSet(notice);
			break;
		default:
			break;
		}
		
		// Rerun constraints
		if (constraints != null && !constraints.isEmpty()) {
			for (Entry<EObject, Set<Constraint>> entry : constraints.entrySet()) {
				for (Constraint constraint : entry.getValue()) {
					try {
						if (constraint != null) {
							constraint.check(entry.getKey(), context);
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (EolRuntimeException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public Map<EObject, Set<Constraint>> onRemoveAdapter(Notification notice) {
		final EObject notifier = (EObject) notice.getNotifier();
		
		// Still attached to a resource, element not deleted, do nothing
		if (notifier.eResource() != null) {
			return null;
		}
		
		// Retrieve ID. If no ID then this was never added to this model.
		final String elementId = idMap.remove(notifier);
		if (elementId == null) {
			return null;
		}
		
		// Get the properties
		Iterable<TScope> scopes = this.getGraph().getScopesPartOf(elementId);	
		Map<EObject, Set<Constraint>> map = processScopes(scopes);
		this.getGraph().removeElement(elementId);
		return map;
	}
	
	public Map<EObject, Set<Constraint>> onSet(Notification notice) {
		final EObject notifier = (EObject) notice.getNotifier();
		final EStructuralFeature feature = (EStructuralFeature) notice.getFeature();
		
		final TProperty property = this.getGraph().getProperty(
				feature.getName(), 
				this.model.getElementId(notifier));
		
		final Map<EObject, Set<Constraint>> constraints = 
				this.processScopes(property.getScopes());
		
		// Check if the property was deleted and remove from trace
		if (notice.wasSet() && !notifier.eIsSet(feature)) {
			this.getGraph().removeProperty(property);
		}
		
		return constraints;
	}
	
	public Map<EObject, Set<Constraint>> getConstraints(String elementId, String property) {
		final Map<EObject, Set<Constraint>> map = new HashMap<EObject, Set<Constraint>>();
		Iterable<TScope> scopes = this.context.getTraceGraph().getProperty(property, elementId).getScopes();
		for (TScope s : scopes) {
			EObject eobj = (EObject) model.getElementById(s.getRootElement().getElementId());
			Set<Constraint> set = map.get(eobj);
			if (set == null ){
				set = new HashSet<Constraint>();
				map.put(eobj, set);
			}
			
			try {
				set.add(module.getConstraints().getConstraint(s.getConstraint().getName(), eobj, context));
			} catch (EolRuntimeException e) {
				// FIXME
			}
		}
		
		return map;
	}
	
	public Map<EObject, Set<Constraint>> processScopes(Iterable<TScope> scopes) {
		final Map<EObject, Set<Constraint>> map = new HashMap<EObject, Set<Constraint>>();
		for(TScope s : scopes) {
			EObject eobject = (EObject) model.getElementById(s.getRootElement().getElementId());
			if (eobject == null) {
				continue;
			}
			Set<Constraint> set = map.get(eobject);
			if (set == null ){
				set = new HashSet<Constraint>();
				map.put(eobject, set);
			}
			
			try {
				set.add(module.getConstraints().getConstraint(s.getConstraint().getName(), eobject, context));
			} catch (EolRuntimeException e) {
				// FIXME
			}
		}
		return map;
	}

	/**
	 * Stores all eResource ID's of adapted objects
	 */
	@Override
	protected void setTarget(EObject target) {
		this.idMap.put(target, model.getElementId(target));
		super.setTarget(target);
	}
	
	private TraceGraph<? extends Graph> getGraph() {
		return this.context.getTraceGraph();
	}
}
