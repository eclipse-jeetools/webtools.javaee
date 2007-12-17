/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Kiril Mitov, k.mitov@sap.com	- bug 204160
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
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.web.validation.UrlPattern;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * The NewServletClassDataModelProvider is a subclass of ArtifactEditOperationDataModelProvider and
 * follows the IDataModel Operation and Wizard frameworks.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * 
 * This data model provider is a subclass of the NewJavaClassDataModelProvider, which stores base
 * properties necessary in the creation of a default java class.
 * @see org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider
 * 
 * The NewServletClassDataModelProvider provides more specific properties for java class creation
 * that are required in creating a servlet java class. The data model provider is used to store
 * these values for the NewServletClassOperation.
 * @see org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties That
 *      operation will create the servlet java class based on the settings defined here in the data
 *      model.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewServletClassOperation
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
public class NewServletClassDataModelProvider extends NewJavaClassDataModelProvider implements INewServletClassDataModelProperties {

	/**
	 * The fully qualified default servlet superclass: HttpServlet.
	 */
	private final static String SERVLET_SUPERCLASS = "javax.servlet.http.HttpServlet"; //$NON-NLS-1$ 
	/**
	 * String array of the default, minimum required fully qualified Servlet interfaces
	 */
	private final static String[] SERVLET_INTERFACES = {"javax.servlet.Servlet"}; //$NON-NLS-1$

	private final static String ANNOTATED_TEMPLATE_DEFAULT = "servlet.javajet"; //$NON-NLS-1$

	private final static String NON_ANNOTATED_TEMPLATE_DEFAULT = "servlet.javajet"; //$NON-NLS-1$

	/**
	 * The cache of all the interfaces the servlet java class will implement.
	 */
	private List interfaceList;
	
	private String classNameCache;
	private String existingClassNameCache;

	private static boolean useAnnotations = false;

	/**
	 * Subclasses may extend this method to provide their own default operation for this data model
	 * provider. This implementation uses the AddServletOperation to drive the servlet creation. It
	 * will not return null.
	 * 
	 * @see IDataModel#getDefaultOperation()
	 * 
	 * @return IDataModelOperation AddServletOperation
	 */
	public IDataModelOperation getDefaultOperation() {
		return new AddServletOperation(getDataModel());
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
		// Annotations should only be enabled on a valid j2ee project of version 1.3 or higher
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getBooleanProperty(USE_EXISTING_CLASS) || !isAnnotationsSupported())
				return false;
			return true;
		}
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
		// Add servlet specific properties defined in this data model
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(INIT);
		propertyNames.add(DESTROY);
		propertyNames.add(GET_SERVLET_CONFIG);
		propertyNames.add(GET_SERVLET_INFO);
		propertyNames.add(SERVICE);
		propertyNames.add(DO_GET);
		propertyNames.add(DO_POST);
		propertyNames.add(DO_PUT);
		propertyNames.add(DO_DELETE);
		propertyNames.add(DO_HEAD);
		propertyNames.add(DO_OPTIONS);
		propertyNames.add(DO_TRACE);
		propertyNames.add(TO_STRING);
		propertyNames.add(IS_SERVLET_TYPE);
		propertyNames.add(INIT_PARAM);
		propertyNames.add(URL_MAPPINGS);
		propertyNames.add(USE_ANNOTATIONS);
		propertyNames.add(DISPLAY_NAME);
		propertyNames.add(DESCRIPTION);
		propertyNames.add(NON_ANNOTATED_TEMPLATE_FILE);
		propertyNames.add(TEMPLATE_FILE);
		propertyNames.add(USE_EXISTING_CLASS);
		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own default values for any of the
	 * properties in the data model hierarchy. This method does not accept a null parameter. It may
	 * return null. This implementation sets annotation use to be true, and to generate a servlet
	 * with doGet and doPost.
	 * 
	 * @see NewJavaClassDataModelProvider#getDefaultProperty(String)
	 * @see IDataModelProvider#getDefaultProperty(String)
	 * 
	 * @param propertyName
	 * @return Object default value of property
	 */
	public Object getDefaultProperty(String propertyName) {
		// Generate a doPost and doGet methods by default only if a class 
		// extending HttpServlet is selected
		if (propertyName.equals(DO_POST) || propertyName.equals(DO_GET)) {
			ServletSupertypesValidator validator = new ServletSupertypesValidator(getDataModel());
			if (validator.isHttpServletSuperclass())
				return Boolean.TRUE;
		}
		
		// Generate a service method by default only if a class 
		// not extending HttpServlet is selected
		if (propertyName.equals(SERVICE)) {
			ServletSupertypesValidator validator = new ServletSupertypesValidator(getDataModel());
			if (!validator.isHttpServletSuperclass())
				return Boolean.TRUE;
		}
		
		if (propertyName.equals(INIT) || propertyName.equals(DESTROY) || 
			propertyName.equals(GET_SERVLET_CONFIG) || propertyName.equals(GET_SERVLET_INFO)) {
			ServletSupertypesValidator validator = new ServletSupertypesValidator(getDataModel());
			if (!validator.isGenericServletSuperclass()) 
				return Boolean.TRUE;
		}
		
		// Use servlet by default
		else if (propertyName.equals(IS_SERVLET_TYPE))
			return Boolean.TRUE;
		else if (propertyName.equals(USE_ANNOTATIONS))
			return shouldDefaultAnnotations();
		else if (propertyName.equals(DISPLAY_NAME)) {
			String className = getStringProperty(CLASS_NAME);
			if (className.endsWith(".jsp")) { //$NON-NLS-1$
				int index = className.lastIndexOf("/"); //$NON-NLS-1$
				className = className.substring(index+1,className.length()-4);
			} else {
				int index = className.lastIndexOf("."); //$NON-NLS-1$
				className = className.substring(index+1);
			}
			return className;
		}
		else if (propertyName.equals(URL_MAPPINGS))
			return getDefaultUrlMapping();
		else if (propertyName.equals(INTERFACES))
			return getServletInterfaces();
		else if (propertyName.equals(SUPERCLASS))
			return SERVLET_SUPERCLASS;
		else if (propertyName.equals(TEMPLATE_FILE))
			return ANNOTATED_TEMPLATE_DEFAULT;
		else if (propertyName.equals(NON_ANNOTATED_TEMPLATE_FILE))
			return NON_ANNOTATED_TEMPLATE_DEFAULT;
		else if (propertyName.equals(USE_EXISTING_CLASS))
			return Boolean.FALSE;
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * Returns the default Url Mapping depending upon the display name of the Servlet
	 * 
	 * @return List containting the default Url Mapping
	 */
	private Object getDefaultUrlMapping() {
		List urlMappings = null;
		String text = (String) getProperty(DISPLAY_NAME);
		if (text != null) {
			urlMappings = new ArrayList();
			urlMappings.add(new String[]{"/" + text}); //$NON-NLS-1$
		}
		return urlMappings;
	}

	/**
	 * Subclasses may extend this method to add their own specific behaviour when a certain property
	 * in the data model heirarchy is set. This method does not accept null for the property name,
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
		// If class name is changed, update the display name to be the same
		if (propertyName.equals(CLASS_NAME) && !getDataModel().isPropertySet(DISPLAY_NAME)) {
			getDataModel().notifyPropertyChange(DISPLAY_NAME, IDataModel.DEFAULT_CHG);
		}
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
			
			if (((Boolean) propertyValue).booleanValue()) {
				classNameCache = getStringProperty(CLASS_NAME);
				setProperty(CLASS_NAME, existingClassNameCache);
			} else {
				existingClassNameCache = getStringProperty(CLASS_NAME);
				setProperty(CLASS_NAME, classNameCache);
			}
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
	 * model properties in the hierarchy. This implementation adds validation for the init params,
	 * servlet mappings, display name, and existing class fields specific to the servlet java class
	 * creation. It does not accept a null parameter. This method will not return null.
	 * 
	 * @see NewJavaClassDataModelProvider#validate(String)
	 * 
	 * @param propertyName
	 * @return IStatus is property value valid?
	 */
	public IStatus validate(String propertyName) {
		IStatus result = Status.OK_STATUS;
		// Validate super class
		if (propertyName.equals(SUPERCLASS)) 
			return validateSuperClassName(getStringProperty(propertyName));
		// Validate init params
		if (propertyName.equals(INIT_PARAM))
			return validateInitParamList((List) getProperty(propertyName));
		// Validate servlet mappings
		if (propertyName.equals(URL_MAPPINGS))
			return validateURLMappingList((List) getProperty(propertyName));
		// Validate the servlet name in DD
		if (propertyName.equals(DISPLAY_NAME))
			return validateDisplayName(getStringProperty(propertyName));
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
		// Otherwise defer to super to validate the property
		return super.validate(propertyName);
	}
	
	/**
	 * Subclasses may extend this method to provide their own validation of the specified java
	 * classname. This implementation will ensure the class name is not set to Servlet and then will
	 * forward on to the NewJavaClassDataModel to validate the class name as valid java. This method
	 * does not accept null as a parameter. It will not return null. 
	 * It will check if the super class extends the javax.servlet.Servlet interface also.
	 * 
	 * @see NewServletClassDataModelProvider#validateExistingClass(boolean)
	 * @see NewJavaClassDataModelProvider#validateJavaClassName(String)
	 * 
	 * @param className
	 * @return IStatus is java classname valid?
	 */
	protected IStatus validateSuperClassName(String superclassName) {
		//If the servlet implements javax.servlet.Servlet, we do not need a super class
		ServletSupertypesValidator validator = new ServletSupertypesValidator(getDataModel());
		if (validator.isGenericServletSuperclass())
			return WTPCommonPlugin.OK_STATUS;
		
		// Check the super class as a java class
		IStatus status = null;
		if (superclassName.trim().length() > 0) {
			status = super.validate(SUPERCLASS);
			if (status.getSeverity() == IStatus.ERROR)
				return status;
		}
		
		if (!validator.isServletSuperclass())
			return WTPCommonPlugin.createErrorStatus(WebMessages.ERR_SERVLET_INTERFACE);
		
		return status;
	}

	/**
	 * This method is intended for internal use only. It will be used to validate the init params
	 * list to ensure there are not any duplicates. This method will accept a null paramter. It will
	 * not return null.
	 * 
	 * @see NewServletClassDataModelProvider#validate(String)
	 * 
	 * @param prop
	 * @return IStatus is init params list valid?
	 */
	private IStatus validateInitParamList(List prop) {
		if (prop != null && !prop.isEmpty()) {
			// Ensure there are not duplicate entries in the list
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.ERR_DUPLICATED_INIT_PARAMETER;
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		// Return OK
		return WTPCommonPlugin.OK_STATUS;
	}

	/**
	 * This method is intended for internal use only. This will validate the servlet mappings list
	 * and ensure there are not duplicate entries. It will accept a null parameter. It will not
	 * return null.
	 * 
	 * @see NewServletClassDataModelProvider#validate(String)
	 * 
	 * @param prop
	 * @return IStatus is servlet mapping list valid?
	 */
	private IStatus validateURLMappingList(List prop) {
		if (prop != null && !prop.isEmpty()) {
			// Ensure there are not duplicates in the mapping list
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.ERR_DUPLICATED_URL_MAPPING;
				return WTPCommonPlugin.createErrorStatus(msg);
			}
			String isValidValue = validateValue(prop);
			if (isValidValue != null && isValidValue.length() > 0) {
				NLS.bind(WebMessages.ERR_URL_PATTERN_INVALID, isValidValue);
				String resourceString = WebMessages.getResourceString(WebMessages.ERR_URL_PATTERN_INVALID, new String[]{isValidValue});
				return WTPCommonPlugin.createErrorStatus(resourceString);
			}
		} else {
			String msg = WebMessages.ERR_URL_MAPPING_LIST_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// Return OK
		return WTPCommonPlugin.OK_STATUS;
	}
	
	/**
 	 * This method is intended for internal use only. It provides a simple algorithm for detecting
 	 * if there are invalid pattern's value in a list. It will accept a null parameter.
 	 *
 	 * @see NewServletClassDataModelProvider#validateURLMappingList(List)
 	 *
 	 * @param input
 	 * @return String first invalid pattern's value?
 	 */
	private String validateValue(List prop) {
		if (prop == null) {
			return "";
		}
		int size = prop.size();
		for (int i = 0; i < size; i++) {
			String urlMappingValue = ((String[]) prop.get(i))[0];
			if (!UrlPattern.isValid(urlMappingValue))
				return urlMappingValue;
		}
		return "";
	}
	
	/**
	 * This method is intended for internal use only. It provides a simple algorithm for detecting
	 * if there are duplicate entries in a list. It will accept a null paramter. It will not return
	 * null.
	 * 
	 * @see NewServletClassDataModelProvider#validateInitParamList(List)
	 * @see NewServletClassDataModelProvider#validateURLMappingList(List)
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
	 * @see NewServletClassDataModelProvider#hasDuplicatesInStringArrayList(List)
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
	 * This method will return the list of servlet interfaces to be implemented for the new servlet
	 * java class. It will intialize the list using lazy initialization to the minimum interfaces
	 * required by the data model SERVLET_INTERFACES. This method will not return null.
	 * 
	 * @see INewServletClassDataModelProperties#SERVLET_INTERFACES
	 * 
	 * @return List of servlet interfaces to be implemented
	 */
	private List getServletInterfaces() {
		if (interfaceList == null) {
			interfaceList = new ArrayList();
			// Add minimum required list of servlet interfaces to be implemented
			for (int i = 0; i < SERVLET_INTERFACES.length; i++) {
				interfaceList.add(SERVLET_INTERFACES[i]);
			}
		}
		// Return interface list
		return interfaceList;
	}

	/**
	 * This method is intended for internal use only. This will validate whether the display name
	 * selected is a valid display name for the servlet in the specified web application. It will
	 * make sure the name is not empty and that it doesn't already exist in the web app. This method
	 * will accept null as a parameter. It will not return null.
	 * 
	 * @see NewServletClassDataModelProvider#validate(String)
	 * 
	 * @param prop
	 * @return IStatus is servlet display name valid?
	 */
	private IStatus validateDisplayName(String prop) {
		// Ensure the servlet display name is not null or empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = WebMessages.ERR_DISPLAY_NAME_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (getTargetProject() == null || getTargetComponent() == null)
			return WTPCommonPlugin.OK_STATUS;
		
		IModelProvider provider = ModelProviderManager.getModelProvider( getTargetProject() );
		Object mObj = provider.getModelObject();
		if( mObj instanceof org.eclipse.jst.j2ee.webapplication.WebApp ){
			org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp) mObj;

			List servlets = webApp.getServlets();
			boolean exists = false;
			// Ensure the display does not already exist in the web application
			if (servlets != null && !servlets.isEmpty()) {
				for (int i = 0; i < servlets.size(); i++) {
					String name = ((org.eclipse.jst.j2ee.webapplication.Servlet) servlets.get(i)).getServletName();
					if (prop.equals(name))
						exists = true;
				}
			}
			// If the servlet name already exists, throw an error
			if (exists) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_DISPLAY_NAME_EXIST, new String[]{prop});
				return WTPCommonPlugin.createErrorStatus(msg);
			}			
		}else if ( mObj instanceof org.eclipse.jst.javaee.web.WebApp){
			org.eclipse.jst.javaee.web.WebApp webApp= (org.eclipse.jst.javaee.web.WebApp) mObj;

			List servlets = webApp.getServlets();
			boolean exists = false;
			// Ensure the display does not already exist in the web application
			if (servlets != null && !servlets.isEmpty()) {
				for (int i = 0; i < servlets.size(); i++) {
					String name = ((org.eclipse.jst.javaee.web.Servlet) servlets.get(i)).getServletName();
					if (prop.equals(name))
						exists = true;
				}
			}
			// If the servlet name already exists, throw an error
			if (exists) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_DISPLAY_NAME_EXIST, new String[]{prop});
				return WTPCommonPlugin.createErrorStatus(msg);
			}			
		}
		
		// Otherwise, return OK
		return WTPCommonPlugin.OK_STATUS;
	}

	/**
	 * @return boolean should the default annotations be true?
	 */
	private static Boolean shouldDefaultAnnotations() {
		if (useAnnotations)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private boolean hasServletInterfaceToImplement(List newInterfacesList) {
		return newInterfacesList != null && newInterfacesList.contains(SERVLET_INTERFACES[0]);		
	}
}
