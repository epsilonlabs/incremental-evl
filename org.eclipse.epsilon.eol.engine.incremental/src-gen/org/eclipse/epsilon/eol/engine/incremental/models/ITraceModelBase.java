 /*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/

/*******************************************************************************
 ** trace model Interface automatically generated on Fri Sep 15 13:10:14 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.eol.engine.incremental.models;

import org.eclipse.epsilon.eol.incremental.trace.*;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * An IModelExtension that provides specialized methods for working with execution traces.
 * 
 * @author Horacio Hoyos
 *
 */
public interface ITraceModelBase extends IModel {
  
    /**
     * Add a new ExecutionContext.
     *
     * @return The ID of the created ExecutionContext
     */
    Object insertExecutionContext();
    
    /**
     * Find the ExecutionContext by Id.
     *
     * @param id The id to use for the search 
     * @return The ExecutionContext for the id, null if not found.
     */
    ExecutionContext findExecutionContextbyId(Object id);
    
    /**
     * Delete the ExecutionContext.
     * 
     * @param executionContext the ExecutionContext to delete
     * @return True on success, false on failure
     */
    boolean deleteExecutionContext(ExecutionContext executionContext);
    
    /**
     * Update the ExecutionContext. Note that this method should only update the element attributes.
     * Changes in references should be done using the provided link methods.
     *
     * @param executionContext the ExecutionContext to update
     * @return True on success, false on failure
     */
    boolean updateExecutionContext(ExecutionContext executionContext);
    
    /**
     * Add a For link between the ExecutionContext and the Script.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addForLink(ExecutionContext source, Script target);
    
    /**
     * Add a Uses link between the ExecutionContext and the Model.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addUsesLink(ExecutionContext source, Model target);
    
  
    /**
     * Add a new Script.
     *
     * @return The ID of the created Script
     */
    Object insertScript(java.lang.String scriptId);
    
    /**
     * Find the Script by Id.
     *
     * @param id The id to use for the search 
     * @return The Script for the id, null if not found.
     */
    Script findScriptbyId(Object id);
    
    /**
     * Delete the Script.
     * 
     * @param script the Script to delete
     * @return True on success, false on failure
     */
    boolean deleteScript(Script script);
    
    /**
     * Update the Script. Note that this method should only update the element attributes.
     * Changes in references should be done using the provided link methods.
     *
     * @param script the Script to update
     * @return True on success, false on failure
     */
    boolean updateScript(Script script);
    
  
    /**
     * Add a new ModuleElement.
     *
     * @return The ID of the created ModuleElement
     */
    Object insertModuleElement(java.lang.String moduleId);
    
    /**
     * Find the ModuleElement by Id.
     *
     * @param id The id to use for the search 
     * @return The ModuleElement for the id, null if not found.
     */
    ModuleElement findModuleElementbyId(Object id);
    
    /**
     * Delete the ModuleElement.
     * 
     * @param moduleElement the ModuleElement to delete
     * @return True on success, false on failure
     */
    boolean deleteModuleElement(ModuleElement moduleElement);
    
    /**
     * Update the ModuleElement. Note that this method should only update the element attributes.
     * Changes in references should be done using the provided link methods.
     *
     * @param moduleElement the ModuleElement to update
     * @return True on success, false on failure
     */
    boolean updateModuleElement(ModuleElement moduleElement);
    
    /**
     * Add a DefinedIn link between the ModuleElement and the Script.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addDefinedInLink(ModuleElement source, Script target);
    
  
    /**
     * Add a new Trace.
     *
     * @return The ID of the created Trace
     */
    Object insertTrace();
    
    /**
     * Find the Trace by Id.
     *
     * @param id The id to use for the search 
     * @return The Trace for the id, null if not found.
     */
    Trace findTracebyId(Object id);
    
    /**
     * Delete the Trace.
     * 
     * @param trace the Trace to delete
     * @return True on success, false on failure
     */
    boolean deleteTrace(Trace trace);
    
    /**
     * Update the Trace. Note that this method should only update the element attributes.
     * Changes in references should be done using the provided link methods.
     *
     * @param trace the Trace to update
     * @return True on success, false on failure
     */
    boolean updateTrace(Trace trace);
    
    /**
     * Add a CreatedIn link between the Trace and the ExecutionContext.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addCreatedInLink(Trace source, ExecutionContext target);
    
    /**
     * Add a Traces link between the Trace and the ModuleElement.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addTracesLink(Trace source, ModuleElement target);
    
    /**
     * Add a Involves link between the Trace and the ModelElement.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addInvolvesLink(Trace source, ModelElement target);
    
    /**
     * Add a Accesses link between the Trace and the Property.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addAccessesLink(Trace source, Property target);
    
  
    /**
     * Add a new Model.
     *
     * @return The ID of the created Model
     */
    Object insertModel(java.lang.String uri);
    
    /**
     * Find the Model by Id.
     *
     * @param id The id to use for the search 
     * @return The Model for the id, null if not found.
     */
    Model findModelbyId(Object id);
    
    /**
     * Delete the Model.
     * 
     * @param model the Model to delete
     * @return True on success, false on failure
     */
    boolean deleteModel(Model model);
    
    /**
     * Update the Model. Note that this method should only update the element attributes.
     * Changes in references should be done using the provided link methods.
     *
     * @param model the Model to update
     * @return True on success, false on failure
     */
    boolean updateModel(Model model);
    
    /**
     * Add a Owns link between the Model and the ModelElement.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addOwnsLink(Model source, ModelElement target);
    
  
    /**
     * Add a new ModelElement.
     *
     * @return The ID of the created ModelElement
     */
    Object insertModelElement(java.lang.String elementId);
    
    /**
     * Find the ModelElement by Id.
     *
     * @param id The id to use for the search 
     * @return The ModelElement for the id, null if not found.
     */
    ModelElement findModelElementbyId(Object id);
    
    /**
     * Delete the ModelElement.
     * 
     * @param modelElement the ModelElement to delete
     * @return True on success, false on failure
     */
    boolean deleteModelElement(ModelElement modelElement);
    
    /**
     * Update the ModelElement. Note that this method should only update the element attributes.
     * Changes in references should be done using the provided link methods.
     *
     * @param modelElement the ModelElement to update
     * @return True on success, false on failure
     */
    boolean updateModelElement(ModelElement modelElement);
    
    /**
     * Add a Contains link between the ModelElement and the Property.
     *
     * @param source The source of the link
     * @param target The target of the link
     * @return True on success, false on failure
     */
    boolean addContainsLink(ModelElement source, Property target);
    
  
    /**
     * Add a new Property.
     *
     * @return The ID of the created Property
     */
    Object insertProperty(java.lang.String name, java.lang.Object value);
    
    /**
     * Find the Property by Id.
     *
     * @param id The id to use for the search 
     * @return The Property for the id, null if not found.
     */
    Property findPropertybyId(Object id);
    
    /**
     * Delete the Property.
     * 
     * @param property the Property to delete
     * @return True on success, false on failure
     */
    boolean deleteProperty(Property property);
    
    /**
     * Update the Property. Note that this method should only update the element attributes.
     * Changes in references should be done using the provided link methods.
     *
     * @param property the Property to update
     * @return True on success, false on failure
     */
    boolean updateProperty(Property property);
    
}

 
 