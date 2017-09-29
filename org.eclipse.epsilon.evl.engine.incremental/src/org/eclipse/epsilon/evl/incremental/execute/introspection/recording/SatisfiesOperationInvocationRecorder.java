package org.eclipse.epsilon.evl.incremental.execute.introspection.recording;

import java.util.List;

import org.eclipse.epsilon.eol.incremental.execute.IModuleElementAccessListener;
import org.eclipse.epsilon.evl.dom.Constraint;

public class SatisfiesOperationInvocationRecorder implements IOperationInvocationRecorder, IModuleElementAccessListener {
	
	private boolean recording = false;
	private IOperationInvocations<SimpleOperationInvocation<Constraint>> currentOperationInvocations = new SimpleOperationInvocations();
	private List<Constraint> satisfiesConstraints;


	@Override
	public IOperationInvocations<SimpleOperationInvocation<Constraint>> getOperationInvocations() {
		return currentOperationInvocations;
	}

	@Override
	public void startRecording() {
		currentOperationInvocations.clear();
		satisfiesConstraints.clear();
		recording = true;

	}

	@Override
	public void stopRecording() {
		recording = false;
	}

	@Override
	public void record(String operationName) {
		if (recording) {
			currentOperationInvocations.add(createOperationAccess(operationName));
		}
	}

	private SimpleOperationInvocation<Constraint> createOperationAccess(String operationName) {
		SimpleOperationInvocation<Constraint> opIv = new SimpleOperationInvocation<Constraint>(operationName, satisfiesConstraints);
		return opIv;
	}

	@Override
	public void accessed(Object moduleElement) {
		if (recording) {
	 		if (moduleElement instanceof Constraint) {
				satisfiesConstraints.add((Constraint) moduleElement);
			}
		}
	}

}
