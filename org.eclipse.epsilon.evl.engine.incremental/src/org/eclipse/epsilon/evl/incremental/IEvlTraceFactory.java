package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.IBaseFactory;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

public interface IEvlTraceFactory extends IBaseFactory {

	IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateRelation;

}
