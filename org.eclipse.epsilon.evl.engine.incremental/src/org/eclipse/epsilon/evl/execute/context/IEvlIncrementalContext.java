package org.eclipse.epsilon.evl.execute.context;

import org.eclipse.epsilon.eol.dom.TypeExpression;
import org.eclipse.epsilon.eol.execute.introspection.recording.IPropertyAccessRecorder;
import org.eclipse.epsilon.eol.incremental.execute.IEolIncrementalContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;

public interface IEvlIncrementalContext extends IEolIncrementalContext, IEvlContext {
	
	/**
	 * Add trace information for all the property access in the recorder.
	 * 
	 * @param recorder
	 */
	void addPropertyAccessTraces(IPropertyAccessRecorder recorder);

	/**
	 * Add trace information for the type access.
	 * 
	 * @param typeExpression
	 */
	void addTypeAccessTrace(TypeExpression typeExpression);

}
