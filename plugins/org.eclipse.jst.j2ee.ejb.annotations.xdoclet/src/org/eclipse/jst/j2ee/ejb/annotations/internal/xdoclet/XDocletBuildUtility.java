/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class XDocletBuildUtility {

	public static final Object FAMILY_MANUAL_BUILD = new Object();
	private static final String XDOCLETBUILDER = "org.eclipse.jst.j2ee.ejb.annotations.xdoclet.xdocletbuilder";

	class BuildJob extends Job {
		
		private final IProject fProject;
		ICommand xdocletBuilder;
	
		private BuildJob(String name, IProject project) {
			super(name);
			fProject = project;
			xdocletBuilder = getXDocletBuilder(project);
	
	
		}
	
		public boolean isCoveredBy(IProject aProject) {
	
			return fProject != null && fProject.equals(aProject);
		}
	
		public boolean belongsTo(Object family) {
			return family == XDocletBuildUtility.FAMILY_MANUAL_BUILD;
		}
		
		protected IStatus run(IProgressMonitor monitor) {
			if (xdocletBuilder != null) {
				try {
					fProject.build(IncrementalProjectBuilder.FULL_BUILD,
							XDOCLETBUILDER, xdocletBuilder.getArguments(), monitor);
				} catch (CoreException e) {
					Logger.logException(e);
					return Status.CANCEL_STATUS;
				}
			}
			return Status.OK_STATUS;
		}
	
		protected ICommand getXDocletBuilder(IProject aProject) {
			IProjectDescription description;
			try {
				description = aProject.getDescription();
	
				if (description == null) {
					return null;
				}
	
				ICommand[] commands = description.getBuildSpec();
				for (int i = 0; i < commands.length; ++i) {
					if (commands[i].getBuilderName().equals(XDOCLETBUILDER)) {
						return commands[i];
					}
				}
			} catch (CoreException e) {
				Logger.logException(e);
			}
			return null;
	
		}
	
	}

	public static void runNecessaryBuilders(IProgressMonitor monitor, IProject project) throws CoreException {
		if (project != null)
			runNecessaryBuildersForProject(project, monitor);
		else {
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			for (int i = 0; i < projects.length; i++) {
				runNecessaryBuildersForProject(projects[i], monitor);
			}
	
		}
	}

	private static void runNecessaryBuildersForProject(IProject aProject, IProgressMonitor monitor) throws CoreException {
	
		Job[] buildJobs = Platform.getJobManager().find(FAMILY_MANUAL_BUILD);
		for (int i = 0; i < buildJobs.length; i++) {
			Job curr = buildJobs[i];
			if (curr instanceof BuildJob) {
				BuildJob job = (BuildJob) curr;
				if (job.isCoveredBy(aProject)) {
					curr.cancel(); // cancel all other build jobs of our kind
				}
			}
		}
	
		XDocletBuildUtility buildUtility = new XDocletBuildUtility();
		XDocletBuildUtility.BuildJob buildJob = buildUtility.new BuildJob("XDoclet Build", aProject);
		buildJob.schedule(1000L);
		
	}

	protected ICommand getXDocletBuilder(IProjectDescription description) throws CoreException {
		if (description == null) {
			return null;
		}
	
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(XDOCLETBUILDER)) {
				return commands[i];
			}
		}
		return null;
	
	}

}
