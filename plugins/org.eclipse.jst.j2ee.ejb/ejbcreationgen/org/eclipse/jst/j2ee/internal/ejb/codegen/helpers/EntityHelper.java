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
package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBExtensionFilter;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.impl.RequiredLocalRelationshipRoleFilter;
import org.eclipse.jst.j2ee.ejb.impl.RequiredRelationshipRoleFilter;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.Navigator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Insert the type's description here. Creation date: (9/6/2000 1:19:36 PM)
 * 
 * @author: Administrator
 */
public class EntityHelper extends EnterpriseBeanHelper {
	private List fAttributeHelpers;
	private List fRoleHelpers;
	private List fKeyPropagationHelpers;
	private EJBClassReferenceHelper fKeyHelper;
	private boolean migrationCleanup;
	private Boolean localReqRolesChange;
	private Boolean reqRolesChange;

	/**
	 * EntityCodegenHelper constructor comment.
	 * 
	 * @param aMetaObject
	 *            org.eclipse.emf.ecore.EObject
	 */
	public EntityHelper(EObject aMetaObject) {
		super(aMetaObject);
	}

	protected void addAttributeHelper(AttributeHelper aHelper) {
		if (aHelper != null)
			getAttributeHelpers().add(aHelper);
	}

	protected void addRoleHelper(RoleHelper aHelper) {
		if (aHelper != null) {
			if (aHelper.isKeyPropagationHelper())
				getKeyPropagationHelpers().add(aHelper);
			else
				getRoleHelpers().add(aHelper);
		}
	}

	protected static IEJBCodegenHandler getEntiyCodegenHandler(EnterpriseBean anEJB) {
		return EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(ProjectUtilities.getProject(anEJB));
	}

	/**
	 * append method comment.
	 */
	public void append(EJBGenerationHelper aHelper) {
		if (aHelper != null) {
			if (aHelper.isPersistentFeatureHelper()) {
				aHelper.setParent(this);
				appendPersistentFeature((FeatureHelper) aHelper);
			} else
				super.append(aHelper);
		}
	}

	/**
	 * appendClassReference method comment.
	 */
	protected void appendClassReference(EJBClassReferenceHelper helper) {
		if (helper.isKeyHelper())
			setKeyHelper(helper);
		else
			super.appendClassReference(helper);
	}

	/**
	 * appendClassReference method comment.
	 */
	protected void appendPersistentFeature(FeatureHelper aHelper) {
		if (aHelper.isAttributeHelper())
			addAttributeHelper((AttributeHelper) aHelper);
		else if (aHelper.isRoleHelper())
			addRoleHelper((RoleHelper) aHelper);
	}

	/**
	 * Returns the list of attribute helpers.
	 */
	public List getAttributeHelpers() {
		if (fAttributeHelpers == null)
			fAttributeHelpers = new ArrayList();
		return fAttributeHelpers;
	}

	public Entity getEntity() {
		return (Entity) getMetaObject();
	}

	/**
	 * Returns a list of all feature helpers.
	 */
	public List getFeatureHelpers() {
		List all = new ArrayList();
		all.addAll(getAttributeHelpers());
		all.addAll(getRoleHelpers());
		return all;
	}

	/**
	 * Return any attribute helpers that are defined to be part of the Key. This will only return
	 * the Key attribute helpers for those key attributes which are changing. Creation date:
	 * (9/6/2000 1:23:21 PM)
	 * 
	 * @return java.util.List
	 */
	public List getKeyAttributeHelpers() {
		return selectKeyFeatures(getAttributeHelpers());
	}

	/**
	 * Return any attribute helpers that are defined to be part of the Key. This will only return
	 * the Key attribute helpers for those key attributes which are changing. It will also create
	 * update attribute helpers for the case where a current delete attribute matches the same index
	 * as a current create helper. This is needed to preserve order of fields in the Key class.
	 * 
	 * @return java.util.List
	 */
	public List getKeyAttributeHelpersForKeyClass() {
		List helpers = getKeyAttributeHelpers();
		List deleteHelpers = null;
		List createHelpers = null;
		AttributeHelper helper;
		if (helpers.size() > 1) {
			for (int i = 0; i < helpers.size(); i++) {
				helper = (AttributeHelper) helpers.get(i);
				if (helper.isDelete()) {
					if (deleteHelpers == null)
						deleteHelpers = new ArrayList();
					deleteHelpers.add(helper);
				} else if (helper.isCreate()) {
					if (createHelpers == null)
						createHelpers = new ArrayList();
					createHelpers.add(helper);
				}
			}
		}
		if (deleteHelpers != null && createHelpers != null)
			setupUpdateHelpers(helpers, deleteHelpers, createHelpers);
		return helpers;
	}

	/**
	 * Create update attribute helpers for matching delete and create helpers.
	 * 
	 * @param helpers
	 * @param deleteHelpers
	 * @param createHelpers
	 */
	private void setupUpdateHelpers(List helpers, List deleteHelpers, List createHelpers) {
		AttributeHelper dhelper = null, chelper = null;
		int dindex, cindex;
		CMPAttribute datt = null, catt = null;
		ContainerManagedEntity dcmp = (ContainerManagedEntity) getOldMetaObject();
		ContainerManagedEntity ccmp = (ContainerManagedEntity) getEjb();
		for (int i = 0; i < deleteHelpers.size(); i++) {
			dhelper = (AttributeHelper) deleteHelpers.get(i);
			datt = dhelper.getOldAttribute();
			if (datt == null)
				continue;
			dindex = dcmp.getKeyAttributes().indexOf(datt);
			for (int j = 0; j < createHelpers.size(); j++) {
				chelper = (AttributeHelper) createHelpers.get(j);
				catt = chelper.getAttribute();
				if (catt == null)
					continue;
				cindex = ccmp.getKeyAttributes().indexOf(catt);
				if (dindex == cindex) {
					createUpdateAttributeHelper(dhelper, chelper, helpers);
					break;
				}
			}
		}
	}

	/**
	 * Method createUpdateAttributeHelper.
	 * 
	 * @param dhelper
	 * @param chelper
	 * @param helpers
	 */
	private void createUpdateAttributeHelper(AttributeHelper dhelper, AttributeHelper chelper, List helpers) {
		chelper.setMetadataHistory(dhelper.getMetadataHistory());
		chelper.setUpdate();
		helpers.remove(dhelper);
	}


	/**
	 * Returns a list of all key feature helpers.
	 */
	public List getKeyFeatureHelpers() {
		return selectKeyFeatures(getFeatureHelpers());
	}

	/**
	 * Returns the key class reference helper.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getKeyHelper() {
		return fKeyHelper;
	}

	/**
	 * Returns the list of KeyPropagationHelpers.
	 */
	public List getKeyPropagationHelpers() {
		if (fKeyPropagationHelpers == null)
			fKeyPropagationHelpers = new ArrayList();
		return fKeyPropagationHelpers;
	}

	/**
	 * Returns a list of key role helpers.
	 * 
	 * @return java.util.List
	 */
	public List getKeyRoleHelpers() {
		return selectKeyFeatures(getRoleHelpers());
	}

	public CMPAttribute getPrimaryKeyAttribute() {
		if (getEntity().isContainerManagedEntity())
			return ((ContainerManagedEntity) getEntity()).getPrimKeyField();
		return null;
	}

	protected List getRequiredRoles(Entity entity) {
		return getRequiredRoles(entity, RequiredRelationshipRoleFilter.singleton());
	}

	protected List getLocalRequiredRoles(Entity entity) {
		return getRequiredRoles(entity, RequiredLocalRelationshipRoleFilter.singleton());
	}

	protected List getRequiredRoles(Entity entity, EJBExtensionFilter filter) {
		if (entity != null && entity.isContainerManagedEntity()) {
			((ContainerManagedEntity) entity).getFilteredFeatures(filter);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * Returns the list of role helpers.
	 */
	public List getRoleHelpers() {
		if (fRoleHelpers == null)
			fRoleHelpers = new ArrayList();
		return fRoleHelpers;
	}

	/**
	 * Returns the list of RoleHelpers if not empty or KeyPropagationHelpers. if not empty.
	 */
	public List getRoleOrKeyPropagationHelpers() {
		List roleHelpers, keyPropHelpers, helpers = null;
		roleHelpers = getRoleHelpers();
		keyPropHelpers = getKeyPropagationHelpers();
		if (!roleHelpers.isEmpty() && keyPropHelpers.isEmpty())
			helpers = roleHelpers;
		else if (roleHelpers.isEmpty() && !keyPropHelpers.isEmpty())
			helpers = keyPropHelpers;
		if (helpers == null)
			helpers = Collections.EMPTY_LIST;
		return helpers;
	}



	/**
	 * @param roles
	 * @return
	 */
	private List createSourceKeyChangeRoleHelpers(List roles) {
		if (!roles.isEmpty()) {
			List helpers = new ArrayList(roles.size());
			CommonRelationshipRole role, oldRole = null;
			RoleHelper helper;
			for (int i = 0; i < roles.size(); i++) {
				role = (CommonRelationshipRole) roles.get(i);
				helper = new RoleHelper(role);
				helper.setParent(this);
				helper.setUpdate();
				oldRole = getOldRole((EnterpriseBean) getOldMetaObject(), role.getName());
				helper.getMetadataHistory().setOldMetadata(oldRole);
				helpers.add(helper);
			}
			return helpers;
		}
		return Collections.EMPTY_LIST;
	}

	protected CommonRelationshipRole getOldRole(EnterpriseBean anEJB, String roleName) {
		getCodegenHandler();
		return codegenHandler.getOldRole(anEJB, roleName);

	}

	/**
	 * Return a list of RoleHelpers that need to be created for all roles pointing to this entity.
	 * It will be filtered based on existing RoleHelpers from getRoleHelpers(). This will only
	 * return role helpers if the key has changed for the entity.
	 * 
	 * Currently, this is only used to update link classes when the source EJB's key class changes.
	 * 
	 * @return List
	 */
	public List getDistinctSourceKeyChangeBackwardRoleHelpers(EnterpriseBean ejb) {
		List helpers = null;
		if (isKeyClassChanging()) {
			List roles = getLocalRelationshipRoles(ejb);
			if (roles != null && !roles.isEmpty()) {
				roles = filterForwardRolesAndRoleHelpers(roles);
				helpers = createSourceKeyChangeRoleHelpers(roles);
			}
		}
		if (helpers == null)
			helpers = Collections.EMPTY_LIST;
		return helpers;
	}

	public List getLocalRelationshipRoles(EnterpriseBean ejb) {
		if (ejb.isContainerManagedEntity()) {
			if (ejb.getVersionID() <= J2EEVersionConstants.EJB_1_1_ID)
				return getCodegenHandler().getExtendedEjb11RelationshipRolesWithType(ejb);
			return ((ContainerManagedEntity) ejb).getRoles();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @param roles
	 */
	private List filterForwardRolesAndRoleHelpers(List roles) {
		List helpers = getRoleHelpers();
		List removedRoles = null;
		CommonRelationshipRole role;
		RoleHelper helper;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			if (role.isForward()) {
				if (removedRoles == null)
					removedRoles = new ArrayList();
				removedRoles.add(role);
				continue;
			}
			for (int j = 0; j < helpers.size(); j++) {
				helper = (RoleHelper) helpers.get(j);
				if (helper.getRole().equals(role)) {
					if (removedRoles == null)
						removedRoles = new ArrayList();
					removedRoles.add(role);
				}
			}
		}
		if (removedRoles != null) {
			if (removedRoles.size() == roles.size())
				return Collections.EMPTY_LIST;
			List result = new ArrayList(roles.size());
			result.addAll(roles);
			result.removeAll(removedRoles);
			return result;
		}
		return roles;
	}


	public static boolean hasLocalRequiredRolesWithSupertype(EnterpriseBean anEJB) throws GenerationException {
		if (anEJB == null || !anEJB.isContainerManagedEntity())
			return false;
		ContainerManagedEntity superCMP = (ContainerManagedEntity) getSupertype(anEJB);
		if (superCMP == null)
			return false;
		IEJBCodegenHandler handler = getEntiyCodegenHandler(anEJB);
		if (handler != null)
			return !((ContainerManagedEntity) anEJB).getFilteredFeatures(RequiredLocalRelationshipRoleFilter.singleton()).isEmpty();
		return false;
	}

	public static boolean hasLocalRoles(EnterpriseBean anEJB) throws GenerationException {
		if (anEJB == null || !anEJB.isContainerManagedEntity())
			return false;
		IEJBCodegenHandler handler = getEntiyCodegenHandler(anEJB);
		if (handler != null)
			return handler.hasLocalRelationshipRoles(anEJB);
		return false;
	}

	public boolean hasPrimaryKeyAttribute() {
		CMPAttribute att = getPrimaryKeyAttribute();
		return att != null && !att.eIsProxy();
	}

	/**
	 * Returns the list of role helpers.
	 */
	public boolean hasRoleHelpers() {
		return !getRoleHelpers().isEmpty();
	}

	/**
	 * Returns true if the key shape is changing by checking the old meta-data.
	 */
	protected boolean isBasicKeyShapeChanging() {
		Entity oldEntity = (Entity) getOldMetaObject();
		if (oldEntity != null && oldEntity.isContainerManagedEntity()) {
			ContainerManagedEntity oldCmp = (ContainerManagedEntity) oldEntity;
			List oldKeys, newKeys;
			oldKeys = oldCmp.getKeyAttributes();
			newKeys = ((ContainerManagedEntity) getEntity()).getKeyAttributes();
			if (oldKeys.size() != newKeys.size())
				return true;
			CMPAttribute oldAtt, newAtt;
			boolean found;
			for (int i = 0; i < oldKeys.size(); i++) {
				oldAtt = (CMPAttribute) oldKeys.get(i);
				found = false;
				for (int ii = 0; ii < newKeys.size(); ii++) {
					newAtt = (CMPAttribute) newKeys.get(ii);
					if (newAtt.getName().equals(oldAtt.getName()) && newAtt.getType() == oldAtt.getType()) {
						found = true;
						break;
					}
				}
				if (!found)
					return true;
			}
		}
		return false;

	}

	public boolean isKeyChanging() {
		return isKeyClassChanging() || isKeyShapeChanging();
	}

	public boolean isKeyClassChanging() {
		if ((getOldMetaObject() != null) && (((Entity) getOldMetaObject()).getPrimaryKeyName() != null))
			return !((Entity) getOldMetaObject()).getPrimaryKeyName().equals(getEntity().getPrimaryKeyName());
		return false;
	}

	/**
	 * Returns true if the key shape is changing in any way.
	 */
	public boolean isKeyShapeChanging() {
		boolean shapeChanged = isKeyShapeChangingByFeature();
		if (!shapeChanged)
			shapeChanged = isKeyShapeChangingByPropagation();
		return shapeChanged;
	}

	/**
	 * Returns true if the key shape is changing due to an attribute or role being added or removed.
	 */
	public boolean isKeyShapeChangingByFeature() {
		boolean shapeChanged = !getKeyFeatureHelpers().isEmpty();
		if (!shapeChanged)
			shapeChanged = isRemovingKeyFeature();
		return shapeChanged;
	}

	/**
	 * Returns true if the key shape is changing due to a propagation.
	 */
	public boolean isKeyShapeChangingByPropagation() {
		return isKeyShapeChangingByRolePropagation() || isBasicKeyShapeChanging();
	}

	/**
	 * Returns true if the key shape is changing due to a propagation.
	 */
	protected boolean isKeyShapeChangingByRolePropagation() {
		return !getKeyPropagationHelpers().isEmpty();
	}

	/**
	 * Return true if the key shape is changing due to a role being added or removed from the key or
	 * if there is a key role propogation taking place.
	 */
	public boolean isKeyShapeChangingByRole() {
		if (isKeyShapeChangingByRolePropagation())
			return true;
		List helpers = getRoleHelpers();
		RoleHelper helper;
		for (int i = 0; i < helpers.size(); i++) {
			helper = (RoleHelper) helpers.get(i);
			if (helper.isOrWasKey())
				return true;
		}
		return false;
	}


	/**
	 * Return true if the current or old entity has any key roles (including inherited roles).
	 */
	public boolean hasOrHadKeyRoles(Navigator nav) {
		Entity current = getEntity();
		if (current != null) {
			try {
				if (!EJBGenHelpers.getEntityKeyRoles(current, this, nav).isEmpty())
					return true;
			} catch (GenerationException e) {
			}
		}
		Entity old = (Entity) getOldMetaObject();
		if (old != null) {
			try {
				if (!EJBGenHelpers.getEntityKeyRoles(old, this, nav).isEmpty())
					return true;
			} catch (GenerationException e) {
			}
		}
		return false;
	}

	/**
	 * Returns true if an attribute is going from being a key to not being a key.
	 */
	public boolean isRemovingKeyAttribute() {
		List attHelpers = getAttributeHelpers();
		AttributeHelper attHelper;
		for (int i = 0; i < attHelpers.size(); i++) {
			attHelper = (AttributeHelper) attHelpers.get(i);
			if (!attHelper.isKey() && attHelper.getOldAttribute() != null && attHelper.getOldAttribute().isKey())
				return true;
		}
		return false;
	}

	/**
	 * Returns true if a role or attribute is going from being a key to not being a key.
	 */
	public boolean isRemovingKeyFeature() {
		boolean removingKey = isRemovingKeyAttribute();
		if (!removingKey)
			removingKey = isRemovingKeyRole();
		return removingKey;
	}

	/**
	 * Returns true if a role is going from being a key to not being a key.
	 */
	public boolean isRemovingKeyRole() {
		List roleHelpers = getRoleHelpers();
		RoleHelper roleHelper;
		for (int i = 0; i < roleHelpers.size(); i++) {
			roleHelper = (RoleHelper) roleHelpers.get(i);
			if (!roleHelper.isKey() && roleHelper.getOldRole() != null && roleHelper.getOldRole().isKey())
				return true;
		}
		return false;
	}

	public boolean isRequiredRolesChanging() {
		if (reqRolesChange == null) {
			if (isDeleteAll())
				reqRolesChange = Boolean.FALSE;
			else {
				Entity oldEntity = (Entity) getOldMetaObject();
				List reqRoles, oldReqRoles;
				reqRoles = getRequiredRoles(getEntity());
				oldReqRoles = getRequiredRoles(oldEntity);
				reqRolesChange = new Boolean(reqRoles.size() != oldReqRoles.size());
			}
		}
		return reqRolesChange.booleanValue();
	}

	public boolean isLocalRequiredRolesChanging() {
		if (localReqRolesChange == null) {
			if (isDeleteAll())
				localReqRolesChange = Boolean.FALSE;
			else {
				Entity oldEntity = (Entity) getOldMetaObject();
				List reqRoles, oldReqRoles;
				reqRoles = getLocalRequiredRoles(getEntity());
				oldReqRoles = getLocalRequiredRoles(oldEntity);
				localReqRolesChange = new Boolean(reqRoles.size() != oldReqRoles.size());
			}
		}
		return localReqRolesChange.booleanValue();
	}

	/**
	 * Selects key features from a list of features.
	 */
	protected List selectKeyFeatures(List someFeatures) {
		List keys = new ArrayList(someFeatures.size());
		FeatureHelper nextKey = null;
		Iterator someFeaturesIter = someFeatures.iterator();
		while (someFeaturesIter.hasNext()) {
			nextKey = (FeatureHelper) someFeaturesIter.next();
			if (nextKey.isKey())
				keys.add(nextKey);
		}
		return keys;
	}

	/**
	 * Insert the method's description here. Creation date: (10/11/00 9:40:18 AM)
	 * 
	 * @param newKeyHelper
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public void setKeyHelper(EJBClassReferenceHelper newKeyHelper) {
		fKeyHelper = newKeyHelper;
	}

	/**
	 * Returns the migrationCleanup.
	 * 
	 * @return boolean
	 */
	public boolean isMigrationCleanup() {
		return migrationCleanup;
	}

	/**
	 * Sets the migrationCleanup.
	 * 
	 * @param migrationCleanup
	 *            The migrationCleanup to set
	 */
	public void setMigrationCleanup(boolean migrationCleanup) {
		this.migrationCleanup = migrationCleanup;
	}
}