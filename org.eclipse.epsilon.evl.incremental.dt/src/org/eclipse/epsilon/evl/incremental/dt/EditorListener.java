package org.eclipse.epsilon.evl.incremental.dt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
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
	public void partVisible(IWorkbenchPartReference partRef) {
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
	private EditingDomain getEditingDomain(IWorkbenchPartReference partRef) {
		final IWorkbenchPart part = partRef.getPart(true);
		if (part instanceof EditingDomain) {
			return (EditingDomain) part;
		}
		if (part instanceof IEditingDomainProvider) {
			return ((IEditingDomainProvider) part).getEditingDomain();
		}
		return null;
	}

}
