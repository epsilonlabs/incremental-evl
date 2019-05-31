package org.eclipse.epsilon.evl.incremental;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;

/**
 * Provide different Evl execution strategies so we can compare different approaches
 * @author Horacio Hoyos
 *
 */
public interface IncrementalEvlExecutionStrategy extends IncrementalExecutionStrategy {
	
	/**
	 * Execute.
	 *
	 * @param sourceChksum the source chksum
	 */
	void execute(
		String sourceChksum,
		ModelRepository modelRepository,
		IEvlModuleTraceRepository  executionTraceRepo,
		IModelTraceRepository  modelTraceRepo,
		IIncrementalEvlContext context) throws EolRuntimeException;
	
	
	TracedConstraint getConstraint(ICheckTrace checkTrace);

	TracedConstraint getConstraint(IMessageTrace messageTrace);

	TracedConstraint getConstraint(IInvariantTrace invariantTrace);

	TracedConstraint getConstraint(IGuardTrace guardTrace);
	
	TracedConstraintContext getConstraintContext(IGuardTrace guardTrace);
	
	TracedConstraintContext getConstraintContext(IContextTrace contextTrace);
	
}