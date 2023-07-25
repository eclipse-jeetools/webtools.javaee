/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.io.File;

import org.eclipse.jst.j2ee.commonarchivecore.internal.util.DeleteOnExitUtility;


/**
 * Used as an optimization for reading jars within jars. Reading from a ZipFile is much faster than
 * from a ZipInputStream, therefore, when possible, it makes sense to copy the nested jar to a temp
 * file. It is the responsibility of the caller to ensure that the temp file gets deleted on normal
 * system exit, using the helper method on java.io.File. This adapter will also delete the file
 * when closed or finalized.
 */
public class TempZipFileArchiveLoadAdapterImpl extends ZipFileArchiveLoadAdapterImpl {

	protected File file;
	
	public TempZipFileArchiveLoadAdapterImpl() {
		super();
	}

	public TempZipFileArchiveLoadAdapterImpl(java.io.File file) throws java.io.IOException {
		super(ArchiveUtil.newZipFile(file));
		this.file = file;
	}
	
	public File getFile(){
		return file;
	}
	
	@Override
	public void close() {
		super.close();
		File file = getFile();
		file.delete();
		DeleteOnExitUtility.fileHasBeenDeleted(file);
	}
	
}
