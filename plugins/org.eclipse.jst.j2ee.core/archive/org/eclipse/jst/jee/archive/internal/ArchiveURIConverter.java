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
package org.eclipse.jst.jee.archive.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
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

	protected Map uriToPathMap = new HashMap();

	protected Map pathToURIMap = new HashMap();

	public final URI getURI(IPath path) {
		if (!pathToURIMap.containsKey(path)) {
			URI uri = convertPathToURI(path);
			uriToPathMap.put(uri, path);
			pathToURIMap.put(path, uri);
			return uri;
		} else {
			return (URI) pathToURIMap.get(path);
		}
	}

	protected URI convertPathToURI(IPath path) {
		return URI.createURI(path.toString());
	}

	public final IPath getPath(URI uri) {
		return (IPath) uriToPathMap.get(uri);
	}

	public InputStream createInputStream(URI uri) throws IOException {
		IPath path = getPath(uri);
		try {
			IArchiveResource archiveResource = getArchive().getArchiveResource(path);
			return archiveResource.getInputStream();
		} catch (FileNotFoundException e) {
			throw new IOException(e.getMessage());
		}

	}

}
