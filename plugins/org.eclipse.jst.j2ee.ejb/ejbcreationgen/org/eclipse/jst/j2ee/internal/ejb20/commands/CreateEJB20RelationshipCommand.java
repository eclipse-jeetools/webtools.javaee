/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.internal.ejb.commands.PersistentRoleCommand;


public class CreateEJB20RelationshipCommand extends EJB20RelationshipCommand {
	protected boolean createdRelationshipList = false;

	/**
	 * Constructor for CreateEJB20RelationshipCommand.
	 */
	public CreateEJB20RelationshipCommand() {
		super();
	}

	/**
	 * Constructor for CreateEJB20RelationshipCommand.
	 * 
	 * @param desc
	 */
	public CreateEJB20RelationshipCommand(String desc) {
		super(desc);
	}

	/**
	 * Constructor for CreateEJB20RelationshipCommand.
	 * 
	 * @param desc
	 * @param firstRole
	 * @param secondRole
	 */
	public CreateEJB20RelationshipCommand(String desc, EJBRelationshipRole firstRole, EJBRelationshipRole secondRole) {
		super(desc, firstRole, secondRole);
	}

	/**
	 * Constructor for CreateEJB20RelationshipCommand.
	 * 
	 * @param desc
	 * @param firstRoleCommand
	 * @param secondRoleCommand
	 */
	public CreateEJB20RelationshipCommand(String desc, PersistentRoleCommand firstRoleCommand, PersistentRoleCommand secondRoleCommand) {
		super(desc, firstRoleCommand, secondRoleCommand);
	}

	public CreateEJB20RelationshipCommand(EJBRelation rel, PersistentRoleCommand firstRoleCommand, PersistentRoleCommand secondRoleCommand) {
		super(rel.getName(), firstRoleCommand, secondRoleCommand);
		setRelationship(rel);
	}

	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		if (getEJBRelation() == null)
			setRelationship(createRelationship());
		connectRoles();
	}

	protected void initializeRelationship() {
		//do nothing since this is a create
	}

	protected CommonRelationship createRelationship() {
		EJBRelation relation = getEjbFactory().createEJBRelation();
		relation.setName(getName());
		relation.setDescription(getRelationshipDescription());
		EJBJar jar = getEJBJar();
		Relationships relList = jar.getRelationshipList();
		if (relList == null) {
			relList = getEjbFactory().createRelationships();
			jar.setRelationshipList(relList);
			createdRelationshipList = true;
		}
		relList.getEjbRelations().add(relation);
		return relation;
	}

	/**
	 * Undo the the creation of the relationship.
	 */
	protected void undoMetadataGeneration() {
		EJBRelation rel = getEJBRelation();
		if (rel != null) {
			EJBJar jar = getEJBJar();
			if (jar != null) {
				if (createdRelationshipList)
					jar.setRelationshipList(null);
				else {
					jar.getRelationshipList().getEjbRelations().remove(rel);
				}
			}
		}
		super.undoMetadataGeneration();
	}
}