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
 * Created on Jan 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CreateSessionBeanDataModel extends CreateEnterpriseBeanWithClientViewDataModel {
	/**
	 * The session type either stateful or stateless. (Optional - defaults to Stateless)
	 * 
	 * @link org.eclipse.jst.j2ee.internal.internal.ejb.SessionType
	 */
	public static final String SESSION_TYPE = "CreateSessionBeanDataModel.sessionType"; //$NON-NLS-1$
	/**
	 * The name of session type either stateful or stateless. (Optional - defaults to Stateless)
	 * 
	 * @link String
	 */
	public static final String SESSION_TYPE_NAME = "CreateSessionBeanDataModel.sessionTypeName"; //$NON-NLS-1$
	/**
	 * The transaction type either container or bean. (Optional - defaults to container)
	 * 
	 * @link com.ibm.wtp.ejb.TransactionType
	 */
	public static final String TRANSACTION_TYPE = "CreateSessionBeanDataModel.transactionType"; //$NON-NLS-1$
	/**
	 * The name of the transaction type either container or bean. (Optional - defaults to container)
	 * 
	 * @link String
	 */
	public static final String TRANSACTION_TYPE_NAME = "CreateSessionBeanDataModel.transactionTypeName"; //$NON-NLS-1$

	private static final String SESSION_INTERFACE_TYPE = "javax.ejb.SessionBean"; //$NON-NLS-1$
	/**
	 * Add service endpoint client view property. (Optional - Defaults to false for Session beans
	 * and true for entities in a 2.1 EJB project. This property is not used if the project version
	 * is less than 2.1.)
	 * 
	 * @link Boolean
	 * 
	 * @since EJB 2.1
	 */
	public static final String ADD_SERVICE_ENDPOINT = "CreateSessionBeanDataModel.ADD_SERVICE_ENDPOINT"; //$NON-NLS-1$
	/**
	 * The service endpoint interface qualified name. (Optional - Defaults based on the bean name
	 * and the default package name (i.e., [DEFAULT_PACKAGE_NAME].[BEAN_NAME]Local).)
	 * 
	 * @link String
	 * 
	 * @since EJB 2.1
	 */
	public static final String SERVICE_ENDPOINT_INTERFACE = "CreateSessionBeanDataModel.SERVICE_ENDPOINT_INTERFACE"; //$NON-NLS-1$
	private static final String SERVICE_ENDPOINT_INTERFACE_TYPE = "java.rmi.Remote"; //$NON-NLS-1$

	/**
	 *  
	 */
	public CreateSessionBeanDataModel() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new CreateSessionBeanOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanWithClientViewDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SESSION_TYPE);
		addValidBaseProperty(SESSION_TYPE_NAME);
		addValidBaseProperty(TRANSACTION_TYPE);
		addValidBaseProperty(TRANSACTION_TYPE_NAME);
		addValidBaseProperty(ADD_SERVICE_ENDPOINT);
		addValidBaseProperty(SERVICE_ENDPOINT_INTERFACE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanWithClientViewDataModel#getDefaultAddLocalFor2x()
	 */
	protected Boolean getDefaultAddLocalFor2x() {
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanWithClientViewDataModel#getDefaultAddRemoteFor2x()
	 */
	protected Boolean getDefaultAddRemoteFor2x() {
		return Boolean.TRUE;
	}

	/**
	 * @return
	 */
	private Object getDefaultAddServiceEndpoint() {
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanWithClientViewDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(SESSION_TYPE))
			return SessionType.STATELESS_LITERAL;
		if (propertyName.equals(SESSION_TYPE_NAME))
			return SessionType.STATELESS_LITERAL.getName();
		if (propertyName.equals(TRANSACTION_TYPE))
			return TransactionType.CONTAINER_LITERAL;
		if (propertyName.equals(TRANSACTION_TYPE_NAME))
			return TransactionType.CONTAINER_LITERAL.getName();
		if (propertyName.equals(ADD_SERVICE_ENDPOINT))
			return getDefaultAddServiceEndpoint();
		if (propertyName.equals(SERVICE_ENDPOINT_INTERFACE))
			return getDefaultServiceEndpointInterface();
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#getBeanClassEJBInterfaceName()
	 */
	protected String getBeanClassEJBInterfaceName() {
		return SESSION_INTERFACE_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#getEJBType()
	 */
	public int getEJBType() {
		return CreateEnterpriseBeanDataModel.EJB_TYPE_SESSION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanWithClientViewDataModel#getInterfaceType(java.lang.String)
	 */
	public String getInterfaceType(String propertyName) {
		if (propertyName.equals(SERVICE_ENDPOINT_INTERFACE))
			return SERVICE_ENDPOINT_INTERFACE_TYPE;
		return super.getInterfaceType(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(SESSION_TYPE_NAME))
			return getValidSessionTypeNames();
		if (propertyName.equals(TRANSACTION_TYPE_NAME))
			return getValidTransactionTypeNames();
		return super.doGetValidPropertyValues(propertyName);
	}

	/**
	 * @return
	 */
	private String[] getValidTransactionTypeNames() {
		return getStringValues(TransactionType.VALUES);
	}

	/**
	 * @return
	 */
	private String[] getValidSessionTypeNames() {
		return getStringValues(SessionType.VALUES);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanWithClientViewDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(SESSION_TYPE_NAME)) {
			doSetProperty(SESSION_TYPE, SessionType.get((String) propertyValue));
			notifyEnablementChange(ADD_SERVICE_ENDPOINT);
			notifyEnablementChange(SERVICE_ENDPOINT_INTERFACE);
			notifyValidValuesChange(BEAN_SUPEREJB_NAME);
			if ((getProperty(SESSION_TYPE) == SessionType.STATEFUL_LITERAL))
				setProperty(ADD_SERVICE_ENDPOINT, Boolean.FALSE);
			if (getBooleanProperty(ADD_SERVICE_ENDPOINT))
				addServiceEndpointValues();
			else
				removeServiceEndpointValues();
		} else if (propertyName.equals(TRANSACTION_TYPE_NAME)) {
			doSetProperty(TRANSACTION_TYPE, TransactionType.get((String) propertyValue));
		} else if (propertyName.equals(ADD_SERVICE_ENDPOINT)) {
			if (((Boolean) propertyValue).booleanValue())
				addServiceEndpointValues();
			else
				removeServiceEndpointValues();
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			if (((Boolean) propertyValue).booleanValue()) {
				setBooleanProperty(ADD_SERVICE_ENDPOINT, false);
				setProperty(SERVICE_ENDPOINT_INTERFACE, null);
			}
			notifyEnablementChange(ADD_SERVICE_ENDPOINT);
			notifyEnablementChange(SERVICE_ENDPOINT_INTERFACE);
		}
		return notify;
	}

	private void addServiceEndpointValues() {
		notifyDefaultChange(SERVICE_ENDPOINT_INTERFACE);
	}

	private void removeServiceEndpointValues() {
		if (isSet(SERVICE_ENDPOINT_INTERFACE))
			setProperty(SERVICE_ENDPOINT_INTERFACE, null);
		else
			notifyDefaultChange(SERVICE_ENDPOINT_INTERFACE);
	}

	/**
	 * @return
	 */
	private Object getDefaultServiceEndpointInterface() {
		if (isVersion21OrGreater() && getBooleanProperty(ADD_SERVICE_ENDPOINT))
			return getDefaultClassName("Service"); //$NON-NLS-1$
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(SERVICE_ENDPOINT_INTERFACE)) {
			boolean sessionType = ((getProperty(SESSION_TYPE) == SessionType.STATELESS_LITERAL));
			Boolean addEndpoint = (Boolean) getProperty(ADD_SERVICE_ENDPOINT);
			if (sessionType && addEndpoint.booleanValue())
				return Boolean.TRUE;
			return Boolean.FALSE;
		} else if (propertyName.equals(ADD_SERVICE_ENDPOINT)) {
			if (getBooleanProperty(USE_ANNOTATIONS))
				return Boolean.FALSE;
			else if (getProperty(SESSION_TYPE) == SessionType.STATELESS_LITERAL)
				return Boolean.TRUE;
			else
				return Boolean.FALSE;
		}
		return super.basicIsEnabled(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanWithClientViewDataModel#beanNameChanged()
	 */
	protected void beanNameChanged() {
		super.beanNameChanged();
		if (getBooleanProperty(ADD_SERVICE_ENDPOINT))
			notifyDefaultChange(SERVICE_ENDPOINT_INTERFACE);
	}

	/**
	 * @return
	 */
	protected IStatus validateAddProperties() {
		if (!getBooleanProperty(ADD_LOCAL) && !getBooleanProperty(ADD_REMOTE) && !getBooleanProperty(ADD_SERVICE_ENDPOINT))
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Atleast_one_client_with_service"), null); //$NON-NLS-1$
		return OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#processAndFilterSuperEJBNames(java.util.List)
	 */
	protected Object[] processAndFilterSuperEJBNames(List beans) {
		List remove = new ArrayList();
		SessionType type = (SessionType) getProperty(SESSION_TYPE);
		if (type == SessionType.STATELESS_LITERAL) {
			Iterator iter = beans.iterator();
			while (iter.hasNext()) {
				EnterpriseBean bean = (EnterpriseBean) iter.next();
				if (bean.isSession()) {
					if (((Session) bean).getSessionType() == SessionType.STATEFUL_LITERAL) {
						remove.add(bean);
					}
				}
			}
		}
		beans.removeAll(remove);
		return super.processAndFilterSuperEJBNames(beans);
	}
}