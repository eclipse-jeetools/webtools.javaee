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



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.AnalysisResult;
import org.eclipse.jst.j2ee.internal.codegen.BaseGenerator;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.GeneratorNotFoundException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTopLevelGenerationHelper;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

/**
 * Insert the type's description here. Creation date: (8/19/2000 3:08:52 PM)
 * 
 * @author: Administrator
 */
public abstract class EnterpriseBeanCodegenCommand extends org.eclipse.jst.j2ee.internal.ejb.commands.AbstractEJBCommand implements org.eclipse.jst.j2ee.internal.ejb.operations.IEJBProgressCommand {
	protected static final String INITIALIZE_TASK_STRING = EJBCodeGenResourceHandler.getString("--_Java_Generation____Init_UI_"); //$NON-NLS-1$ = "-- Java Generation :: Initializing..."
	protected static final String ANALYZE_TASK_STRING = EJBCodeGenResourceHandler.getString("--_Java_Generation____Anal_UI_"); //$NON-NLS-1$ = "-- Java Generation :: Analyzing..."
	protected static final String RUN_TASK_STRING = EJBCodeGenResourceHandler.getString("--_Java_Generation____Gene_UI_"); //$NON-NLS-1$ = "-- Java Generation :: Generating..."
	protected static final String TERMINATE_TASK_STRING = EJBCodeGenResourceHandler.getString("--_Java_Generation____Fini_UI_"); //$NON-NLS-1$ = "-- Java Generation :: Finishing..."
	protected EnterpriseBean ejb;
	protected EJBEditModel editModel;
	protected JavaTopLevelGenerationHelper helper;
	protected IProgressMonitor progressMonitor;
	protected IOperationHandler operationHandler;

	public EnterpriseBeanCodegenCommand() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 8:55:37 AM)
	 * 
	 * @param aProjectName
	 *            java.lang.String
	 * @param aPackageRootName
	 *            java.lang.String
	 */
	public EnterpriseBeanCodegenCommand(EnterpriseBean anEJB) {
		setEjb(anEJB);
	}

	public void append(EJBGenerationHelper aHelper) {
		((EnterpriseBeanHelper) getHelper()).append(aHelper);
	}

	public void appendInverse(EJBGenerationHelper aHelper) {
		append(aHelper);
	}

	/**
	 * Modify the totalWork to account for the possibility of codegen. This is just a guess. We are
	 * better off to guess high than low.
	 */
	public int calculateTotalWork() {
		int total = 0;
		total += getInitializeWorkUnits();
		total += getRunWorkUnits();
		total += getAnalyzeWorkUnits();
		total += getTerminateWorkUnits();
		return total;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 10:01:03 AM)
	 */
	protected abstract JavaTopLevelGenerationHelper createCodegenHelper();

	/**
	 * Execute the code generation. We will get a generator and initialize it with the EJB. Creation
	 * date: (10/11/2000 11:08:07 AM)
	 */
	public void execute() {
		execute(null);
	}

	/**
	 * Execute the code generation. We will get a generator and initialize it with the EJB. Creation
	 * date: (10/11/2000 11:08:07 AM)
	 */
	public void execute(IProgressMonitor monitor) {
		setProgressMonitor(monitor);
		initializeHelper();
		IBaseGenerator start = null;
		try {
			start = getGenerator();
			executeInitializeTask(start);
			processAnalysisResult(executeAnalyzeTask(start));
			executeRunTask(start);
		} finally {
			executeTerminateTask(start);
		}
	}

	/**
	 * Execute the code generation. We will get a generator and initialize it with the EJB. Creation
	 * date: (10/11/2000 11:08:07 AM)
	 */
	protected AnalysisResult executeAnalyzeTask(IBaseGenerator generator) {
		subTask(ANALYZE_TASK_STRING);
		AnalysisResult result = null;
		try {
			result = generator.analyze();
			worked(getAnalyzeWorkUnits());
			return result;
		} catch (GenerationException e2) {
			throw new CommandExecutionFailure(e2);
		}
	}

	/**
	 * Execute the code generation. We will get a generator and initialize it with the EJB. Creation
	 * date: (10/11/2000 11:08:07 AM)
	 */
	protected void executeInitializeTask(IBaseGenerator generator) {
		subTask(INITIALIZE_TASK_STRING);
		try {
			generator.initialize(getInitializer());
			worked(getInitializeWorkUnits());
		} catch (GenerationException e2) {
			throw new CommandExecutionFailure(e2);
		}
	}

	/**
	 * Execute the code generation. We will get a generator and initialize it with the EJB. Creation
	 * date: (10/11/2000 11:08:07 AM)
	 */
	protected void executeRunTask(IBaseGenerator generator) {
		subTask(RUN_TASK_STRING);
		try {
			generator.run();
			worked(getRunWorkUnits());
		} catch (GenerationException e2) {
			throw new CommandExecutionFailure(e2);
		}
	}

	/**
	 * Execute the code generation. We will get a generator and initialize it with the EJB. Creation
	 * date: (10/11/2000 11:08:07 AM)
	 */
	protected void executeTerminateTask(IBaseGenerator generator) {
		subTask(TERMINATE_TASK_STRING);
		try {
			if (generator != null)
				generator.terminate();
			worked(getTerminateWorkUnits());
		} catch (GenerationException e2) {
		}
	}

	/**
	 * Return true if the codegen should proceed.
	 */
	protected void processAnalysisResult(AnalysisResult result) {
		if (result == null)
			return;
		List fileList = collectReadOnlyFilesToValidate(result);
		if (fileList.isEmpty())
			return;
		IFile[] files = new IFile[fileList.size()];
		fileList.toArray(files);
		Object context = null;
		if (getOperationHandler() != null)
			context = getOperationHandler().getContext();
		IStatus status = ResourcesPlugin.getWorkspace().validateEdit(files, context);
		if (!status.isOK())
			throw new CommandExecutionFailure(status.getMessage());
	}

	protected List collectReadOnlyFilesToValidate(AnalysisResult result) {
		List files = null;
		List children = result.getChildResults();
		int size = children.size();
		AnalysisResult childResult = null;
		IFile file = null;
		for (int i = 0; i < size; i++) {
			childResult = (AnalysisResult) children.get(i);
			file = getChangedFile(childResult);
			if (file != null && file.isReadOnly()) {
				if (files == null)
					files = new ArrayList();
				files.add(file);
			}
		}
		if (files == null)
			files = Collections.EMPTY_LIST;
		return files;
	}

	protected IFile getChangedFile(AnalysisResult result) {
		if (result.getReasonCode() == JavaCompilationUnitGenerator.COMPILATION_UNIT_CHANGED_REASON_CODE)
			return (IFile) result.getReason();
		List children = result.getChildResults();
		int size = children.size();
		AnalysisResult childResult = null;
		IFile file = null;
		for (int i = 0; i < size; i++) {
			childResult = (AnalysisResult) children.get(i);
			file = getChangedFile(childResult);
			if (file != null)
				return file;
		}
		return null;
	}

	/**
	 * Return the number of work units used during analyze. Creation date: (10/11/2000 11:08:07 AM)
	 */
	protected int getAnalyzeWorkUnits() {
		return 10;
	}

	/**
	 * Insert the method's description here. Creation date: (4/26/2001 8:13:35 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.ejbproject.EJBEditModel
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel getEditModel() {
		return editModel;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 8:59:04 AM)
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjb() {
		if (ejb == null && hasParent())
			ejb = getParent().getEjb();
		return ejb;
	}

	/**
	 * Insert the method's description here. Creation date: (8/19/2000 4:04:11 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.java.codegen.api.IJavaBaseGenerator
	 */
	protected IBaseGenerator getGenerator() {
		try {
			return BaseGenerator.getGenerator(getGeneratorDictionaryName(), getGeneratorName(), this.getClass(), getHelper());
		} catch (GeneratorNotFoundException e) {
			com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(e);
			throw new RuntimeException(e.getMessage());
		}
	}

	protected String getGeneratorDictionaryName() {
		return IEJBGenConstants.DICTIONARY_NAME;
	}

	/**
	 * Insert the method's description here. Creation date: (8/19/2000 4:24:13 PM)
	 * 
	 * @return java.lang.String
	 */
	protected abstract String getGeneratorName();

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 10:51:23 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.EnterpriseBeanHelper
	 */
	public JavaTopLevelGenerationHelper getHelper() {
		if (helper == null)
			helper = createCodegenHelper();
		return helper;
	}

	/**
	 * Return the object that will be used to initialize the base generator.
	 */
	protected Object getInitializer() {
		return getEjb();
	}

	/**
	 * Return the number of work units used during intiailize. Creation date: (10/11/2000 11:08:07
	 * AM)
	 */
	protected int getInitializeWorkUnits() {
		return 2;
	}

	protected J2EENature getNature() {
		return getEditModel().getJ2EENature();
	}

	/**
	 * Insert the method's description here. Creation date: (9/7/2001 2:46:52 PM)
	 * 
	 * @return org.eclipse.core.runtime.IProgressMonitor
	 */
	public org.eclipse.core.runtime.IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 9:07:59 AM)
	 * 
	 * @return com.ibm.itp.core.api.resources.IProject
	 */
	public IProject getProject() {
		J2EENature nature = getEditModel() == null ? null : getEditModel().getJ2EENature();
		return nature == null ? null : nature.getProject();
	}

	/**
	 * Return the number of work units used during run. Creation date: (10/11/2000 11:08:07 AM)
	 */
	protected int getRunWorkUnits() {
		return 10;
	}

	/**
	 * Return the number of work units used during terminate. Creation date: (10/11/2000 11:08:07
	 * AM)
	 */
	protected int getTerminateWorkUnits() {
		return 1;
	}

	protected void initializeHelper() {
		getHelper().setWorkingCopyProvider(getEditModel());
		setProjectName(getHelper());
	}

	public void redo() {
		execute();
	}

	protected void resetHelper() {
		helper = null;
	}

	/**
	 * Insert the method's description here. Creation date: (9/11/2000 1:37:31 PM)
	 * 
	 * @param newDefaultPackageFragmentRootName
	 *            java.lang.String
	 */
	public void setDefaultPackageFragmentRootName(java.lang.String newDefaultPackageFragmentRootName) {
		getHelper().setDefaultPackageFragmentRootName(newDefaultPackageFragmentRootName);
	}

	/**
	 * Insert the method's description here. Creation date: (4/26/2001 8:13:35 AM)
	 * 
	 * @param newEditModel
	 *            org.eclipse.jst.j2ee.ejb.ejbproject.EJBEditModel
	 */
	public void setEditModel(org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel newEditModel) {
		editModel = newEditModel;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 8:59:04 AM)
	 * 
	 * @param newEjb
	 *            EnterpriseBean
	 */
	public void setEjb(EnterpriseBean newEjb) {
		ejb = newEjb;
		if (ejb != null)
			setDescription(ejb.getName());
	}

	/**
	 * Insert the method's description here. Creation date: (9/7/2001 2:46:52 PM)
	 * 
	 * @param newProgressMonitor
	 *            org.eclipse.core.runtime.IProgressMonitor
	 */
	public void setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	/**
	 * Insert the method's description here. Creation date: (9/7/2000 6:12:06 PM)
	 */
	protected void setProjectName(JavaTopLevelGenerationHelper aHelper) {
		if (aHelper != null) {
			IProject project = getProject();
			if (project == null)
				aHelper.setProjectName(null);
			else
				aHelper.setProjectName(project.getName());
		}
	}

	protected void subTask(String aString) {
		if (progressMonitor != null)
			progressMonitor.subTask(aString);
	}

	public void undo() {
		undo(null);
	}

	public void undo(IProgressMonitor monitor) {
		execute(monitor);
	}

	protected void worked(int units) {
		if (progressMonitor != null)
			progressMonitor.worked(units);
	}

	/**
	 * Returns the operationHandler.
	 * 
	 * @return IOperationHandler
	 */
	public IOperationHandler getOperationHandler() {
		return operationHandler;
	}


	/**
	 * Sets the operationHandler.
	 * 
	 * @param operationHandler
	 *            The operationHandler to set
	 */
	public void setOperationHandler(IOperationHandler operationHandler) {
		this.operationHandler = operationHandler;
	}

	protected EnterpriseBeanCommand getEnterpriseBeanCommand() {
		if (getParent() != null && getParent().isEnterpriseBeanRootCommand())
			return (EnterpriseBeanCommand) getParent();
		return null;
	}
}