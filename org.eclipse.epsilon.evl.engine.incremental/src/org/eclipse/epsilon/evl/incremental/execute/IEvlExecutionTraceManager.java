/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.execute;

import org.eclipse.epsilon.base.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;

public interface IEvlExecutionTraceManager
		extends IEolExecutionTraceManager<IEvlExecutionTraceRepository> {

	SatisfiesInvocationExecutionListener getSatisfiesListener();

}
