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



import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.common.CMPJavaChangeSynchronizationAdapter;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.KeyPropagatorRoleHelper;


/**
 * Insert the type's description here. Creation date: (8/10/2001 3:32:52 PM)
 * 
 * @author: Administrator
 */
public class KeyPropagatorRoleCommand extends PersistentRoleCommand {
	/**
	 * KeyPropagatorRoleCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aName
	 *            java.lang.String
	 * @param shouldGenJava
	 *            boolean
	 * @param shouldGenMetadata
	 *            boolean
	 */
	public KeyPropagatorRoleCommand(IRootCommand parent, CommonRelationshipRole aRole, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aRole.getName(), shouldGenJava, shouldGenMetadata);
		setCommonRole(aRole);
	}

	/**
	 * primCreateCodegenHelper method comment.
	 */
	protected EJBGenerationHelper createCodegenHelper() {
		return new KeyPropagatorRoleHelper(getCommonRole());
	}

	/**
	 * Return a helper that is the inverse of the codegen helper.
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper createInverseCodegenHelper() {
		return new KeyPropagatorRoleHelper(getOriginalCommonRole());
	}

	/**
	 *  
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		getCommonRole().reconcileAttributes();
		preserveKeyIndicator();
	}

	/**
	 * If the role used to be a key, then make sure that it is still a key. This is necessary if one
	 * of the derived key fields was renamed (i.e., deleted and re-created).
	 */
	private void preserveKeyIndicator() {
		CommonRelationshipRole newRole, oldRole;
		newRole = getCommonRole();
		oldRole = (CommonRelationshipRole) getMetadataCopy();
		if (oldRole != null && oldRole.isKey() && !newRole.isKey()) {
			ContainerManagedEntity cmp = newRole.getSourceEntity();
			if (cmp != null)
				cmp.getKeyAttributes().addAll(newRole.getAttributes());
		}
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setUpdate();
	}

	/**
	 * Reconcile the attributes for our role.
	 */
	protected void undoMetadataGeneration() {
		super.undoMetadataGeneration();
		getCommonRole().reconcileAttributes();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.PersistentRoleCommand#initializeFeature()
	 */
	protected void initializeFeature() {
		//do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		//Disable notifications to key synch
		CMPJavaChangeSynchronizationAdapter.disable((ContainerManagedEntity) getEjb());
		super.execute();
	}

}