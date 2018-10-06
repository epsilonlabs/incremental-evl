package thanos;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
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

public class IncrementalEmfModel extends AbstractIncrementalEMFModel implements IModel  {
	
	/**
	 * @param properties
	 * @throws EolModelLoadingException
	 * @see org.eclipse.epsilon.eol.models.IModel#load(org.eclipse.epsilon.common.util.StringProperties)
	 */
	public void load(StringProperties properties) throws EolModelLoadingException {
		delegate.load(properties);
	}

	/**
	 * @param properties
	 * @param basePath
	 * @throws EolModelLoadingException
	 * @see org.eclipse.epsilon.eol.models.IModel#load(org.eclipse.epsilon.common.util.StringProperties, java.lang.String)
	 */
	public void load(StringProperties properties, String basePath) throws EolModelLoadingException {
		delegate.load(properties, basePath);
	}

	/**
	 * @param properties
	 * @param relativePathResolver
	 * @throws EolModelLoadingException
	 * @see org.eclipse.epsilon.eol.models.IModel#load(org.eclipse.epsilon.common.util.StringProperties, org.eclipse.epsilon.eol.models.IRelativePathResolver)
	 */
	public void load(StringProperties properties, IRelativePathResolver relativePathResolver)
			throws EolModelLoadingException {
		delegate.load(properties, relativePathResolver);
	}

	/**
	 * @throws EolModelLoadingException
	 * @see org.eclipse.epsilon.eol.models.IModel#load()
	 */
	public void load() throws EolModelLoadingException {
		delegate.load();
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getName()
	 */
	public String getName() {
		return delegate.getName();
	}

	/**
	 * @param name
	 * @see org.eclipse.epsilon.eol.models.IModel#setName(java.lang.String)
	 */
	public void setName(String name) {
		delegate.setName(name);
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getAliases()
	 */
	public List<String> getAliases() {
		return delegate.getAliases();
	}

	/**
	 * @param enumeration
	 * @param label
	 * @return
	 * @throws EolEnumerationValueNotFoundException
	 * @see org.eclipse.epsilon.eol.models.IModel#getEnumerationValue(java.lang.String, java.lang.String)
	 */
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		return delegate.getEnumerationValue(enumeration, label);
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#allContents()
	 */
	public Collection<?> allContents() {
		return delegate.allContents();
	}

	/**
	 * @param type
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 * @see org.eclipse.epsilon.eol.models.IModel#getAllOfType(java.lang.String)
	 */
	public Collection<?> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		return delegate.getAllOfType(type);
	}

	/**
	 * @param type
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 * @see org.eclipse.epsilon.eol.models.IModel#getAllOfKind(java.lang.String)
	 */
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		return delegate.getAllOfKind(type);
	}

	/**
	 * @param instance
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getTypeOf(java.lang.Object)
	 */
	public Object getTypeOf(Object instance) {
		return delegate.getTypeOf(instance);
	}

	/**
	 * @param instance
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getTypeNameOf(java.lang.Object)
	 */
	public String getTypeNameOf(Object instance) {
		return delegate.getTypeNameOf(instance);
	}

	/**
	 * @param instance
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getFullyQualifiedTypeNameOf(java.lang.Object)
	 */
	public String getFullyQualifiedTypeNameOf(Object instance) {
		return delegate.getFullyQualifiedTypeNameOf(instance);
	}

	/**
	 * @param type
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 * @throws EolNotInstantiableModelElementTypeException
	 * @see org.eclipse.epsilon.eol.models.IModel#createInstance(java.lang.String)
	 */
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		return delegate.createInstance(type);
	}

	/**
	 * @param type
	 * @param parameters
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 * @throws EolNotInstantiableModelElementTypeException
	 * @see org.eclipse.epsilon.eol.models.IModel#createInstance(java.lang.String, java.util.Collection)
	 */
	public Object createInstance(String type, Collection<Object> parameters)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		return delegate.createInstance(type, parameters);
	}

	/**
	 * @param id
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getElementById(java.lang.String)
	 */
	public Object getElementById(String id) {
		return delegate.getElementById(id);
	}

	/**
	 * @param instance
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getElementId(java.lang.Object)
	 */
	public String getElementId(Object instance) {
		return delegate.getElementId(instance);
	}

	/**
	 * @param instance
	 * @param newId
	 * @see org.eclipse.epsilon.eol.models.IModel#setElementId(java.lang.Object, java.lang.String)
	 */
	public void setElementId(Object instance, String newId) {
		delegate.setElementId(instance, newId);
	}

	/**
	 * @param instance
	 * @throws EolRuntimeException
	 * @see org.eclipse.epsilon.eol.models.IModel#deleteElement(java.lang.Object)
	 */
	public void deleteElement(Object instance) throws EolRuntimeException {
		delegate.deleteElement(instance);
	}

	/**
	 * @param instance
	 * @param type
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 * @see org.eclipse.epsilon.eol.models.IModel#isOfKind(java.lang.Object, java.lang.String)
	 */
	public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
		return delegate.isOfKind(instance, type);
	}

	/**
	 * @param instance
	 * @param type
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 * @see org.eclipse.epsilon.eol.models.IModel#isOfType(java.lang.Object, java.lang.String)
	 */
	public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
		return delegate.isOfType(instance, type);
	}

	/**
	 * @param instance
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#owns(java.lang.Object)
	 */
	public boolean owns(Object instance) {
		return delegate.owns(instance);
	}

	/**
	 * @param instance
	 * @param property
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#knowsAboutProperty(java.lang.Object, java.lang.String)
	 */
	public boolean knowsAboutProperty(Object instance, String property) {
		return delegate.knowsAboutProperty(instance, property);
	}

	/**
	 * @param instance
	 * @param property
	 * @return
	 * @throws EolRuntimeException
	 * @see org.eclipse.epsilon.eol.models.IModel#isPropertySet(java.lang.Object, java.lang.String)
	 */
	public boolean isPropertySet(Object instance, String property) throws EolRuntimeException {
		return delegate.isPropertySet(instance, property);
	}

	/**
	 * @param type
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#isInstantiable(java.lang.String)
	 */
	public boolean isInstantiable(String type) {
		return delegate.isInstantiable(type);
	}

	/**
	 * @param instance
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#isModelElement(java.lang.Object)
	 */
	public boolean isModelElement(Object instance) {
		return delegate.isModelElement(instance);
	}

	/**
	 * @param type
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#hasType(java.lang.String)
	 */
	public boolean hasType(String type) {
		return delegate.hasType(type);
	}

	/**
	 * @param location
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#store(java.lang.String)
	 */
	public boolean store(String location) {
		return delegate.store(location);
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#store()
	 */
	public boolean store() {
		return delegate.store();
	}

	/**
	 * 
	 * @see org.eclipse.epsilon.eol.models.IModel#dispose()
	 */
	public void dispose() {
		delegate.dispose();
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getPropertyGetter()
	 */
	public IPropertyGetter getPropertyGetter() {
		return delegate.getPropertyGetter();
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getPropertySetter()
	 */
	public IPropertySetter getPropertySetter() {
		return delegate.getPropertySetter();
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#isStoredOnDisposal()
	 */
	public boolean isStoredOnDisposal() {
		return delegate.isStoredOnDisposal();
	}

	/**
	 * @param storedOnDisposal
	 * @see org.eclipse.epsilon.eol.models.IModel#setStoredOnDisposal(boolean)
	 */
	public void setStoredOnDisposal(boolean storedOnDisposal) {
		delegate.setStoredOnDisposal(storedOnDisposal);
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#isReadOnLoad()
	 */
	public boolean isReadOnLoad() {
		return delegate.isReadOnLoad();
	}

	/**
	 * @param readOnLoad
	 * @see org.eclipse.epsilon.eol.models.IModel#setReadOnLoad(boolean)
	 */
	public void setReadOnLoad(boolean readOnLoad) {
		delegate.setReadOnLoad(readOnLoad);
	}

	/**
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getTransactionSupport()
	 */
	public IModelTransactionSupport getTransactionSupport() {
		return delegate.getTransactionSupport();
	}

	/**
	 * @param properties
	 * @param resolver
	 * @return
	 * @see org.eclipse.epsilon.eol.models.IModel#getMetamodel(org.eclipse.epsilon.common.util.StringProperties, org.eclipse.epsilon.eol.models.IRelativePathResolver)
	 */
	public Metamodel getMetamodel(StringProperties properties, IRelativePathResolver resolver) {
		return delegate.getMetamodel(properties, resolver);
	}

	@Override
	public Resource getResource() {
		return ((EmfModel) delegate).getResource();
	}
	
}
