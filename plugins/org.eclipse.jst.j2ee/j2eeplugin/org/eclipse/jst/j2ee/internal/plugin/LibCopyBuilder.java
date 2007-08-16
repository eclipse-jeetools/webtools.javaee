/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;

/**
 * An example incremental project builder that copies additional class files from a library package
 * fragment root folder into a Java project's output directory.
 * 
 * General parameters:
 * <ul>
 * <li>The project should be a Java project.</li>
 * <li>The class files are in the "imported_classes" folder of the project.</li>
 * <li>This builder should run <b>after </b> the Java builder.</li>
 * <li>Full build should copy class files from a secondary library folder into the output folder
 * maintaining package hierarchy; existing class files must never be overwritten.</li>
 * <li>Only *.class files should be copied (not other resource files).</li>
 * <li>Incremental build and auto-build should will perform the copy when there is an add/change in
 * the "imported_classes" folder.</li>
 * <li>Changing the project's output folder should be handled.</li>
 * </ul>
 * Note: the builder is not currently invoking the Minimize helper, it is relying on the copy to not
 * replace existing class files, and the build path order to ensure that compiled classes override
 * imported ones.
 * 
 * @deprecated This class is only used for backwards compatibility
 */
public class LibCopyBuilder extends IncrementalProjectBuilder {
	/**
	 * Internal debug tracing.
	 */
	static boolean DEBUG = false;

	/**
	 * Builder id of this incremental project builder.
	 */
	public static final String BUILDER_ID = J2EEPlugin.LIBCOPY_BUILDER_ID;

	/**
	 * The path where we expect to find the .class files to be copied.
	 */
	public static final String IMPORTED_CLASSES_PATH = "imported_classes"; //$NON-NLS-1$

	/**
	 * The path of the output folder that we last copied class files into, or <code>null</code> if
	 * this builder has not built this project before.
	 */
	private IPath lastOutputPath = null;

	private List sourceContainers;

	private boolean needOutputRefresh;

	/**
	 * Creates a new instance of the library copying builder.
	 * <p>
	 * All incremental project builders are required to have a public 0-argument constructor.
	 * </p>
	 */
	public LibCopyBuilder() {
		super();
	}


	/**
	 * 
	 * The <code>LibCopyBuilder</code> implementation of this
	 * <code>IncrementalProjectBuilder</code> method copies additional class files into the output
	 * folder.
	 * <p>
	 * [Issue: the implementation should report progress.]
	 * </p>
	 * <p>
	 * [Issue: the implementation should probably use a workspace runnable.]
	 * </p>
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
		// this builder is unnecessary in WTP 1.5
		return null;
//		sourceContainers = null;
//		needOutputRefresh = false;
//		if (DEBUG) {
//			System.out.println(BUILDER_ID + J2EEPluginResourceHandler.__Start_build_project_INFO_ + getProject().getName()); 
//		}
//
//		boolean builderOrderOK = checkBuilderOrdering();
//
//		if (DEBUG && !builderOrderOK) {
//			System.out.println(BUILDER_ID + J2EEPluginResourceHandler.__Bad_builder_order_for_project_INFO_ + getProject().getName()); 
//		}
//
//		IFolder[] classFolders = getClassesFolders();
//		if (classFolders.length == 0) {
//			// no files to copy
//			if (DEBUG)
//				System.out.println(BUILDER_ID + J2EEPluginResourceHandler.__No_imported_classes_folder__quitting_INFO_); 
//			return null;
//		}
//
//		IJavaProject jproject = JavaCore.create(getProject());
//		if (jproject == null) {
//			// not a java project (anymore?)
//			return null;
//		}
//
//		IPath outputPath = jproject.getOutputLocation();
//		IFolder outputFolder = getProject().getParent().getFolder(outputPath);
//		if (outputPath.equals(lastOutputPath)) {
//			if (kind == INCREMENTAL_BUILD || kind == AUTO_BUILD) {
//				processDelta(getDelta(getProject()), outputFolder, monitor, classFolders);
//				refreshOutputIfNecessary(outputFolder);
//				return null;
//			}
//		}
//
//		if (DEBUG) {
//			System.out.println(BUILDER_ID + J2EEPluginResourceHandler.__Full_first_build_INFO_);
//		}
//		copyAllClassFolders(monitor, classFolders, outputFolder);
//		lastOutputPath = outputPath;
//		refreshOutputIfNecessary(outputFolder);
//		return null;
	}

	/**
	 *  
	 */
	private void refreshOutputIfNecessary(IFolder outputFolder) throws CoreException {
		if (needOutputRefresh && outputFolder != null && outputFolder.exists())
			outputFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
	}

	private void copyAllClassFolders(IProgressMonitor monitor, IFolder[] classFolders, IFolder outputFolder) throws CoreException {
		for (int i = 0; i < classFolders.length; i++) {
			if (!classFolders[i].equals(outputFolder))
				copyClassFiles(classFolders[i], outputFolder, monitor);
		}

	}


	/**
	 * Process an incremental build delta.
	 * 
	 * @return <code>true</code> if the delta requires a copy
	 * @param dest
	 *            the destination folder; may or may not exist
	 * @param monitor
	 *            the progress monitor, or <code>null</code> if none
	 * @exception CoreException
	 *                if something goes wrong
	 */
	protected void processDelta(IResourceDelta delta, final IFolder outputFolder, final IProgressMonitor monitor, final IFolder[] classesFolders) {
		if (DEBUG) {
			System.out.println(BUILDER_ID + J2EEPluginResourceHandler.__Considering_delta_INFO_ + delta); 
		}
		IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
			private List copiedClassFolders = new ArrayList(classesFolders.length);

			public boolean visit(IResourceDelta subdelta) throws CoreException {
				IResource resource = subdelta.getResource();
				if (resource.getType() == IResource.FILE) {
					IFolder classesFolder = retrieveClassesFolder(resource, classesFolders);
					if (classesFolder != null && !copiedClassFolders.contains(classesFolder)) {
						int kind = subdelta.getKind();
						switch (kind) {
							case IResourceDelta.ADDED :
							case IResourceDelta.CHANGED :
								if (DEBUG) {
									System.out.println(BUILDER_ID + J2EEPluginResourceHandler.__Delta_build_INFO_ + subdelta); 
								}
								copyClassFiles(classesFolder, outputFolder, monitor);
								break;
							case IResourceDelta.REMOVED :
								deleteCorrespondingFile((IFile) resource, classesFolder, outputFolder, monitor);
								break;
							case IResourceDelta.ADDED_PHANTOM :
								break;
							case IResourceDelta.REMOVED_PHANTOM :
								break;
						}

					}
				} else if (resource.getType() == IResource.FOLDER && resource.equals(outputFolder)) {
					copyAllClassFolders(null, classesFolders, outputFolder);
					return false;
				}
				return true;
			}
		};
		if (delta != null) {
			try {
				delta.accept(visitor);
			} catch (CoreException e) {
				// should not happen
			}
		}
	}

	/**
	 * @param file
	 * @param classesFolder
	 * @param outputFolder
	 * @param monitor
	 */
	protected void deleteCorrespondingFile(IFile file, IFolder classesFolder, IFolder outputFolder, IProgressMonitor monitor) throws CoreException {
		IPath path = file.getFullPath();
		int segCount = classesFolder.getFullPath().segmentCount();
		path = path.removeFirstSegments(segCount);
		IFile javaFile = findCorrespondingJavaFile(path);
		if (javaFile != null && javaFile.exists())
			return; //There is nothing to do because the file in the output location is from the
		// java compilation not the copy.
		IFile outFile = outputFolder.getFile(path);
		if (outFile.exists())
			outFile.delete(true, false, monitor);
	}


	/**
	 * Method retrieveClassesFolder.
	 * 
	 * @param resource
	 * @return IFolder
	 */
	protected IFolder retrieveClassesFolder(IResource resource, IFolder[] classesFolders) {
		for (int i = 0; i < classesFolders.length; i++) {
			if (classesFolders[i].getName().equals(resource.getProjectRelativePath().segment(0)))
				return classesFolders[i];
		}
		return null;
	}


	/**
	 * Checks whether this builder is configured to run <b>after </b> the Java builder.
	 * 
	 * @return <code>true</code> if the builder order is correct, and <code>false</code>
	 *         otherwise
	 * @exception CoreException
	 *                if something goes wrong
	 */
	private boolean checkBuilderOrdering() throws CoreException {
		// determine relative builder position from project's buildspec
		ICommand[] cs = getProject().getDescription().getBuildSpec();
		int myIndex = -1;
		int javaBuilderIndex = -1;
		for (int i = 0; i < cs.length; i++) {
			if (cs[i].getBuilderName().equals(JavaCore.BUILDER_ID)) {
				javaBuilderIndex = i;
			} else if (cs[i].getBuilderName().equals(BUILDER_ID)) {
				myIndex = i;
			}
		}
		return myIndex > javaBuilderIndex;
	}

	/**
	 * Copies class files from the given source folder to the given destination folder. The
	 * destination folder will be created if required, but only if at least one class file is
	 * copied.
	 * 
	 * @param source
	 *            the source folder; must exist
	 * @param dest
	 *            the destination folder; may or may not exist
	 * @param monitor
	 *            the progress monitor, or <code>null</code> if none
	 * @exception CoreException
	 *                if something goes wrong
	 */
	private void copyClassFiles(IFolder source, final IFolder dest, final IProgressMonitor monitor) throws CoreException {
		if (DEBUG) {
			System.out.println(BUILDER_ID + ": Begin copying class files from " + source.getFullPath() + " to " + dest.getFullPath()); //$NON-NLS-1$ //$NON-NLS-2$
		}

		final int sourcePathLength = source.getFullPath().segmentCount();

		class Visitor implements IResourceVisitor {
			public boolean visit(IResource res) throws CoreException {
				if (res.getType() == IResource.FILE) {
					IFile file = (IFile) res;

					// compute relative path from source folder to this file
					IPath filePath = file.getFullPath();
					IPath dpath = filePath.removeFirstSegments(sourcePathLength);
					IFile targetFile = dest.getFile(dpath);
					copyFile(file, targetFile, dpath, monitor);
				}
				return true;
			}
		}

		try {
			source.accept(new Visitor());
		} catch (CoreException e) {
			// should not happen
		}

	}

	/**
	 * Copies the given file to the given destination file. Does nothing if the destination file
	 * already exists.
	 * 
	 * @param source
	 *            the source file; must exist
	 * @param dest
	 *            the destination file; may or may not exist; never overwritten
	 * @param monitor
	 *            the progress monitor, or <code>null</code> if none
	 * @exception CoreException
	 *                if something goes wrong
	 */
	private void copyFile(IFile source, IFile dest, IPath fileRelativePath, IProgressMonitor monitor) throws CoreException {
		if (pruneForJavaSource(source, fileRelativePath, monitor))
			return; //no copy necessary.
		File sourceFile = null, destFile = null;
		if (source.exists())
			sourceFile = source.getLocation().toFile();
		if (dest.exists())
			destFile = dest.getLocation().toFile();

		if (destFile != null && sourceFile != null) {
			if (DEBUG)
				System.out.println(BUILDER_ID + ": " + dest.getFullPath() + " already exists."); //$NON-NLS-1$ //$NON-NLS-2$
			if (destFile.lastModified() == sourceFile.lastModified())
				return;
			dest.setContents(source.getContents(false), true, false, monitor); //we have to force
			// b/c set the mod
			// stamp makes it
			// think it is out of
			// synch.
			synchronizeModificationStamps(sourceFile, destFile);
			return;
		}
		if (DEBUG) {
			System.out.println(BUILDER_ID + ": Creating " + dest.getFullPath()); //$NON-NLS-1$
		}

		IContainer parent = dest.getParent();
		if (parent.getType() == IResource.FOLDER) {
			mkdirs((IFolder) parent, monitor);
		}
		dest.create(source.getContents(false), false, monitor);
		destFile = dest.getLocation().toFile();
		synchronizeModificationStamps(sourceFile, destFile);
		dest.setDerived(true);
	}

	/**
	 * Return true if a corresponding .java file is found. Remove the .class file from the
	 * imported_classes folder (i.e., delete the source file).
	 * 
	 * @param source
	 * @param monitor
	 * @return
	 */
	private boolean pruneForJavaSource(IFile classFile, IPath fileRelativePath, IProgressMonitor monitor) throws CoreException {
		if (classFile.exists()) {
			IFile javaFile = findCorrespondingJavaFile(fileRelativePath);
			if (javaFile != null && javaFile.exists()) {
				ResourcesPlugin.getWorkspace().validateEdit(new IFile[]{javaFile}, null);
				classFile.delete(true, false, monitor);
				return true;
			}
		}
		return false;
	}


	/**
	 * @param classFilePath
	 * @return
	 */
	private IFile findCorrespondingJavaFile(IPath classFilePath) {
		IPath javaPath = convertToJavaPath(classFilePath);
		List sourceFolders = getSourceContainers();
		IContainer cont;
		IFile javaFile;
		for (int i = 0; i < sourceFolders.size(); i++) {
			cont = (IContainer) sourceFolders.get(i);
			javaFile = cont.getFile(javaPath);
			if (javaFile.exists())
				return javaFile;
		}
		return null;
	}


	private List getSourceContainers() {
		if (sourceContainers == null)
			sourceContainers = JemProjectUtilities.getSourceContainers(getProject());
		return sourceContainers;
	}


	/**
	 * @param classFile
	 * @return
	 */
	private IPath convertToJavaPath(IPath classFilePath) {
		IPath javaPath = classFilePath.removeFileExtension();
		//handle inner classes...look for outermost java file
		String fileName = classFilePath.lastSegment();
		int innerIndex = fileName.indexOf('$');
		if (innerIndex > -1) {
			javaPath = javaPath.removeLastSegments(1);
			javaPath = javaPath.append(fileName.substring(0, innerIndex));
		}
		javaPath = javaPath.addFileExtension("java"); //$NON-NLS-1$
		return javaPath;
	}


	/**
	 * @param source
	 * @param dest
	 */
	private void synchronizeModificationStamps(File sourceFile, File destFile) {
		if (destFile != null && sourceFile != null) {
			destFile.setLastModified(sourceFile.lastModified());
			needOutputRefresh = true;
		}
	}


	/**
	 * Creates the given folder, and its containing folders, if required. Does nothing if the given
	 * folder already exists.
	 * 
	 * @param folder
	 *            the folder to create
	 * @param monitor
	 *            the progress monitor, or <code>null</code> if none
	 * @exception CoreException
	 *                if something goes wrong
	 */
	private void mkdirs(IFolder folder, IProgressMonitor monitor) throws CoreException {
		if (folder.exists()) {
			return;
		}
		IContainer parent = folder.getParent();
		if (!parent.exists() && parent.getType() == IResource.FOLDER) {
			mkdirs((IFolder) parent, monitor);
		}
		folder.create(false, true, monitor);
	}

	private IFolder[] getClassesFolders() {
		IProject project = getProject();
		IJavaProject javaProj = JemProjectUtilities.getJavaProject(project);
		if (javaProj == null)
			return new IFolder[0];
		List result = null;
		IClasspathEntry[] entries;
		try {
			entries = javaProj.getResolvedClasspath(true);
		} catch (JavaModelException e) {
			return new IFolder[0];
		}
		for (int i = 0; i < entries.length; i++) {
			IClasspathEntry entry = entries[i];
			if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
				IPath path = entry.getPath();
				IResource res = project.getWorkspace().getRoot().findMember(path);
				if (res != null && res.isAccessible() && res.getType() == IResource.FOLDER && res.getProject().equals(project)) {
					if (result == null)
						result = new ArrayList(1);
					result.add(res);
				}
			}
		}
		if (result == null)
			return new IFolder[0];
		return (IFolder[]) result.toArray(new IFolder[result.size()]);
	}
}
