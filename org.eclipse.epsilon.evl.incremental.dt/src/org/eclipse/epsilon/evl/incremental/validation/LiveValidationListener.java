package org.eclipse.epsilon.evl.incremental.validation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

public class LiveValidationListener extends EContentAdapter {
	
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
			System.out.println("Live Validation is Off");
			return;
		}
		
		IValidator<Notification> validator = ModelValidationService.getInstance().newValidator(EvaluationMode.LIVE);
		
		
		
		validator.validate(notification);
		
		System.out.println("Live validation is On");
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void enable() {
		this.enabled = true;
	}
	
	public void disable() {
		this.enabled = false;
	}
}
