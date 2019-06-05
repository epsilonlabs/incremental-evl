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
package org.eclipse.epsilon.evl.incremental.execute.context;

import java.util.Optional;

import org.eclipse.epsilon.base.incremental.execute.context.IIncrementalBaseContext;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.IEvlModuleIncremental;
import org.eclipse.epsilon.evl.incremental.dom.TracedConstraint;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;

/**
 * The Interface IIncrementalEvlContext allows extra information about incremental
 * execution to be stored and accessed.
 *
 */
public interface IIncrementalEvlContext extends IIncrementalBaseContext, IEvlContext {

	@Override
	IEvlExecutionTraceManager getTraceManager();
	

	@Override
	default IEvlModuleIncremental getModule() {
		return (IEvlModuleIncremental) this.getModule();
	}
	
	Optional<UnsatisfiedConstraint> getUnsatisfiedConstraint(TracedConstraint constraint, Object self);
	
	void mapUnsatisfiedConstraint(TracedConstraint constraint, UnsatisfiedConstraint uc, Object self);

	void unmapUnsatisfiedConstraint(TracedConstraint constraint, UnsatisfiedConstraint uc, Object self);

}