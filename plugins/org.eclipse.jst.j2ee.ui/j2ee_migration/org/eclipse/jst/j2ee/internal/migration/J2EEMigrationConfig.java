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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class J2EEMigrationConfig extends EditModelOperationDataModel implements IAdaptable, J2EEVersionConstants {

	public static final String DEPLOYMENT_DESCRIPTOR_TYPE = "J2EEMigrationConfig.deploymentDesType"; //$NON-NLS-1$
	public static final String IS_SELECTED = "J2EEMigrationConfig.isSelected"; //$NON-NLS-1$
	public static final String J2EE_MIGRATION_VERSION = "J2EEMigrationConfig.J2EE_MIGRATION_VERSION"; //$NON-NLS-1$
	public static final String J2EE_VERSIONS_LBL = "J2EEMigrationConfig.J2EE_VERSIONS_LBL"; //$NON-NLS-1$
	protected static final int PRIM_COMPOSED_TYPE = -1;
	public static final String MIGRATE_VERSION = "J2EEMigrationConfig.migrateVersion"; //$NON-NLS-1$
	public ServerTargetDataModel serverTargetDataModel;
	public static final String NESTED_MODEL_SERVER_TARGET = "J2EEMigrationConfig.NESTED_MODEL_SERVER_TARGET"; //$NON-NLS-1$
	public static final String MIGRATE_PROJECT_STRUCTURE = "J2EEMigrationConfig.migrateProjectStructure"; //$NON-NLS-1$
	public static final String MIGRATION_STATUS = "J2EEMigrationConfig.migrationStatus"; //$NON-NLS-1$

	public static J2EEMigrationConfig createConfig(IProject aProject) {
		J2EENature aNature = J2EENature.getRegisteredRuntime(aProject);
		return createConfig(aNature);
	}

	public static J2EEMigrationConfig createConfig(J2EENature aNature) {
		if (aNature == null)
			return null;
		switch (aNature.getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				return new AppClientMigrationConfig(aNature.getProject(), aNature.getDeploymentDescriptorType());
			case XMLResource.EJB_TYPE :
				return new EJBJarMigrationConfig(aNature.getProject(), aNature.getDeploymentDescriptorType());
			case XMLResource.WEB_APP_TYPE :
				return new WebMigrationConfig(aNature.getProject(), aNature.getDeploymentDescriptorType());
			case XMLResource.RAR_TYPE :
				return new ConnectorMigrationConfig(aNature.getProject(), aNature.getDeploymentDescriptorType());
			default :
				return new J2EEMigrationConfig(aNature.getProject(), aNature.getDeploymentDescriptorType());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#initNestedModels()
	 */
	protected void initNestedModels() {
		super.initNestedModels();
		addNestedServerTargetDataModel();
	}

	/**
	 *  
	 */
	private void addNestedServerTargetDataModel() {
		serverTargetDataModel = new ServerTargetDataModel();
		serverTargetDataModel.setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.APPLICATION_TYPE);
		serverTargetDataModel.setProperty(ServerTargetDataModel.J2EE_VERSION_ID, getDefaultProperty(J2EE_MIGRATION_VERSION));
		addNestedModel(NESTED_MODEL_SERVER_TARGET, serverTargetDataModel);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(J2EE_VERSIONS_LBL)) {
			return getValidJ2EEVersionLabels();
		}
		return super.doGetValidPropertyValues(propertyName);
	}

	/**
	 * filter out non-j2ee projects, eg, util JAR project
	 */
	public List createConfigs(Collection projects) {
		List result = new ArrayList();
		Iterator it = projects.iterator();
		while (it.hasNext()) {
			IProject aProject = (IProject) it.next();
			if (aProject != null) {
				J2EEMigrationConfig config = createConfig(aProject);
				if (config != null) {
					initialize(config);
					result.add(config);
				}
			}
		}
		return result;
	}

	public static final int APP_CLIENT_TYPE = 1;
	public static final int APPLICATION_TYPE = 2;
	public static final int EJB_TYPE = 3;
	public static final int WEB_APP_TYPE = 4;
	public static final int RAR_TYPE = 5;
	public static final int WEB_SERVICES_CLIENT_TYPE = 6;

	/**
	 * @param config
	 */
	protected void initialize(J2EEMigrationConfig config) {
		int i = J2EEMigrationHelper.getDeploymentDescriptorType(config);
		config.setProperty(EDIT_MODEL_ID, getEditModelId(i));
		config.getNestedModel(NESTED_MODEL_SERVER_TARGET).setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, i);
	}

	protected String getEditModelId(int i) {
		String editModelId = null;
		switch (i) {
			case 1 :
				editModelId = IApplicationClientNatureConstants.EDIT_MODEL_ID;
				break;
			case 2 :
				editModelId = IEARNatureConstants.EDIT_MODEL_ID;
				break;
			case 3 :
				editModelId = IEJBNatureConstants.EDIT_MODEL_ID;
				break;
			case 4 :
				editModelId = IWebNatureConstants.EDIT_MODEL_ID;
				break;
			case 5 :
				editModelId = IConnectorNatureConstants.EDIT_MODEL_ID;
			default :
				editModelId = IEARNatureConstants.EDIT_MODEL_ID;
				break;
		}
		return editModelId;
	}

	/**
	 * Extract the projects from the configs
	 */
	public static List getProjects(List j2eeMigrationConfigs) {
		List result = new ArrayList(j2eeMigrationConfigs.size());
		for (int i = 0; i < j2eeMigrationConfigs.size(); i++) {
			result.add(j2eeMigrationConfigs.get(i));
		}
		return result;
	}

	public static boolean isAnyVersionMigrateable(List configs) {
		for (int i = 0; i < configs.size(); i++) {
			J2EEMigrationConfig child = (J2EEMigrationConfig) configs.get(i);
			if (child.isAnyVersionMigrateable())
				return true;
		}
		return false;
	}

	/**
	 * Constructor for J2EEMigrationConfig.
	 */
	public J2EEMigrationConfig(IProject project, int aDeploymentDesType) {
		super();
		if (project != null)
			setProperty(PROJECT_NAME, project.getName());
		setIntProperty(DEPLOYMENT_DESCRIPTOR_TYPE, aDeploymentDesType);
	}

	/**
	 * @param i
	 * @return
	 */
	private Object convertVersionIDtoLabel(int i) {
		switch (i) {
			case J2EE_1_2_ID :
				return VERSION_1_2_TEXT;
			case J2EE_1_3_ID :
				return VERSION_1_3_TEXT;
			case J2EE_1_4_ID :
				return VERSION_1_4_TEXT;
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * All clients that use a J2EEMigrationConfig should call this method to ensure that all
	 * resources are released. Subclasses should override to release resources, especially
	 * J2EEEditModels.
	 */
	public void dispose() {
	}

	/**
	 * @see org.eclipse.core.runtime.IAdaptable#EcoreUtil.getAdapter(eAdapters(),Class)
	 */
	public Object getAdapter(Class adapter) {
		if (getTargetProject() == null)
			return null;
		return Platform.getAdapterManager().getAdapter(getTargetProject(), adapter);
	}

	/**
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	private Object getDefaultJ2EEVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new Integer(J2EE_1_4_ID);
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new Integer(J2EE_1_3_ID);
			default :
				return new Integer(J2EE_1_4_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return null;
	}

	/**
	 * @return
	 */
	public ServerTargetDataModel getServerTargetDataModel() {
		return serverTargetDataModel;
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(J2EE_MIGRATION_VERSION))
			return getDefaultJ2EEVersion();
		if (propertyName.equals(J2EE_VERSIONS_LBL))
			return convertVersionIDtoLabel(getIntProperty(J2EE_MIGRATION_VERSION));
		if (propertyName.equals(MIGRATE_PROJECT_STRUCTURE))
			return getDefaultProjectStructure();
		if (propertyName.equals(MIGRATE_VERSION))
			return getDefaultMigrateVersionProperty();
		if (propertyName.equals(IS_SELECTED))
			return getDefaultIsSelectedProperty();
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	private Object getDefaultIsSelectedProperty() {
		return new Boolean(true);
	}

	/**
	 * @return
	 */
	private Object getDefaultMigrateVersionProperty() {
		return new Boolean(true);
	}

	/**
	 * @return
	 */
	private Object getDefaultProjectStructure() {
		return new Boolean(true);
	}

	/**
	 * Returns the deploymentDesType.
	 * 
	 * @return int
	 */
	public int getDeploymentDesType() {
		return getIntProperty(DEPLOYMENT_DESCRIPTOR_TYPE);
	}

	/**
	 * @return Return a String[] of the valid J2EE versions for the selected J2EE Preference Level.
	 */
	protected Object[] getValidJ2EEVersionLabels() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new String[]{VERSION_1_3_TEXT, VERSION_1_4_TEXT};
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new String[]{VERSION_1_3_TEXT};
			default :
				return new String[]{VERSION_1_3_TEXT, VERSION_1_4_TEXT};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.emf.workbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(DEPLOYMENT_DESCRIPTOR_TYPE);
		addValidBaseProperty(IS_SELECTED);
		addValidBaseProperty(MIGRATE_VERSION);
		addValidBaseProperty(J2EE_MIGRATION_VERSION);
		addValidBaseProperty(J2EE_VERSIONS_LBL);
		addValidBaseProperty(MIGRATE_PROJECT_STRUCTURE);
		addValidBaseProperty(MIGRATION_STATUS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#isResultProperty(java.lang.String)
	 */
	protected boolean isResultProperty(String propertyName) {
		if (propertyName.equals(J2EEMigrationConfig.MIGRATION_STATUS))
			return true;
		return super.isResultProperty(propertyName);
	}

	public boolean isAnyVersionMigrateable() {
		return getBooleanProperty(MIGRATE_VERSION);
	}

	public boolean isAppClient() {
		return getIntProperty(DEPLOYMENT_DESCRIPTOR_TYPE) == XMLResource.APP_CLIENT_TYPE;
	}

	public boolean isComplex() {
		return false;
	}

	public boolean isComposed() {
		return isPrimComposed() || isEAR();
	}

	public boolean isEAR() {
		return getIntProperty(DEPLOYMENT_DESCRIPTOR_TYPE) == XMLResource.APPLICATION_TYPE;
	}

	public boolean isEJB() {
		return getIntProperty(DEPLOYMENT_DESCRIPTOR_TYPE) == XMLResource.EJB_TYPE;
	}

	/**
	 * Returns the migrateVersion12to13.
	 * 
	 * @return boolean
	 */
	public boolean shouldMigrateJ2EEVersion() {
		return getBooleanProperty(MIGRATE_VERSION);
	}

	public boolean shouldMigrateProjectStructure() {
		return getBooleanProperty(MIGRATE_PROJECT_STRUCTURE);
	}

	public boolean isPrimComposed() {
		return getIntProperty(DEPLOYMENT_DESCRIPTOR_TYPE) == PRIM_COMPOSED_TYPE;
	}

	/**
	 * Returns the isSelected.
	 * 
	 * @return boolean
	 */
	public boolean isSelected() {
		return getBooleanProperty(IS_SELECTED);
	}

	public boolean isWeb() {
		return getIntProperty(DEPLOYMENT_DESCRIPTOR_TYPE) == XMLResource.WEB_APP_TYPE;
	}

	/**
	 * Sets the isSelected.
	 * 
	 * @param isSelected
	 *            The isSelected to set
	 */
	public void setIsSelected(boolean isSelected) {
		setBooleanProperty(IS_SELECTED, isSelected);
	}

	/**
	 * Sets the migrateVersion.
	 * 
	 * @param migrateVersion
	 *            The migrateVersion to set
	 */
	public void setMigrateVersion(boolean migrateVersion) {
		setBooleanProperty(MIGRATE_VERSION, migrateVersion);
	}

	/**
	 * Sets the migrateProjectStructure.
	 * 
	 * @param migrateProjectStructure
	 *            The migrateProjectStructure to set
	 */
	public void setMigrateProjectStructure(boolean migrateStructure) {
		setBooleanProperty(MIGRATE_PROJECT_STRUCTURE, migrateStructure);
	}

	public String toString() {
		return getClass().getName() + "(project: " + getTargetProject().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}