/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;


public class EJBJarMigrationConfig extends J2EEMigrationConfig {
	public static String DEFAULT_LOCAL_SUFFIX = "Local"; //$NON-NLS-1$ 
	public static String DELETE_REMOTE_CLIENT_VIEW = "EJBJarMigrationConfig.deleteRemoteClientView"; //$NON-NLS-1$
	public static String LOCAL_CLIENT_VIEW_SUFFIX = "EJBJarMigrationConfig.localClientViewSuffix"; //$NON-NLS-1$
	public static String MIGRATE_CMP1X_TO_2X = "EJBJarMigrationConfig.migrateCMP1xto2x"; //$NON-NLS-1$
	public static String REUSE_REMOTE_CLIENT_VIEW_NAME = "EJBJarMigrationConfig.reuseRemoteClientViewName"; //$NON-NLS-1$

	public static List filterConfigsWithNoApplicableClientConfigs(List jarConfigs) {
		List result = new ArrayList();
		for (int i = 0; i < jarConfigs.size(); i++) {
			EJBJarMigrationConfig config = (EJBJarMigrationConfig) jarConfigs.get(i);
			if (!config.getApplicableChildren().isEmpty())
				result.add(config);
		}
		return result;
	}

	protected Map beansToConfigs = new HashMap();
	protected List children;
	protected Map createCommandMap = new HashMap();
	protected IRootCommand deleteCommand, createCommand;
	protected EjbModuleExtensionHelper modHelper;
	protected List relationships11;

	public EJBJarMigrationConfig(IProject aProject, int aDeploymentDesType) {
		super(aProject, aDeploymentDesType);
		if (children == null)
			init1XTo2XChildren();
	}

	public void appendCreateCommand(IEJBCommand cmd) {
		if (createCommand == null && cmd.isRootCommand())
			createCommand = (IRootCommand) cmd;
		else
			createCommand = createCommand.append(cmd);
		if (cmd.isEnterpriseBeanRootCommand())
			createCommandMap.put(((EnterpriseBeanCommand) cmd).getEjb(), cmd);
	}

	public void appendDeleteCommand(IRootCommand rootCommand) {
		if (deleteCommand == null)
			deleteCommand = rootCommand;
		else
			deleteCommand = deleteCommand.append(rootCommand);
	}

	public void deselectAllChildren() {
		setAllChildrenSelected(false);
	}

	public List get11CMPChildren() {
		List result = null;
		for (int i = 0; i < getChildren().size(); i++) {
			EJBClientViewMigrationConfig child = (EJBClientViewMigrationConfig) getChildren().get(i);
			if (!child.is11CMP())
				continue;
			if (result == null)
				result = new ArrayList();
			result.add(child);
		}
		return result == null ? Collections.EMPTY_LIST : result;
	}

	public List get11CMPs() {
		List result = null;
		if (getChildren().isEmpty())
			return Collections.EMPTY_LIST;
		int size = getChildren().size();
		EJBClientViewMigrationConfig config;
		for (int i = 0; i < size; i++) {
			config = (EJBClientViewMigrationConfig) children.get(i);
			if (config.is11CMP()) {
				if (result == null)
					result = new ArrayList(size);
				result.add(config.getEjb());
			}
		}
		return result;
	}

	public List get11Relationships() {
		if (relationships11 == null) {
			EJBJar jar = getEJBJar();
			initializeModuleExtHelper();
			if (modHelper != null)
				relationships11 = modHelper.getRelationships_cmp11(jar);
			else
				relationships11 = Collections.EMPTY_LIST;
		}
		return relationships11;
	}

	/**
	 * Filters the 1.1 CMP beans if we are not migrating to 2.x
	 */
	public List getApplicableChildren() {
		if (isMigrateCMP1xto2x())
			return getChildren();
		List result = new ArrayList();
		for (int i = 0; i < children.size(); i++) {
			EJBClientViewMigrationConfig child = (EJBClientViewMigrationConfig) children.get(i);
			if (!child.is11CMP())
				result.add(child);
		}
		return result;
	}

	public int getChildCount() {
		return getChildren().size();
	}

	public EJBClientViewMigrationConfig getChildFor(EnterpriseBean ejb) {
		return (EJBClientViewMigrationConfig) beansToConfigs.get(ejb);
	}

	public List getChildren() {
		return children;
	}

	private List getChildrenForRoots() {
		if (getChildren().isEmpty())
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		int size = getChildren().size();
		for (int i = 0; i < size; i++) {
			EJBClientViewMigrationConfig child = (EJBClientViewMigrationConfig) children.get(i);
			if (isRoot(child))
				result.add(child);
		}
		return result;
	}

	public List getChildrenRequiringSelection() {
		Set beanConfigs = new HashSet();
		traverseRelationships(beanConfigs);
		traverseInheritance(beanConfigs);
		List result = new ArrayList();
		Iterator iter = beanConfigs.iterator();
		while (iter.hasNext()) {
			EJBClientViewMigrationConfig config = (EJBClientViewMigrationConfig) iter.next();
			if (!config.isSelected())
				result.add(config);
		}
		return result;
	}

	/**
	 * Returns the createCommand.
	 * 
	 * @return IRootCommand
	 */
	public IRootCommand getCreateCommand() {
		return createCommand;
	}

	public EnterpriseBeanCommand getCreateCommand(EnterpriseBean ejb) {
		return (EnterpriseBeanCommand) createCommandMap.get(ejb);
	}

	public List getDefaults() {
		List result = null;
		if (getChildren().isEmpty())
			return Collections.EMPTY_LIST;;
		int size = children.size();
		EJBClientViewMigrationConfig config;
		for (int i = 0; i < size; i++) {
			config = (EJBClientViewMigrationConfig) children.get(i);
			if (result == null)
				result = new ArrayList(size);
			result.add(config);
		}
		if (result == null)
			return Collections.EMPTY_LIST;
		return result;
	}



	/**
	 * Returns the deleteCommand.
	 * 
	 * @return IRootCommand
	 */
	public IRootCommand getDeleteCommand() {
		return deleteCommand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#init()
	 */
	protected void init() {
		super.init();
		setProperty(EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
	}

	public EJBJar getEJBJar() {
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(getTargetProject());
		if (nature != null)
			return nature.getEJBJar();
		return null;
	}

	/**
	 * Get all the child configs that hold entities
	 */
	public List getEntityChildren() {
		List result = null;
		if (getChildren().isEmpty())
			return Collections.EMPTY_LIST;
		int size = children.size();
		EJBClientViewMigrationConfig config;
		for (int i = 0; i < size; i++) {
			config = (EJBClientViewMigrationConfig) children.get(i);
			if (config.isEntity()) {
				if (result == null)
					result = new ArrayList(size);
				result.add(config);
			}
		}
		if (result == null)
			return Collections.EMPTY_LIST;
		return result;
	}

	/**
	 * Returns the localClientViewSuffix.
	 * 
	 * @return String
	 */
	public String getLocalClientViewSuffix() {
		return getStringProperty(LOCAL_CLIENT_VIEW_SUFFIX);
	}

	public int getSelectedCount() {
		if (getChildren().isEmpty())
			return 0;
		int count = 0;
		for (int i = 0; i < children.size(); i++) {
			EJBClientViewMigrationConfig config = (EJBClientViewMigrationConfig) children.get(i);
			if (config.isSelected())
				count++;
		}
		return count;
	}

	/**
	 * Method getSelectedLocalClientEJBs.
	 * 
	 * @return List
	 */
	public List getSelectedLocalClientEJBs() {
		List result = null;
		if (getChildren().isEmpty())
			return Collections.EMPTY_LIST;
		int size = children.size();
		EJBClientViewMigrationConfig config;
		for (int i = 0; i < size; i++) {
			config = (EJBClientViewMigrationConfig) children.get(i);
			if (config.isSelected() && !J2EEMigrationHelper.isEJB1_X(config.getEjb())) {
				if (result == null)
					result = new ArrayList(size);
				result.add(config.getEjb());
			}
		}
		if (result == null)
			return Collections.EMPTY_LIST;
		return result;
	}

	private List getSubtypes(EJBClientViewMigrationConfig superTypeConfig) {
		List subTypes = null;
		initializeModuleExtHelper();
		if (modHelper != null)
			subTypes = modHelper.getSubtypes(superTypeConfig.getEjb());
		if (subTypes == null)
			return Collections.EMPTY_LIST;
		return subTypes;
	}

	public boolean has11CMPsToMigrate() {
		if (!getChildren().isEmpty()) {
			int size = getChildren().size();
			EJBClientViewMigrationConfig config;
			for (int i = 0; i < size; i++) {
				config = (EJBClientViewMigrationConfig) getChildren().get(i);
				if (config.is11CMP())
					return true;
			}
		}
		return false;
	}

	/**
	 * Method hasLocalClientSelected.
	 * 
	 * @return boolean
	 */
	public boolean hasLocalClientSelected() {
		if (!getChildren().isEmpty()) {
			int size = getChildren().size();
			EJBClientViewMigrationConfig config;
			for (int i = 0; i < size; i++) {
				config = (EJBClientViewMigrationConfig) getChildren().get(i);
				if (config.isSelected())
					return true;
			}
		}
		return false;
	}

	private void init1XTo2XChildren() {
		children = new ArrayList();
		List beans = getEJBJar().getEnterpriseBeans();
		for (int i = 0; i < beans.size(); i++) {
			EnterpriseBean bean = (EnterpriseBean) beans.get(i);
			if (bean != null && bean.isMessageDriven())
				continue;
			EJBClientViewMigrationConfig child = new EJBClientViewMigrationConfig(bean, this);
			children.add(child);
			beansToConfigs.put(bean, child);
			if (child.is11CMP())
				child.setIsSelected(getBooleanProperty(MIGRATE_CMP1X_TO_2X));
		}
	}

	/**
	 * @return-load and return the EJBModuleExtensionsHelper from the WAS extensions plugin
	 */
	private void initializeModuleExtHelper() {
		if (modHelper == null)
			modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(MIGRATE_CMP1X_TO_2X);
		addValidBaseProperty(DELETE_REMOTE_CLIENT_VIEW);
		addValidBaseProperty(LOCAL_CLIENT_VIEW_SUFFIX);
		addValidBaseProperty(REUSE_REMOTE_CLIENT_VIEW_NAME);
		addValidBaseProperty(DEFAULT_LOCAL_SUFFIX);
	}

	public boolean isAllSelected() {
		if (getChildren().isEmpty())
			return false;
		for (int i = 0; i < children.size(); i++) {
			EJBClientViewMigrationConfig config = (EJBClientViewMigrationConfig) children.get(i);
			if (!config.isSelected())
				return false;
		}
		return true;
	}

	public boolean isAnyChildSelected() {
		if (getChildren().isEmpty())
			return false;
		for (int i = 0; i < children.size(); i++) {
			EJBClientViewMigrationConfig config = (EJBClientViewMigrationConfig) children.get(i);
			if (config.isSelected())
				return true;
		}
		return false;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig#isComplex()
	 */
	public boolean isComplex() {
		return shouldMigrateJ2EEVersion() && isMigrateCMP1xto2x();
	}

	/**
	 * Returns the deleteRemoteClientView.
	 * 
	 * @return boolean
	 */
	public boolean isDeleteRemoteClientView() {
		return getBooleanProperty(DELETE_REMOTE_CLIENT_VIEW);
	}

	/**
	 * Returns the migrateCMP1xto2x.
	 * 
	 * @return boolean
	 */
	public boolean isMigrateCMP1xto2x() {
		return getBooleanProperty(MIGRATE_CMP1X_TO_2X);
	}

	/**
	 * Returns the reuseRemoteClientViewName.
	 * 
	 * @return boolean
	 */
	public boolean isReuseRemoteClientViewName() {
		return getBooleanProperty(REUSE_REMOTE_CLIENT_VIEW_NAME);
	}

	private boolean isRoot(EJBClientViewMigrationConfig current) {
		initializeModuleExtHelper();
		if (modHelper != null)
			return modHelper.getSuperType(current.getEjb()) == null;
		return true;
	}

	private boolean isSelectedOrRequired(Set beanConfigs, EJBClientViewMigrationConfig current) {
		return current.isSelected() || beanConfigs.contains(current);
	}

	private boolean needsRelationshipTraversal(EJBClientViewMigrationConfig child, List roles) {
		if (roles.isEmpty())
			return false;
		return J2EEMigrationHelper.isEJB1_X(child.getEjb()) && isMigrateCMP1xto2x();
	}

	public void selectAllChildren() {
		setAllChildrenSelected(true);
	}

	public void selectAllEntities() {
		List entities = getEntityChildren();
		if (entities.isEmpty())
			entities = getDefaults();
		for (int i = 0; i < entities.size(); i++) {
			((EJBClientViewMigrationConfig) entities.get(i)).setIsSelected(true);
		}
	}

	public void selectRequiredChildren() {
		setChildrenSelected(getChildrenRequiringSelection(), true);
	}

	public void setAllChildrenSelected(boolean selected) {
		setChildrenSelected(getChildren(), selected);
	}

	public void setChildrenSelected(List theChildren, boolean selected) {
		for (int i = 0; i < theChildren.size(); i++) {
			EJBClientViewMigrationConfig config = (EJBClientViewMigrationConfig) theChildren.get(i);
			config.setIsSelected(selected);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.migration.J2EEMigrationConfig#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(MIGRATE_CMP1X_TO_2X))
			return new Boolean(false);
		if (propertyName.equals(DELETE_REMOTE_CLIENT_VIEW))
			return new Boolean(false);
		if (propertyName.equals(REUSE_REMOTE_CLIENT_VIEW_NAME))
			return new Boolean(false);
		if (propertyName.equals(LOCAL_CLIENT_VIEW_SUFFIX))
			return DEFAULT_LOCAL_SUFFIX;
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * Sets the deleteRemoteClientView.
	 * 
	 * @param deleteRemoteClientView
	 *            The deleteRemoteClientView to set
	 */
	public void setDeleteRemoteClientView(boolean deleteRemoteClientView) {
		this.setBooleanProperty(DELETE_REMOTE_CLIENT_VIEW, deleteRemoteClientView);
	}

	/**
	 * Sets the localClientViewSuffix.
	 * 
	 * @param localClientViewSuffix
	 *            The localClientViewSuffix to set
	 */
	public void setLocalClientViewSuffix(String localClientViewSuffix) {
		setProperty(LOCAL_CLIENT_VIEW_SUFFIX, localClientViewSuffix);
	}

	/**
	 * Sets the migrateCMP1xto2x.
	 * 
	 * @param migrateCMP1xto2x
	 *            The migrateCMP1xto2x to set
	 */
	public void setMigrateCMP1xto2x(boolean migrateCMP1xto2x) {
		setBooleanProperty(MIGRATE_CMP1X_TO_2X, migrateCMP1xto2x);
		setChildrenSelected(get11CMPChildren(), migrateCMP1xto2x);
	}

	/**
	 * Sets the reuseRemoteClientViewName.
	 * 
	 * @param reuseRemoteClientViewName
	 *            The reuseRemoteClientViewName to set
	 */
	public void setReuseRemoteClientViewName(boolean reuseRemoteClientViewName) {
		setBooleanProperty(REUSE_REMOTE_CLIENT_VIEW_NAME, reuseRemoteClientViewName);
		if (reuseRemoteClientViewName)
			setLocalClientViewSuffix(null);
	}

	/**
	 * Method traverseInheritance.
	 * 
	 * @param beanConfigs
	 */
	private void traverseInheritance(Set beanConfigs) {
		traverseInheritanceFull(beanConfigs);
	}

	/**
	 * If any bean in an inheritance tree is selected, select all the beans in the tree
	 */
	private void traverseInheritanceFull(Set beanConfigs) {
		List roots = getChildrenForRoots();
		for (int i = 0; i < roots.size(); i++) {
			EJBClientViewMigrationConfig rootChild = (EJBClientViewMigrationConfig) roots.get(i);
			traverseInheritanceFull(beanConfigs, rootChild);
		}

	}

	private void traverseInheritanceFull(Set beanConfigs, EJBClientViewMigrationConfig rootConfig) {
		List flattened = new ArrayList();
		boolean anyRequired = traverseSubtypesAndCheckForRequired(beanConfigs, flattened, rootConfig, false);
		if (anyRequired)
			beanConfigs.addAll(flattened);
	}

	//	/**
	//	 * For each required child, add all it's supertypes to the set
	//	 */
	//	private void traverseInheritanceUpOnly(Set beanConfigs) {
	//		Set visited = new HashSet();
	//		for (int i = 0; i < children.size(); i++) {
	//			EJBClientViewMigrationConfig config = (EJBClientViewMigrationConfig) children.get(i);
	//			traverseInheritanceUpOnly(beanConfigs, config, null, visited);
	//		}
	//	}

	//	/**
	//	 * Walk up the inheritance chain and select all parents
	//	 */
	//	private void traverseInheritanceUpOnly(Set beanConfigs, EJBClientViewMigrationConfig current,
	// EJBClientViewMigrationConfig subtype, Set visited) {
	//		if (visited.contains(current))
	//			return;
	//		visited.add(current);
	//		if (subtype != null)
	//			beanConfigs.add(current);
	//		else if (!isSelectedOrRequired(beanConfigs, current))
	//			return;
	//		initializeModuleExtHelper();
	//		if (modHelper != null) {
	//			EnterpriseBean supertype = modHelper.getSuperType(current.getEjb());
	//			if (supertype != null) {
	//				EJBClientViewMigrationConfig superConfig = getChildFor(supertype);
	//				traverseInheritanceUpOnly(beanConfigs, superConfig, current, visited);
	//			}
	//		}
	//	}

	/**
	 * Add every selected CMP bean and related beans to the set
	 */
	private void traverseRelationships(Set beanConfigs) {
		if (getChildren().isEmpty())
			return;
		int size = children.size();
		for (int i = 0; i < size; i++) {
			EJBClientViewMigrationConfig child = (EJBClientViewMigrationConfig) children.get(i);
			traverseRelationships(beanConfigs, child);
		}
	}

	/**
	 * Add the config and configs for related beans to the set
	 */
	private void traverseRelationships(Set beanConfigs, EJBClientViewMigrationConfig child) {
		if (!child.getEjb().isContainerManagedEntity())
			return;
		initializeModuleExtHelper();
		if (modHelper != null) {
			List roles = modHelper.getLocalRelationshipRoles_cmp11((ContainerManagedEntity) child.getEjb());
			if (!needsRelationshipTraversal(child, roles))
				return;
			beanConfigs.add(child);
			for (int i = 0; i < roles.size(); i++) {
				CommonRelationshipRole role = (CommonRelationshipRole) roles.get(i);
				ContainerManagedEntity opp = role.getTypeEntity();
				if (opp != null)
					beanConfigs.add(getChildFor(opp));
			}
		}
	}

	private boolean traverseSubtypesAndCheckForRequired(Set beanConfigs, List flattened, EJBClientViewMigrationConfig superTypeConfig, boolean required) {
		flattened.add(superTypeConfig);
		required = required || isSelectedOrRequired(beanConfigs, superTypeConfig);
		List subtypes = getSubtypes(superTypeConfig);
		for (int i = 0; i < subtypes.size(); i++) {
			EnterpriseBean subtype = (EnterpriseBean) subtypes.get(i);
			EJBClientViewMigrationConfig subTypeConfig = getChildFor(subtype);
			flattened.add(subTypeConfig);
			required = traverseSubtypesAndCheckForRequired(beanConfigs, flattened, subTypeConfig, required);
		}
		return required;
	}

	/**
	 * @return
	 */
	public boolean has20CMPs() {
		if (!getChildren().isEmpty()) {
			int size = getChildren().size();
			EJBClientViewMigrationConfig config;
			for (int i = 0; i < size; i++) {
				config = (EJBClientViewMigrationConfig) getChildren().get(i);
				if (config.is20CMP())
					return true;
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	public List get20CMPs() {
		List result = null;
		if (getChildren().isEmpty())
			return Collections.EMPTY_LIST;
		int size = getChildren().size();
		EJBClientViewMigrationConfig config;
		for (int i = 0; i < size; i++) {
			config = (EJBClientViewMigrationConfig) children.get(i);
			if (config.is20CMP()) {
				if (result == null)
					result = new ArrayList(size);
				result.add(config.getEjb());
			}
		}
		return result;
	}
}