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
        model.setProperty(EnterpriseApplicationExportDataModel.ARCHIVE_DESTINATION, exportPath);
        model.setBooleanProperty(EnterpriseApplicationExportDataModel.EXPORT_SOURCE_FILES, exportSource);
        model.setBooleanProperty(EnterpriseApplicationExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES, true);
        runAndVerify(model);
    }

    public void testEARExport() throws Exception {
        String projectName = "AuctionEAR";
        String earName = "TestData" + fileSep + "Auction" + fileSep + "Auction.ear";
        EARImportOperationTest.load(projectName, earName);
        exportEARProject("AuctionEAR", J2EEPlugin.getWorkspace().getRoot().getLocation().append("ExpAuctionWithoutSource.ear"), false);
    }
    public void testEARExportWithMetaData() throws Exception {
        String projectName = "UtilityJarTestEAR";
        String earName = "TestData" + fileSep + "ExportTests" + fileSep + "UtilityJarTestEAR.ear";
        EARImportOperationTest.load(projectName, earName);
        exportEARProject("UtilityJarTestEAR", J2EEPlugin.getWorkspace().getRoot().getLocation().append("UtilityJarTestEARWithMetaData.ear"), true);
    }
    public void testEARExportWithSource() throws Exception {
        String projectName = "AuctionEAR";
        String earName = "TestData" + fileSep + "Auction" + fileSep + "Auction.ear";
        EARImportOperationTest.load(projectName, earName);
        exportEARProject("AuctionEAR", J2EEPlugin.getWorkspace().getRoot().getLocation().append("ExpAuctionWithSource.ear"), true);
    }

}
