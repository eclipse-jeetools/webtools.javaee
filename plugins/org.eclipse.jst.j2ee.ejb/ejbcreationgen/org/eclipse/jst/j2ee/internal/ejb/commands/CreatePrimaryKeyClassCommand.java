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


import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.util.CMPKeySynchronizationAdapter;
import org.eclipse.jst.j2ee.internal.common.CMPJavaChangeSynchronizationAdapter;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.PrimaryKeyHelper;


/**
 * Insert the type's description here. Creation date: (11/21/2000 12:29:52 PM)
 * 
 * @author: Administrator
 */
public class CreatePrimaryKeyClassCommand extends EJBClassReferenceCommand {
	private Adapter keyAdapter = null;
	private Adapter javaKeyAdapter = null;

	/**
	 * CreatePrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 */
	public CreatePrimaryKeyClassCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
	}

	/**
	 * CreatePrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 * @param shouldGenJava
	 *            boolean
	 */
	public CreatePrimaryKeyClassCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenJava) {
		super(parent, aJavaClass, shouldGenJava);
	}

	/**
	 * CreatePrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 * @param shouldGenJava
	 *            boolean
	 * @param shouldGenMetadata
	 *            boolean
	 */
	public CreatePrimaryKeyClassCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aJavaClass, shouldGenJava, shouldGenMetadata);
	}

	/**
	 * CreatePrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClassName
	 *            java.lang.String
	 * @param aPackageName
	 *            java.lang.String
	 */
	public CreatePrimaryKeyClassCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		super(parent, aJavaClassName, aPackageName);
	}

	/**
	 * CreatePrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClassName
	 *            java.lang.String
	 * @param aPackageName
	 *            java.lang.String
	 * @param shouldGenJava
	 *            boolean
	 */
	public CreatePrimaryKeyClassCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenJava) {
		super(parent, aJavaClassName, aPackageName, shouldGenJava);
	}

	/**
	 * CreatePrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand
	 * @param aJavaClassName
	 *            java.lang.String
	 * @param aPackageName
	 *            java.lang.String
	 * @param shouldGenJava
	 *            boolean
	 * @param shouldGenMetadata
	 *            boolean
	 */
	public CreatePrimaryKeyClassCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aJavaClassName, aPackageName, shouldGenJava, shouldGenMetadata);
	}

	protected EJBGenerationHelper createCodegenHelper() {
		return new PrimaryKeyHelper(getJavaClass());
	}

	protected EJBGenerationHelper createInverseCodegenHelper() {
		return new PrimaryKeyHelper(getOriginalJavaClass());
	}

	/**
	 * Update the primaryKey attribute for the EJB.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		if (getEjb() == null) {
			failedSettingClass();
			return;
		}
		JavaClass pkClass = getJavaClass();
		try {
			if (pkClass == null) {
				disableCMPChangeSynchronizationAdapters(true);
				getEntity().setPrimaryKeyName(getQualifiedName());
				setJavaClass(getEntity().getPrimaryKey());
			} else {
				disableCMPChangeSynchronizationAdapters(false);
				getEntity().setPrimaryKey(pkClass);
				updatePrimKeyField(pkClass);
			}
		} finally {
			enableCMPChangeSynchronizationAdapters();
		}
	}

	private void disableCMPChangeSynchronizationAdapters(boolean disableKeySynchronizer) {
		//	  DISABLE notification to key sycnhronization adapter during key
		// class creation
		List adapters = getEntity().eAdapters();
		if (!adapters.isEmpty()) {
			Iterator synchAdaptersIt = adapters.iterator();
			while (synchAdaptersIt.hasNext()) {
				Adapter next = (Adapter) synchAdaptersIt.next();
				if (disableKeySynchronizer && next instanceof CMPKeySynchronizationAdapter) {
					keyAdapter = next;
					synchAdaptersIt.remove();
					continue;
				} else if (next instanceof CMPJavaChangeSynchronizationAdapter) {
					javaKeyAdapter = next;
					synchAdaptersIt.remove();
				}
			}
		}
	}

	private void enableCMPChangeSynchronizationAdapters() {
		List adapters = getEntity().eAdapters();
		// ENABLE notification for key synchronization
		if (keyAdapter != null) {
			adapters.add(keyAdapter);
			keyAdapter = null;
		}
		if (javaKeyAdapter != null) {
			adapters.add(javaKeyAdapter);
			javaKeyAdapter = null;
		}
	}

	/**
	 * @param primaryKeyClass
	 * @todo Generated comment
	 */
	private void updatePrimKeyField(JavaClass primaryKeyClass) {
		if (getEntity().isContainerManagedEntity()) {
			CMPAttribute primKeyField = null;
			ContainerManagedEntity cmp = (ContainerManagedEntity) getEntity();
			if (cmp.getKeyAttributes().size() == 1) {
				CMPAttribute key = (CMPAttribute) cmp.getKeyAttributes().get(0);
				if (primaryKeyClass == key.getType())
					primKeyField = key;
			}
			cmp.setPrimKeyField(primKeyField);
		}
	}

	/**
	 * Find the original primaryKey
	 */
	public EObject findOriginalSourceMetaType() {
		Entity ejb = (Entity) getOriginalEjb();
		if (ejb != null)
			return ejb.getPrimaryKey();
		return null;
	}

	protected Entity getEntity() {
		return (Entity) getEjb();
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
	 * Undo the homeInterfaceClass updates.
	 */
	protected void undoMetadataGeneration() {
		if (getEntity() != null)
			getEntity().setPrimaryKey(getOriginalJavaClass());
		super.undoMetadataGeneration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.EJBCommand#executeForCodeGeneration()
	 */
	protected void executeForCodeGeneration() throws CommandExecutionFailure {
		if (!generateJava)
			return;
		super.executeForCodeGeneration();
		if (getEjb() instanceof ContainerManagedEntity)
			((ContainerManagedEntity) getEjb()).setPrimKeyField(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		//Disable notifications to key synch
		if (getEjb() instanceof ContainerManagedEntity)
			CMPJavaChangeSynchronizationAdapter.disable((ContainerManagedEntity) getEjb());
		super.execute();
	}
}