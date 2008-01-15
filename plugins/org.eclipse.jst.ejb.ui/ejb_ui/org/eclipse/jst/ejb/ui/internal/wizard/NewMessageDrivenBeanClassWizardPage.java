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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

public class NewMessageDrivenBeanClassWizardPage extends NewJavaClassWizardPage
		implements INewMessageDrivenBeanClassDataModelProperties {

	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		return super.getExtendedSelectedProject(selection);
	}

	private Label destinationLabel;
	private Combo destinationTypeCombo;
	private Button jmsCheckbox;
	private Text destinationText;

	public NewMessageDrivenBeanClassWizardPage(IDataModel model, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	public DataModelSynchHelper initializeSynchHelper(IDataModel dm) {
		return new ComboIndexSynchHelper(dm);
	}

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
				// TODO Auto-generated method stub
			}
		});
		
		destinationLabel = new Label(composite, SWT.LEFT);
		destinationLabel.setText(IEjbWizardConstants.DESTINATION_LABEL);

		destinationTypeCombo = new Combo(composite, SWT.READ_ONLY);
		destinationTypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		destinationTypeCombo.setItems(new String[] { 
				IEjbWizardConstants.DESTINATION_TYPE_QUEUE, 
				IEjbWizardConstants.DESTINATION_TYPE_TOPIC
		});
		destinationTypeCombo.select(0);
		((ComboIndexSynchHelper) synchHelper).synchComboIndex(destinationTypeCombo, DESTINATION_TYPE, null);
		destinationTypeCombo.setEnabled(false);
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean result = super.isProjectValid(project);
		result = result && J2EEProjectUtilities.isJEEProject(project);
		return result;
	}
	
	@Override
	protected void updateControls() {
		super.updateControls();
		destinationTypeCombo.setEnabled(jmsCheckbox.getSelection());
	}
	
}
