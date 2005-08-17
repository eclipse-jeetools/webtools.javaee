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
package org.eclipse.jst.j2ee.internal.earcreation;



import org.eclipse.core.resources.IProject;

import com.ibm.etools.j2ee.internal.project.EARNatureRuntime;



/**
 * Insert the type's description here. Creation date: (03/29/01 4:48:46 PM)
 * 
 * @author: Administrator
 */
public class AddModuleToEARProjectCommand extends ModuleInEARProjectCommand {
	public AddModuleToEARProjectCommand(IProject aNestedJ2EEProject, IProject anEarProject, String uri, String contextRoot, String altDD) {
		super(aNestedJ2EEProject, anEarProject, uri, contextRoot, altDD);
	}

	protected void addModule() {

		super.addModule();
		moduleSuccessful = true;
	}

	protected void addModuleMapping() {
		mapSuccessful = true; // Set this first, because failure in super will turn it to false
		super.addModuleMapping();
	}

	public boolean canExecute() {
		return super.canExecute() && EARNatureRuntime.getRuntime(getEarProject()) != null && org.eclipse.jst.j2ee.internal.project.J2EENature.getRegisteredRuntime(getNestedJ2EEProject()) != null;
	}

	protected void primExecute() {
		addModule();
		if (moduleSuccessful)
			addModuleMapping();
	}

	protected void primUndo() {
		if (mapSuccessful) {
			removeModuleMapping();
			mapSuccessful = false;
		}
		if (moduleSuccessful) {
			removeModule();
			moduleSuccessful = false;
		}

	}

	// override
	public String getLabel() {
		return label == null ? EARCreationResourceHandler.getString("Add_Module_Command_Label_UI_") : label;//$NON-NLS-1$
	}
}