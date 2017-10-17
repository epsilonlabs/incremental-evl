package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.List;

public interface ISimpleOperationInvocation extends IOperationInvocation {
	
	List<?> getParameters();

}
