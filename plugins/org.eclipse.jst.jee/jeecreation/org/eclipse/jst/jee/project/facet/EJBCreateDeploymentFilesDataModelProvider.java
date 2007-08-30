package org.eclipse.jst.jee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EJBCreateDeploymentFilesDataModelProvider extends
		CreateDeploymentFilesDataModelProvider implements
		IEJBCreateDeploymentFilesDataModelProperties {
	public IDataModelOperation getDefaultOperation() {
        return new EJBCreateDeploymentFilesOperation(model);
    }

}
