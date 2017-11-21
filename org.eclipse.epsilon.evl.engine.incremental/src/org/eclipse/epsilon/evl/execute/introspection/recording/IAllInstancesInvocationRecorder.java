package org.eclipse.epsilon.evl.execute.introspection.recording;

import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;

public interface IAllInstancesInvocationRecorder extends IRecorder<IAllInstancesAccess> {
	
	void record(boolean isKind, String typeName);

}
