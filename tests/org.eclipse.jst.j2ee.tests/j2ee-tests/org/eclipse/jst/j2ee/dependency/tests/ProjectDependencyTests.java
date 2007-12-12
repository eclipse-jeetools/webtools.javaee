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
 * Tests project creation logic in the DependencyCreationUtil helper class.
 */
public class ProjectDependencyTests extends AbstractTests {
	
    private ProjectDependencyTests(final String name) {
        super(name);
    }
    
    public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Project Dependency Tests" );
        suite.addTest(new ProjectDependencyTests("testEARWebDependency"));
        suite.addTest(new ProjectDependencyTests("testEARDependencyRemoval"));
        suite.addTest(new ProjectDependencyTests("testEARUtilDependency"));
        suite.addTest(new ProjectDependencyTests("testEAREJBDependency"));
        suite.addTest(new ProjectDependencyTests("testStandaloneWebUtilDependency"));
        suite.addTest(new ProjectDependencyTests("testWebUtilWebInfLibDependency"));
        suite.addTest(new ProjectDependencyTests("testWebEJBModuleDependency"));
        return suite;
    }
    
    public void testEARWebDependency() throws Exception {
    	testEARDependency(ProjectUtil.createEARProject("TestEAR"),
    			ProjectUtil.createWebProject("TestWeb", null), true);
    }
    
    public void testEARDependencyRemoval() throws Exception {
    	final IProject earProject = ProjectUtil.createEARProject("TestEAR");
    	final IProject webProject = ProjectUtil.createWebProject("TestWeb", null);
    	final String moduleURI = testEARDependency(earProject, webProject, true);
    	DependencyCreationUtil.removeEARDependency(earProject, webProject);
    	DependencyVerificationUtil.verifyEARDependencyRemoved(earProject, webProject, moduleURI);
    }
    
    public void testEARUtilDependency() throws Exception {
    	testEARDependency(ProjectUtil.createEARProject("TestEAR"),
    			ProjectUtil.createUtilityProject("TestUtil", null), false);
    }
    
    public void testEAREJBDependency() throws Exception {
    	testEARDependency(ProjectUtil.createEARProject("TestEAR"),
    			ProjectUtil.createEJBProject("TestEJB", null), true);
    }
    
    private String testEARDependency(final IProject earProject, final IProject childProject, final boolean moduleRef) throws Exception {
    	DependencyCreationUtil.createEARDependency(earProject, childProject);
    	return DependencyVerificationUtil.verifyEARDependency(earProject, childProject, moduleRef);
    }
    
    public void testStandaloneWebUtilDependency() throws Exception {
    	final IProject saWeb =  ProjectUtil.createWebProject("TestWeb", null);
    	final IProject util =  ProjectUtil.createUtilityProject("TestUtil", null);
    	DependencyCreationUtil.createWebLibDependency(saWeb, util);
    	DependencyVerificationUtil.verifyWebLibDependency(saWeb, util);
    }
    
    public void testWebUtilWebInfLibDependency() throws Exception {
    	final IProject web =  ProjectUtil.createWebProject("TestWeb", null);
    	final IProject util =  ProjectUtil.createUtilityProject("TestUtil", null);
    	final IProject ear = ProjectUtil.createEARProject("TestEAR");
    	testEARDependency(ear, web, true);
    	testEARDependency(ear, util, false);
    	DependencyCreationUtil.createWebLibDependency(web, util);
    	DependencyVerificationUtil.verifyWebLibDependency(web, util);
    }
    
    public void testWebEJBModuleDependency() throws Exception {
    	final IProject web =  ProjectUtil.createWebProject("TestWeb", null);
    	final IProject ejb =  ProjectUtil.createEJBProject("TestEJB", null);
    	final IProject ear = ProjectUtil.createEARProject("TestEAR");
    	testEARDependency(ear, web, true);
    	testEARDependency(ear, ejb, true);
    	DependencyCreationUtil.createModuleDependency(web, ejb);
    	DependencyVerificationUtil.verifyModuleDependency(web, ejb);	
    }
}