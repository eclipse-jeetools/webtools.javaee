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
package org.eclipse.jst.j2ee.classpath.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.classpath.tests.util.ClasspathDependencyTestUtil;
import org.eclipse.jst.j2ee.classpathdep.UpdateClasspathAttributeUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests creation of classpath component dependencies via addition of the
 * classpath entry attribute.
 */
public class ClasspathDependencyCreationTests extends AbstractTests {

    private ClasspathDependencyCreationTests(final String name) {
        super(name);
    }
    
    public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Classpath Component Dependency Creation Tests" );
        suite.addTest(new ClasspathDependencyCreationTests("testNoClasspathDependencies"));
        suite.addTest(new ClasspathDependencyCreationTests("testAddRemoveClasspathDependency"));
        suite.addTest(new ClasspathDependencyCreationTests("testLibraryClasspathDependency"));
        suite.addTest(new ClasspathDependencyCreationTests("testClassFolderDependency"));
        return suite;
    }
    
    public void testNoClasspathDependencies() throws Exception {
    	final IProject project = ProjectUtil.createUtilityProject("TestUtil", "TestEAR");
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    }
    
    public void testAddRemoveClasspathDependency() throws Exception {
    	final IProject project = ProjectUtil.createUtilityProject("TestUtil", "TestEAR");
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	
    	ClasspathDependencyTestUtil.addCustomClasspathContainer(javaProject);
    	
    	final Set entryPaths = new HashSet();
    	entryPaths.add(ClasspathDependencyTestUtil.CUSTOM_CLASSPATH_CONTAINER);
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), entry);
    	
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, entryPaths);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(comp, archiveNames);
    	
    	UpdateClasspathAttributeUtil.removeDependencyAttribute(null, project.getName(), entry);
    	
    	ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    }
    
    public void testLibraryClasspathDependency() throws Exception {
    	final IProject project = ProjectUtil.createWebProject("TestWeb", "TestEAR");
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	
    	ClasspathDependencyTestUtil.addLibraryEntry(javaProject, ClasspathDependencyTestUtil.TEST3_JAR_PATH, true);
    	
    	final Set entryPaths = new HashSet();
    	entryPaths.add(ClasspathDependencyTestUtil.TEST3_JAR_PATH);
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), entry);
    	
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, entryPaths);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST3_JAR);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(comp, archiveNames);    	
    }
    
    public void testClassFolderDependency() throws Exception {
    	final IProject project = ProjectUtil.createWebProject("TestWeb", "TestEAR");
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	final IPath binPath = new Path("bin");
    	final IPath fullBinPath = project.getFullPath().append(binPath);
    	project.getFolder(binPath).create(true, true, null);
    	
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	
    	ClasspathDependencyTestUtil.addLibraryEntry(javaProject, fullBinPath, true);
    	
    	final Set entryPaths = new HashSet();
    	entryPaths.add(fullBinPath);
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), entry);
    	
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, entryPaths);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(fullBinPath.toString());
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(comp, archiveNames);    	
    }
}
