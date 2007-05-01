package org.eclipse.jst.jee.archive.internal;

import java.util.List;

import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveHandler;


public class ArchiveHanderFactory {

	private static ArchiveHanderFactory instance = null;

	public static ArchiveHanderFactory getInstance() {
		if (instance == null) {
			instance = new ArchiveHanderFactory();
		}
		return instance;
	}

	private ArchiveHanderFactory() {
	}

	public IArchiveHandler getArchiveHandler(IArchive archive) {
		return null;
	}

	public List getArchiveHandlers() {
		return null;
	}

}
