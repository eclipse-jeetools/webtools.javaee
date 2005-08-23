package org.eclipse.jst.j2ee.archive.test;


import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveInit;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfigRegister;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigFactory;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;


public class LooseArchiveTests extends AbstractArchiveTest {

	/** The current working directory */
	protected String cwd;
	protected URI looseConfigLoc;
	protected URI looseAppLoc;
	protected String looseModuleWorkspace;
	protected String looseEARUri;
	
	protected static final String EJB_JAR_URI = "LooseEAREjb.jar";
	protected static final String WAR_URI = "LooseEARWeb.war";
	protected static final String UTIL_JAR_URI = "util.jar";
	protected static final String WEBLIB_URI = "WEB-INF/lib/library.jar";
	protected static final String WEBLIB_FULL_URI = "WEB-INF/lib/library.jar";
	protected static final String OUTPUT_EAR_FILE_URI = AutomatedBVT.baseDirectory +"testOutput/LooseArchiveTests/LooseApp.ear";
	protected static char sep = java.io.File.separatorChar;
	/**
	 * Constructor for LooseArchiveTests.
	 * @param name
	 */
	public LooseArchiveTests(String name) {
		super(name);
	}
	
	public void testLooseConfigCreation() throws Exception {
		Resource resource = Resource.Factory.Registry.INSTANCE.getFactory(looseConfigLoc).createResource(looseConfigLoc);
		resource.getContents().add(createLooseConfig());
		resource.save(new java.util.HashMap());
	}
	
	public void testLooseAppCreation() throws Exception {
		Resource resource = Resource.Factory.Registry.INSTANCE.getFactory(looseAppLoc).createResource(looseAppLoc);
		resource.getContents().add(createLooseApplication());
		resource.save(new java.util.HashMap());
	}
	
	public void testLooseConfigOpenAndSave() throws Exception {
		System.setProperty(LooseConfigRegister.LOOSE_CONFIG_PROPERTY, looseConfigLoc.toString());
		EARFile ear = getArchiveFactory().openEARFile(looseEARUri);
		verify(ear);
		//printClasspaths(ear);
		ear.saveAs(OUTPUT_EAR_FILE_URI);
		ear.close();
		ear = getArchiveFactory().openEARFile(OUTPUT_EAR_FILE_URI);
		verify(ear);
		ear.close();
			
	}
	

	
	public void testLooseConfigUnimodeOpenAndSave() throws Exception {
		System.setProperty(LooseConfigRegister.LOOSE_CONFIG_PROPERTY, "");

		LooseConfigRegister.singleton().addLooseMapping(looseEARUri, looseAppLoc.toString());
		EARFile ear = getArchiveFactory().openEARFile(looseEARUri);
		verify(ear);
		//printClasspaths(ear);
		ear.saveAs(OUTPUT_EAR_FILE_URI);
		ear.close(); 

		ear = getArchiveFactory().openEARFile(OUTPUT_EAR_FILE_URI);
		verify(ear);
		ear.close();
			
	}
	
	public void testLooseConfigOpenAndReOpen() throws Exception {
		System.setProperty(LooseConfigRegister.LOOSE_CONFIG_PROPERTY, looseConfigLoc.toString());
		EARFile ear = getArchiveFactory().openEARFile(looseEARUri);
		ear.close();
		ear.reopen();
		List jars = ear.getArchiveFiles();
		for (int i = 0; i < jars.size(); i++) {
			Archive archive = (Archive) jars.get(i);
			archive.close();
			archive.reopen(ear);
		}
		ear.close();
			
	}

	protected void verify(EARFile ear) throws Exception {
		verifyModules(ear);
		verifyModuleRefs(ear);
		verifyUtilAndLib(ear);
	}

	/**
	 * Another level of verification that for each module, the module files exists
	 * and the deployment descriptor can be read
	 */
	protected void verifyModuleRefs(EARFile ear) throws Exception {
		List moduleRefs = ear.getModuleRefs();
		int size = moduleRefs.size();
		assertEquals("Wrong number of modules", 2, size);
		for (int i = 0; i < size; i++) {
			ModuleRef ref = (ModuleRef) moduleRefs.get(i);
			assertNotNull("ModuleRef should have a module file:"+ref.getModule().getUri(), ref.getModuleFile());
			assertNotNull("Deployment descriptor should not be null", ref.getDeploymentDescriptor());	
		}
	}
	
	/**
	 * Verify that the util jar in the ear and the library in the war exist and are not empty
	 */
	protected void verifyUtilAndLib(EARFile ear) throws Exception {
		verifyJAR(ear, UTIL_JAR_URI);
		verifyJAR((Archive)ear.getFile(WAR_URI), WEBLIB_FULL_URI);
	
	}
	
	protected void verifyJAR(Archive parent, String uri) throws Exception {
		assertTrue("Should contain JAR: "+uri, parent.containsFile(uri));
		Archive jar = (Archive)parent.getFile(uri);
		//at lease one file plus the manifest
		assertTrue("JAR should contain a class file: "+uri, containsClassFile(jar));
	}
	
	protected boolean containsClassFile(Archive jar) {
		List files = jar.getFiles();
		for (int i = 0; i < files.size(); i++) {
			File aFile = (File) files.get(i);
			if (aFile.getURI().endsWith(".class"));
				return true;	
		}
		return false;
	}
	
	/**
	 * Make sure {@link Container#containsFile(String) works for loose modules
	 */
	public void verifyModules(EARFile ear) throws Exception {
		List modules = ear.getDeploymentDescriptor().getModules();
		int size = modules.size();
		for (int i = 0; i < modules.size(); i++) {
			Module m = (Module) modules.get(i);
			assertTrue("EAR file should contain module file: "+m.getUri(), ear.containsFile(m.getUri()));
		}
		assertEquals("Wrong number of modules", 2, size);
		for (int i = 0; i < modules.size(); i++) {
			Module m = (Module) modules.get(i);
			assertTrue("EAR file should contain module file: "+m.getUri(), ear.containsFile(m.getUri()));
		}
	}
	
	protected LooseConfiguration createLooseConfig() {
		LooseConfiguration config = looseConfigFactory().createLooseConfiguration();
		config.getApplications().add(createLooseApplication());
		return config;
	}
	
	protected LooseApplication createLooseApplication() {
		LooseApplication app = looseConfigFactory().createLooseApplication();
		app.setUri(looseEARUri);
		app.setResourcesPath(looseEARUri);
		app.setBinariesPath(looseEARUri);
		List looseArchives = app.getLooseArchives();
		looseArchives.add(createLooseEJB());
		looseArchives.add(createLooseWEB());
		looseArchives.add(createLooseUtil());
		return app;
	}
	
	protected LooseModule createLooseEJB() {
		LooseModule mod = looseConfigFactory().createLooseModule();
		mod.setUri(EJB_JAR_URI);
		String absPath = looseModuleWorkspace+sep+"LooseEAREjb"+sep+"bin";
		mod.setBinariesPath(absPath);
		mod.setResourcesPath(absPath);
		return mod;
	}
	
	protected LooseModule createLooseWEB() {
		LooseWARFile mod = looseConfigFactory().createLooseWARFile();
		mod.setUri(WAR_URI);
		String absPath = looseModuleWorkspace+sep+"LooseEARWeb"+sep+"webApplication";
		mod.setBinariesPath(absPath);
		mod.setResourcesPath(absPath);
		mod.getLooseLibs().add(createLooseWebLib());
		return mod;
	}
	
	protected LooseLibrary createLooseUtil() {
		LooseLibrary lib = looseConfigFactory().createLooseLibrary();
		lib.setUri(UTIL_JAR_URI);
		String absPath = looseModuleWorkspace+sep+"LooseJavaUtil"+sep+"bin";
		lib.setBinariesPath(absPath);
		lib.setResourcesPath(absPath);
		return lib;
	}
	
	protected LooseLibrary createLooseWebLib() {
		LooseLibrary lib = looseConfigFactory().createLooseLibrary();
		lib.setUri(WEBLIB_URI);
		String absPath = looseModuleWorkspace+sep+"LooseJavaWebLib"+sep+"bin";
		lib.setBinariesPath(absPath);
		lib.setResourcesPath(absPath);
		return lib;
	}
	
	protected LooseconfigFactory looseConfigFactory() {
		return LooseconfigPackage.eINSTANCE.getLooseconfigFactory();
	}
	
	protected void printClasspaths(EARFile ear) {
		List archives = ear.getArchiveFiles();
		for (int i = 0; i < archives.size(); i++) {
			Archive archive = (Archive) archives.get(i);
			System.out.println("Local runtime classpath for: "+archive.getURI());
			RuntimeClasspathEntry[] entries = archive.getLocalRuntimeClassPath();
			for (int j = 0; j < entries.length; j++) {
				System.out.println(entries[j]);
			}
			System.out.println("Full runtime classpath for: "+archive.getURI());
			entries = archive.getFullRuntimeClassPath();
			for (int j = 0; j < entries.length; j++) {
				System.out.println(entries[j]);
			}
		}
	}
	

	public static void main(String[] args) {
		String[] className = { "com.ibm.etools.archive.test.LooseArchiveTests", "-noloading" };
		TestRunner.main(className);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("LooseArchiveTests");
		suite.addTest(new LooseArchiveTests("testLooseConfigCreation"));
		suite.addTest(new LooseArchiveTests("testLooseAppCreation"));
		suite.addTest(new LooseArchiveTests("testLooseConfigOpenAndSave"));
		suite.addTest(new LooseArchiveTests("testLooseConfigOpenAndReOpen"));
		suite.addTest(new LooseArchiveTests("testLooseConfigUnimodeOpenAndSave"));
		return suite;
	}
		/*
		 * @see TestCase#setUp()
		 */
		protected void setUp() throws Exception {
			super.setUp();
			ArchiveInit.init();
			LooseConfigRegister.singleton().flush();
			cwd = AutomatedBVT.baseDirectory;
			looseModuleWorkspace = cwd+"loose_module_workspace";
			looseConfigLoc = URI.createFileURI(looseModuleWorkspace+sep+".metadata"+sep+".plugins"+sep+"com.ibm.etools.j2ee"+sep+"looseConfig.xmi");
			looseAppLoc = URI.createFileURI(looseModuleWorkspace+sep+".metadata"+sep+".plugins"+sep+"com.ibm.etools.j2ee"+sep+"looseApp.xmi");
			looseEARUri = ArchiveUtil.getOSUri(looseModuleWorkspace+sep+"LooseEAR");
		}

}
