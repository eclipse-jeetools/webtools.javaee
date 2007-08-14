/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2005 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */
package org.eclipse.jst.jee.ui.internal;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.actions.BaseAction;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author ramanday
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class CreateDeploymentFilesActionDelegate extends BaseAction {

	public void selectionChanged(org.eclipse.jface.action.IAction action, org.eclipse.jface.viewers.ISelection aSelection) {
		super.selectionChanged(action, aSelection);
		boolean isEnabled = false;
		if (aSelection != null) {
			IStructuredSelection structuredSelection = (IStructuredSelection) aSelection;
			IProject project = ProjectUtilities.getProject(structuredSelection.getFirstElement());
			isEnabled = isValidSelection(project, null);
		}
		setEnabled(isEnabled);
	}

	private boolean isValidSelection(IProject project, Shell shell) {
		return (project != null
				&& J2EEProjectUtilities.isJEEProject(project)
				&& !hasDeploymentDescriptor(project, shell));
	}

	/**
	 * Method informInvalidSelection.
	 */
	private void informInvalidSelection(Shell shell) {
		MessageDialog.openInformation(shell, Messages.INVALID_DEP_DESC_SELECTION_TITLE,
				Messages.INFORM_INVALID_DEP_DESC_SELECTION);  
	}

	private boolean hasDeploymentDescriptor(IProject project, Shell shell) {
		boolean ret = true;
		IPath ddFilePath = null;
		if(J2EEProjectUtilities.isEARProject(project)){
			ddFilePath = new Path(J2EEConstants.APPLICATION_DD_URI);
		} else if(J2EEProjectUtilities.isEJBProject(project)){
			ddFilePath = new Path(J2EEConstants.EJBJAR_DD_URI);
		} else if(J2EEProjectUtilities.isDynamicWebProject(project)){
			ddFilePath = new Path(J2EEConstants.WEBAPP_DD_URI);
		} else if(J2EEProjectUtilities.isApplicationClientProject(project)){
			ddFilePath = new Path(J2EEConstants.APP_CLIENT_DD_URI);
		}
		IVirtualComponent component = ComponentCore.createComponent(project);
		if(component.getRootFolder() != null
				&& component.getRootFolder().getUnderlyingFolder() != null){
			IFile ddXmlFile = component.getRootFolder().getUnderlyingFolder().getFile(ddFilePath);
			ret = ddXmlFile.exists();
			if(shell != null && ret)
				informInvalidSelection(shell);
		}
		return ret;
	}


	/*
	 */
	protected void primRun(Shell shell) {
		IProject project = ProjectUtilities.getProject(selection.getFirstElement());
		if (!validateSelection(project, shell))
			return;
		IDataModel dataModel = DataModelFactory.createDataModel(ICreateDeploymentFilesDataModelProperties.class);
		dataModel.setProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT, project);
		try {
			dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			Logger.getLogger().logError(e);
		}
	}
	
	private boolean validateSelection(IProject project, Shell shell) {
		boolean validSelection = true;
		if (!isValidSelection(project, shell)) {
			validSelection = false;
		}
		return validSelection;
	}
}
