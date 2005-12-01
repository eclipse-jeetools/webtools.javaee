/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEUtilityJarListImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class J2EEUtilityJarListImportDataModelProvider extends AbstractDataModelProvider implements IJ2EEUtilityJarListImportDataModelProperties {



	private static final Object[] EMPTY_ARRAY = new Object[0];

	public J2EEUtilityJarListImportDataModelProvider() {
		super();
	}

	public Set getPropertyNames(){
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EAR_PROJECT);
		propertyNames.add(CREATE_PROJECT);
		propertyNames.add(LINK_IMPORT);
		propertyNames.add(CREATE_LINKED_PROJECT);
		propertyNames.add(COPY);
		propertyNames.add(BINARY_IMPORT);
		propertyNames.add(UTILITY_JAR_LIST);
		propertyNames.add(AVAILABLE_JARS_DIRECTORY);
		propertyNames.add(PROJECT_ROOT);
		propertyNames.add(OVERRIDE_PROJECT_ROOT);
		propertyNames.add(OVERWRITE_IF_NECESSARY);
		propertyNames.add(CREATE_LINKED_PATH_VARIABLE);
		propertyNames.add(LINKED_PATH_VARIABLE);
		return propertyNames;
	}

	public boolean propertySet(String propertyName, Object propertyValue) {

		boolean notify = super.propertySet(propertyName, propertyValue);

		if (AVAILABLE_JARS_DIRECTORY.equals(propertyName)) {
			setProperty(UTILITY_JAR_LIST, EMPTY_ARRAY);
		} else if (UTILITY_JAR_LIST.equals(propertyName)) {
			if (propertyValue == null)
				setProperty(UTILITY_JAR_LIST, EMPTY_ARRAY);
			else
				model.setProperty(UTILITY_JAR_LIST, propertyValue);

		} else if (PROJECT_ROOT.equals(propertyName)) {
			if (propertyValue == null || ((String) propertyValue).length() == 0)
				setBooleanProperty(OVERRIDE_PROJECT_ROOT, false);
			else
				setBooleanProperty(OVERRIDE_PROJECT_ROOT, true);

		} else if (J2EEUtilityJarListImportDataModelProvider.CREATE_PROJECT.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_PROJECT)) {

			setBooleanProperty(LINK_IMPORT, false);
			setBooleanProperty(CREATE_LINKED_PROJECT, false);
			setBooleanProperty(COPY, false);

			model.notifyPropertyChange(BINARY_IMPORT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(OVERRIDE_PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(CREATE_LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);

		} else if (J2EEUtilityJarListImportDataModelProvider.LINK_IMPORT.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.LINK_IMPORT)) {

			setBooleanProperty(CREATE_PROJECT, false);
			setBooleanProperty(CREATE_LINKED_PROJECT, false);
			setBooleanProperty(COPY, false);

			model.notifyPropertyChange(BINARY_IMPORT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(OVERRIDE_PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(CREATE_LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);

		} else if (J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PROJECT.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PROJECT)) {

			setBooleanProperty(LINK_IMPORT, false);
			setBooleanProperty(CREATE_PROJECT, false);
			setBooleanProperty(COPY, false);

			model.notifyPropertyChange(BINARY_IMPORT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(OVERRIDE_PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(CREATE_LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);

		} else if (J2EEUtilityJarListImportDataModelProvider.COPY.equals(propertyName) && getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.COPY)) {

			setBooleanProperty(CREATE_PROJECT, false);
			setBooleanProperty(LINK_IMPORT, false);
			setBooleanProperty(CREATE_LINKED_PROJECT, false);

			model.notifyPropertyChange(BINARY_IMPORT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(OVERRIDE_PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(PROJECT_ROOT, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(CREATE_LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);


		} else if (J2EEUtilityJarListImportDataModelProvider.OVERRIDE_PROJECT_ROOT.equals(propertyName)) {
			model.notifyPropertyChange(PROJECT_ROOT, IDataModel.ENABLE_CHG);
		} else if (J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PATH_VARIABLE.equals(propertyName)) {

			if (isLinkedPathVariableInvalid())
				setProperty(J2EEUtilityJarListImportDataModelProvider.LINKED_PATH_VARIABLE, "TEAM_SHARED_DIRECTORY"); //$NON-NLS-1$

			model.notifyPropertyChange(J2EEUtilityJarListImportDataModelProvider.LINKED_PATH_VARIABLE, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(AVAILABLE_JARS_DIRECTORY, IDataModel.ENABLE_CHG);

		} else if (J2EEUtilityJarListImportDataModelProvider.LINKED_PATH_VARIABLE.equals(propertyName)) {
			// will only set if necessary
			setProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PATH_VARIABLE, Boolean.TRUE);

			if (linkedPathExists()) {
				String linkedPathVariable = getStringProperty(J2EEUtilityJarListImportDataModelProvider.LINKED_PATH_VARIABLE);
				IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
				IPath availableJarsPath = manager.getValue(linkedPathVariable);
				setProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PATH_VARIABLE, Boolean.valueOf(availableJarsPath != null));

				String availableJarsPathString = availableJarsPath.toOSString();
				if (availableJarsPathString != null && availableJarsPathString.length() > 0)
					setProperty(J2EEUtilityJarListImportDataModelProvider.AVAILABLE_JARS_DIRECTORY, availableJarsPathString);
			}

			model.notifyPropertyChange(AVAILABLE_JARS_DIRECTORY, IDataModel.ENABLE_CHG);
		}
		return notify;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	public boolean isPropertyEnabled(String propertyName) {
		if (J2EEUtilityJarListImportDataModelProvider.BINARY_IMPORT.equals(propertyName)) {
			return getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_PROJECT) || getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PROJECT);
		} else if (J2EEUtilityJarListImportDataModelProvider.OVERRIDE_PROJECT_ROOT.equals(propertyName)) {
			return getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_PROJECT) || getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PROJECT);
		} else if (J2EEUtilityJarListImportDataModelProvider.PROJECT_ROOT.equals(propertyName)) {
			return (getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_PROJECT) || getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PROJECT)) && getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.OVERRIDE_PROJECT_ROOT);
		} else if (J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PATH_VARIABLE.equals(propertyName)) {
			return (getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.LINK_IMPORT) || getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PROJECT));
		} else if (J2EEUtilityJarListImportDataModelProvider.LINKED_PATH_VARIABLE.equals(propertyName)) {
			return getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PATH_VARIABLE);
		} else if (J2EEUtilityJarListImportDataModelProvider.AVAILABLE_JARS_DIRECTORY.equals(propertyName)) {
			boolean createPath = getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PATH_VARIABLE);
			return !createPath || !linkedPathExists();
		}
		return super.isPropertyEnabled(propertyName);
	}

	private boolean linkedPathExists() {
		String linkedPathVariable = getStringProperty(J2EEUtilityJarListImportDataModelProvider.LINKED_PATH_VARIABLE);
		if (linkedPathVariable == null || linkedPathVariable.trim().length() == 0)
			return false;

		IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
		IPath availableJarsPath = manager.getValue(linkedPathVariable);
		return availableJarsPath != null;
	}

	private boolean isLinkedPathVariableInvalid() {
		boolean createPath = getBooleanProperty(J2EEUtilityJarListImportDataModelProvider.CREATE_LINKED_PATH_VARIABLE);
		String linkedPathVariable = getStringProperty(J2EEUtilityJarListImportDataModelProvider.LINKED_PATH_VARIABLE);
		return (createPath && (linkedPathVariable == null || linkedPathVariable.trim().length() == 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#getDefaultProperty(java.lang.String)
	 */
	public Object getDefaultProperty(String propertyName) {
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

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (EAR_PROJECT.equals(propertyName)) {
			return DataModelPropertyDescriptor.createDescriptors(getValidProjectNames());
		} else if (LINKED_PATH_VARIABLE.equals(propertyName)) {
			IPathVariableManager manager = ResourcesPlugin.getWorkspace().getPathVariableManager();
			return DataModelPropertyDescriptor.createDescriptors(manager.getPathVariableNames());
		} else
			return super.getValidPropertyDescriptors(propertyName);
	}

	/**
	 * Populate the resource name combo with connector projects that are not encrypted.
	 */
	protected Object[] getValidProjectNames() {
		List projects = Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects());
		List projectsWithNature = new ArrayList();

		for (int i = 0; i < projects.size(); i++) {
			IProject project = (IProject) projects.get(i);
			//TODO filter only on ear projects
			if (project.isOpen()) {
				projectsWithNature.add(project.getFullPath().toString());
			}
		}

		return ProjectUtilities.getProjectNamesWithoutForwardSlash((String[]) projectsWithNature.toArray(new String[projectsWithNature.size()]));
	}

	public IStatus validate(String propertyName) {
		if (EAR_PROJECT.equals(propertyName) /* && isSet(EAR_PROJECT) */) {
			String earProjectName = getStringProperty(EAR_PROJECT);
			if (earProjectName != null && earProjectName.length() > 0) {
				IProject earProject = ResourcesPlugin.getWorkspace().getRoot().getProject(earProjectName);
				try {
					if (!earProject.isAccessible())
						return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.J2EEUtilityJarListImportDataModel_Specify_Valid_Project, null); //$NON-NLS-1$
					return Status.OK_STATUS;
				} catch (Exception e) {
					return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.J2EEUtilityJarListImportDataModel_Specify_Valid_Project, e); //$NON-NLS-1$
				}
			}
			return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.J2EEUtilityJarListImportDataModel_Specify_Valid_Project, null); //$NON-NLS-1$

		} else if (UTILITY_JAR_LIST.equals(propertyName) || J2EEUtilityJarListImportDataModelProvider.OVERWRITE_IF_NECESSARY.equals(propertyName)) {

			Object[] list = (Object[]) getProperty(UTILITY_JAR_LIST);
			if (list == null || list.length == 0)
				return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.J2EEUtilityJarListImportDataModel_Select_Jar, null); //$NON-NLS-1$

			/* return validateExistingProjects(); */

			return Status.OK_STATUS;
		} else if (LINKED_PATH_VARIABLE.equals(propertyName)) {
			if (isLinkedPathVariableInvalid())
				return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.J2EEUtilityJarListImportDataModel_Specify_Linked_Path, null); //$NON-NLS-1$
		} else if (PROJECT_ROOT.equals(propertyName)) {
			return validateProjectRoot();
		}
		return validate(propertyName);
	}

	private IStatus validateProjectRoot() {
		if (isPropertySet(PROJECT_ROOT) && getBooleanProperty(OVERRIDE_PROJECT_ROOT)) {
			String loc = (String) getProperty(PROJECT_ROOT);
			File file = new File(loc);
			if (!file.canWrite() || !file.isDirectory())
				return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.J2EEUtilityJarListImportDataModel_0, null); //$NON-NLS-1$
		}
		return OK_STATUS;
	}

	// private IStatus validateExistingProjects() {
	// boolean createProject = (getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT)
	// || getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT));
	// boolean overwrite =
	// getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY);
	// if (createProject && !overwrite) {
	// File jarFile = null;
	// String nameWithoutJar = null;
	// Object[] selectedJars = (Object[]) getProperty(UTILITY_JAR_LIST);
	// for (int i = 0; i < selectedJars.length; i++) {
	// jarFile = (File) selectedJars[i];
	// int start = jarFile.getName().indexOf(".jar");
	// nameWithoutJar = jarFile.getName().substring(0, start);
	// if (ProjectUtilities.getProject(nameWithoutJar).exists())
	// return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, 0, "Existing projects with the derived
	// names already exist (\"{0}\").", null); // {0}
	// // nameWithoutJar
	//
	// }
	// }
	// return Status.OK_STATUS;
	// }

}
