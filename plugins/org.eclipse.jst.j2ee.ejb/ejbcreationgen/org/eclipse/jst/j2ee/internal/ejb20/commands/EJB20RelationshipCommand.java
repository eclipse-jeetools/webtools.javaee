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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.commands.AbstractEJBRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBRelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.PersistentRoleCommand;


public abstract class EJB20RelationshipCommand extends EJBRelationshipCommand {
	protected String relationshipDescription;

	/**
	 * Constructor for EJB20RelationshipCommand.
	 */
	public EJB20RelationshipCommand() {
		super();
	}

	/**
	 * Constructor for EJB20RelationshipCommand.
	 * 
	 * @param desc
	 */
	public EJB20RelationshipCommand(String desc) {
		super(desc);
	}

	/**
	 * Constructor for EJB20RelationshipCommand.
	 * 
	 * @param desc
	 * @param firstRole
	 * @param secondRole
	 */
	public EJB20RelationshipCommand(String desc, EJBRelationshipRole firstRole, EJBRelationshipRole secondRole) {
		super(desc);
		setFirstRole(firstRole);
		setSecondRole(secondRole);
	}

	/**
	 * Constructor for EJB20RelationshipCommand.
	 * 
	 * @param desc
	 * @param firstRoleCommand
	 * @param secondRoleCommand
	 */
	public EJB20RelationshipCommand(String desc, PersistentRoleCommand firstRoleCommand, PersistentRoleCommand secondRoleCommand) {
		super(desc);
		if (firstRoleCommand.isForward()) {
			setFirstRoleCommand(firstRoleCommand);
			setSecondRoleCommand(secondRoleCommand);
		} else {
			setFirstRoleCommand(secondRoleCommand);
			setSecondRoleCommand(firstRoleCommand);
		}
	}

	protected EJBJar getEJBJar() {
		if (getParent() != null)
			return ((AbstractEJBRootCommand) getParent()).getEJBJar();
		return null;
	}

	protected EJBRelation getEJBRelation() {
		return (EJBRelation) getSourceMetaType();
	}

	protected void initializeRelationship() {
		if (getEJBRelation() != null)
			return;
		EJBJar jar = getEJBJar();
		if (jar != null) {
			EJBRelation rel = jar.getEJBRelation(getName());
			setRelationship(rel);
		}
	}

	protected EjbFactory getEjbFactory() {
		return ((EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory();
	}

	protected void connectRoles() {
		List roles = getEJBRelation().getRelationshipRoles();
		roles.add(getFirstCommonRole());
		roles.add(getSecondCommonRole());
		/*
		 * if (getFirstRoleCommand() != null) getFirstRoleCommand().createEJBReference(); if
		 * (getSecondRoleCommand() != null) getSecondRoleCommand().createEJBReference();
		 */
	}

	protected void disconnectRoles() {
		List roles = getEJBRelation().getRelationshipRoles();
		roles.remove(getFirstCommonRole());
		roles.remove(getSecondCommonRole());
	}

	/**
	 * Returns the relationshipDescription.
	 * 
	 * @return String
	 */
	public String getRelationshipDescription() {
		return relationshipDescription;
	}


	/**
	 * Sets the relationshipDescription.
	 * 
	 * @param relationshipDescription
	 *            The relationshipDescription to set
	 */
	public void setRelationshipDescription(String relationshipDescription) {
		this.relationshipDescription = relationshipDescription;
	}

	public CommonRelationship getCommonRelationship() {
		return getEJBRelation();
	}



}