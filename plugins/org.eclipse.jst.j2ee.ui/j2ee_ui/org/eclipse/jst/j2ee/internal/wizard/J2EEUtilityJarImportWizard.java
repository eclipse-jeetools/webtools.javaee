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
 * Created on May 12, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEUtilityJarListImportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.CommonEditorUtility;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.internal.common.frameworks.ui.WTPWizard;


/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class J2EEUtilityJarImportWizard extends WTPWizard implements IImportWizard {

	private static final String SELECT_JARS = "SELECT_JARS"; //$NON-NLS-1$
	private static final String IMPORT_TYPE = "IMPORT_TYPE"; //$NON-NLS-1$
	private IStructuredSelection selection;


	/**
	 * @param model
	 */
	public J2EEUtilityJarImportWizard(WTPOperationDataModel model) {
		super(model);
	}

	/**
	 *  
	 */
	public J2EEUtilityJarImportWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new J2EEUtilityJarListImportDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createOperation() {
		return getModel().getDefaultOperation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection aSelection) {
		this.selection = aSelection;
	}

	protected J2EEUtilityJarListImportDataModel getUtilityJarImportModel() {
		return (J2EEUtilityJarListImportDataModel) getModel();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		this.addPage(new J2EEUtilityJarImportTypePage(getUtilityJarImportModel(), IMPORT_TYPE, selection));
		this.addPage(new J2EEUtilityJarImportPage(getUtilityJarImportModel(), SELECT_JARS));
	}

}