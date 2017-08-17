package org.eclipse.epsilon.evl.incremental.orientdb.dialog;

import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class OrientDBManagerConfiguration extends AbstractTraceManagerConfigurationDialog {

	private Text databaseUriText;
	private Text userText;
	private Text passwordText;

	@Override
	protected void loadProperties() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createGroups(Composite parent) {
		final Composite groupContent = createGroupContainer(parent, "Connection information", 3);
		Label databaseUriLabel = new Label(groupContent, SWT.NONE);
		databaseUriLabel.setText("DataBase URI: ");
		databaseUriText = new Text(groupContent, SWT.BORDER);
		databaseUriText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		
		Label userLabel = new Label(groupContent, SWT.NONE);
		userLabel.setText("user: ");
		userText = new Text(groupContent, SWT.BORDER);
		userText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label passwordLabel = new Label(groupContent, SWT.NONE);
		passwordLabel.setText("password: ");
		passwordText = new Text(groupContent, SWT.BORDER);
		passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	}

	@Override
	protected String getExecutionTraceManagerName() {
		// TODO Auto-generated method stub
		return "OrientDB Trace Manager";
	}

	@Override
	public String[] getConfigurationParameters() {
		String[] config = {databaseUriText.getText(), userText.getText(), passwordText.getText()};
		return config;
	}

}
