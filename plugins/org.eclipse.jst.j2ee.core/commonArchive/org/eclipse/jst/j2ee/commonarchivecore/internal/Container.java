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
package org.eclipse.jst.j2ee.commonarchivecore.internal;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.util.EList;

/**
 * @generated
 */
public interface Container extends File {

	/**
	 * Indicate whether the archive contains a file having a relative path of the parameter; the uri
	 * may or may not have a leading separator
	 */
	public boolean containsFile(String uri);

	/**
	 * Return the absolute path of the file from its load strategy, if it is known. Should be used
	 * mainly for read-only runtime purposes, as edit-time modifications may make the result
	 * undefined.
	 * 
	 * @throws FileNotFoundException
	 *             if the archive is "virtual", eg, a nested jar
	 */
	public String getAbsolutePath() throws FileNotFoundException;

	public File getFile(String uri) throws FileNotFoundException;

	public InputStream getInputStream(String uri) throws FileNotFoundException, IOException;

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return com.ibm.etools.archive.LoadStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy getLoadStrategy();

	/**
	 * Indicates whether the archive has ever had its files enumerated; used as an optimization
	 */
	public boolean isIndexed();

	/**
	 * Goes directly to the strategy
	 */
	public InputStream primGetInputStream(String uri) throws FileNotFoundException, IOException;

	public void rebuildFileIndex();

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newLoadStrategy
	 *            com.ibm.etools.archive.LoadStrategy
	 */
	public void setLoadStrategy(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy newLoadStrategy);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The list of Files references
	 */
	EList getFiles();

	/**
	 * Clears the list of files in this Container and drops the index
	 */
	public void clearFiles();
}
