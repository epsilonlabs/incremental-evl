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
package org.eclipse.epsilon.evl.incremental.orientdb.models;

import java.util.Collection;
import java.util.List;
import java.util.Queue;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.transactions.IModelTransactionSupport;
import org.eclipse.epsilon.evl.incremental.models.AbstractEvlTraceModel;
import org.eclipse.epsilon.evl.incremental.orientdb.dialog.OrientDBManagerConfiguration;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.OrientDbEvlTraceInformation;
import org.eclipse.epsilon.evl.incremental.orientdb.execute.TraceOrientDbDAO;
import org.eclipse.epsilon.incremental.trace.eol.ExecutionContext;
import org.eclipse.epsilon.incremental.trace.eol.Trace;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

/**
 * The Class OrientDbEvlTraceModel.
 */
public class OrientDbEvlTraceModel extends AbstractEvlTraceModel  {
	
	/** The url of the DB. */
	private String url;
	
	/** The user to use for credentials. */
	private String user;
	
	/** The password to use for credentials. */
	private String password;
	
	/** Flag to indicate if the DB should be setup (crate schema). */
	private boolean setup;
	
	/** The pool minimum size. */
	private int poolMinSize;
	
	/** The pool maximum size. */
	private int poolMaxSize;
	
	/** The factory. */
	private OrientGraphFactory factory;
	
	/** A flag to indicate if a connection pool should be created. */
	private boolean createPool;
	
	private final Queue<OrientDbEvlTraceInformation> traceQueue;
	
	public OrientDbEvlTraceModel(Queue<OrientDbEvlTraceInformation> traceQueue) {
		super();
		this.traceQueue = traceQueue;
	}

	@Override
	public ExecutionContext acquireExecutionContext(String scriptId, List<String> modelsUris) {
		// TODO Implement ITraceModel.acquireExecutionContext
		throw new UnsupportedOperationException("Unimplemented Method    ITraceModel.acquireExecutionContext invoked.");
	}

	@Override
	public Collection<?> allContents() {
		// TODO Implement IModel.allContents
		throw new UnsupportedOperationException("Unimplemented Method    IModel.allContents invoked.");
	}

	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		// TODO Implement IModel.createInstance
		throw new UnsupportedOperationException("Unimplemented Method    IModel.createInstance invoked.");
	}

	@Override
	public void deleteElement(Object instance) throws EolRuntimeException {
		// TODO Implement IModel.deleteElement
		throw new UnsupportedOperationException("Unimplemented Method    IModel.deleteElement invoked.");
	}

	@Override
	public List<Trace> findTraces(String elementId) {
		// TODO Implement ITraceModel.findTraces
		throw new UnsupportedOperationException("Unimplemented Method    ITraceModel.findTraces invoked.");
	}

	@Override
	public List<Trace> findTraces(String elementId, String propertyId) {
		// TODO Implement ITraceModel.findTraces
		throw new UnsupportedOperationException("Unimplemented Method    ITraceModel.findTraces invoked.");
	}

	@Override
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		// TODO Implement IModel.getAllOfKind
		throw new UnsupportedOperationException("Unimplemented Method    IModel.getAllOfKind invoked.");
	}

	@Override
	public Collection<?> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		// TODO Implement IModel.getAllOfType
		throw new UnsupportedOperationException("Unimplemented Method    IModel.getAllOfType invoked.");
	}

	@Override
	public Object getElementById(String id) {
		// TODO Implement IModel.getElementById
		throw new UnsupportedOperationException("Unimplemented Method    IModel.getElementById invoked.");
	}

	@Override
	public String getElementId(Object instance) {
		// TODO Implement IModel.getElementId
		throw new UnsupportedOperationException("Unimplemented Method    IModel.getElementId invoked.");
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		// TODO Implement IModel.getEnumerationValue
		throw new UnsupportedOperationException("Unimplemented Method    IModel.getEnumerationValue invoked.");
	}

	@Override
	public String getTypeNameOf(Object instance) {
		// TODO Implement IModel.getTypeNameOf
		throw new UnsupportedOperationException("Unimplemented Method    IModel.getTypeNameOf invoked.");
	}
	
	/**
	 * Load the model using the set of properties specified by the first argument.
	 *
	 * @param properties the properties
	 * @param resolver the resolver
	 * @throws EolModelLoadingException the eol model loading exception
	 * @see OrientDBManagerConfiguration#PROPERTY_DB_URL
	 * @see OrientDBManagerConfiguration#PROPERTY_DB_USER
	 * @see OrientDBManagerConfiguration#PROPERTY_DB_PASSSWORD
	 * @see OrientDBManagerConfiguration#PROPERTY_DB_CREATE
	 * @see OrientDBManagerConfiguration#PROPERTY_DB_POOL_MIN_SIZE
	 * @see OrientDBManagerConfiguration#PROPERTY_DB_POOL_MAX_SIZE
	 */
	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		
		super.load(properties, resolver);
		url = properties.getProperty(OrientDBManagerConfiguration.PROPERTY_DB_URL);
		user = properties.getProperty(OrientDBManagerConfiguration.PROPERTY_DB_USER, "");
		password = properties.getProperty(OrientDBManagerConfiguration.PROPERTY_DB_PASSSWORD, "");
		// "memory:EVLTrace"
		setup = properties.getBooleanProperty(OrientDBManagerConfiguration.PROPERTY_DB_CREATE, false);
		createPool = properties.getBooleanProperty(OrientDBManagerConfiguration.PROPERTY_DB_CREATE_POOL, true);
		poolMinSize = properties.getIntegerProperty(OrientDBManagerConfiguration.PROPERTY_DB_POOL_MIN_SIZE, 8);
		poolMaxSize = properties.getIntegerProperty(OrientDBManagerConfiguration.PROPERTY_DB_POOL_MAX_SIZE, -1);
		load();
	}

	@Override
	public void load() throws EolModelLoadingException {
		if (user.equals("")) {
			factory = new OrientGraphFactory(url);
		}
		else {
			factory = new OrientGraphFactory(url, user, password);
		}
		if (createPool) {
			factory.setupPool(poolMinSize, poolMaxSize);
		}
		TraceOrientDbDAO orientDbDAO = new TraceOrientDbDAO(factory);
		if (setup) {
			try {
				orientDbDAO.setupSchema();
			}
			catch (Exception ex) {
				throw new EolModelLoadingException(ex, this);
			}
		}
		if (!readOnLoad) {
			factory.drop();
		}
	}

	@Override
	public boolean owns(Object instance) {
		// TODO Implement IModel.owns
		throw new UnsupportedOperationException("Unimplemented Method    IModel.owns invoked.");
	}

	@Override
	public void setElementId(Object instance, String newId) {
		throw new UnsupportedOperationException("DB ids can not be changed.");
	}
	
	/**
	 * Dumps the que to the DB. 
	 */
	@Override
	public boolean store() {
		return true;
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
		factory.close();
	}

	@Override
	public boolean store(String location) {
		throw new UnsupportedOperationException("Orient DB can not be stored in a alternate location.");
	}


	
	// Generator ideas
//	public T insertT(Object tId) {
//		TVertex tV = orientDbDAO.createT(tId);
//		if (tV == null) {
//			throw new EOLIncrementalExecutionException("Error creating the DB vertex.");
//		}
//		TImpl timpl = null;
//		try {
//			timpl = OrientDbUtil.wrap(TImpl.class, tV);
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e) {
//			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
//		}
//		return timpl;
//	}
//	
//	public List<VModelElement> findTbyId(Object id) {
//		TVertex tV = orientDbDAO.findT(tId);
//		if (tV == null) {
//			throw new EOLIncrementalExecutionException("Error creating the DB vertex.");
//		}
//		TImpl timpl = null;
//		try {
//			timpl = OrientDbUtil.wrap(TImpl.class, tV);
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e) {
//			throw new EOLIncrementalExecutionException("Error creating the DB vertex wrapper instance..", e);
//		}
//		return timpl;
//    }    
	
	//delete
	
	//update
	
	/* The dao is the same.. which makes me think this interface should be simpler...
	 * an abstraction as IModel is
	
	insert = createInstance(T)
	findbyId = findInstance(T, id), ids should be unique -> findInstance(id)
	deletebyId = delete(id);  -> delete(element)
	update = update(T t) -> we can get the type and Id from t
	
	this methods will use a switch or similar to dispatch, or a generics method
	
	update is the only real difference with IModel... then perhaps it is a IDaoModel
	or something that requires the update because the elements are unsyncronized with
	the persistance layer. We could ignore the update and just rely on the store
	method to persist the changes. 
	
	The property getter and setter could be used to create edges....
	
	The Ecore could be used to create the abstract implementation that adds the switch
	calls using a dao and a hasType that only supports the known types.
		
	*/
	

	////////////////
	
	

}
