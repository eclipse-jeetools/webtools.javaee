/*
 * Created on Nov 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.jca.ui.internal.plugin;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author jlanuti
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JCAUIPlugin extends AbstractUIPlugin {
	
	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.jca.ui"; //$NON-NLS-1$
	
	
//	The shared instance.
	private static JCAUIPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public JCAUIPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		plugin = this;
	}
	
	public JCAUIPlugin() {
		
	}

	/**
	 * Returns the shared instance.
	 */
	public static JCAUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = JCAUIPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null ? bundle.getString(key) : key);
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
