package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.wst.common.modulecore.builder.DeployableModuleBuilderDataModel;
import org.eclipse.wst.common.modulecore.builder.DeployableModuleBuilderFactory;

public class JavaDeployableModuleBuilderFactory implements DeployableModuleBuilderFactory {

    /**
     * 
     */
    public JavaDeployableModuleBuilderFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.modulecore.builder.DeployableModuleFactory#createDeploymentModuleDataModel()
     */
    public DeployableModuleBuilderDataModel createDeploymentModuleDataModel() {
        return new JavaDeployableModuleBuilderDataModel();
    }

}
