package org.eclipse.jst.j2ee.internal.web.deployables;

import org.eclipse.wst.common.modulecore.builder.DeployableModuleDataModel;
import org.eclipse.wst.common.modulecore.builder.DeployableModuleFactory;

public class WebAppDeployableModuleFactory implements DeployableModuleFactory {

    /**
     * 
     */
    public WebAppDeployableModuleFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.modulecore.builder.DeployableModuleFactory#createDeploymentModuleDataModel()
     */
    public DeployableModuleDataModel createDeploymentModuleDataModel() {
        return new WebAppDeployableModuleDataModel();
    }

}
