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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class AppClientFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IAppClientFacetInstallDataModelProperties {

	public AppClientFacetInstallDataModelProvider() {
		super();
	}

	@Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CREATE_DEFAULT_MAIN_CLASS);
		return names;
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(FACET_ID))
			return APPLICATION_CLIENT;
		else if (propertyName.equals(CREATE_DEFAULT_MAIN_CLASS))
			return Boolean.TRUE;
		else if (propertyName.equals(CONFIG_FOLDER))
		{
			if (model.isPropertySet(FACET_PROJECT_NAME))
			{
				String projectName = model.getStringProperty(FACET_PROJECT_NAME);
				IProject project = (projectName.length() > 0) ? ResourcesPlugin.getWorkspace().getRoot().getProject(projectName) : null;
				
				if (project != null && project.exists()) {
					if (ModuleCoreNature.isFlexibleProject(project))
					{
						IVirtualComponent c = ComponentCore.createComponent(project, true);
						IVirtualFolder ejbroot = c.getRootFolder();
						IPath configFolderPath = J2EEModuleVirtualComponent.getDefaultDeploymentDescriptorFolder(ejbroot);
						if (configFolderPath != null && project.getFolder(configFolderPath).exists())
						{
							return configFolderPath.toString();
						}
					}
				}
			}
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APP_CLIENT_CONTENT_FOLDER);
		}
		else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ','_');
			return projectName + IJ2EEModuleConstants.JAR_EXT; 
		} else if (propertyName.equals(IJ2EEFacetInstallDataModelProperties.GENERATE_DD)) {
			String facetVersion = getStringProperty(FACET_VERSION_STR);
			if(J2EEVersionUtil.convertVersionStringToInt(facetVersion) >= J2EEVersionConstants.JEE_5_0_ID){
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.APP_CLIENT_GENERATE_DD));
			}
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	@Override
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return J2EEVersionUtil.convertAppClientVersionStringToJ2EEVersionID(version.getVersionString());
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
        else if (FACET_PROJECT_NAME.equals(propertyName))
        {
			if (!model.isPropertySet(CONFIG_FOLDER)) {
				model.notifyPropertyChange(CONFIG_FOLDER, IDataModel.DEFAULT_CHG);
			}
        }

        return super.propertySet(propertyName, propertyValue);
    }

}
