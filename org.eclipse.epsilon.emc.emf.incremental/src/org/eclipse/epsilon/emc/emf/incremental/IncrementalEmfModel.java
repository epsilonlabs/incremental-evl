package org.eclipse.epsilon.emc.emf.incremental;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;

public class IncrementalEmfModel extends EmfModel implements IIncrementalModel  {
	
	BaseIncrementalEMFModel delegate;
	
	public IncrementalEmfModel() {
		this.delegate = new BaseIncrementalEMFModel(this);
	}

	public IIncrementalModule getModule() {
		return delegate.getModule();
	}

	public boolean enableNotifications() {
		return delegate.enableNotifications();
	}

	public boolean disableNotifications() {
		return delegate.disableNotifications();
	}

	public void setModule(IIncrementalModule module) {
		delegate.setModule(module);
	}

	public boolean supportsNotifications() {
		return delegate.supportsNotifications();
	}

	public Object getPropertyValue(String elementId, String propertyName) {
		return delegate.getPropertyValue(elementId, propertyName);
	}
}
