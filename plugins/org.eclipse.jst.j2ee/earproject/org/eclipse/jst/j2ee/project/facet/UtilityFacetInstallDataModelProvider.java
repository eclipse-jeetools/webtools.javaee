package org.eclipse.jst.j2ee.project.facet;

import java.util.Set;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;

public class UtilityFacetInstallDataModelProvider
	extends FacetInstallDataModelProvider{

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return J2EEProjectUtilities.UTILITY;
		}
		return super.getDefaultProperty(propertyName);
	}	
}
