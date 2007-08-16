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
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter;


/**
 * Knows how to export the contents of an archive file. This provides a delegation model saving the
 * archive. Examples might include exporting to a zip output stream, to the file system, or into a
 * workbench.
 */
public interface SaveStrategy extends ArchiveStrategy {
	/**
	 * Close and release any resources being held by this object
	 */
	public void close() throws java.io.IOException;

	/**
	 * Notify resources if necessary that save is complete for this archive, being careful not to
	 * close any resources that a parent archive might still be using
	 */
	public void finish() throws java.io.IOException;

	public SaveFilter getFilter();

	/**
	 * Returns whether this archive is saving files to a directory on the file system
	 */
	public boolean isDirectory();

	public void save() throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;

	public void save(ArchiveManifest aManifest) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;

	public void save(File aFile, FileIterator iterator) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;

	public void save(File aFile, java.io.InputStream in) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;

	public void saveMofResource(Resource aResource) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;

	public void setFilter(SaveFilter aFilter);
}
