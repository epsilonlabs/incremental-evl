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

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  the live EVL validation is to be performed.
 * The SET, MOVE, ADD and ADDMANY events trigger the invocation of the {@link #onChange(EObject, EStructuralFeature)}
 * method. The REMOVING_ADAPTER, REMOVE, REMOVE_MANY and UNSET events trigger the {@link #onChange(EObject, EStructuralFeature)}
 * method. The {@link #onCreate(EObject)}
 * @author Jonathan Co
 * @author Horacio Hoyos
 *
 */
public class EmfPropertyChangeListener extends EContentAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(EmfPropertyChangeListener.class);
	
	private boolean checkNew = false;
	
	private IIncrementalModel model;
	
	private IModuleIncremental module;
	
	private final Set<String> elementsOfInterest;
	
	
	public EmfPropertyChangeListener(IIncrementalModel model, IModuleIncremental module,
			Set<String> elementIds) {
		this.model = model;
		this.module = module;
		this.elementsOfInterest = elementIds;
	}

	public void onCreate(EObject notifier) {
		logger.debug("onCreate {}", notifier);
		module.onCreate(model, notifier);	
	}
	
	public void onChange(EObject notifier, EStructuralFeature feature) {
		logger.debug("onChange {}", notifier);
		if (notifier == null || feature == null) {
			return;
		}
		String propertyName = feature.getName();
		module.onChange(model, notifier, propertyName);
	}

	public void onDelete(EObject notifier, EStructuralFeature feature) {
		logger.debug("onDelete {}", notifier);
		if (notifier == null || feature == null) {
			return;
		}
		module.onDelete(model, notifier);
		model.getModelTraceFactory().removeModelElement(model.getElementId(notifier));
	}

	@Override
	protected void setTarget(EObject target) {		
		super.setTarget(target);
		String id = model.getElementId(target);
		if (checkNew && !elementsOfInterest.contains(id)) {
			elementsOfInterest.add(id);
			onCreate(target);
		}
	}
	
	@Override
	protected void setTarget(Resource target) {
		super.setTarget(target);
		this.checkNew = true;
	}
	
	@Override
	public void notifyChanged(Notification notice) {
		super.notifyChanged(notice);
		
		if (!(notice.getNotifier() instanceof EObject)) {
			return;
		}
		
		final EObject notifier = this.getEObject(notice);		
		final EStructuralFeature feature = this.getFeature(notice);
		
		switch (notice.getEventType()) {
		case Notification.SET:
		case Notification.ADD:
		case Notification.MOVE:
		case Notification.ADD_MANY:
			onChange(notifier, feature);
			break;
		case Notification.REMOVING_ADAPTER:
		case Notification.REMOVE:
		case Notification.REMOVE_MANY:
		case Notification.UNSET:
			onDelete(notifier, feature);
		}
	}

	public EObject getEObject(Notification notice) {
		final Object notifier = notice.getNotifier();
		return (notifier instanceof EObject ? (EObject) notifier : null);
	}
	
	public EStructuralFeature getFeature(Notification notice) {
		Object feature = notice.getFeature();
		return (feature instanceof EStructuralFeature ? (EStructuralFeature) feature : null);
	}
	
	
}
