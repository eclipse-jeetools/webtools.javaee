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
 * Created on May 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPPropertyDescriptor;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.emf.workbench.nature.EMFNature;

/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class J2EEUtilityJarListImportDataModel extends WTPOperationDataModel {

	/**
	 * Required. The EAR Project to which Utility Jars should be added.
	 */
	public static final String EAR_PROJECT = J2EEUtilityJarImportDataModel.EAR_PROJECT;

	/**
	 * Optional. Boolean Property. True indicates to create a Project Can be used with
	 * LINK_IMPORT="true|"false" or BINARY_IMPORT="true|false" The default value is true.
	 */
	public static final String CREATE_PROJECT = "J2EEUtilityJarListImportDataModel.CREATE_PROJECT"; //$NON-NLS-1$

	/**
	 * Optional. Boolean property. True indicates to import the jar as a linked project Can be
	 * specified with BINARY_IMPORT="true|false" and/or CREATE_PROJECT="true|false" The default
	 * value is false.
	 */
	public static final String LINK_IMPORT = "J2EEUtilityJarListImportDataModel.LINK_IMPORT"; //$NON-NLS-1$

	/**
	 * Optional. Boolean Property. True indicates to create a Project Can be used with
	 * LINK_IMPORT="true|"false" or BINARY_IMPORT="true|false" The default value is true.
	 */
	public static final String CREATE_LINKED_PROJECT = "J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT"; //$NON-NLS-1$

	/**
	 * Optional. Boolean property. True indicates to import the jar as a linked project Can be
	 * specified with BINARY_IMPORT="true|false" and/or CREATE_PROJECT="true|false" The default
	 * value is false.
	 */
	public static final String COPY = "J2EEUtilityJarListImportDataModel.COPY"; //$NON-NLS-1$

	/**
	 * Optional. Boolean property. True indicates to import the jar as a binary project Can be
	 * specified with LINK_IMPORT="true|false" and/or CREATE_PROJECT="true|false" The default value
	 * is false.
	 */
	public static final String BINARY_IMPORT = "J2EEUtilityJarListImportDataModel.BINARY_IMPORT"; //$NON-NLS-1$

	/**
	 * Required. A java.util.List of fully qualified file names for each Utility Jar that should be
	 * imported.
	 */
	public static final String UTILITY_JAR_LIST = "J2EEUtilityJarListImportDataModel.UTILITY_JAR_LIST"; //$NON-NLS-1$

	public static final String AVAILABLE_JARS_DIRECTORY = "J2EEUtilityJarListImportDataModel.AVAILABLE_JARS_DIRECTORY"; //$NON-NLS-1$

	public static final String PROJECT_ROOT = "J2EEUtilityJarListImportDataModel.PROJECT_ROOT"; //$NON-NLS-1$

	public static final String OVERRIDE_PROJECT_ROOT = "J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT"; //$NON-NLS-1$

	public static final String OVERWRITE_IF_NECESSARY = "J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY"; //$NON-NLS-1$

	public static final String CREATE_LINKED_PATH_VARIABLE = "J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH"; //$NON-NLS-1$

	public static final String LINKED_PATH_VARIABLE = "J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE"; //$NON-NLS-1$	

	private static final Object[] EMPTY_ARRAY = new Object[0];

	public J2EEUtilityJarListImportDataModel() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(EAR_PROJECT);
		addValidBaseProperty(CREATE_PROJECT);
		addValidBaseProperty(LINK_IMPORT);
		addValidBaseProperty(CREATE_LINKED_PROJECT);
		addValidBaseProperty(COPY);
		addValidBaseProperty(BINARY_IMPORT);
		addValidBaseProperty(UTILITY_JAR_LIST);
		addValidBaseProperty(AVAILABLE_JARS_DIRECTORY);
		addValidBaseProperty(PROJECT_ROOT);
		addValidBaseProperty(OVERRIDE_PROJECT_ROOT);
		addValidBaseProperty(OVERWRITE_IF_NECESSARY);
		addValidBaseProperty(CREATE_LINKED_PATH_VARIABLE);
		addValidBaseProperty(LINKED_PATH_VARIABLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {

		return new J2EEUtilityJarListImportOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {

		boolean result = super.doSetProperty(propertyName, propertyValue);

		if (AVAILABLE_JARS_DIRECTORY.equals(propertyName)) {
			setProperty(UTILITY_JAR_LIST, EMPTY_ARRAY);
		} else if (UTILITY_JAR_LIST.equals(propertyName)) {
			if (propertyValue == null)
				setProperty(UTILITY_JAR_LIST, EMPTY_ARRAY);
			else
				super.doSetProperty(UTILITY_JAR_LIST, propertyValue);

		} else if (PROJECT_ROOT.equals(propertyName)) {
			if (propertyValue == null || ((String) propertyValue).length() == 0)
				setBooleanProperty(OVERRIDE_PROJECT_ROOT, false);
			else
				setBooleanProperty(OVERRIDE_PROJECT_ROOT, true);

		} else if (J2EEUtilityJarListImportDataModel.CREATE_PROJECT.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT)) {

			setBooleanProperty(LINK_IMPORT, false);
			setBooleanProperty(CREATE_LINKED_PROJECT, false);
			setBooleanProperty(COPY, false);

			notifyEnablementChange(BINARY_IMPORT);
			notifyEnablementChange(OVERRIDE_PROJECT_ROOT);
			notifyEnablementChange(PROJECT_ROOT);
			notifyEnablementChange(CREATE_LINKED_PATH_VARIABLE);
			notifyEnablementChange(LINKED_PATH_VARIABLE);

		} else if (J2EEUtilityJarListImportDataModel.LINK_IMPORT.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModel.LINK_IMPORT)) {

			setBooleanProperty(CREATE_PROJECT, false);
			setBooleanProperty(CREATE_LINKED_PROJECT, false);
			setBooleanProperty(COPY, false);

			notifyEnablementChange(BINARY_IMPORT);
			notifyEnablementChange(OVERRIDE_PROJECT_ROOT);
			notifyEnablementChange(PROJECT_ROOT);
			notifyEnablementChange(CREATE_LINKED_PATH_VARIABLE);
			notifyEnablementChange(LINKED_PATH_VARIABLE);

		} else if (J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT)) {

			setBooleanProperty(LINK_IMPORT, false);
			setBooleanProperty(CREATE_PROJECT, false);
			setBooleanProperty(COPY, false);

			notifyEnablementChange(BINARY_IMPORT);
			notifyEnablementChange(OVERRIDE_PROJECT_ROOT);
			notifyEnablementChange(PROJECT_ROOT);
			notifyEnablementChange(CREATE_LINKED_PATH_VARIABLE);
			notifyEnablementChange(LINKED_PATH_VARIABLE);

		} else if (J2EEUtilityJarListImportDataModel.COPY.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModel.COPY)) {

			setBooleanProperty(CREATE_PROJECT, false);
			setBooleanProperty(LINK_IMPORT, false);
			setBooleanProperty(CREATE_LINKED_PROJECT, false);

			notifyEnablementChange(BINARY_IMPORT);
			notifyEnablementChange(OVERRIDE_PROJECT_ROOT);
			notifyEnablementChange(PROJECT_ROOT);
			notifyEnablementChange(CREATE_LINKED_PATH_VARIABLE);
			notifyEnablementChange(LINKED_PATH_VARIABLE);


		} else if (J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT.equals(propertyName)) {
			notifyEnablementChange(PROJECT_ROOT);
		} else if (J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH_VARIABLE.equals(propertyName)) {

			if (isLinkedPathVariableInvalid())
				setProperty(J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE, "TEAM_SHARED_DIRECTORY"); //$NON-NLS-1$

			notifyEnablementChange(J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE);
			notifyEnablementChange(AVAILABLE_JARS_DIRECTORY);

		} else if (J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE.equals(propertyName)) {
			// will only set if necessary
			setProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH_VARIABLE, Boolean.TRUE);

			if (linkedPathExists()) {
				String linkedPathVariable = getStringProperty(J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE);
				IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
				IPath availableJarsPath = manager.getValue(linkedPathVariable);
				setProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH_VARIABLE, Boolean.valueOf(availableJarsPath != null));

				String availableJarsPathString = availableJarsPath.toOSString();
				if (availableJarsPathString != null && availableJarsPathString.length() > 0)
					setProperty(J2EEUtilityJarListImportDataModel.AVAILABLE_JARS_DIRECTORY, availableJarsPathString);
			}

			notifyEnablementChange(AVAILABLE_JARS_DIRECTORY);
		}
		return result;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (J2EEUtilityJarListImportDataModel.BINARY_IMPORT.equals(propertyName)) {
			return Boolean.valueOf(getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT) || getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT));
		} else if (J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT.equals(propertyName)) {
			return Boolean.valueOf(getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT) || getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT));
		} else if (J2EEUtilityJarListImportDataModel.PROJECT_ROOT.equals(propertyName)) {
			return Boolean.valueOf((getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT) || getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT)) && getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT));
		} else if (J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH_VARIABLE.equals(propertyName)) {
			return Boolean.valueOf((getBooleanProperty(J2EEUtilityJarListImportDataModel.LINK_IMPORT) || getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT)));
		} else if (J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE.equals(propertyName)) {
			return Boolean.valueOf(getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH_VARIABLE));
		} else if (J2EEUtilityJarListImportDataModel.AVAILABLE_JARS_DIRECTORY.equals(propertyName)) {
			boolean createPath = getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH_VARIABLE);
			return Boolean.valueOf(!createPath || !linkedPathExists());
		}
		return super.basicIsEnabled(propertyName);
	}

	private boolean linkedPathExists() {
		String linkedPathVariable = getStringProperty(J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE);
		if (linkedPathVariable == null || linkedPathVariable.trim().length() == 0)
			return false;

		IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
		IPath availableJarsPath = manager.getValue(linkedPathVariable);
		return availableJarsPath != null;
	}

	private boolean isLinkedPathVariableInvalid() {
		boolean createPath = getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH_VARIABLE);
		String linkedPathVariable = getStringProperty(J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE);
		return (createPath && (linkedPathVariable == null || linkedPathVariable.trim().length() == 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (CREATE_PROJECT.equals(propertyName))
			return Boolean.TRUE;
		else if (LINK_IMPORT.equals(propertyName))
			return Boolean.FALSE;
		else if (COPY.equals(propertyName))
			return Boolean.FALSE;
		else if (CREATE_LINKED_PROJECT.equals(propertyName))
			return Boolean.FALSE;
		else if (CREATE_LINKED_PATH_VARIABLE.equals(propertyName))
			return Boolean.FALSE;
		else if (OVERRIDE_PROJECT_ROOT.equals(propertyName))
			return Boolean.FALSE;
		else if (BINARY_IMPORT.equals(propertyName))
			return Boolean.FALSE;
		else if (PROJECT_ROOT.equals(propertyName))
			return ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
		else if (EAR_PROJECT.equals(propertyName))
			return ""; //$NON-NLS-1$
		else if (AVAILABLE_JARS_DIRECTORY.equals(propertyName))
			return ""; //$NON-NLS-1$
		else if (LINKED_PATH_VARIABLE.equals(propertyName))
			return ""; //$NON-NLS-1$
		else if (UTILITY_JAR_LIST.equals(propertyName))
			return EMPTY_ARRAY;
		else
			return super.getDefaultProperty(propertyName);
	}

	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
		if (EAR_PROJECT.equals(propertyName)) {
			return WTPPropertyDescriptor.createDescriptors(getValidProjectNames());
		} else if (LINKED_PATH_VARIABLE.equals(propertyName)) {
			IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
			return WTPPropertyDescriptor.createDescriptors(manager.getPathVariableNames());
		} else
			return super.doGetValidPropertyDescriptors(propertyName);
	}

	/**
	 * Populate the resource name combo with connector projects that are not encrypted.
	 */
	protected Object[] getValidProjectNames() {
		List projects = Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects());
		List projectsWithNature = new ArrayList();

		for (int i = 0; i < projects.size(); i++) {
			IProject project = (IProject) projects.get(i);
			if (EMFNature.hasRuntime(project, IEARNatureConstants.NATURE_ID) && project.isOpen()) {
				projectsWithNature.add(project.getFullPath().toString());
			}
		}

		return ProjectUtilities.getProjectNamesWithoutForwardSlash((String[]) projectsWithNature.toArray(new String[projectsWithNature.size()]));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (EAR_PROJECT.equals(propertyName) /* && isSet(EAR_PROJECT) */) {
			String earProjectName = getStringProperty(EAR_PROJECT);
			if (earProjectName != null && earProjectName.length() > 0) {
				IProject earProject = ResourcesPlugin.getWorkspace().getRoot().getProject(earProjectName);
				try {
					if (!earProject.isAccessible() || !earProject.hasNature(IEARNatureConstants.NATURE_ID))
						return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.getString("J2EEUtilityJarListImportDataModel_Specify_Valid_Project"), null); //$NON-NLS-1$
					return Status.OK_STATUS;
				} catch (CoreException e) {
					return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.getString("J2EEUtilityJarListImportDataModel_Specify_Valid_Project"), e); //$NON-NLS-1$
				}
			}
			return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.getString("J2EEUtilityJarListImportDataModel_Specify_Valid_Project"), null); //$NON-NLS-1$

		} else if (UTILITY_JAR_LIST.equals(propertyName) || J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY.equals(propertyName)) {

			Object[] list = (Object[]) getProperty(UTILITY_JAR_LIST);
			if (list == null || list.length == 0)
				return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.getString("J2EEUtilityJarListImportDataModel_Select_Jar"), null); //$NON-NLS-1$

			/* return validateExistingProjects(); */

			return Status.OK_STATUS;
		} else if (LINKED_PATH_VARIABLE.equals(propertyName)) {
			if (isLinkedPathVariableInvalid())
				return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.getString("J2EEUtilityJarListImportDataModel_Specify_Linked_Path"), null); //$NON-NLS-1$
		} else if (PROJECT_ROOT.equals(propertyName)) {
			return validateProjectRoot();
		}
		return super.doValidateProperty(propertyName);
	}

	private IStatus validateProjectRoot() {
		if (isSet(PROJECT_ROOT) && getBooleanProperty(OVERRIDE_PROJECT_ROOT)) {
			String loc = (String) getProperty(PROJECT_ROOT);
			File file = new File(loc);
			if (!file.canWrite() || !file.isDirectory())
				return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.getString("J2EEUtilityJarListImportDataModel.0"), null); //$NON-NLS-1$
		}
		return OK_STATUS;
	}

	//	private IStatus validateExistingProjects() {
	//		boolean createProject = (getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT)
	// || getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT));
	//		boolean overwrite =
	// getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY);
	//		if (createProject && !overwrite) {
	//			File jarFile = null;
	//			String nameWithoutJar = null;
	//			Object[] selectedJars = (Object[]) getProperty(UTILITY_JAR_LIST);
	//			for (int i = 0; i < selectedJars.length; i++) {
	//				jarFile = (File) selectedJars[i];
	//				int start = jarFile.getName().indexOf(".jar");
	//				nameWithoutJar = jarFile.getName().substring(0, start);
	//				if (ProjectUtilities.getProject(nameWithoutJar).exists())
	//					return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, "Existing projects with the derived
	// names already exist (\"{0}\").", null); // {0}
	//				// nameWithoutJar
	//
	//			}
	//		}
	//		return Status.OK_STATUS;
	//	}

}