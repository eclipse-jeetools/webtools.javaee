/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.MAPPED_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.DESTINATION_TYPE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.JMS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewMessageDrivenBeanClassWizardPage extends NewEnterpriseBeanClassWizardPage {

	private Label destinationLabel;
	private Combo destinationTypeCombo;
	private Button jmsCheckbox;
	private Text destinationText;

	public NewMessageDrivenBeanClassWizardPage(IDataModel model, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		addSeperator(composite, 3);
		
		new Label(composite,SWT.LEFT).setText(IEjbWizardConstants.DESTINATION_NAME_LABEL);
		destinationText = new Text(composite,SWT.SINGLE | SWT.BORDER);
		destinationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(destinationText, MAPPED_NAME, null);
		
		createJMSGroup(composite);
		return composite;
	}

	private void createJMSGroup(Composite composite) {

		jmsCheckbox = new Button(composite, SWT.CHECK);
		jmsCheckbox.setText(IEjbWizardConstants.JMS);
		GridData data = new GridData();
		data.horizontalSpan = 3;
		jmsCheckbox.setLayoutData(data);
		synchHelper.synchCheckbox(jmsCheckbox, JMS, null);
		jmsCheckbox.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				destinationTypeCombo.setEnabled(jmsCheckbox.getSelection());
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		destinationLabel = new Label(composite, SWT.LEFT);
		destinationLabel.setText(IEjbWizardConstants.DESTINATION_LABEL);

		destinationTypeCombo = new Combo(composite, SWT.READ_ONLY);
		destinationTypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		DataModelPropertyDescriptor[] descriptors = model.getValidPropertyDescriptors(DESTINATION_TYPE);
		for (DataModelPropertyDescriptor descriptor : descriptors) {
			destinationTypeCombo.add(descriptor.getPropertyDescription());
		}
		destinationTypeCombo.select(0);
		synchHelper.synchCombo(destinationTypeCombo, DESTINATION_TYPE, null);
		destinationTypeCombo.setEnabled(false);
	}
	
	@Override
	protected String[] getValidationPropertyNames() {
		String[] base = super.getValidationPropertyNames();
		String[] result = new String[base.length + 1];
		System.arraycopy(base, 0, result, 0, base.length);
		result[base.length] = JMS;
		return result;
	}
	
	@Override
	protected void updateControls() {
		super.updateControls();
		destinationTypeCombo.setEnabled(jmsCheckbox.getSelection());
	}
	
}
