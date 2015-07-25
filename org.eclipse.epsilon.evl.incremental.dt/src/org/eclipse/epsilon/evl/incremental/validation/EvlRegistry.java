package org.eclipse.epsilon.evl.incremental.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class EvlRegistry extends HashMap<ResourceSet, Set<IFile>> {
	
	public static final EvlRegistry REGISTRY = new EvlRegistry(); 

	private static final long serialVersionUID = 1L;
	
	/**
	 * Private constructor
	 */
	private EvlRegistry() {}
		
	/**
	 * Convenience method for adding a new EVL file association
	 * @param resourceSet
	 * @param file
	 */
	public void put(ResourceSet resourceSet, IFile file) {
		Set<IFile> evlFiles = this.get(resourceSet);
		if (evlFiles == null) {
			evlFiles = new HashSet<IFile>();
			this.put(resourceSet, evlFiles);
		}
		evlFiles.add(file);
	}
	
}
