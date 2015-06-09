package org.eclipse.epsilon.evl.notifications;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.RuleInstance;

public class ReEvaluateListener extends EContentAdapter {

	private final IModel model;
	private final Collection<RuleInstance> ruleInstances;

	public ReEvaluateListener(Collection<RuleInstance> ruleInstances, IModel model) {
		this.ruleInstances = ruleInstances;
		this.model = model;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		System.out.println(notification);
		if (notification.getNotifier() instanceof EObject) {
			
			String eObjId = model.getElementId(notification.getNotifier());
			String eAttrName = null;
			String eAttrId = null;
			if (notification.getFeature() instanceof ENamedElement) {
				eAttrName = ((ENamedElement) notification.getFeature()).getName();
				eAttrId = eObjId + "." + eAttrName;
			}
			
			System.out.println("Object " + eObjId + " changed its " + eAttrName);
			
			StringBuilder sb = new StringBuilder();
			sb.append("Re-run rules [ ");
			for (RuleInstance ri : ruleInstances) {
				if (ri.getScopes().contains(eObjId) || ri.getScopes().contains(eAttrId)) {
					sb.append("<").append(ri.getRule()).append(",").append(ri.getRootElementId()).append(">, ");
				}
			}
			sb.append("]");
			System.out.println(sb.toString());;
		}
		

	}
}
