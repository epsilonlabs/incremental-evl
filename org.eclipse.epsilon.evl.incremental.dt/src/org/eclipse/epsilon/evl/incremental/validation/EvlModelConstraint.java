package org.eclipse.epsilon.evl.incremental.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;

public class EvlModelConstraint implements IModelConstraint {

	private final EvlConstraintDescriptor descriptor;
	private final TraceEvlModule module;

	public EvlModelConstraint(EvlConstraintDescriptor descriptor, TraceEvlModule module) {
		this.descriptor = descriptor;
		this.module = module;
	}

	@Override
	public IStatus validate(IValidationContext ctx) {		
		Resource eResource = ctx.getTarget().eResource();
		InMemoryEmfModel model = new InMemoryEmfModel(eResource);
		this.module.getContext().getModelRepository().addModel(model);
		
		try {
			boolean check = descriptor.getConstraint().check(ctx.getTarget(), this.module.getContext());
			System.out.println(check);
			return new IStatus() {
				
				@Override
				public boolean matches(int severityMask) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isOK() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isMultiStatus() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public int getSeverity() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getPlugin() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getMessage() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Throwable getException() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getCode() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public IStatus[] getChildren() {
					// TODO Auto-generated method stub
					return null;
				}
			};
		} catch (EolRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IConstraintDescriptor getDescriptor() {
		return this.descriptor;
	}

}
