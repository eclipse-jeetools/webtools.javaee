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
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.model.IMessageDrivenBeanDelegate;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;



public class MessageDrivenBeanDelegate implements IMessageDrivenBeanDelegate {


	private MessageDriven messageDriven;
	private MessageDrivenBeanDataModel messageDrivenBeanDataModel;
	
	public MessageDrivenBeanDelegate() {
		super();
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.IEnterpriseBeanDelegate#getEnterrpriseBeanDataModel()
	 */
	public EjbCommonDataModel getEnterpriseBeanDataModel() {
		return messageDrivenBeanDataModel;
	}	
	
	public EnterpriseBean getEjb() {
		return messageDriven;
	}
	public void setEjb(EnterpriseBean messageDriven) {
		this.messageDriven = (MessageDriven)messageDriven;
	}
	
	public MessageDrivenBeanDataModel getMessageDrivenBeanDataModel() {
		return messageDrivenBeanDataModel;
	}

	public WTPOperationDataModel getDataModel() {
		return messageDrivenBeanDataModel;
	}
	
	public void setEnterpriseBeanDataModel(
			EjbCommonDataModel messageDrivenBeanDataModel) {
		this.messageDrivenBeanDataModel = (MessageDrivenBeanDataModel)messageDrivenBeanDataModel;
	}

	public String getJndiName() {
		return messageDrivenBeanDataModel.getStringProperty(MessageDrivenBeanDataModel.JNDI_NAME);
	}
	
	public String getEjbName() {
		return messageDrivenBeanDataModel.getStringProperty(MessageDrivenBeanDataModel.EJB_NAME);
	}

	public String getInterfaces() {
		NewJavaClassDataModel classDataModel = (NewJavaClassDataModel)messageDrivenBeanDataModel.getNestedModel("NewEJBJavaClassDataModel");
		List ints = (List)classDataModel.getProperty(NewJavaClassDataModel.INTERFACES);
		Iterator iterator =  ints.iterator();
		String intStr = (iterator.hasNext()? (String)iterator.next() : "javax.ejb.MessageDrivenBean, javax.jms.MessageListener");
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
		NewJavaClassDataModel classDataModel = (NewJavaClassDataModel)messageDrivenBeanDataModel.getNestedModel("NewEJBJavaClassDataModel");
		return classDataModel.getStringProperty(NewJavaClassDataModel.CLASS_NAME);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getTransactionType()
	 */
	public String getTransactionType() {
		return messageDriven.getTransactionType().getName();
	}

	public String getDisplayName() {
		return messageDrivenBeanDataModel.getStringProperty(MessageDrivenBeanDataModel.DISPLAY_NAME);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotation.model.ISessionBeanDelegate#getDescription()
	 */
	public String getDescription() {
		return messageDrivenBeanDataModel.getStringProperty(MessageDrivenBeanDataModel.DESCRIPTION);
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.model.IMessageDrivenBeanDelegate#getDestinationType()
	 */
	public String getDestinationType() {
		return messageDriven.getDestination().getType().getName();
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.model.IMessageDrivenBeanDelegate#getDestinationJndiName()
	 */
	public String getDestinationJndiName() {
		return messageDriven.getMessageSelector();
	}

}
