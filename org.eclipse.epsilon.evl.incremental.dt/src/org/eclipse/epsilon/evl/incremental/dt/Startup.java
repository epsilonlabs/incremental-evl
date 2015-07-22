package org.eclipse.epsilon.evl.incremental.dt;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class Startup implements IStartup {

	private static final String EXTENSION_POINT = "org.eclipse.epsilon.evl.emf.validation";
	private static final String NAMESPACE_URI_ATTR = "namespaceURI";
	private static final String CONSTRAINTS_ATTR = "constraints";

	@Override
	public void earlyStartup() {
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

	public EPackage getEPackage() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = registry
				.getConfigurationElementsFor(EXTENSION_POINT);

		for (IConfigurationElement ce : configElements) {
			String ePackageUri = ce.getAttribute(NAMESPACE_URI_ATTR);
			EPackage ePackage = EPackage.Registry.INSTANCE
					.getEPackage(ePackageUri);
			if (ePackage != null) {
				return ePackage;
			}
		}

		return null;
	}

}
