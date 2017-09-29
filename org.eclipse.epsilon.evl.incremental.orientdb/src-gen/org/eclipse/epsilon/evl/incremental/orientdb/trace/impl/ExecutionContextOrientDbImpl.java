/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jonathan Co - Initial API and implementation
 *     Horacio Hoyos - API extension
 *******************************************************************************/

/*******************************************************************************
 ** ExecutionContext OrientDB Trace Model Implementation automatically
 ** generated on Mon Sep 04 15:50:37 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.evl.incremental.orientdb.trace.impl;

import java.util.List;

import org.eclipse.epsilon.incremental.trace.eol.ExecutionContext;
import org.eclipse.epsilon.incremental.trace.eol.ModelReference;
import org.eclipse.epsilon.incremental.trace.eol.Script;
import org.eclipse.epsilon.incremental.trace.eol.Trace;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.command.OCommandPredicate;
import com.orientechnologies.orient.core.command.traverse.OTraverse;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import java.util.ArrayList;


/**
 * An implementation of the ExecutionContext that delegates to an OrientDB vertex.
 */
public class ExecutionContextOrientDbImpl implements ExecutionContext {

	/** The delegate. */
	final private OrientVertex delegate;
	
	/**
	 * Instantiates a new ExecutionContextOrientDb.
	 *
	 * @param delegate the delegate
	 */
	public ExecutionContextOrientDbImpl(OrientVertex  delegate) {
		this.delegate = delegate;
		delegate.detach();
	}

	@Override
	public Object getId() {
		return delegate.getId();
	}

	@Override
	public Script getFor() {
		// Is this worth it, or used framed??
		OTraverse target = new OTraverse()
				.field("out")
				.target(new ORecordId((String)getId()))
				.predicate(new OCommandPredicate() {
					
						public Object evaluate(OIdentifiable iRecord, ODocument iCurrentResult, OCommandContext iContext) {
							return iCurrentResult.getSchemaClass().getName().equals("Script");
						}}
						);
		if (target.hasNext()) {
			return (Script) target.next();
		}
		return null;
	}

	@Override
	public void setFor(Script value) {
		// TODO Implement ExecutionContext.setFor
		throw new UnsupportedOperationException("Unimplemented Method    ExecutionContext.setFor invoked.");
	}

	@Override
	public List<Trace> getTraces() {
		// TODO Implement ExecutionContext.getTraces
		throw new UnsupportedOperationException("Unimplemented Method    ExecutionContext.getTraces invoked.");
	}

	@Override
	public List<ModelReference> getUses() {
		// TODO Implement ExecutionContext.getUses
		throw new UnsupportedOperationException("Unimplemented Method    ExecutionContext.getUses invoked.");
	}

	

	
}
