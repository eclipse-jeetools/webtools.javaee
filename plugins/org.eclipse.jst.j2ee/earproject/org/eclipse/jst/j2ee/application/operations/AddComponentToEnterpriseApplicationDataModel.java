package org.eclipse.jst.j2ee.application.operations;

import java.util.Collections;

import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEditOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;

public class AddComponentToEnterpriseApplicationDataModel extends EARArtifactEditOperationDataModel {
	
	public static final String MODULE_LIST = "AddComponentToEnterpriseApplicationDataModel.MODULE_LIST"; //$NON-NLS-1$
	
	public static final String EAR_MODULE_NAME = "AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME";

	public AddComponentToEnterpriseApplicationDataModel() {
		super();
		
	}
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(MODULE_LIST);
		addValidBaseProperty(EAR_MODULE_NAME);
	}
	
	public WTPOperation getDefaultOperation() {
		return new AddComponentToEnterpriseApplicationOperation(this);
	}
	
	protected Object getDefaultProperty(String propertyName) {
	if (propertyName.equals(MODULE_LIST) ) 
				return Collections.EMPTY_LIST;
	 return super.getDefaultProperty(propertyName);
	}
	

}
