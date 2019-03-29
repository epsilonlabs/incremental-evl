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
package org.eclipse.epsilon.common.incremental.dt.launching.dialogs;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractTraceManagerConfigurationDialog extends TitleAreaDialog {
	
	protected StringProperties properties;
	
	protected static Composite getOrCreateGroupContainer(Composite parent, String text, int columns) {
		final Group group = new Group(parent, SWT.FILL);
		
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setText(text);
		group.setLayout(new GridLayout(1,false));
		
		final Composite groupContent = new Composite(group, SWT.FILL);
		groupContent.setLayout(new GridLayout(columns, false));
		groupContent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		return groupContent;
	}
	
	public AbstractTraceManagerConfigurationDialog() {
		//super(LaunchConfigurationDialog.getCurrentlyVisibleLaunchConfigurationDialog().getActiveTab().getControl().getShell());
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite superControl = (Composite) super.createDialogArea(parent);
		
		this.setTitle(getExecutionTraceManagerName() + " Configuration");
		this.setMessage("Configure the details of the " + getExecutionTraceManagerName());
		this.getShell().setText("Configure " + getExecutionTraceManagerName());
		
		Composite control = new Composite(superControl, SWT.FILL);
		control.setLayout(new GridLayout(1,true));
		control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		getOrCreateGroups(control);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(control, "org.eclipse.epsilon.help.emc_dialogs");
		
		loadProperties();
		
		control.layout();
		control.pack();
		
		return control;
	}

	abstract protected void loadProperties();
	
	abstract protected void storeProperties();

	abstract protected void getOrCreateGroups(Composite control);

	abstract protected  String getExecutionTraceManagerName();
	
	public StringProperties getProperties(){
		return properties;
	}
	
	public void setProperties(StringProperties properties){
		this.properties = properties;
	}
	
	@Override
	protected void okPressed() {
		storeProperties();
		super.okPressed();
	}
	
	


}
