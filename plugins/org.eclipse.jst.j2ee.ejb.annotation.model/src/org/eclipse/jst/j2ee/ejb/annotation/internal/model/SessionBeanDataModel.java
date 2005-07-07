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
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddSessionBeanOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class SessionBeanDataModel extends EnterpriseBeanClassDataModel implements IAnnotationsDataModel {
	public static final String STATELESS = "SessionBeanDataModel.STATELESS";
	public final static String EJB_SUPERCLASS = "java.lang.Object"; //$NON-NLS-1$ 
	public final static String[] EJB_INTERFACES = {"javax.ejb.SessionBean" //$NON-NLS-1$
	};

	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddSessionBeanOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(STATELESS);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		else if (propertyName.equals(EJB_TYPE))
			return "SessionBean";
		else if (propertyName.equals(STATELESS))
			return SessionType.STATELESS_LITERAL.getName();
		else if (propertyName.equals(MODIFIER_ABSTRACT))
			return Boolean.TRUE;
		else if (propertyName.equals(SUPERCLASS))
			return getEjbSuperclassName();
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(STATELESS))
			return validateStateless(getStringProperty(propertyName));
		return super.doValidateProperty(propertyName);
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



	private IStatus validateStateless(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_STATELESS_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Stateless") >= 0 || prop.indexOf("Stateful") >= 0) {
			return WTPCommonPlugin.OK_STATUS;
		}
		String msg = IEJBAnnotationConstants.ERR_STATELESS_VALUE;
		return WTPCommonPlugin.createErrorStatus(msg);
	}	
	
	protected void initializeDelegate() {
		SessionBeanDelegate delegate = new SessionBeanDelegate();
		delegate.setDataModel(this);
		this.setProperty(MODELDELEGATE,delegate);
		//Set the defaults so that they are propagated via events
		this.setProperty(STATELESS, this.getProperty(STATELESS));
		this.setProperty(TRANSACTIONTYPE, this.getProperty(TRANSACTIONTYPE));
	}
	
}