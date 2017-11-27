package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;

/**
 * An all instances access recorder creates {@link IAllInstancesAccess} elements to hold information about
 * invocations of of all Instances operations.
 *  
 * @author Horacio Hoyos Rodriguez
 *
 */
@Deprecated
public interface IAllInstancesInvocationRecorder extends IRecorder<IAllInstancesAccess> {
	
	/** Record an all instances operation invocation. */ 
	void record(boolean isKind, String typeName);

}
