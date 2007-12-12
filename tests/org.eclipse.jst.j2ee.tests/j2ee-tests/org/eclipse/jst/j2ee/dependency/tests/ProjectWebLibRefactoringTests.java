/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    BEA Systems, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.dependency.tests;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyVerificationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests the refactoring logic that handles rename/delete refactoring of projects that
 * participate in web-inf/lib module dependencies.
 */
public class ProjectWebLibRefactoringTests extends AbstractTests {
	
	public ProjectWebLibRefactoringTests(String name) {
		super(name);
	}
	
	public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Project WEB-INF/lib Dependency Refactoring Tests" );
        suite.addTest(new ProjectWebLibRefactoringTests("testDeleteWebLibDependency"));
        suite.addTest(new ProjectWebLibRefactoringTests("testRenameWebLibDependency"));
        suite.addTest(new ProjectWebLibRefactoringTests("testDeleteMultipleDependency"));
        suite.addTest(new ProjectWebLibRefactoringTests("testRenameMultipleModuleDependency"));
        return suite;
    }

	public void testDeleteWebLibDependency() throws Exception {
		final IProject[] projects = setupWebLibDependency();
		final IProject earProject = projects[0];
		final IProject webProject = projects[1];
		final IProject utilProject = projects[2];
		
		ProjectUtil.deleteProject(utilProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, utilProject);	
		DependencyVerificationUtil.verifyWebLibDependencyRemoved(webProject, utilProject);
    }

	public void testRenameWebLibDependency() throws Exception {
		final IProject[] projects = setupWebLibDependency();
		final IProject earProject = projects[0];
		final IProject webProject = projects[1];
		final IProject utilProject = projects[2];
		
		final IProject newUtil = ProjectUtil.renameProject(utilProject, "newUtil");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject, utilProject, newUtil);	
		DependencyVerificationUtil.verifyWebLibDependencyChanged(webProject, utilProject, newUtil);
    }
	
	public void testDeleteMultipleDependency() throws Exception {
		final IProject[] projects = setupMultipleDependency();
		final IProject earProject1 = projects[0];
		final IProject earProject2 = projects[1];
		final IProject webProject1 = projects[2];
		final IProject webProject2 = projects[3];
		final IProject utilProject = projects[4];

		ProjectUtil.deleteProject(utilProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject1, utilProject);	
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject2, utilProject);	
		DependencyVerificationUtil.verifyWebLibDependencyRemoved(webProject1, utilProject);
		DependencyVerificationUtil.verifyWebLibDependencyRemoved(webProject2, utilProject);
	}
	
	public void testRenameMultipleModuleDependency() throws Exception {
		final IProject[] projects = setupMultipleDependency();
		final IProject earProject1 = projects[0];
		final IProject earProject2 = projects[1];
		final IProject webProject1 = projects[2];
		final IProject webProject2 = projects[3];
		final IProject utilProject = projects[4];

		final IProject newUtil = ProjectUtil.renameProject(utilProject, "newUtil");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject1, utilProject, newUtil);
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject2, utilProject, newUtil);
		DependencyVerificationUtil.verifyWebLibDependencyChanged(webProject1, utilProject, newUtil);
		DependencyVerificationUtil.verifyWebLibDependencyChanged(webProject2, utilProject, newUtil);
	}
	
	private static IProject[] setupWebLibDependency() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject.getName());
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject.getName());
		DependencyCreationUtil.createWebLibDependency(webProject, utilProject);
		DependencyVerificationUtil.verifyEARDependency(earProject, utilProject, false);	
		DependencyVerificationUtil.verifyEARDependency(earProject, webProject, true);
		DependencyVerificationUtil.verifyWebLibDependency(webProject, utilProject);
		return new IProject[] {earProject, webProject, utilProject};
	}
	
	private static IProject[] setupMultipleDependency() throws Exception {
		// create the projects
		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject1.getName());

		// create the dependencies
		DependencyCreationUtil.createEARDependency(earProject2, utilProject);
		final IProject webProject1 = ProjectUtil.createWebProject("TestWeb1", earProject1.getName());
		final IProject webProject2 = ProjectUtil.createWebProject("TestWeb2", earProject2.getName());

		DependencyCreationUtil.createWebLibDependency(webProject1, utilProject);
		DependencyCreationUtil.createWebLibDependency(webProject2, utilProject);

		// verify the dependencies
		DependencyVerificationUtil.verifyEARDependency(earProject1, utilProject, false);
		DependencyVerificationUtil.verifyEARDependency(earProject1, webProject1, true);
		DependencyVerificationUtil.verifyEARDependency(earProject2, utilProject, false);
		DependencyVerificationUtil.verifyEARDependency(earProject2, webProject2, true);
		DependencyVerificationUtil.verifyWebLibDependency(webProject1, utilProject);	
		DependencyVerificationUtil.verifyWebLibDependency(webProject2, utilProject);	
		
		return new IProject[] {earProject1, earProject2, webProject1, webProject2, utilProject};
	}

}
