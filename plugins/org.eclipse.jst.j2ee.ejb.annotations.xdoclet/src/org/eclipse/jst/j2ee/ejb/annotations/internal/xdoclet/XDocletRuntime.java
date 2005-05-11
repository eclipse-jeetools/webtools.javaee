/*******************************************************************************
 * Copyright (c) 2002, 2003,2004,2005 Eteration Bilisim A.S.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci Dai
 * For more information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;

public class XDocletRuntime {

	// relative path to the libraries
	public final static String ID = "XDoxlet";

	public final static String LIBPATH = "lib";

	public final static String VERSIONS[] = { "1.2.1", "1.2.2", "1.2.3" };

	// 1.2.2
	private String version = "1.2.2";

	// location of the axis installation
	private String home = "";

	private String libs[][][] = {
			{
					{ "1.2.1" },
					{ "xdoclet-1.2.1.jar", "xjavadoc-1.0.3.jar",
							"xdoclet-xdoclet-module-1.2.1.jar",
							"xdoclet-ejb-module-1.2.1.jar",
							"commons-collections-2.0.jar",
							"commons-logging.jar" } },
			{
					{ "1.2.2" },
					{ "xdoclet-1.2.2.jar", "xjavadoc-1.1.jar",
							"xdoclet-xdoclet-module-1.2.2.jar",
							"xdoclet-ejb-module-1.2.2.jar",
							"commons-collections-2.0.jar",
							"commons-logging.jar" } },
			{
					{ "1.2.3" },
					{ "xdoclet-1.2.2.jar", "xjavadoc-1.1.jar",
							"xdoclet-xdoclet-module-1.2.3.jar",
							"xdoclet-ejb-module-1.2.3.jar",
							"commons-collections-2.0.jar",
							"commons-logging.jar" } }

	};

	/**
	 * @return Returns the home.
	 */
	public String getHome() {
		return home;
	}

	/**
	 * @param home
	 *            The home to set.
	 */
	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return Returns the libs.
	 */
	public String[] getLibs(String version) {
		for (int i = 0; i < libs.length; i++) {
			if (version.equals(libs[i][0][0]))
				return libs[i][1];

		}
		return null;
	}

	/**
	 * @param runtime
	 * @param id2
	 */
	public IClasspathEntry[] resolveClasspathContainer() {
		List list = new ArrayList();
		addLibraryEntries(list, new File(home + File.separator + LIBPATH),
				false);
		return resolveList(list);
	}

	protected static void addJarFiles(File dir, List list,
			boolean includeSubdirectories) {
		int depth = 0;
		if (includeSubdirectories)
			depth = 2;
		addJarFiles(dir, list, depth);
	}

	private static void addJarFiles(File dir, List list, int depth) {
		File[] files = dir.listFiles();
		if (files != null) {
			int size = files.length;
			for (int i = 0; i < size; i++) {
				if (files[i].isDirectory() && depth > 0) {
					addJarFiles(files[i], list, depth - 1);
				} else if (files[i].getAbsolutePath().endsWith(".jar")
						|| files[i].getAbsolutePath().endsWith(".zip")) {
					IPath path = new Path(files[i].getAbsolutePath());
					list.add(JavaCore.newLibraryEntry(path, null, null));
				}
			}
		}
	}

	protected static void addLibraryEntries(List list, File dir,
			boolean includeSubdirectories) {
		addJarFiles(dir, list, includeSubdirectories);
	}

	protected static IClasspathEntry[] resolveList(List list) {
		IClasspathEntry[] entries = new IClasspathEntry[list.size()];
		list.toArray(entries);
		return entries;
	}

	public static XDocletRuntime getPreferredRuntime() {
		XDocletRuntime axisRuntime = new XDocletRuntime();
		axisRuntime.setHome(XDocletPreferenceStore.getProperty("AXIS", "home"));
		axisRuntime.setVersion(XDocletPreferenceStore.getProperty("AXIS",
				"version"));
		return axisRuntime;
	}

	/**
	 * @return
	 */
	public URL getHomeURL() throws MalformedURLException {
		return (new File(getHome())).toURL();
	}

	public IStatus[] validate(String version) {
		ArrayList errors = new ArrayList();
		String[] vLibs = this.getLibs(version);
		if (vLibs == null)
			errors.add(new Status(IStatus.ERROR,
					XDocletAnnotationPlugin.PLUGINID, 0,
					"Could not find library for version: " + version, null));
		else {
			for (int i = 0; i < vLibs.length; i++) {
				String jar = vLibs[i];
				File libFile = new File(getHome() + File.separator + LIBPATH
						+ File.separator + jar);

				// This is fo people who change the location of the files
				// from the original distribution. It is unncessary but
				// makes people happy!
				File libFile2 = new File(getHome() + File.separator + jar);

				if (!libFile.exists() && !libFile2.exists())
					errors.add(new Status(IStatus.ERROR,
							XDocletAnnotationPlugin.PLUGINID, 0,
							"Could not find library: " + jar, null));
			}
		}
		return (IStatus[]) errors.toArray(new IStatus[errors.size()]);
	}

	public boolean isValid(String version) {
		return validate(version).length == 0;
	}
}
