/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thanos Zolotas - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.old;

// TODO: Auto-generated Javadoc
/**
 * POJO to hold a Scope.
 * 
 * @author Jonathan Co
 *
 */
@Deprecated
public class PropertyAccess {

	/** The element id. */
	private String elementId;
	
	/** The property name. */
	private String propertyName;
	
	/**
	 * Instantiates a new property access.
	 *
	 * @param elementId the element id
	 * @param propertyName the property name
	 */
	public PropertyAccess(String elementId, String propertyName) {
		this.elementId = elementId;
		this.propertyName = propertyName;
	}

	/**
	 * Gets the element id.
	 *
	 * @return the element id
	 */
	public String getElementId() {
		return elementId;
	}

	/**
	 * Sets the element id.
	 *
	 * @param elementId the new element id
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
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
	 * Sets the property name.
	 *
	 * @param propertyName the new property name
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((elementId == null) ? 0 : elementId.hashCode());
		result = prime * result
				+ ((propertyName == null) ? 0 : propertyName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyAccess other = (PropertyAccess) obj;
		if (elementId == null) {
			if (other.elementId != null)
				return false;
		} else if (!elementId.equals(other.elementId))
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		return true;
	}
	
}
