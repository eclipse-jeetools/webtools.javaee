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
 * This interface is not intended to be subclassed by clients. Clients may
 * subclass {@link AbstractLoadStrategy}.
 * 
 * LoadStrategy knows how to load or read the contents of a container. This
 * provides a delegation model for reading in the contents of an archive file.
 * 
 * By extending{@link AbstractLoadStrategy} and using {@link ArchiveOptions}
 * clients may "plug in" to an instance of an archive. Examples might include
 * reading from a zip file, from an input stream, from the local file system, or
 * from a workbench.
 */
public interface IArchiveLoadStrategy extends IArchiveStrategy {

	public void close();

	/**
	 * Returns the specified file if this {@link IArchiveLoadStrategy} knows about it.
	 * Returns <code>null</code> if otherwise.
	 * 
	 * @param filePath
	 * @return
	 */
	public IArchiveResource getArchiveResource(IPath resourcePath) throws FileNotFoundException;

	public List getArchiveResource();

	public InputStream getInputStream(IArchiveResource archiveResource) throws IOException, FileNotFoundException;

	public boolean containsModelObject(IPath modelObjectPath);

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException;

}
