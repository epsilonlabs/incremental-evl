package org.eclipse.epsilon.evl.incremental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of IncrementalEvlExecutionStrategy that reexecutes by visiting the modes
 * via TreeIterator and executes each trace as found.
 */
public class SimpleStrategy implements IncrementalEvlExecutionStrategy {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(SimpleStrategy.class);

	/** The incremental evl module. */
	private final IncrementalEvlModule incrementalEvlModule;

	/**
	 * Instantiates a new tree iterator strategy.
	 *
	 * @param incrementalEvlModule the incremental evl module
	 */
	SimpleStrategy(IncrementalEvlModule incrementalEvlModule) {
		this.incrementalEvlModule = incrementalEvlModule;
	}

	/**
	 * Execute.
	 *
	 * @param sourceChksum the source chksum
	 * @param modelRepository the model repository
	 * @param executionTraceRepo the execution trace repo
	 * @param modelTraceRepo the model trace repo
	 * @param context the context
	 * @throws EolRuntimeException the eol runtime exception
	 */
	@Override
	public void execute(
		String sourceChksum,
		ModelRepository modelRepository,
		IEvlModuleTraceRepository  executionTraceRepo,
		IModelTraceRepository  modelTraceRepo,
		IIncrementalEvlContext context) throws EolRuntimeException {
		for (IModel m : modelRepository.getModels()) {
			if (m instanceof IIncrementalModel) {
				IIncrementalModel im = (IIncrementalModel) m;
				Iterator<? extends Object> it = im.getAllElements();
				Set<String> visited = new HashSet<>();
				while (it.hasNext()) {
					Set<TraceReexecution> traces = new HashSet<>();
					Object element = it.next();
					// Get all Accesses for the element
					// For property access compare values, use property getter?
					Collection<IPropertyAccess> pas = executionTraceRepo
							.getPropertyAccessesForElement(im.getElementId(element), im.getModelUri(), sourceChksum);
					if (pas.isEmpty()) {
						// If there are not access maybe new element. Do we have any type/kind access?
						Collection<String> alltypes = im.getAllTypeNamesOf(element);
						for (String type : alltypes) {		// FIXME We can group all missing types, then execute?
							// Check for type access and if so, if there is no element trace its a new element
//							traces.addAll(executionTraceRepo
//									.findAllInstancesExecutionTraces(
//										sourceChksum,
//										im.getModelUri(),
//										type));
						}
					}		
					else {
						for (IPropertyAccess pa : pas) {
							// The PropertyAccessExecutionListener asks the model how to store the properties
							// so we must use the same "wrapping" in order to get comparable results
							if (! im.comparePropertyValue(element, pa.property().get().getName(), pa.getValue())) {
								// Change
								traces.addAll(executionTraceRepo
										.findPropertyAccessExecutionTraces(pa));
								logger.debug("Found {} traces for the element", traces.size());
							}
						}
						// If any property changed also get all Instance accesses by elements type/kind
						// and then execute. FIXME Can we tell if the property access was after an AllInstances?
						if (traces != null) {
							traces.addAll(executionTraceRepo
									.findAllInstancesExecutionTraces(
										sourceChksum,
										im.getModelUri(),
										im.getTypeNameOf(element)));
							// this.incrementalEvlModule.executeTraces(sourceChksum, im, traces, element);			
						}
					}
					for (TraceReexecution t : traces) {
						try {
							t.reexecute(context, this);
						} catch (EolRuntimeException e) {
							logger.error("Error reexecuting trace", e);
						}
					}
					// We need to keep all ElementTraces so we can then know which has been deleted...
					visited.add(im.getElementId(element));
				}
				Collection<IModelElementTrace> deleted = modelTraceRepo.getModelElementTraces(sourceChksum, im.getModelUri(), visited);
				for (IModelElementTrace met : deleted) {
					this.incrementalEvlModule.onDelete(im, im.getElementById(met.getUri()));
				}
			}
		}
		context.getConstraintTrace().clear();
	}

	/**
	 * Gets the constraint.
	 *
	 * @param checkTrace the check trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getConstraint(ICheckTrace checkTrace) {
		IInvariantTrace invariantT = checkTrace.invariant().get();
		ConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantT, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an ICheckTrace. "
				+ "One could not be found for: " + checkTrace.toString());
	}

	/**
	 * Gets the constraint.
	 *
	 * @param messageTrace the message trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getConstraint(IMessageTrace messageTrace) {
		IInvariantTrace invariantT = messageTrace.invariant().get();
		ConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantT, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an IMessageTrace. "
				+ "One could not be found for: " + messageTrace.toString());
	}

	/**
	 * Gets the constraint.
	 *
	 * @param invariantTrace the invariant trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getConstraint(IInvariantTrace invariantTrace) {
		ConstraintContext conCtx = getConstraintContextForTrace(invariantTrace.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantTrace, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an IInvariantTrace. "
				+ "One could not be found for: " + invariantTrace.toString());
	}

	/**
	 * Gets the constraint.
	 *
	 * @param guardTrace the guard trace
	 * @return the constraint
	 */
	@Override
	public TracedConstraint getConstraint(IGuardTrace guardTrace) {
		IGuardedElementTrace limits = guardTrace.limits().get();
		assert limits instanceof IInvariantTrace;
		IInvariantTrace invariantT = (IInvariantTrace) limits;
		TracedConstraintContext conCtx = getConstraintContextForTrace(invariantT.invariantContext().get());
		if (conCtx != null) {
			return findTracedConstraint(invariantT, conCtx);
		}
		throw new IllegalStateException("A traced constraint should always be found for an IGuardTrace. "
				+ "One could not be found for: " + guardTrace.toString());
	}
	
	/**
	 * Gets the constraint context.
	 *
	 * @param guardTrace the guard trace
	 * @return the constraint context
	 */
	@Override
	public TracedConstraintContext getConstraintContext(IGuardTrace guardTrace) {
		IGuardedElementTrace limits = guardTrace.limits().get();
		assert limits instanceof IContextTrace;
		IContextTrace contextT = (IContextTrace) limits;
		TracedConstraintContext conCtx = getConstraintContextForTrace(contextT);
		if (conCtx != null) {
			return conCtx;
		}
		throw new IllegalStateException("A traced context should always be found for an IGuardTrace. "
				+ "One could not be found for: " + guardTrace.toString());
		
	}
	
	/**
	 * Gets the constraint context.
	 *
	 * @param contextTrace the context trace
	 * @return the constraint context
	 */
	@Override
	public TracedConstraintContext getConstraintContext(IContextTrace contextTrace) {
		TracedConstraintContext conCtx = getConstraintContextForTrace(contextTrace);
		if (conCtx != null) {
			return conCtx;
		}
		throw new IllegalStateException("A traced context should always be found for an IContextTrace. "
				+ "One could not be found for: " + contextTrace.toString());
	}
	
	/**
	 * Gets the constraint context for trace.
	 *
	 * @param trace the trace
	 * @return the constraint context for trace
	 */
	// TODO Adding a cache could help a bit
	private TracedConstraintContext getConstraintContextForTrace(IContextTrace trace) {
		for (ConstraintContext conCtx : incrementalEvlModule.getConstraintContexts()) {
			TracedConstraintContext tConCtx = (TracedConstraintContext) conCtx;
			if (conCtx.getTypeName().equals(trace.getKind()) && (tConCtx.getIndex() == trace.getIndex())) {
				tConCtx.setModuleElementTrace(trace);
				return tConCtx;
			}
		}
		return null;
	}
	
	/**
	 * Find traced constraint.
	 *
	 * @param invariantTrace the invariant trace
	 * @param conCtx the con ctx
	 * @return the traced constraint
	 */
	// TODO Cache?
	private TracedConstraint findTracedConstraint(IInvariantTrace invariantTrace, ConstraintContext conCtx) {
		TracedConstraint tracedConstraint = (TracedConstraint) conCtx.getConstraints().stream()
				.filter(c -> c.getName().equals(invariantTrace.getName()))
				.findFirst().get();
		tracedConstraint.setModuleElementTrace(invariantTrace);
		return tracedConstraint;
	}

}