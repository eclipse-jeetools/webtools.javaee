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
/*
 * Created on Jan 30, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.UIProjectUtilities;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.common.operation.extension.ui.ExtendableWizard;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEProjectCreationWizard extends ExtendableWizard implements INewWizard, IExecutableExtension, IPluginContribution {
	protected IConfigurationElement configData;
	private IStructuredSelection selection;

	/**
	 * @param model
	 */
	public J2EEProjectCreationWizard(WTPOperationDataModel model) {
		super(model);
	}

	/**
	 *  
	 */
	public J2EEProjectCreationWizard() {
		super();
	}

	public String getLocalId() {
		return getWizardID();
	}

	public String getPluginId() {
		return "org.eclipse.jst.j2ee.ui"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		configData = config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection aSelection) {
		setNeedsProgressMonitor(true);
		this.selection = aSelection;
		preFillSelectedEARProject();
	}

	protected void preFillSelectedEARProject() {
		IProject project = getSelectedEARProject();
		if (project != null && model != null) {
			EARNatureRuntime earNature = EARNatureRuntime.getRuntime(project);
			if (null != earNature) {
				int j2eeVersion = earNature.getJ2EEVersion();
				if (j2eeVersion == J2EEVersionConstants.J2EE_1_2_ID && model instanceof ConnectorProjectCreationDataModel) {
					j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
				}
				model.setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
				model.setIntProperty(J2EEModuleCreationDataModel.J2EE_VERSION, j2eeVersion);
				model.setProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME, project.getName());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#postPerformFinish()
	 */
	protected void postPerformFinish() {
		BasicNewProjectResourceWizard.updatePerspective(configData);
		BasicNewResourceWizard.selectAndReveal(((J2EEProjectCreationDataModel) model).getTargetProject(), J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow());
	}

	/**
	 * @return Returns the selection.
	 */
	protected IStructuredSelection getSelection() {
		return selection;
	}

	/**
	 * @return
	 */
	protected IProject getSelectedEARProject() {
		return UIProjectUtilities.getSelectedProject(getSelection(), IEARNatureConstants.NATURE_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getDialogSettings()
	 */
	public IDialogSettings getDialogSettings() {
		return J2EEUIPlugin.getDefault().getDialogSettings();
	}
}