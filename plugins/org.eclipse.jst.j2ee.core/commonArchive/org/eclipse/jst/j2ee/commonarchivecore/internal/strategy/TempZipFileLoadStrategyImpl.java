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
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.DeleteOnExitUtility;


/**
 * Used as an optimization for reading jars within jars. Reading from a ZipFile is much faster than
 * from a ZipInputStream, therefore, when possible, it makes sense to copy the nested jar to a temp
 * file. It is the responsibility of the caller to ensure that the temp file gets deleted on normal
 * system exit, using the helper method on java.io.File. This strategy will also delete the file
 * when closed or finalized.
 */
public class TempZipFileLoadStrategyImpl extends ZipFileLoadStrategyImpl {
	/**
	 * TemporaryZipFileLoadStrategyImpl constructor comment.
	 */
	public TempZipFileLoadStrategyImpl() {
		super();
	}

	/**
	 * TemporaryZipFileLoadStrategyImpl constructor comment.
	 * 
	 * @param file
	 *            java.io.File
	 * @throws java.io.IOException
	 *             The exception description.
	 */
	public TempZipFileLoadStrategyImpl(java.io.File file) throws java.io.IOException {
		super(file);
	}

	public void close() {
		super.close();
		File file = getFile();
		file.delete();
		DeleteOnExitUtility.fileHasBeenDeleted(file);
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public java.lang.String getAbsolutePath() throws java.io.FileNotFoundException {
		throw new FileNotFoundException(CommonArchiveResourceHandler.Absolute_path_unknown_EXC_); // = "Absolute path unknown"
	}
}
