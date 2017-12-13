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
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyVerificationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests the refactoring logic that handles rename/delete refactoring of EAR child
 * projects. 
 */
public class ProjectEARRefactoringTests extends AbstractTests {
	
	public ProjectEARRefactoringTests(String name) {
		super(name);
	}
	
	public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Project EAR Refactoring Tests" );
        //suite.addTest(new ProjectEARRefactoringTests("testDeleteEARWebModule"));
        // XXX this can fail
        //suite.addTest(new ProjectEARRefactoringTests("testDeleteEARWebModuleWithValidation"));
        //suite.addTest(new ProjectEARRefactoringTests("testDeleteEARUtilModule"));
        //suite.addTest(new ProjectEARRefactoringTests("testDeleteEAREJBModule"));
        suite.addTest(new ProjectEARRefactoringTests("testRenameEARWebModule"));
        suite.addTest(new ProjectEARRefactoringTests("testRenameEARUtilModule"));
        //suite.addTest(new ProjectEARRefactoringTests("testRenameEAREJBModule"));
        //suite.addTest(new ProjectEARRefactoringTests("testMultipleEARWebDelete"));
        suite.addTest(new ProjectEARRefactoringTests("testMultipleEARWebRename"));
        //suite.addTest(new ProjectEARRefactoringTests("testMultipleEARUtilDelete"));
        suite.addTest(new ProjectEARRefactoringTests("testMultipleEARUtilRename"));
        //suite.addTest(new ProjectEARRefactoringTests("testDeleteWithEARModuleDependency"));
        suite.addTest(new ProjectEARRefactoringTests("testRenameWithMultipleEARModuleDependency"));
        //suite.addTest(new ProjectEARRefactoringTests("testDependencyRemovalWithMultipleEARModuleDependency"));        
        return suite;
    }
    
	// bug 261555 switches us to use LTK refactoring- need to update the delete test cases
	public void _testDeleteEARWebModule() throws Exception {
		DependencyUtil.disableValidation();
		
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject.getName());
		String moduleURI = DependencyVerificationUtil.verifyEARDependency(earProject, webProject, true);
		
		ProjectUtil.deleteProject(webProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, webProject, moduleURI);
		
		DependencyUtil.enableValidation();
	}
	
	// bug 261555 switches us to use LTK refactoring- need to update the delete test cases
	public void _testDeleteEARWebModuleWithValidation() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject.getName());
		String moduleURI = DependencyVerificationUtil.verifyEARDependency(earProject, webProject, true);
		ProjectUtil.deleteProject(webProject);
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, webProject, moduleURI);
	}
	
	// bug 261555 switches us to use LTK refactoring- need to update the delete test cases
	public void _testDeleteEARUtilModule() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject.getName());
		
		DependencyVerificationUtil.verifyEARDependency(earProject, utilProject, false);
		
		ProjectUtil.deleteProject(utilProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, utilProject);	
    }
	
	// bug 261555 switches us to use LTK refactoring- need to update the delete test cases
	public void _testDeleteEAREJBModule() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject ejbProject = ProjectUtil.createEJBProject("TestEJB", earProject.getName());
		
		final IProject ejbClientProject = ProjectUtil.getProject("TestEJBClient");
		String moduleURI = DependencyVerificationUtil.verifyEARDependency(earProject, ejbProject, true);
		
		DependencyVerificationUtil.verifyEARDependency(earProject, ejbClientProject, false);
		DependencyVerificationUtil.verifyModuleDependency(ejbProject, ejbClientProject);
		
		ProjectUtil.deleteProject(ejbProject);
		ProjectUtil.deleteProject(ejbClientProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, ejbProject, moduleURI);
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, ejbClientProject);
    }
	
	public void testRenameEARWebModule() throws Exception {
		DependencyUtil.disableValidation();

		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject.getName());
		String moduleURI = DependencyVerificationUtil.verifyEARDependency(earProject, webProject, true);
		
		final IProject newWeb = ProjectUtil.renameProject(webProject, "newWeb");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject, webProject, moduleURI, newWeb);
		
		DependencyUtil.enableValidation();
	}
	
	public void testRenameEARUtilModule() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject.getName());
		
		DependencyVerificationUtil.verifyEARDependency(earProject, utilProject, false);
		
		final IProject newUtil = ProjectUtil.renameProject(utilProject, "newUtil");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject, utilProject, newUtil);	
    }
	
	public void testRenameEAREJBModule() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject ejbProject = ProjectUtil.createEJBProject("TestEJB", earProject.getName());
		final IProject ejbClientProject = ProjectUtil.getProject("TestEJBClient");
		
		String moduleURI = DependencyVerificationUtil.verifyEARDependency(earProject, ejbProject, true);
		DependencyVerificationUtil.verifyEARDependency(earProject, ejbClientProject, false);
		
		final IProject newEJB = ProjectUtil.renameProject(ejbProject, "newEJB");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject, ejbProject, moduleURI, newEJB);
		DependencyVerificationUtil.verifyModuleDependency(newEJB, ejbClientProject);
    }
	
	// bug 261555 switches us to use LTK refactoring- need to update the delete test cases
	public void _testMultipleEARWebDelete() throws Exception {
		DependencyUtil.disableValidation();

		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject1.getName());
		DependencyCreationUtil.createEARDependency(earProject2, webProject);
		
		final String moduleURI1 = DependencyVerificationUtil.verifyEARDependency(earProject1, webProject, true);	
		final String moduleURI2 = DependencyVerificationUtil.verifyEARDependency(earProject2, webProject, true);
		
		ProjectUtil.deleteProject(webProject);

		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject1, webProject, moduleURI1);	
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject2, webProject, moduleURI2);	
		
		DependencyUtil.enableValidation();
	}
	
	public void testMultipleEARWebRename() throws Exception {
		DependencyUtil.disableValidation();
		
		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject1.getName());
		DependencyCreationUtil.createEARDependency(earProject2, webProject);
		
		final String moduleURI1 = DependencyVerificationUtil.verifyEARDependency(earProject1, webProject, true);	
		final String moduleURI2 = DependencyVerificationUtil.verifyEARDependency(earProject2, webProject, true);
		
		final IProject newWeb = ProjectUtil.renameProject(webProject, "newWeb");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject1, webProject, moduleURI1, newWeb);	
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject2, webProject, moduleURI2, newWeb);		

		DependencyUtil.enableValidation();
	}
	
	// bug 261555 switches us to use LTK refactoring- need to update the delete test cases
	public void _testMultipleEARUtilDelete() throws Exception {
		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject1.getName());
		DependencyCreationUtil.createEARDependency(earProject2, utilProject);
		DependencyVerificationUtil.verifyEARDependency(earProject1, utilProject, false);	
		DependencyVerificationUtil.verifyEARDependency(earProject2, utilProject, false);
		ProjectUtil.deleteProject(utilProject);
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject1, utilProject);	
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject2, utilProject);		
	}
	
	public void testMultipleEARUtilRename() throws Exception {
		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject1.getName());
		DependencyCreationUtil.createEARDependency(earProject2, utilProject);
		DependencyVerificationUtil.verifyEARDependency(earProject1, utilProject, false);	
		DependencyVerificationUtil.verifyEARDependency(earProject2, utilProject, false);
		
		final IProject newUtil= ProjectUtil.renameProject(utilProject, "newUtil");
		
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject1, utilProject, newUtil);	
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject2, utilProject, newUtil);				
	}
	
	// bug 261555 switches us to use LTK refactoring- need to update the delete test cases
	public void _testDeleteWithEARModuleDependency() throws Exception {
		final IProject earProject = ProjectUtil.getProject("TestEAR");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject.getName());
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", earProject.getName());
		DependencyCreationUtil.createModuleDependency(webProject, utilProject);
		
		DependencyVerificationUtil.verifyEARDependency(earProject, utilProject, false);	
		DependencyVerificationUtil.verifyEARDependency(earProject, webProject, true);
		DependencyVerificationUtil.verifyModuleDependency(webProject, utilProject);
		
		ProjectUtil.deleteProject(utilProject);
		
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, utilProject);	
		DependencyVerificationUtil.verifyModuleDependencyRemoved(webProject, utilProject);
		DependencyVerificationUtil.verifyEARDependency(earProject, webProject, true);
	}
	
	public void testRenameWithMultipleEARModuleDependency() throws Exception {
		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject ejbProject = ProjectUtil.createEJBProject("TestEJB", earProject1.getName());
		DependencyCreationUtil.createEARDependency(earProject2, ejbProject);
		final IProject webProject1 = ProjectUtil.createWebProject("TestWeb1", earProject1.getName());
		final IProject webProject2 = ProjectUtil.createWebProject("TestWeb2", earProject2.getName());
		DependencyCreationUtil.createModuleDependency(webProject1, ejbProject);
		DependencyCreationUtil.createWebLibDependency(webProject2, ejbProject);
		
		DependencyUtil.waitForJobs(ResourcesPlugin.FAMILY_AUTO_BUILD);
				
		final String moduleURI1 = DependencyVerificationUtil.verifyEARDependency(earProject1, ejbProject, true);
		final String moduleURI2 = DependencyVerificationUtil.verifyEARDependency(earProject2, ejbProject, true);	
		DependencyVerificationUtil.verifyEARDependency(earProject1, webProject1, true);
		DependencyVerificationUtil.verifyEARDependency(earProject2, webProject2, true);
       	DependencyVerificationUtil.verifyModuleDependency(webProject1, ejbProject);
		DependencyVerificationUtil.verifyWebLibDependency(webProject2, ejbProject);
		
		IProject newEJB = ProjectUtil.renameProject(ejbProject, "newEJB");
			
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject1, ejbProject, moduleURI1, newEJB);	
		DependencyVerificationUtil.verifyEARDependencyChanged(earProject2, ejbProject, moduleURI2, newEJB);
      	DependencyVerificationUtil.verifyModuleDependencyChanged(webProject1, ejbProject, newEJB);
		DependencyVerificationUtil.verifyWebLibDependencyChanged(webProject2, ejbProject, newEJB);
	}
	
	/* XXX only works through the UI right now
	public void testDependencyRemovalWithMultipleEARModuleDependency() throws Exception {
		final IProject earProject1 = ProjectUtil.getProject("TestEAR1");
		final IProject earProject2 = ProjectUtil.createEARProject("TestEAR2");
		final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", earProject1.getName());
		DependencyCreationUtil.createEARDependency(earProject2, utilProject);
		final IProject webProject1 = ProjectUtil.createWebProject("TestWeb1", earProject1.getName());
		final IProject webProject2 = ProjectUtil.createWebProject("TestWeb2", earProject2.getName());
		DependencyCreationUtil.createModuleDependency(webProject1, utilProject);
		DependencyCreationUtil.createModuleDependency(webProject2, utilProject);
		
		// verify all of the dependencies
		DependencyVerificationUtil.verifyEARDependency(earProject1, utilProject, false);
		DependencyVerificationUtil.verifyEARDependency(earProject2, utilProject, false);	
		DependencyVerificationUtil.verifyEARDependency(earProject1, webProject1, true);
		DependencyVerificationUtil.verifyEARDependency(earProject2, webProject2, true);
		DependencyVerificationUtil.verifyModuleDependency(webProject1, utilProject);
		DependencyVerificationUtil.verifyModuleDependency(webProject2, utilProject);
		
		// remove the dependency between the util and just EAR1
		DependencyCreationUtil.removeEARDependency(earProject1, utilProject);
		
		// verify the changed dependencies
		DependencyVerificationUtil.verifyEARDependencyRemoved(earProject1, utilProject);
		DependencyVerificationUtil.verifyModuleDependencyRemoved(webProject1, utilProject);
		
		// verify the dependencies that should be unchanged
		DependencyVerificationUtil.verifyEARDependency(earProject1, webProject1, true);
		DependencyVerificationUtil.verifyEARDependency(earProject2, webProject2, true);
		DependencyVerificationUtil.verifyEARDependency(earProject2, utilProject, false);
		DependencyVerificationUtil.verifyWebLibDependency(webProject2, utilProject);
	}
	*/
	
}
