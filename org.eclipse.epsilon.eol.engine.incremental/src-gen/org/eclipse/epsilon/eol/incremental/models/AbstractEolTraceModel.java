/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
/*******************************************************************************
 ** Eol model Interface automatically generated on 2017-09-29.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.eol.incremental.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.compile.m3.Metamodel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.Model;
import org.eclipse.epsilon.incremental.trace.eol.*;

public abstract class AbstractEolTraceModel extends Model implements ITraceModel  {
    
    private static final Set<String> KNOWN_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"ExecutionContext","Script","ModuleElement","Trace","ModelReference","ModelElement","Property"}
                ))
            );
    
    private static final Set<String> INSTANTIATABLE_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"ExecutionContext","Script","ModuleElement","Trace","ModelReference","ModelElement","Property"}
                ))
            );
    
    private static final Map<String, Set<String>> TYPE_HIERARCHY;
    static {
        TYPE_HIERARCHY = new HashMap<String, Set<String>>();    
        TYPE_HIERARCHY.put("ExecutionContext", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","ExecutionContext"})));    
        TYPE_HIERARCHY.put("Script", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","Script"})));    
        TYPE_HIERARCHY.put("ModuleElement", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","ModuleElement"})));    
        TYPE_HIERARCHY.put("Trace", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","Trace"})));    
        TYPE_HIERARCHY.put("ModelReference", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","ModelReference"})));    
        TYPE_HIERARCHY.put("ModelElement", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","ModelElement"})));    
        TYPE_HIERARCHY.put("Property", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","Property"})));        
    }
    
    private static final Map<String, Set<String>> TYPE_PROPERTIES;
    static {
        TYPE_PROPERTIES = new HashMap<String, Set<String>>();    
        TYPE_PROPERTIES.put("ExecutionContext", new HashSet<String>(Arrays.asList(new String[]{"id","for","traces","uses"})));    
        TYPE_PROPERTIES.put("Script", new HashSet<String>(Arrays.asList(new String[]{"id","scriptId","moduleElements"})));    
        TYPE_PROPERTIES.put("ModuleElement", new HashSet<String>(Arrays.asList(new String[]{"id","moduleId","definedIn","traces"})));    
        TYPE_PROPERTIES.put("Trace", new HashSet<String>(Arrays.asList(new String[]{"id","createdIn","traces","involves","accesses"})));    
        TYPE_PROPERTIES.put("ModelReference", new HashSet<String>(Arrays.asList(new String[]{"id","uri","executionContext","owns"})));    
        TYPE_PROPERTIES.put("ModelElement", new HashSet<String>(Arrays.asList(new String[]{"id","elementId","traces","contains","owner"})));    
        TYPE_PROPERTIES.put("Property", new HashSet<String>(Arrays.asList(new String[]{"id","name","modelElement","traces","value"})));        
    }
    
    @Override
    public Metamodel getMetamodel(StringProperties properties, IRelativePathResolver resolver) {
        // TODO Implement OrientDbEvlTraceModel.isInstantiable
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
        if (instance instanceof ExecutionContext) {
            return ExecutionContext.class;
        }
        if (instance instanceof Script) {
            return Script.class;
        }
        if (instance instanceof ModuleElement) {
            return ModuleElement.class;
        }
        if (instance instanceof Trace) {
            return Trace.class;
        }
        if (instance instanceof ModelReference) {
            return ModelReference.class;
        }
        if (instance instanceof ModelElement) {
            return ModelElement.class;
        }
        if (instance instanceof Property) {
            return Property.class;
        }        
        return null;
    }
    
    @Override
    public boolean hasType(String type) {
        return KNOWN_TYPES.contains(type);
    }
    
    @Override
    public boolean isInstantiable(String type) {
        if (!hasType(type)) {
            return false;
        }
        return INSTANTIATABLE_TYPES.contains(type);
    }
    
    @Override
    public boolean isModelElement(Object instance) {
        return instance instanceof TraceElement;
    }
    
    @Override
    public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
        if (instance instanceof ExecutionContext) {
            return TYPE_HIERARCHY.get("ExecutionContext").contains(type);
        }
        if (instance instanceof Script) {
            return TYPE_HIERARCHY.get("Script").contains(type);
        }
        if (instance instanceof ModuleElement) {
            return TYPE_HIERARCHY.get("ModuleElement").contains(type);
        }
        if (instance instanceof Trace) {
            return TYPE_HIERARCHY.get("Trace").contains(type);
        }
        if (instance instanceof ModelReference) {
            return TYPE_HIERARCHY.get("ModelReference").contains(type);
        }
        if (instance instanceof ModelElement) {
            return TYPE_HIERARCHY.get("ModelElement").contains(type);
        }
        if (instance instanceof Property) {
            return TYPE_HIERARCHY.get("Property").contains(type);
        }        
        return false;
    }
    
    @Override
    public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
        if (instance instanceof ExecutionContext) {
            return "ExecutionContext".equals(type);
        }
        if (instance instanceof Script) {
            return "Script".equals(type);
        }
        if (instance instanceof ModuleElement) {
            return "ModuleElement".equals(type);
        }
        if (instance instanceof Trace) {
            return "Trace".equals(type);
        }
        if (instance instanceof ModelReference) {
            return "ModelReference".equals(type);
        }
        if (instance instanceof ModelElement) {
            return "ModelElement".equals(type);
        }
        if (instance instanceof Property) {
            return "Property".equals(type);
        }  
        return false;
    }
  
    @Override
    public boolean knowsAboutProperty(Object instance, String property) {        
        if (instance instanceof ExecutionContext) {
            return TYPE_PROPERTIES.get("ExecutionContext").contains(property);
        }        
        if (instance instanceof Script) {
            return TYPE_PROPERTIES.get("Script").contains(property);
        }        
        if (instance instanceof ModuleElement) {
            return TYPE_PROPERTIES.get("ModuleElement").contains(property);
        }        
        if (instance instanceof Trace) {
            return TYPE_PROPERTIES.get("Trace").contains(property);
        }        
        if (instance instanceof ModelReference) {
            return TYPE_PROPERTIES.get("ModelReference").contains(property);
        }        
        if (instance instanceof ModelElement) {
            return TYPE_PROPERTIES.get("ModelElement").contains(property);
        }        
        if (instance instanceof Property) {
            return TYPE_PROPERTIES.get("Property").contains(property);
        }        
        return false;
    }    
    
}