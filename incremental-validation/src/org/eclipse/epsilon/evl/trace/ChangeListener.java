package org.eclipse.epsilon.evl.trace;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.IncEvlModule;
import org.eclipse.epsilon.evl.dom.Constraint;

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
			
			// Get the property that was changed
			TraceGraph<?> trace = module.getTraceGraph();
			TProperty property = trace.getProperty(
					((ENamedElement) notification.getFeature()).getName(), 
					trace.getElement(model.getElementId(notification.getNotifier())));
			
			// Retrieve the scopes and re-run the constraints
			// FIXME: add context properly
			for (TScope scope : property.getScopes()) {
				String constraintName = scope.getConstraint().getName();
				
				Object target = model.getElementById(scope.getRootElement().getElementId());
				
				try {
					Constraint constraint = this.module.getConstraints().getConstraint(
							constraintName.split("\\.")[1], target,
							this.module.getContext());
					
					if (constraint.getConstraintContext().getName()
							.equals(constraintName.split("\\.")[0]))
					constraint.check(target, this.module.getContext());
					
				} catch (EolRuntimeException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
