
/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IMessageDrivenBeanDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class AddMessageDrivenBeanWizardPage extends DataModelWizardPage implements IBeanWizardPage{


	private Text ejbNameText;
	private Text jndiNameText;
	private Text displayNameText;

	private Combo destinationButton;

	
	private Combo transactionButton;

	public AddMessageDrivenBeanWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_DESC);
		this.setTitle(IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{
				IEnterpriseBeanClassDataModelProperties.EJB_NAME, 
				IMessageDrivenBeanDataModelProperties.DESTINATIONNAME, 
				IEnterpriseBeanClassDataModelProperties.DISPLAY_NAME, 				
				IEnterpriseBeanClassDataModelProperties.DESCRIPTION};
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);

		Composite group = new Composite(composite, SWT.NULL);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));

		createNameDescription(group);
		createDestinationGroup(group);
		createTransactionGroup(group);
		displayNameText.setFocus();

		IStatus projectStatus = validateProjectName();
		if (!projectStatus.isOK()) {
			setErrorMessage(projectStatus.getMessage());
			composite.setEnabled(false);
		}
		
		return composite;
	}

	protected IStatus validateProjectName() {
		// check for empty
		if (model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME) == null || model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME).trim().length() == 0) {
			return WTPCommonPlugin.createErrorStatus(IEJBAnnotationConstants.NO_EJB_PROJECTS);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected void createNameDescription(Composite composite) {
		// ejb name
		Label ejbNameLabel = new Label(composite, SWT.LEFT);
		ejbNameLabel.setText(IEJBAnnotationConstants.EJB_NAME_LABEL);
		ejbNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		ejbNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		ejbNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(ejbNameText, IEnterpriseBeanClassDataModelProperties.EJB_NAME, null);

		// jndi name
		Label jndiNameLabel = new Label(composite, SWT.LEFT);
		jndiNameLabel.setText(IEJBAnnotationConstants.DESTINATION_NAME_LABEL);
		jndiNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		jndiNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		jndiNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(jndiNameText, IMessageDrivenBeanDataModelProperties.DESTINATIONNAME, null);

		// display name
		Label displayNameLabel = new Label(composite, SWT.LEFT);
		displayNameLabel.setText(IEJBAnnotationConstants.DISPLAY_NAME_LABEL);
		displayNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		displayNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		displayNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(displayNameText, IEnterpriseBeanClassDataModelProperties.DISPLAY_NAME, null);

		// description
		Label descLabel = new Label(composite, SWT.LEFT);
		descLabel.setText(IEJBAnnotationConstants.DESCRIPTION_LABEL);
		descLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		final Text descText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		descText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(descText, IEnterpriseBeanClassDataModelProperties.DESCRIPTION, null);
		
		ejbNameText.addModifyListener(new ModifyListener()
				{

					public void modifyText(ModifyEvent e) {
						jndiNameText.setText(ejbNameText.getText());
						displayNameText.setText(ejbNameText.getText());
						descText.setText("A MessageDriven bean named "+ejbNameText.getText());
						
					}
					
				});		
	}

	protected void createDestinationGroup(Composite composite) {

		// description
		Label destinationLabel = new Label(composite, SWT.LEFT);
		destinationLabel.setText(IEJBAnnotationConstants.DESTINATION_LABEL);
		destinationLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		
		destinationButton = new Combo(composite,SWT.DROP_DOWN |  SWT.READ_ONLY);
		destinationButton.setItems(new String[]{DestinationType.QUEUE_LITERAL.getName(),DestinationType.TOPIC_LITERAL.getName()});
		destinationButton.setText(DestinationType.QUEUE_LITERAL.getName());
		destinationButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		destinationButton.select(0);
		synchHelper.synchCombo(destinationButton, IMessageDrivenBeanDataModelProperties.DESTINATIONTYPE, null);

	}

	protected void createTransactionGroup(Composite composite) {
	
		// description
		Label txLabel = new Label(composite, SWT.LEFT);
		txLabel.setText(IEJBAnnotationConstants.TRANSACTION_LABEL);
		txLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		transactionButton = new Combo(composite, SWT.DROP_DOWN |  SWT.READ_ONLY);
		transactionButton.setItems(new String[]{TransactionType.CONTAINER_LITERAL.getName(),TransactionType.BEAN_LITERAL.getName()});
		transactionButton.setText(TransactionType.CONTAINER_LITERAL.getName());
		transactionButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		transactionButton.select(0);
		synchHelper.synchCombo(transactionButton, IEnterpriseBeanClassDataModelProperties.TRANSACTIONTYPE, null);

	}



	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage();
	}

	public boolean canFinish() {
		return false;
	}



	public String getDisplayName() {
		return displayNameText.getText();
	}

	public String getEjbName() {
		return model.getStringProperty(IEnterpriseBeanClassDataModelProperties.EJB_NAME);
	}
}