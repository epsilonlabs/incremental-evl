package org.eclipse.epsilon.evl.trace;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.IncEvlModule;

public class ChangeListener extends EContentAdapter {

	private final IModel model;
	private final IncEvlModule module;

	public ChangeListener(IModel model, IncEvlModule module) {
		this.model = model;
		this.module = module;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		// FIXME: Change to fire only for certain notification event types
		
		if (notification.getNotifier() instanceof EObject 
				&& notification.getFeature() instanceof ENamedElement) {
			
			TraceGraph<?> trace = module.getTraceGraph();
			TElement element = 
					trace.getElement(model.getElementId(notification.getNotifier()));
			String propertyName = 
					((ENamedElement) notification.getFeature()).getName();
			
			TProperty property = trace.getProperty(propertyName, element);
			
			for (TScope tScope : property.getScopes()) {
				System.out.println(tScope.getRootElement().getElementId());
			}

		}
	}

}
