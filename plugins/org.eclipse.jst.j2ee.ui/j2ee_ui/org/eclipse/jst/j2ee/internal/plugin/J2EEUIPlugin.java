/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.internal.boot.PlatformURLConnection;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import java.lang.Throwable;


/**
 * The main plugin class to be used in the desktop.
 */
public class J2EEUIPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.ui"; //$NON-NLS-1$

	//The shared instance.
	private static J2EEUIPlugin plugin;
	private static IPath location;

	/**
	 * The constructor.
	 */
	public J2EEUIPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static J2EEUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	public static IPath getInstallLocation() {
		if (location == null) {
			URL url = getInstallURL();
			try {
				String installLocation = ((PlatformURLConnection) url.openConnection()).getURLAsLocal().getFile();
				location = new Path(installLocation);
			}catch (IOException e) {
				logWarning(J2EEPluginResourceHandler.getString("Install_Location_Error_", new Object[]{url}) + e); //$NON-NLS-1$
			}
		}
		return location;
	}

	public static URL getInstallURL() {
		return getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
	}

	/**
	 * Get a .gif from the image registry.
	 */
	public Image getImage(String key) {
		ImageRegistry imageRegistry = getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null || image.isDisposed()) {
			ImageDescriptor descriptor = getImageDescriptor(key);
			if (descriptor != null) {
				image = descriptor.createImage();
				imageRegistry.put(key, image);
			}
		}
		return image;
	}

	/**
	 * This gets a .gif from the icons folder.
	 */
	public ImageDescriptor getImageDescriptor(String key) {
		ImageDescriptor imageDescriptor = null;
		URL gifImageURL = getImageURL(key);
		if (gifImageURL != null)
			imageDescriptor = ImageDescriptor.createFromURL(gifImageURL);
		return imageDescriptor;
	}

	/**
	 * @param key
	 * @return
	 */
	private URL getImageURL(String key) {
		return J2EEPlugin.getImageURL(key, getBundle());
	}

	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getPluginWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * Return the workbench
	 * 
	 * This method is internal to the j2ee plugin and must not be called by any other plugins.
	 */

	public static IWorkbench getPluginWorkbench() {
		return getDefault().getWorkbench();
	}
	
	public static Shell getActiveWorkbenchShell() {
		 IWorkbenchWindow window = getActiveWorkbenchWindow();
		 if (window != null) {
		 	return window.getShell();
		 }
		 return null;
	}

	public static IStructuredSelection getCurrentSelection() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				return (IStructuredSelection) selection;
			}

		}
		return null;
	}

	
	/**
	 * Record an error against this plugin's log. 
	 * 
	 * @param aCode
	 * @param aMessage
	 * @param anException
	 */
	public static void logError(int aCode, String aMessage,
			Throwable anException) {
		getDefault().getLog().log(
				createErrorStatus(aCode, aMessage, anException));
	}

	/**
	 * 
	 * Record a message against this plugin's log. 
	 * 
	 * @param severity
	 * @param aCode
	 * @param aMessage
	 * @param exception
	 */
	public static void log(int severity, int aCode, String aMessage,
			Throwable exception) {
		log(createStatus(severity, aCode, aMessage, exception));
	}

	/**
	 * 
	 * Record a status against this plugin's log. 
	 * 
	 * @param aStatus
	 */
	public static void log(IStatus aStatus) {
		getDefault().getLog().log(aStatus);
	}

	/**
	 * Create a status associated with this plugin.
	 *  
	 * @param severity
	 * @param aCode
	 * @param aMessage
	 * @param exception
	 * @return A status configured with this plugin's id and the given parameters.
	 */
	public static IStatus createStatus(int severity, int aCode,
			String aMessage, Throwable exception) {
		return new Status(severity, PLUGIN_ID, aCode,
				aMessage != null ? aMessage : "No message.", exception); //$NON-NLS-1$
	}

	/**
	 * 
	 * @param aCode
	 * @param aMessage
	 * @param exception
	 * @return A status configured with this plugin's id and the given parameters.
	 */
	public static IStatus createErrorStatus(int aCode, String aMessage,
			Throwable exception) {
		return createStatus(IStatus.ERROR, aCode, aMessage, exception);
	}

	public static IStatus createStatus(int severity, String message, Throwable exception) {
		return new Status(severity, PLUGIN_ID, message, exception);
	}

	public static IStatus createStatus(int severity, String message) {
		return createStatus(severity, message, null);
	}

	public static void logError(Throwable exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( createStatus(IStatus.ERROR, exception.getMessage(), exception));
	}

	public static void logError(CoreException exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( exception.getStatus() );
	}

	public static void logError(String message, Throwable exception) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( createStatus(IStatus.ERROR, message, exception));
	}

	public static void logError(String message, CoreException exception) {
		MultiStatus status = new MultiStatus(PLUGIN_ID,IStatus.ERROR,new IStatus[]{exception.getStatus()},message,exception);
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log( status );
	}

	public static void logWarning(String message) {
		Platform.getLog(Platform.getBundle(PLUGIN_ID)).log(createStatus(IStatus.WARNING, message));
	}


}
