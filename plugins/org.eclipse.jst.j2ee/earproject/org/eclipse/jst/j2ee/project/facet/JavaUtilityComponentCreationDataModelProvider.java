package org.eclipse.jst.j2ee.project.facet;


import java.util.Set;

import org.eclipse.jst.j2ee.datamodel.properties.IUtilityJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * 
 * @deprecated
 * @see UtilityProjectCreationDataModelProvider
 */

public class JavaUtilityComponentCreationDataModelProvider
	extends JavaComponentCreationDataModelProvider implements IUtilityJavaComponentCreationDataModelProperties{

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
			propertyNames.add(EAR_PROJECT_NAME);
		return propertyNames;
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new JavaUtilityComponentCreationFacetOperation(model);
	}

}
