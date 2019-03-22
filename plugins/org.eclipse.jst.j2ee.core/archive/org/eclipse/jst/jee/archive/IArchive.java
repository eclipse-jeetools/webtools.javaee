/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * This interface is not intended to be implemented by clients.
 * 
 * @author jasholl
 */
public interface IArchive extends IArchiveResource {

	IPath EMPTY_MODEL_PATH = new Path("/"); //$NON-NLS-1$

	public boolean isOpen();

	public boolean containsArchiveResource(IPath archiveRelativePath);

	/**
	 * Returns the {@link IArchiveResource} specified by the archive relative
	 * path. Throws a {@link RuntimeException} if the specified path is not
	 * relative.
	 * 
	 * @param archiveRelativePath
	 * @return
	 * @throws java.io.FileNotFoundException
	 */
	public IArchiveResource getArchiveResource(IPath archiveRelativePath) throws java.io.FileNotFoundException;

	/**
	 * Returns an {@link IArchive} for the specified archiveResource. If the
	 * { @link IArchive} is successfully returned, it will be added to the list
	 * returned by {@link #getNestedArchives()}. Also, the original
	 * { @link IArchiveResource} will be removed from this {@link IArchive}'s
	 * list of {@link IArchiveResource}s returned by
	 * { @link #getArchiveResources()} and replaced with the returned
	 * { @link IArchive}.
	 * 
	 * @param archiveResource
	 * @return
	 * @throws ArchiveOpenFailureException
	 */
	public IArchive getNestedArchive(IArchiveResource archiveResource) throws ArchiveOpenFailureException;

	public List<IArchive> getNestedArchives();

	/**
	 * Returns a list of all {@link IArchiveResource}s in the archive.
	 * 
	 * @return
	 */
	public List<IArchiveResource> getArchiveResources();

	public boolean containsModelObject();

	public boolean containsModelObject(IPath modelObjectPath);

	public Object getModelObject() throws ArchiveModelLoadException;

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException;

	public ArchiveOptions getArchiveOptions();

	public IArchiveLoadAdapter getLoadAdapter();

}
