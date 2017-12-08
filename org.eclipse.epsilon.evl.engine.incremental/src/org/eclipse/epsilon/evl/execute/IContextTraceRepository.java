package org.eclipse.epsilon.evl.execute;

import org.eclipse.epsilon.eol.incremental.execute.IRepository;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;


public interface IContextTraceRepository extends IRepository<IContextTrace> {

	IContextTrace getContextTraceFor(String typeName, IModelElementTrace modelElement);

}
