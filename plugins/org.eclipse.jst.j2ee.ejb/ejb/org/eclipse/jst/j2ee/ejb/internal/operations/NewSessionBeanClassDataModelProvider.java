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

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.BUSINESS_INTERFACES;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_COMPONENT_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_COMPONENT_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.STATE_TYPE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface.BusinessInterfaceType;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class NewSessionBeanClassDataModelProvider extends NewEnterpriseBeanClassDataModelProvider {


	public static final int STATE_TYPE_STATELESS_INDEX = 0;
	public static final int STATE_TYPE_STATEFUL_INDEX = 1;

	private static final String LOCAL_SUFFIX = "Local"; //$NON-NLS-1$
	private static final String REMOTE_SUFFIX = "Remote"; //$NON-NLS-1$
	private static final String LOCAL_HOME_SUFFIX = "LocalHome"; //$NON-NLS-1$
	private static final String REMOTE_HOME_SUFFIX = "RemoteHome"; //$NON-NLS-1$
	private static final String LOCAL_COMPONENT_SUFFIX = "LocalComponent"; //$NON-NLS-1$
	private static final String REMOTE_COMPONENT_SUFFIX = "RemoteComponent"; //$NON-NLS-1$


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
		propertyNames.add(LOCAL_COMPONENT_INTERFACE);
		propertyNames.add(REMOTE_COMPONENT_INTERFACE);

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
			return className + ((className != null && className.length() > 0) ? REMOTE_SUFFIX : "");
		}
		else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName)) {
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			return className + ((className != null && className.length() > 0) ? LOCAL_SUFFIX : "");
//			return className + ;
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
		else if (LOCAL_COMPONENT_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			return className + LOCAL_COMPONENT_SUFFIX;
		}
		else if (REMOTE_COMPONENT_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			return className + REMOTE_COMPONENT_SUFFIX;
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
				getDataModel().notifyPropertyChange(REMOTE_BUSINESS_INTERFACE, IDataModel.VALUE_CHG);
			}else{
				updateBusinessInterfaces(REMOTE);
			}

		}
		if (propertyName.equals(LOCAL)  && !getDataModel().isPropertySet(BUSINESS_INTERFACES)) {
			getDataModel().notifyPropertyChange(BUSINESS_INTERFACES, IDataModel.DEFAULT_CHG);
			getDataModel().notifyPropertyChange(LOCAL_BUSINESS_INTERFACE, IDataModel.VALUE_CHG);
			// TODO - ccc- shouldn't there be an updateBusinessInterfaces(LOCAL) here?
		}
		if (REMOTE_BUSINESS_INTERFACE.equals(propertyName))
		{
			if(getRemoteProperty() != null){
				getRemoteProperty().setFullyQualifiedName(propertyValue.toString());
			}
		}
		else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName))
		{
			if(getLocalProperty() != null){
				getLocalProperty().setFullyQualifiedName(propertyName);
			}

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
			dataModel.notifyPropertyChange(REMOTE_COMPONENT_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(LOCAL_COMPONENT_INTERFACE, IDataModel.DEFAULT_CHG);
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
		IStatus status = null;
		
		if (EJB_NAME.equals(propertyName)){
			return validateEjbName();
		} else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName)) {
			if (model.getBooleanProperty(LOCAL)) {
				return validateEjbInterface(getStringProperty(propertyName));
			}
		} else if (REMOTE_BUSINESS_INTERFACE.equals(propertyName)) {
			if (model.getBooleanProperty(REMOTE)) {
				return validateEjbInterface(getStringProperty(propertyName));
			}
		} else if (LOCAL_COMPONENT_INTERFACE.equals(propertyName) || 
				REMOTE_COMPONENT_INTERFACE.equals(propertyName) || 
				LOCAL_HOME_INTERFACE.equals(propertyName) || 
				REMOTE_HOME_INTERFACE.equals(propertyName)) {
			return validateComponentHomeInterfaces();
		}
			
		return super.validate(propertyName);
	}
	
	protected IStatus validateEjbInterface(String fullyQualifiedName) {
		IStatus status = validateJavaTypeName(fullyQualifiedName);
		if (status.getSeverity() != IStatus.ERROR) {
			IStatus existsStatus = canCreateTypeInClasspath(
					Signature.getQualifier(fullyQualifiedName), 
					Signature.getSimpleName(fullyQualifiedName));
			if (existsStatus.matches(IStatus.ERROR | IStatus.WARNING))
				status = existsStatus;
		}
		return status;
	}

	private IStatus validateEjbName() {
		// check if an EJB with the same name already exists
		String projectName = getStringProperty(PROJECT_NAME);
		if (projectName != null && projectName.length() > 0) {
			IModelProvider provider = ModelProviderManager.getModelProvider(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName));
			EJBJar modelObject = (EJBJar) provider.getModelObject();
			EnterpriseBeans enterpriseBeans = modelObject.getEnterpriseBeans();
			if (enterpriseBeans != null)
			{
				List sessionBeans = enterpriseBeans.getSessionBeans();
				for (Object object : sessionBeans) {
					SessionBean session = (SessionBean) object;
					if (session.getEjbName().equals(getDataModel().getStringProperty(EJB_NAME))){
						return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.ERR_BEAN_ALREADY_EXISTS);
					}
				}
			}
		}
		return Status.OK_STATUS;
	}

	protected IStatus validateComponentHomeInterfaces() {
		IStatus result = Status.OK_STATUS;
		String projectName = getStringProperty(PROJECT_NAME);
		if (projectName != null && projectName.length() > 0) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			IJavaProject javaProject = JavaCore.create(project);
			try {
				if (getBooleanProperty(REMOTE_HOME)) {
					String remoteHomeInterface = getStringProperty(REMOTE_HOME_INTERFACE);
					String remoteComponentInterface = getStringProperty(REMOTE_COMPONENT_INTERFACE);
					result = validate2xInterfaces(javaProject, remoteHomeInterface, remoteComponentInterface, false);
					if (!result.isOK()){
						return result;
					}
					if (result.isOK()) {
						IType findType = javaProject.findType(remoteHomeInterface);
						if (findType == null || !findType.exists()) {
							result = validateEjbInterface(remoteHomeInterface);
						}
						findType = javaProject.findType(remoteComponentInterface);
						if ((findType == null || !findType.exists()) && result.isOK()) {
							result = validateEjbInterface(remoteComponentInterface);
						}
					}
				}

				if (getBooleanProperty(LOCAL_HOME)) {
					String localHomeInterface = getStringProperty(LOCAL_HOME_INTERFACE);
					String localComponentInterface = getStringProperty(LOCAL_COMPONENT_INTERFACE);
					result = validate2xInterfaces(javaProject, localHomeInterface, localComponentInterface, true);
					if (result.isOK()) {
						IType findType = javaProject.findType(localHomeInterface);
						if (findType == null || !findType.exists()) {
							result = validateEjbInterface(localHomeInterface);
						}
						findType = javaProject.findType(localComponentInterface);
						if ((findType == null || !findType.exists()) && result.isOK()) {
							result = validateEjbInterface(localComponentInterface);
						}
					}
				}
			} catch (JavaModelException e) {
				return WTPCommonPlugin.createErrorStatus(NLS.bind(
						EJBCreationResourceHandler.ERR_COULD_NOT_RESOLVE_INTERFACE, 
						new Object[] { e.getMessage() }));
			}
		}
		return result;
	}

	private IStatus validate2xInterfaces(IJavaProject javaProject, String rhI, String rI, boolean isLocal) throws JavaModelException {

		IType home = javaProject.findType(rhI);
		IType component = javaProject.findType(rI);

		if (home != null && 
				(!home.isInterface() || 
						!hasRequiredElementInSignature(home.getSuperInterfaceTypeSignatures(), new String[] { isLocal ? "EJBLocalHome" : "EJBHome" }))) {
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_NOT_INTERFACE : EJBCreationResourceHandler.ERR_REMOTE_HOME_NOT_INTERFACE;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}

		if (component != null && 
				(!component.isInterface() 
						|| !hasRequiredElementInSignature(component.getSuperInterfaceTypeSignatures(), new String[] { isLocal ? "EJBLocalObject" : "EJBObject" }))) {
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_COMPONENT_NOT_INTERFACE : EJBCreationResourceHandler.ERR_REMOTE_COMPONENT_NOT_INTERFACE;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}

		if (home == null){
			return Status.OK_STATUS;
		}


		IMethod createMehod = home.getMethod("create", null);
		if (createMehod == null || !createMehod.exists() || !hasRequiredElementInSignature(createMehod.getExceptionTypes(), new String[]{"CreateException", "RemoteException"})){
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_MISSING_CREATE_METHOD: EJBCreationResourceHandler.ERR_REMOTE_HOME_MISSING_CREATE_METHOD;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}

		if (component == null && !rI.endsWith("."+Signature.getSignatureSimpleName(createMehod.getReturnType()))){
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_CREATE_METHOD_RETURN_TYPE_INVALID: EJBCreationResourceHandler.ERR_REMOTE_HOME_CREATE_METHOD_RETURN_TYPE_INVALID;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}
		if (!component.getElementName().equals(Signature.getSignatureSimpleName(createMehod.getReturnType()))){
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_CREATE_METHOD_RETURN_TYPE_INVALID: EJBCreationResourceHandler.ERR_REMOTE_HOME_CREATE_METHOD_RETURN_TYPE_INVALID;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}
		return Status.OK_STATUS;
	}

	private boolean hasRequiredElementInSignature(String[] allElementNames, String[] wanted) {
		if (allElementNames == null || allElementNames.length == 0){
			return false;
		}
		int found = 0;
		for (int j = 0; j < wanted.length; j++) {
			if(wanted[j] == null){
				continue;
			}
			for (int i = 0; i < allElementNames.length; i++) {
				if(allElementNames[i] == null){
					continue;
				}
				if(wanted[j].equals(Signature.toString(allElementNames[i]))){
					found++;
					if (found == wanted.length){
						return true;
					}
					break;
				}

			}

		}
		return false;
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
