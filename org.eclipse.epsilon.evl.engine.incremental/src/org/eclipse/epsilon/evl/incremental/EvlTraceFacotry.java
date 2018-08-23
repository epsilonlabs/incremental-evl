package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.BaseFactoryImpl;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;

public class EvlTraceFacotry extends BaseFactoryImpl implements IEvlTraceFactory {

	@Override
	public IEvlModuleTrace getOrCreateModuleTrace(String uri) throws TraceModelDuplicateRelation {
		return new EvlModuleTrace(uri);
	}

}
