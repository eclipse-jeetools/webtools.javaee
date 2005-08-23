/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 28, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.plugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddResourceFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.WebServicesClientResourceFactory;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.BundleContext;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServicePlugin extends WTPPlugin implements ResourceLocator {

	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.internal.internal.webservice"; //$NON-NLS-1$

	/**
	 * The reference to the singleton instance of this plugin.
	 */
	private static WebServicePlugin singleton;

	/**
	 * @param descriptor
	 */
	public WebServicePlugin() {
		super();
		if (singleton == null)
			singleton = this;

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
	 * Returns an image descriptor for the named resource as relative to the plugin install
	 * location.
	 * 
	 * @return An image descriptor, possibly null.
	 */
	public static ImageDescriptor getImageDescriptor(String name) {
		try {
			URL installURL = getInstance().getBundle().getEntry("/"); //$NON-NLS-1$
			URL imageURL = new URL(installURL, name);
			return ImageDescriptor.createFromURL(imageURL);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	/**
	 * Returns the singleton instance of this plugin. Equivalent to calling
	 * (ATKUIPlugin)Platform.getPlugin("com.ibm.etools.webservice.atk.ui");
	 * 
	 * @return The ATKUIPlugin singleton.
	 */
	static public WebServicePlugin getInstance() {
		return singleton;
	}

	/**
	 * Returns the message string identified by the given key from the plugin.properties file for
	 * the appropriate locale.
	 * 
	 * @param key
	 *            The message key string prefixed by a "%" symbol. That is, the string passed in
	 *            must be of the form "%KEY" where the plugin.properties file contains a line of the
	 *            form: "KEY = value".
	 * @return The locale-specific message.
	 */
	public static String getMessage(String key) {
		return Platform.getResourceString(getInstance().getBundle(), key);
	}

	/**
	 * Returns the message string identified by the given key from the plugin.properties file for
	 * the appropriate locale. Substitution sequences in the message string are replaced by the
	 * given array of substitution objects (which are most frequently strings). See
	 * java.text.MessageFormat for further details on substitution.
	 * 
	 * @param key
	 *            The message key string prefixed by a "%" symbol. That is, the string passed in
	 *            must be of the form "%KEY" where the plugin.properties file contains a line of the
	 *            form: "KEY = value".
	 * @param args
	 *            The substitution values for the message as required by the message in
	 *            plugin.properties and by the rules of class java.text.MessageFormat.
	 * @return The locale-specific message.
	 */
	public static String getMessage(String key, Object[] args) {
		return MessageFormat.format(getMessage(key), args);
	}


	/**
	 * Returns the URL from which all resources are based.
	 * 
	 * @return the URL from which all resources are based.
	 */
	public URL getBaseURL() {
		String installURL = getPluginInstallLocation();
		if (installURL != null) {
			try {
				StringBuffer sb = new StringBuffer(installURL);
				sb.append("plugin.properties"); //$NON-NLS-1$
				return new URL(sb.toString());
			} catch (MalformedURLException e) {
				//Ignore
			}
		}
		return null;
	}

	/**
	 * Returns the description that can be used to create the image resource associated with the
	 * key. The description will typically be in the form of a URL to the image data. Creation of an
	 * actual image depends on the GUI environment; within Eclipse,
	 * org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry can be used.
	 * 
	 * @param key
	 *            the key of the image resource.
	 * @return the description on the image resource.
	 */
	public Object getImage(String key) {
		ImageDescriptor imageDescriptor = getImageDescriptor(key);
		if (imageDescriptor != null)
			return imageDescriptor.createImage();
		return null;
	}

	public String getPluginStateLocation() {
		return Platform.getPluginStateLocation(this).addTrailingSeparator().toOSString();
	}

	public String getPluginInstallLocation() {
		try {
			return Platform.resolve(getInstance().getBundle().getEntry("/")).getFile(); //$NON-NLS-1$
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 * Returns the string resource associated with the key.
	 * 
	 * @param key
	 *            the key of the string resource.
	 * @return the string resource associated with the key.
	 */
	public String getString(String key) {
		return getMessage(key);
	}

	/**
	 * Returns a string resource associated with the key, and peforms substitutions.
	 * 
	 * @param key
	 *            the key of the string.
	 * @param substitutions
	 *            the message substitutions.
	 * @return a string resource associated with the key.
	 * @see #getString(String)
	 * @see MessageFormat#format(String, Object[])
	 */
	public String getString(String key, Object[] substitutions) {
		return getMessage(key, substitutions);
	}

	/**
	 * Called once by the platform when this plugin is first loaded.
	 * 
	 * @throws CoreException
	 *             If this plugin fails to start.
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		WsddResourceFactory.register(WTPResourceFactoryRegistry.INSTANCE);
		WebServicesClientResourceFactory.register(WTPResourceFactoryRegistry.INSTANCE);
	}

	/**
	 * Called once by the platform when this plugin is unloaded.
	 * 
	 * @throws CoreException
	 *             If this plugin fails to shutdown.
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}
	public String getString(String key, boolean translate) {
		// TODO For now...  translate not supported
		return getString(key);
	}

	public String getString(String key, Object[] substitutions, boolean translate) {
		// TODO For now...  translate not supported
		return getString(key,substitutions);
	}
}