package org.eclipse.epsilon.evl.incremental.dt.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class EvlSelectionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		EvlSelectionDialog dialog = new EvlSelectionDialog(window.getShell());

		// LinkedList<Resource> resources = new LinkedList<Resource>()

		if (dialog.open() == Window.OK) {
			for (Object object : dialog.getResult()) {
				if (object instanceof IFile) {
//					EvlRegistry.REGISTRY.
				}
			}
		}

		return null;
	}

}
