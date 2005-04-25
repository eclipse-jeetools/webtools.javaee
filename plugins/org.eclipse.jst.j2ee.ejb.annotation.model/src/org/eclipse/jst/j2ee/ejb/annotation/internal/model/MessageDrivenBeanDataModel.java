/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddMessageDrivenBeanOperation;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class MessageDrivenBeanDataModel extends EjbCommonDataModel implements IAnnotationsDataModel {
	public static final String DESTINATIONNAME = "MessageDrivenBeanDataModel.DESTINATIONNAME"; //$NON-NLS-1$
	public static final String DESTINATIONTYPE = "MessageDrivenBeanDataModel.DESTINATIONTYPE"; //$NON-NLS-1$

	public final static String EJB_SUPERCLASS = "java.lang.Object"; //$NON-NLS-1$ 
	public final static String[] EJB_INTERFACES = {"javax.ejb.MessageDrivenBean" //$NON-NLS-1$
													, "javax.jms.MessageListener" //$NON-NLS-1$ 
	};
	
	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddMessageDrivenBeanOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(DESTINATIONTYPE);
		addValidBaseProperty(DESTINATIONNAME);
	}


	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		else if (propertyName.equals(DESTINATIONNAME))
			return getProperty(JNDI_NAME);
		else if (propertyName.equals(EJB_TYPE))
			return "MessageDrivenBean";
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(DESTINATIONNAME))
			return validateJndiName(getStringProperty(propertyName));
		if (propertyName.equals(DESTINATIONTYPE))
			return validateDestinationType(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateDestinationType(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_DESTINATIONTYPE_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Queue") >= 0 || prop.indexOf("Topic") >= 0) {
			return WTPCommonPlugin.OK_STATUS;
		}
		String msg = IEJBAnnotationConstants.ERR_DESTINATIONTYPE_VALUE;
		return WTPCommonPlugin.createErrorStatus(msg);
	}


	public String getEjbSuperclassName() {
		return EJB_SUPERCLASS;
	}

	public List getEJBInterfaces() {
		if (this.interfaceList == null) {
			this.interfaceList = new ArrayList();
			for (int i = 0; i < EJB_INTERFACES.length; i++) {
				this.interfaceList.add(EJB_INTERFACES[i]);
			}
		}
		return this.interfaceList;
	}


	protected Boolean basicIsEnabled(String propertyName) {
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (((EnterpriseArtifactEdit)artifactEdit).getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3) {
				if (getBooleanProperty(USE_ANNOTATIONS))
					setBooleanProperty(USE_ANNOTATIONS, false);
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		} else if (propertyName.equals(JNDI_NAME))
			notifyDefaultChange(DESTINATIONNAME);
		return super.basicIsEnabled(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(USE_ANNOTATIONS)) {
			if (((Boolean) propertyValue).booleanValue() && ((EnterpriseArtifactEdit)artifactEdit).getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return true;
			notifyEnablementChange(USE_ANNOTATIONS);
		}
		return super.doSetProperty(propertyName, propertyValue);
	}
}