/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

	protected Map <URI, IPath> uriToPathMap = new HashMap <URI, IPath>();

	protected Map <IPath, URI> pathToURIMap = new HashMap <IPath, URI>();

	public final URI getURI(IPath path) {
		if (!pathToURIMap.containsKey(path)) {
			URI uri = convertPathToURI(path);
			uriToPathMap.put(uri, path);
			pathToURIMap.put(path, uri);
			return uri;
		} else {
			return pathToURIMap.get(path);
		}
	}

	protected URI convertPathToURI(IPath path) {
		return URI.createURI(path.toString());
	}

	protected IPath convertURIToPath(URI uri) {
		return new Path(uri.toString());
	}

	public final IPath getPath(URI uri) {
		if (!uriToPathMap.containsKey(uri)) {
			IPath path = convertURIToPath(uri);
			uriToPathMap.put(uri, path);
			pathToURIMap.put(path, uri);
			return path;
		} else {
			return uriToPathMap.get(uri);
		}
	}

	@Override
	public InputStream createInputStream(URI uri) throws IOException {
		IPath path = getPath(uri);
		IArchiveResource archiveResource = getArchive().getArchiveResource(path);
		return archiveResource.getInputStream();
	}

}
