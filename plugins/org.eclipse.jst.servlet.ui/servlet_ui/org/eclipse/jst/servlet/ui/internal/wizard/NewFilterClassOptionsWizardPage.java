/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.Iterator;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.FilterSupertypesValidator;
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
public class NewFilterClassOptionsWizardPage extends NewJavaClassOptionsWizardPage implements ISelectionChangedListener {
	
	protected Button initButton;
	protected Button destroyButton;
	protected Button doFilterButton;
	
	private FilterSupertypesValidator validator;
	
	public NewFilterClassOptionsWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
		validator = new FilterSupertypesValidator(model);
	}
	
	protected void enter() {
		super.enter();
		
		interfaceViewer.getList().deselectAll();
		removeButton.setEnabled(false);
		String superClass = getDataModel().getStringProperty(INewFilterClassDataModelProperties.SUPERCLASS);
		boolean hasSuperClass = (superClass == null) ? false : superClass.trim().length() > 0;
		constructorButton.setEnabled(hasSuperClass);
		if (!hasSuperClass) constructorButton.setSelection(false);
		
		inheritButton.setSelection(true);
		inheritButton.setEnabled(false);
		
        initButton.setSelection(true);
        initButton.setEnabled(false);

        destroyButton.setSelection(true);
        destroyButton.setEnabled(false);
		
        doFilterButton.setSelection(true);
        doFilterButton.setEnabled(false);
	}
	
	protected void createModifierControls(Composite parent) {
		super.createModifierControls(parent);
		
		// The user should not be able to change the public and abstract modifiers. 
		// The filter class must be always public and non-abstract. 
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

		Composite comp = new Composite(buttonCompo, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.makeColumnsEqualWidth = true;
		comp.setLayout(layout);
		data = new GridData(GridData.FILL_BOTH);
		comp.setLayoutData(data);
		
		initButton = new Button(comp, SWT.CHECK);
		initButton.setText("&init"); //$NON-NLS-1$
		synchHelper.synchCheckbox(initButton, INewFilterClassDataModelProperties.INIT, null);

		destroyButton = new Button(comp, SWT.CHECK);
		destroyButton.setText("destro&y"); //$NON-NLS-1$
		synchHelper.synchCheckbox(destroyButton, INewFilterClassDataModelProperties.DESTROY, null);

		doFilterButton = new Button(comp, SWT.CHECK);
		doFilterButton.setText("do&Filter"); //$NON-NLS-1$
		synchHelper.synchCheckbox(doFilterButton, INewFilterClassDataModelProperties.DO_FILTER, null);
		
		interfaceViewer.addSelectionChangedListener(this);
		
	    Dialog.applyDialogFont(parent);
	}

	protected String[] getValidationPropertyNames() {
		return new String[] { INewJavaClassDataModelProperties.INTERFACES };
	}
	
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection selection = (StructuredSelection) event.getSelection();
		
		// if the selection is empty, then the remove button is disabled
		if (selection.isEmpty()) {
			removeButton.setEnabled(false);
			return;
		}
		
		// if the selection is non-empty and the filter extends a class which
		// implements javax.servlet.Filter, then the remove button is enabled
		if (validator.isFilterSuperclass()) {
			removeButton.setEnabled(true);
			return;
		} 
		
		// if the selection is non-empty and the filter does not extend a class
		// which implements javax.servlet.Filter, then the remove button is
		// disabled only if the Filter interface is in the selection
		Iterator iter = selection.iterator();
		while (iter.hasNext()) {
			if (FilterSupertypesValidator.FILTER_INTERFACE_NAME.equals(iter.next()))
			removeButton.setEnabled(false);
			return;
		}

		// in all other cases the remove button is enabled
		removeButton.setEnabled(true);
	}
}
