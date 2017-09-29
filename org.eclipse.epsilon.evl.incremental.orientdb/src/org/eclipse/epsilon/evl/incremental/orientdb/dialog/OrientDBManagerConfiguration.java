package org.eclipse.epsilon.evl.incremental.orientdb.dialog;

import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class OrientDBManagerConfiguration extends AbstractTraceManagerConfigurationDialog {

	public static final String PROPERTY_DB_URL = "iURL";

	public static final String PROPERTY_DB_USER = "iUser";

	public static final String PROPERTY_DB_PASSSWORD = "iPassword";

	public static final String PROPERTY_DB_CREATE = "create";

	public static final String PROPERTY_DB_POOL_MIN_SIZE = "iMin";

	public static final String PROPERTY_DB_POOL_MAX_SIZE = "iMax";

	public static final String PROPERTY_DB_CREATE_POOL = "createPool";
	
	private Text databaseUrlText;
	private Text userText;
	private Text passwordText;

	@Override
	protected String getExecutionTraceManagerName() {
		return "OrientDB Trace Manager";
	}
	
	@Override
	protected void loadProperties() {
		if (properties == null) return;
		databaseUrlText.setText(properties.getProperty(PROPERTY_DB_URL));
		// FIXME add a save credentials button (safety risk)
		userText.setText(properties.getProperty(PROPERTY_DB_USER));
		passwordText.setText(properties.getProperty(PROPERTY_DB_PASSSWORD));
	}

	@Override
	protected void storeProperties() {
		properties = new StringProperties();
		properties.put(PROPERTY_DB_URL, databaseUrlText.getText());
		properties.put(PROPERTY_DB_USER, userText.getText());
		properties.put(PROPERTY_DB_PASSSWORD, passwordText.getText());
		// FIXME Add check button for this
		properties.put(PROPERTY_DB_CREATE, true);
	}
	
	@Override
	protected void createGroups(Composite parent) {
		final Composite groupContent = createGroupContainer(parent, "DB information", 2);
		Label databaseUriLabel = new Label(groupContent, SWT.NONE);
		databaseUriLabel.setText("DataBase URI: ");
		databaseUrlText = new Text(groupContent, SWT.BORDER);
		databaseUrlText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		
		Label userLabel = new Label(groupContent, SWT.NONE);
		userLabel.setText("user: ");
		userText = new Text(groupContent, SWT.BORDER);
		userText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label passwordLabel = new Label(groupContent, SWT.NONE);
		passwordLabel.setText("password: ");
		passwordText = new Text(groupContent, SWT.BORDER);
		passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
	}

}
