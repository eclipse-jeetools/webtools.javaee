/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import junit.framework.Test;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationExportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase;

/**
 * @author Changeme
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
public class EARExportOperationTest extends OperationTestCase {

    public static String fileSep = System.getProperty("file.separator");

    public EARExportOperationTest() {
        super();
    }
    public EARExportOperationTest(String name) {
        super(name);
    }
    public static Test suite() {
        return new SimpleTestSuite(EARExportOperationTest.class);
    }

    public static void exportEARProject(String projectName, IPath exportPath, boolean exportSource) throws Exception {
    	EnterpriseApplicationExportDataModel model = new EnterpriseApplicationExportDataModel();
        model.setProperty(EnterpriseApplicationExportDataModel.PROJECT_NAME, projectName);
        model.setProperty(EnterpriseApplicationExportDataModel.ARCHIVE_DESTINATION, exportPath.toOSString());
        model.setBooleanProperty(EnterpriseApplicationExportDataModel.EXPORT_SOURCE_FILES, exportSource);
        runAndVerify(model);
    }

    public static void exportEARProjectWithMetaData(String projectName, IPath exportPath, boolean exportSource) throws Exception {
    	EnterpriseApplicationExportDataModel model = new EnterpriseApplicationExportDataModel();
        model.setProperty(EnterpriseApplicationExportDataModel.PROJECT_NAME, projectName);
        model.setProperty(EnterpriseApplicationExportDataModel.ARCHIVE_DESTINATION, exportPath.toOSString());
        model.setBooleanProperty(EnterpriseApplicationExportDataModel.EXPORT_SOURCE_FILES, exportSource);
        model.setBooleanProperty(EnterpriseApplicationExportDataModel.OVERWRITE_EXISTING, true);
        model.setBooleanProperty(EnterpriseApplicationExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES, true);
        runAndVerify(model);
    }

    public void testEAR12Export() throws Exception {
        String projectName = "Test12WEBEJBEAR";
        String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test12WEBEJBEAR.ear";
        EARImportOperationTest.load(projectName, earName);
        exportEARProject("Test12WEBEJBEAR", J2EEPlugin.getWorkspace().getRoot().getLocation().append("Test12WEBEJBEAR.ear"), false);
    }
    public void testEARExportWithMetaData() throws Exception {
        String projectName = "Test14WEBEJBEARWithMetaData";
        String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test14WEBEJBEARWithMetaData.ear";
        EARImportOperationTest.load(projectName, earName);
        exportEARProjectWithMetaData("Test14WEBEJBEARWithMetaData", J2EEPlugin.getWorkspace().getRoot().getLocation().append("Test14WEBEJBEARWithMetaData.ear"), true);
    }
    public void testEARExportWithSource() throws Exception {
        String projectName = "Test14WEBEJBEAR";
        String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test14WEBEJBEAR.ear";
        EARImportOperationTest.load(projectName, earName);
        exportEARProject("Test14WEBEJBEAR", J2EEPlugin.getWorkspace().getRoot().getLocation().append("Test14WEBEJBEAR.ear"), true);
    }

}
