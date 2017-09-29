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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.epsilon.common.dt.EpsilonCommonsPlugin;
import org.eclipse.epsilon.common.dt.launching.extensions.ModelTypeExtension;
import org.eclipse.epsilon.common.dt.util.ListContentProvider;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.common.dt.util.StringList;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.ExecutionTraceManagerSelectionDialog;
import org.eclipse.epsilon.common.incremental.dt.launching.extensions.ExecutionTraceManagerExtension;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.evl.dt.EvlPlugin;
import org.eclipse.jface.viewers.AbstractTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

public class EvlIncrementalTab extends AbstractLaunchConfigurationTab {

	private class AddModelListener implements Listener {

		@Override
		public void handleEvent(Event event) {
			ExecutionTraceManagerSelectionDialog dialog = new ExecutionTraceManagerSelectionDialog(getShell());
			dialog.setBlockOnOpen(true);
			dialog.open();
			
			if (dialog.getReturnCode() == Window.OK){
				
				ExecutionTraceManagerExtension modelType = dialog.getExecutionTraceManagerExtension();
				
				if (modelType == null) return;
				
				try {
					AbstractTraceManagerConfigurationDialog modelConfigurationDialog = modelType.createConfigurationDialog();
					modelConfigurationDialog.setBlockOnOpen(true);
					modelConfigurationDialog.open();
					if (modelConfigurationDialog.getReturnCode() == Window.OK){
						models.add(modelConfigurationDialog.getProperties().toString());
						modelViewer.refresh(true);
						canSave();
						updateLaunchConfigurationDialog();
					}
				} catch (Exception e) {
					//PDE.logException(e);
					e.printStackTrace();
				}
				finally {
					toggleButtons();
				}
			}

		}

	}

	private class EditModelListener implements Listener {

		@Override
		public void handleEvent(Event event) {
			IStructuredSelection selection = (IStructuredSelection) modelViewer.getSelection();
			if (selection.getFirstElement() == null) return;
			
			StringProperties properties = new StringProperties();
			properties.load(selection.getFirstElement().toString());
			
			ExecutionTraceManagerExtension modelType = ExecutionTraceManagerExtension.forType(properties.getProperty("type"));
			
			AbstractTraceManagerConfigurationDialog dialog = null;
			try {
				dialog = modelType.createConfigurationDialog();
			} catch (Exception e) {
				LogUtil.log(e);
				return;
			}
			dialog.setBlockOnOpen(true);
			
			dialog.setProperties(properties);
			dialog.open();
			if (dialog.getReturnCode() == Window.OK){
				int index = models.indexOf(selection.getFirstElement());
				models.add(index, dialog.getProperties().toString());
				models.remove(index + 1);
				modelViewer.refresh(true);
				canSave();
				updateLaunchConfigurationDialog();				
			}
			
		}

	}

	private class ModelLabelProvider implements ILabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Image getImage(Object element) {
			StringProperties properties = new StringProperties();
			properties.load(element.toString());
			ModelTypeExtension modelTypeExtension = ModelTypeExtension.forType(properties.getProperty("type"));
			if (modelTypeExtension != null) return modelTypeExtension.getImage();
			else return EpsilonCommonsPlugin.getDefault().createImage("icons/unknown.gif");
		}

		@Override
		public String getText(Object element) {
			StringProperties properties = new StringProperties();
			properties.load(element.toString());
			return properties.getProperty("name");
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}

	}

	private class RemoveModelListener implements Listener {

		@Override
		public void handleEvent(Event event) {
			IStructuredSelection selection = (IStructuredSelection) modelViewer.getSelection();
			if (selection.getFirstElement() == null) return;
			int index = models.indexOf(selection.getFirstElement());
			models.remove(index);
			modelViewer.refresh(true);
			toggleButtons();
			canSave();
			updateLaunchConfigurationDialog();

		}

	}

	private TableViewer modelViewer;
	private List<String> models;
	private AbstractTableViewer modelControls;
	private Button addButton;
	private Button editButton;
	private Button removeButton;

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
			LogUtil.log("Error encountered whilst attempting to restore settings from launch configuration", e);
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

	private Button createButton(Composite parent, String text) {
		Button button = new Button(parent, SWT.NONE);
		button.setText(text);
		button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		modelControls.add(button);
		
		return button;
	}

	private void createIncrementalGroup(Composite parent) {
		Composite topControl = new Composite(parent, SWT.FILL);
		GridLayout topControlLayout = new GridLayout(2, false);
		topControl.setLayout(topControlLayout);
		
		GridData topControlData = new GridData(GridData.FILL_BOTH);
		topControl.setLayoutData(topControlData);

		modelViewer = new TableViewer(topControl, SWT.BORDER);
		modelViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				new EditModelListener().handleEvent(null);
			}
		});
		
		modelViewer.setContentProvider(new ListContentProvider());
		modelViewer.setLabelProvider(new ModelLabelProvider());
		modelViewer.setInput(models);
		
		
		GridData buttonsData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);

		GridData viewerData = new GridData(GridData.FILL_BOTH);
		modelViewer.getControl().setLayoutData(viewerData);
		modelViewer.getControl().setFocus();

		Composite buttons = new Composite(topControl, SWT.FILL | SWT.TOP);
		buttons.setLayoutData(buttonsData);

		GridLayout buttonsLayout = new GridLayout(1, true);
		buttons.setLayout(buttonsLayout);

		addButton = createButton(buttons, "Add...");
		addButton.addListener(SWT.Selection, new AddModelListener());
		editButton = createButton(buttons, "Edit...");
		editButton.addListener(SWT.Selection, new EditModelListener());
		removeButton = createButton(buttons, "Remove");
		removeButton.addListener(SWT.Selection, new RemoveModelListener());
		toggleButtons();
		
	}

	private void initializeIncrementalGroupFrom(ILaunchConfiguration configuration) throws CoreException {
		models = new StringList(configuration.getAttribute("models", new StringList()));
		toggleButtons();
		modelViewer.setInput(models);
		modelViewer.refresh(true);
		canSave();
		updateLaunchConfigurationDialog();
	}
	
	private void saveIncrementalOptionsTo(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		
	}
	
	private void toggleButtons() {
		if (models.isEmpty()) {
			addButton.setEnabled(true);
			removeButton.setEnabled(false);
			editButton.setEnabled(false);
		}
		else {
			addButton.setEnabled(false);
			removeButton.setEnabled(true);
			editButton.setEnabled(true);
		}
	}
	
	


}
