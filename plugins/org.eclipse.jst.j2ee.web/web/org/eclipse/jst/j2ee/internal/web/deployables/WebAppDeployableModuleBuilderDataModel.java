package org.eclipse.jst.j2ee.internal.web.deployables;

import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.builder.DeployableModuleBuilderDataModel;

public class WebAppDeployableModuleBuilderDataModel extends DeployableModuleBuilderDataModel {

    /**
     * 
     */
    public WebAppDeployableModuleBuilderDataModel() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.modulecore.builder.DeployableModuleDataModel#getDefaultOperation()
     */
    public WTPOperation getDefaultOperation() {
        return new WebAppDeployableModuleBuilderOperation(this);
    }

}
