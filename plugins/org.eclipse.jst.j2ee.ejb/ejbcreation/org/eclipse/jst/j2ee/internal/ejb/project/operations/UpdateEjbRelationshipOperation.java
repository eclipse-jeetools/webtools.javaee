package org.eclipse.jst.j2ee.internal.ejb.project.operations;

/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBRelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.PersistentRoleCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.EJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.Persistent20RoleCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.RenameEJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.UpdatePersistent20RoleCommand;
import org.eclipse.wst.common.framework.operation.IOperationHandler;


/**
 * Insert the type's description here. Creation date: (8/13/2001 10:44:02 AM)
 * 
 * @author: Administrator
 */
public class UpdateEjbRelationshipOperation extends NewEjbRelationshipOperation {
	/**
	 * UpdateEjbRelationshipOperation constructor comment.
	 * 
	 * @param anEditModel
	 *            org.eclipse.jst.j2ee.ejb.ejbproject.EJBEditModel
	 * @param aProvider
	 *            com.ibm.ejs.models.base.extensions.ejbext.operations.EjbRelationshipModifyInfoProvider
	 * @param anOperationHandler
	 *            org.eclipse.jst.j2ee.operations.IOperationHandler
	 */
	public UpdateEjbRelationshipOperation(EJBEditModel anEditModel, EjbUpdateRelationshipModel aModel, IOperationHandler anOperationHandler) {
		super(anEditModel, aModel, anOperationHandler);
	}

	protected EJBRelationshipCommand create20RelationshipCommand(PersistentRoleCommand aRoleCommand, PersistentRoleCommand bRoleCommand) {
		return createRename20RelationshipCommand();
	}

	protected EJBRelationshipCommand createRename20RelationshipCommand() {
		Ejb20RelationshipModel updateModel = (Ejb20RelationshipModel) model;
		EJBRelation rel = (EJBRelation) updateModel.getRelationship();
		String relName = rel.getName();
		String relDesc, updateDesc;
		relDesc = rel.getDescription();
		updateDesc = updateModel.getRelationshipDescription();
		if (!updateModel.getRelationshipName().equals(relName) || relDesc != updateDesc || (relDesc != null && !relDesc.equals(updateDesc))) {
			EJB20RelationshipCommand cmd = new RenameEJB20RelationshipCommand(rel, updateModel.getRelationshipName());
			cmd.setRelationshipDescription(updateDesc);
			return cmd;
		}
		return null;
	}

	protected PersistentRoleCommand createARole20Command(IRootCommand root) {
		CommonRelationshipRole roleA = ((EjbUpdateRelationshipModel) model).getRoleA();
		Persistent20RoleCommand command = new UpdatePersistent20RoleCommand(root, (EJBRelationshipRole) roleA);
		updateARole20Command(command);
		return command;
	}

	protected void updateARole20Command(Persistent20RoleCommand command) {
		Ejb20RelationshipModel model20 = (Ejb20RelationshipModel) model;
		command.setName(model20.getRoleAName());
		super.updateARole20Command(command);
	}

	protected PersistentRoleCommand createBRole20Command(IRootCommand root) {
		CommonRelationshipRole roleB = ((EjbUpdateRelationshipModel) model).getRoleB();
		Persistent20RoleCommand command = new UpdatePersistent20RoleCommand(root, (EJBRelationshipRole) roleB);
		updateBRole20Command(command);
		return command;
	}

	protected void updateBRole20Command(Persistent20RoleCommand command) {
		Ejb20RelationshipModel model20 = (Ejb20RelationshipModel) model;
		command.setName(model20.getRoleBName());
		super.updateBRole20Command(command);
	}

	/**
	 * @see com.ibm.ejs.models.base.extensions.ejbext.operations.NewEjbRelationshipOperation#createARoleSourceEjbCommand()
	 */
	protected IRootCommand createARoleSourceEjbCommand() {
		if (((EjbUpdateRelationshipModel) model).shouldUpdateRoles())
			return super.createARoleSourceEjbCommand();
		return null;
	}

	/**
	 * @see com.ibm.ejs.models.base.extensions.ejbext.operations.NewEjbRelationshipOperation#createBRoleSourceEjbCommand()
	 */
	protected IRootCommand createBRoleSourceEjbCommand() {
		if (((EjbUpdateRelationshipModel) model).shouldUpdateRoles())
			return super.createBRoleSourceEjbCommand();
		return null;
	}


}