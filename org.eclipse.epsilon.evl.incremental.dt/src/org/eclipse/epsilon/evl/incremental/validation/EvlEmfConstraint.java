package org.eclipse.epsilon.evl.incremental.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.AbstractConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;

public class EvlEmfConstraint extends AbstractConstraintDescriptor implements
		IConstraintDescriptor, IModelConstraint {

	private final Constraint constraint;
	private final ConstraintContext constraintContext;
	private final int statusCode;
	
	public EvlEmfConstraint(Constraint constraint, int statusCode) {
		super();
		this.constraint = constraint;
		this.constraintContext = constraint.getConstraintContext();
		this.statusCode = statusCode;
	}

	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return this.constraint.getName();
	}

	@Override
	public String getId() {
		// TODO: add category to this
		return this.constraintContext.getName() + "." + this.constraint.getName();
	}

	@Override
	public String getPluginId() {
		// FIXME
		return "";
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public ConstraintSeverity getSeverity() {
		// FIXME
		return ConstraintSeverity.ERROR;
	}

	@Override
	public int getStatusCode() {
		return this.statusCode;
	}

	@Override
	public EvaluationMode<?> getEvaluationMode() {
		return EvaluationMode.LIVE;
	}

	@Override
	public boolean targetsTypeOf(EObject eObject) {
		// FIXME should use the appliesTo method but does not currently know context when this is called
		return true;
	}

	@Override
	public boolean targetsEvent(Notification notification) {
		return true;
	}

	@Override
	public String getMessagePattern() {
		return String.format("Constraint %s violated on {0}", getName()); //$NON-NLS-1$
	}

	@Override
	public String getBody() {
		return null;
	}

}
