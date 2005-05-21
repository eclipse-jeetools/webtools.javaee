/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.locator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibInfo;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibLocator;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;


/**
 * @version 1.0
 * @author
 */
public abstract class AbstractTaglibLocator implements ITaglibLocator {
	protected IProject project;
	static protected final ITaglibInfo[] EMPTY_TAGLIBINFO_ARRAY = new ITaglibInfo[0];

	public AbstractTaglibLocator(IProject project) {
		this.project = project;
	}

	protected String calculateURIForFile(IPath projectRelativePath) {
		IPath result = new Path("/"); //$NON-NLS-1$
		IPath serverRoot = getServerRoot();
		IPath filePath = projectRelativePath;
		result = result.append(filePath.removeFirstSegments(serverRoot.segmentCount()));
		return result.makeAbsolute().toString();
	}

	abstract protected IPath getServerRoot();

	protected TLDDigester getTLDDigester(IFile tldFile) {
		InputStream stream = null;
		try {
			stream = tldFile.getContents();
			return getTLDDigester(stream);
		} catch (CoreException e) {
			Logger.getLogger().log(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					Logger.getLogger().log(e);
				}
			}
		}
		return null;
	}

	protected TLDDigester getTLDDigester(InputStream stream) {
		TLDDigester digester = new TLDDigester(stream);
		return digester;
	}

	protected ITaglibInfo createTaglibForTLD(IFile file) {
		TLDDigester digester = getTLDDigester(file);
		try {
			if (digester != null) {
				if (isValidTLD(digester)) {
					String uri = digester.getURI();
					if (uri != null) {
						TaglibInfo taglibInfo = new TaglibInfo(this.project, uri, file.getProjectRelativePath());
						taglibInfo.setPrefix(digester.getShortName());
						taglibInfo.setIsURIFromTLD(true);
						return taglibInfo;
					}
				}
			}
		} finally {
			if (digester != null)
				digester.close();
		}
		return null;
	}

	protected ITaglibInfo createTaglibForTLD(String uri, IPath file) {
		return new TaglibInfo(this.project, uri, file);
	}

	protected ITaglibInfo createTaglibForJar(String uri, IPath jarfile, IPath tldLocation) {
		return new TaglibInfo(this.project, uri, jarfile, tldLocation);
	}

	protected ITaglibInfo createTaglibForLibModuleJar(String uri, IPath jarfile, IResource tldFile) {
		TaglibInfo taglibInfo = new TaglibInfo(tldFile.getProject(), uri, jarfile, tldFile.getProjectRelativePath());
		taglibInfo.setIsLibModule(true);
		return taglibInfo;
	}

	protected boolean hasJarExtension(IPath file) {
		String extension = file.getFileExtension();
		if (extension != null) {
			if (extension.equalsIgnoreCase("jar")) //$NON-NLS-1$
				return true;
			if (extension.equalsIgnoreCase("zip")) //$NON-NLS-1$
				return true;
		}
		return false;
	}


	protected boolean hasJarExtension(String filename) {
		return hasJarExtension(new Path(filename));
	}


	protected boolean hasTLDExtension(IPath file) {
		String extension = file.getFileExtension();
		if (extension != null && extension.equalsIgnoreCase("tld")) //$NON-NLS-1$
			return true;
		return false;
	}

	protected boolean hasTagExtension(String filename) {
		return hasTagExtension(new Path(filename));
	}

	protected boolean hasTagExtension(IPath file) {
		String extension = file.getFileExtension();
		if (extension != null && extension.equalsIgnoreCase("tag")) //$NON-NLS-1$
			return true;
		return false;
	}


	protected boolean hasTLDExtension(String filename) {
		return hasTLDExtension(filename);
	}

	protected boolean isValidTLD(TLDDigester digester) {
		return true;
	}

	/*
	 * @see ITaglibLocator#search(IResource)
	 */
	public ITaglibInfo[] search(IResource resource) {
		final ArrayList results = new ArrayList();

		try {
			resource.accept(new IResourceVisitor() {
				public boolean visit(IResource aresource) throws CoreException {
					if (aresource.getType() != IResource.FILE) {
						if (aresource.getType() != IResource.ROOT && aresource.getProject() != AbstractTaglibLocator.this.project)
							return false;
						return true;
					}
					ITaglibInfo[] taglibs = searchFile((IFile) aresource);
					if (taglibs != null)
						results.addAll(Arrays.asList(taglibs));

					return false;
				}
			});
		} catch (CoreException e) {
			Logger.getLogger().log(e);
		}
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

	abstract protected ITaglibInfo[] searchFile(IFile file);



}