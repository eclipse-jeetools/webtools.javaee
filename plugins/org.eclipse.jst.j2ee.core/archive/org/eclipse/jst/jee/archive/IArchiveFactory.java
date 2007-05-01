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
