/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class J2EEFacetProjectCreationDataModelProvider extends FacetProjectCreationDataModelProvider implements IJ2EEFacetProjectCreationDataModelProperties {
    
    private static Set<IProjectFacet> MODULE_FACETS = new HashSet<IProjectFacet>();
    private boolean caseSensitiveFs = EFS.getLocalFileSystem().isCaseSensitive();
    
    static
    {
        for( IProjectFacetVersion fv : ProjectFacetsManager.getGroup( "modules" ).getMembers() ) //$NON-NLS-1$
        {
            MODULE_FACETS.add( fv.getProjectFacet() );
        }
    }

	public J2EEFacetProjectCreationDataModelProvider() {
		super();
	}

	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public Object getDefaultProperty(String propertyName) {
		if (EAR_PROJECT_NAME.equals(propertyName)) {
			IDataModel nestedJ2EEFacetDataModel = getJ2EEFacetModel();
			if (nestedJ2EEFacetDataModel!=null)
				return nestedJ2EEFacetDataModel.getProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);
		}
		return super.getDefaultProperty(propertyName);
	}

	@Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(EAR_PROJECT_NAME);
		names.add(ADD_TO_EAR);
		names.add(MODULE_URI);
		return names;
	}

	@Override
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (FACET_RUNTIME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			DataModelPropertyDescriptor[] descriptors = super.getValidPropertyDescriptors(propertyName);
			List list = new ArrayList();
			for (int i = 0; i < descriptors.length; i++) {
				IRuntime rt = (IRuntime) descriptors[i].getPropertyValue();
				if (rt == null || rt.supports(IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET)) {
					list.add(descriptors[i]);
				}
			}
			descriptors = new DataModelPropertyDescriptor[list.size()];
			for (int i = 0; i < descriptors.length; i++) {
				descriptors[i] = (DataModelPropertyDescriptor) list.get(i);
			}
			return descriptors;
		}
		return super.getValidPropertyDescriptors(propertyName);
	}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		if (EAR_PROJECT_NAME.equals(propertyName) || ADD_TO_EAR.equals(propertyName)) {
			IDataModel nestedJ2EEFacetDataModel = getJ2EEFacetModel();
			if(null != nestedJ2EEFacetDataModel){
				if(EAR_PROJECT_NAME.equals(propertyName)){
					nestedJ2EEFacetDataModel.setProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME, propertyValue);
				} else {
					nestedJ2EEFacetDataModel.setProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, propertyValue);	
				}
				
			}
			if (getBooleanProperty(ADD_TO_EAR)) {
				IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
				if (status.isOK()) {
					IProject earProject = ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
					if (earProject != null) {
						IFacetedProject facetdEarProject;
						try {
							facetdEarProject = ProjectFacetsManager.create(earProject);
							if (facetdEarProject != null) {
								setProperty(FACET_RUNTIME, facetdEarProject.getRuntime());
							}
						} catch (CoreException e) {
							J2EEPlugin.logError(e);
						}
					}
				}
			}
			if (ADD_TO_EAR.equals(propertyName)) {
				model.notifyPropertyChange(FACET_RUNTIME, IDataModel.VALID_VALUES_CHG);
			}
			model.notifyPropertyChange(FACET_RUNTIME, IDataModel.ENABLE_CHG);
		}
		return super.propertySet(propertyName, propertyValue);
	}

	protected IDataModel getJ2EEFacetModel() 
	{
	    final IFacetedProjectWorkingCopy fpjwc
	        = (IFacetedProjectWorkingCopy) this.model.getProperty( FACETED_PROJECT_WORKING_COPY );
	    
	    for( IProjectFacet moduleFacet : MODULE_FACETS )
	    {
	        if( fpjwc.hasProjectFacet( moduleFacet ) )
	        {
	            final IFacetedProject.Action action = fpjwc.getProjectFacetAction( moduleFacet );
	            return (IDataModel) action.getConfig();
	        }
	    }

		return null;
	}

	@Override
	public boolean isPropertyEnabled(String propertyName) {
		if (FACET_RUNTIME.equals(propertyName)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
				if (status.isOK()) {
					IProject earProject = ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
					if (earProject != null) {
						IFacetedProject facetdEarProject;
						try {
							facetdEarProject = ProjectFacetsManager.create(earProject);
							if (facetdEarProject != null) {
								return false;
							}
						} catch (CoreException e) {
							J2EEPlugin.logError(e);
						}
					}
				}
			}
			return true;
		}
		return super.isPropertyEnabled(propertyName);
	}

	@Override
	public IStatus validate(String propertyName) {
		if (ADD_TO_EAR.equals(propertyName) || EAR_PROJECT_NAME.equals(propertyName) || FACET_PROJECT_NAME.equals(propertyName)) {
			if (model.getBooleanProperty(ADD_TO_EAR)) {
				IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
				if (!status.isOK())
					return status;
				if (getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME).equals(getStringProperty(EAR_PROJECT_NAME))) {
					String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{getStringProperty(EAR_PROJECT_NAME)});
					return WTPCommonPlugin.createErrorStatus(errorMessage);
				}
				if (!caseSensitiveFs && getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME).equalsIgnoreCase(getStringProperty(EAR_PROJECT_NAME))) {
					String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME_DIFFERENT_CASE, new Object[]{getStringProperty(EAR_PROJECT_NAME)});
					return WTPCommonPlugin.createErrorStatus(errorMessage);
				}
			}else if (EAR_PROJECT_NAME.equals(propertyName)){
				return validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
			}
		}
		return super.validate(propertyName);
	}

	protected IStatus validateEAR(String earName) {
		if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		} else if (earName.equals("")) { //$NON-NLS-1$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		}
		
		IStatus status = ProjectCreationDataModelProviderNew.validateProjectName(earName);
		//check for the deleted case, the project is deleted from the workspace but still exists in the
		//file system.
		if( status.isOK()){
			IProject earProject = ResourcesPlugin.getWorkspace().getRoot().getProject(getStringProperty(EAR_PROJECT_NAME));//ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
			if(!earProject.exists()){
					IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation();
					path = path.append(earName);
					status = ProjectCreationDataModelProviderNew.validateExisting(earName, path.toOSString());
			}else if(!JavaEEProjectUtilities.isEARProject(earProject)){
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.EAR_NAME_USED_BY_NON_EAR_PROJECT);
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
		}
		return status;
	}

}
