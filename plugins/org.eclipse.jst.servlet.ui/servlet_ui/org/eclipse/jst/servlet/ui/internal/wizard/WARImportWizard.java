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
 * Created on Dec 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.internal.registry.ConfigurationElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WARImportDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WARImportOperation;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WARImportWizard extends J2EEModuleImportWizard {
	private static final String MAIN_PG = "main"; //$NON-NLS-1$
	private IConfigurationElement configData;

	/**
	 * @param model
	 */
	public WARImportWizard(WARImportDataModel model) {
		super(model);
	}

	/**
	 *  
	 */
	public WARImportWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		WARImportDataModel aModel = new WARImportDataModel();
		aModel.setBooleanProperty(J2EEModuleImportDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation getImportOperation() {
		return new WARImportOperation(getWebImportDataModel());
	}

	public void addPages() {
		addPage(new WARImportPage(getWebImportDataModel(), MAIN_PG));
	}

	/**
	 *  
	 */
	private WARImportDataModel getWebImportDataModel() {
		return (WARImportDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.IMPORT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_IMPORT_WIZARD_BANNER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEModuleImportWizard#getModuleValidatorStrings()
	 */
	protected String[] getModuleValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.internal.web.validation.UIWarValidator"}; //$NON-NLS-1$
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		configData = config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#postPerformFinish()
	 */
	protected void postPerformFinish() {
		J2EEProjectCreationDataModel projCreationModel = ((WARImportDataModel) model).getJ2eeProjectCreationDataModel();
		String finalPerspective = null;
		if (projCreationModel != null)
			finalPerspective = projCreationModel.getStringProperty(J2EEProjectCreationDataModel.FINAL_PERSPECTIVE);
		if (finalPerspective == null || finalPerspective.equals("")) { //$NON-NLS-1$
			BasicNewProjectResourceWizard.updatePerspective(configData);
		} else {
			IConfigurationElement newConfig = new ConfigurationElement() {
				public String getAttribute(String aName) {
					if (aName.equals("finalPerspective")) //$NON-NLS-1$
						return model.getStringProperty(J2EEProjectCreationDataModel.FINAL_PERSPECTIVE);
					return super.getAttribute(aName);
				}
			};
			BasicNewProjectResourceWizard.updatePerspective(newConfig);
		}
		if (projCreationModel != null)
			BasicNewResourceWizard.selectAndReveal(projCreationModel.getTargetProject(), J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow());
	}
}