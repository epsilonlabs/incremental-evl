package org.eclipse.epsilon.evl.incremental.dt;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.ILiveValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.epsilon.evl.emf.validation.EvlValidator;

public class EvlValidatorAdaptor extends EvlValidator {

	private final ILiveValidator liveValidator;

	public EvlValidatorAdaptor() {
		super();
		liveValidator = ModelValidationService.getInstance().newValidator(
				EvaluationMode.LIVE);
		liveValidator.setReportSuccesses(true);
	}

	@Override
	public boolean validate(EObject object, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		System.out.println("Validate EObject");
		return true;
	}

	@Override
	public boolean validate(EClass eClass, EObject eObject,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		System.out.println("Validate EClass");
		return true;
	}

	@Override
	public boolean validate(EDataType dataType, Object value,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		System.out.println("Validate EDataType");
		return true;
	}

	@Override
	protected void validate(Resource resource, Map<Object, Object> context) {
		System.out.println("Validate resource");
	}

}
