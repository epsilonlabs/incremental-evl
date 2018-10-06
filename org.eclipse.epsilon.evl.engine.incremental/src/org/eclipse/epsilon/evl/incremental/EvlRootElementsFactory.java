package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.BaseRootElementsFactory;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.impl.EvlModuleTrace;

public class EvlRootElementsFactory extends BaseRootElementsFactory implements IEvlRootElementsFactory {

	@Override
	public IEvlModuleTrace createModuleTrace(String uri) throws TraceModelDuplicateElement, TraceModelConflictRelation {
		return new EvlModuleTrace(uri);
	}

}
