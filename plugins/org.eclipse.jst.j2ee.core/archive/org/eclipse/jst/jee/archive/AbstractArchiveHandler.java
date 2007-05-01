package org.eclipse.jst.jee.archive;

public class AbstractArchiveHandler implements IArchiveHandler {

	public boolean handlesArchive(IArchive archive) {
		return false;
	}

	public IArchive convertToSpecificArchive(IArchive archive) {
		return null;
	}

}
