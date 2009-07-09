/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.flexible.project.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit.Test0_7Workspace;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.internal.ModuleMigratorManager;

public class Migrate07EJBTest extends TestCase {
	
	private IProject ejbProject,webProject;
	public Migrate07EJBTest() {
		super();

		if (Test0_7Workspace.init()) {
			ejbProject = Test0_7Workspace.getTargetProject("MyEarEJB");
			webProject = Test0_7Workspace.getTargetProject("TestWeb");
		} else {
			fail();

		}
	}

	private void pass() {
		assertTrue(true);
	}
	public String getFacetFromProject(IProject aProject) {
		return J2EEProjectUtilities.getJ2EEProjectType(aProject);
	}

	public void testMigrate() {
		EJBArtifactEdit ejbedit = null;
		WebArtifactEdit webEdit = null;
		
		
			//Run full build
			try {
				ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, null);
			} catch (CoreException e) {
			}
			migrateProjects();
			
			ISchedulingRule rule= ResourcesPlugin.getWorkspace().getRuleFactory().buildRule();
			IJobManager manager= Platform.getJobManager();
			try {
				manager.beginRule(rule, null);

				try {
				ejbedit = EJBArtifactEdit.getEJBArtifactEditForRead(ejbProject);
				if (ejbedit != null) {
					EJBJar ejb = ejbedit.getEJBJar();
					assertTrue(ejb != null);
				}
				webEdit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
				if (webEdit != null) {
					WebApp web = webEdit.getWebApp();
					assertTrue(web != null);
				}
				} finally {
					if (ejbedit != null) {
						ejbedit.dispose();
					}
					if (webEdit != null) {
						webEdit.dispose();
					}
				}

			} finally {
				manager.endRule(rule);
			}
	}

	private void migrateProjects() {

		ModuleMigratorManager manager = ModuleMigratorManager.getManager(ejbProject);
		if (!manager.isMigrating() && !ResourcesPlugin.getWorkspace().isTreeLocked())
			try {
				manager.migrateOldMetaData(ejbProject,true);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		
		manager = ModuleMigratorManager.getManager(webProject);
		if (!manager.isMigrating() && !ResourcesPlugin.getWorkspace().isTreeLocked())
			try {
				manager.migrateOldMetaData(webProject,true);
			} catch (CoreException e) {
				e.printStackTrace();
			}
    
		
	}
	

}
