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
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

public class NewSessionBeanClassWizardPage extends NewJavaClassWizardPage
		implements INewSessionBeanClassDataModelProperties {

	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		return super.getExtendedSelectedProject(selection);
	}

	private Label stateTypeLabel;
	private Combo stateTypeCombo;
	private Button remoteCheckbox;
	private Button localCheckbox;

	public NewSessionBeanClassWizardPage(IDataModel model, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	public DataModelSynchHelper initializeSynchHelper(IDataModel dm) {
		return new ComboIndexSynchHelper(dm);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		createBusinessInterfacesGroup(composite);
		return composite;
	}

	private void createBusinessInterfacesGroup(Composite composite) {

		stateTypeLabel = new Label(composite, SWT.LEFT);
		stateTypeLabel.setText(IEjbWizardConstants.STATE_TYPE_LABEL);

		stateTypeCombo = new Combo(composite, SWT.READ_ONLY);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		stateTypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		stateTypeCombo.setItems(new String[] { 
				IEjbWizardConstants.STATE_TYPE_STATELESS, 
				IEjbWizardConstants.STATE_TYPE_STATEFUL
		});
		stateTypeCombo.select(0);
		((ComboIndexSynchHelper) synchHelper).synchComboIndex(stateTypeCombo, STATE_TYPE, null);

		addSeperator(composite, 3);

		GridData data = gridData;
		data.horizontalSpan = 3;
		
		Label businessInterfacesLabel = new Label(composite, SWT.LEFT);
		businessInterfacesLabel.setLayoutData(data);
		businessInterfacesLabel.setText(IEjbWizardConstants.CREATE_BUSINESS_INTERFACE);
		
		remoteCheckbox = new Button(composite, SWT.CHECK);
		remoteCheckbox.setLayoutData(data);
		remoteCheckbox.setText(IEjbWizardConstants.REMOTE_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(remoteCheckbox, REMOTE, null);

		localCheckbox = new Button(composite, SWT.CHECK);
		localCheckbox.setText(IEjbWizardConstants.LOCAL_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(localCheckbox, LOCAL, null);
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean result = super.isProjectValid(project);
		result = result && J2EEProjectUtilities.isJEEProject(project);
		return result;
	}
	
}
