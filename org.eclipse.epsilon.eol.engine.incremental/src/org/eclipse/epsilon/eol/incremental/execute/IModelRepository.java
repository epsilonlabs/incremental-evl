package org.eclipse.epsilon.eol.incremental.execute;

import org.eclipse.epsilon.eol.incremental.IRepository;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;

public interface IModelRepository extends IRepository<IModelTrace> {
	
	
	IModelTrace getModelTraceByName(String name);

	IModelTrace first();

}
