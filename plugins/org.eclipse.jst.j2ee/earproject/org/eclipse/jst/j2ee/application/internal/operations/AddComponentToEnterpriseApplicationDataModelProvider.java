package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class AddComponentToEnterpriseApplicationDataModelProvider extends CreateReferenceComponentsDataModelProvider {
	

	public AddComponentToEnterpriseApplicationDataModelProvider() {
		super();
		
	}

	public String[] getPropertyNames() {
		return super.getPropertyNames();
	}
	
	/**
	 * 
	 */
	public IStatus validate(String propertyName) {
		return super.validate(propertyName);
	}

	private IVirtualComponent getEarComponent() {
		ComponentHandle handle = (ComponentHandle) getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT_HANDLE);
		IVirtualComponent earComponent = handle.createComponent();
		return earComponent;
	}

	public IDataModelOperation getDefaultOperation() {
		return new AddComponentToEnterpriseApplicationOp(model);
	}
	
	public Object getDefaultProperty(String propertyName) {
	 return super.getDefaultProperty(propertyName);
	}
	
}
