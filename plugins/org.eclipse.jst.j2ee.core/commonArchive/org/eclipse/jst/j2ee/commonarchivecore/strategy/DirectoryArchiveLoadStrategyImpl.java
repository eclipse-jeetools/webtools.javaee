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
package org.eclipse.jst.j2ee.commonarchivecore.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseConfigRegister;
import org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil;


/**
 * Load strategy used for reading expanding archives; typically in a running server this would be an
 * EAR or WAR, although it can be any Archive
 */
public class DirectoryArchiveLoadStrategyImpl extends DirectoryLoadStrategyImpl {



	public DirectoryArchiveLoadStrategyImpl(String aDirectoryUri) {
		super(aDirectoryUri);
	}

	protected void addDirectory(java.io.File aFile, java.util.List aList) {
		addFiles(aFile, aList);
	}

	protected java.io.File getDirectoryForList() {
		return new java.io.File(getDirectoryUri());
	}



	public LooseArchive getLooseArchive() {
		if (looseArchive == null && getContainer().isEARFile()) {
			String osString = ArchiveUtil.getOSUri(getContainer().getURI());
			setLooseArchive(LooseConfigRegister.singleton().findLooseApplication(osString));
		}
		return looseArchive;
	}

}
