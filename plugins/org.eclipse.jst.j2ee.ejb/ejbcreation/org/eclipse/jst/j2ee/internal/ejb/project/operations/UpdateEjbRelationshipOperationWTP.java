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
import org.eclipse.jst.j2ee.internal.ejb20.commands.EJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.Persistent20RoleCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.RenameEJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.UpdatePersistent20RoleCommand;


/**
 * Insert the type's description here. Creation date: (8/13/2001 10:44:02 AM)
 * 
 * @author: Administrator
 */
public class UpdateEjbRelationshipOperationWTP extends EJBRelationshipCreationOperation {
	/**
	 * @param operationDataModel
	 * @todo Generated comment
	 */
	public UpdateEjbRelationshipOperationWTP(EJBRelationshipDataModel operationDataModel) {
		super(operationDataModel);

	}


	protected EJBRelationshipCommand create20RelationshipCommand(PersistentRoleCommand aRoleCommand, PersistentRoleCommand bRoleCommand) {
		return createRename20RelationshipCommand();
	}

	protected EJBRelationshipCommand createRename20RelationshipCommand() {
		EJBRelation rel = (EJBRelation) model.getProperty(EJBRelationshipDataModel.RELATIONSHIP);
		String relName = rel.getName();
		String relDesc, updateDesc;
		relDesc = rel.getDescription();
		updateDesc = model.getStringProperty(EJBRelationshipDataModel.DESCRIPTION);
		if (!model.getStringProperty(EJBRelationshipDataModel.RELATIONSHIP_NAME).equals(relName) || relDesc != updateDesc || (relDesc != null && !relDesc.equals(updateDesc))) {
			EJB20RelationshipCommand cmd = new RenameEJB20RelationshipCommand(rel, model.getStringProperty(EJBRelationshipDataModel.RELATIONSHIP_NAME));
			cmd.setRelationshipDescription(updateDesc);
			return cmd;
		}
		return null;
	}

	protected PersistentRoleCommand createARole20Command(IRootCommand root) {
		CommonRelationshipRole roleA = (CommonRelationshipRole) model.getProperty(EJBRelationshipDataModel.EJB_ROLE_A);
		Persistent20RoleCommand command = new UpdatePersistent20RoleCommand(root, (EJBRelationshipRole) roleA);
		updateARole20Command(command);
		return command;
	}

	protected void updateARole20Command(Persistent20RoleCommand command) {
		command.setName(model.getStringProperty(EJBRelationshipDataModel.BEAN_A_ROLE_NAME));
		super.updateARole20Command(command);
	}

	protected PersistentRoleCommand createBRole20Command(IRootCommand root) {
		CommonRelationshipRole roleB = (CommonRelationshipRole) model.getProperty(EJBRelationshipDataModel.EJB_ROLE_B);
		Persistent20RoleCommand command = new UpdatePersistent20RoleCommand(root, (EJBRelationshipRole) roleB);
		updateBRole20Command(command);
		return command;
	}

	protected void updateBRole20Command(Persistent20RoleCommand command) {
		command.setName(model.getStringProperty(EJBRelationshipDataModel.BEAN_B_ROLE_NAME));
		super.updateBRole20Command(command);
	}

	/**
	 * @see com.ibm.ejs.models.base.extensions.ejbext.operations.NewEjbRelationshipOperation#createARoleSourceEjbCommand()
	 */
	protected IRootCommand createARoleSourceEjbCommand() {
		if (model.shouldUpdateRoles())
			return super.createARoleSourceEjbCommand();
		return null;
	}

	/**
	 * @see com.ibm.ejs.models.base.extensions.ejbext.operations.NewEjbRelationshipOperation#createBRoleSourceEjbCommand()
	 */
	protected IRootCommand createBRoleSourceEjbCommand() {
		if (model.shouldUpdateRoles())
			return super.createBRoleSourceEjbCommand();
		return null;
	}


}