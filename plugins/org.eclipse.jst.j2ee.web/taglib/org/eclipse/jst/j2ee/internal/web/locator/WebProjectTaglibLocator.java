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

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.web.taglib.DirTaglibInfo;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;
import org.eclipse.jst.j2ee.web.taglib.ITaglibInfo;

import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * @version 1.0
 * @author
 */
public class WebProjectTaglibLocator extends AbstractWebTaglibLocator {
	public WebProjectTaglibLocator(IProject project) {
		super(project);
	}

	protected ITaglibInfo[] searchFile(IFile file) {
		if (isTaglibJar(file)) {
			return searchJarFile(file);
		} else if (isTLDFile(file)) {
			return searchTLDFile(file);
		}
		return EMPTY_TAGLIBINFO_ARRAY;
	}

	protected ITaglibInfo[] searchDir(IContainer container) {
		//		File containerDir = container.getLocation().toFile();
		//		if(containerDir.isDirectory()){
		//			File tldFiles[] = containerDir.listFiles(new FileFilter() {
		//				public boolean accept(File pathname) {
		//					return (!pathname.isDirectory() && ((new Path(pathname
		//							.getName()).getFileExtension()
		//							.equalsIgnoreCase("tld"))));
		//				}
		//			});
		//			if (tldFiles == null || tldFiles.length == 0) {
		File tagFiles[] = container.getLocation().toFile().listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return (!pathname.isDirectory() && ((new Path(pathname.getName()).getFileExtension().equalsIgnoreCase("tag")))); //$NON-NLS-1$
			}
		});
		if (tagFiles != null && tagFiles.length > 0) {
			ITaglibInfo taglib = createTaglibForDir(container, tagFiles);

			if (taglib != null)
				return new ITaglibInfo[]{taglib};
		}
		//			}
		//		}
		return EMPTY_TAGLIBINFO_ARRAY;
	}


	/**
	 * @param container
	 * @param tagFiles
	 * @return
	 */
	private ITaglibInfo createTaglibForDir(IContainer container, File[] tagFiles) {
		String uri = calculateURIForFile(container.getProjectRelativePath());



		DirTaglibInfo dirTaglibInfo = new DirTaglibInfo(container, uri, container.getProjectRelativePath());

		dirTaglibInfo.setPrefix(calculateShortNameForTaglib(uri));

		return dirTaglibInfo;
	}

	/**
	 * @param uri
	 * @return
	 */
	private String calculateShortNameForTaglib(String uri) {
		uri = uri.replace('\\', '/');
		if (uri.equalsIgnoreCase("/WEB-INF/tags")) //$NON-NLS-1$
			return "tags"; //$NON-NLS-1$


		String suffixOfURI = uri.substring("/WEB-INF/tags/".length()); //$NON-NLS-1$
		suffixOfURI = suffixOfURI.replace('/', '-');
		suffixOfURI = suffixOfURI.replace('\\', '-');

		return suffixOfURI;

	}

	protected ITaglibInfo[] searchTLDFile(IFile file) {
		ArrayList results = new ArrayList(2);

		//		Even though the spec says auto-discover should work for loose tld's, both websphere &
		// tomcat dont do it hence
		//		it is commented
		//		// Add a taglib for the uri in the tld file.
		//		if (getWebNature().isJSP1_2() && isInWebInfFolder(file)) {
		//			ITaglibInfo taglib = createTaglibForTLD(file);
		//			if (taglib != null)
		//				results.add(taglib);
		//		}

		// Create a URI based on the file location in the project
		//TLDDigester
		boolean canAddTaglibTLD = true;
		TLDDigester digester = getTLDDigester(file);

		if (!isValidTLD(digester))// If JSP1.1 then taglib.tld better be 1.1 defect CMVC 217548
			canAddTaglibTLD = false;

		if (canAddTaglibTLD) {
			ITaglibInfo taglib = createTaglibForTLD(calculateURIForFile(file.getProjectRelativePath()), file.getProjectRelativePath());
			((TaglibInfo) taglib).setPrefix(digester);
			if (taglib != null)
				results.add(taglib);
		}
		if (digester != null)
			digester.close();
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}


	/*
	 * @see ITaglibLocator#search(IResource)
	 */
	public ITaglibInfo[] search(IResource resource) {
		final ArrayList results = new ArrayList();

		try {
			resource.accept(new IResourceVisitor() {
				public boolean visit(IResource aresource) throws CoreException {
					int JSPVersion = getJSPVersion();
					if (aresource.getType() != IResource.FILE) {
						if (aresource.getType() != IResource.ROOT && WebProjectTaglibLocator.this.project != aresource.getProject())
							return false;
						if (JSPVersion==J2EEVersionConstants.JSP_2_0_ID) {
							ITaglibInfo[] taglibs = searchDir((IContainer) aresource);
							if (taglibs != null)
								results.addAll(Arrays.asList(taglibs));
							return true;
						}
						// directory taglibs are only in jsp 2.0
						return false;

					}

					if (hasTagExtension(aresource.getName())) {
						if (JSPVersion==J2EEVersionConstants.JSP_2_0_ID) {
							ITaglibInfo[] taglibs = searchDir(aresource.getParent());
							if (taglibs != null)
								results.addAll(Arrays.asList(taglibs));
						}
					} else {
						ITaglibInfo[] taglibs = searchFile((IFile) aresource);
						if (taglibs != null)
							results.addAll(Arrays.asList(taglibs));
					}

					return false;
				}
			});
		} catch (CoreException e) {
			Logger.getLogger().log(e);
		}
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}

}