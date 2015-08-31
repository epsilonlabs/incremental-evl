package org.eclipse.epsilon.evl.incremental.dt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator.LiveValidator.LiveValidationAction;
import org.eclipse.epsilon.evl.incremental.TraceEvlContext;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;
import org.eclipse.epsilon.evl.incremental.validation.EnableLiveValidationAction;
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
@Deprecated
public class EditorListener implements IPartListener2 {
	
	private final Map<Notifier, LiveValidationListener> listenerMap = new HashMap<Notifier, LiveValidationListener>();

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		// No-op
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
		final EditingDomain editingDomain = this.getEditingDomain(partRef);
		if (editingDomain == null) {
			return;
		}
		
		final ResourceSet resourceSet = editingDomain.getResourceSet();
		LiveValidationListener listener = this.listenerMap.get(resourceSet);
		if (listener == null) {
			listener = new LiveValidationListener(editingDomain);
			resourceSet.eAdapters().add(listener);
			this.listenerMap.put(resourceSet, listener);
			this.modifyEcoreEditorMenu(partRef, listener);
		}
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
