package org.eclipse.jst.validation.test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.frameworks.internal.WTPPlugin;


/**
 * Plugin for TVT testing.
 */
public class BVTValidationPlugin extends WTPPlugin {
	private static BVTValidationPlugin inst = null;
	public static final String PLUGIN_ID = "org.eclipse.jst.validation.test"; //$NON-NLS-1$
	private ResourceBundle resourceBundle;

	/**
	 * ValidationTVTPlugin constructor comment.
	 * @param descriptor org.eclipse.core.runtime.IPluginDescriptor
	 */
	public BVTValidationPlugin() {
		super();
		inst = this;
		try {
			resourceBundle= ResourceBundle.getBundle("org.eclipse.jst.validation.test.ValidationTestResource");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}
	public static BVTValidationPlugin getPlugin() {
		return inst;
	}
	public String getPluginID() {
	    return PLUGIN_ID;
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
		return resourceBundle;
	}
}
