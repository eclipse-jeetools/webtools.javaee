/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.jst.j2ee.internal.migration.actions.IJ2EEMigrationConstants;
import org.eclipse.jst.j2ee.internal.migration.actions.J2EEMigrationUIResourceHandler;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.plugin.LibCopyBuilder;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverterImpl;

public class J2EEProjectStructureMigrationOperation extends AbstractJ2EEMigrationOperation implements IJ2EEMigrationConstants {
	protected List projects;
	protected IProgressMonitor progressMonitor;
	protected boolean currentProjectMigrated = false;

	public J2EEProjectStructureMigrationOperation(J2EEMigrationConfig config, IOperationHandler handler) {
		super(config, handler);
		setCurrentProject(config.getTargetProject());
		if (config.isComposed())
			projects = ((ComposedMigrationConfig) config).getAllSelectedProjects();
		else
			projects = Collections.singletonList(config.getTargetProject());
	}

	/**
	 * @see org.eclipse.jst.j2ee.operations.HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		progressMonitor = monitor;
		monitor.beginTask(MIGRATING_PROJECT_STRUCTURES, projects.size());
		try {
			migrateProject(currentProject);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
			appendStatus(J2EEMigrationStatus.ERROR, e.toString());
			appendStatus(J2EEMigrationStatus.ERROR, format(PROJECT_STRUCTURE_FAILED, currentProject.getName()));
		} finally {
			monitor.done();
		}
	}

	protected void migrateProject(IProject project) throws CoreException, IOException {
		currentProjectMigrated = false;
		progressMonitor.subTask(project.getName());
		// if project is binary, migration is not possible
		if (ProjectUtilities.isBinaryProject(project)) {
			appendStatus(J2EEMigrationStatus.WARNING, format(BINARY_MIGRATION_FAILED, project.getName()));
			return;
		}
		J2EENature nature = J2EENature.getRegisteredRuntime(project);
		if (nature == null)
			return;
		switch (nature.getDeploymentDescriptorType()) {
			case XMLResource.EJB_TYPE :
				migrateEJBProject((EJBNatureRuntime) nature);
				break;
			case XMLResource.APP_CLIENT_TYPE :
				migrateAppClientProject((ApplicationClientNatureRuntime) nature);
				break;
			case XMLResource.WEB_APP_TYPE :
				migrateJ2EEWebProject((J2EEWebNatureRuntime) nature);
				break;
			case XMLResource.APPLICATION_TYPE :
				migrateEARProject((EARNatureRuntime) nature);
				break;
			case XMLResource.RAR_TYPE :
				migrateConnectorProject((ConnectorNatureRuntime) nature);
				break;
			default :
				break;
		}
		String projectName = project.getName();
		if (currentProjectMigrated)
			appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(PROJECT_STRUCTURE_SUCCESS, projectName));
		else
			appendStatus(J2EEMigrationStatus.NOT_NEEDED, format(PROJECT_NOT_NEED_MIGRATION, projectName));
	}

	/**
	 * @param runtime
	 */
	protected void migrateConnectorProject(ConnectorNatureRuntime rarNature) throws CoreException, IOException {
		migrateBasicJ2EEJavaProject(rarNature);
	}

	/**
	 * Applicable for EJB and AppClient projects
	 */
	protected void migrateBasicJ2EEJavaProject(J2EEModuleNature nature) throws CoreException, IOException {
		IContainer source = nature.getEMFRoot();
		IContainer output = ProjectUtilities.getJavaProjectOutputContainer(nature.getProject());
		String projectName = nature.getProject().getName();
		if (source != null && !source.equals(output)) {
			setOutputLocation(source, nature);
			output.delete(IResource.KEEP_HISTORY, new SubProgressMonitor(progressMonitor, 1));
			appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(PROJECT_CLASSPATH_UPDATED, projectName));
			currentProjectMigrated = true;
		}
		migrateImportedClassesIfNecessary(nature);
	}

	protected void migrateImportedClassesIfNecessary(J2EEModuleNature nature) throws CoreException, IOException {
		IProject project = nature.getProject();
		if (project == null)
			return;
		IResource[] members = new IResource[0];
		boolean isweb = false;
		if (nature instanceof J2EEWebNatureRuntime) {
			IContainer libDir = ((J2EEWebNatureRuntime) nature).getLibraryFolder();
			if (libDir != null && libDir.exists())
				members = libDir.members();
			isweb = true;
		} else
			members = project.members();
		for (int i = 0; i < members.length; i++) {
			IResource res = members[i];
			boolean migrate = false;
			if (isweb)
				migrate = isImportedClassesJARFileInLibDir(res);
			else
				migrate = isImportedClassesJARFile(res);
			if (migrate)
				migrateImportedClassesJARFile(res, nature);
		}
	}

	protected boolean isImportedClassesJARFile(IResource resource) {
		if (resource == null || !resource.exists())
			return false;
		return resource.getType() == IResource.FILE && resource.getName().endsWith(J2EEMigrationHelper.IMPORTED_JAR_SUFFIX) && isZip(resource);
	}

	protected boolean isImportedClassesJARFileInLibDir(IResource resource) {
		if (resource == null || !resource.exists())
			return false;
		return resource.getType() == IResource.FILE && resource.getName().endsWith(IWebNatureConstants.IMPORTED_CLASSES_SUFFIX) && isZip(resource);
	}

	protected boolean isLibDirJARFile(IResource resource) {
		if (resource == null || !resource.exists())
			return false;
		return resource.getType() == IResource.FILE && isZip(resource);
	}

	protected boolean isZip(IResource resource) {
		String path = resource.getLocation().toOSString();
		ZipFile zip = null;
		try {
			zip = new ZipFile(path);
		} catch (IOException notAZip) {
			return false;
		} finally {
			if (zip != null) {
				try {
					zip.close();
				} catch (IOException ex) {
				}
			}
		}
		return zip != null;
	}

	protected void migrateImportedClassesJARFile(IResource resource, J2EEModuleNature nature) throws CoreException, IOException {
		String uri = resource.getLocation().toOSString();
		Archive anArchive = null;
		try {
			anArchive = CommonarchiveFactoryImpl.getActiveFactory().primOpenArchive(uri);
		} catch (OpenFailureException exc) {
			appendStatus(J2EEMigrationStatus.ERROR, format(FAILED_MIGRATING_IMPORTED_CLASSES, exc.getConcatenatedMessages()));
			return;
		}
		List classes = getClassFilesWithoutSource(anArchive, getJavaProject(nature));
		if (!classes.isEmpty()) {
			saveImportedClasses(classes, nature);
			appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(EXTRACTED_IMPORTED_CLASSES, resource.getFullPath().toString()));
		}
		anArchive.close();
		resource.delete(true, new SubProgressMonitor(progressMonitor, 1));
		removeFromAllBuildPaths(resource);
		removeFromManifest(nature, resource);
		appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(IMPORTED_CLASSES_FILE_DELETED, resource.getFullPath().toString()));
		currentProjectMigrated = true;
	}

	protected List getClassFilesWithoutSource(Archive anArchive, IJavaProject javaProj) {
		List files = anArchive.getFiles();
		List result = new ArrayList();
		for (int i = 0; i < files.size(); i++) {
			File aFile = (File) files.get(i);
			if (isClassWithoutSource(aFile, javaProj))
				result.add(aFile);
		}
		return result;
	}

	protected boolean isClassWithoutSource(File aFile, IJavaProject javaProj) {
		String javaUri = ArchiveUtil.classUriToJavaUri(aFile.getURI());
		if (javaUri == null)
			return false;
		List sourceFolders = ProjectUtilities.getSourceContainers(javaProj.getProject());
		for (int i = 0; i < sourceFolders.size(); i++) {
			IContainer container = (IContainer) sourceFolders.get(i);
			IFile anIFile = container.getFile(new Path(javaUri));
			if (anIFile != null && anIFile.exists())
				return false;
		}
		return true;
	}

	protected void saveImportedClasses(List classFiles, J2EEModuleNature nature) throws CoreException, IOException {
		if (classFiles == null || classFiles.isEmpty())
			return;
		//Initialize the classes folder here.
		IFolder classesFolder = nature.getProject().getFolder(LibCopyBuilder.IMPORTED_CLASSES_PATH);
		WorkbenchURIConverterImpl conv = new WorkbenchURIConverterImpl(classesFolder);
		for (int i = 0; i < classFiles.size(); i++) {
			File classFile = (File) classFiles.get(i);
			// The following line was commented out because it doesn't
			// work in the V51 branch. This line came from the V5PTF
			// branch.
			//OutputStream out = conv.makeOutputStream(classFile.getURI());
			OutputStream out = conv.createOutputStream(URI.createURI(classFile.getURI()));
			ArchiveUtil.copy(classFile.getInputStream(), out);
			out.close();
		}
		ProjectUtilities.appendJavaClassPath(nature.getProject(), JavaCore.newLibraryEntry(classesFolder.getFullPath(), null, null, true));
		ProjectUtilities.addToBuildSpec(LibCopyBuilder.BUILDER_ID, nature.getProject());
	}

	protected void setOutputLocation(IContainer source, J2EEModuleNature nature) throws CoreException {
		IJavaProject javaProj = getJavaProject(nature);
		javaProj.setOutputLocation(source.getFullPath(), new SubProgressMonitor(progressMonitor, 1));
	}

	protected void removeFromAllBuildPaths(IResource res) throws JavaModelException {
		IProject[] localProjects = J2EEPlugin.getWorkspace().getRoot().getProjects();
		for (int i = 0; i < localProjects.length; i++) {
			ProjectUtilities.removeFromJavaClassPath(localProjects[i], res);
		}
	}

	protected void removeFromManifest(J2EENature nature, IResource res) throws IOException {
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(nature.getProject());
		if (mf == null)
			return;
		String importedClassesString = res.getProjectRelativePath().toString();
		String[] entries = mf.getClassPathTokenized();
		StringBuffer sb = new StringBuffer();
		boolean empty = true;
		for (int i = 0; i < entries.length; i++) {
			String entry = entries[i];
			if (entry.equals(importedClassesString))
				continue;
			if (!empty)
				sb.append(" "); //$NON-NLS-1$
			empty = false;
			sb.append(entries[i]);
		}
		mf.setClassPath(sb.toString());
		J2EEProjectUtilities.writeManifest(nature.getProject(), mf);
	}

	protected IJavaProject getJavaProject(J2EEModuleNature nature) throws CoreException {
		return (IJavaProject) nature.getProject().getNature(JavaCore.NATURE_ID);
	}

	protected void migrateAppClientProject(ApplicationClientNatureRuntime clientNature) throws CoreException, IOException {
		migrateBasicJ2EEJavaProject(clientNature);
	}

	protected void migrateEJBProject(EJBNatureRuntime ejbNature) throws CoreException, IOException {
		migrateBasicJ2EEJavaProject(ejbNature);
	}

	/**
	 * This method migrates the given Web Project nature from the old WSAD 4.x structure to the new
	 * structure if necessary. In addition, it renames the Special folders (Web content & Java
	 * Source) to the user-set defaults.
	 * 
	 * @param webNature
	 *            Project Nature to migrate
	 * @param monitor
	 *            Progress monitor indicating status
	 * @throws CoreException
	 * @throws IOException
	 */
	protected void migrateJ2EEWebProject(J2EEWebNatureRuntime webNature) throws CoreException, IOException {
		boolean sourceFolderMigrated = false;
		boolean contentFolderMigrated = false;
		boolean currentVersionMigrated = false;
		boolean extendedPreMigration = false;
		boolean extendedPostMigration = false;
		IContainer oldSourceFolder = null;
		IContainer newSourceFolder = null;
		Object[] ops = J2EEPlugin.getDefault().getJ2EEWebProjectMigrationExtensions();
		IProject project = webNature.getProject();
		// 1. migrate the xx_classes.jar libraries before renaming the
		// webApplication dir
		migrateImportedClassesIfNecessary(webNature);
		// 2. look for a single source folder
		oldSourceFolder = WebPropertiesUtil.getJavaSourceFolder(project);
		// 3. execute any preMigration extension operations
		extendedPreMigration = executePreMigrationExtensions(webNature, oldSourceFolder, ops);
		if (oldSourceFolder == null) {
			appendStatus(J2EEMigrationStatus.WARNING, SOURCE_FOLDER_RENAME_SKIPPED);
		} else {
			// 4. migrate the old source folder if necessary
			try {
				newSourceFolder = WebPropertiesUtil.updateJavaSourceName(project, oldSourceFolder, null, progressMonitor);
				sourceFolderMigrated = newSourceFolder != null;
				if (sourceFolderMigrated) {
					appendStatus(J2EEMigrationStatus.COMPLETED_OK, J2EEMigrationUIResourceHandler.getString("J2EEProjectStructureMigrationOperation_UI_0", new Object[]{project.getName(), oldSourceFolder.getName(), newSourceFolder.getName()})); //$NON-NLS-1$
				}
			} catch (CoreException e) {
				appendStatus(J2EEMigrationStatus.ERROR, J2EEMigrationUIResourceHandler.getString("J2EEProjectStructureMigrationOperation_UI_1", new Object[]{e.getMessage()})); //$NON-NLS-1$
			}
		}
		// 5. rename the web content folder to the current default, and if
		// successful
		//    migrate the version of the web project
		String oldWebContentName = webNature.getRootPublishableFolder().getName();
		try {
			contentFolderMigrated = WebPropertiesUtil.updateWebContentNameAndProperties(project, null, progressMonitor);
			if (contentFolderMigrated) {
				appendStatus(J2EEMigrationStatus.COMPLETED_OK, J2EEMigrationUIResourceHandler.getString("J2EEProjectStructureMigrationOperation_UI_0", new Object[]{project.getName(), oldWebContentName, webNature.getRootPublishableFolder().getName()})); //$NON-NLS-1$
			}
		} catch (CoreException e) {
			appendStatus(J2EEMigrationStatus.ERROR, J2EEMigrationUIResourceHandler.getString("J2EEProjectStructureMigrationOperation_UI_2", new Object[]{e.getMessage()})); //$NON-NLS-1$
		}

		currentVersionMigrated = WebPropertiesUtil.updateNatureToCurrentVersion(webNature);
		// 7. execute any postMigration extension operations ONLY
		//	  if the project has been migrated to the new version.
		if (currentVersionMigrated) {
			appendStatus(J2EEMigrationStatus.COMPLETED_OK, J2EEMigrationUIResourceHandler.getString("J2EEProjectStructureMigrationOperation_UI_4")); //$NON-NLS-1$
			extendedPostMigration = executePostMigrationExtensions(webNature, newSourceFolder, ops);
		}
		currentProjectMigrated = sourceFolderMigrated || contentFolderMigrated || extendedPreMigration || currentVersionMigrated || extendedPostMigration;
	}

	/**
	 * This will execute any post migration extension points.
	 * 
	 * @param webNature
	 *            The Web Nature we are working with
	 * @param ops
	 *            An array of migration operation extension points
	 * @param newSourceFolder
	 *            The new "Java Source" folder name, or null if it has been skipped
	 * @return True, if the migration executed and was successful
	 */
	protected boolean executePostMigrationExtensions(J2EEWebNatureRuntime webNature, IContainer newSourceFolder, Object[] ops) {
		boolean extendedPostMigration = false;
		if (ops != null && ops.length > 0) {
			for (int i = 0; i < ops.length; i++) {
				try {
					Method method = ops[i].getClass().getMethod("postMigrate", new Class[]{IContainer.class, IContainer.class}); //$NON-NLS-1$
					if (method != null) {
						Object localStatus = method.invoke(ops[i], new Object[]{newSourceFolder, webNature.getRootPublishableFolder()});
						if (localStatus != null && localStatus instanceof IStatus) {
							IStatus istatus = (IStatus) localStatus;
							//appendStatus(istatus.getSeverity(),
							// istatus.getMessage());
							if (istatus.getSeverity() == IStatus.OK)
								extendedPostMigration = true;
						}
					}
				} catch (Exception e) {
					// ignore this operation, keep going
					J2EEPlugin.getDefault().getLogger().write(e);
				}
			}
		}
		return extendedPostMigration;
	}

	/**
	 * This will execute any pre-migration extensions points.
	 * 
	 * @param oldSourceFolder
	 *            The original "Java Source" folder, or null if we are skipping it
	 * @param webNature
	 *            The Web Nature we are working with.
	 * @param ops
	 *            An array of migration operation extension points.
	 * @return True, if we executed an extension point and it was successful.
	 */
	protected boolean executePreMigrationExtensions(J2EEWebNatureRuntime webNature, IContainer oldSourceFolder, Object[] ops) {
		boolean extendedPreMigration = false;
		if (ops != null && ops.length > 0) {
			for (int i = 0; i < ops.length; i++) {
				try {
					Method method = ops[i].getClass().getMethod("preMigrate", new Class[]{IContainer.class, IContainer.class}); //$NON-NLS-1$
					if (method != null) {
						Object localStatus = method.invoke(ops[i], new Object[]{oldSourceFolder, webNature.getRootPublishableFolder()});
						if (localStatus != null && localStatus instanceof IStatus) {
							IStatus istatus = (IStatus) localStatus;
							// appendStatus(istatus.getSeverity(),
							// istatus.getMessage());
							if (istatus.getSeverity() == IStatus.OK)
								extendedPreMigration = true;
						}
					}
				} catch (Exception e) {
					// ignore this operation, keep going
					J2EEPlugin.getDefault().getLogger().write(e);
				}
			}
		}
		return extendedPreMigration;
	}

	protected void migrateEARProject(EARNatureRuntime runtime) {
		//TODO Operation to be performed by WAS Ext plugin to remove the absolute paths
		/*
		 * EAREditModel earEditModel = (EAREditModel) editModel; boolean referencesChanged = false;
		 * boolean absolutePathsRemoved = false; //TODO Operation to be performed by WAS Ext plugin
		 * //absolutePathsRemoved = runtime.resetAbsolutePathsIfNecessary(new //
		 * NullOperationHandler()); referencesChanged = earEditModel.hasProjectReferenceChanges();
		 * if (referencesChanged || absolutePathsRemoved) { currentProjectMigrated = true; } if
		 * (!currentProjectMigrated) return; String projectName = runtime.getProject().getName(); if
		 * (absolutePathsRemoved) appendStatus(J2EEMigrationStatus.COMPLETED_OK,
		 * format(ABS_PATHS_APP_EXT_REMOVED, projectName)); if (referencesChanged)
		 * appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(PROJECT_REFERENCES_UPDATED,
		 * projectName));
		 */
	}
}