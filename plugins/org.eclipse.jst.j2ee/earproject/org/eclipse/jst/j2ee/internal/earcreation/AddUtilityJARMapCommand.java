/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;


import org.eclipse.core.resources.IProject;

import com.ibm.etools.j2ee.internal.project.EAREditModel;


public class AddUtilityJARMapCommand extends UtilityJARInEARProjectCommand {

	public static final String LABEL = EARCreationResourceHandler.getString("Add_Utility_JAR_UI"); //$NON-NLS-1$

	/**
	 * AddModuleMapCommand constructor comment.
	 */
	public AddUtilityJARMapCommand(EAREditModel earEditModel, String aUri, IProject aProject) {
		super(earEditModel, aUri, aProject, LABEL);
	}

	public AddUtilityJARMapCommand(IProject anEarProject, String aUri, IProject aProject) {
		super(anEarProject, aUri, aProject, LABEL);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.earcreation.UtilityJARInEARProjectCommand#primExecute()
	 */
	protected void primExecute() {
		addMapping();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.earcreation.UtilityJARInEARProjectCommand#primUndo()
	 */
	protected void primUndo() {
		removeMapping();
	}


}