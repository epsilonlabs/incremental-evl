package org.eclipse.epsilon.evl.incremental.validation;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.validation.internal.modeled.model.validation.ConstraintProvider;
import org.eclipse.emf.validation.service.AbstractConstraintProvider;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;

/**
 * {@link ConstraintProvider} that gets its constraints from the
 * {@code org.eclipse.epsilon.evl.emf.validation} extension point.
 * 
 * @author Jonathan Co
 *
 */
public class StaticEvlConstraintProvider extends AbstractConstraintProvider {

	private static final String VALIDATION_EXT_POINT = "org.eclipse.epsilon.evl.emf.validation";

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);

		IExtensionRegistry extensionRegistry = RegistryFactory.getRegistry();
		IExtensionPoint extensionPoint = extensionRegistry
				.getExtensionPoint(VALIDATION_EXT_POINT);

		for (IConfigurationElement configElement : extensionPoint.getConfigurationElements()) {

			// Get the ePackage this applies to
			final EPackage ePackage = this.getEPackage(configElement);
			if (ePackage == null) {
				continue;
			}

			// Get the constraints file
			final URI evlFile = this.getEvlFile(configElement);
			if (evlFile == null) {
				continue;
			}
			
			// All required configuration elements present, parse file
			try {
				// Attempt to parse the file
				TraceEvlModule module = new TraceEvlModule(false);
				module.parse(evlFile);

				if (!module.getParseProblems().isEmpty()) {
					System.out.println("NO CONSTRAINTS PARSED");
					continue;
					// TODO: More robust error handling
				}

				// Adapt the constraints to EMF Validation Framework
				for (Constraint constraint : module.getConstraints()) {
					final int statusCode = this.getConstraints().size() + 1;
					final EvlEmfConstraint evlEmfConstraint = 
							new EvlEmfConstraint(constraint, statusCode);
					this.getConstraints().add(evlEmfConstraint);
				}

				this.registerConstraints(getConstraints()); // FIXME: handle exception properly

			} catch (Exception e) {
				// FIXME
				e.printStackTrace();
			}
		}

	}

	/**
	 * Retrieve the {@link EPackage} specified by the
	 * {@code org.eclipse.epsilon.evl.validation} extension point.
	 * 
	 * @param configElement
	 *            Configuration element to look in
	 * @return The {@link EPackage} or {@code null} if none has been specified
	 */
	protected EPackage getEPackage(IConfigurationElement configElement) {
		// Get the package this supports
		String ePackageUri = configElement.getAttribute("namespaceURI");
		return EPackage.Registry.INSTANCE.getEPackage(ePackageUri);
	}
	
	/**
	 * Retrieve the {@link URI} of the EVL file specified by the
	 * {@code org.eclipse.epsilon.evl.validation} extension point.
	 * 
	 * @param configElement
	 *            Configuration element to look in
	 * @return The URI of an EVL file or {@code null} if none has been specified
	 */
	protected URI getEvlFile(IConfigurationElement configElement) {
		String bundleId = configElement.getAttribute("bundleId");

		if (bundleId == null || bundleId.trim().length() == 0) {
			bundleId = configElement.getDeclaringExtension()
					.getNamespaceIdentifier();
		}

		// Retrieve the name of the constraints file
		final String constraints = configElement.getAttribute("constraints");
		final URL url = Platform.getBundle(bundleId).getResource(constraints);
				
		try {
			return url == null ? null : url.toURI();
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	// TODO: Is this needed?
//	private void createCategory(IConfigurationElement config,
//			String propertyName, Object data) {
//		// Create constraint category
//		String categoryID = config.getDeclaringExtension()
//				.getUniqueIdentifier();
//		if (categoryID == null) {
//			categoryID = "EvlConstraintProvider@"
//					+ Long.toHexString(System.identityHashCode(this));
//		}
//		categoryID = "org.eclipse.epsilon.evl.incremental.validation.category";
//
//		Category category = CategoryManager.getInstance().getCategory(
//				categoryID);
//		category.setName(config.getAttribute("category"));
//
//		Bundle contributor = Platform.getBundle(config.getDeclaringExtension()
//				.getNamespaceIdentifier());
//	}
	
}
