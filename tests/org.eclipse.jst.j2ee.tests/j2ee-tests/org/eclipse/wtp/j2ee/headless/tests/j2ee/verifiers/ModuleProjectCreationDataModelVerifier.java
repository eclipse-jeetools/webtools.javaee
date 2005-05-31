/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class ModuleProjectCreationDataModelVerifier extends J2EEProjectCreationDataModelVerifier {
    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.J2EEProjectCreationDataModelVerifier#verify(org.eclipse.wtp.common.operation.WTPOperationDataModel)
     */
    public void verify(IDataModel model) throws Exception {
        super.verify(model);
        verifyProjectCreationDataModel(model);
    }

    public abstract void verifyProjectCreationDataModel(IDataModel model); 


}
