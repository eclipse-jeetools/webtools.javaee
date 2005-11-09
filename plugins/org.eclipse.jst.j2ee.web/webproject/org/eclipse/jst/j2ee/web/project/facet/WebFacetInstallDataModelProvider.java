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
package org.eclipse.jst.j2ee.web.project.facet;

import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class WebFacetInstallDataModelProvider extends FacetInstallDataModelProvider implements IWebFacetInstallDataModelProperties {

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(ADD_TO_EAR);
		names.add(EAR_PROJECT_NAME);
		names.add(CONTEXT_ROOT);
		names.add(CONTENT_DIR);
		names.add(CREATE_WEB_INF_SRC);
		names.add(RUNTIME_TARGET_ID);
		return names;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CONTENT_DIR)) {
			return "WebContent";
		} else if (propertyName.equals(CREATE_WEB_INF_SRC)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(CONTEXT_ROOT)) {
			return getProperty(FACET_PROJECT_NAME);
		} else if (propertyName.equals(FACET_ID)) {
			return J2EEProjectUtilities.DYNAMIC_WEB;
		} else if(propertyName.equals(EAR_PROJECT_NAME)){
			DataModelPropertyDescriptor[] validEars = getValidPropertyDescriptors(EAR_PROJECT_NAME);
			if(validEars.length > 0){
				return validEars[0].getPropertyDescription();
			}
		}
		return super.getDefaultProperty(propertyName);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		if (ADD_TO_EAR.equals(propertyName)) {
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(CONTEXT_ROOT, IDataModel.ENABLE_CHG);
		} else if(FACET_VERSION.equals(propertyName)){
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
		}
		return super.propertySet(propertyName, propertyValue);
	}

	public boolean isPropertyEnabled(String propertyName) {
		if (EAR_PROJECT_NAME.equals(propertyName) || CONTEXT_ROOT.equals(propertyName)) {
			return getBooleanProperty(ADD_TO_EAR);
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
	
	protected final int getJ2EEVersion() {
		return convertModuleVersionToJ2EEVersion((IProjectFacetVersion) getProperty(FACET_VERSION));
	}

	protected int convertModuleVersionToJ2EEVersion(IProjectFacetVersion version) {
		if (WebFacetUtils.WEB_22.equals(version)) {
			return J2EEVersionConstants.J2EE_1_2_ID;
		} else if (WebFacetUtils.WEB_23.equals(version)) {
			return J2EEVersionConstants.J2EE_1_3_ID;
		}
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	private DataModelPropertyDescriptor[] getEARPropertyDescriptors(int j2eeVersion) {
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
			validateEAR(getStringProperty(EAR_PROJECT_NAME));
		} else if (name.equals(CONTEXT_ROOT) && getBooleanProperty(ADD_TO_EAR)) {
			return validateContextRoot(getStringProperty(CONTEXT_ROOT));
		} else if (name.equals(CONTENT_DIR)) {
			IStatus status = OK_STATUS;
			String webFolderName = model.getStringProperty(CONTENT_DIR);
			if (webFolderName == null || webFolderName.length() == 0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.WEBCONTENTFOLDER_EMPTY);
				status = WTPCommonPlugin.createErrorStatus(errorMessage);
				return status;
			}
		}
		return super.validate(name);
	}

	protected IStatus validateEAR(String earName) {
		if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS); //$NON-NLS-1$
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		} else if (earName == null || earName.equals("")) { //$NON-NLS-1$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		}
		// IProject earProject =
		// applicationCreationDataModel.getTargetProject();
		// if (null != earProject && earProject.exists()) {
		// if (earProject.isOpen()) {
		// try {
		// EARNatureRuntime earNature = (EARNatureRuntime)
		// earProject.getNature(IEARNatureConstants.NATURE_ID);
		// if (earNature == null) {
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EAR,
		// new Object[]{earProject.getName()}));
		// } else if (earNature.getJ2EEVersion() < getJ2EEVersion()) {
		// String earVersion =
		// EnterpriseApplicationCreationDataModel.getVersionString(earNature.getJ2EEVersion());
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.INCOMPATABLE_J2EE_VERSIONS,
		// new Object[]{earProject.getName(), earVersion}));
		// }
		// return OK_STATUS;
		// } catch (CoreException e) {
		// return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, -1, null, e);
		// }
		// }
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_ClOSED,
		// new Object[]{earProject.getName()}));
		// } else if (null != earProject && null != getTargetProject()) {
		// if (earProject.getName().equals(getTargetProject().getName())) {
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME,
		// new Object[]{earProject.getName()}));
		// } else if (!CoreFileSystemLibrary.isCaseSensitive()) {
		// if
		// (earProject.getName().toLowerCase().equals(getTargetProject().getName().toLowerCase()))
		// {
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME,
		// new Object[]{earProject.getName()}));
		// }
		// }
		// }
		// IStatus status =
		// applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME);
		// if (status.isOK()) {
		// status =
		// applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION);
		// }
		// return status;

		return OK_STATUS;
	}


	protected IStatus validateContextRoot(String contextRoot) {
		if (contextRoot.equals("") || contextRoot == null) { //$NON-NLS-1$
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Context_Root_cannot_be_empty_2", new Object[]{contextRoot}), null); //$NON-NLS-1$
		} else if (contextRoot.trim().equals(contextRoot)) {
			StringTokenizer stok = new StringTokenizer(contextRoot, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				for (int i = 0; i < token.length(); i++) {
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						Object[] invalidChar = new Object[]{(new Character(token.charAt(i))).toString()};
						String errorStatus = ProjectSupportResourceHandler.getString("The_character_is_invalid_in_a_context_root", invalidChar); //$NON-NLS-1$
						return J2EEPlugin.newErrorStatus(errorStatus, null);
					}
				}
			}
		} else
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Names_cannot_begin_or_end_with_whitespace_5", new Object[]{contextRoot}), null); //$NON-NLS-1$
		return OK_STATUS;
	}
}
