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
package org.eclipse.jst.j2ee.internal.ejb.creation;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddRemoteInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateRemoteInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientViewModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.AddLocalHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.AddLocalInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateLocalHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateLocalInterfaceCommand;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

/**
 * Defines operations for adding interfaces to an EJB.
 * 
 * @deprecated
 */
public class CreateClientViewOperation extends AbstractClientViewOperation {

	/**
	 * Constructor for CreateClientViewOperation.
	 * 
	 * @param ejbBean -
	 *            The EJB to modify.
	 * @param aViewModel -
	 *            The ClientViewModel.
	 * @param ejbEditModel -
	 *            The EJB edit model.
	 * @param operationHandler -
	 *            The current operation handler.
	 */
	public CreateClientViewOperation(EnterpriseBean ejbBean, ClientViewModel aViewModel, EJBEditModel ejbEditModel, IOperationHandler operationHandler) {

		super(ejbBean, aViewModel, ejbEditModel, operationHandler);

	} // CreateClientViewOperation

	/**
	 * Creates the home create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected void createHomeCommand(EnterpriseBeanCommand ejbCommand) {

		JavaClass homeInterface = this.getClientViewModel().getEjbBean().getHomeInterface();

		if (homeInterface != null) {
			new AddHomeInterfaceCommand(ejbCommand, homeInterface);
		} else {
			String homeName = this.getClientViewModel().getHomeExistingName();

			if (homeName != null && homeName.length() > 0) {
				CreateHomeInterfaceCommand homeInterfaceCmd = new CreateHomeInterfaceCommand(ejbCommand, this.getSimpleName(homeName), this.getPackageName(homeName));

				homeInterfaceCmd.setMethodCollection(this.getClientViewModel().getHomeMethodCollection());
			} // if
		} // if
	} // createHomeCommand

	/**
	 * Creates the remote create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected void createRemoteCommand(EnterpriseBeanCommand ejbCommand) {

		JavaClass remoteInterface = this.getClientViewModel().getEjbBean().getRemoteInterface();

		if (remoteInterface != null) {
			new AddRemoteInterfaceCommand(ejbCommand, remoteInterface);
		} else {
			String remoteName = this.getClientViewModel().getRemoteExistingName();

			if (remoteName != null && remoteName.length() > 0) {
				CreateRemoteInterfaceCommand remoteInterfaceCmd = new CreateRemoteInterfaceCommand(ejbCommand, this.getSimpleName(remoteName), this.getPackageName(remoteName));

				List methods = getClientViewModel().getMethodCollection();
				EnterpriseBean ejb = getEjbBean();
				if (ejb.isContainerManagedEntity() && ejb.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {
					List roleMethods = getRelationshipMethodNames((ContainerManagedEntity) ejb);
					if (!roleMethods.isEmpty()) {
						methods = filterOutRelationshipMethods(methods, roleMethods);
					}
				}
				if (!methods.isEmpty()) {
					remoteInterfaceCmd.setMethodCollection(methods);
				}
			} //if
		} // if
	} // createRemoteCommand

	/**
	 * @param entity
	 * @return List relationship method names.
	 */
	private List getRelationshipMethodNames(ContainerManagedEntity entity) {
		List roleMethodNames = new ArrayList();
		List roles = entity.getRoles();
		if (roles != null && !roles.isEmpty()) {
			for (int s = 0; s < roles.size(); s++) {
				CommonRelationshipRole role = (CommonRelationshipRole) roles.get(s);
				roleMethodNames.add(RoleHelper.getSetterName(role));
				roleMethodNames.add(RoleHelper.getGetterName(role));
			} //for
		}
		return roleMethodNames;
	}

	/**
	 * @param entity
	 * @param methods
	 * @return List of methods other than those created to support a relationship to another bean.
	 */
	private List filterOutRelationshipMethods(List methods, List roleMethNames) {
		List methodsToAdd = new ArrayList();
		for (int i = 0; i < methods.size(); i++) {
			Method method = (Method) methods.get(i);
			String methodName = method.getName();
			if (!(roleMethNames.contains(methodName))) {
				methodsToAdd.add(method);
			}
		} //for
		return methodsToAdd;
	}

	/**
	 * Creates the local home create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected void createLocalHomeCommand(EnterpriseBeanCommand ejbCommand) {

		JavaClass localHomeInterface = this.getClientViewModel().getEjbBean().getLocalHomeInterface();

		if (localHomeInterface != null) {
			new AddLocalHomeInterfaceCommand(ejbCommand, localHomeInterface);
		} else {

			String localHomeName = this.getClientViewModel().getLocalHomeExistingName();

			if (localHomeName != null && localHomeName.length() > 0) {

				CreateLocalHomeInterfaceCommand localHomeInterfaceCmd = new CreateLocalHomeInterfaceCommand(ejbCommand, this.getSimpleName(localHomeName), this.getPackageName(localHomeName));

				localHomeInterfaceCmd.setMethodCollection(this.getClientViewModel().getHomeMethodCollection());
			} // if
		} // if
	} // createLocalHomeCommand

	/**
	 * Creates the local create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected void createLocalCommand(EnterpriseBeanCommand ejbCommand) {

		JavaClass localInterface = this.getClientViewModel().getEjbBean().getLocalInterface();

		if (localInterface != null) {
			new AddLocalInterfaceCommand(ejbCommand, localInterface);
		} else {
			String localName = this.getClientViewModel().getLocalExistingName();

			if (localName != null && localName.length() > 0) {

				CreateLocalInterfaceCommand localInterfaceCommand = new CreateLocalInterfaceCommand(ejbCommand, this.getSimpleName(localName), this.getPackageName(localName));
				localInterfaceCommand.setMethodCollection(this.getClientViewModel().getMethodCollection());
			} // if
		} // if
	} // createLocalCommand

	/**
	 * Gets an error message.
	 * 
	 * @return String
	 */
	protected String errorMessage() {
		return EJBCodeGenResourceHandler.getString("Error_in_Client_View_Creation_1"); //$NON-NLS-1$
	} // errorMessage

} // CreateClientViewOperation
