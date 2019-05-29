package org.eclipse.epsilon.evl.incremental;

import java.util.Optional;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.IncrementalExecutionStrategy;
import org.eclipse.epsilon.base.incremental.TraceReexecution;
import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.execute.context.IIncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;

/**
 * A base implementation of TraceReexecution for EVL reexecution.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
 // TODO We might need a ModuleElementTraceReexecution shared by all languages
public abstract class ConstraintModuleElementTraceReexecution implements TraceReexecution {

	protected final IEvlModuleTrace moduleTrace;
	protected final IModelElementTrace selfTrace;
	protected final IExecutionContext executionContext;	
	protected final Set<TraceReexecution> children;
	protected final TraceReexecution parent;
	
	protected abstract TracedConstraint getTracedConstraint(IncrementalExecutionStrategy strategy);
	
	protected abstract String section();
	
	/**
	 * Create a new ConstraintModuleElementTraceReexecution
	 * @param mdulTrc		The module element being executed
	 * @param slfTrc
	 * @param exctnCntxt
	 * @param chldrn
	 * @param prnt
	 */
	public ConstraintModuleElementTraceReexecution(
		IEvlModuleTrace mdulTrc,
		IModelElementTrace slfTrc,
		IExecutionContext exctnCntxt,
		Set<TraceReexecution> chldrn,
		TraceReexecution prnt) {
		super();
		moduleTrace = mdulTrc;
		selfTrace = slfTrc;
		executionContext = exctnCntxt;
		children = chldrn;
		parent = prnt;
	}

	@Override
	public Optional<TraceReexecution> parent() {
		return Optional.ofNullable(parent);
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
	public void reexecute(IIncrementalBaseContext context, IncrementalExecutionStrategy strategy)
			throws EolRuntimeException {
		assert context instanceof IIncrementalEvlContext;
		assert strategy instanceof IncrementalEvlExecutionStrategy;
		Object selfVal = getSelf((IIncrementalEvlContext) context);
		TracedConstraint tc = getTracedConstraint(strategy);
		tc.executeImpl(selfVal, (IIncrementalEvlContext) context, section());
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((executionContext == null) ? 0 : executionContext.hashCode());
		result = prime * result + ((moduleElementTrace() == null) ? 0 : moduleElementTrace().hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConstraintModuleElementTraceReexecution other = (ConstraintModuleElementTraceReexecution) obj;
		if (executionContext == null) {
			if (other.executionContext != null)
				return false;
		} else if (!executionContext.equals(other.executionContext))
			return false;
		if (moduleElementTrace() == null) {
			if (other.moduleElementTrace() != null)
				return false;
		} else if (!moduleElementTrace().equals(other.moduleElementTrace()))
			return false;
		return true;
	}

	
	

}
