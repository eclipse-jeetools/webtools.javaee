/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.locator;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;
import org.eclipse.jst.j2ee.web.taglib.ITaglibInfo;


public class JavaProjectTaglibLocator extends AbstractTaglibLocator {
	protected IJavaProject javaProject;
	protected IPath metaInfPath;

	/**
	 * Constructor for JavaProjectTaglibLocator.
	 * 
	 * @param project
	 */
	public JavaProjectTaglibLocator(IProject project) {
		super(project);
	}

	protected boolean isTLDFile(IFile file) {
		return isInMetaInfFolder(file) && hasTLDExtension(file.getProjectRelativePath());
	}

	protected boolean isTaglibTLD(IFile tldFile) {
		return isTLDFile(tldFile) && tldFile.getProjectRelativePath().lastSegment().equalsIgnoreCase("taglib.tld"); //$NON-NLS-1$
	}

	protected boolean isInMetaInfFolder(IFile file) {
		this.metaInfPath = getMetaInfPath();
		if (file.getFullPath().matchingFirstSegments(this.metaInfPath) >= 1) {
			return true;
		}
		return false;
	}

	protected IPath getMetaInfPath() {
		if (this.metaInfPath == null) {
			try {
				this.metaInfPath = getJavaProject().getOutputLocation();
				this.metaInfPath = this.metaInfPath.append(J2EEConstants.META_INF);
			} catch (JavaModelException e) {
				this.metaInfPath = new Path("/"); //$NON-NLS-1$
			}
		}
		return this.metaInfPath;
	}

	/**
	 * @see AbstractTaglibLocator#searchFile(IFile)
	 */
	protected ITaglibInfo[] searchFile(IFile file) {
		if (isTLDFile(file)) {
			return searchTLDFile(file);
		}
		return EMPTY_TAGLIBINFO_ARRAY;
	}

	protected IJavaProject getJavaProject() {
		if (this.javaProject == null)
			this.javaProject = JavaCore.create(this.project);
		return this.javaProject;
	}

	/**
	 * @see AbstractTaglibLocator#getServerRoot()
	 */
	protected IPath getServerRoot() {
		try {
			return getJavaProject().getOutputLocation();
		} catch (JavaModelException e) {
		}
		return new Path("/"); //$NON-NLS-1$
	}

	protected ITaglibInfo[] searchTLDFile(IFile file) {
		ArrayList results = new ArrayList(2);
		TLDDigester digester = null;
		try {
			// Add a taglib for the uri in the tld file.
			digester = getTLDDigester(file);
			ITaglibInfo taglib = createTaglibForTLD(file);
			if (taglib != null) {
				((TaglibInfo) taglib).setPrefix(digester);
				results.add(taglib);
			}


			// Create a URI based on the file location in the project
			if (isTaglibTLD(file)) {
				taglib = createTaglibForTLD(calculateURIForFile(file.getProjectRelativePath()), file.getProjectRelativePath());
				if (taglib != null) {
					((TaglibInfo) taglib).setPrefix(digester);
					results.add(taglib);
				}
			}

		} finally {
			if (digester != null)
				digester.close();
		}
		return (ITaglibInfo[]) results.toArray(new ITaglibInfo[results.size()]);
	}
}