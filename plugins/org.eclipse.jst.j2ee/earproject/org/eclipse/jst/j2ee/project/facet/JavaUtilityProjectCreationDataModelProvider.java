package org.eclipse.jst.j2ee.project.facet;


import java.util.Set;

import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;



public class JavaUtilityProjectCreationDataModelProvider
	extends AbstractDataModelProvider implements IJavaUtilityProjectCreationDataModelProperties{

	public JavaUtilityProjectCreationDataModelProvider() {
		super();
	}
	
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_NAME);
		//propertyNames.add(PROJECT_LOCATION);
		propertyNames.add(EAR_PROJECT_NAME);
		propertyNames.add(RUNTIME);
		propertyNames.add(SOURCE_FOLDER);
		return propertyNames;
	}
	
	
	public IDataModelOperation getDefaultOperation() {
		return new JavaUtilityProjectCreationOperation(model);
	}

}
