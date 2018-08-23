package org.eclipse.epsilon.base.incremental;

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;

public abstract class BaseFactoryImpl implements IBaseFactory {

	@Override
	public IModelTrace getOrCreateModelTrace(String uri) throws TraceModelDuplicateRelation {
		return new ModelTrace(uri);
	}

}
