package org.eclipse.epsilon.evl.incremental.dt.commands;

import org.eclipse.epsilon.evl.incremental.validation.LiveValidationListener;
import org.eclipse.jface.action.Action;

public class EnableLiveValidationAction extends Action {

	public static final String ID = "org.eclipse.epsilon.evl.incremental.dt.commands.enableLiveValidation";

	private final LiveValidationListener listener;

	public EnableLiveValidationAction(LiveValidationListener listener) {
		// TODO: Change to get from properties for localisation
		super("Live Validation", Action.AS_CHECK_BOX);
		this.setEnabled(true);
		this.setId(EnableLiveValidationAction.ID);
		this.listener = listener;
	}

	@Override
	public void run() {
		if (this.isChecked()) {
			this.listener.enable();
		} else {
			this.listener.disable();
		}
	}

	public LiveValidationListener getListener() {
		return listener;
	}

}
