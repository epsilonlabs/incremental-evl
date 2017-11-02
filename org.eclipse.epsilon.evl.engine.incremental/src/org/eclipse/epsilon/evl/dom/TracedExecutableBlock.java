package org.eclipse.epsilon.evl.dom;

import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.execute.introspection.recording.PropertyAccessRecorder;

public class TracedExecutableBlock<T> extends ExecutableBlock<T> {

	protected final PropertyAccessRecorder recorder;
	
	public TracedExecutableBlock(Class<?> expectedResultClass) {
		super(expectedResultClass);
		this.recorder = new PropertyAccessRecorder();

	}

	public PropertyAccessRecorder getRecorder() {
		return recorder;
	}	

}
