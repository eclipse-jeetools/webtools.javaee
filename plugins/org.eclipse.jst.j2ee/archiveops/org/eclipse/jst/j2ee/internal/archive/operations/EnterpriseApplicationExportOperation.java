/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.IWorkspace.ProjectOrder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationExportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactExportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.internal.moduleextension.WebModuleExtension;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;


public final class EnterpriseApplicationExportOperation extends WTPOperation {
	protected IProgressMonitor monitor;
	protected IProject project;
	protected IPath destinationPath;
	protected EARFile earFile;
	protected boolean exportSource = false;
	protected boolean includeProjectMetaFiles = false;
	/**
	 * flag which indicates whether nested projects should be incrementally built as part of export
	 */
	protected boolean buildIncremental = true;

	/**
	 * @param operationDataModel
	 */
	public EnterpriseApplicationExportOperation(EnterpriseApplicationExportDataModel model) {
		super(model);
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(model.getStringProperty(J2EEArtifactExportDataModel.PROJECT_NAME));
		destinationPath = new Path((model.getStringProperty(J2EEArtifactExportDataModel.ARCHIVE_DESTINATION)));
		exportSource = model.getBooleanProperty(J2EEArtifactExportDataModel.EXPORT_SOURCE_FILES);
		includeProjectMetaFiles = model.getBooleanProperty(EnterpriseApplicationExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES);
	}

	public void createEARFile() throws OpenFailureException {

		CommonarchiveFactory caf = CommonarchiveFactory.eINSTANCE;
		/*
		 * ((CommonarchivePackage)
		 * EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
		 */
		EARProjectLoadStrategyImpl ls = new EARProjectLoadStrategyImpl(project);
		ls.setExportSource(exportSource);
		ls.setIncludeProjectMetaFiles(includeProjectMetaFiles);
		earFile = caf.openEARFile(ls, destinationPath.toOSString());

	}

	protected void execute(IProgressMonitor localMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		try {
			setProgressMonitor(localMonitor);
			startProgressMonitor();
			buildProjectsIfNecessary(localMonitor);
			exportEARProject();
		} catch (SaveFailureException e) {
			String errorString = EARArchiveOpsResourceHandler.getString("ERROR_EXPORTING_EAR_FILE"); //$NON-NLS-1$
			throw new WFTWrappedException(e, errorString);
		} finally {
			if (earFile != null)
				earFile.close();
			localMonitor.done();
		}
	}

	protected void buildProjectsIfNecessary(IProgressMonitor localMonitor) throws CoreException {
		if (!buildIncremental || !operationDataModel.getBooleanProperty(J2EEArtifactExportDataModel.RUN_BUILD)) {
			return;
		}
		EAREditModel earEditModel = null;
		SubProgressMonitor subMonitor = new SubProgressMonitor(localMonitor, 1);
		try {
			EARNatureRuntime nature = EARNatureRuntime.getRuntime(project);
			earEditModel = nature.getEarEditModelForRead(this);
			Set projectsToBuild = new HashSet();
			Set mappedProjects = earEditModel.getModuleMappedProjects();
			addAccessibleProjects(projectsToBuild, mappedProjects);

			Iterator iter = mappedProjects.iterator();
			while (iter.hasNext()) {
				IProject aProject = (IProject) iter.next();
				if (J2EENature.hasRuntime(aProject, IWebNatureConstants.J2EE_NATURE_ID)) {
					WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
					webExt.addWLPProjects(aProject, projectsToBuild);
				}
			}

			List inOrderProjects = getProjectsInOrder(projectsToBuild);
			subMonitor.beginTask("", inOrderProjects.size()); //$NON-NLS-1$
			for (int i = 0; i < inOrderProjects.size(); i++) {
				IProject moduleProject = (IProject) inOrderProjects.get(i);
				if (moduleProject.isAccessible()) {
					// reflesh. Defect 240999
					moduleProject.refreshLocal(IResource.DEPTH_INFINITE, null);
					javac(moduleProject, subMonitor);
				}
			}
		} finally {
			earEditModel.releaseAccess(this);
			subMonitor.done();
		}
	}


	private static ICommand getLibCopyBuilder(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(LibCopyBuilder.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;

	}

	/**
	 * Find the specific Java command amongst the build spec of a given description
	 */
	private static ICommand getJavaCommand(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}

		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(JavaCore.BUILDER_ID)) {
				return commands[i];
			}
		}
		return null;
	}

	public static void javac(IProject project, IProgressMonitor monitor) throws CoreException {
		IProjectDescription description = project.getDescription();
		ICommand javaBuilder = getJavaCommand(description);
		if (javaBuilder != null) {
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, JavaCore.BUILDER_ID, javaBuilder.getArguments(), monitor);
		}
		ICommand libCopyBuilder = getLibCopyBuilder(description);
		if (null != libCopyBuilder) {
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, LibCopyBuilder.BUILDER_ID, libCopyBuilder.getArguments(), monitor);
		}
	}


	private void addAccessibleProjects(Set projectsToBuild, Set mappedProjects) {
		Iterator iter = mappedProjects.iterator();
		while (iter.hasNext()) {
			IProject p = (IProject) iter.next();
			if (p.isAccessible())
				projectsToBuild.add(p);
		}
	}

	private List getProjectsInOrder(Set projectsToBuild) {
		List result = new ArrayList();
		IProject[] projects = (IProject[]) projectsToBuild.toArray(new IProject[projectsToBuild.size()]);
		ProjectOrder projectOrder = ResourcesPlugin.getWorkspace().computeProjectOrder(projects);
		result.addAll(Arrays.asList(projectOrder.projects));
		if (projectOrder.hasCycles) {
			for (int i = 0; i < projectOrder.knots.length; i++) {
				result.addAll(Arrays.asList(projectOrder.knots[i]));
			}
		}
		return result;
	}

	public void exportEARProject() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {

		try {
			worked(1);
			createEARFile();
			worked(1);
			earFile.saveAsNoReopen(destinationPath.toOSString());
			worked(1);
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			String errorString = EARArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_OpeningArchive"); //$NON-NLS-1$
			throw new WFTWrappedException(e, errorString); //$NON-NLS-1$
		} finally {
		}

	}

	public void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		monitor = newProgressMonitor;
	}

	private IProgressMonitor getProgressMonitor() {
		return monitor;
	}

	protected void startProgressMonitor() {
		if (getProgressMonitor() != null) {
			getProgressMonitor().beginTask(null, 4);
		}
	}

	public void worked(int units) {
		if (getProgressMonitor() != null)
			getProgressMonitor().worked(units);
	}
}