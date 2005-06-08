/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.application.impl.ApplicationResourceImpl;
import org.eclipse.jst.j2ee.internal.archive.operations.EJBArchiveOpsResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.AddModuleToEARProjectCommand;
import org.eclipse.jst.j2ee.internal.earcreation.AddUtilityJARMapCommand;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.RemoveModuleFromEARProjectCommand;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEditOperation;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetOperation;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

public class AddArchiveProjectToEAROperation extends EARArtifactEditOperation {

	private IProgressMonitor monitor;

	private static class CopyJARFileCommand extends AbstractCommand implements Command {

		IProject earProject = null;
		IProgressMonitor monitor = null;
		IFile file = null;
		String uri = null;
		IFile target = null;

		/**
		 * Constructor
		 */
		public CopyJARFileCommand(IProject earProject, IProgressMonitor monitor, IFile file, String uri) {
			super();
			this.earProject = earProject;
			this.monitor = monitor;
			this.file = file;
			this.uri = uri;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.Command#execute()
		 */
		public void execute() {
			IPath path = null;
			try {
				path = new Path(uri);
				target = earProject.getFile(path);
				mkdirs(path);
				file.copy(target.getFullPath(), true, new SubProgressMonitor(new NullProgressMonitor(), 1));
			} catch (Exception ex) {
				Logger.getLogger().logError(ex);
			}


		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.Command#undo()
		 */
		public void undo() {
			try {
				if (target != null)
					target.delete(true, new NullProgressMonitor());
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.Command#redo()
		 */
		public void redo() {
			execute();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
		 */
		protected boolean prepare() {
			return true;
		}

		/**
		 * @param path
		 * @throws CoreException
		 */
		private void mkdirs(IPath path) throws CoreException {
			int segments = path.segmentCount();
			for (int i = segments - 1; i > 0; i--) {
				IFolder folder = earProject.getFolder(path.removeLastSegments(i));
				if (!folder.exists())
					folder.create(true, true, null);
			}
		}
	}

	public AddArchiveProjectToEAROperation(AddArchiveToEARDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		this.monitor = aMonitor;
		AddArchiveToEARDataModel dataModel = (AddArchiveToEARDataModel) operationDataModel;
		String originalID = null;
		ApplicationResource resource = null;
		String uri = dataModel.getStringProperty(AddArchiveToEARDataModel.ARCHIVE_URI);
		EARNatureRuntime nature = EARNatureRuntime.getRuntime(dataModel.getTargetProject());
		Application app = nature.getApplication();
		Module module = app.getFirstModule(uri);
		if (module != null) {
			resource = (ApplicationResource) module.eResource();
			originalID = resource.getID(module);
		}

		Command command = createCommand(dataModel);
		if (null != command) {
			getCommandStack().execute(command);
		}

		if (originalID != null) {
			module = app.getFirstModule(uri);
			resource = (ApplicationResourceImpl) module.eResource();
			resource.setID(module, originalID);
		}

		addServerTarget(aMonitor);
		if (!dataModel.isModuleArchive())
			updateUtilProject((AddUtilityProjectToEARDataModel) dataModel, aMonitor);
		addAllDependentJARs();

	}

	private void addAllDependentJARs() throws CoreException {
		Map dependents = computeAllDependents();
		if (dependents.isEmpty())
			return;
		Iterator iter = dependents.entrySet().iterator();
		Map.Entry entry = null;
		IResource resource = null;
		String uri = null;
		CompoundCommand compoundCommand = new CompoundCommand();
		compoundCommand.setLabel(EJBArchiveOpsResourceHandler.getString("Copy_Dep_JARS_UI")); //$NON-NLS-1$
		while (iter.hasNext()) {
			entry = (Map.Entry) iter.next();
			resource = (IResource) entry.getKey();
			uri = (String) entry.getValue();
			switch (resource.getType()) {
				case IResource.FILE :
					addDependentJARFile((IFile) resource, uri, compoundCommand);
					break;
				case IResource.PROJECT :
					addDependentJARProject((IProject) resource, uri, compoundCommand);
					break;
			}
		}
		getCommandStack().execute(compoundCommand);
	}

	/**
	 * @param project
	 * @param uri
	 * @param compoundCommand
	 */
	private void addDependentJARProject(IProject project, String uri, CompoundCommand compoundCommand) {
		AddUtilityJARMapCommand cmd = new AddUtilityJARMapCommand(getEARProject(), uri, project);
		compoundCommand.append(cmd);
	}

	/**
	 * @param file
	 * @param uri
	 * @param compoundCommand
	 * @throws CoreException
	 */
	private void addDependentJARFile(IFile file, String uri, CompoundCommand compoundCommand) throws CoreException {
		CopyJARFileCommand cmd = new CopyJARFileCommand(getEARProject(), monitor, file, uri);
		compoundCommand.append(cmd);
	}

	protected void addServerTarget(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		if (operationDataModel.getBooleanProperty(AddArchiveToEARDataModel.SYNC_TARGET_RUNTIME)) {
			ServerTargetDataModel model = ((AddArchiveToEARDataModel) operationDataModel).getServerTargetDataModel();
			ServerTargetOperation serverTargetOperation = new ServerTargetOperation(model);
			serverTargetOperation.doRun(aMonitor);
		}
	}

	/**
	 * @param model
	 */
	private void updateUtilProject(AddUtilityProjectToEARDataModel model, IProgressMonitor aMonitor) {
		WorkbenchComponent wbComp = (WorkbenchComponent) model.getProperty(AddArchiveToEARDataModel.ARCHIVE_MODULE);
		IProject proj = ((AddArchiveToEARDataModel)operationDataModel).getProjectForGivenComponent(wbComp);
		if (proj != null && J2EEProjectUtilities.getFirstReferencingEARProject(proj) == null) {
			createManifest(model, aMonitor);
			IRuntime runtime = ServerCore.getProjectProperties(proj).getRuntimeTarget();
			if (runtime == null)
				addRuntimeTarget(model, aMonitor);
		}
	}

	/**
	 * @param model
	 */
	private void addRuntimeTarget(AddUtilityProjectToEARDataModel model, IProgressMonitor aMonitor) {
		ServerTargetOperation op = new ServerTargetOperation(model.getServerTargetModel());
		runNestedOperation(op, aMonitor);
	}

	/**
	 * @param model
	 */
	private void createManifest(AddUtilityProjectToEARDataModel model, IProgressMonitor aMonitor) {
		UpdateManifestOperation op = new UpdateManifestOperation(model.getManifestModel());
		runNestedOperation(op, aMonitor);
	}

	private void runNestedOperation(WTPOperation op, IProgressMonitor aMonitor) {
		try {
			op.doRun(aMonitor);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}

	/**
	 * @param dataModel
	 * @return
	 */
	private Command createCommand(AddArchiveToEARDataModel dataModel) {
		IProject project = getArchiveProject();
		String uri = dataModel.getStringProperty(AddArchiveToEARDataModel.ARCHIVE_URI);
		EARNatureRuntime nature = EARNatureRuntime.getRuntime(dataModel.getTargetProject());
		if (project == nature.getMappedProject(uri)) {
			return null;
		}

		Module m = null;
		//TODO fix up ((EAREditModel) editModel).getApplication().getFirstModule(uri);
		RemoveModuleFromEARProjectCommand removeCommand = null;
		if (null != m) {
			removeCommand = new RemoveModuleFromEARProjectCommand(m, dataModel.getTargetProject());
		}
		Command addCommand = null;
		if (dataModel.isModuleArchive()) {
			String contextRoot = null;
			/*if (((AddModuleToEARDataModel) dataModel).isWebModuleArchive())
				contextRoot = dataModel.getStringProperty(iAddWebModuleToEARDataModel.CONTEXT_ROOT);
			*/addCommand = new AddModuleToEARProjectCommand(project, dataModel.getTargetProject(), uri, contextRoot, null);
		} else
			addCommand = new AddUtilityJARMapCommand(dataModel.getTargetProject(), uri, project);

		if (null != removeCommand) {
			return removeCommand.chain(addCommand);
		}
		return addCommand;
	}

	private IProject getArchiveProject() {
	    WorkbenchComponent wbComp = (WorkbenchComponent) operationDataModel.getProperty(AddArchiveToEARDataModel.ARCHIVE_MODULE);
		return ((AddArchiveToEARDataModel)operationDataModel).getProjectForGivenComponent(wbComp);
	}

	private IProject getEARProject() {
		return ((AddArchiveToEARDataModel)operationDataModel).getTargetProject();
	}

	private Map computeAllDependents() {
		IProject archiveProject = getArchiveProject();
		IProject earProject = getEARProject();
		if (archiveProject == null || earProject == null)
			return Collections.EMPTY_MAP;
		return new JARDependencyTraverser(archiveProject, earProject).run();
	}
}