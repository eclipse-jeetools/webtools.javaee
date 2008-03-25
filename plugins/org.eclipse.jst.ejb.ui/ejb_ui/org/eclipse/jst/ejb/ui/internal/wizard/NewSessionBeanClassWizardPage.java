/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.STATE_TYPE;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewSessionBeanClassWizardPage extends NewEnterpriseBeanClassWizardPage {

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
	
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		createBusinessInterfacesGroup(composite);
		
		return composite;
	}

	private void createBusinessInterfacesGroup(Composite composite) {

		addSeperator(composite, 3);

		stateTypeLabel = new Label(composite, SWT.LEFT);
		stateTypeLabel.setText(IEjbWizardConstants.STATE_TYPE_LABEL);

		stateTypeCombo = new Combo(composite, SWT.READ_ONLY);
		stateTypeCombo.setLayoutData(gdhspan(1));
		stateTypeCombo.setItems(new String[] { 
				IEjbWizardConstants.STATE_TYPE_STATELESS, 
				IEjbWizardConstants.STATE_TYPE_STATEFUL
		});
		stateTypeCombo.select(0);
		((ComboIndexSynchHelper) synchHelper).synchComboIndex(stateTypeCombo, STATE_TYPE, null);
		
		Group group = new Group(composite, SWT.NONE);
        group.setLayoutData(gdhspan(2));
        group.setLayout(new GridLayout(2, false));
        group.setText(IEjbWizardConstants.CREATE_BUSINESS_INTERFACE);
		
		remoteCheckbox = new Button(group, SWT.CHECK);
		remoteCheckbox.setLayoutData(gdhspan(3));
		remoteCheckbox.setText(IEjbWizardConstants.REMOTE_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(remoteCheckbox, REMOTE, null);

		localCheckbox = new Button(group, SWT.CHECK);
		localCheckbox.setLayoutData(gdhspan(3));
		localCheckbox.setText(IEjbWizardConstants.LOCAL_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(localCheckbox, LOCAL, null);
	}

	private static GridData gdhspan(int span) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = span;
		return gd;
	}
	
}
