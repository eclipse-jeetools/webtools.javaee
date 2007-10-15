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
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassOptionsWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * 
 */
public class NewServletClassOptionsWizardPage extends NewJavaClassOptionsWizardPage {
	
	protected Button initButton;
	protected Button destroyButton;
	protected Button getConfigButton;
	protected Button getInfoButton;
	protected Button serviceButton;
	protected Button doGetButton;
	protected Button doPostButton;
	protected Button doPutButton;
	protected Button doDeleteButton;
	protected Button doHeadButton;
	protected Button doOptionsButton;
	protected Button doTraceButton;
	
	public NewServletClassOptionsWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	
	protected void createModifierControls(Composite parent) {
		super.createModifierControls(parent);
		
		// The user should not be able to change the public and abstract modifiers. 
		// The servlet class must be always public and non-abstract. 
		// Otherwise, the servlet container may not initialize it. 
		publicButton.setEnabled(false);
		abstractButton.setEnabled(false);
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
		synchHelper.synchCheckbox(constructorButton, INewJavaClassDataModelProperties.CONSTRUCTOR, null);

		inheritButton = new Button(buttonCompo, SWT.CHECK);
		inheritButton.setText(IWebWizardConstants.JAVA_CLASS_INHERIT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(inheritButton, INewJavaClassDataModelProperties.ABSTRACT_METHODS, null);
		inheritButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				boolean enable = inheritButton.getSelection();
				initButton.setEnabled(enable);
				destroyButton.setEnabled(enable);
				getConfigButton.setEnabled(enable);
				getInfoButton.setEnabled(enable);
				serviceButton.setEnabled(enable);
				doGetButton.setEnabled(enable);
				doPostButton.setEnabled(enable);
				doPutButton.setEnabled(enable);
				doDeleteButton.setEnabled(enable);
				doHeadButton.setEnabled(enable);
				doOptionsButton.setEnabled(enable);
				doTraceButton.setEnabled(enable);
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
		initButton.setText("init"); //$NON-NLS-1$
		synchHelper.synchCheckbox(initButton, INewServletClassDataModelProperties.INIT, null);

		destroyButton = new Button(comp, SWT.CHECK);
		destroyButton.setText("destroy"); //$NON-NLS-1$
		synchHelper.synchCheckbox(destroyButton, INewServletClassDataModelProperties.DESTROY, null);

		getConfigButton = new Button(comp, SWT.CHECK);
		getConfigButton.setText("getServletConfig"); //$NON-NLS-1$
		synchHelper.synchCheckbox(getConfigButton, INewServletClassDataModelProperties.GET_SERVLET_CONFIG, null);

		getInfoButton = new Button(comp, SWT.CHECK);
		getInfoButton.setText("getServletInfo"); //$NON-NLS-1$
		synchHelper.synchCheckbox(getInfoButton, INewServletClassDataModelProperties.GET_SERVLET_INFO, null);
		
		serviceButton = new Button(comp, SWT.CHECK);
		serviceButton.setText("service"); //$NON-NLS-1$
		synchHelper.synchCheckbox(serviceButton, INewServletClassDataModelProperties.SERVICE, null);

		doGetButton = new Button(comp, SWT.CHECK);
		doGetButton.setText("doGet"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doGetButton, INewServletClassDataModelProperties.DO_GET, null);

		doPostButton = new Button(comp, SWT.CHECK);
		doPostButton.setText("doPost"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doPostButton, INewServletClassDataModelProperties.DO_POST, null);

		doPutButton = new Button(comp, SWT.CHECK);
		doPutButton.setText("doPut"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doPutButton, INewServletClassDataModelProperties.DO_PUT, null);

		doDeleteButton = new Button(comp, SWT.CHECK);
		doDeleteButton.setText("doDelete"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doDeleteButton, INewServletClassDataModelProperties.DO_DELETE, null);

		doHeadButton = new Button(comp, SWT.CHECK);
		doHeadButton.setText("doHead"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doHeadButton, INewServletClassDataModelProperties.DO_HEAD, null);

		doOptionsButton = new Button(comp, SWT.CHECK);
		doOptionsButton.setText("doOptions"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doOptionsButton, INewServletClassDataModelProperties.DO_OPTIONS, null);

		doTraceButton = new Button(comp, SWT.CHECK);
		doTraceButton.setText("doTrace"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doTraceButton, INewServletClassDataModelProperties.DO_TRACE, null);
		
	    Dialog.applyDialogFont(parent);
	}
}
