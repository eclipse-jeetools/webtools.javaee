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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public abstract class J2EEArtifactExportDataModelProvider extends AbstractDataModelProvider implements IJ2EEComponentExportDataModelProperties {

	public HashMap componentMap;

	public J2EEArtifactExportDataModelProvider() {
		super();
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(COMPONENT_NAME);
		propertyNames.add(PROJECT_NAME);
		propertyNames.add(ARCHIVE_DESTINATION);
		propertyNames.add(EXPORT_SOURCE_FILES);
		propertyNames.add(OVERWRITE_EXISTING);
		propertyNames.add(RUN_BUILD);
		propertyNames.add(COMPONENT);
		return propertyNames;
	}

	protected abstract String getProjectType();

	protected abstract String getWrongComponentTypeString(String projectName);

	protected abstract String getModuleExtension();

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ARCHIVE_DESTINATION)) {
			return ""; //$NON-NLS-1$
		} else if (propertyName.equals(EXPORT_SOURCE_FILES)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(OVERWRITE_EXISTING)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(RUN_BUILD)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean set = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(COMPONENT_NAME)) {
			if (getComponentMap().isEmpty())
				intializeComponentMap();
			IVirtualComponent component = (IVirtualComponent) getComponentMap().get(propertyValue);
			if (null != component && component.getName().equals(propertyValue)) {
				setProperty(COMPONENT, component);
				setProperty(PROJECT_NAME, component.getProject().getName());
			} else {
				setProperty(COMPONENT, null);
				setProperty(PROJECT_NAME, null);
			}
		}
		return set;
	}

	public HashMap getComponentMap() {
		if (componentMap == null)
			componentMap = new HashMap();
		return componentMap;
	}

	public void intializeComponentMap() {
		IVirtualComponent[] comps = ComponentUtilities.getAllWorkbenchComponents();
		for (int i = 0; i < comps.length; i++) {
			getComponentMap().put(comps[i].getName(), comps[i]);
		}
	}

	/**
	 * Populate the resource name combo with projects that are not encrypted.
	 */
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		// TODO: populate valid components
		if (propertyName.equals(COMPONENT_NAME)) {
			List componentNames = new ArrayList();
			IVirtualComponent[] wbComps = ComponentUtilities.getAllWorkbenchComponents();

			List relevantComponents = new ArrayList();
			for (int i = 0; i < wbComps.length; i++) {
				if (J2EEProjectUtilities.getJ2EEProjectType(wbComps[i].getProject()).equals(getProjectType())) {
					relevantComponents.add(wbComps[i]);
					getComponentMap().put(wbComps[i].getName(), wbComps[i]);
				}
			}

			if (relevantComponents == null || relevantComponents.size() == 0)
				return null;

			for (int j = 0; j < relevantComponents.size(); j++) {
				componentNames.add(((IVirtualComponent) relevantComponents.get(j)).getName());
			}
			String[] names = (String[]) componentNames.toArray(new String[componentNames.size()]);

			return DataModelPropertyDescriptor.createDescriptors(names);
		}
		return super.getValidPropertyDescriptors(propertyName);
		// (ProjectUtilities.getProjectNamesWithoutForwardSlash((String[])
		// projectsWithNature.toArray(new String[projectsWithNature.size()])));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	public IStatus validate(String propertyName) {
		if (COMPONENT_NAME.equals(propertyName)) {
			String componentName = (String) model.getProperty(COMPONENT_NAME);
			if (componentName == null || componentName.equals("")) //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.MODULE_EXISTS_ERROR));
			IVirtualComponent component = (IVirtualComponent) componentMap.get(componentName);
			if (component == null) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.MODULE_EXISTS_ERROR));
			}
			if (!J2EEProjectUtilities.getJ2EEProjectType(component.getProject()).equals(getProjectType())) {
				return WTPCommonPlugin.createErrorStatus(getWrongComponentTypeString(componentName));
			}
		}
		if (ARCHIVE_DESTINATION.equals(propertyName)) {
			String archiveLocation = (String) model.getProperty(ARCHIVE_DESTINATION);
			if (!model.isPropertySet(ARCHIVE_DESTINATION) || archiveLocation.equals("")) { //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID)); //);
			} else if (model.isPropertySet(ARCHIVE_DESTINATION) && !validateModuleType(archiveLocation)) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_ARCHIVE_SHOULD_END_WITH, new Object[]{getModuleExtension()})); //);
			} else if (model.isPropertySet(ARCHIVE_DESTINATION)) {
				IStatus tempStatus = validateLocation(archiveLocation);
				if (tempStatus != OK_STATUS)
					return tempStatus;
			}
		}
		if (ARCHIVE_DESTINATION.equals(propertyName) || OVERWRITE_EXISTING.equals(propertyName)) {
			String location = (String) getProperty(ARCHIVE_DESTINATION);
			if (checkForExistingFileResource(location)) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.RESOURCE_EXISTS_ERROR, new Object[]{location}));
			}
		}
		return OK_STATUS;
	}

	private IStatus validateLocation(String archiveLocation) {
		IPath path = null;
		try {
			path = new Path(archiveLocation);
		} catch (IllegalArgumentException ex) {
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IStatus status = workspace.validateName(path.lastSegment(), IResource.FILE);
		if (!status.isOK()) {
			return status;
		}
		String device = path.getDevice();
		if (device == null)
			return OK_STATUS;
		if (path == null || device.length() == 1 && device.charAt(0) == IPath.DEVICE_SEPARATOR)
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));

		if (!path.toFile().canWrite()) {
			if (path.toFile().exists()) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.IS_READ_ONLY));
			}
			boolean OK = false;
			path = path.removeLastSegments(1);
			for (int i = 1; !OK && i < 20 && path.segmentCount() > 0; i++) {
				if (path.toFile().exists()) {
					OK = true;
				}
				status = workspace.validateName(path.lastSegment(), IResource.FOLDER);
				if (!status.isOK()) {
					return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
				}
				path = path.removeLastSegments(1);
			}
		}

		return OK_STATUS;
	}

	private boolean checkForExistingFileResource(String fileName) {
		if (!model.getBooleanProperty(OVERWRITE_EXISTING)) {
			java.io.File externalFile = new java.io.File(fileName);
			if (externalFile != null && externalFile.exists())
				return true;
		}
		return false;
	}

	private boolean validateModuleType(String archive) {
		if ((archive.length() < 4) || (!(archive.substring(archive.length() - 4, archive.length()).equalsIgnoreCase(getModuleExtension())))) {
			return false;
		}
		return true;
	}

}
