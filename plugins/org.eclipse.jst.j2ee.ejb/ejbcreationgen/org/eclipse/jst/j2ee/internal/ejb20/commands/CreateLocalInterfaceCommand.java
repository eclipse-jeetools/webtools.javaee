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
 * Insert the type's description here. Creation date: (9/4/2000 10:31:28 PM)
 * 
 * @author: Administrator
 */
public class CreateLocalInterfaceCommand extends EJBClassReferenceCommand {
	/**
	 * AddRemoteInterfaceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 * @param aJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 */
	public CreateLocalInterfaceCommand(IRootCommand parent, JavaClass aJavaClass) {
		super(parent, aJavaClass);
	}

	/**
	 * AddRemoteInterfaceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public CreateLocalInterfaceCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenMetadata) {
		super(parent, aJavaClass, true, shouldGenMetadata);
	}

	/**
	 * AddRemoteInterfaceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 * @param aJavaClassName
	 *            java.lang.String
	 * @param aPackageName
	 *            java.lang.String
	 */
	public CreateLocalInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		super(parent, aJavaClassName, aPackageName);
	}

	/**
	 * AddRemoteInterfaceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public CreateLocalInterfaceCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenMetadata) {
		super(parent, aJavaClassName, aPackageName, true, shouldGenMetadata);
	}

	protected EJBGenerationHelper createCodegenHelper() {
		EJBClassReferenceHelper ejbClassRefHelper = new EJBClassReferenceHelper(getJavaClass());
		ejbClassRefHelper.setLocalHelper();
		return ejbClassRefHelper;
	}

	protected EJBGenerationHelper createInverseCodegenHelper() {
		EJBClassReferenceHelper ejbClassRefHelper = new EJBClassReferenceHelper(getOriginalJavaClass());
		ejbClassRefHelper.setLocalHelper();
		return ejbClassRefHelper;
	}

	/**
	 * Update the remoteInterfaceClass attribute for the EJB.
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		super.executeForMetadataGeneration();
		if (getEjb() == null) {
			failedSettingClass();
			return;
		}
		JavaClass remote = getJavaClass();
		if (remote == null) {
			getEjb().setLocalInterfaceName(getQualifiedName());
			setJavaClass(getEjb().getLocalInterface());
		} else
			getEjb().setLocalInterface(remote);
	}

	/**
	 * Find the original remoteInterface
	 */
	public EObject findOriginalSourceMetaType() {
		EnterpriseBean ejb = getOriginalEjb();
		if (ejb != null)
			return ejb.getRemoteInterface();
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
	 * Undo the remoteInterfaceClass updates.
	 */
	protected void undoMetadataGeneration() throws CommandExecutionFailure {
		if (getEjb() != null)
			getEjb().setLocalInterface(getOriginalJavaClass());
		super.undoMetadataGeneration();
	}
}