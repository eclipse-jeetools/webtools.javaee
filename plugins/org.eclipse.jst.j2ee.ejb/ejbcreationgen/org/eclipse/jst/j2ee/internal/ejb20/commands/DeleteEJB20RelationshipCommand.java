/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.wst.common.emf.utilities.ExtendedEcoreUtil;


public class DeleteEJB20RelationshipCommand extends EJB20RelationshipCommand {
	/**
	 * Constructor for DeleteEJB20RelationshipCommand.
	 */
	public DeleteEJB20RelationshipCommand() {
		super();
	}

	/**
	 * Constructor for DeleteEJB20RelationshipCommand.
	 * 
	 * @param desc
	 */
	public DeleteEJB20RelationshipCommand(String desc) {
		super(desc);
	}

	public DeleteEJB20RelationshipCommand(EJBRelation aRelation) {
		setRelationship(aRelation);
	}

	/**
	 * Override to perform the necessary operation to update the Metadata.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		EJBJar jar = getEJBJar();
		if (jar != null) {
			Relationships rels = jar.getRelationshipList();
			if (rels != null) {
				reconcileRoleAttributes();
				if (!getEJBRelation().eIsProxy())
					ExtendedEcoreUtil.becomeProxy(getEJBRelation(), getEJBRelation().eResource());
				rels.getEjbRelations().remove(getEJBRelation());
				if (rels.getEjbRelations().isEmpty())
					jar.setRelationshipList(null);
			}
		}
	}

	/**
	 * Method reconcileRoleAttributes.
	 */
	private void reconcileRoleAttributes() {
		if (getEJBRelation() != null) {
			EJBRelationshipRole role1, role2;
			role1 = getEJBRelation().getFirstRole();
			role2 = getEJBRelation().getSecondRole();
			if (role1 != null) {
				role1.setRelationship(null);
				role1.reconcileAttributes();
			}
			if (role2 != null)
				role2.reconcileAttributes();
		}
	}

	/**
	 * Undo the the creation of the relationship.
	 */
	protected void undoMetadataGeneration() {
		EJBRelation rel = getEJBRelation();
		if (rel != null) {
			ExtendedEcoreUtil.removeProxy(rel, rel.eResource());
			EJBJar jar = getEJBJar();
			Relationships relList = jar.getRelationshipList();
			if (relList == null) {
				relList = getEjbFactory().createRelationships();
				jar.setRelationshipList(relList);
			}
			relList.getEjbRelations().add(rel);
			connectRoles();
		}
		super.undoMetadataGeneration();
	}
}