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
package org.eclipse.epsilon.evl.incremental.execute.introspection.recording;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.evl.dom.Constraint;

public class SimpleOperationInvocations implements IOperationInvocations<SimpleOperationInvocation<Constraint>> {
	
	private final List<SimpleOperationInvocation<Constraint>> storage = new ArrayList<>();

	@Override
	public void add(SimpleOperationInvocation<Constraint> operationInvocation) {
		storage.add(operationInvocation);
	}

	@Override
	public Collection<SimpleOperationInvocation<Constraint>> all() {
		return Collections.unmodifiableCollection(storage);
	}

	@Override
	public void clear() {
		storage.clear();
	}

	@Override
	public boolean isEmpty() {
		return storage.isEmpty();
	}

	@Override
	public Set<SimpleOperationInvocation<Constraint>> unique() {
		return Collections.unmodifiableSet(new HashSet<>(storage));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((storage == null) ? 0 : storage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleOperationInvocations other = (SimpleOperationInvocations) obj;
		if (storage == null) {
			if (other.storage != null)
				return false;
		} else if (!storage.equals(other.storage))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return storage.toString();
	}

}
