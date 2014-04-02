/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 * Roberto Sanchez, rsanchez@mx1.ibm.com - Add remote and local annotations to bean class
 * Roberto Sanchez, rsanchez@mx1.ibm.com - Allow adopter to change default value of package for interfaces
 * IBM - Async and Non-persistent support
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_COMPONENT_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.NO_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.ASYNC;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_COMPONENT_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.STATE_TYPE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.BUSINESS_INTERFACE_ANNOTATION_LOCATION;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
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
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.project.facet.IProductConstants;

public class NewSessionBeanClassDataModelProvider extends NewEnterpriseBeanClassDataModelProvider {

	private static final String LOCAL_SUFFIX = "Local"; //$NON-NLS-1$
	private static final String REMOTE_SUFFIX = "Remote"; //$NON-NLS-1$
	private static final String LOCAL_HOME_SUFFIX = "LocalHome"; //$NON-NLS-1$
	private static final String REMOTE_HOME_SUFFIX = "RemoteHome"; //$NON-NLS-1$
	private static final String LOCAL_COMPONENT_SUFFIX = "LocalComponent"; //$NON-NLS-1$
	private static final String REMOTE_COMPONENT_SUFFIX = "RemoteComponent"; //$NON-NLS-1$
	
	
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new AddSessionBeanOperation(getDataModel());
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

		propertyNames.add(REMOTE_BUSINESS_INTERFACE);
		propertyNames.add(LOCAL_BUSINESS_INTERFACE);
		propertyNames.add(REMOTE);
		propertyNames.add(LOCAL);
		propertyNames.add(NO_INTERFACE);
		propertyNames.add(ASYNC);
		propertyNames.add(STATE_TYPE);
		propertyNames.add(REMOTE_HOME);
		propertyNames.add(LOCAL_HOME);
		propertyNames.add(REMOTE_HOME_INTERFACE);
		propertyNames.add(LOCAL_HOME_INTERFACE);
		propertyNames.add(LOCAL_COMPONENT_INTERFACE);
		propertyNames.add(REMOTE_COMPONENT_INTERFACE);
		propertyNames.add(BUSINESS_INTERFACE_ANNOTATION_LOCATION);

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
		if (propertyName.equals(REMOTE_HOME))
			return Boolean.FALSE;
		else if (propertyName.equals(LOCAL_HOME))
			return Boolean.FALSE;
		else if (propertyName.equals(REMOTE))
			return Boolean.FALSE;
		else if (propertyName.equals(LOCAL))
			return new Boolean(!ejb3xOrLater(J2EEVersionConstants.VERSION_3_1));
		else if (propertyName.equals(NO_INTERFACE)) 
			return new Boolean(ejb3xOrLater(J2EEVersionConstants.VERSION_3_1));
		else if (ASYNC.equals(propertyName))
			return Boolean.FALSE;
		else if (propertyName.equals(STATE_TYPE))
			return StateType.STATELESS.toString(); 
		else if (propertyName.equals(INTERFACES)) {
			List<BusinessInterface> listResult = new ArrayList<BusinessInterface>();
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			if (getBooleanProperty(REMOTE) && className.length() > 0) {
				BusinessInterface remoteInterface = new BusinessInterface(getStringProperty(REMOTE_BUSINESS_INTERFACE), BusinessInterfaceType.REMOTE);
				listResult.add(remoteInterface);
			}
			if (getBooleanProperty(LOCAL) && className.length() > 0) {
				BusinessInterface localInterface = new BusinessInterface(getStringProperty(LOCAL_BUSINESS_INTERFACE), BusinessInterfaceType.LOCAL);
				listResult.add(localInterface);
			}
			return listResult;
		}
		else if (REMOTE_BUSINESS_INTERFACE.equals(propertyName)) {		
			String className = getStringProperty(QUALIFIED_CLASS_NAME);			
			String packageSuffix = J2EEPlugin.getDefault().getJ2EEPreferences().getString(IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX);
			return buildInterfaceName(className, REMOTE_SUFFIX, packageSuffix);
 		}
		else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName)) {
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			String packageSuffix = J2EEPlugin.getDefault().getJ2EEPreferences().getString(IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX);
			return buildInterfaceName(className, LOCAL_SUFFIX, packageSuffix);
		}
		else if (REMOTE_HOME_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			String packageSuffix = J2EEPlugin.getDefault().getJ2EEPreferences().getString(IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX);
			return buildInterfaceName(className, REMOTE_HOME_SUFFIX, packageSuffix);
		}
		else if (LOCAL_HOME_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			String packageSuffix = J2EEPlugin.getDefault().getJ2EEPreferences().getString(IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX);
			return buildInterfaceName(className, LOCAL_HOME_SUFFIX, packageSuffix);
		}
		else if (LOCAL_COMPONENT_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			String packageSuffix = J2EEPlugin.getDefault().getJ2EEPreferences().getString(IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX);
			return buildInterfaceName(className, LOCAL_COMPONENT_SUFFIX, packageSuffix);
		}
		else if (REMOTE_COMPONENT_INTERFACE.equals(propertyName))
		{
			String className = getStringProperty(QUALIFIED_CLASS_NAME);
			String packageSuffix = J2EEPlugin.getDefault().getJ2EEPreferences().getString(IProductConstants.EJB_INTERFACE_PACKAGE_SUFFIX);
			return buildInterfaceName(className, REMOTE_COMPONENT_SUFFIX, packageSuffix);
		}
		else if (BUSINESS_INTERFACE_ANNOTATION_LOCATION.equals(propertyName))
		{
			boolean inInterface = J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(IProductConstants.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_INTERFACE);
			boolean inBean = J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(IProductConstants.EJB_BUSINESS_INTERFACE_ANNOTATION_IN_BEAN);
			if (inInterface && inBean){
				return BusinessInterfaceAnnotationLocationType.BEAN_CLASS_AND_INTERFACE.toString();
			}
			if (inBean){
				return BusinessInterfaceAnnotationLocationType.BEAN_CLASS_ONLY.toString();
			}
			return BusinessInterfaceAnnotationLocationType.INTERFACE_ONLY.toString();
			
		}
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	
	private String buildInterfaceName(final String qualifiedBeanClassName, final String interfaceSuffix,
			final String interfacePackageSuffix) {

		boolean usePackageSuffix = (interfacePackageSuffix != null  && interfacePackageSuffix.length() > 0);
		String interfaceName = qualifiedBeanClassName + ((qualifiedBeanClassName != null && qualifiedBeanClassName.length() > 0) ? interfaceSuffix : ""); //$NON-NLS-1$	

		if (!usePackageSuffix || interfaceName.equals("")){ //$NON-NLS-1$
			return interfaceName;
		}
		StringBuffer buf = new StringBuffer(interfaceName);

		int index = buf.lastIndexOf("."); //$NON-NLS-1$
		if (index == -1){
			return buf.insert(0, interfacePackageSuffix + ".").toString(); //$NON-NLS-1$
		}
		return buf.insert(index, "." + interfacePackageSuffix).toString(); //$NON-NLS-1$
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
		// Call super to set the property on the data model
		boolean result = super.propertySet(propertyName, propertyValue);

		if (PROJECT_NAME.equals(propertyName)) {
			IDataModel dataModel = getDataModel();
			dataModel.notifyPropertyChange(REMOTE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(LOCAL, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(NO_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(ASYNC, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(REMOTE_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(LOCAL_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
		} else if (CLASS_NAME.equals(propertyName) || JAVA_PACKAGE.equals(propertyName)) {
			IDataModel dataModel = getDataModel();
			dataModel.notifyPropertyChange(REMOTE_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(LOCAL_BUSINESS_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(REMOTE_HOME_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(LOCAL_HOME_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(REMOTE_COMPONENT_INTERFACE, IDataModel.DEFAULT_CHG);
			dataModel.notifyPropertyChange(LOCAL_COMPONENT_INTERFACE, IDataModel.DEFAULT_CHG);
		} else if (propertyName.equals(REMOTE)) {
			if (!getDataModel().isPropertySet(INTERFACES)) {
				getDataModel().notifyPropertyChange(INTERFACES, IDataModel.DEFAULT_CHG);
			} else {
				updateBusinessInterfaces(REMOTE);
			}
			getDataModel().notifyPropertyChange(REMOTE_BUSINESS_INTERFACE, IDataModel.ENABLE_CHG);

		} else if (propertyName.equals(LOCAL)) {
			if (!getDataModel().isPropertySet(INTERFACES)) {
				getDataModel().notifyPropertyChange(INTERFACES, IDataModel.DEFAULT_CHG);
			} else {
				updateBusinessInterfaces(LOCAL);
			}
			getDataModel().notifyPropertyChange(LOCAL_BUSINESS_INTERFACE, IDataModel.ENABLE_CHG);
		} else if (REMOTE_BUSINESS_INTERFACE.equals(propertyName)) {
			if(getRemoteProperty() != null){
				getRemoteProperty().setFullyQualifiedName(propertyValue.toString());
			}
		} else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName)) {
			if(getLocalProperty() != null){
				getLocalProperty().setFullyQualifiedName(propertyValue.toString());
			}
		} else if (propertyName.equals(ASYNC)) {
			getDataModel().notifyPropertyChange(ASYNC, IDataModel.DEFAULT_CHG);
		} 

		return result;
	}
	
	@Override
	public boolean isPropertyEnabled(String propertyName) {
		if (REMOTE_BUSINESS_INTERFACE.equals(propertyName))	{
			return getDataModel().getBooleanProperty(REMOTE);
		} else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName)) {
			return getDataModel().getBooleanProperty(LOCAL);
		} else if (ASYNC.equals(propertyName)) {
			return ejb3xOrLater(J2EEVersionConstants.VERSION_3_1);
		}
		
		return super.isPropertyEnabled(propertyName);
	}
	
	@Override
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(STATE_TYPE)) {
			return DataModelPropertyDescriptor.createDescriptors(
					new String[] {
							StateType.STATELESS.toString(), 
							StateType.STATEFUL.toString(), 
							StateType.SINGLETON.toString()
					}, 
					new String[] {
							EJBCreationResourceHandler.STATE_TYPE_STATELESS, 
							EJBCreationResourceHandler.STATE_TYPE_STATEFUL, 
							EJBCreationResourceHandler.STATE_TYPE_SINGLETON
					}
			);
		} 
		
		return super.getValidPropertyDescriptors(propertyName);
	}

	private void updateBusinessInterfaces(String propertyName) {
		List<BusinessInterface> list = (List<BusinessInterface>) getProperty(INTERFACES);
		if (propertyName.equals(REMOTE)) {
			if (getBooleanProperty(propertyName)) {
				list.add(new BusinessInterface(getStringProperty(REMOTE_BUSINESS_INTERFACE), BusinessInterfaceType.REMOTE));
			} else {
				BusinessInterface remoteInterface = getRemoteProperty();
				int indexOf = list.indexOf(remoteInterface);
				list.remove(indexOf);
			}
		} else if (propertyName.equals(LOCAL)) {
			if (getBooleanProperty(propertyName)) {
				list.add(new BusinessInterface(getStringProperty(LOCAL_BUSINESS_INTERFACE), BusinessInterfaceType.LOCAL));
			} else {
				BusinessInterface localInterface = getLocalProperty();
				int indexOf = list.indexOf(localInterface);
				list.remove(indexOf);
			}
		}
	}

	private BusinessInterface getRemoteProperty() {
		List<BusinessInterface> businessInterfaces = (List<BusinessInterface>) getProperty(INTERFACES);
		for (BusinessInterface iface : businessInterfaces) {
			if ((iface.getJavaType() == null) && (iface.isRemote())) {
				return iface;
			}
		}
		return null;
	}
	private BusinessInterface getLocalProperty() {
		List<BusinessInterface> businessInterfaces = (List<BusinessInterface>) getProperty(INTERFACES);
		for (BusinessInterface iface : businessInterfaces) {
			if ((iface.getJavaType() == null) && (iface.isLocal())) {
				return iface;
			}
		}
		return null;
	}

	@Override
	public IStatus validate(String propertyName) {
		if (STATE_TYPE.equals(propertyName)) {
			return validateStateType();			
		} else if (NO_INTERFACE.equals(propertyName)) {
			if (getBooleanProperty(NO_INTERFACE)) {
				return validateNoInterface();
			}
		} else if (LOCAL_BUSINESS_INTERFACE.equals(propertyName)) {
			if (getBooleanProperty(LOCAL)) {
				return validateEjbInterface(getStringProperty(propertyName));
			}
		} else if (REMOTE_BUSINESS_INTERFACE.equals(propertyName)) {
			if (getBooleanProperty(REMOTE)) {
				return validateEjbInterface(getStringProperty(propertyName));
			}
		} else if (LOCAL_COMPONENT_INTERFACE.equals(propertyName) || 
				REMOTE_COMPONENT_INTERFACE.equals(propertyName) || 
				LOCAL_HOME_INTERFACE.equals(propertyName) || 
				REMOTE_HOME_INTERFACE.equals(propertyName)) {
			return validateComponentHomeInterfaces();
		} else if (INTERFACES.equals(propertyName)) {
			return validateInterfacesList();
		} else if (ASYNC.equals(propertyName) && getBooleanProperty(ASYNC)) {
			return validateAsynchronous();
		}
			
		return super.validate(propertyName);
	}

	protected IStatus validateStateType() {
		String value = getStringProperty(STATE_TYPE);
		if (StateType.SINGLETON.toString().equals(value) && !ejb3xOrLater(J2EEVersionConstants.VERSION_3_1)) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.ERR_SINGLETON_NOT_ALLOWED);
		}
		return Status.OK_STATUS;
	}

	protected IStatus validateNoInterface() {
		if (!ejb3xOrLater(J2EEVersionConstants.VERSION_3_1)) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.ERR_NO_INTERFACE_NOT_ALLOWED);
		}
		return Status.OK_STATUS;
	}
	
	protected IStatus validateAsynchronous() {
		IStatus retVal = Status.OK_STATUS;
		if (getBooleanProperty(ASYNC)) {
			if (ejb3xOrLater(J2EEVersionConstants.VERSION_3_1) && !ejb3xOrLater(J2EEVersionConstants.VERSION_3_2)) {
				retVal = new Status(IStatus.WARNING, EjbPlugin.PLUGIN_ID, EJBCreationResourceHandler.WRN_EJB31_ASYNC_NOT_SUPPORTED);
			}
		}
		return retVal;
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

	protected IStatus validateComponentHomeInterfaces() {
		IStatus result = Status.OK_STATUS;
		String projectName = getStringProperty(PROJECT_NAME);
		if (projectName != null && projectName.length() > 0) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			IJavaProject javaProject = JavaCore.create(project);
			try {
				if (getBooleanProperty(LOCAL_HOME)) {
					String localHomeInterface = getStringProperty(LOCAL_HOME_INTERFACE);
					String localComponentInterface = getStringProperty(LOCAL_COMPONENT_INTERFACE);
					result = validate2xInterfaces(javaProject, localHomeInterface, localComponentInterface, true);
					if (!result.isOK()) return result;
					
					IType findType = javaProject.findType(localComponentInterface);
					if ((findType == null || !findType.exists()) && result.isOK()) {
						result = validateEjbInterface(localComponentInterface);
						if (!result.isOK()) return result;
					}
					
					findType = javaProject.findType(localHomeInterface);
					if (findType == null || !findType.exists()) {
						result = validateEjbInterface(localHomeInterface);
						if (!result.isOK()) return result;
					}
				}
				if (getBooleanProperty(REMOTE_HOME)) {
					String remoteHomeInterface = getStringProperty(REMOTE_HOME_INTERFACE);
					String remoteComponentInterface = getStringProperty(REMOTE_COMPONENT_INTERFACE);
					result = validate2xInterfaces(javaProject, remoteHomeInterface, remoteComponentInterface, false);
					if (!result.isOK()) return result;
					
					IType findType = javaProject.findType(remoteComponentInterface);
					if ((findType == null || !findType.exists()) && result.isOK()) {
						result = validateEjbInterface(remoteComponentInterface);
						if (!result.isOK()) return result;
					}
					findType = javaProject.findType(remoteHomeInterface);
					if (findType == null || !findType.exists()) {
						result = validateEjbInterface(remoteHomeInterface);
						if (!result.isOK()) return result;
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
						!hasRequiredElementInSignature(home.getSuperInterfaceTypeSignatures(), new String[] { isLocal ? "EJBLocalHome" : "EJBHome" }))) { //$NON-NLS-1$ //$NON-NLS-2$
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_NOT_INTERFACE : EJBCreationResourceHandler.ERR_REMOTE_HOME_NOT_INTERFACE;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}

		if (component != null && 
				(!component.isInterface() 
						|| !hasRequiredElementInSignature(component.getSuperInterfaceTypeSignatures(), new String[] { isLocal ? "EJBLocalObject" : "EJBObject" }))) { //$NON-NLS-1$ //$NON-NLS-2$
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_COMPONENT_NOT_INTERFACE : EJBCreationResourceHandler.ERR_REMOTE_COMPONENT_NOT_INTERFACE;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}

		if (home == null){
			return Status.OK_STATUS;
		}


		IMethod createMehod = home.getMethod("create", null); //$NON-NLS-1$
		if (createMehod == null || !createMehod.exists() || !hasRequiredElementInSignature(createMehod.getExceptionTypes(), new String[]{"CreateException", "RemoteException"})){ //$NON-NLS-1$ //$NON-NLS-2$
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_MISSING_CREATE_METHOD: EJBCreationResourceHandler.ERR_REMOTE_HOME_MISSING_CREATE_METHOD;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}

		if (component == null && !rI.endsWith("."+Signature.getSignatureSimpleName(createMehod.getReturnType()))){ //$NON-NLS-1$
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_CREATE_METHOD_RETURN_TYPE_INVALID: EJBCreationResourceHandler.ERR_REMOTE_HOME_CREATE_METHOD_RETURN_TYPE_INVALID;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}
		if (component != null && !component.getElementName().equals(Signature.getSignatureSimpleName(createMehod.getReturnType()))){
			String msg = (isLocal) ? EJBCreationResourceHandler.ERR_LOCAL_HOME_CREATE_METHOD_RETURN_TYPE_INVALID: EJBCreationResourceHandler.ERR_REMOTE_HOME_CREATE_METHOD_RETURN_TYPE_INVALID;
			return new Status(IStatus.ERROR, EjbPlugin.PLUGIN_ID, msg);
		}
		return Status.OK_STATUS;
	}

	protected IStatus validateInterfacesList() {
		List<BusinessInterface> list = (List<BusinessInterface>) getProperty(INTERFACES);
		if (list.isEmpty() && !ejb3xOrLater(J2EEVersionConstants.VERSION_3_1)) {
			return new Status(IStatus.WARNING, EjbPlugin.PLUGIN_ID, EJBCreationResourceHandler.WRN_NO_CLIENT_VIEW); 
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
