/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
/*******************************************************************************
 ** Evl model Interface automatically generated on 2017-10-18.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.compile.m3.Metamodel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.incremental.models.AbstractEolTraceModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.incremental.trace.evl.*;

public abstract class AbstractEvlTraceModel extends AbstractEolTraceModel {
    
    private static final Set<String> KNOWN_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"EvlConstraint","EvlContext","Guard","Check","Message"}
                ))
            );
    
    private static final Set<String> INSTANTIATABLE_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"EvlConstraint","EvlContext","Guard","Check","Message"}
                ))
            );
    
    private static final Map<String, Set<String>> TYPE_HIERARCHY;
    static {
        TYPE_HIERARCHY = new HashMap<String, Set<String>>();    
        TYPE_HIERARCHY.put("EvlConstraint", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ModuleElement","EvlConstraint"})));    
        TYPE_HIERARCHY.put("EvlContext", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ModuleElement","EvlContext"})));    
        TYPE_HIERARCHY.put("Guard", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ExecutableBlock","Guard"})));    
        TYPE_HIERARCHY.put("Check", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ExecutableBlock","Check"})));    
        TYPE_HIERARCHY.put("Message", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ExecutableBlock","Message"})));        
    }
    
    private static final Map<String, Set<String>> TYPE_PROPERTIES;
    static {
        TYPE_PROPERTIES = new HashMap<String, Set<String>>();    
        TYPE_PROPERTIES.put("EvlConstraint", new HashSet<String>(Arrays.asList(new String[]{"id","uri","executableblocks"})));    
        TYPE_PROPERTIES.put("EvlContext", new HashSet<String>(Arrays.asList(new String[]{"id","uri","executableblocks"})));    
        TYPE_PROPERTIES.put("Guard", new HashSet<String>(Arrays.asList(new String[]{"id","uri","traces","owner","result"})));    
        TYPE_PROPERTIES.put("Check", new HashSet<String>(Arrays.asList(new String[]{"id","uri","traces","owner","result"})));    
        TYPE_PROPERTIES.put("Message", new HashSet<String>(Arrays.asList(new String[]{"id","uri","traces","owner","result"})));        
    }
    
    @Override
    public Metamodel getMetamodel(StringProperties properties, IRelativePathResolver resolver) {
        // TODO Generate AbstractEvlTraceModel.getMetamodel
        return null;
    }
    
    @Override
    public String getTypeNameOf(Object instance) {
        if (!isModelElement(instance))
            throw new IllegalArgumentException("Not a valid Trace model element: " + instance + " (" + instance.getClass().getCanonicalName() + ") ");
    
        return ((Class<?>)getTypeOf(instance)).getName();
    }
    
    @Override
    public Object getTypeOf(Object instance) {
        Object type = super.getTypeOf(instance);
        if (type == null) {
            if (instance instanceof EvlConstraint) {
	            type = EvlConstraint.class;
	        }
            else if (instance instanceof EvlContext) {
	            type = EvlContext.class;
	        }
            else if (instance instanceof Guard) {
	            type = Guard.class;
	        }
            else if (instance instanceof Check) {
	            type = Check.class;
	        }
            else if (instance instanceof Message) {
	            type = Message.class;
	        }
	    }        
        return type;
    }
    
    @Override
    public boolean hasType(String type) {
        boolean result = super.hasType(type);
        if (!result) {
            return KNOWN_TYPES.contains(type);
        }
        return result;
    }
    
    @Override
    public boolean isInstantiable(String type) {
        if (!hasType(type)) {
            return false;
        }
        boolean result = super.isInstantiable(type);
        if (!result) {
            result = INSTANTIATABLE_TYPES.contains(type);
        }
        return result;
    }
    
    @Override
    public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
        boolean result = super.isOfKind(instance, type);
        if (!result) {
            if (instance instanceof EvlConstraint) {
	            result = TYPE_HIERARCHY.get("EvlConstraint").contains(type);
	        }
            else if (instance instanceof EvlContext) {
	            result = TYPE_HIERARCHY.get("EvlContext").contains(type);
	        }
            else if (instance instanceof Guard) {
	            result = TYPE_HIERARCHY.get("Guard").contains(type);
	        }
            else if (instance instanceof Check) {
	            result = TYPE_HIERARCHY.get("Check").contains(type);
	        }
            else if (instance instanceof Message) {
	            result = TYPE_HIERARCHY.get("Message").contains(type);
	        }	        
        }        
        return result;
    }
    
    @Override
    public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
        boolean result = super.isOfType(instance, type);
        if (!result) {
            if (instance instanceof EvlConstraint) {
	            result = "EvlConstraint".equals(type);
	        }
            else if (instance instanceof EvlContext) {
	            result = "EvlContext".equals(type);
	        }
            else if (instance instanceof Guard) {
	            result = "Guard".equals(type);
	        }
            else if (instance instanceof Check) {
	            result = "Check".equals(type);
	        }
            else if (instance instanceof Message) {
	            result = "Message".equals(type);
	        }	        
        }  
        return result;
    }
  
    @Override
    public boolean knowsAboutProperty(Object instance, String property) {
        boolean result = super.knowsAboutProperty(instance, property);
        if (!result) {
            if (instance instanceof EvlConstraint) {
	            result = TYPE_PROPERTIES.get("EvlConstraint").contains(property);
	        }
            else if (instance instanceof EvlContext) {
	            result = TYPE_PROPERTIES.get("EvlContext").contains(property);
	        }
            else if (instance instanceof Guard) {
	            result = TYPE_PROPERTIES.get("Guard").contains(property);
	        }
            else if (instance instanceof Check) {
	            result = TYPE_PROPERTIES.get("Check").contains(property);
	        }
            else if (instance instanceof Message) {
	            result = TYPE_PROPERTIES.get("Message").contains(property);
	        }        
	        
        }
        return result;
    }    
    
}