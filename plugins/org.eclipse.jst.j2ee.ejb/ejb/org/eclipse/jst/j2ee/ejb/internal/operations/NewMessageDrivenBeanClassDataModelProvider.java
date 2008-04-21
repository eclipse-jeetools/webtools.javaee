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
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SUPERCLASS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class NewMessageDrivenBeanClassDataModelProvider extends NewEnterpriseBeanClassDataModelProvider {

	public static final String QUALIFIED_JMS_MESSAGELISTENER = "javax.jms.MessageListener"; //$NON-NLS-1$

	public IDataModelOperation getDefaultOperation() {
		return new AddMessageDrivenBeanOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to add their own data model's properties as valid base
	 * properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	public Set<String> getPropertyNames() {
		// Add Bean specific properties defined in this data model
		Set<String> propertyNames = (Set<String>) super.getPropertyNames();

		propertyNames.add(DESTINATION_TYPE);
		propertyNames.add(JMS);

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
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(INTERFACES)){
			List<String> list = new ArrayList<String>();
			return list;
		} else if (propertyName.equals(SUPERCLASS))
			return "";
		else if (propertyName.equals(JMS))
			return Boolean.FALSE;
		else if (propertyName.equals(DESTINATION_TYPE)) {
			return DestinationType.QUEUE.toString();
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
	public boolean propertySet(String propertyName, Object propertyValue) {

		// Call super to set the property on the data model
		boolean result = super.propertySet(propertyName, propertyValue);
		
		if (propertyName.equals(JMS)){
			updateInterfaces(JMS, (Boolean) propertyValue);
		}
		if (propertyName.equals(INTERFACES)){
			boolean isAlreadyExist = ((List<String>) propertyValue).contains(QUALIFIED_JMS_MESSAGELISTENER);
			if (isAlreadyExist && !getBooleanProperty(JMS)){
				getDataModel().setProperty(JMS, true);
				return true;
			}
			if (!isAlreadyExist && getBooleanProperty(JMS))
				getDataModel().setProperty(JMS, false);
		}
		return result;
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

	private void updateInterfaces(String property, Boolean propertyValue) {
		List<String> interfacesList = (List<String>) getDataModel().getProperty(INTERFACES);
		boolean isAlreadyExist = interfacesList.contains(QUALIFIED_JMS_MESSAGELISTENER);
		if (propertyValue){
			if (!isAlreadyExist)
				interfacesList.add(QUALIFIED_JMS_MESSAGELISTENER);
		}else
			if (isAlreadyExist)
				interfacesList.remove(QUALIFIED_JMS_MESSAGELISTENER);
		getDataModel().setProperty(INTERFACES, interfacesList);
	}
}
