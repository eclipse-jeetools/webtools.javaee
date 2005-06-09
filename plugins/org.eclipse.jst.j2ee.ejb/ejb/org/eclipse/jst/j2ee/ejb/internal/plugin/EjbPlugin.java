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
package org.eclipse.jst.j2ee.ejb.internal.plugin;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.internal.boot.PlatformURLConnection;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.IEJBArchiveTransformationOperation;
import org.eclipse.jst.j2ee.internal.ejb.impl.EJBJarResourceFactory;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPluginResourceHandler;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


/**
 * This is a top-level class of the j2ee plugin.
 * 
 * @see AbstractUIPlugin for additional information on UI plugins
 */
public class EjbPlugin extends WTPPlugin implements ResourceLocator {
	// Default instance of the receiver
	private static EjbPlugin inst;
	protected final IPath iconsFolder = new Path(Platform.getBundle(PLUGIN_ID).getEntry("icons").getPath());//$NON-NLS-1$
	// Links View part of the plugin
	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.ejb";//$NON-NLS-1$
	private static IPath location;

	/**
	 * Create the J2EE plugin and cache its default instance
	 */
	public EjbPlugin() {
		super();
		if (inst == null)
			inst = this;
	}

	/**
	 * Get the plugin singleton.
	 */
	static public EjbPlugin getDefault() {
		return inst;
	}

	/*
	 * Javadoc copied from interface.
	 */
	public URL getBaseURL() {
		return getBundle().getEntry("/");
	}

	/**
	 * This gets a .gif from the icons folder.
	 */
	public Object getImage(String key) {
		return J2EEPlugin.getImageURL(key, getBundle());
	}

	public static IPath getInstallLocation() {
		if (location == null) {
			URL url = getInstallURL();
			try {
				String installLocation = ((PlatformURLConnection) url.openConnection()).getURLAsLocal().getFile();
				location = new Path(installLocation);
			} catch (IOException e) {
				org.eclipse.jem.util.logger.proxy.Logger.getLogger().logWarning(J2EEPluginResourceHandler.getString("Install_Location_Error_", new Object[]{url}) + e); //$NON-NLS-1$
			}
		}
		return location;
	}

	public static URL getInstallURL() {
		return getDefault().getBundle().getEntry("/");
	}

	public static EjbPlugin getPlugin() {
		return inst;
	}

	/**
	 * Return the plugin directory location- the directory that all the plugins are located in (i.e.
	 * d:\installdir\plugin)
	 */
	public static IPath getPluginLocation(String pluginId) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		Bundle bundle = Platform.getBundle(pluginId);
		if (bundle != null) {
			try {
				IPath installPath = new Path(bundle.getEntry("/").toExternalForm()).removeTrailingSeparator();
				String installStr = Platform.asLocalURL(new URL(installPath.toString())).getFile();
				return new Path(installStr);
			} catch (IOException e) {
			};
		}
		return null;
	}

	/**
	 * If this is called from an operation, in response to some other exception that was caught,
	 * then the client code should throw {@link com.ibm.etools.wft.util.WFTWrappedException};
	 * otherwise this can still be used to signal some other error condition within the operation,
	 * or to throw a core exception in a context other than executing an operation
	 * 
	 * Create a new IStatus of type ERROR, code OPERATION_FAILED, using the J2EEPlugin ID
	 */
	public static IStatus createErrorStatus(String aMessage, Throwable exception) {
		return createErrorStatus(0, aMessage, exception);
	}

	/**
	 * If this is called from an operation, in response to some other exception that was caught,
	 * then the client code should throw {@link com.ibm.etools.wft.util.WFTWrappedException};
	 * otherwise this can still be used to signal some other error condition within the operation.
	 * 
	 * Create a new IStatus of type ERROR, code OPERATION_FAILED, using the J2EEPlugin ID
	 */
	public static IStatus newOperationFailedStatus(String aMessage, Throwable exception) {
		return createStatus(IStatus.ERROR, IResourceStatus.OPERATION_FAILED, aMessage, exception);
	}

	/**
	 * Create a new IStatus with a severity using the J2EEPlugin ID. aCode is just an internal code.
	 */
	public static IStatus createStatus(int severity, int aCode, String aMessage, Throwable exception) {
		return new Status(severity, PLUGIN_ID, aCode, aMessage, exception);
	}

	/*
	 * Javadoc copied from interface.
	 */
	public String getString(String key) {
		return Platform.getResourceString(getBundle(), key);
	}

	/*
	 * Javadoc copied from interface.
	 */
	public String getString(String key, Object[] substitutions) {
		return MessageFormat.format(getString(key), substitutions);
	}

	public String getPluginID() {
		return PLUGIN_ID;
	}

	/**
	 * If this is called from an operation, in response to some other exception that was caught,
	 * then the client code should throw {@link com.ibm.etools.wft.util.WFTWrappedException};
	 * otherwise this can still be used to signal some other error condition within the operation,
	 * or to throw a core exception in a context other than executing an operation
	 * 
	 * Create a new IStatus of type ERROR using the J2EEPlugin ID. aCode is just an internal code.
	 */
	public static IStatus createErrorStatus(int aCode, String aMessage, Throwable exception) {
		return createStatus(IStatus.ERROR, aCode, aMessage, exception);
	}

	public IEJBArchiveTransformationOperation getExtendedArchiveOperation() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint pct = registry.getExtensionPoint(J2EEPlugin.PLUGIN_ID, "PreAndPostArchiveOperation");//$NON-NLS-1$
		IExtension[] extension = pct.getExtensions();
		for (int l = 0; l < extension.length; ++l) {
			IExtension config = extension[l];
			IConfigurationElement[] cElems = config.getConfigurationElements();
			for (int i = 0; i < cElems.length; i++) {
				IConfigurationElement d = cElems[i];
				if (d.getName().equals("operation")) //$NON-NLS-1$
				{
					// operation class
					try {
						return (IEJBArchiveTransformationOperation) d.createExecutableExtension("run");//$NON-NLS-1$
						/*
						 * Class aClass = null;
						 * 
						 * aClass = Class.forName(className);
						 *  
						 */
					} catch (Exception ex) {
						return null;
					}
				}
			}
		}
		return null;
	}
	public void start(BundleContext context) throws Exception {
		super.start(context);
		EJBJarResourceFactory.register(WTPResourceFactoryRegistry.INSTANCE);
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