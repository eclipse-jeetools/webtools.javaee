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


import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;


/**
 * Class loader which loads a given set of classes stored in byte arrays. (Assumption: System
 * classes and those in the set are the only classes needed to resolve each one)
 */

public class ArchiveFileDynamicClassLoader extends ClassLoader {
	protected Archive archive = null;
	protected ClassLoader extraClassLoader;
	protected boolean inEARFile;

	public ArchiveFileDynamicClassLoader(Archive anArchive, ClassLoader parentCl, ClassLoader extraCl) {
		super(parentCl);
		setArchive(anArchive);
		setExtraClassLoader(extraCl);
		inEARFile = anArchive.getContainer() != null && anArchive.getContainer().isEARFile();
	}

	/**
	 * Loads a specified class. This gets called only after the parent class loader has had it's
	 * chance, based on the Java2 delegation model
	 */
	protected Class findClass(String name) throws ClassNotFoundException {

		Class result;
		// Load class bytes from current set of class byte[]'s
		byte[] bytes = getClassBytesFor(name);

		if (bytes != null) {
			result = defineClass(name, bytes, 0, bytes.length);
			if (result == null) {
				throw new ClassNotFoundException(name);
			} // endif
		} else {
			throw new ClassNotFoundException(name);
		} // endif
		return result;
	}

	/**
	 * Insert the method's description here. Creation date: (12/17/00 9:59:57 PM)
	 * 
	 * @return com.ibm.etools.commonarchive.Archive
	 */
	public Archive getArchive() {
		return archive;
	}

	protected byte[] getClassBytesFor(String className) {

		if (className == null)
			return null;
		// Change the class name to a jar entry name
		String jarEntryName = ArchiveUtil.classNameToUri(className);

		try {
			InputStream in = getArchive().getInputStream(jarEntryName);
			return ArchiveUtil.inputStreamToBytes(in);
		} catch (java.io.FileNotFoundException ex) {
			return null;
		} catch (java.io.IOException ex) {
			throw new ArchiveRuntimeException(CommonArchiveResourceHandler.getString("io_ex_loading_EXC_", (new Object[]{className})), ex); //$NON-NLS-1$ = "An IO exception occurred loading "
		}
	}

	protected EARFile getEARFile() {
		return (EARFile) getArchive().getContainer();
	}

	/**
	 * Insert the method's description here. Creation date: (11/21/00 6:58:05 PM)
	 * 
	 * @return java.lang.ClassLoader
	 */
	public java.lang.ClassLoader getExtraClassLoader() {
		return extraClassLoader;
	}

	/**
	 * Used for dynamic class loading in dependent jars in ears; the set is used to terminate a
	 * cycle if one exists; the cycle is invalid, but you never know what people might try...
	 */
	protected synchronized Class loadClass(String name, Set visitedArchives) throws ClassNotFoundException {
		if (visitedArchives.contains(getArchive()))
			throw new ClassNotFoundException(name);
		visitedArchives.add(getArchive());
		try {
			return super.loadClass(name, false);
		} catch (ClassNotFoundException ex) {
			return loadClassInDependentJarInEAR(name, visitedArchives);
		}
	}

	protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
		try {
			return super.loadClass(name, resolve);
		} catch (ClassNotFoundException ex) {
			Class c = loadClassInDependentJar(name);
			if (c != null && resolve)
				resolveClass(c);
			return c;
		}
	}

	protected Class loadClassInDependentJar(String name) throws ClassNotFoundException {

		if (inEARFile) {
			return loadClassInDependentJarInEAR(name);
		} else if (getExtraClassLoader() != null) {
			return getExtraClassLoader().loadClass(name);
		}
		throw new ClassNotFoundException(name);
	}

	protected Class loadClassInDependentJarInEAR(String name) throws ClassNotFoundException {

		Set visitedArchives = new HashSet(5);
		visitedArchives.add(getArchive());
		return loadClassInDependentJarInEAR(name, visitedArchives);
	}

	protected Class loadClassInDependentJarInEAR(String name, Set visitedArchives) throws ClassNotFoundException {

		String[] classpath = archive.getManifest().getClassPathTokenized();
		for (int i = 0; i < classpath.length; i++) {
			try {
				String uri = ArchiveUtil.deriveEARRelativeURI(classpath[i], archive);
				if (uri == null)
					continue;
				File file = getEARFile().getFile(uri);
				if (file.isArchive()) {
					Archive dep = (Archive) file;
					try {
						return ((ArchiveFileDynamicClassLoader) dep.getArchiveClassLoader()).loadClass(name, visitedArchives);
					} catch (ClassNotFoundException noDice) {
						continue;
					}
				}
			} catch (java.io.FileNotFoundException depJarNotInEAR) {
				//Ignore
			}
		}
		throw new ClassNotFoundException(name);
	}

	/**
	 * Insert the method's description here. Creation date: (12/17/00 9:59:57 PM)
	 * 
	 * @param newArchive
	 *            com.ibm.etools.commonarchive.Archive
	 */
	public void setArchive(Archive newArchive) {
		archive = newArchive;
	}

	/**
	 * Insert the method's description here. Creation date: (11/21/00 6:58:05 PM)
	 * 
	 * @param newExtraClassLoader
	 *            java.lang.ClassLoader
	 */
	public void setExtraClassLoader(java.lang.ClassLoader newExtraClassLoader) {
		extraClassLoader = newExtraClassLoader;
	}
}
