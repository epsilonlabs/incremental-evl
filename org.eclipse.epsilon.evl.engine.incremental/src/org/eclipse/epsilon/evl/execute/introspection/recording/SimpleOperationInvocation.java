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
package org.eclipse.epsilon.evl.execute.introspection.recording;

import java.util.List;

public class SimpleOperationInvocation<T> implements ISimpleOperationInvocation {
	
	protected final String name;
	protected final List<T> parameters;

	
	public SimpleOperationInvocation(String name, List<T> parameters) {
		super();
		this.name = name;
		this.parameters = parameters;
	}

	@Override
	public String getOperationName() {
		return name;
	}

	@Override
	public List<?> getParameters() {
		return parameters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SimpleOperationInvocation))
			return false;
		SimpleOperationInvocation<?> other = (SimpleOperationInvocation<?>) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "OperationInvocation of '" + name + "' with parameters: " + parameters;
	}

}
