package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.IBaseRootElementsFactory;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

public interface IEvlRootElementsFactory extends IBaseRootElementsFactory {

	IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateElement, TraceModelConflictRelation;

}
