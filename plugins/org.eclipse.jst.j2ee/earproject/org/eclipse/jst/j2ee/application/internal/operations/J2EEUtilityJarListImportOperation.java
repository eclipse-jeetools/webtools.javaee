/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEUtilityJarListImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class J2EEUtilityJarListImportOperation extends AbstractDataModelOperation implements IUndoableOperation {

	private static final int ASSISTANT_TICKS = 5;

	public J2EEUtilityJarListImportOperation(IDataModel model) { 
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		MultiStatus status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, EARCreationResourceHandler.J2EEUtilityJarListImportOperation_UI_Import_Utility_Jars, null);  
			
		Object[] utilityJars = (Object[]) model.getProperty(IJ2EEUtilityJarListImportDataModelProperties.UTILITY_JAR_LIST);
		if (utilityJars == null || utilityJars.length == 0)
			return J2EEPlugin.createErrorStatus(0, "There were no utility jars selected.", null);

		monitor.beginTask(EARCreationResourceHandler.J2EEUtilityJarListImportOperation_UI_Import_Utility_Jars, utilityJars.length * (1 + ASSISTANT_TICKS)); //$NON-NLS-1$

//		String earProjectName = model.getStringProperty(IJ2EEUtilityJarListImportDataModelProperties.EAR_PROJECT_NAME);
//		boolean isBinary = model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.BINARY_IMPORT);
		
		// if model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.COPY) then
		// isLinked = createProject = false;
		boolean toLinkImportedJars = shouldLinkImportedJars();
		boolean toCreateProject = shouldCreateProject();
//		boolean toOverrideProjectRoot = shouldOverrideProjectRoot();
		
//		String projectRoot = model.getStringProperty(IJ2EEUtilityJarListImportDataModelProperties.PROJECT_ROOT);
		
		monitor.subTask(EARCreationResourceHandler.J2EEUtilityJarListImportOperation_UI_Preparing_to_import); //$NON-NLS-1$

		String associatedEARProjectName = model.getStringProperty(IJ2EEUtilityJarListImportDataModelProperties.EAR_PROJECT_NAME);		
		
		J2EEUtilityJarImportAssistantOperation assistantOperation = null;
		List utilityJarOperations = new ArrayList();
		File utilityJar = null;
		String linkedPathVariable = null; 
		if(model.isPropertySet(IJ2EEUtilityJarListImportDataModelProperties.LINKED_PATH_VARIABLE))
			linkedPathVariable = model.getStringProperty(IJ2EEUtilityJarListImportDataModelProperties.LINKED_PATH_VARIABLE);
		for (int i = 0; i < utilityJars.length && !monitor.isCanceled(); i++) {
			utilityJar = (File) utilityJars[i];
			if (toCreateProject) { 
				if (!toLinkImportedJars) {
					assistantOperation = new CreateProjectWithExtractedJarOperation(utilityJar); 
				 
				} else {
					assistantOperation = new CreateProjectWithLinkedJarOperation(utilityJar, linkedPathVariable);
					
				} 
			} else { 
				if (!toLinkImportedJars) {
					assistantOperation = new CopyArchiveIntoProjectOperation(utilityJar);
				} else
					assistantOperation = new LinkArchiveIntoProjectOperation(utilityJar, linkedPathVariable);
			} 
			assistantOperation.setAssociatedEARProjectName(associatedEARProjectName); 
			assistantOperation.setOverwriteIfNecessary(model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.OVERWRITE_IF_NECESSARY));
			utilityJarOperations.add(assistantOperation);

			monitor.worked(1);
		}
		
		for (Iterator iter = utilityJarOperations.iterator(); iter.hasNext() && !monitor.isCanceled();) {
			try {
				assistantOperation = (J2EEUtilityJarImportAssistantOperation) iter.next();
				IProgressMonitor submonitor = new SubProgressMonitor(monitor, ASSISTANT_TICKS);
				assistantOperation.execute(submonitor, null);
				submonitor.done();
			} catch (Exception e) {
				status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
				J2EEPlugin.logError(0, e.getMessage(), e);
			} 
		} 
		
		monitor.done();
		
		return status;
	}
 
	protected final boolean shouldOverrideProjectRoot() {
		return model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.OVERRIDE_PROJECT_ROOT);
	}
 
	protected final  boolean shouldCreateProject() {
		return (model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.CREATE_PROJECT) || model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.CREATE_LINKED_PROJECT));
	}
 
	protected final  boolean shouldLinkImportedJars() {
		return (model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.LINK_IMPORT) || model.getBooleanProperty(IJ2EEUtilityJarListImportDataModelProperties.CREATE_LINKED_PROJECT));
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}


 


}
