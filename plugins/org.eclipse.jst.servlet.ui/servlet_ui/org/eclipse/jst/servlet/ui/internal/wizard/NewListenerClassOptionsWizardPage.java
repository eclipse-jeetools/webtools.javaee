/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     David Schneider, david.schneider@unisys.com - bug 142500
 *     Kiril Mitov, k.mitov@sap.com	- bug 204160
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


/**
 * 
 */
public class NewListenerClassOptionsWizardPage extends NewJavaClassOptionsWizardPage  {
	
	public NewListenerClassOptionsWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	
	protected void enter() {
		super.enter();
		
		String superClass = getDataModel().getStringProperty(INewFilterClassDataModelProperties.SUPERCLASS);
		boolean hasSuperClass = (superClass == null) ? false : superClass.trim().length() > 0;
		constructorButton.setEnabled(hasSuperClass);
		if (!hasSuperClass) constructorButton.setSelection(false);
	}
	
	protected void createModifierControls(Composite parent) {
		super.createModifierControls(parent);
		
		// The user should not be able to change the public and abstract modifiers. 
		// The servlet class must be always public and non-abstract. 
		// Otherwise, the servlet container may not initialize it. 
		publicButton.setEnabled(false);
		abstractButton.setEnabled(false);
	}
	
	protected String[] getValidationPropertyNames() {
		return new String[] { INewJavaClassDataModelProperties.INTERFACES };
	}
	
	
	protected void createStubsComposite(Composite parent) {
		Label stubLabel = new Label(parent, SWT.NONE);
		stubLabel.setText(IWebWizardConstants.JAVA_CLASS_METHOD_STUBS_LABEL);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		stubLabel.setLayoutData(data);

		Composite buttonCompo = new Composite(parent, SWT.NULL);
		buttonCompo.setLayout(new GridLayout());
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		buttonCompo.setLayoutData(data);

		constructorButton = new Button(buttonCompo, SWT.CHECK);
		constructorButton.setText(IWebWizardConstants.JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(constructorButton, INewJavaClassDataModelProperties.CONSTRUCTOR, null);
		
		Dialog.applyDialogFont(parent);
	}
	
}
