package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;

/**
 * Provide different Evl execution strategies so we can compare different approaches
 * @author Horacio Hoyos
 *
 */
public interface IncrementalEvlExecutionStrategy extends IncrementalExecutionStrategy {
	
	/**
	 * Execute the strategy
	 * @param moduleTraceRepo 	the module trace repository
	 * @param modelTraceRepo 	the model trace repository
	 * @param context 			the EVL context
	 * @param evlModule TODO
	 *
	 * @throws EolRuntimeException the eol runtime exception
	 */
	void execute(IIncrementalEvlContext context,
		IncrementalEvlModule evlModule) throws EolRuntimeException;

}