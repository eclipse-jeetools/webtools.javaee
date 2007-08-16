/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * Contains the API of
 * 
 * @link java.util.jar.Manifest, along with added helpers
 */
public interface ArchiveManifest {
	/**
	 * Creates a new manifest entry (attributes) for the given name
	 */
	public void addEntry(String entryName);

	public void addEntry(String entryName, Attributes attr);

	/**
	 * Adds the key/value pair to the attributes for the given entry name; if the entry does not
	 * exist, creates a new attributes
	 */
	public void addEntryAttribute(String entryName, String key, String value);

	/**
	 * Defaults the version to "1.0" if not already set
	 */
	public void addVersionIfNecessary();

	public void appendClassPath(String extension);

	/**
	 * @see java.util.jar.Manifest#clear
	 */
	public void clear();

	/**
	 * @see java.util.jar.Manifest#getAttributes
	 */
	public Attributes getAttributes(String name);

	public String getClassPath();

	public String[] getClassPathTokenized();

	/**
	 * @see java.util.jar.Manifest#getEntries
	 */
	public Map getEntries();

	public String getEntryAttribute(String entryName, String key);

	/**
	 * @see java.util.jar.Manifest#getAttributes
	 */
	public Attributes getMainAttributes();

	public String getMainClass();

	/**
	 * Return the value iff the entry exists in a case-sensitive manner; manifest version is
	 * required for the manifest to save correctly
	 */
	public String getManifestVersion();
	
	/**
	 * Return the value iff the entry exists in a case-sensitive manner; implementation version is
	 * optional in  the manifest
	 * */
	public String getImplementationVersion();

	/**
	 * Add all the entries not already contained in the class path of this manifest
	 */
	public void mergeClassPath(String[] classPathEntries);

	/**
	 * @see java.util.jar.Manifest#read
	 */
	public void read(InputStream is) throws IOException;

	public void removeEntry(String entryName);

	public void removeEntryAttribute(String entryName, Object key);

	public void setClassPath(String aSpaceDelimitedPath);

	public void setMainClass(String className);

	public void setManifestVersion(java.lang.String version);
	
	public void setImplemenationVersion(java.lang.String version);

	/**
	 * @see java.util.jar.Manifest#write
	 */
	public void write(OutputStream out) throws IOException;

	/**
	 * Writes the Manifest to the specified OutputStream, splitting each classpath entry on a line
	 * by itself.
	 * 
	 * @param out
	 *            the output stream
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void writeSplittingClasspath(OutputStream out) throws IOException;
}
