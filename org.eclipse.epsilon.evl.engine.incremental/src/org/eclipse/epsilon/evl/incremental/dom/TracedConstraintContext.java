/*******************************************************************************
 * Copyright (c) 2018 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.dom;

import java.util.Collection;

import org.eclipse.epsilon.base.incremental.dom.TracedExecutableBlock;
import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.base.incremental.trace.util.IncrementalUtils;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlRootElementsFactory;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.ICheckTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IMessageTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ConstraintContext that holds a reference to its tracing reference so it can create 
 * ElementAccessTraces and that starts/stops the recording of the guardBlock accesses.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedConstraintContext extends ConstraintContext implements TracedModuleElement<IContextTrace> {

	private static final Logger logger = LoggerFactory.getLogger(TracedConstraintContext.class);

	private IContextTrace moduleElementTrace;
	private IExecutionContext currentContext;
	private int index;

	@Override
	public void setModuleElementTrace(IContextTrace trace) {
		this.moduleElementTrace = trace;
	}

	@Override
	public IContextTrace getModuleElementTrace() {
		return moduleElementTrace;
	}

	@Override
	public IExecutionContext getCurrentContext() {
		return currentContext;
	}

	@Override
	public void setCurrentContext(IExecutionContext currentContext) {
		this.currentContext = currentContext;
	}

	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		this.index = cst.childIndex;
	}

	@Override
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		final IModel owningModel = context.getModelRepository().getOwningModel(object);
		if (checkType && !owningModel.isOfKind(object, getTypeName())) {
			return false;
		}
		@SuppressWarnings("unchecked")
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> tracedEvlContext = 
				(IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>>) context;
		if (moduleElementTrace == null) {
			createModuleElementTraces(tracedEvlContext);	
		}
		populateExecutionContext(object, tracedEvlContext, (IIncrementalModel) owningModel);
		if (guardBlock != null) {
			return guardBlock.execute(tracedEvlContext, Variable.createReadOnlyVariable("self", object));
		} else {
			return true;
		}

	}
	
	/**
	 * Create the execution context for the current element and create result traces for the guard
	 * and the check.
	 * @param modelElement
	 * @param context
	 * @param model
	 * @throws EolRuntimeException
	 */
	private void populateExecutionContext(Object modelElement,
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> context,
		final IIncrementalModel model) throws EolRuntimeException {		
		logger.info("createExecutionContext for {}:{}  with element {}", getTypeName(), index,
				modelElement);
		IModelElementTrace elementTrace;
		try {
			currentContext = moduleElementTrace.getOrCreateExecutionContext();
			assert this.currentContext != null;
			elementTrace = IncrementalUtils.getOrCreateModelElementTrace(modelElement, context,
					model);
			if (guardBlock != null) {
				((TracedExecutableBlock<?,?>) guardBlock).setCurrentContext(getCurrentContext());
			}
			getCurrentContext().getOrCreateModelElementVariable("self", elementTrace);
			
			IModuleExecutionTraceRepository<?> executionTraceRepository = context.getTraceManager()
					.getExecutionTraceRepository();
			String moduleUri = context.getModule().getUri().toString();
			IModuleExecutionTrace moduleExecutionTrace = executionTraceRepository
					.getModuleExecutionTraceByIdentity(moduleUri);
			if (moduleExecutionTrace == null) {
				throw new EolIncrementalExecutionException(
						"A moduleExecutionTrace was not found for the module under execution. "
								+ "The module execution trace must be created at the begining of the execution of the module.");
			}
			moduleExecutionTrace.getOrCreateAccess(IElementAccess.class, moduleElementTrace, getCurrentContext(), elementTrace);
			for (Constraint c : constraints) {
				TracedConstraint tc = (TracedConstraint) c;
				tc.setCurrentContext(getCurrentContext());
				if (tc.hasGuard()) {
					IGuardTrace gt = tc.getOrCreateGuardTrace();
					gt.getOrCreateGuardResult(getCurrentContext());
					
				}
				if (tc.hasCheck()) {
					ICheckTrace ct = tc.getOrCreateCheckTrace();
					ct.getOrCreateCheckResult(getCurrentContext());
				}		
			}
		} catch (EolIncrementalExecutionException e) {
			throw new EolRuntimeException("There was an error creating the incremental trace information.", e);
		}
	}
	
	
	/**
	 * Create module element traces for the ConstraintContext guard and all the constraints in the
	 * context.
	 * 
	 * @param context      			The IEolContext of the execution
	 * @throws EolRuntimeException if there was an error creating the trace information.
	 */
	@SuppressWarnings("unchecked")
	private void createModuleElementTraces(
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> context) throws EolRuntimeException {
		logger.info("createModuleElementTraces for {}:{}", getTypeName(), index);
		if (moduleElementTrace == null) {
			try {
				String moduleUri = context.getModule().getUri().toString();
				IEvlModuleTrace moduleExecutionTrace = getModuleExecutionTrace(context, moduleUri);
				moduleElementTrace = moduleExecutionTrace.getOrCreateContextTrace(getTypeName(), index);
				if (guardBlock != null) {
					try {
						IGuardTrace guard = moduleElementTrace.getOrCreateGuardTrace();
						((TracedExecutableBlock<IGuardTrace, Boolean>) guardBlock).setModuleElementTrace(guard);
						guard.contextModuleElement().create(moduleElementTrace);
					} catch (TraceModelConflictRelation e) {
						throw new EolRuntimeException("Can't create GuardTrace for Context " + getTypeName() + ".", e);
					}
				}
				for (Constraint c : constraints) {
					TracedConstraint tc = (TracedConstraint) c;
					IInvariantTrace invariantTrace = moduleElementTrace.getOrCreateInvariantTrace(tc.getName());
					try {
						invariantTrace.contextModuleElement().create(moduleElementTrace);
						tc.setModuleElementTrace(invariantTrace);
						tc.setCurrentContext(getCurrentContext());
						if (tc.hasGuard()) {
							IGuardTrace gt = tc.getOrCreateGuardTrace();
							if (!gt.contextModuleElement().create(moduleElementTrace)) {
								logger.error("Not possible to create parent trace for guard");
							}
						}
						if (tc.hasCheck()) {
							ICheckTrace ct = tc.getOrCreateCheckTrace();
							if (!ct.contextModuleElement().create(moduleElementTrace)) {
								logger.error("Not possible to create parent trace for check");
							}
						}
						if (tc.hasMessage()) {
							IMessageTrace mt = tc.getOrCreateMessageTrace();
							if (!mt.contextModuleElement().create(moduleElementTrace)) {
								logger.error("Not possible to create parent trace for message");
							}
						}
					} catch (TraceModelConflictRelation e) {
						throw new EolRuntimeException("Not possible to create module traces traces for invariant " + c, e);
					}
				}
			}
			catch (EolIncrementalExecutionException e) {
				throw new EolRuntimeException("There was an error creating the incremental trace information.", e);
			}
		}
	}

	/**
	 * Gets the module execution trace for the current module.
	 *
	 * @param context 				the execution context
	 * @param moduleUri 			the module uri
	 * @return the module execution trace
	 * @throws EolRuntimeException if the module execution trace was not found.
	 */
	private IEvlModuleTrace getModuleExecutionTrace(
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlRootElementsFactory, IEvlExecutionTraceManager<IEvlModuleTraceRepository, IEvlRootElementsFactory>> context,
			String moduleUri) throws EolRuntimeException {
		IEvlModuleTraceRepository repo = context.getTraceManager().getExecutionTraceRepository();
		IEvlModuleTrace moduleExecutionTrace = repo.getEvlModuleTraceByIdentity(moduleUri);
		if (moduleExecutionTrace == null) {
			throw new EolRuntimeException(
					"A moduleExecutionTrace was not found for the module under execution. "
							+ "The module execution trace must be created at the begining of the execution of the module.");
		}
		return moduleExecutionTrace;
	}



	public void goOnline() {
		for (Constraint c : constraints) {
			((TracedConstraint) c).setListeningToChagnes(true);
		}
	}

	public void goOffline() {
		for (Constraint c : constraints) {
			((TracedConstraint) c).setListeningToChagnes(false);
		}
	}

	// Hack to solve bug #538175
	public Collection<?> getAllOfSourceKind(IEvlContext context)
			throws EolModelElementTypeNotFoundException, EolModelNotFoundException {
		String modelName = getType(context).getModel().getName();

		return context.getModelRepository().getModelByName(modelName).getAllOfKind(getTypeName());
	}

}
