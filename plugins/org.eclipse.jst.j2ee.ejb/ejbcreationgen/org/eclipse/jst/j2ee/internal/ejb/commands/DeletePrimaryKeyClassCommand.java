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



import org.eclipse.jem.java.JavaClass;

/**
 * Insert the type's description here. Creation date: (6/29/2001 12:15:32 AM)
 * 
 * @author: Administrator
 */
public class DeletePrimaryKeyClassCommand extends CreatePrimaryKeyClassCommand {
	/**
	 * DeletePrimaryKeyClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.commands.IRootCommand
	 * @param aJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 */
	public DeletePrimaryKeyClassCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
	}

	/**
	 * Update the primaryKey attribute for the EJB.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		JavaClass pk = getJavaClass();
		if (pk != null)
			getEntity().setPrimaryKey(null);
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setDelete();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		super.setupHelper();
		getHelper().setCreate();
	}
}