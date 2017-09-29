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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.common.dt.EpsilonCommonsPlugin;
import org.eclipse.epsilon.common.dt.util.ListContentProvider;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.common.incremental.dt.launching.extensions.ExecutionTraceManagerExtension;
import org.eclipse.epsilon.common.incremental.dt.launching.tabs.ExecutionTraceManagerLabelProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Provides a dialog to select a TraceManager form the registered ones.
 * 
 * @author Horacio Hoyos
 *
 */
public class ExecutionTraceManagerSelectionDialog extends TitleAreaDialog implements ISelectionChangedListener {

	private List<ExecutionTraceManagerExtension> traceManagers;
	private ExecutionTraceManagerExtension traceManagerExtension;

	public ExecutionTraceManagerSelectionDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected void setShellStyle(int newShellStyle) {
	   super.setShellStyle(newShellStyle | SWT.RESIZE);
	}
	
	@Override
	protected Rectangle getConstrainedShellBounds(Rectangle preferredSize) {
		preferredSize.height = 350;
		return super.getConstrainedShellBounds(preferredSize);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite dialogArea = (Composite) super.createDialogArea(parent);
		GridData dialogAreaData = new GridData(GridData.FILL_BOTH);
		dialogArea.setLayoutData(dialogAreaData);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "org.eclipse.epsilon.help.emc_dialogs");
		
		this.setTitle("Execution Trace Manager Selection");
		this.setMessage("Select the execution trace manager to use");
		this.getShell().setText("Select execution trace manager");
		this.getShell().setImage(EpsilonCommonsPlugin.getDefault().createImage("icons/model.gif"));
		
		//GridLayout controlLayout = new GridLayout(1, false);
		//control.setLayout(controlLayout);
		
		Composite control = new Composite(dialogArea, SWT.NONE);
		GridLayout controlLayout = new GridLayout(1, false);
		control.setLayout(controlLayout);
		GridData controlData = new GridData(GridData.FILL_BOTH);
		//controlData.horizontalIndent = 10;
		//controlData.verticalIndent = 10;
		control.setLayoutData(controlData);
		
		findTraceManagers();
		
		TableViewer traceManagersViewer = new TableViewer(control, SWT.BORDER);
		
		GridData viewerData = new GridData(GridData.FILL_BOTH);
		traceManagersViewer.getControl().setLayoutData(viewerData);
		traceManagersViewer.getControl().setFocus();
		
		Button showAllButton = new Button(control, SWT.CHECK);
		GridData showAllButtonGridData = new GridData(GridData.FILL_HORIZONTAL);
		showAllButtonGridData.horizontalAlignment = SWT.END;
		showAllButton.setLayoutData(showAllButtonGridData);
		showAllButton.setText("Show all trace model types");
		showAllButton.setSelection(false);
		
		//traceManagersViewer.getControl().setLayoutData(viewerData);
		
		traceManagersViewer.setContentProvider(new ListContentProvider());
		traceManagersViewer.setInput(getStableExecutionTraceManagerExtensions());
		traceManagersViewer.addSelectionChangedListener(this);
		traceManagersViewer.setLabelProvider(new ExecutionTraceManagerLabelProvider());
		traceManagersViewer.getControl().addMouseListener(new MouseListener() {

			public void mouseDoubleClick(MouseEvent e) {
				ExecutionTraceManagerSelectionDialog.this.traceManagerExtension = (ExecutionTraceManagerExtension)((IStructuredSelection)traceManagersViewer.getSelection()).getFirstElement();
				if (ExecutionTraceManagerSelectionDialog.this.traceManagerExtension != null) {
					ExecutionTraceManagerSelectionDialog.this.close();
				}
			}

			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		traceManagersViewer.refresh();
		

		showAllButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event event) {
				
				List<ExecutionTraceManagerExtension> filtered;
				
				if (showAllButton.getSelection()) {
					filtered = traceManagers;
				}
				else {
					filtered = getStableExecutionTraceManagerExtensions();
				}
				
				traceManagersViewer.setInput(filtered);
				traceManagersViewer.refresh();
				
			}
		});
		
		return control;
	}
	
	private List<ExecutionTraceManagerExtension> getStableExecutionTraceManagerExtensions() {
		ArrayList<ExecutionTraceManagerExtension> filtered = new ArrayList<ExecutionTraceManagerExtension>();
		for (ExecutionTraceManagerExtension ext : traceManagers) {
			if (ext.isStable()) {
				filtered.add(ext);
			}
		}
		return filtered;
	}
	
	private void findTraceManagers() {
		try {
			traceManagers = ExecutionTraceManagerExtension.allTraceManagers();
		}
		catch (Exception ex) {
			LogUtil.log(ex);
		}
	}

	public void selectionChanged(SelectionChangedEvent event) {
		this.traceManagerExtension = (ExecutionTraceManagerExtension)((IStructuredSelection)event.getSelection()).getFirstElement();
	}
	
	public ExecutionTraceManagerExtension getExecutionTraceManagerExtension(){
		return traceManagerExtension;
	}

}
