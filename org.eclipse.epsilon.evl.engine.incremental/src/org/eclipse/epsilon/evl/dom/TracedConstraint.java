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

import java.util.Iterator;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
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
	
	private IInvariantTrace trace;

	public IInvariantTrace getTrace() {
		return trace;
	}

	public void setTrace(IInvariantTrace trace) {
		this.trace = trace;
	}
	
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException{
		if (checkType && !constraintContext.getAllOfSourceKind(context).contains(object)) return false;

		if (guardBlock != null) {
			return guardBlock.execute(context, Variable.createReadOnlyVariable("self", object));
		}
		else {
			return true;
		}
	}
	

	@Override
	public boolean check(Object self, IEvlContext context, boolean checkType) throws EolRuntimeException {
		
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
		//((TracedExecutableBlock<?>)checkBlock).startRecording();
		Boolean result = checkBlock.execute(context, false);
		//((TracedExecutableBlock<?>)checkBlock).stopRecording();
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
