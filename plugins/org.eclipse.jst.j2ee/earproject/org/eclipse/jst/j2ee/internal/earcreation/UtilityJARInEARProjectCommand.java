/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;


import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.plugin.LibCopyBuilder;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

public abstract class UtilityJARInEARProjectCommand extends AbstractCommand {
	protected EAREditModel editModel;
	protected IProject earProject;
	protected String uri;
	protected IProject project;
	protected UtilityJARMapping map;

	/**
	 * AddModuleMapCommand constructor comment.
	 */
	protected UtilityJARInEARProjectCommand() {
		super();
	}

	/**
	 * AddModuleMapCommand constructor comment.
	 */
	public UtilityJARInEARProjectCommand(EAREditModel earEditModel, String aUri, IProject aProject, String label) {
		super(label);
		editModel = earEditModel;
		if (earEditModel != null)
			earProject = earEditModel.getProject();
		project = aProject;
		uri = aUri;
	}

	public UtilityJARInEARProjectCommand(IProject anEarProject, String aUri, IProject aProject, String label) {
		super(label);
		earProject = anEarProject;
		project = aProject;
		uri = aUri;
	}

	/**
	 * @return
	 */
	protected IFile[] getAffectedFiles() {
		return new IFile[]{earProject.getFile(EAREditModel.MODULE_MAP_URI), earProject.getFile(".project") //$NON-NLS-1$
		};
	}

	public boolean canUndo() {
		return map != null;
	}

	/**
	 * This will perform the command activity required for the effect. The effect of calling execute
	 * when canExecute returns false, or when canExecute hasn't been called, is undefined.
	 */
	public void execute() {
		boolean createdEditModel = editModel == null;
		setupEditModel();
		try {
			primExecute();
			if (createdEditModel)
				editModel.saveIfNecessary(this);
		} finally {
			if (createdEditModel) {
				editModel.releaseAccess(this);
				editModel = null;
			}
		}
	}

	/**
	 * This will perform the command activity required for the effect. The effect of calling execute
	 * when canExecute returns false, or when canExecute hasn't been called, is undefined.
	 */
	public void undo() {
		boolean createdEditModel = editModel == null;
		setupEditModel();
		try {
			primUndo();
			if (createdEditModel)
				editModel.saveIfNecessary(this);
		} finally {
			if (createdEditModel) {
				editModel.releaseAccess(this);
				editModel = null;
			}
		}
	}

	protected abstract void primUndo();

	protected abstract void primExecute();

	protected void setupEditModel() {
		ResourcesPlugin.getWorkspace().validateEdit(getAffectedFiles(), null);
		if (editModel == null && earProject != null) {
			EARNatureRuntime nature = EARNatureRuntime.getRuntime(earProject);
			if (nature != null)
				editModel = nature.getEarEditModelForWrite(this);
		}
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * This will again perform the command activity required to redo the effect after undoing the
	 * effect. The effect, if any, of calling redo before undo is called is undefined. Note that if
	 * you implement redo to call execute then any derived class will be restricted to by that
	 * decision also.
	 */
	public void redo() {
		execute();
	}

	protected void addLibCopyBuilder(IProject aProject) {
		try {
			ProjectUtilities.addToBuildSpec(LibCopyBuilder.BUILDER_ID, aProject);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		}
	}

	protected void removeLibCopyBuilder(IProject aProject) {
		//If this project is in other EARs, then leave the builder there
		if (J2EEProjectUtilities.getFirstReferencingEARProject(aProject) != null)
			return;

		try {
			ProjectUtilities.removeFromBuildSpec(LibCopyBuilder.BUILDER_ID, aProject);
		} catch (ResourceException e) {
			//this is on a delete...do nothing if already deleted
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		}
	}

	public void addMapping() {
		setExistingMap();
		if (map == null)
			map = editModel.addUtilityJARMapping(uri, project);
		else
			editModel.getUtilityJARMappings().add(map);
		addLibCopyBuilder(project);
	}

	protected void removeMapping() {
		setExistingMap();
		if (map != null)
			editModel.getUtilityJARMappings().remove(map);
		removeLibCopyBuilder(project);
	}

	protected void setExistingMap() {
		if (map == null)
			map = editModel.getUtilityJARMapping(uri, project);
	}

}