package org.eclipse.epsilon.evl.incremental.dt.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.epsilon.evl.incremental.dt.EvlRegistry;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class EvlSelectionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO: null checks
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		
		if (window == null) {
			return null;
		}
		
		final EvlSelectionDialog dialog = new EvlSelectionDialog(window.getShell());

		IEditingDomainProvider edp = (IEditingDomainProvider) HandlerUtil.getActiveEditor(event);
		ResourceSet resourceSet = edp.getEditingDomain().getResourceSet();
		
		if (dialog.open() == Window.OK) {
			for (Object object : dialog.getResult()) {
				if (object instanceof IFile) {
					EvlRegistry.REGISTRY.put(resourceSet, (IFile) object);
				}
			}
		}

		// Method must return null
		return null;
	}

}
