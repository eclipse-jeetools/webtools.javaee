/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.internal.ArchiveResourceImpl;

public abstract class AbstractLoadStrategy extends AbstractArchiveStrategy implements IArchiveLoadStrategy {

	public void close() {
	}

	/**
	 * Utility method for creating a directory
	 * 
	 * @param path
	 * @return
	 */
	protected IArchiveResource createDirectory(IPath path) {
		IArchiveResource aFile = null;
		aFile = new ArchiveResourceImpl();
		aFile.setPath(path);
		aFile.setType(IArchiveResource.DIRECTORY_TYPE);
		aFile.setArchive(getArchive());
		return aFile;
	}

	/**
	 * Utility method for creating a file
	 * 
	 * @param path
	 * @return
	 */
	protected IArchiveResource createFile(IPath path) {
		// TODO possibly handle the nested archive case here
		IArchiveResource aFile = null;
		aFile = new ArchiveResourceImpl();
		aFile.setPath(path);
		aFile.setType(IArchiveResource.FILE_TYPE);
		aFile.setArchive(getArchive());
		return aFile;
	}


	
}
