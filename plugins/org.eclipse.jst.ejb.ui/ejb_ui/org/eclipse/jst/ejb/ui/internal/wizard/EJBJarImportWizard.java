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
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBJarImportOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBJarImportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EJBJarImportWizard extends J2EEModuleImportWizard {
	private static final String MAIN_PG = "main"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public EJBJarImportWizard(EJBJarImportDataModel model) {
		super(model);
	}

	/**
	 *  
	 */
	public EJBJarImportWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		EJBJarImportDataModel aModel = new EJBJarImportDataModel();
		aModel.setBooleanProperty(J2EEModuleImportDataModel.ADD_TO_EAR, true);
		// set the default ear selected if a ear is selected
		try {
			StructuredSelection sel = (StructuredSelection) Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getSelection();
			if (sel != null && !sel.isEmpty() && sel.getFirstElement() instanceof IProject) {
				IProject proj = (IProject) sel.getFirstElement();
				if (proj.hasNature(IEARNatureConstants.NATURE_ID))
					aModel.setProperty(J2EEModuleImportDataModel.EAR_PROJECT, proj.getName());
			}
		} catch (Exception e) {
		}
		return aModel;
	}

	public void addPages() {
		addPage(new EJBImportPage(getEJBImportDataModel(), MAIN_PG));
	}

	private EJBJarImportDataModel getEJBImportDataModel() {
		return (EJBJarImportDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.IMPORT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_IMPORT_WIZARD_BANNER));
	}

	protected WTPOperation getImportOperation() {
		return new EJBJarImportOperation(getEJBImportDataModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEModuleImportWizard#getModuleValidatorStrings()
	 */
	protected String[] getModuleValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.model.internal.validation.EJBValidator"}; //$NON-NLS-1$ 
	}

}