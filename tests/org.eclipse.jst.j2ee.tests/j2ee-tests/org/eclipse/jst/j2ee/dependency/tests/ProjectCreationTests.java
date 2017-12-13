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
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyVerificationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;

/**
 * Tests project creation logic in the ProjectCreationUtil helper class.
 */
public class ProjectCreationTests extends AbstractTests {

    private ProjectCreationTests(final String name) {
        super(name);
    }
    
    public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Project Creation Tests" );
        suite.addTest(new ProjectCreationTests("testEARCreation"));
        suite.addTest(new ProjectCreationTests("testWebCreation"));
        suite.addTest(new ProjectCreationTests("testWebWithEARCreation"));
        suite.addTest(new ProjectCreationTests("testEJBCreation"));
        suite.addTest(new ProjectCreationTests("testEJBWithEARCreation"));
        suite.addTest(new ProjectCreationTests("testUtilCreation"));
        suite.addTest(new ProjectCreationTests("testUtilWithEARCreation"));
        return suite;
    }
    
    public void testEARCreation() throws Exception {
    	ProjectUtil.createEARProject("TestEAR");
    }
    
    public void testWebCreation() throws Exception {
    	ProjectUtil.createWebProject("TestWeb", null);
    }
    
    public void testWebWithEARCreation() throws Exception {
    	final IProject earProject = ProjectUtil.getProject("TestEAR"); 
    	DependencyVerificationUtil.verifyEARDependency(earProject, ProjectUtil.createWebProject("TestWeb", earProject.getName()), true);
    }
    
    public void testEJBCreation() throws Exception {
    	ProjectUtil.createEJBProject("TestEJB", null);
    }
    
    public void testEJBWithEARCreation() throws Exception {
    	final IProject earProject = ProjectUtil.getProject("TestEAR"); 
    	final IProject ejbProject = ProjectUtil.createEJBProject("TestEJB", earProject.getName());
    	DependencyVerificationUtil.verifyEARDependency(earProject, ejbProject, true);
    	testEJBClient(ejbProject, earProject);
    }

    private void testEJBClient(final IProject ejbProject, final IProject earProject) throws Exception {
    	final IProject ejbClient = ProjectUtil.getProject(ejbProject.getName() + "Client"); 
    	// check dependencies from EAR to EJB client
    	DependencyVerificationUtil.verifyProjectReference(earProject, ejbClient, true);
    	DependencyVerificationUtil.verifyComponentReference(earProject, ejbClient, DependencyVerificationUtil.ROOT, true);
    	
    	// check dependencies from EJB to EJB client
    	//DependencyVerificationUtil.verifyModuleDependency(ejbProject, ejbClient);
    	DependencyVerificationUtil.verifyManifestReference(ejbProject, ejbClient.getName() + ".jar", true);    	
    }
    
    public void testUtilCreation() throws Exception {
    	ProjectUtil.createUtilityProject("TestUtil", null);
    }
    
    public void testUtilWithEARCreation() throws Exception {
    	final IProject earProject = ProjectUtil.getProject("TestEAR");
    	DependencyVerificationUtil.verifyEARDependency(earProject, ProjectUtil.createUtilityProject("TestUtil", earProject.getName()), false);
    }
}
