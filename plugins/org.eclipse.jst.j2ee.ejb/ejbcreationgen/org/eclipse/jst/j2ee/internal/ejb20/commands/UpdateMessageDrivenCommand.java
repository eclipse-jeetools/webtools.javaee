/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


public class UpdateMessageDrivenCommand extends MessageDrivenCommand {

	/**
	 * Constructor for UpdateMessageDrivenCommand.
	 * 
	 * @param anEjb
	 * @param anEditModel
	 */
	public UpdateMessageDrivenCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/*
	 * @see AbstractEJBRootCommand#getTaskName()
	 */
	public String getTaskName() {
		return getUpdatingTaskName();
	}

	public boolean shouldGenerateJava() {
		return shouldGenerateJavaForModify();
	}

}