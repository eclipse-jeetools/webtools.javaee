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


import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Insert the type's description here. Creation date: (10/12/2000 10:20:40 AM)
 * 
 * @author: Administrator
 */
public abstract class AbstractEJBRootCommand extends CompoundCommand implements IRootCommand {
	public static final boolean USE_ONE_RESOURCE = true;

	protected static final String METADATA_TASK_STRING = EJBCodeGenResourceHandler.getString("--_Updating_Metadata..._UI_"); //$NON-NLS-1$ = "-- Updating Metadata..."

	protected boolean generateMetadata = true;

	protected boolean generateJava = true;

	protected boolean createdExtensionResource = false;

	protected EJBEditModel editModel;

	protected IEJBCommand fParent;

	protected EJBCommandCopier copier;

	protected boolean hasSetCopier = false;

	protected IProgressMonitor progressMonitor;

	protected EJBPropagationCommand additionalCommand;

	//Default to true to always avoid the key propogation dialog.
	//Should look at removing this alltogether.
	protected boolean propogateAllKeyChanges = true;

	protected IOperationHandler operationHandler;

	protected boolean shouldPropagateAllChanges = true;

	protected IEJBCodegenHandler codgenHandler;

	private boolean shouldClearCopier = false;

	/**
	 * EJBAbstractRootCommand constructor comment.
	 * 
	 * @param desc
	 *            java.lang.String
	 */
	public AbstractEJBRootCommand(String desc) {
		super(desc);
	}

	/**
	 * This adds a command to this compound command's the list of commands.
	 */
	public void append(Command command) {
		if (command != null && !commandList.contains(command))
			super.append(command);
	}

	public IRootCommand append(IEJBCommand command) {
		if (command == null)
			return null;
		if (command.isRootCommand())
			return append((IRootCommand) command);
		((EJBCommand) command).setParent(this);
		super.append(command);
		return this;
	}

	public abstract IRootCommand append(IRootCommand rootCommand);

	/**
	 * Return the number of child commands plus myself.
	 */
	public int calculateTotalWork() {
		int total = 1;
		Command command;
		for (int i = 0; i < commandList.size(); i++) {
			command = (Command) commandList.get(i);
			if (command instanceof IEJBCommand)
				total += ((IEJBCommand) command).calculateTotalWork();
			else
				total += 1;
		}
		return total;
	}

	/**
	 * Currently the EJB commands are not undoable.
	 */
	public boolean canUndo() {
		return false;
	}

	/**
	 * Check if the ProgressMonitor has been cancelled. If so, null out the codegen command and
	 * throw a CommandExecutionFailure. Throwing the failure will ensure that the CommandStack will
	 * call a dispose() on the command.
	 */

	protected void checkCancelled() {
		if (getProgressMonitor() != null && getProgressMonitor().isCanceled())
			throw new CommandExecutionFailure(EJBCodeGenResourceHandler.getString("Cancelled_ERROR_")); //$NON-NLS-1$ = "Cancelled"
	}

	/**
	 * createEJBJar method comment.
	 */
	protected void createEJBJar(Resource aResource) {
		if (aResource != null) {
			String projectName = getProject() != null ? getProject().getName() : "";//$NON-NLS-1$
			EJBJar jar = getEJBFactory().createEJBJar();
			jar.setDescription(projectName);
			aResource.getContents().add(jar);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 9:05:54 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.Document
	 */
	public Resource createResource() {
		Resource resource = getEditModel().makeEjbXmiResource();
		if (USE_ONE_RESOURCE)
			createEJBJar(resource);
		return resource;
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 9:05:54 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.Document
	 */
	protected Resource createResourceIfNecessary() {
		Resource resource = getResource();
		if (resource == null)
			resource = createResource();
		return resource;
	}

	/**
	 * Delete the document as a resource in the Workbench.
	 */
	protected void deleteResource() {
		getEditModel().deleteResource(getResource());
	}

	protected void displayError(String anErrorMsg) {
		if (getOperationHandler() != null)
			getOperationHandler().error(anErrorMsg);
	}

	public void dispose() {
		super.dispose();
		disposeAdditionalCommand();
		if (!hasSetCopier && primGetCopier() != null)
			primGetCopier().dispose();
	}

	protected void disposeAdditionalCommand() {
		if (hasAdditionalCommand())
			getAdditionalCommand().dispose();
	}

	protected void endProgressMonitor() {
		if (!hasParent() && getProgressMonitor() != null)
			getProgressMonitor().done();
	}

	/**
	 * The flow of events in this method is as follows: 1) Setup the EJB if necessary, usually
	 * during a create. 2) Execute each of my children. 3) Provide a hook just before calling finish
	 * (postExecuteChildren()) to do any other changes. This is needed when meta data needs to be
	 * created or modified after all of the meta data exists (i.e., all the children have executed).
	 * 4) Finish which will save the resource updates and call any code generation command.
	 */
	public void execute() {
		startProgressMonitor();
		try {
			initializeRoot();
			super.execute();
			postExecuteChildren();
		} catch (RuntimeException re) {
			failedExecution(re);
			throw re;
		} catch (Throwable e) {
			failedExecution(e);
			throw new CommandExecutionFailure(e);
		}
		executeCodegenCommandWithErrorHandling();
		initializeAndExecuteAdditionalCommand();
		postExecuteCodegen();
		endProgressMonitor();
	}

	/**
	 * Execute the command. For a compound command this means executing all of the commands that it
	 * contains.
	 */
	protected abstract void executeCodegenCommand();

	/**
	 * execute method comment.
	 */
	protected void executeCodegenCommandIfNecessary() {
		if (!J2EEPlugin.hasDevelopmentRole()) {
			// Check if this is the Default Session Command
			if (!((this instanceof CreateSessionCommand) && ((CreateSessionCommand) this).getName().equals(EJBModuleCreationOperation.DEFAULT_SESSION_BEAN_NAME)))
				return;
		}
		checkCancelled();
		executeCodegenCommand();
	}

	/**
	 * Execute the codegen command, if necessary, and set the failure state if there is an error.
	 */
	protected void executeCodegenCommandWithErrorHandling() {
		try {
			executeCodegenCommandIfNecessary();
		} catch (RuntimeException re) {
			failedCodegenExecution(re);
			throw re;
		} catch (Throwable e) {
			failedCodegenExecution(e);
			throw new CommandExecutionFailure(e);
		}
	}

	/**
	 * This calls {@link Command#executeCommandFailed}for each command in the list.
	 */
	public void executeCommandFailed() {
		for (Iterator commands = commandList.iterator(); commands.hasNext();) {
			Command command = (Command) commands.next();
			command.execute();
		}
	}

	protected void failedCodegenExecution(Throwable aThrowable) {
		String error = EJBCodeGenResourceHandler.getString("The_command_failed_to_exec_ERROR_") + aThrowable.toString(); //$NON-NLS-1$ = "The command failed to execute code generation for the following reason:  "
		Logger.getLogger().logError(error);
		Logger.getLogger().logError(aThrowable);
		displayError(error);
	}

	protected void failedExecution(Throwable aThrowable) {
		String error = EJBCodeGenResourceHandler.getString("The_command_failed_to_upda_ERROR_") + aThrowable.toString(); //$NON-NLS-1$ = "The command failed to update the meta-data for the following reason:  "
		Logger.getLogger().logError(error);
		Logger.getLogger().logError(aThrowable);
		displayError(error);
	}

	protected void failedUndo(Throwable aThrowable) {
		String error = EJBCodeGenResourceHandler.getString("The_command_failed_to_undo_ERROR_") + aThrowable.toString(); //$NON-NLS-1$ = "The command failed to undo the meta-data change for the following reason:  "
		Logger.getLogger().logError(error);
		Logger.getLogger().logError(aThrowable);
		displayError(error);
	}

	/**
	 * Insert the method's description here. Creation date: (8/10/2001 2:11:44 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.commands.AbstractEJBRootCommand
	 */
	protected EJBPropagationCommand getAdditionalCommand() {
		if (hasParent())
			return ((AbstractEJBRootCommand) getParent()).getAdditionalCommand();
		if (additionalCommand == null) {
			additionalCommand = new EJBPropagationCommand(EJBCodeGenResourceHandler.getString("Add-On_Command_UI_")); //$NON-NLS-1$ = "Add-On Command"
			additionalCommand.setEditModel(getEditModel());
			additionalCommand.setCopier(getCopier());
			additionalCommand.setOperationHandler(getOperationHandler());
			additionalCommand.setPropogateAllKeyChanges(shouldPropogateAllKeyChanges());
		}
		return additionalCommand;
	}

	/**
	 * Return an IEJBCommand that has a sourceMetaType that is equal to
	 * 
	 * @aRefObject.
	 */
	protected IEJBCommand getChildCommand(EObject aRefObject) {
		Iterator it = commandList.iterator();
		IEJBCommand command, foundInRoot = null;
		while (it.hasNext()) {
			command = (IEJBCommand) it.next();
			if (command.getSourceMetaType() == aRefObject)
				return command;
			if (command.isRootCommand())
				foundInRoot = ((AbstractEJBRootCommand) command).getChildCommand(aRefObject);
			if (foundInRoot != null)
				return foundInRoot;
		}
		return null;
	}

	/**
	 * Return the copy for aRefObject from the <code>copyUtility</code>.
	 */
	public EObject getCopy(EObject aRefObject) {
		return getCopyUtility().getCopy(aRefObject);
	}

	/**
	 * Insert the method's description here. Creation date: (12/21/2000 3:04:07 PM)
	 * 
	 * @return com.ibm.etools.emf.ecore.utilities.copy.EtoolsCopyUtility
	 */
	public EtoolsCopyUtility getCopyUtility() {
		return getCopier().getCopyUtility();
	}

	protected void initializeEJBCodegenHandler() {
		if (codgenHandler == null)
			codgenHandler = EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(getProject());
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2001 8:40:38 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBEditModel
	 */
	public EJBEditModel getEditModel() {
		return editModel;
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 8:51:51 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.Factory
	 */
	public EjbFactory getEJBFactory() {
		return ((EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory();
	}

	/**
	 * Return an existing EJBJar from the Document. Assume that the EJBJar is the first and only
	 * element in the EList.
	 */
	public EJBJar getEJBJar() {
		return getEditModel().getEJBJar();
	}

	public EJBNatureRuntime getEJBNature() {
		return getEditModel().getEJBNature();
	}

	/**
	 * Insert the method's description here. Creation date: (10/11/2000 1:50:22 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getName() {
		return getDescription();
	}

	/**
	 * Insert the method's description here. Creation date: (8/10/2001 3:19:08 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.operations.IOperationHandler
	 */
	public IOperationHandler getOperationHandler() {
		if (operationHandler == null && hasParent())
			return ((AbstractEJBRootCommand) getParent()).getOperationHandler();
		return operationHandler;
	}

	/**
	 * Insert the method's description here. Creation date: (08/18/00 5:00:53 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public IEJBCommand getParent() {
		return fParent;
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 12:28:46 PM)
	 * 
	 * @return com.ibm.itp.common.IProgressMonitor
	 */
	public IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	public IProject getProject() {
		return getEditModel().getProject();
	}

	/**
	 * Insert the method's description here. Creation date: (10/24/2000 9:50:27 PM)
	 * 
	 * @return org.eclipse.emf.ecore.resource.Resource
	 */
	public org.eclipse.emf.ecore.resource.Resource getResource() {
		if (hasParent())
			return ((IRootCommand) getParent()).getResource();
		return getEditModel().getEjbXmiResource();
	}

	/**
	 * Required to suffice the IEJBCommand.
	 */
	public EObject getSourceMetaType() {
		return getEjb();
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:00:10 PM)
	 * 
	 * @return java.lang.String
	 */
	public abstract String getTaskName();

	/**
	 * Insert the method's description here. Creation date: (8/10/2001 2:11:44 PM)
	 * 
	 * @return boolean
	 */
	protected boolean hasAdditionalCommand() {
		return additionalCommand != null;
	}

	protected boolean hasInitializedAdditionalCommand() {
		if (hasParent())
			return ((AbstractEJBRootCommand) getParent()).hasInitializedAdditionalCommand();
		return hasAdditionalCommand();
	}

	public boolean hasParent() {
		return getParent() != null;
	}

	protected abstract void initializeAdditionalCommand();

	protected void initializeAdditionalCommandIfNecessary() {
		if (shouldPropagateAllChanges) {
			if (shouldClearCopier)
				getCopier().reset();
			initializeAdditionalCommand();
		}
	}

	protected void initializeAndExecuteAdditionalCommand() {
		initializeAdditionalCommandIfNecessary();
		if (hasAdditionalCommand() && getAdditionalCommand().canExecute())
			getAdditionalCommand().execute();
	}

	/**
	 * The default is to do nothing.
	 */
	protected void initializeRoot() {
	}

	/**
	 * Insert the method's description here. Creation date: (8/10/2001 2:05:38 PM)
	 * 
	 * @return boolean
	 */
	protected boolean isAdditionalCommand() {
		if (hasParent())
			return ((AbstractEJBRootCommand) getParent()).isAdditionalCommand();
		return false;
	}

	public boolean isEnterpriseBeanRootCommand() {
		return false;
	}

	public boolean isRoot() {
		return getParent() == null;
	}

	public boolean isRootCommand() {
		return true;
	}

	/**
	 * Override if there is anything that is necessary to be done before the document is saved. All
	 * child commands are already executed so their results have already been applied to the EJB.
	 * This is called prior to code generation.
	 */

	protected void postExecuteChildren() {
		//no-op
	}

	/**
	 * Save the document prior to executing codegen.
	 */
	public void postExecuteCodegen() {
		worked(1);
	}

	/**
	 * Do whatever is needed prior to saving resources during undo.
	 */
	protected void postUndoChildren() {
	}

	/**
	 * Do whatever is necessary prior to undoing the Codegen Command.
	 */
	protected void postUndoCodegen() throws CommandExecutionFailure {
	}

	/**
	 * Do whatever is necessary prior to undoing the Codegen Command.
	 */
	protected void preUndoCodegen() throws CommandExecutionFailure {
	}

	/**
	 * redo method comment.
	 */
	public void redo() {
		setCopier(null);
		execute();
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2001 8:40:38 AM)
	 * 
	 * @param newEditModel
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBEditModel
	 */
	public void setEditModel(org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel newEditModel) {
		editModel = newEditModel;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 6:12:50 PM)
	 * 
	 * @param newGenerateJava
	 *            boolean
	 */
	public void setGenerateJava(boolean newGenerateJava) {
		generateJava = newGenerateJava;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 6:12:50 PM)
	 * 
	 * @param newGenerateMetadata
	 *            boolean
	 */
	public void setGenerateMetadata(boolean newGenerateMetadata) {
		generateMetadata = newGenerateMetadata;
	}

	/**
	 * Insert the method's description here. Creation date: (10/11/2000 1:50:22 PM)
	 * 
	 * @return java.lang.String
	 */
	public void setName(String aNewName) {
		setDescription(aNewName);
	}

	/**
	 * Insert the method's description here. Creation date: (8/10/2001 3:19:08 PM)
	 * 
	 * @param newOperationHandler
	 *            org.eclipse.jst.j2ee.internal.internal.operations.IOperationHandler
	 */
	public void setOperationHandler(IOperationHandler newOperationHandler) {
		operationHandler = newOperationHandler;
	}

	/**
	 * Insert the method's description here. Creation date: (08/18/00 5:00:53 PM)
	 * 
	 * @param newParent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	protected void setParent(IEJBCommand newParent) {
		fParent = newParent;
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 12:28:46 PM)
	 * 
	 * @param newProgressMonitor
	 *            com.ibm.itp.common.IProgressMonitor
	 */
	public void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	/**
	 * Setting this value to true will avoid any dialog appearing if a key propogation needs to take
	 * place.
	 * 
	 * @param newPropogateAllKeyChanges
	 *            boolean
	 */
	public void setPropogateAllKeyChanges(boolean newPropogateAllKeyChanges) {
		propogateAllKeyChanges = newPropogateAllKeyChanges;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 6:12:50 PM)
	 * 
	 * @return boolean
	 */
	public boolean shouldGenerateJava() {
		return generateJava;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 6:12:50 PM)
	 * 
	 * @return boolean
	 */
	public boolean shouldGenerateMetadata() {
		return generateMetadata;
	}

	/**
	 * Insert the method's description here. Creation date: (8/10/2001 1:44:33 PM)
	 * 
	 * @return boolean
	 */
	protected boolean shouldPropogateAllKeyChanges() {
		if (hasParent())
			return ((AbstractEJBRootCommand) getParent()).shouldPropogateAllKeyChanges();
		return propogateAllKeyChanges;
	}

	protected void startProgressMonitor() {
		if (getProgressMonitor() != null) {
			if (!hasParent())
				getProgressMonitor().beginTask(getTaskName(), calculateTotalWork());
			getProgressMonitor().subTask(METADATA_TASK_STRING);
		}
	}

	/**
	 * Notifies that a subtask of the main task is beginning. Subtasks are optional; the main task
	 * might not have subtasks.
	 * 
	 * @param name
	 *            the name (or description) of the subtask
	 */
	public void subTask(String name) {
		if (getProgressMonitor() != null)
			getProgressMonitor().subTask(name);
	}

	/**
	 * undo method comment.
	 */
	public void undo() throws CommandExecutionFailure {
		startProgressMonitor();
		try {
			undoAdditionalCommand();
			super.undo();
			postUndoChildren();
			preUndoCodegen();
		} catch (RuntimeException re) {
			failedUndo(re);
			throw re;
		} catch (Throwable e) {
			failedUndo(e);
			throw new CommandExecutionFailure(e);
		}
		undoCodegenCommandWithErrorHandling();
		postUndoCodegen();
		endProgressMonitor();
	}

	protected void undoAdditionalCommand() {
		if (hasAdditionalCommand() && getAdditionalCommand().canUndo())
			getAdditionalCommand().undo();
	}

	protected abstract void undoCodegenCommand();

	/**
	 * Undo the codegen command setting the failure state if an error occurs.
	 */
	protected void undoCodegenCommandWithErrorHandling() {
		try {
			undoCodegenCommand();
		} catch (RuntimeException re) {
			failedCodegenExecution(re);
			throw re;
		} catch (Throwable e) {
			failedCodegenExecution(e);
			throw new CommandExecutionFailure(e);
		}
	}

	/**
	 * Set the number of units worked with the ProgressMonitor.
	 */

	public void worked(int units) {
		if (getProgressMonitor() != null)
			getProgressMonitor().worked(units);
	}

	/**
	 * Gets the copier.
	 * 
	 * @return Returns a EJBCommandCopier
	 */
	public EJBCommandCopier getCopier() {
		if (hasParent())
			return ((AbstractEJBRootCommand) getParent()).getCopier();
		if (copier == null)
			copier = new EJBCommandCopier(getEditModel());
		return copier;
	}

	protected EJBCommandCopier primGetCopier() {
		return copier;
	}

	/**
	 * Sets the copier.
	 * 
	 * @param copier
	 *            The copier to set
	 */
	public void setCopier(EJBCommandCopier aCopier) {
		if (copier != null)
			copier.dispose();
		this.copier = aCopier;
		hasSetCopier = true;
	}

	/**
	 * Set this value to true if you do not want to propagate changes to subclasses and related
	 * beans. This should only be used when you know that the related beans are already being fully
	 * updated.
	 * 
	 * @param shouldPropagateAllChanges
	 *            The shouldPropagateAllChanges to set
	 */
	public void setShouldPropagateAllChanges(boolean shouldPropagateAllChanges) {
		this.shouldPropagateAllChanges = shouldPropagateAllChanges;
	}

	public void setShouldClearCopierOnPropagation(boolean shouldClearCopier) {
		this.shouldClearCopier = shouldClearCopier;
	}
}