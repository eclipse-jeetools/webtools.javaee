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
/*
 * Created on Jun 2, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.commonarchivecore.util;

import java.io.InputStream;
import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.exception.ArchiveRuntimeException;


public class RarFileDynamicClassLoader extends ArchiveFileDynamicClassLoader {


	public RarFileDynamicClassLoader(Archive anArchive, ClassLoader parentCl, ClassLoader extraCl) {
		super(anArchive, parentCl, extraCl);
	}

	protected byte[] getClassBytesFor(String className) {

		if (className == null)
			return null;
		// Change the class name to a jar entry name
		List children = getRarFile().getArchiveFiles();
		String jarEntryName = ArchiveUtil.classNameToUri(className);
		for (int i = 0; i < children.size(); i++) {
			try {
				InputStream in = ((Archive) children.get(i)).getInputStream(jarEntryName);
				return ArchiveUtil.inputStreamToBytes(in);
			} catch (java.io.FileNotFoundException ex) {
				continue;
			} catch (java.io.IOException ex) {
				throw new ArchiveRuntimeException(CommonArchiveResourceHandler.getString("io_ex_loading_EXC_", (new Object[]{className})), ex); //$NON-NLS-1$ = "An IO exception occurred loading "
			}
		}
		return null;
	}

	private RARFile getRarFile() {
		return (RARFile) getArchive();
	}
}
