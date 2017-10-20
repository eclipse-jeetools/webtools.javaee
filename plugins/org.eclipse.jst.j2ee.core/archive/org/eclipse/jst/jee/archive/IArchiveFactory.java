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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.jee.archive.internal.ArchiveFactoryImpl;

/**
 * { @link IArchiveFactory} is the where clients should open and save archives.
 * 
 * This interface is not intended to be implemented or subclassed by clients.
 * Clients should access through {@link #INSTANCE}
 */
public interface IArchiveFactory {

	/**
	 * A concrete implementation of {@link IArchiveFactory}.
	 */
	IArchiveFactory INSTANCE = new ArchiveFactoryImpl();

	public IArchive openArchive(IPath archivePath) throws ArchiveOpenFailureException;

	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException;

	public void closeArchive(IArchive archive);

	public void saveArchive(IArchive archive, IPath outputPath, IProgressMonitor monitor) throws ArchiveSaveFailureException;

	public void saveArchive(IArchive archive, ArchiveOptions archiveOptions, IProgressMonitor monitor) throws ArchiveSaveFailureException;

}
