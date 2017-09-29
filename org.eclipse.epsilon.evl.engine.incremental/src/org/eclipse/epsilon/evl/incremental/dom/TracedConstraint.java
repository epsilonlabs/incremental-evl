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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.execute.introspection.recording.IPropertyAccess;
import org.eclipse.epsilon.eol.execute.introspection.recording.PropertyAccessRecorder;
import org.eclipse.epsilon.eol.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.eol.incremental.execute.IModuleElementAccessListener;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IncrementalEvlModule;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesOperationInvocationRecorder;
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
	
	private final PropertyAccessRecorder recorder;
	private final SatisfiesOperationInvocationRecorder satisfiesRecorder;
	private ArrayList<IModuleElementAccessListener> accessListeners = new ArrayList<>();
	
	
	public TracedConstraint(IncrementalEvlModule incrementalEvlModule, PropertyAccessRecorder recorder, 
			SatisfiesOperationInvocationRecorder satisfiesRecoder) {
		setModule(incrementalEvlModule);
		this.recorder = recorder;
		this.satisfiesRecorder = satisfiesRecoder;
		addAccessListener(satisfiesRecoder);
	}
	
	public void addAccessListener(IModuleElementAccessListener listener) {
		accessListeners.add(listener);
	}
	
	public boolean removeAccessListener(IModuleElementAccessListener listener) {
		return accessListeners.remove(listener);
	}

	@Override
	public boolean check(Object self, IEvlContext context, boolean checkType) throws EolRuntimeException {
		
		for (IModuleElementAccessListener listener : accessListeners ) {
			listener.accessed(self);
		}
		// First look in the cache
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
				String modelId= null;
				// FIXME The IntrospectionManager should return model, object and property!!
				for (IModel model : context.getModelRepository().getModels()) {
					if (model.knowsAboutProperty(pa.getModelElement(), pa.getPropertyName())) {
						elementId = model.getElementId(pa.getModelElement());
						modelId = ((IIncrementalModel)model).getModelId();
						break;
					}
				}
				Set<String> satisfiesConstraints = satisfiesRecorder.getOperationInvocations().unique().stream()
						.map(oi -> oi.getParameters())
						.flatMap(List::stream)
						.map(Constraint.class::cast)
						.map(c -> {
							try {
								return ((IncrementalEvlModule) module).getModuleElementId(c);
							} catch (EolRuntimeException e) {
								throw new RuntimeException(e);
							}
						})
						.collect(Collectors.toSet());
			
				IEolExecutionTraceManager executionTraceManager = ((IncrementalEvlModule) module).getExecutionTraceManager();
				((IEvlExecutionTraceManager) executionTraceManager)
						.addTraceInformation(moduleElementId, modelId, elementId, pa.getPropertyName(),
								satisfiesConstraints.toArray(new String[satisfiesConstraints.size()]));
				
			}
		} catch (EolRuntimeException e) {
			throw new EolRuntimeException("Error getting module Id", this);
		} catch (RuntimeException ex) {
			throw new EolRuntimeException(ex.getCause().getMessage(), this);
		}
		boolean postResult = postprocessCheck(self, context, unsatisfiedConstraint, result);
		context.getConstraintTrace().clear();
		return postResult;
		
	}
	
	// Listen to the guard to catch satisfies constraints
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		if (checkType && !constraintContext.getAllOfSourceKind(context).contains(object)) return false;

		if (guardBlock != null) {
			satisfiesRecorder.startRecording();
			Boolean result = guardBlock.execute(context, Variable.createReadOnlyVariable("self", object));
			satisfiesRecorder.stopRecording();
			return result;
		}
		else {
			return true;
		}
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
