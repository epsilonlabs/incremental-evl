/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.emc.emf.online;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotInstantiableModelElementValueException;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotSerializableModelException;
import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.impl.MemoryModelTraceFactory;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class IncrementalEmfModel extends EmfModel implements IIncrementalModel {
	
	private final MemoryModelTraceFactory modelTraceFactory;
	private boolean deliver;
	private ArrayList<IModuleIncremental> modules;
	
	public IncrementalEmfModel() throws EolModelLoadingException {
		super();
		try {
			modelTraceFactory = new MemoryModelTraceFactory(this);
		} catch (EolIncrementalExecutionException e) {
			throw new EolModelLoadingException(e, this);
		}
	}

	@Override
	public MemoryModelTraceFactory getModelTraceFactory() {
		return modelTraceFactory;
	}

	@Override
	public String convertToString(Object instance) throws NotSerializableModelException {
		if (!owns(instance)) {
			throw new NotSerializableModelException("Can not convert objects that dont belong to this model.");
		}
		// We assume that once the model is loaded, we can get the correspoing value from its id.
		return getElementId(instance);
	}

	@Override
	public Object createFromString(String value) throws NotInstantiableModelElementValueException {
		return getElementById(value);
	}

	@Override
	public String getModelId() {
		return getResource().getURI().toString();
	}

	@Override
	public boolean supportsNotifications() {
		return true;
	}

	@Override
	public void setDeliver(boolean deliver) {
		if (deliver != this.deliver) {
			if (deliver) {
				for (IModuleIncremental module : getModules()) {
					Set<String> elementIds = getModelTraceFactory().getAllModelElementIds();
					EmfPropertyChangeListener emfPCL = new EmfPropertyChangeListener(this, module, elementIds);
					getResource().eAdapters().add(emfPCL);
				}
			}
			else {
				unRegister();
			}
		}
		this.deliver = deliver;
	}

	@Override
	public boolean isDelivering() {
		return deliver;
	}

	@Override
	public Collection<IModuleIncremental> getModules() {
		if (modules == null) {
			modules = new ArrayList<>();
		}
		return modules;
	}
	
	/**
	 * Compare the model against the module traces and rexecute as needed. THis probably should be part
	 * of the load method? 
	 * @param module
	 */
	public void reexecuteTraces(IModuleIncremental module) {
		
	}
	
	/**
	 * 
	 */
	private void unRegister() {
		Iterator<Adapter> it = getResource().eAdapters().iterator();
		while (it.hasNext()) {
			Adapter d = it.next();
			if (d instanceof EmfPropertyChangeListener) {
				it.remove();
			}
		};
	}

	
}
