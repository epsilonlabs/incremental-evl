package org.eclipse.epsilon.evl.incremental.dt;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	// The shared instance.
	private static Activator plugin;

	/**
	 * The constructor.
	 */
	public Activator() {
		super();
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
