/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.ejb.ui.internal.actions;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.ejb.ui.internal.plugin.EJBUIPlugin;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientProjectRemovalDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.IEjbClientProjectRemovalDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.ui.UIOperationHandler;
import org.eclipse.wst.common.frameworks.internal.ui.WorkspaceModifyComposedOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;
import org.eclipse.wst.validation.internal.operations.ValidatorSubsetOperation;
import org.eclipse.wst.validation.internal.ui.plugin.ValidationUIPlugin;



public class EJBClientRemovalAction extends AbstractClientJARAction {

	
	public static String LABEL = Platform.getResourceString(
				Platform.getBundle(EJBUIPlugin.PLUGIN_ID),
				"%ejb.client.jar.remove.action.description_ui_"); //$NON-NLS-1$
	


	
	public EJBClientRemovalAction() {
		super();
		setText(LABEL);
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2ee.common.actions.BaseAction#primRun(org.eclipse.swt.widgets.Shell)
	 */
	protected void primRun(final Shell shell) {
		final IProject ejbProject = getProject();

		if (!checkClientExists(shell))
			return;
		if (!checkBinaryProject(shell))
			return;
		if (!confirmProceed(shell))
			return;

		final IProject clientProject = getClientProject();
		
		ValidatorManager validatorMgr = ValidatorManager.getManager();		
		try{
				//validatorMgr.suspendAllValidation(true);
				
				Job clientRemoveJob = new Job("Removing EJB Client Project"){ //$NON-NLS-1$
					protected IStatus run(IProgressMonitor monitor) {
						
						UIOperationHandler opHandler = new UIOperationHandler(shell);
						IDataModel model = DataModelFactory.createDataModel( new EjbClientProjectRemovalDataModelProvider() );
						model.setProperty(IEjbClientProjectRemovalDataModelProperties.EJB_PROJECT, ejbProject);
						model.setProperty(IEjbClientProjectRemovalDataModelProperties.EJB_CLIENT_VIEW_PROJECT, clientProject);
						model.setProperty(IEjbClientProjectRemovalDataModelProperties.OP_HANDLER, opHandler );
						
						IStatus status = Status.OK_STATUS;
						try {
							status = model.getDefaultOperation().execute(monitor, null);
						} catch (ExecutionException e) {
							Logger.getLogger().logError(e);
						}
						return status;
					}
				};
		
				clientRemoveJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
				clientRemoveJob.setUser(true);
				clientRemoveJob.schedule();

		}finally {
//			validatorMgr.suspendAllValidation(false);
//			ProgressMonitorDialog dlg = new ProgressMonitorDialog(shell);
//			runValidationOperation(dlg);
		}
		
		
//		IRunnableWithProgress runnable = WTPUIPlugin.getRunnableWithProgress(op);
//		ProgressMonitorDialog dlg = new ProgressMonitorDialog(shell);
//		
//		ValidatorManager validatorMgr = ValidatorManager.getManager();
//		try {
//			validatorMgr.suspendAllValidation(true);
//			//dlg.run(false, false, runnable);
//		} catch (InvocationTargetException e) {
//			handleException(shell, e);
//		} catch (RuntimeException e) {
//			handleException(shell, e);
//		} catch (InterruptedException e) {
//			//do nothing
//		} finally {
//			validatorMgr.suspendAllValidation(false);
//			runValidationOperation(dlg);
//		}
	}
	
	protected void runValidationOperation(ProgressMonitorDialog dlg) {
		WorkspaceModifyComposedOperation runnable = new WorkspaceModifyComposedOperation();
		ValidatorSubsetOperation sop = new ValidatorSubsetOperation(getProject(), true, false);
		sop.setValidators(new String[] { "org.eclipse.jst.j2ee.core.internal.validation.EJBValidator"}); //$NON-NLS-1$
		//TODO: add extension back in for validator  "com.ibm.etools.ejb.mapvalidator.MapValidation" 
		runnable.addRunnable(ValidationUIPlugin.getRunnableWithProgress(sop));
		try {
			dlg.run(true, false, runnable);
		} catch (InvocationTargetException e) {
			Logger.getLogger(J2EEUIPlugin.PLUGIN_ID).logError(e);
		} catch (InterruptedException ie) {
		}
	}
	
	private void handleException(Shell shell, Exception e) {
		Logger.getLogger().logError(e);
		MessageDialog.openError(shell, REMOVE_ERROR_TITLE, ERROR_REMOVING_CLIENT);
	}

	/**
	 * @return
	 */
	private boolean checkClientExists(Shell shell) {
		if (getClientProject() == null) {
			MessageDialog.openError(shell, NO_CLIENT_JAR_TITLE, NO_CLIENT_JAR_MSG);
			return false;
		}
		return true; 
	}
	
	private boolean confirmProceed(Shell shell) {
		String message = MessageFormat.format(REMOVE_MESSAGE, new Object[] {getClientProject().getName()});
		return MessageDialog.openQuestion(shell, REMOVE_TITLE, message);
	}
	
	/**
	 * @return
	 */
	private IProject getClientProject() {
		IProject ejbProject = getProject();
		if( ejbProject.exists() && ejbProject.isAccessible()){
			EJBArtifactEdit edit = null;
			try {
				edit = EJBArtifactEdit.getEJBArtifactEditForRead( ejbProject );
				if (edit != null){
					IVirtualComponent clientComp = edit.getEJBClientJarModule();
					if( clientComp != null ){
						return clientComp.getProject();
					}
				}
			} finally {
				if(edit != null)
					edit.dispose();
					  
			}
		}
		return null;
	}

	private boolean checkBinaryProject(Shell shell) {
		if (JemProjectUtilities.isBinaryProject(getProject())) {
			MessageDialog.openError(shell, REMOVE_ERROR_TITLE, BINARY_EJB_PROJECT);
			return false;
		} else if (JemProjectUtilities.isBinaryProject(getClientProject())) {
			MessageDialog.openError(shell, REMOVE_ERROR_TITLE, BINARY_CLIENT_PROJECT);
			return false;
		}
		return true;		
	}

	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		if( !hasClientJar() )
			action.setEnabled( false );
	}    
}
