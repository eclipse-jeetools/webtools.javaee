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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class J2EEFacetProjectCreationDataModelProvider extends FacetProjectCreationDataModelProvider implements IJ2EEFacetProjectCreationDataModelProperties {

	public J2EEFacetProjectCreationDataModelProvider() {
		super();
	}

	public void init() {
		super.init();
	}
	
	public Object getDefaultProperty(String propertyName) {
		if (EAR_PROJECT_NAME.equals(propertyName)) {
			IDataModel nestedJ2EEFacetDataModel = getJ2EEFacetModel();
			if (nestedJ2EEFacetDataModel!=null)
				return nestedJ2EEFacetDataModel.getProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);
		}
		return super.getDefaultProperty(propertyName);
	}

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(EAR_PROJECT_NAME);
		names.add(ADD_TO_EAR);
		names.add(MODULE_URI);
		return names;
	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (FACET_RUNTIME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			DataModelPropertyDescriptor[] descriptors = super.getValidPropertyDescriptors(propertyName);
			List list = new ArrayList();
			for (int i = 0; i < descriptors.length; i++) {
				IRuntime rt = (IRuntime) descriptors[i].getPropertyValue();
				if (rt == null || rt.supports(EARFacetUtils.EAR_FACET)) {
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
							e.printStackTrace();
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

	protected IDataModel getJ2EEFacetModel() {
		FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
		String [] j2eeFacets = new String [] { J2EEProjectUtilities.APPLICATION_CLIENT, J2EEProjectUtilities.EJB, J2EEProjectUtilities.JCA, J2EEProjectUtilities.DYNAMIC_WEB, J2EEProjectUtilities.UTILITY};
		IDataModel j2eeFacetDataModel = null;
		for(int i=0;i<j2eeFacets.length; i++){
			j2eeFacetDataModel = map.getFacetDataModel(j2eeFacets[i]);
			if(null != j2eeFacetDataModel){
				return j2eeFacetDataModel;
			}
		}
		return null;
	}

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
							e.printStackTrace();
						}
					}
				}
			}
			return true;
		}
		return super.isPropertyEnabled(propertyName);
	}

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
			}
		}
		return super.validate(propertyName);
	}

	protected IStatus validateEAR(String earName) {
		if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		} else if (earName == null || earName.equals("")) { //$NON-NLS-1$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		}
		
		IStatus status = ProjectCreationDataModelProviderNew.validateProjectName(earName);
		//check for the deleted case, the project is deleted from the workspace but still exists in the
		//file system.
		if( status.isOK()){
			IProject earProject = ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
			if( !earProject.exists() ){
				IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation();
				path = path.append(earName);
				status = ProjectCreationDataModelProviderNew.validateExisting(earName, path.toOSString());
			}
		}
		return status;
	}

}
