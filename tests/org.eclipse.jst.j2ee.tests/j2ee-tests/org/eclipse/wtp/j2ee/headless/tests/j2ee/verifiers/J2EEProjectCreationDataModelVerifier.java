/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class J2EEProjectCreationDataModelVerifier extends DataModelVerifier {

	public void verify(WTPOperationDataModel model) throws Exception {
		super.verify(model);		
		ProjectUtility.verifyProject(model.getStringProperty(J2EEModuleCreationDataModelOld.PROJECT_NAME), true);

	}

}
