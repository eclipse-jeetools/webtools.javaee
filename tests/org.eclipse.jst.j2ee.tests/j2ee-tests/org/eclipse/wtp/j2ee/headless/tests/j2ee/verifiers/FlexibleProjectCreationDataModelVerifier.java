package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.jst.j2ee.application.internal.operations.FlexibleProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.ProjectUtility;

public class FlexibleProjectCreationDataModelVerifier extends DataModelVerifier {

		
	public void verify(WTPOperationDataModel model) throws Exception {
		super.verify(model);		
		ProjectUtility.verifyProject(model.getStringProperty(FlexibleProjectCreationDataModel.PROJECT_NAME), true);

	}

		

}
