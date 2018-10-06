/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.execute;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.epsilon.eol.incremental.trace.TraceElement;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class OrientDbUtil {
	
	/**
	 * Creates a new In-Memory database, no user or password.
	 * 
	 * @param name
	 * @return
	 */
	public static OrientGraphFactory getInMemoryFactory(String name) {
		OrientGraphFactory factory = new OrientGraphFactory(String.format("memory:%s", name));
		return factory;
	}
	
	//TODO Add other connection methods
	
    /**
     * Wrap the OrientDB vertices in the EOL Incremental implementations. 
     * 
     * @throws InvocationTargetException    If invocation of the constructor method fails 
     * @throws IllegalArgumentException     If invocation of the constructor method fails
     * @throws IllegalAccessException       If invocation of the constructor method fails
     * @throws InstantiationException       If invocation of the constructor method fails
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T, W extends TraceElement> W wrap(Class<W> cls, T item) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<W> cons = (Constructor<W>) cls.getConstructors()[0];        // We know we only have one
        return cons.newInstance(item);
    }

}
