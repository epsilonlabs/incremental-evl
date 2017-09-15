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
package org.eclipse.epsilon.evl.incremental.dt.launching.tabs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.evl.dt.EvlPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

public class EvlIncrementalTab extends AbstractLaunchConfigurationTab {

	@Override
	public void createControl(Composite parent) {
		final FillLayout parentLayout = new FillLayout();
		parent.setLayout(parentLayout);

		final Composite control = new Composite(parent, SWT.NONE);
		setControl(control);
		control.setLayout(new GridLayout(1, false));
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(control, "org.eclipse.epsilon.help.egl_generated_text_tab");
		
		createIncrementalGroup(control);
		
		control.setBounds(0, 0, 300, 300);
		control.layout();
		control.pack();
		
	}

	@Override
	public Image getImage() {
		return EvlPlugin.getDefault().createImage("icons/incremental.gif");
	}

	@Override
	public String getName() {
		return "Incremental";
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			initializeIncrementalGroupFrom(configuration);			
		} catch (CoreException e) {
			LogUtil.log("Error encountered whilst attempting to restore selection of default formatters from launch configuration", e);
		}
		
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		saveIncrementalOptionsTo(configuration);
		
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		
	}

	private void createIncrementalGroup(Composite control) {
		// TODO Auto-generated method stub
		
	}

	private void initializeIncrementalGroupFrom(ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}
	
	private void saveIncrementalOptionsTo(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		
	}
	
	


}
