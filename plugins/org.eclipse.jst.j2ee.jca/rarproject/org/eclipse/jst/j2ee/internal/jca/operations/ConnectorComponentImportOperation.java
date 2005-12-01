/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.ConnectorComponentSaveStrategyImpl;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorComponentImportOperation extends J2EEArtifactImportOperation {

	private static final String JAR_EXTENSION = ".jar"; //$NON-NLS-1$

	public ConnectorComponentImportOperation(IDataModel model) {
		super(model);
	}

	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		addAssociateArchivesToClassPath();
	}

	/**
	 * Adds all jar within the file to the classpath.
	 */
	protected void addAssociateArchivesToClassPath() {
		List extraEntries = new ArrayList();
		List archiveList = moduleFile.getArchiveFiles();
		Iterator iterator = archiveList.iterator();
		IVirtualFile vFile = null;
		IFile file = null;
		while (iterator.hasNext()) {
			Archive anArchive = (Archive) iterator.next();
			if (anArchive.getName().endsWith(JAR_EXTENSION)) {
				vFile = virtualComponent.getRootFolder().getFile(anArchive.getURI());
				if (vFile.exists()) {
					file = vFile.getUnderlyingFile();
					extraEntries.add(JavaCore.newLibraryEntry(file.getFullPath(), file.getFullPath(), null, true));
				}
			}
		}
		try {
			addToClasspath(getDataModel(), extraEntries);
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent component) {
		ConnectorComponentSaveStrategyImpl saveStrat = new ConnectorComponentSaveStrategyImpl(component);
		return saveStrat;

	}
}
