/*******************************************************************************
 * Copyright (c) 2003, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.defect.tests;

//import java.io.File;
import java.io.File;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.etools.common.test.apitools.ProjectUnzipUtil;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJavaProjectMigrationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaProjectMigrationDataModelProvider;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebImportOperationTest;

public class DefectVerificationTests extends OperationTestCase {

	public static String BASE_DATA_DIRECTORY = System.getProperty("user.dir") + java.io.File.separatorChar + "DefectTestData" + java.io.File.separatorChar;
	private static final String TEST_DATA_PATH = System.getProperty("user.dir") + java.io.File.separatorChar + "TestData" + java.io.File.separatorChar;
	
	
	private static String getDataPath(String suffix) {
		return BASE_DATA_DIRECTORY + "componentLoadAdapterTestData" + java.io.File.separatorChar + suffix;
	}
	
	public static String getFullTestDataPath(String dataPath) {
		try {
			String defectTestDataPath = "DefectTestData" + fileSep + dataPath;
			HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
			if (plugin != null) {
				return ProjectUtility.getFullFileName(plugin, defectTestDataPath);
			}
			return System.getProperty("user.dir") + java.io.File.separatorChar + defectTestDataPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public void _test261508() throws Exception {
		String earFileName = getFullTestDataPath("EARForLibDep.ear");
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
		runAndVerify(model);
	
		IVirtualComponent webComponent = ComponentCore.createComponent(J2EEProjectUtilities.getProject("WebForLibDep"));
		IVirtualReference[] refs = webComponent.getReferences();
		Assert.assertTrue(refs.length == 2);
		for( IVirtualReference ref : refs ){
			IVirtualComponent refComponent = ref.getReferencedComponent();
			if ( refComponent instanceof J2EEModuleVirtualArchiveComponent ){
				J2EEModuleVirtualArchiveComponent j2eeVirtualArchive = (J2EEModuleVirtualArchiveComponent)refComponent;
				Assert.assertTrue( j2eeVirtualArchive.getName().endsWith("EjbTestClient.jar"));
			}
		}
	}
	
	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=130657
	 */
	public void test130657() throws Exception {
		IDataModel model = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
		model.setProperty(IWebFacetInstallDataModelProperties.FACET_PROJECT_NAME, "Test120018");
		model.getDefaultOperation().execute(null, null);

		IVirtualComponent component = ComponentUtilities.getComponent("Test120018");

		IFolder folder = component.getProject().getFolder("imported_classes");
		folder.create(true,true,null);
		IFile fakeClassFile = folder.getFile("Fake.class");
		fakeClassFile.create(new StringBufferInputStream(""),true,null);
		Assert.assertTrue("Class file should have been created in project.",component.getProject().exists(fakeClassFile.getProjectRelativePath()));

		final IVirtualFolder jsrc = component.getRootFolder().getFolder("/WEB-INF/classes");
		jsrc.createLink(folder.getProjectRelativePath(), 0, null);
		Assert.assertEquals("Should be a link for each class in imported_classes.",folder.members().length, jsrc.members().length);
		
		//Export war
		IDataModel dataModel = DataModelFactory.createDataModel(new WebComponentExportDataModelProvider());
		dataModel.setProperty(J2EEComponentExportDataModelProvider.ARCHIVE_DESTINATION, getDataPath("testblah.war"));
		dataModel.setProperty(J2EEComponentExportDataModelProvider.COMPONENT, component);
		dataModel.setBooleanProperty(J2EEComponentExportDataModelProvider.EXPORT_SOURCE_FILES, true);
		dataModel.setBooleanProperty(J2EEComponentExportDataModelProvider.OVERWRITE_EXISTING, true);
		IStatus status = dataModel.getDefaultOperation().execute(null, null);
		Assert.assertEquals("Creating WAR failed " + status.getMessage(), IStatus.OK, status.getSeverity());
		
		IArchive archiveWAR = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(getDataPath("testblah.war")));
		for(int i=0; i<jsrc.members().length; i++) {
			Assert.assertTrue("Archive does not contain resource for each linked imported class.",
				archiveWAR.containsArchiveResource((jsrc.members())[i].getRuntimePath().makeRelative()));
		}
		
		//archiveWAR.
		JavaEEArchiveUtilities.INSTANCE.closeArchive(archiveWAR);
	}


	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=120018
	 */
	public void test120018() throws Exception {
		IDataModel model = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
		model.setProperty(IWebFacetInstallDataModelProperties.FACET_PROJECT_NAME, "Test120018");
		model.getDefaultOperation().execute(null, null);

		IVirtualComponent component = ComponentUtilities.getComponent("Test120018");

		
		IVirtualFolder folder = component.getRootFolder().getFolder("imported_classes");
		folder.create(IResource.NONE, null);
		IPath folderPath = folder.getProjectRelativePath();
	
		final IVirtualFolder jsrc = component.getRootFolder().getFolder("/WEB-INF/classes");
		jsrc.createLink(folder.getProjectRelativePath(), 0, null);
		
		J2EEComponentClasspathUpdater.getInstance().forceUpdate(Collections.singleton(component.getProject()));
		IJobManager manager = Job.getJobManager(); 
		Job [] jobs = manager.find(J2EEComponentClasspathUpdater.MODULE_UPDATE_JOB_NAME);
		if(jobs.length > 0){
			try {
				for (int i = 0; i < jobs.length; i++){
					if(jobs[i].getName().equals(J2EEComponentClasspathUpdater.MODULE_UPDATE_JOB_NAME))
						jobs[i].join();
				}
			} catch (InterruptedException e) {
				Logger.getLogger().log(e);
			}
		}

		IJavaProject javaProject = JavaCore.create(component.getProject());
		IClasspathEntry[] entries = javaProject.getRawClasspath();
		boolean foundImportedClasses = false;
		for (int i = 0; i < entries.length && !foundImportedClasses; i++) {
			if (IClasspathEntry.CPE_CONTAINER == entries[i].getEntryKind()) {
				IClasspathContainer container = JavaCore.getClasspathContainer(entries[i].getPath(), javaProject);
				IClasspathEntry[] containerEntries = container.getClasspathEntries();
				for (int j = 0; j < containerEntries.length && !foundImportedClasses; j++) {
					IPath entryPath = containerEntries[j].getPath().removeFirstSegments(1);
					foundImportedClasses = folderPath.equals(entryPath);
					if (foundImportedClasses) break;
				}
			}

		}
		Assert.assertTrue(foundImportedClasses);
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=105901
	 */
	public void test105901() throws Exception {
		String earFileName = getFullTestDataPath("Collision.ear");
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
		runAndVerify(model);
		IVirtualComponent comp = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
		IVirtualReference[] refs = comp.getReferences();
		assertEquals(3, refs.length);
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=109430
	 */
	public void test109430() throws Exception {
		String earFileName = getFullTestDataPath("EJBLocalAndRemoteRefEARWithClientJars.ear");
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
		List moduleList = (List) model.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);

		runAndVerify(model);
		IVirtualComponent component = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
		EnterpriseArtifactEdit artifactEdit = null;
		try {
			artifactEdit = EARArtifactEdit.getEARArtifactEditForRead(component);
			EARFile earFile = null;
			try {
				earFile = (EARFile) artifactEdit.asArchive(true);
				earFile.getEJBReferences(false, false);
			} finally {
				if (earFile != null) {
					earFile.close();
				}
			}
		} finally {
			if (artifactEdit != null) {
				artifactEdit.dispose();
			}
		}
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=112636
	 */
	public void test112636() throws Exception {
		checkDeploy("BeenThere.ear");
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=112835
	 */
	public void test112835() throws Exception {
		checkDeploy("sib.test.mediations.m5.JsMBR.ear");
	}


	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=121158
	 */
	public void test121158() throws Exception {
		String earFileName = getFullTestDataPath("EAR121158.ear"); //$NON-NLS-1$
		EARFile earFile = null;
		try {
			ArchiveOptions opts = new ArchiveOptions();
			opts.setIsReadOnly(true);
			earFile = CommonarchiveFactory.eINSTANCE.openEARFile(opts, earFileName);

			List moduleList = earFile.getModuleFiles();
			for (int i = 0; i < moduleList.size(); i++) {
				ModuleFile module = (ModuleFile) moduleList.get(i);
				RuntimeClasspathEntry[] entries = module.getFullRuntimeClassPath();
				assertEquals(2, entries.length);
				assertTrue(entries[0].toString().endsWith(module.getURI()));
				assertTrue(entries[1].toString().endsWith("EAR121158Util.jar")); //$NON-NLS-1$
			}

		} finally {
			if (earFile != null && earFile.isOpen()) {
				earFile.close();
				earFile = null;
			}
		}
	}

	protected void checkDeploy(String earName) throws Exception {
		String earFileName = getFullTestDataPath(earName);
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);

		runAndVerify(model);
		IVirtualComponent comp = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
		EnterpriseArtifactEdit earEdit = EARArtifactEdit.getEARArtifactEditForRead(comp);
		EARFile earFile = (EARFile) earEdit.asArchive(false);
		earFile.getEJBReferences(true, true);
		earFile.getEJBReferences(true, false);
		earFile.getEJBReferences(false, true);
		earFile.getEJBReferences(false, false);
		earFile.close();
		earFile = null;

		Thread.sleep(5000);

		String earOutputName = "c:\\temp\\Output" + System.currentTimeMillis() + ".ear";
		IDataModel export = DataModelFactory.createDataModel(new EARComponentExportDataModelProvider());
		export.setProperty(IEARComponentExportDataModelProperties.PROJECT_NAME, comp.getProject().getName());
		export.setProperty(IEARComponentExportDataModelProperties.ARCHIVE_DESTINATION, earOutputName);
		runAndVerify(export);


	}
	
	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=143483
	 */
	public void test143483() throws Exception {
		checkDeploy("undeployed_DefaultApplication.ear");//$NON-NLS-1$
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=144288

	public void test144288() throws Exception {
		String earName = "WorkAreaFvtApp.ear";//$NON-NLS-1$
		String earFileName = getFullTestDataPath(earName);
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);

		List moduleList = (List) model.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
		for (int i = moduleList.size() - 1; i > -1; i--) {
			IDataModel aModel = (IDataModel) moduleList.get(i);
			Object file = aModel.getProperty(IEARComponentImportDataModelProperties.FILE);
			if (file instanceof ModuleFile) {
				ModuleFile moduleFile = (ModuleFile) file;
				if (moduleFile.isWARFile())
					moduleList.remove(aModel);
				if (moduleFile.isApplicationClientFile())
					moduleList.remove(aModel);
			}
		}

		runAndVerify(model);
		IVirtualComponent comp = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
		String appClientURI = "WorkAreaFvtClient.jar";//$NON-NLS-1$

		IFile file = ComponentUtilities.findFile(comp, new Path(appClientURI));
		ApplicationClientFile appClientJar = CommonarchiveFactoryImpl.getActiveFactory().openApplicationClientFile(file.getLocation().toOSString());
		ArchiveManifest manf = appClientJar.getManifest();
		String existingEntries[] = manf.getClassPathTokenized();
		manf.appendClassPath("foo.jar");//$NON-NLS-1$
		appClientJar.saveNoReopen();

		String earOutputName = "d:\\temp\\Output" + System.currentTimeMillis() + ".ear";
		IDataModel export = DataModelFactory.createDataModel(new EARComponentExportDataModelProvider());
		export.setProperty(IEARComponentExportDataModelProperties.PROJECT_NAME, comp.getProject().getName());
		export.setProperty(IEARComponentExportDataModelProperties.ARCHIVE_DESTINATION, earOutputName);
		runAndVerify(export);
	}
	*/

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=145460
	 */
	public void test145460() throws Exception {
		String warName = "Example1.war"; //$NON-NLS-1$
		String warFileName = getFullTestDataPath(warName);

		IDataModel dataModel = DataModelFactory.createDataModel(new WebComponentImportDataModelProvider());
		dataModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, "AN_EAR");
		dataModel.setBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
		dataModel.setProperty(IWebComponentImportDataModelProperties.FILE_NAME, warFileName);
		dataModel.setProperty(IWebComponentImportDataModelProperties.PROJECT_NAME, "A_WAR");
		runAndVerify(dataModel);
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=149995
	 */
	public void test149995() throws Exception {
		String earName = "149995.ear";//$NON-NLS-1$
		String earFileName = getFullTestDataPath(earName);
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
		runAndVerify(model);
		
		IVirtualComponent earComponent = ComponentCore.createComponent(J2EEProjectUtilities.getProject("149995"));
		IVirtualResource [] members = earComponent.getRootFolder().members();
		Assert.assertEquals(5, members.length);
		
		setUp();
		
		model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
		model.setProperty(IEARComponentImportDataModelProperties.MODULE_MODELS_LIST, Collections.EMPTY_LIST);
		runAndVerify(model);
		
		earComponent = ComponentCore.createComponent(J2EEProjectUtilities.getProject("149995"));
		members = earComponent.getRootFolder().members();
		Assert.assertEquals(5, members.length);
	}	
		
	/**
	 * The following code checks to ensure classpaths are being setup properly for dependent projects
	 * In the ear there is one AppClient which depends directly on the utility jar C which contains class test.C.
	 * The utility jar C depends on the utility jar B which contains class test.B
	 * The utility jar B depends on the utility jar A which contains class test.A
	 * 
	 * This portion of the test ensures the classpath is setup properly by importing every combination of the 
	 * A, B, and C utility jars along with AppClient.  Then the types test.A, test.B, and test.C are opened through
	 * AppClient to ensure they are pulled from the correct location (the utility jars in the EAR, or the expanded projects)
	 * 
	 */
	public void test149995_BinaryClaspathTest() throws Exception {	
//		int A = 1;
//		int B = 2;
//		int C = 4;
//		
//		String earName = "149995BinaryClasspathTest.ear";//$NON-NLS-1$
//		String earFileName = getFullTestDataPath(earName);
//		
//		for(int i=0; i<8; i++){
//			setUp();
//			
//			IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
//			model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
//			List utilityArchives = EARComponentImportDataModelProvider.getAllUtilities((EARFile) model.getProperty(IEARComponentImportDataModelProperties.FILE));
//			List utilsList = new ArrayList();
//			
//			for(int j =0;j<utilityArchives.size(); j++){
//				Archive archive = (Archive)utilityArchives.get(j);
//				String projectName = archive.getName();
//				if((i & A) == A && "A.jar".equals(projectName)){
//					utilsList.add(archive);
//				} else if((i & B) == B && "B.jar".equals(projectName)){
//					utilsList.add(archive);
//				} else if((i & C) == C && "C.jar".equals(projectName)){
//					utilsList.add(archive);
//				}
//			}
//			model.setProperty(IEARComponentImportDataModelProperties.UTILITY_LIST, utilsList);
//			runAndVerify(model);
//			
//			IJavaProject appClient = JavaCore.create(J2EEProjectUtilities.getProject("AppClient"));
//			IType aType = appClient.findType("test.A");
//			Assert.assertNotNull(aType);
//			if((i & A) == A ){
//				Assert.assertTrue(aType instanceof SourceType);
//			} else {
//				Assert.assertTrue(aType instanceof BinaryType);
//			}
//			
//			IType bType = appClient.findType("test.B");
//			Assert.assertNotNull(bType);
//			if((i & B) == B ){
//				Assert.assertTrue(bType instanceof SourceType);
//			} else {
//				Assert.assertTrue(bType instanceof BinaryType);
//			}
//			
//			IType cType = appClient.findType("test.C");
//			Assert.assertNotNull(cType);
//			if((i & C) == C ){
//				Assert.assertTrue(cType instanceof SourceType);
//			} else {
//				Assert.assertTrue(cType instanceof BinaryType);
//			}
//		}
	}
	
	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=145031
	 */
	/*
	public void test145031() throws Exception {
		String [] shortNames = new String [] { "JarTest.man.jar", "JarTest.auto.jar"};
		
		for (int i = 0; i < shortNames.length; i++) {
			//System.out.println("trying shortName ="+shortNames[i]);
			String longName = getFullTestDataPath(shortNames[i]);
			//System.out.println("longName ="+longName);
			URLClassLoader jarCL = new URLClassLoader(new URL[] { new URL("file:\\"+longName) });
			Enumeration resources = jarCL.getResources("META-INF/");
			boolean foundResource = false;
			while(resources.hasMoreElements()){
				URL url = (URL)resources.nextElement();
				
				if(url.toString().indexOf(shortNames[i]) != -1){
					//System.out.println("  Found URL with URLClassLoader.getResources(\"META-INF/\") ");
					//System.out.println("  url = "+ url);
					foundResource = true;
				}
			}
			
			//this is cheating because we know the the manual archive is first and does not have the directory info
			// and that second index is auto and should have the directory info
			if(i == 0) {
				Assert.assertFalse("Should not have found URL with URLClassLoader.getResources(\"META-INF/\") ", foundResource);
			} else if(i == 1) {
				Assert.assertTrue("Didn't find URL with URLClassLoader.getResources(\"META-INF/\") ", foundResource);
			}
			
			if(!foundResource){
				System.out.println("  Didn't find URL with URLClassLoader.getResources(\"META-INF/\") ");
			}

			ZipFile zipFile = new ZipFile(longName);
			Enumeration entries = zipFile.entries();
			while(entries.hasMoreElements()){
				ZipEntry entry = (ZipEntry)entries.nextElement();
				System.out.println("  Entry found = "+entry);
			}
			
		}
	}
	*/
	
	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=159481
	 */
	public void test159481() throws Exception {
		ArchiveOptions options = new ArchiveOptions();
		options.setRendererType(ArchiveOptions.DOM);
		final String earPath = getFullTestDataPath("WebDavTest.war"); //$NON-NLS-1$
		CommonarchivePackage pkg = CommonarchivePackage.eINSTANCE;
		WARFile warFile = pkg.getCommonarchiveFactory().openWARFile(options, earPath);
		warFile.getDeploymentDescriptor();

	}
	
	
	/**
	 * To run this test, first override setUp() to do nothing, and then import a
	 * few ear projects containing modules.
	 * 
	 * @throws Exception
	 */
	public void test145805() throws Exception {
		ClasspathContainerThreading threading = new ClasspathContainerThreading();
		threading.testDeadlock();
	}
	
	public void test160562() throws Exception {
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("A.jar", "B.jar"));
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("./A.jar", "B.jar"));
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("A.jar", "./B.jar"));
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("././././A.jar", "B.jar"));
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("A.jar", "././././B.jar"));
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("././././A.jar", "././././B.jar"));
		Assert.assertEquals("lib/A.jar", ArchiveUtil.deriveEARRelativeURI("A.jar", "lib/B.jar"));
		Assert.assertEquals("lib/A.jar", ArchiveUtil.deriveEARRelativeURI("./A.jar", "lib/B.jar"));
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("../A.jar", "lib/B.jar"));
		Assert.assertEquals("lib/A.jar", ArchiveUtil.deriveEARRelativeURI("../lib/A.jar", "lib/B.jar"));
		Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("../../../A.jar", "lib/foo/bar/B.jar"));
		Assert.assertEquals("lib/A.jar", ArchiveUtil.deriveEARRelativeURI("../../A.jar", "lib/foo/bar/B.jar"));
		Assert.assertEquals("lib/foo/A.jar", ArchiveUtil.deriveEARRelativeURI("../A.jar", "lib/foo/bar/B.jar"));
		Assert.assertEquals("lib/foo/bar/A.jar", ArchiveUtil.deriveEARRelativeURI("A.jar", "lib/foo/bar/B.jar"));
		Assert.assertEquals("lib/foo/bar/A.jar", ArchiveUtil.deriveEARRelativeURI("./A.jar", "lib/foo/bar/B.jar"));
		Assert.assertEquals("lib/foo/bar/A.jar", ArchiveUtil.deriveEARRelativeURI("./A.jar", "lib/foo/bar/B.jar"));
		Assert.assertEquals("lib/foo/bar/A.jar", ArchiveUtil.deriveEARRelativeURI("./../bar/../../foo/./bar/A.jar", "lib/foo/bar/B.jar"));
		Assert.assertEquals("lib/foo/bar/A.jar", ArchiveUtil.deriveEARRelativeURI("./A.jar", "lib/foo/bar/B.jar"));
	}
	
	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=184154
	 */
	public void test184154() throws Exception {

		// Scenario 1 - create ejb project with add to ear and ejb client
		
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
		
		String projName = "TestAPIEjbProject";//$NON-NLS-1$
		String ejbVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_2_1_ID);
		IProjectFacet ejbFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE);
		IProjectFacetVersion ejbFacetVersion = ejbFacet.getVersion(ejbVersionString); //$NON-NLS-1$


		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appmodel = (IDataModel) map.get(IModuleConstants.JST_EJB_MODULE);
		appmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, ejbFacetVersion);
		appmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"ejb333"); //$NON-NLS-1$

		String ejbClientName =  projName + "Client"; //$NON-NLS-1$

		map = (FacetDataModelMap) dataModel
		.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		appmodel = (IDataModel) map.get(IModuleConstants.JST_EJB_MODULE);

		appmodel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, true);
		appmodel.setStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME, ejbClientName);
		
		String clientURI = ejbClientName+"zzzzz"+IJ2EEModuleConstants.JAR_EXT;
		
		appmodel.setStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_URI, clientURI);

		String earProjName =  projName + "EAR"; //$NON-NLS-1$

		dataModel.setBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
		
		dataModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earProjName);

	    runAndVerify(dataModel);

	    IVirtualComponent component = ComponentUtilities.getComponent(earProjName);
	    
	    IVirtualComponent ejbClientComponent = ComponentUtilities.getComponent(ejbClientName);
		
	    IVirtualReference reference = component.getReference(ejbClientComponent.getName());
		
	    Assert.assertEquals(reference.getReferencedComponent(), ejbClientComponent);
	    
	    Assert.assertEquals(reference.getArchiveName(), clientURI);
		
		// Scenario 2 - create utility project with add to ear
		
	    String utilityProjectName = "TestUtilityProject";
	    String utilEarProjectName     = utilityProjectName + "EAR"; //$NON-NLS-1$
	    String utilityURI =  utilityProjectName + IJ2EEModuleConstants.JAR_EXT;
	    
        ProjectUtil.createUtilityProject(utilityProjectName	, utilEarProjectName);
        
	    IVirtualComponent earComponent = ComponentUtilities.getComponent(utilEarProjectName);
	    
	    IVirtualComponent utilClientComponent = ComponentUtilities.getComponent(utilityProjectName);
		
	    IVirtualReference reference1 = earComponent.getReference(utilClientComponent.getName());
		
	    Assert.assertEquals(reference1.getReferencedComponent(), utilClientComponent);
	    
	    Assert.assertEquals(reference1.getArchiveName(), utilityURI);
       
	    String javaProjectName = "TestJavaProject";
	    
	    // Scenario 3 - create java project add it as a j2ee module dependency for an ear project
	    
	    IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(javaProjectName);
	    proj.create(null);
	    proj.open(null);
	    
	    IProjectDescription description = proj.getDescription();
	    List natureList = new ArrayList();
	    natureList.add(JavaCore.NATURE_ID);
	    natureList.addAll(Arrays.asList(description.getNatureIds()));
	    description.setNatureIds((String[]) natureList.toArray(new String[natureList.size()]));
	    proj.setDescription(description, null);
	    
	    IJavaProject javaProject = JavaCore.create(proj);
	    
	    IDataModel migrationdm = DataModelFactory.createDataModel(new JavaProjectMigrationDataModelProvider());
		migrationdm.setProperty(IJavaProjectMigrationDataModelProperties.PROJECT_NAME, proj.getName());
		
		runAndVerify(migrationdm);

		String javaProjectURI = proj.getName() + IJ2EEModuleConstants.JAR_EXT;

		IDataModel refdm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
		List targetCompList = (List) refdm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);

		IVirtualComponent targetcomponent = ComponentCore.createComponent(proj);
		targetCompList.add(targetcomponent);

		refdm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComponent);
		refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetCompList);
		
		// referenced java projects should have archiveName attribute
		((Map)refdm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP)).put(targetcomponent, javaProjectURI);

		runAndVerify(refdm, true, true, null, true, true);

	    IVirtualReference reference2 = earComponent.getReference(targetcomponent.getName());
		
	    Assert.assertEquals(reference2.getReferencedComponent(), targetcomponent);
	    
	    Assert.assertEquals(reference2.getArchiveName(), javaProjectURI);


	}

	/*
	public void test192752() throws Exception {
		String warPath = null;
		String projectName = null;
		WebArtifactEdit webArtifactEditForRead = null;
		IDataModel model = null;
		

		warPath = getWarFile("InvalidWebXML1.war");
		projectName = warPath.substring(warPath.lastIndexOf(File.separator) + 1, warPath.length() - 4);
		model = WebImportOperationTest.getWebImportDataModel(warPath, projectName, null, null, true, null, null);
		OperationTestCase.runAndVerify(model);
		webArtifactEditForRead = WebArtifactEdit.getWebArtifactEditForRead(ProjectUtil.getProject(projectName));
		checkIfModelIsParsed(webArtifactEditForRead.getWebApp());

		warPath = getWarFile("InvalidWebXML2.war");
		projectName = warPath.substring(warPath.lastIndexOf(File.separator) + 1, warPath.length() - 4);
		model = WebImportOperationTest.getWebImportDataModel(warPath, projectName, null, null, true, null, null);
		OperationTestCase.runAndVerify(model);
		webArtifactEditForRead = WebArtifactEdit.getWebArtifactEditForRead(ProjectUtil.getProject(projectName));
		checkIfModelIsParsed(webArtifactEditForRead.getWebApp());

		warPath = getWarFile("InvalidWebXML14_1.war");
		projectName = warPath.substring(warPath.lastIndexOf(File.separator) + 1, warPath.length() - 4);
		model = WebImportOperationTest.getWebImportDataModel(warPath, projectName, null, null, true, null, null);
		OperationTestCase.runAndVerify(model);
		webArtifactEditForRead = WebArtifactEdit.getWebArtifactEditForRead(ProjectUtil.getProject(projectName));
		checkIfModelIsParsed(webArtifactEditForRead.getWebApp());

		warPath = getWarFile("InvalidWebXML14_2.war");
		projectName = warPath.substring(warPath.lastIndexOf(File.separator) + 1, warPath.length() - 4);
		model = WebImportOperationTest.getWebImportDataModel(warPath, projectName, null, null, true, null, null);
		OperationTestCase.runAndVerify(model);
		webArtifactEditForRead = WebArtifactEdit.getWebArtifactEditForRead(ProjectUtil.getProject(projectName));
		checkIfModelIsParsed(webArtifactEditForRead.getWebApp());
	}
	*/

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=376643
	 */
	public void test376643() throws Exception {
		final String[] projectNames = {"EAR", "Web"};
		final String zipLocation = getFullTestDataPath("TestCaseSourceNotExported.zip");
		final Path servletJavaFilePath = new Path("com/test/TestServlet1.java");
		
		// Import projects
		ProjectUnzipUtil util = new ProjectUnzipUtil(new Path(zipLocation), projectNames);
		util.createProjects();
		IVirtualComponent webComponent = ComponentCore.createComponent(J2EEProjectUtilities.getProject("Web"));
		
		final IVirtualFolder jsrc = webComponent.getRootFolder().getFolder("/WEB-INF/classes");
	
		//Export WAR including source
		IDataModel dataModel = DataModelFactory.createDataModel(new WebComponentExportDataModelProvider());
		dataModel.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, projectNames[1]);
		dataModel.setProperty(J2EEComponentExportDataModelProvider.ARCHIVE_DESTINATION, getDataPath("Web.war"));
		dataModel.setBooleanProperty(J2EEComponentExportDataModelProvider.EXPORT_SOURCE_FILES, true);
		dataModel.setBooleanProperty(J2EEComponentExportDataModelProvider.OVERWRITE_EXISTING, true);
		dataModel.setProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD, true);
		IStatus status = dataModel.getDefaultOperation().execute(null, null);
		Assert.assertEquals("Exporting WAR failed " + status.getMessage(), IStatus.OK, status.getSeverity());
		
		// Verify source was exported in the WAR
		IArchive archiveWAR = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(getDataPath("Web.war")));
		Assert.assertTrue("Archive does not contain file " + servletJavaFilePath,
						archiveWAR.containsArchiveResource(jsrc.getRuntimePath().append(servletJavaFilePath).makeRelative())); 
												
		//archiveWAR.
		JavaEEArchiveUtilities.INSTANCE.closeArchive(archiveWAR);
		
	}
	
	
	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=311542
	 */
	public void test311542() throws Exception {
		String warPath = null;
		String projectName = null;
		IDataModel model = null;
		

		warPath = getWarFile("Bug311542.war");
		projectName = warPath.substring(warPath.lastIndexOf(File.separator) + 1, warPath.length() - 4);
		model = WebImportOperationTest.getWebImportDataModel(warPath, projectName, null, true, null, null);
		OperationTestCase.runAndVerify(model);
	}

	private String getWarFile(String fileName) {
		return TEST_DATA_PATH + "WARImportTests" + java.io.File.separatorChar + fileName;
	}

	private void checkIfModelIsParsed(WebApp deploymentDescriptor1) {
		// tests to ensure model is usable to a degree
		Assert.assertNotNull(deploymentDescriptor1);
		Assert.assertNotNull(deploymentDescriptor1.getConstraints());
		Assert.assertFalse(deploymentDescriptor1.getConstraints().isEmpty());
		Assert.assertNotNull(deploymentDescriptor1.getSecurityRoles());
		Assert.assertFalse(deploymentDescriptor1.getSecurityRoles().isEmpty());
	}

	
	/**
	 * This defect was canceled.
	public void test147917() throws Exception {
		
		Assert.assertEquals("Lib/A.jar", ArchiveUtil.deriveEARRelativeURI("Lib/A.jar", "B.jar"));
		
		
		String workingDir = new java.io.File(".").getCanonicalPath(); //$NON-NLS-1$
		if(workingDir.lastIndexOf(java.io.File.separatorChar) == -1){
			return;
		}
		
		List prefixes = new ArrayList();
		String lastSegment = workingDir.substring(workingDir.lastIndexOf(java.io.File.separatorChar)+1);
		prefixes.add(lastSegment);
		String lower = lastSegment.toLowerCase();
		prefixes.add(lower);
		String upper = lastSegment.toUpperCase();
		prefixes.add(upper);
		//switch each charactor
		for(int i=0;i<lastSegment.length(); i++){
			char [] newValue = lastSegment.toCharArray();
			char c = Character.toUpperCase(newValue[i]);
			if(c == newValue[i]){
				c = Character.toLowerCase(c);
			}
			if(c != newValue[i]){
				newValue[i] = c;
				String newString = new String(newValue);
				prefixes.add(newString);	
			}
		}
		
		for(Iterator itr = prefixes.iterator(); itr.hasNext();){
			String prefix = (String)itr.next();
			Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("/A.jar", "B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI(prefix+"/A.jar", "B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI("/"+prefix+"/A.jar", "B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI(prefix+"/A.jar", "./B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI("A.jar", prefix+"/B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI("/A.jar", prefix+"/B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI("./A.jar", prefix+"/B.jar"));
			Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("../A.jar", prefix+"/B.jar"));
			Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("/../A.jar", prefix+"/B.jar"));
			Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI(prefix+"/../../A.jar", prefix+"/B.jar"));
			Assert.assertEquals("A.jar", ArchiveUtil.deriveEARRelativeURI("/"+prefix+"/../../A.jar", prefix+"/B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI(prefix+"/../A.jar", prefix+"/B.jar"));
			Assert.assertEquals(prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI("/"+prefix+"/../A.jar", prefix+"/B.jar"));
			Assert.assertEquals(prefix+"/"+prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI(prefix+"/A.jar", prefix+"/B.jar"));
			Assert.assertEquals(prefix+"/"+prefix+"/A.jar", ArchiveUtil.deriveEARRelativeURI("/"+prefix+"/A.jar", prefix+"/B.jar"));
			
			
		}
	}
	**/
	
	

}
