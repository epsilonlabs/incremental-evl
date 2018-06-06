package org.eclipse.epsilon.base.incremental.dom;

import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.common.module.ModuleElement;

/**
 * An Epsilon ModuleElement than can be traced during execution. Traces can be added and retrieved. 
 * @author Horacio Hoyos Rodriguez
 *
 * @param <T> The type of traces used to trace the module.
 */
public interface TracedModuleElement<T extends IModuleElementTrace> extends ModuleElement {
	
	/** Set the current trace */
	void setCurrentTrace(T trace);
	
	/** Get the current trace */
	T getCurrentTrace();

}
