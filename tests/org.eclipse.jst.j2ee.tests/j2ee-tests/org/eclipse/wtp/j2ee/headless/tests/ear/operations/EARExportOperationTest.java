/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.AssertWarn;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ArchiveTestsUtil;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JEEExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaFileTestingUtilities;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.JCAExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebExportOperationTest;

/**
 * @author Changeme
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
public class EARExportOperationTest extends JEEExportOperationTest {
	private static final String JAR_EXTENSION = "jar";
	private static final String RAR_EXTENSION = "rar";
	private static final String WAR_EXTENSION = "war";
	
    public EARExportOperationTest() {
        super("EARExportOperationTests");
    }
    
    public EARExportOperationTest(String name) {
        super(name);
    }
    
	public static Test suite() {
		return new SimpleTestSuite(EARExportOperationTest.class);
	}
	
    public void testEARExport12_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("zEAR", null, EARProjectCreationOperationTest.getJ2EEDependencyList_12(), EARProjectCreationOperationTest.getJavaDependencyList_12(), JavaEEFacetConstants.EAR_12, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEARExport13_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("yEAR", null, EARProjectCreationOperationTest.getJ2EEDependencyList_13(), EARProjectCreationOperationTest.getJavaDependencyList_13(), JavaEEFacetConstants.EAR_13, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEARExport14_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("xEAR", null, EARProjectCreationOperationTest.getJ2EEDependencyList_14(), EARProjectCreationOperationTest.getJavaDependencyList_14(), JavaEEFacetConstants.EAR_14, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEARExport50_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("wEAR", null, EARProjectCreationOperationTest.getJ2EEDependencyList_5(), EARProjectCreationOperationTest.getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    
    public void testEARExport12_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("zEAR", "myContent", EARProjectCreationOperationTest.getJ2EEDependencyList_12(), EARProjectCreationOperationTest.getJavaDependencyList_12(), JavaEEFacetConstants.EAR_12, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEARExport13_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("yEAR", "ourContent", EARProjectCreationOperationTest.getJ2EEDependencyList_13(), EARProjectCreationOperationTest.getJavaDependencyList_13(), JavaEEFacetConstants.EAR_13, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEARExport14_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("xEAR", "theirContent", EARProjectCreationOperationTest.getJ2EEDependencyList_14(), EARProjectCreationOperationTest.getJavaDependencyList_14(), JavaEEFacetConstants.EAR_14, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEARExport50_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("wEAR", "yourContent", EARProjectCreationOperationTest.getJ2EEDependencyList_5(), EARProjectCreationOperationTest.getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    
    
    public void testEARExport50_WithDependencies_WithDD() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("anEAR", null, EARProjectCreationOperationTest.getJ2EEDependencyList_5(), EARProjectCreationOperationTest.getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEARExport50_ChangedContentDir_WithDependencies_WithDD() throws Exception{
    	IDataModel dm = EARProjectCreationOperationTest.getEARDataModel("theirEAR", "gotContent", EARProjectCreationOperationTest.getJ2EEDependencyList_5(), EARProjectCreationOperationTest.getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    
	@Override
	protected String getModuleExtension() {
		return ".ear";
	}
	
	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return getEARExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}
	
    /**
     * @param projectName name of the project to export
     * @param destination destination to export to
     * @param exportSource if TRUE export source files, else don't
     * @param runBuild if TRUE run a build before exporting, else don't
     * @param overwriteExisting if TRUE overwrite existing files, else don't
     * @return an EARComponentExport data model with all of the given settings.
     */
    public static IDataModel getEARExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting){
    	IDataModel exportModel = DataModelFactory.createDataModel(new EARComponentExportDataModelProvider());
    	
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, projectName);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, destination);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, exportSource);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING, overwriteExisting);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD, runBuild);
		
		return exportModel;
    }

	@Override
	protected void runExportTests_All(IDataModel creationModel) throws Exception {
		String projectName = creationModel.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
		String archiveName = null;
		String archivePath = null;
		
		IProject earProject = JavaEEProjectUtilities.getProject(projectName);
		JavaFileTestingUtilities.addJavaFilesToAllProjectsInEAR(earProject);
		
		archiveName = runAndVerifyExport_Defaults(projectName);
		archivePath = getDataPath(archiveName);
		JavaFileTestingUtilities.verifyAllJavaFilesExportedToProjectsInEAR(archivePath, true, false);
		deleteExported(archivePath);
		
		archiveName = runAndVerifyExport_WithSource(projectName);
		archivePath = getDataPath(archiveName);
		JavaFileTestingUtilities.verifyAllJavaFilesExportedToProjectsInEAR(archivePath, true, true);
		deleteExported(archivePath);
		
		archiveName = runAndVerifyExport_DontRunBuild(projectName);
		archivePath = getDataPath(archiveName);
		deleteExported(archivePath);
		
		archiveName = runAndVerifyExport_WithSource_DontRunBuild(projectName);
		archivePath = getDataPath(archiveName);
		JavaFileTestingUtilities.verifyAllJavaFilesExportedToProjectsInEAR(archivePath, false, true);
		deleteExported(archivePath);
		
		
		runTest_AttemptToOverwriteButCant(projectName);
		runTest_AttemptToOverwriteSholdSucceed(projectName);
		
		runAndVerify_CompareProjectsExportedAloneAndExportedInEAR(projectName);
		
		JavaFileTestingUtilities.clearJavaFilesForEAR();
	}
	
	/**
	 * verifies that the archives exported within the EAR are the same as if the projects were exported separately 
	 */
	private void runAndVerify_CompareProjectsExportedAloneAndExportedInEAR(String projectName) throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_ExportedNestedArchivesTest" + getModuleExtension();
		String destination = getDataPath(archiveName);
		
		IArchive earArchive = null;
		try {
			IDataModel exportModel = getExportDataModel(projectName, destination, true, true, true);
			//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
			runDataModel(exportModel);
			
			earArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(destination));
			List<IArchiveResource> resources = earArchive.getArchiveResources();
			String extension = null;
			for(IArchiveResource resource : resources) {
				extension = resource.getPath().getFileExtension();
				
				if(extension != null && (extension.equals(JAR_EXTENSION) || extension.equals(RAR_EXTENSION) || extension.equals(WAR_EXTENSION))) {
					earArchive.getNestedArchive(resource);
				}
			}
			
			IProject earProject = JavaEEProjectUtilities.getProject(projectName);
			IProject[] referencedProjects = earProject.getReferencedProjects();
			String referencedProjectType = null;
			String referencedArchiveProjectName = null;
			String referencedArchiveDestination = null;
			IDataModel referencedArchiveExportModel = null;
			Map<String, IPath> referencedProjectsArchiveMap = new HashMap<String, IPath>();
			for(IProject referencedProject : referencedProjects) {
				referencedProjectType = JavaEEProjectUtilities.getJ2EEProjectType(referencedProject);
				
				referencedArchiveProjectName = referencedProject.getName();
				referencedArchiveDestination = getDataPath(referencedArchiveProjectName);
				
				if(referencedProjectType.equals(IJ2EEFacetConstants.APPLICATION_CLIENT)) {
					referencedArchiveDestination += "." + JAR_EXTENSION;
					referencedArchiveExportModel = AppClientExportOperationTest.getAppClientExportDataModel(referencedArchiveProjectName, referencedArchiveDestination, true, true, true);
				} else if(referencedProjectType.equals(IJ2EEFacetConstants.DYNAMIC_WEB)) {
					referencedArchiveDestination += "." + WAR_EXTENSION;
					referencedArchiveExportModel = WebExportOperationTest.getWebExportDataModel(referencedArchiveProjectName, referencedArchiveDestination, true, true, true, false);
				} else if(referencedProjectType.equals(IJ2EEFacetConstants.EJB)) {
					referencedArchiveDestination += "." + JAR_EXTENSION;
					referencedArchiveExportModel = EJBExportOperationTest.getEJBExportDataModel(referencedArchiveProjectName, referencedArchiveDestination, true, true, true);
				} else if(referencedProjectType.equals(IJ2EEFacetConstants.JCA)) {
					referencedArchiveDestination += "." + RAR_EXTENSION;
					referencedArchiveExportModel = JCAExportOperationTest.getRARExportDataModel(referencedArchiveProjectName, referencedArchiveDestination, true, true, true);
				}
				
				//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
				runDataModel(referencedArchiveExportModel);
				
				referencedProjectsArchiveMap.put(referencedArchiveProjectName, new Path(referencedArchiveDestination));
			}
			
			List<IArchive> nestedArchives = earArchive.getNestedArchives();
			String nestedArchiveName = null;
			IPath referencedProjectArchivePath = null;
			IArchive referencedProjectArchive = null;
			for(IArchive nestedArchive : nestedArchives) {
				nestedArchiveName = nestedArchive.getPath().removeFileExtension().lastSegment();
				referencedProjectArchivePath = referencedProjectsArchiveMap.get(nestedArchiveName);
				
				AssertWarn.warnNotNull("There should be an exported project archive path for this EAR's nested archive", referencedProjectArchivePath);
				if(referencedProjectArchivePath != null) {
					try {
						referencedProjectArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(referencedProjectArchivePath);
						
						ArchiveTestsUtil.compareArchives(referencedProjectArchive, nestedArchive);						
					} finally {
						if(referencedProjectArchive != null) {
							JavaEEArchiveUtilities.INSTANCE.closeArchive(referencedProjectArchive);
						}
					}
					
				}
			}
			
		} finally {
			if(earArchive != null) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(earArchive);
			}
			
			deleteExported(archiveName);
		}
	}
}
