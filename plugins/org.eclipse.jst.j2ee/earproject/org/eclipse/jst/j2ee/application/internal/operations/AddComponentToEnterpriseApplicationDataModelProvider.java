package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class AddComponentToEnterpriseApplicationDataModelProvider extends CreateReferenceComponentsDataModelProvider {

	public AddComponentToEnterpriseApplicationDataModelProvider() {
		super();

	}

	public IStatus validate(String propertyName) {
		return super.validate(propertyName);
	}

	private IVirtualComponent getEarComponent() {
		IProject project = (IProject) getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT_PROJECT);
		IVirtualComponent earComponent = ComponentCore.createComponent(project);
		return earComponent;
	}

	public IDataModelOperation getDefaultOperation() {
		return new AddComponentToEnterpriseApplicationOp(model);
	}

	public Object getDefaultProperty(String propertyName) {
		return super.getDefaultProperty(propertyName);
	}

}
