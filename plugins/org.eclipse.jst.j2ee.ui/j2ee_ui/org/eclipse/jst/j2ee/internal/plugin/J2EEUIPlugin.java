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
package org.eclipse.jst.j2ee.internal.plugin;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

import org.eclipse.core.internal.boot.PlatformURLConnection;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.internal.wizard.ImportUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;


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
			} catch (IOException e) {
				com.ibm.wtp.common.logger.proxy.Logger.getLogger().logWarning(J2EEPluginResourceHandler.getString("Install_Location_Error_", new Object[]{url}) + e); //$NON-NLS-1$
			}
		}
		return location;
	}

	public static URL getInstallURL() {
		return getDefault().getBundle().getEntry("/");
	}

	public static String getArchiveDefaultProjectName(Archive anArchive) {
		if (anArchive == null)
			return null;
		int type = getArchiveType(anArchive);
		return getTypeDefaultProjectName(anArchive.getURI(), type);
	}

	public static String getArchiveDefaultUtilProjectName(Archive anArchive) {
		if (anArchive == null)
			return null;
		int type = getArchiveType(anArchive);
		return getTypeDefaultUtilProjectName(anArchive.getName(), type);
	}

	public static String getArchiveDefaultProjectName(Archive anArchive, HashSet moduleNames) {
		if (anArchive == null)
			return null;
		int type = getArchiveType(anArchive);
		return getTypeDefaultProjectName(anArchive.getURI(), type, moduleNames);
	}

	public static int getArchiveType(Archive anArchive) {
		int type = ImportUtil.UNKNOWN;
		try {
			try {
				if (anArchive.isEJBJarFile())
					type = ImportUtil.EJBJARFILE;
				else if (anArchive.isWARFile())
					type = ImportUtil.WARFILE;
				else if (anArchive.isApplicationClientFile())
					type = ImportUtil.CLIENTJARFILE;
				else if (anArchive.isRARFile())
					type = ImportUtil.RARFILE;
				else if (anArchive.isEARFile())
					type = ImportUtil.EARFILE;
			} catch (Exception e) {
				//Ignore
			}
		} finally {
			if (anArchive != null)
				anArchive.close();
		}
		return type;
	}

	public static String getTypeDefaultProjectName(String text, int type) {
		IPath path = new Path(text);
		text = path.makeRelative().removeFileExtension().lastSegment();

		boolean exists = false;
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(text);
		if (project != null && project.exists())
			exists = true;
		if (exists && text.toLowerCase().indexOf(ImportUtil.SUFFIXES[type].toLowerCase()) == -1)
			text = text + ImportUtil.SUFFIXES[type];
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int j = 1; j < 10; j++) {
			boolean found = false;
			String iteratedProjectName = text + ((j == 1) ? "" : Integer.toString(j)); //$NON-NLS-1$
			for (int i = 0; !found && (i < projects.length); i++) {
				if ((projects[i]).getName().equalsIgnoreCase(iteratedProjectName)) {
					found = true;
				}
			}
			if (!found)
				return iteratedProjectName;
		}
		return text;
	}

	private static String getTypeDefaultProjectName(String text, int type, HashSet moduleNames) {
		IPath path = new Path(text);
		text = path.makeRelative().removeFileExtension().lastSegment();

		boolean isValidName = moduleNames.add(text);
		boolean exists = false;
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(text);
		if (project != null && project.exists())
			exists = true;
		if (!isValidName || (exists && text.toLowerCase().indexOf(ImportUtil.SUFFIXES[type].toLowerCase()) == -1))
			text = text + ImportUtil.SUFFIXES[type];
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int j = 1; j < 10; j++) {
			boolean found = false;
			String iteratedProjectName = text + ((j == 1) ? "" : Integer.toString(j)); //$NON-NLS-1$
			for (int i = 0; !found && (i < projects.length); i++) {
				if ((projects[i]).getName().equalsIgnoreCase(iteratedProjectName)) {
					found = true;
				}
			}
			if (!found)
				return iteratedProjectName;
		}
		return text;
	}

	// the following two methods are used by web editor

	private static String getTypeDefaultUtilProjectName(String text, int type) {
		text = text.substring(text.lastIndexOf(java.io.File.separator) + 1);
		int i = text.lastIndexOf('.');
		if (i > 0)
			text = text.substring(0, i);
		if (text.toLowerCase().indexOf(ImportUtil.SUFFIXES[type].toLowerCase()) == -1)
			text = text + ImportUtil.SUFFIXES[type];
		IProject[] projects = getWorkspace().getRoot().getProjects();
		for (int j = 0; j < 10; j++) {
			boolean found = false;
			String iteratedProjectName = text + ((j == 0) ? "" : Integer.toString(j)); //$NON-NLS-1$
			for (i = 0; !found && (i < projects.length); i++) {
				if ((projects[i]).getName().equalsIgnoreCase(iteratedProjectName)) {
					found = true;
				}
			}
			if (!found)
				return iteratedProjectName;
		}
		return text;
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



	}
