/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore;



import java.util.List;

/**
 * @generated
 */
public interface ReadOnlyDirectory extends Container {

	public boolean containsFileInSelfOrSubdirectory(String uri);

	public File getFileInSelfOrSubdirectory(String uri) throws java.io.FileNotFoundException;

	/**
	 * Returns a flat list of all the files contained in this directory and subdirectories, with the
	 * directories filtered out, as the list would appear in an archive
	 */
	public List getFilesRecursive();

	/**
	 * Return a filtered list on the files with just the instances of ReadOnlyDirectory
	 */
	public List getReadOnlyDirectories();
}
