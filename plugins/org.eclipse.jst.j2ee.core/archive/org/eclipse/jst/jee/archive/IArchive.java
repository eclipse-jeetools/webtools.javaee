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

import java.util.List;

import org.eclipse.core.runtime.IPath;

/**
 * This interface is not intended to be implemented by clients.
 * 
 * @author jasholl
 */
public interface IArchive extends IArchiveResource {

	public boolean isOpen();

	public boolean containsArchiveResource(IPath archiveRelativePath);

	/**
	 * Returns the {@link IArchiveResource} specified by the archive relative path.
	 * 
	 * @param archiveRelativePath
	 * @return
	 * @throws java.io.FileNotFoundException
	 */
	public IArchiveResource getArchiveResource(IPath archiveRelativePath) throws java.io.FileNotFoundException;

	/**
	 * Returns a list of all {@link IArchiveResource}s in the archive.
	 * 
	 * @return
	 */
	public List getArchiveResources();

	public boolean containsModelObject();

	public boolean containsModelObject(IPath modelObjectPath);

	public Object getModelObject() throws ArchiveModelLoadException;

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException;

	public ArchiveOptions getArchiveOptions();

	public IArchiveLoadStrategy getLoadStrategy();

}
