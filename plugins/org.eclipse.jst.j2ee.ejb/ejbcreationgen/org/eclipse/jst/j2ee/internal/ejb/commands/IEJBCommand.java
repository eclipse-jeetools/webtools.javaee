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


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;


/**
 * Insert the type's description here. Creation date: (9/4/2000 10:55:47 PM)
 * 
 * @author: Administrator
 */
public interface IEJBCommand extends Command {
	void append(EJBGenerationHelper aHelper);

	void appendInverse(EJBGenerationHelper aHelper);

	/**
	 * Return the number of units that are being worked.
	 */
	int calculateTotalWork();

	EnterpriseBeanCodegenCommand getCodegenCommand();

	public EObject getCopy(EObject anObject);

	EtoolsCopyUtility getCopyUtility();

	public EnterpriseBean getEjb();

	EObject getSourceMetaType();

	boolean isEnterpriseBeanRootCommand();

	boolean isRootCommand();

	void setGenerateJava(boolean newGenerateJava);

	void setGenerateMetadata(boolean newGenerateMetadata);

	boolean shouldGenerateJava();

	/**
	 * Notifies that a subtask of the main task is beginning. Subtasks are optional; the main task
	 * might not have subtasks.
	 * 
	 * @param name
	 *            the name (or description) of the subtask
	 */
	void subTask(String name);

	/**
	 * Set the number of units worked with the ProgressMonitor.
	 */

	void worked(int units);

	/**
	 * Should only be called by commands that require the ejbcommandcopier be cleared before running
	 * a propagation
	 */

	void setShouldClearCopierOnPropagation(boolean shouldClearCopier);
}