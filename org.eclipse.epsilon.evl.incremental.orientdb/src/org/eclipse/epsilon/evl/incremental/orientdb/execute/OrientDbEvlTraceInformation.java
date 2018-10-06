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
package org.eclipse.epsilon.evl.incremental.orientdb.execute;

import java.util.Set;

/**
 * An Class to define the positions to place/access trace data in a String array. Provides static
 * methods for working with the trace data 
 * @author Horacio Hoyos
 *
 */
public class OrientDbEvlTraceInformation {
	
	/** The module element id. */
	public final String moduleElementId;
	
	/** The model id. */
	public final String modelId;
	
	/** The model element id. */
	public final String modelElementId;
	
	/** The property name. */
	public final String propertyName;
	
	/** The satisfies module ids. */
	public final Set<String> satisfiesModuleIds;
	
	
	/**
	 * Instantiates a new orient db evl trace information.
	 *
	 * @param moduleElementId the module element id
	 * @param modelId the model id
	 * @param modelElementId the model element id
	 * @param propertyName the property name
	 * @param satisfiesModuleIds the satisfies module ids
	 */
	public OrientDbEvlTraceInformation(String moduleElementId, String modelId, String modelElementId,
			String propertyName, Set<String> satisfiesModuleIds) {
		super();
		this.moduleElementId = moduleElementId;
		this.modelId = modelId;
		this.modelElementId = modelElementId;
		this.propertyName = propertyName;
		this.satisfiesModuleIds = satisfiesModuleIds;
	}


	/**
	 * Gets the module element id.
	 *
	 * @return the module element id
	 */
	public String getModuleElementId() {
		return moduleElementId;
	}


	/**
	 * Gets the model id.
	 *
	 * @return the model id
	 */
	public String getModelId() {
		return modelId;
	}


	/**
	 * Gets the model element id.
	 *
	 * @return the model element id
	 */
	public String getModelElementId() {
		return modelElementId;
	}


	/**
	 * Gets the property name.
	 *
	 * @return the property name
	 */
	public String getPropertyName() {
		return propertyName;
	}


	/**
	 * Gets the satisfies module ids.
	 *
	 * @return the satisfies module ids
	 */
	public Set<String> getSatisfiesModuleIds() {
		return satisfiesModuleIds;
	}
	
}
