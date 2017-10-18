 /*******************************************************************************
 * This file was automatically generated on: 2017-10-18.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
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
                    new String[] {"Trace","ExecutableBlock","ModuleElement","ElementTrace","ModelElement","Property","TypeTrace","Type"}
                ))
            );
    
    private static final Set<String> INSTANTIATABLE_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"Trace","ExecutableBlock","ModuleElement","ElementTrace","ModelElement","Property","TypeTrace","Type"}
                ))
            );
    
    private static final Map<String, Set<String>> TYPE_HIERARCHY;
    static {
        TYPE_HIERARCHY = new HashMap<String, Set<String>>();    
        TYPE_HIERARCHY.put("Trace", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Trace"})));    
        TYPE_HIERARCHY.put("ExecutableBlock", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ExecutableBlock"})));    
        TYPE_HIERARCHY.put("ModuleElement", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ModuleElement"})));    
        TYPE_HIERARCHY.put("ElementTrace", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Trace","ElementTrace"})));    
        TYPE_HIERARCHY.put("ModelElement", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","ModelElement"})));    
        TYPE_HIERARCHY.put("Property", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","Property"})));    
        TYPE_HIERARCHY.put("TypeTrace", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Trace","TypeTrace"})));    
        TYPE_HIERARCHY.put("Type", new HashSet<String>(Arrays.asList(new String[]{"IdElement","TraceElement","Type"})));        
    }
    
    private static final Map<String, Set<String>> TYPE_PROPERTIES;
    static {
        TYPE_PROPERTIES = new HashMap<String, Set<String>>();    
        TYPE_PROPERTIES.put("Trace", new HashSet<String>(Arrays.asList(new String[]{"id","blocks"})));    
        TYPE_PROPERTIES.put("ExecutableBlock", new HashSet<String>(Arrays.asList(new String[]{"id","uri","traces","owner","result"})));    
        TYPE_PROPERTIES.put("ModuleElement", new HashSet<String>(Arrays.asList(new String[]{"id","uri","executableblocks"})));    
        TYPE_PROPERTIES.put("ElementTrace", new HashSet<String>(Arrays.asList(new String[]{"id","blocks","elements","accesses"})));    
        TYPE_PROPERTIES.put("ModelElement", new HashSet<String>(Arrays.asList(new String[]{"id","uri","traces"})));    
        TYPE_PROPERTIES.put("Property", new HashSet<String>(Arrays.asList(new String[]{"id","uri","traces","value"})));    
        TYPE_PROPERTIES.put("TypeTrace", new HashSet<String>(Arrays.asList(new String[]{"id","blocks","types"})));    
        TYPE_PROPERTIES.put("Type", new HashSet<String>(Arrays.asList(new String[]{"id","uri","traces"})));        
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
        if (instance instanceof Trace) {
            return Trace.class;
        }
        if (instance instanceof ExecutableBlock) {
            return ExecutableBlock.class;
        }
        if (instance instanceof ModuleElement) {
            return ModuleElement.class;
        }
        if (instance instanceof ElementTrace) {
            return ElementTrace.class;
        }
        if (instance instanceof ModelElement) {
            return ModelElement.class;
        }
        if (instance instanceof Property) {
            return Property.class;
        }
        if (instance instanceof TypeTrace) {
            return TypeTrace.class;
        }
        if (instance instanceof Type) {
            return Type.class;
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
        if (instance instanceof Trace) {
            return TYPE_HIERARCHY.get("Trace").contains(type);
        }
        if (instance instanceof ExecutableBlock) {
            return TYPE_HIERARCHY.get("ExecutableBlock").contains(type);
        }
        if (instance instanceof ModuleElement) {
            return TYPE_HIERARCHY.get("ModuleElement").contains(type);
        }
        if (instance instanceof ElementTrace) {
            return TYPE_HIERARCHY.get("ElementTrace").contains(type);
        }
        if (instance instanceof ModelElement) {
            return TYPE_HIERARCHY.get("ModelElement").contains(type);
        }
        if (instance instanceof Property) {
            return TYPE_HIERARCHY.get("Property").contains(type);
        }
        if (instance instanceof TypeTrace) {
            return TYPE_HIERARCHY.get("TypeTrace").contains(type);
        }
        if (instance instanceof Type) {
            return TYPE_HIERARCHY.get("Type").contains(type);
        }        
        return false;
    }
    
    @Override
    public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
        if (instance instanceof Trace) {
            return "Trace".equals(type);
        }
        if (instance instanceof ExecutableBlock) {
            return "ExecutableBlock".equals(type);
        }
        if (instance instanceof ModuleElement) {
            return "ModuleElement".equals(type);
        }
        if (instance instanceof ElementTrace) {
            return "ElementTrace".equals(type);
        }
        if (instance instanceof ModelElement) {
            return "ModelElement".equals(type);
        }
        if (instance instanceof Property) {
            return "Property".equals(type);
        }
        if (instance instanceof TypeTrace) {
            return "TypeTrace".equals(type);
        }
        if (instance instanceof Type) {
            return "Type".equals(type);
        }  
        return false;
    }
  
    @Override
    public boolean knowsAboutProperty(Object instance, String property) {        
        if (instance instanceof Trace) {
            return TYPE_PROPERTIES.get("Trace").contains(property);
        }        
        if (instance instanceof ExecutableBlock) {
            return TYPE_PROPERTIES.get("ExecutableBlock").contains(property);
        }        
        if (instance instanceof ModuleElement) {
            return TYPE_PROPERTIES.get("ModuleElement").contains(property);
        }        
        if (instance instanceof ElementTrace) {
            return TYPE_PROPERTIES.get("ElementTrace").contains(property);
        }        
        if (instance instanceof ModelElement) {
            return TYPE_PROPERTIES.get("ModelElement").contains(property);
        }        
        if (instance instanceof Property) {
            return TYPE_PROPERTIES.get("Property").contains(property);
        }        
        if (instance instanceof TypeTrace) {
            return TYPE_PROPERTIES.get("TypeTrace").contains(property);
        }        
        if (instance instanceof Type) {
            return TYPE_PROPERTIES.get("Type").contains(property);
        }        
        return false;
    }    
    
}