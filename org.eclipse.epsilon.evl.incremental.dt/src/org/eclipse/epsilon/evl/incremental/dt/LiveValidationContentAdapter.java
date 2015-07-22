package org.eclipse.epsilon.evl.incremental.dt;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class LiveValidationContentAdapter extends EContentAdapter {

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getNotifier() instanceof EObject) {
			System.out.println(notification);
		}
	}
}
