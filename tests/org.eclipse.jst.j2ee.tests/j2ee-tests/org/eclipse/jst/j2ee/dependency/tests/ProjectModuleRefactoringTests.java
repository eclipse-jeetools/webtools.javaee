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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyVerificationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;

/**
 * Tests the refactoring logic that handles rename/delete refactoring of projects that
 * participate in inter-module dependencies.
 */
public class ProjectModuleRefactoringTests extends AbstractTests {
	
	public ProjectModuleRefactoringTests(String name) {
		super(name);
	}
	
	public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Project Module Dependency Refactoring Tests" );
        suite.addTest(new ProjectModuleRefactoringTests("testDeleteModuleDependency"));
        suite.addTest(new ProjectModuleRefactoringTests("testRenameModuleDependency"));
        suite.addTest(new ProjectModuleRefactoringTests("testDeleteModuleDependencyWithMarker"));
        suite.addTest(new ProjectModuleRefactoringTests("testRenameModuleDependencyWithMarker"));
        suite.addTest(new ProjectModuleRefactoringTests("testDeleteMultipleDependency"));
        suite.addTest(new ProjectModuleRefactoringTests("testRenameMultipleModuleDependency"));
        return suite;
    }
	
	public void testDeleteModuleDependency() throws Exception {
		final IProject[] projects = setupModuleDependency();
		final IProject earProject = projects[0];
		final IProject webProject = projects[1];
		final IProject utilProject = projects[2];

		ProjectUtil.deleteProject(utilProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, utilProject);	
		DependencyVerificationUtil.verifyModuleDependencyRemoved(webProject, utilProject);
    }
    
    public void testDeleteModuleDependencyWithMarker() throws Exception {
        final IProject[] projects = setupModuleDependency();
        final IProject earProject = projects[0];
        final IProject webProject = projects[1];
        final IProject utilProject = projects[2];

        // add a marker 
        utilProject.createMarker("TEST_MARKER");
        
        ProjectUtil.deleteProject(utilProject);
        
        DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, utilProject); 
        DependencyVerificationUtil.verifyModuleDependencyRemoved(webProject, utilProject);
    }

    public void testRenameModuleDependency() throws Exception {
        final IProject[] projects = setupModuleDependency();
        final IProject earProject = projects[0];
        final IProject webProject = projects[1];
        final IProject utilProject = projects[2];
        
        final IProject newUtil = ProjectUtil.renameProject(utilProject, "newUtil");
        
        DependencyVerificationUtil.verifyEARDependencyChanged(earProject, utilProject, newUtil);    
        DependencyVerificationUtil.verifyModuleDependencyChanged(webProject, utilProject, newUtil);
    }
    
    public void testRenameModuleDependencyWithMarker() throws Exception {
        final IProject[] projects = setupModuleDependency();
        final IProject earProject = projects[0];
        final IProject webProject = projects[1];
        final IProject utilProject = projects[2];
        
        // add a marker 
        utilProject.createMarker("TEST_MARKER");
        
        final IProject newUtil = ProjectUtil.renameProject(utilProject, "newUtil");
        
        DependencyVerificationUtil.verifyEARDependencyChanged(earProject, utilProject, newUtil);    
        DependencyVerificationUtil.verifyModuleDependencyChanged(webProject, utilProject, newUtil);
    }

	public void testDeleteMultipleDependency() throws Exception {
		final IProject[] projects = setupMultipleDependency();
		final IProject earProject1 = projects[0];
		final IProject earProject2 = projects[1];
		final IProject webProject = projects[2];
		final IProject ejbProject = projects[3];
		final IProject utilProject = projects[4];

		ProjectUtil.deleteProject(utilProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject1, utilProject);	
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject2, utilProject);	
		DependencyVerificationUtil.verifyModuleDependencyRemoved(webProject, utilProject);
		DependencyVerificationUtil.verifyModuleDependencyRemoved(ejbProject, utilProject);
	}
	
	public void testRenameMultipleModuleDependency() throws Exception {
		final IProject[] projects = setupMultipleDependency();
		final IProject earProject1 = projects[0];
		final IProject earProject2 = projects[1];
		final IProject webProject = projects[2];
		final IProject ejbProject = projects[3];
		final IProject utilProject = projects[4];

		final IProject newUtil = ProjectUtil.renameProject(utilProject, "newUtil");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject1, utilProject, newUtil);
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject2, utilProject, newUtil);
		DependencyVerificationUtil.verifyModuleDependencyChanged(webProject, utilProject, newUtil);
		DependencyVerificationUtil.verifyModuleDependencyChanged(ejbProject, utilProject, newUtil);
	}
	
	private static IProject[] setupModuleDependency() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject.getName());
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject.getName());
		DependencyCreationUtil.createModuleDependency(webProject, utilProject);
		DependencyVerificationUtil.verifyEARDependency(earProject, utilProject, false);	
		DependencyVerificationUtil.verifyEARDependency(earProject, webProject, true);
		DependencyVerificationUtil.verifyModuleDependency(webProject, utilProject);	
		return new IProject[] {earProject, webProject, utilProject};
	}

	private static IProject[] setupMultipleDependency() throws Exception {
		// create the projects
		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject1.getName());

		// create the dependencies
		DependencyCreationUtil.createEARDependency(earProject2, utilProject);
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject1.getName());
		final IProject ejbProject = ProjectUtil.createEJBProject("TestEJB", earProject2.getName());
		final IProject ejbClientProject = ProjectUtil.getProject("TestEJBClient");
		DependencyCreationUtil.createModuleDependency(webProject, utilProject);
		DependencyCreationUtil.createModuleDependency(ejbProject, utilProject);

		// verify the dependencies
		DependencyVerificationUtil.verifyEARDependency(earProject1, utilProject, false);
		DependencyVerificationUtil.verifyEARDependency(earProject1, webProject, true);
		DependencyVerificationUtil.verifyEARDependency(earProject2, utilProject, false);
		DependencyVerificationUtil.verifyEARDependency(earProject2, ejbProject, true);
		DependencyVerificationUtil.verifyEARDependency(earProject2, ejbClientProject, false);
		DependencyVerificationUtil.verifyModuleDependency(webProject, utilProject);	
		DependencyVerificationUtil.verifyModuleDependency(ejbProject, utilProject);
		
		return new IProject[] {earProject1, earProject2, webProject, ejbProject, utilProject};
	}
	
}
