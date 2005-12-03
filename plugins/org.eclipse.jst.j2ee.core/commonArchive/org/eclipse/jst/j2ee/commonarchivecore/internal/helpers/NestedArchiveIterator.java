/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;


public class NestedArchiveIterator extends FileIteratorImpl {
	protected ZipInputStream zipInputStream;
	protected ZipEntry currentEntry;

	static class WrapperInputStream extends FilterInputStream {
		/**
		 * @param in
		 */
		public WrapperInputStream(InputStream in) {
			super(in);
			// TODO Auto-generated constructor stub
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.FilterInputStream#close()
		 */
		public void close() throws IOException {
			//do nothing because we want to prevent the clients from closing the zip
		}
	}

	/**
	 * NestedArchiveIterator constructor comment.
	 */
	public NestedArchiveIterator(List theFiles, ZipInputStream stream) {
		super(theFiles);
		zipInputStream = stream;
	}

	public InputStream getInputStream(File aFile) throws java.io.IOException, java.io.FileNotFoundException {
		if (!aFile.getURI().equals(currentEntry.getName()))
			throw new java.io.IOException(CommonArchiveResourceHandler.Internal_Error__Iterator_o_EXC_); // = "Internal Error: Iterator out of sync with zip entries"
		return new WrapperInputStream(zipInputStream);
	}

	public File next() {
		File next = super.next();
		try {
			do {
				currentEntry = zipInputStream.getNextEntry();
			} while (currentEntry.isDirectory());
		} catch (java.io.IOException ex) {
			throw new ArchiveRuntimeException(CommonArchiveResourceHandler.Error_iterating_the_archiv_EXC_, ex); // = "Error iterating the archive"
		}
		return next;
	}


}
