 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.Satisfies;
import org.eclipse.epsilon.evl.incremental.trace.SatisfiesHasSatsfies;

/**
 * Implementation of Satisfies. 
 */
public class SatisfiesImpl implements Satisfies {

    /** The all */
    private boolean all;

    /** The satsfies relation */
    private final SatisfiesHasSatsfies satsfies;

    /**
     * Instantiates a new Satisfies.
     */
    public SatisfiesImpl() {
        satsfies = new SatisfiesHasSatsfiesImpl(this);
    }
    
    @Override
    public boolean getAll() {
        return all;
    }
    
    
    @Override
    public void setAll(boolean value) {
        this.all = value;
    }   
     
    @Override
    public SatisfiesHasSatsfies satsfies() {
        return satsfies;
    }
 

}