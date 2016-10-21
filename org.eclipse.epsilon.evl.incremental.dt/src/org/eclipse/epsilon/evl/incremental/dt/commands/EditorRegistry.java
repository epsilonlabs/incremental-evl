package org.eclipse.epsilon.evl.incremental.dt.commands;

import java.util.HashMap;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;

public class EditorRegistry extends HashMap<IEditingDomainProvider, IncrementalEvlModule>{
	
	public static final EditorRegistry REGISTRY = new EditorRegistry(); 

	private static final long serialVersionUID = 1L;
	
	/**
	 * Private constructor
	 */
	private EditorRegistry() {}


}
