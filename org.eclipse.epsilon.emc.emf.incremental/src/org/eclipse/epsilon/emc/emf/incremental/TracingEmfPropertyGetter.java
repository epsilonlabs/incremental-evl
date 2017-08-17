/*******************************************************************************
 * Copyright (c) 2017 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.emc.emf.incremental;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.emc.emf.EmfPropertyGetter;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.models.ITracingPropertyGetter;

/**
 * The tracing EMF Property Getter informs all registered Module Elements of access to the properties of objects in the
 * EMF model.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracingEmfPropertyGetter extends EmfPropertyGetter implements ITracingPropertyGetter {
	
	Map<ModuleElement, Boolean> moduleElements = new HashMap<>();
	
	boolean globalDeliver = false;

	private IncrementalEmfModel model;

	
	public TracingEmfPropertyGetter(IncrementalEmfModel model) {
		super();
		this.model = model;
	}

	public Object invoke(Object object, String property) throws EolRuntimeException {
		Object result = super.invoke(object, property);
		if (globalDeliver) {
			notify(object, property);
		}
		return result;
		
	}

	@Override
	public Set<ModuleElement> getTracedModuleElements() {
		// TODO Auto-generated method stub
		return moduleElements.keySet();
	}


	@Override
	public void notify(Object object, String propertyName) {
		String objectId = model.getElementId(object);
		for (ModuleElement me : moduleElements.keySet()) {
			if (moduleElements.get(me)) {
				IIncrementalModule module = (IIncrementalModule) me.getModule();
				String moduleElementId;
				try {
					moduleElementId = module.getModuleElementId(me);
				} catch (EolRuntimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
				IExecutionTraceManager manager = module.getExecutionTraceManager();
				manager.createTrace(moduleElementId, objectId, propertyName);
			}
			// Clear any existing scope
//			trace.removeScope(elementId, constraintName, contextName);
//			trace.commit();
		}
		
	}

	@Override
	public void setDeliver(boolean deliver) {
		
		globalDeliver = deliver;		
	}

	@Override
	public boolean isDelivering() {

		return globalDeliver;
	}


	@Override
	public void setDeliver(boolean deliver, ModuleElement element) {
		moduleElements.put(element, deliver);
		
	}

	@Override
	public boolean isDelivering(ModuleElement element) {
		
		return moduleElements.getOrDefault(element, false);
	}
	

}
