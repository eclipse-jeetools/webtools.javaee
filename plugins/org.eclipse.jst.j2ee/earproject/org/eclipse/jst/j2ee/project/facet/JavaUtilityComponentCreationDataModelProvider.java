package org.eclipse.jst.j2ee.project.facet;


import java.util.Set;

import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


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
