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
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddPrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class CreateEntityBeanOperation extends CreateEnterpriseBeanWithClientViewOperation {
	public CreateEntityBeanOperation(CreateEntityBeanDataModel dataModel) {
		super(dataModel);
	}

	public CreateEntityBeanOperation() {
		super();
	}

	protected EnterpriseBeanCommand createRootCommand(String beanName) {
		return new CreateEntityCommand(beanName, (EJBEditModel) editModel);
	}

	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		super.createDependentCommands(ejbCommand);
		createKeyClassCommand(ejbCommand);
	}

	protected void createKeyClassCommand(EnterpriseBeanCommand ejbCommand) {
		String keyClassName = operationDataModel.getStringProperty(CreateEntityBeanDataModel.KEY_CLASS_NAME);
		JavaClass keyClass = reflectJavaClass(keyClassName);
		if (shouldGenerateClass(CreateEntityBeanDataModel.KEY_CLASS_NAME)) {
			new CreatePrimaryKeyClassCommand(ejbCommand, keyClass);
		} else {
			new AddPrimaryKeyClassCommand(ejbCommand, keyClass);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanOperation#createTemplateModel()
	 */
	protected CreateEnterpriseBeanTemplateModel createTemplateModel() {
		return new CreateEntityBeanTemplateModel((CreateEntityBeanDataModel) operationDataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanOperation#getTemplateFileName()
	 */
	protected String getTemplateFileName() {
		return "entityXDoclet.javajet"; //$NON-NLS-1$
	}
}