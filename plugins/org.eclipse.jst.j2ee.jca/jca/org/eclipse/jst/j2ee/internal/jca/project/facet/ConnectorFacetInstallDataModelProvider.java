/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.project.facet;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.jca.project.facet.IConnectorFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class ConnectorFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IConnectorFacetInstallDataModelProperties {

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(FACET_ID)) {
			return JCA;
		} else if (propertyName.equals(CONFIG_FOLDER)) {
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.JCA_CONTENT_FOLDER);
		} else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ','_');
			return projectName + IJ2EEModuleConstants.RAR_EXT; 
		} else if(propertyName.equals(GENERATE_DD)){
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	@Override
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return J2EEVersionUtil.convertConnectorVersionStringToJ2EEVersionID(version.getVersionString());
	}
	
    @Override
	public boolean propertySet(String propertyName, Object propertyValue) {
        if (propertyName.equals(CONFIG_FOLDER)) 
        {
            if( this.javaFacetInstallConfig != null )
            {
                final IPath sourceFolder
                    = propertyValue == null ? null : new Path( (String) propertyValue );
                
                this.javaFacetInstallConfig.setSourceFolder( sourceFolder );
            }
        }
        
        return super.propertySet(propertyName, propertyValue);
    }
    
}
