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
package org.eclipse.jst.j2ee.internal.migration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author dfholttp
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ComposedMigrationConfig extends J2EEMigrationConfig {
	public static ComposedMigrationConfig createComposedConfigForEAR(IProject anEARProject) {
		return new ComposedMigrationConfig(anEARProject, XMLResource.APPLICATION_TYPE);
	}

	public static ComposedMigrationConfig createComposedConfigForModules(List projects) {
		return new ComposedMigrationConfig(projects, PRIM_COMPOSED_TYPE);
	}

	protected List children;
	protected List composedProjects;

	/**
	 * Constructor for ComposedMigrationConfig, which takes an EAR project
	 * 
	 * @param aProject
	 * @param aDeploymentDesType
	 *  
	 */
	protected ComposedMigrationConfig(IProject aProject, int aDeploymentDesType) {
		super(aProject, aDeploymentDesType);
	}

	/**
	 * Constructor for ComposedMigrationConfig, which takes a list of children only project
	 */
	protected ComposedMigrationConfig(List projects, int aDeploymentDesType) {
		super(null, aDeploymentDesType);
		composedProjects = projects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(J2EE_VERSIONS_LBL))
			return (Boolean) getProperty(MIGRATE_VERSION);
		if (propertyName.equals(ServerTargetDataModel.J2EE_VERSION_ID))
			return (Boolean) getProperty(MIGRATE_VERSION);
		return super.basicIsEnabled(propertyName);
	}

	protected Integer convertVersionLabeltoID(String label) {
		int id = -1;
		if (label.equals(J2EEVersionConstants.VERSION_1_3_TEXT))
			id = J2EE_1_3_ID;
		else if (label.equals(VERSION_1_4_TEXT))
			id = J2EE_1_4_ID;
		return new Integer(id);
	}

	public void deselectAllChildren() {
		setAllChildrenSelected(false);
	}

	/**
	 * Dispose the EAR edit model and each child.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig#dispose()
	 */
	public void dispose() {
		super.dispose();
		disposeChildren();
	}

	protected void disposeChildren() {
		if (children == null || children.isEmpty())
			return;
		int size = children.size();
		for (int i = 0; i < size; i++)
			((J2EEMigrationConfig) children.get(i)).dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#propertyChanged(org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent)
	 */
	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
		if (event.getDataModel() == serverTargetDataModel && event.getPropertyName().equals(ServerTargetDataModel.RUNTIME_TARGET_ID)) {
			for (int i = 0; i < children.size(); i++) {
				J2EEMigrationConfig j2eeConfig = (J2EEMigrationConfig) children.get(i);
				j2eeConfig.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID, event.getNewValue());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName == J2EE_VERSIONS_LBL && null != propertyValue)
			setIntProperty(J2EE_MIGRATION_VERSION, convertVersionLabeltoID((String) propertyValue).intValue());
		if (propertyName == J2EE_MIGRATION_VERSION && null != propertyValue) {
			serverTargetDataModel.setProperty(ServerTargetDataModel.J2EE_VERSION_ID, propertyValue);
			notifyValidValuesChange(ServerTargetDataModel.J2EE_VERSION_ID);
		}
		if (EditModelOperationDataModel.PROJECT_NAME.equals(propertyName))
			serverTargetDataModel.setProperty(ServerTargetDataModel.PROJECT_NAME, propertyValue);
		return super.doSetProperty(propertyName, propertyValue);
	}

	/**
	 * Return a list of all the projects that are selected, including this project
	 */
	public List getAllSelectedProjects() {
		List result = new ArrayList();
		if (isEAR())
			result.add(getTargetProject());
		List theChildren = getSelectedChildren();
		for (int i = 0; i < theChildren.size(); i++) {
			J2EEMigrationConfig child = (J2EEMigrationConfig) theChildren.get(i);
			result.add(child.getTargetProject());
		}
		return result;
	}

	/**
	 * Return a list which includes any config that is selected and is migrating J2EE 1.2 to 1.3
	 */
	public List getAllVersionMigratableConfigs() {
		List result = new ArrayList();
		if (isEAR() && shouldMigrateJ2EEVersion())
			result.add(this);
		List theChildren = getChildren();
		for (int i = 0; i < theChildren.size(); i++) {
			J2EEMigrationConfig child = (J2EEMigrationConfig) theChildren.get(i);
			if (child.isSelected() && child.shouldMigrateJ2EEVersion())
				result.add(child);
		}
		return result;
	}

	public List getAppClientChildren() {
		return getChildrenOfType(XMLResource.APP_CLIENT_TYPE);
	}

	/**
	 * Returns the childConfigs.
	 * 
	 * @return List
	 */
	public List getChildren() {
		if (children == null)
			initChildren();
		return children;
	}

	public List getChildrenOfType(int ddType) {
		if (getChildren().isEmpty())
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		for (int i = 0; i < children.size(); i++) {
			J2EEMigrationConfig child = (J2EEMigrationConfig) children.get(i);
			if (child.getDeploymentDesType() == ddType)
				result.add(child);
		}
		return result;
	}

	public List getConnectorChildren() {
		return getChildrenOfType(XMLResource.RAR_TYPE);
	}

	public List getEJBJarChildren() {
		return getChildrenOfType(XMLResource.EJB_TYPE);
	}

	public List getSelectedChildren() {
		List theChildren = getChildren();
		List result = new ArrayList();
		for (int i = 0; i < theChildren.size(); i++) {
			J2EEMigrationConfig child = (J2EEMigrationConfig) theChildren.get(i);
			if (child.isSelected())
				result.add(child);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig#getProject()
	 */
	public IProject getTargetProject() {
		if (composedProjects != null && composedProjects.size() > 0)
			return (IProject) composedProjects.get(0);
		return super.getTargetProject();
	}

	public List getWebChildren() {
		return getChildrenOfType(XMLResource.WEB_APP_TYPE);
	}

	public boolean hasAppClientChildren() {
		return hasChildOfType(XMLResource.APP_CLIENT_TYPE);
	}

	public boolean hasConnectorChildren() {
		return hasChildOfType(XMLResource.RAR_TYPE);
	}

	public boolean hasChildOfType(int ddType) {
		if (getChildren().isEmpty())
			return false;
		for (int i = 0; i < children.size(); i++) {
			J2EEMigrationConfig child = (J2EEMigrationConfig) children.get(i);
			if (child.getDeploymentDesType() == ddType)
				return true;
		}
		return false;
	}

	public boolean hasEJBJarChildren() {
		return hasChildOfType(XMLResource.EJB_TYPE);
	}

	public boolean hasSelectedAppClientChildren() {
		return hasSelectedChildOfType(XMLResource.APP_CLIENT_TYPE);
	}

	public boolean hasSelectedChildOfType(int ddType) {
		if (getChildren().isEmpty())
			return false;
		for (int i = 0; i < children.size(); i++) {
			J2EEMigrationConfig child = (J2EEMigrationConfig) children.get(i);
			if (child.getDeploymentDesType() == ddType && child.isSelected())
				return true;
		}
		return false;
	}

	public boolean hasSelectedEJBJarChildren() {
		return hasSelectedChildOfType(XMLResource.EJB_TYPE);
	}

	public boolean hasSelectedWebChildren() {
		return hasSelectedChildOfType(XMLResource.WEB_APP_TYPE);
	}

	public boolean hasWebChildren() {
		return hasChildOfType(XMLResource.WEB_APP_TYPE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#init()
	 */
	protected void init() {
		super.init();
		setProperty(EDIT_MODEL_ID, IEARNatureConstants.EDIT_MODEL_ID);
	}

	protected void initChildConfigsForEAR() {
		EARNatureRuntime earNature = EARNatureRuntime.getRuntime(getTargetProject());
		if (earNature == null)
			return;
		Collection projects = earNature.getAllMappedProjects().values();
		children = createConfigs(projects);
	}

	protected void initChildren() {
		if (isEAR())
			initChildConfigsForEAR();
		else
			children = createConfigs(composedProjects);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig#isAnyVersionMigrateable()
	 */
	public boolean isAnyVersionMigrateable() {
		return super.isAnyVersionMigrateable() || isAnyVersionMigrateable(getChildren());
	}

	public void selectAllChildren() {
		setAllChildrenSelected(true);
	}

	public void setAllChildrenSelected(boolean selected) {
		List theChildren = getChildren();
		for (int i = 0; i < theChildren.size(); i++) {
			J2EEMigrationConfig config = (J2EEMigrationConfig) theChildren.get(i);
			config.setIsSelected(selected);
		}
	}

	public void setMigrateVersionForAll(boolean migrate) {
		setMigrateVersion(migrate);
		if (getChildren().isEmpty())
			return;
		for (int i = 0; i < children.size(); i++) {
			((J2EEMigrationConfig) children.get(i)).setMigrateVersion(migrate);
		}
	}

	public void setMigrateStructureForAll(boolean migrate) {
		setMigrateProjectStructure(migrate);
		if (getChildren().isEmpty())
			return;
		for (int i = 0; i < children.size(); i++) {
			((J2EEMigrationConfig) children.get(i)).setMigrateProjectStructure(migrate);
		}
	}

	public List withAllChildren() {
		if (isPrimComposed())
			return getChildren();
		List result = new ArrayList();
		result.add(this);
		result.addAll(getChildren());
		return result;
	}

	public List withAllSelectedChildren() {
		if (isPrimComposed())
			return getSelectedChildren();
		List result = new ArrayList();
		result.add(this);
		result.addAll(getSelectedChildren());
		return result;
	}
}