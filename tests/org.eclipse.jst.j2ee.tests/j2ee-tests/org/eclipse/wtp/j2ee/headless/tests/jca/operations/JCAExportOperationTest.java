/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.jca.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentExportDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaFileTestingUtilities;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JCAExportOperationTest extends ModuleExportOperationTest {
	public JCAExportOperationTest() {
		super("JCAExportOperationTests");
	}
	
	public JCAExportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(JCAExportOperationTest.class);
	}
	
	public void testConnectorExport10_Defaults() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("aConnector", null, null, JavaEEFacetConstants.CONNECTOR_1);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}

	public void testConnectorExport15_Defaults() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("bConnector", null, null, JavaEEFacetConstants.CONNECTOR_15);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}

	public void testConnectorExport10_WithEAR() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("cConnector", "myEAR", null, JavaEEFacetConstants.CONNECTOR_1);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}

	public void testConnectorExport15_WithEAR() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("dConnector", "yourEAR", null, JavaEEFacetConstants.CONNECTOR_15);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}

	public void testConnectorExport10_ChangedSourceFolder() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("eConnector",null, "mySrc", JavaEEFacetConstants.CONNECTOR_1);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}

	public void testConnectorExport15_ChangedSourceFolder() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("fConnector", null, "ourSrc", JavaEEFacetConstants.CONNECTOR_15);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}

	public void testConnectorExport10_ChangedSourceFolder_WithEAR() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("gConnector", "coolEAR", "theirSrc", JavaEEFacetConstants.CONNECTOR_1);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}

	public void testConnectorExport15_ChangedSourceFolder_WithEAR() throws Exception{
		IDataModel dm = JCAProjectCreationOperationTest.getConnectorDataModel("hConnector", "netoEAR", "weSrc", JavaEEFacetConstants.CONNECTOR_15);
		OperationTestCase.runDataModel(dm);

		runExportTests_All(dm);
	}
	
	@Override
	protected String getModuleExtension() {
		return ".rar";
	}

	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return getRARExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}

	/**
	 * @param projectName name of the project to export
	 * @param destination destination to export to
	 * @param exportSource if TRUE export source files, else don't
	 * @param runBuild if TRUE run a build before exporting, else don't
	 * @param overwriteExisting if TRUE overwrite existing files, else don't
	 * @return an EJBComponentExport data model with all of the given settings.
	 */
	public static IDataModel getRARExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting){
		IDataModel exportModel = DataModelFactory.createDataModel(new ConnectorComponentExportDataModelProvider());

		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, projectName);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, destination);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, exportSource);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING, overwriteExisting);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD, runBuild);

		return exportModel;
	}
	
	@Override
	protected void addJavaFilesToProject(String projectName, String[] classNames, String prackageName) throws Exception {
		JavaFileTestingUtilities.addJavaFilesToConnector(projectName, classNames, prackageName);
	}
	
	@Override
	protected void verifyJavaFilesExported(String archiveName, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
		JavaFileTestingUtilities.verifyJavaFilesInRAR(archiveName, classNames, packageName, withClassFiles, withSource);
	}
}
