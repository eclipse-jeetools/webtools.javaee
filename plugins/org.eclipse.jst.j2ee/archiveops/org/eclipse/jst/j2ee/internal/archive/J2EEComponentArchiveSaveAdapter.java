/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

import com.ibm.icu.util.StringTokenizer;

public abstract class J2EEComponentArchiveSaveAdapter extends ComponentArchiveSaveAdapter {

	protected final String DOT_CLASS = ".class"; //$NON-NLS-1$

	protected final String DOT_JAVA = ".java"; //$NON-NLS-1$

	protected final String IMPORTED_CLASSES = "ImportedClasses"; //$NON-NLS-1$

	public J2EEComponentArchiveSaveAdapter(IVirtualComponent vComponent) {
		super(vComponent);
	}

	@Override
	public void save(IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int SUPER_TICKS = 1000;
		final int LOCAL_TICKS = 10;
		final int REFRESH_TICKS = 100;
		final int TOTAL_TICKS = SUPER_TICKS + LOCAL_TICKS + REFRESH_TICKS;

		try {
			monitor.beginTask(NLS.bind(ArchiveMessages.ComponentArchiveSaveAdapter_Importing_0_, vComponent.getName()), TOTAL_TICKS);
			super.save(new SubProgressMonitor(monitor, SUPER_TICKS));
			linkImportedClassesFolderIfNecessary();
			monitor.worked(LOCAL_TICKS);
			try {
				vComponent.getProject().refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(monitor, REFRESH_TICKS));
			} catch (CoreException ex) {
				J2EEPlugin.logError(ex);
			}
		} finally {
			monitor.done();
		}
	}

	public boolean endsWithClassType(String aFileName) {

		if (aFileName != null && aFileName.endsWith(DOT_CLASS))
			return true;
		return false;
	}

	@Override
	protected boolean shouldSave(IArchiveResource aFile) {
		if (endsWithClassType(aFile.getPath().lastSegment())) {
			boolean shouldSave = isClassWithoutSource(aFile);
			if (shouldSave && !importedClassesFolderCreated) {
				createImportedClassesFolder();
			}
			return shouldSave;
		}
		return super.shouldSave(aFile);
	}

	/*
	 * public void save(ArchiveManifest aManifest) throws SaveFailureException {
	 * IVirtualFolder rootFolder = vComponent.getRootFolder(); IVirtualFile
	 * vFile = rootFolder.getFile(new Path(J2EEConstants.MANIFEST_URI)); IFile
	 * iFile = vFile.getUnderlyingFile(); validateEdit(iFile); OutputStream out =
	 * new WorkbenchByteArrayOutputStream(iFile); try { aManifest.write(out); }
	 * catch (IOException e) { J2EEPlugin.logError(e); } finally { try {
	 * out.close(); } catch (IOException e) { J2EEPlugin.logError(e); } } }
	 */
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
	protected IPath getProjectRelativePath(IArchiveResource aFile) {
		if (endsWithClassType(aFile.getPath().lastSegment())) {
			return importedClassesFolder.getFile(getImportedClassesURI(aFile)).getProjectRelativePath();
		}
		return super.getProjectRelativePath(aFile);
	}
	
	protected IPath getImportedClassesURI(IArchiveResource aFile) {
		return aFile.getPath();
	}

	protected boolean isClassWithoutSource(IArchiveResource aFile) {
		String javaUri = classUriToJavaUri(aFile.getPath().toString());
		if (javaUri == null)
			return false;
		return !archive.containsArchiveResource(new Path(javaUri));
	}

	public String classUriToJavaUri(String classUri) {
		if (classUri == null || !classUri.endsWith(DOT_CLASS))
			return null;

		String truncated = truncateIgnoreCase(classUri, DOT_CLASS);
		StringTokenizer tok = new StringTokenizer(truncated, "$"); //$NON-NLS-1$
		return tok.nextToken().concat(DOT_JAVA);
	}

	/**
	 * Return a substring of the first parameter, up to the last index of the
	 * second
	 */
	public static String truncateIgnoreCase(String aString, String trailingSubString) {
		int index = aString.toLowerCase().lastIndexOf(trailingSubString.toLowerCase());
		if (index != -1)
			return aString.substring(0, index);
		return aString;
	}

}
