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
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.jar.Attributes;

import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;


/**
 * Helper class for manifest files
 */
public class ArchiveManifestImpl extends java.util.jar.Manifest implements org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest {
	/**
	 * ArchiveManifest constructor comment.
	 */
	public ArchiveManifestImpl() {
		super();
	}

	/**
	 * ArchiveManifest constructor comment.
	 * 
	 * @param is
	 *            java.io.InputStream
	 * @throws java.io.IOException
	 *             The exception description.
	 */
	public ArchiveManifestImpl(java.io.InputStream is) throws java.io.IOException {
		super(is);
	}

	/**
	 * ArchiveManifest constructor comment.
	 * 
	 * @param man
	 *            java.util.jar.Manifest
	 */
	public ArchiveManifestImpl(java.util.jar.Manifest man) {
		super(man);
	}

	/**
	 * Creates a new manifest entry (attributes) for the given name
	 */
	public void addEntry(String entryName) {
		Attributes attr = new Attributes();
		addEntry(entryName, attr);
	}

	public void addEntry(String entryName, Attributes attr) {
		getEntries().put(entryName, attr);
	}

	/**
	 * Adds the key/value pair to the attributes for the given entry name; if the entry does not
	 * exist, creates a new attributes
	 */
	public void addEntryAttribute(String entryName, String key, String value) {
		Attributes attr = getAttributes(entryName);
		if (attr == null)
			addEntry(entryName);
		attr = getAttributes(entryName);
		attr.putValue(key, value);
	}

	public void addVersionIfNecessary() {
		//This is a hack because of the fact that the manifest does not serialize correctly if
		//The version is not set. In addition to saves, the serialization is used for copy
		if (getManifestVersion() == null || getManifestVersion().equals(""))//$NON-NLS-1$
			setManifestVersion("1.0");//$NON-NLS-1$
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveManifest
	 */
	public void appendClassPath(java.lang.String extension) {
		String classPath = getClassPath();
		if (classPath != null)
			setClassPath(classPath + " " + extension);//$NON-NLS-1$
		else
			setClassPath(extension);
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveManifest
	 */
	public java.lang.String getClassPath() {
		return ArchiveUtil.getValueIgnoreKeyCase(Attributes.Name.CLASS_PATH.toString(), getMainAttributes());
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveManifest
	 */
	public java.lang.String[] getClassPathTokenized() {
		String classPath = getClassPath();
		if (classPath == null)
			return new String[0];
		return org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil.getTokens(classPath);
	}

	public String getEntryAttribute(String entryName, String key) {
		Attributes attr = getAttributes(entryName);
		if (attr == null)
			return null;
		return attr.getValue(key);
	}

	public String getMainClass() {
		return ArchiveUtil.getValueIgnoreKeyCase(Attributes.Name.MAIN_CLASS.toString(), getMainAttributes());
	}

	public String getManifestVersion() {
		return getMainAttributes().getValue(Attributes.Name.MANIFEST_VERSION);
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveManifest
	 */
	public void mergeClassPath(java.lang.String[] classPathEntries) {
		StringBuffer sb = new StringBuffer();
		java.util.List existing = java.util.Arrays.asList(getClassPathTokenized());
		String cp = getClassPath();
		if (cp != null)
			sb.append(cp);
		boolean empty = cp == null || "".equals(cp); //$NON-NLS-1$
		for (int i = 0; i < classPathEntries.length; i++) {
			if (!existing.contains(classPathEntries[i])) {
				if (!empty)
					sb.append(" "); //$NON-NLS-1$
				else
					empty = false;
				sb.append(classPathEntries[i]);
			}
		}
		setClassPath(sb.toString());
	}

	public void removeEntry(String entryName) {
		getEntries().remove(entryName);
	}

	public void removeEntryAttribute(String entryName, Object key) {
		Attributes attr = getAttributes(entryName);
		if (attr != null)
			attr.remove(key);
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveManifest
	 */
	public void setClassPath(java.lang.String aSpaceDelimitedPath) {
		Attributes attributes = getMainAttributes();
		if (aSpaceDelimitedPath == null)
			attributes.remove(Attributes.Name.CLASS_PATH);
		else
			attributes.putValue(Attributes.Name.CLASS_PATH.toString(), aSpaceDelimitedPath);
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveManifest
	 */
	public void setMainClass(java.lang.String className) {
		Attributes attributes = getMainAttributes();
		if (className == null)
			attributes.remove(Attributes.Name.MAIN_CLASS);
		else
			attributes.putValue(Attributes.Name.MAIN_CLASS.toString(), className);
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveManifest
	 */
	public void setManifestVersion(java.lang.String version) {
		Attributes attributes = getMainAttributes();
		attributes.putValue(Attributes.Name.MANIFEST_VERSION.toString(), version);
	}

	/**
	 * Writes the Manifest to the specified OutputStream, splitting each classpath entry on a line
	 * by itself.
	 * 
	 * @param out
	 *            the output stream
	 * @exception IOException
	 *                if an I/O error has occurred
	 */
	public void writeSplittingClasspath(OutputStream out) throws IOException {
		DataOutputStream dos = new DataOutputStream(out);
		// Write out the main attributes for the manifest
		writeMainSplittingClasspath(getMainAttributes(), dos);
		// Now write out the pre-entry attributes
		Iterator it = getEntries().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			StringBuffer buffer = new StringBuffer("Name: "); //$NON-NLS-1$
			buffer.append((String) e.getKey());
			buffer.append("\r\n"); //$NON-NLS-1$
			localMake72Safe(buffer);
			dos.writeBytes(buffer.toString());
			write((Attributes) e.getValue(), dos);
		}
		dos.flush();
	}

	/*
	 * Writes the current attributes to the specified data output stream. XXX Need to handle UTF8
	 * values and break up lines longer than 72 bytes
	 * 
	 * @see Attributes#write
	 */
	protected void write(Attributes attributes, DataOutputStream os) throws IOException {
		Iterator it = attributes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			StringBuffer buffer = new StringBuffer(((Attributes.Name) e.getKey()).toString());
			buffer.append(": "); //$NON-NLS-1$
			buffer.append((String) e.getValue());
			buffer.append("\r\n"); //$NON-NLS-1$
			localMake72Safe(buffer);
			os.writeBytes(buffer.toString());
		}
		os.writeBytes("\r\n"); //$NON-NLS-1$
	}

	/*
	 * Writes the current attributes to the specified data output stream, make sure to write out the
	 * MANIFEST_VERSION or SIGNATURE_VERSION attributes first.
	 * 
	 * @see Attributes#writeMain
	 */
	protected void writeMainSplittingClasspath(Attributes attributes, DataOutputStream out) throws IOException {
		// write out the *-Version header first, if it exists
		String vername = Attributes.Name.MANIFEST_VERSION.toString();
		String version = attributes.getValue(vername);
		if (version == null) {
			vername = Attributes.Name.SIGNATURE_VERSION.toString();
			version = attributes.getValue(vername);
		}

		if (version != null) {
			out.writeBytes(vername + ": " + version + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// write out all attributes except for the version
		// we wrote out earlier
		Iterator it = attributes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			String name = ((Attributes.Name) e.getKey()).toString();
			if ((version != null) && !(name.equalsIgnoreCase(vername))) {
				if (name.equalsIgnoreCase(Attributes.Name.CLASS_PATH.toString())) {
					writeSplit(out, name, (String) e.getValue());
					continue;
				}
				StringBuffer buffer = new StringBuffer(name);
				buffer.append(": "); //$NON-NLS-1$
				buffer.append((String) e.getValue());
				buffer.append("\r\n"); //$NON-NLS-1$
				localMake72Safe(buffer);
				out.writeBytes(buffer.toString());
			}
		}
		out.writeBytes("\r\n"); //$NON-NLS-1$
	}

	protected void writeSplit(DataOutputStream out, String name, String value) throws IOException {
		StringTokenizer tok = new StringTokenizer(value);
		int inc = 0;
		while (tok.hasMoreTokens()) {
			StringBuffer buffer = null;
			if (inc == 0) {
				buffer = new StringBuffer(name);
				buffer.append(": "); //$NON-NLS-1$
			} else {
				buffer = new StringBuffer();
				buffer.append(' ');
			}
			buffer.append(tok.nextToken());
			if (tok.countTokens() > 0)
				buffer.append(" \r\n"); //$NON-NLS-1$
			else
				buffer.append("\r\n"); //$NON-NLS-1$
			localMake72Safe(buffer);
			out.writeBytes(buffer.toString());
			inc++;
		}
	}

	/**
	 * Adds line breaks to enforce a maximum 72 bytes per line.
	 */
	protected static void localMake72Safe(StringBuffer line) {
		int length = line.length();
		if (length > 72) {
			int index = 70;
			while (index - 1 < length) {
				line.insert(index, "\r\n "); //$NON-NLS-1$
				index += 72;
				length += 3;
			}
		}
		return;
	}


}
