package org.eclipse.epsilon.evl.execute.introspection.recording;

import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.models.IModel;


public interface IPropertyAccessRecorder extends IRecorder<IPropertyAccess> {
	
	/**
	 * Record execution of a property access.
	 */
	public void record(IModel model, Object modelElement, String propertyName);

}
