/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.DeletePersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.DeletePrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.creation.CMPField;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class EditCmpFieldOperation extends WTPOperation {

	protected CMPAttribute attributeToEdit;
	protected CMPField editField;
	protected EJBNatureRuntime ejbNature;
	protected EJBEditModel editModel;
	protected String newKeyClassName;
	protected IOperationHandler opHandler;

	/**
	 * The <code>attribute</code> is the one being editted and <code>field</code> has the new
	 * information. If the attribute being editted is a primitive key attribute and the new field
	 * type is a valide primitive key attribute type, then this constructor will set the key class
	 * of the owning ContainerManagedEntity to the new key class type.
	 */
	public EditCmpFieldOperation(EJBNatureRuntime nature, CMPAttribute attribute, CMPField field, IOperationHandler handler) {
		ejbNature = nature;
		attributeToEdit = attribute;
		editField = field;
		opHandler = handler;
	}

	/**
	 * This constructor should be used if the <code>attribute</code> is a primitive key attribute
	 * and the <code>field</code> type is not a valid primitive key field type.
	 */
	public EditCmpFieldOperation(EJBNatureRuntime nature, CMPAttribute attribute, CMPField field, String qualifiedKeyClassName, IOperationHandler handler) {
		this(nature, attribute, field, handler);
		newKeyClassName = qualifiedKeyClassName;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.operations.HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		editModel = ejbNature.getEJBEditModelForWrite(this);
		try {
			primExecute(monitor);
		} finally {
			//Save if necessary should be done here once the commands
			//are corrected to remove the save.
			editModel.saveIfNecessary(this);
			editModel.releaseAccess(this);
		}
	}

	protected void primExecute(IProgressMonitor monitor) {
		ContainerManagedEntity cmp = (ContainerManagedEntity) attributeToEdit.eContainer();
		if (cmp == null)
			return;
		IRootCommand root = createCommand(cmp);
		root.setProgressMonitor(monitor);
		root.setOperationHandler(opHandler);
		editModel.getCommandStack().execute(root);
	}

	protected IRootCommand createCommand(ContainerManagedEntity cmp) {
		boolean onlyCapChange = isOnlyCapitalizationNameChange();
		boolean shouldGenJava = !onlyCapChange || cmp.getVersionID() <= J2EEVersionConstants.EJB_1_1_ID;
		IRootCommand root = new UpdateContainerManagedEntityCommand(cmp, editModel);
		//delete old attribute
		new DeletePersistentAttributeCommand(root, attributeToEdit.getName(), shouldGenJava);
		//create new attribute
		setupCreateAttributeCommand(cmp, root, shouldGenJava);
		//Remove PK class if the old attribute was a primitive primary key field.
		if (attributeToEdit == cmp.getPrimKeyField()) {
			new DeletePrimaryKeyClassCommand(root, cmp.getPrimaryKey()).setGenerateJava(false);
			if (newKeyClassName != null) {
				new CreatePrimaryKeyClassCommand(root, JavaRefFactory.eINSTANCE.createClassRef(newKeyClassName));
			}
		}
		return root;
	}

	protected boolean isOnlyCapitalizationNameChange() {
		String attName = attributeToEdit.getName();
		if (Character.isUpperCase(attName.charAt(0)) && editField.getType().equals(attributeToEdit.getType().getQualifiedName())) {
			char[] name = attName.toCharArray();
			name[0] = Character.toLowerCase(name[0]);
			attName = new String(name);
			return attName.equals(editField.getName());
		}
		return false;
	}

	protected void setupCreateAttributeCommand(ContainerManagedEntity cmp, IRootCommand root, boolean shouldGenJava) {
		CreatePersistentAttributeCommand attCmd = new CreatePersistentAttributeCommand(root, editField.getName(), shouldGenJava);
		attCmd.setTypeName(editField.getType());
		if (editField.isIsArray())
			attCmd.setArrayDimensions(editField.getArrayDimension());
		attCmd.setGenerateAccessors(editField.isAccessWithGS());
		attCmd.setRemote(editField.isPromoteGS());
		attCmd.setLocal(editField.isPromoteLocalGS());
		attCmd.setIsReadOnly(editField.isGetterRO());
		attCmd.setAttributeIndex(cmp.getPersistentAttributes().indexOf(attributeToEdit));
		attCmd.setAttributeID(((XMIResource) attributeToEdit.eResource()).getID(attributeToEdit));
		if (editField.isIsKey()) {
			attCmd.setKey(true);
			attCmd.setKeyAttributeIndex(cmp.getKeyAttributes().indexOf(attributeToEdit));
		}
	}

}