/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IType;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class CreateEnterpriseBeanWithClientViewDataModel extends CreateEnterpriseBeanDataModel {
	/**
	 * Add remote client view property. (Optional - Defaults to true for Session beans and false for
	 * entities is in a 2.x EJB project; otherwise, it defaults to true for all bean types.)
	 * 
	 * @link Boolean
	 */
	public static final String ADD_REMOTE = "CreateEnterpriseBeanWithClientViewDataModel.addRemote"; //$NON-NLS-1$
	/**
	 * The remote home interface qualified name. (Optional - Defaults based on the bean name and the
	 * default package name (i.e., [DEFAULT_PACKAGE_NAME].[BEAN_NAME]Home).)
	 * 
	 * @link String
	 */
	public static final String REMOTE_HOME = "CreateEnterpriseBeanWithClientViewDataModel.remoteHome"; //$NON-NLS-1$
	/**
	 * The remote interface qualified name. (Optional - Defaults based on the bean name and the
	 * default package name (i.e., [DEFAULT_PACKAGE_NAME].[BEAN_NAME]).)
	 * 
	 * @link String
	 */
	public static final String REMOTE_INTERFACE = "CreateEnterpriseBeanWithClientViewDataModel.remoteInterface"; //$NON-NLS-1$
	/**
	 * A String[] of qualified interface names that the remote interface should extend. (Optional)
	 * 
	 * @link String[]
	 */
	public static final String REMOTE_INTERFACE_EXTENSIONS = "CreateEnterpriseBeanWithClientViewDataModel.remoteInterfaceExtensions"; //$NON-NLS-1$
	/**
	 * Add local client view property. (Optional - Defaults to false for Session beans and true for
	 * entities in a 2.x EJB project. This property is not used if the project version is less than
	 * 2.0.)
	 * 
	 * @link Boolean
	 * 
	 * @since EJB 2.0
	 */
	public static final String ADD_LOCAL = "CreateEnterpriseBeanWithClientViewDataModel.addLocal"; //$NON-NLS-1$
	/**
	 * The local home interface qualified name. (Optional - Defaults based on the bean name and the
	 * default package name (i.e., [DEFAULT_PACKAGE_NAME].[BEAN_NAME]LocalHome).)
	 * 
	 * @link String
	 * 
	 * @since EJB 2.0
	 */
	public static final String LOCAL_HOME = "CreateEnterpriseBeanWithClientViewDataModel.localHome"; //$NON-NLS-1$
	/**
	 * The local interface qualified name. (Optional - Defaults based on the bean name and the
	 * default package name (i.e., [DEFAULT_PACKAGE_NAME].[BEAN_NAME]Local).)
	 * 
	 * @link String
	 * 
	 * @since EJB 2.0
	 */
	public static final String LOCAL_INTERFACE = "CreateEnterpriseBeanWithClientViewDataModel.localInterface"; //$NON-NLS-1$
	/**
	 * A String[] of qualified interface names that the local interface should extend. (Optional)
	 * 
	 * @link String[]
	 * 
	 * @since EJB 2.0
	 */
	public static final String LOCAL_INTERFACE_EXTENSIONS = "CreateEnterpriseBeanWithClientViewDataModel.localInterfaceExtensions"; //$NON-NLS-1$
	private static final String HOME_INTERFACE_TYPE = "javax.ejb.EJBHome"; //$NON-NLS-1$
	private static final String REMOTE_INTERFACE_TYPE = "javax.ejb.EJBObject"; //$NON-NLS-1$
	private static final String LOCAL_HOME_INTERFACE_TYPE = "javax.ejb.EJBLocalHome"; //$NON-NLS-1$
	private static final String LOCAL_INTERFACE_TYPE = "javax.ejb.EJBLocalObject"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(ADD_LOCAL);
		addValidBaseProperty(ADD_REMOTE);
		addValidBaseProperty(LOCAL_HOME);
		addValidBaseProperty(LOCAL_INTERFACE);
		addValidBaseProperty(LOCAL_INTERFACE_EXTENSIONS);
		addValidBaseProperty(REMOTE_HOME);
		addValidBaseProperty(REMOTE_INTERFACE);
		addValidBaseProperty(REMOTE_INTERFACE_EXTENSIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_LOCAL))
			return getDefaultAddLocal();
		if (propertyName.equals(ADD_REMOTE))
			return getDefaultAddRemote();
		if (propertyName.equals(REMOTE_HOME))
			return getDefaultRemoteHome();
		if (propertyName.equals(REMOTE_INTERFACE))
			return getDefaultRemoteInterface();
		if (propertyName.equals(LOCAL_HOME))
			return getDefaultLocalHome();
		if (propertyName.equals(LOCAL_INTERFACE))
			return getDefaultLocalInterface();
		if (propertyName.equals(REMOTE_INTERFACE_EXTENSIONS))
			return getDefaultRemoteInterfaceExtensions();
		if (propertyName.equals(LOCAL_INTERFACE_EXTENSIONS))
			return getDefaultLocalInterfaceExtensions();
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	private String[] getDefaultRemoteInterfaceExtensions() {
		if (getBooleanProperty(ADD_REMOTE)) {
			EnterpriseBean superEJB = (EnterpriseBean) getProperty(BEAN_SUPEREJB);
			if (superEJB != null) {
				JavaClass remote = superEJB.getRemoteInterface();
				if (remote != null)
					return new String[]{remote.getQualifiedName()};
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	private String[] getDefaultLocalInterfaceExtensions() {
		if (getBooleanProperty(ADD_LOCAL)) {
			EnterpriseBean superEJB = (EnterpriseBean) getProperty(BEAN_SUPEREJB);
			if (superEJB != null) {
				JavaClass local = superEJB.getLocalInterface();
				if (local != null)
					return new String[]{local.getQualifiedName()};
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	private Object getDefaultAddLocal() {
		if (isVersion2xOrGreater())
			return getDefaultAddLocalFor2x();
		return Boolean.FALSE;
	}

	/**
	 * Return a Boolean whether or not to add a local client view when the EJB module version is
	 * greater than or equal to 2.0.
	 * 
	 * @return
	 */
	protected abstract Boolean getDefaultAddLocalFor2x();

	/**
	 * @return
	 */
	private Object getDefaultAddRemote() {
		if (isVersion2xOrGreater())
			return getDefaultAddRemoteFor2x();
		return Boolean.TRUE;
	}

	/**
	 * Return a Boolean whether or not to add a remote client view when the EJB module version is
	 * greater than or equal to 2.0.
	 * 
	 * @return
	 */
	protected abstract Boolean getDefaultAddRemoteFor2x();

	/**
	 * @return
	 */
	private Object getDefaultRemoteHome() {
		if (isVersion2xOrGreater() && !getBooleanProperty(ADD_REMOTE))
			return null;
		return getDefaultClassName("Home"); //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	private Object getDefaultRemoteInterface() {
		if (isVersion2xOrGreater() && !getBooleanProperty(ADD_REMOTE))
			return null;
		return getDefaultClassName(null);
	}

	/**
	 * @return
	 */
	private Object getDefaultLocalHome() {
		if (isVersion2xOrGreater() && getBooleanProperty(ADD_LOCAL))
			return getDefaultClassName("LocalHome"); //$NON-NLS-1$
		return null;
	}

	/**
	 * @return
	 */
	private Object getDefaultLocalInterface() {
		if (isVersion2xOrGreater() && getBooleanProperty(ADD_LOCAL))
			return getDefaultClassName("Local"); //$NON-NLS-1$
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(ADD_LOCAL)) {
			if (((Boolean) propertyValue).booleanValue())
				addLocalValues();
			else
				removeLocalValues();
			notifyDefaultChange(LOCAL_INTERFACE_EXTENSIONS);
		} else if (propertyName.equals(ADD_REMOTE)) {
			if (((Boolean) propertyValue).booleanValue())
				addRemoteValues();
			else
				removeRemoteValues();
			notifyDefaultChange(REMOTE_INTERFACE_EXTENSIONS);
		} else if (propertyName.equals(PROJECT_NAME)) {
			if (!isVersion2xOrGreater()) {
				setBooleanProperty(ADD_REMOTE, true);
				setBooleanProperty(ADD_LOCAL, false);
			}
		}
		return notify;
	}

	private void addRemoteValues() {
		notifyDefaultChange(REMOTE_HOME);
		notifyDefaultChange(REMOTE_INTERFACE);
		notifyEnablementChange(REMOTE_INTERFACE_EXTENSIONS);
	}

	private void removeRemoteValues() {
		if (isSet(REMOTE_HOME))
			setProperty(REMOTE_HOME, null);
		else
			notifyDefaultChange(REMOTE_HOME);
		if (isSet(REMOTE_INTERFACE))
			setProperty(REMOTE_INTERFACE, null);
		else
			notifyDefaultChange(REMOTE_INTERFACE);
		setProperty(REMOTE_INTERFACE_EXTENSIONS, null);
		notifyEnablementChange(REMOTE_INTERFACE_EXTENSIONS);
	}

	private void addLocalValues() {
		notifyDefaultChange(LOCAL_HOME);
		notifyDefaultChange(LOCAL_INTERFACE);
		notifyEnablementChange(LOCAL_INTERFACE_EXTENSIONS);
	}

	private void removeLocalValues() {
		if (isSet(LOCAL_HOME))
			setProperty(LOCAL_HOME, null);
		else
			notifyDefaultChange(LOCAL_HOME);
		if (isSet(LOCAL_INTERFACE))
			setProperty(LOCAL_INTERFACE, null);
		else
			notifyDefaultChange(LOCAL_INTERFACE);
		setProperty(LOCAL_INTERFACE_EXTENSIONS, null);
		notifyEnablementChange(LOCAL_INTERFACE_EXTENSIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(ADD_REMOTE))
			return validateAddRemote();
		if (propertyName.equals(ADD_LOCAL))
			return validateAddLocal();
		if (propertyName.equals(REMOTE_HOME))
			return validateRemoteHome();
		if (propertyName.equals(REMOTE_INTERFACE))
			return validateRemote();
		if (propertyName.equals(LOCAL_HOME))
			return validateLocalHome();
		if (propertyName.equals(LOCAL_INTERFACE))
			return validateLocal();
		return super.doValidateProperty(propertyName);
	}

	/**
	 * @return
	 */
	private IStatus validateAddRemote() {
		if (getBooleanProperty(ADD_REMOTE)) {
			EnterpriseBean superEJB = (EnterpriseBean) getProperty(BEAN_SUPEREJB);
			if (superEJB != null && !superEJB.hasRemoteClient())
				return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Super_EJB_has_no_remote_interface"), null); //$NON-NLS-1$
		}
		return validateAddProperties();
	}

	/**
	 * @return
	 */
	private IStatus validateAddLocal() {
		if (getBooleanProperty(ADD_LOCAL)) {
			EnterpriseBean superEJB = (EnterpriseBean) getProperty(BEAN_SUPEREJB);
			if (superEJB != null && !superEJB.hasLocalClient())
				return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Super_EJB_has_no_local_interface"), null); //$NON-NLS-1$
		}
		return OK_STATUS;
	}

	/**
	 * @return
	 */
	protected IStatus validateAddProperties() {
		if (!getBooleanProperty(ADD_LOCAL) && !getBooleanProperty(ADD_REMOTE))
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Atleast_one_client"), null); //$NON-NLS-1$
		return OK_STATUS;
	}

	/**
	 * @return
	 */
	private IStatus validateRemoteHome() {
		if (isVersion2xOrGreater() && !getBooleanProperty(ADD_REMOTE))
			return OK_STATUS;
		String remoteHome = (String) getProperty(REMOTE_HOME);
		if (remoteHome == null || remoteHome.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Message_Enter_home_interface_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(remoteHome);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Home_interface_cannot_be_i_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(remoteHome, "Message_Home_Interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(remoteHome, new String[]{BEAN_CLASS_NAME, REMOTE_INTERFACE, LOCAL_INTERFACE, LOCAL_HOME});
		if (!status.isOK())
			return status;
		return validateInterfaceType(remoteHome, HOME_INTERFACE_TYPE);
	}

	/**
	 * @param remoteHome
	 * @return
	 */
	private IStatus validateInterfaceType(String interfaceName, String implementsInterfaceName) {
		IType type = findType(interfaceName);
		if (type != null) {
			if (!implementsInterface(type, implementsInterfaceName)) {
				String msg_pattern = EJBCreationResourceHandler.getString("Interface_extends"); //$NON-NLS-1$
				return EjbPlugin.createErrorStatus(MessageFormat.format(msg_pattern, new String[]{interfaceName, implementsInterfaceName}), null);
			}
		}
		return OK_STATUS;
	}

	/**
	 * @return
	 */
	private IStatus validateRemote() {
		if (isVersion2xOrGreater() && !getBooleanProperty(ADD_REMOTE))
			return OK_STATUS;
		String remoteInterface = (String) getProperty(REMOTE_INTERFACE);
		if (remoteInterface == null || remoteInterface.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Message_Enter_remote_interface_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(remoteInterface);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Remote_interface_cannot_be_i_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(remoteInterface, "Message_Remote_Interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(remoteInterface, new String[]{BEAN_CLASS_NAME, REMOTE_HOME, LOCAL_INTERFACE, LOCAL_HOME});
		if (!status.isOK())
			return status;
		return validateInterfaceType(remoteInterface, REMOTE_INTERFACE_TYPE);
	}

	/**
	 * @return
	 */
	private IStatus validateLocalHome() {
		if (!getBooleanProperty(ADD_LOCAL))
			return OK_STATUS;
		String localHome = (String) getProperty(LOCAL_HOME);
		if (localHome == null || localHome.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Enter_local_home_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(localHome);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Local_Home_not_in_a_default_pkg_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(localHome, "Local_home_interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(localHome, new String[]{BEAN_CLASS_NAME, REMOTE_HOME, REMOTE_INTERFACE, LOCAL_INTERFACE});
		if (!status.isOK())
			return status;
		return validateInterfaceType(localHome, LOCAL_HOME_INTERFACE_TYPE);
	}

	/**
	 * @return
	 */
	private IStatus validateLocal() {
		if (!getBooleanProperty(ADD_LOCAL))
			return OK_STATUS;
		String localInterface = (String) getProperty(LOCAL_INTERFACE);
		if (localInterface == null || localInterface.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Enter_local_int_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(localInterface);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Local_not_in_a_default_pkg_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(localInterface, "Local_interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(localInterface, new String[]{BEAN_CLASS_NAME, REMOTE_HOME, REMOTE_INTERFACE, LOCAL_HOME});
		if (!status.isOK())
			return status;
		return validateInterfaceType(localInterface, LOCAL_INTERFACE_TYPE);
	}

	/**
	 * @param typeName
	 * @param strings
	 * @return
	 */
	protected IStatus validateNoCollisions(String typeName, String[] propertyNames) {
		String compareName = null;
		for (int i = 0; i < propertyNames.length; i++) {
			compareName = (String) getProperty(propertyNames[i]);
			if (compareName != null && compareName.equals(typeName)) {
				String msg_pattern = EJBCreationResourceHandler.getString("Name_collision_pattern"); //$NON-NLS-1$
				return EjbPlugin.createErrorStatus(MessageFormat.format(msg_pattern, new String[]{typeName}), null);
			}
		}
		return OK_STATUS;
	}

	public String getInterfaceType(String propertyName) {
		if (propertyName.equals(REMOTE_HOME))
			return HOME_INTERFACE_TYPE;
		if (propertyName.equals(REMOTE_INTERFACE))
			return REMOTE_INTERFACE_TYPE;
		if (propertyName.equals(LOCAL_HOME))
			return LOCAL_HOME_INTERFACE_TYPE;
		if (propertyName.equals(LOCAL_INTERFACE))
			return LOCAL_INTERFACE_TYPE;
		return super.getInterfaceType(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(REMOTE_HOME) || propertyName.equals(REMOTE_INTERFACE))
			return (Boolean) getProperty(ADD_REMOTE);
		if (propertyName.equals(REMOTE_INTERFACE_EXTENSIONS))
			return isInterfaceExtensionsEnabled(ADD_REMOTE);
		if (propertyName.equals(LOCAL_HOME) || propertyName.equals(LOCAL_INTERFACE))
			return (Boolean) getProperty(ADD_LOCAL);
		if (propertyName.equals(LOCAL_INTERFACE_EXTENSIONS))
			return isInterfaceExtensionsEnabled(ADD_LOCAL);
		return super.basicIsEnabled(propertyName);
	}

	/**
	 * @param add_remote2
	 * @return
	 */
	private Boolean isInterfaceExtensionsEnabled(String propertyName) {
		EnterpriseBean superEJB = (EnterpriseBean) getProperty(BEAN_SUPEREJB);
		if (superEJB != null)
			return Boolean.FALSE;
		return (Boolean) getProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#beanNameChanged()
	 */
	protected void beanNameChanged() {
		super.beanNameChanged();
		if (getBooleanProperty(ADD_REMOTE)) {
			notifyDefaultChange(REMOTE_HOME);
			notifyDefaultChange(REMOTE_INTERFACE);
			notifyDefaultChange(REMOTE_INTERFACE_EXTENSIONS);
		}
		if (getBooleanProperty(ADD_LOCAL)) {
			notifyDefaultChange(LOCAL_HOME);
			notifyDefaultChange(LOCAL_INTERFACE);
			notifyDefaultChange(LOCAL_INTERFACE_EXTENSIONS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#superEJBNameChanged()
	 */
	protected void superEJBNameChanged() {
		super.superEJBNameChanged();
		notifyDefaultChange(REMOTE_INTERFACE_EXTENSIONS);
		notifyDefaultChange(LOCAL_INTERFACE_EXTENSIONS);
	}
}