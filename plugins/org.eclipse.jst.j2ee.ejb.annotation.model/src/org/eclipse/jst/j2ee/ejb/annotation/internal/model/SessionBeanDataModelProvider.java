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
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddSessionBeanOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class SessionBeanDataModelProvider extends EnterpriseBeanClassDataModelProvider implements ISessionBeanDataModelProperties {
	protected final static String DEFAULT_EJB_SUPERCLASS = "java.lang.Object"; //$NON-NLS-1$ 
	protected final static String[] DEFAULT_EJB_INTERFACES = {"javax.ejb.SessionBean"}; //$NON-NLS-1$

	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public IDataModelOperation getDefaultOperation() {
		return new AddSessionBeanOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to add their own data model's properties as valid base properties.
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	public String[] getPropertyNames() {
		String[] props = new String[]{STATELESS, EJB_INTERFACES};
        return combineProperties(super.getPropertyNames(), props);
	}
	
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		else if (propertyName.equals(EJB_TYPE))
			return "SessionBean"; //$NON-NLS-1$
		else if (propertyName.equals(STATELESS))
			return SessionType.STATELESS_LITERAL.getName();
		else if (propertyName.equals(MODIFIER_ABSTRACT))
			return Boolean.TRUE;
		else if (propertyName.equals(SUPERCLASS))
			return DEFAULT_EJB_SUPERCLASS;
		else if (propertyName.equals(EJB_INTERFACES))
			return DEFAULT_EJB_INTERFACES;
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	public IStatus validate(String propertyName) {
		if (propertyName.equals(STATELESS))
			return validateStateless(getStringProperty(propertyName));
		return super.validate(propertyName);
	}

	protected List getEJBInterfaces() {
		if (this.interfaceList == null) {
			this.interfaceList = new ArrayList();
			for (int i = 0; i < ((String[])getProperty(EJB_INTERFACES)).length; i++) {
				this.interfaceList.add(((String[])getProperty(EJB_INTERFACES))[i]);
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
		if (prop.indexOf("Stateless") >= 0 || prop.indexOf("Stateful") >= 0) { //$NON-NLS-1$ //$NON-NLS-2$
			return WTPCommonPlugin.OK_STATUS;
		}
		String msg = IEJBAnnotationConstants.ERR_STATELESS_VALUE;
		return WTPCommonPlugin.createErrorStatus(msg);
	}	
	
	protected void initializeDelegate() {
		SessionBeanDelegate delegate = new SessionBeanDelegate();
		delegate.setDataModel(getDataModel());
		this.setProperty(MODELDELEGATE,delegate);
		//Set the defaults so that they are propagated via events
		this.setProperty(STATELESS, this.getProperty(STATELESS));
		this.setProperty(TRANSACTIONTYPE, this.getProperty(TRANSACTIONTYPE));
	}
	
}