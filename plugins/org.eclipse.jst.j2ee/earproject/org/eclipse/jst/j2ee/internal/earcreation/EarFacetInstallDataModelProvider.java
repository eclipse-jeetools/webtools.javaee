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
package org.eclipse.jst.j2ee.internal.earcreation;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.osgi.util.NLS;

public class EarFacetInstallDataModelProvider extends J2EEFacetInstallDataModelProvider implements IEarFacetInstallDataModelProperties {

	@Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CONTENT_DIR);
		names.add(J2EE_PROJECTS_LIST);
		names.add(JAVA_PROJECT_LIST);
		return names;
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(FACET_ID)) {
			return ENTERPRISE_APPLICATION;
		} else if (propertyName.equals(CONTENT_DIR)) {
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
						if (configFolderPath != null && (configFolderPath.isRoot() || project.getFolder(configFolderPath).exists()))
						{
							return configFolderPath.toString();
						}
					}
				}
			}
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APPLICATION_CONTENT_FOLDER);
		} else if (propertyName.equals(J2EE_PROJECTS_LIST) || propertyName.equals(JAVA_PROJECT_LIST)) {
			return Collections.EMPTY_LIST;
		} else if(propertyName.equals(GENERATE_DD)){
			String facetVersion = getStringProperty(FACET_VERSION_STR);
			if(J2EEVersionUtil.convertVersionStringToInt(facetVersion) >= J2EEVersionConstants.JEE_5_0_ID){
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.APPLICATION_GENERATE_DD));
			}
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	@Override
	public IStatus validate(String name) {
		if (name.equals(J2EE_PROJECTS_LIST)) {
			return validateTargetComponentVersion((List) model.getProperty(J2EE_PROJECTS_LIST));
		} else if (name.equals(CONTENT_DIR)) {
			return validateFolderName(getStringProperty(CONTENT_DIR));
		}
		return super.validate(name);
	}

	private IStatus validateTargetComponentVersion(List list) {
		int earVersion = getJ2EEVersion();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			IProject handle = (IProject) iter.next();
			IVirtualComponent comp = ComponentCore.createComponent(handle.getProject());
			if (comp == null) return OK_STATUS; //Not a faceted project, so version not relevant
			int compVersion = J2EEVersionUtil.convertVersionStringToInt(comp);
			if (earVersion < compVersion) {
				String errorStatus = NLS.bind(ProjectSupportResourceHandler.EarFacetInstallDataModelProvider_The_Module_specification_level_of_, handle.getName());
				return J2EEPlugin.newErrorStatus(errorStatus, null);
			}
		}
		return OK_STATUS;
	}

	@Override
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return J2EEVersionUtil.convertVersionStringToInt(version.getVersionString());
	}

    @Override
	public boolean propertySet(String propertyName, Object propertyValue) {
        if (FACET_PROJECT_NAME.equals(propertyName))
        {
			if (!model.isPropertySet(CONTENT_DIR)) {
				model.notifyPropertyChange(CONTENT_DIR, IDataModel.DEFAULT_CHG);
			}
        }

        return super.propertySet(propertyName, propertyValue);
    }
}
