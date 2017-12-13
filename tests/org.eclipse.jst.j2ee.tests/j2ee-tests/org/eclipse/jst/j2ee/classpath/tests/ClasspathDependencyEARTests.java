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

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.ArchiveImpl;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyEnablement;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.IModuleFile;
import org.eclipse.wst.server.core.model.IModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wst.server.core.util.ProjectModule;

/**
 * Tests export and publish behavior for classpath component dependencies on the module projects associated with an EAR.
 */
public class ClasspathDependencyEARTests extends AbstractTests {

	private static final String UTIL_PROJECT = "TestUtil";
	private static final String WEB_PROJECT = "TestWeb";
	private static final String EAR_PROJECT = "TestEAR";
	
    private ClasspathDependencyEARTests(final String name) {
        super(name);
    }
    
    public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Classpath Dependency EAR Tests" );
        suite.addTest(new ClasspathDependencyEARTests("testEARExportJ2EE"));
        //suite.addTest(new ClasspathDependencyEARTests("testEARExportJEE5"));
        suite.addTest(new ClasspathDependencyEARTests("testEARPublishJ2EE"));
        suite.addTest(new ClasspathDependencyEARTests("testEARPublishJEE5"));
        suite.addTest(new ClasspathDependencyEARTests("testEARLibPublishJEE5"));
        return suite;
    }
    
    public void testEARExportJ2EE() throws Exception {
    	testEARExport(false);
    }
    
    public void testEARExportJEE5() throws Exception {
    	testEARExport(true);
    }

    // XXX TODO need to modify test logic to work with new JEE5 model provider logic rather than just EARArtifactEdit
    private void testEARExport(boolean JEE5) throws Exception {
    	// create the EAR and module projects
    	createProjects(JEE5);
    	final IProject earProject = ProjectUtil.getProject(EAR_PROJECT);
    	final IVirtualComponent earComp = ComponentCore.createComponent(earProject);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	
    	// verify that the exported EAR does not contain the cp container Jars from the utility,
    	// that the utility MANIFEST classpath does not include entries for the cp container jars and
    	// that the contents of the util and web "bin" class folders are not included in the exported archives.
    	verifyExportedEAR(earComp, archiveNames, false);

    	// add the attribute to the cp container and "bin" class folder in the utility project and to the "bin" class
    	// folder in the web project
    	addDependencyAttribute(true);
    	
    	// verify that the exported EAR does contain the cp container Jars from the utility,
    	// that the utility MANIFEST classpath includes entries for the cp container
    	// jars, that the utility includes the contents of the "bin" class folder and that the web's WEB-INF/classes
    	// includes the contents of the web's "bin" class folder.
    	verifyExportedEAR(earComp, archiveNames, true);
    }
    
    private void verifyExportedEAR(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveDependencies) throws Exception {
		EnterpriseArtifactEdit earEdit = null;
		EARFile earFile = null;
		try {
			earEdit = EARArtifactEdit.getEARArtifactEditForRead(comp);
			if (earEdit != null) {
				earFile = (EARFile) earEdit.asArchive(true);
				List archives = earFile.getArchiveFiles();
				Iterator it = archiveNames.iterator();
				while (it.hasNext()) {
					String name = (String) it.next();
					boolean hasArchive = false;
					for (int i = 0; i < archives.size(); i++) {
						File archive = (File) archives.get(i);
						if (archive.isArchive()) {
							if (archive.getName().equals(name)) {
								hasArchive= true;
							}
						}
					}
					if (shouldHaveDependencies) {
						assertTrue("Exported EAR missing classpath dependency Jar " + name, hasArchive);  					
					} else {
						assertFalse("Exported EAR has unexpected classpath dependency Jar " + name, hasArchive);
					}
				}
				for (int i = 0; i < archives.size(); i++) {
					File archive = (File) archives.get(i);
					if (archive.isArchive() && archive.getName().equals(UTIL_PROJECT + ".jar")) {
						ArchiveImpl archiveImpl = (ArchiveImpl) archive;
						String[] cp = archiveImpl.getManifest().getClassPathTokenized();
						while (it.hasNext()) {
							String name = (String) it.next();
							boolean isOnCP = false;
							for (int j = 0; j < cp.length; j++) {
								if (cp[j].equals(name)) {
									isOnCP = true;
								}
							}
							if (shouldHaveDependencies) {
								assertTrue("Exported utility project MANIFEST.MF classpath in exported EAR is entry for dependency Jar " + name, isOnCP);  					
							} else {
								assertFalse("Exported utility project MANIFEST.MF classpath in exported EAR has unexpected entry for dependency Jar " + name, isOnCP);  					
							}
						}
						archiveImpl.getFiles();
						boolean hasTestFile = archiveImpl.containsFile("test");
						if (shouldHaveDependencies) {
							assertTrue("Exported utility project missing file from published/exported class folder", hasTestFile);  					
						} else {
							assertFalse("Exported utility project has unexpected entry for file from non-published/exported class folder", hasTestFile);  					
						}
						
					} else if (archive.isArchive() && archive.getName().equals(WEB_PROJECT + ".war")) {
						ArchiveImpl archiveImpl = (ArchiveImpl) archive;
						archiveImpl.getFiles();
						boolean hasTestFile = archiveImpl.containsFile("WEB-INF/classes/test");
						if (shouldHaveDependencies) {
							assertTrue("Exported web project missing file from published/exported class folder", hasTestFile);  					
						} else {
							assertFalse("Exported web project has unexpected entry for file from non-published/exported class folder", hasTestFile);
						}
					}
				}
			}
		} finally {
			if (earEdit !=null)
				earEdit.dispose();
			if(earFile != null){
				earFile.close();
			}
		}    	
    }
    
    public void testEARPublishJ2EE() throws Exception {
    	testEARPublish(false);
    }
    
    public void testEARPublishJEE5() throws Exception {
    	testEARPublish(true);
    }
    
    public void testEARLibPublishJEE5() throws Exception {
    	testEARLibPublishJEE5(true);
    }
    
    private void testEARPublish(boolean JEE5) throws Exception {

    	// create the EAR and module projects
    	createProjects(JEE5);
    	final IProject earProject = ProjectUtil.getProject(EAR_PROJECT);
    	final IVirtualComponent earComp = ComponentCore.createComponent(earProject);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	
    	// verify that the published EAR does not contain the cp container Jars from the utility,
    	// that the utility MANIFEST classpath does not include entries for the cp container jars and
    	// that the contents of the util and web "bin" class folders are not included in the exported archives.
    	verifyPublishedEAR(earComp, archiveNames, false, JEE5);

    	// add the attribute to the cp container in the utility project
    	addDependencyAttribute(false);
    	
    	// verify that the published EAR does contain the cp container Jars from the utility,
    	// that the utility MANIFEST classpath includes entries for the cp container
    	// jars, that the utility includes the contents of the "bin" class folder and that the web's WEB-INF/classes
    	// includes the contents of the web's "bin" class folder.
    	verifyPublishedEAR(earComp, archiveNames, true, JEE5);
    }
    
    private void testEARLibPublishJEE5(boolean JEE5) throws Exception {
    	IVirtualComponent webComp = createWebProject(JEE5);
    	
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	final IProject earProject = ProjectUtil.getProject(EAR_PROJECT);
    	final IVirtualComponent earComp = ComponentCore.createComponent(earProject);
    	
    	verifyPublishedEARLibRef(earComp, archiveNames, false, JEE5);

    	addEARLibDependencyAttribute(false);
    	
    	verifyPublishedEARLibRef(earComp, archiveNames, true, JEE5);
    }
    
    private void verifyPublishedEAR(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveDependencies, boolean isEE5) throws Exception {
    	
    	// verify that the published EAR contains the cp container jars from the Utility
    	J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(comp.getProject(), comp);
		try {
			IModuleResource[] rootmembers = deployable.members();
			IModuleResource[] members = null;
			if (isEE5) {
				for (int i=0; i<rootmembers.length; i++) {
					String name = rootmembers[i].getName();
					if (name.equals("lib")) {
						members = ((IModuleFolder)rootmembers[i]).members();
					}
				}
			}

			if (members == null)
				members = rootmembers;
				
			Iterator it = archiveNames.iterator();						
			while (it.hasNext()) {
				String name = (String) it.next();
				boolean hasArchive = false;
				for (int i=0; i<members.length; i++) {
					if (members[i].getName().equals(name)) {
						hasArchive = true;
					}
				}
				if (shouldHaveDependencies) {
					assertTrue("Published EAR missing classpath dependency Jar " + name, hasArchive);  					
				} else {
					assertFalse("Published EAR has unexpected classpath dependency Jar " + name, hasArchive);
				}
			}
			
			// get the web and util child modules
			
			IModule utilModule = null;
			IModule webModule = null;
			IModule[] childModules = deployable.getChildModules();
			for (int i=0; i < childModules.length; i++) {
				if (childModules[i].getName().equals(UTIL_PROJECT)) {
					utilModule = childModules[i];
				} else if (childModules[i].getName().equals(WEB_PROJECT)) {
					webModule = childModules[i];
				}
			}
			
			// verify that the util module has MANIFEST.MF classpath references to the cp container jars and has the
			// class folder file

			assertNotNull("Missing entry for utility project", utilModule);

			J2EEFlexProjDeployable projectModule =(J2EEFlexProjDeployable) utilModule.loadAdapter(ProjectModule.class, null);
			IModuleResource[] moduleMembers = projectModule.members();
			ArchiveManifest manifest = null;
			boolean foundMetaInf = false;
			boolean foundTest = false;
			for (int i=0; i< moduleMembers.length; i++) {
				String name = moduleMembers[i].getName();
				if (name.equals("META-INF")) {
					foundMetaInf = true;
					IModuleResource manifestResource= ((IModuleFolder)moduleMembers[i]).members()[0];
					assertTrue(manifestResource.getModuleRelativePath().toString().equals("META-INF"));
					assertTrue("Expected MANIFEST.MF, got " + manifestResource.getName(), manifestResource.getName().equals("MANIFEST.MF"));
					java.io.File manifestFile = (java.io.File) manifestResource.getAdapter(java.io.File.class);
					if (manifestFile == null) {
						manifestFile = ((IFile) manifestResource.getAdapter(IFile.class)).getLocation().toFile();
					}
					assertNotNull(manifestFile);
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(manifestFile);
						manifest = new ArchiveManifestImpl(fis);
					} finally {
						if (fis != null) {
							fis.close();
						}
					}
				} else if (name.equals("test")) {
					if (moduleMembers[i] instanceof IModuleFile) {
						foundTest = true;
					}
				}
			}
			if (!foundMetaInf) {
				assertTrue("members() failed to return META-INF for utility project module in published EAR", foundMetaInf);
			}
			
			if (shouldHaveDependencies) {
				assertTrue("Published utility project missing file from published/exported class folder", foundTest);  					
			} else {
				assertFalse("Published utility project has unexpected entry for file from non-published/exported class folder", foundTest);  					
			}

			assertNotNull("Failed to retrieve MANIFEST.MF from utility project module in published EAR", manifest);
			
			it = archiveNames.iterator();						
			while (it.hasNext()) {
				String name = (String) it.next();
				boolean isOnCP = false;
				String[] cp = manifest.getClassPathTokenized();
				for (int j = 0; j < cp.length; j++) {
					if (cp[j].equals(name)) {
						isOnCP = true;
					}
				}
				if (shouldHaveDependencies && ClasspathDependencyEnablement.isAllowClasspathComponentDependency()) {
					assertTrue("Utility project MANIFEST.MF classpath in published EAR missing entry for dependency Jar " + name, isOnCP);  					
				} else {
					assertFalse("Utility project MANIFEST.MF classpath in published EAR has unexpected entry for dependency Jar " + name, isOnCP);  					
				}
			}
			
			assertNotNull("Missing entry for web project", webModule);
		
			projectModule =(J2EEFlexProjDeployable) webModule.loadAdapter(ProjectModule.class, null);
			moduleMembers = projectModule.members();
			foundTest = false;
			for (int i=0; i< moduleMembers.length; i++) {
				if (moduleMembers[i].getName().equals("WEB-INF")) {
					IModuleResource[] webinfMembers = ((IModuleFolder)moduleMembers[i]).members();
					for (int j = 0; j < webinfMembers.length; j++) {
						if (webinfMembers[j].getName().equals("classes")) {
							IModuleResource[] classesMembers = ((IModuleFolder)webinfMembers[j]).members();
							if (classesMembers.length > 0) {
								IModuleResource test = classesMembers[0];
								if (test.getName().equals("test") && test.getModuleRelativePath().equals(new Path("WEB-INF/classes")) && test instanceof IModuleFile) {
									foundTest = true;
								}
							}
						}
					}
				}
			}
			if (shouldHaveDependencies) {
				assertTrue("Exported web project does not contain class folder file in WEB-INF/classes", foundTest);
			} else {
				assertFalse("Exported Web project should not class folder file in WEB-INF/classes", foundTest);
			}
		} catch (CoreException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}    	
    }
    
private void verifyPublishedEARLibRef(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveDependencies, boolean isEE5) throws Exception {
    	
    	J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(comp.getProject(), comp);
		try {
			IModuleResource[] rootmembers = deployable.members();
			IModuleResource[] members = null;
			if (isEE5) {
				for (int i=0; i<rootmembers.length; i++) {
					String name = rootmembers[i].getName();
					if (name.equals("lib")) {
						members = ((IModuleFolder)rootmembers[i]).members();
					}
				}
			}

			if (members == null)
				members = rootmembers;
			
			Iterator it = archiveNames.iterator();						
			while (it.hasNext()) {
				String name = (String) it.next();
				boolean hasArchive = false;
				for (int i=0; i<members.length; i++) {
					if (members[i].getName().equals(name)) {
						hasArchive = true;
					}
				}
				if (shouldHaveDependencies) {
					assertTrue("Published EAR missing classpath dependency Jar " + name, hasArchive);  					
				} else {
					assertFalse("Published EAR has unexpected classpath dependency Jar " + name, hasArchive);
				}
			}
			
			IModule webModule = null;
			IModule[] childModules = deployable.getChildModules();
			for (int i=0; i < childModules.length; i++) {
				if (childModules[i].getName().equals(WEB_PROJECT)) {
					webModule = childModules[i];
				}
			}
			
			assertNotNull("Missing entry for web project", webModule);

			J2EEFlexProjDeployable projectModule =(J2EEFlexProjDeployable) webModule.loadAdapter(ProjectModule.class, null);
			IModuleResource[] moduleMembers = projectModule.members();
			ArchiveManifest manifest = null;
			boolean foundMetaInf = false;
			for (int i=0; i< moduleMembers.length; i++) {
				String name = moduleMembers[i].getName();
				if (name.equals("META-INF")) {
					foundMetaInf = true;
					IModuleResource manifestResource= ((IModuleFolder)moduleMembers[i]).members()[0];
					assertTrue(manifestResource.getModuleRelativePath().toString().equals("META-INF"));
					assertTrue("Expected MANIFEST.MF, got " + manifestResource.getName(), manifestResource.getName().equals("MANIFEST.MF"));
					java.io.File manifestFile = (java.io.File) manifestResource.getAdapter(java.io.File.class);
					if (manifestFile == null) {
						manifestFile = ((IFile) manifestResource.getAdapter(IFile.class)).getLocation().toFile();
					}
					assertNotNull(manifestFile);
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(manifestFile);
						manifest = new ArchiveManifestImpl(fis);
					} finally {
						if (fis != null) {
							fis.close();
						}
					}
				} 
			}
			if (!foundMetaInf) {
				assertTrue("members() failed to return META-INF for web project module in published EAR", foundMetaInf);
			}
			
			assertNotNull("Failed to retrieve MANIFEST.MF from web project module in published EAR", manifest);
			
			it = archiveNames.iterator();						
			while (it.hasNext()) {
				String name = (String) it.next();
				boolean isOnCP = false;
				String[] cp = manifest.getClassPathTokenized();
				for (int j = 0; j < cp.length; j++) {
					if (cp[j].equals("lib/" + name)) {
						isOnCP = true;
					}
				}
				if (shouldHaveDependencies && ClasspathDependencyEnablement.isAllowClasspathComponentDependency()) {
					assertTrue("Utility project MANIFEST.MF classpath in published EAR missing entry for dependency Jar " + name, isOnCP);  					
				} else {
					assertFalse("Utility project MANIFEST.MF classpath in published EAR has unexpected entry for dependency Jar " + name, isOnCP);  					
				}
			}
			
		} catch (CoreException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}    	
    }
    
    private IVirtualComponent createProjects(boolean JEE5) throws Exception {

    	// create a Web project
    	int version = J2EEVersionConstants.SERVLET_2_5;
    	if (!JEE5) {
    		version = J2EEVersionConstants.SERVLET_2_4;
    	}
    	final IProject webProject = ProjectUtil.createWebProject(WEB_PROJECT, EAR_PROJECT, version, true);
    	final IJavaProject webJavaProject = JavaCore.create(webProject);
    	final IVirtualComponent webComp = ComponentCore.createComponent(webProject);
    	// create a new "bin" folder
    	final IPath binPath = new Path("bin");
    	IFolder webBin = webProject.getFolder(binPath);
    	webBin.create(true, true, null);
    	// add a file
    	webBin.getFile("test").create(new java.io.StringBufferInputStream("blah"), true, null);
    	// add as a library cp entry
    	ClasspathDependencyTestUtil.addLibraryEntry(webJavaProject, webBin.getFullPath(), true);
    	
    	// create a Utility project
    	final IProject util = ProjectUtil.createUtilityProject(UTIL_PROJECT, EAR_PROJECT, true);
    	IFolder output = util.getFolder("build").getFolder("classes");
    	final IJavaProject utilJava = JavaCore.create(util);
    	// create a folder "bin"
    	IFolder utilBin = util.getFolder(binPath);
    	utilBin.create(true, true, null);
    	// add a file
    	utilBin.getFile("test").create(new java.io.StringBufferInputStream("blah"), true, null);
    	final IVirtualComponent utilComp = ComponentCore.createComponent(util);
    	// add as a library cp entry
    	ClasspathDependencyTestUtil.addLibraryEntry(utilJava, utilBin.getFullPath(), true);
    	
    	// add a dependency from the Web to the Utility
    	DependencyCreationUtil.createModuleDependency(webProject, util); 
    	
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
    	final IProject webProject = ProjectUtil.createWebProject(WEB_PROJECT, EAR_PROJECT, version, true);
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
     * a later time (such as thru a members call in export or publish).  
     * @throws Exception
     */
    private void addDependencyAttribute(boolean verifyClasspathDependencies) throws Exception {
    	final IProject util = ProjectUtil.getProject(UTIL_PROJECT);
    	final IPath fullUtilBinPath = util.getFullPath().append("bin");
    	final IJavaProject utilJava = JavaCore.create(util);
    	final IVirtualComponent utilComp = ComponentCore.createComponent(util);
    	final IProject web = ProjectUtil.getProject(WEB_PROJECT);
    	final IJavaProject webJava = JavaCore.create(web);
    	final IPath fullWebBinPath = web.getFullPath().append("bin");
    	final IVirtualComponent webComp = ComponentCore.createComponent(web);
    	
    	final Set entryPaths = new HashSet();
    	entryPaths.add(ClasspathDependencyTestUtil.CUSTOM_CLASSPATH_CONTAINER);
    	entryPaths.add(fullUtilBinPath);
    	// verify that "bin" and the custom cp container are potential entries
    	List entries = ClasspathDependencyTestUtil.verifyPotentialClasspathEntries(utilJava, entryPaths);
    	// verify that no entries have the classpath attribute
    	ClasspathDependencyTestUtil.verifyNoClasspathAttributes(utilJava);
    	// verify that there are no classpath dependencies
    	ClasspathDependencyTestUtil.verifyNoClasspathDependencies(utilComp);
    	IClasspathEntry entry = (IClasspathEntry) entries.get(0);

    	// add the dependency attribute to "bin" and the cp container    	
    	for (Object o: entries) {
    		UpdateClasspathAttributeUtil.addDependencyAttribute(null, util.getName(), (IClasspathEntry) o);
    	}
    	// should no longer have potential entries
    	ClasspathDependencyTestUtil.verifyNoPotentialClasspathEntries(utilJava);
    	// verify that "bin" and the cp container have the attribute
    	ClasspathDependencyTestUtil.verifyClasspathAttributes(utilJava, entryPaths);
    	// verify that "bin" and the cp container are dependencies
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	archiveNames.add(fullUtilBinPath.toString());
    	if (verifyClasspathDependencies)
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
    	if (verifyClasspathDependencies)
    		ClasspathDependencyTestUtil.verifyClasspathDependencies(webComp, archiveNames);
    }
    
    /**
     * 
     * @param verifyClasspathDependencies - true if you want to immediately verify that
     * the classpath dependencies were added.  Set to false if you want to verify this at
     * a later time (such as thru a members call in export or publish)  
     * @throws Exception
     */
    private void addEARLibDependencyAttribute(boolean verifyClasspathDependencies) throws Exception {

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
    		UpdateClasspathAttributeUtil.addDependencyAttribute(null, web.getName(), (IClasspathEntry) o, new Path("../lib"));
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
