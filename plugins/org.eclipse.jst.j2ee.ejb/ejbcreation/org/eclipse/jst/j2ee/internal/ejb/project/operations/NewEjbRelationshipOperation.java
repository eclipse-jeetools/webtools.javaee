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
package org.eclipse.jst.j2ee.internal.ejb.project.operations;



import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBRelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.PersistentRoleCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateSessionCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.provider.EJBProviderLibrariesResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateEJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreatePersistent20RoleCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.EJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.Persistent20RoleCommand;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

/**
 * Insert the type's description here. Creation date: (6/18/2001 8:27:04 AM)
 * 
 * @author: Administrator
 */
public class NewEjbRelationshipOperation extends org.eclipse.jst.j2ee.internal.ejb.creation.EjbModificationOperation {
	protected EjbRelationshipModel model;
	protected EJBRelationshipCommand relationshipCommand;

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 9:57:35 AM)
	 */
	public NewEjbRelationshipOperation(EJBEditModel anEditModel, EjbRelationshipModel aModel, IOperationHandler anOperationHandler) {
		super(anEditModel, anOperationHandler);
		model = aModel;
	}

	protected IEJBCommand createCommand() {
		IRootCommand aSource, bSource, merged = null;
		PersistentRoleCommand aRole, bRole;
		aSource = createARoleSourceEjbCommand();
		bSource = createBRoleSourceEjbCommand();
		aRole = createARoleCommand(aSource);
		bRole = createBRoleCommand(bSource);
		if (aSource != null)
			merged = aSource.append(bSource);
		relationshipCommand = createRelationshipCommand(aRole, bRole);
		if (merged == null)
			return relationshipCommand;
		else if (relationshipCommand != null)
			merged.append(relationshipCommand);
		return merged;
	}

	protected IRootCommand createEjbUpdateCommand(EnterpriseBean anEJB) {
		if (anEJB.isEntity()) {
			if (anEJB.isContainerManagedEntity()) {
				return new UpdateContainerManagedEntityCommand(anEJB, getEditModel());
			}
			return new UpdateEntityCommand(anEJB, getEditModel());
		}
		return new UpdateSessionCommand(anEJB, getEditModel());
	}

	protected IRootCommand createARoleSourceEjbCommand() {
		//The BRole type is the source for ARole
		return createEjbUpdateCommand(model.getRoleBType());
	}

	protected PersistentRoleCommand createARoleCommand(IRootCommand root) {
		if (root == null)
			return null;
		return createARole20Command(root);
	}

	protected PersistentRoleCommand createARole20Command(IRootCommand root) {
		Persistent20RoleCommand command = new CreatePersistent20RoleCommand(root, model.getRoleAName());
		updateARole20Command(command);
		return command;
	}

	protected void updateARole20Command(Persistent20RoleCommand command) {
		Ejb20RelationshipModel model20 = (Ejb20RelationshipModel) model;
		command.setForward(model20.isRoleAForward());
		command.setNavigable(model20.isRoleANavigable());
		command.setIsMany(model20.isRoleAMany());
		command.setCmrFieldName(model20.getRoleACmrFieldName());
		command.setCmrFieldCollectionTypeName(model20.getRoleACmrFieldCollectionType());
		command.setIsCascadeDelete(model20.isRoleACascadeDelete());
	}

	protected EJBRelationshipCommand createRelationshipCommand(PersistentRoleCommand aRoleCommand, PersistentRoleCommand bRoleCommand) {
		return create20RelationshipCommand(aRoleCommand, bRoleCommand);
	}

	protected EJBRelationshipCommand create20RelationshipCommand(PersistentRoleCommand aRoleCommand, PersistentRoleCommand bRoleCommand) {
		EJB20RelationshipCommand cmd = new CreateEJB20RelationshipCommand(model.getRelationshipName(), aRoleCommand, bRoleCommand);
		cmd.setRelationshipDescription(((Ejb20RelationshipModel) model).getRelationshipDescription());
		return cmd;
	}

	protected IRootCommand createBRoleSourceEjbCommand() {
		//The ARole type is the source for BRole
		return createEjbUpdateCommand(model.getRoleAType());
	}

	protected PersistentRoleCommand createBRoleCommand(IRootCommand root) {
		if (root == null)
			return null;
		return createBRole20Command(root);
	}

	protected PersistentRoleCommand createBRole20Command(IRootCommand root) {
		Persistent20RoleCommand command = new CreatePersistent20RoleCommand(root, model.getRoleBName());
		updateBRole20Command(command);
		return command;
	}

	protected void updateBRole20Command(Persistent20RoleCommand command) {
		Ejb20RelationshipModel model20 = (Ejb20RelationshipModel) model;
		command.setForward(model20.isRoleBForward());
		command.setNavigable(model20.isRoleBNavigable());
		command.setIsMany(model20.isRoleBMany());
		command.setCmrFieldName(model20.getRoleBCmrFieldName());
		command.setCmrFieldCollectionTypeName(model20.getRoleBCmrFieldCollectionType());
		command.setIsCascadeDelete(model20.isRoleBCascadeDelete());
	}


	/**
	 * Insert the method's description here. Creation date: (12/12/2000 3:44:26 PM)
	 * 
	 * @return IEJBProgressCommand
	 */
	protected String errorMessage() {
		//return J2EEResourceHandler.getExternalizedMessage("ejb","Error_has_occurred",
		// getClass()); //$NON-NLS-1$
		return EJBProviderLibrariesResourceHandler.getString("An_error_has_occurred_crea_ERROR_"); //$NON-NLS-1$ = "An error has occurred creating a new relationship."
	}

	public CommonRelationship getRelationship() {
		if (relationshipCommand != null)
			return relationshipCommand.getCommonRelationship();
		return null;
	}

}