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

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbPackage;


/**
 * Insert the type's description here. Creation date: (9/5/2000 10:03:27 PM)
 * 
 * @author: Administrator
 */
public class CreatePersistentAttributeCommand extends PersistentAttributeCommand {
	private int attributeIndex = -1;
	private int keyAttributeIndex = -1;
	private String id;

	/**
	 * AddPersistentAttributeCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.commands.ICommand
	 */
	public CreatePersistentAttributeCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public CreatePersistentAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public CreatePersistentAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
		setCreate(true);
	}

	/**
	 * Add a persistent attribute to the EJB. If the <code>key</code> flag is set to
	 * <code>true</code> then add to the keyFeatures.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		if (getEjb() == null) {
			nullEJBError();
			return;
		}
		ContainerManagedEntity cmp = getContainerManagedEntity();
		CMPAttribute newAttribute = cmp.getPersistentAttribute(getName());
		if (newAttribute == null) {
			newAttribute = getAttribute();
			if (newAttribute == null)
				newAttribute = createPersistentAttribute(getName());
			if (attributeIndex > -1)
				cmp.getPersistentAttributes().add(attributeIndex, newAttribute);
			else
				cmp.getPersistentAttributes().add(newAttribute);
		}
		setAttribute(newAttribute);
		if (isKey() && !cmp.getKeyAttributes().contains(newAttribute)) { //The
			// CMPKeySynchronizationAdapter
			// adds it to the key.
			if (keyAttributeIndex > -1)
				cmp.getKeyAttributes().add(keyAttributeIndex, newAttribute);
			else
				cmp.getKeyAttributes().add(newAttribute);
		}
	}

	protected CMPAttribute createPersistentAttribute(String aName) {
		CMPAttribute attribute = EjbPackage.eINSTANCE.getEjbFactory().createCMPAttribute();
		attribute.setName(aName);
		attribute.setEType(reflectType());
		if (id != null)
			((XMIResource) getEJBJar().eResource()).setID(attribute, id);
		return attribute;
	}

	protected String getTypeNameForReflection() {
		String typeName = getTypeName();
		if (typeName != null) {
			for (int i = 0; i < getArrayDimensions(); i++)
				typeName += "[]"; //$NON-NLS-1$
		}
		return typeName;
	}

	protected JavaHelpers reflectType() {
		String fullTypeName = getTypeNameForReflection();
		if (fullTypeName == null || fullTypeName.length() <= 0)
			return null;
		return JavaRefFactory.eINSTANCE.reflectType(fullTypeName, getEjb());
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setCreate();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		super.setupInverseHelper();
		getInverseHelper().setDelete();
	}

	/**
	 * Undo the the addition of the persistent attribute as well as removal from the key features if
	 * necessary.
	 */
	protected void undoMetadataGeneration() {
		if (getAttribute() != null && getContainerManagedEntity() != null) {
			if (isKey())
				getContainerManagedEntity().getKeyAttributes().remove(getAttribute());
			getContainerManagedEntity().getPersistentAttributes().remove(getAttribute());
		}
		super.undoMetadataGeneration();
	}

	/**
	 * Sets the attributeIndex for the attribute that will be created at a particular index.
	 * 
	 * @param attributeIndex
	 *            The attributeIndex to set
	 */
	public void setAttributeIndex(int attributeIndex) {
		this.attributeIndex = attributeIndex;
	}

	/**
	 * Sets the keyAttributeIndex for the attribute that will be created at a particular index. This
	 * index will only be used if this command is set to key.
	 * 
	 * @param attributeIndex
	 *            The attributeIndex to set
	 */
	public void setKeyAttributeIndex(int attributeIndex) {
		this.keyAttributeIndex = attributeIndex;
	}

	/**
	 * This id will be set on the new attribute. This id will not be checked for uniqueness.
	 */
	public void setAttributeID(String anID) {
		id = anID;
	}
}