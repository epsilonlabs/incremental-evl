package org.eclipse.epsilon.evl.execute;

import org.eclipse.epsilon.eol.incremental.IRepository;
import org.eclipse.epsilon.eol.incremental.trace.IElementAccess;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;



public interface IContextTraceRepository extends IRepository<IContextTrace> {

	IContextTrace getContextFor(String typeName, IEvlModuleTrace evlModule);

	IElementAccess getElementAccessFor(IContextTrace context, IModelElementTrace modelElement);

}
