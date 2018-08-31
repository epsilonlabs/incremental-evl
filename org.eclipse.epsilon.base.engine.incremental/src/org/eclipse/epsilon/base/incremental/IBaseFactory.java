package org.eclipse.epsilon.base.incremental;

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;

public interface IBaseFactory {

	IModuleExecutionTrace createModuleTrace(String uri) throws TraceModelDuplicateElement, TraceModelConflictRelation;

	IModelTrace createModelTrace(String uri) throws TraceModelDuplicateElement, TraceModelConflictRelation;

}
