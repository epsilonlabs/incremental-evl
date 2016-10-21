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
package org.eclipse.epsilon.evl.incremental;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.dom.ExecutableBlock;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.dom.Fix;
import org.eclipse.epsilon.evl.execute.EvlOperationFactory;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.dom.TraceConstraint;
import org.eclipse.epsilon.evl.incremental.trace.IPropertyAccessTrace;
import org.eclipse.epsilon.evl.incremental.trace.orient.OrientPropertyAccessTraceFactory;
import org.eclipse.epsilon.evl.parse.EvlParser;


/**
 * The Class IncrementalEvlModule.
 */
// FIXME This changes should be merged into EVL Moudle and use the incremental execution flag
// to enable the incremental behaviour
// FIXME Some of this API should belong to the base Eol Module (e.g. the trace model API can be shared by all languages
public class IncrementalEvlModule extends EvlModule {
	
	private boolean incrementalMode = true;
	

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.evl.EvlModule#execute()
	 */
	@Override
	public Object execute() throws EolRuntimeException {
		if (!incrementalMode) {
			return super.execute();
		}
		if (hasTrace()) {
			return null;
		}
		prepareContext(context);
		context.setOperationFactory(new EvlOperationFactory());
		context.getFrameStack().put(Variable.createReadOnlyVariable("thisModule", this));
		
		// Perform evaluation
		execute(getPre(), context);
		for (ConstraintContext conCtx : getConstraintContexts()) { 
			conCtx.checkAll(context);	
		}		
		if (fixer != null) {
			fixer.fix(this);
		}
		execute(getPost(), context);
		getTrace().commit();
		setHasTrace(true);
		for (UnsatisfiedConstraint uc : context.getUnsatisfiedConstraints()) {
			System.out.println(uc.getMessage());
		}
		return null;
	} 
	
	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.evl.EvlModule#adapt(org.eclipse.epsilon.common.parse.AST, org.eclipse.epsilon.common.module.ModuleElement)
	 */
	@Override
	public ModuleElement adapt(AST cst, ModuleElement parentAst) {
		switch (cst.getType()) {
			case EvlParser.FIX: return new Fix();
			case EvlParser.DO: return new ExecutableBlock<Void>(Void.class);
			case EvlParser.TITLE: return new ExecutableBlock<String>(String.class);
			case EvlParser.MESSAGE: return new ExecutableBlock<String>(String.class);
			case EvlParser.CHECK: return new ExecutableBlock<Boolean>(Boolean.class);
			case EvlParser.GUARD: return new ExecutableBlock<Boolean>(Boolean.class);
//			case EvlParser.CONSTRAINT: return new Constraint();
//			case EvlParser.CRITIQUE: return new Constraint();
			// Modified to return the appropriate subclasses of Constraint
			case EvlParser.CONSTRAINT: return new TraceConstraint();
			case EvlParser.CRITIQUE: return new TraceConstraint();
			// ----
			case EvlParser.CONTEXT: return new ConstraintContext();
		}
		return super.adapt(cst, parentAst);
	}
	
	// ======================== From Trace Evl Context
	
	private IPropertyAccessTrace trace = null;
	private boolean hasTrace = false;

	private Map<IModel, IPropertyChangeListener> listenerMap = new HashMap<IModel, IPropertyChangeListener>();

	public IPropertyAccessTrace getTrace() {
		if (trace == null) {
			this.trace = OrientPropertyAccessTraceFactory.getInstance()
					.getTrace();
		}
		return this.trace;
	}

	public boolean hasTrace() {
		return this.hasTrace;
	}

	public void setHasTrace(boolean trace) {
		this.hasTrace = trace;
	}
	
	public void attachChangeListeners() {
		// Attach change listeners to models
		for (IModel model : this.getContext().getModelRepository().getModels()) {
			if (this.listenerMap.containsKey(model)) {
				continue;
			}

			if (model instanceof EmfModel) {
				EmfModel emfModel = (EmfModel) model;
				EmfPropertyChangeListener listener = new EmfPropertyChangeListener(
						emfModel, this);
				listenerMap.put(emfModel, listener);
				((EmfModel) model).getResource().eAdapters().add(listener);
			}
		}
	}
	
//	for (Entry<IModel, IPropertyChangeListener> entry : this.listenerMap
//			.entrySet()) {
//		IModel model = entry.getKey();
//		if (model instanceof EmfModel) {
//			EList<Adapter> adapters = ((EmfModel) model).getResource()
//					.eAdapters();
//			int indexOf = adapters.indexOf(entry.getValue());
//			adapters.remove(indexOf);
//		}
//	}
//	this.trace.shutdown();

	
}
