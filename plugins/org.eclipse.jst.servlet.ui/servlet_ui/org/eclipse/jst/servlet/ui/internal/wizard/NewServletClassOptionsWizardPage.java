/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class NewServletClassOptionsWizardPage extends NewJavaClassOptionsWizardPage {
	protected Button initButton;
	protected Button toStringButton;
	protected Button getInfoButton;
	protected Button doPostButton;
	protected Button doPutButton;
	protected Button doDeleteButton;
	protected Button destroyButton;
	protected Button doGetButton;

	public NewServletClassOptionsWizardPage(NewServletClassDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	/**
	 * Create the composite with all the stubs
	 */
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
		synchHelper.synchCheckbox(constructorButton, NewJavaClassDataModel.CONSTRUCTOR, null);

		inheritButton = new Button(buttonCompo, SWT.CHECK);
		inheritButton.setText(IWebWizardConstants.JAVA_CLASS_INHERIT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(inheritButton, NewJavaClassDataModel.ABSTRACT_METHODS, null);
		inheritButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				boolean enable = inheritButton.getSelection();
				initButton.setEnabled(enable);
				toStringButton.setEnabled(enable);
				getInfoButton.setEnabled(enable);
				doPostButton.setEnabled(enable);
				doPutButton.setEnabled(enable);
				doDeleteButton.setEnabled(enable);
				destroyButton.setEnabled(enable);
				doGetButton.setEnabled(enable);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				//Do nothing
			}
		});

		Composite comp = new Composite(buttonCompo, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.makeColumnsEqualWidth = true;
		comp.setLayout(layout);
		data = new GridData(GridData.FILL_BOTH);
		comp.setLayoutData(data);

		initButton = new Button(comp, SWT.CHECK);
		initButton.setText("Init"); //$NON-NLS-1$
		synchHelper.synchCheckbox(initButton, NewServletClassDataModel.INIT, null);

		toStringButton = new Button(comp, SWT.CHECK);
		toStringButton.setText("toString"); //$NON-NLS-1$
		synchHelper.synchCheckbox(toStringButton, NewServletClassDataModel.TO_STRING, null);

		getInfoButton = new Button(comp, SWT.CHECK);
		getInfoButton.setText("getServletInfo"); //$NON-NLS-1$
		synchHelper.synchCheckbox(getInfoButton, NewServletClassDataModel.GET_SERVLET_INFO, null);

		doPostButton = new Button(comp, SWT.CHECK);
		doPostButton.setText("doPost"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doPostButton, NewServletClassDataModel.DO_POST, null);

		doPutButton = new Button(comp, SWT.CHECK);
		doPutButton.setText("doPut"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doPutButton, NewServletClassDataModel.DO_PUT, null);

		doDeleteButton = new Button(comp, SWT.CHECK);
		doDeleteButton.setText("doDelete"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doDeleteButton, NewServletClassDataModel.DO_DELETE, null);

		destroyButton = new Button(comp, SWT.CHECK);
		destroyButton.setText("destroy"); //$NON-NLS-1$
		synchHelper.synchCheckbox(destroyButton, NewServletClassDataModel.DESTROY, null);

		doGetButton = new Button(comp, SWT.CHECK);
		doGetButton.setText("doGet"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doGetButton, NewServletClassDataModel.DO_GET, null);
	}
}