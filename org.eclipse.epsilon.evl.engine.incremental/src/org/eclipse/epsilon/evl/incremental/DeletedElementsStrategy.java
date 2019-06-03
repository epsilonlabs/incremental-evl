package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IncrementalEvlExecutionStrategy to deal with deleted elements.
 * 
 * 
 * @author Goblin
 *
 */
public class DeletedElementsStrategy implements IncrementalEvlExecutionStrategy {
	
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(SimpleStrategy.class);
	
	private final Collection<IModelElementTrace> deleted;
	private final IIncrementalModel im;
	private final boolean live;

	public DeletedElementsStrategy(
		Collection<IModelElementTrace> deleted,
		IIncrementalModel im) {
		this(deleted, im, false);
	}
	
	
	public DeletedElementsStrategy(Collection<IModelElementTrace> deleted, IIncrementalModel im, boolean live) {
		super();
		this.deleted = deleted;
		this.im = im;
		this.live = live;
	}


	@Override
	public void execute(
		IIncrementalEvlContext context,
		IncrementalEvlModule evlModule) throws EolRuntimeException {
		IEvlModuleTraceRepository  moduleTraceRepo = context.getTraceManager().getExecutionTraceRepository();
		Set<TraceReexecution> traces = new HashSet<>();
		for (IModelElementTrace met : deleted) {
			traces.addAll(moduleTraceRepo.findIndirectExecutionTraces(
					evlModule.getChksum(),
					met.getUri(),
					im.getAllTypeNamesOf(met)));
		}
		for (TraceReexecution t : traces) {
			try {
				t.reexecute(context, evlModule);
			} catch (EolRuntimeException e) {
				logger.error("Error reexecuting trace", e);
			}
		}
		if (live) {
			for (IModelElementTrace met : deleted) {
				// Remove unsatisfied constraints related to the element.
				Iterator<UnsatisfiedConstraint> it = context.getUnsatisfiedConstraints().iterator();
				while (it.hasNext()) {
					UnsatisfiedConstraint usc = it.next();
					if (im.getElementId(usc.getInstance()).equals(met.getUri())) {
						logger.info("Removing unsatisfied contraint");
						it.remove();
					}
				}
				moduleTraceRepo.removeTraceInformation(evlModule.getChksum()  , met.getUri(), im.getModelUri());
				//getContext().getConstraintTrace().clear();
			}
			
		}
		
	}

}
