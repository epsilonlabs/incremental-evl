 /*******************************************************************************
 * This file was automatically generated on: 2017-10-20.
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
                    new String[] {"ExecutionTrace","Execution","Access","AllInstancesAccess","PropertyAccess","Property","ModelElement","ModelType","Model"}
                ))
            );
    
    private static final Set<String> INSTANTIATABLE_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"ExecutionTrace","Execution","Access","AllInstancesAccess","PropertyAccess","Property","ModelElement","ModelType","Model"}
                ))
            );
    
    private static final Map<String, Set<String>> TYPE_HIERARCHY;
    static {
        TYPE_HIERARCHY = new HashMap<String, Set<String>>();    
        TYPE_HIERARCHY.put("ExecutionTrace", new HashSet<String>(Arrays.asList(new String[]{"IdElement","ExecutionTrace"})));    
        TYPE_HIERARCHY.put("Execution", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Execution"})));    
        TYPE_HIERARCHY.put("Access", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Access"})));    
        TYPE_HIERARCHY.put("AllInstancesAccess", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Access","AllInstancesAccess"})));    
        TYPE_HIERARCHY.put("PropertyAccess", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Access","PropertyAccess"})));    
        TYPE_HIERARCHY.put("Property", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Property"})));    
        TYPE_HIERARCHY.put("ModelElement", new HashSet<String>(Arrays.asList(new String[]{"IdElement","ModelElement"})));    
        TYPE_HIERARCHY.put("ModelType", new HashSet<String>(Arrays.asList(new String[]{"IdElement","ModelType"})));    
        TYPE_HIERARCHY.put("Model", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Model"})));        
    }
    
    private static final Map<String, Set<String>> TYPE_PROPERTIES;
    static {
        TYPE_PROPERTIES = new HashMap<String, Set<String>>();    
        TYPE_PROPERTIES.put("ExecutionTrace", new HashSet<String>(Arrays.asList(new String[]{"id","execution","model"})));    
        TYPE_PROPERTIES.put("Execution", new HashSet<String>(Arrays.asList(new String[]{"id","accesses"})));    
        TYPE_PROPERTIES.put("Access", new HashSet<String>(Arrays.asList(new String[]{"id","execution"})));    
        TYPE_PROPERTIES.put("AllInstancesAccess", new HashSet<String>(Arrays.asList(new String[]{"id","execution","ofKind","type"})));    
        TYPE_PROPERTIES.put("PropertyAccess", new HashSet<String>(Arrays.asList(new String[]{"id","execution","value","property"})));    
        TYPE_PROPERTIES.put("Property", new HashSet<String>(Arrays.asList(new String[]{"id","name","element","accessedBy"})));    
        TYPE_PROPERTIES.put("ModelElement", new HashSet<String>(Arrays.asList(new String[]{"id","uri","model","properties"})));    
        TYPE_PROPERTIES.put("ModelType", new HashSet<String>(Arrays.asList(new String[]{"id","name","model","accessedBy"})));    
        TYPE_PROPERTIES.put("Model", new HashSet<String>(Arrays.asList(new String[]{"id","name","elements","types"})));        
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
        if (instance instanceof ExecutionTrace) {
            return ExecutionTrace.class;
        }
        if (instance instanceof Execution) {
            return Execution.class;
        }
        if (instance instanceof Access) {
            return Access.class;
        }
        if (instance instanceof AllInstancesAccess) {
            return AllInstancesAccess.class;
        }
        if (instance instanceof PropertyAccess) {
            return PropertyAccess.class;
        }
        if (instance instanceof Property) {
            return Property.class;
        }
        if (instance instanceof ModelElement) {
            return ModelElement.class;
        }
        if (instance instanceof ModelType) {
            return ModelType.class;
        }
        if (instance instanceof Model) {
            return Model.class;
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
        return instance instanceof IdElement;
    }
    
    @Override
    public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
        if (instance instanceof ExecutionTrace) {
            return TYPE_HIERARCHY.get("ExecutionTrace").contains(type);
        }
        if (instance instanceof Execution) {
            return TYPE_HIERARCHY.get("Execution").contains(type);
        }
        if (instance instanceof Access) {
            return TYPE_HIERARCHY.get("Access").contains(type);
        }
        if (instance instanceof AllInstancesAccess) {
            return TYPE_HIERARCHY.get("AllInstancesAccess").contains(type);
        }
        if (instance instanceof PropertyAccess) {
            return TYPE_HIERARCHY.get("PropertyAccess").contains(type);
        }
        if (instance instanceof Property) {
            return TYPE_HIERARCHY.get("Property").contains(type);
        }
        if (instance instanceof ModelElement) {
            return TYPE_HIERARCHY.get("ModelElement").contains(type);
        }
        if (instance instanceof ModelType) {
            return TYPE_HIERARCHY.get("ModelType").contains(type);
        }
        if (instance instanceof Model) {
            return TYPE_HIERARCHY.get("Model").contains(type);
        }        
        return false;
    }
    
    @Override
    public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
        if (instance instanceof ExecutionTrace) {
            return "ExecutionTrace".equals(type);
        }
        if (instance instanceof Execution) {
            return "Execution".equals(type);
        }
        if (instance instanceof Access) {
            return "Access".equals(type);
        }
        if (instance instanceof AllInstancesAccess) {
            return "AllInstancesAccess".equals(type);
        }
        if (instance instanceof PropertyAccess) {
            return "PropertyAccess".equals(type);
        }
        if (instance instanceof Property) {
            return "Property".equals(type);
        }
        if (instance instanceof ModelElement) {
            return "ModelElement".equals(type);
        }
        if (instance instanceof ModelType) {
            return "ModelType".equals(type);
        }
        if (instance instanceof Model) {
            return "Model".equals(type);
        }  
        return false;
    }
  
    @Override
    public boolean knowsAboutProperty(Object instance, String property) {        
        if (instance instanceof ExecutionTrace) {
            return TYPE_PROPERTIES.get("ExecutionTrace").contains(property);
        }        
        if (instance instanceof Execution) {
            return TYPE_PROPERTIES.get("Execution").contains(property);
        }        
        if (instance instanceof Access) {
            return TYPE_PROPERTIES.get("Access").contains(property);
        }        
        if (instance instanceof AllInstancesAccess) {
            return TYPE_PROPERTIES.get("AllInstancesAccess").contains(property);
        }        
        if (instance instanceof PropertyAccess) {
            return TYPE_PROPERTIES.get("PropertyAccess").contains(property);
        }        
        if (instance instanceof Property) {
            return TYPE_PROPERTIES.get("Property").contains(property);
        }        
        if (instance instanceof ModelElement) {
            return TYPE_PROPERTIES.get("ModelElement").contains(property);
        }        
        if (instance instanceof ModelType) {
            return TYPE_PROPERTIES.get("ModelType").contains(property);
        }        
        if (instance instanceof Model) {
            return TYPE_PROPERTIES.get("Model").contains(property);
        }        
        return false;
    }    
    
}