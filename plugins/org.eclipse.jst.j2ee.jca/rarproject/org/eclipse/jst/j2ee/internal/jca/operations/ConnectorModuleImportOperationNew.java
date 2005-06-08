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
/*
 * Created on Dec 5, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.jca.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.ConnectorComponentSaveStrategyImpl;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperationNew;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorModuleImportOperationNew extends J2EEArtifactImportOperationNew {

	private static final String JAR_EXTENSION = ".jar"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public ConnectorModuleImportOperationNew(IDataModel model) {
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
		// List classPathEntries = new ArrayList();
		// List rarArchive = ((ConnectorModuleImportDataModel)
		// getOperationDataModel()).getModuleFile().getArchiveFiles();
		// Iterator archiveList = rarArchive.iterator();
		// while (archiveList.hasNext()) {
		// Archive anArchive = (Archive) archiveList.next();
		// if (anArchive != null) {
		// if (anArchive.getName().endsWith(JAR_EXTENSION)) {
		// ConnectorNatureRuntime connectorNature =
		// ConnectorNatureRuntime.getRuntime(((ConnectorModuleImportDataModel)
		// getOperationDataModel()).getProject());
		// IFile file = connectorNature.getModuleRoot().getFile(new Path(anArchive.getURI()));
		// IPath path = file.getFullPath();
		// IClasspathEntry newEntry = JavaCore.newLibraryEntry(path, null, null, true);
		// classPathEntries.add(newEntry);
		// }
		// }
		// }
		// if (!classPathEntries.isEmpty()) {
		// try {
		// JavaProjectUtilities.appendJavaClassPath(((ConnectorModuleImportDataModel)
		// getOperationDataModel()).getProject(), classPathEntries);
		// } catch (JavaModelException jme) {
		// //Ignore
		// }
		// }
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent virtualComponent) {
		ConnectorComponentSaveStrategyImpl saveStrat = new ConnectorComponentSaveStrategyImpl(virtualComponent);
		return saveStrat;

	}
}