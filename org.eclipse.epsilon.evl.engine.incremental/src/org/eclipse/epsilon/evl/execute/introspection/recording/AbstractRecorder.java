package org.eclipse.epsilon.evl.execute.introspection.recording;

/**
 * Common functionality shared by all recorders.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <T>
 */
public abstract class AbstractRecorder<T> {

	protected boolean recording = false;
	protected IRecordings<T> currentRecording;

	public AbstractRecorder() {
		super();
	}
	
	public void startRecording() {
		currentRecording = new Recordings<T>();
		recording = true;
	}

	public void stopRecording() {
		recording = false;
	}

	public IRecordings<T> getRecordings() {
		return currentRecording;
	}

}