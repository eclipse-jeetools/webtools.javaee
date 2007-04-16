package org.eclipse.jst.j2ee.classpath.tests;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.internal.ModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wst.server.core.util.ProjectModule;

import junit.framework.Test;
import junit.framework.TestSuite;

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
        suite.addTest(new ClasspathDependencyEARTests("testEARExport"));
        suite.addTest(new ClasspathDependencyEARTests("testEARPublish"));
        return suite;
    }
    
    public void testEARExport() throws Exception {

    	// create the EAR and module projects
    	createProjects();
    	final IProject earProject = ProjectUtil.getProject(EAR_PROJECT);
    	final IVirtualComponent earComp = ComponentCore.createComponent(earProject);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	
    	// verify that the exported EAR does not contain the cp container Jars from the utility
    	// and that the utility MANIFEST classpath does not include entries for the cp container
    	// jars
    	verifyExportedEAR(earComp, archiveNames, false);

    	// add the attribute to the cp container in the utility project
    	addDependencyAttribute();
    	
    	// verify that the exported EAR does not contain the cp container Jars from the utility
    	// and that the utility MANIFEST classpath does include entries for the cp container
    	// jars
    	verifyExportedEAR(earComp, archiveNames, true);
    }
    
    private void verifyExportedEAR(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveArchives) throws Exception {
		EARArtifactEdit earEdit = null;
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
					if (shouldHaveArchives) {
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
							if (shouldHaveArchives) {
								assertTrue("Utility project MANIFEST.MF classpath in exported EAR is entry for dependency Jar " + name, isOnCP);  					
							} else {
								assertTrue("Utility project MANIFEST.MF classpath in exported EAR has unexpected entry for dependency Jar " + name, isOnCP);  					
							}
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
    
    public void testEARPublish() throws Exception {

    	// create the EAR and module projects
    	createProjects();
    	final IProject earProject = ProjectUtil.getProject(EAR_PROJECT);
    	final IVirtualComponent earComp = ComponentCore.createComponent(earProject);
    	final Set archiveNames = new HashSet();
    	archiveNames.add(ClasspathDependencyTestUtil.TEST1_JAR);
    	archiveNames.add(ClasspathDependencyTestUtil.TEST2_JAR);
    	
    	// verify that the exported EAR does not contain the cp container Jars from the utility
    	// and that the utility MANIFEST classpath does not include entries for the cp container
    	// jars
    	verifyPublishedEAR(earComp, archiveNames, false);

    	// add the attribute to the cp container in the utility project
    	addDependencyAttribute();
    	
    	// verify that the exported EAR does not contain the cp container Jars from the utility
    	// and that the utility MANIFEST classpath does include entries for the cp container
    	// jars
    	verifyPublishedEAR(earComp, archiveNames, true);
    }
    
    private void verifyPublishedEAR(final IVirtualComponent comp, final Set archiveNames, final boolean shouldHaveArchives) throws Exception {
    	// verify that the published EAR contains the cp container jars from the Utility
    	J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(comp.getProject(), comp);
		try {
			IModuleResource[] members = deployable.members();
			Iterator it = archiveNames.iterator();						
			while (it.hasNext()) {
				String name = (String) it.next();
				boolean hasArchive = false;
				for (int i=0; i<members.length; i++) {
					if (members[i].getName().equals(name)) {
						hasArchive = true;
					}
				}
				if (shouldHaveArchives) {
					assertTrue("Published EAR missing classpath dependency Jar " + name, hasArchive);  					
				} else {
					assertFalse("Published EAR has unexpected classpath dependency Jar " + name, hasArchive);
				}
			}
			
			IModule utilModule = null;
			IModule[] childModules = deployable.getChildModules();
			for (int i=0; i < childModules.length; i++) {
				if (childModules[i].getName().equals(UTIL_PROJECT)) {
					utilModule = childModules[i];
				}
			}
			assertNotNull("Missing entry for utility project", utilModule);
			J2EEFlexProjDeployable projectModule =(J2EEFlexProjDeployable) utilModule.loadAdapter(ProjectModule.class, null);
			IModuleResource[] moduleMembers = projectModule.members();
			ArchiveManifest manifest = null;
			boolean foundMetaInf = false;
			for (int i=0; i< moduleMembers.length; i++) {
				String name = moduleMembers[i].getName();
				if (name.equals("META-INF")) {
					foundMetaInf = true;
					IModuleResource manifestResource= ((ModuleFolder)moduleMembers[i]).members()[0];
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
				assertTrue("members() failed to return META-INF for utility project module in published EAR", foundMetaInf);
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
				if (shouldHaveArchives) {
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
    
    
    private IVirtualComponent createProjects() throws Exception {
    	// create a Utility project
    	final IProject util = ProjectUtil.createUtilityProject(UTIL_PROJECT, EAR_PROJECT, true);
    	IFolder output = util.getFolder("build").getFolder("classes");
    	final IJavaProject utilJava = JavaCore.create(util);
    	final IVirtualComponent utilComp = ComponentCore.createComponent(util);

    	// create a Web project
    	final IProject webProject = ProjectUtil.createWebProject(WEB_PROJECT, EAR_PROJECT, true);
    	final IJavaProject webJavaProject = JavaCore.create(webProject);
    	final IVirtualComponent webComp = ComponentCore.createComponent(webProject);
    	
    	// add a dependency from the Web to the Utility
    	DependencyCreationUtil.createModuleDependency(webProject, util); 
    	
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
