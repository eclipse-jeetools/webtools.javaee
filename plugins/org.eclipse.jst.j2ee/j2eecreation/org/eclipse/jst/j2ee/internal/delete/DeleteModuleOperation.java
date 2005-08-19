/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.delete;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


public class DeleteModuleOperation { //extends WTPOperation {

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		// TODO Auto-generated method stub
		
	}

//	protected DeleteOptions options;
//	/*
//	 * key is an EAREditModel and the value is a list of delete commands. We cache the commands and
//	 * execute later because the modules still need to exist when we go to remove module dependency
//	 * references.
//	 */
//	protected Map deferredCommands;
//	protected IProgressMonitor monitor;
//	protected DeleteModuleDependencyOperation deleteModuleDependencyOperation;
//	protected LooseComposedEditModel deleteEditModel;
//	protected List dirtyEarModels;
//
//	protected static String EDIT_MODEL_KEY = "DELETE_MODULES"; //$NON-NLS-1$
//
//	public DeleteModuleOperation(DeleteOptions options) {
//		this.options = options;
//	}
//
//	/**
//	 * @see HeadlessJ2EEOperation#execute(IProgressMonitor)
//	 */
//	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
//		this.monitor = aMonitor;
//		try {
//			buildDeleteCommandsIfNecessary();
//			if (options.shouldDeleteModuleDependencies()) {
//				deleteModuleDependencies();
//			}
//			checkIfEarsAreDirty();
//			executeDeferredCommands();
//			saveModelsIfNecessary();
//		} finally {
//			releaseModels();
//		}
//
//	}
//
//	protected void checkIfEarsAreDirty() {
//		dirtyEarModels = new ArrayList();
//		if (deferredCommands == null)
//			return;
//		Iterator models = deferredCommands.keySet().iterator();
//		while (models.hasNext()) {
//			EAREditModel model = (EAREditModel) models.next();
//			if (model.isDirty() && !dirtyEarModels.contains(model)) {
//				dirtyEarModels.add(model);
//			}
//		}
//	}
//
//	protected void buildDeleteCommandsIfNecessary() {
//		if (deferredCommands != null && !deferredCommands.isEmpty())
//			return;
//		deferredCommands = new HashMap();
//		List earProjects = EARNatureRuntime.getAllEARProjectsInWorkbench();
//		for (int i = 0; i < earProjects.size(); i++) {
//			IProject p = (IProject) earProjects.get(i);
//			if (options.getSelectedProjects().contains(p))
//				return;
//			EARNatureRuntime runtime = EARNatureRuntime.getRuntime(p);
//			if (runtime != null)
//				buildDeleteCommands(runtime);
//		}
//	}
//
//	protected void buildDeleteCommands(EARNatureRuntime runtime) {
//		List commands = new ArrayList();
//		EAREditModel model = runtime.getEarEditModelForWrite(this);
//		List projects = options.getModuleAndUtilityProjects();
//		try {
//			if (options.shouldDeleteModules()) {
//				for (int i = 0; i < projects.size(); i++) {
//					IProject project = (IProject) projects.get(i);
//					Module m = runtime.getModule(project);
//					if (m != null)
//						commands.add(new RemoveModuleFromEARProjectCommand(m, runtime.getProject()));
//					else {
//						String uri = runtime.getJARUri(project);
//						if (uri != null)
//							commands.add(new RemoveUtilityJARMapCommand(runtime.getProject(), uri, project));
//					}
//				}
//			}
//			if (!commands.isEmpty())
//				deferredCommands.put(model, commands);
//			else
//				model.releaseAccess(this);
//		} catch (RuntimeException e) {
//			model.releaseAccess(this);
//			throw e;
//		} catch (Error err) {
//			model.releaseAccess(this);
//			throw err;
//		}
//	}
//
//	/**
//	 * Do this for unhandled exceptions
//	 */
//	protected void releaseModels() {
//		if (deleteEditModel != null) {
//			//TODO - RLS, 2/19/2004 we need to revisit the whole notion of the loose composed edit
//			//model beccause this does not release the nested models as expected, also it looks
//			// like
//			// the delete edit model is never created which means validate edit is not happening
//			//TODO - JAS when you look at this class to clean up the above, also look at
//			// RenameModuleOperation
//			//because it has the same kind of mess
//			deleteEditModel.releaseAccess(this);
//			deleteEditModel = null;
//		}
//		if (deferredCommands != null) {
//			Iterator iter = deferredCommands.keySet().iterator();
//			while (iter.hasNext()) {
//				EditModel editModel = (EditModel) iter.next();
//				editModel.releaseAccess(this);
//				iter.remove();
//			}
//		}
//	}
//
//	public void release() {
//		releaseModels();
//	}
//
//	/**
//	 * After a normal execution flow, save the models if necessary
//	 */
//	protected void saveModelsIfNecessary() {
//		if (deferredCommands == null)
//			return;
//		Iterator models = deferredCommands.keySet().iterator();
//		while (models.hasNext()) {
//			EAREditModel model = (EAREditModel) models.next();
//			if (model.isDirty()) {
//				if (dirtyEarModels.contains(model)) {
//					model.saveIfNecessary(this);
//				} else {
//					model.save(this);
//				}
//			}
//		}
//	}
//
//	protected void deleteModuleDependencies() throws InvocationTargetException, InterruptedException {
//		getDeleteModuleDependencyOperation().run(monitor);
//	}
//
//	protected DeleteModuleDependencyOperation getDeleteModuleDependencyOperation() {
//		if (deleteModuleDependencyOperation == null) {
//			List earEditModels = new ArrayList();
//			Iterator iter = deferredCommands.keySet().iterator();
//			while (iter.hasNext())
//				earEditModels.add(((EAREditModel) iter.next()).getEARNature());
//			deleteModuleDependencyOperation = new DeleteModuleDependencyOperation(earEditModels, new HashSet(options.getModuleAndUtilityProjects()));
//		}
//		return deleteModuleDependencyOperation;
//	}
//
//	protected void executeDeferredCommands() {
//		Iterator it = deferredCommands.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			List commands = (List) entry.getValue();
//			executeCommands(commands);
//
//		}
//	}
//
//	protected void executeCommands(List commands) {
//		for (int i = 0; i < commands.size(); i++) {
//			Command cmd = (Command) commands.get(i);
//			cmd.execute();
//		}
//	}
//
//	/**
//	 * Return a list of all the non-resource files that will get modified as a result of running
//	 * this operation; used for validate edit.
//	 */
//	protected List getAffectedNonResourceFiles() {
//		if (options.shouldDeleteModuleDependencies())
//			return getDeleteModuleDependencyOperation().getAffectedFiles();
//		return Collections.EMPTY_LIST;
//	}
//
//	/**
//	 * Use this only for validate edits
//	 */
//	public ComposedEditModel getDeleteEditModel() {
//		if (deleteEditModel == null)
//			initializeDeleteEditModel();
//		return deleteEditModel;
//	}
//
//	protected void initializeDeleteEditModel() {
//		if (options.getAllProjectsToDelete().size() == 0) {
//			throw new RuntimeException(J2EECreationResourceHandler.getString("DeleteModuleOperation_UI_0")); //$NON-NLS-1$
//		}
//
//		buildDeleteCommandsIfNecessary();
//		IProject project = null;
//		project = (IProject) options.getAllProjectsToDelete().get(0);
//
//		deleteEditModel = new LooseComposedEditModel(EDIT_MODEL_KEY, (EMFWorkbenchContext) WorkbenchResourceHelper.getEMFContext(project)) {
//
//			/**
//			 * @see org.eclipse.jst.j2ee.internal.internal.workbench.EditModel#getNonResourceFiles()
//			 */
//			public List getNonResourceFiles() {
//				List result = super.getNonResourceFiles();
//				result.addAll(getAffectedNonResourceFiles());
//				return result;
//			}
//		};
//
//		Iterator it = deferredCommands.keySet().iterator();
//		while (it.hasNext()) {
//			deleteEditModel.addChild((J2EEEditModel) it.next());
//		}
//	}
//
//	public IStatus getStatus() {
//		return deleteModuleDependencyOperation.getStatus();
//	}

}