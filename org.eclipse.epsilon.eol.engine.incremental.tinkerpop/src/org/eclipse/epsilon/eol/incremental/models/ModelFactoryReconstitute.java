package org.eclipse.epsilon.eol.incremental.models;

import org.eclipse.epsilon.eol.incremental.trace.Model;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModelType;
import org.eclipse.epsilon.eol.incremental.trace.Property;

/**
 * The model factory is responsible for instantiating elements form the model aggregate:
 *  - Model
 *  - ModelType
 *  - ModelElement
 *  - ModelProperty
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface ModelFactoryReconstitute extends ModelFactory {

	// Maybe, so containment can be queried when needed? OR as in JDBC with a streaming or something to reduce 
	// memory?
	Model createModel(String name, Object delegate);
	
	ModelType createModelType(String name, Model container);
	
	ModelElement createModelElement(String uri, Model container);
	
	Property createProperty(String name, ModelElement container);
}
