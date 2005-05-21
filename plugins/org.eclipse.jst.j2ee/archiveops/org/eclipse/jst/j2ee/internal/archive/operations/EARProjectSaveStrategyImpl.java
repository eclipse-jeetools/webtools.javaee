/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.EnterpriseApplicationImportDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactImportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipStreamSaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;

public class EARProjectSaveStrategyImpl extends SaveStrategyImpl implements IJ2EEImportExportConstants {
	protected IProject project;
	protected URIConverter earURIConverter;
	protected EnterpriseApplicationImportDataModel dataModel;
	protected IOverwriteHandler overwriteHandler;
	protected IProgressMonitor monitor;
	protected boolean includeProjectMetaFiles = false;
	/**
	 * Used to store the actual project names used to create projects for the modules; needed for
	 * updating project class paths after all the projects have been saved
	 */
	protected Map createdProjectsMap;

	public EARProjectSaveStrategyImpl(EnterpriseApplicationImportDataModel model) {
		super();
		this.dataModel = model;
		project = dataModel.getProject();
		setArchive(model.getEARFile());
		includeProjectMetaFiles = model.getBooleanProperty(EnterpriseApplicationImportDataModel.PRESERVE_PROJECT_METADATA);
		overwriteHandler = (IOverwriteHandler) model.getProperty(EnterpriseApplicationImportDataModel.OVERWRITE_HANDLER);
		if (null != overwriteHandler) {
			overwriteHandler.setEarSaveStrategy(this);
		}
		buildProjectsMap();
	}

	/**
	 * Creates a Map mapping archive uris to projects for all archives in the ear that imported as
	 * projects.
	 */
	private void buildProjectsMap() {
		createdProjectsMap = new HashMap();
		List createdProjectsList = dataModel.getProjectModels();
		J2EEArtifactImportDataModel importDM = null;
		Archive anArchive = null;
		for (int i = 0; i < createdProjectsList.size(); i++) {
			importDM = (J2EEArtifactImportDataModel) createdProjectsList.get(i);
			anArchive = importDM.getArchiveFile();
			createdProjectsMap.put(anArchive.getURI(), importDM.getProject());
		}
	}

	protected void addFileToClasspath(IProject p, IFile file, List cp) {
		if (!file.exists())
			return;

		//Assume the file also contains the source
		IPath path = file.getFullPath();
		IClasspathEntry entry = JavaCore.newLibraryEntry(path, path, null, true);
		if (!cp.contains(entry))
			cp.add(entry);
	}

	protected void addProjectToClasspath(IProject dependent, IProject prereq, List cp) {
		IClasspathEntry entry = JavaCore.newProjectEntry(prereq.getFullPath(), true);
		if (!cp.contains(entry))
			cp.add(entry);
	}

	protected SaveStrategy createNestedSaveStrategy(Archive anArchive) throws java.io.IOException {
		try {
			if (earURIConverter == null)
				getEARURIConverter();
			OutputStream out = earURIConverter.createOutputStream(URI.createURI(anArchive.getURI()));
			return new ZipStreamSaveStrategyImpl(out);
		} catch (Exception ex) {
			throw new IOException(ex.getLocalizedMessage());
		}
	}

	protected EARFile getEARFile() {
		return (EARFile) getArchive();
	}

	public org.eclipse.emf.ecore.resource.URIConverter getEARURIConverter() {

		EARNatureRuntime ear = EARNatureRuntime.getRuntime(project);
		earURIConverter = ear.getResourceSet().getURIConverter();
		return earURIConverter;
	}

	protected java.io.OutputStream getOutputStreamForResource(org.eclipse.emf.ecore.resource.Resource aResource) throws java.io.IOException {
		return null;
	}

	public void save() throws SaveFailureException {

		saveFiles();
		saveManifest();
		saveMofResources();
		monitor.subTask(EARArchiveOpsResourceHandler.getString("Updating_project_classpath_UI_")); //$NON-NLS-1$ = "Updating project classpaths..."
		updateProjectClasspaths();

	}

	public void save(ArchiveManifest aManifest) throws SaveFailureException {
		try {
			EARNatureRuntime ear = EARNatureRuntime.getRuntime(project);
			if (ear == null)
				throw new SaveFailureException(EARArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_ProjectNature")); //$NON-NLS-1$
			URIConverter wuc = ear.getResourceSet().getURIConverter();
			OutputStream out = wuc.createOutputStream(URI.createURI(ArchiveConstants.MANIFEST_URI));
			aManifest.write(out);
			out.close();
		} catch (IOException ioe) {
			throw new SaveFailureException(EARArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveManifest")); //$NON-NLS-1$
		}
	}

	public void save(Archive anArchive) throws SaveFailureException {
		monitor.subTask(anArchive.getURI());
		saveArchiveAsJARInEAR(anArchive);
	}

	protected void saveArchiveAsJARInEAR(Archive anArchive) throws SaveFailureException {

		try {
			anArchive.save(createNestedSaveStrategy(anArchive));
			monitor.worked(1);
		} catch (IOException e) {
			throw new SaveFailureException(anArchive.getURI(), e);
		}
	}

	public void save(File aFile, java.io.InputStream in) throws SaveFailureException {
		monitor.subTask(aFile.getURI());
		try {
			if (earURIConverter == null)
				getEARURIConverter();
			IFile iFile = ((WorkbenchURIConverter) earURIConverter).getOutputFileWithMappingApplied(aFile.getURI());
			mkdirs(iFile.getFullPath(), ResourcesPlugin.getWorkspace().getRoot());
			if (iFile.exists())
				iFile.setContents(in, true, true, null);
			else
				iFile.create(in, true, null);
			//OutputStream out = earURIConverter.createOutputStream(URI.createURI(aFile.getURI()));
			//ArchiveUtil.copy(in, out);
		} catch (Exception iox) {
			throw new SaveFailureException(aFile.getURI(), iox);
		}
		monitor.worked(1);

	}

	protected void mkdirs(IPath newPath, IWorkspaceRoot root) throws CoreException {
		if (newPath.segmentCount() <= 2)
			return;
		IPath parentPath = newPath.removeLastSegments(1);
		IFolder folder = root.getFolder(parentPath);
		if (!folder.exists()) {
			mkdirs(parentPath, root);
			folder.create(true, true, null);
		}
	}

	protected SubProgressMonitor subMonitor() {
		return new SubProgressMonitor(monitor, 10);
	}

	public void saveMofResource(org.eclipse.emf.ecore.resource.Resource aResource) throws SaveFailureException {
		setEncoding(aResource);
		try {
			if (earURIConverter == null)
				getEARURIConverter();
			OutputStream out = null;
			out = earURIConverter.createOutputStream(aResource.getURI());
			aResource.save(out, new HashMap());
			out.close();

		} catch (Exception e) {
			String errorString = EARArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveMofResources"); //$NON-NLS-1$
			throw new SaveFailureException(errorString, e);
		}
	}

	public void setMonitor(org.eclipse.core.runtime.IProgressMonitor newMonitor) {
		monitor = newMonitor;
	}

	public void setProject(org.eclipse.core.resources.IProject newProject) {
		project = newProject;
	}

	protected boolean shouldSave(File aFile) {
		if (aFile.isArchive()) {
			if (dataModel.handlesArchive((Archive) aFile)) {
				return false;
			}
			return getFilter().shouldSave(aFile.getURI(), getArchive());
		}
		return super.shouldSave(aFile);
	}

	protected boolean isProjectMetaFile(String uri) {
		return PROJECT_FILE_URI.equals(uri) || EAREditModel.MODULE_MAP_URI.equals(uri);
	}

	protected boolean shouldSave(String uri) {
		if (isProjectMetaFile(uri))
			return includeProjectMetaFiles;
		if (shouldAlwaysExclude(uri)) {
			return false;
		}

		if (overwriteHandler != null) {
			if (overwriteHandler.isOverwriteNone())
				return false;
			return ((super.shouldSave(uri)) && (!overwriteHandler.isOverwriteResources()) && (!overwriteHandler.isOverwriteAll()) && (overwriteHandler.shouldOverwrite(uri)));
		}
		return true;
	}

	//TODO move up and use correct Strings
	private boolean shouldAlwaysExclude(String uri) {
		if (uri.equals(".runtime")) { //$NON-NLS-1$
			return true;
		}
		return false;
	}

	/*
	 * Parse the manifest of the module file; for each cp entry 1) cananonicalize to a uri that
	 * looks like the entry in the ear 2) If the ear contains a file with that uri (the entry is
	 * valid) a) If the file is another was blown out to a project, add a cp entry for a referenced
	 * project b) otherwise, add a cp entry that points to the file in the ear project, and cp
	 * entries for all prereqs
	 */
	protected void updateProjectClasspath(Archive anArchive, IProject p) {

		String message = EARArchiveOpsResourceHandler.getString("Updating_project_classpath_UI_") + p.getName(); //$NON-NLS-1$ = "Updating project classpaths..."
		monitor.subTask(message);
		List projectCpEntries = new ArrayList();
		Set visited = new HashSet();
		traverseClasspaths(p, anArchive, projectCpEntries, visited);

		try {
			if (!projectCpEntries.isEmpty())
				JemProjectUtilities.appendJavaClassPath(p, projectCpEntries);
			JemProjectUtilities.forceClasspathReload(p);
		} catch (JavaModelException ex) {
			org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ex);
		}

	}

	/*
	 * If you have a dependency to a JAR in the EAR project, and the JAR depends on another JAR in
	 * the EAR; you want to compile cleanly after import, so you need both those JARs on your build
	 * path
	 */
	protected void traverseClasspaths(IProject p, Archive anArchive, List projectCpEntries, Set visitedArchives) {

		visitedArchives.add(anArchive);
		String[] manifestCpEntries = anArchive.getManifest().getClassPathTokenized();
		for (int i = 0; i < manifestCpEntries.length; i++) {
			String uri = ArchiveUtil.deriveEARRelativeURI(manifestCpEntries[i], anArchive);
			//ensure the entry is valid or skip to the next
			if (uri == null)
				continue;
			File aFile = null;
			try {
				aFile = dataModel.getEARFile().getFile(uri);
			} catch (FileNotFoundException notThere) {
			}
			if (aFile == null || !aFile.isArchive() || visitedArchives.contains(aFile))
				continue;
			Archive depArchive = (Archive) aFile;

			IProject prereq = (IProject) createdProjectsMap.get(uri);

			if (prereq != null) {
				addProjectToClasspath(p, prereq, projectCpEntries);
			} else {
				addFileToClasspath(p, project.getFile(uri), projectCpEntries);
				traverseClasspaths(p, depArchive, projectCpEntries, visitedArchives);
			}
		}
	}

	protected void updateProjectClasspaths() {
		//We're preserving the original classpath
		if (includeProjectMetaFiles)
			return;
		List jarFiles = getEARFile().getArchiveFiles();
		for (int i = 0; i < jarFiles.size(); i++) {
			Archive anArchive = (Archive) jarFiles.get(i);
			IProject p = (IProject) createdProjectsMap.get(anArchive.getURI());
			if (p != null)
				updateProjectClasspath(anArchive, p);
		}
	}

	protected void ensureBinary(Archive anArchive, IProject p) {
		//TODO
		//        if (!getImportConfiguration().isBinary(anArchive))
		//            return;
		J2EEModuleNature nature = (J2EEModuleNature) J2EENature.getRegisteredRuntime(p);
		//Right now WARs are not optimized
		if (nature != null && !nature.canBeBinary())
			return;
		IJavaProject javaP = JemProjectUtilities.getJavaProject(p);
		if (javaP == null)
			return;
		List newCp = new ArrayList();
		try {
			IClasspathEntry[] entries = javaP.getRawClasspath();
			for (int i = 0; i < entries.length; i++) {
				IClasspathEntry entry = entries[i];
				if (entry.getEntryKind() != IClasspathEntry.CPE_SOURCE)
					newCp.add(entry);
			}
			entries = (IClasspathEntry[]) newCp.toArray(new IClasspathEntry[newCp.size()]);
			javaP.setRawClasspath(entries, new SubProgressMonitor(monitor, 1));
		} catch (JavaModelException ex) {
			Logger.getLogger().logError(ex);
		}
	}
}