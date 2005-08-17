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


import java.io.OutputStream;
import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;

import com.ibm.etools.j2ee.internal.project.EAREditModel;
import com.ibm.etools.j2ee.internal.project.EARNatureRuntime;


/**
 * Insert the type's description here. Creation date: (03/29/01 4:48:46 PM)
 * 
 * @author: Administrator
 */
public class RollupRolesCommand extends AbstractCommand {
	protected IProject earProject;
	protected boolean saveResources;

	/**
	 * AddModuleToEARProjectCommand constructor comment.
	 */
	protected RollupRolesCommand() {
		super();
	}

	/**
	 * AddModuleToEARProjectCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 */
	protected RollupRolesCommand(String label) {
		super(label);
	}

	/**
	 * AddModuleToEARProjectCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 */
	protected RollupRolesCommand(String label, String description) {
		super(label, description);
	}

	public RollupRolesCommand(IProject earProject, boolean saveResources) {
		super();
		this.earProject = earProject;
		this.saveResources = saveResources;
	}

	public boolean canExecute() {
		return super.canExecute() && com.ibm.etools.j2ee.internal.project.EARNatureRuntime.getRuntime(getEarProject()) != null;
	}

	public void dispose() {
		// TODO Edit Model Rework This line does not make any sense.
		//EARNatureRuntime.getRuntime(getEarProject()).getEarEditModelForWrite().releaseAccess();
		//	setEarProject(null);
	}

	/**
	 * @see Command
	 */
	public void execute() {
		try {
			EARFile earFile = (EARFile)EARNatureRuntime.getRuntime(getEarProject()).asArchive();
			earFile.rollUpRoles();
		} catch (OpenFailureException e) {
			org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(e);
		}
	}

	protected Application getApplication() {

		EAREditModel earEditModel = null;
		Application application = null;
		try {
			earEditModel = EARNatureRuntime.getRuntime(getEarProject()).getEarEditModelForWrite(this);
			application = earEditModel.getApplication();
		} finally {
			if (earEditModel != null)
				earEditModel.releaseAccess(this);
		}
		return application;
	}

	/**
	 * Insert the method's description here. Creation date: (03/29/01 5:34:26 PM)
	 * 
	 * @return org.eclipse.core.resources.IProject
	 */
	public org.eclipse.core.resources.IProject getEarProject() {
		return earProject;
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * @see Command
	 */
	public void redo() {
		execute();
	}

	protected void saveResources() throws Exception {
		Resource resource = getApplication().eResource();
		ResourceSet set = resource.getResourceSet();
		if (set != null) {
			OutputStream out = set.getURIConverter().createOutputStream(resource.getURI());
			resource.save(out, new HashMap());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (03/29/01 5:34:26 PM)
	 * 
	 * @param newEarProject
	 *            org.eclipse.core.resources.IProject
	 */
	public void setEarProject(org.eclipse.core.resources.IProject newEarProject) {
		earProject = newEarProject;
	}

	public void undo() {
	}
}