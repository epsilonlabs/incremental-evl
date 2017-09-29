package org.eclipse.epsilon.evl.incremental.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.incremental.models.AbstractEolTraceModel;
import org.eclipse.epsilon.incremental.trace.evl.EvlModuleElement;
import org.eclipse.epsilon.incremental.trace.evl.EvlScript;

public abstract class AbstractEvlTraceModel extends AbstractEolTraceModel {
	
	private static final Set<String> KNOWN_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
                    new String[] {"EvlScript","EvlModuleElement"}
                ))
            );
    
    private static final Set<String> INSTANTIATABLE_TYPES = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(
            		new String[] {"EvlScript","EvlModuleElement"}
                ))
            );
    
    private static final Map<String, Set<String>> TYPE_HIERARCHY;
    static {
        TYPE_HIERARCHY = new HashMap<String, Set<String>>();    
        TYPE_HIERARCHY.put("EvlScript", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","Script","EvlScript"})));    
        TYPE_HIERARCHY.put("EvlModuleElement", new HashSet<String>(Arrays.asList(new String[]{"TraceElement","ModuleElement","EvlModuleElement"})));    
    }
    
    private static final Map<String, Set<String>> TYPE_PROPERTIES;
    static {
        TYPE_PROPERTIES = new HashMap<String, Set<String>>();    
        TYPE_PROPERTIES.put("EvlScript", new HashSet<String>(Arrays.asList(new String[]{"id","scriptId","moduleElements"})));    
        TYPE_PROPERTIES.put("EvlModuleElement", new HashSet<String>(Arrays.asList(new String[]{"id","moduleId","definedIn","traces","satisfies","dependsOn"})));
    }
    
    @Override
    public Object getTypeOf(Object instance) {
    	Object type = super.getTypeOf(instance);
    	if (type == null) {
	        if (instance instanceof EvlScript) {
	            type = EvlScript.class;
	        }
	        if (instance instanceof EvlModuleElement) {
	            type =  EvlModuleElement.class;
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
    public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
    	boolean result = super.isOfKind(instance, type);
    	if (!result) {
    		if (instance instanceof EvlScript) {
                return TYPE_HIERARCHY.get("EvlScript").contains(type);
            }
            if (instance instanceof EvlModuleElement) {
                return TYPE_HIERARCHY.get("EvlModuleElement").contains(type);
            }
    	}
    	return result;
    }
    
    @Override
    public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
    	boolean result = super.isOfKind(instance, type);
    	if (!result) {
    		if (instance instanceof EvlScript) {
                return "EvlScript".equals(type);
            }
            if (instance instanceof EvlModuleElement) {
                return "EvlModuleElement".equals(type);
            }
    	}
    	return result;
    }
    
    @Override
    public boolean knowsAboutProperty(Object instance, String property) {
    	boolean result = super.knowsAboutProperty(instance, property);
    	if (!result) {
    		if (instance instanceof EvlScript) {
                return TYPE_PROPERTIES.get("EvlScript").contains(property);
            }
            if (instance instanceof EvlModuleElement) {
                return TYPE_PROPERTIES.get("EvlModuleElement").contains(property);
            }
    	}
    	return result;
    }
    	
    
}
