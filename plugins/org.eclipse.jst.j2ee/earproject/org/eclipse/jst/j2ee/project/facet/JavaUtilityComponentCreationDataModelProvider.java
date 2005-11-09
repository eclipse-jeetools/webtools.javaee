package org.eclipse.jst.j2ee.project.facet;


import java.util.Set;

import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * This class is used to create a JavaUtility component, e.g. 
 *  <pre>
 * IDataModel dm = DataModelFactory.createDataModel(new JavaUtilityComponentCreationDataModelProvider());
 * dm.setProperty(JavaComponentCreationDataModelProvider.PROJECT_NAME,
 * "utility");
 * 
 * dm.setProperty(JavaComponentCreationDataModelProvider.JAVASOURCE_FOLDER,
 * "src"));
 *
 * Setting RUNTIME_TARGET_ID is optional 
 * dm.setProperty(JavaComponentCreationDataModelProvider.RUNTIME_TARGET_ID,
 * runtimeId);
 *				
 * dm.getDefaultOperation().execute(monitor, null);
 *</pre>
 */

public class JavaUtilityComponentCreationDataModelProvider
	extends JavaComponentCreationDataModelProvider{

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		return propertyNames;
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new JavaUtilityComponentCreationFacetOperation(model);
	}

}
