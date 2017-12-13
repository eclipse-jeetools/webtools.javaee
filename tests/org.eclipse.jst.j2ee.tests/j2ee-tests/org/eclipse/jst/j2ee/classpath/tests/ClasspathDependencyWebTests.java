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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.classpath.tests.util.ClasspathDependencyTestUtil;
import org.eclipse.jst.j2ee.classpathdep.UpdateClasspathAttributeUtil;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.FileImpl;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.model.IModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;

/**
 * Tests export and publish behavior for classpath component dependencies and web projects.
 */
public class ClasspathDependencyWebTests extends AbstractTests {

	private static final String UTIL_PROJECT = "TestUtil";
	private static final String WEB_PROJECT = "TestWeb";
	
    private ClasspathDependencyWebTests(final String name) {
        super(name);
    }
    
    public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Classpath Dependency Web Tests" );
        suite.addTest(new ClasspathDependencyWebTests("testWebExportJ2EE"));
        //suite.addTest(new ClasspathDependencyWebTests("testWebExportJEE5"));
        suite.addTest(new ClasspathDependencyWebTests("testWebPublishJ2EE"));
        suite.addTest(new ClasspathDependencyWebTests("testWebPublishJEE5"));
        suite.addTest(new ClasspathDependencyWebTests("testWebContainerPublishJEE5"));
        return suite;
    }
    
    public void testWebExportJ2EE() throws Exception {
        testWebExport(false);
    }
    
    public void testWebExportJEE5() throws Exception {
    	testWebExport(true);
    }
    
    private void testWebExport(boolean JEE5) throws Exception {

    	// create the Web and utility projects
    	IVirtualComponent webComp = createProjects(JEE5);
    	
    	// verify that the exported WAR WEB-INF/lib does not contain the cp container jars from the Utility
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	verifyExportedWebInfLibs(webComp, archiveNames, false);
    	
    	// add the cp dependency attribute to the cp container in the util
    	addDependencyAttribute(false);
    	
    	// verify that the exported WAR WEB-INF/lib does contain the cp container jars from the Utility
    	verifyExportedWebInfLibs(webComp, archiveNames, true);
    }
    
    // TODO need to modify to work with both JEE5 model provider and J2EE ArtifactEdit logic 
    private void verifyExportedWebInfLibs(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveDependencies) throws Exception {
		WebArtifactEdit webEdit = null;
		WARFile warFile = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(comp);
			if (webEdit != null) {
				warFile = (WARFile) webEdit.asArchive(true);
				List libArchives = warFile.getLibArchives();
				Iterator it = archiveNames.iterator();
				while (it.hasNext()) {
					String name = (String) it.next();
					boolean hasArchive = false;
					for (int i = 0; i < libArchives.size(); i++) {
						File archive = (File) libArchives.get(i);
						if (archive.isArchive()) {
							if (archive.getName().equals(name)) {
								hasArchive= true;
							}
						}
					}
					if (shouldHaveDependencies) {
						assertTrue("Exported WAR missing classpath dependency Jar " + name, hasArchive);  					
					} else {
						assertFalse("Exported WAR has unexpected classpath dependency Jar " + name, hasArchive);
					}
				}
				List webInfClasses = warFile.getClasses();
				it = webInfClasses.iterator();
				boolean hasNestedTest = false;
				while (it.hasNext()) {
					Object o = it.next();
					if (o instanceof FileImpl && ((FileImpl)o).getURI().toString().equals("WEB-INF/classes/nested/test")) {
						hasNestedTest = true;
					}
				}
				if (shouldHaveDependencies) {
					assertTrue("Exported WAR missing nested class folder file", hasNestedTest);  					
				} else {
					assertFalse("Exported WAR has unexpected nested class folder file", hasNestedTest);
				}
			}
		} finally {
			if (webEdit !=null)
				webEdit.dispose();
			if(warFile != null){
				warFile.close();
			}
		}    	
    }
    
    public void testWebPublishJ2EE() throws Exception {
        testWebPublish(false);
    }
    
    public void testWebPublishJEE5() throws Exception {
        testWebPublish(true);
    }
    
    public void testWebContainerPublishJEE5() throws Exception {
        testWebContainerPublish(true);
    }
    
    private void testWebPublish(boolean JEE5) throws Exception {

    	// create the web and utility projects
    	IVirtualComponent webComp = createProjects(JEE5);
    	
    	// verify that the exported WAR WEB-INF/lib does not contain the cp container jars from the Utility
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	verifyPublishedWebInfLibs(webComp, archiveNames, false);
    	
    	// add the cp dependency attribute to the cp container in the util
    	addDependencyAttribute(true);
    	
    	// verify that the exported WAR WEB-INF/lib does contain the cp container jars from the Utility
    	verifyPublishedWebInfLibs(webComp, archiveNames, true);
    }
    
    private void testWebContainerPublish(boolean JEE5) throws Exception {
    	IVirtualComponent webComp = createWebProject(JEE5);
    	
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	
    	verifyPublishedWebInfContainer(webComp, archiveNames, false);
    	
    	addWebInfContainerDependencyAttribute(false);

    	verifyPublishedWebInfContainer(webComp, archiveNames, true);
    }
    
    private void verifyPublishedWebInfLibs(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveDependencies) throws Exception {
    	J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(comp.getProject(), comp);
    	
		try {
			IModuleResource[] members = deployable.members();
			assertTrue(members.length==2);
			
			for (int i=0; i<members.length; i++) {
				String name = members[i].getName();
				if (name.equals("WEB-INF")) {
					IModuleResource[] webInf = ((IModuleFolder)members[i]).members();
					for (int j=0; j<webInf.length; j++) {
						IModuleResource webResource = webInf[j];
						assertTrue(webResource.getModuleRelativePath().toString().equals("WEB-INF"));
						if (webResource.getName().equals("lib")) {
							IModuleResource[] webresMembers = ((IModuleFolder)webResource).members();
							Iterator it = archiveNames.iterator();
							while (it.hasNext()) {
								String archiveName = (String) it.next();
								boolean hasArchive = false;
								for (int k = 0; k < webresMembers.length; k++) {
									String localName = webresMembers[k].getName();
									if (localName.equals(archiveName)) {
										hasArchive= true;
									}
								}
								if (shouldHaveDependencies) {
									assertTrue("Published WAR missing classpath dependency Jar " + archiveName, hasArchive);  					
								} else {
									assertFalse("Published WAR has unexpected classpath dependency Jar " + archiveName, hasArchive);
								}
							}
						} else if (webResource.getName().equals("classes")) {
							IModuleResource[] webresMembers = ((IModuleFolder)webResource).members();
							for (j = 0; j < webresMembers.length; j++) {
								if (webresMembers[j].getName().equals("nested")) {
									IModuleResource[] nestedMembers = ((IModuleFolder)webresMembers[j]).members();
									assertTrue("Published WAR should have have nested folder without class folder dependency", shouldHaveDependencies);
									boolean hasNestedTest = false;
									if (nestedMembers.length == 1 && nestedMembers[0].getName().equals("test")) {
										hasNestedTest = true;
									}
									if (shouldHaveDependencies) {
										assertTrue("Published WAR missing nested class folder file", hasNestedTest);  					
									} else {
										assertFalse("Published WAR has unexpected nested class folder file", hasNestedTest);
									}
								}
							}
						}
					}
				} 
			}

		} catch (CoreException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}    	
    }
    
    private void verifyPublishedWebInfContainer(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveDependencies) throws Exception {
    	J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(comp.getProject(), comp);
    	
		try {
			IModuleResource[] members = deployable.members();
			assertTrue(members.length==2);
			
			for (int i=0; i<members.length; i++) {
				String name = members[i].getName();
				if (name.equals("WEB-INF")) {
					IModuleResource[] webInf = ((IModuleFolder)members[i]).members();
					for (int j=0; j<webInf.length; j++) {
						IModuleResource webResource = webInf[j];
						assertTrue(webResource.getModuleRelativePath().toString().equals("WEB-INF"));
						if (webResource.getName().equals("lib")) {
							IModuleResource[] webresMembers = ((IModuleFolder)webResource).members();
							Iterator it = archiveNames.iterator();
							while (it.hasNext()) {
								String archiveName = (String) it.next();
								boolean hasArchive = false;
								for (int k = 0; k < webresMembers.length; k++) {
									String localName = webresMembers[k].getName();
									if (localName.equals(archiveName)) {
										hasArchive= true;
									}
								}
								if (shouldHaveDependencies) {
									assertTrue("Published WAR missing classpath dependency Jar " + archiveName, hasArchive);  					
								} else {
									assertFalse("Published WAR has unexpected classpath dependency Jar " + archiveName, hasArchive);
								}
							}
						} 
					}
				} 
			}

		} catch (CoreException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}    	
    }
    
    private IVirtualComponent createProjects(boolean JEE5) throws Exception {
    	// create a Utility project
    	final IProject util = ProjectUtil.createUtilityProject(UTIL_PROJECT, null, true);
    	final IJavaProject utilJava = JavaCore.create(util);
    	final IVirtualComponent utilComp = ComponentCore.createComponent(util);

    	// create a Web project
    	// create a Web project
    	int version = J2EEVersionConstants.SERVLET_2_5;
    	if (!JEE5) {
    		version = J2EEVersionConstants.SERVLET_2_4;
    	}
    	final IProject webProject = ProjectUtil.createWebProject(WEB_PROJECT, null, version, true);
    	final IJavaProject webJavaProject = JavaCore.create(webProject);
    	final IVirtualComponent webComp = ComponentCore.createComponent(webProject);
    	// create a new "bin" folder
    	final IPath binPath = new Path("bin");
    	final IFolder webBin = webProject.getFolder(binPath);
    	webBin.create(true, true, null);
    	// create a nested folder
    	final IFolder nested = webBin.getFolder("nested");
    	nested.create(true, true, null);
    	// add a file
    	nested.getFile("test").create(new java.io.StringBufferInputStream("blah"), true, null);
    	// add as a library cp entry
    	ClasspathDependencyTestUtil.addLibraryEntry(webJavaProject, webBin.getFullPath(), true);
    	
    	// add a dependency from the Web to the Utility
    	DependencyCreationUtil.createWebLibDependency(webProject, util); 
    	
    	// add a cp dependency to the Utility
    	ClasspathDependencyTestUtil.addCustomClasspathContainer(utilJava);
    	
    	return webComp;
    }
    
    private IVirtualComponent createWebProject(boolean JEE5) throws Exception {
    	// create a Web project
    	int version = J2EEVersionConstants.SERVLET_2_5;
    	if (!JEE5) {
    		version = J2EEVersionConstants.SERVLET_2_4;
    	}
    	final IProject webProject = ProjectUtil.createWebProject(WEB_PROJECT, null, version, true);
    	final IJavaProject webJavaProject = JavaCore.create(webProject);
    	final IVirtualComponent webComp = ComponentCore.createComponent(webProject);
 	
    	// add a cp dependency to the Utility
    	ClasspathDependencyTestUtil.addCustomClasspathContainer(webJavaProject);
    	
    	return webComp;
    }
    
    /**
     * 
     * @param verifyClasspathDependencies - true if you want to immediately verify that
     * the classpath dependencies were added.  Set to false if you want to verify this at
     * a later time (such as thru a members call in export or publish)  
     * @throws Exception
     */
    private void addDependencyAttribute(boolean verifyClasspathDependencies) throws Exception {
    	final IProject util = ProjectUtil.getProject(UTIL_PROJECT);
    	final IJavaProject utilJava = JavaCore.create(util);
    	final IVirtualComponent utilComp = ComponentCore.createComponent(util);
    	final IProject web = ProjectUtil.getProject(WEB_PROJECT);
    	final IJavaProject webJava = JavaCore.create(web);
    	final IPath fullWebBinPath = web.getFullPath().append("bin");
    	final IVirtualComponent webComp = ComponentCore.createComponent(web);
    	
    	final Set entryPaths = new HashSet();
    	entryPaths.add(ClasspathDependencyTestUtil.CUSTOM_CLASSPATH_CONTAINER);
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(utilJava, entryPaths);
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(utilJava);
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(utilComp);
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, util.getName(), entry);
    	
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(utilJava);
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(utilJava, entryPaths);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(utilComp, archiveNames);
    	
    	entryPaths.clear();
    	entryPaths.add(fullWebBinPath);
    	// verify that "bin" in the web project is a potential entry
    	entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(webJava, entryPaths);
    	// verify that no entries have the classpath attribute
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(webJava);
    	// verify that there are no classpath dependencies
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(webComp);
    	
    	// add the dependency attribute to "bin"
    	entry = (IClasspathEntry) entries.get(0);
    	UpdateClasspathAttributeUtil.addDependencyAttribute(null, web.getName(), entry);
    	// should no longer have potential entries
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(webJava);
    	// verify that "bin" has the attribute
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(webJava, entryPaths);
    	// verify that "bin" is a dependency
    	archiveNames.clear();
    	archiveNames.add(fullWebBinPath.toString());
    	ClasspathDependencyTestUtil.verifyClasspathDependencies(webComp, archiveNames);
    }
    
    /**
     * 
     * @param verifyClasspathDependencies - true if you want to immediately verify that
     * the classpath dependencies were added.  Set to false if you want to verify this at
     * a later time (such as thru a members call in export or publish)  
     * @throws Exception
     */
    private void addWebInfContainerDependencyAttribute(boolean verifyClasspathDependencies) throws Exception {

    	final IProject web = ProjectUtil.getProject(WEB_PROJECT);
    	final IJavaProject webJava = JavaCore.create(web);
    	final IVirtualComponent webComp = ComponentCore.createComponent(web);
    	
    	final Set entryPaths = new HashSet();
    	entryPaths.add(ClasspathDependencyTestUtil.CUSTOM_CLASSPATH_CONTAINER);
    	// verify that "bin" and the custom cp container are potential entries
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(webJava, entryPaths);
    	// verify that no entries have the classpath attribute
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(webJava);
    	// verify that there are no classpath dependencies
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(webComp);
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);

    	// add the dependency attribute to "bin" and the cp container    	
    	for (Object o: entries) {
    		UpdateClasspathAttributeUtil.addDependencyAttribute(null, web.getName(), (IClasspathEntry) o);
    	}
    	// should no longer have potential entries
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(webJava);
    	// verify that "bin" and the cp container have the attribute
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(webJava, entryPaths);
    	// verify that "bin" and the cp container are dependencies
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	if (verifyClasspathDependencies)
    		ClasspathDependencyTestUtil.verifyClasspathDependencies(webComp, archiveNames);
    }
    
}
