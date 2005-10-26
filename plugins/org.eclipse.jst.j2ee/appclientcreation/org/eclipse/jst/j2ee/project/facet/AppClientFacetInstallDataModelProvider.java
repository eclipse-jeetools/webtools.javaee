package org.eclipse.jst.j2ee.project.facet;

import java.util.Set;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;

public class AppClientFacetInstallDataModelProvider
	extends FacetInstallDataModelProvider
	implements IJ2EEFacetInstallDataModelProperties{

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(EAR_PROJECT_NAME);
		names.add(CONFIG_FOLDER);
		names.add(RUNTIME_TARGET_ID);
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return J2EEProjectUtilities.APPLICATION_CLIENT;
		}
		return super.getDefaultProperty(propertyName);
	}
	
}
