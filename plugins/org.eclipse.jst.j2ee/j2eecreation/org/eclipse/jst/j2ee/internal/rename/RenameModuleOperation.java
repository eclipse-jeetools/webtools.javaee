/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.rename;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.earcreation.AddModuleToEARProjectCommand;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.RemoveModuleFromEARProjectCommand;
import org.eclipse.jst.j2ee.internal.earcreation.UpdateModuleReferencesInEARProjectCommand;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.common.internal.emfworkbench.integration.ComposedEditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.LooseComposedEditModel;


public class RenameModuleOperation extends WTPOperation {
	protected RenameOptions options;
	protected IProject renamedProject = null;
	protected LooseComposedEditModel renameEditModel = null;
	/*
	 * key is an EAREditModel and the value is a list of rename commands. We cache the commands and
	 * execute later because the modules still need to exist when we go to change module dependency
	 * references.
	 */
	protected Map deferredCommands;
	protected IProgressMonitor monitor;
	protected RenameModuleDependencyOperation renameModuleDependencyOperation;
	protected List dirtyEarModels;
	protected static String EDIT_MODEL_KEY = "RENAME_MODULES"; //$NON-NLS-1$

	public RenameModuleOperation(RenameOptions options) {
		this.options = options;
	}

	/**
	 * @see HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		this.monitor = aMonitor;
		try {
			buildRenameCommandsIfNecessary();
			if (options.shouldRenameModuleDependencies()) {
				renameModuleDependencies();
			}
			checkIfEarsAreDirty();
			executeDeferredCommands();
			saveModelsIfNecessary();
		} finally {
			releaseModels();
		}
	}

	protected void checkIfEarsAreDirty() {
		dirtyEarModels = new ArrayList();
		if (deferredCommands == null)
			return;
		Iterator models = deferredCommands.keySet().iterator();
		while (models.hasNext()) {
			EAREditModel model = (EAREditModel) models.next();
			if (model.isDirty() && !dirtyEarModels.contains(model)) {
				dirtyEarModels.add(model);
			}
		}
	}

	protected void buildRenameCommands() {
		deferredCommands = new HashMap();
		List earProjects = EARNatureRuntime.getAllEARProjectsInWorkbench();
		for (int i = 0; i < earProjects.size(); i++) {
			IProject p = (IProject) earProjects.get(i);
			EARNatureRuntime runtime = EARNatureRuntime.getRuntime(p);
			if (runtime != null)
				buildRenameCommands(runtime);
		}
	}

	protected void buildRenameCommands(EARNatureRuntime runtime) {
		List commands = new ArrayList();
		List projects = options.getModuleAndUtilityProjects();
		EAREditModel model = null;
		try {
			model = runtime.getEarEditModelForWrite(this);
			if (options.shouldRenameModules()) {
				for (int i = 0; i < projects.size(); i++) {
					IProject project = (IProject) projects.get(i);
					Module m = runtime.getModule(project);
					if (m != null) {
						String uri;
						//Do not rename the module uri unless it was
						//the same as the original project name.
						boolean changedUri = false;
						if (!m.getUri().startsWith(project.getName()))
							uri = m.getUri();
						else {
							changedUri = true;
							// the following was taken from
							// WebProjectInfo.getDefaultUri()
							uri = options.getNewName().replace(' ', '_');
							uri += "." + URI.createURI(m.getUri()).fileExtension(); //$NON-NLS-1$
						}
						//AbstractCommand.class.getClass();
						CompoundCommand compoundCommand = new CompoundCommand();
						compoundCommand.append(new RemoveModuleFromEARProjectCommand(m, runtime.getProject()));
						renamedProject = project.getWorkspace().getRoot().getProject(options.getNewName());
						compoundCommand.append(new AddModuleToEARProjectCommand(renamedProject, runtime.getProject(), uri, options.getNewContextRoot(), null));
						if (changedUri)
							compoundCommand.append(new UpdateModuleReferencesInEARProjectCommand(runtime.getProject(), m, uri));
						commands.add(compoundCommand);
					}
				}
			}
		} finally {
			if (!commands.isEmpty()) {
				deferredCommands.put(model, commands);
			} else {
				model.releaseAccess(this);
			}

		}
	}

	/**
	 * Do this for unhandled exceptions
	 */
	/**
	 * Do this for unhandled exceptions
	 */
	protected void releaseModels() {
		if (renameEditModel != null) {
			renameEditModel = null;
		}
		if (deferredCommands != null) {
			Iterator iterator = deferredCommands.keySet().iterator();
			EditModel model = null;
			while (iterator.hasNext()) {
				model = (EditModel) iterator.next();
				model.releaseAccess(this);
			}
			deferredCommands.clear();
		}
	}

	public void release() {
		releaseModels();
	}

	/**
	 * After a normal execution flow, save the models if necessary
	 */
	protected void saveModelsIfNecessary() {
		if (deferredCommands == null)
			return;
		Iterator models = deferredCommands.keySet().iterator();
		while (models.hasNext()) {
			EAREditModel model = (EAREditModel) models.next();
			if (model.isDirty()) {
				if (dirtyEarModels.contains(model)) {
					model.saveIfNecessary(this);
				} else {
					model.save(this);
				}
			}
		}
	}

	protected void renameModuleDependencies() throws InvocationTargetException, InterruptedException {
		getRenameModuleDependencyOperation().run(monitor);
	}

	protected void executeDeferredCommands() {
		Iterator it = deferredCommands.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			List commands = (List) entry.getValue();
			executeCommands(commands);
		}
	}

	protected void executeCommands(List commands) {
		for (int i = 0; i < commands.size(); i++) {
			AbstractCommand cmd = (AbstractCommand) commands.get(i);
			cmd.execute();
		}
	}

	/**
	 * Use this only for validate edits
	 */
	public ComposedEditModel getRenameEditModel() {
		if (renameEditModel == null)
			initializeRenameEditModel();
		return renameEditModel;
	}

	protected void initializeRenameEditModel() {
		if (options.getAllProjectsToRename().size() == 0) {
			throw new RuntimeException(J2EECreationResourceHandler.getString("RenameModuleOperation_UI_0")); //$NON-NLS-1$
		}
		IProject project = null;
		project = (IProject) options.getAllProjectsToRename().get(0);
		buildRenameCommandsIfNecessary();
		renameEditModel = new LooseComposedEditModel(EDIT_MODEL_KEY, (EMFWorkbenchContext) WorkbenchResourceHelper.getEMFContext(project)) {
			/**
			 * @see org.eclipse.jst.j2ee.internal.internal.workbench.EditModel#getNonResourceFiles()
			 */
			public List getNonResourceFiles() {
				List result = super.getNonResourceFiles();
				result.addAll(getAffectedNonResourceFiles());
				return result;
			}
		};
		Iterator it = deferredCommands.keySet().iterator();
		while (it.hasNext()) {
			renameEditModel.addChild((J2EEEditModel) it.next());
		}
	}

	protected void buildRenameCommandsIfNecessary() {
		if (deferredCommands != null && !deferredCommands.isEmpty())
			return;
		buildRenameCommands();
	}

	/**
	 * Return a list of all the non-resource files that will get modified as a result of running
	 * this operation; used for validate edit.
	 */
	protected List getAffectedNonResourceFiles() {
		if (options.shouldRenameModuleDependencies())
			return getRenameModuleDependencyOperation().getAffectedFiles();
		return Collections.EMPTY_LIST;
	}

	protected RenameModuleDependencyOperation getRenameModuleDependencyOperation() {
		if (renameModuleDependencyOperation == null) {
			List earEditModels = new ArrayList();
			earEditModels.addAll(deferredCommands.keySet());
			renameModuleDependencyOperation = new RenameModuleDependencyOperation(earEditModels, new HashSet(options.getModuleAndUtilityProjects()), renamedProject);
		}
		return renameModuleDependencyOperation;
	}

	public IStatus getStatus() {
		return renameModuleDependencyOperation.getStatus();
	}
}