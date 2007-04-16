package org.eclipse.jst.j2ee.classpath.tests;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.classpath.tests.util.ClasspathDependencyTestUtil;
import org.eclipse.jst.j2ee.classpathdep.UpdateClasspathAttributeUtil;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.internal.ModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;

import junit.framework.Test;
import junit.framework.TestSuite;

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
        suite.addTest(new ClasspathDependencyWebTests("testWebExport"));
        suite.addTest(new ClasspathDependencyWebTests("testWebPublish"));
        return suite;
    }
    
    public void testWebExport() throws Exception {

    	// create the Web and utility projects
    	IVirtualComponent webComp = createProjects();
    	
    	// verify that the exported WAR WEB-INF/lib does not contain the cp container jars from the Utility
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	verifyExportedWebInfLibs(webComp, archiveNames, false);
    	
    	// add the cp dependency attribute to the cp container in the util
    	addDependencyAttribute();
    	
    	// verify that the exported WAR WEB-INF/lib does contain the cp container jars from the Utility
    	verifyExportedWebInfLibs(webComp, archiveNames, true);
    }
    
    private void verifyExportedWebInfLibs(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveArchives) throws Exception {
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
					if (shouldHaveArchives) {
						assertTrue("Exported WAR missing classpath dependency Jar " + name, hasArchive);  					
					} else {
						assertFalse("Exported WAR has unexpected classpath dependency Jar " + name, hasArchive);
					}
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
    
    public void testWebPublish() throws Exception {

    	// create the web and utility projects
    	IVirtualComponent webComp = createProjects();
    	
    	// verify that the exported WAR WEB-INF/lib does not contain the cp container jars from the Utility
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	verifyPublishedWebInfLibs(webComp, archiveNames, false);
    	
    	// add the cp dependency attribute to the cp container in the util
    	addDependencyAttribute();
    	
    	// verify that the exported WAR WEB-INF/lib does contain the cp container jars from the Utility
    	verifyPublishedWebInfLibs(webComp, archiveNames, true);
    }
    
    private void verifyPublishedWebInfLibs(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveArchives) throws Exception {
    	// verify that the published WAR's WEB-INF/lib contains the cp container jars from the Utility
    	J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(comp.getProject(), comp);
		try {
			IModuleResource[] members = deployable.members();
			assertTrue(members.length==2);
			
			for (int i=0; i<members.length; i++) {
				String name = members[i].getName();
				if (name.equals("WEB-INF")) {
					IModuleResource[] webInf = ((ModuleFolder)members[i]).members();
					for (int j=0; j<webInf.length; j++) {
						IModuleResource webResource = webInf[j];
						assertTrue(webResource.getModuleRelativePath().toString().equals("WEB-INF"));
						if (webResource.getName().equals("lib")) {
							IModuleResource[] webresMembers = ((ModuleFolder)webResource).members();
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
								if (shouldHaveArchives) {
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
    
    private IVirtualComponent createProjects() throws Exception {
    	// create a Utility project
    	final IProject util = ProjectUtil.createUtilityProject(UTIL_PROJECT, null, true);
    	final IJavaProject utilJava = JavaCore.create(util);
    	final IVirtualComponent utilComp = ComponentCore.createComponent(util);

    	// create a Web project
    	final IProject webProject = ProjectUtil.createWebProject(WEB_PROJECT, null, true);
    	final IJavaProject webJavaProject = JavaCore.create(webProject);
    	final IVirtualComponent webComp = ComponentCore.createComponent(webProject);
    	
    	// add a dependency from the Web to the Utility
    	DependencyCreationUtil.createWebLibDependency(webProject, util); 
    	
    	// add a cp dependency to the Utility
    	ClasspathDependencyTestUtil.addCustomClasspathContainer(utilJava);
    	
    	return webComp;
    }
    
    private void addDependencyAttribute() throws Exception {
    	final IProject util = ProjectUtil.getProject(UTIL_PROJECT);
    	final IJavaProject utilJava = JavaCore.create(util);
    	final IVirtualComponent utilComp = ComponentCore.createComponent(util);
    	
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
    }
}
