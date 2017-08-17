package org.eclipse.epsilon.common.incremental.dt.launching.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epsilon.common.dt.launching.dialogs.AbstractModelConfigurationDialog;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ExecutionTraceManagerExtension {
	
	protected IConfigurationElement configurationElement;
	protected String label;
	protected Image image;
	protected String managerclazz;
	protected String dialogclazz;
	
	public IConfigurationElement getConfigurationElement() {
		return configurationElement;
	}
	public void setConfigurationElement(IConfigurationElement configurationElement) {
		this.configurationElement = configurationElement;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getManagerclazz() {
		return managerclazz;
	}
	public void setManagerclazz(String managerclazz) {
		this.managerclazz = managerclazz;
	}
	public String getDialogclazz() {
		return dialogclazz;
	}
	public void setDialogclazz(String dialogclazz) {
		this.dialogclazz = dialogclazz;
	}
	
	public AbstractTraceManagerConfigurationDialog createConfigurationDialog() throws CoreException {
		return (AbstractTraceManagerConfigurationDialog) configurationElement.createExecutableExtension("dialog");
	}

	public IExecutionTraceManager createTraceManager() throws CoreException {
		return (IExecutionTraceManager) configurationElement.createExecutableExtension("class");
	}
	
}
