package org.eclipse.epsilon.emc.emf.incremental;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;

public class BaseIncrementalEMFModel implements IIncrementalModel {

	private IIncrementalModule module;
	private EmfPropertyChangeListener emfPCL;
	private AbstractEmfModel owner;

	/**
	 * @param owner
	 */
	public BaseIncrementalEMFModel(AbstractEmfModel owner) {
		this.owner = owner;
	}

	public IIncrementalModule getModule() {
		return module;
	}

	public void setModule(IIncrementalModule module) {
		this.module = module;
	}

	@Override
	public boolean supportsNotifications() {
		return true;
	}

	@Override
	public boolean enableNotifications() {
		if (module == null) {
			return false;
		}
		emfPCL = new EmfPropertyChangeListener(owner, module);
		this.getResource().eAdapters().add(emfPCL);
		return true;
	}

	@Override
	public boolean disableNotifications() {
		return this.getResource().eAdapters().remove(emfPCL);
	}

	@Override
	public Object getPropertyValue(String elementId, String propertyName) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Resource getResource() {
		return owner.getResource();
	}

}