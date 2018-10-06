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
import java.util.Optional;

import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckResult;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardResult;
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

	private IInvariantTrace moduleElementTrace;
	private IExecutionContext currentContext;

	/**
	 * Flag to indicate that we are on live mode, i.e. listening to model changes
	 */
	private boolean listeningToChagnes = false;

	@Override
	public void setModuleElementTrace(IInvariantTrace trace) {
		this.moduleElementTrace = trace;
	}

	@Override
	public IInvariantTrace getModuleElementTrace() {
		return moduleElementTrace;
	}
	
	@Override
	public void setCurrentContext(IExecutionContext currentContext) {
		this.currentContext = currentContext;
	}

	@Override
	public IExecutionContext getCurrentContext() {
		return this.currentContext;
	}
	
	public boolean shouldBeChecked(Object modelElement, IEvlContext context) throws EolRuntimeException {
		
		boolean result = !isLazy(context);
		if (result && (guardBlock != null)) {
			result &= appliesTo(modelElement, context);
			try {
				@SuppressWarnings("unchecked")
				IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> tracedEvlContext = (IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>>) context;
				IGuardTrace guardTrace = (IGuardTrace) ((TracedExecutableBlock<?, ?>) guardBlock).getModuleElementTrace();
				IGuardResult guardResult = tracedEvlContext.getTraceManager().getExecutionTraceRepository()
						.findResultInGuard(guardTrace, getCurrentContext());
				guardResult.setValue(result);
			} catch (EolRuntimeException e) {
				logger.error("Error setting guard result", e);
			}
		}
		return result;
	}
	
	@Override
	public Optional<UnsatisfiedConstraint> check(Object self, IEvlContext context) throws EolRuntimeException {
		logger.info("Check {} for {}", getName(), self);
		UnsatisfiedConstraint unsatisfiedConstraint = preprocessCheck(self, context);
		@SuppressWarnings("unchecked")
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> tracedEvlContext = (IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>>) context;
		
		boolean result = false;
		ConstraintTrace trace;
		
		// Look for a result in the trace first if this constraint is a dependency, otherwise run the check block
		if (checkTrace && (trace = context.getConstraintTrace()).isChecked(this, self)) {
			assert context.getConstraintsDependedOn().contains(this);
			result = trace.isSatisfied(this, self);
		}
		else {
			result = checkBlock.execute(context, false);
			if (!tracedEvlContext.isOnlineExecutionMode() && !context.optimizeConstraintsCache()) {
				context.getConstraintTrace().addChecked(this, self, result);
			}
			ICheckTrace chkTrace = (ICheckTrace) ((TracedExecutableBlock<?, ?>)checkBlock).getModuleElementTrace();
			ICheckResult chkResult = tracedEvlContext.getTraceManager().getExecutionTraceRepository()
					.findResultInCheck(chkTrace, ((TracedExecutableBlock<?, ?>)checkBlock).getCurrentContext());
			if (chkResult == null) {
				throw new EolRuntimeException("Result should have been previously created for the ckeck."
						+ "See TraceContraintContext createExecutionContext");
			} else {
				boolean oldResult = chkResult.getValue();
				if (!oldResult && result) {
					logger.debug("Removing unsatisfied constraint");
					removeUnsatisfiedConstraint(context, self);
				}
			}
			chkResult.setValue(result);
		}
		logger.info("Result: {}", result);
		return !postprocessCheck(self, context, unsatisfiedConstraint, result)
				? Optional.of(unsatisfiedConstraint) : Optional.empty();

	}

	/**
	 * Create a new guard trace for the constraint.
	 * 
	 * @param evlExecution
	 * @param tracedGuard
	 * @throws EolIncrementalExecutionException
	 */
	@SuppressWarnings("unchecked")
	public IGuardTrace getOrCreateGuardTrace() throws EolIncrementalExecutionException {
		IGuardTrace guard = null;
		if (guardBlock != null) {

			guard = moduleElementTrace.guard().get();
			if (guard == null) {
				try {
					guard = moduleElementTrace.getOrCreateGuardTrace();
					((TracedExecutableBlock<IGuardTrace, Boolean>) guardBlock).setModuleElementTrace(guard);
					((TracedExecutableBlock<IGuardTrace, Boolean>) guardBlock).setCurrentContext(getCurrentContext());
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException(
							"Can't create GuardTrace for Invariant " + getName() + ".");
				}
			}
		}
		return guard;
	}

	/**
	 * Create a new check trace for the constraint
	 * 
	 * @param evlExecution
	 * @param tracedCheck
	 * @return 
	 * @throws EolIncrementalExecutionException
	 */
	@SuppressWarnings("unchecked")
	public ICheckTrace getOrCreateCheckTrace() throws EolIncrementalExecutionException {
		ICheckTrace check = null;
		if (checkBlock != null) {
			check = moduleElementTrace.check().get();
			if (check == null) {
				try {
					check = moduleElementTrace.getOrCreateCheckTrace();
					((TracedExecutableBlock<ICheckTrace, Boolean>) checkBlock).setModuleElementTrace(check);
					((TracedExecutableBlock<IGuardTrace, Boolean>) checkBlock).setCurrentContext(getCurrentContext());
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException(
							"Can't create GuardTrace for Invariant " + getName() + ".");
				}
			}
		}
		return check;
	}

	/**
	 * Create a new message trace for the constraint
	 * 
	 * @param evlExecution
	 * @param tracedMessage
	 * @throws EolIncrementalExecutionException
	 */
	@SuppressWarnings("unchecked")
	public IMessageTrace getOrCreateMessageTrace() throws EolIncrementalExecutionException {
		IMessageTrace message = null;
		if (messageBlock != null) {
			message = moduleElementTrace.message().get();
			if (message == null) {
				try {
					message = moduleElementTrace.getOrCreateMessageTrace();
					((TracedExecutableBlock<IMessageTrace, String>) messageBlock).setModuleElementTrace(message);
					((TracedExecutableBlock<IMessageTrace, String>) messageBlock).setCurrentContext(getCurrentContext());
				} catch (EolIncrementalExecutionException e) {
					throw new EolIncrementalExecutionException(
							"Can't create MessageTrace for Invariant " + getName() + ".");
				}
			}
		}
		return message;
	}

	public boolean isListeningToChagnes() {
		return listeningToChagnes;
	}

	public void setListeningToChagnes(boolean listeningToChagnes) {
		this.listeningToChagnes = listeningToChagnes;
	}

	public boolean hasGuard() {
		return guardBlock != null;
	}

	public boolean hasCheck() {
		return checkBlock != null;
	}

	public boolean hasMessage() {
		return messageBlock != null;
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
