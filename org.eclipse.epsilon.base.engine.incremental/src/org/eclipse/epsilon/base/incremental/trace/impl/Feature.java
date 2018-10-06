package org.eclipse.epsilon.base.incremental.trace.impl;

/**
 * A simple class to store information about a Feature. Currently, extending classes are not expected to manage
 * containment.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public abstract class Feature {
	
//	protected final boolean isContainer;		# Future
//	protected final boolean isContainment;		# Future
//	protected final boolean isMultivalued;		# No need as code is generated
	protected final boolean isUnique;
	
	public Feature(boolean isUnique) {
		this.isUnique = isUnique;
	}
	
	public Feature() {
		this.isUnique = true;
	}

	public boolean isUnique() {
		return isUnique;
	}
	
	

}
