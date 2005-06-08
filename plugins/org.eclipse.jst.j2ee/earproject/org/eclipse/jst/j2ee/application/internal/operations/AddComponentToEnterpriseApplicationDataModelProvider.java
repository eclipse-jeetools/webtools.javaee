package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
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
		if (TARGET_COMPONENTS_HANDLE_LIST.equals(propertyName)) {
			return validateTargetComponentVersion((List)getProperty(TARGET_COMPONENTS_HANDLE_LIST));
		}
		return super.validate(propertyName);
	}

	
	private IStatus validateTargetComponentVersion(List list) {
		IVirtualComponent earComponent = getEarComponent();
		int earVersion = J2EEVersionUtil.convertVersionStringToInt(earComponent);
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			IVirtualComponent comp = (IVirtualComponent) iter.next();
			int compVersion = J2EEVersionUtil.convertVersionStringToInt(comp);
			if (earVersion < compVersion) {
				String errorStatus = "The Module specification level, is incompatible with the containing EAR version"; //$NON-NLS-1$
				return J2EEPlugin.newErrorStatus(errorStatus, null);
			}
			
		}
		return OK_STATUS;
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
