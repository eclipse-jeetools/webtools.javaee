/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntryImpl;

import org.eclipse.jem.util.logger.proxy.Logger;


public class ClasspathUtil {

	public static void processManifest(String inPath, List rtClasspath, Set processedPaths) {
		ArrayList paths = getManifestPaths(inPath);
		for (int i = 0; i < paths.size(); i++) {
			RuntimeClasspathEntry entry = (RuntimeClasspathEntry) paths.get(i);
			if (!processedPaths.contains(entry)) {
				processedPaths.add(entry);
				rtClasspath.add(entry);
				processManifest(entry.getAbsolutePath(), rtClasspath, processedPaths);
			}
		}
	}

	protected static RuntimeClasspathEntry createRuntimeClasspathEntry(String absolutePath, String manifestValue) {
		RuntimeClasspathEntry entry = new RuntimeClasspathEntryImpl();
		entry.setAbsolutePath(absolutePath);
		entry.setManifestValue(manifestValue);
		return entry;
	}

	protected static ArrayList getManifestPaths(String path) {
		ArrayList manifestPaths = new ArrayList();
		File f = new File(path);
		if (f.exists()) {
			if (f.isDirectory()) {
				File manifestFile = new File(path + File.separator + "META-INF" + File.separator + "MANIFEST.MF"); //$NON-NLS-1$ //$NON-NLS-2$
				if (manifestFile.exists()) {
					FileInputStream inputStream = null;
					try {
						inputStream = new FileInputStream(manifestFile);
						Manifest manifest = new Manifest(inputStream);
						manifestPaths = getManifestClassPaths(manifest, f.getParent());
					} catch (IOException e) {
						//Ignore
					} finally {
						if (null != inputStream) {
							try {
								inputStream.close();
							} catch (IOException ex) {
								Logger.getLogger().logError(ex);
							}
						}
					}
				}
			} else {
				JarFile jarFile = null;
				try {
					jarFile = new JarFile(f);
					Manifest manifest = jarFile.getManifest();
					if (manifest != null)
						manifestPaths = getManifestClassPaths(manifest, f.getParent());
				} catch (IOException e) {
					//Ignore
				} finally {
					if (jarFile != null)
						try {
							jarFile.close();
						} catch (IOException ex) {
							//Ignore
						}
				}
			}
		}
		return manifestPaths;
	}

	protected static ArrayList getManifestClassPaths(Manifest manifest, String archivePath) {
		ArrayList pathList = new ArrayList();
		Attributes main = manifest.getMainAttributes();
		String classPath = main.getValue(Attributes.Name.CLASS_PATH);
		if (classPath != null) {
			StringTokenizer st = new StringTokenizer(classPath, " "); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				String path = archivePath + File.separator + token;
				path = normalizePath(path);
				if (new File(path).exists()) {
					pathList.add(createRuntimeClasspathEntry(path, token));
				}
			}
		}
		return pathList;
	}

	public static String normalizePath(String path) {
		File f = new File(path);
		try {
			f = f.getCanonicalFile();
		} catch (Exception ex) {
			//Ignore
		}
		return f.getPath();
	}
}
