/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import java.util.List;

import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.MultiplicityKind;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;


public class UpdatePersistent20RoleCommand extends Persistent20RoleCommand {
	protected boolean wasForward;

	/**
	 * Constructor for UpdatePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 */
	public UpdatePersistent20RoleCommand(IRootCommand parent, EJBRelationshipRole aRole) {
		super(parent, aRole.getName());
		initializeFromRole(aRole);
	}

	/**
	 * Constructor for UpdatePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 */
	public UpdatePersistent20RoleCommand(IRootCommand parent, EJBRelationshipRole aRole, boolean shouldGenJava) {
		super(parent, aRole.getName(), shouldGenJava);
		initializeFromRole(aRole);
	}

	/**
	 * Constructor for UpdatePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public UpdatePersistent20RoleCommand(IRootCommand parent, EJBRelationshipRole aRole, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aRole.getName(), shouldGenJava, shouldGenMetadata);
		initializeFromRole(aRole);
	}

	protected void initializeFromRole(EJBRelationshipRole aRole) {
		if (aRole != null) {
			setIsMany(aRole.getMultiplicity().getValue() == MultiplicityKind.MANY);
			setIsCascadeDelete(aRole.isCascadeDelete());
			CMRField field = aRole.getCmrField();
			if (field != null) {
				setCmrFieldName(field.getName());
				setCmrFieldCollectionTypeName(field.getCollectionTypeName());
			}
			wasForward = aRole.isForward();
			setCommonRole(aRole);
		}
	}

	/**
	 * Update the role.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		EJBRelationshipRole aRole = getEJBRelationshipRole();
		if (aRole != null) {
			//removeREADintents() ; // Updating this role may impact our opposite that is already
			// finished
			aRole.setRoleName(getName());
			setRoleMultiplicity(aRole);
			updateCmrFieldInformation(aRole);
			if (isCascadeDelete())
				aRole.setCascadeDelete(true);
			else
				aRole.unsetCascadeDelete();
			boolean removedAttributes = removeAttributesIfNecessary();
			updateForwardFlag(aRole);
			if (!removedAttributes)
				aRole.reconcileAttributes();
		}
	}

	/**
	 * Method removeAttributesIfNecessary.
	 * 
	 * @return boolean
	 */
	private boolean removeAttributesIfNecessary() {
		if (wasForward && !isForward()) {
			List attributes = getCommonRole().getAttributes();
			if (!attributes.isEmpty()) {
				ContainerManagedEntity cmp = getCommonRole().getSourceEntity();
				if (cmp != null) {
					cmp.getPersistentAttributes().removeAll(attributes);
					cmp.getKeyAttributes().removeAll(attributes);
				}
				attributes.clear();
			}
			return true;
		}
		return false;
	}


	protected void updateForwardFlag(EJBRelationshipRole aRole) {
		boolean currentForwardFlag = aRole.isForward();
		if (currentForwardFlag != isForward()) {
			EJBRelation relation = aRole.getRelationship();
			if (relation != null) {
				if (isForward())
					relation.setFoward(aRole);
			}
		}
	}

	protected void updateCmrFieldInformation(EJBRelationshipRole aRole) {
		CMRField field = aRole.getCmrField();
		aRole.setName(null); //allow the name to be recomputed
		boolean createdField = false;
		if (field == null && hasCmrFieldUpdates()) {
			field = getEJBFactory().createCMRField();
			createdField = true;
		}
		if (field == null)
			return;
		if (getCmrFieldName() != null) {
			field.setName(getCmrFieldName());
			if (getCmrFieldCollectionTypeName() != null)
				field.setCollectionTypeName(getCmrFieldCollectionTypeName());
			else
				field.setCollectionType(null);
		} else
			aRole.setCmrField(null);
		if (createdField)
			aRole.setCmrField(field);
	}

	protected boolean hasCmrFieldUpdates() {
		return getCmrFieldName() != null || getCmrFieldCollectionTypeName() != null;
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setUpdate();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		super.setupInverseHelper();
		getInverseHelper().setUpdate();
	}

	/**
	 * Update the role with the old information.
	 */
	protected void undoMetadataGeneration() {
		super.undoMetadataGeneration();
		//still need to implement
	}
}