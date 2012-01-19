/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.extension;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Shell;

public interface IEJBClientActionExtender {
	/**
	 * Check to see if an extension is capable of creating an EJB Client jar project for the selected EJB project
	 * 
	 * @param project - the EJB Project for which an EJB Client is to be created
	 * @return true if the extender can create an EJB Client jar project for this EJB project
	 */
	boolean allowEJBClientCreation(IProject project);

	/**
	 * Launch the appropriate wizard (or action if no UI) to create an EJB Client jar for the selected EJB project
	 * 
	 * @param shell - the current shell
	 * @param project - the EJB Project for which an EJB Client is to be created
	 * @return Window.OK if the wizard completed successfully
	 */
	int showEJBClientCreationWizard(Shell shell, IProject project);

	/**
	 * Check to see if an extension is capable of removing an EJB Client jar project from the selected EJB project
	 * 
	 * @param project - the EJB Project for which an EJB Client is to be removed
	 * @return true if the extender can remove an EJB Client jar project from this EJB project
	 */
	boolean allowEJBClientRemoval(IProject project);

	/**
	 * Launch the appropriate wizard (or action if no UI) to remove an EJB Client jar from the selected EJB project
	 * 
	 * @param shell - the current shell
	 * @param project - the EJB Project from which an EJB Client is to be removed
	 * @return Window.OK if the wizard completed successfully
	 */
	IStatus performEJBClientRemoval(Shell shell, IProject project);
}
