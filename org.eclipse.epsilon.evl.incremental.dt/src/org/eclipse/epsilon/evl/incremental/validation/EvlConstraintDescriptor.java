package org.eclipse.epsilon.evl.incremental.validation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.AbstractConstraintDescriptor;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;

public class EvlConstraintDescriptor extends AbstractConstraintDescriptor {

	private final ConstraintContext context;
	private final Constraint constraint;
	private final int code;
	
	public EvlConstraintDescriptor(Constraint constraint, int code) {
		super();
		this.constraint = constraint;
		this.context = constraint.getConstraintContext();
		this.code = code;
	}

	@Override
	public String getName() {
		return this.context.getName() + "." + this.getConstraint().getName();
	}

	@Override
	public String getId() {
		return this.context.getName() + "." + this.getConstraint().getName();
	}

	@Override
	public String getPluginId() {
		return "TODO";
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public ConstraintSeverity getSeverity() {
		return ConstraintSeverity.WARNING;
	}

	@Override
	public int getStatusCode() {
		return this.code;
	}

	@Override
	public EvaluationMode<?> getEvaluationMode() {
		return EvaluationMode.LIVE;
	}

	@Override
	public boolean targetsTypeOf(EObject eObject) {
		return true; // TODO
	}

	@Override
	public boolean targetsEvent(Notification notification) {
		return true; // TODO:
	}

	@Override
	public String getMessagePattern() {
		return String.format("Constraint %s violated on {0}", getName()); //$NON-NLS-1$
	}

	@Override
	public String getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	public Constraint getConstraint() {
		return constraint;
	}

}
