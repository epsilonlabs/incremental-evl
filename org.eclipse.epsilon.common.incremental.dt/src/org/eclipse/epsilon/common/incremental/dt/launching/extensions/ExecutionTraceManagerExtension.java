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
package org.eclipse.epsilon.common.incremental.dt.launching.extensions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epsilon.base.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Place holder for information about a TraceManager provided via the extension point.
 * @author Horacio Hoyos
 * @since 1.5
 *
 */
public class ExecutionTraceManagerExtension {
	
	public static final String TRACE_MANAGER_STABLE = "stable";

	public static final String EXTENSION_POINT = "org.eclipse.epsilon.common.incremental.dt.executionTraceManager";

	public static final String TRACE_MANAGER_CLASS = "class";
	
	public static final String TRACE_MANAGER_DIALOG = "dialog";
	
	public static final String TRACE_MANAGER_TYPE = "type";
	
	public static final String TRACE_MANAGER_LABEL = "label";
	
	public static final String TRACE_MANAGER_IMAGE = "icon";
	
	/**
	 * Get all registered ExecutionTraceManagerExtensions
	 * 
	 * @return
	 */
	public static List<ExecutionTraceManagerExtension> allTraceManagers() {
		
		ArrayList<ExecutionTraceManagerExtension> traceManagers = new ArrayList<ExecutionTraceManagerExtension>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry.getExtensionPoint(EXTENSION_POINT);
		IConfigurationElement[] configurationElements =  extensionPoint.getConfigurationElements();
		for (int i=0;i<configurationElements.length; i++){
			IConfigurationElement configurationElement = configurationElements[i];
			ExecutionTraceManagerExtension traceManagerType = initializeExecutionTraceManagerExtension(
					configurationElement);
			traceManagers.add(traceManagerType);
			
		}
		return traceManagers;
	}
	
	/**
	 * Get the ExecutionTraceManagerExtension for a particular type of trace manager
	 * 
	 * @param type the type to match
	 * @return
	 */
	public static ExecutionTraceManagerExtension forType(String type) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry.getExtensionPoint(EXTENSION_POINT);
		IConfigurationElement[] configurationElements =  extensionPoint.getConfigurationElements();
		for (int i=0;i<configurationElements.length; i++){
			IConfigurationElement configurationElement = configurationElements[i];
			
			if (configurationElement.getAttribute(TRACE_MANAGER_TYPE).equalsIgnoreCase(type)){
				
				ExecutionTraceManagerExtension traceManagerType = initializeExecutionTraceManagerExtension(
						configurationElement);
				return traceManagerType;
			}
		}
		return null;
	}
	
	/**
	 * Initialise a new ExecutionTraceManagerExtension for the given configuration element.
	 * 
	 * @param configurationElement
	 * @return a new ExecutionTraceManagerExtension with its fields appropriately set
	 */
	private static ExecutionTraceManagerExtension initializeExecutionTraceManagerExtension(
			IConfigurationElement configurationElement) {
		ExecutionTraceManagerExtension traceManagerType = new ExecutionTraceManagerExtension();
		traceManagerType.configurationElement = configurationElement;
		traceManagerType.managerclazz = configurationElement.getAttribute(TRACE_MANAGER_CLASS);
		traceManagerType.dialogclazz = configurationElement.getAttribute(TRACE_MANAGER_DIALOG);
		traceManagerType.type = configurationElement.getAttribute(TRACE_MANAGER_TYPE);
		traceManagerType.label = configurationElement.getAttribute(TRACE_MANAGER_LABEL);
		traceManagerType.stable = Boolean.parseBoolean(configurationElement.getAttribute(TRACE_MANAGER_STABLE));
		String contributingPlugin = configurationElement.getDeclaringExtension().getNamespaceIdentifier();
		Image image = AbstractUIPlugin.imageDescriptorFromPlugin(contributingPlugin,configurationElement.getAttribute(TRACE_MANAGER_IMAGE)).createImage();
		traceManagerType.image = image;
		return traceManagerType;
	}
	
	/**
	 * The configuration element constructed from the pluing.xml extension point description
	 */
	protected IConfigurationElement configurationElement;
	
	/**
	 * The label for the trace manager in the UI selection dialog
	 */
	protected String label;
	
	/**
	 * The icon for the trace manager in the UI selection dialog
	 */
	protected Image image;
	
	/**
	 * The name of the class that implements the ITraceManager interface
	 */
	protected String managerclazz;
	
	/**
	 * The name of the class that provides the trace model configuration dialog
	 */
	protected String dialogclazz;

	/**
	 * The type that identifies this trace manager extension
	 */
	protected String type;
	
	/**
	 * The name of the class that implements the ITraceModel interface and is used by this manager
	 */
	private String modelClass;
	
	
	protected boolean stable;
	
	
	public AbstractTraceManagerConfigurationDialog createConfigurationDialog() throws CoreException {
		return (AbstractTraceManagerConfigurationDialog) configurationElement.createExecutableExtension(TRACE_MANAGER_DIALOG);
	}
	
	public IEolExecutionTraceManager createTraceManager() throws CoreException {
		return (IEolExecutionTraceManager) configurationElement.createExecutableExtension(TRACE_MANAGER_CLASS);
	}
	
	public IConfigurationElement getConfigurationElement() {
		return configurationElement;
	}
	
	public String getDialogclazz() {
		return dialogclazz;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getManagerclazz() {
		return managerclazz;
	}
	
	public String getModelClass() {
		return modelClass;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isStable() {
		return stable;
	}
	
	public void setConfigurationElement(IConfigurationElement configurationElement) {
		this.configurationElement = configurationElement;
	}
	
	public void setDialogclazz(String dialogclazz) {
		this.dialogclazz = dialogclazz;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setManagerclazz(String managerclazz) {
		this.managerclazz = managerclazz;
	}
	
	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}

	public void setStable(boolean stable) {
		this.stable = stable;
	}

	public void setType(String type) {
		this.type = type;
	}


	
}
