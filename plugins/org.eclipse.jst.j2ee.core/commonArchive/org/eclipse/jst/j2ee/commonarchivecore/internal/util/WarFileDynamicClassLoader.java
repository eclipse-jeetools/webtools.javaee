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
 * Created on Jun 23, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;

import java.io.InputStream;
import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WarFileDynamicClassLoader extends ArchiveFileDynamicClassLoader {

	public WarFileDynamicClassLoader(Archive anArchive, ClassLoader parentCl, ClassLoader extraCl) {
		super(anArchive, parentCl, extraCl);
	}

	protected byte[] getClassBytesFor(String className) {

		String jarEntryName = ArchiveUtil.classNameToUri(className);
		String swizzledName = ArchiveUtil.concatUri(ArchiveConstants.WEBAPP_CLASSES_URI, jarEntryName, '/');

		try {
			InputStream in = getWarFile().getInputStream(swizzledName);
			return ArchiveUtil.inputStreamToBytes(in);
		} catch (java.io.FileNotFoundException ex) {
			//Ignore
		} catch (java.io.IOException ex) {
			throw new ArchiveRuntimeException(CommonArchiveResourceHandler.getString("io_ex_loading_EXC_", (new Object[]{className})), ex); //$NON-NLS-1$ = "An IO exception occurred loading "
		}

		List children = getWarFile().getLibs();
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
		return super.getClassBytesFor(className);
	}

	private WARFile getWarFile() {
		return (WARFile) getArchive();
	}
}
