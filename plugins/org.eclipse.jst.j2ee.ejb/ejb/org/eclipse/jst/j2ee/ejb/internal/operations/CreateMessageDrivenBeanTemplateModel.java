/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateMessageDrivenBeanTemplateModel extends
		CreateEnterpriseBeanTemplateModel implements
		INewMessageDrivenBeanClassDataModelProperties {

	public static final String QUALIFIED_ACTIVATION_CONFIG_PROPERTY = "javax.ejb.ActivationConfigProperty"; //$NON-NLS-1$
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT = "javax.ejb.TransactionManagement"; //$NON-NLS-1$
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT_TYPE = "javax.ejb.TransactionManagementType"; //$NON-NLS-1$
	public static final String QUALIFIED_JSM_MESSAGE = "javax.jms.Message"; //$NON-NLS-1$
	public static final String QUALIFIED_MESSAGE_DRIVEN = "javax.ejb.MessageDriven"; //$NON-NLS-1$
	public static final String ATT_ACTIVATION_CONFIG = "activationConfig"; //$NON-NLS-1$
	public static final String ON_MESSAGE = "onMessage"; //$NON-NLS-1$
	public static final String ON_MESSAGE_SIGNATURE = "(Ljavax/jms/Message;)V"; //$NON-NLS-1$

	public CreateMessageDrivenBeanTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();

		collection.add(QUALIFIED_MESSAGE_DRIVEN);
		if (isJMS()){
			collection.add(QUALIFIED_ACTIVATION_CONFIG_PROPERTY);
			collection.add(NewMessageDrivenBeanClassDataModelProvider.QUALIFIED_JMS_MESSAGELISTENER);
			collection.add(QUALIFIED_JSM_MESSAGE);
		}
		if (!isContainerType()) {
			collection.add(QUALIFIED_TRANSACTION_MANAGEMENT);
			collection.add(QUALIFIED_TRANSACTION_MANAGEMENT_TYPE);
		}

		return collection;
	}

	public Map<String, String> getClassAnnotationParams() {
		Map<String, String> result = new Hashtable<String, String>();
		if (isJMS()){
			if (dataModel.getProperty(DESTINATION_TYPE) == DestinationType.QUEUE){
				result.put(ATT_ACTIVATION_CONFIG, "javax.jms.Queue");
			} else{
				result.put(ATT_ACTIVATION_CONFIG, "javax.jms.Topic");
			}
		}
		String dispName = getProperty(EJB_NAME);
		if (!dispName.equals(getClassName()))
			result.put(ATT_NAME, QUOTATION_STRING + dispName + QUOTATION_STRING);
		String mappedName = getProperty(MAPPED_NAME).trim();
		if (mappedName != null && mappedName.length() > 0) {
			result.put(ATT_MAPPED_NAME, QUOTATION_STRING + mappedName + QUOTATION_STRING);
		}
		return result;
	}

	public boolean isJMS(){
		return dataModel.getBooleanProperty(INewMessageDrivenBeanClassDataModelProperties.JMS);
	}



	@Override
	public Collection<Method> getUnimplementedMethods() {
		Collection<Method> unimplementedMethods = super.getUnimplementedMethods();
		for (Method method : unimplementedMethods) {
			if (ON_MESSAGE.equals(method.getName()) && ON_MESSAGE_SIGNATURE.equals(method.getSignature())) {
				unimplementedMethods.remove(method);
				break;
			}
		}
		return unimplementedMethods;
	}

}
