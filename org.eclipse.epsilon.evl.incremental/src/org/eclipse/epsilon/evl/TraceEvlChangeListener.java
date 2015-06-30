package org.eclipse.epsilon.evl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.eol.models.IModel;

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
	}

	@Override
	protected void setTarget(EObject target) {
		this.idMap.put(target, model.getElementId(target));
		super.setTarget(target);
	}
	
}
