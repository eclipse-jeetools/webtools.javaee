/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEImportDataModelVerifier extends DataModelVerifier {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifier#verify(org.eclipse.wtp.common.operation.WTPOperationDataModel)
	 */
	public void verify(WTPOperationDataModel model) throws Exception {

		super.verify(model);

		IProject project = ProjectUtility.verifyAndReturnProject(model.getStringProperty(J2EEModuleImportDataModel.PROJECT_NAME), true);

		String serverTargetId = model.getStringProperty(J2EEModuleImportDataModel.SERVER_TARGET_ID);
		IRuntime stt = ServerCore.getProjectProperties(project).getRuntimeTarget();
		/* fail because exactly one of the following is set */
		if (serverTargetId != null) {
			if (stt == null)
				Assert.fail("The server target of the project (Not Set) does not match that of the model (" + serverTargetId + ")");
			/*
			 * we know that either stt == null && serverTargetId == null OR stt != null &&
			 * serverTargetId != null
			 */
			else 
				Assert.assertEquals("The actual server target should match the model's server target.",serverTargetId,stt.getName());
		}

	}

}
