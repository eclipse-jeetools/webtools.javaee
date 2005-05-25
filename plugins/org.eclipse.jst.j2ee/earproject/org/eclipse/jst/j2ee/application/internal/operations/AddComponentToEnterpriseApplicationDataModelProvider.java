package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class AddComponentToEnterpriseApplicationDataModelProvider extends CreateReferenceComponentsDataModelProvider {
	

	public AddComponentToEnterpriseApplicationDataModelProvider() {
		super();
		
	}

	public String[] getPropertyNames() {
		return super.getPropertyNames();
	}

	
	public IDataModelOperation getDefaultOperation() {
		return new AddComponentToEnterpriseApplicationOp(model);
	}
	
	public Object getDefaultProperty(String propertyName) {
	 return super.getDefaultProperty(propertyName);
	}
	
}
