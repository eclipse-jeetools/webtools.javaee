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
/*
 * Created on Aug 14, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.earcreation;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;

import com.ibm.etools.j2ee.internal.project.EAREditModel;


/**
 * @author schacher
 */
public class RemoveUtilityJARMapCommand extends UtilityJARInEARProjectCommand {

	public static final String LABEL = EARCreationResourceHandler.getString("REM_UTIL_JAR_UI"); //$NON-NLS-1$

	public RemoveUtilityJARMapCommand(EAREditModel earEditModel, String aUri, IProject aProject) {
		super(earEditModel, aUri, aProject, LABEL);
	}

	public RemoveUtilityJARMapCommand(IProject anEarProject, String aUri, IProject aProject) {
		super(anEarProject, aUri, aProject, LABEL);
	}

	public RemoveUtilityJARMapCommand(IProject anEarProject, UtilityJARMapping jarMapping, IProject moduleProject) {
		super(anEarProject, null, moduleProject, LABEL);
		map = jarMapping;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.earcreation.UtilityJARInEARProjectCommand#primUndo()
	 */
	protected void primUndo() {
		addMapping();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.earcreation.UtilityJARInEARProjectCommand#primExecute()
	 */
	protected void primExecute() {
		removeMapping();
	}

}