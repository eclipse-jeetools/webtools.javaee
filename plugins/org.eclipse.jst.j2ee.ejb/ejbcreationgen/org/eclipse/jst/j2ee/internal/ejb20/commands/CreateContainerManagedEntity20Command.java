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
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCodegenCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * Insert the type's description here. Creation date: (8/22/2000 8:22:01 AM)
 * 
 * @author: Administrator
 */
public class CreateContainerManagedEntity20Command extends CreateContainerManagedEntityCommand {
	/**
	 * Constructor method for an CreateContainerManagedEntityCommand.
	 * 
	 * @param java.lang.String
	 *            aName
	 * @param java.lang.String
	 *            aFolderName
	 * 
	 * aName - The name and ID of the EJB. aProject - The project in which to create the folder.
	 */
	public CreateContainerManagedEntity20Command(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
		setVersion2_X(true);
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 10:01:03 AM)
	 */
	protected EnterpriseBeanCodegenCommand createCodegenCommand() {
		return new ContainerManagedEntity20CodegenCommand(getContainerManagedEntity());
	}

	/**
	 * createEJB method comment.
	 */
	protected EnterpriseBean createEJB() {
		ContainerManagedEntity bean = (ContainerManagedEntity) super.createEJB();
		bean.setVersion(ContainerManagedEntity.VERSION_2_X);
		bean.setAbstractSchemaName(bean.getName());
		return bean;
	}
}