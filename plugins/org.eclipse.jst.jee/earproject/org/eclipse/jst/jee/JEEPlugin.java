package org.eclipse.jst.jee;

import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JEEPlugin extends WTPPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.jst.jee";

	// The shared instance
	private static JEEPlugin plugin;
	
	private JEEPreferences preferences = null;
	
	/**
	 * The constructor
	 */
	public JEEPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JEEPlugin getDefault() {
		return plugin;
	}

	   /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.WTPPlugin#getPluginID()
	 */
	public String getPluginID() {
		return PLUGIN_ID;
	}
	
	/**
	 * @return Returns the preferences.
	 */
	public JEEPreferences getJEEPreferences() {
		if (this.preferences == null)
			this.preferences = new JEEPreferences(this);
		return this.preferences;
	}
	
	protected void initializeDefaultPluginPreferences() {
		getJEEPreferences().initializeDefaultPreferences();
	}

}
