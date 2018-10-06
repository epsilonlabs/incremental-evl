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
package org.eclipse.epsilon.evl.incremental.dt.launching;

import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.epsilon.evl.dt.launching.EvlLaunchConfigurationTabGroup;
import org.eclipse.epsilon.evl.dt.launching.tabs.EvlAdvancedConfigurationTab;
import org.eclipse.epsilon.evl.incremental.dt.launching.tabs.EvlIncrementalTab;

/**
 * The Class EvlIncrementalLaunchConfigurationTabGroup.
 */
public class EvlIncrementalLaunchConfigurationTabGroup extends EvlLaunchConfigurationTabGroup {
	
	
	@Override
	public ILaunchConfigurationTab[] getOtherConfigurationTabs() {
		return new ILaunchConfigurationTab[]{new EvlAdvancedConfigurationTab(), new EvlIncrementalTab()};
	}
}
