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



import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * Insert the type's description here. Creation date: (9/8/2000 2:29:19 PM)
 * 
 * @author: Administrator
 */
public class UpdateContainerManagedEntityCommand extends ContainerManagedEntityCommand {
	private static final String KEY_PROP_MSG = EJBCodeGenResourceHandler.getString("The_key_shape_has_changed__UI_"); //$NON-NLS-1$ = "The key shape has changed for the CMP Entity named {0} and there are relationship roles pointing to this entity, would you like to propagate these key changes?"
	private boolean isMigrationCleanup = false;

	/**
	 * UpdateContainerManagedEntityCommand constructor comment.
	 * 
	 * @param aName
	 *            java.lang.String
	 * @param aFolder
	 *            com.ibm.itp.core.api.resources.IFolder
	 */
	public UpdateContainerManagedEntityCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/**
	 * Create an UpdateContainerManagedEntityCommand for the owner of each role in
	 * 
	 * @roles and add a KeyPropagatorCommand to each of these.
	 */
	protected void createKeyPropagationCommand(CommonRelationshipRole role, boolean needToProp) {
		if (role == null)
			return;
		ContainerManagedEntity cmp = role.getSourceEntity();
		if (cmp == null || cmp == getContainerManagedEntity())
			return;
		EJBPropagationCommand propCommand = getAdditionalCommand();
		if (!propCommand.shouldPropagate(role))
			return; //do not propogate if we already have
		IRootCommand cmpCommand = propCommand.getRootCommand(cmp);
		if (cmpCommand == null) {
			cmpCommand = new UpdateContainerManagedEntityCommand(cmp, getEditModel());
			cmpCommand.setGenerateJava(shouldGenerateJava());
			cmpCommand.setGenerateMetadata(shouldGenerateMetadata());
			((ContainerManagedEntityCommand) cmpCommand).setShouldPropagate(needToProp);
			propCommand.append(cmpCommand);
		}
		KeyPropagatorRoleCommand roleCmd = new KeyPropagatorRoleCommand(cmpCommand, role, shouldGenerateJava(), shouldGenerateMetadata());
		propCommand.cachePropagationCommand(roleCmd);
	}

	/**
	 * Create an UpdateContainerManagedEntityCommand for the owner of each role in
	 * 
	 * @roles and add a KeyPropagatorCommand to each of these.
	 */
	protected void createKeyPropagationCommands(List roles) {
		CommonRelationshipRole role;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			createKeyPropagationCommand(role, true);
			createKeyPropagationCommand(role.getOppositeAsCommonRole(), false);
		}
	}

	protected void filterNonForwardRoles(List roles) {
		if (roles.isEmpty())
			return;
		EnterpriseBean testEJB = getEjb();
		Iterator it = roles.iterator();
		CommonRelationshipRole role;
		while (it.hasNext()) {
			role = (CommonRelationshipRole) it.next();
			if (!role.isForward() || testEJB.equals(role.getSourceEntity()))
				it.remove();
		}
	}

	protected void filterRolesMarkedForDeletion(List roles) {
		if (roles.isEmpty())
			return;
		Iterator it = roles.iterator();
		CommonRelationshipRole role;
		while (it.hasNext()) {
			role = (CommonRelationshipRole) it.next();
			if (hasDeleteCommand(role))
				it.remove();
		}
	}

	protected boolean hasDeleteCommand(CommonRelationshipRole aRole) {
		AbstractEJBRootCommand root = this;
		while (!root.isRoot())
			root = (AbstractEJBRootCommand) root.getParent();
		IEJBCommand child = root.getChildCommand(aRole);
		return child != null && (child instanceof PersistentRoleCommand && ((PersistentRoleCommand) child).isDeleteCommand());
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:20:02 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTaskName() {
		return getUpdatingTaskName();
	}

	/**
	 * Subclasses should override this method to initialize the additional command because the
	 * 
	 * @rootCommands Map ensures that duplicate commands will not be created for the same EJB.
	 */
	protected void doInitializeAdditionalCommand() {
		super.doInitializeAdditionalCommand();
		if (isKeyChanging())
			initializeKeyPropagationCommands();
	}

	/**
	 * We need to find all the EjbRelationshipRoles that are pointing to the EJB. If there are some
	 * roles, we need to prompt the user to see if they want to propagate these changes. If this
	 * returns true, create key propogation commands for each role.
	 */
	protected void initializeKeyPropagationCommands() {
		List roles;
		switch (getContainerManagedEntity().getVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID :
			case J2EEVersionConstants.EJB_1_1_ID :
				initializeEJBCodegenHandler();
				if (codgenHandler != null)
					roles = codgenHandler.getExtendedEjb11RelationshipRolesWithType(getEjb());
				else
					roles = Collections.EMPTY_LIST;
				break;
			case J2EEVersionConstants.EJB_2_0_ID :
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				roles = getEJBJar().getEJBRelationshipRolesForType((ContainerManagedEntity) getEjb());
				filterNonKeyRoles(roles); //only key roles should be propogated for 2.0 CMPs
				break;
		}
		filterNonForwardRoles(roles);
		filterRolesMarkedForDeletion(roles);
		if (!roles.isEmpty() && promptForPropagation())
			createKeyPropagationCommands(roles);
	}



	/**
	 * Remove any roles from the list that are not key
	 * 
	 * @param roles
	 */
	private void filterNonKeyRoles(List roles) {
		if (!roles.isEmpty()) {
			CommonRelationshipRole role, oldRole;
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				role = (CommonRelationshipRole) it.next();
				if (!role.isKey()) {
					oldRole = (CommonRelationshipRole) getCopy(role);
					if (oldRole != null && oldRole.isKey())
						continue;
					it.remove();
				}
			}
		}

	}

	/**
	 * If the key shape is changing based on the oldEJB and the new EJB, we need to initialize the
	 * key propagation command.
	 */
	protected boolean isKeyShapeChanging() {
		List keyAttributes, oldKeyAttributes;
		keyAttributes = getContainerManagedEntity().getKeyAttributes();
		oldKeyAttributes = ((ContainerManagedEntity) getOldEjb()).getKeyAttributes();
		if (keyAttributes.size() != oldKeyAttributes.size())
			return true;
		boolean found = false;
		CMPAttribute oldAttribute, attribute;
		for (int i = 0; i < keyAttributes.size(); i++) {
			attribute = (CMPAttribute) keyAttributes.get(i);
			found = false;
			for (int ii = 0; ii < oldKeyAttributes.size(); ii++) {
				oldAttribute = (CMPAttribute) oldKeyAttributes.get(ii);
				if (attribute.getName().equals(oldAttribute.getName())) {
					found = true;
					break;
				}
			}
			if (!found)
				return true;
		}
		return false;
	}

	/**
	 *  
	 */
	protected boolean promptForPropagation() {
		if (shouldPropogateAllKeyChanges() || getOperationHandler() == null)
			return true;
		String msg = java.text.MessageFormat.format(KEY_PROP_MSG, new String[]{getEjb().getName()});
		int result = getOperationHandler().canContinueWithAllCheck(msg);
		setPropogateAllKeyChanges(result == 1);
		return result == 0 || result == 1;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 6:12:50 PM)
	 * 
	 * @return boolean
	 */
	public boolean shouldGenerateJava() {
		if (isAdditionalCommand())
			return super.shouldGenerateJava();
		return shouldGenerateJavaForModify();
	}

	/**
	 * Sets the isMigrationCleanup.
	 * 
	 * @param isMigrationCleanup
	 *            The isMigrationCleanup to set
	 */
	public void setIsMigrationCleanup(boolean isMigrationCleanup) {
		this.isMigrationCleanup = isMigrationCleanup;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.EnterpriseBeanCommand#initializeHelper(EnterpriseBeanHelper)
	 */
	protected void initializeHelper(EnterpriseBeanHelper aHelper) {
		super.initializeHelper(aHelper);
		((EntityHelper) aHelper).setMigrationCleanup(isMigrationCleanup);
	}

}