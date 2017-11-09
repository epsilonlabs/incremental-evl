 /*******************************************************************************
 * This file was automatically generated on: 2017-11-09.
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
import org.eclipse.epsilon.eol.incremental.trace.*;


public abstract class AbstractEolTraceModel extends Model implements ITraceModel  {
 
    private static final Set<String> KNOWN_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"ExecutionTrace","Module","ModuleElement","Execution","Access","AllInstancesAccess","PropertyAccess","Property","ModelElement","ModelType","Model"}
                ))
            );
    
    private static final Set<String> INSTANTIATABLE_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"ExecutionTrace","Module","ModuleElement","Execution","Access","AllInstancesAccess","PropertyAccess","Property","ModelElement","ModelType","Model"}
                ))
            );
    
    private static final Map<String, Set<String>> TYPE_HIERARCHY;
    static {
        TYPE_HIERARCHY = new HashMap<String, Set<String>>();    
        TYPE_HIERARCHY.put("ExecutionTrace", new HashSet<String>(Arrays.asList(new String[]{"IdElement","ExecutionTrace"})));    
        TYPE_HIERARCHY.put("Module", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Module"})));    
        TYPE_HIERARCHY.put("ModuleElement", new HashSet<String>(Arrays.asList(new String[]{"IdElement","ModuleElement"})));    
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
        TYPE_PROPERTIES.put("ExecutionTrace", new HashSet<String>(Arrays.asList(new String[]{"id","model","module"})));    
        TYPE_PROPERTIES.put("Module", new HashSet<String>(Arrays.asList(new String[]{"id","source","modules"})));    
        TYPE_PROPERTIES.put("ModuleElement", new HashSet<String>(Arrays.asList(new String[]{"id","module"})));    
        TYPE_PROPERTIES.put("Execution", new HashSet<String>(Arrays.asList(new String[]{"id","accesses"})));    
        TYPE_PROPERTIES.put("Access", new HashSet<String>(Arrays.asList(new String[]{"id","execution"})));    
        TYPE_PROPERTIES.put("AllInstancesAccess", new HashSet<String>(Arrays.asList(new String[]{"id","execution","ofKind","type"})));    
        TYPE_PROPERTIES.put("PropertyAccess", new HashSet<String>(Arrays.asList(new String[]{"id","execution","value","property"})));    
        TYPE_PROPERTIES.put("Property", new HashSet<String>(Arrays.asList(new String[]{"id","name","element"})));    
        TYPE_PROPERTIES.put("ModelElement", new HashSet<String>(Arrays.asList(new String[]{"id","uri","model","properties"})));    
        TYPE_PROPERTIES.put("ModelType", new HashSet<String>(Arrays.asList(new String[]{"id","name","model"})));    
        TYPE_PROPERTIES.put("Model", new HashSet<String>(Arrays.asList(new String[]{"id","name","elements","types"})));        
    }
    
    
    
    @Override
    public Metamodel getMetamodel(StringProperties properties, IRelativePathResolver resolver) {
        // TODO Generate AbstractEolTraceModel.getMetamodel
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
        Object type = null;
        {
            if (instance instanceof ExecutionTrace) {
                type = ExecutionTrace.class;
            }
            
            else if (instance instanceof Module) {
                type = Module.class;
            }
            
            else if (instance instanceof ModuleElement) {
                type = ModuleElement.class;
            }
            
            else if (instance instanceof Execution) {
                type = Execution.class;
            }
            
            else if (instance instanceof Access) {
                type = Access.class;
            }
            
            else if (instance instanceof AllInstancesAccess) {
                type = AllInstancesAccess.class;
            }
            
            else if (instance instanceof PropertyAccess) {
                type = PropertyAccess.class;
            }
            
            else if (instance instanceof Property) {
                type = Property.class;
            }
            
            else if (instance instanceof ModelElement) {
                type = ModelElement.class;
            }
            
            else if (instance instanceof ModelType) {
                type = ModelType.class;
            }
            
            else if (instance instanceof Model) {
                type = Model.class;
            }
            
        }       
        return type;
    }
    
    @Override
    public boolean hasType(String type) {
        boolean result = false;
        {
            result = KNOWN_TYPES.contains(type);
        }
        return result;
    }
    
    @Override
    public boolean isInstantiable(String type) {
        if (!hasType(type)) {
            return false;
        }
        boolean result = false;
        {
        
            result = INSTANTIATABLE_TYPES.contains(type);
        }
        return result;
    }
    
    @Override
    public boolean isModelElement(Object instance) {
        return instance instanceof IdElement;
    }
    
    @Override
    public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
        boolean result = false;
        {

	        if  (instance instanceof ExecutionTrace) {
	            result = TYPE_HIERARCHY.get("ExecutionTrace").contains(type);
	        }

	        else if  (instance instanceof Module) {
	            result = TYPE_HIERARCHY.get("Module").contains(type);
	        }

	        else if  (instance instanceof ModuleElement) {
	            result = TYPE_HIERARCHY.get("ModuleElement").contains(type);
	        }

	        else if  (instance instanceof Execution) {
	            result = TYPE_HIERARCHY.get("Execution").contains(type);
	        }

	        else if  (instance instanceof Access) {
	            result = TYPE_HIERARCHY.get("Access").contains(type);
	        }

	        else if  (instance instanceof AllInstancesAccess) {
	            result = TYPE_HIERARCHY.get("AllInstancesAccess").contains(type);
	        }

	        else if  (instance instanceof PropertyAccess) {
	            result = TYPE_HIERARCHY.get("PropertyAccess").contains(type);
	        }

	        else if  (instance instanceof Property) {
	            result = TYPE_HIERARCHY.get("Property").contains(type);
	        }

	        else if  (instance instanceof ModelElement) {
	            result = TYPE_HIERARCHY.get("ModelElement").contains(type);
	        }

	        else if  (instance instanceof ModelType) {
	            result = TYPE_HIERARCHY.get("ModelType").contains(type);
	        }

	        else if  (instance instanceof Model) {
	            result = TYPE_HIERARCHY.get("Model").contains(type);
	        }
        } 
        return result;
    }
    
    @Override
    public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
        boolean result = false;
        {

	        if  (instance instanceof ExecutionTrace) {
	            result = "ExecutionTrace".equals(type);
	        }

	        else if  (instance instanceof Module) {
	            result = "Module".equals(type);
	        }

	        else if  (instance instanceof ModuleElement) {
	            result = "ModuleElement".equals(type);
	        }

	        else if  (instance instanceof Execution) {
	            result = "Execution".equals(type);
	        }

	        else if  (instance instanceof Access) {
	            result = "Access".equals(type);
	        }

	        else if  (instance instanceof AllInstancesAccess) {
	            result = "AllInstancesAccess".equals(type);
	        }

	        else if  (instance instanceof PropertyAccess) {
	            result = "PropertyAccess".equals(type);
	        }

	        else if  (instance instanceof Property) {
	            result = "Property".equals(type);
	        }

	        else if  (instance instanceof ModelElement) {
	            result = "ModelElement".equals(type);
	        }

	        else if  (instance instanceof ModelType) {
	            result = "ModelType".equals(type);
	        }

	        else if  (instance instanceof Model) {
	            result = "Model".equals(type);
	        }
        }
        return result;
    }
  
    @Override
    public boolean knowsAboutProperty(Object instance, String property) {
        boolean result = false;
        {
        
	        if  (instance instanceof ExecutionTrace) {
	            result = TYPE_PROPERTIES.get("ExecutionTrace").contains(property);
	        }
        
	        else if  (instance instanceof Module) {
	            result = TYPE_PROPERTIES.get("Module").contains(property);
	        }
        
	        else if  (instance instanceof ModuleElement) {
	            result = TYPE_PROPERTIES.get("ModuleElement").contains(property);
	        }
        
	        else if  (instance instanceof Execution) {
	            result = TYPE_PROPERTIES.get("Execution").contains(property);
	        }
        
	        else if  (instance instanceof Access) {
	            result = TYPE_PROPERTIES.get("Access").contains(property);
	        }
        
	        else if  (instance instanceof AllInstancesAccess) {
	            result = TYPE_PROPERTIES.get("AllInstancesAccess").contains(property);
	        }
        
	        else if  (instance instanceof PropertyAccess) {
	            result = TYPE_PROPERTIES.get("PropertyAccess").contains(property);
	        }
        
	        else if  (instance instanceof Property) {
	            result = TYPE_PROPERTIES.get("Property").contains(property);
	        }
        
	        else if  (instance instanceof ModelElement) {
	            result = TYPE_PROPERTIES.get("ModelElement").contains(property);
	        }
        
	        else if  (instance instanceof ModelType) {
	            result = TYPE_PROPERTIES.get("ModelType").contains(property);
	        }
        
	        else if  (instance instanceof Model) {
	            result = TYPE_PROPERTIES.get("Model").contains(property);
	        }
        }
        return result;
    }    
    
}