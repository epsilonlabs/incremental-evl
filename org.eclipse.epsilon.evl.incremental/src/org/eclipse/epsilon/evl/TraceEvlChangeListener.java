package org.eclipse.epsilon.evl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.trace.TElement;
import org.eclipse.epsilon.evl.trace.TProperty;
import org.eclipse.epsilon.evl.trace.TScope;
import org.eclipse.epsilon.evl.trace.TraceGraph;

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
		if (!(notice.getNotifier() instanceof EObject))
			return;
		
	
		Map<EObject, Set<Constraint>> constraints = null; // Constraints to be re-run
		String elementId = null; // ID of element changed
		String property = null;	// Name of property changed if applicable
		
		// Object changed
		EObject notifier = (EObject) notice.getNotifier();
		
		// EObject is detached from resource
		if (notifier.eResource() == null) {
			System.out.println(notice);
			switch (notice.getEventType()) {
			case Notification.REMOVING_ADAPTER:
				constraints = this.onRemoveAdapter(notice);
				break;
			default:
				break;
			}
		}

		switch (notice.getEventType()) {
		case Notification.SET:
			constraints = onSet(model.getElementId(notifier), ((ENamedElement) notice.getFeature()).getName());
			
			// The attribute was removed
			if(notice.wasSet() && !notifier.eIsSet((EStructuralFeature) notice.getFeature())) {
				onDeleteAttr(((ENamedElement) notice.getFeature()).getName(), model.getElementId(notifier));
			}
			
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
						System.out.println();
					}
					
					catch (EolRuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		// Finally call super
	}
	
	Map<EObject, Set<Constraint>> onRemoveAdapter(Notification notice) {
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
		final TraceGraph<? extends Graph> graph = context.getTraceGraph();		
		Map<EObject, Set<Constraint>> map = processScopes(graph.getScopesPartOf(elementId));
		graph.removeElement(elementId);
		return map;
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


	
	public Map<EObject, Set<Constraint>> onSet(String elementId, String property) {
		TraceGraph<? extends Graph> graph = context.getTraceGraph();
		TProperty tProperty = graph.getProperty(elementId, property);
		Map<EObject, Set<Constraint>> map = this.processScopes(tProperty.getScopes());
		return map;
	}
	
	public void onDeleteAttr(String property, String elementId) { 
		TraceGraph<? extends Graph> graph = context.getTraceGraph();
		TProperty tProperty = graph.getProperty(property, elementId);
		
		if (tProperty != null) {
			graph.removeProperty(property, elementId);
		}
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
	
}
