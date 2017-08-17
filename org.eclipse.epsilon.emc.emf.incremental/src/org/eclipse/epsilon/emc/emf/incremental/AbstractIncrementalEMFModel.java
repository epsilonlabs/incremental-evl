/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	   Jonathan Co   - Initial API and implementation
 *     Horacio Hoyos - Decoupling and abstraction
 *******************************************************************************/
package org.eclipse.epsilon.emc.emf.incremental;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.models.IModel;

public abstract class AbstractIncrementalEMFModel implements IIncrementalModel {
	
	protected IModel delegate;
	private List<IIncrementalModule> modules;
	private boolean deliver;
	
	abstract Resource getResource();
	
	public IModel getDelegate() {
		return delegate;
	}

	public void setDelegate(IModel delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean supportsNotifications() {
		return true;
	}
	
	@Override
	public String getModelId() {
		return getResource().getURI().toString();
	}

	@Override
	public void setDeliver(boolean deliver) {
		if (deliver != this.deliver) {
			if (deliver) {
				for (IIncrementalModule module : getModules()) {
					EmfPropertyChangeListener emfPCL = new EmfPropertyChangeListener(delegate, module);
					this.getResource().eAdapters().add(emfPCL);
				}
			}
			else {
				this.getResource().eAdapters().clear();
			}
		}
		this.deliver = deliver;
	}

	@Override
	public boolean isDelivering() {

		return deliver;
	}

	@Override
	public List<IIncrementalModule> getModules() {
		if (modules == null) {
			modules = new ArrayList<>();
		}
		return modules;
	}


}
