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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactCreationDataModelOld;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
import org.eclipse.jst.j2ee.internal.jca.archive.operations.RARProjectSaveStrategyImpl;

public class ConnectorModuleImportOperation extends J2EEArtifactImportOperation {

	private static final String JAR_EXTENSION = ".jar"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public ConnectorModuleImportOperation(ConnectorModuleImportDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEImportOperationNEW#createModuleProject(org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEProjectCreationDataModel,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void createModuleProject(J2EEArtifactCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		ConnectorModuleCreationOperation op = new ConnectorModuleCreationOperation((ConnectorModuleCreationDataModel) model);
		op.run(monitor);
	}

	protected void modifyStrategy(SaveStrategy saveStrat) {
		RARProjectSaveStrategyImpl strategy = (RARProjectSaveStrategyImpl) saveStrat;
		if (null != strategy.getOverwriteHandler()) {
			strategy.getOverwriteHandler().setRARSaveStrategy(strategy);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEImportOperationNEW#createSaveStrategy(org.eclipse.core.resources.IProject)
	 */
	protected SaveStrategy createSaveStrategy(IProject project) {
		RARProjectSaveStrategyImpl saveStrat = new RARProjectSaveStrategyImpl(project);
		return saveStrat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEImportOperation#doExecute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void doExecute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.doExecute(monitor);
		addAssociateArchivesToClassPath();
	}

	/**
	 * Adds all jar within the file to the classpath.
	 */
	protected void addAssociateArchivesToClassPath() {
		List classPathEntries = new ArrayList();
		List rarArchive = ((ConnectorModuleImportDataModel) getOperationDataModel()).getModuleFile().getArchiveFiles();
		Iterator archiveList = rarArchive.iterator();
		while (archiveList.hasNext()) {
			Archive anArchive = (Archive) archiveList.next();
			if (anArchive != null) {
				if (anArchive.getName().endsWith(JAR_EXTENSION)) {
					ConnectorNatureRuntime connectorNature = ConnectorNatureRuntime.getRuntime(((ConnectorModuleImportDataModel) getOperationDataModel()).getProject());
					IFile file = connectorNature.getModuleRoot().getFile(new Path(anArchive.getURI()));
					IPath path = file.getFullPath();
					IClasspathEntry newEntry = JavaCore.newLibraryEntry(path, null, null, true);
					classPathEntries.add(newEntry);
				}
			}
		}
		if (!classPathEntries.isEmpty()) {
			try {
				JavaProjectUtilities.appendJavaClassPath(((ConnectorModuleImportDataModel) getOperationDataModel()).getProject(), classPathEntries);
			} catch (JavaModelException jme) {
				//Ignore
			}
		}
	}
}