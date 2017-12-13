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

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.ABSTRACT_METHODS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CONSTRUCTOR;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassOptionsWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddEnterpriseBeanWizardPage extends NewJavaClassOptionsWizardPage {
	
	public AddEnterpriseBeanWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	
	@Override
	protected void createStubsComposite(Composite parent) {
		Label stubLabel = new Label(parent, SWT.NONE);
		stubLabel.setText(J2EEUIMessages.JAVA_CLASS_METHOD_STUBS_LABEL);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		stubLabel.setLayoutData(data);

		Composite buttonCompo = new Composite(parent, SWT.NULL);
		buttonCompo.setLayout(new GridLayout());
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		buttonCompo.setLayoutData(data);

		inheritButton = new Button(buttonCompo, SWT.CHECK);
		inheritButton.setText(J2EEUIMessages.JAVA_CLASS_INHERIT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(inheritButton, ABSTRACT_METHODS, null);

		constructorButton = new Button(buttonCompo, SWT.CHECK);
		constructorButton.setText(J2EEUIMessages.JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(constructorButton, CONSTRUCTOR, null);
	}

	@Override
	protected String[] getValidationPropertyNames() {
		return null;
	}

	protected void addSeperator(Composite composite, int horSpan) {
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.widthHint = 300;
		// Separator label
		Label seperator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = horSpan;
		seperator.setLayoutData(data);
	}

}
