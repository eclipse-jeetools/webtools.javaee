package org.eclipse.jst.validation.test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.util.logger.proxyrender.DefaultPluginTraceRenderer;
import org.osgi.framework.BundleContext;


/**
 * Plugin for TVT testing.
 */
public class BVTValidationPlugin extends Plugin {
	private static BVTValidationPlugin inst = null;
	public static final String PLUGIN_ID = "org.eclipse.jst.validation.test"; //$NON-NLS-1$
	private ResourceBundle resourceBundle;
	protected static Logger logger = null;

	/**
	 * ValidationTVTPlugin constructor comment.
	 * @param descriptor org.eclipse.core.runtime.IPluginDescriptor
	 */
	public BVTValidationPlugin() {
		super();
		inst = this;
	}
	public static BVTValidationPlugin getPlugin() {
		return inst;
	}
	
	public BVTValidationPlugin(IPluginDescriptor pd) {
		this();
	}
	public String getPluginID() {
	    return PLUGIN_ID;
	}
	
	public Logger getMsgLogger() {
		if (logger == null) {
			logger = Logger.getLogger(getPluginID());
			setRenderer(logger);
		}
		return logger;
	}
	
	/**
	 * @param aLogger
	 */
	protected void setRenderer(Logger aLogger) {
		new DefaultPluginTraceRenderer(aLogger);
	}
	
	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle= getPlugin().getResourceBundle();
		try {
			return (bundle!=null ? bundle.getString(key) : key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
	
	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle.getBundle("org.eclipse.jst.validation.test.BVTValidationPluginResource");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}
	
	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		inst = null;
		resourceBundle = null;
	}
}
