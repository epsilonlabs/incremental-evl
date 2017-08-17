package org.eclipse.epsilon.evl.incremental.dt.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.ExecutionTraceManagerSelectionDialog;
import org.eclipse.epsilon.common.incremental.dt.launching.extensions.ExecutionTraceManagerExtension;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.emc.emf.incremental.IncrementalInMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.EolInternalException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
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
		final IncrementalEvlModule module = EditorRegistry.REGISTRY.remove(edp);
		final ModelRepository modelRepository = module.getContext()
				.getModelRepository();
		for (IModel m : modelRepository.getModels()) {
			if (m instanceof IIncrementalModel) {
				((IIncrementalModel) m).setDeliver(false);
			}
		}
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
		
		final ExecutionTraceManagerSelectionDialog managerDialog = new ExecutionTraceManagerSelectionDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell());
		
		ExecutionTraceManagerExtension managerExtension = null;
		if (managerDialog.open() == Window.OK) {
			managerExtension = managerDialog.getExecutionTraceManager();
		}
		else {
			// Raise exception, we can not run without a trace manager
		}
		// Configure the manager
		AbstractTraceManagerConfigurationDialog configDialog = null;
		try {
			configDialog = managerExtension.createConfigurationDialog();
		} catch (CoreException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (configDialog.open() == Window.OK) {
			// No info to get from this dialog
		}
		else {
			// Raise exception, we can not run without a trace manager configuration
		}
		
		final IncrementalEvlModule module = new IncrementalEvlModule();
		
		IExecutionTraceManager traceManager = null;
		try {
			traceManager = managerExtension.createTraceManager();
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		traceManager.configure(configDialog.getConfigurationParameters());
		module.setExecutionTraceManager(traceManager);
		
		// View not working
		//module.setUnsatisfiedConstraintFixer(new ValidationViewFixer(null));
		//module.reset();
		try {
			module.parse(evlFile.getLocationURI());
		} catch (Exception e) {
			throw new EolInternalException(e);
		}

		final ModelRepository modelRepository = module.getContext()
				.getModelRepository();
		
		// FIXME I think we only want to attach to the model opened in the editor
		for (Resource resource : edp.getEditingDomain().getResourceSet()
				.getResources()) {						
			IncrementalInMemoryEmfModel incrementalInMemoryEmfModel = new IncrementalInMemoryEmfModel();
			InMemoryEmfModel delegate = new InMemoryEmfModel(resource);
			incrementalInMemoryEmfModel.setDelegate(delegate);
			incrementalInMemoryEmfModel.getModules().add(module);
			modelRepository.addModel(incrementalInMemoryEmfModel );
		}

		module.execute();
		// Enable notifications after the initial execution
		for (IModel m : modelRepository.getModels()) {
			if (m instanceof IIncrementalModel) {
				((IIncrementalModel) m).setDeliver(true);
			}
		}
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
