package org.eclipse.epsilon.evl.incremental;

import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewElementsStrategy implements IncrementalEvlExecutionStrategy {
	
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(SimpleStrategy.class);
	
	private final Object newElement;
	private final IIncrementalModel im;
	private final boolean live;
	
	
	public NewElementsStrategy(Object newElement, IIncrementalModel im) {
		this(newElement, im, false);
	}

	public NewElementsStrategy(Object newElement, IIncrementalModel im, boolean live) {
		super();
		this.newElement = newElement;
		this.im = im;
		this.live = live;
	}

	@Override
	public void execute(
		IIncrementalEvlContext context,
		IncrementalEvlModule evlModule) throws EolRuntimeException {
		// Do we need to execute the pre blocks to restore context?
		// logger.info("Executing pre{}");
		// execute(getPre(), context);
		IEvlExecutionTraceManager etManager = context.getTraceManager();
		IEvlModuleTraceRepository  moduleTraceRepo = context.getTraceManager().getExecutionTraceRepository();
		
		for (ConstraintContext conCtx : evlModule.getConstraintContexts()) {
			try {
				if (conCtx.appliesTo(newElement, context)) {
					logger.info("Found matching context, executing");
					conCtx.execute(evlModule.preProcessConstraintContext(conCtx), context);
				}
			} catch (EolRuntimeException e) {
				logger.error("Error executing contexts for new element", e);
			}
		}
		
		Set<TraceReexecution> traces = moduleTraceRepo.findAllInstancesExecutionTraces(evlModule.getChksum(),
				im.getModelUri(), im.getTypeNameOf(newElement));
		for (TraceReexecution t : traces) {
			try {
				t.reexecute(context, evlModule);
			} catch (EolRuntimeException e) {
				logger.error("Error reexecuting trace", e);
			}
		}
		// FIXME WE need to update the EVL window messages!
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			logger.debug(uc.getMessage());
		}
		logger.info("Persisting traces");
		etManager.persistTraceInformation();
		//context.getConstraintTrace().clear();
	}

}
