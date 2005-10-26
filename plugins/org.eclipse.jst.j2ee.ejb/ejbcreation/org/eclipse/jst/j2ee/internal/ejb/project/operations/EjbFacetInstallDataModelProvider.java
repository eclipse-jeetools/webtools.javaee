package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.Set;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;

public class EjbFacetInstallDataModelProvider
		extends FacetInstallDataModelProvider
		implements IEjbFacetInstallDataModelProperties{

	
	
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(EAR_PROJECT_NAME);
		names.add(CONFIG_FOLDER);
		names.add(CREATE_CLIENT);
		names.add(CLIENT_NAME);
		names.add(CLIENT_SOURCE_FOLDER);
		names.add(RUNTIME_TARGET_ID);
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return J2EEProjectUtilities.EJB;
		}
		return super.getDefaultProperty(propertyName);
	}
		
}
