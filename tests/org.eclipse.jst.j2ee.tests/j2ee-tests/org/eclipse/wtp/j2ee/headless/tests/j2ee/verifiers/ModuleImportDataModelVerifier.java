/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class ModuleImportDataModelVerifier extends J2EEImportDataModelVerifier {

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifier#verify(org.eclipse.wtp.common.operation.WTPOperationDataModel)
	 */
	public final void verify(WTPOperationDataModel model) throws Exception { 
		super.verify(model);
		
		doModuleSpecificVerification(model);
	}
	
	public void doModuleSpecificVerification(WTPOperationDataModel model) throws Exception {
		
	}
}
