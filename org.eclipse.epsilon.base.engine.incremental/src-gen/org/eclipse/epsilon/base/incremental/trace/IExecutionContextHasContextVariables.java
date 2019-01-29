 /*******************************************************************************
 * This file was automatically generated on: 2019-01-24.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace;

import java.util.Iterator;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.trace.IModelElementVariable;

public interface IExecutionContextHasContextVariables {

    // PUBLIC API
    
    boolean isUnique();
    
    /**
     * Get the value(s) for the reference
     */
    public Iterator<IModelElementVariable> get();

    /**
     * Create a reference to the target element. Returns true if the relation was created or if the
     * relation already existed. 
     * The relation is created if there are no conflicts (see {@link ExecutionHasAccesses#conflict(IModelElementVariable)}).
     * If the reference has an opposite, that relation is also created.
     *
     * @see ExecutionHasAccesses#conflict(IModelElementVariable)
     * @see ExecutionHasAccesses#related(IModelElementVariable)
     * @param target The IModelElementVariable to create a relation with
     * @throws TraceModelConflictRelation if a relation to another IModelElementVariable exists
     */
    boolean create(IModelElementVariable target) throws TraceModelConflictRelation;
    
    /**
     * Destroy a reference to the target element. Returns true, if the reference existed
     * and was properly destroyed. If the reference has an opposite, that relation
     * is also destroyed.
     *
     * @see ExecutionHasAccesses#related(IModelElementVariable)
     */    
    boolean destroy(IModelElementVariable target);
    
    /**
     * Determines if there is a conflict with a possible target. Conflicts can only arise for if
     * the reference has an opposite and for unique multi-valued references. Conflicts enforce
     * that the application destroys relations before creating new ones. This helps maintain data
     * integrity. Returns true if there is a conflict. Conflicts are determined by:
     * <ul>
     *  <li>If the relation is many-to-many, there is no conflict.<li>
     *  <li>If the relation is one-to-one there is a conflict if the target is already part of
     *      another relation, i.e. target.oppoiste != null.</li>
     *  <li>If the relation is one-to-many:
     *      <ul>
     *          <li>If the relation is not containment and non-unique, there is no conflict.<li>
     *          <li>If the relation is not-containment and unique, there is conflict if the target
     *              is already related to the source.<li>
     *          <li>If the relation is containment and non-unique, there is a conflict if the target
     *              is contained elsewhere, i.e. target.oppoiste != null</li>
     *          <li>If the relation is containment and unique, there is a conflict if the target is
     *              contained elsewhere, i.e. target.oppoiste != null, or if the target is already
     *              related to the source.</li>
     *      </ul>
     *  </li>
     */
    boolean conflict(IModelElementVariable target);
    
    /**
     * Returns true if the target is already related via this reference.
     */
    boolean related(IModelElementVariable target);

    // RESTRICTED API: This methods should only be called from create/destroy operations in the
    // implementing class or its opposite relation class. 

    /**
     * Set a new value for the reference. This method should be only accessed by classes in the
     * relation.
     *
     * @see ExecutionHasAccesses#create(IModelElementVariable)
     */
    void set(IModelElementVariable target);

    /**
     * Remove a value for the reference. This method should be only accessed by classes in the
     * relation.
     *
     * @see ExecutionHasAccesses#destroy(IModelElementVariable)
     */
    void remove(IModelElementVariable target);
}