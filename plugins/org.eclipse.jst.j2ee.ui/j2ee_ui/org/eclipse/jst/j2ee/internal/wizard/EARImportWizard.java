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
package org.eclipse.jst.j2ee.internal.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.internal.registry.ConfigurationElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.EARImportDataModel;
import org.eclipse.jst.j2ee.internal.archive.operations.EARImportOperation;
import org.eclipse.jst.j2ee.internal.plugin.CommonEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.internal.common.ui.WTPWizard;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EARImportWizard extends WTPWizard implements IExecutableExtension, IImportWizard {
	protected static final String PROJECT_PG = "projects"; //$NON-NLS-1$
	protected static final String OPTIONS_PG = "options"; //$NON-NLS-1$
	protected static final String MAIN_PG = "main"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public EARImportWizard(EARImportDataModel model) {
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString("38")); //$NON-NLS-1$
	}

	/**
	 *  
	 */
	public EARImportWizard() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString("38")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		EARImportDataModel aModel = new EARImportDataModel();
		return aModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createOperation() {
		return new EARImportOperation(getEARDataModel());
	}

	public void addPages() {
		addPage(new EARImportPage(getEARDataModel(), MAIN_PG));
		addPage(new EARImportOptionsPage(getEARDataModel(), OPTIONS_PG));
		addPage(new EARImportProjectsPage(getEARDataModel(), PROJECT_PG));
	}

	/**
	 *  
	 */
	protected EARImportDataModel getEARDataModel() {
		return (EARImportDataModel) model;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.IMPORT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_IMPORT_WIZARD_BANNER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
	}

	protected String getFinalPerspectiveID() {
		return "org.eclipse.jst.j2ee.J2EEPerspective"; //$NON-NLS-1$
	}

	protected boolean prePerformFinish() {
		if (!CommonEditorUtility.promptToSaveAllDirtyEditors()) {
			return false;
		}
		if (CommonEditorUtility.getDirtyEditors().length != 0) { // all checkboxes were not selected
			return false;
		}
		return super.prePerformFinish();
	}

	protected void postPerformFinish() throws InvocationTargetException {
		super.postPerformFinish();
		IConfigurationElement element = new ConfigurationElement() {
			public String getAttribute(String aName) {
				if (aName.equals("finalPerspective")) { //$NON-NLS-1$
					return getFinalPerspectiveID();
				}
				return super.getAttribute(aName);
			}

		};
		BasicNewProjectResourceWizard.updatePerspective(element);
	}

}