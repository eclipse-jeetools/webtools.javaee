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
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.CommandExecutionFailure;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;


/**
 * Insert the type's description here. Creation date: (9/4/2000 7:05:29 PM)
 * 
 * @author: Administrator
 */
public class CreateLocalHomeInterfaceCommand extends EJBClassReferenceCommand {
	/**
	 * AddHomeInterfaceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public CreateLocalHomeInterfaceCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
	}

	/**
	 * AddBeanClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public CreateLocalHomeInterfaceCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenMetadata) {
		super(parent, aJavaClass, true, shouldGenMetadata);
	}

	/**
	 * AddHomeInterfaceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public CreateLocalHomeInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		super(parent, aJavaClassName, aPackageName);
	}

	/**
	 * AddBeanClassCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public CreateLocalHomeInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenMetadata) {
		super(parent, aJavaClassName, aPackageName, true, shouldGenMetadata);
	}

	protected EJBGenerationHelper createCodegenHelper() {
		EJBClassReferenceHelper ejbClassRefHelper = new EJBClassReferenceHelper(getJavaClass());
		ejbClassRefHelper.setLocalHomeHelper();
		return ejbClassRefHelper;
	}

	protected EJBGenerationHelper createInverseCodegenHelper() {
		EJBClassReferenceHelper ejbClassRefHelper = new EJBClassReferenceHelper(getOriginalJavaClass());
		ejbClassRefHelper.setLocalHomeHelper();
		return ejbClassRefHelper;
	}

	/**
	 * Update the homeInterfaceClass attribute for the EJB.
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		super.executeForMetadataGeneration();
		if (getEjb() == null) {
			failedSettingClass();
			return;
		}
		JavaClass home = getJavaClass();
		if (home == null) {
			getEjb().setLocalHomeInterfaceName(getQualifiedName());
			setJavaClass(getEjb().getLocalHomeInterface());
		} else
			getEjb().setLocalHomeInterface(home);
	}

	/**
	 * Find the original homeInterface
	 */
	public EObject findOriginalSourceMetaType() {
		EnterpriseBean ejb = getOriginalEjb();
		if (ejb != null)
			return ejb.getHomeInterface();
		return null;
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
	protected void undoMetadataGeneration() throws CommandExecutionFailure {
		if (getEjb() != null)
			getEjb().setLocalHomeInterface(getOriginalJavaClass());
		super.undoMetadataGeneration();
	}
}