/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.reference;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.webservices.WebServicesManager;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

public abstract class ReferenceDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Required, type ApplicationClient, WebApp, EJBJar
	 */
	public static final String OWNER = "ReferenceDataModel.OWNER"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String REF_NAME = "ReferenceDataModel.REF_NAME"; //$NON-NLS-1$

	/**
	 * Optional, type String
	 */
	public static final String DESCRIPTION = "ReferenceDataModel.DESCRIPTION"; //$NON-NLS-1$
	/**
	 * set by model String, j2ee version via J2eeConstants
	 */
	public static final String MODULE_TYPE = "ReferenceDataModel.MODULE_TYPE"; //$NON-NLS-1$

	/**
	 * set by model INTEGER, j2ee version
	 */
	public static final String J2EE_VERSION = "ReferenceDataModel.J2EE_VERSION"; //$NON-NLS-1$

	//ejb = 0, appclient = 1, web = 2
	public static final int EJB_TYPE = 0;
	public static final int APP_CLIENT_TYPE = 1;
	public static final int WEB_TYPE = 2;

	protected int ownerType;

	protected void init() {
		super.init();
	}

	protected void initializeOwnerNature() {
		EObject owner = (EObject) getProperty(OWNER);
		if (owner != null) {
			IProject ownerProject = ProjectUtilities.getProject(owner);
			if (ownerProject != null) {
				if (owner instanceof EnterpriseBean) {
					ownerType = EJB_TYPE;
				} else if (owner instanceof ApplicationClient) {
					ownerType = APP_CLIENT_TYPE;
				} else if (owner instanceof WebApp) {
					ownerType = WEB_TYPE;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(OWNER);
		addValidBaseProperty(REF_NAME);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(J2EE_VERSION);
		addValidBaseProperty(MODULE_TYPE);
		super.initValidBaseProperties();
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean doSet = super.doSetProperty(propertyName, propertyValue);
		if (OWNER.equals(propertyName)) {
			try {
				initializeOwnerNature();
				notifyDefaultChange(MODULE_TYPE);
				notifyDefaultChange(J2EE_VERSION);
				IProject proj = ProjectUtilities.getProject((EObject) propertyValue);
				if (proj != null)
					setProperty(EditModelOperationDataModel.PROJECT_NAME, proj.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return doSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(MODULE_TYPE)) {
			return calculateOwnerModuleType();
		}
		if (propertyName.equals(J2EE_VERSION)) {
			int version = calculateJ2EEVersionForOwnerModule();
			return new Integer(version);
		}
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	private Object calculateOwnerModuleType() {
		EObject owner = (EObject) getProperty(OWNER);
		if (owner != null) {
			IProject ownerProject = ProjectUtilities.getProject(owner);
			if (ownerProject != null) {
				if (owner instanceof EnterpriseBean) {
					return J2EEConstants.EJBJAR_ID;
				} else if (owner instanceof ApplicationClient) {
					return J2EEConstants.APP_CLIENT_ID;
				} else if (owner instanceof WebApp) {
					return J2EEConstants.WEBAPP_ID;
				}
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	private int calculateJ2EEVersionForOwnerModule() {
		String moduleType = getStringProperty(MODULE_TYPE);
		if (moduleType == null)
			return -1;
		if (moduleType.equals(J2EEConstants.EJBJAR_ID)) {
			switch (((EnterpriseBean) getProperty(OWNER)).getVersionID()) {
				case J2EEVersionConstants.EJB_1_0_ID :
					return J2EEVersionConstants.J2EE_1_2_ID;
				case J2EEVersionConstants.EJB_1_1_ID :
					return J2EEVersionConstants.J2EE_1_2_ID;
				case J2EEVersionConstants.EJB_2_0_ID :
					return J2EEVersionConstants.J2EE_1_3_ID;
				case J2EEVersionConstants.EJB_2_1_ID :
					return J2EEVersionConstants.J2EE_1_4_ID;
				default :
					return J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleType.equals(J2EEConstants.WEBAPP_ID)) {
			switch (((WebApp) getProperty(OWNER)).getVersionID()) {
				case J2EEVersionConstants.WEB_2_2_ID :
					return J2EEVersionConstants.J2EE_1_2_ID;
				case J2EEVersionConstants.WEB_2_3_ID :
					return J2EEVersionConstants.J2EE_1_3_ID;
				case J2EEVersionConstants.WEB_2_4_ID :
					return J2EEVersionConstants.J2EE_1_4_ID;
				default :
					return J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleType.equals(J2EEConstants.APP_CLIENT_ID)) {
			return ((ApplicationClient) getProperty(OWNER)).getVersionID();
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (OWNER.equals(propertyName)) {
			if (getProperty(OWNER) == null)
				return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("ReferenceDataModel_UI_3")); //$NON-NLS-1$
		}
		if (REF_NAME.equals(propertyName)) {
			String nameValue = getStringProperty(propertyName);
			//TODO:find solution to empty string
			if (nameValue == null || nameValue.trim().equals("")) { //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("ReferenceDataModel_UI_5")); //$NON-NLS-1$
			}
			if (!validateReferenceDoesNotExist(nameValue.trim())) {
				return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("ReferenceDataModel_UI_6")); //$NON-NLS-1$
			}
		}
		return super.doValidateProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#validateReferenceDoesNotExist(java.lang.String)
	 */
	public boolean validateReferenceDoesNotExist(String nameValue) {
		if (ownerType == EJB_TYPE) {
			EnterpriseBean bean = (EnterpriseBean) getProperty(ReferenceDataModel.OWNER);
			if (checkResourceRefExists(bean.getResourceRefs(), nameValue))
				return false;
			if (checkResourceEnvRefExists(bean.getResourceEnvRefs(), nameValue))
				return false;
			if (checkEJBLocalRefExists(bean.getEjbLocalRefs(), nameValue))
				return false;
			if (checkEJBRefExists(bean.getEjbRefs(), nameValue))
				return false;
			if (checkSecurityRoleRefExists(bean.getSecurityRoleRefs(), nameValue))
				return false;
			if (bean.getVersionID() >= J2EEVersionConstants.EJB_2_1_ID) {
				if (checkServiceRefExists(bean.getServiceRefs(), nameValue))
					return false;
				if (checkMessageDestRefExists(bean.getMessageDestinationRefs(), nameValue))
					return false;
			}
			else {
				Collection temp = WebServicesManager.getInstance().get13ServiceRefs(bean);
				if (temp != null && !temp.isEmpty() && checkServiceRefExists((List) temp, nameValue))
					return false;
			}
		} else if (ownerType == APP_CLIENT_TYPE) {
			ApplicationClient appClient = (ApplicationClient) getProperty(ReferenceDataModel.OWNER);
			if (checkResourceRefExists(appClient.getResourceRefs(), nameValue))
				return false;
			if (checkResourceEnvRefExists(appClient.getResourceEnvRefs(), nameValue))
				return false;
			if (checkEJBRefExists(appClient.getEjbReferences(), nameValue))
				return false;
			if (appClient.getVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
				if (checkServiceRefExists(appClient.getServiceRefs(), nameValue))
					return false;
				if (checkMessageDestRefExists(appClient.getMessageDestinationRefs(), nameValue))
					return false;
			}
			else {
				Collection temp = WebServicesManager.getInstance().get13ServiceRefs(appClient);
				if (temp != null && !temp.isEmpty() && checkServiceRefExists((List) temp, nameValue))
					return false;
			}
		} else if (ownerType == WEB_TYPE) {
			WebApp webApp = (WebApp) getProperty(ReferenceDataModel.OWNER);
			if (checkResourceRefExists(webApp.getResourceRefs(), nameValue))
				return false;
			if (checkResourceEnvRefExists(webApp.getResourceEnvRefs(), nameValue))
				return false;
			if (checkEJBLocalRefExists(webApp.getEjbLocalRefs(), nameValue))
				return false;
			if (checkEJBRefExists(webApp.getEjbRefs(), nameValue))
				return false;
			if (webApp.getVersionID() >= J2EEVersionConstants.WEB_2_4_ID) {
				if (checkServiceRefExists(webApp.getServiceRefs(), nameValue))
					return false;
				if (checkMessageDestRefExists(webApp.getMessageDestinationRefs(), nameValue))
					return false;
			}
			else {
				Collection temp = WebServicesManager.getInstance().get13ServiceRefs(webApp);
				if (temp != null && !temp.isEmpty() && checkServiceRefExists((List) temp, nameValue))
					return false;
			}
		}
		return true;
	}

	//	private boolean checkSecurityRoleRefExists(EList list) {
	//		return checkSecurityRoleRefExists(list, getStringProperty(REF_NAME));
	//	}

	/**
	 * @param list
	 * @return
	 */
	private boolean checkSecurityRoleRefExists(EList list, String refName) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				SecurityRoleRef ref = (SecurityRoleRef) list.get(i);
				if (ref.getName() != null && ref.getName().equals(refName)) {
					return true;
				}
			}
		}
		return false;
	}

	//	private boolean checkEJBRefExists(EList list) {
	//		return checkEJBRefExists(list, getStringProperty(REF_NAME));
	//	}

	/**
	 * @param list
	 */
	private boolean checkEJBRefExists(EList list, String refName) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				EjbRef ref = (EjbRef) list.get(i);
				if (ref.getName() != null && ref.getName().equals(refName)) {
					return true;
				}
			}
		}
		return false;
	}

	//	private boolean checkEJBLocalRefExists(EList list) {
	//		return checkEJBLocalRefExists(list, getStringProperty(REF_NAME));
	//	}

	/**
	 * @param list
	 */
	private boolean checkEJBLocalRefExists(EList list, String refName) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				EJBLocalRef ref = (EJBLocalRef) list.get(i);
				if (ref.getName() != null && ref.getName().equals(refName)) {
					return true;
				}
			}
		}
		return false;
	}

	//	private boolean checkServiceRefExists(EList list) {
	//		return checkServiceRefExists(list, getStringProperty(REF_NAME));
	//	}

	/**
	 * @param list
	 */
	private boolean checkServiceRefExists(List list, String refName) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ServiceRef ref = (ServiceRef) list.get(i);
				if (ref.getServiceRefName() != null && ref.getServiceRefName().equals(refName)) {
					return true;
				}
			}
		}
		return false;
	}

	//	private boolean checkResourceEnvRefExists(EList list) {
	//		return checkResourceEnvRefExists(list, getStringProperty(REF_NAME));
	//	}

	/**
	 * @param list
	 */
	private boolean checkResourceEnvRefExists(EList list, String refName) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ResourceEnvRef ref = (ResourceEnvRef) list.get(i);
				if (ref.getName() != null && ref.getName().equals(refName)) {
					return true;
				}
			}
		}
		return false;
	}

	//	private boolean checkResourceRefExists(EList list) {
	//		return checkResourceRefExists(list, getStringProperty(REF_NAME));
	//	}

	/**
	 * @param list
	 */
	private boolean checkResourceRefExists(EList list, String refName) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ResourceRef ref = (ResourceRef) list.get(i);
				if (ref.getName() != null && ref.getName().equals(refName)) {
					return true;
				}
			}
		}
		return false;
	}

	//	private boolean checkMessageDestRefExists(EList list) {
	//		return checkMessageDestRefExists(list, getStringProperty(REF_NAME));
	//	}

	/**
	 * @param list
	 */
	private boolean checkMessageDestRefExists(EList list, String refName) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				MessageDestinationRef ref = (MessageDestinationRef) list.get(i);
				if (ref.getName() != null && ref.getName().equals(refName)) {
					return true;
				}
			}
		}
		return false;
	}
}