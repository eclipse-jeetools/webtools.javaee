/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.model.ISessionBeanDelegate;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;



public class SessionBeanDelegate implements ISessionBeanDelegate {


	private Session session;
	private SessionBeanDataModel sessionBeanDataModel;
	
	public SessionBeanDelegate() {
		super();
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.IEnterpriseBeanDelegate#getEnterrpriseBeanDataModel()
	 */
	public EjbCommonDataModel getEnterpriseBeanDataModel() {
		return sessionBeanDataModel;
	}	
	
	public EnterpriseBean getEjb() {
		return session;
	}
	public void setEjb(EnterpriseBean session) {
		this.session = (Session)session;
	}
	
	public SessionBeanDataModel getSessionBeanDataModel() {
		return sessionBeanDataModel;
	}

	public WTPOperationDataModel getDataModel() {
		return sessionBeanDataModel;
	}
	
	public void setEnterpriseBeanDataModel(
			EjbCommonDataModel sessionBeanDataModel) {
		this.sessionBeanDataModel = (SessionBeanDataModel)sessionBeanDataModel;
	}

	public String getJndiName() {
		return sessionBeanDataModel.getStringProperty(SessionBeanDataModel.JNDI_NAME);
	}
	
	public String getEjbName() {
		return sessionBeanDataModel.getStringProperty(SessionBeanDataModel.EJB_NAME);
	}

	public String getInterfaces() {
		NewJavaClassDataModel classDataModel = (NewJavaClassDataModel)sessionBeanDataModel.getNestedModel("NewEJBJavaClassDataModel");
		List ints = (List)classDataModel.getProperty(NewJavaClassDataModel.INTERFACES);
		Iterator iterator =  ints.iterator();
		String intStr = (iterator.hasNext()? (String)iterator.next() : "javax.ejb.SessionBean");
		while (iterator.hasNext()) {
			String intrfc = (String) iterator.next();
			intStr += ", " + intrfc ;
		}
		
		return intStr;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getSimpleClassName()
	 */
	public String getSimpleClassName() {
		NewJavaClassDataModel classDataModel = (NewJavaClassDataModel)sessionBeanDataModel.getNestedModel("NewEJBJavaClassDataModel");
		return classDataModel.getStringProperty(NewJavaClassDataModel.CLASS_NAME);
	}

	public String getSessionType() {		
		return session.getSessionType().getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getTransactionType()
	 */
	public String getTransactionType() {
		return session.getTransactionType().getName();
	}

	public String getDisplayName() {
		return sessionBeanDataModel.getStringProperty(SessionBeanDataModel.DISPLAY_NAME);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getDescription()
	 */
	public String getDescription() {
		return sessionBeanDataModel.getStringProperty(SessionBeanDataModel.DESCRIPTION);
	}

}
