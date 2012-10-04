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

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.MAPPED_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.DESTINATION_TYPE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.JMS;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.MESSAGE_LISTENER_INTERFACE;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.internal.common.operations.Method;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateMessageDrivenBeanTemplateModel extends
		CreateEnterpriseBeanTemplateModel {

	public static final String QUALIFIED_ACTIVATION_CONFIG_PROPERTY = "javax.ejb.ActivationConfigProperty"; //$NON-NLS-1$
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT = "javax.ejb.TransactionManagement"; //$NON-NLS-1$
	public static final String QUALIFIED_TRANSACTION_MANAGEMENT_TYPE = "javax.ejb.TransactionManagementType"; //$NON-NLS-1$
	public static final String QUALIFIED_JSM_MESSAGE = "javax.jms.Message"; //$NON-NLS-1$
	public static final String QUALIFIED_MESSAGE_DRIVEN = "javax.ejb.MessageDriven"; //$NON-NLS-1$
	public static final String ATT_ACTIVATION_CONFIG = "activationConfig"; //$NON-NLS-1$
	public static final String ATT_MESSAGE_LISTENER_INTERFACE = "messageListenerInterface"; //$NON-NLS-1$
	public static final String ON_MESSAGE = "onMessage"; //$NON-NLS-1$
	public static final String ON_MESSAGE_SIGNATURE = "(Ljavax/jms/Message;)V"; //$NON-NLS-1$
	public static final String PROP_DESTINATION_TYPE = "destinationType"; //$NON-NLS-1$
	public static final String PROP_DESTINATION = "destination"; //$NON-NLS-1$

	private static final String CLASS_SUFFIX = ".class"; //$NON-NLS-1$
	
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
		
		collection.add(dataModel.getStringProperty(MESSAGE_LISTENER_INTERFACE));

		return collection;
	}

	public Map<String, Object> getClassAnnotationParams() {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> activationConfigProperties = new HashMap<String, String>();
		result.put(ATT_ACTIVATION_CONFIG, activationConfigProperties);
		
		if (isJMS()){
			String destinationType = dataModel.getStringProperty(DESTINATION_TYPE);
			if (destinationType.equals(DestinationType.QUEUE.toString())) 
				activationConfigProperties.put(PROP_DESTINATION_TYPE, "javax.jms.Queue"); //$NON-NLS-1$
			else if (destinationType.equals(DestinationType.TOPIC.toString())) 
				activationConfigProperties.put(PROP_DESTINATION_TYPE, "javax.jms.Topic"); //$NON-NLS-1$
			else 
				throw new IllegalStateException("illegal destination type: " + destinationType); //$NON-NLS-1$
		}
		String dispName = getProperty(EJB_NAME).trim();
		if (!dispName.equals(getClassName()) && (dispName.length() > 0))
			result.put(ATT_NAME, QUOTATION_STRING + dispName + QUOTATION_STRING);
		String mappedName = getProperty(MAPPED_NAME).trim();
		if (mappedName != null && mappedName.length() > 0) {
			result.put(ATT_MAPPED_NAME, QUOTATION_STRING + mappedName + QUOTATION_STRING);
			if (isJMS()) {
				activationConfigProperties.put(PROP_DESTINATION, mappedName);
			}
		}
		String messageListenerInterface = getProperty(MESSAGE_LISTENER_INTERFACE);
		
		List<String> interfaces = getQualifiedInterfaces();
		if (interfaces.size() != 1 || !interfaces.contains(messageListenerInterface)) {
			result.put(ATT_MESSAGE_LISTENER_INTERFACE, 
					Signature.getSimpleName(messageListenerInterface) + CLASS_SUFFIX);
		}
			
		return result;
	}

	public boolean isJMS(){
		return dataModel.getBooleanProperty(JMS);
	}

	@Override
	public Collection<Method> getUnimplementedMethods() {
		Collection<Method> unimplementedMethods = super.getUnimplementedMethods();
		Iterator<Method> iterator = unimplementedMethods.iterator();
		
		while (iterator.hasNext()) {
			Method method = iterator.next();
			if (ON_MESSAGE.equals(method.getName()) && ON_MESSAGE_SIGNATURE.equals(method.getSignature())) {
				iterator.remove();
			}
		}
		
		return unimplementedMethods;
	}

}
