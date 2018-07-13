package org.eclipse.epsilon.evl.incremental.dt.launching.tabs;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.epsilon.evl.dt.launching.tabs.EvlModuleConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class IncrementalEvlModuleConfiguration extends EvlModuleConfiguration {

	protected Label hostLabel;
	protected Text hostText;

	@Override
	public Composite createModuleConfigurationGroup(Composite parent) {
		final Composite group = super.createModuleConfigurationGroup(parent);
		hostLabel =new Label(group, SWT.SIMPLE);
		hostLabel.setText("DB host");
		hostText = new Text(group, SWT.BORDER);
		return group;
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		// TODO Implement IncrementalEvlModuleConfiguration.initializeFrom
//		throw new UnsupportedOperationException(
//				"Unimplemented Method    IncrementalEvlModuleConfiguration.initializeFrom invoked.");
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
//		// TODO Implement IncrementalEvlModuleConfiguration.performApply
//		throw new UnsupportedOperationException(
//				"Unimplemented Method    IncrementalEvlModuleConfiguration.performApply invoked.");
	}

}
