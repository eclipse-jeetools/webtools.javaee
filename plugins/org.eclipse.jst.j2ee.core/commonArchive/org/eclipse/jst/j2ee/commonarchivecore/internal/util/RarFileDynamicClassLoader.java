/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;

import java.security.ProtectionDomain;
import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;

public class RarFileDynamicClassLoader extends ArchiveFileDynamicClassLoader {

    public RarFileDynamicClassLoader(Archive anArchive, ClassLoader parentCl, ClassLoader extraCl, ProtectionDomain pDomain) {
        super(anArchive, parentCl, extraCl, pDomain);
    }
    
	public RarFileDynamicClassLoader(Archive anArchive, ClassLoader parentCl, ClassLoader extraCl) {
		super(anArchive, parentCl, extraCl);
	}

	@Override
	protected File getFile(String name) {
		List children = getRarFile().getArchiveFiles();
		for (int i = 0; i < children.size(); i++) {
			try {
				return ((Archive) children.get(i)).getFile(name);
			} catch (java.io.FileNotFoundException ex) {
				continue;
			}
		}
		return getFileFromDependentJar(name);
	}

	private RARFile getRarFile() {
		return (RARFile) getArchive();
	}
}
