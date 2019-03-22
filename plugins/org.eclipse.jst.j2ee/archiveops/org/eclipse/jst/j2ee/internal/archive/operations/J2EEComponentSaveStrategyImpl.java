/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * @deprecated this will be deleted post WTP 3.2.  This code is no longer
 * called because the IArchive API is used to handle imports.
 * 
 * @author jsholl
 */
public abstract class J2EEComponentSaveStrategyImpl extends ComponentSaveStrategyImpl {

	protected final String DOT_CLASS = ".class"; //$NON-NLS-1$
	protected final String IMPORTED_CLASSES = "ImportedClasses";  //$NON-NLS-1$
	
	public J2EEComponentSaveStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	@Override
	protected void saveFiles() throws SaveFailureException {
		super.saveFiles();
		linkImportedClassesFolderIfNecessary();
		try {
			vComponent.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException ex) {
			J2EEPlugin.logError(ex);
		}
	}

	public boolean endsWithClassType(String aFileName) {
		if (aFileName.endsWith(DOT_CLASS))
			return true;
		return false;
	}

	@Override
	protected boolean shouldSave(File aFile) {
		if (endsWithClassType(aFile.getURI())){
			boolean shouldSave = isClassWithoutSource(aFile);
			if(shouldSave && !importedClassesFolderCreated){
				createImportedClassesFolder();
			}
			return shouldSave;
		}
		return super.shouldSave(aFile);
	}

	@Override
	public void save(ArchiveManifest aManifest) throws SaveFailureException {
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		IVirtualFile vFile = rootFolder.getFile(new Path(J2EEConstants.MANIFEST_URI));
		IFile iFile = vFile.getUnderlyingFile();
		validateEdit(iFile);
		OutputStream out = new WorkbenchByteArrayOutputStream(iFile);
		try {
			aManifest.write(out);
		} catch (IOException e) {
			J2EEPlugin.logError(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				J2EEPlugin.logError(e);
			}
		}
	}

	protected boolean importedClassesFolderCreated = false;
	protected IFolder importedClassesFolder;

	protected void createImportedClassesFolder() {
		importedClassesFolderCreated = true;
		IContainer jarParent = vComponent.getRootFolder().getUnderlyingFolder().getParent();
		importedClassesFolder = jarParent.getFolder(new Path(IMPORTED_CLASSES));
		try {
			importedClassesFolder.create(true, true, null);
		} catch (CoreException e1) {
			J2EEPlugin.logError(e1);
		}
	}
	
	protected void linkImportedClassesFolderIfNecessary() {
		if (importedClassesFolder != null) {
			try {
				vComponent.getRootFolder().getFolder(getImportedClassesRuntimePath()).createLink(importedClassesFolder.getProjectRelativePath(), 0, null);
				if (shouldAddImportedClassesToClasspath()) {
					if (JemProjectUtilities.getJavaProject(vComponent.getProject()) != null) {
						IJavaProject javaProject = JavaCore.create(vComponent.getProject());
						IClasspathEntry[] javaClasspath = javaProject.getRawClasspath();
						IClasspathEntry[] newJavaClasspath = new IClasspathEntry[javaClasspath.length + 1];
						System.arraycopy(javaClasspath, 0, newJavaClasspath, 0, javaClasspath.length);
						newJavaClasspath[newJavaClasspath.length - 1] = JavaCore.newLibraryEntry(importedClassesFolder.getFullPath(), null, null, true);
						javaProject.setRawClasspath(newJavaClasspath, new NullProgressMonitor());
					}
				}
			} catch (CoreException e) {
				J2EEPlugin.logError(e);
			}
		}
	}

	protected boolean shouldAddImportedClassesToClasspath() {
		return true;
	}

	protected IPath getImportedClassesRuntimePath() {
		return new Path("/"); //$NON-NLS-1$
	}

	@Override
	protected IPath getOutputPathForFile(File aFile) {
		if(endsWithClassType(aFile.getURI())){
			return importedClassesFolder.getFile(getImportedClassesURI(aFile)).getProjectRelativePath();
		}
		return super.getOutputPathForFile(aFile);
	}
	
	protected String getImportedClassesURI(File aFile) {
		return aFile.getURI();
	}

	protected boolean isClassWithoutSource(File aFile) {
		String javaUri = ArchiveUtil.classUriToJavaUri(aFile.getURI());
		if (javaUri == null)
			return false;
		return !archive.containsFile(javaUri);
	}
}
