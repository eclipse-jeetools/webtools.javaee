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
/*
 * Created on Jun 23, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;

import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WarFileDynamicClassLoader extends ArchiveFileDynamicClassLoader {

	private boolean allowLoadingFromWAR = true;

	public WarFileDynamicClassLoader(Archive anArchive, ClassLoader parentCl, ClassLoader extraCl) {
		super(anArchive, parentCl, extraCl);
		allowLoadingFromWAR = anArchive.getOptions().getClassLoadingMode() == ArchiveOptions.LOAD_MODE_COMPAT;
	}

	protected File getFile(String name) {
		//search classes directory first, then war, then nested archives.
		//search classes directory
		String swizzledName = ArchiveUtil.concatUri(ArchiveConstants.WEBAPP_CLASSES_URI, name, '/');
		try {
			return getWarFile().getFile(swizzledName);
		} catch (java.io.FileNotFoundException ex) {
		}
		//search war if running with compatibility
		if (allowLoadingFromWAR) {
			File file = getFileFromArchive(name);
			if (file != null) {
				return file;
			}
		}
		//search nested archives
		List children = getWarFile().getLibs();
		for (int i = 0; i < children.size(); i++) {
			try {
				return ((Archive) children.get(i)).getFile(name);
			} catch (java.io.FileNotFoundException ex) {
				continue;
			}
		}
		//finally search jars in ear
		return getFileFromDependentJar(name);
	}

	private WARFile getWarFile() {
		return (WARFile) getArchive();
	}
}
