/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;

/**
 * @generated
 */
public class ReadOnlyDirectoryImpl extends ContainerImpl implements ReadOnlyDirectory {

	public ReadOnlyDirectoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.READ_ONLY_DIRECTORY;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	@Override
	public boolean containsFile(java.lang.String aUri) {
		getFiles();
		return getFileIndex().containsKey(aUri);
	}

	/**
	 * containsFileInRootOrSubdirectory method comment.
	 */
	@Override
	public boolean containsFileInSelfOrSubdirectory(java.lang.String aUri) {
		return getLoadStrategy().contains(aUri);
	}

	/**
	 * getFileInSelfOrSubdirectory method comment.
	 */
	@Override
	public File getFileInSelfOrSubdirectory(java.lang.String aUri) throws java.io.FileNotFoundException {
		if (!containsFileInSelfOrSubdirectory(aUri))
			throw new java.io.FileNotFoundException(aUri);

		if (containsFile(aUri))
			return getFile(aUri);

		List subdirs = getReadOnlyDirectories();
		for (int i = 0; i < subdirs.size(); i++) {
			ReadOnlyDirectory subdir = (ReadOnlyDirectory) subdirs.get(i);
			if (subdir.containsFileInSelfOrSubdirectory(aUri))
				return subdir.getFileInSelfOrSubdirectory(aUri);
		}
		throw new java.io.FileNotFoundException(aUri);
	}

	/**
	 * Returns a flat list of all the files contained in this directory and subdirectories, with the
	 * directories filtered out, as the list would appear in an archive
	 */
	@Override
	public java.util.List getFilesRecursive() {
		List allFiles = new ArrayList();
		List filesList = getFiles();
		for (int i = 0; i < filesList.size(); i++) {
			File aFile = (File) filesList.get(i);
			if (aFile.isReadOnlyDirectory())
				allFiles.addAll(((ReadOnlyDirectory) aFile).getFilesRecursive());
			else
				allFiles.add(aFile);
		}
		return allFiles;
	}

	/**
	 * Return a filtered list on the files with just the instances of ReadOnlyDirectory
	 */
	@Override
	public List getReadOnlyDirectories() {
		List filtered = new ArrayList();
		List filesList = getFiles();
		for (int i = 0; i < filesList.size(); i++) {
			File aFile = (File) filesList.get(i);
			if (aFile.isReadOnlyDirectory())
				filtered.add(aFile);
		}
		return filtered;
	}

	/**
	 * isReadOnlyDirectory method comment.
	 */
	@Override
	public boolean isReadOnlyDirectory() {
		return true;
	}
}
