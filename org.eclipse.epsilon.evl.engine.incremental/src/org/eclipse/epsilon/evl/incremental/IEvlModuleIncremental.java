package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.evl.IEvlModule;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;

public interface IEvlModuleIncremental extends IIncrementalModule, IEvlModule {
	
	/**
	 * We use a checksum as a form of ID so we can recognise the same file/code executed from different
	 * locations.
	 * @return The checksum (MD5) for the code/file being executed
	 */
	String getChksum();
	
	IncrementalEvlContext getContext();

}
