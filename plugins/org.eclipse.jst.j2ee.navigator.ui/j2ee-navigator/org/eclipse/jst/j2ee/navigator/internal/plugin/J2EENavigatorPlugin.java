/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal.plugin;

import java.net.URL;
import java.util.Collections;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jst.j2ee.navigator.internal.J2EEEMFAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class J2EENavigatorPlugin extends AbstractUIPlugin {
	//The shared instance.
	private static J2EENavigatorPlugin plugin;
	public static final String PLUGIN_ID = "org.eclipse.jst.j2ee.navigator.ui"; //$NON-NLS-1$
	private static final String ICONS = "icons/"; //$NON-NLS-1$
	
	private ImageRegistry images;


	/**
	 * The constructor.
	 */
	public J2EENavigatorPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static J2EENavigatorPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		WorkbenchResourceHelper.initializeFileAdapterFactory();
		IAdapterManager manager = Platform.getAdapterManager();
		manager.registerAdapters(new J2EEEMFAdapterFactory(), EObject.class);
		images = new ImageRegistry(Display.getDefault());
	}
	
	public ImageDescriptor getImageDescriptor(String relativePath) {
		ImageDescriptor descriptor = images.getDescriptor(relativePath);
		if(descriptor == null) {
			synchronized (this) {
				descriptor = images.getDescriptor(relativePath);
				if(descriptor == null) {
					URL imageUrl = FileLocator.find(getBundle(), new Path(ICONS+relativePath), Collections.EMPTY_MAP);
					descriptor = ImageDescriptor.createFromURL(imageUrl);
				} 
			}
		}
		return descriptor; 
	}
	
	
	public Image getImage(ImageDescriptor descriptor) { 

		Image image = images.get(descriptor.toString());		
		if(image == null) {
			synchronized (this) {
				image = images.get(descriptor.toString());
				if(image == null) { 
					images.put(descriptor.toString(), (image = descriptor.createImage(true)));
				} 
			}
		}
		return image; 
	}
	
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		images.dispose();
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
}
