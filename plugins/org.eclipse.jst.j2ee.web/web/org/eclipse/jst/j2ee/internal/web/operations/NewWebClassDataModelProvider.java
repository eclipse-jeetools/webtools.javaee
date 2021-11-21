/*******************************************************************************
 * Copyright (c) 2007, 2021 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel.USE_ANNOTATIONS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CONSTRUCTOR;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.MODIFIER_ABSTRACT;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.MODIFIER_PUBLIC;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SOURCE_FOLDER;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SUPERCLASS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.DESCRIPTION;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.DISPLAY_NAME;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.JAVA_EE_VERSION;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.REGISTER_IN_WEB_XML;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.USE_EXISTING_CLASS;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.wst.common.componentcore.internal.util.FacetedProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;

/**
 * The NewWebClassDataModelProvider is a subclass of
 * NewJavaClassDataModelProvider and follows the IDataModel Operation and Wizard
 * frameworks.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * 
 * This data model provider is a subclass of the NewJavaClassDataModelProvider,
 * which stores base properties necessary in the creation of a default java
 * class.
 * @see org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider
 * 
 * The NewWebClassDataModelProvider provides more specific properties for java
 * class creation that are required in creating a web artifact java class. The
 * data model provider is used to store these values for the
 * AddWebClassOperation.
 * @see org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties
 *      That operation will create the web artifact java class based on the
 *      settings defined here in the data model.
 * @see org.eclipse.jst.j2ee.internal.web.operations.AddWebClassOperation
 * 
 * This data model properties implements the IAnnotationsDataModel to get the
 * USE_ANNOTATIONS property for determining whether or not to generate an
 * annotated java class.
 * @see org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel
 * 
 * Clients can subclass this data model provider to cache and provide their own
 * specific attributes. They should also provide their own validation methods,
 * properties interface, and default values for the properties they add.
 * 
 */
public abstract class NewWebClassDataModelProvider extends NewJavaClassDataModelProvider  {
	
	/**
	 * The fully qualified default superclass.
	 */
	private final static String DEFAULT_SUPERCLASS = ""; //$NON-NLS-1$ 

	/**
	 * The cache of all the interfaces the web artifact java class will
	 * implement.
	 */
	protected List<String> interfaceList;
	protected List<String> jakartaInterfaceList;
	
	private String classNameCache = ""; //$NON-NLS-1$
	private String existingClassNameCache = ""; //$NON-NLS-1$

	private static boolean useAnnotations = false;
	
	/**
	 * Subclasses may extend this method to provide their own determination of
	 * whether or not certain properties should be disabled or enabled. This
	 * method does not accept null parameter. It will not return null. This
	 * implementation makes sure annotation support is only allowed on web
	 * projects of J2EE version 1.3 or higher.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#isPropertyEnabled(String)
	 * @see IAnnotationsDataModel#USE_ANNOTATIONS
	 * 
	 * @param propertyName
	 * @return boolean should property be enabled?
	 */
	@Override
	public boolean isPropertyEnabled(String propertyName) {
		// Annotations should only be enabled on a valid j2ee project of version 1.3 or higher
		if (USE_ANNOTATIONS.equals(propertyName)) {
			return !getBooleanProperty(USE_EXISTING_CLASS) && isAnnotationsSupported();
		} else if (MODIFIER_PUBLIC.equals(propertyName)) {
			return false;
		} else if (MODIFIER_ABSTRACT.equals(propertyName)) {
			return false;
		} else if (CONSTRUCTOR.equals(propertyName)) {
			return hasSuperClass();
		}
		// Otherwise return super implementation
		return super.isPropertyEnabled(propertyName);
	}

	/**
	 * Subclasses may extend this method to add their own data model's
	 * properties as valid base properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	@Override
	public Set getPropertyNames() {
		// Add web artifact specific properties defined in this data model
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(REGISTER_IN_WEB_XML);
		propertyNames.add(DISPLAY_NAME);
		propertyNames.add(DESCRIPTION);
		propertyNames.add(USE_EXISTING_CLASS);
		propertyNames.add(USE_ANNOTATIONS);
		propertyNames.add(JAVA_EE_VERSION);
		
		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own default values for
	 * any of the properties in the data model hierarchy. This method does not
	 * accept a null parameter. It may return null. This implementation sets
	 * annotation use to be true.
	 * 
	 * @see NewJavaClassDataModelProvider#getDefaultProperty(String)
	 * @see IDataModelProvider#getDefaultProperty(String)
	 * 
	 * @param propertyName
	 * @return Object default value of property
	 */
	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(DISPLAY_NAME))
			return Signature.getSimpleName(getStringProperty(CLASS_NAME));
		else if (propertyName.equals(USE_ANNOTATIONS))
			return shouldDefaultAnnotations();
		else if (propertyName.equals(SUPERCLASS))
			return DEFAULT_SUPERCLASS;
		else if (propertyName.equals(USE_EXISTING_CLASS))
			return Boolean.FALSE;
		else if (propertyName.equals(CONSTRUCTOR))
			return hasSuperClass();
		else if (propertyName.equals(REGISTER_IN_WEB_XML)){
			return !isJavaEE6Project();
		} else if (JAVA_EE_VERSION.equals(propertyName)){
			return getJavaEEVersion();
		}
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	@SuppressWarnings("restriction")
	public boolean isJavaEE6Project() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getStringProperty(PROJECT_NAME));
		if (project != null && project.isAccessible()){
			try {
				// check to see if it is Java EE 6 or later
				return (FacetedProjectFramework.hasProjectFacet(project, WebFacetUtils.WEB_FACET.getId(), WebFacetUtils.WEB_30.getVersionString())
						|| FacetedProjectFramework.hasProjectFacet(project, WebFacetUtils.WEB_FACET.getId(), WebFacetUtils.WEB_31.getVersionString())
						|| FacetedProjectFramework.hasProjectFacet(project, WebFacetUtils.WEB_FACET.getId(), WebFacetUtils.WEB_40.getVersionString())
						|| FacetedProjectFramework.hasProjectFacet(project, WebFacetUtils.WEBFRAGMENT_FACET.getId(), WebFacetUtils.WEBFRAGMENT_30.getVersionString())
						|| FacetedProjectFramework.hasProjectFacet(project, WebFacetUtils.WEBFRAGMENT_FACET.getId(), WebFacetUtils.WEBFRAGMENT_31.getVersionString())
						|| FacetedProjectFramework.hasProjectFacet(project, WebFacetUtils.WEBFRAGMENT_FACET.getId(), WebFacetUtils.WEBFRAGMENT_40.getVersionString()));
			} catch (CoreException e) {
				WebPlugin.log(e);
			}
		}
		return false;
	}
	

	/**
	 * @return <code>True</code> when generated classes should refer to types in the <code>jakarta.*</code>
	 * packages rather than the older <code>javax.*</code> packages.
	 */
	protected boolean projectUsesJakartaPackages() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getStringProperty(PROJECT_NAME));
		if (project != null && project.isAccessible()) {
			// long term, the default when otehrwise not determinable should be the newer one
			try {
				IJavaProject javaProject = JavaCore.create(project);
				if (javaProject != null && javaProject.exists()) {
					if (javaProject.findType(IServletConstants.QUALIFIED_JAKARTA_HTTP_SERVLET) != null) {
						return true;
					}
				}
			}
			catch (CoreException e) {
				WTPCommonPlugin.logError(e);
			}
			try {
				// check to see if it is Jakarta Servlet 5 or newer
				if (FacetedProjectFramework.hasProjectFacet(project, WebFacetUtils.WEB_FACET.getId(), WebFacetUtils.WEB_50.getVersionString())) {
					return true;
				}
			}
			catch (CoreException e) {
				WebPlugin.log(e);
			}
		}
		return false;
	}
	
	@SuppressWarnings("restriction")
	public String getJavaEEVersion() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getStringProperty(PROJECT_NAME));
		String id = null;
		if(JavaEEProjectUtilities.isDynamicWebProject(project)){
			id = WebFacetUtils.WEB_FACET.getId();
		}else {
			id = WebFacetUtils.WEBFRAGMENT_FACET.getId();
		}
		return FacetedProjectUtilities.getProjectFacetVersion(project, id).getVersionString();
	}

	/**
	 * Subclasses may extend this method to add their own specific behavior when
	 * a certain property in the data model hierarchy is set. this method does
	 * not accept null for the property name, but it will for propertyValue. It
	 * will not return null. It will return false if the set fails. This
	 * implementation verifies the display name is set to the class name, that
	 * the annotations is disabled/enabled properly, and that the target project
	 * name is determined from the source folder setting.
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
		// If annotations is changed, notify an enablement change
		if (propertyName.equals(USE_ANNOTATIONS)) {
			useAnnotations = ((Boolean) propertyValue).booleanValue();
			if (useAnnotations && !isAnnotationsSupported())
				return true;
			model.notifyPropertyChange(USE_ANNOTATIONS, IDataModel.ENABLE_CHG);
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
		if (propertyName.equals(CLASS_NAME) && !model.isPropertySet(DISPLAY_NAME)) {
			model.notifyPropertyChange(DISPLAY_NAME, IDataModel.DEFAULT_CHG);
		}
		// After the property is set, if project changed, update the nature and the annotations
		// enablement
		if (propertyName.equals(COMPONENT_NAME)) {
			model.notifyPropertyChange(USE_ANNOTATIONS, IDataModel.ENABLE_CHG);
		}
		// After property is set, if annotations is set to true, update its value based on the new
		// level of the project
		if (getBooleanProperty(USE_ANNOTATIONS)) {
			if (!isAnnotationsSupported())
				setBooleanProperty(USE_ANNOTATIONS, false);
		}
		if (propertyName.equals(USE_EXISTING_CLASS)) {
			model.notifyPropertyChange(USE_ANNOTATIONS, IDataModel.ENABLE_CHG);
			
			if (((Boolean) propertyValue).booleanValue()) {
				classNameCache = getStringProperty(CLASS_NAME);
				setProperty(CLASS_NAME, existingClassNameCache);
			} else {
				existingClassNameCache = getStringProperty(CLASS_NAME);
				setProperty(CLASS_NAME, classNameCache);
			}
		}
		// if super class is changed, notify the constructor checkbox to update
		// its enabled state
		if (SUPERCLASS.equals(propertyName)) {
			model.notifyPropertyChange(CONSTRUCTOR, IDataModel.ENABLE_CHG);
			if (!hasSuperClass()) {
				model.setProperty(CONSTRUCTOR, null);
			}
			model.notifyPropertyChange(CONSTRUCTOR, IDataModel.DEFAULT_CHG);
		}
		
		// Return whether property was set
		return result;
	}

	/**
	 * This method is used to determine if annotations should try to enable
	 * based on workspace settings
	 * 
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
	 * This method checks to see if valid annotation providers exist and if
	 * valid project version levels exist.
	 * 
	 * @return should annotations be supported for this project in this
	 *         workspace
	 */
	protected boolean isAnnotationsSupported() {
		//TODO add this check back in for defect 146696
//		if (!isAnnotationProviderDefined())
//			return false;
		if (!model.isPropertySet(PROJECT_NAME))
			return true;
		if (getStringProperty(PROJECT_NAME).equals("")) //$NON-NLS-1$
			return true;
		IProject project = ProjectUtilities.getProject(getStringProperty(PROJECT_NAME));
		String moduleName = getStringProperty(COMPONENT_NAME);
		if (project == null || moduleName == null || moduleName.equals(""))return true; //$NON-NLS-1$
		int j2eeVersion = J2EEVersionUtil.convertVersionStringToInt(J2EEProjectUtilities.getJ2EEProjectVersion(project));
		
		return j2eeVersion > J2EEVersionConstants.VERSION_1_2;
		
	}

	/**
	 * Subclasses may extend this method to provide their own validation on any
	 * of the valid data model properties in the hierarchy. It does not accept a
	 * null parameter. This method will not return null.
	 * 
	 * @see NewJavaClassDataModelProvider#validate(String)
	 * 
	 * @param propertyName
	 * @return IStatus is property value valid?
	 */
	@Override
	public IStatus validate(String propertyName) {
		if (propertyName.equals(SUPERCLASS)) {
			// If our default is the superclass, we know it is ok
			if (getStringProperty(propertyName).equals(DEFAULT_SUPERCLASS))			
				return WTPCommonPlugin.OK_STATUS;
		}
		
		if (propertyName.equals(CLASS_NAME)) {
			if (getStringProperty(propertyName).length() !=0 && getBooleanProperty(USE_EXISTING_CLASS)) {
				return WTPCommonPlugin.OK_STATUS;
			}
		}
		
		// Otherwise defer to super to validate the property
		return super.validate(propertyName);
	}
	
	/**
	 * This method is intended for internal use only. It provides a simple
	 * algorithm for detecting if there are duplicate entries in a list. It will
	 * accept a null parameter. It will not return null.
	 * 
	 * @see NewWebClassDataModelProvider#validateInitParamList(List)
	 * @see NewWebClassDataModelProvider#validateURLMappingList(List)
	 * 
	 * @param input
	 * @return boolean are there dups in the list?
	 */
	protected boolean hasDuplicatesInStringArrayList(List input) {
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
	 * This method is intended for internal use only. This checks to see if the
	 * two string arrays are equal. If either of the arrays are null or empty,
	 * it returns false.
	 * 
	 * @see NewWebClassDataModelProvider#hasDuplicatesInStringArrayList(List)
	 * 
	 * @param sArray1
	 * @param sArray2
	 * @return boolean are Arrays equal?
	 */
	protected boolean isTwoStringArraysEqual(String[] sArray1, String[] sArray2) {
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

	protected boolean hasSuperClass() {
		String superClass = model.getStringProperty(SUPERCLASS);
		return (superClass == null) ? false : superClass.trim().length() > 0;
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
