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

import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * Insert the type's description here. Creation date: (8/24/2001 4:46:50 PM)
 * 
 * @author: Administrator
 */
public class PrimaryKeyHelper extends EJBClassReferenceHelper {
	protected boolean initializedKeyAttributeHelpers = false;
	protected boolean initializedRoleHelpers = false;
	protected List keyAttributeHelpers;
	protected List roleHelpers;

	/**
	 * PrimaryKeyHelper constructor comment.
	 * 
	 * @param aMetaObject
	 *            org.eclipse.emf.ecore.EObject
	 */
	public PrimaryKeyHelper(org.eclipse.emf.ecore.EObject aMetaObject) {
		super(aMetaObject);
	}

	protected AttributeHelper createAttributeHelper(CMPAttribute anAttribute) {
		AttributeHelper helper = new AttributeHelper(anAttribute);
		helper.setMetadataHistory(new AttributeHistory());
		helper.setCreate();
		return helper;
	}

	protected RoleHelper createRoleHelper(CommonRelationshipRole anRole) {
		RoleHelper helper = new RoleHelper(anRole);
		helper.setMetadataHistory(new MetadataHistory());
		helper.setCreate();
		return helper;
	}

	protected void filterRoleAttributes(List keyAttributes) {
		List roleHelpersList = getRoleHelpers();
		List roleAttributes;
		if (roleHelpersList.isEmpty())
			return;
		CMPAttribute att, roleAtt;
		RoleHelper roleHelper;
		Iterator it = keyAttributes.iterator();
		while (it.hasNext()) {
			att = (CMPAttribute) it.next();
			boolean found = false;
			for (int i = 0; i < roleHelpersList.size(); i++) {
				roleHelper = (RoleHelper) roleHelpersList.get(i);
				roleAttributes = roleHelper.getRole().getAttributes();
				for (int j = 0; j < roleAttributes.size(); j++) {
					roleAtt = (CMPAttribute) roleAttributes.get(j);
					if (roleAtt == att) {
						found = true;
						it.remove();
						break;
					}
				}
				if (found)
					break;
			}
		}
	}

	protected EntityHelper getEntityParentHelper() {
		return (EntityHelper) getParent();
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 4:51:34 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List getKeyAttributeHelpers() {
		if (!initializedKeyAttributeHelpers) {
			initializedKeyAttributeHelpers = true;
			initializeKeyAttributeHelpers();
		}
		return primGetKeyAttributeHelpers();
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 5:26:30 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List getRoleHelpers() {
		if (!initializedRoleHelpers) {
			initializedRoleHelpers = true;
			initializeRoleHelpers();
		}
		return primGetRoleHelpers();
	}

	protected void initializeKeyAttributeHelpers() {
		if (!isCreate() || !getEjb().isContainerManagedEntity())
			return;
		List parentHelpers = getEntityParentHelper().getKeyAttributeHelpers();
		List actualKeys = new ArrayList(((ContainerManagedEntity) getEjb()).getKeyAttributes());
		filterRoleAttributes(actualKeys);
		List allHelpers = new ArrayList(actualKeys.size());
		allHelpers.addAll(parentHelpers);
		CMPAttribute attribute;
		AttributeHelper helper;
		boolean found = false;
		for (int i = 0; i < actualKeys.size(); i++) {
			found = false;
			attribute = (CMPAttribute) actualKeys.get(i);
			for (int ii = 0; ii < parentHelpers.size(); ii++) {
				helper = (AttributeHelper) parentHelpers.get(ii);
				if (helper.getAttribute() == attribute) {
					found = true;
					break;
				}
			}
			if (!found)
				allHelpers.add(createAttributeHelper(attribute));
		}
		if (allHelpers.size() != parentHelpers.size())
			setKeyAttributeHelpers(allHelpers);
	}

	protected void initializeRoleHelpers() {
		if (!isCreate() || !getEjb().isContainerManagedEntity())
			return;
		List parentHelpers = getEntityParentHelper().getKeyRoleHelpers();
		List actualRoles = get11LocalRelationshipRoles(getEjb());
		List allHelpers = new ArrayList(actualRoles.size());
		allHelpers.addAll(parentHelpers);
		CommonRelationshipRole role;
		RoleHelper helper;
		boolean found = false;
		for (int i = 0; i < actualRoles.size(); i++) {
			found = false;
			role = (CommonRelationshipRole) actualRoles.get(i);
			for (int ii = 0; ii < parentHelpers.size(); ii++) {
				helper = (RoleHelper) parentHelpers.get(ii);
				if (helper.getRole() == role) {
					found = true;
					break;
				}
			}
			if (!found && role.isKey())
				allHelpers.add(createRoleHelper(role));
		}
		if (allHelpers.size() != parentHelpers.size())
			setRoleHelpers(allHelpers);
	}

	protected List get11LocalRelationshipRoles(EnterpriseBean ejb) {
		List list = null;
		if (ejb.getVersionID() <= J2EEVersionConstants.EJB_1_1_ID && getCodegenHandler() != null) {
			list = getCodegenHandler().getExtendedEjb11RelationshipRolesWithType(ejb);
		}
		return null == list ? Collections.EMPTY_LIST : list;
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 5:25:42 PM)
	 * 
	 * @return boolean
	 */
	public boolean isInitializedRoleHelpers() {
		return initializedRoleHelpers;
	}

	public boolean isKeyHelper() {
		return true;
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 4:51:34 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List primGetKeyAttributeHelpers() {
		if (keyAttributeHelpers == null)
			return getEntityParentHelper().getKeyAttributeHelpers();
		return keyAttributeHelpers;
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 4:51:34 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List primGetRoleHelpers() {
		if (roleHelpers == null)
			return getEntityParentHelper().getRoleHelpers();
		return roleHelpers;
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 5:25:42 PM)
	 * 
	 * @param newInitializedRoleHelpers
	 *            boolean
	 */
	protected void setInitializedRoleHelpers(boolean newInitializedRoleHelpers) {
		initializedRoleHelpers = newInitializedRoleHelpers;
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 4:51:34 PM)
	 * 
	 * @param newKeyAttributeHelpers
	 *            java.util.List
	 */
	protected void setKeyAttributeHelpers(java.util.List newKeyAttributeHelpers) {
		keyAttributeHelpers = newKeyAttributeHelpers;
	}

	/**
	 * Insert the method's description here. Creation date: (8/24/2001 5:26:30 PM)
	 * 
	 * @param newRoleHelpers
	 *            java.util.List
	 */
	protected void setRoleHelpers(java.util.List newRoleHelpers) {
		roleHelpers = newRoleHelpers;
	}
}