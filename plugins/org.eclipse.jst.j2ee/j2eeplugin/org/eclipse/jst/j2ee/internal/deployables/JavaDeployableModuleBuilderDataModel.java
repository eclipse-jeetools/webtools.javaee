package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.internal.builder.DeployableModuleBuilderDataModel;

public class JavaDeployableModuleBuilderDataModel extends DeployableModuleBuilderDataModel {

    /**
     * 
     */
    public JavaDeployableModuleBuilderDataModel() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.modulecore.builder.DeployableModuleDataModel#getDefaultOperation()
     */
    public WTPOperation getDefaultOperation() {
        return new JavaDeployableModuleBuilderOperation(this);
    }

}
