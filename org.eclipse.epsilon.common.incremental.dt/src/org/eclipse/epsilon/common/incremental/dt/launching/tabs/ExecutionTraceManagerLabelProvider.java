package org.eclipse.epsilon.common.incremental.dt.launching.tabs;


import org.eclipse.epsilon.common.incremental.dt.launching.extensions.ExecutionTraceManagerExtension;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class ExecutionTraceManagerLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}
	
	public Image getImage(Object element) {
		return ((ExecutionTraceManagerExtension) element).getImage();
	}

	public String getText(Object element) {
		return ((ExecutionTraceManagerExtension) element).getLabel();
	}

}
