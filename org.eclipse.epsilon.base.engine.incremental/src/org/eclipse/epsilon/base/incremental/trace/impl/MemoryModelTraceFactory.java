package org.eclipse.epsilon.base.incremental.trace.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateElement;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IAllInstancesAccess;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IPropertyAccess;
import org.eclipse.epsilon.base.incremental.trace.IPropertyTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A factory for creating value objects. It can be reused by all incremental models.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
@Deprecated
public class MemoryModelTraceFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(MemoryModelTraceFactory.class);
	
	private final IIncrementalModel model;
//	private final IModelTrace modelTrace;
//	private final Map<String, IModelTypeTrace> modelTypeTraces = new HashMap<>();
//	private final Map<String, IModelElementTrace> modelElementTraces = new HashMap<>();
//	private final Map<String, IPropertyTrace> propertyTraces = new HashMap<>();
//	private final Map<String, IAllInstancesAccess> ofKindAccesses = new HashMap<>();
//	private final Map<String, IAllInstancesAccess> ofTypeAccesses = new HashMap<>();
//	private final Map<String, IPropertyAccess> propertyAccesses = new HashMap<>();
//	private final Map<String, IElementAccess> elementAccesses = new HashMap<>();
//	private final Map<String, IModelElementVariable> modelElementVariables = new HashMap<>();

	public MemoryModelTraceFactory(IIncrementalModel model) throws EolIncrementalExecutionException {
		super();
		this.model = model;
//		try {
//			modelTrace = new ModelTrace(model.getName());
//		} catch (TraceModelDuplicateRelation e) {
//			logger.error("Error creating modelTrace", e);
//			throw new EolIncrementalExecutionException("Error creating modelTrace");
//		}
	};
	
	/**
	 * Create a trace for this model.
	 *
	 * @return the model trace
	 */
//	public IModelTrace getOrCreateModelTrace() {
//		return modelTrace;
//	}

	/**
	 * Create a type trace for this type.
	 *
	 * @param typeName the type name
	 * @return the model type trace
	 * @throws EolIncrementalExecutionException if {@link #hasType(String)} is false for the type
	 * @see #hasType(String)
	 */
	public IModelTypeTrace getOrCreateModelTypeTrace(String typeName) throws EolIncrementalExecutionException {
		logger.info("Creting ModelTypeTrace for {}", typeName);
//		if (!model.hasType(typeName)) {
//			logger.error("Unknonw type {} in model{}.", typeName, model.getName());
//			throw new EolIncrementalExecutionException("Model does not know about type " + typeName);
//		}
//		if (modelTypeTraces.containsKey(typeName)) {
//			logger.info("Retreiving ModelTypeTrace from cache");
//			return modelTypeTraces.get(typeName);
//		}
		IModelTypeTrace trace = null;
//		try {
//			logger.info("Creating new ModelTypeTrace");
//			trace = new ModelTypeTrace(typeName, modelTrace);
//		} catch (TraceModelDuplicateRelation e) {
//			throw new EolIncrementalExecutionException("Error creating ModelTypeTrace trace for " + typeName);
//		}
//		modelTypeTraces.put(typeName, trace);
		return trace;
	}

	/**
	 * Creates a model element trace.
	 *
	 * 
	 * @param element the element
	 * @return the model element trace
	 * @throws EolIncrementalExecutionException if the model does not own the element
	 * @see #owns(Object)
	 */
	public IModelElementTrace getOrCreateModelElementTrace(Object element) throws EolIncrementalExecutionException {
//		logger.info("Creting ModelElementTrace for {}", element);
//		String elementId = model.getElementId(element);
//		if (modelElementTraces.containsKey(elementId)) {
//			logger.info("Retreiving ModelElementTrace from cache");
//			return modelElementTraces.get(elementId);
//		}
//		if (!model.owns(element)) {
//			throw new EolIncrementalExecutionException("Model does not know about element " + element);
//		}
		IModelElementTrace trace = null;
//		try {
//			logger.info("Creating new ModelElementTrace");
//			trace = new ModelElementTrace(elementId, modelTrace);
//		} catch (TraceModelDuplicateRelation e) {
//			throw new EolIncrementalExecutionException("Error creating ModelElementTrace trace for " + element);
//		}
//		modelElementTraces.put(elementId, trace);
		return trace;
	}

	/**
	 * Creates a property trace.
	 *
	 * @param element the element
	 * @param propertyName the property name
	 * @return the property trace
	 * @throws EolIncrementalExecutionException if the model does not know about the property
	 * @see #knowsAboutProperty(Object, String)
	 */
	public IPropertyTrace getOrCreatePropertyTrace(Object element, String propertyName)
			throws EolIncrementalExecutionException {
		
//		String elementId = model.getElementId(element);
//		logger.info("Creting PropertyTrace for {}.{}", elementId, propertyName);
//		String propertyId = String.format("%s:%s", elementId, propertyName);
//		if (propertyTraces.containsKey(propertyId)) {
//			logger.info("Retreiving PropertyTrace from cache");
//			return propertyTraces.get(propertyId);
//		}
//		if (!model.knowsAboutProperty(element, propertyName)) {
//			throw new EolIncrementalExecutionException("Model does not know about property " + propertyName +" for element " + element);
//		}
		IPropertyTrace trace = null;
//		try {
//			logger.info("Creating new PropertyTrace");
//			trace = new PropertyTrace(propertyName, getOrCreateModelElementTrace(element));
//		} catch (TraceModelDuplicateRelation e) {
//			throw new EolIncrementalExecutionException("Error creating PropertyTrace trace property for " + propertyName +" for element " + element);
//		}
//		propertyTraces.put(propertyId, trace);
		return trace;
	}

	/**
	 * Creates the all instances access.
	 * @param ofKind the of kind
	 * @param typeName the type name
	 * @param executionTrace the execution trace
	 *
	 * @return the all instances access
	 * @throws EolIncrementalExecutionException if {@link #hasType(String)} is false for the type
	 * @see #hasType(String)
	 */
	public IAllInstancesAccess getOrCreateAllInstancesAccess(boolean ofKind, String typeName, IModuleElementTrace executionTrace)
			throws EolIncrementalExecutionException {
		
//		logger.info("Creting AllInstancesAccess for {} , kind: {}", typeName, ofKind);
//		Map<String, IAllInstancesAccess> cache;
//		if (ofKind) {
//			cache = ofKindAccesses;
//		}
//		else {
//			cache = ofTypeAccesses;
//		}
//		if (cache.containsKey(typeName)) {
//			logger.info("Retreiving AllInstancesAccess from cache");
//			return cache.get(typeName);
//		}
		IAllInstancesAccess access = null;
//		try {
//			logger.info("Creating new AllInstancesAccess");
//			access = new AllInstancesAccess(ofKind, executionTrace, getOrCreateModelTypeTrace(typeName));
//		} catch (TraceModelDuplicateRelation e) {
//			throw new EolIncrementalExecutionException("Error creating AllInstancesAccess for " + typeName, e);
//		}
//		cache.put(typeName, access);
		return access;
	}

	/**
	 * Creates the property access.
	 *
	 * @param element the element
	 * @param propertyName the property name
	 * @param executionTrace the execution trace
	 * @return the i property access
	 * @throws EolIncrementalExecutionException if the model does not know about the property
	 * @see #knowsAboutProperty(Object, String)
	 */
	public IPropertyAccess getOrCreatePropertyAccess(Object element, String propertyName, IModuleElementTrace executionTrace)
			throws EolIncrementalExecutionException {
		
//		logger.info("Creting PropertyAccess for {} , proerty: {}", element, propertyName);
//		String elementId = model.getElementId(element);
//		String propertyId = String.format("%s:%s", elementId, propertyName);
//		if (propertyAccesses.containsKey(propertyId)) {
//			logger.info("Retreiving PropertyAccess from cache");
//			return propertyAccesses.get(propertyId);
//		}
		IPropertyAccess access = null;
//		try {
//			logger.info("Creating new PropertyAccess");
//			access = new PropertyAccess(executionTrace, getOrCreatePropertyTrace(element, propertyName));
//		} catch (TraceModelDuplicateRelation e) {
//			throw new EolIncrementalExecutionException("Error creating AllInstancesAccess for " + element + " and property " + propertyName, e);
//		}
//		propertyAccesses.put(propertyId, access);
		return access;
	}

	/**
	 * Creates the element access.
	 *
	 * @param element the element
	 * @param currentTrace the execution trace
	 * @return the i element access
	 * @throws EolIncrementalExecutionException if the model does not own the element
	 * @see #owns(Object)
	 */
	public IElementAccess getOrCreateElementAccess(Object element, IModuleElementTrace currentTrace) throws EolIncrementalExecutionException {
//		logger.info("Creting ElementAccess for {}", element);
//		String elementId = model.getElementId(element);
//		if (elementAccesses.containsKey(elementId)) {
//			logger.info("Retreiving ElementAccess from cache");
//			return elementAccesses.get(elementId);
//		}
		IElementAccess access = null;
//		try {
//			logger.info("Creating new ElementAccess");
//			access = new ElementAccess(currentTrace, getOrCreateModelElementTrace(element));
//		} catch (TraceModelDuplicateRelation e) {
//			throw new EolIncrementalExecutionException("Error creating ElementAccess for " + element, e);
//		}
//		elementAccesses.put(elementId, access);
		return access;
	}
	
	/**
	 * Create an Model Element Variable
	 * @param name the name of the variable
	 * @param element the value of the variable
	 * @return
	 * @throws EolIncrementalExecutionException
	 */
	public IModelElementVariable getOrCreateModelElementVariable(String name, Object element) throws EolIncrementalExecutionException {
		
//		String elementId = model.getElementId(element);
//		logger.info("Creting ModelElementVariable for {}@{}", name, elementId);
//		String variableId = String.format("%s:%s", name, elementId);
//		if (modelElementVariables.containsKey(variableId)) {
//			logger.info("Retreiving ModelElementVariable from cache");
//			return modelElementVariables.get(variableId);
//		}
		IModelElementVariable variable = null;
//		try {
//			logger.info("Creating new ModelElementVariable");
//			variable = new ModelElementVariable(name, getOrCreateModelElementTrace(element));
//		} catch (TraceModelDuplicateRelation e) {
//			throw new EolIncrementalExecutionException("Error creating ModelElementVariable for " + name + " for element " + element);
//		}
//		modelElementVariables.put(variableId, variable);
		return variable;
	}

	public Set<String> getAllModelElementIds() {
		//return modelElementTraces.keySet();
		return null;
	}

	public void removeModelElement(String uri) {
//		IModelElementTrace deleted = modelElementTraces.remove(uri);
//		if (deleted != null) {
//			elementAccesses.remove(uri);
//			Set<IPropertyTrace> ownedProperties = propertyTraces.values().stream()
//					.filter(pt -> pt.element().get().equals(deleted))
//					.collect(Collectors.toSet());
//			Set<IPropertyAccess> accessedProperties = propertyAccesses.values().stream()
//					.filter(pa -> ownedProperties.contains(pa.property().get()))
//					.collect(Collectors.toSet());
//			ownedProperties.forEach(op -> propertyTraces.remove(String.format("%s:%s", uri, op.getName())));
//			accessedProperties.forEach(ap -> propertyAccesses.remove(String.format("%s:%s", uri, ap.property().get().getName())));
//		}
	}

}
