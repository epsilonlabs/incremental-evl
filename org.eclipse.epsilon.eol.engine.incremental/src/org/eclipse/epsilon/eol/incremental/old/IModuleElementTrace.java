package org.eclipse.epsilon.eol.incremental.old;

@Deprecated
public interface IModuleElementTrace {
	
	/**
	 * Gets the element id.
	 *
	 * @return The id of this model element
	 */
	String getElementId();

	/**
	 * Set the id of this model element.
	 *
	 * @param elementId            The new id
	 */
	void setElementId(String elementId);

}
