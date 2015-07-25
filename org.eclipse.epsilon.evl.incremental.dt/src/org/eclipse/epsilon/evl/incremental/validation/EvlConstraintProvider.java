package org.eclipse.epsilon.evl.incremental.validation;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.CategoryManager;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.AbstractConstraintProvider;
import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.osgi.framework.Bundle;

public class EvlConstraintProvider extends AbstractConstraintProvider {

	public EvlConstraintProvider() {
		super();
	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {

		super.setInitializationData(config, propertyName, data);

		// Create constraint category
		String categoryID = config.getDeclaringExtension()
				.getUniqueIdentifier();
		if (categoryID == null) {
			categoryID = "EvlConstraintProvider@"
					+ Long.toHexString(System.identityHashCode(this));
		}
		categoryID = "org.eclipse.epsilon.evl.incremental.dt.category";

		Category category = CategoryManager.getInstance().getCategory(
				categoryID);
		category.setName(config.getAttribute("category"));

		Bundle contributor = Platform.getBundle(config.getDeclaringExtension()
				.getNamespaceIdentifier());

		// TODO: Delegate to retrieve EVL Constraints here
		// Add a dummy constraint for now
		List<IModelConstraint> constraints = getConstraints();

		try {
			registerConstraints(constraints);
		} catch (ConstraintExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if (category != null)
		//
		// getConstraints().add(new EvlConstraintDescriptor(null, null, 1));
	}

	@Override
	protected List<IModelConstraint> getConstraints() {
		System.out.println("here");
		return super.getConstraints();
	}

	@Override
	public Collection<IModelConstraint> getLiveConstraints(
			Notification notification, Collection<IModelConstraint> constraints) {
		System.out.println("live");
		return super.getLiveConstraints(notification, constraints);
	}

	@Override
	public Collection<IModelConstraint> getBatchConstraints(EObject eObject,
			Collection<IModelConstraint> constraints) {
		System.out.println("batch");
		return super.getBatchConstraints(eObject, constraints);
	}

}
