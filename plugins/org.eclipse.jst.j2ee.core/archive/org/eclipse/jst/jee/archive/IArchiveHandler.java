package org.eclipse.jst.jee.archive;

public interface IArchiveHandler {

	public boolean handlesArchive(IArchive archive);

	public IArchive convertToSpecificArchive(IArchive archive);

}
