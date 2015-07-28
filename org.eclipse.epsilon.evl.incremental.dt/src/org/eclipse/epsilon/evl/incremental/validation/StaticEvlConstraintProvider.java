package org.eclipse.epsilon.evl.incremental.validation;

import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.CategoryManager;
import org.eclipse.emf.validation.service.AbstractConstraintProvider;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;
import org.osgi.framework.Bundle;

public class StaticEvlConstraintProvider extends AbstractConstraintProvider {

	private static final String VALIDATION_EXT_POINT = "org.eclipse.epsilon.evl.emf.validation";

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);

		IExtensionRegistry extensionRegistry = RegistryFactory.getRegistry();
		IExtensionPoint extensionPoint = extensionRegistry
				.getExtensionPoint(VALIDATION_EXT_POINT);

		for (IConfigurationElement configurationElement : extensionPoint
				.getConfigurationElements()) {

			try {
				// Get the package this supports
				String ePackageUri = configurationElement
						.getAttribute("namespaceURI");
				EPackage ePackage = EPackage.Registry.INSTANCE
						.getEPackage(ePackageUri);
				if (ePackage == null)
					continue;

				// Get the constraints file
				String bundleId = configurationElement.getAttribute("bundleId");

				if (bundleId == null || bundleId.trim().length() == 0) {
					bundleId = configurationElement.getDeclaringExtension()
							.getNamespaceIdentifier();
				}

				URL url = Platform.getBundle(bundleId).getResource(
						configurationElement.getAttribute("constraints"));
				if (url == null) {
					LogUtil.log(
							"Constraints file "
									+ configurationElement
											.getAttribute("constraints")
									+ " not found in bundle " + bundleId,
							new Exception());
					continue;
				}
		
				TraceEvlModule module = new TraceEvlModule(false);
				module.parse(url.toURI());
				
				if (!module.getParseProblems().isEmpty()) {
					continue;
					// TODO: More robust error handling
				}
				
				for (Constraint constraint : module.getConstraints()) {
					EvlConstraintDescriptor constraintDescriptor = this.adapt(constraint);
					this.addConstraint(constraintDescriptor, module);
				}
				
				this.registerConstraints(getConstraints());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void addConstraint(EvlConstraintDescriptor descriptor, TraceEvlModule module) {
		this.getConstraints().add(new EvlModelConstraint(descriptor, module));
	}
	
	protected EvlConstraintDescriptor adapt(Constraint constraint) {
		return new EvlConstraintDescriptor(constraint, this.getConstraints().size() + 1);
	}

	// TODO: Is this needed?
	private void createCategory(IConfigurationElement config,
			String propertyName, Object data) {
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
	}


	
}
