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
package org.eclipse.epsilon.evl.dom;

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
import org.eclipse.epsilon.evl.IncrementalEvlModule;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.execute.introspection.recording.SatisfiesOperationInvocationRecorder;
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
	
	//private final PropertyAccessRecorder recorder;
	//private final SatisfiesOperationInvocationRecorder satisfiesRecorder;
	//private ArrayList<IModuleElementAccessListener> accessListeners = new ArrayList<>();
	
	
	public TracedConstraint(IncrementalEvlModule incrementalEvlModule, PropertyAccessRecorder recorder, 
			SatisfiesOperationInvocationRecorder satisfiesRecoder) {
		setModule(incrementalEvlModule);
		//this.recorder = recorder;
		//this.satisfiesRecorder = satisfiesRecoder;
		//addAccessListener(satisfiesRecoder);
	}
	
//	public void addAccessListener(IModuleElementAccessListener listener) {
//		accessListeners.add(listener);
//	}
//	
//	public boolean removeAccessListener(IModuleElementAccessListener listener) {
//		return accessListeners.remove(listener);
//	}

	@Override
	public boolean check(Object self, IEvlContext context, boolean checkType) throws EolRuntimeException {
		
//		for (IModuleElementAccessListener listener : accessListeners ) {
//			listener.accessed(self);
//		}
		// First look in the cache
		if (context.getConstraintTrace().isChecked(this,self)){
			return context.getConstraintTrace().isSatisfied(this,self);
		}
		// Return immediately if constraint does not apply
		if (!appliesTo(self, context, checkType))
			return false;
		
		// FIXME Why remove?
		//removeOldUnsatisfiedConstraint(self, context);
		
		final UnsatisfiedConstraint unsatisfiedConstraint = preprocessCheck(self, context);
		// Traces are added via notifications
		//recorder.startRecording();
		Boolean result = checkBlock.execute(context, false);
		//recorder.stopRecording();
		boolean postResult = postprocessCheck(self, context, unsatisfiedConstraint, result);
		return postResult;
		
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
