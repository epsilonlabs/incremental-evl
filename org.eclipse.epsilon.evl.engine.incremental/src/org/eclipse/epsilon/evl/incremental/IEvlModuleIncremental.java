package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.evl.IEvlModule;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;

public interface IEvlModuleIncremental extends IIncrementalModule, IEvlModule {
	
	IncrementalEvlContext getContext();

}
