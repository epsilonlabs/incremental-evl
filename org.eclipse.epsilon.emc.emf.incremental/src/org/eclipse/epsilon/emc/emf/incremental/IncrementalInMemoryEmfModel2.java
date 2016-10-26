package org.eclipse.epsilon.emc.emf.incremental;

import java.util.Collection;
import java.util.List;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.compile.m3.Metamodel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.transactions.IModelTransactionSupport;

public class IncrementalInMemoryEmfModel2 implements IModel {

	@Override
	public void load(StringProperties properties) throws EolModelLoadingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(StringProperties properties, String basePath) throws EolModelLoadingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver relativePathResolver)
			throws EolModelLoadingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() throws EolModelLoadingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> allContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getTypeOf(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeNameOf(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFullyQualifiedTypeNameOf(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createInstance(String type, Collection<Object> parameters)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getElementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementId(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElementId(Object instance, String newId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElement(Object instance) throws EolRuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean owns(Object instance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean knowsAboutProperty(Object instance, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPropertySet(Object instance, String property) throws EolRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInstantiable(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModelElement(Object instance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store(String location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPropertyGetter getPropertyGetter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertySetter getPropertySetter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStoredOnDisposal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStoredOnDisposal(boolean storedOnDisposal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isReadOnLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReadOnLoad(boolean readOnLoad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IModelTransactionSupport getTransactionSupport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Metamodel getMetamodel(StringProperties properties, IRelativePathResolver resolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
