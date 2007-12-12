/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewSessionBeanClassWizardPage extends NewJavaClassWizardPage {

	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		
		return super.getExtendedSelectedProject(selection);
	}

	private Label stateTypeLabel;
	private Button remoteBussinesInterface;
	private Button localBussinesInterface;
	private Combo stateTypeCombo;

	public NewSessionBeanClassWizardPage(IDataModel model, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		createUseExistingGroup(composite);
		return composite;
	}

	private void createUseExistingGroup(Composite composite) {

		stateTypeLabel = new Label(composite, SWT.LEFT);
		stateTypeLabel.setText(IEjbWizardConstants.STATE_TYPE_LABEL);

		stateTypeCombo = new Combo(composite, SWT.None | SWT.READ_ONLY);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		stateTypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		stateTypeCombo.setItems(IEjbWizardConstants.BEAN_TYPE.LABELS);
		synchHelper.synchCombo(stateTypeCombo,
				INewSessionBeanClassDataModelProperties.STATE_TYPE, null);

		addSeperator(composite, 3);

		Label createBussinesInterface = new Label(composite, SWT.LEFT);
		createBussinesInterface
				.setText(IEjbWizardConstants.CREATE_BUSSINES_INTERFACE);

		GridData data = gridData;
		data.horizontalSpan = 3;
		
		remoteBussinesInterface = new Button(composite, SWT.CHECK);
		remoteBussinesInterface.setLayoutData(data);
		remoteBussinesInterface
				.setText(IEjbWizardConstants.REMOTE_BUSSINES_INTERFACE);
		synchHelper.synchCheckbox(remoteBussinesInterface,
				INewSessionBeanClassDataModelProperties.REMOTE_BI, null);

		localBussinesInterface = new Button(composite, SWT.CHECK);
		localBussinesInterface
				.setText(IEjbWizardConstants.LOCAL_BUSSINES_INTERFACE);
		synchHelper.synchCheckbox(localBussinesInterface,
				INewSessionBeanClassDataModelProperties.LOCAL_BI, null);
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean result = super.isProjectValid(project);
		result = result && J2EEProjectUtilities.isJEEProject(project);
		return result;
	}
	
}
