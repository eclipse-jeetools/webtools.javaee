package org.eclipse.jst.j2ee.internal.web.deployables;

import org.eclipse.wst.common.modulecore.builder.DeployableModuleBuilderDataModel;
import org.eclipse.wst.common.modulecore.builder.DeployableModuleBuilderFactory;

public class WebAppDeployableModuleBuilderFactory implements DeployableModuleBuilderFactory {

    /**
     * 
     */
    public WebAppDeployableModuleBuilderFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.modulecore.builder.DeployableModuleFactory#createDeploymentModuleDataModel()
     */
    public DeployableModuleBuilderDataModel createDeploymentModuleDataModel() {
        return new WebAppDeployableModuleBuilderDataModel();
    }

}
