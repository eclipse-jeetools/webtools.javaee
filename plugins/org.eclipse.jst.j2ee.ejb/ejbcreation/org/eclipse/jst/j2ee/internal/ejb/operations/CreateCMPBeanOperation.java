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
 * Created on Feb 25, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.util.List;

import org.eclipse.jst.j2ee.internal.ejb.commands.AddPersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IPersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.creation.CMPField;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateContainerManagedEntity20Command;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class CreateCMPBeanOperation extends CreateEntityBeanOperation {
	private CreateCMPBeanDataModel cmpDM = null;

	public CreateCMPBeanOperation(CreateCMPBeanDataModel model) {
		super(model);
		cmpDM = model;
	}

	protected EnterpriseBeanCommand createRootCommand(String beanName) {
		if (cmpDM.isVersion2xOrGreater())
			return new CreateContainerManagedEntity20Command(beanName, (EJBEditModel) editModel);
		return new CreateContainerManagedEntityCommand(beanName, (EJBEditModel) editModel);
	}

	protected void createKeyClassCommand(EnterpriseBeanCommand ejbCommand) {
		if (!cmpDM.isSet(CreateCMPBeanDataModel.BEAN_SUPEREJB_NAME)) {
			super.createKeyClassCommand(ejbCommand);
		}
	}

	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		super.createDependentCommands(ejbCommand);
		createAttributeCommands(ejbCommand);
	}

	protected void createAttributeCommands(EnterpriseBeanCommand ejbCommand) {
		IPersistentAttributeCommand attributeCommand;
		List fields = ((CreateCMPBeanDataModel) operationDataModel).getCMPFields();
		if (fields == null)
			return;
		CMPField field;
		for (int i = 0; i < fields.size(); i++) {
			field = (CMPField) fields.get(i);
			if (shouldGenerateClass(CreateEnterpriseBeanWithClientViewDataModel.BEAN_CLASS_NAME) || (shouldGenerateClass(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_INTERFACE) && field.isPromoteGS()) || (cmpDM.isVersion2xOrGreater() && shouldGenerateClass(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_INTERFACE) && field.isPromoteLocalGS()))
				attributeCommand = new CreatePersistentAttributeCommand(ejbCommand, field.getName(), true);
			else
				attributeCommand = new AddPersistentAttributeCommand(ejbCommand, field.getName());
			initializePersistentAttributeCommand(ejbCommand, attributeCommand, field);
		}
	}

	protected void initializePersistentAttributeCommand(EnterpriseBeanCommand ejbCommand, IPersistentAttributeCommand attributeCommand, CMPField field) {
		if (attributeCommand == null)
			return;
		attributeCommand.setTypeName(field.getType());
		attributeCommand.setInitializer(field.getInitialValue());
		attributeCommand.setKey(field.isIsKey());
		attributeCommand.setGenerateAccessors(field.isAccessWithGS() || cmpDM.isVersion2xOrGreater());
		if (field.isIsArray())
			attributeCommand.setArrayDimensions(field.getArrayDimension());
		if (cmpDM.isVersion2xOrGreater()) {
			if (cmpDM.getBooleanProperty(CreateCMPBeanDataModel.ADD_REMOTE))
				attributeCommand.setRemote(field.isPromoteGS());
			if (cmpDM.getBooleanProperty(CreateCMPBeanDataModel.ADD_LOCAL))
				attributeCommand.setLocal(field.isPromoteLocalGS());
		} else {
			attributeCommand.setRemote(field.isPromoteGS());
			attributeCommand.setIsReadOnly(field.isGetterRO());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperation#getTemplateFileName()
	 */
	protected String getTemplateFileName() {
		return cmpDM.isVersion2xOrGreater() ? "cmp2xEntityXDoclet.javajet" : "cmp1xEntityXDoclet.javajet"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperation#createTemplateModel()
	 */
	protected CreateEnterpriseBeanTemplateModel createTemplateModel() {
		return new CreateCMPEntityBeanTemplateModel((CreateEntityBeanDataModel) operationDataModel);
	}
}