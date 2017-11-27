package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * A property access recorder creates {@link IPropertyAccess} elements to hold information about property access.
 * @author Horacio Hoyos Rodriguez
 *
 */
@Deprecated
public interface IPropertyAccessRecorder extends IRecorder<IPropertyAccess> {
	
	/**
	 * Record a property access.
	 */
	public void record(IModel model, Object modelElement, String propertyName);

}
