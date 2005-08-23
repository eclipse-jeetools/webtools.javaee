/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.util;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbFactoryImpl;

public abstract class CommonRelationshipAttributeMaintenanceAdapter extends AdapterImpl {
	protected static EjbPackage EJB_PACK = EjbFactoryImpl.getPackage();
	/**
	 * Constructor for CommonRelationshipAttributeMaintenanceAdapter.
	 */
	public CommonRelationshipAttributeMaintenanceAdapter() {
		super();
	}
	/**
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(Notification)
	 */
	public void notifyChanged(Notification msg) {
		switch (msg.getEventType()) {
			case Notification.ADD :
				addedNotification(msg);
				break;
			case Notification.REMOVE :
				removedNotification(msg);
				break;
		}
	}
	protected void addedNotification(Notification msg) {
		if ((EStructuralFeature)msg.getFeature() == EJB_PACK.getContainerManagedEntity_KeyAttributes())
			addedKeyAttribute((ContainerManagedEntity) msg.getNotifier(), (CMPAttribute) msg.getNewValue());
		else if (isRelationshipsSF((EStructuralFeature)msg.getFeature()))
			added();
		else if (isRelationshipRolesSF((EStructuralFeature)msg.getFeature()))
			addedRole((CommonRelationshipRole) msg.getNewValue());
	}
	protected void removedNotification(Notification msg) {
		if ((EStructuralFeature)msg.getFeature() == EJB_PACK.getContainerManagedEntity_KeyAttributes())
			removedKeyAttribute((ContainerManagedEntity) msg.getNotifier(), (CMPAttribute) msg.getOldValue());
		else if (isRelationshipsSF((EStructuralFeature)msg.getFeature()))
			removed();
		else if (isRelationshipRolesSF((EStructuralFeature)msg.getFeature()))
			removedRole((CommonRelationshipRole) msg.getOldValue());
	}
	protected abstract boolean isRelationshipRolesSF(EObject sf);
	protected abstract boolean isRelationshipsSF(EObject sf);
	//Same as the add
	private void removedKeyAttribute(ContainerManagedEntity aCMPEntity, CMPAttribute cmpAttribute) {
		addedKeyAttribute(aCMPEntity, cmpAttribute);
	}
	protected void addedKeyAttribute(ContainerManagedEntity aCMPEntity, CMPAttribute newAttribute) {
		CommonRelationshipRole role, inverse;
		role = getRole(aCMPEntity);
		if (role != null && !role.isForward()) {
			inverse = role.getOppositeAsCommonRole();
			if (inverse != null)
				inverse.reconcileAttributes();
		}
	}
	protected CommonRelationshipRole getRole(ContainerManagedEntity aCMPEntity) {
	    CommonRelationship relation = getRelationship();
	    if (relation == null) return null;
		List roles = relation.getCommonRoles();
		if (roles.isEmpty())
			return null;
		CommonRelationshipRole role = null;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			if (role.getSourceEntity() == aCMPEntity)
				return role;
		}
		return role;
	}
	protected CommonRelationship getRelationship() {
		return  (CommonRelationship) getTarget();
	}
	protected void reconcileRoleAttributes() {
		List roles = getRelationship().getCommonRoles();
		if (roles.isEmpty())
			return;
		CommonRelationshipRole role = null;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			role.reconcileAttributes();
		}
	}
	protected void added() {
		reconcileRoleAttributes();
	}
	protected void removed() {
		if (getRelationship().getCommonRoles().size() > 1) {
			//remove one force a disconnect
			getRelationship().getCommonRoles().remove(0);
		}
	}
	/**
	* Add this Adapter to @aRole and the ContainerManagedEntity
	* that the ContainerManagedEntityExtension container is pointing to.
	* This only works if @aRole is contained.
	*/
	protected void addAdaptors(CommonRelationshipRole aRole) {
		removeAdaptors(aRole); //to ensure this adapter is not added twice
		aRole.eAdapters().add(this);
		ContainerManagedEntity cmp = aRole.getSourceEntity();
		if (cmp != null)
			cmp.eAdapters().add(this);
	}
	/**
	 * Remove this Adapter from @aRole and the ContainerManagedEntity
	 * that the ContainerManagedEntityExtension container is pointing to.
	 * This only works if @aRole is contained.
	 */
	protected void removeAdaptors(CommonRelationshipRole aRole) {
		aRole.eAdapters().remove(this);
		ContainerManagedEntity cmp = aRole.getSourceEntity();
		if (cmp != null)
			cmp.eAdapters().remove(this);
	}
	protected void addedRole(CommonRelationshipRole aRole) {
		addAdaptors(aRole);
		if ((getRelationship().eContainer() != null) && rolesFullyReflected())
			reconcileRoleAttributes();
	}
	protected boolean rolesFullyReflected() {
		List roles = getRelationship().getCommonRoles();
		if (roles.isEmpty())
			return false;
		CommonRelationshipRole role = null;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			if (role.getSourceEntity() == null)
				return false;
		}
		return true;
	}
		
	protected void removedRole(CommonRelationshipRole aRole) {
		removeAdaptors(aRole);
		if (getRelationship().eContainer() != null) {
			aRole.reconcileAttributes();
			reconcileRoleAttributes();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier notifier) {
		//Only set the target if it is a CommonRelationship
		if (notifier == null || notifier instanceof CommonRelationship)
			super.setTarget(notifier);
	}

}

