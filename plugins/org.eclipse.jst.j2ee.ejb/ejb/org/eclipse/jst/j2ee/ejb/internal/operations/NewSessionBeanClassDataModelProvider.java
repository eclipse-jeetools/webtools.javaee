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
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.STATE_TYPE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface.BusinessInterfaceType;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class NewSessionBeanClassDataModelProvider extends NewEnterpriseBeanClassDataModelProvider {

	private static final String LOCAL_SUFFIX = "Local"; //$NON-NLS-1$
	private static final String REMOTE_SUFFIX = "Remote"; //$NON-NLS-1$
	private static final String LOCAL_HOME_SUFFIX = "LocalHome"; //$NON-NLS-1$
	private static final String REMOTE_HOME_SUFFIX = "Home"; //$NON-NLS-1$

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
		propertyNames.add(REMOTE_BUSINESS_INTERFACE);
		propertyNames.add(LOCAL_BUSINESS_INTERFACE);
		propertyNames.add(REMOTE);
		propertyNames.add(LOCAL);
		propertyNames.add(STATE_TYPE);
		propertyNames.add(REMOTE_HOME);
		propertyNames.add(LOCAL_HOME);
		propertyNames.add(REMOTE_HOME_INTERFACE);
		propertyNames.add(LOCAL_HOME_INTERFACE);

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
			return StateType.STATELESS.toString(); 
		else if (propertyName.equals(BUSINESS_INTERFACES)) {
			List<BusinessInterface> listResult = new ArrayList<BusinessInterface>();
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			if ((Boolean) getProperty(REMOTE) && className.length() > 0) {
				BusinessInterface remoteInterface = new BusinessInterface(getStringProperty(REMOTE_BUSINESS_INTERFACE), BusinessInterfaceType.REMOTE);
				listResult.add(remoteInterface);
			}
			if ((Boolean) getProperty(LOCAL) && className.length() > 0) {
				BusinessInterface localInterface = new BusinessInterface(getStringProperty(LOCAL_BUSINESS_INTERFACE), BusinessInterfaceType.LOCAL);
				listResult.add(localInterface);
			}
			return listResult;
		}
		else if (REMOTE_BUSINESS_INTERFACE.equals(propertyName)) {
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			return className + REMOTE_SUFFIX;
		}
		else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName)) {
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			return className + LOCAL_SUFFIX;
		}
		else if (REMOTE_HOME_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			return className + REMOTE_HOME_SUFFIX;
		}
		else if (LOCAL_HOME_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			return className + LOCAL_HOME_SUFFIX;
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
				getDataModel().notifyPropertyChange(REMOTE_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
			}else{
				updateBusinessInterfaces(REMOTE);
			}
				
		}
		if (propertyName.equals(LOCAL)  && !getDataModel().isPropertySet(BUSINESS_INTERFACES)) {
			getDataModel().notifyPropertyChange(BUSINESS_INTERFACES, IDataModel.DEFAULT_CHG);
			getDataModel().notifyPropertyChange(LOCAL_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
			// TODO - ccc- shouldn't there be an updateBusinessInterfaces(LOCAL) here?
		}
		if (REMOTE_BUSINESS_INTERFACE.equals(propertyName))
		{
			getRemoteProperty().setFullyQualifiedName(propertyValue.toString());
		}
		else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName))
		{
			getLocalProperty().setFullyQualifiedName(propertyName);
		}
		else if (CLASS_NAME.equals(propertyName) || JAVA_PACKAGE.equals(propertyName))
		{
			IDataModel dataModel = getDataModel();
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			BusinessInterface remoteInterface = getRemoteProperty();
			if (remoteInterface != null)
			{
				remoteInterface.setFullyQualifiedName(className + REMOTE_SUFFIX);
				dataModel.notifyPropertyChange(REMOTE_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
			}
			BusinessInterface localInterface = getLocalProperty();
			if (localInterface != null)
			{
				localInterface.setFullyQualifiedName(className + LOCAL_SUFFIX);
				dataModel.notifyPropertyChange(LOCAL_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
			}
			dataModel.notifyPropertyChange(REMOTE_HOME_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(LOCAL_HOME_INTERFACE, IDataModel.DEFAULT_CHG);
		}

		return result;
	}
	
	@Override
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(STATE_TYPE)) {
			return DataModelPropertyDescriptor.createDescriptors(
					new String[] { 
							StateType.STATELESS.toString(), 
							StateType.STATEFUL.toString()
					}, 
					new String[] {
							EJBCreationResourceHandler.STATE_TYPE_STATELESS, 
							EJBCreationResourceHandler.STATE_TYPE_STATEFUL
					});
		} 
		
		return super.getValidPropertyDescriptors(propertyName);
	}

	private void updateBusinessInterfaces(String propertyName) {
		List<BusinessInterface> list = (List<BusinessInterface>) getProperty(BUSINESS_INTERFACES);
		if (propertyName.equals(REMOTE)) {
			if (getBooleanProperty(propertyName)) {
				// should be add remote property
				list.add(new BusinessInterface(getStringProperty(REMOTE_BUSINESS_INTERFACE), BusinessInterfaceType.REMOTE));
			} else {
				BusinessInterface remoteInterface = getRemoteProperty();
				int indexOf = list.indexOf(remoteInterface);
				list.remove(indexOf);
			}
		} else if (propertyName.equals(LOCAL)) {
			if (getBooleanProperty(propertyName)) {
				// should be add remote property
				String className = Signature.getSimpleName(getStringProperty(CLASS_NAME));
				list.add(new BusinessInterface(getStringProperty(LOCAL_BUSINESS_INTERFACE), BusinessInterfaceType.LOCAL));
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

	@Override
	public IStatus validate(String propertyName) {

		IStatus currentStatus = null;
		if (LOCAL_HOME_INTERFACE.equals(propertyName) || REMOTE_HOME_INTERFACE.equals(propertyName)
				|| LOCAL_BUSINESS_INTERFACE.equals(propertyName) || REMOTE_BUSINESS_INTERFACE.equals(propertyName)) {
			String value = getStringProperty(propertyName);
			currentStatus = validateJavaTypeName(value);
			if (!currentStatus.isOK()) {
				return currentStatus;
			}
		}
		return super.validate(propertyName);
	}

	protected IStatus validateJavaTypeName(String className) {
		// Check Java class name by standard java conventions
		IStatus javaStatus = JavaConventions.validateJavaTypeName(className);
		if (javaStatus.getSeverity() == IStatus.ERROR) {
			String msg = J2EECommonMessages.ERR_JAVA_CLASS_NAME_INVALID + javaStatus.getMessage();
			return WTPCommonPlugin.createErrorStatus(msg);
		} else if (javaStatus.getSeverity() == IStatus.WARNING) {
			String msg = J2EECommonMessages.ERR_JAVA_CLASS_NAME_WARNING + javaStatus.getMessage();
			return WTPCommonPlugin.createWarningStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}
}
