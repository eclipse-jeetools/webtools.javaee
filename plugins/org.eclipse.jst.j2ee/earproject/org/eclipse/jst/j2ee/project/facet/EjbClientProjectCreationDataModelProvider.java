package org.eclipse.jst.j2ee.project.facet;

import java.util.Set;

import org.eclipse.jst.j2ee.project.facet.JavaUtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EjbClientProjectCreationDataModelProvider
	extends JavaUtilityProjectCreationDataModelProvider
	implements IEjbClientProjectCreationDataModelProperties{

	public EjbClientProjectCreationDataModelProvider() {
		super();
	}
	
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EJB_PROJECT_NAME);
		return propertyNames;
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new EjbClientProjectCreationOperation(model);
	}
	
}
