package org.eclipse.jst.jee.archive.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;



public class ArchiveURIConverter extends URIConverterImpl {

	private IArchive archive = null;

	public ArchiveURIConverter(IArchive archive) {
		this.archive = archive;
	}

	public IArchive getArchive() {
		return archive;
	}

	public InputStream createInputStream(URI uri) throws IOException {
		IPath path = new Path(uri.toString());
		try {
			IArchiveResource archiveResource = getArchive().getArchiveResource(path);
			return archiveResource.getInputStream();
		} catch (FileNotFoundException e) {
			throw new IOException(e.getMessage());
		}

	}

}
