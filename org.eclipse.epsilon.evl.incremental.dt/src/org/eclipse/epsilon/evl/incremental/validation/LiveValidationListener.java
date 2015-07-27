package org.eclipse.epsilon.evl.incremental.validation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class LiveValidationListener extends EContentAdapter {
	
	private boolean enabled;
	
	public LiveValidationListener() {
		this(true);
	}
	
	public LiveValidationListener(boolean isEnabled) {
		super();
		this.enabled = isEnabled;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		
		// Live validation has been disabled, do nothing
		if (!enabled) {
			System.out.println("Live Validation is Off");
			return;
		}
		
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
