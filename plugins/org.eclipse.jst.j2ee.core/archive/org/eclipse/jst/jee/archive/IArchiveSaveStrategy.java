package org.eclipse.jst.jee.archive;

public interface IArchiveSaveStrategy extends IArchiveStrategy {

	/**
	 * Close and release any resources being held by this object
	 */
	public void close() throws java.io.IOException;

	/**
	 * Notify resources if necessary that save is complete for this archive,
	 * being careful not to close any resources that a parent archive might
	 * still be using
	 */
	public void finish() throws java.io.IOException;

	public void save() throws ArchiveSaveFailureException;

}
