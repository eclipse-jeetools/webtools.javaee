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

public class NewFilterClassDataModelProvider extends NewJavaClassDataModelProvider implements INewFilterClassDataModelProperties {

	/**
	 * String array of the default, minimum required fully qualified Filter interfaces
	 */
	private final static String[] FILTER_INTERFACES = {"javax.servlet.Filter"}; //$NON-NLS-1$
    
    private final static String NON_ANNOTATED_TEMPLATE_DEFAULT = "filterNonAnnotated.javajet"; //$NON-NLS-1$

	/**
	 * The cache of all the interfaces the filter java class will implement.
	 */
	private List interfaceList;

	private static boolean useAnnotations = false;

	/**
	 * Subclasses may extend this method to provide their own default operation for this data model
	 * provider. This implementation uses the AddFilterOperation to drive the filter creation. It
	 * will not return null.
	 * 
	 * @see IDataModel#getDefaultOperation()
	 * 
	 * @return IDataModelOperation AddFilterOperation
	 */
	public IDataModelOperation getDefaultOperation() {
		return new AddFilterOperation(getDataModel());
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
		// Add filter specific properties defined in this data model
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(INIT);
		propertyNames.add(DESTROY);
		propertyNames.add(TO_STRING);
		propertyNames.add(DO_FILTER);
		propertyNames.add(INIT_PARAM);
        propertyNames.add(FILTER_MAPPINGS);
		propertyNames.add(USE_ANNOTATIONS);
		propertyNames.add(DISPLAY_NAME);
		propertyNames.add(DESCRIPTION);
		propertyNames.add(USE_EXISTING_CLASS);
		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own default values for any of the
	 * properties in the data model hierarchy. This method does not accept a null parameter. It may
	 * return null. This implementation sets annotation use to be true, and to generate a filter
	 * with doFilter.
	 * 
	 * @see NewJavaClassDataModelProvider#getDefaultProperty(String)
	 * @see IDataModelProvider#getDefaultProperty(String)
	 * 
	 * @param propertyName
	 * @return Object default value of property
	 */
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(DISPLAY_NAME)) {
			String className = getStringProperty(CLASS_NAME);
			int index = className.lastIndexOf("."); //$NON-NLS-1$
			className = className.substring(index+1);
			return className;
		}
		else if (propertyName.equals(USE_ANNOTATIONS))
			return shouldDefaultAnnotations();
        else if (propertyName.equals(DESTROY))
            return Boolean.TRUE;
		else if (propertyName.equals(DO_FILTER))
            return Boolean.TRUE;
        else if (propertyName.equals(INIT))
            return Boolean.TRUE;
		else if (propertyName.equals(FILTER_MAPPINGS))
			return getDefaultFilterMapping();
		else if (propertyName.equals(INTERFACES))
			return getFilterInterfaces();
		else if (propertyName.equals(SUPERCLASS))
			return "";
		else if (propertyName.equals(USE_EXISTING_CLASS))
			return Boolean.FALSE;
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * Returns the default Url Mapping depending upon the display name of the Filter
	 * 
	 * @return List containting the default Url Mapping
	 */
	private Object getDefaultFilterMapping() {
		List filterMappings = null;
		String text = (String) getProperty(DISPLAY_NAME);
		if (text != null) {
		    filterMappings = new ArrayList();
		    filterMappings.add(new FilterMappingItem(FilterMappingItem.URL_PATTERN, "/" + text)); //$NON-NLS-1$
		}
		return filterMappings;
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
			if (((Boolean)propertyValue).booleanValue())
				setProperty(USE_ANNOTATIONS,Boolean.FALSE);
			setProperty(JAVA_PACKAGE, null);
			setProperty(CLASS_NAME, null);
		}
		// Return whether property was set
		return result;
	}
	
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
	 * filter mappings, display name, and existing class fields specific to the filter java class
	 * creation. It does not accept a null parameter. This method will not return null.
	 * 
	 * @see NewJavaClassDataModelProvider#validate(String)
	 * 
	 * @param propertyName
	 * @return IStatus is property value valid?
	 */
	public IStatus validate(String propertyName) {
		IStatus result = Status.OK_STATUS;
		// If our default is the superclass, we know it is ok
		if (propertyName.equals(SUPERCLASS) && "".equals(getStringProperty(propertyName)))
			return WTPCommonPlugin.OK_STATUS;
		// Validate init params
		if (propertyName.equals(INIT_PARAM))
			return validateInitParamList((List) getProperty(propertyName));
        // Validate url pattern and servlet name mappings
        if (propertyName.equals(FILTER_MAPPINGS))
            return validateFilterMappingList((List) getProperty(FILTER_MAPPINGS));
		// Validate the filter name in DD
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
	 * This method is intended for internal use only. It will be used to validate the init params
	 * list to ensure there are not any duplicates. This method will accept a null paramter. It will
	 * not return null.
	 * 
	 * @see NewFilterClassDataModelProvider#validate(String)
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
     * This method is intended for internal use only. This will validate the filter 
     * mappings list and ensure there are not duplicate entries. It will accept 
     * a null parameter. It will not return null.
     * 
     * @see NewFilterClassDataModelProvider#validate(String)
     * 
     * @param prop
     * @return IStatus is filter mapping list valid?
     */
	private IStatus validateFilterMappingList(List prop) {
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
			String msg = WebMessages.ERR_FILTER_MAPPING_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// Return OK
		return WTPCommonPlugin.OK_STATUS;
	}
	
	/**
 	 * This method is intended for internal use only. It provides a simple algorithm for detecting
 	 * if there are invalid pattern's value in a list. It will accept a null parameter.
 	 *
 	 * @see NewFilterClassDataModelProvider#validateFilterMappingList(List)
 	 *
 	 * @param input
 	 * @return String first invalid pattern's value
 	 */
	private String validateValue(List prop) {
		if (prop == null) {
			return "";
		}
		int size = prop.size();
		for (int i = 0; i < size; i++) {
			IFilterMappingItem filterMappingValue = (IFilterMappingItem) prop.get(i);
			if (filterMappingValue.isUrlPatternType() && 
					!UrlPattern.isValid(filterMappingValue.getName()))
				return filterMappingValue.getName();
		}
		return "";
	}

	/**
	 * This method is intended for internal use only. It provides a simple algorithm for detecting
	 * if there are duplicate entries in a list. It will accept a null parameter. It will not return
	 * null.
	 * 
	 * @see NewFilterClassDataModelProvider#validateInitParamList(List)
	 * @see NewFilterClassDataModelProvider#validateURLMappingList(List)
	 * 
	 * @param input
	 * @return boolean are there dups in the list?
	 */
	private boolean hasDuplicatesInStringArrayList(List input) {
		// If list is null or empty return false
		if (input == null) return false;
		int n = input.size();
		boolean dup = false;
		// nested for loops to check each element to see if other elements are the same
		for (int i = 0; i < n; i++) {
		    Object object = input.get(i);
		    if (object instanceof IFilterMappingItem) {
		        IFilterMappingItem item = (IFilterMappingItem) object;
		        for (int j = i + 1; j < n; j++) {
		            IFilterMappingItem item2 = (IFilterMappingItem) input.get(j);
                    if (item.getName().equals(item2.getName()) && 
                    		item.getMappingType() == item2.getMappingType()) {
                        dup = true;
                        break;
                    }
                }
                if (dup) break;
		    } else {
		        String[] sArray1 = (String[]) object;
		        for (int j = i + 1; j < n; j++) {
		            String[] sArray2 = (String[]) input.get(j);
		            if (isTwoStringArraysEqual(sArray1, sArray2)) {
		                dup = true;
		                break;
		            }
		        }
		        if (dup) break;
		    }
		}
		// Return boolean status for duplicates
		return dup;
	}

    /**
	 * This method is intended for internal use only. This checks to see if the two string arrays
	 * are equal. If either of the arrays are null or empty, it returns false.
	 * 
	 * @see NewFilterClassDataModelProvider#hasDuplicatesInStringArrayList(List)
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
	 * This method will return the list of filter interfaces to be implemented for the new servlet
	 * java class. It will intialize the list using lazy initialization to the minimum interfaces
	 * required by the data model FILTER_INTERFACES. This method will not return null.
	 * 
	 * @see INewFilterClassDataModelProperties#FILTER_INTERFACES
	 * 
	 * @return List of servlet interfaces to be implemented
	 */
	private List getFilterInterfaces() {
		if (interfaceList == null) {
			interfaceList = new ArrayList();
			// Add minimum required list of servlet interfaces to be implemented
			for (int i = 0; i < FILTER_INTERFACES.length; i++) {
				interfaceList.add(FILTER_INTERFACES[i]);
			}
		}
		// Return interface list
		return interfaceList;
	}

	/**
	 * This method is intended for internal use only. This will validate whether the display name
	 * selected is a valid display name for the filter in the specified web application. It will
	 * make sure the name is not empty and that it doesn't already exist in the web app. This method
	 * will accept null as a parameter. It will not return null.
	 * 
	 * @see NewFilterClassDataModelProvider#validate(String)
	 * 
	 * @param prop
	 * @return IStatus is filter display name valid?
	 */
	private IStatus validateDisplayName(String prop) {
		// Ensure the filter display name is not null or empty
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

			List filters = webApp.getFilters();
			boolean exists = false;
			// Ensure the display does not already exist in the web application
			if (filters != null && !filters.isEmpty()) {
				for (int i = 0; i < filters.size(); i++) {
					String name = ((org.eclipse.jst.j2ee.webapplication.Filter) filters.get(i)).getName();
					if (prop.equals(name))
						exists = true;
				}
			}
			// If the filter name already exists, throw an error
			if (exists) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_DISPLAY_NAME_EXIST, new String[]{prop});
				return WTPCommonPlugin.createErrorStatus(msg);
			}			
		} else if ( mObj instanceof org.eclipse.jst.javaee.web.WebApp){
			org.eclipse.jst.javaee.web.WebApp webApp= (org.eclipse.jst.javaee.web.WebApp) mObj;

			List filters = webApp.getFilters();
			boolean exists = false;
			// Ensure the display does not already exist in the web application
			if (filters != null && !filters.isEmpty()) {
				for (int i = 0; i < filters.size(); i++) {
					String name = ((org.eclipse.jst.javaee.web.Filter) filters.get(i)).getFilterName();
					if (prop.equals(name))
						exists = true;
				}
			}
			// If the filter name already exists, throw an error
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
	
}
