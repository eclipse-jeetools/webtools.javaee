/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;

//TODO delete
/**
 * @deprecated
 *
 */
public class WebComponentCreationDataModel extends J2EEComponentCreationDataModel {

	/**
	 * Type Integer
	 */
	public static final String SERVLET_VERSION = "WebModuleCreationDataModel.SERVLET_VERSION"; //$NON-NLS-1$
	/**
	 * Type Integer
	 */
	public static final String JSP_VERSION = "WebModuleCreationDataModel.JSP_VERSION"; //$NON-NLS-1$
	/**
	 * Type String
	 */
	public static final String CONTEXT_ROOT = AddWebModuleToEARDataModel.CONTEXT_ROOT;




	public WTPOperation getDefaultOperation() {
		return new WebComponentCreationOperation(this);
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


	protected void init() {
		//setJ2EENatureID(IWebNatureConstants.J2EE_NATURE_ID);
		//setProperty(EDIT_MODEL_ID, IWebNatureConstants.EDIT_MODEL_ID);
//		getProjectDataModel().setProperty(ProjectCreationDataModel.PROJECT_NATURES, new String[]{IModuleConstants.MODULE_NATURE_ID});
//		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{getDefaultJavaSourceFolderName()});
		updateOutputLocation();
		super.init();
	}



	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean retVal = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(USE_ANNOTATIONS)) {
			notifyEnablementChange(COMPONENT_VERSION);
		} else if (propertyName.equals(COMPONENT_VERSION)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				setProperty(USE_ANNOTATIONS, Boolean.FALSE);
			notifyEnablementChange(USE_ANNOTATIONS);
		} else if (propertyName.equals(CONTEXT_ROOT)) {
			getAddModuleToApplicationDataModel().setProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT, propertyValue);
		} else if (propertyName.equals(COMPONENT_NAME)) {
			if (!isSet(CONTEXT_ROOT)) {
				notifyDefaultChange(CONTEXT_ROOT);
				((AddWebModuleToEARDataModel) getAddModuleToApplicationDataModel()).defaultContextRoot=(String)propertyValue;
				getAddModuleToApplicationDataModel().notifyDefaultChange(AddWebModuleToEARDataModel.CONTEXT_ROOT);
			}
		}
		return retVal;
	}

	private void updateOutputLocation() {
//		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.OUTPUT_LOCATION, getOutputLocation());
	}

//	private Object getOutputLocation() {
//		StringBuffer buf = new StringBuffer(getStringProperty(WEB_CONTENT));
//		buf.append(IPath.SEPARATOR);
//		buf.append(IWebNatureConstants.INFO_DIRECTORY);
//		buf.append(IPath.SEPARATOR);
//		buf.append(IWebNatureConstants.CLASSES_DIRECTORY);
//		return buf.toString();
//	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SERVLET_VERSION);
		addValidBaseProperty(JSP_VERSION);
		addValidBaseProperty(USE_ANNOTATIONS);
		addValidBaseProperty(CONTEXT_ROOT);
	}

	protected AddComponentToEnterpriseApplicationDataModel createModuleNestedModel() {
		return  new AddWebModuleToEARDataModel();	
	}

	private Object updateAddToEar() {
		//IRuntime type = getServerTargetDataModel().getRuntimeTarget();
//		Boolean ret = Boolean.FALSE;
//		IRuntime type = getProjectDataModel().getServerTargetDataModel().getRuntimeTarget();
//		if (type == null)
//			return Boolean.TRUE;
//		IRuntimeType rType = type.getRuntimeType();
//		if (rType == null)
//			return Boolean.TRUE;
//		return ret;
		//return new Boolean(!rType.getVendor().equals(APACHE_VENDER_NAME));
		return null;
	}

	protected Object getDefaultProperty(String propertyName) {


		if (propertyName.equals(ADD_TO_EAR)) {
			return updateAddToEar();

		}

//		if (propertyName.equals(WEB_CONTENT)) {
//			String webContentFolderPref = J2EEPlugin.getDefault().getJ2EEPreferences().getJ2EEWebContentFolderName();
//			if (webContentFolderPref == null || webContentFolderPref.length() == 0)
//				webContentFolderPref = IWebNatureConstants.WEB_MODULE_DIRECTORY_;
//			return webContentFolderPref;
//		}
		if (propertyName.equals(CONTEXT_ROOT)) {
			return getProperty(COMPONENT_NAME);
		}

		if (propertyName.equals(SERVLET_VERSION)) {
			int moduleVersion = getIntProperty(COMPONENT_VERSION);
			int servletVersion = J2EEVersionConstants.SERVLET_2_2;
			switch (moduleVersion) {
				case J2EEVersionConstants.WEB_2_2_ID :
					servletVersion = J2EEVersionConstants.SERVLET_2_2;
					break;
				case J2EEVersionConstants.WEB_2_3_ID :
				case J2EEVersionConstants.WEB_2_4_ID :
					servletVersion = J2EEVersionConstants.SERVLET_2_3;
					break;
			}
			return new Integer(servletVersion);
		}
		if (propertyName.equals(JSP_VERSION)) {
			int moduleVersion = getIntProperty(COMPONENT_VERSION);
			int jspVersion = J2EEVersionConstants.JSP_1_2_ID;
			switch (moduleVersion) {
				case J2EEVersionConstants.WEB_2_2_ID :
					jspVersion = J2EEVersionConstants.JSP_1_2_ID;
					break;
				case J2EEVersionConstants.WEB_2_3_ID :
				case J2EEVersionConstants.WEB_2_4_ID :
					jspVersion = J2EEVersionConstants.JSP_2_0_ID;
					break;
			}
			return new Integer(jspVersion);
		}
		if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "WebContent"+IPath.SEPARATOR + J2EEConstants.WEB_INF;
		}		
		if (propertyName.equals(J2EEComponentCreationDataModel.JAVASOURCE_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "JavaSource";
		}		
		if (propertyName.equals(MANIFEST_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "WebContent"+IPath.SEPARATOR + J2EEConstants.META_INF;
		}			
		return super.getDefaultProperty(propertyName);
	}

	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
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
			return new WTPPropertyDescriptor(propertyValue, description);
		}
		return super.doGetPropertyDescriptor(propertyName);
	}

	protected WTPPropertyDescriptor[] getValidComponentVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		WTPPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new WTPPropertyDescriptor[1];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new WTPPropertyDescriptor[2];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_3_ID), J2EEVersionConstants.VERSION_2_3_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new WTPPropertyDescriptor[3];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_3_ID), J2EEVersionConstants.VERSION_2_3_TEXT);
				descriptors[2] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_4_ID), J2EEVersionConstants.VERSION_2_4_TEXT);
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
		return CommonarchiveFactoryImpl.getPackage().getWARFile();
	}

	protected String getComponentExtension() {
		return ".war"; //$NON-NLS-1$
	}

	protected Boolean basicIsEnabled(String propertyName) {
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return Boolean.FALSE;
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(CONTEXT_ROOT)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				return getAddModuleToApplicationDataModel().validateProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT);
			}
			return OK_STATUS;

		}
		return super.doValidateProperty(propertyName);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
		if (event.getDataModel() == getAddModuleToApplicationDataModel() && event.getPropertyName().equals(AddWebModuleToEARDataModel.CONTEXT_ROOT) && event.getDataModel().isSet(AddWebModuleToEARDataModel.CONTEXT_ROOT)) {
			setProperty(CONTEXT_ROOT, event.getProperty());
		} //else if (event.getDataModel() == getServerTargetDataModel() && event.getPropertyName().equals(ServerTargetDataModel.RUNTIME_TARGET_ID) && event.getDataModel().isSet(ServerTargetDataModel.RUNTIME_TARGET_ID))
			//setProperty(ADD_TO_EAR, updateAddToEar());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
	 */
	protected String getComponentID() {
		return IModuleConstants.JST_WEB_MODULE;
	}
}