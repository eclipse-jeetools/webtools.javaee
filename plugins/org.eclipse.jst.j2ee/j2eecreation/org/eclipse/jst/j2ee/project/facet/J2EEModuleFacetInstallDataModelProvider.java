/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.project.facet.ProductManager;

public abstract class J2EEModuleFacetInstallDataModelProvider extends J2EEFacetInstallDataModelProvider implements IJ2EEModuleFacetInstallDataModelProperties {

	/**
	 * An internal Boolean property used to prohibit adding this module to an EAR. This is set on
	 * the nested models when used during EAR creation since EAR creation handles adding to the EAR
	 */
	public static final String PROHIBIT_ADD_TO_EAR = "J2EEModuleFacetInstallDataModelProvider.PROHIBIT_ADD_TO_EAR"; //$NON-NLS-1$

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(ADD_TO_EAR);
		names.add(PROHIBIT_ADD_TO_EAR);
		names.add(CONFIG_FOLDER);
		names.add(EAR_PROJECT_NAME);
		names.add(LAST_EAR_NAME);
		names.add(MODULE_URI);
		return names;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(PROHIBIT_ADD_TO_EAR)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(ADD_TO_EAR)) {
			return new Boolean(ProductManager.shouldAddToEARByDefault());
		} else if (propertyName.equals(EAR_PROJECT_NAME)) {
			if (model.isPropertySet(LAST_EAR_NAME)) {
				IProject project = ProjectUtilities.getProject(getStringProperty(LAST_EAR_NAME));
				if (project.exists())
					return project.getName();
			}
			DataModelPropertyDescriptor[] descs = getValidPropertyDescriptors(EAR_PROJECT_NAME);
			if (descs.length > 0) {
				DataModelPropertyDescriptor desc = descs[0];
				String eARName = desc.getPropertyDescription();
				if (eARName != null && !eARName.equals("")) { //$NON-NLS-1$
					return eARName;
				} else {
					return getDataModel().getStringProperty(FACET_PROJECT_NAME) + "EAR"; //$NON-NLS-1$
				}
			} else {
				return getDataModel().getStringProperty(FACET_PROJECT_NAME) + "EAR"; //$NON-NLS-1$
			}
		}
		return super.getDefaultProperty(propertyName);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		if (propertyName.equals(PROHIBIT_ADD_TO_EAR)) {
			setBooleanProperty(ADD_TO_EAR, false);
		}
		if (ADD_TO_EAR.equals(propertyName)) {
			IStatus stat = model.validateProperty(propertyName);
			if (stat != OK_STATUS) {
				return true;
			}
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
		} else if (FACET_PROJECT_NAME.equals(propertyName)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				if (!model.isPropertySet(EAR_PROJECT_NAME)) {
					model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.DEFAULT_CHG);
				}
			}
		} else if (FACET_VERSION.equals(propertyName)) {
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
		} else if (propertyName.equals(CONFIG_FOLDER)) {
			IDataModel masterModel = (IDataModel) model.getProperty(MASTER_PROJECT_DM);
			if (masterModel != null) {
				FacetDataModelMap map = (FacetDataModelMap) masterModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
				IDataModel javaModel = map.getFacetDataModel(IModuleConstants.JST_JAVA);
				if (javaModel != null)
					javaModel.setProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, propertyValue);
			}
		} else if (EAR_PROJECT_NAME.equals(propertyName)) {
			IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
			if (status.isOK()) {
				IProject project = ProjectUtilities.getProject((String) propertyValue);
				if (project.exists() && project.isAccessible() && J2EEProjectUtilities.isEARProject(project)) {
					try {
						IFacetedProject facetProj = ProjectFacetsManager.create(project, false, new NullProgressMonitor());
						setProperty(FACET_RUNTIME, facetProj.getRuntime());
					} catch (CoreException e) {
						Logger.getLogger().logError(e);
					}
				}
			}
		} else if (LAST_EAR_NAME.equals(propertyName)) {
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.DEFAULT_CHG);
		} else if (propertyName.equals(IFacetProjectCreationDataModelProperties.FACET_RUNTIME)) {
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
		}
		return super.propertySet(propertyName, propertyValue);
	}

	public boolean isPropertyEnabled(String propertyName) {
		if (ADD_TO_EAR.equals(propertyName)) {
			return !getBooleanProperty(PROHIBIT_ADD_TO_EAR) && isEARSupportedByRuntime();
		}
		if (EAR_PROJECT_NAME.equals(propertyName)) {
			return !getBooleanProperty(PROHIBIT_ADD_TO_EAR) && getBooleanProperty(ADD_TO_EAR);
		}
		return super.isPropertyEnabled(propertyName);
	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (EAR_PROJECT_NAME.equals(propertyName)) {
			int j2eeVersion = getJ2EEVersion();
			return getEARPropertyDescriptors(j2eeVersion);
		}
		return super.getValidPropertyDescriptors(propertyName);
	}

	protected DataModelPropertyDescriptor[] getEARPropertyDescriptors(int j2eeVersion) {
		StructureEdit mc = null;
		ArrayList earDescriptorList = new ArrayList();

		IProject[] projs = ProjectUtilities.getAllProjects();

		for (int index = 0; index < projs.length; index++) {
			IProject flexProject = projs[index];
			try {
				if (flexProject != null) {
					if (ModuleCoreNature.isFlexibleProject(flexProject)) {
						IVirtualComponent comp = ComponentCore.createComponent(flexProject);
						if (J2EEProjectUtilities.isEARProject(comp.getProject())) {
							String sVer = J2EEProjectUtilities.getJ2EEProjectVersion(comp.getProject());
							int ver = J2EEVersionUtil.convertVersionStringToInt(sVer);
							if (j2eeVersion <= ver) {
								DataModelPropertyDescriptor desc = new DataModelPropertyDescriptor(comp.getProject().getName());
								earDescriptorList.add(desc);
							}
						}
					}
				}
			} finally {
				if (mc != null)
					mc.dispose();
			}
		}
		DataModelPropertyDescriptor[] descriptors = new DataModelPropertyDescriptor[earDescriptorList.size()];
		for (int i = 0; i < descriptors.length; i++) {
			DataModelPropertyDescriptor desc = (DataModelPropertyDescriptor) earDescriptorList.get(i);
			descriptors[i] = new DataModelPropertyDescriptor(desc.getPropertyDescription(), desc.getPropertyDescription());
		}
		return descriptors;
	}

	public IStatus validate(String name) {
		if (name.equals(EAR_PROJECT_NAME) && getBooleanProperty(ADD_TO_EAR)) {
			IStatus status = validateEAR(getStringProperty(EAR_PROJECT_NAME));
			if (!status.isOK())
				return status;
		} else if (name.equals(CONFIG_FOLDER)) {
			String folderName = model.getStringProperty(CONFIG_FOLDER);
			if (folderName == null || folderName.length() == 0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.SOURCEFOLDER_EMPTY);
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
		} else if (name.equals(ADD_TO_EAR)) {
			if (!isEARSupportedByRuntime()) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.MODULE_NOT_SUPPORTED);
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
		}
		return super.validate(name);
	}

	protected IStatus validateEAR(String earName) {
		if (earName == null || earName.equals("")) { //$NON-NLS-1$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		} else if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		}
		return (ProjectCreationDataModelProviderNew.validateProjectName(earName));
	}

	private boolean isEARSupportedByRuntime() {
		boolean ret = true;
		IRuntime rt = (IRuntime) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME);
		if (rt != null)
			ret = rt.supports(EARFacetUtils.EAR_FACET);
		return ret;
	}
}
