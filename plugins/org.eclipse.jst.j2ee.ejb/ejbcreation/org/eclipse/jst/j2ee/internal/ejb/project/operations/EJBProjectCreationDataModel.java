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
 * Created on Oct 31, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.internal.jdt.integration.JavaProjectCreationDataModel;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EJBProjectCreationDataModel extends J2EEModuleCreationDataModel {

	private static final String NESTED_MODEL_EJB_CLIENT_CREATION = "EJBProjectCreationDataModel.NESTED_MODEL_EJB_CLIENT_CREATION"; //$NON-NLS-1$
	public static final String CREATE_DEFAULT_SESSION_BEAN = "AppClientProjectCreationDataModel.CREATE_DEFAULT_SESSION_BEAN"; //$NON-NLS-1$
	private EJBClientProjectDataModel ejbClientProjectDataModel;

	public WTPOperation getDefaultOperation() {
		return new EJBProjectCreationOperation(this);
	}

	/**
	 * Optional - Indicates if this model is for an EJB client project. defaults to false
	 * 
	 * @return Boolean
	 */
	public static final String CREATE_CLIENT = "EJBProjectCreationDataModel.IS_CLIENT"; //$NON-NLS-1$

	protected void init() {
		j2eeNatureID = IEJBNatureConstants.NATURE_ID;
		super.init();
		setProperty(EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
		serverTargetDataModel.setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.EJB_TYPE);
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.OUTPUT_LOCATION, IEJBNatureConstants.DEFAULT_EJB_MODULE_PATH);
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{IEJBNatureConstants.DEFAULT_EJB_MODULE_PATH});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(CREATE_CLIENT);
		super.initValidBaseProperties();
		addValidBaseProperty(CREATE_DEFAULT_SESSION_BEAN);
	}

	protected void initNestedModels() {
		super.initNestedModels();
		ejbClientProjectDataModel = new EJBClientProjectDataModel();
		addNestedModel(NESTED_MODEL_EJB_CLIENT_CREATION, ejbClientProjectDataModel);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION))
			return getDefaultJ2EEVersion();
		if (propertyName.equals(CREATE_CLIENT))
			return Boolean.TRUE;
		if (propertyName.equals(CREATE_DEFAULT_SESSION_BEAN))
			return Boolean.FALSE;

		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	private Object getDefaultJ2EEVersion() {
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

	protected String convertVersionIDtoLabel(int id) {
		switch (id) {
			case J2EEVersionConstants.EJB_1_0_ID :
				return J2EEVersionConstants.VERSION_1_0_TEXT;
			case J2EEVersionConstants.EJB_1_1_ID :
				return J2EEVersionConstants.VERSION_1_1_TEXT;
			case J2EEVersionConstants.EJB_2_0_ID :
				return J2EEVersionConstants.VERSION_2_0_TEXT;
			case J2EEVersionConstants.EJB_2_1_ID :
				return J2EEVersionConstants.VERSION_2_1_TEXT;
		}
		return ""; //$NON-NLS-1$
	}

	protected Integer convertVersionLabeltoID(String label) {
		int id = -1;
		if (label == null)
			return null;
		else if (label.equals(J2EEVersionConstants.VERSION_1_0_TEXT))
			id = J2EEVersionConstants.EJB_1_0_ID;
		else if (label.equals(J2EEVersionConstants.VERSION_1_1_TEXT))
			id = J2EEVersionConstants.EJB_1_1_ID;
		else if (label.equals(J2EEVersionConstants.VERSION_2_0_TEXT))
			id = J2EEVersionConstants.EJB_2_0_ID;
		else if (label.equals(J2EEVersionConstants.VERSION_2_1_TEXT))
			id = J2EEVersionConstants.EJB_2_1_ID;

		return new Integer(id);
	}

	/**
	 * @return Return a String[] of the valid J2EE versions for the selected J2EE Preference Level.
	 */
	protected Object[] getValidJ2EEVersionLabels() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new String[]{J2EEVersionConstants.VERSION_1_1_TEXT, J2EEVersionConstants.VERSION_2_0_TEXT, J2EEVersionConstants.VERSION_2_1_TEXT};
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new String[]{J2EEVersionConstants.VERSION_1_1_TEXT, J2EEVersionConstants.VERSION_2_0_TEXT};
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new String[]{J2EEVersionConstants.VERSION_1_1_TEXT};
			default :
				return new String[]{J2EEVersionConstants.VERSION_1_1_TEXT, J2EEVersionConstants.VERSION_2_0_TEXT, J2EEVersionConstants.VERSION_2_1_TEXT};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#convertModuleVersionToJ2EEVersion(java.lang.Integer)
	 */
	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		switch (moduleVersion) {
			case J2EEVersionConstants.EJB_1_0_ID :
				return J2EEVersionConstants.J2EE_1_2_ID;
			case J2EEVersionConstants.EJB_1_1_ID :
				return J2EEVersionConstants.J2EE_1_2_ID;
			case J2EEVersionConstants.EJB_2_0_ID :
				return J2EEVersionConstants.J2EE_1_3_ID;
			case J2EEVersionConstants.EJB_2_1_ID :
				return J2EEVersionConstants.J2EE_1_4_ID;
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#convertJ2EEVersionToModuleVersion(java.lang.Integer)
	 */
	protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
		int version = convertJ2EEVersionToModuleversion(j2eeVersion.intValue());
		if (version > -1)
			return new Integer(version);
		return super.convertJ2EEVersionToModuleVersion(j2eeVersion);
	}

	protected int convertJ2EEVersionToModuleversion(int j2eeVersion) {
		switch (j2eeVersion) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return J2EEVersionConstants.EJB_1_1_ID;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return J2EEVersionConstants.EJB_2_0_ID;
			case J2EEVersionConstants.J2EE_1_4_ID :
				return J2EEVersionConstants.EJB_2_1_ID;
		}
		return -1;
	}

	protected EClass getModuleType() {
		return CommonarchiveFactoryImpl.getPackage().getEJBJarFile();
	}

	protected String getModuleExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean doSet = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(ADD_TO_EAR)) {
			if (!((Boolean) propertyValue).booleanValue()) {
				setProperty(CREATE_CLIENT, propertyValue);
			}
			notifyEnablementChange(CREATE_CLIENT);
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			ejbClientProjectDataModel.setProperty(EJBClientProjectDataModel.USE_ANNOTATIONS, propertyValue);
			if (((Boolean) propertyValue).booleanValue()) {
				if (getIntProperty(J2EE_MODULE_VERSION) == J2EEVersionConstants.EJB_1_1_ID) {
					setProperty(J2EE_MODULE_VERSION_LBL, null);
				} else
					notifyEnablementChange(J2EE_MODULE_VERSION);
			} else
				notifyEnablementChange(J2EE_MODULE_VERSION);
		} else if (propertyName.equals(J2EE_MODULE_VERSION)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				setProperty(USE_ANNOTATIONS, Boolean.FALSE);
			//else
			//    setProperty(USE_ANNOTATIONS,Boolean.TRUE);
			notifyEnablementChange(USE_ANNOTATIONS);
		} else if (propertyName.equals(CREATE_CLIENT)) {
			if (getBooleanProperty(CREATE_CLIENT)) {
				getNestEJBClientProjectDM().enableValidation();
			} else {
				getNestEJBClientProjectDM().disableValidation();
			}
		}
		if (getBooleanProperty(CREATE_CLIENT)) {
			if (propertyName.equals(CREATE_CLIENT) || propertyName.equals(PROJECT_NAME) || propertyName.equals(ADD_TO_EAR)) {
				ejbClientProjectDataModel.setProperty(EJBClientProjectDataModel.EJB_PROJECT_NAME, getProperty(PROJECT_NAME));
			}
		}
		return doSet;
	}

	public EJBClientProjectDataModel getNestEJBClientProjectDM() {
		return ejbClientProjectDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(CREATE_CLIENT))
			return (Boolean) getProperty(ADD_TO_EAR);
		else if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return Boolean.FALSE;
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(NESTED_MODEL_VALIDATION_HOOK)) {
			if (getBooleanProperty(CREATE_CLIENT)) {
				String clientName = ejbClientProjectDataModel.getStringProperty(EJBClientProjectDataModel.CLIENT_PROJECT_NAME);
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
					String earName = getStringProperty(EAR_PROJECT_NAME);
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

}