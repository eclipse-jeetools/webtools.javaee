package org.eclipse.jst.jee.project.facet;

import java.util.Set;

import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class CreateDeploymentFilesDataModelProvider extends
		AbstractDataModelProvider implements ICreateDeploymentFilesDataModelProperties {

	public Object getDefaultProperty(String propertyName) {
		if(ICreateDeploymentFilesDataModelProperties.GENERATE_DD.equals(propertyName)){
			return true;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(GENERATE_DD);
		propertyNames.add(TARGET_PROJECT);
		return propertyNames;
	}
	public IDataModelOperation getDefaultOperation() {
        return new CreateDeploymentFilesDataModelOperation(model);
    }

}
