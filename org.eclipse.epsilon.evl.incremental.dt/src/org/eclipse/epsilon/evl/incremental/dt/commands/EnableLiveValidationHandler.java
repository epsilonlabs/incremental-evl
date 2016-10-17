package org.eclipse.epsilon.evl.incremental.dt.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.EolInternalException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.evl.dt.views.ValidationViewFixer;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class EnableLiveValidationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Command command = event.getCommand();
		boolean oldState = HandlerUtil.toggleCommandState(command);
		
//		try {
			IEditingDomainProvider edp = getCurrentEDP(event);
			if (edp == null) {
				return null;
			}
			
			if (oldState) {
				detach(edp, event);
			} else {
				try {
					attach(edp, event);
				} catch (EolRuntimeException e) {
					throw new ExecutionException(e.getMessage());
				}
			}
//		} catch (Exception e) {
//			throw new ExecutionException("", e);
//		}
		return null;
	}
	
	public void detach(IEditingDomainProvider edp, ExecutionEvent event) {
		final TraceEvlModule module = EditorRegistry.REGISTRY.remove(edp);
		module.reset();
	}

	public void attach(IEditingDomainProvider edp, ExecutionEvent event) throws EolRuntimeException {

		final EvlSelectionDialog dialog = new EvlSelectionDialog(
				HandlerUtil.getActiveWorkbenchWindow(event).getShell());

		IFile evlFile = null;
		if (dialog.open() == Window.OK) {
			for (Object object : dialog.getResult()) {
				if (object instanceof IFile) {
					evlFile = (IFile) object;
				}
			}
		}

		final TraceEvlModule module = new TraceEvlModule();
		// View not working
		//module.setUnsatisfiedConstraintFixer(new ValidationViewFixer(null));
		module.reset();
		try {
			module.parse(evlFile.getLocationURI());
		} catch (Exception e) {
			throw new EolInternalException(e);
		}

		final ModelRepository modelRepository = module.getContext()
				.getModelRepository();

		for (Resource resource : edp.getEditingDomain().getResourceSet()
				.getResources()) {						
			InMemoryEmfModel inMemoryEmfModel = new InMemoryEmfModel(resource);
			modelRepository.addModel(inMemoryEmfModel);
		}

		module.execute();
		module.getContext().attachChangeListeners();

		EditorRegistry.REGISTRY.put(edp, module);
	}
	
	public IEditingDomainProvider getCurrentEDP(ExecutionEvent event) {
		final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
		if (!(editorPart instanceof IEditingDomainProvider)) {
			return null;
		}

		return (IEditingDomainProvider) editorPart;
	}
}
