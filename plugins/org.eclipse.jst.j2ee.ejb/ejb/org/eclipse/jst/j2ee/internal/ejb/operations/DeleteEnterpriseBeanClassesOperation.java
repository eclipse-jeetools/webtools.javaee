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
/*
 * Created on May 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCommandHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.provider.EJBProviderLibrariesResourceHandler;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;


/**
 * @author jlanuti
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DeleteEnterpriseBeanClassesOperation extends DeleteEnterpriseBeanAbstractOperation {

	private List removeRelationships;

	public DeleteEnterpriseBeanClassesOperation() {
		super(null);
	}

	public DeleteEnterpriseBeanClassesOperation(EditModelOperationDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Return a String for error message if the operation fails.
	 */
	protected String errorMessage() {
		return EJBProviderLibrariesResourceHandler.getString("Failed_To_Delete_EJB_UI_", new Object[]{getCurrentEJB().getName()}); //$NON-NLS-1$
	}

	protected IRootCommand createRootDeleteCommand(EnterpriseBean ejb) {

		IRootCommand root = EJBCommandHelper.createEnterpriseBeanDeleteCommand(ejb, (EJBEditModel) editModel);
		root.setGenerateJava(true);
		root.setGenerateMetadata(false);
		return root;
	}

	protected void addRelationship(CommonRelationshipRole role, ContainerManagedEntity cmp) {
		ContainerManagedEntity typeCmp = role.getTypeEntity();
		if (cmp.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID && getBeansToBeDeleted().contains(typeCmp))
			addRelationshipForRemove(role.getCommonRelationship());
	}

	protected void addRelationshipForRemove(CommonRelationship commonRelationship) {
		if (commonRelationship != null) {
			if (removeRelationships == null) {
				removeRelationships = new ArrayList();
				removeRelationships.add(commonRelationship);
			} else if (!removeRelationships.contains(commonRelationship))
				removeRelationships.add(commonRelationship);
		}
	}

	protected void executeRemoveRelationshipOperations() throws InterruptedException, InvocationTargetException {
		if (removeRelationships != null && !removeRelationships.isEmpty()) {
			RemoveRelationshipDataModel model = new RemoveRelationshipDataModel();
			model.setProperty(RemoveRelationshipDataModel.COMMON_RELATIONSHIP_LIST, removeRelationships);
			model.setProperty(RemoveRelationshipDataModel.EDIT_MODEL_ID, operationDataModel.getProperty(EditModelOperationDataModel.EDIT_MODEL_ID));
			model.setProperty(RemoveRelationshipDataModel.PROJECT_NAME, operationDataModel.getProperty(EditModelOperationDataModel.PROJECT_NAME));
			RemoveRelationshipDataModelOperation op = new RemoveRelationshipDataModelOperation(model);
			op.setGenJava(false);
			op.run(new NullProgressMonitor());
		}
	}

	protected void executeRelationshipOperations() throws InterruptedException, InvocationTargetException {
		if (getBeansToBeDeleted() == null || getBeansToBeDeleted().isEmpty())
			return;
		initializeRelationships();
		executeRemoveRelationshipOperations();
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

	protected void preExecute() throws CoreException, InvocationTargetException, InterruptedException {
		executeRelationshipOperations();
	}
}