package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.wst.common.modulecore.internal.builder.ComponentStructuralBuilderDataModel;
import org.eclipse.wst.common.modulecore.internal.builder.ComponentStructuralBuilderFactory;

public class JavaDeployableModuleBuilderFactory implements ComponentStructuralBuilderFactory {

    /**
     * 
     */
    public JavaDeployableModuleBuilderFactory() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.modulecore.builder.DeployableModuleFactory#createDeploymentModuleDataModel()
     */
    public ComponentStructuralBuilderDataModel createDeploymentModuleDataModel() {
        return new JavaDeployableModuleBuilderDataModel();
    }

}
