/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Dec 3, 2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactCreationDataModelOld;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactImportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetOperation;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public abstract class J2EEArtifactImportOperation extends WTPOperation {
	public J2EEArtifactImportOperation(J2EEArtifactImportDataModel model) {
		super(model);
	}

	protected final void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		try {
			doExecute(monitor);
		} finally {
			operationDataModel.dispose();
		}
	}

	protected void doExecute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEArtifactImportDataModel model = (J2EEArtifactImportDataModel) operationDataModel;
		monitor.beginTask(null, model.getModuleFile().getFiles().size());
		if (model.getBooleanProperty(J2EEArtifactImportDataModel.OVERWRITE_PROJECT) && model.getBooleanProperty(J2EEArtifactImportDataModel.DELETE_BEFORE_OVERWRITE_PROJECT)) {
			IProject project = model.getProject();
			if (project.exists()) {
				project.delete(true, true, new NullProgressMonitor());
			}
		}
		if (!model.getJ2eeArtifactCreationDataModel().getTargetProject().exists()) {
			createModuleProject(model.getJ2eeArtifactCreationDataModel(), monitor);
		}
		if (model.getBooleanProperty(J2EEArtifactImportDataModel.BINARY)) {
			BinaryProjectHelper binaryHelper = new BinaryProjectHelper();
			binaryHelper.importArchiveAsBinary(model.getModuleFile(), model.getProject(), monitor);
		} else {
			importModuleFile(monitor);
		}

	}

	protected void addServerTarget(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEArtifactCreationDataModelOld projModel = ((J2EEArtifactImportDataModel) operationDataModel).getJ2eeArtifactCreationDataModel();
		ServerTargetDataModel servModel = projModel.getServerTargetDataModel();
		ServerTargetOperation serverTargetOperation = new ServerTargetOperation(servModel);
		serverTargetOperation.doRun(monitor);
	}

	protected abstract void createModuleProject(J2EEArtifactCreationDataModelOld model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException;

	/**
	 * Creates the appropriate save strategy. Subclases should overwrite this method to create the
	 * appropriate save startegy for the kind of J2EE module project to import the archive
	 */
	protected abstract SaveStrategy createSaveStrategy(IProject project);

	protected void modifyStrategy(SaveStrategy saveStrat) {
	}

	/**
	 * perform the archive import operation
	 * 
	 * @throws java.lang.reflect.InvocationTargetException
	 * @throws java.lang.InterruptedException
	 */
	protected void importModuleFile(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		J2EEArtifactImportDataModel model = (J2EEArtifactImportDataModel) operationDataModel;
		ModuleFile moduleFile = ((J2EEArtifactImportDataModel) operationDataModel).getModuleFile();
		try {
			monitor.worked(1);
			if (model.isSet(J2EEArtifactImportDataModel.SAVE_FILTER)) {
				moduleFile.setSaveFilter((SaveFilter) model.getProperty(J2EEArtifactImportDataModel.SAVE_FILTER));
			}
			J2EESaveStrategyImpl aStrategy = (J2EESaveStrategyImpl) createSaveStrategy(model.getJ2eeArtifactCreationDataModel().getTargetProject());
			aStrategy.setProgressMonitor(monitor);
			aStrategy.setIncludeProjectMetaFiles(model.getBooleanProperty(J2EEArtifactImportDataModel.PRESERVE_PROJECT_METADATA));
			aStrategy.setShouldIncludeImportedClasses(!model.getBooleanProperty(J2EEArtifactImportDataModel.PRESERVE_PROJECT_METADATA));
			aStrategy.setIsBinary(model.getBooleanProperty(J2EEArtifactImportDataModel.PRESERVE_PROJECT_METADATA));
			aStrategy.setOverwriteHandler((IOverwriteHandler) model.getProperty(J2EEArtifactImportDataModel.OVERWRITE_HANDLER));
			modifyStrategy(aStrategy);
			moduleFile.save(aStrategy);
			if (model.getBooleanProperty(J2EEArtifactImportDataModel.PRESERVE_PROJECT_METADATA)) {
				JavaProjectUtilities.forceClasspathReload(model.getJ2eeArtifactCreationDataModel().getTargetProject());
			}
		} catch (OverwriteHandlerException oe) {
			throw new InterruptedException();
		} catch (Exception ex) {
			throw new WFTWrappedException(ex, EJBArchiveOpsResourceHandler.getString("ERROR_IMPORTING_MODULE_FILE")); //$NON-NLS-1$
		}
	}

	protected static void addToClasspath(J2EEArtifactImportDataModel importModel, List extraEntries) throws JavaModelException {
		if (extraEntries.size() > 0) {
			IJavaProject javaProject = JavaCore.create(importModel.getProject());
			IClasspathEntry[] javaClasspath = javaProject.getRawClasspath();
			List nonDuplicateList = new ArrayList();
			for (int i = 0; i < extraEntries.size(); i++) {
				IClasspathEntry extraEntry = (IClasspathEntry) extraEntries.get(i);
				boolean include = true;
				for (int j = 0; include && j < javaClasspath.length; j++) {
					if (extraEntry.equals(javaClasspath[j])) {
						include = false;
					}
				}
				if (include) {
					nonDuplicateList.add(extraEntry);
				}
			}
			if (nonDuplicateList.size() > 0) {
				IClasspathEntry[] newJavaClasspath = new IClasspathEntry[javaClasspath.length + nonDuplicateList.size()];
				System.arraycopy(javaClasspath, 0, newJavaClasspath, 0, javaClasspath.length);
				for (int j = 0; j < nonDuplicateList.size(); j++) {
					newJavaClasspath[javaClasspath.length + j] = (IClasspathEntry) nonDuplicateList.get(j);
				}
				javaProject.setRawClasspath(newJavaClasspath, new NullProgressMonitor());
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#dispose(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void dispose(IProgressMonitor pm) {
		try {
			getOperationDataModel().dispose();
			super.dispose(pm);
		} catch (RuntimeException re) {
		}
	}
}