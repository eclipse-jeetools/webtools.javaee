/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.BUSINESS_INTERFACES;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.STATE_TYPE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface.BusinessInterfaceType;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class NewSessionBeanClassDataModelProvider extends NewEnterpriseBeanClassDataModelProvider {

	public static final int STATE_TYPE_STATELESS_INDEX = 0;
	public static final int STATE_TYPE_STATEFUL_INDEX = 1;
	
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

		propertyNames.add(BUSINESS_INTERFACES);
		propertyNames.add(REMOTE);
		propertyNames.add(LOCAL);
		propertyNames.add(STATE_TYPE);
		propertyNames.add(REMOTE_HOME);
		propertyNames.add(LOCAL_HOME);

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
		if (propertyName.equals(REMOTE_HOME))
			return Boolean.FALSE;
		else if (propertyName.equals(LOCAL_HOME))
			return Boolean.FALSE;
		else if (propertyName.equals(REMOTE))
			return Boolean.FALSE;
		else if (propertyName.equals(LOCAL))
			return Boolean.TRUE;
		else if (propertyName.equals(STATE_TYPE))
			return NewSessionBeanClassDataModelProvider.STATE_TYPE_STATELESS_INDEX; 
		else if (propertyName.equals(BUSINESS_INTERFACES)) {
			List<BusinessInterface> listResult = new ArrayList<BusinessInterface>();
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			if ((Boolean) getProperty(REMOTE) && className.length() > 0) {
				BusinessInterface remoteInterface = new BusinessInterface(className + REMOTE_SUFFIX, BusinessInterfaceType.REMOTE);
				listResult.add(remoteInterface);
			}
			if ((Boolean) getProperty(LOCAL) && className.length() > 0) {
				BusinessInterface localInterface = new BusinessInterface(className + LOCAL_SUFFIX, BusinessInterfaceType.LOCAL);
				listResult.add(localInterface);
			}
			return listResult;
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
		
		if (propertyName.equals(REMOTE)) {
			if (!getDataModel().isPropertySet(BUSINESS_INTERFACES)) {
				getDataModel().notifyPropertyChange(BUSINESS_INTERFACES, IDataModel.DEFAULT_CHG);
			}else{
				updateBusinessInterfaces(REMOTE);
			}
				
		}
		if (propertyName.equals(LOCAL)  && !getDataModel().isPropertySet(BUSINESS_INTERFACES)) {
			getDataModel().notifyPropertyChange(BUSINESS_INTERFACES, IDataModel.DEFAULT_CHG);
		}

		return result;
	}

	private void updateBusinessInterfaces(String propertyName) {
		List<BusinessInterface> list = (List<BusinessInterface>) getProperty(BUSINESS_INTERFACES);
		if (propertyName.equals(REMOTE)) {
			if (getBooleanProperty(propertyName)) {
				// should be add remote property
				String className = Signature.getSimpleName(getStringProperty(CLASS_NAME));
				list.add(new BusinessInterface(className + REMOTE_SUFFIX, BusinessInterfaceType.REMOTE));
			} else {
				BusinessInterface remoteInterface = getRemoteProperty();
				int indexOf = list.indexOf(remoteInterface);
				list.remove(indexOf);
			}
		} else if (propertyName.equals(LOCAL)) {
			if (getBooleanProperty(propertyName)) {
				// should be add remote property
				String className = Signature.getSimpleName(getStringProperty(CLASS_NAME));
				list.add(new BusinessInterface(className + LOCAL_SUFFIX, BusinessInterfaceType.LOCAL));
			} else {
				BusinessInterface localInterface = getLocalProperty();
				int indexOf = list.indexOf(localInterface);
				list.remove(indexOf);
			}
		}
	}

	private BusinessInterface getRemoteProperty() {
		List<BusinessInterface> businessInterfaces = (List<BusinessInterface>) getProperty(BUSINESS_INTERFACES);
		for (BusinessInterface iface : businessInterfaces) {
			if ((iface.getJavaType() == null) && (iface.isRemote())) {
				return iface;
			}
		}
		return null;
	}
	private BusinessInterface getLocalProperty() {
		List<BusinessInterface> businessInterfaces = (List<BusinessInterface>) getProperty(BUSINESS_INTERFACES);
		for (BusinessInterface iface : businessInterfaces) {
			if ((iface.getJavaType() == null) && (iface.isLocal())) {
				return iface;
			}
		}
		return null;
	}
}
