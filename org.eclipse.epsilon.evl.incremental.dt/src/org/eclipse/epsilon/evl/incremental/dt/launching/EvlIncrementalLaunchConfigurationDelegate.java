package org.eclipse.epsilon.evl.incremental.dt.launching;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.epsilon.common.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.incremental.dt.launching.EclipseIncrementalContextManager;
import org.eclipse.epsilon.evl.IncrementalEvlModule;
import org.eclipse.epsilon.evl.dt.launching.EvlLaunchConfigurationDelegate;

public class EvlIncrementalLaunchConfigurationDelegate extends EvlLaunchConfigurationDelegate {
	
	
	@Override
	public IEolModule createModule() {
		return new IncrementalEvlModule();
	}
	
	@Override
	public void setupContext(ILaunchConfiguration configuration, ILaunch launch, IProgressMonitor progressMonitor,
			IEolModule module, boolean setup) throws EolRuntimeException {
		EclipseIncrementalContextManager.setup(module.getContext(),configuration, progressMonitor, launch, setup);
	}

}
