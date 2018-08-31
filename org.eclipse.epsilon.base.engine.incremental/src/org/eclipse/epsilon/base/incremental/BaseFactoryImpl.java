package org.eclipse.epsilon.base.incremental;

import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.impl.ModelTrace;

public abstract class BaseFactoryImpl implements IBaseFactory {

	@Override
	public IModelTrace createModelTrace(String uri) throws TraceModelDuplicateElement, TraceModelConflictRelation {
		return new ModelTrace(uri);
	}

}
