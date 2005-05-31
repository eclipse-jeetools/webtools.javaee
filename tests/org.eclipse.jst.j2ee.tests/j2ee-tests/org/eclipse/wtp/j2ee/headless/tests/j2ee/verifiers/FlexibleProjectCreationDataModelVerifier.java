package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.properties.IFlexibleProjectCreationDataModelProperties;
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.ProjectUtility;

public class FlexibleProjectCreationDataModelVerifier extends DataModelVerifier {

		
	public void verify(IDataModel model) throws Exception {
		super.verify(model);		
		ProjectUtility.verifyProject(model.getStringProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME), true);

	}

		

}
