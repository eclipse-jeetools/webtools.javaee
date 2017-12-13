/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class SessionBeanDelegate extends EnterpriseBeanDelegate implements ISessionBean {
	public SessionBeanDelegate() {
		super();
		Session sessionBean = EjbFactory.eINSTANCE.createSession();
		this.setEnterpriseBean(sessionBean);
	}

	public void setDataModel(IDataModel dataModel) {
		super.setDataModel(dataModel);
		Session session = (Session) this.getEnterpriseBean();
		if (session == null)
			return;

		SessionType sessionBeanType = SessionType.STATELESS_LITERAL;
		if (dataModel.getStringProperty(ISessionBeanDataModelProperties.STATELESS).equals(SessionType.STATEFUL_LITERAL.getName()))
			sessionBeanType = SessionType.STATEFUL_LITERAL;
		session.setSessionType(sessionBeanType);

		TransactionType transactionType = TransactionType.CONTAINER_LITERAL;
		if (dataModel.getStringProperty(ISessionBeanDataModelProperties.TRANSACTIONTYPE)
				.equals(TransactionType.BEAN_LITERAL.getName()))
			transactionType = TransactionType.BEAN_LITERAL;
		session.setTransactionType(transactionType);

	}

	public String getSessionType() {
		Session session = (Session) this.getEnterpriseBean();
		return session.getSessionType().getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getTransactionType()
	 */
	public String getTransactionType() {
		Session session = (Session) this.getEnterpriseBean();
		return session.getTransactionType().getName();
	}

	/**
	 * This method permits us to keep emf model for the bean in sync with the
	 * changes in the datamodel
	 */

	public void propertyChanged(DataModelEvent event) {
		super.propertyChanged(event);
		String property = event.getPropertyName();
		Object propertyValue = event.getProperty();
		Session session = (Session) this.getEnterpriseBean();
		if (session == null)
			return;

		if (ISessionBeanDataModelProperties.STATELESS.equals(property)) {
			SessionType sessionBeanType = SessionType.STATELESS_LITERAL;
			if (propertyValue.equals(SessionType.STATEFUL_LITERAL.getName()))
				sessionBeanType = SessionType.STATEFUL_LITERAL;
			session.setSessionType(sessionBeanType);
		} else if (IEnterpriseBeanClassDataModelProperties.TRANSACTIONTYPE.equals(property)) {
			TransactionType transactionType = TransactionType.CONTAINER_LITERAL;
			if (propertyValue.equals(TransactionType.BEAN_LITERAL.getName()))
				transactionType = TransactionType.BEAN_LITERAL;
			session.setTransactionType(transactionType);
		}

	}
}
