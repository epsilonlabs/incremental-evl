package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.incremental.trace.IExecutionTrace;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.context.TracedEvlContext;
import org.eclipse.epsilon.evl.execute.introspection.recording.AllInstancesInvocationRecorder;
import org.eclipse.epsilon.evl.execute.introspection.recording.PropertyAccessRecorder;
import org.eclipse.epsilon.evl.execute.introspection.recording.SatisfiesOperationInvocationRecorder;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleExecution;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

/**
 * An executable block that has different type of recorders and that is capable of starting/stopping all its recorders
 * in a single invocation.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <T> The expected type of the execution of the block
 */
public class TracedExecutableBlock<T> extends ExecutableBlock<T> {
	
	private PropertyAccessRecorder propAccrecorder;
	private AllInstancesInvocationRecorder allInvRecorder;
	private SatisfiesOperationInvocationRecorder satisfiesRecorder;

	/**
	 * A reference to the specific type of ExecutionTrace being traced.
	 * This is necessary because an executable block can represent different elements,
	 * for example in EVL it can be a guard, check, message, etc.
	 */
	private IExecutionTrace trace;

	public IExecutionTrace getTrace() {
		return trace;
	}

	public void setTrace(IExecutionTrace trace) {
		this.trace = trace;
	}
		
	public TracedExecutableBlock(Class<?> expectedResultClass) {
		super(expectedResultClass);
		//this.recorder = new PropertyAccessRecorder(unitOfWork, evlExecution, executionTrace);
	}
	
	public PropertyAccessRecorder initPropertyAccessRecorder(TracedEvlContext context) {
		if (propAccrecorder == null) {
			IEvlExecutionTraceManager unitOfWork = context.getTraceManager();
			IEvlModuleExecution evlExecution = context.getEvlExecution();
			propAccrecorder = new PropertyAccessRecorder(unitOfWork, evlExecution, trace);
		}
		return propAccrecorder;
	}
	
	public AllInstancesInvocationRecorder initAllAccessRecorder(TracedEvlContext context) {
		if (allInvRecorder == null) {
			IEvlExecutionTraceManager unitOfWork = context.getTraceManager();
			IEvlModuleExecution evlExecution = context.getEvlExecution();
			allInvRecorder = new AllInstancesInvocationRecorder(unitOfWork, evlExecution, trace);
		}
		return allInvRecorder;
	}
	
	public SatisfiesOperationInvocationRecorder initSatisfiesOperationInvocationRecorder(IInvariantTrace invariant) {
		if (satisfiesRecorder == null) {
			satisfiesRecorder = new SatisfiesOperationInvocationRecorder(invariant);
		}
		return satisfiesRecorder;
	}
	
	/**
	 * Any chores to be done before execution
	 */
	public void preExecution() {
		startRecording();
	}
	
	/**
	 * Any chores to be done after execution
	 */
	public void postExecution() {
		stopRecording();
	}
	

	
	public void startRecording() {
		if (propAccrecorder != null) propAccrecorder.startRecording();
		if (allInvRecorder != null) allInvRecorder.startRecording();
		if (satisfiesRecorder != null) satisfiesRecorder.startRecording();
	}

	public void stopRecording() {
		if (propAccrecorder != null) propAccrecorder.stopRecording();
		if (allInvRecorder != null) allInvRecorder.stopRecording();
		if (satisfiesRecorder != null) satisfiesRecorder.stopRecording();
	}
	

}
