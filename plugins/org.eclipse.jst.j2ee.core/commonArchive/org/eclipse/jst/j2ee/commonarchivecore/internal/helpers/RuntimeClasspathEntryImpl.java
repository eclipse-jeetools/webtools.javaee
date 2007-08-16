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


import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;

public class RuntimeClasspathEntryImpl implements RuntimeClasspathEntry {

	/** A single token from the Class-Path: attrbute */
	protected String manifestValue;
	/** The resolved absolute path of the entry */
	protected String absolutePath;
	/** valid only if this entry is a library in a WARFile, under WEB-INF/lib */
	protected WARFile warFile;

	protected Archive referencedArchive;

	/**
	 * Constructor for ManifestClasspathEntryImpl.
	 */
	public RuntimeClasspathEntryImpl() {
		super();
	}

	/**
	 * Gets the absolutePath.
	 * 
	 * @return Returns a String
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}

	/**
	 * Sets the absolutePath.
	 * 
	 * @param absolutePath
	 *            The absolutePath to set
	 */
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * Gets the manifestValue.
	 * 
	 * @return Returns a String
	 */
	public String getManifestValue() {
		return manifestValue;
	}

	/**
	 * Sets the manifestValue.
	 * 
	 * @param manifestValue
	 *            The manifestValue to set
	 */
	public void setManifestValue(String manifestValue) {
		this.manifestValue = manifestValue;
	}



	/**
	 * Gets the warFile.
	 * 
	 * @return Returns a WARFile
	 */
	public WARFile getWarFile() {
		return warFile;
	}

	/**
	 * Sets the warFile.
	 * 
	 * @param warFile
	 *            The warFile to set
	 */
	public void setWarFile(WARFile warFile) {
		this.warFile = warFile;
	}

	public String toString() {
		return getAbsolutePath();
	}

	public boolean equals(Object o) {
		if (o instanceof RuntimeClasspathEntry)
			return getAbsolutePath().equals(((RuntimeClasspathEntry) o).getAbsolutePath());
		return false;
	}

	public int hashCode() {
		return getAbsolutePath().hashCode();
	}


	/**
	 * @see RuntimeClasspathEntry#isWebLib()
	 */
	public boolean isWebLib() {
		return warFile != null;
	}

	/**
	 * Gets the referencedArchive.
	 * 
	 * @return Returns a Archive
	 */
	public Archive getReferencedArchive() {
		return referencedArchive;
	}

	/**
	 * Sets the referencedArchive.
	 * 
	 * @param referencedArchive
	 *            The referencedArchive to set
	 */
	public void setReferencedArchive(Archive referencedArchive) {
		this.referencedArchive = referencedArchive;
	}

}
