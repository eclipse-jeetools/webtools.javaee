/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Mar 25, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;

import com.ibm.wtp.common.logger.proxy.Logger;

;

public class LibDirBuilder extends IncrementalProjectBuilder implements IResourceDeltaVisitor {

	//$NON-NLS-1$
	public static boolean TRACING = false;
	protected IProgressMonitor pMonitor = null;

	/**
	 * LibDirChangeListener constructor comment.
	 */
	public LibDirBuilder() {
	}

	/**
	 * Implemements a method in <code>IncrementalProjectBuilder</code>.
	 * 
	 * @see IncrementalProjectBuilder
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
		IResourceDelta delta = getDelta(getProject());
		boolean isFullBuild = (kind == IncrementalProjectBuilder.FULL_BUILD) || (delta == null);
		try {
			if (isFullBuild) {
				synch(getProject(), monitor);
			} else {
				pMonitor = monitor;
				delta.accept(this);
			}
		} catch (CoreException ex) {
			Logger.getLogger().log(ex);
		}
		return null;
	}

	/**
	 * Adds a new entry to the java runtime class path Creation date: (4/12/2001 1:22:03 PM)
	 * 
	 * @return boolean
	 * @param libentry_path
	 *            java.lang.String
	 */
	protected static boolean createLibEntry(IJavaProject javaProject, IPath library_path) {
		boolean added = false;

		try {
			WebProjectInfo projectInfo = new WebProjectInfo();
			projectInfo.setProjectName(javaProject.getProject().getName());

			IClasspathEntry[] cp = javaProject.getRawClasspath();

			//Try to make an entry for the java class path
			IClasspathEntry entry = JavaCore.newLibraryEntry(library_path, null, null);

			boolean addEntry = true;

			// Before the jar is added, search the existing classpath to make sure
			// it dose not already exist.
			for (int i = 0; i < cp.length; i++) {
				if (cp[i].equals(entry)) {
					addEntry = false;

				}

			}

			// Finally, add the jar if necessary

			if (addEntry) {

				IClasspathEntry[] newPath = new IClasspathEntry[cp.length + 1];
				int i = 0;
				for (i = 0; i < cp.length; i++) {
					if (i < cp.length) {
						newPath[i] = cp[i];
					}

				}
				newPath[i] = entry;

				javaProject.setRawClasspath(newPath, null);

			}

			added = true;

		} catch (JavaModelException ex) {
			Logger.getLogger().log(ex);
		}
		return added;
	}

	/**
	 * Removes entry to the java runtime class path Creation date: (4/12/2001 1:22:03 PM)
	 * 
	 * @return boolean
	 * @param libentry_path
	 *            java.lang.String
	 */
	protected static boolean removeLibEntry(IJavaProject javaProject, IPath library_path) {
		boolean added = false;
		try {
			WebProjectInfo projectInfo = new WebProjectInfo();
			projectInfo.setProjectName(javaProject.getProject().getName());

			IClasspathEntry[] cp = javaProject.getRawClasspath();

			//Make a class path entry to match with one you will remove
			IClasspathEntry entry = JavaCore.newLibraryEntry(library_path, null, null);

			int found = -1;
			// Before the jar is added, search the existing classpath to make sure
			// it dose not already exist.
			for (int i = 0; i < cp.length; i++) {
				if (cp[i].getPath().equals(entry.getPath())) {
					found = i;
				}

			}

			// Finally, remove the jar if necessary

			if (found != -1) {
				IClasspathEntry[] newPath = new IClasspathEntry[cp.length - 1];
				int i = 0;
				int pos = 0;
				for (i = 0; i <= newPath.length; i++) {
					if (i != found) {
						newPath[pos++] = cp[i];
					}
				}

				javaProject.setRawClasspath(newPath, null);
			}

			added = true;

		} catch (JavaModelException ex) {
			Logger.getLogger().log(ex);
		}
		return added;
	}

	/**
	 * Informs this builder that it is being started by the build management infrastructure. By the
	 * time this method is run, the builder's project is available and
	 * <code>setInitializationData</code> has been called.
	 * 
	 * @see BaseBuilder#startupOnInitialize()
	 */
	protected void startupOnInitialize() {
		super.startupOnInitialize();

		if (TRACING)
			Logger.getLogger().log(getClass().getName() + ProjectSupportResourceHandler.getString("24concat_INFO_", //$NON-NLS-1$
						(new Object[]{getProject()})));
		//$NON-NLS-1$ = ".startupOnInitialize() for "

	}

	/**
	 * Synchonizies the class path and the lib directories to catch any changes from the last use
	 * Creation date: (4/17/01 11:48:12 AM)
	 */
	protected static void synch(IProject project, IProgressMonitor monitor) {

		try {
			if (monitor == null) {
				monitor = new NullProgressMonitor();
			}
			monitor.beginTask(ProjectSupportResourceHandler.getString("Sychronize_Class_Path_UI_"), 4); //$NON-NLS-1$
			//$NON-NLS-1$ = "Sychronize Class Path"

			J2EEWebNatureRuntime webNature = (J2EEWebNatureRuntime) project.getNature(IWebNatureConstants.J2EE_NATURE_ID);
			IContainer lib_folder = webNature.getLibraryFolder();
			//Nothing to do if the lib folder does not exist.
			if (lib_folder == null || !lib_folder.isAccessible())
				return;
			IJavaProject javaProject = webNature.getJ2EEJavaProject();
			IPath lib_path = lib_folder.getProjectRelativePath();
			IPath lib_full_path = lib_folder.getFullPath();

			IClasspathEntry[] cp = javaProject.getRawClasspath();

			boolean needsToBeModified = false;
			//Create a map of the lib projects in the current project
			Hashtable lib_jars = new Hashtable();
			IResource[] children = lib_folder.members();
			monitor.subTask(ProjectSupportResourceHandler.getString("Catalog_Lib_Directory__UI_")); //$NON-NLS-1$
			//$NON-NLS-1$ = "Catalog Lib Directory:"
			for (int j = 0; j < children.length; j++) {
				IResource child = children[j];
				//monitor.setTaskName(ResourceHandler.getString("Catalog_Lib_Directory__UI_") +
				// child); //$NON-NLS-1$ = "Catalog Lib Directory:"
				//Make sure it is a zip or a jar file
				if (child.getType() == IResource.FILE && (child.getFullPath().toString().toLowerCase().endsWith(".jar") //$NON-NLS-1$
							|| child.getFullPath().toString().toLowerCase().endsWith(".zip"))) { //$NON-NLS-1$
					lib_jars.put(child.getFullPath(), child);
				}

			}

			monitor.worked(1);
			monitor.subTask(ProjectSupportResourceHandler.getString("Update_ClassPath__UI_")); //$NON-NLS-1$
			//$NON-NLS-1$ = "Update ClassPath:"
			//Loop through all the classpath dirs looking for ones that may have
			//been deleted
			Vector newClassPathVector = new Vector();
			for (int j = 0; j < cp.length; j++) {

				//If it is a lib_path
				if (cp[j].getPath().toString().startsWith(lib_path.toString()) || cp[j].getPath().toString().startsWith(lib_full_path.toString())) {
					//It was already in the class path
					if (lib_jars.get(cp[j].getPath()) != null) {
						newClassPathVector.add(cp[j]);
						//Remove it from the hash table of paths to add back
						//monitor.setTaskName(ResourceHandler.getString("Catalog_Lib_Directory__UI_")
						// + cp[j].getPath()); //$NON-NLS-1$ = "Catalog Lib Directory:"
						lib_jars.remove(cp[j].getPath());

					} else {
						//You have removed something form the class path you
						//will need to re-build
						//monitor.setTaskName(ResourceHandler.getString("Catalog_Lib_Directory_Remo_UI_")
						// + cp[j].getPath()); //$NON-NLS-1$ = "Catalog Lib Directory:Remove "
						needsToBeModified = true;
					}
				} else {
					monitor.subTask(ProjectSupportResourceHandler.getString("Catalog_Lib_Directory__UI_") + cp[j].getPath()); //$NON-NLS-1$
					//$NON-NLS-1$ = "Catalog Lib Directory:"
					newClassPathVector.add(cp[j]);
				}
			}
			monitor.worked(1);
			monitor.subTask(ProjectSupportResourceHandler.getString("Update_ClassPath__UI_")); //$NON-NLS-1$
			//$NON-NLS-1$ = "Update ClassPath:"

			//Add any entries not already found
			Enumeration aenum = lib_jars.keys();
			while (aenum.hasMoreElements()) {
				IPath path = (IPath) aenum.nextElement();
				newClassPathVector.add(JavaCore.newLibraryEntry(path, null, null));
				//You have added something form the class path you
				//will need to re-build
				//monitor.setTaskName(ResourceHandler.getString("23concat_UI_", (new Object[] {
				// path }))); //$NON-NLS-1$ = "Catalog Lib Directory:Add {0}"
				needsToBeModified = true;
			}

			monitor.worked(1);
			monitor.subTask(ProjectSupportResourceHandler.getString("Set_ClassPath__UI_")); //$NON-NLS-1$
			//$NON-NLS-1$ = "Set ClassPath:"

			//Tansfer the vector to an array
			IClasspathEntry[] newClassPathArray = new IClasspathEntry[newClassPathVector.size()];

			for (int j = 0; j < newClassPathArray.length; j++) {
				newClassPathArray[j] = (IClasspathEntry) newClassPathVector.get(j);
			}

			//Only change the class path if there has been a modification
			if (needsToBeModified) {

				try {
					javaProject.setRawClasspath(newClassPathArray, monitor);
				} catch (Exception e) {
					Logger.getLogger().log(e);
				}
			}

		} catch (ClassCastException ex) {
			Logger.getLogger().log(ex);
		} catch (CoreException ex) {
			Logger.getLogger().log(ex);
		} finally {
			monitor.done();
		}

	}

	public boolean visit(IResourceDelta subdelta) throws CoreException {
		//Pull out resource
		try {
			IResource resource = subdelta.getResource();

			if (resource.getType() == IResource.FILE) {
				String filePath = subdelta.getFullPath().toString();
				//only allow .jar or .zip
				if (filePath.toLowerCase().endsWith(".jar") //$NON-NLS-1$
							|| filePath.toLowerCase().endsWith(".zip")) { //$NON-NLS-1$
					IProject project = resource.getProject();
					J2EEWebNatureRuntime webNature = (J2EEWebNatureRuntime) project.getNature(IWebNatureConstants.J2EE_NATURE_ID);
					IJavaProject javaProject = webNature.getJ2EEJavaProject();
					IPath lib_path = project.getFullPath().append(webNature.getLibraryFolder().getProjectRelativePath());

					int file_seg_count = subdelta.getFullPath().segmentCount();
					int lib_path_seg_count = lib_path.segmentCount();

					//File must be in the lib path and not a subdir
					if (filePath.startsWith(lib_path.toString()) && file_seg_count == lib_path_seg_count + 1) { //
						// Find out what happened
						//
						int kind = subdelta.getKind();
						switch (kind) {

							case IResourceDelta.ADDED :
								createLibEntry(javaProject, new Path(filePath));
								break;
							case IResourceDelta.REMOVED :
								removeLibEntry(javaProject, new Path(filePath));
								break;
							case IResourceDelta.ADDED_PHANTOM :
								break;
							case IResourceDelta.REMOVED_PHANTOM :
								break;
							case IResourceDelta.CHANGED :
								break;
						}

					}
				}
			} else if (resource.getType() == IResource.PROJECT) {
				synch(((IProject) resource), pMonitor);
			}
		} catch (ClassCastException ex) {
			//ignore it just means this is not a web project
		}
		return true;
	}
}