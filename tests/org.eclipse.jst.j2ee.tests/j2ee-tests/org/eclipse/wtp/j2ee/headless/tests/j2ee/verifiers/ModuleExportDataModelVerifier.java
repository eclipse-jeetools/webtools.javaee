/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author mdelder
 */
public abstract class ModuleExportDataModelVerifier extends J2EEExportDataModelVerifier {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.J2EEExportDataModelVerifier#verify(org.eclipse.wtp.common.operation.WTPOperationDataModel)
	 */
	public final void verify(WTPOperationDataModel model) throws Exception {
		super.verify(model);

		doModuleSpecificVerification(model);
	}

	public void doModuleSpecificVerification(WTPOperationDataModel model) throws Exception {
		
	}
}
