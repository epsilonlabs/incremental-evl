package org.eclipse.epsilon.evl.execute;

import org.eclipse.epsilon.eol.incremental.IRepository;
import org.eclipse.epsilon.eol.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;


public interface IEvlModuleExecutionRepository extends IRepository<IEvlModuleExecution> {

	IEvlModuleExecution getEvlModuleExecutionForSource(String string);

	//IPropertyAccess getPropertyAccessFor(IGuardTrace guard, IPropertyTrace property);

	//IPropertyAccess getPropertyAccessFor(ICheckTrace check, IPropertyTrace property);

	//IAllInstancesAccess getAllInstancesAccessFor(IMessageTrace message, IModelTypeTrace modelType);

	IPropertyAccess getPropertyAccessFor(IExecutionTrace executionTrace, IPropertyTrace property);

	IAllInstancesAccess getAllInstancesAccessFor(IExecutionTrace executionTrace, IModelTypeTrace modelType);

}
