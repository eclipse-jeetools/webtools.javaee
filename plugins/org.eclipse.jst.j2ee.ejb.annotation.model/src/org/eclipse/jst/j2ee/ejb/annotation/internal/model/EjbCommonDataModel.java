/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public abstract class EjbCommonDataModel extends
		J2EEModelModifierOperationDataModel {

	public static final String EJB_NAME = "EjbCommonDataModel.EJB_NAME"; //$NON-NLS-1$

	public static final String JNDI_NAME = "EjbCommonDataModel.JNDI_NAME"; //$NON-NLS-1$

	public static final String DISPLAY_NAME = "EjbCommonDataModel.DISPLAY_NAME"; //$NON-NLS-1$

	public static final String DESCRIPTION = "EjbCommonDataModel.DESCRIPTION"; //$NON-NLS-1$

	public static final String CLASS_NAME = "EjbCommonDataModel.CLASS_NAME"; //$NON-NLS-1$

	public static final String STATELESS = "EjbCommonDataModel.STATELESS";

	public static final String TRANSACTIONTYPE = "EjbCommonDataModel.TRANSACTIONTYPE";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(EJB_NAME);
		addValidBaseProperty(DISPLAY_NAME);
		addValidBaseProperty(JNDI_NAME);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(CLASS_NAME);
		addValidBaseProperty(STATELESS);
		addValidBaseProperty(TRANSACTIONTYPE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(EJB_NAME))
			return validateEJBName(getStringProperty(propertyName));
		if (propertyName.equals(JNDI_NAME))
			return validateJndiName(getStringProperty(propertyName));
		if (propertyName.equals(DISPLAY_NAME))
			return validateDisplayName(getStringProperty(propertyName));
		if (propertyName.equals(CLASS_NAME))
			return validateClassName(getStringProperty(propertyName));
		if (propertyName.equals(STATELESS))
			return validateStateless(getStringProperty(propertyName));
		if (propertyName.equals(TRANSACTIONTYPE))
			return validateTransaction(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateStateless(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_STATELESS_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Stateless") >= 0 || prop.indexOf("Stateful") >= 0) {
			String msg = IEJBAnnotationConstants.ERR_STATELESS_VALUE;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateTransaction(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_TRANSACTION_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Container") >= 0 || prop.indexOf("Bean") >= 0) {
			String msg = IEJBAnnotationConstants.ERR_TRANSACTION_VALUE;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateJndiName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_JNDI_NAME_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf(" ") >= 0) {
			String msg = IEJBAnnotationConstants.ERR_JNDI_NAME_VALUE;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateEJBName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_EJB_NAME_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Bean") >= 0) {
			String msg = IEJBAnnotationConstants.ERR_EJB_NAME_ENDS_WITH_BEAN;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateDisplayName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_DISPLAY_NAME_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}

		// check for duplicate
		EJBJar ejbJar = (EJBJar) getDeploymentDescriptorRoot();
		if (ejbJar != null) {
			List ejbs = ejbJar.getEnterpriseBeans();
			if (ejbs != null && ejbs.size() > 0) {
				for (int i = 0; i < ejbs.size(); i++) {
					EnterpriseBean ejb = (EnterpriseBean) ejbs.get(i);
					if (prop.equals(ejb.getDisplayName())) {
						String msg = IEJBAnnotationConstants.ERR_EJB_DISPLAY_NAME_USED;
						return WTPCommonPlugin.createErrorStatus(msg);
					}
				}
			}
		}

		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateClassName(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = J2EECommonMessages.getResourceString(
					J2EECommonMessages.ERR_JAVA_CLASS_NAME_EMPTY,
					new String[] { prop });
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// check for duplicate
		EJBJar ejbJar = (EJBJar) getDeploymentDescriptorRoot();
		List ejbs = ejbJar.getEnterpriseBeans();
		if (ejbs != null && ejbs.size() > 0) {
			for (int i = 0; i < ejbs.size(); i++) {
				EnterpriseBean ejb = (EnterpriseBean) ejbs.get(i);
				if (prop.equals(ejb.getEjbClass().getQualifiedName())) {
					String msg = IEJBAnnotationConstants.ERR_EJB_CLASS_NAME_USED;
					return WTPCommonPlugin.createErrorStatus(msg);
				}
			}
		}

		return WTPCommonPlugin.OK_STATUS;
	}
}