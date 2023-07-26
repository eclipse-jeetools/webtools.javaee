/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.ABSTRACT_METHODS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CONSTRUCTOR;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.JAVA_CLASS_INHERIT_CHECKBOX_LABEL;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.JAVA_CLASS_METHOD_STUBS_LABEL;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassOptionsWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewWebClassOptionsWizardPage extends NewJavaClassOptionsWizardPage  {
	
	protected Composite methodStubs;
	
	public NewWebClassOptionsWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	
	@Override
	protected String[] getValidationPropertyNames() {
		return new String[] { INTERFACES };
	}
	
	@Override	
	protected void createStubsComposite(Composite parent) {
		Label stubLabel = new Label(parent, SWT.NONE);
		stubLabel.setText(JAVA_CLASS_METHOD_STUBS_LABEL);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		stubLabel.setLayoutData(data);

		methodStubs = new Composite(parent, SWT.NULL);
		methodStubs.setLayout(new GridLayout());
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		methodStubs.setLayoutData(data);

		constructorButton = new Button(methodStubs, SWT.CHECK);
		constructorButton.setText(JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(constructorButton, CONSTRUCTOR, null);
		
		inheritButton = new Button(methodStubs, SWT.CHECK);
		inheritButton.setText(JAVA_CLASS_INHERIT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(inheritButton, ABSTRACT_METHODS, null);
		
		Dialog.applyDialogFont(parent);
	}
	
}
