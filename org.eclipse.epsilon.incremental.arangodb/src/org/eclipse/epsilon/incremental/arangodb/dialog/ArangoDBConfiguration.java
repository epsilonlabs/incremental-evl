package org.eclipse.epsilon.incremental.arangodb.dialog;


import java.util.Arrays;

import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.arangodb.Protocol;



public class ArangoDBConfiguration extends AbstractTraceManagerConfigurationDialog {
	
	/**
	 * Use default configuration
	 */
	public static final String DB_USE_DEFAULT = "useDefault";
	
	// Arango DB configuration
	public static final String DB_NAME = "name";
	
	/**
	 * ArangoDB host. Default 127.0.0.1
	 */
	public static final String DB_HOST = "host";
	
	/**
	 * ArangoDB port. Default 8529
	 */
	public static final String DB_PORT = "port";
	
	/**
	 * Socket connect timeout(millisecond). Default 0
	 */
	public static final String DB_TIMEOUT = "timeout";
	
	/**
	 * Basic Authentication User
	 */
	public static final String DB_USER = "user";
	
	/**
	 * Basic Authentication Password
	 */
	public static final String DB_PASSWORD = "password";
	
	/**
	 * Use SSL connection. Default False
	 */
	public static final String DB_USE_SSL = "use_ssl";
	
	/**
	 * VelocyStream Chunk content-size(bytes). Default 3000
	 */
	public static final String DB_CHUNK_SIZE = "chunkSize";

	public static final String DB_MAX_CONNECTIONS = "maxConnections";

	public static final String DB_PROTOCOL = "protocol";
	
	/**
	 * The name of the graph in the DB
	 */
	public static final String DB_GRAPH_NAME = "graphName";

	/**
	 * Whether the vertyex and edge collections must be created 
	 */
	public static final String DB_SETUP_SCHEMA = "setupSchema";
	
	/**
	 * Wheter a new graph is created in the DB.
	 */
	public static final String DB_CREATE_GRAPH = "createGraph";
	
	private Text dbNameText;
	private Text dbHostText;
	private Spinner dbPortSpinner;
	private Spinner dbTimeoutSpinner;
	private Text dbUserText;
	private Text dbPasswordText;
	private Button dbUseSslCheck;
	private Spinner dbChunkSizeSpinner;
	private Button dbUseDefaultCheck;
	private Spinner dbMaxConnectionsSpinner;
	private Combo dbProtocolCombo;
	private Button dbSetUpSchemaCheck;
	private Text dbGraphName;
	private Button dbCreateGraphCheck;

	private Composite configContent;
	
	
		
	@Override
	protected void loadProperties() {
		if (properties == null) return;
		dbNameText.setText(properties.getProperty(DB_NAME));
		dbHostText.setText(properties.getProperty(DB_HOST, "127.0.0.1"));
		dbPortSpinner.setSelection(properties.getIntegerProperty(DB_PORT, 8529));
		dbTimeoutSpinner.setSelection(properties.getIntegerProperty(DB_TIMEOUT, 0));
		dbUserText.setText(properties.getProperty(DB_USER, ""));
		dbPasswordText.setText(properties.getProperty(DB_PASSWORD, ""));
		dbUseSslCheck.setSelection(properties.getBooleanProperty(DB_USE_SSL, false));
		dbChunkSizeSpinner.setSelection(properties.getIntegerProperty(DB_CHUNK_SIZE, 30000));
		dbUseDefaultCheck.setSelection(properties.getBooleanProperty(DB_USE_DEFAULT, true));
		if (dbUseDefaultCheck.getSelection()) {
			configContent.setEnabled(false);
		}
		dbMaxConnectionsSpinner.setSelection(properties.getIntegerProperty(DB_MAX_CONNECTIONS, 1));
		String protocol = properties.getProperty(DB_PROTOCOL, Protocol.VST.toString());
		dbProtocolCombo.select(Arrays.asList(Protocol.values()).indexOf(Protocol.valueOf(protocol)));
		dbSetUpSchemaCheck.setSelection(properties.getBooleanProperty(DB_SETUP_SCHEMA, false));
		dbGraphName.setText(properties.getProperty(DB_GRAPH_NAME, "offline"));
		dbCreateGraphCheck.setSelection(properties.getBooleanProperty(DB_CREATE_GRAPH, false));
	}

	@Override
	protected void storeProperties() {
		properties = new StringProperties();
		properties.put(DB_NAME, dbNameText.getText());
		properties.put(DB_HOST, dbHostText.getText());
		properties.put(DB_PORT, dbPortSpinner.getSelection());
		properties.put(DB_TIMEOUT, dbTimeoutSpinner.getSelection());
		properties.put(DB_USER, dbUserText.getText());
		properties.put(DB_PASSWORD, dbPasswordText.getText());
		properties.put(DB_USE_SSL, dbUseSslCheck.getSelection());
		properties.put(DB_CHUNK_SIZE, dbChunkSizeSpinner.getSelection());
		properties.put(DB_USE_DEFAULT, dbUseDefaultCheck.getSelection());
		properties.put(DB_MAX_CONNECTIONS, dbMaxConnectionsSpinner.getSelection());
		properties.put(DB_PROTOCOL, Protocol.values()[dbProtocolCombo.getSelectionIndex()].toString());
		properties.put(DB_CREATE_GRAPH, dbCreateGraphCheck.getSelection());
		properties.put(DB_SETUP_SCHEMA, dbSetUpSchemaCheck.getSelection());
		properties.put(DB_GRAPH_NAME, dbGraphName.getText());
		
	}

	@Override
	protected String getExecutionTraceManagerName() {
		return "ArangoDB Trace Manager";
	}

	@Override
	protected void createGroups(Composite parent) {
		final Composite groupContent = createGroupContainer(parent, "DB information", 2);
		Label dbNameLabel = new Label(groupContent, SWT.NONE);
		dbNameLabel.setText("Database name:");
		dbNameText = new Text(groupContent, SWT.BORDER);
		dbNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbNameText.setText("IncrementalEVL");
		
		Label defaultLabel = new Label(groupContent, SWT.NONE);
		defaultLabel.setText("Use default connection values:");
		dbUseDefaultCheck = new Button(groupContent, SWT.CHECK);
		dbUseDefaultCheck.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				//configContent.setEnabled(!dbUseDefaultCheck.getSelection());
				recursiveSetEnabled(configContent, !dbUseDefaultCheck.getSelection());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		dbUseDefaultCheck.setLayoutData(new GridData(GridData. FILL_HORIZONTAL));
		dbUseDefaultCheck.setSelection(true);
		
		configContent = createGroupContainer(groupContent, "Connection Configuration", 2);
		
		Label hostLabel = new Label(configContent, SWT.NONE);
		hostLabel.setText("Host:");
		dbHostText = new Text(configContent, SWT.BORDER);
		dbHostText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbHostText.setText("127.0.0.1");
		
		Label portLabel = new Label(configContent, SWT.NONE);
		portLabel.setText("Port:");
		dbPortSpinner = new Spinner(configContent, SWT.BORDER);
		dbPortSpinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbPortSpinner.setValues(8529, 1, 65535, 0, 1, 100);
		
		Label userLabel = new Label(configContent, SWT.NONE);
		userLabel.setText("User:");
		dbUserText = new Text(configContent, SWT.BORDER);
		dbUserText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label passwordLabel = new Label(configContent, SWT.NONE);
		passwordLabel.setText("Password:");
		dbPasswordText = new Text(configContent, SWT.BORDER);
		dbPasswordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label timeoutLabel = new Label(configContent, SWT.NONE);
		timeoutLabel.setText("Timeout (millisecond):");
		dbTimeoutSpinner = new Spinner(configContent, SWT.BORDER);
		dbTimeoutSpinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbTimeoutSpinner.setValues(0, 0, 100000, 0, 1, 1000);
		
		Label sslLabel = new Label(configContent, SWT.NONE);
		sslLabel.setText("Use SSL connection:");
		dbUseSslCheck = new Button(configContent, SWT.CHECK);
		dbUseSslCheck.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label chunkLabel = new Label(configContent, SWT.NONE);
		chunkLabel.setText("VelocyStream Chunk content-size(bytes):");
		dbChunkSizeSpinner = new Spinner(configContent, SWT.BORDER);
		dbChunkSizeSpinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbChunkSizeSpinner.setValues(30000, 1, 100000, 0, 1, 1000);
		
		Label maxConLabel = new Label(configContent, SWT.NONE);
		maxConLabel.setText("Max number connections:");
		dbMaxConnectionsSpinner = new Spinner(configContent, SWT.BORDER);
		dbMaxConnectionsSpinner.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbMaxConnectionsSpinner.setValues(1, 1, 100, 0, 1, 5);
		
		Label protocolLabel = new Label(configContent, SWT.NONE);
		protocolLabel.setText("Protocol:");
		dbProtocolCombo = new Combo(configContent, SWT.BORDER);
		dbProtocolCombo.setItems(Protocol.HTTP_JSON.name(), Protocol.HTTP_VPACK.name(), Protocol.VST.name());
		dbProtocolCombo.select(2);
		
		dbSetUpSchemaCheck = new Button(configContent, SWT.CHECK);
		dbSetUpSchemaCheck.setText("Setup DB schema:");
		dbSetUpSchemaCheck.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbSetUpSchemaCheck.setSelection(false);
		
		dbCreateGraphCheck = new Button(configContent, SWT.CHECK);
		dbCreateGraphCheck.setText("Create Graph:");
		dbCreateGraphCheck.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbCreateGraphCheck.setSelection(false);
		
		Label graphLabel = new Label(configContent, SWT.NONE);
		graphLabel.setText("GraphName:");
		dbGraphName = new Text(configContent, SWT.BORDER);
		dbGraphName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dbGraphName.setText("evlScript");
		recursiveSetEnabled(configContent, false);
		
	}
	
	private void recursiveSetEnabled(Control ctrl, boolean enabled) {
		if (ctrl instanceof Composite) {
			Composite comp = (Composite) ctrl;
			for (Control c : comp.getChildren()) {
				recursiveSetEnabled(c, enabled);
			}
		}
		ctrl.setEnabled(enabled);
	}

}
