package org.eclipse.jst.j2ee.internal.earcreation;

import java.util.Set;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EarFacetInstallDataModelProvider extends FacetInstallDataModelProvider{

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return J2EEProjectUtilities.ENTERPRISE_APPLICATION;
		}		
		return super.getDefaultProperty(propertyName);
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new EarFacetInstallOperation(model);
	}	
}
