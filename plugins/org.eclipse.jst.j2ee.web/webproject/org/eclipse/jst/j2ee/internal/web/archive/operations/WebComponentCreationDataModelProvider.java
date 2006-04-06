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
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.AddWebComponentToEARDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.datamodel.properties.IAddWebComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @deprecated 
 * @see WebFacetProjectCreationDataModelProvider
 */

public class WebComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider implements IWebComponentCreationDataModelProperties {

	public WebComponentCreationDataModelProvider() {
		super();
	}

	public IDataModelOperation getDefaultOperation() {
		return new WebComponentCreationFacetOperation(model);
		//return new WebComponentCreationOperation(model);
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(USE_ANNOTATIONS);
		propertyNames.add(CONTEXT_ROOT);
		propertyNames.add(WEBCONTENT_FOLDER);
		return propertyNames;
	}

	/**
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	protected Integer getDefaultComponentVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new Integer(J2EEVersionConstants.WEB_2_4_ID);
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new Integer(J2EEVersionConstants.WEB_2_3_ID);
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new Integer(J2EEVersionConstants.WEB_2_2_ID);
			default :
				return new Integer(J2EEVersionConstants.WEB_2_4_ID);
		}
	}

	public AddComponentToEnterpriseApplicationDataModelProvider createAddComponentToEAR() {
		return new AddWebComponentToEARDataModelProvider();
	}

	public void init() {
		super.init();
		// setJ2EENatureID(IWebNatureConstants.J2EE_NATURE_ID);
		// setProperty(EDIT_MODEL_ID, IWebNatureConstants.EDIT_MODEL_ID);
		// getProjectDataModel().setProperty(ProjectCreationDataModel.PROJECT_NATURES, new
		// String[]{IModuleConstants.MODULE_NATURE_ID});
		// getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS,
		// new String[]{getDefaultJavaSourceFolderName()});

		updateOutputLocation();
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean retVal = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(USE_ANNOTATIONS)) {
			model.notifyPropertyChange(COMPONENT_VERSION, DataModelEvent.ENABLE_CHG);
		} else if (propertyName.equals(CONTEXT_ROOT)) {
			getAddComponentToEARDataModel().setProperty(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT, propertyValue);
		} else if (propertyName.equals(COMPONENT_NAME)) {
			if (!isPropertySet(CONTEXT_ROOT)) {
				model.notifyPropertyChange(CONTEXT_ROOT, DataModelEvent.VALUE_CHG);
				getAddComponentToEARDataModel().setProperty(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT, propertyValue);
				getAddComponentToEARDataModel().notifyPropertyChange(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT, IDataModel.DEFAULT_CHG);
			}
		} else if (propertyName.equals(WEBCONTENT_FOLDER)) {
			model.setProperty(DD_FOLDER, "/" + propertyValue + IPath.SEPARATOR + J2EEConstants.WEB_INF); //$NON-NLS-1$
			model.setProperty(MANIFEST_FOLDER, "/" + propertyValue + IPath.SEPARATOR + J2EEConstants.META_INF); //$NON-NLS-1$

		}
		return retVal;
	}

	private void updateOutputLocation() {
		// getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.OUTPUT_LOCATION,
		// getOutputLocation());
	}

	// private Object getOutputLocation() {
	// StringBuffer buf = new StringBuffer(getStringProperty(WEB_CONTENT));
	// buf.append(IPath.SEPARATOR);
	// buf.append(IWebNatureConstants.INFO_DIRECTORY);
	// buf.append(IPath.SEPARATOR);
	// buf.append(IWebNatureConstants.CLASSES_DIRECTORY);
	// return buf.toString();
	// }



	public Object getDefaultProperty(String propertyName) {
//		if (propertyName.equals(ADD_TO_EAR)) {
//			if (isEARSupported())
//				setProperty(ADD_TO_EAR, Boolean.TRUE);
//		}
		// if (propertyName.equals(WEB_CONTENT)) {
		// String webContentFolderPref =
		// J2EEPlugin.getDefault().getJ2EEPreferences().getJ2EEWebContentFolderName();
		// if (webContentFolderPref == null || webContentFolderPref.length() == 0)
		// webContentFolderPref = IWebNatureConstants.WEB_MODULE_DIRECTORY_;
		// return webContentFolderPref;
		// }
		if (propertyName.equals(CONTEXT_ROOT)) {
			return getProperty(COMPONENT_NAME);
		}
		// To do: after porting
		// if (propertyName.equals(SERVLET_VERSION)) {
		// int moduleVersion = getIntProperty(COMPONENT_VERSION);
		// int servletVersion = J2EEVersionConstants.SERVLET_2_2;
		// switch (moduleVersion) {
		// case J2EEVersionConstants.WEB_2_2_ID :
		// servletVersion = J2EEVersionConstants.SERVLET_2_2;
		// break;
		// case J2EEVersionConstants.WEB_2_3_ID :
		// case J2EEVersionConstants.WEB_2_4_ID :
		// servletVersion = J2EEVersionConstants.SERVLET_2_3;
		// break;
		// }
		// return new Integer(servletVersion);
		// }
		// if (propertyName.equals(JSP_VERSION)) {
		// int moduleVersion = getIntProperty(COMPONENT_VERSION);
		// int jspVersion = J2EEVersionConstants.JSP_1_2_ID;
		// switch (moduleVersion) {
		// case J2EEVersionConstants.WEB_2_2_ID :
		// jspVersion = J2EEVersionConstants.JSP_1_2_ID;
		// break;
		// case J2EEVersionConstants.WEB_2_3_ID :
		// case J2EEVersionConstants.WEB_2_4_ID :
		// jspVersion = J2EEVersionConstants.JSP_2_0_ID;
		// break;
		// }
		// return new Integer(jspVersion);
		// }
		if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + WebArtifactEdit.WEB_CONTENT + IPath.SEPARATOR + J2EEConstants.WEB_INF;
		}
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			return CreationConstants.DEFAULT_WEB_SOURCE_FOLDER;
		}
		if (propertyName.equals(MANIFEST_FOLDER)) {
			return IPath.SEPARATOR + WebArtifactEdit.WEB_CONTENT + IPath.SEPARATOR + J2EEConstants.META_INF;
		} else if (propertyName.equals(WEBCONTENT_FOLDER)) {
			return WebArtifactEdit.WEB_CONTENT;

		} else if (propertyName.equals(MODULE_URI)) {
			return getProject().getName()+IJ2EEModuleConstants.WAR_EXT;
		}
		return super.getDefaultProperty(propertyName);
	}

	public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName) {
		if (propertyName.equals(COMPONENT_VERSION)) {
			Integer propertyValue = (Integer) getProperty(propertyName);
			String description = null;
			switch (propertyValue.intValue()) {
				case J2EEVersionConstants.WEB_2_2_ID :
					description = J2EEVersionConstants.VERSION_2_2_TEXT;
					break;
				case J2EEVersionConstants.WEB_2_3_ID :
					description = J2EEVersionConstants.VERSION_2_3_TEXT;
					break;
				case J2EEVersionConstants.WEB_2_4_ID :
				default :
					description = J2EEVersionConstants.VERSION_2_4_TEXT;
					break;
			}
			return new DataModelPropertyDescriptor(propertyValue, description);
		}
		return super.getPropertyDescriptor(propertyName);
	}

	protected DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		DataModelPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new DataModelPropertyDescriptor[1];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new DataModelPropertyDescriptor[2];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
				descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_3_ID), J2EEVersionConstants.VERSION_2_3_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new DataModelPropertyDescriptor[3];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
				descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_3_ID), J2EEVersionConstants.VERSION_2_3_TEXT);
				descriptors[2] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_4_ID), J2EEVersionConstants.VERSION_2_4_TEXT);
				break;
		}
		return descriptors;
	}

	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		switch (moduleVersion) {
			case J2EEVersionConstants.WEB_2_2_ID :
				return J2EEVersionConstants.J2EE_1_2_ID;
			case J2EEVersionConstants.WEB_2_3_ID :
				return J2EEVersionConstants.J2EE_1_3_ID;
			case J2EEVersionConstants.WEB_2_4_ID :
				return J2EEVersionConstants.J2EE_1_4_ID;
		}
		return -1;
	}

	protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
		switch (j2eeVersion.intValue()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return new Integer(J2EEVersionConstants.WEB_2_2_ID);
			case J2EEVersionConstants.J2EE_1_3_ID :
				return new Integer(J2EEVersionConstants.WEB_2_3_ID);
			case J2EEVersionConstants.J2EE_1_4_ID :
				return new Integer(J2EEVersionConstants.WEB_2_4_ID);
		}
		return super.convertJ2EEVersionToModuleVersion(j2eeVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#getModuleType()
	 */
	protected EClass getComponentType() {
		return CommonarchivePackage.eINSTANCE.getWARFile();
	}

	protected String getComponentExtension() {
		return ".war"; //$NON-NLS-1$
	}

	public boolean isPropertyEnabled(String propertyName) {
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return false;
			return true;
		}
		return super.isPropertyEnabled(propertyName);
	}

	public IStatus validate(String propertyName) {
		if (propertyName.equals(CONTEXT_ROOT)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				return getAddComponentToEARDataModel().validateProperty(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT);
			}
			return OK_STATUS;
		} else if (propertyName.equals(WEBCONTENT_FOLDER)) {
			IStatus status = OK_STATUS;
			String webFolderName = model.getStringProperty(WEBCONTENT_FOLDER);
			if (webFolderName == null || webFolderName.length() == 0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.WEBCONTENTFOLDER_EMPTY);
				status = WTPCommonPlugin.createErrorStatus(errorMessage);
				return status;
			}
		}
		return super.validate(propertyName);
	}

	public void propertyChanged(DataModelEvent event) {
		super.propertyChanged(event);
		if (event.getDataModel() == getAddComponentToEARDataModel() && event.getPropertyName().equals(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT) && event.getDataModel().isPropertySet(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT)) {
			setProperty(CONTEXT_ROOT, event.getProperty());
		}
		// else if (event.getDataModel() == getServerTargetDataModel() &&
		// event.getPropertyName().equals(ServerTargetDataModel.RUNTIME_TARGET_ID) &&
		// event.getDataModel().isSet(ServerTargetDataModel.RUNTIME_TARGET_ID))
		// setProperty(ADD_TO_EAR, updateAddToEar());
	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		return super.getValidPropertyDescriptors(propertyName);
	}
	
	protected String getJ2EEProjectType() {
		return J2EEProjectUtilities.DYNAMIC_WEB;
	}

}
