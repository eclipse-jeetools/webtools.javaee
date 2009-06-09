package org.eclipse.jst.jee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EarCreateDeploymentFilesDataModelProvider extends
		CreateDeploymentFilesDataModelProvider implements
		IEarCreateDeploymentFilesDataModelProperties {
	@Override
	public IDataModelOperation getDefaultOperation() {
        return new EarCreateDeploymentFilesOperation(model);
    }

}
