package org.eclipse.jem.tests;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaProjectUtil.java,v $
 *  $Revision: 1.4 $  $Date: 2004/06/02 15:57:16 $ 
 */


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.*;
import org.osgi.framework.Bundle;

import org.eclipse.jem.internal.proxy.core.ProxyPlugin;

/**
 * The purpose of this class is to be a utility for manipulating/populating java projects.
 *  1) Get a java project as one of the resources.
 *  2) Create a project from a directory. The directory needs to be all set up to be a project already. Typically
 *     from an unzip.
 */

public class JavaProjectUtil {	
	public static IProject getProject(String projectName) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		return (project.exists()) ? project: null;
	}
	
	
	/**
	 * Create a project from the path. The path will be a complete project, including a .project file.
	 * 
	 * @param workspace
	 * @param projectPath Needs to be a complete path to a directory within the file system. The directory must be a complete project including .project file.
	 * @param pm
	 * @return The new IProject.
	 * @throws CoreException
	 */
	public static IProject createProject(IWorkspace workspace, IPath projectPath, final IProgressMonitor pm) throws CoreException {
		IProjectDescription newDescription = null;
		
		File projectFile = new File(projectPath.toFile(), IProjectDescription.DESCRIPTION_FILE_NAME);
		if (!projectFile.exists())
			throw new CoreException(new Status(IStatus.ERROR, JavaTestsPlugin.getPlugin().getBundle().getSymbolicName(), 0, "Project file not in project directory. Couldn't create project \""+projectPath.toString()+"\"", null));


		IPath projectFilePath = new Path(projectFile.getPath());
		newDescription = workspace.loadProjectDescription(projectFilePath);
		
		// create the new project operation
		final IProject project = workspace.getRoot().getProject(newDescription.getName());		
		final IProjectDescription description = newDescription;
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {		
			public void run(IProgressMonitor monitor) throws CoreException {
				project.create(description, new SubProgressMonitor(monitor, 1000));
				project.open(new SubProgressMonitor(monitor, 1000));				
			}
		}, project, 0, pm);
		
		return project;
	}

	/**
	 * Create an empty project in the workspace root. If there is a .project file already there, then use it.
	 * @param workspace The workspace to create projec in.
	 * @param projectPath The name of the project. It should only be one segment long, and that will be the name of the project.
	 * @param pm
	 * @return The project.
	 * @throws CoreException
	 * 
	 * @since 1.0.0
	 */
	public static IProject createEmptyJavaProject(IWorkspace workspace, IPath projectPath, final IProgressMonitor pm) throws CoreException {
		projectPath = workspace.getRoot().getFullPath().append(projectPath);	// Put it into the workspace relative.
		File projectFile = new File(projectPath.toFile(), IProjectDescription.DESCRIPTION_FILE_NAME);
		if (projectFile.exists())
			return createProject(workspace, projectPath, pm);	// Let it be created normally.

		// create the new project operation
		final IProject project = workspace.getRoot().getProject(projectPath.lastSegment());		
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {		
			public void run(IProgressMonitor monitor) throws CoreException {
				project.create(new SubProgressMonitor(monitor, 1000));
				project.open(new SubProgressMonitor(monitor, 1000));
				IProjectDescription description = project.getDescription();
				String[] natureids = description.getNatureIds();
				for (int i = 0; i < natureids.length; i++) {
					if (natureids[i].equals("org.eclipse.jdt.core.javanature"))
						return;	// Already has nature.
				}
				String[] newNatureids = new String[natureids.length+1];
				newNatureids[0] = "org.eclipse.jdt.core.javanature";
				System.arraycopy(natureids, 0, newNatureids, 1, natureids.length);
				description.setNatureIds(newNatureids);
				project.setDescription(description, new SubProgressMonitor(monitor, 1000));
				// Need to put out a classfile too. We need a src and a bin directory for the classpath.
				IFolder sf = project.getFolder("src");
				sf.create(true, true, new SubProgressMonitor(monitor, 1000));
				IFolder bf = project.getFolder("bin");
				bf.create(true, true, new SubProgressMonitor(monitor, 1000));
				IFile cp = project.getFile(".classpath"); 
				try {
					cp.create(getClass().getResource(".classpath").openStream(), true, new SubProgressMonitor(monitor, 1000));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, project, 0, pm);
		
		return project;
	}
	
	/**
	 * Delete a project and all files from the project.
	 * @param project
	 * @throws CoreException
	 */
	public static void deleteProject(IProject project) throws CoreException {
		if (project != null) {
			project.delete(true, false, null); // Get rid of the project and the files themselves.
		}
	}
	

	/**
	 * This sets the workspace autobuild to the specified state. It returns what
	 * the state was previously so that it can be later restored.
	 * @param autoBuild
	 * @return Previous autoBuild state.
	 * @throws CoreException
	 */
	public static boolean setAutoBuild(boolean autoBuild) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (autoBuild != workspace.isAutoBuilding()) {
			IWorkspaceDescription description = workspace.getDescription();
			description.setAutoBuilding(true);
			workspace.setDescription(description);
			return !autoBuild;	// It wasn't this state.			
		} else
			return autoBuild;	// It already is this state. 		
	}
	
	/**
	 * Import the given projects from the given zipfiles into the current workspace root.
	 * @param projectNames The array of project names to create
	 * @param zipFiles The zipfiles containing each project. It must be a complete project. 
	 *                 The first directory in the zip must be the project directory. 
	 *                 It must be the same name as from projectNames. Also it must include a .project file
	 *                 in that directory.
	 * @return The array of IProjects that were created.
	 * @throws CoreException
	 */
	public static IProject[] importProjects(final String[] projectNames, final String[] zipFiles) throws CoreException {
		// Delete/create/populate within a runnable so that the build is done only after all changes have been made.
		// Make sure autobuild is on.
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProject[] result = new IProject[projectNames.length];
		workspace.run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				// First import all, then after that, create the projects.
				IPath rootLocation = workspace.getRoot().getLocation();
				URL installLoc = JavaTestsPlugin.getPlugin().getBundle().getEntry("/");					
				try {
					String antFile = Platform.asLocalURL(new URL(installLoc, "testdata/unzip.xml")).getFile();
					for (int i = 0; i < zipFiles.length; i++) {
						// First create/recreate the project.
						IProject p = getProject(projectNames[i]);
						if (p != null)
							p.delete(true, true, new SubProgressMonitor(monitor, 50)); // Get rid of it, we want a clean one for testing.
					
						// Now import the requested files.
						AntRunner ant = new AntRunner();
						ant.setBuildFileLocation(antFile);
						ant.setArguments(new String[] { "-Dzipfrom=" + zipFiles[i], "-Dzipto=" + rootLocation.toString()});
						ant.run(new SubProgressMonitor(monitor, 50));
					}
					for (int i = 0; i < zipFiles.length; i++) {
						result[i] = createProject(workspace, rootLocation.append(projectNames[i]), new SubProgressMonitor(monitor, 50));
					}
				} catch (MalformedURLException e) {
				} catch (IOException e) {
				}
			}
		}, workspace.getRoot(), 0, null);
		
		return result;
	}

	static int cfSuffix = 0;
	/**
	 * Add a path to plugin jar to the java project's class path.
	 * @param bundle The plugin where the jar is located.
	 * @param pathToJar Path to the jar within the above plugin
	 * @param project java project to add to.
	 * 
	 * @since 1.0.0
	 */
	public static void addBundleJarToPath(Bundle bundle, String pathToJar, final IJavaProject project, IProgressMonitor pm) throws CoreException {
		final IPath actualPath = new Path(ProxyPlugin.getPlugin().localizeFromBundle(bundle, pathToJar));
		if (actualPath.isEmpty())
			return;	// Didn't exist.
		
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {		
				if (actualPath.toFile().isFile()) {
					// It is a jar, this will be during runtime
					// Create an external jar entry.
					IClasspathEntry[] raw = project.getRawClasspath();
					IClasspathEntry[] newRaw = new IClasspathEntry[raw.length+1];
					newRaw[raw.length] = JavaCore.newLibraryEntry(actualPath, null, null);
					System.arraycopy(raw, 0, newRaw, 0, raw.length);
					project.setRawClasspath(newRaw, new SubProgressMonitor(monitor, 100));
				} else {
					// It is a path to class folder, this will be during development time.
					// But classfolders MUST exist in the workspace. JDT doesn't understand them outside workspace,
					// so we will link it into the project.
					IFolder cf = project.getProject().getFolder("linkbin"+(++cfSuffix));
					cf.createLink(actualPath, 0, new SubProgressMonitor(monitor, 100));
					// Create class folder entry.
					IClasspathEntry[] raw = project.getRawClasspath();
					IClasspathEntry[] newRaw = new IClasspathEntry[raw.length+1];
					newRaw[raw.length] = JavaCore.newLibraryEntry(cf.getFullPath(), null, null);
					System.arraycopy(raw, 0, newRaw, 0, raw.length);
					project.setRawClasspath(newRaw, new SubProgressMonitor(monitor, 100));
				}
			}
		}, project.getProject(), 0, pm);
	}
}