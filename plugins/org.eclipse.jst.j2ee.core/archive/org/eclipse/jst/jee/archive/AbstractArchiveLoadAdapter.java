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

/**
 * Abstract implementation of {@link IArchiveLoadAdapter} intended for
 * subclassing by clients. See {@link IArchiveLoadAdapter} for details.
 */
public abstract class AbstractArchiveLoadAdapter extends AbstractArchiveAdapter implements IArchiveLoadAdapter {

	/**
	 * Subclasses may wish to override.
	 */
	public void close() {
	}

	/**
	 * Utility method for creating an {@link IArchiveResource} representing a
	 * directory entry for the passed {@link IPath}.
	 * 
	 * @param path
	 * @return
	 */
	protected IArchiveResource createDirectory(IPath archiveRelativePath) {
		verifyRelative(archiveRelativePath);
		IArchiveResource aFile = null;
		aFile = new ArchiveResourceImpl();
		aFile.setPath(archiveRelativePath);
		aFile.setType(IArchiveResource.DIRECTORY_TYPE);
		aFile.setArchive(getArchive());
		return aFile;
	}

	/**
	 * Utility method for creating an {@link IArchiveResource} representing a
	 * file entry for the passed {@link IPath}.
	 * 
	 * @param path
	 * @return
	 */
	protected IArchiveResource createFile(IPath archiveRelativePath) {
		verifyRelative(archiveRelativePath);
		// TODO possibly handle the nested archive case here
		IArchiveResource aFile = null;
		aFile = new ArchiveResourceImpl();
		aFile.setPath(archiveRelativePath);
		aFile.setType(IArchiveResource.FILE_TYPE);
		aFile.setArchive(getArchive());
		return aFile;
	}

	public static void verifyRelative(IPath archiveRelativePath) {
		if (archiveRelativePath.isAbsolute() && !archiveRelativePath.equals(IArchive.EMPTY_MODEL_PATH)) {
			throw new RuntimeException(archiveRelativePath + " must be relative."); //$NON-NLS-1$
		}
	}

}
