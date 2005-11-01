package org.eclipse.jst.j2ee.internal.earcreation;

import java.util.Set;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;

public class EarFacetInstallDataModelProvider extends FacetInstallDataModelProvider
implements IEarFacetInstallDataModelProperties{

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CONTENT_DIR);
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return J2EEProjectUtilities.ENTERPRISE_APPLICATION;
		}
        else if(propertyName.equals(CONTENT_DIR)) {
            return "EarContent";
        }
		return super.getDefaultProperty(propertyName);
	}
}
