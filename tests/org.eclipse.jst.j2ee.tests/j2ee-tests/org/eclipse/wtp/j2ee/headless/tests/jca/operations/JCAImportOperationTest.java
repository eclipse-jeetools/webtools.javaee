/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.jca.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentImportDataModelProvider;
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
public class JCAImportOperationTest extends ModuleImportOperationTest {

	public JCAImportOperationTest() {
		super("JCAImportOperationTests");
	}
	
	public JCAImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(JCAImportOperationTest.class);
	}
	
	public void testConnectorImport10_Defaults() throws Exception{
		runImportTests_All("Connector10_Defaults");
	}

	public void testConnectorImport15_Defaults() throws Exception{
		runImportTests_All("Connector15_Defaults");
	}

	public void testConnectorImport10_WithEAR() throws Exception{
		runImportTests_All("Connector10_WithEAR");
	}

	public void testConnectorImport15_WithEAR() throws Exception{
		runImportTests_All("Connector15_WithEAR");
	}

	public void testConnectorImport10_DiffSourceFolder() throws Exception{
		runImportTests_All("Connector10_DiffSourceFolder");
	}

	public void testConnectorImport15_DiffSourceFolder() throws Exception{
		runImportTests_All("Connector15_DiffSourceFolder");
	}

	public void testConnectorImport10_DiffSourceFolder_WithEAR() throws Exception{
		runImportTests_All("Connector10_DiffSourceFolder_WithEAR");
	}

	public void testConnectorImport15_DiffSourceFolder_WithEAR() throws Exception{
		runImportTests_All("Connector15_DiffSourceFolder_WithEAR");
	}
	
	
	@Override
	protected String getModuleExtension() {
		return ".rar";
	}
	
	@Override
	protected IDataModel getImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose) {
		return getJCAImportDataModel(filePath, projectName, creationModel, closeArchiveOnDispose);
	}
	
    public static IDataModel getJCAImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose) {
    	IDataModel importModel = DataModelFactory.createDataModel(new ConnectorComponentImportDataModelProvider());
    	
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filePath);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, closeArchiveOnDispose);
    	
    	if(creationModel != null) {
    		importModel.setProperty(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION, creationModel);
    	}
    	
    	return importModel;
    }
	
	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return JCAExportOperationTest.getRARExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}
}
