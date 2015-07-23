package org.eclipse.epsilon.evl.incremental.dt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator.LiveValidator.LiveValidationAction;
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

	private final Map<Notifier, EContentAdapter> listenerMap = new HashMap<Notifier, EContentAdapter>();

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		final EditingDomain editingDomain = this.getEditingDomain(partRef);
		if (editingDomain == null) {
			return;
		}

		final ResourceSet resourceSet = editingDomain.getResourceSet();
		final EContentAdapter listener = new LiveValidationContentAdapter();
		resourceSet.eAdapters().add(listener);
		listenerMap.put(resourceSet, listener);

		this.modifyEcoreEditorMenu(partRef);
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		final EditingDomain editingDomain = this.getEditingDomain(partRef);
		if (editingDomain == null) {
			return;
		}

		final ResourceSet resourceSet = editingDomain.getResourceSet();
		final EContentAdapter listener = listenerMap.remove(resourceSet);
		resourceSet.eAdapters().remove(listener);
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

	public void modifyEcoreEditorMenu(IWorkbenchPartReference partRef) {
		final IWorkbenchPart part = partRef.getPart(true);

		// Actions to modify
		IMenuManager ecoreMenuManager = null;
		IContributionItem liveValidationAction = null;
		
		// Get the Ecore Menu Manager
		if (part instanceof IEditorPart) {
			IMenuManager globalMenuManager = ((IEditorPart) part)
					.getEditorSite().getActionBars().getMenuManager();
			ecoreMenuManager = (IMenuManager) globalMenuManager
					.find("org.eclipse.emf.ecoreMenuID");
		}
		
		if (ecoreMenuManager instanceof SubMenuManager) {
			ecoreMenuManager = (IMenuManager) ((SubMenuManager) ecoreMenuManager).getParent();
		}
		
		// Retrieve the items to modify
		for (IContributionItem item : ecoreMenuManager.getItems()) {
			if (item instanceof ActionContributionItem
					&& ((ActionContributionItem) item).getAction() instanceof LiveValidationAction) {
				liveValidationAction = item;
				continue;
			}
		}
		
		if (liveValidationAction != null) {
			ecoreMenuManager.remove(liveValidationAction);
		}
	}
	
}
