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
package org.eclipse.jst.j2ee.internal.project;


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;

/**
 * This is a special wrapper class that cleans up after itself and closes the zip file when it is
 * closed
 */
public class ZipFileEntryInputStream extends FilterInputStream {
	protected ZipFile owner;

	/**
	 * Constructor for ZipFileEntryInputStream.
	 * 
	 * @param in
	 */
	public ZipFileEntryInputStream(InputStream in, ZipFile owningZipFile) {
		super(in);
		owner = owningZipFile;
	}


	/*
	 * @see InputStream#close()
	 */
	public void close() throws IOException {
		super.close();
		owner.close();
	}

}