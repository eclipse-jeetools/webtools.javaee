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
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.UIProjectUtilities;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.wst.common.frameworks.internal.operation.extensionui.ExtendableWizard;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * <p>
 * Serves as a base class for the Wizards which create J2EE artifact structures. 
 * An Artifact can be a J2EE application, module, or utility component.
 * </p>  
 * 
 * @see org.eclipse.wst.common.frameworks.internal.operation.extensionui.ExtendableWizard
 */
public abstract class J2EEArtifactCreationWizard extends ExtendableWizard implements INewWizard, IExecutableExtension, IPluginContribution {
	protected IConfigurationElement configData;
	private IStructuredSelection selection;
	
	/**
	 * <p>
	 * Creates a default instance of the wizard with no configuration data, 
	 * no selection, and no operation data model.
	 * </p>
	 */
	public J2EEArtifactCreationWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate wizard controls and to collect data 
	 * from the user. The model will eventually be used to run the operation,
	 * if the user does not cancel the Wizard. 
	 * </p>
	 * 
	 * @param model used to collect information and interface with the WTP Operation
	 */
	public J2EEArtifactCreationWizard(WTPOperationDataModel model) {
		super(model);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		configData = config;
	}

	/**   
	 * <p>
	 * The selection is used to pre-populate values in the Wizard 
	 * dialog controls.
	 * </p>
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 *      
	 * @param workbench the current workbench parent of the wizard
	 * @param aSelection the selection from the view used to start the wizard (if any)
	 */
	public final void init(IWorkbench workbench, IStructuredSelection aSelection) {
		setNeedsProgressMonitor(true);
		this.selection = aSelection; 
		doInit();
	}

	/**
	 * <p>
	 * Invoked from init(IWorkbench, IStructuredSelection) once the workbench 
	 * and selection have been safely stored away.
	 * </p>
	 * <p>
	 * No-op by default.
	 * </p>
	 */
	protected void doInit() { 
		
	}

	/** 
	 * 
	 * <p>
	 * Invoked after the user has clicked the "Finish" button of the wizard. The default
	 * implmentation will attempt to select the project that was created in the active 
	 * view.
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#postPerformFinish()
	 */
	protected void postPerformFinish() {
		BasicNewProjectResourceWizard.updatePerspective(configData);
		BasicNewResourceWizard.selectAndReveal(((J2EEProjectCreationDataModel) model).getTargetProject(), J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow());
	}
	
	/**
	 * @inheritDoc 
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getDialogSettings()
	 */
	public IDialogSettings getDialogSettings() {
		return J2EEUIPlugin.getDefault().getDialogSettings();
	}
	 
	

	/**
	 * @return Returns the selection.
	 */
	protected IStructuredSelection getSelection() {
		return selection;
	}
	

	/**
	 * <p>
	 * Returns the an id component used for Activity filtering.
	 * </p>
	 * @return Returns the wizard id
	 */
	public final String getLocalId() {
		return getWizardID();
	}

	/**
	 * <p>
	 * Returns the an id component used for Activity filtering.
	 * </p>
	 * @return Returns the plugin id associated with this wizard
	 */
	public String getPluginId() {
		return "org.eclipse.jst.j2ee.ui"; //$NON-NLS-1$
	}
	
	/**
	 * @return Returns the EAR project selected in the view used to launch the wizard.
	 */
	protected final IProject getSelectedEARProject() {
		return UIProjectUtilities.getSelectedProject(getSelection(), IEARNatureConstants.NATURE_ID);
	}


}