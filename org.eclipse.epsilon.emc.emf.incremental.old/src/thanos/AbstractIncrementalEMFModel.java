package thanos;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.models.IModel;

public abstract class AbstractIncrementalEMFModel implements IIncrementalModel {
	
	protected IModel delegate;
	protected IIncrementalModule module;
	private EmfPropertyChangeListener emfPCL;
	
	abstract public Resource getResource();

	public IModel getDelegate() {
		return delegate;
	}

	public void setDelegate(IModel delegate) {
		this.delegate = delegate;
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
		emfPCL = new EmfPropertyChangeListener(delegate, module);
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
	


}
