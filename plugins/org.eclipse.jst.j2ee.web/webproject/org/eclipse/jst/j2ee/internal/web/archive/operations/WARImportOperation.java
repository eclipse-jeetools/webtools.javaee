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
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.application.operations.J2EEImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEUtilityJarImportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.BinaryProjectHelper;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEImportOperation;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntimeUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.WebSettingsMigrator;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.web.internal.operation.LibModule;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WARImportOperation extends J2EEImportOperation {
	/**
	 * @param model
	 */
	public WARImportOperation(WARImportDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEImportOperationNEW#createModuleProject(org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEProjectCreationDataModel,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void createModuleProject(J2EEProjectCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		WebProjectCreationOperation op = new WebProjectCreationOperation((WebProjectCreationDataModel) model);
		op.run(monitor);
	}

	protected void modifyStrategy(SaveStrategy saveStrat) {
		WTProjectSaveStrategyImpl strategy = (WTProjectSaveStrategyImpl) saveStrat;
		if (null != strategy.getOverwriteHandler()) {
			strategy.getOverwriteHandler().setWarSaveStrategy(strategy);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.archive.j2ee.operations.J2EEImportOperationNEW#createSaveStrategy(org.eclipse.core.resources.IProject)
	 */
	protected SaveStrategy createSaveStrategy(IProject project) {
		WTProjectSaveStrategyImpl saveStrat = new WTProjectSaveStrategyImpl(project);
		saveStrat.setDataModel((WARImportDataModel) operationDataModel);
		return saveStrat;
	}

	protected void doExecute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.doExecute(monitor);
		WARImportDataModel model = (WARImportDataModel) operationDataModel;
		J2EEWebNatureRuntime nature = J2EEWebNatureRuntimeUtilities.getJ2EERuntime(model.getProject());
		if (!model.getBooleanProperty(J2EEImportDataModel.PRESERVE_PROJECT_METADATA)) {
			IFolder folder = model.getProject().getFolder(nature.getLibraryFolder().getFullPath().removeFirstSegments(1));
			if (!folder.exists()) {
				folder.create(true, true, new NullProgressMonitor());
			}
		}

		addExtraClasspathEntries(monitor, model);
		IProject project = model.getProject();
		if (model.getBooleanProperty(J2EEImportDataModel.PRESERVE_PROJECT_METADATA)) {
			BinaryProjectHelper.removeImportedClassesFromClasspathIfNecessary(project);
		}
		//J2EEWebNatureRuntime webNature = J2EEWebNatureRuntime.getRuntime(project);
		//webNature.getWebSettings().write();
		//project.getFile(webNature.getWebSettingsPath()).refreshLocal(0, monitor);
		WebSettingsMigrator migrator = new WebSettingsMigrator();
		migrator.migrate(project);
		if (!model.getJ2eeProjectCreationDataModel().getBooleanProperty(J2EEProjectCreationDataModel.ADD_SERVER_TARGET))
			addServerTarget(monitor);
	}

	private void addExtraClasspathEntries(IProgressMonitor monitor, WARImportDataModel model) throws InvocationTargetException, InterruptedException, CoreException, JavaModelException {
		boolean preserveMetadata = model.getBooleanProperty(J2EEImportDataModel.PRESERVE_PROJECT_METADATA);
		List extraEntries = null;
		IJavaProject javaProject = JavaCore.create(model.getProject());
		if (!preserveMetadata)
			extraEntries = new ArrayList();
		importWebLibraryProjects(monitor, extraEntries, javaProject);

		if (!preserveMetadata) {
			IResource[] libs = J2EEWebNatureRuntimeUtilities.getJ2EERuntime(model.getProject()).getLibraryFolder().members();
			for (int i = 0; i < libs.length; i++) {
				if (!javaProject.isOnClasspath(libs[i]))
					extraEntries.add(JavaCore.newLibraryEntry(libs[i].getFullPath(), libs[i].getFullPath(), null));
			}
			addToClasspath(model, extraEntries);
		}
	}

	private void importWebLibraryProjects(IProgressMonitor monitor, List extraEntries, IJavaProject javaProject) throws InvocationTargetException, InterruptedException {
		boolean preserveMetadata = operationDataModel.getBooleanProperty(J2EEImportDataModel.PRESERVE_PROJECT_METADATA);
		List libProjects = (List) operationDataModel.getProperty(WARImportDataModel.HANDLED_ARCHIVES);
		J2EEUtilityJarImportDataModel importModel = null;
		WTPOperation importOperation = null;
		ArrayList libModules = new ArrayList();
		for (int i = 0; null != libProjects && i < libProjects.size(); i++) {
			importModel = (J2EEUtilityJarImportDataModel) libProjects.get(i);
			libModules.add(new LibModule(importModel.getArchiveFile().getName(), importModel.getProject().getName()));
			importOperation = importModel.getDefaultOperation();
			importOperation.run(monitor);
			if (extraEntries != null) {
				if (!javaProject.isOnClasspath(importModel.getProject())) {
					if (preserveMetadata) {
						extraEntries.add(JavaCore.newLibraryEntry(importModel.getProject().getFullPath(), importModel.getProject().getFullPath(), null));
					} else {
						extraEntries.add(JavaCore.newProjectEntry(importModel.getProject().getFullPath()));
					}
				}
			}
		}
		LibModule[] libModulesArray = new LibModule[libModules.size()];
		for (int i = 0; i < libModules.size(); i++) {
			libModulesArray[i] = (LibModule) libModules.get(i);
		}
		J2EEWebNatureRuntime webNature = (J2EEWebNatureRuntime) J2EENature.getRegisteredRuntime(((WARImportDataModel) operationDataModel).getProject());
		try {
			webNature.setLibModules(libModulesArray);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		}
	}
}