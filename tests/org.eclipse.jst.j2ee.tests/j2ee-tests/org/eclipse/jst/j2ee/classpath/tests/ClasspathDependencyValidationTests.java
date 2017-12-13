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

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jst.j2ee.classpath.tests.util.ClasspathDependencyTestUtil;
import org.eclipse.jst.j2ee.classpathdep.UpdateClasspathAttributeUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainer;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests validation rules for classpath component dependencies.
 */
public class ClasspathDependencyValidationTests extends AbstractTests {

    private ClasspathDependencyValidationTests(final String name) {
        super(name);
    }
    
    public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Classpath Component Dependency Validation Tests" );
        suite.addTest(new ClasspathDependencyValidationTests("testSourceEntryRule"));
        suite.addTest(new ClasspathDependencyValidationTests("testProjectEntryRule"));
        suite.addTest(new ClasspathDependencyValidationTests("testDuplicateClassFolderRule"));
        suite.addTest(new ClasspathDependencyValidationTests("testNonWebNonExportedRule"));
        //suite.addTest(new ClasspathDependencyValidationTests("testDuplicateArchiveNamesRule"));
        suite.addTest(new ClasspathDependencyValidationTests("testRootMappingNonEARWARRefRule"));
        suite.addTest(new ClasspathDependencyValidationTests("testInvalidContainerRules"));
        suite.addTest(new ClasspathDependencyValidationTests("testNonTaggedExportedClassesRule"));

        return suite;
    }
    //SourceEntry=Invalid classpath component dependency {0}. Source entries not supported.
    public void testSourceEntryRule() throws Exception {
    	final IProject project = ProjectUtil.createUtilityProject("TestUtil", "TestEAR", true);
    	ClasspathDependencyTestUtil.verifyNoValidationError(project);
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);

    	IClasspathEntry[] entries = javaProject.getRawClasspath();
    	IClasspathEntry src = null;
    	for (int i = 0; i < entries.length; i++) {
    		if (entries[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
    			src = entries[i];
    			break;
    		}
    	}
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), src);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyClasspathDependencyError(project);
    	
    	UpdateClasspathAttributeUtil.removeDependencyAttribute(null, project.getName(), src);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencyError(project);
    }
    
    //ProjectClasspathEntry=Invalid classpath component dependency {0}. Project entries not supported.
    public void testProjectEntryRule() throws Exception {
    	final IProject utilProject = ProjectUtil.createUtilityProject("TestUtil", "TestEAR");
    	final IProject webProject = ProjectUtil.createWebProject("TestWeb", "TestEAR", true);
    	final IJavaProject webJavaProject = JavaCore.create(webProject);
    	final IVirtualComponent webComp = ComponentCore.createComponent(webProject);
    	    	
    	final IClasspathEntry entry = JavaCore.newProjectEntry(utilProject.getFullPath());
    	ClasspathDependencyTestUtil.addEntryToCP(webJavaProject, entry);
    	
    	DependencyUtil.waitForValidationJobs(webProject);
    	ClasspathDependencyTestUtil.verifyNoValidationError(webProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(webJavaProject);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(webJavaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(webComp);

    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, webProject.getName(), entry);
    	
    	DependencyUtil.waitForValidationJobs(webProject);
    	ClasspathDependencyTestUtil.verifyClasspathDependencyError(webProject);
    	
    	UpdateClasspathAttributeUtil.removeDependencyAttribute(null, webProject.getName(), entry);
    	
    	DependencyUtil.waitForValidationJobs(webProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencyError(webProject);
    }

    //DuplicateClassFolderEntry
    public void testDuplicateClassFolderRule() throws Exception {
    	final IProject project = ProjectUtil.createUtilityProject("TestUtil", "TestEAR", true);
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	final IPath classFolderPath = project.getFullPath().append(ClasspathDependencyTestUtil.TEST3_BIN_PATH);
    	
    	// create a library classpath entry
    	ClasspathDependencyTestUtil.addLibraryEntry(javaProject, classFolderPath, true);
    	
    	// link via the component file 
    	comp.getRootFolder().createLink(ClasspathDependencyTestUtil.TEST3_BIN_PATH, IVirtualResource.FORCE, null);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoValidationError(project);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);

    	IClasspathEntry[] entries = javaProject.getRawClasspath();
    	IClasspathEntry lib = null;
    	for (int i = 0; i < entries.length; i++) {
    		if (entries[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
    			lib = entries[i];
    			break;
    		}
    	}
    	
    	// Mark that cp entry for publish/export
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), lib);
    	
    	final Set entryPaths = Collections.singleton(classFolderPath);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyClasspathDependencyError(project);

    	UpdateClasspathAttributeUtil.removeDependencyAttribute(null, project.getName(), lib);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencyError(project);
    }
    
    //NonWebNonExported=Invalid classpath component dependency {0}. Classpath component dependencies for non-web projects must be exported.
    public void testNonWebNonExportedRule() throws Exception {
    	final IProject project = ProjectUtil.createUtilityProject("TestUtil", "TestEAR", true);
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	
    	ClasspathDependencyTestUtil.addLibraryEntry(javaProject, ClasspathDependencyTestUtil.TEST3_JAR_PATH, false);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoValidationError(project);
    	final Set entryPaths = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR_PATH);
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);

    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);    	
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), entry);

    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, entryPaths);
    	final Set archiveNames = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(comp, archiveNames);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyClasspathDependencyMarker(project);
    }
    
    //DuplicateArchiveName=Invalid classpath component dependency {0}. The project contains another classpath component dependency the same archive name.
    public void testDuplicateArchiveNamesRule() throws Exception {
    	final IProject webProject = ProjectUtil.createWebProject("TestWeb", "TestEAR");
    	final IJavaProject webJavaProject = JavaCore.create(webProject);
    	final IProject util1 = ProjectUtil.createUtilityProject("TestUtil1", "TestEAR");
    	DependencyCreationUtil.createWebLibDependency(webProject, util1);
    	final IJavaProject utilJava1 = JavaCore.create(util1);
    	final IVirtualComponent utilComp1 = ComponentCore.createComponent(util1);
    	final IProject util2 = ProjectUtil.createUtilityProject("TestUtil2", "TestEAR");
    	DependencyCreationUtil.createWebLibDependency(webProject, util2);
    	final IVirtualComponent utilComp2 = ComponentCore.createComponent(util2);
    	final IJavaProject utilJava2 = JavaCore.create(util2);
    	final IProject util3 = ProjectUtil.createUtilityProject("TestUtil3", "TestEAR", true);
    	DependencyCreationUtil.createWebLibDependency(webProject, util3);
    	final IVirtualComponent utilComp3 = ComponentCore.createComponent(util3);
    	final IJavaProject utilJava3 = JavaCore.create(util3);
    	ClasspathDependencyTestUtil.addLibraryEntry(utilJava1, ClasspathDependencyTestUtil.TEST3_JAR_PATH, true);
    	ClasspathDependencyTestUtil.addLibraryEntry(utilJava2, ClasspathDependencyTestUtil.TEST3_JAR_PATH, true);
    	ClasspathDependencyTestUtil.addLibraryEntry(utilJava3, ClasspathDependencyTestUtil.TEST3_JAR_OTHER_LOCATION_PATH, true);
    	final Set entryPaths = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR_PATH);
    	final Set otherEntryPaths = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR_OTHER_LOCATION_PATH);
    	final Set archiveNames = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR);

    	
    	// add the dependency attribute to util1 and util2 (these point to the same jar so won't 
    	// generate a validation error)
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(utilJava1, entryPaths);
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, util1.getName(), entry);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(utilComp1, archiveNames);
    	entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(utilJava2, entryPaths);
    	entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, util2.getName(), entry);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(utilComp2, archiveNames);

    	// no validation error on the EAR or Web
    	DependencyUtil.waitForValidationJobs(webProject);
    	IProject earProject = ProjectUtil.getProject("TestEAR");
    	ClasspathDependencyTestUtil.verifyNoValidationError(earProject);
    	ClasspathDependencyTestUtil.verifyNoValidationError(webProject);    	
    	
    	// add the dependency attribute to util3
    	entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(utilJava3, otherEntryPaths);
    	entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, util3.getName(), entry);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(utilComp3, archiveNames);
    	
    	// will have a validation error on the EAR and on the Web
    	DependencyUtil.waitForValidationJobs(webProject);
    	DependencyUtil.waitForValidationJobs(earProject);
    	ClasspathDependencyTestUtil.verifyValidationError(webProject);
    	ClasspathDependencyTestUtil.verifyValidationError(earProject);
    	
    	// remove dependency attribute from util3
    	UpdateClasspathAttributeUtil.removeDependencyAttribute(null, util3.getName(), entry);

    	// will have no validation errors on the EAR and on the Web
    	DependencyUtil.waitForValidationJobs(webProject);
    	DependencyUtil.waitForValidationJobs(earProject);
    	ClasspathDependencyTestUtil.verifyNoValidationError(webProject);
    	ClasspathDependencyTestUtil.verifyNoValidationError(earProject);    	
    }
    
    //RootMappingNonEARWARRef=Non-web projects must be referenced by an EAR or a WAR to use classpath component dependencies.
    public void testRootMappingNonEARWARRefRule() throws Exception {
    	final IProject project = ProjectUtil.createUtilityProject("TestUtil", null, true);
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	
    	ClasspathDependencyTestUtil.addLibraryEntry(javaProject, ClasspathDependencyTestUtil.TEST3_JAR_PATH, true);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoValidationError(project);
    	final Set entryPaths = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR_PATH);
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), entry);

    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyClasspathDependencyMarker(project);
    	
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, entryPaths);
    	final Set archiveNames = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(comp, archiveNames);
    	
    	UpdateClasspathAttributeUtil.removeDependencyAttribute(null, project.getName(), entry);
    	
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    }
    
    //EarLibrariesContainer=Invalid classpath component dependency {0}. The EAR Libraries classpath container cannot be exposed.
    //WebAppLibrariesContainer=Invalid classpath component dependency {0}. The Web App Libraries classpath container cannot be exposed.
    //RuntimeClasspathContainer=Invalid classpath component dependency {0}. Server Runtime classpath containers cannot be exposed.
    //JREContainer=Invalid classpath component dependency {0}. JRE classpath containers cannot be exposed.
    public void testInvalidContainerRules() throws Exception {
    	final IProject project = ProjectUtil.createWebProject("TestWeb", "TestEAR", true);
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoValidationError(project);
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);    	
    	
    	IClasspathEntry earLibContainer = null;
    	IClasspathEntry webAppLibContainer = null;
    	IClasspathEntry jreContainer = null;
    	IClasspathEntry[] rawCP = javaProject.getRawClasspath();
    	for (int i = 0; i < rawCP.length; i++) {
    		if (rawCP[i].getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
    			final IPath path = rawCP[i].getPath();
    			if (path.equals(J2EEComponentClasspathContainer.CONTAINER_PATH)) {
    				earLibContainer = rawCP[i];
    			} else if (path.segment(0).equals("org.eclipse.jst.j2ee.internal.web.container")) {
    				webAppLibContainer = rawCP[i];
    			} else if (path.segment(0).equals(JavaRuntime.JRE_CONTAINER)) {
    				jreContainer = rawCP[i];
    			}
    		}
    	}
    	
    	// XXX Encountering sporadic JUnit problems trying to wait for and verify results of validator
    	// the ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject) will need to be 
    	// sufficient for now: the results of that call are computed by calling the validator
    	
    	// try adding the Ear Libraries container
    	//testInvalidContainer(javaProject, earLibContainer);
    	
    	// try adding the Web App Libraries container
    	//testInvalidContainer(javaProject, webAppLibContainer);
    	
    	// try adding the JRE container
    	//testInvalidContainer(javaProject, jreContainer);
    }
    
    private void testInvalidContainer(final IJavaProject javaProject, final IClasspathEntry entry) throws Exception {
    	IProject project = javaProject.getProject();
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), entry);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, Collections.singleton(entry.getPath()));
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyClasspathDependencyError(project);
    	UpdateClasspathAttributeUtil.removeDependencyAttribute(null, project.getName(), entry);
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencyError(project);    	    	
    }

    //NonTaggedExportedClasses=Classpath entry {0} will not be exported or published. Runtime ClassNotFoundExceptions may result.  
    public void testNonTaggedExportedClassesRule() throws Exception {
    	final IProject project = ProjectUtil.createUtilityProject("TestUtil", "TestEAR", true);
    	final IJavaProject javaProject = JavaCore.create(project);
    	final IVirtualComponent comp = ComponentCore.createComponent(project);
    	
    	ClasspathDependencyTestUtil.addLibraryEntry(javaProject, ClasspathDependencyTestUtil.TEST3_JAR_PATH, true);
    	
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyClasspathDependencyMarker(project);
    	final Set entryPaths = Collections.singleton(ClasspathDependencyTestUtil.TEST3_JAR_PATH);
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(javaProject, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(javaProject);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(comp);
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, project.getName(), entry);

    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(javaProject);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(javaProject, entryPaths);
    	DependencyUtil.waitForValidationJobs(project);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencyMarker(project);
    }
    
}
