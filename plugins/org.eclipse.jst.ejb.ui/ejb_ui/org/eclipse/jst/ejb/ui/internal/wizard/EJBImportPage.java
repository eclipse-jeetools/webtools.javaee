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

import org.eclipse.jst.ejb.ui.EJBModuleCreationWizard;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModelOld;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.wizard.AnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.ui.WTPWizard;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EJBImportPage extends J2EEModuleImportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public EJBImportPage(J2EEModuleImportDataModel model, String pageName) {
		super(model, pageName);
		setTitle(EJBUIMessages.getResourceString(EJBUIMessages.EJB_IMPORT_MAIN_PG_TITLE));
		setDescription(EJBUIMessages.getResourceString(EJBUIMessages.EJB_IMPORT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_IMPORT_WIZARD_BANNER));
	}

	protected String getFileNamesStoreID() {
		return IEJBNatureConstants.NATURE_ID;
	}

	protected String getFileImportLabel() {
		return EJBUIMessages.getResourceString(EJBUIMessages.EJB_JAR_FILE_LBL);
	}

	protected String[] getFilterExpression() {
		return new String[]{"*.jar"}; //$NON-NLS-1$
	}

	protected J2EEArtifactCreationDataModelOld getNewProjectCreationDataModel() {
		return getEJBDataModel().getJ2eeArtifactCreationDataModel();
	}

	protected WTPWizard getNewProjectWizard(EjbComponentCreationDataModel aModel) {
		return new EJBModuleCreationWizard(aModel);
	}

	private EJBModuleImportDataModel getEJBDataModel() {
		return (EJBModuleImportDataModel) model;
	}

	protected String getProjectImportLabel() {
		return EJBUIMessages.getResourceString(EJBUIMessages.EJB_PROJECT_LBL);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.IMPORT_EJB_WIZARD_P1);
		return super.createTopLevelComposite(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEModuleImportPage#createAnnotationsStandaloneGroup(org.eclipse.swt.widgets.Composite)
	 */
	protected void createAnnotationsStandaloneGroup(Composite composite) {
		new AnnotationsStandaloneGroup(composite, getEJBDataModel(), false);
	}
}