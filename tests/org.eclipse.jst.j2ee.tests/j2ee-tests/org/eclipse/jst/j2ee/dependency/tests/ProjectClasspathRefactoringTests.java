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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;

/**
 * Tests refactoring logic that updates component mappings when source IClasspathEntries are 
 * added to/removed from the Java build path.
 */
public class ProjectClasspathRefactoringTests extends AbstractTests {
	
	public ProjectClasspathRefactoringTests(String name) {
		super(name);
	}
	
	public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Project Classpath Refactoring Tests" );
        suite.addTest(new ProjectClasspathRefactoringTests("testSourcePathAdditionWeb"));
        suite.addTest(new ProjectClasspathRefactoringTests("testSourcePathRemovalWeb"));
        //suite.addTest(new ProjectClasspathRefactoringTests("testSourcePathRenameWeb"));
        suite.addTest(new ProjectClasspathRefactoringTests("testSourcePathAdditionUtil"));
        suite.addTest(new ProjectClasspathRefactoringTests("testSourcePathRemovalUtil"));
        //suite.addTest(new ProjectClasspathRefactoringTests("testSourcePathRenameUtil"));
        suite.addTest(new ProjectClasspathRefactoringTests("testWebContentRename"));
        suite.addTest(new ProjectClasspathRefactoringTests("testEarContentRename"));
        return suite;
    }

	public void testSourcePathAdditionWeb() throws Exception {
		testSourcePathAddition(ProjectUtil.createWebProject("TestWeb", null));
	}
	
	public void testSourcePathAdditionUtil() throws Exception {
		testSourcePathAddition(ProjectUtil.createUtilityProject("TestUtil", null));
	}
	
	private static void testSourcePathAddition(final IProject project) throws Exception {
		final IPath srcPath = new Path("src");
		final IPath newSrcPath = new Path("newSrc");
		
		DependencyUtil.verifyComponentMapping(project, srcPath, true);
		DependencyUtil.verifyComponentMapping(project, newSrcPath, false);
		
		assertTrue("Failed to add new source path " + newSrcPath, DependencyUtil.addJavaSrcPath(project, newSrcPath));
		
		DependencyUtil.verifyComponentMapping(project, srcPath, true);
		DependencyUtil.verifyComponentMapping(project, newSrcPath, true);
	}
	
	public void testSourcePathRemovalWeb() throws Exception {
		testSourcePathRemoval(ProjectUtil.createWebProject("TestWeb", null));
	}
	
	public void testSourcePathRemovalUtil() throws Exception {
		testSourcePathRemoval(ProjectUtil.createUtilityProject("TestUtil", null));
	}
	
	private static void testSourcePathRemoval(final IProject project) throws Exception {
		final IPath srcPath = new Path("src");
		DependencyUtil.verifyComponentMapping(project, srcPath, true);
		assertTrue("Failed to remove src path " + srcPath, DependencyUtil.removeJavaSrcPath(project, srcPath));
		DependencyUtil.verifyComponentMapping(project, srcPath, false);
	}
	
	public void testWebContentRename() throws Exception {
		final IProject webProject = ProjectUtil.createWebProject("TestWeb", null);
		final IPath webContent = new Path(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.WEB_CONTENT_FOLDER));
		final IPath newWebContent = new Path("WebContent2");
		
		DependencyUtil.verifyComponentMapping(webProject, webContent, Path.ROOT, true);
		DependencyUtil.verifyComponentMapping(webProject, newWebContent, Path.ROOT, false);
		
		// rename the WebContent
		final IFolder folder = webProject.getFolder(webContent);
		folder.move(webProject.getFullPath().append(newWebContent), true, null);
		DependencyUtil.waitForComponentRefactoringJobs();
		
		DependencyUtil.verifyComponentMapping(webProject, webContent, Path.ROOT, false);
		DependencyUtil.verifyComponentMapping(webProject, newWebContent, Path.ROOT, true);	
	}
	
	public void testEarContentRename() throws Exception {
		final IProject earProject = ProjectUtil.createEARProject("TestEAR");
		final IPath earContent = new Path(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APPLICATION_CONTENT_FOLDER));
		final IPath newEarContent = new Path("EarContent2");
		
		DependencyUtil.verifyComponentMapping(earProject, earContent, Path.ROOT, true);
		DependencyUtil.verifyComponentMapping(earProject, newEarContent, Path.ROOT, false);
		
		// rename the EarContent
		final IFolder folder = earProject.getFolder(earContent);
		folder.move(earProject.getFullPath().append(newEarContent), true, null);
		DependencyUtil.waitForComponentRefactoringJobs();
			
		DependencyUtil.verifyComponentMapping(earProject, earContent, Path.ROOT, false);
		DependencyUtil.verifyComponentMapping(earProject, newEarContent, Path.ROOT, true);
	}

//  XXX need to change to execute a refactor->rename
//	public void testSourcePathRenameWeb() throws Exception {
//		testSourcePathRename(ProjectUtil.createWebProject("TestWeb", null));
//	}
//	
//	public void testSourcePathRenameUtil() throws Exception {
//		testSourcePathRename(ProjectUtil.createUtilityProject("TestUtil", null));
//	}
//	
//	private static void testSourcePathRename(final IProject project) throws Exception {
//		final IPath srcPath = new Path("src");
//		final IPath newSrcPath = new Path("newSrc");
//		
//		DependencyUtil.verifyComponentMapping(project, srcPath, true);
//		
//		final IFolder srcFolder = project.getFolder(srcPath);
//		srcFolder.move(newSrcPath, true, null);
//		
//		DependencyUtil.verifyComponentMapping(project, srcPath, false);
//		DependencyUtil.verifyComponentMapping(project, newSrcPath, true);
//	}
}
