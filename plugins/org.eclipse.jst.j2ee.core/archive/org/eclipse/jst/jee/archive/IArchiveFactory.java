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
import org.eclipse.jst.jee.archive.internal.ArchiveFactoryImpl;

/**
 * This interface is not intended to be implemented by clients
 * 
 * @author jasholl
 * 
 */
public interface IArchiveFactory {

	IArchiveFactory INSTANCE = new ArchiveFactoryImpl();

	public IArchive openArchive(IPath archivePath) throws ArchiveOpenFailureException;

	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException;

	public void closeArchive(IArchive archive);

	public void saveArchive(IArchive archive, IPath outputPath) throws ArchiveSaveFailureException;

	public void saveArchive(IArchive archive, ArchiveOptions archiveOptions) throws ArchiveSaveFailureException;

}
