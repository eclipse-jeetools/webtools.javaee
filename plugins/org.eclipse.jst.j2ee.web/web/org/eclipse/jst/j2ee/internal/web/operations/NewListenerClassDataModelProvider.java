/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.web.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * The NewListenerClassDataModelProvider is a subclass of ArtifactEditOperationDataModelProvider and
 * follows the IDataModel Operation and Wizard frameworks.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * 
 * This data model provider is a subclass of the NewJavaClassDataModelProvider, which stores base
 * properties necessary in the creation of a default java class.
 * @see org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider
 * 
 * The NewListenerClassDataModelProvider provides more specific properties for java class creation
 * that are required in creating a listener java class. The data model provider is used to store
 * these values for the NewListenerClassOperation.
 * @see org.eclipse.jst.j2ee.internal.web.operations.INewListenerClassDataModelProperties That
 *      operation will create the listener java class based on the settings defined here in the data
 *      model.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassOperation
 * 
 * This data model properties implements the IAnnotationsDataModel to get the USE_ANNOTATIONS
 * property for determining whether or not to generate an annotated java class.
 * @see org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel
 * 
 * Clients can subclass this data model provider to cache and provide their own specific attributes.
 * They should also provide their own validation methods, properties interface, and default values
 * for the properties they add.
 * 
 * The use of this class is EXPERIMENTAL and is subject to substantial changes.
 */
public class NewListenerClassDataModelProvider extends NewJavaClassDataModelProvider implements INewListenerClassDataModelProperties {

	/**
	 * <code>javax.servlet.ServletContextListener</code> interface. 
	 */
	public static final String SERVLET_CONTEXT_LISTENER = "javax.servlet.ServletContextListener"; //$NON-NLS-1$
	
	/**
	 * <code>javax.servlet.ServletContextAttributeListener</code> interface. 
	 */
	public static final String SERVLET_CONTEXT_ATTRIBUTE_LISTENER = "javax.servlet.ServletContextAttributeListener"; //$NON-NLS-1$
	
	/**
	 * <code>javax.servlet.http.HttpSessionListener</code> interface. 
	 */
	public static final String HTTP_SESSION_LISTENER = "javax.servlet.http.HttpSessionListener"; //$NON-NLS-1$
	
	/**
	 * <code>javax.servlet.http.HttpSessionAttributeListener</code> interface. 
	 */
	public static final String HTTP_SESSION_ATTRIBUTE_LISTENER = "javax.servlet.http.HttpSessionAttributeListener"; //$NON-NLS-1$
	
	/**
	 * <code>javax.servlet.http.HttpSessionActivationListener</code> interface. 
	 */
	public static final String HTTP_SESSION_ACTIVATION_LISTENER = "javax.servlet.http.HttpSessionActivationListener"; //$NON-NLS-1$
	
	/**
	 * <code>javax.servlet.http.HttpSessionBindingListener</code> interface. 
	 */
	public static final String HTTP_SESSION_BINDING_LISTENER = "javax.servlet.http.HttpSessionBindingListener"; //$NON-NLS-1$
	
	/**
	 * <code>javax.servlet.ServletRequestListener</code> interface. 
	 */
	public static final String SERVLET_REQUEST_LISTENER = "javax.servlet.ServletRequestListener"; //$NON-NLS-1$
	
	/**
	 * <code>javax.servlet.ServletRequestAttributeListener</code> interface. 
	 */
	public static final String SERVLET_REQUEST_ATTRIBUTE_LISTENER = "javax.servlet.ServletRequestAttributeListener"; //$NON-NLS-1$
	
	public static final String[] LISTENER_INTERFACES = {
		SERVLET_CONTEXT_LISTENER,	
		SERVLET_CONTEXT_ATTRIBUTE_LISTENER,
		HTTP_SESSION_LISTENER,
		HTTP_SESSION_ATTRIBUTE_LISTENER,
		HTTP_SESSION_ACTIVATION_LISTENER,
		HTTP_SESSION_BINDING_LISTENER,
		SERVLET_REQUEST_LISTENER,
		SERVLET_REQUEST_ATTRIBUTE_LISTENER
	};
	
	/**
	 * The fully qualified default listener superclass.
	 */
	private final static String LISTENER_SUPERCLASS = ""; //$NON-NLS-1$ 

	/**
	 * The cache of all the interfaces the listener java class will implement.
	 */
	private List interfaceList;

	private static boolean useAnnotations = false;

	/**
	 * Subclasses may extend this method to provide their own default operation for this data model
	 * provider. This implementation uses the AddListenerOperation to drive the listener creation. It
	 * will not return null.
	 * 
	 * @see IDataModel#getDefaultOperation()
	 * 
	 * @return IDataModelOperation AddListenerOperation
	 */
	public IDataModelOperation getDefaultOperation() {
		return new AddListenerOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to provide their own determination of whether or not
	 * certain properties should be disabled or enabled. This method does not accept null parameter.
	 * It will not return null. This implementation makes sure annotation support is only allowed on
	 * web projects of J2EE version 1.3 or higher.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#isPropertyEnabled(String)
	 * @see IAnnotationsDataModel#USE_ANNOTATIONS
	 * 
	 * @param propertyName
	 * @return boolean should property be enabled?
	 */
	public boolean isPropertyEnabled(String propertyName) {
//		// Annotations should only be enabled on a valid j2ee project of version 1.3 or higher
//		if (USE_ANNOTATIONS.equals(propertyName)) {
//			if (getBooleanProperty(USE_EXISTING_CLASS) || !isAnnotationsSupported())
//				return false;
//			return true;
//		}
		// Otherwise return super implementation
		return super.isPropertyEnabled(propertyName);
	}

	/**
	 * Subclasses may extend this method to add their own data model's properties as valid base
	 * properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	public Set getPropertyNames() {
		// Add listener specific properties defined in this data model
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(USE_ANNOTATIONS);
		propertyNames.add(USE_EXISTING_CLASS);
		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own default values for any of the
	 * properties in the data model hierarchy. This method does not accept a null parameter. It may
	 * return null. This implementation sets annotation use to be true. 
	 * 
	 * @see NewJavaClassDataModelProvider#getDefaultProperty(String)
	 * @see IDataModelProvider#getDefaultProperty(String)
	 * 
	 * @param propertyName
	 * @return Object default value of property
	 */
	public Object getDefaultProperty(String propertyName) {
		// Create an annotated listener java class by default
		if (propertyName.equals(USE_ANNOTATIONS))
			return shouldDefaultAnnotations();
		else if (propertyName.equals(SUPERCLASS))
			return LISTENER_SUPERCLASS;
		else if (propertyName.equals(USE_EXISTING_CLASS))
			return Boolean.FALSE;
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * Subclasses may extend this method to add their own specific behavior when a certain property
	 * in the data model hierarchy is set. This method does not accept null for the property name,
	 * but it will for propertyValue. It will not return null. It will return false if the set
	 * fails. This implementation verifies the display name is set to the class name, that the
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

		// If annotations is changed, notify an enablement change
		if (propertyName.equals(USE_ANNOTATIONS)) {
			useAnnotations = ((Boolean) propertyValue).booleanValue();
			if (useAnnotations && !isAnnotationsSupported())
				return true;
			getDataModel().notifyPropertyChange(USE_ANNOTATIONS, IDataModel.ENABLE_CHG);
		}
		// If the source folder is changed, ensure we have the correct project name
		if (propertyName.equals(SOURCE_FOLDER)) {
			// Get the project name from the source folder name
			String sourceFolder = (String) propertyValue;
			int index = sourceFolder.indexOf(File.separator);
			String projectName = sourceFolder;
			if (index == 0)
				projectName = sourceFolder.substring(1);
			index = projectName.indexOf(File.separator);
			if (index != -1) {
				projectName = projectName.substring(0, index);
				setProperty(PROJECT_NAME, projectName);
			}
		}
		// Call super to set the property on the data model
		boolean result = super.propertySet(propertyName, propertyValue);
		// After the property is set, if project changed, update the nature and the annotations
		// enablement
		if (propertyName.equals(COMPONENT_NAME)) {
			getDataModel().notifyPropertyChange(USE_ANNOTATIONS, IDataModel.ENABLE_CHG);
		}
		// After property is set, if annotations is set to true, update its value based on the new
		// level of the project
		if (getBooleanProperty(USE_ANNOTATIONS)) {
			if (!isAnnotationsSupported())
				setBooleanProperty(USE_ANNOTATIONS, false);
		}
		if (propertyName.equals(USE_EXISTING_CLASS)) {
			getDataModel().notifyPropertyChange(USE_ANNOTATIONS, IDataModel.ENABLE_CHG);
			if (((Boolean)propertyValue).booleanValue())
				setProperty(USE_ANNOTATIONS,Boolean.FALSE);
			setProperty(JAVA_PACKAGE, null);
			setProperty(CLASS_NAME, null);
		}
		// Return whether property was set
		return result;
	}

	/**
	 * This method is used to determine if annotations should try to enable based on workspace settings
	 * @return does any valid annotation provider or xdoclet handler exist
	 */
	//TODO add this method back in for defect 146696
//	protected boolean isAnnotationProviderDefined() {
//		boolean isControllerEnabled = AnnotationsControllerManager.INSTANCE.isAnyAnnotationsSupported();
//		final String preferred = AnnotationPreferenceStore.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER);
//		IAnnotationProvider annotationProvider = null;
//		boolean isProviderEnabled = false;
//		if (preferred != null) {
//			try {
//				annotationProvider = AnnotationUtilities.findAnnotationProviderByName(preferred);
//			} catch (Exception ex) { 
//				//Default
//			}
//			if (annotationProvider != null && annotationProvider.isValid())
//				isProviderEnabled = true;
//		}
//		return isControllerEnabled || isProviderEnabled;
//	}
	
	/**
	 * This method checks to see if valid annotation providers exist and if valid project version levels exist.
	 * @return should annotations be supported for this project in this workspace
	 */
	protected boolean isAnnotationsSupported() {
		//TODO add this check back in for defect 146696
//		if (!isAnnotationProviderDefined())
//			return false;
		if (!getDataModel().isPropertySet(IArtifactEditOperationDataModelProperties.PROJECT_NAME))
			return true;
		if (getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME).equals("")) //$NON-NLS-1$
			return true;
		IProject project = ProjectUtilities.getProject(getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME));
		String moduleName = getStringProperty(IArtifactEditOperationDataModelProperties.COMPONENT_NAME);
		if (project == null || moduleName == null || moduleName.equals(""))return true; //$NON-NLS-1$
		int j2eeVersion = J2EEVersionUtil.convertVersionStringToInt(J2EEProjectUtilities.getJ2EEProjectVersion(project));
		
		return j2eeVersion > J2EEVersionConstants.VERSION_1_2;
		
	}

	/**
	 * Subclasses may extend this method to provide their own validation on any of the valid data
	 * model properties in the hierarchy. It does not accept a null parameter. This method will 
	 * not return null.
	 * 
	 * @see NewJavaClassDataModelProvider#validate(String)
	 * 
	 * @param propertyName
	 * @return IStatus is property value valid?
	 */
	public IStatus validate(String propertyName) {
		IStatus result = Status.OK_STATUS;
		// If our default is the superclass, we know it is ok
		if (propertyName.equals(SUPERCLASS) && getStringProperty(propertyName).equals(LISTENER_SUPERCLASS))
			return WTPCommonPlugin.OK_STATUS;
		if (propertyName.equals(CLASS_NAME)) {
			if (getStringProperty(propertyName).length()!=0 && getBooleanProperty(USE_EXISTING_CLASS))
				return WTPCommonPlugin.OK_STATUS;
			result = super.validateJavaClassName(getStringProperty(propertyName));
			if (result.isOK()) {
				result = validateJavaClassName(getStringProperty(propertyName));
				if (result.isOK()&&!getBooleanProperty(USE_EXISTING_CLASS))
					result = canCreateTypeInClasspath(getStringProperty(CLASS_NAME));
			}
			return result;
		}
		if (propertyName.equals(INTERFACES)) {
			return validateListeners();
		}
		// Otherwise defer to super to validate the property
		return super.validate(propertyName);
	}
	

	/**
	 * Checks if at least one of the application lifecycle listeners is selected. 
	 */
	private IStatus validateListeners() {
		boolean atLeastOneSelected = false;
		Object value = model.getProperty(INewJavaClassDataModelProperties.INTERFACES);
		if (value != null && (value instanceof List)) {
			List interfaces = (List) value;
			for (String iface : LISTENER_INTERFACES) {
				if (interfaces.contains(iface)) { 
					atLeastOneSelected = true;
					break;
				}
			}
		}
		
		if (atLeastOneSelected) {
			return WTPCommonPlugin.OK_STATUS;
		} else {
			String msg = WebMessages.ERR_NO_LISTENER_SELECTED;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
	}

	/**
	 * This method is intended for internal use only. It provides a simple algorithm for detecting
	 * if there are duplicate entries in a list. It will accept a null parameter. It will not return
	 * null.
	 * 
	 * @see NewListenerClassDataModelProvider#validateInitParamList(List)
	 * @see NewListenerClassDataModelProvider#validateURLMappingList(List)
	 * 
	 * @param input
	 * @return boolean are there dups in the list?
	 */
	private boolean hasDuplicatesInStringArrayList(List input) {
		// If list is null or empty return false
		if (input == null)
			return false;
		int n = input.size();
		boolean dup = false;
		// nested for loops to check each element to see if other elements are the same
		for (int i = 0; i < n; i++) {
			String[] sArray1 = (String[]) input.get(i);
			for (int j = i + 1; j < n; j++) {
				String[] sArray2 = (String[]) input.get(j);
				if (isTwoStringArraysEqual(sArray1, sArray2)) {
					dup = true;
					break;
				}
			}
			if (dup)
				break;
		}
		// Return boolean status for duplicates
		return dup;
	}

	/**
	 * This method is intended for internal use only. This checks to see if the two string arrays
	 * are equal. If either of the arrays are null or empty, it returns false.
	 * 
	 * @see NewListenerClassDataModelProvider#hasDuplicatesInStringArrayList(List)
	 * 
	 * @param sArray1
	 * @param sArray2
	 * @return boolean are Arrays equal?
	 */
	private boolean isTwoStringArraysEqual(String[] sArray1, String[] sArray2) {
		// If either array is null, return false
		if (sArray1 == null || sArray2 == null)
			return false;
		int n1 = sArray1.length;
		int n2 = sArray1.length;
		// If either array is empty, return false
		if (n1 == 0 || n2 == 0)
			return false;
		// If they don't have the same length, return false
		if (n1 != n2)
			return false;
		// If their first elements do not match, return false
		if (!sArray1[0].equals(sArray2[0]))
			return false;
		// Otherwise return true
		return true;
	}

	/**
	 * @return boolean should the default annotations be true?
	 */
	private static Boolean shouldDefaultAnnotations() {
		if (useAnnotations)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
