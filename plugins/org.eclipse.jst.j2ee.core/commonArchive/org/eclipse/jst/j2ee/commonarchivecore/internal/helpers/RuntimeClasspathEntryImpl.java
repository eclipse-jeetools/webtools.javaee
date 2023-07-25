/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	@Override
	public String getAbsolutePath() {
		return absolutePath;
	}

	/**
	 * Sets the absolutePath.
	 * 
	 * @param absolutePath
	 *            The absolutePath to set
	 */
	@Override
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * Gets the manifestValue.
	 * 
	 * @return Returns a String
	 */
	@Override
	public String getManifestValue() {
		return manifestValue;
	}

	/**
	 * Sets the manifestValue.
	 * 
	 * @param manifestValue
	 *            The manifestValue to set
	 */
	@Override
	public void setManifestValue(String manifestValue) {
		this.manifestValue = manifestValue;
	}



	/**
	 * Gets the warFile.
	 * 
	 * @return Returns a WARFile
	 */
	@Override
	public WARFile getWarFile() {
		return warFile;
	}

	/**
	 * Sets the warFile.
	 * 
	 * @param warFile
	 *            The warFile to set
	 */
	@Override
	public void setWarFile(WARFile warFile) {
		this.warFile = warFile;
	}

	@Override
	public String toString() {
		return getAbsolutePath();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof RuntimeClasspathEntry)
			return getAbsolutePath().equals(((RuntimeClasspathEntry) o).getAbsolutePath());
		return false;
	}

	@Override
	public int hashCode() {
		return getAbsolutePath().hashCode();
	}


	/**
	 * @see RuntimeClasspathEntry#isWebLib()
	 */
	@Override
	public boolean isWebLib() {
		return warFile != null;
	}

	/**
	 * Gets the referencedArchive.
	 * 
	 * @return Returns a Archive
	 */
	@Override
	public Archive getReferencedArchive() {
		return referencedArchive;
	}

	/**
	 * Sets the referencedArchive.
	 * 
	 * @param referencedArchive
	 *            The referencedArchive to set
	 */
	@Override
	public void setReferencedArchive(Archive referencedArchive) {
		this.referencedArchive = referencedArchive;
	}

}
