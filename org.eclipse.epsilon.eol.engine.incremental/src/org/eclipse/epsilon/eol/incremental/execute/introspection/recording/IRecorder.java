package org.eclipse.epsilon.eol.incremental.execute.introspection.recording;

import org.eclipse.epsilon.eol.execute.control.IExecutionListener;

/**
 * A Recorder can be used by an {@linkplain IExecutionListener} in order to
 * record information about program execution. Different recorders can
 * be used to record different information, e.g. access of a property
 * value from a model element, or execution of the AllInstances operation.
 * For example, an ExecutionListener attached to the execution of the
 * EOL code: `Person.all.first.name` could record the property access
 * and the allInstances access.
 * 
 * Recording is separated into sessions. A session is started using
 * {@link #startRecording()} and ended with {@link #stopRecording()}.
 * Record accesses for the most recently started session are available
 * from {@link #getRecordings()}. Clients that wish to access 
 * property accesses from earlier recording sessions are expected to 
 * call {@link #getRecordings()} and store the resulting value 
 * before calling {@link #startRecording()}. 
 */
public interface IRecorder<T> {

	/**
	 * Tells the PropertyAccessRecorder to start a new recording
	 * session. Any subsequent notifications of property accesses
	 * are recorded and are accessible via {@link #getRecordings()}.
	 * Property accesses from any previous recording session are 
	 * disregarded (i.e., are no longer accessible from 
	 * {@link #getRecordings()})  
	 */
	void startRecording();

	/**
	 * Tells the PropertyAccessRecorder to finalise the current recording
	 * session. Any subsequent notifications of property accesses are not
	 * recorded, until {@link #startRecording()} is called. Property accesses 
	 * from the ended recording session are available via 
	 * {@link #getRecordings()}.  
	 */
	void stopRecording();

	/**
	 * Returns the property accesses that have occurred in the most 
	 * recently started recording session.
	 */
	IRecordings<T> getRecordings();
	
	/**
	 * State of the recorder
	 * 
	 * @return True if it is recording.
	 */
	boolean isRecording();

}