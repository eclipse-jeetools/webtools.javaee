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

public interface RuntimeClasspathEntry {


	/**
	 * The resolved absolute path of the entry
	 */
	String getAbsolutePath();

	void setAbsolutePath(String absolutePath);

	/**
	 * A single token from the Class-Path: attrbute
	 */
	String getManifestValue();

	void setManifestValue(String manifestValue);

	/**
	 * valid only if this entry is a library in a WARFile, under WEB-INF/lib
	 */
	WARFile getWarFile();

	void setWarFile(WARFile aWarFile);

	/**
	 * true if this entry is a library in a WAR file
	 */
	boolean isWebLib();

	/**
	 * The resolved archive inside an EAR that this entry points to; Note that this is NOT the
	 * Archive that has the entry in the manifest, but rather the referenced archive/
	 */
	Archive getReferencedArchive();

	void setReferencedArchive(Archive anArchive);

}
