package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.wst.common.componentcore.internal.builder.ComponentStructuralBuilderDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class JavaDeployableModuleBuilderDataModel extends ComponentStructuralBuilderDataModel {

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
