/*
 * Created on Nov 6, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleExportDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.TaskViewUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifierFactory;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
public abstract class OperationTestCase extends TestCase {

    public static IStatus OK_STATUS = new Status(IStatus.OK, "org.eclipse.wtp.common", 0, "OK", null); //$NON-NLS-1$

    protected void setUp() throws Exception {
        super.setUp();
        ProjectUtility.deleteAllProjects();
        LogUtility.getInstance().resetLogging();
    }

    public OperationTestCase() {
        super("OperationsTestCase");
    }

    public OperationTestCase(String name) {
        super(name);
    }

    public static void runAndVerify(WTPOperationDataModel dataModel) throws Exception {
        OperationTestCase.runAndVerify(dataModel, true, true);
    }

    /**
     * Guaranteed to close the dataModel
     * 
     * @param dataModel
     * @throws Exception
     */
    public static void runAndVerify(WTPOperationDataModel dataModel, boolean checkTasks, boolean checkLog) throws Exception {
        try {
            verifyValidDataModel(dataModel);
            dataModel.getDefaultOperation().run(null);
            DataModelVerifier verifier = DataModelVerifierFactory.getInstance().createVerifier(dataModel);
            verifier.verify(dataModel);
            if (checkTasks) {
                checkTasksList();
            }
            if (checkLog) {
                checkLogUtility();
            }
        } finally {
            dataModel.dispose();
        }
    }

    protected static void checkLogUtility() {
        LogUtility.getInstance().verifyNoWarnings();
    }

    protected static void checkTasksList() {
        TaskViewUtility.verifyNoErrors();
    }

    public static void verifyValidDataModel(WTPOperationDataModel dataModel) {
    	 if(dataModel instanceof WebModuleExportDataModel )
        	System.out.print("");
        IStatus status = dataModel.validateDataModel();
        if (!status.isOK()) {
            Assert.assertTrue("DataModel is invalid operation will not run:" + status.getMessage(), false);
        }
    }

    public static void verifyInvalidDataModel(WTPOperationDataModel dataModel) {
        IStatus status = dataModel.validateDataModel();
        if (status.isOK()) {
            Assert.assertTrue("DataModel should be invalid:" + status.getMessage(), false);
        }
    }
}
