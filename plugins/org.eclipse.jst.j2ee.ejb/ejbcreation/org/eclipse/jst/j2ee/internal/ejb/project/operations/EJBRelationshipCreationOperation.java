/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Apr 2, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;


public class EJBRelationshipCreationOperation extends EditModelOperation {
	protected EJBRelationshipCommand relationshipCommand;
	protected EJBRelationshipDataModel model;

	/**
	 * @param operationDataModel
	 */
	public EJBRelationshipCreationOperation(EJBRelationshipDataModel operationDataModel) {
		super(operationDataModel);
		model = operationDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		IEJBCommand command = createCommand();
		if (command != null) {
			if (command.isRootCommand())
				((IRootCommand) command).setProgressMonitor(monitor);
			try {
				getCommandStack().execute(command);
			} catch (Exception e) {
				throw new CoreException(J2EEPlugin.newErrorStatus(errorMessage(), e));
			} finally {
				monitor.done();
			}
		}
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
		relationshipCommand = create20RelationshipCommand(aRole, bRole);
		if (merged == null)
			return relationshipCommand;
		else if (relationshipCommand != null)
			merged.append(relationshipCommand);
		return merged;
	}

	protected EJBRelationshipCommand create20RelationshipCommand(PersistentRoleCommand aRoleCommand, PersistentRoleCommand bRoleCommand) {
		EJB20RelationshipCommand cmd = new CreateEJB20RelationshipCommand(model.getStringProperty(EJBRelationshipDataModel.RELATIONSHIP_NAME), aRoleCommand, bRoleCommand);
		cmd.setRelationshipDescription(model.getStringProperty(EJBRelationshipDataModel.DESCRIPTION));
		return cmd;
	}

	protected IRootCommand createARoleSourceEjbCommand() {
		//The BRole type is the source for ARole
		return createEjbUpdateCommand((EnterpriseBean) model.getProperty(EJBRelationshipDataModel.BEAN_B));
	}

	protected IRootCommand createEjbUpdateCommand(EnterpriseBean anEJB) {
		if (anEJB.isEntity()) {
			if (anEJB.isContainerManagedEntity()) {
				return new UpdateContainerManagedEntityCommand(anEJB, (EJBEditModel) editModel);
			}
			return new UpdateEntityCommand(anEJB, (EJBEditModel) editModel);
		}
		return new UpdateSessionCommand(anEJB, (EJBEditModel) editModel);
	}

	protected PersistentRoleCommand createARoleCommand(IRootCommand root) {
		if (root == null)
			return null;
		return createARole20Command(root);
	}

	protected PersistentRoleCommand createARole20Command(IRootCommand root) {
		Persistent20RoleCommand command = new CreatePersistent20RoleCommand(root, model.getStringProperty(EJBRelationshipDataModel.BEAN_A_ROLE_NAME));
		updateARole20Command(command);
		return command;
	}

	protected void updateARole20Command(Persistent20RoleCommand command) {
		command.setForward(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_A_FOREIGN_KEY));
		command.setNavigable(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_A_NAVIGABILITY));
		command.setIsMany(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_B_IS_MANY));

		String fieldName = model.getStringProperty(EJBRelationshipDataModel.BEAN_A_CMR_NAME);
		if (!fieldName.equals("")) //$NON-NLS-1$
			command.setCmrFieldName(model.getStringProperty(EJBRelationshipDataModel.BEAN_A_CMR_NAME));
		else
			command.setCmrFieldName(null);
		String fieldType = model.getStringProperty(EJBRelationshipDataModel.BEAN_A_CMR_TYPE);
		if (!fieldType.equals("")) //$NON-NLS-1$
			command.setCmrFieldCollectionTypeName(model.getStringProperty(EJBRelationshipDataModel.BEAN_A_CMR_TYPE));
		else
			command.setCmrFieldCollectionTypeName(null);
		command.setIsCascadeDelete(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_A_CASCADE_DELETE));
	}

	protected IRootCommand createBRoleSourceEjbCommand() {
		//The ARole type is the source for BRole
		return createEjbUpdateCommand((EnterpriseBean) model.getProperty(EJBRelationshipDataModel.BEAN_A));
	}

	protected PersistentRoleCommand createBRoleCommand(IRootCommand root) {
		if (root == null)
			return null;
		return createBRole20Command(root);
	}

	protected PersistentRoleCommand createBRole20Command(IRootCommand root) {
		Persistent20RoleCommand command = new CreatePersistent20RoleCommand(root, model.getStringProperty(EJBRelationshipDataModel.BEAN_B_ROLE_NAME));
		updateBRole20Command(command);
		return command;
	}

	protected void updateBRole20Command(Persistent20RoleCommand command) {
		command.setForward(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_B_FOREIGN_KEY));
		command.setNavigable(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_B_NAVIGABILITY));
		command.setIsMany(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_A_IS_MANY));
		String fieldName = model.getStringProperty(EJBRelationshipDataModel.BEAN_B_CMR_NAME);
		if (!fieldName.equals("")) //$NON-NLS-1$
			command.setCmrFieldName(model.getStringProperty(EJBRelationshipDataModel.BEAN_B_CMR_NAME));
		else
			command.setCmrFieldName(null);
		String fieldType = model.getStringProperty(EJBRelationshipDataModel.BEAN_B_CMR_TYPE);
		if (!fieldType.equals("")) //$NON-NLS-1$
			command.setCmrFieldCollectionTypeName(model.getStringProperty(EJBRelationshipDataModel.BEAN_B_CMR_TYPE));
		else
			command.setCmrFieldCollectionTypeName(null);
		command.setIsCascadeDelete(model.getBooleanProperty(EJBRelationshipDataModel.BEAN_B_CASCADE_DELETE));
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
}