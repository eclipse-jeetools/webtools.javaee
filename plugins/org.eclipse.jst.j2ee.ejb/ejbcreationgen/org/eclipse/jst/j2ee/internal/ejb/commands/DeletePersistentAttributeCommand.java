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
package org.eclipse.jst.j2ee.internal.ejb.commands;


import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.wst.common.emf.utilities.ExtendedEcoreUtil;


/**
 * Insert the type's description here. Creation date: (9/10/2000 7:20:39 PM)
 * 
 * @author: Administrator
 */
public class DeletePersistentAttributeCommand extends PersistentAttributeCommand {
	public DeletePersistentAttributeCommand(IRootCommand parent, CMPAttribute anAttribute) {
		super(parent, anAttribute.getName());
		setAttribute(anAttribute);
	}

	/**
	 * RemovePersistentAttributeCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 * @param aName
	 *            java.lang.String
	 */
	public DeletePersistentAttributeCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public DeletePersistentAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public DeletePersistentAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
		setDelete(true);
	}

	/**
	 * Remove the persistent attribute from the EJB. If the <code>key</code> flag is set to
	 * <code>true</code> then remove from the keyFeatures.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		if (getContainerManagedEntity() == null) {
			nullEJBError();
			return;
		}
		CMPAttribute att = getAttribute();
		if (att == null) {
			att = getContainerManagedEntity().getPersistentAttribute(getName());
			setAttribute(att);
		}
		if (att != null) {
			att.getType(); //ensure the type can be resolved
			if (att.isPrimKeyField()) {
				getContainerManagedEntity().setPrimKeyField(null);
			}
			ExtendedEcoreUtil.becomeProxy(att, att.eResource()); //make a proxy
			getContainerManagedEntity().getPersistentAttributes().remove(att);
			if (isKey()) {
				getContainerManagedEntity().getKeyAttributes().remove(att);

			}
		}
	}

	protected String getReadableTypeName() {
		JavaHelpers type = getAttribute().getType();
		return type == null ? null : type.getQualifiedName();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		AttributeHelper myHelper = (AttributeHelper) getHelper();
		//Make sure to check for methods
		myHelper.setGenerateAccessors(true);
		myHelper.setRemote(true);
		myHelper.setDelete();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		super.setupInverseHelper();
		AttributeHelper myHelper = (AttributeHelper) getInverseHelper();
		//Make sure to check for methods
		myHelper.setGenerateAccessors(true);
		myHelper.setRemote(true);
		myHelper.setCreate();
	}

	/**
	 * Undo the the removal of the persistent attribute as well as the addition to the key features
	 * if necessary.
	 */
	protected void undoMetadataGeneration() {
		if (getContainerManagedEntity() != null && getAttribute() != null) {
			ExtendedEcoreUtil.removeProxy(getAttribute(), getAttribute().eResource());
			getContainerManagedEntity().getPersistentAttributes().add(getAttribute());
		}
		if (isKey())
			getContainerManagedEntity().getKeyAttributes().add(getAttribute());
		super.undoMetadataGeneration();
	}
}