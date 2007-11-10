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
package org.eclipse.jst.j2ee.project.facet;

import java.util.Set;

import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class AppClientFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IAppClientFacetInstallDataModelProperties {

	public AppClientFacetInstallDataModelProvider() {
		super();
	}

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CREATE_DEFAULT_MAIN_CLASS);
		return names;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(FACET_ID))
			return APPLICATION_CLIENT;
		else if (propertyName.equals(CREATE_DEFAULT_MAIN_CLASS))
			return Boolean.TRUE;
		else if (propertyName.equals(CONFIG_FOLDER))
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APP_CLIENT_CONTENT_FOLDER);
		else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ','_');
			return projectName + IJ2EEModuleConstants.JAR_EXT; 
		}
		return super.getDefaultProperty(propertyName);
	}
	
    public boolean propertySet(String propertyName, Object propertyValue) {
        if( propertyName.equals( FACET_VERSION ) )
        {
            if( propertyValue == APPLICATION_CLIENT_50 )
            {
                setProperty( GENERATE_DD, Boolean.FALSE );
            }
            else
            {
                setProperty( GENERATE_DD, Boolean.TRUE );
            }
        }
        return super.propertySet(propertyName, propertyValue);
    }
	
	
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return J2EEVersionUtil.convertAppClientVersionStringToJ2EEVersionID(version.getVersionString());
	}
}
