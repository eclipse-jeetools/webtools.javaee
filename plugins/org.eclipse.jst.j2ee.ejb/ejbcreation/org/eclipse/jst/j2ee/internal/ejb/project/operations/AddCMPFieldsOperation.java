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
 * Created on Apr 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddPersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IPersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.creation.CMPField;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;



/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class AddCMPFieldsOperation extends EditModelOperation {
	/**
	 * @param dataModel
	 */
	public AddCMPFieldsOperation(AddCMPFieldsDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		AddCMPFieldsDataModel model = (AddCMPFieldsDataModel) operationDataModel;
		ContainerManagedEntity bean = (ContainerManagedEntity) model.getProperty(AddCMPFieldsDataModel.ENTERPRISE_BEAN);
		List cmpFields = (List) model.getProperty(AddCMPFieldsDataModel.CMP_FIELD_LIST);
		int size = cmpFields.size();
		if (size == 0)
			return;
		String keyPackageName = model.getStringProperty(AddCMPFieldsDataModel.KEY_PACKAGE_NAME);
		String keyClassName = model.getStringProperty(AddCMPFieldsDataModel.KEY_CLASS_NAME);
		if ((keyClassName == null || keyClassName.equals("")) && bean.getPrimaryKey() != null) { //$NON-NLS-1$
			keyClassName = bean.getPrimaryKey().getName();
			keyPackageName = bean.getPrimaryKey().getJavaPackage().getPackageName();
		}
		IRootCommand root = new UpdateContainerManagedEntityCommand(bean, (EJBEditModel) editModel);
		for (int i = 0; i < size; i++) {
			CMPField cmpField = (CMPField) cmpFields.get(i);
			createAttributeCommand(root, cmpField);
		}
		boolean shouldCreatePrimaryKeyClass = !model.getStringProperty(AddCMPFieldsDataModel.KEY_CLASS_NAME).equals("") && keyPackageName != null && keyClassName != null; //$NON-NLS-1$
		if (shouldCreatePrimaryKeyClass) {
			new CreatePrimaryKeyClassCommand(root, keyClassName, keyPackageName);
		}
		getCommandStack().execute(root);
	}

	/**
	 * createRootCommand method comment.
	 */
	public IPersistentAttributeCommand createAttributeCommand(IRootCommand rootCommand, CMPField field) {
		IPersistentAttributeCommand attributeCommand = null;
		// attributeCommand;
		if (field.isExisting())
			attributeCommand = new AddPersistentAttributeCommand(rootCommand, field.getName());
		else {
			attributeCommand = new CreatePersistentAttributeCommand(rootCommand, field.getName());
			attributeCommand.setRemote(field.isPromoteGS());
			attributeCommand.setLocal(field.isPromoteLocalGS());
			attributeCommand.setGenerateAccessors(field.isAccessWithGS());
			if (field.isIsArray())
				attributeCommand.setArrayDimensions(field.getArrayDimension());
			attributeCommand.setInitializer(field.getInitialValue());
		}
		attributeCommand.setTypeName(field.getType());
		attributeCommand.setKey(field.isIsKey());
		attributeCommand.setIsReadOnly(field.isGetterRO());
		return attributeCommand;
	}

}