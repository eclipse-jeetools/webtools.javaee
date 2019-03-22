/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfigRegister;


/**
 * Load strategy used for reading expanding archives; typically in a running server this would be an
 * EAR or WAR, although it can be any Archive
 */
public class DirectoryArchiveLoadStrategyImpl extends DirectoryLoadStrategyImpl {



	public DirectoryArchiveLoadStrategyImpl(String aDirectoryUri) {
		super(aDirectoryUri);
	}

	@Override
	protected void addDirectory(java.io.File aFile, java.util.List aList) {
		addFiles(aFile, aList);
	}

	@Override
	protected java.io.File getDirectoryForList() {
		return new java.io.File(getDirectoryUri());
	}



	@Override
	public LooseArchive getLooseArchive() {
		if (looseArchive == null && getContainer().isEARFile()) {
			String osString = ArchiveUtil.getOSUri(getContainer().getURI());
			setLooseArchive(LooseConfigRegister.singleton().findLooseApplication(osString));
		}
		return looseArchive;
	}

}
