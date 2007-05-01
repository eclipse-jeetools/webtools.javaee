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

	public InputStream getInputStream() throws java.io.FileNotFoundException, java.io.IOException;
}
