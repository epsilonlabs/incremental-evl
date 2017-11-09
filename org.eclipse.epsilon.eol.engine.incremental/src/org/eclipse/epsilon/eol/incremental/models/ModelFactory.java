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
 *  
 * The model is the root entity, as the ids of the other entities are unique only inside the aggregate.  
 * @author Horacio Hoyos Rodriguez
 *
 */
public interface ModelFactory {

	Model createModel(String name);
	
	ModelType createModelType(String name, Model container);
	
	ModelElement createModelElement(String uri, Model container);
	
	Property createProperty(String name, ModelElement container);
}
