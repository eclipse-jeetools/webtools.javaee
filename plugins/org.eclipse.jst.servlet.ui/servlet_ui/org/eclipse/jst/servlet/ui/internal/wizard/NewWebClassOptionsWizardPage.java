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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties;
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
	protected void enter() {
		super.enter();
		
		interfaceViewer.getList().deselectAll();
		removeButton.setEnabled(false);
		
		String superClass = getDataModel().getStringProperty(INewFilterClassDataModelProperties.SUPERCLASS);
		boolean hasSuperClass = (superClass == null) ? false : superClass.trim().length() > 0;
		constructorButton.setEnabled(hasSuperClass);
		if (!hasSuperClass) constructorButton.setSelection(false);
	}
	
	@Override
	protected void createModifierControls(Composite parent) {
		super.createModifierControls(parent);
		
		// The user should not be able to change the public and abstract modifiers. 
		// The servlet class must be always public and non-abstract. 
		// Otherwise, the servlet container may not initialize it. 
		publicButton.setEnabled(false);
		abstractButton.setEnabled(false);
	}
	
	@Override
	protected String[] getValidationPropertyNames() {
		return new String[] { INewJavaClassDataModelProperties.INTERFACES };
	}
	
	@Override	
	protected void createStubsComposite(Composite parent) {
		Label stubLabel = new Label(parent, SWT.NONE);
		stubLabel.setText(IWebWizardConstants.JAVA_CLASS_METHOD_STUBS_LABEL);
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
		constructorButton.setText(IWebWizardConstants.JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(constructorButton, INewJavaClassDataModelProperties.CONSTRUCTOR, null);
		
		inheritButton = new Button(methodStubs, SWT.CHECK);
		inheritButton.setText(IWebWizardConstants.JAVA_CLASS_INHERIT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(inheritButton, INewJavaClassDataModelProperties.ABSTRACT_METHODS, null);
		
		Dialog.applyDialogFont(parent);
	}
	
}
