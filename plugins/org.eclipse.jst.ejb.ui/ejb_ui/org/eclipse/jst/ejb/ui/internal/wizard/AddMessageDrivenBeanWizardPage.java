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

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddMessageDrivenBeanWizardPage extends
		AddEnterpriseBeanWizardPage implements
		INewMessageDrivenBeanClassDataModelProperties {

	private Text ejbNameText;
	private Combo transactionTypeCombo;

	public AddMessageDrivenBeanWizardPage(IDataModel model, String pageName) {
		super(model, pageName,
				IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEAN_WIZARD_PAGE_DESC,
				IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEANS_WIZARD_PAGE_TITLE);
	}

	protected Composite createTopLevelComposite(Composite parent) {

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(3, false));

		Label ejbNameLabel = new Label(composite, SWT.LEFT);
		ejbNameLabel.setText(IEjbWizardConstants.EJB_NAME);

		GridData data = new GridData ();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;

		ejbNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		ejbNameText.setLayoutData(data);
		synchHelper.synchText(ejbNameText, EJB_NAME, null);

		Label transactionTypeLabel = new Label(composite, SWT.LEFT);
		transactionTypeLabel.setText(EJBUIMessages.TRANSACTION_TYPE);
		transactionTypeCombo = new Combo(composite, SWT.None | SWT.READ_ONLY);
		transactionTypeCombo.setLayoutData(data);
		transactionTypeCombo.setItems(new String[] {
				IEjbWizardConstants.TRANSACTION_TYPE_CONTAINER, 
				IEjbWizardConstants.TRANSACTION_TYPE_BEAN
		});
		transactionTypeCombo.select(0);
		((ComboIndexSynchHelper) synchHelper).synchComboIndex(transactionTypeCombo, TRANSACTION_TYPE, null);

		addSeperator(composite, 3);

		createInterfaceControls(composite);

		createStubsComposite(composite);

		return composite;
	}

}

