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

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.DESTINATION_TYPE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.JMS;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.MESSAGE_LISTENER_INTERFACE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SUPERCLASS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class NewMessageDrivenBeanClassDataModelProvider extends NewEnterpriseBeanClassDataModelProvider {

	public static final String QUALIFIED_JMS_MESSAGELISTENER = "javax.jms.MessageListener"; //$NON-NLS-1$

	@Override
	public IDataModelOperation getDefaultOperation() {
		return new AddMessageDrivenBeanOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to add their own data model's properties as valid base
	 * properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	@Override
	public Set<String> getPropertyNames() {
		// Add Bean specific properties defined in this data model
		Set<String> propertyNames = super.getPropertyNames();

		propertyNames.add(DESTINATION_TYPE);
		propertyNames.add(JMS);
		propertyNames.add(MESSAGE_LISTENER_INTERFACE);

		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own default values for any of the
	 * properties in the data model hierarchy. This method does not accept a null parameter. It may
	 * return null. 
	 * 
	 * @see NewJavaClassDataModelProvider#getDefaultProperty(String)
	 * @see IDataModelProvider#getDefaultProperty(String)
	 * 
	 * @param propertyName
	 * @return Object default value of property
	 */
	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(INTERFACES)){
			List<String> list = new ArrayList<String>();
			list.add(QUALIFIED_JMS_MESSAGELISTENER);
			return list;
		} else if (propertyName.equals(SUPERCLASS)) {
			return ""; //$NON-NLS-1$
		} else if (propertyName.equals(JMS)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(DESTINATION_TYPE)) {
			return DestinationType.QUEUE.toString();
		} else if (propertyName.equals(MESSAGE_LISTENER_INTERFACE)) {
			return QUALIFIED_JMS_MESSAGELISTENER;
		}
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * Subclasses may extend this method to add their own specific behavior when a certain property
	 * in the data model hierarchy is set. This method does not accept null for the property name,
	 * but it will for propertyValue. It will not return null. It will return false if the set
	 * fails. This implementation verifies the display name is set to the classname, that the
	 * annotations is disabled/enabled properly, and that the target project name is determined from
	 * the source folder setting.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#propertySet(String,
	 *      Object)
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @return boolean was property set?
	 */
	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean result = false;
		
		if (propertyName.equals(JMS)) {
			updateInterfaces();
			updateMessageListenerInterfaces();
			result = true;
		}
		
		if (propertyName.equals(INTERFACES)) {
			updateMessageListenerInterfaces();
		}
		
		if (propertyName.equals(MESSAGE_LISTENER_INTERFACE)) {
			boolean needSetJMS = QUALIFIED_JMS_MESSAGELISTENER.equals(propertyValue);
			if (getDataModel().getBooleanProperty(JMS) != needSetJMS)
				getDataModel().setProperty(JMS, needSetJMS);
			result = true;
		}
		
		return result || super.propertySet(propertyName, propertyValue);
	}

	@Override
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(DESTINATION_TYPE)) {
			return DataModelPropertyDescriptor.createDescriptors(
					new String[] { 
							DestinationType.QUEUE.toString(), 
							DestinationType.TOPIC.toString()
					}, 
					new String[] {
							EJBCreationResourceHandler.DESTINATION_TYPE_QUEUE, 
							EJBCreationResourceHandler.DESTINATION_TYPE_TOPIC
					});
		} 

		return super.getValidPropertyDescriptors(propertyName);
	}

	@Override
	public IStatus validate(String propertyName) {
		if (propertyName.equals(MESSAGE_LISTENER_INTERFACE)) {
			String value = (String) getProperty(MESSAGE_LISTENER_INTERFACE);
			if (value == null || value.trim().length() == 0){
				getProperty(INTERFACES);
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.ERR_NO_MESSAGE_LISTENER_INTERFACE);
			}
		}
		return super.validate(propertyName);
	}

	private void updateInterfaces() {
		List<String> interfacesList = (List<String>) getDataModel().getProperty(INTERFACES);
		boolean isJMS = getDataModel().getBooleanProperty(JMS);
		if (isJMS) {
			if (!interfacesList.contains(QUALIFIED_JMS_MESSAGELISTENER))
				interfacesList.add(QUALIFIED_JMS_MESSAGELISTENER);
		} else {
			interfacesList.remove(QUALIFIED_JMS_MESSAGELISTENER);
		}
		getDataModel().setProperty(INTERFACES, interfacesList);
		getDataModel().notifyPropertyChange(INTERFACES, DataModelEvent.VALUE_CHG);
	}

	private void updateMessageListenerInterfaces() {
		boolean isJMS = getDataModel().getBooleanProperty(JMS);
		if (isJMS){
			getDataModel().setProperty(MESSAGE_LISTENER_INTERFACE, QUALIFIED_JMS_MESSAGELISTENER);
		} else {
			String value = getStringProperty(MESSAGE_LISTENER_INTERFACE);
			if (value == null || value.length() == 0 || QUALIFIED_JMS_MESSAGELISTENER.equals(value)) {
				List<String> interfaces = (List<String>) getProperty(INTERFACES);
				String messageListenerInterface = ""; //$NON-NLS-1$
				if (interfaces.size() > 0) {
					messageListenerInterface = interfaces.get(0);
				}
				getDataModel().setProperty(MESSAGE_LISTENER_INTERFACE, messageListenerInterface);
			}
		}
	}

}
