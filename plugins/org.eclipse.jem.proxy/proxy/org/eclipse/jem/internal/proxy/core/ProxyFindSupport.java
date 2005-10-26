/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProxyFindSupport.java,v $
 *  $Revision: 1.2 $  $Date: 2005/10/26 22:14:01 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
 

/**
 * Utility to help finding files. It is used to find them within plugins of the current platform or to find them in the PDE target platform.
 * Also if in the current platform and the current platform is in development mode it will find the bin directories for the jars instead since
 * there are no jars in that case.
 * It can also extract non-local files (such as files within a jar) and make a cache of them so that are a local file and not extracted everytime
 * they are needed.
 *
 * TODO If we ever get back to continuing development we should add to this PDE Target support. And test the code here 
 * since it hasn't been tested yet. Also note that in configuration contributor,
 * when we add the library path we expect it to be a folder. This would be a problem with jarred plugins because the directory containing the
 * libraries may be the root of the plugin and localizing a directory would extract and cache the entire plugin. Need to change to figure out
 * how to only extract the dll/so needed instead. Also, none of these handle the case where the bundle is actually non-local (e.g. "http:/").
 * @since 1.2.0
 */
public class ProxyFindSupport {

	
	/**
	 * Find the filename relative to the platform bundle. It will search in the bundle and in all fragments for the file name.
	 * It will return a URL for each found file.
	 * <p>
	 * This is useful for NLS purposes. In NLS situations the main bundle would contain the main non-library jar with the default properties files.
	 * Then a fragment for each set of languages will be installed. Within each fragment will be a jar with the same filepath, and it will
	 * contain the properties files for that language. This would be done only for plugin non-library jars. 
	 * The NLS for library jars of a plugin (i.e. those jars that are the <runtime> jars for the plugin) are usually library jars themselves in
	 * the fragment, and they usually have a different name than the main library jar. So this should not be used for library jars.
	 * <p>
	 * If in development mode, then if the jar is not found it will look in the plugin project's build.properties to find the output directory
	 * of the jar. If not in the build.properties (meaning it isn't being built by PDE), it will look in the proxy.jars file (if there is one).
	 * This file is used for jars that are built in a separate project from the plugin but will be found in the plugin at execution.
	 * 
	 * @param bundle bundle and its fragments to look in.
	 * @param filenameWithinBundle path to filename within the bundle and fragments. <b>This must be a file, not a directory.</b> 
	 *   For finding the bundle itself (only when the bundle is packaged as a jar), use <@link #platformFind(Bundle)>. This does not use
	 *   the platform shortcuts like "$os$". It finds the files exactly as specified.
	 * @param asLocal <code>true</code> if the urls need to point to local filesystem files. This may cause caching of files locally. This
	 * could happen if the file is within a plugin that is jarred. <code>false</code> if the url can be a non-local filesystem url. 
	 * @return array of URL's for found files. 
	 * 
	 * @since 1.2.0
	 */
	public static URL[] platformFindAll(Bundle bundle, IPath filenameWithinBundle, boolean asLocal) {
		Bundle[] fragments = Platform.getFragments(bundle);
		ArrayList urls = new ArrayList((fragments == null ? 0 : fragments.length) + 1);
		String filename = filenameWithinBundle.toString();
		URL url = internalFindFromBundleOnly(bundle, filename, asLocal);
		if (url != null)
			urls.add(url);
		if (fragments != null) {
			for (int i = 0; i < fragments.length; i++) {
				Bundle fragment = fragments[i];
				url = internalFindFromBundleOnly(fragment, filename, asLocal);
				if (url != null)
					urls.add(url);
			}
		}
		return (URL[]) urls.toArray(new URL[urls.size()]);
	}
	
	/**
	 * @param bundle
	 * @param filenameWithinBundle
	 * @param asLocal
	 * @return
	 * 
	 * @since 1.2.0
	 */
	private static URL internalFindFromBundleOnly(Bundle bundle, String filenameWithinBundle, boolean asLocal) {
		try {
			URL pvm = bundle.getEntry(filenameWithinBundle);
			if (pvm != null)
				return asLocal ? Platform.asLocalURL(pvm) : pvm;
		} catch (IOException e) {
		}
		if (ProxyPlugin.getPlugin().isDevMode()) {
			return findDev(bundle, filenameWithinBundle, asLocal);
		} else
			return null;
	}

	/**
	 * Find the filename relative to the platform bundle. It will search in the bundle and in all fragments for the file name. It will return
	 * URL for the file if found.
	 * <p>
	 * If in development mode, then if the jar is not found it will look in the plugin project's build.properties to find the output directory
	 * of the jar. If not in the build.properties (meaning it isn't being built by PDE), it will look in the proxy.jars file (if there is one).
	 * This file is used for jars that are built in a separate project from the plugin but will be found in the plugin at execution.
	 * 
	 * @param bundle bundle and its fragments to look in.
	 * @param filenameWithinBundle path to filename within the bundle and fragments. <b>This must be a file, not a directory.</b> 
	 *   For finding the bundle itself (only when the bundle is packaged as a jar), use <@link #platformFind(Bundle)>.
	 * @param asLocal <code>true</code> if the urls need to point to local filesystem files. This may cause caching of files locally. This
	 * could happen if the file is within a plugin that is jarred. <code>false</code> if the url can be a non-local filesystem url.  
	 * @return the found url or <code>null</code> if not found.
	 * 
	 * @since 1.2.0
	 */
	public static URL platformFind(Bundle bundle, IPath filenameWithinBundle, boolean asLocal) {
		try {
			URL pvm = Platform.find(bundle, filenameWithinBundle);
			if (pvm != null)
				return asLocal ? Platform.asLocalURL(pvm) : pvm;
		} catch (IOException e) {
		}
		if (ProxyPlugin.getPlugin().isDevMode()) {
			return findDev(bundle, filenameWithinBundle.toString(), asLocal);
		} else
			return null;
		
	}
	
	private static final String BUILDPROPERTIES = "build.properties";	//$NON-NLS-1$
	private static final String PROXYJARS = "proxy.jars";	//$NON-NLS-1$
	
	private static URL findDev(Bundle bundle, String filenameWithinBundle, boolean asLocal) {
		// Got this far and in dev mode means it wasn't found, so we'll try for development style.
		// It is assumed that in dev mode, we are running with the IDE as local and any 
		// build outputs will be local so local file protocol will be returned
		// from Platform.resolve(). We won't be running in dev mode with our entireplugin being in a jar,
		// or on a separate system.
		try {
			URL bp = bundle.getEntry(BUILDPROPERTIES);
			if (bp != null) {
				InputStream ios = null;
				try {
					ios = bp.openStream();
					Properties props = new Properties();
					props.load(ios);
					String pathString = props.getProperty("output."+filenameWithinBundle.toString());
					if (pathString != null) {
						URL pvm = bundle.getEntry(pathString);
						if (pvm != null)
							return asLocal ? Platform.asLocalURL(pvm) : pvm;
					}
				} finally {
					if (ios != null)
						ios.close();
				}
			}
		} catch (IOException e) {
		}

		try {
			URL pvm = bundle.getEntry(PROXYJARS);
			if (pvm != null) {
				InputStream ios = null;
				try {
					ios = pvm.openStream();
					Properties props = new Properties();
					props.load(ios);
					String pathString = props.getProperty(filenameWithinBundle.toString());
					if (pathString != null) {
						URL url = Platform.resolve(bundle.getEntry("/"));	// It is assumed that if in debug mode, then this plugin is an imported plugin within the developement workspace. //$NON-NLS-1$
						if (url.getProtocol().equals("file")) { //$NON-NLS-1$
							File file = new File(url.getFile()).getParentFile();	// This gets us to workspace root of development workspace.
							file = new File(file, pathString);
							return file.toURL();
						}
					}
				} finally {
					if (ios != null)
						ios.close();
				}
			}
		} catch (IOException e) {
		}
		return null;
	}

	
	/**
	 * Find the bundle and all active fragments libraries. This should normally be used only when the bundle is packaged as a jar and not as a directory. It will
	 * return the urls to the jars of the bundle and fragments. If in development mode it will find the bundle's "." output directory.
	 * @param bundle
	 * @return array of URL's for the jarred bundle and fragments. If the bundle/fragments are not packaged as jars, then the array will be empty.
	 * 
	 * @since 1.2.0
	 */
	public static URL[] platformFind(Bundle bundle) {
		return null;
	}
	

}
