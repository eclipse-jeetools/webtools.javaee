package org.eclipse.jst.jee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class WebCreateDeploymentFilesDataModelProvider extends
		CreateDeploymentFilesDataModelProvider implements
		IWebCreateDeploymentFilesDataModelProperties {
	public IDataModelOperation getDefaultOperation() {
        return new WebCreateDeploymentFilesOperation(model);
    }

}
