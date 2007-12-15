/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class NewSessionBeanClassDataModelProvider extends NewJavaClassDataModelProvider implements INewSessionBeanClassDataModelProperties {

	public static final int STATE_TYPE_STATELESS_INDEX = 0;
	public static final int STATE_TYPE_STATEFUL_INDEX = 1;
	
	public static final int TRANSACTION_TYPE_CONTAINER_INDEX = 0;
	public static final int TRANSACTION_TYPE_BEAN_INDEX = 1;
	
	private static final String LOCAL_SUFFIX = "Local"; //$NON-NLS-1$
	private static final String REMOTE_SUFFIX = "Remote"; //$NON-NLS-1$

	public IDataModelOperation getDefaultOperation() {
		return new AddSessionBeanOperation(getDataModel());
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

		propertyNames.add(BUSINESSINTERFACES);
		propertyNames.add(EJB_NAME);
		propertyNames.add(REMOTE);
		propertyNames.add(LOCAL);
		propertyNames.add(STATE_TYPE);
		propertyNames.add(REMOTE_HOME);
		propertyNames.add(LOCAL_HOME);
		propertyNames.add(MAPPED_NAME);
		propertyNames.add(TRANSACTION_TYPE);

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
		if (propertyName.equals(EJB_NAME)) {
			String className = getStringProperty(CLASS_NAME);
			int index = className.lastIndexOf("."); //$NON-NLS-1$
			className = className.substring(index+1);
			return className;
		}
		else if (propertyName.equals(REMOTE_HOME))
			return Boolean.FALSE;
		else if (propertyName.equals(LOCAL_HOME))
			return Boolean.FALSE;
		else if (propertyName.equals(REMOTE))
			return Boolean.FALSE;
		else if (propertyName.equals(LOCAL))
			return Boolean.TRUE;
		else if (propertyName.equals(STATE_TYPE))
			return NewSessionBeanClassDataModelProvider.STATE_TYPE_STATELESS_INDEX; 
		else if (propertyName.equals(SUPERCLASS))
			return "";
		else if (propertyName.equals(TRANSACTION_TYPE)){
			return NewSessionBeanClassDataModelProvider.TRANSACTION_TYPE_CONTAINER_INDEX;
		}
		else if (propertyName.equals(BUSINESSINTERFACES)){
			List<BusinessInterface> listResult = new ArrayList<BusinessInterface>();
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			if ((Boolean) getProperty(REMOTE) && className.length() > 0){
				BusinessInterface remoteInterface = new BusinessInterface(className + REMOTE_SUFFIX,true,false);
				listResult.add(remoteInterface);
			}
			if ((Boolean) getProperty(LOCAL) && className.length() > 0){
				BusinessInterface localInterface = new BusinessInterface(className + LOCAL_SUFFIX,false,true);
				listResult.add(localInterface);
			}
			return listResult;
		}
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	@Override
	public IStatus validate(String propertyName) {

		if (propertyName.equals(SUPERCLASS)) {
			String value = getStringProperty(propertyName);
			if (value == null || value.trim().length() == 0)
				return WTPCommonPlugin.OK_STATUS;
		} else if (propertyName.equals(JAVA_PACKAGE)) {
			String value = getStringProperty(propertyName);
			if (value == null || value.trim().length() == 0) {
				String msg = EJBCreationResourceHandler.Bean_Class_Cannot_Be_In_UI_;
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return super.validate(propertyName);
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
		// If class name is changed, update the display name to be the same
		if (propertyName.equals(CLASS_NAME) && !getDataModel().isPropertySet(EJB_NAME)) {
			getDataModel().notifyPropertyChange(EJB_NAME, IDataModel.DEFAULT_CHG);
			getDataModel().notifyPropertyChange(REMOTE, IDataModel.DEFAULT_CHG);
		}
		if (propertyName.equals(REMOTE)){
			if (!getDataModel().isPropertySet(BUSINESSINTERFACES)){
				getDataModel().notifyPropertyChange(BUSINESSINTERFACES, IDataModel.DEFAULT_CHG);
			}else{
				updateBussnesInterfaces(REMOTE);
			}
				
		}
		if (propertyName.equals(LOCAL)  && !getDataModel().isPropertySet(BUSINESSINTERFACES)){
			getDataModel().notifyPropertyChange(BUSINESSINTERFACES, IDataModel.DEFAULT_CHG);
		}
		if (propertyName.equals(BUSINESSINTERFACES)){
			//Should uncheck Remote && Local BI in case list is Empty
		}
		return result;
	}

	private void updateBussnesInterfaces(String propertyName) {
		List<BusinessInterface> list = (List<BusinessInterface>) getProperty(BUSINESSINTERFACES);
		if (propertyName.equals(REMOTE)){
			if (getBooleanProperty(propertyName)){
				// should be add remote property
				String className = Signature.getSimpleName(getStringProperty(CLASS_NAME));
				list.add(new BusinessInterface(className + REMOTE_SUFFIX,true,false));
			}else{
				BusinessInterface remoteInterface = getRemoteProperty();
				int indexOf = list.indexOf(remoteInterface);
				list.remove(indexOf);
			}
		} else if (propertyName.equals(LOCAL)){
			if (getBooleanProperty(propertyName)){
				// should be add remote property
				String className = Signature.getSimpleName(getStringProperty(CLASS_NAME));
				list.add(new BusinessInterface(className + LOCAL_SUFFIX,true,false));
			}else{
				BusinessInterface localInterface = getLocalProperty();
				int indexOf = list.indexOf(localInterface);
				list.remove(indexOf);
			}
		}
	}

	public BusinessInterface getRemoteProperty(){
		BusinessInterface result = null;
		List<BusinessInterface> busnesInterfaceList = (List<BusinessInterface>) getProperty(BUSINESSINTERFACES);
		for (Iterator<BusinessInterface> i =busnesInterfaceList.iterator();i.hasNext(); ){
			BusinessInterface element = (BusinessInterface) i.next();
			if ((element.getInterfaceType() == null) && (element.isRemote())){
				result = element;
			}
		}
		return result;
	}
	public BusinessInterface getLocalProperty(){
		BusinessInterface result = null;
		List<BusinessInterface> busnesInterfaceList = (List<BusinessInterface>) getProperty(BUSINESSINTERFACES);
		for (Iterator<BusinessInterface> i =busnesInterfaceList.iterator();i.hasNext(); ){
			BusinessInterface element = (BusinessInterface) i.next();
			if ((element.getInterfaceType() == null) && (element.isLocal())){
				result = element;
			}
		}
		return result;
	}
}
