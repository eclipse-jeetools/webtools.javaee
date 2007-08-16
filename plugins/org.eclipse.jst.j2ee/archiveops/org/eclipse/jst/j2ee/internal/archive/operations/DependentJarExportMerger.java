/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;

public class DependentJarExportMerger {
	protected Archive outgoingArchive;
	protected IProject project;
	protected List dependentArchives;
	protected boolean includeSource;

	/**
	 * DependentJarExportMerger constructor comment.
	 */
	public DependentJarExportMerger(Archive archive, IProject aProject, boolean includeSourceFiles) {
		super();
		outgoingArchive = archive;
		project = aProject;
		includeSource = includeSourceFiles;
	}

	protected void finalize() throws Throwable {
		release();
	}

	public CommonarchiveFactory getArchiveFactory() {
		return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
	}

	public void merge() {
		String[] cp = outgoingArchive.getManifest().getClassPathTokenized();
		dependentArchives = new ArrayList(cp.length);
		List keepClassPaths = new ArrayList();
		for (int i = 0; i < cp.length; i++) {
			Archive dependentJar = null;
			IFile file = null;
			try {
				file = project.getFile(cp[i]);
			} catch (IllegalArgumentException e) {
				continue;
			}
			if (file.exists() && cp[i].endsWith(J2EEImportConstants.IMPORTED_JAR_SUFFIX)) {
				try {
					dependentJar = getArchiveFactory().primOpenArchive(file.getLocation().toOSString());
				} catch (OpenFailureException ex) {
					// Need to write this to the log file
					org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ex);
					continue;
				}
				dependentArchives.add(dependentJar);
				mergeFiles(dependentJar);
			} else
				keepClassPaths.add(cp[i]);
		}
		resetClassPath(keepClassPaths);
	}

	protected void mergeFiles(Archive dependentJar) {
		List files = dependentJar.getFiles();
		List filesToAdd = new java.util.ArrayList();
		for (int i = 0; i < files.size(); i++) {
			File aFile = (File) files.get(i);
			if (shouldMerge(aFile))
				filesToAdd.add(aFile);
		}
		try {
			outgoingArchive.addCopyFiles(filesToAdd);
		} catch (DuplicateObjectException ignore) {
			// Shouldn't happen because we just checked for dups
		}
	}

	/**
	 * Clients must call this when done to close the archives
	 */
	public void release() {
		if (dependentArchives == null)
			return;
		for (int i = 0; i < dependentArchives.size(); i++) {
			Archive anArchive = (Archive) dependentArchives.get(i);
			if (anArchive.isOpen())
				anArchive.close();
		}
	}

	protected void resetClassPath(List cp) {
		ArchiveManifest mf = outgoingArchive.getManifest();
		if (cp.isEmpty())
			mf.getMainAttributes().remove(Attributes.Name.CLASS_PATH);
		else {
			mf.setClassPath("");//$NON-NLS-1$
			for (int i = 0; i < cp.size(); i++) {
				mf.appendClassPath((String) cp.get(i));
			}
		}
	}

	protected boolean shouldMerge(File aFile) {

		String uri = aFile.getURI();
		if (!(uri.endsWith(".java") || uri.endsWith(".class")))//$NON-NLS-2$//$NON-NLS-1$
			return false;
		if (!includeSource && uri.endsWith(".java"))//$NON-NLS-1$
			return false;
		return !outgoingArchive.containsFile(uri) && !ArchiveConstants.MANIFEST_URI.equals(uri);
	}
}
