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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

/**
 * This dataModel is used for to create EJB Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public class EjbComponentCreationDataModel extends J2EEComponentCreationDataModel {

	public static final String CREATE_CLIENT = "EJBModuleCreationDataModel.CREATE_CLIENT"; //$NON-NLS-1$
	
	public static final String CREATE_DEFAULT_SESSION_BEAN = "EJBModuleCreationDataModel.CREATE_DEFAULT_SESSION_BEAN"; //$NON-NLS-1$

	private static final String NESTED_MODEL_EJB_CLIENT_CREATION = "EJBModuleCreationDataModel.NESTED_MODEL_EJB_CLIENT_CREATION"; //$NON-NLS-1$
	
	private EJBClientComponentDataModel ejbClientComponentDataModel;

	public WTPOperation getDefaultOperation() {
		return new EjbComponentCreationOperation(this);
	}

	/**
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	protected Integer getDefaultComponentVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new Integer(J2EEVersionConstants.EJB_2_1_ID);
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new Integer(J2EEVersionConstants.EJB_2_0_ID);
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new Integer(J2EEVersionConstants.EJB_1_1_ID);
			default :
				return new Integer(J2EEVersionConstants.EJB_2_1_ID);
		}
	}
	public EJBClientComponentDataModel getNestedEJBClientComponentDataModel() {
		return ejbClientComponentDataModel;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean doSet = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(ADD_TO_EAR)) {
			if (!((Boolean) propertyValue).booleanValue()) {
				setProperty(CREATE_CLIENT, propertyValue);
			}
			notifyEnablementChange(CREATE_CLIENT);
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			notifyEnablementChange(COMPONENT_VERSION);
		} else if (propertyName.equals(COMPONENT_VERSION)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				setProperty(USE_ANNOTATIONS, Boolean.FALSE);
			notifyEnablementChange(USE_ANNOTATIONS);
		} else if (propertyName.equals(CREATE_CLIENT)) {
			//getNestedEJBClientComponentDataModel().setProperty(EJBClientComponentDataModel.CREATE_PROJECT, propertyValue);

			if (getBooleanProperty(CREATE_CLIENT)) {
				getNestedEJBClientComponentDataModel().enableValidation();
			} else {
				getNestedEJBClientComponentDataModel().disableValidation();
			}
		} else if (propertyName.equals(COMPONENT_NAME)) {
			ejbClientComponentDataModel.setProperty(EJBClientComponentDataModel.EJB_COMPONENT_NAME, propertyValue);
			if (!ejbClientComponentDataModel.isSet(ComponentCreationDataModel.COMPONENT_NAME))
				ejbClientComponentDataModel.notifyDefaultChange(ComponentCreationDataModel.COMPONENT_NAME);
			if (!ejbClientComponentDataModel.isSet(EJBClientComponentDataModel.CLIENT_COMPONENT_URI))
				ejbClientComponentDataModel.notifyDefaultChange(EJBClientComponentDataModel.CLIENT_COMPONENT_URI);

			if( getBooleanProperty(CREATE_CLIENT)){
				ejbClientComponentDataModel.setProperty(EJBClientComponentDataModel.CREATE_PROJECT, getProperty(CREATE_CLIENT));
				ejbClientComponentDataModel.setProperty(ComponentCreationDataModel.PROJECT_NAME, ejbClientComponentDataModel.getComponentName());
			}	
		}
		
		if (getBooleanProperty(CREATE_CLIENT)) {
			if (propertyName.equals(CREATE_CLIENT) || propertyName.equals(PROJECT_NAME) || propertyName.equals(ADD_TO_EAR)
						|| propertyName.equals(EAR_MODULE_DEPLOY_NAME) || propertyName.equals(COMPONENT_DEPLOY_NAME)) {
				ejbClientComponentDataModel.setProperty(EJBClientComponentDataModel.EJB_PROJECT_NAME, getProperty(PROJECT_NAME));
				ejbClientComponentDataModel.setProperty(EJBClientComponentDataModel.EAR_MODULE_DEPLOY_NAME, getProperty(EAR_MODULE_DEPLOY_NAME));
				ejbClientComponentDataModel.setProperty(EJBClientComponentDataModel.EJB_COMPONENT_DEPLOY_NAME, getProperty(COMPONENT_DEPLOY_NAME));
			}
		}
		

		return doSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(CREATE_CLIENT);
		super.initValidBaseProperties();
		addValidBaseProperty(CREATE_DEFAULT_SESSION_BEAN);
	}

	protected void initNestedModels() {
		super.initNestedModels();
		ejbClientComponentDataModel = new EJBClientComponentDataModel();
		addNestedModel(NESTED_MODEL_EJB_CLIENT_CREATION, ejbClientComponentDataModel);
		ejbClientComponentDataModel.setEarComponentHandle(getEarComponentHandle());
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
		if (propertyName.equals(CREATE_CLIENT)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(CREATE_DEFAULT_SESSION_BEAN)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + getModuleName()+IPath.SEPARATOR + "ejbModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
		}else if (propertyName.equals(JAVASOURCE_FOLDER)) {
			return IPath.SEPARATOR + getModuleName()+IPath.SEPARATOR + "ejbModule";
		} else if (propertyName.equals(MANIFEST_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "ejbModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
		} else {
			return super.getDefaultProperty(propertyName);
		}	
	}

	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
		if (propertyName.equals(COMPONENT_VERSION)) {
			Integer propertyValue = (Integer) getProperty(propertyName);
			String description = null;
			switch (propertyValue.intValue()) {
				case J2EEVersionConstants.EJB_1_1_ID :
					description = J2EEVersionConstants.VERSION_1_1_TEXT;
					break;
				case J2EEVersionConstants.EJB_2_0_ID :
					description = J2EEVersionConstants.VERSION_2_0_TEXT;
					break;
				case J2EEVersionConstants.EJB_2_1_ID :
				default :
					description = J2EEVersionConstants.VERSION_2_1_TEXT;
					break;
			}
			return new WTPPropertyDescriptor(propertyValue, description);
		}
		return super.doGetPropertyDescriptor(propertyName);
	}
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(NESTED_MODEL_VALIDATION_HOOK)) {
			if (getBooleanProperty(CREATE_CLIENT)) {
				String clientName = ejbClientComponentDataModel.getStringProperty(ComponentCreationDataModel.COMPONENT_NAME);
				String moduleName = getStringProperty(PROJECT_NAME);
				if (clientName.equals(moduleName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				}
				if (!CoreFileSystemLibrary.isCaseSensitive()) {
					if (clientName.toLowerCase().equals(moduleName.toLowerCase())) {
						return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
					}
				}

				if (getBooleanProperty(ADD_TO_EAR)) {
					String earName = getStringProperty(EAR_MODULE_NAME);
					if (clientName.equals(earName)) {
						return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EAR));
					}
					if (!CoreFileSystemLibrary.isCaseSensitive()) {
						if (clientName.toLowerCase().equals(earName.toLowerCase())) {
							return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EAR));
						}
					}
				}
			}
		}
		return super.doValidateProperty(propertyName);
	}

	protected WTPPropertyDescriptor[] getValidComponentVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		WTPPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new WTPPropertyDescriptor[1];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_1_1_ID), J2EEVersionConstants.VERSION_1_1_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new WTPPropertyDescriptor[2];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_1_1_ID), J2EEVersionConstants.VERSION_1_1_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_2_0_ID), J2EEVersionConstants.VERSION_2_0_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new WTPPropertyDescriptor[3];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_1_1_ID), J2EEVersionConstants.VERSION_1_1_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_2_0_ID), J2EEVersionConstants.VERSION_2_0_TEXT);
				descriptors[2] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_2_1_ID), J2EEVersionConstants.VERSION_2_1_TEXT);
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
		return CommonarchiveFactoryImpl.getPackage().getEJBJarFile();
	}

	protected String getComponentExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	protected Boolean basicIsEnabled(String propertyName) {
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return Boolean.FALSE;
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getComponentID() {
        return IModuleConstants.JST_EJB_MODULE;
    }
}