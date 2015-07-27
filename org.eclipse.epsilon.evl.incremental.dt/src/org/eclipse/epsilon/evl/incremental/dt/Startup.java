package org.eclipse.epsilon.evl.incremental.dt;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class Startup implements IStartup {

	@Override
	public void earlyStartup() {
		 this.setupEditorListener();
	}

	private void setupEditorListener() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchPage page = null;
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		if (window != null) {
			page = window.getActivePage();
		}

		// If the page is still null loop through until a page can be found
		if (page == null) {
			IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
			for (IWorkbenchWindow w : windows) {
				if (w != null) {
					window = w;
					page = window.getActivePage();
				}
				if (page != null) {
					break;
				}
			}
		}

		// Attach the page listener;
		page.addPartListener(new EditorListener());
	}

}
