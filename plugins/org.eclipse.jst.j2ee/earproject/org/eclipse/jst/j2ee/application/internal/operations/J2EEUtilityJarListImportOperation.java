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
 * Created on May 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class J2EEUtilityJarListImportOperation extends WTPOperation {

	/**
	 * @param operationDataModel
	 */
	public J2EEUtilityJarListImportOperation(WTPOperationDataModel operationDataModel) {
		super(operationDataModel);
	}

	/**
	 *  
	 */
	public J2EEUtilityJarListImportOperation() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		J2EEUtilityJarListImportDataModel model = (J2EEUtilityJarListImportDataModel) getOperationDataModel();
		Object[] utilityJars = (Object[]) model.getProperty(J2EEUtilityJarListImportDataModel.UTILITY_JAR_LIST);
		if (utilityJars == null || utilityJars.length == 0)
			return;

		monitor.beginTask(EARCreationResourceHandler.getString("J2EEUtilityJarListImportOperation_UI_0"), utilityJars.length); //$NON-NLS-1$

		String earProject = model.getStringProperty(J2EEUtilityJarListImportDataModel.EAR_PROJECT);
		boolean isBinary = model.getBooleanProperty(J2EEUtilityJarListImportDataModel.BINARY_IMPORT);
		// if model.getBooleanProperty(J2EEUtilityJarListImportDataModel.COPY) then isLinked =
		// createProject = false;
		boolean isLinked = (model.getBooleanProperty(J2EEUtilityJarListImportDataModel.LINK_IMPORT) || model.getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT));
		boolean createProject = (model.getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT) || model.getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT));
		;
		boolean overrideProjectRoot = model.getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT);
		String projectRoot = model.getStringProperty(J2EEUtilityJarListImportDataModel.PROJECT_ROOT);

		File utilityJar = null;
		J2EEUtilityJarImportDataModel importModel = null;
		IWorkspace root = ResourcesPlugin.getWorkspace();
		for (int i = 0; i < utilityJars.length; i++) {
			utilityJar = (File) utilityJars[i];
			monitor.subTask(EARCreationResourceHandler.getString("J2EEUtilityJarListImportOperation_UI_1") + utilityJar.getAbsolutePath()); //$NON-NLS-1$

			try {

				if (createProject) {

					if (!isLinked) {
						Archive archive = CommonarchiveFactory.eINSTANCE.primOpenArchive(utilityJar.getAbsolutePath());

						importModel = new J2EEUtilityJarImportDataModel();
						importModel.setBooleanProperty(J2EEUtilityJarImportDataModel.PRESERVE_PROJECT_METADATA, isBinary);
						importModel.setProperty(J2EEUtilityJarImportDataModel.FILE, archive);

						if (overrideProjectRoot && projectRoot != null && projectRoot.length() > 0)
							importModel.getJ2eeArtifactCreationDataModel().setProperty(J2EEArtifactCreationDataModelOld.PROJECT_LOCATION, projectRoot);

						importModel.getJ2eeArtifactCreationDataModel().setBooleanProperty(J2EEArtifactCreationDataModelOld.ADD_SERVER_TARGET, true);
						importModel.setBooleanProperty(J2EEArtifactImportDataModel.OVERWRITE_PROJECT, model.getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY));
						importModel.setProperty(J2EEUtilityJarImportDataModel.EAR_PROJECT, earProject);

						importModel.getDefaultOperation().run(new SubProgressMonitor(monitor, 1));
					} else {
						createProjectWithLinkedJar(utilityJar, new SubProgressMonitor(monitor, 1));
					}

					String utilityJarProjectName = (importModel != null) ? importModel.getStringProperty(J2EEArtifactImportDataModel.PROJECT_NAME) : getUtilityJarProjectName(utilityJar);

					IProject utilityJarProject = root.getRoot().getProject(utilityJarProjectName);
					linkArchiveToEAR(earProject, utilityJar.getName(), utilityJarProject, new SubProgressMonitor(monitor, 1));
					importModel = null;
				} else {

					IProject targetEARProject = root.getRoot().getProject(earProject);
					if (!isLinked)
						createCopiedArchive(targetEARProject, utilityJar.getName(), utilityJar, new SubProgressMonitor(monitor, 1));
					else
						createLinkedArchive(targetEARProject, utilityJar.getName(), utilityJar, new SubProgressMonitor(monitor, 1));

				}

			} catch (OpenFailureException e) {
				Logger.getLogger().logError(e);
			} catch (InvocationTargetException e) {
				Logger.getLogger().logError(e);
			} catch (InterruptedException e) {
				Logger.getLogger().logError(e);
			} catch (Exception e) {
				Logger.getLogger().logError(e);
			}

			monitor.worked(1);
		}
		monitor.done();
	}

	/**
	 * @param utilityJar
	 * @return
	 */
	private String getUtilityJarProjectName(File utilityJar) {
		String name = null;
		if (utilityJar != null) {
			int len = utilityJar.getName().indexOf('.');
			name = utilityJar.getName().substring(0, len);
		}
		return name;
	}

	protected IPath getLinkedPath(File archiveFile) throws CoreException {
		String linkedPathVariable = getOperationDataModel().getStringProperty(J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE);

		if (linkedPathVariable == null || linkedPathVariable.length() == 0)
			return new Path(archiveFile.getAbsolutePath());
		createLinkedPathVariableIfNecessary(linkedPathVariable, archiveFile.getParentFile());
		return new Path(linkedPathVariable).append(archiveFile.getName());
	}

	/**
	 * @param linkedPathVariable
	 * @param archiveFile
	 */
	protected void createLinkedPathVariableIfNecessary(String linkedPathVariable, File archiveFile) throws CoreException {
		IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
		IPath linkedPath = new Path(archiveFile.getAbsolutePath());
		manager.setValue(linkedPathVariable, linkedPath);

	}

	protected void createProjectWithLinkedJar(File jarFile, IProgressMonitor monitor) throws CoreException {
		try {
			boolean overrideProjectRoot = getOperationDataModel().getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT);
			String projectRoot = getOperationDataModel().getStringProperty(J2EEUtilityJarListImportDataModel.PROJECT_ROOT);

			IWorkspace root = ResourcesPlugin.getWorkspace();
			IProject project = root.getRoot().getProject(getUtilityJarProjectName(jarFile));
			IProjectDescription description = null;
			if (project.exists()) {
				if (!project.isOpen())
					return;
				description = project.getDescription();
				ProjectUtilities.addNatureToProject(project, JavaCore.NATURE_ID);
			} else {
				project.create(new SubProgressMonitor(monitor, 1));
				description = root.newProjectDescription(project.getName());
				description.setNatureIds(new String[]{JavaCore.NATURE_ID});
				if (overrideProjectRoot)
					description.setLocation(new Path(projectRoot));
				else
					description.setLocation(null);
				project.open(new SubProgressMonitor(monitor, 1));
				project.setDescription(description, new SubProgressMonitor(monitor, 1));
			}
			createLinkedArchive(project, jarFile.getName(), jarFile, monitor);

			JavaProjectUtilities.forceClasspathReload(project);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
			throw new CoreException(new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.getString("J2EEUtilityJarListImportOperation_UI_2"), e)); //$NON-NLS-1$
		}
	}

	protected void createLinkedArchive(IProject project, String linkedFileName, File archiveFile, IProgressMonitor monitor) throws Exception {
		IFile linkedJarFile = null;
		IPath pathToArchive = getLinkedPath(archiveFile);
		boolean overwriteIfNecessary = getOperationDataModel().getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY);
		linkedJarFile = project.getFile(linkedFileName);
		if (linkedJarFile.exists()) {
			if (overwriteIfNecessary)
				linkedJarFile.delete(true, true, new SubProgressMonitor(monitor, 1));
			else
				return;
		}
		linkedJarFile.createLink(pathToArchive, IResource.ALLOW_MISSING_LOCAL, new SubProgressMonitor(monitor, 1));

		if (project.hasNature(JavaCore.NATURE_ID)) {
			IClasspathEntry entry = new ClasspathEntry(IPackageFragmentRoot.K_BINARY, IClasspathEntry.CPE_LIBRARY, linkedJarFile.getFullPath(), ClasspathEntry.INCLUDE_ALL, ClasspathEntry.EXCLUDE_NONE, null, // source
						// attachment
						null, // source attachment root
						null, // custom output location
						false);

			JavaProjectUtilities.appendJavaClassPath(project, entry);
		}
	}

	protected void createCopiedArchive(IProject project, String jarFileName, File archiveFile, IProgressMonitor monitor) throws Exception {

		IPath pathToArchive = new Path(archiveFile.getAbsolutePath());

		boolean overwriteIfNecessary = getOperationDataModel().getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY);
		IFile copiedJarFile = project.getFile(jarFileName);
		if (copiedJarFile.exists()) {
			if (overwriteIfNecessary)
				copiedJarFile.delete(true, true, new SubProgressMonitor(monitor, 1));
			else
				return;
		}
		FileInputStream fileInputStream = null;
		ByteArrayOutputStream bos = null;
		ByteArrayInputStream jarFileInputStream = null;
		try {
			fileInputStream = new FileInputStream(pathToArchive.toOSString());
			bos = new ByteArrayOutputStream();
			byte[] data = new byte[4096];
			try {
				int bytesRead = 0;
				while ((bytesRead = fileInputStream.read(data)) > 0)
					bos.write(data, 0, bytesRead);
				// clear space for GC
				data = null;
			} finally {
				fileInputStream.close();
			}

			jarFileInputStream = new ByteArrayInputStream(bos.toByteArray());
			copiedJarFile.create(jarFileInputStream, 0, new SubProgressMonitor(monitor, 1));

			if (project.hasNature(JavaCore.NATURE_ID)) {
				IClasspathEntry entry = new ClasspathEntry(IPackageFragmentRoot.K_BINARY, IClasspathEntry.CPE_LIBRARY, copiedJarFile.getFullPath(), ClasspathEntry.INCLUDE_ALL, ClasspathEntry.EXCLUDE_NONE, null, // source
							// attachment
							null, // source attachment root
							null, // custom output location
							false);

				JavaProjectUtilities.appendJavaClassPath(project, entry);
			}
		} finally {
			if (bos != null)
				bos.close();
			if (jarFileInputStream != null)
				jarFileInputStream.close();
		}
	}

	public void linkArchiveToEAR(String earProjectName, String uriMapping, IProject utlityProject, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		AddUtilityProjectToEARDataModel addArchiveProjectToEARDataModel = AddUtilityProjectToEARDataModel.createAddToEARDataModel(earProjectName, utlityProject);
		addArchiveProjectToEARDataModel.setProperty(AddModuleToEARDataModel.ARCHIVE_URI, uriMapping);
		addArchiveProjectToEARDataModel.setBooleanProperty(AddArchiveToEARDataModel.SYNC_TARGET_RUNTIME, true);
		addArchiveProjectToEARDataModel.getDefaultOperation().run(monitor);
	}
}