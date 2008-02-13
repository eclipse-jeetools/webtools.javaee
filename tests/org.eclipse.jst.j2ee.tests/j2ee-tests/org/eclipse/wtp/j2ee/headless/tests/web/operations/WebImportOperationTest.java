/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.util.List;

import junit.framework.Test;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTest;

/**
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
		return new SimpleTestSuite(WebImportOperationTest.class);
	}
	
	public void testWebImport22_Defaults() throws Exception {
		runImportTests_All("Web22_Defaults");
	}
	
	public void testWebImport23_Defaults() throws Exception {
		runImportTests_All("Web23_Defaults");
	}
	
	public void testWebImport24_Defaults() throws Exception {
		runImportTests_All("Web24_Defaults");
	}
	
	public void testWebImport25_Defaults() throws Exception {
		runImportTests_All("Web25_Defaults");
	}	
	
	public void testWebImport22_DiffContentDir() throws Exception {
		runImportTests_All("Web22_DiffContentDir");
	}
	
	public void testWebImport23_DiffContentDir() throws Exception {
		runImportTests_All("Web23_DiffContentDir");
	}
	
	public void testWebImport24_DiffContentDir() throws Exception {
		runImportTests_All("Web24_DiffContentDir");
	}
	
	public void testWebImport25_DiffContentDir() throws Exception {
		runImportTests_All("Web25_DiffContentDir");
	}
	
	
	public void testWebImport22_DiffSrcDir() throws Exception {
		runImportTests_All("Web22_DiffSrcDir");
	}
	
	public void testWebImport23_DiffSrcDir() throws Exception {
		runImportTests_All("Web23_DiffSrcDir");
	}
	
	public void testWebImport24_DiffSrcDir() throws Exception {
		runImportTests_All("Web24_DiffSrcDir");
	}
	
	public void testWebImport25_DiffSrcDir() throws Exception {
		runImportTests_All("Web25_DiffSrcDir");
	}
	
	
	public void testWebImport22_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web22_DiffContentDir_DiffSrcDir");
	}
	
	public void testWebImport23_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web23_DiffContentDir_DiffSrcDir");
	}
	
	public void testWebImport24_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web24_DiffContentDir_DiffSrcDir");
	}
	
	public void testWebImport25_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir");
	}
	
	
	public void testWebImport22_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web22_Defaults_WithEAR");
	}
	
	public void testWebImport23_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web23_Defaults_WithEAR");
	}
	
	public void testWebImport24_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web24_Defaults_WithEAR");
	}
	
	public void testWebImport25_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web25_Defaults_WithEAR");
	}
	
	
	public void testWebImport22_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_WithEAR");
	}
	
	public void testWebImport25_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_WithEAR");
	}
	
	
	public void testWebImport22_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContentDir_WithEAR");
	}
	
	public void testWebImport23_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContentDir_WithEAR");
	}
	
	public void testWebImport24_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContentDir_WithEAR");
	}
	
	public void testWebImport25_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContentDir_WithEAR");
	}
	
	
	public void testWebImport22_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport25_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffSrcDir_WithEAR");
	}
	
	
	public void testWebImport22_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	public void testWebImport25_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	
	public void testWebImport22_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport25_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	
	public void testWebImport22_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport25_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	
	public void testWebImport22_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	
	
	public void testWebImport25_Defaults_WithDD() throws Exception {
		runImportTests_All("Web25_Defaults_WithDD");
	}

	public void testWebImport25_DiffContextRoot_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_WithDD");
	}

	public void testWebImport25_DiffContentDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_WithDD");
	}

	public void testWebImport25_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffSrcDir_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffSrcDir_WithDD");
	}

	public void testWebImport25_DiffContentDir_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithDD");
	}

	public void testWebImport25_Defaults_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_Defaults_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContentDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffSrcDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffSrcDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContentDir_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR_WithDD");
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
