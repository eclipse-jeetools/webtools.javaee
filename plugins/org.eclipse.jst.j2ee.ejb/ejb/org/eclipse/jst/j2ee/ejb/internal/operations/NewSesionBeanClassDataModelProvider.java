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
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class NewSesionBeanClassDataModelProvider extends NewJavaClassDataModelProvider implements INewSessionBeanClassDataModelProperties{

	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String LOCAL = "Local"; //$NON-NLS-1$
	private static final String REMOTE = "Remote"; //$NON-NLS-1$

	public IDataModelOperation getDefaultOperation() {
		return new AddSessionBeanOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to add their own data model's properties as valid base
	 * properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getPropertyNames() {
		// Add Bean specific properties defined in this data model
		Set<String> propertyNames = (Set<String>) super.getPropertyNames();
		propertyNames.add(USE_EXISTING_CLASS);

		propertyNames.add(BUSSNESINTERFACE_LIST);
		propertyNames.add(EJB_NAME);
		propertyNames.add(REMOTE_BI);
		propertyNames.add(LOCAL_BI);
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
		else if (propertyName.equals(REMOTE_BI))
			return Boolean.FALSE;
		else if (propertyName.equals(LOCAL_BI))
			return Boolean.TRUE;
		else if (propertyName.equals(STATE_TYPE))
			return "Stateless Session Bean"; //$NON-NLS-1$
		else if (propertyName.equals(SUPERCLASS))
			return EMPTY_STRING;
		else if (propertyName.equals(TRANSACTION_TYPE)){
			return "Container"; //$NON-NLS-1$
		}
		else if (propertyName.equals(USE_EXISTING_CLASS))
			return Boolean.FALSE;
		else if (propertyName.equals(BUSSNESINTERFACE_LIST)){
			List<RemoteLocalInterface> listResult = new ArrayList<RemoteLocalInterface>();
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			if ((Boolean) getProperty(REMOTE_BI) && !className.isEmpty()){
				RemoteLocalInterface remoteInterface = new RemoteLocalInterface(className + REMOTE,true,false);
				listResult.add(remoteInterface);
			}
			if ((Boolean) getProperty(LOCAL_BI) && !className.isEmpty()){
				RemoteLocalInterface localInterface = new RemoteLocalInterface(className + LOCAL,false,true);
				listResult.add(localInterface);
			}
			return listResult;
		}
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	@Override
	public IStatus validate(String propertyName) {

		if (propertyName.equals(SUPERCLASS) && EMPTY_STRING.equals(getStringProperty(propertyName)))
			return WTPCommonPlugin.OK_STATUS;

		if (propertyName.equals(JAVA_PACKAGE) && EMPTY_STRING.equals(getStringProperty(propertyName))){
			String msg = "Choose package for source classes";
			return WTPCommonPlugin.createErrorStatus(msg);
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
			getDataModel().notifyPropertyChange(REMOTE_BI, IDataModel.DEFAULT_CHG);
		}
		if (propertyName.equals(REMOTE_BI)){
			if (!getDataModel().isPropertySet(BUSSNESINTERFACE_LIST)){
				getDataModel().notifyPropertyChange(BUSSNESINTERFACE_LIST, IDataModel.DEFAULT_CHG);
			}else{
				updateBussnesInterfaces(REMOTE_BI);
			}
				
		}
		if (propertyName.equals(LOCAL_BI)  && !getDataModel().isPropertySet(BUSSNESINTERFACE_LIST)){
			getDataModel().notifyPropertyChange(BUSSNESINTERFACE_LIST, IDataModel.DEFAULT_CHG);
		}
		if (propertyName.equals(BUSSNESINTERFACE_LIST)){
			//Should uncheck Remote && Local BI in case list is Empty
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private void updateBussnesInterfaces(String propertyName) {
		List<RemoteLocalInterface> list = (List<RemoteLocalInterface>) getProperty(BUSSNESINTERFACE_LIST);
		if (propertyName.equals(REMOTE_BI)){
			if (getBooleanProperty(propertyName)){
				// should be add remote property
				String className = Signature.getSimpleName(getStringProperty(CLASS_NAME));
				list.add(new RemoteLocalInterface(className + REMOTE,true,false));
			}else{
				RemoteLocalInterface remoteInterface = getRemoteProperty();
				int indexOf = list.indexOf(remoteInterface);
				list.remove(indexOf);
			}
		} else if (propertyName.equals(LOCAL_BI)){
			if (getBooleanProperty(propertyName)){
				// should be add remote property
				String className = Signature.getSimpleName(getStringProperty(CLASS_NAME));
				list.add(new RemoteLocalInterface(className + LOCAL,true,false));
			}else{
				RemoteLocalInterface localInterface = getLocalProperty();
				int indexOf = list.indexOf(localInterface);
				list.remove(indexOf);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public RemoteLocalInterface getRemoteProperty(){
		RemoteLocalInterface result = null;
		List<RemoteLocalInterface> busnesInterfaceList = (List<RemoteLocalInterface>) getProperty(BUSSNESINTERFACE_LIST);
		for (Iterator<RemoteLocalInterface> i =busnesInterfaceList.iterator();i.hasNext(); ){
			RemoteLocalInterface element = (RemoteLocalInterface) i.next();
			if ((element.getInterfaceType() == null) && (element.isRemote)){
				result = element;
			}
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public RemoteLocalInterface getLocalProperty(){
		RemoteLocalInterface result = null;
		List<RemoteLocalInterface> busnesInterfaceList = (List<RemoteLocalInterface>) getProperty(BUSSNESINTERFACE_LIST);
		for (Iterator<RemoteLocalInterface> i =busnesInterfaceList.iterator();i.hasNext(); ){
			RemoteLocalInterface element = (RemoteLocalInterface) i.next();
			if ((element.getInterfaceType() == null) && (element.isLocal)){
				result = element;
			}
		}
		return result;
	}
}
