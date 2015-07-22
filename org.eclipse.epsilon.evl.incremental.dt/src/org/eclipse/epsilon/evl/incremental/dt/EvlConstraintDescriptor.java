package org.eclipse.epsilon.evl.incremental.dt;

import java.util.UUID;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.AbstractConstraintDescriptor;
import org.eclipse.epsilon.evl.dom.Constraint;

class EvlConstraintDescriptor extends AbstractConstraintDescriptor {

	private final Constraint constraint;
	private final String id;
	private final String name;
	private final String namespace;
	private final int code;
	
	public EvlConstraintDescriptor(String namespace, Constraint constraint, int code) {
		this.constraint = constraint;
		// FIXME
		this.id = UUID.randomUUID().toString();
		this.name = UUID.randomUUID().toString();
		this.namespace = UUID.randomUUID().toString();
		this.code = code;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getPluginId() {
		return this.namespace;
	}

	@Override
	public String getDescription() {
		return this.getBody();
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
		return true;
	}

	@Override
	public boolean targetsEvent(Notification notification) {
		return true;
	}

	@Override
	public String getMessagePattern() {
		return String.format("Constraint %s violated on {0}", getName());
	}

	@Override
	public String getBody() {
		return "getbody";
	}
	
}