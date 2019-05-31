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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotSerializableModelException;
import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.models.ModuleNotifications;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfUtil;

public class IncrementalEmfModel extends EmfModel implements IIncrementalModel {
	
	private boolean deliver;
	private ModuleNotifications moduleNotifications;
	
	public IncrementalEmfModel() {
		super();
		moduleNotifications = new ModuleNotifications(this);
	}

	@Override
	public Object serializePropertyValue(
		Object value,
		Object modelElement,
		String propertyName) throws NotSerializableModelException {
		assert modelElement instanceof EObject;
		EStructuralFeature sf = EmfUtil.getEStructuralFeature(((EObject) modelElement).eClass(), propertyName);
		if (sf instanceof EAttribute) {
			// Let the DB serialise primitive and Java types
			return value;
		}
		EReference er = (EReference)sf;
		if (er.isMany()) {
			// If Many, it must be a collection
			@SuppressWarnings("unchecked")
			List<EObject> values = (List<EObject>) value;
			final List<String> result = new ArrayList<>(values.size());
			for (EObject ref : values) {
				// TODO Verify that ECoreUtil does as expected
				result.add(EcoreUtil.getURI(ref).toString());
			}
			// Return the result as CSV
			return result.stream().collect(Collectors.joining(","));
		}
		else {
			assert value instanceof EObject;
			if (!owns(value)) {
				throw new NotSerializableModelException("Can not serialise objects that dont belong to this model.");
			}	
			return getElementId(value);
		}
	}

	@Override
	public boolean comparePropertyValue(Object modelElement, String propertyName, Object oldValue) {
		// We need to determine the type of the property, if its an ERefernce, then we need to 
		// compare via ids, else compare values directly
		assert modelElement instanceof EObject;
		EObject element = (EObject) modelElement;
		EStructuralFeature sf = EmfUtil.getEStructuralFeature(element.eClass(), propertyName);
		Object currentValue = element.eGet(sf);
		if (sf instanceof EAttribute) {
			return currentValue.equals(oldValue);
		}		
		EReference er = (EReference)sf;
		if (er.isMany()) {
			// If Many, it must be a collection
			@SuppressWarnings("unchecked")
			List<EObject> currentValues = (List<EObject>) currentValue;
			Collection<String> compare;
			Collection<String> oldValues;
			if (er.isUnique()) {
				if (er.isOrdered()) {
					oldValues = Arrays.asList(((String)oldValue).split(","));
					compare = new ArrayList<>(currentValues.size());
				}
				else {
					oldValues = new HashSet<>();
					Collections.addAll(oldValues, ((String)oldValue).split(","));
					compare = new HashSet<>(currentValues.size());
				}
				for (EObject ref : currentValues) {
					// TODO Verify that ECoreUtil does as expected
					compare.add(EcoreUtil.getURI(ref).toString());
				}
				return compare.equals(oldValues);
			}
			else {
				// For non unique we can not use sets... is it worth the difference? or use this
				// block for both cases?
				oldValues = Arrays.asList(((String)oldValue).split(","));
				compare = new ArrayList<>(currentValues.size());
				for (EObject ref : currentValues) {
					// TODO Verify that ECoreUtil does as expected
					compare.add(EcoreUtil.getURI(ref).toString());
				}
				if (er.isOrdered()) {
					return compare.equals(oldValues);
				}
				else {
					if (compare.size() != oldValues.size()) {
						return false;
					}
					for (String current : compare) {
						if (!oldValues.remove(current)) {
							return false;
						}
					}
				}
				return true;
			}
		}
		else {
			return currentValue.equals(getElementById((String) oldValue));
		}
	}

	@Override
	public String getModelUri() {
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
//				for (IIncrementalModule module : getModules()) {
//					Set<String> elementIds = getModelTraceFactory().getAllModelElementIds();
//					EmfPropertyChangeListener emfPCL = new EmfPropertyChangeListener(this, module, elementIds);
//					getResource().eAdapters().add(emfPCL);
//				}
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
	}	@Override
	public void notifyChange(Object element, String propertyName) {
		moduleNotifications.notifyChange(element, propertyName);
	}

	@Override
	public void notifyDeletion(Object element) {
		moduleNotifications.notifyDeletion(element);
	}

	@Override
	public void notifyCreation(Object element) {
		moduleNotifications.notifyCreation(element);
	}
	
	@Override
	public boolean registerModule(IIncrementalModule module) {
		return moduleNotifications.registerModule(module);
	}

	@Override
	public boolean isRegistered(IIncrementalModule module) {
		return moduleNotifications.isRegistered(module);
	}

	@Override
	public boolean unregisterModule(IIncrementalModule module) {
		return moduleNotifications.unregisterModule(module);
	}

	@Override
	public Iterator<Object> getAllElements() {
		return new ResourceIterator(getResources());
	}
	
	private class ResourceIterator implements Iterator<Object> {
	
		private final Queue<Iterator<EObject>> iterators;
		private Iterator<EObject> current = null;
		
		public ResourceIterator(List<Resource> resources) {
			iterators = new ArrayDeque<>();
			for (Resource r : resources) {
				iterators.add(r.getAllContents());
			}
		}

		@Override
		public boolean hasNext() {
			if (current == null) {
				if (iterators.isEmpty()) {
					return false;
				}
				current = iterators.poll();
			}
			if (current.hasNext()) {
				return true;
			}
			current = iterators.poll();
			if (current == null) {
				return false;
			}
			return current.hasNext();
			
		}

		@Override
		public EObject next() {
			if (current == null) {
				if (iterators.isEmpty()) {
					throw new NoSuchElementException();
				}
				current = iterators.poll();
			}
			return current.next();
		}
	}
	
}
