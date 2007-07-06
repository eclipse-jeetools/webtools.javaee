/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebImportOperationTest extends ModuleImportOperationTest {

	public WebImportOperationTest() {
		super("WebImportOperationTests");
	}
	
	public WebImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(WebImportOperationTest.class);
		return suite;
	}
	
	public void testWebImport22_Defaults() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_Defaults() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_Defaults() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_Defaults() throws Exception {
		runImportTests_All();
	}	
	
	public void testWebImport22_ChangedContentDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContentDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContentDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContentDir() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_Defaults_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_Defaults_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_Defaults_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_Defaults_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedContextRoot_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContextRoot_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContextRoot_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContextRoot_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	public void testWebImport22_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport23_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport24_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	public void testWebImport25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		runImportTests_All();
	}
	
	
	
	public void testWebImport25_Defaults_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContentDir_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedJavaSrcDir_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_ChangedContentDir_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_ChangedJavaSrcDir_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_Defaults_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContentDir_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_ChangedContentDir_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}

	public void testWebImport25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All();
	}
	
	
	@Override
	protected String getModuleExtension() {
		return ".war";
	}
	
    @Override
    protected IDataModel getImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose) {
    	return getWebImportDataModel(filePath, projectName, overwriteHandler, creationModel, closeArchiveOnDispose, null, null);
    }
    
    public static IDataModel getWebImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose, List<IArchive> webLibArchivesToExpand, String contextRoot) {
    	IDataModel importModel = DataModelFactory.createDataModel(new WebComponentImportDataModelProvider());
    	
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filePath);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, closeArchiveOnDispose);
    	
    	if(overwriteHandler != null) {
    		importModel.setProperty(IJ2EEComponentImportDataModelProperties.OVERWRITE_HANDLER, overwriteHandler);
    	}
    	
    	if(creationModel != null) {
    		importModel.setProperty(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION, creationModel);
    	}
    	
    	if(webLibArchivesToExpand != null) {
    		importModel.setProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED, webLibArchivesToExpand);
    	}
    	
    	if(contextRoot != null) {
    		importModel.setProperty(IWebComponentImportDataModelProperties.CONTEXT_ROOT, contextRoot);
    	}
    	
    	return importModel;
    }
    
    @Override
    protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
    	return WebExportOperationTest.getWebExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting, false);
    }
}
