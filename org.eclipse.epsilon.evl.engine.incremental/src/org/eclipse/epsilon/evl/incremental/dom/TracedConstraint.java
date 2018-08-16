/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	   Jonathan Co   - Initial API and implementation
 *     Horacio Hoyos - Decoupling and abstraction
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.dom;

import java.util.Iterator;

import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlTraceFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subclass of {@link Constraint} for use with incremental evaluation and
 * traces. This bypasses the default checking of the {@link ConstraintTrace}'s.
 * 
 * @author Jonathan Co
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedConstraint extends Constraint implements TracedModuleElement<IInvariantTrace> {

	private static final Logger logger = LoggerFactory.getLogger(TracedConstraint.class);

	private IInvariantTrace trace;

	/**
	 * Flag to indicate that we are on live mode, i.e. listening to model changes
	 */
	private boolean listeningToChagnes = false;

	@Override
	public void setCurrentTrace(IInvariantTrace trace) {
		this.trace = trace;
	}

	@Override
	public IInvariantTrace getCurrentTrace() {
		return trace;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		if (checkType && !constraintContext.getAllOfSourceKind(context).contains(object))
			return false;
		return _appliesTo(object,
				(IncrementalEvlContext<IEvlModuleTraceRepository, IEvlTraceFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory>>) context);
	}

	private boolean _appliesTo(Object object,
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlTraceFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory>> tracedEvlContext)
			throws EolRuntimeException {

		Boolean result = true;
		if (guardBlock != null) {
			// IncrementalEvlContext tracedEvlContext = (IncrementalEvlContext)context;
			tracedEvlContext.getSatisfiesListener().aboutToExecute(this, tracedEvlContext);
			tracedEvlContext.getPropertyAccessExecutionListener().aboutToExecute(guardBlock, tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().aboutToExecute(guardBlock, tracedEvlContext);
			result = guardBlock.execute(tracedEvlContext, Variable.createReadOnlyVariable("self", object));
			trace.guard().get().setResult(result);
			tracedEvlContext.getSatisfiesListener().finishedExecuting(this, result, tracedEvlContext);
			tracedEvlContext.getPropertyAccessExecutionListener().finishedExecuting(guardBlock, result,
					tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().finishedExecuting(guardBlock, result,
					tracedEvlContext);
			return result;
		}
		return result;
	}

	@Override
	public boolean check(Object self, IEvlContext context, boolean checkType) throws EolRuntimeException {

		logger.info("Check {} for {}", getName(), self);
		// First look in the cache
		if (context.getConstraintTrace().isChecked(this, self)) {
			logger.debug("Result found in cache");
			return context.getConstraintTrace().isSatisfied(this, self);
		}
		// Return immediately if constraint does not apply
		if (!appliesTo(self, context, checkType)) {
			logger.debug("Does not apply");
			trace.setResult(false);
			return false;
		}
		final UnsatisfiedConstraint unsatisfiedConstraint = preprocessCheck(self, context);
		@SuppressWarnings("unchecked")
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlTraceFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory>> tracedEvlContext = (IncrementalEvlContext<IEvlModuleTraceRepository, IEvlTraceFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlTraceFactory>>) context;

		tracedEvlContext.getPropertyAccessExecutionListener().aboutToExecute(checkBlock, tracedEvlContext);
		tracedEvlContext.getAllInstancesInvocationExecutionListener().aboutToExecute(checkBlock, tracedEvlContext);
		Boolean result = checkBlock.execute(context, false);
		logger.info("Result: {}", result);
		tracedEvlContext.getPropertyAccessExecutionListener().finishedExecuting(checkBlock, result, tracedEvlContext);
		tracedEvlContext.getAllInstancesInvocationExecutionListener().finishedExecuting(checkBlock, result,
				tracedEvlContext);

		if (messageBlock != null) {
			tracedEvlContext.getPropertyAccessExecutionListener().aboutToExecute(messageBlock, tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().aboutToExecute(messageBlock,
					tracedEvlContext);
		}
		boolean postResult = postprocessCheck(self, context, unsatisfiedConstraint, result);
		if (messageBlock != null) {
			tracedEvlContext.getPropertyAccessExecutionListener().finishedExecuting(messageBlock, postResult,
					tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().finishedExecuting(messageBlock, postResult,
					tracedEvlContext);
		}
		boolean oldResult = trace.getResult();
		if (!oldResult && postResult) {
			logger.debug("Removing unsatisfied constraint");
			removeUnsatisfiedConstraint(context, self);
		}
		trace.setResult(postResult);
		return postResult;

	}

	/**
	 * @param self
	 * @param context
	 */
	@Override
	protected void addToCache(Object self, IEvlContext context) {
		if (!listeningToChagnes) {
			logger.debug("Adding result to cache");
			context.getConstraintTrace().addChecked(this, self, false);
		}
	}

	/**
	 * Create a new guard trace for the constraint.
	 * 
	 * @param evlExecution
	 * @param tracedGuard
	 * @throws EolIncrementalExecutionException
	 */
	@SuppressWarnings("unchecked")
	public void createGuardTrace() throws EolIncrementalExecutionException {
		if (guardBlock != null) {

			IGuardTrace guard = trace.guard().get();
			if (guard == null) {
				try {
					guard = trace.createGuardTrace();
					((TracedExecutableBlock<IGuardTrace, Boolean>) guardBlock).setCurrentTrace(guard);
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException(
							"Can't create GuardTrace for Invariant " + getName() + ".");
				}
			}
		}
	}

	/**
	 * Create a new check trace for the constraint
	 * 
	 * @param evlExecution
	 * @param tracedCheck
	 * @throws EolIncrementalExecutionException
	 */
	@SuppressWarnings("unchecked")
	public void createCheckTrace() throws EolIncrementalExecutionException {
		if (checkBlock != null) {
			ICheckTrace check = trace.check().get();
			if (check == null) {
				try {
					check = trace.createCheckTrace();
					((TracedExecutableBlock<ICheckTrace, Boolean>) checkBlock).setCurrentTrace(check);
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException(
							"Can't create GuardTrace for Invariant " + getName() + ".");
				}
			}
		}
	}

	/**
	 * Create a new message trace for the constraint
	 * 
	 * @param evlExecution
	 * @param tracedMessage
	 * @throws EolIncrementalExecutionException
	 */
	@SuppressWarnings("unchecked")
	public void createMessageTrace() throws EolIncrementalExecutionException {
		if (messageBlock != null) {

			IMessageTrace message = trace.message().get();
			if (message == null) {
				try {
					message = trace.createMessageTrace();
					((TracedExecutableBlock<IMessageTrace, String>) messageBlock).setCurrentTrace(message);
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException(
							"Can't create MessageTrace for Invariant " + getName() + ".");
				}
			}
		}
	}

	public boolean isListeningToChagnes() {
		return listeningToChagnes;
	}

	public void setListeningToChagnes(boolean listeningToChagnes) {
		this.listeningToChagnes = listeningToChagnes;
	}

	private void removeUnsatisfiedConstraint(IEvlContext context, Object self) {
		Iterator<UnsatisfiedConstraint> it = context.getUnsatisfiedConstraints().iterator();
		while (it.hasNext()) {
			UnsatisfiedConstraint current = it.next();
			if (current.getConstraint().equals(this) && current.getInstance().equals(self)) {
				it.remove();
			}
		}
	}

}
