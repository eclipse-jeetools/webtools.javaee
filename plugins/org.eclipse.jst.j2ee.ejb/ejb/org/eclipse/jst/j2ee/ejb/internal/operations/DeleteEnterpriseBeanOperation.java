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
package org.eclipse.jst.j2ee.ejb.internal.operations;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCommandHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.provider.EJBProviderLibrariesResourceHandler;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;


/**
 * @author: jlanuti
 */
public class DeleteEnterpriseBeanOperation extends DeleteEnterpriseBeanAbstractOperation {

	private List deleteRelationships;

	public DeleteEnterpriseBeanOperation() {
		super(null);
	}

	public DeleteEnterpriseBeanOperation(EditModelOperationDataModel dataModel) {
		super(dataModel);
	}

	protected void preExecute() throws CoreException, InvocationTargetException, InterruptedException {
		executeRelationshipOperations();
	}

	protected IRootCommand createRootDeleteCommand(EnterpriseBean ejb) {
		IRootCommand root = EJBCommandHelper.createEnterpriseBeanDeleteCommand(ejb, (EJBEditModel) editModel);
		root.setGenerateJava(false);
		root.setGenerateMetadata(true);
		((EnterpriseBeanCommand) root).doNotDeleteGeneratedClasses();
		root.setOperationHandler(((DeleteEnterpriseBeanDataModel) operationDataModel).getOperationHandler());
		return root;
	}

	/**
	 * Return a String for error message if the operation fails.
	 */
	protected String errorMessage() {
		return EJBProviderLibrariesResourceHandler.getString("Failed_To_Delete_EJB_UI_", new Object[]{getCurrentEJB().getName()}); //$NON-NLS-1$
	}

	protected void executeRelationshipOperations() throws InterruptedException, InvocationTargetException {
		if (getBeansToBeDeleted() == null || getBeansToBeDeleted().isEmpty())
			return;
		initializeRelationships();
		executeDeleteRelationshipOperations();
	}

	protected void executeDeleteRelationshipOperations() throws InterruptedException, InvocationTargetException {
		if (deleteRelationships != null && !deleteRelationships.isEmpty()) {
			RemoveRelationshipDataModel model = new RemoveRelationshipDataModel();
			model.setProperty(RemoveRelationshipDataModel.COMMON_RELATIONSHIP_LIST, deleteRelationships);
			model.setProperty(RemoveRelationshipDataModel.EDIT_MODEL_ID, operationDataModel.getProperty(EditModelOperationDataModel.EDIT_MODEL_ID));
			model.setProperty(RemoveRelationshipDataModel.PROJECT_NAME, operationDataModel.getProperty(EditModelOperationDataModel.PROJECT_NAME));
			RemoveRelationshipDataModelOperation op = new RemoveRelationshipDataModelOperation(model);
			op.setGenJava(false);
			op.run(new NullProgressMonitor());
		}
	}

	protected void initializeRelationships() {
		EnterpriseBean ejb;
		for (int i = 0; i < getBeansToBeDeleted().size(); i++) {
			ejb = (EnterpriseBean) getBeansToBeDeleted().get(i);
			addRelationships(ejb);
		}
	}

	protected void addRelationships(EnterpriseBean ejb) {
		if (ejb.isContainerManagedEntity()) {
			ContainerManagedEntity cmp = (ContainerManagedEntity) ejb;
			List roles = cmp.getRoles();
			CommonRelationshipRole role;
			for (int i = 0; i < roles.size(); i++) {
				role = (CommonRelationshipRole) roles.get(i);
				addRelationship(role, cmp);
			}
		}
	}

	protected void addRelationship(CommonRelationshipRole role, ContainerManagedEntity cmp) {
		addRelationshipForDelete(role.getCommonRelationship());
	}


	protected void addRelationshipForDelete(CommonRelationship commonRelationship) {
		if (commonRelationship != null) {
			if (deleteRelationships == null) {
				deleteRelationships = new ArrayList();
				deleteRelationships.add(commonRelationship);
			} else if (!deleteRelationships.contains(commonRelationship))
				deleteRelationships.add(commonRelationship);
		}
	}
}