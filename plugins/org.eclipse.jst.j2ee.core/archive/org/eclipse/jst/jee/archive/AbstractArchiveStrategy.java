package org.eclipse.jst.jee.archive;

public abstract class AbstractArchiveStrategy implements IArchiveStrategy {

	protected IArchive archive = null;

	public IArchive getArchive() {
		return archive;
	}

	public void setArchive(IArchive archive) {
		this.archive = archive;
	}

}
