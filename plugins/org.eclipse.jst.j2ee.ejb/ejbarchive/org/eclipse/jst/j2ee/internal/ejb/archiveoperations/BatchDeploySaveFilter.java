/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter;


/**
 * Insert the type's description here. Creation date: (05/04/01 9:19:09 PM)
 * 
 * @author: Administrator
 */
public class BatchDeploySaveFilter implements SaveFilter {
	protected SaveFilter target;

	/**
	 * Insert the method's description here. Creation date: (05/04/01 9:23:36 PM)
	 * 
	 * @param targetFilter
	 *            com.ibm.etools.archive.SaveFilter
	 */
	public BatchDeploySaveFilter(SaveFilter targetFilter) {
		super();
		target = targetFilter;
	}

	/**
	 * Answer whether an element in the archive having the uri should be saved; the uri may be for a
	 * file, a nested archive, or a loaded mof resource. For deployed jars, if the file came was
	 * copied from the orignal jar file, always save
	 */
	public boolean shouldSave(java.lang.String uri, Archive anArchive) {
		File aFile = null;
		try {
			aFile = anArchive.getFile(uri);
		} catch (java.io.FileNotFoundException mustBeAResource) {
			//Ignore
		}
		if (aFile == null)
			return targetShouldSave(uri, anArchive);
		return aFile.getLoadingContainer() != anArchive || targetShouldSave(uri, anArchive);
	}

	protected boolean targetShouldSave(String uri, Archive anArchive) {
		return target == null || target.shouldSave(uri, anArchive);
	}
}