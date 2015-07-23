package org.eclipse.epsilon.evl.incremental.dt.commands;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.eclipse.ui.dialogs.SearchPattern;

public class EvlSelectionDialog extends FilteredResourcesSelectionDialog {

	public EvlSelectionDialog(Shell shell) {
		super(shell, true, ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
	}
	
	@Override
	protected ItemsFilter createFilter() {
		return new EvlResourceFilter();
	}

	/**
	 * {@link ResourceFilter} subclass for only listing EVL files.
	 * 
	 * @author Jonathan Co
	 *
	 */
	class EvlResourceFilter extends ResourceFilter {

		public static final String EVL_PATTERN = "*.evl";
		
		public EvlResourceFilter() {
			super();
			this.patternMatcher = new SearchPattern();
			this.patternMatcher.setPattern(EVL_PATTERN);
		}
	}

}
