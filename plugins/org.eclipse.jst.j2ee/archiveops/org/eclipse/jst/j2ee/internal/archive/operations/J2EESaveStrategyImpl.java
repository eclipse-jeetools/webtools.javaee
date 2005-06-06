/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverterImpl;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipStreamSaveStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;

//TODO delete jsholl
/**
 * @deprecated
 *
 */
public abstract class J2EESaveStrategyImpl extends SaveStrategyImpl implements IJ2EEImportExportConstants {
	protected WorkbenchURIConverter sourceURIConverter;
	// This one is used for the imported_classes
	protected WorkbenchURIConverter importedClassesURIConverter;
	protected IProject project;
	protected IOverwriteHandler overwriteHandler;
	protected IProgressMonitor progressMonitor;
	// protected boolean includeProjectMetaFiles = false;
	protected Map importedClassFiles;
	// protected boolean shouldIncludeImportedClasses;
	protected List mofResourceURIList;

	public J2EESaveStrategyImpl(IProject p) {
		super();
		project = p;
	}

	protected SaveStrategy createNestedSaveStrategy(Archive anArchive) throws java.io.IOException {
		try {
			OutputStream out = getSourceURIConverter().createOutputStream(URI.createURI(anArchive.getURI()));
			return new ZipStreamSaveStrategyImpl(out);
		} catch (Exception ex) {
			throw new IOException(ex.getLocalizedMessage());
		}
	}

	public boolean endsWithClassType(String aFileName) {
		if (aFileName.endsWith(".class")) //$NON-NLS-1$
			return true;
		return false;
	}

	protected boolean shouldSaveClass(String aFileName) {
		return importedClassFiles != null && importedClassFiles.containsKey(aFileName);
	}

	protected OutputStream getOutputStreamForResource(Resource aResource) throws java.io.IOException {
		// this method has no references in the hirarchy
		return null;
	}

	public IOverwriteHandler getOverwriteHandler() {
		return overwriteHandler;
	}

	public IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	public IProject getProject() {
		return project;
	}

	public abstract WorkbenchURIConverter getSourceURIConverter();

	public void save(ArchiveManifest aManifest) throws SaveFailureException {
		try {
			J2EENature nature = J2EENature.getRegisteredRuntime(project);
			if (nature == null)
				throw new SaveFailureException(EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveManifest")); //$NON-NLS-1$
			WorkbenchURIConverter conv = (WorkbenchURIConverter) nature.getResourceSet().getURIConverter();
			IFile iFile = conv.getOutputFileWithMappingApplied(ArchiveConstants.MANIFEST_URI);
			validateEdit(iFile);
			OutputStream out = new WorkbenchByteArrayOutputStream(iFile);
			aManifest.write(out);
			out.close();
		} catch (IOException ioe) {
			throw new SaveFailureException(EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveManifest"), ioe); //$NON-NLS-1$
		}
	}

	public void save(File aFile, java.io.InputStream in) throws SaveFailureException {
		try {
			String displayString = EJBArchiveOpsResourceHandler.getString("IMPORT_OPERATION_STRING"); //$NON-NLS-1$
			progressMonitor.subTask(displayString + aFile.getURI());
			WorkbenchURIConverter conv = null;
			if (endsWithClassType(aFile.getURI()))
				conv = importedClassesURIConverter;
			else
				conv = getSourceURIConverter();

			IFile iFile = conv.getOutputFileWithMappingApplied(aFile.getURI());
			validateEdit(iFile);
			mkdirs(iFile.getFullPath(), ResourcesPlugin.getWorkspace().getRoot());
			if (iFile.exists())
				iFile.setContents(in, true, true, null);
			else
				iFile.create(in, true, null);
			// OutputStream out = new WorkbenchByteArrayOutputStream(iFile);
			// ArchiveUtil.copy(in, out);
			// worked(1);
		} catch (OverwriteHandlerException ohe) {
			throw ohe;
		} catch (Exception e) {
			String errorString = EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveFile") + aFile.getName(); //$NON-NLS-1$
			throw new SaveFailureException(errorString, e);
		}

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

	protected void validateEdit(IFile aFile) {
		if (overwriteHandler == null)
			return;
		if (!(aFile.exists() && aFile.isReadOnly()))
			return;

		overwriteHandler.validateEdit(aFile);
	}

	public void saveMofResource(Resource aResource) throws SaveFailureException {
		setEncoding(aResource);
		try {
			String displayString = EJBArchiveOpsResourceHandler.getString("IMPORT_MOFRESOURCE_STRING"); //$NON-NLS-1$
			progressMonitor.subTask(displayString);
			IFile iFile = getSourceURIConverter().getOutputFileWithMappingApplied(aResource.getURI().toString());
			validateEdit(iFile);
			OutputStream out = new WorkbenchByteArrayOutputStream(iFile);
			aResource.save(out, Collections.EMPTY_MAP);
			out.close();
			worked(1);
		} catch (OverwriteHandlerException ohe) {
			throw ohe;
		} catch (Exception e) {
			String errorString = EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveMofResources") + aResource.getURI().toString(); //$NON-NLS-1$
			throw new SaveFailureException(errorString, e);
		}
	}

	public void setOverwriteHandler(IOverwriteHandler newOverwriteHandler) {
		overwriteHandler = newOverwriteHandler;
	}

	public void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	public void setProject(IProject newProject) {
		project = newProject;
	}

	protected boolean shouldOverwrite(String uri) {
		if (overwriteHandler.isOverwriteNone())
			return false;
		return (overwriteHandler.isOverwriteResources() || overwriteHandler.isOverwriteAll() || overwriteHandler.shouldOverwrite(uri));
	}

	protected boolean shouldSave(String uri) {
		if (endsWithClassType(uri) && !shouldSaveClass(uri))
			return false;
		if (ArchiveConstants.MANIFEST_URI.equals(uri) && !shouldReplaceManifest())
			return false;

		if (overwriteHandler != null) {
			return (super.shouldSave(uri) && shouldOverwrite(uri));
		}
		return super.shouldSave(uri);
	}

	/**
	 * @return
	 */
	private boolean shouldReplaceManifest() {
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(getProject());
		if (mf == null)
			return true;
		String cp = mf.getClassPath();
		return cp != null && cp.trim().length() <= 0;
	}

	/**
	 * Set the number of units worked with the ProgressMonitor.
	 */
	public void worked(int units) {
		if (getProgressMonitor() != null)
			getProgressMonitor().worked(units);
	}


	public IFile getSaveFile(String aURI) {
		return sourceURIConverter.getFile(aURI);
	}

	/**
	 * Import class files into the project.
	 */
	protected void setImportedClassFilesIfNecessary() throws SaveFailureException {
		Map classFiles = getClassFilesWithoutSource();

		if (classFiles == null || classFiles.isEmpty())
			return;

		importedClassFiles = classFiles;
		/*
		 * Add the imported classes folder to the build path.
		 */
		IFolder classesFolder = getProject().getFolder(LibCopyBuilder.IMPORTED_CLASSES_PATH);
		try {
			if (!classesFolder.exists()) {
				classesFolder.create(true, true, new NullProgressMonitor());
			}

			JemProjectUtilities.appendJavaClassPath(getProject(), JavaCore.newLibraryEntry(classesFolder.getFullPath(), null, null, true));
			// In case this is a Java project, or a non-migrated 4.0 project,
			// add the builder
			// that's a no-op if the builder is already added
			ProjectUtilities.addToBuildSpec(J2EEPlugin.LIBCOPY_BUILDER_ID, getProject());
		} catch (CoreException ex) {
			throw new SaveFailureException(ex);
		}
		importedClassesURIConverter = new WorkbenchURIConverterImpl(classesFolder);
		importedClassesURIConverter.setForceSaveRelative(true);
	}

	protected Map getClassFilesWithoutSource() {
		List files = archive.getFiles();
		Map result = new HashMap();
		for (int i = 0; i < files.size(); i++) {
			File aFile = (File) files.get(i);
			if (isClassWithoutSource(aFile))
				result.put(aFile.getURI(), aFile);
		}
		return result;
	}

	protected boolean isClassWithoutSource(File aFile) {
		String javaUri = ArchiveUtil.classUriToJavaUri(aFile.getURI());
		if (javaUri == null)
			return false;
		return !archive.containsFile(javaUri);
	}

	protected void saveFiles() throws SaveFailureException {
		setImportedClassFilesIfNecessary();
		super.saveFiles();
	}

}