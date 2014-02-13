/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 * IBM - Async and Non-persistent support
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.NO_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_BUSINESS_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.STATE_TYPE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.ASYNC;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewSessionBeanClassWizardPage extends
		NewEnterpriseBeanClassWizardPage {

	private Label stateTypeLabel;
	private Combo stateTypeCombo;
	private Button remoteCheckbox;
	private Button localCheckbox;
	private Button noInterfaceCheckbox;
	private Button ansyncCheckbox;
	private Text remoteInterfaceName;
	private Text localInterfaceName;

	public NewSessionBeanClassWizardPage(IDataModel model, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}

	@Override
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
		DataModelPropertyDescriptor[] descriptors = model
				.getValidPropertyDescriptors(STATE_TYPE);
		for (DataModelPropertyDescriptor descriptor : descriptors) {
			stateTypeCombo.add(descriptor.getPropertyDescription());
		}
		stateTypeCombo.select(0);
		synchHelper.synchCombo(stateTypeCombo, STATE_TYPE, null);

		Group group = new Group(composite, SWT.NONE);
		group.setLayoutData(gdhspan(2));
		group.setLayout(new GridLayout(2, false));
		group.setText(IEjbWizardConstants.CREATE_BUSINESS_INTERFACE);

		remoteCheckbox = new Button(group, SWT.CHECK);
		remoteCheckbox.setLayoutData(gdhspan(1));
		remoteCheckbox.setText(IEjbWizardConstants.REMOTE_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(remoteCheckbox, REMOTE, null);
		remoteInterfaceName = new Text(group, SWT.SINGLE | SWT.BORDER);
		remoteInterfaceName
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(remoteInterfaceName, REMOTE_BUSINESS_INTERFACE,
				null);

		localCheckbox = new Button(group, SWT.CHECK);
		localCheckbox.setLayoutData(gdhspan(1));
		localCheckbox.setText(IEjbWizardConstants.LOCAL_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(localCheckbox, LOCAL, null);
		localInterfaceName = new Text(group, SWT.SINGLE | SWT.BORDER);
		localInterfaceName
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(localInterfaceName, LOCAL_BUSINESS_INTERFACE,
				null);

		noInterfaceCheckbox = new Button(group, SWT.CHECK);
		noInterfaceCheckbox.setLayoutData(gdhspan(2));
		noInterfaceCheckbox.setText(IEjbWizardConstants.NO_INTERFACE);
		synchHelper.synchCheckbox(noInterfaceCheckbox, NO_INTERFACE, null);

		ansyncCheckbox = new Button(group, SWT.CHECK);
		ansyncCheckbox.setLayoutData(gdhspan(2));
		ansyncCheckbox.setText(IEjbWizardConstants.ASYNC);
		synchHelper.synchCheckbox(ansyncCheckbox, ASYNC, null);
	}

	private static GridData gdhspan(int span) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = span;
		return gd;
	}

	@Override
	protected String[] getValidationPropertyNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.addAll(Arrays.asList(super.getValidationPropertyNames()));

		names.add(STATE_TYPE);
		names.add(INTERFACES);
		names.add(LOCAL_BUSINESS_INTERFACE);
		names.add(REMOTE_BUSINESS_INTERFACE);
		names.add(NO_INTERFACE);
		names.add(ASYNC);

		return names.toArray(new String[0]);
	}
}
