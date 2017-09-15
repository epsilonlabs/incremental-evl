/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.models;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.compile.m3.Metamodel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.Script;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.eclipse.epsilon.eol.incremental.trace.TraceElement;
import org.eclipse.epsilon.eol.incremental.trace.TracePackage;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.Model;

public abstract class AbstractTraceModel extends Model implements ITraceModel  {

	@Override
	public Metamodel getMetamodel(StringProperties properties, IRelativePathResolver resolver) {
		// Can we return the ExecutionTrace metamodel? It should be registered
		return null;
	}

	@Override
	public boolean hasType(String type) {
		EClassifier ec = TracePackage.eINSTANCE.getEClassifier(type);
		return ec == null ? false : true;
	}
	
	@Override
	public boolean isInstantiable(String type) {
		if (!hasType(type)) {
			return false;
		}
		EClassifier ec = TracePackage.eINSTANCE.getEClassifier(type);
		if (!(ec instanceof EClass)) {
			return false;
		}
		return !((EClass)ec).isAbstract();
	}

	@Override
	public boolean isModelElement(Object instance) {
		return instance instanceof TraceElement;
	}

	@Override
	public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
		EClass ec = getObjectEClass(instance);
		EList<EClass> st = ec.getEAllSuperTypes();
		st.add(ec);
		return st.stream().anyMatch(c -> type.equals(c.getName()));		
	}

	@Override
	public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
		if (!hasType(type)) {
			throw new EolModelElementTypeNotFoundException(getName(), type);
		}
		EClass ec = getObjectEClass(instance);
		return type.equals(ec.getName());
	}

	@Override
	public boolean knowsAboutProperty(Object instance, String property) {
		EClass ec = getObjectEClass(instance);
		if (ec == null) {
			return false;
		}
		return ec.getEAllStructuralFeatures().stream()
				.anyMatch(ef -> ef.getName().equals(property));
		
	}
	
	/**
	 * Get the EClass that matches the Java interface name. This assumes that in the generated code the interface has
	 * the same name as the EClass. 
	 * 
	 * @param clazz
	 * @return
	 */
	private EClass getEClassFromInterface(Class<?> clazz) {
		return (EClass) TracePackage.eINSTANCE.getEClassifier(clazz.getName());
	}

	/**
	 * Get the EClass that matches the object's Java class
	 * 
	 * @param instance
	 * @return
	 */
	private EClass getObjectEClass(Object instance) {
		EClass ec = null;
		if (ExecutionContext.class.isInstance(instance)) {
			ec = getEClassFromInterface(ExecutionContext.class);
		} else if (org.eclipse.epsilon.eol.incremental.trace.Model.class.isInstance(instance)) {
			ec = getEClassFromInterface(org.eclipse.epsilon.eol.incremental.trace.Model.class);
		} else if (ModelElement.class.isInstance(instance)) {
			ec = getEClassFromInterface(org.eclipse.epsilon.eol.incremental.trace.Model.class);
		} else if (ModuleElement.class.isInstance(instance)) {
			ec = getEClassFromInterface(ModuleElement.class);
		} else if (Property.class.isInstance(instance)) {
			ec = getEClassFromInterface(Property.class);
		} else if (Script.class.isInstance(instance)) {
			ec = getEClassFromInterface(Script.class);
		} else if (Trace.class.isInstance(instance)) {
			ec = getEClassFromInterface(Trace.class);
		}
		return ec;
	}

}
