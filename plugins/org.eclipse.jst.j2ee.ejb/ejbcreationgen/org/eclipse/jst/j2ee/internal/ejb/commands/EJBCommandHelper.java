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

import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.internal.extensions.Delete11Relationships;
import org.eclipse.jst.j2ee.ejb.internal.extensions.Delete11RelationshipsExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.DeleteEJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.DeleteMessageDrivenCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.DeletePersistent20RoleCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.UpdateMessageDrivenCommand;


/**
 * Insert the type's description here. Creation date: (6/28/2001 11:03:43 PM)
 * 
 * @author: Administrator
 */
public class EJBCommandHelper {
	public static IEJBCommand createRemoveEjbRelationshipCommand(CommonRelationship aRelationship, EJBEditModel anEditModel) {
		if (aRelationship == null || anEditModel == null)
			return null;
		return createRemoveEjbRelationshipCommand((EJBRelation) aRelationship, anEditModel);
	}

	public static IRootCommand createDeleteEjbRelationshipCommand(CommonRelationship aRelation, EJBEditModel anEditModel) {
		if (aRelation == null || anEditModel == null)
			return null;
		IRootCommand root, merged = null;
		List roles = aRelation.getCommonRoles();
		CommonRelationshipRole role;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			if (role.getSourceEntity().isVersion1_X()) {
				runDeleteEJB11RelationshipsOperation(aRelation, anEditModel);
			} else {
				root = createEnterpriseBeanUpdateCommand(role.getSourceEntity(), anEditModel);
				if (root != null) {
					new DeletePersistent20RoleCommand(root, (EJBRelationshipRole) role);
					if (merged == null)
						merged = root;
					else
						merged = merged.append(root);
				}
				merged.append(new DeleteEJB20RelationshipCommand((EJBRelation) aRelation));
			}
		}
		return merged;
	}

	/**
	 * @param aRelation
	 * @param anEditModel
	 */
	private static void runDeleteEJB11RelationshipsOperation(CommonRelationship aRelation, EJBEditModel anEditModel) {
		Delete11RelationshipsExtensionReader reader = Delete11RelationshipsExtensionReader.getInstance();
		Delete11Relationships rels11Ext = reader.getDeleteDeployCodeExt();
		if (rels11Ext != null)
			rels11Ext.runDeleteDelete11Relationships(aRelation, anEditModel);
	}

	public static IEJBCommand createRemoveEjbRelationshipCommand(EJBRelation aRelation, EJBEditModel anEditModel) {
		if (aRelation == null || anEditModel == null)
			return null;
		DeleteEJB20RelationshipCommand command = new DeleteEJB20RelationshipCommand(aRelation);
		command.setGenerateJava(false);
		return command;
	}

	/**
	 * Return an IRootCommand that will delete
	 * 
	 * @anEJB as well as its Java classes.
	 */
	public static IRootCommand createEnterpriseBeanDeleteCommand(EnterpriseBean anEJB, EJBEditModel anEditModel) {
		if (anEJB == null || anEditModel == null)
			return null;
		if (anEJB.isEntity()) {
			if (anEJB.isContainerManagedEntity())
				return new DeleteContainerManagedEntityCommand(anEJB, anEditModel);
			return new DeleteEntityCommand(anEJB, anEditModel);
		} else if (anEJB.isSession())
			return new DeleteSessionCommand(anEJB, anEditModel);
		else
			return new DeleteMessageDrivenCommand(anEJB, anEditModel);
	}

	/**
	 * Return the proper update IRootCommand for
	 * 
	 * @anEJB.
	 */
	public static IRootCommand createEnterpriseBeanUpdateCommand(EnterpriseBean anEJB, EJBEditModel anEditModel) {
		if (anEJB == null || anEditModel == null)
			return null;
		if (anEJB.isEntity()) {
			if (anEJB.isContainerManagedEntity())
				return new UpdateContainerManagedEntityCommand(anEJB, anEditModel);
			return new UpdateEntityCommand(anEJB, anEditModel);
		} else if (anEJB.isSession())
			return new UpdateSessionCommand(anEJB, anEditModel);
		else
			return new UpdateMessageDrivenCommand(anEJB, anEditModel);
	}
}