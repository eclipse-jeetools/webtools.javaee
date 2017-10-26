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

import java.io.InputStream;

import org.eclipse.core.runtime.IPath;

/**
 * This interface is not intended to be implemented by clients.
 * 
 * @author jasholl
 * 
 */
public interface IArchiveResource {

	public static int UNKNOWN_TYPE = -1;

	public static int FILE_TYPE = 0;

	public static int DIRECTORY_TYPE = 1;

	public static int ARCHIVE_TYPE = 2;

	public IPath getPath();

	public void setPath(IPath path);

	public long getSize();

	public void setSize(long size);

	public long getLastModified();

	public void setLastModified(long lastModified);

	public int getType();

	public void setType(int fileType);

	public IArchive getArchive();

	public void setArchive(IArchive archive);

	/**
	 * Returns an input stream for this {@link IArchiveResource} if its type is
	 * either a {@link #FILE_TYPE} or {@link #ARCHIVE_TYPE} as defined by
	 * { @link #getType()}. <code>null</code> is returned if the type is
	 * { @link #DIRECTORY_TYPE}.
	 * 
	 * @return
	 * @throws java.io.FileNotFoundException
	 * @throws java.io.IOException
	 */
	public InputStream getInputStream() throws java.io.FileNotFoundException, java.io.IOException;
}
