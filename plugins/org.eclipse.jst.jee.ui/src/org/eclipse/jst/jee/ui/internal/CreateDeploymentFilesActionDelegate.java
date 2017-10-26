/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.actions.BaseAction;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.project.facet.IAppClientCreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.IConnectorCreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.ICreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.IEJBCreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.IEarCreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.IWebCreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author ramanday
 */
public class CreateDeploymentFilesActionDelegate extends BaseAction {

	@Override
	public void selectionChanged(org.eclipse.jface.action.IAction action, org.eclipse.jface.viewers.ISelection aSelection) {
		super.selectionChanged(action, aSelection);
		boolean isEnabled = false;
		if (aSelection != null) {
			IStructuredSelection structuredSelection = (IStructuredSelection) aSelection;
			isEnabled = isValidSelection(getProjectFromSelection(structuredSelection), null);
		}
		setEnabled(isEnabled);
		action.setEnabled(isEnabled);
	}
	
	private IProject getProjectFromSelection(IStructuredSelection structuredSelection){
		IProject project = ProjectUtilities.getProject(structuredSelection.getFirstElement());
		if (project == null){
			Object firstElement = structuredSelection.getFirstElement();
			if (IAdaptable.class.isInstance(firstElement)){
				return ((IAdaptable) firstElement).getAdapter(IProject.class);
			}
		}
		return project;
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
		if(JavaEEProjectUtilities.isEARProject(project)){
			ddFilePath = new Path(J2EEConstants.APPLICATION_DD_URI);
		} else if(JavaEEProjectUtilities.isEJBProject(project)){
			ddFilePath = new Path(J2EEConstants.EJBJAR_DD_URI);
		} else if(JavaEEProjectUtilities.isDynamicWebProject(project)){
			ddFilePath = new Path(J2EEConstants.WEBAPP_DD_URI);
		} else if(JavaEEProjectUtilities.isApplicationClientProject(project)){
			ddFilePath = new Path(J2EEConstants.APP_CLIENT_DD_URI);
		}else if(JavaEEProjectUtilities.isJCAProject(project)){
			ddFilePath = new Path(J2EEConstants.RAR_DD_URI);
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
	@Override
	protected void primRun(Shell shell) {
		IProject project = getProjectFromSelection(selection);
		if (!validateSelection(project, shell))
			return;
		try {
			getDataModel(project).getDefaultOperation().execute( new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			JEEUIPlugin.logError(e);
		}
	}

	private IDataModel getDataModel(IProject project) {
		Class dataModelClass = null;
		if(JavaEEProjectUtilities.isEARProject(project)){
			dataModelClass = IEarCreateDeploymentFilesDataModelProperties.class;
		} else if(JavaEEProjectUtilities.isEJBProject(project)){
			dataModelClass = IEJBCreateDeploymentFilesDataModelProperties.class;
		} else if(JavaEEProjectUtilities.isDynamicWebProject(project)){
			dataModelClass = IWebCreateDeploymentFilesDataModelProperties.class;
		} else if(JavaEEProjectUtilities.isApplicationClientProject(project)){
			dataModelClass = IAppClientCreateDeploymentFilesDataModelProperties.class;
		}else if(JavaEEProjectUtilities.isJCAProject(project)){
			dataModelClass = IConnectorCreateDeploymentFilesDataModelProperties.class;
		}
		IDataModel dataModel = DataModelFactory.createDataModel(dataModelClass);
		dataModel.setProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT, project);
		return dataModel;
	}
	
	private boolean validateSelection(IProject project, Shell shell) {
		boolean validSelection = true;
		if (!isValidSelection(project, shell)) {
			validSelection = false;
		}
		return validSelection;
	}
}
