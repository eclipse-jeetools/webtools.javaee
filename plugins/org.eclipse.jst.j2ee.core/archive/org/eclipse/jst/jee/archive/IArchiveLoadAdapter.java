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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.runtime.IPath;

/**
 * { @link IArchiveLoadAdapter} knows how to load or read the contents for an
 * { @link IArchive}. This provides a delegation model for reading in the
 * contents of an {@link IArchive}.
 * 
 * By extending {@link AbstractArchiveLoadAdapter} and using
 * { @link ArchiveOptions} clients may "plug in" to an instance of an
 * { @link IArchive}. Examples might include reading from a zip file, from an
 * input stream, from the local file system, or from a workbench.
 * 
 * This interface is not intended to be implemented by or subclassed by clients.
 * Clients should instead subclass {@link AbstractArchiveLoadAdapter} or one if
 * its subclasses.
 */
public interface IArchiveLoadAdapter extends IArchiveAdapter {

	/**
	 * Called to release any resources held by this {@link IArchiveLoadAdapter}.
	 */
	public void close();

	/**
	 * Returns <code>true</code> if this {@link IArchiveLoadAdapter} contains
	 * the {@link IArchiveResource} specified by the passed {@link IPath}. If
	 * this method returns <code>true</code> then this
	 * { @link IArchiveLoadAdapter} should be able to successfully return an
	 * { @link IArchiveResource} from {@link #getArchiveResource(IPath)} using an
	 * equal passed {@link IPath}.
	 * 
	 * @param resourcePath
	 * @return
	 */
	public boolean containsArchiveResource(IPath resourcePath);

	/**
	 * Returns the {@link IArchiveResource} specified by the passed
	 * { @link IPath}. A {@link FileNotFoundException} is thrown if this
	 * { @link IArchiveLoadAdapter} does not contain the {@link IArchiveResource}
	 * as specified by {@link #containsArchiveResource(IPath)}.
	 * 
	 * @param resourcePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public IArchiveResource getArchiveResource(IPath resourcePath) throws FileNotFoundException;

	/**
	 * Returns a {@link List} of all {@link IArchiveResource} contained by this
	 * { @link IArchiveLoadAdapter}.
	 * 
	 * @return
	 */
	public List <IArchiveResource> getArchiveResources();

	/**
	 * Returns an {@link InputStream} for the specified {@link IArchiveResource}.
	 * If the specified {@link IArchiveResource} is not contained by this
	 * { @link IArchiveLoadAdapter}, then a {@link FileNotFoundException} is
	 * thrown. An {@link IOException} is thrown for all other errors.
	 * 
	 * @param archiveResource
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public InputStream getInputStream(IArchiveResource archiveResource) throws IOException, FileNotFoundException;

	/**
	 * Returns <code>true</code> if this {@link IArchiveLoadAdapter} contains
	 * a model Object specified by the passed {@link IPath}.
	 * 
	 * @param modelObjectPath
	 * @return
	 */
	public boolean containsModelObject(IPath modelObjectPath);

	/**
	 * Returns the model Object specified by the passed {@link IPath}. An
	 * { @link ArchiveModelLoadException} is thrown if this
	 * { @link IArchiveLoadAdapter} does not contain the model Object as
	 * specified by {@link #containsModelObject(IPath)}.
	 * 
	 * @param modelObjectPath
	 * @return
	 * @throws ArchiveModelLoadException
	 */
	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException;

}
