package org.eclipse.epsilon.base.incremental;

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;

public interface IBaseFactory {

	IModuleExecutionTrace getOrCreateModuleTrace(String uri) throws TraceModelDuplicateRelation;

	IModelTrace getOrCreateModelTrace(String uri) throws TraceModelDuplicateRelation;

}
