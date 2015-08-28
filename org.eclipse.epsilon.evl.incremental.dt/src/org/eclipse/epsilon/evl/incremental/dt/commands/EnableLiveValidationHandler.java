package org.eclipse.epsilon.evl.incremental.dt.commands;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class EnableLiveValidationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Command command = event.getCommand();
		boolean oldState = HandlerUtil.toggleCommandState(command);
		
		try {
			if (oldState) {

			} else {
				attach(event);
			}

			System.out.println(oldState);
		} catch (Exception e) {
			throw new ExecutionException("", e);
		}
		return null;
	}

	private void attach(ExecutionEvent event) throws Exception {
		// Retrieve the current window
		final IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindow(event);
		if (window == null) {
			return;
		}

		// Get the current editor part
		final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
		if (!(editorPart instanceof IEditingDomainProvider)) {
			return;
		}

		// Select the evl file to use
		final IEditingDomainProvider edp = (IEditingDomainProvider) editorPart;
		final EvlSelectionDialog dialog = new EvlSelectionDialog(
				window.getShell());

		IFile evlFile = null;
		if (dialog.open() == Window.OK) {
			for (Object object : dialog.getResult()) {
				if (object instanceof IFile) {
					evlFile = (IFile) object;
				}
			}
		}

		final TraceEvlModule module = new TraceEvlModule();
		module.reset();
		module.parse(evlFile.getLocationURI());

		final ModelRepository modelRepository = module.getContext()
				.getModelRepository();

		for (Resource resource : edp.getEditingDomain().getResourceSet()
				.getResources()) {			
//			final EPackage ePackage = ((EClassifier)resource.getContents().get(0).eClass()).getEPackage();			
			InMemoryEmfModel inMemoryEmfModel = new InMemoryEmfModel(resource);
//			final EmfModel emfModel = new EmfModel();
//			System.out.println(resource.getURI().);
//			File f = new File(resource.getURI().toFileString());
//			EmfModel emfModel = EmfModelFactory.getInstance().createEmfModel(resource.getURI().toString(), f, ePackage);

//			emfModel.setResource(resource);
			
			modelRepository.addModel(inMemoryEmfModel);
		}

		module.execute();
		module.getContext().attachChangeListeners();

		EditorRegistry.REGISTRY.put(edp, module);
	}
}
