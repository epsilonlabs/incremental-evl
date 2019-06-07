package org.eclipse.epsilon.evl.incremental;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.execute.IModuleIncremental;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraintContext;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IGuardedElementTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;

/**
  * A reexecution for a guard trace.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class ReexecutionGuardTrace implements TraceReexecution {

	private final IGuardTrace guardTrace;
	private final IEvlModuleTrace moduleTrace;
	private final IModelElementTrace selfTrace;
	private final IExecutionContext executionContext;	
	private final Set<TraceReexecution> children;
	private final TraceReexecution parent;

	public ReexecutionGuardTrace(
		IGuardTrace trace,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdlUri,
		IModelElementTrace slfTrc) {
		this(trace, exctnCntxt, evlMdlUri, slfTrc, new HashSet<>(), null);
	}

	public ReexecutionGuardTrace(
		IGuardTrace trace,
		IExecutionContext exctnCntxt,
		IEvlModuleTrace evlMdl,
		IModelElementTrace slfTrc,
		Set<TraceReexecution> chldrn,
		TraceReexecution prnt) {
		guardTrace = trace;
		executionContext = exctnCntxt;
		moduleTrace = evlMdl;
		selfTrace = slfTrc;
		children = chldrn;
		parent = prnt;
	}

	@Override
	public final TraceReexecution makeChildOf(TraceReexecution parent) {
		return new ReexecutionGuardTrace(guardTrace, executionContext, moduleTrace, selfTrace,
				children, parent);
	}

	@Override
	public Optional<TraceReexecution> parent() {
		return Optional.ofNullable(parent);
	}


	protected String section() {
		return "GI";
	}

	@Override
	public void reexecute(
		IIncrementalBaseContext context,
		IModuleIncremental evlModule)
		throws EolRuntimeException {
		assert context instanceof IIncrementalEvlContext;
		assert evlModule instanceof IEvlModuleIncremental;
		// Reexecution is different if the guard limits an Invariant or a Context
		IGuardedElementTrace limits = guardTrace.limits().get();
		Object selfVal = getSelf((IIncrementalEvlContext) context);
		if (limits instanceof IInvariantTrace) {
			TracedConstraint tc = ((IEvlModuleIncremental) evlModule).getTracedConstraint(guardTrace);
			tc.reexecute(selfVal, (IIncrementalEvlContext) context, "I");
		}
		else {
			TracedConstraintContext tcc = ((IEvlModuleIncremental) evlModule).getTracedConstraintContext(guardTrace);
			// FIXME need to evluate guard trace!!
			tcc.execute(selfVal, (IIncrementalEvlContext)context);
		}
	}
	
	/**
	 * Retrieve the object that represents "self" (the context for an invariant
	 * execution)
	 * 
	 * @param moduleTrace     TODO
	 * @param selfTrace     the model element trace that holds the reference to the
	 *                      self element
	 * @param context
	 * @param model         the model in which the event was generated
	 * @param modelObject   the object that generated the event
	 * @param selfModelName the name of the model that owns the self reference
	 * 
	 * @return
	 * @throws EolRuntimeException if there is an error retrieving the element
	 */
	protected Object getSelf(IIncrementalEvlContext context) throws EolRuntimeException {
		// logger.info("Resolve self element.");
		Optional<IModelAccess> ma = IncrementalUtils.asStream(moduleTrace.models().get())
				.filter(m -> m.modelTrace().get().equals(selfTrace.modelTrace().get())).findFirst();
		if (!ma.isPresent()) {
			throw new EolRuntimeException(
					"No model access information found for " + selfTrace.modelTrace().get().getUri() + " for the given module.");
		}
		String selfModelName = ma.get().getModelName();
		IModel selfModel = null;
		try {
			selfModel = context.getModelRepository().getModelByName(selfModelName);
		} catch (EolModelNotFoundException e1) {

			throw new EolRuntimeException("No matching model found in context for model " + selfModelName);
		}
		Object self = selfModel.getElementById(selfTrace.getUri());
		return self;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((executionContext == null) ? 0 : executionContext.hashCode());
		result = prime * result + ((guardTrace == null) ? 0 : guardTrace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReexecutionGuardTrace other = (ReexecutionGuardTrace) obj;
		if (executionContext == null) {
			if (other.executionContext != null)
				return false;
		} else if (!executionContext.equals(other.executionContext))
			return false;
		if (guardTrace == null) {
			if (other.guardTrace != null)
				return false;
		} else if (!guardTrace.equals(other.guardTrace))
			return false;
		return true;
	}
	
}
