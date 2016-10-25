package org.eclipse.epsilon.evl.incremental.trace;


/**
 * Interface specifying common methods for a factory for {@link IIncrementalTraceManager}s.
 * 
 * @author Jonathan Co
 *
 * @param <T>
 *            The type of graph engine used by the produced {@link IIncrementalTraceManager}s.
 */
public interface IPropertyAccessTraceFactory {

	/**
	 * @return An instance of the {@link IIncrementalTraceManager}.
	 */
	IIncrementalTraceManager getTrace();
	
}
