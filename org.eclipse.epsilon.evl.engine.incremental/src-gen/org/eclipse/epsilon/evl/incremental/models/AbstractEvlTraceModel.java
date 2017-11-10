 /*******************************************************************************
 * This file was automatically generated on: 2017-11-10.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
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
import org.eclipse.epsilon.eol.incremental.models.ITraceModel;
import org.eclipse.epsilon.eol.incremental.trace.*;
import org.eclipse.epsilon.evl.incremental.trace.*;


public abstract class AbstractEvlTraceModel extends AbstractEolTraceModel implements ITraceModel  {
 
    private static final Set<String> KNOWN_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"EvlExecutionTrace","EvlModule","GuardedElement","Context","Invariant","Guard","Check","Message","Satisfies"}
                ))
            );
    
    private static final Set<String> INSTANTIATABLE_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"EvlExecutionTrace","EvlModule","GuardedElement","Context","Invariant","Guard","Check","Message","Satisfies"}
                ))
            );
    
    private static final Map<String, Set<String>> TYPE_HIERARCHY;
    static {
        TYPE_HIERARCHY = new HashMap<String, Set<String>>();    
        TYPE_HIERARCHY.put("EvlExecutionTrace", new HashSet<String>(Arrays.asList(new String[]{"IdElement","ExecutionTrace","EvlExecutionTrace"})));    
        TYPE_HIERARCHY.put("EvlModule", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Module","EvlModule"})));    
        TYPE_HIERARCHY.put("GuardedElement", new HashSet<String>(Arrays.asList(new String[]{"GuardedElement"})));    
        TYPE_HIERARCHY.put("Context", new HashSet<String>(Arrays.asList(new String[]{"GuardedElement","IdElement","ModuleElement","Context"})));    
        TYPE_HIERARCHY.put("Invariant", new HashSet<String>(Arrays.asList(new String[]{"GuardedElement","Invariant"})));    
        TYPE_HIERARCHY.put("Guard", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Execution","Guard"})));    
        TYPE_HIERARCHY.put("Check", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Execution","Check"})));    
        TYPE_HIERARCHY.put("Message", new HashSet<String>(Arrays.asList(new String[]{"IdElement","Execution","Message"})));    
        TYPE_HIERARCHY.put("Satisfies", new HashSet<String>(Arrays.asList(new String[]{"Satisfies"})));        
    }
    
    private static final Map<String, Set<String>> TYPE_PROPERTIES;
    static {
        TYPE_PROPERTIES = new HashMap<String, Set<String>>();    
        TYPE_PROPERTIES.put("EvlExecutionTrace", new HashSet<String>(Arrays.asList(new String[]{"id","model","module"})));    
        TYPE_PROPERTIES.put("EvlModule", new HashSet<String>(Arrays.asList(new String[]{"id","source","moduleElements"})));    
        TYPE_PROPERTIES.put("GuardedElement", new HashSet<String>(Arrays.asList(new String[]{"guard"})));    
        TYPE_PROPERTIES.put("Context", new HashSet<String>(Arrays.asList(new String[]{"guard","id","module","constraints","context"})));    
        TYPE_PROPERTIES.put("Invariant", new HashSet<String>(Arrays.asList(new String[]{"guard","name","result","check","message","satisfies"})));    
        TYPE_PROPERTIES.put("Guard", new HashSet<String>(Arrays.asList(new String[]{"id","accesses","result","limits"})));    
        TYPE_PROPERTIES.put("Check", new HashSet<String>(Arrays.asList(new String[]{"id","accesses","invariant"})));    
        TYPE_PROPERTIES.put("Message", new HashSet<String>(Arrays.asList(new String[]{"id","accesses","invariant"})));    
        TYPE_PROPERTIES.put("Satisfies", new HashSet<String>(Arrays.asList(new String[]{"all","invariants"})));        
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
            if (instance instanceof EvlExecutionTrace) {
                type = EvlExecutionTrace.class;
            }
            
            else if (instance instanceof EvlModule) {
                type = EvlModule.class;
            }
            
            else if (instance instanceof GuardedElement) {
                type = GuardedElement.class;
            }
            
            else if (instance instanceof Context) {
                type = Context.class;
            }
            
            else if (instance instanceof Invariant) {
                type = Invariant.class;
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
            
            else if (instance instanceof Satisfies) {
                type = Satisfies.class;
            }
            
        }       
        return type;
    }
    
    @Override
    public boolean hasType(String type) {
        boolean result = super.hasType(type);
        if (!result) {
            result = KNOWN_TYPES.contains(type);
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
    public boolean isModelElement(Object instance) {
        return instance instanceof IdElement;
    }
    
    @Override
    public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
        boolean result = super.isOfKind(instance, type);
        if (!result) {

	        if  (instance instanceof EvlExecutionTrace) {
	            result = TYPE_HIERARCHY.get("EvlExecutionTrace").contains(type);
	        }

	        else if  (instance instanceof EvlModule) {
	            result = TYPE_HIERARCHY.get("EvlModule").contains(type);
	        }

	        else if  (instance instanceof GuardedElement) {
	            result = TYPE_HIERARCHY.get("GuardedElement").contains(type);
	        }

	        else if  (instance instanceof Context) {
	            result = TYPE_HIERARCHY.get("Context").contains(type);
	        }

	        else if  (instance instanceof Invariant) {
	            result = TYPE_HIERARCHY.get("Invariant").contains(type);
	        }

	        else if  (instance instanceof Guard) {
	            result = TYPE_HIERARCHY.get("Guard").contains(type);
	        }

	        else if  (instance instanceof Check) {
	            result = TYPE_HIERARCHY.get("Check").contains(type);
	        }

	        else if  (instance instanceof Message) {
	            result = TYPE_HIERARCHY.get("Message").contains(type);
	        }

	        else if  (instance instanceof Satisfies) {
	            result = TYPE_HIERARCHY.get("Satisfies").contains(type);
	        }
        } 
        return result;
    }
    
    @Override
    public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
        boolean result = super.isOfType(instance, type);
        if (!result) {

	        if  (instance instanceof EvlExecutionTrace) {
	            result = "EvlExecutionTrace".equals(type);
	        }

	        else if  (instance instanceof EvlModule) {
	            result = "EvlModule".equals(type);
	        }

	        else if  (instance instanceof GuardedElement) {
	            result = "GuardedElement".equals(type);
	        }

	        else if  (instance instanceof Context) {
	            result = "Context".equals(type);
	        }

	        else if  (instance instanceof Invariant) {
	            result = "Invariant".equals(type);
	        }

	        else if  (instance instanceof Guard) {
	            result = "Guard".equals(type);
	        }

	        else if  (instance instanceof Check) {
	            result = "Check".equals(type);
	        }

	        else if  (instance instanceof Message) {
	            result = "Message".equals(type);
	        }

	        else if  (instance instanceof Satisfies) {
	            result = "Satisfies".equals(type);
	        }
        }
        return result;
    }
  
    @Override
    public boolean knowsAboutProperty(Object instance, String property) {
        boolean result = super.knowsAboutProperty(instance, property);
        if (!result) {
        
	        if  (instance instanceof EvlExecutionTrace) {
	            result = TYPE_PROPERTIES.get("EvlExecutionTrace").contains(property);
	        }
        
	        else if  (instance instanceof EvlModule) {
	            result = TYPE_PROPERTIES.get("EvlModule").contains(property);
	        }
        
	        else if  (instance instanceof GuardedElement) {
	            result = TYPE_PROPERTIES.get("GuardedElement").contains(property);
	        }
        
	        else if  (instance instanceof Context) {
	            result = TYPE_PROPERTIES.get("Context").contains(property);
	        }
        
	        else if  (instance instanceof Invariant) {
	            result = TYPE_PROPERTIES.get("Invariant").contains(property);
	        }
        
	        else if  (instance instanceof Guard) {
	            result = TYPE_PROPERTIES.get("Guard").contains(property);
	        }
        
	        else if  (instance instanceof Check) {
	            result = TYPE_PROPERTIES.get("Check").contains(property);
	        }
        
	        else if  (instance instanceof Message) {
	            result = TYPE_PROPERTIES.get("Message").contains(property);
	        }
        
	        else if  (instance instanceof Satisfies) {
	            result = TYPE_PROPERTIES.get("Satisfies").contains(property);
	        }
        }
        return result;
    }    
    
}