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
package org.eclipse.epsilon.eol.incremental.dt.launching;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.common.dt.console.EpsilonConsole;
import org.eclipse.epsilon.common.dt.util.EclipseUtil;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.common.incremental.dt.launching.extensions.ExecutionTraceManagerExtension;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.dt.launching.EclipseContextManager;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

public class EclipseIncrementalContextManager extends EclipseContextManager {
	
	
	public static void setup(IEolIncrementalContext context, ILaunchConfiguration configuration,
			IProgressMonitor progressMonitor, ILaunch launch,
			boolean loadModels, boolean loadTraceManager) throws EolRuntimeException {
		if (loadTraceManager) {
			loadTraceManager(context,configuration,progressMonitor);
		}
		setup(context, configuration, progressMonitor, launch, loadModels);
	}

	private static void loadTraceManager(IEolIncrementalContext context, ILaunchConfiguration configuration,
			IProgressMonitor progressMonitor) {
		
		String subtask = "Loading trace manager";
		progressMonitor.subTask(subtask);
		progressMonitor.beginTask(subtask, 100);
		
		List<?> traceManagers;
		try {
			traceManagers = configuration.getAttribute("traceManager", new ArrayList<String>());
		} catch (CoreException e) {
			LogUtil.log(e); return;
		}
		ListIterator<?> li = traceManagers.listIterator();
		if (li.hasNext()) {
			String modelDescriptor = li.next().toString();
			StringProperties properties = new StringProperties();
			properties.load(modelDescriptor);
			try {
				ExecutionTraceManagerExtension tmExtension = ExecutionTraceManagerExtension.forType(properties.getProperty(ExecutionTraceManagerExtension.TRACE_MANAGER_TYPE));
				IExecutionTraceManager traceManager = tmExtension.getOrCreateTraceManager();
				//				ITraceModel traceModel = tmExtension.getOrCreateModel();
//				traceModel.load(properties, new IRelativePathResolver() {
//					
//					@Override
//					public String resolve(String relativePath) {
//						try {
//							IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(relativePath));
//							if (file != null) { 
//								return file.getLocation().toOSString(); 
//							}
//						}
//						catch (Exception ex) { LogUtil.log("Error while resolving absolute path for " + relativePath, ex); }
//						
//						return EclipseUtil.getWorkspacePath() + relativePath;
//					}
//				});
				//traceManager.setTraceModel(traceModel);
				context.setExecutionTraceManager(traceManager);
			} catch (CoreException e) {
				EpsilonConsole.getInstance().getErrorStream().print(e.toString());
				LogUtil.log(e);
			}
		}
		progressMonitor.done();
	}
	
}
