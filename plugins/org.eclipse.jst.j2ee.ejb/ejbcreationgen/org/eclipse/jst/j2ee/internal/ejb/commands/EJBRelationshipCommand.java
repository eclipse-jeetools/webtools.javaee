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
package org.eclipse.jst.j2ee.internal.ejb.commands;



import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;

/**
 * Insert the type's description here. Creation date: (11/20/2000 1:45:14 PM)
 * 
 * @author: Administrator
 */
public class EJBRelationshipCommand extends EJBCommand {
	protected CommonRelationshipRole[] commonRoles;
	protected String[] roleNames;
	protected String[] typeNames;
	protected PersistentRoleCommand[] roleCommands;

	/**
	 * EJBRelationshipCommand constructor comment.
	 */
	public EJBRelationshipCommand() {
		super();
		setGenerateJava(false);
	}

	/**
	 * EJBRelationshipCommand constructor comment.
	 * 
	 * @param desc
	 *            java.lang.String
	 */
	public EJBRelationshipCommand(String desc) {
		super(desc);
		setGenerateJava(false);
	}

	/**
	 * EJBRelationshipCommand constructor comment.
	 * 
	 * @param desc
	 *            java.lang.String
	 */
	public EJBRelationshipCommand(String desc, CommonRelationshipRole firstRole, CommonRelationshipRole secondRole) {
		this(desc);
		setFirstRole(firstRole);
		setSecondRole(secondRole);
	}

	/**
	 * EJBRelationshipCommand constructor comment.
	 * 
	 * @param desc
	 *            java.lang.String
	 */
	public EJBRelationshipCommand(String desc, PersistentRoleCommand firstRoleCommand, PersistentRoleCommand secondRoleCommand) {
		this(desc);
		setFirstRoleCommand(firstRoleCommand);
		setSecondRoleCommand(secondRoleCommand);
	}

	/**
	 * createCodegenHelper method comment.
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper createCodegenHelper() {
		return null;
	}

	/**
	 * createCodegenHelper method comment.
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper createInverseCodegenHelper() {
		return null;
	}

	public CommonRelationshipRole getFirstCommonRole() {
		CommonRelationshipRole role = getCommonRoles()[0];
		if (role == null && getFirstRoleCommand() != null)
			role = getFirstRoleCommand().getCommonRole();
		return role;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:25:17 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.commands.PersistentRoleCommand
	 */
	public PersistentRoleCommand getFirstRoleCommand() {
		return getRoleCommands()[0];
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:26:11 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getFirstRoleName() {
		String aName = getRoleNames()[0];
		if (aName == null && getFirstCommonRole() != null)
			aName = getFirstCommonRole().getName();
		return aName;
	}

	public ContainerManagedEntity getFirstCommonRoleType() {
		CommonRelationshipRole role = getFirstCommonRole();
		if (role != null)
			return role.getTypeEntity();
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 1:50:00 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.commands.PersistentRoleCommand[]
	 */
	protected PersistentRoleCommand[] getRoleCommands() {
		if (roleCommands == null)
			roleCommands = new PersistentRoleCommand[2];
		return roleCommands;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 5:18:15 PM)
	 * 
	 * @return java.lang.String[]
	 */
	protected java.lang.String[] getRoleNames() {
		if (roleNames == null)
			roleNames = new String[2];
		return roleNames;
	}

	protected CommonRelationshipRole[] getCommonRoles() {
		if (commonRoles == null)
			commonRoles = new CommonRelationshipRole[2];
		return commonRoles;
	}

	public CommonRelationshipRole getSecondCommonRole() {
		CommonRelationshipRole role = getCommonRoles()[1];
		if (role == null && getSecondRoleCommand() != null)
			role = getSecondRoleCommand().getCommonRole();
		return role;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:25:17 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.commands.PersistentRoleCommand
	 */
	public PersistentRoleCommand getSecondRoleCommand() {
		return getRoleCommands()[1];
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:26:11 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getSecondRoleName() {
		String aName = getRoleNames()[1];
		if (aName == null && getSecondCommonRole() != null)
			aName = getSecondCommonRole().getName();
		return aName;
	}

	public ContainerManagedEntity getSecondCommonRoleType() {
		CommonRelationshipRole role = getSecondCommonRole();
		if (role != null)
			return role.getTypeEntity();
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 5:18:15 PM)
	 * 
	 * @return java.lang.String[]
	 */
	protected java.lang.String[] getTypeNames() {
		if (typeNames == null)
			typeNames = new String[2];
		return typeNames;
	}

	protected void setFirstRole(CommonRelationshipRole commonRole) {
		getCommonRoles()[0] = commonRole;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:31:10 PM)
	 * 
	 * @param aCommand
	 *            org.eclipse.jst.j2ee.commands.PersistentRoleCommand
	 */
	public void setFirstRoleCommand(PersistentRoleCommand aCommand) {
		getRoleCommands()[0] = aCommand;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:24:06 PM)
	 * 
	 * @param aName
	 *            java.lang.String
	 */
	public void setFirstRoleName(String aName) {
		getRoleNames()[0] = aName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:24:06 PM)
	 * 
	 * @param aName
	 *            java.lang.String
	 */
	public void setFirstTypeName(String aName) {
		getTypeNames()[0] = aName;
	}

	protected void setRelationship(CommonRelationship aRelationship) {
		setSourceMetaType(aRelationship);
	}

	protected void setSecondRole(CommonRelationshipRole commonRole) {
		getCommonRoles()[1] = commonRole;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:31:10 PM)
	 * 
	 * @param aCommand
	 *            org.eclipse.jst.j2ee.commands.PersistentRoleCommand
	 */
	public void setSecondRoleCommand(PersistentRoleCommand aCommand) {
		getRoleCommands()[1] = aCommand;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:24:06 PM)
	 * 
	 * @param aName
	 *            java.lang.String
	 */
	public void setSecondRoleName(String aName) {
		getRoleNames()[1] = aName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 2:24:06 PM)
	 * 
	 * @param aName
	 *            java.lang.String
	 */
	public void setSecondTypeName(String aName) {
		getTypeNames()[1] = aName;
	}

	/**
	 * @see IEJBCommand#getEjb()
	 */
	public EnterpriseBean getEjb() {
		return null;
	}

	public CommonRelationship getCommonRelationship() {
		return (CommonRelationship) getSourceMetaType();
	}

}