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
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;


public class FileIteratorImpl implements FileIterator {
	protected List files;
	protected int position = 0;

	/**
	 * Insert the method's description here. Creation date: (05/02/01 6:16:52 PM)
	 */
	public FileIteratorImpl() {
	}

	public FileIteratorImpl(List theFiles) {
		super();
		files = theFiles;
	}

	public InputStream getInputStream(File aFile) throws java.io.IOException, java.io.FileNotFoundException {
		return aFile.getInputStream();
	}

	public boolean hasNext() {
		return position < files.size();
	}

	public File next() {
		if (!hasNext())
			throw new NoSuchElementException(CommonArchiveResourceHandler.getString("End_of_list_reached_EXC_")); //$NON-NLS-1$ = "End of list reached"
		return (File) files.get(position++);
	}
}
