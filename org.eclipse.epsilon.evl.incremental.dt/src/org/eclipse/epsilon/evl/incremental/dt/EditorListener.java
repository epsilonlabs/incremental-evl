package org.eclipse.epsilon.evl.incremental.dt;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator.LiveValidator.LiveValidationAction;
import org.eclipse.epsilon.evl.incremental.dt.commands.EnableLiveValidationAction;
import org.eclipse.epsilon.evl.incremental.validation.LiveValidationListener;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.SubMenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

/**
 * 
 * @author Jonathan Co
 *
 */
public class EditorListener implements IPartListener2 {

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		final EditingDomain editingDomain = this.getEditingDomain(partRef);
		if (editingDomain == null) {
			return;
		}

		final ResourceSet resourceSet = editingDomain.getResourceSet();
		final LiveValidationListener listener = new LiveValidationListener();
		resourceSet.eAdapters().add(listener);

		this.modifyEcoreEditorMenu(partRef, listener);
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		// No-op
		// TODO: Clean up of listener needed?
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		// No-op
	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		// No-op
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// No-op
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		// No-op
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		// No-op
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		// No-op
	}

	/**
	 * 
	 * @param partRef
	 * @return
	 */
	public EditingDomain getEditingDomain(IWorkbenchPartReference partRef) {
		final IWorkbenchPart part = partRef.getPart(true);
		if (part instanceof EditingDomain) {
			return (EditingDomain) part;
		}
		if (part instanceof IEditingDomainProvider) {
			return ((IEditingDomainProvider) part).getEditingDomain();
		}
		return null;
	}

	/**
	 * 
	 * @param partRef
	 */
	public void modifyEcoreEditorMenu(IWorkbenchPartReference partRef,
			LiveValidationListener listener) {
		final IWorkbenchPart part = partRef.getPart(true);

		// Actions to modify
		IEditorPart editor = null;
		IMenuManager ecoreMenuManager = null;
		ActionContributionItem liveValidationAction = null;
		
		// Get the Ecore Menu Manager
		if (part instanceof IEditorPart) {
			editor = (IEditorPart) part;
			IMenuManager globalMenuManager = editor
					.getEditorSite().getActionBars().getMenuManager();
			ecoreMenuManager = (IMenuManager) globalMenuManager
					.find("org.eclipse.emf.ecoreMenuID");
		}
		
		if (ecoreMenuManager instanceof SubMenuManager) {
			ecoreMenuManager = (IMenuManager) ((SubMenuManager) ecoreMenuManager)
					.getParent();
		}
		
		// Retrieve the items to modify
		for (IContributionItem item : ecoreMenuManager.getItems()) {
			if (item instanceof ActionContributionItem
					&& ((ActionContributionItem) item).getAction() instanceof LiveValidationAction) {
				liveValidationAction = (ActionContributionItem) item;
				continue;
			}
		}
				
		if (liveValidationAction != null) {
			liveValidationAction.dispose();
			ecoreMenuManager.remove(liveValidationAction);
		}
		
		ecoreMenuManager.insertBefore("additions", 
				new EnableLiveValidationAction(listener));
	}
	
}
