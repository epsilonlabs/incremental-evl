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

import org.eclipse.epsilon.eol.engine.incremental.EOLIncrementalExecutionException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.recording.IPropertyAccess;
import org.eclipse.epsilon.eol.execute.introspection.recording.PropertyAccessRecorder;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.trace.ConstraintTrace;

/**
 * Subclass of {@link Constraint} for use with incremental evaluation and
 * traces. This bypasses the default checking of the {@link ConstraintTrace}'s.
 * 
 * @author Jonathan Co
 *
 */
// FIXME this could be merged with the Constraint and use a flag
public class TracedConstraint extends Constraint {
	
	//private List<IModel> models;
	
	private final PropertyAccessRecorder recorder;


	public TracedConstraint(IncrementalEvlModule incrementalEvlModule, PropertyAccessRecorder recorder) {
		setModule(incrementalEvlModule);
		//this.models = incrementalEvlModule.getContext().getModelRepository().getModels();
		this.recorder = recorder;
	}


	@Override
	public boolean check(Object self, IEvlContext context, boolean checkType) throws EolRuntimeException {
		
		// First look in the trace
		if (context.getConstraintTrace().isChecked(this,self)){
			return context.getConstraintTrace().isSatisfied(this,self);
		}
		// Return immediately if constraint does not apply
		if (!appliesTo(self, context, checkType))
			return false;
		
		removeOldUnsatisfiedConstraint(self, context);
		final UnsatisfiedConstraint unsatisfiedConstraint = preprocessCheck(self, context);
		// Traces are added via notifications
		recorder.startRecording();
		Boolean result = checkBlock.execute(context, false);
		recorder.stopRecording();
		String moduleElementId = null;
		try {
			moduleElementId = ((IncrementalEvlModule) module).getModuleElementId(this);
			for (IPropertyAccess pa : recorder.getPropertyAccesses().unique()) {
				String elementId = null;
				// FIXME The IntrospectionManager should return model, object and property!!
				for (IModel model : context.getModelRepository().getModels()) {
					if (model.knowsAboutProperty(pa.getModelElement(), pa.getPropertyName())) {
						elementId = model.getElementId(pa.getModelElement());
						break;
					}
				}
				try {
					((IncrementalEvlModule) module).getExecutionTraceManager().createExecutionTrace(moduleElementId, elementId, pa.getPropertyName());
				} catch (EOLIncrementalExecutionException e) {
					throw new EolRuntimeException("Error creating execution traces: " + e.getMessage(), this);
				}
			}
		} catch (EolRuntimeException e) {
			throw new EolRuntimeException("Error getting module Id", this);
		}
		return postprocessCheck(self, context, unsatisfiedConstraint, result);
	}

	
	private void removeOldUnsatisfiedConstraint(Object self, IEvlContext context) {
		Iterator<UnsatisfiedConstraint> it = context.getUnsatisfiedConstraints().iterator();
		while(it.hasNext()) {
			UnsatisfiedConstraint current = it.next();
			if(current.getConstraint().equals(this) && current.getInstance().equals(self)) {
				it.remove();
			}
		}
	}
}
