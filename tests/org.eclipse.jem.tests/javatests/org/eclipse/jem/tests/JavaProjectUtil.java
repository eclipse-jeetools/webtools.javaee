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
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:25:46 $ 
 */


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;

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
			throw new CoreException(new Status(IStatus.ERROR, JavaTestsPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "Project file not in project directory. Couldn't create project \""+projectPath.toString()+"\"", null));


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
		}, pm);
		
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
				URL installLoc = JavaTestsPlugin.getPlugin().getDescriptor().getInstallURL();					
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
		}, null);
		
		return result;
	}
		
}