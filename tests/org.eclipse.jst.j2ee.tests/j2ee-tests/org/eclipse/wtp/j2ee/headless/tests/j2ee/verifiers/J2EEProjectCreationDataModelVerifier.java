/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.ProjectUtility;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class J2EEProjectCreationDataModelVerifier extends DataModelVerifier {

	public void verify(IDataModel model) throws Exception {
		super.verify(model);		
		ProjectUtility.verifyProject(model.getStringProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME), true);

	}

}
