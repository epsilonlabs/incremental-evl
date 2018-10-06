package org.eclipse.epsilon.evl.incremental.dt.commands;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.epsilon.base.incremental.execute.IExecutionTraceManager;
import org.eclipse.epsilon.common.exceptions.EolInternalException;
import org.eclipse.epsilon.common.exceptions.EolRuntimeException;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.AbstractTraceManagerConfigurationDialog;
import org.eclipse.epsilon.common.incremental.dt.launching.dialogs.ExecutionTraceManagerSelectionDialog;
import org.eclipse.epsilon.common.incremental.dt.launching.extensions.ExecutionTraceManagerExtension;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.emc.emf.incremental.IncrementalInMemoryEmfModel;
import org.eclipse.epsilon.eol.incremental.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.evl.IncrementalEvlModule;
import org.eclipse.epsilon.evl.dt.views.ValidationViewFixer;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

/**
 * Handle the Toggle menu, based on https://eclipsesource.com/blogs/2009/01/15/toggling-a-command-contribution/
 * 
 * @author Horacio Hoyos
 *
 */
public class EnableLiveValidationHandler extends AbstractHandler implements IElementUpdater {
	
	/**
	 * The id of the state element of the EVL command in the extension point.
	 */
    private static final String LIVE_TOGGLE = "org.eclipse.epsilon.evl.incremental.dt.live";
    
	private String commandId;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
//FIXME We have to be more carefull with exceptions and make sure the db connection is correctly closed
        Command command = event.getCommand();
        this.commandId = event.getCommand().getId();
        IEditingDomainProvider edp = getCurrentEDP(event);
        if (edp == null) {
            return null;
        }
        State state = event.getCommand().getState(LIVE_TOGGLE);
        if (state == null)
            throw new ExecutionException("The command does not have a toggle state"); //$NON-NLS-1$
        if (!(state.getValue() instanceof Boolean))
	        throw new ExecutionException("The command's toggle state doesn't contain a boolean value"); //$NON-NLS-1$

        ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		boolean oldValue = ((Boolean) state.getValue()).booleanValue();
		if (oldValue) {
			try {
				detach(edp, event);
			} catch (EolIncrementalExecutionException e) {
				throw new ExecutionException("Failed to trace the execution.", e);
			}
			state.setValue(false);
			HandlerUtil.toggleCommandState(command);
		} else {
			boolean success = false;
			try {
				success = attach(edp, event);
			} catch (EolRuntimeException e) {
				throw new ExecutionException(e.getMessage());
			}
			finally {
				if (success) {
					state.setValue(true);
//					HandlerUtil.toggleCommandState(command);
				}
			}
		}
		commandService.refreshElements(event.getCommand().getId(), null);
		return null;
	}

	public boolean attach(IEditingDomainProvider edp, ExecutionEvent event) throws EolRuntimeException {

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
		else {
			return false;
		}
		
		final ExecutionTraceManagerSelectionDialog managerDialog = new ExecutionTraceManagerSelectionDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell());
		ExecutionTraceManagerExtension managerExtension = null;
		if (managerDialog.open() == Window.OK) {
			managerExtension = managerDialog.getExecutionTraceManagerExtension();
		}
		else {
			return false;
		}
		// Configure the manager
		StringProperties properties = null;
		try {
			final AbstractTraceManagerConfigurationDialog  managerConfigurationDialog = managerExtension.createConfigurationDialog();
			managerConfigurationDialog.setBlockOnOpen(true);
			managerConfigurationDialog.open();
			if (managerConfigurationDialog.getReturnCode() == Window.OK){
				properties = managerConfigurationDialog.getProperties();
				// We are online
				properties.put(IIncrementalModule.ONLINE_MODE, true);
			}
			else {
//				managerConfigurationDialog.close();
				return false;
			}
		} catch (CoreException e2) {
			throw new EolRuntimeException(e2.getMessage());
		}
		
		
		final IncrementalEvlModule module = new IncrementalEvlModule();
		IExecutionTraceManager traceManager = null;
		try {
			traceManager = managerExtension.createTraceManager();
		} catch (CoreException e1) {
			throw new EolRuntimeException(e1.getMessage());
		}
		traceManager.configure(properties);
		module.setExecutionTraceManager(traceManager);
		
		// View not working?
		module.setUnsatisfiedConstraintFixer(new ValidationViewFixer(null));
		//module.reset();
		// It seems this is not executed in a UI thread, so the ValidationViewFixer never starts
		//ILaunchConfiguration configuration = null;
		//module.setUnsatisfiedConstraintFixer(new ValidationViewFixer(configuration));
		// FIXME Do we need optimised constraints?
		module.setOptimizeConstraints(false);
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
		EditorRegistry.REGISTRY.put(edp, module);
		return true;
	}
	
	public void detach(IEditingDomainProvider edp, ExecutionEvent event) throws EolIncrementalExecutionException {
		final IncrementalEvlModule module = EditorRegistry.REGISTRY.remove(edp);
		module.listenToModelChanges(false);
		module.getExecutionTraceManager().batchExecutionFinished();
	}
	
	public IEditingDomainProvider getCurrentEDP(ExecutionEvent event) {
		final IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
		if (!(editorPart instanceof IEditingDomainProvider)) {
			return null;
		}

		return (IEditingDomainProvider) editorPart;
	}

	@Override
	public void updateElement(UIElement element, Map parameters) {
        if (commandId != null) {
		    ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(
                    ICommandService.class);
			Command command = commandService.getCommand(commandId);
//			element.setChecked((Boolean) command.getState(IMenuStateIds.STYLE).getValue());
			State state = command.getState(LIVE_TOGGLE);
			element.setChecked((Boolean) state.getValue());
        }		
	}
}
