/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.ui.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.ejb.annotation.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;



public class NewJavaClassOptionsWizardPage extends WTPWizardPage {

	protected Button publicButton;
	protected Button abstractButton;
	protected Button finalButton;
	protected ListViewer interfaceViewer;
	protected Button addButton;
	protected Button removeButton;
	protected Button inheritButton;
	protected Button constructorButton;
	protected Button mainMethodButton;

	/**
	 * @param model
	 * @param pageName
	 */
	public NewJavaClassOptionsWizardPage(WTPOperationDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName);
		setDescription(pageDesc);
		this.setTitle(pageTitle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{NewJavaClassDataModel.MODIFIER_ABSTRACT, NewJavaClassDataModel.MODIFIER_FINAL};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);

		createModifierControls(composite);
		createInterfaceControls(composite);

		// Separator label
		Label seperator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		seperator.setLayoutData(data);

		createStubsComposite(composite);

		publicButton.setFocus();
		WorkbenchHelp.setHelp(composite, getInfopopID());
		return composite;
	}

	/**
	 * Create controls for the modifiers
	 */
	protected void createModifierControls(Composite parent) {
		Label modifiersLabel = new Label(parent, SWT.NONE);
		modifiersLabel.setText(IEJBAnnotationConstants.JAVA_CLASS_MODIFIERS_LABEL);
		modifiersLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.makeColumnsEqualWidth = true;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		publicButton = new Button(composite, SWT.CHECK);
		publicButton.setText(IEJBAnnotationConstants.JAVA_CLASS_PUBLIC_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(publicButton, NewJavaClassDataModel.MODIFIER_PUBLIC, null);

		abstractButton = new Button(composite, SWT.CHECK);
		abstractButton.setText(IEJBAnnotationConstants.JAVA_CLASS_ABSTRACT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(abstractButton, NewJavaClassDataModel.MODIFIER_ABSTRACT, null);

		finalButton = new Button(composite, SWT.CHECK);
		finalButton.setText(IEJBAnnotationConstants.JAVA_CLASS_FINAL_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(finalButton, NewJavaClassDataModel.MODIFIER_FINAL, null);
	}

	protected void createInterfaceControls(Composite parent) {
		Label interfaceLabel = new Label(parent, SWT.NONE);
		interfaceLabel.setText(IEJBAnnotationConstants.JAVA_CLASS_INTERFACES_LABEL);
		interfaceLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING));
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		interfaceViewer = new ListViewer(composite);
		interfaceViewer.getList().setLayoutData(new GridData(GridData.FILL_BOTH));
		interfaceViewer.setContentProvider(getInterfaceContentProvider());
		interfaceViewer.setLabelProvider(getInterfaceLabelProvider());
		interfaceViewer.setInput(model.getProperty(NewJavaClassDataModel.INTERFACES));

		Composite buttonCompo = new Composite(composite, SWT.NULL);
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonCompo.setLayout(layout);
		buttonCompo.setLayoutData(new GridData(GridData.FILL_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING));

		addButton = new Button(buttonCompo, SWT.PUSH);
		addButton.setText(IEJBAnnotationConstants.ADD_BUTTON_LABEL);
		addButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
		addButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				handleInterfaceAddButtonSelected();
			}
			public void widgetDefaultSelected(SelectionEvent event) {
				//Do nothing
			}
		});

		removeButton = new Button(buttonCompo, SWT.PUSH);
		removeButton.setText(IEJBAnnotationConstants.REMOVE_BUTTON);
		removeButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL));
		removeButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				handleInterfaceRemoveButtonSelected();
			}
			public void widgetDefaultSelected(SelectionEvent event) {
				//Do nothing
			}
		});
		removeButton.setEnabled(false);

		interfaceViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				removeButton.setEnabled(!selection.isEmpty());
			}
		});

	}

	/**
	 * Create the composite with all the stubs
	 */
	protected void createStubsComposite(Composite parent) {
		Label stubLabel = new Label(parent, SWT.NONE);
		stubLabel.setText(IEJBAnnotationConstants.JAVA_CLASS_METHOD_STUBS_LABEL);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		stubLabel.setLayoutData(data);

		Composite buttonCompo = new Composite(parent, SWT.NULL);
		buttonCompo.setLayout(new GridLayout());
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		buttonCompo.setLayoutData(data);

		mainMethodButton = new Button(buttonCompo, SWT.CHECK);
		mainMethodButton.setText(IEJBAnnotationConstants.JAVA_CLASS_MAIN_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(mainMethodButton, NewJavaClassDataModel.MAIN_METHOD, null);

		inheritButton = new Button(buttonCompo, SWT.CHECK);
		inheritButton.setText(IEJBAnnotationConstants.JAVA_CLASS_INHERIT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(inheritButton, NewJavaClassDataModel.ABSTRACT_METHODS, null);

		constructorButton = new Button(buttonCompo, SWT.CHECK);
		constructorButton.setText(IEJBAnnotationConstants.JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(constructorButton, NewJavaClassDataModel.CONSTRUCTOR, null);
	}

	/**
	 * @see IStatefulWizardPage#saveWidgetValues()
	 */
	// public void saveWidgetValues() {
	// // TODO: do we want to do this here or in the concrete subclasses???
	// IDialogSettings store = getDialogSettings();
	// if (store != null)
	// store.put(getUniqueKey(""), getUniqueKey("")); //$NON-NLS-1$
	// //$NON-NLS-2$
	// DialogSettingsHelper.saveButton(inheritButton,
	// getUniqueKey(idInheritedAbstractButton), store);
	// DialogSettingsHelper.saveButton(constructorButton,
	// getUniqueKey(idSuperClassConstructorsButton), store);
	// DialogSettingsHelper.saveButton(publicButton,
	// getUniqueKey(idPublicButton), store);
	// DialogSettingsHelper.saveButton(abstractButton,
	// getUniqueKey(idAbstractButton), store);
	// DialogSettingsHelper.saveButton(finalButton, getUniqueKey(idFinalButton),
	// store);
	//
	// }
	/**
	 * @see IStatefulWizardPage#restoreWidgetValues()
	 */
	// public void restoreWidgetValues() {
	// IDialogSettings store = super.getDialogSettings();
	// if (store != null && store.get(getUniqueKey("")) != null) { //$NON-NLS-1$
	// DialogSettingsHelper.restoreButton(inheritButton,
	// getUniqueKey(idInheritedAbstractButton), store);
	// DialogSettingsHelper.restoreButton(constructorButton,
	// getUniqueKey(idSuperClassConstructorsButton), store);
	// DialogSettingsHelper.restoreButton(publicButton,
	// getUniqueKey(idPublicButton), store);
	// DialogSettingsHelper.restoreButton(abstractButton,
	// getUniqueKey(idAbstractButton), store);
	// DialogSettingsHelper.restoreButton(finalButton,
	// getUniqueKey(idFinalButton), store);
	// }
	// }
	/**
	 * Returns the Super Interface Content Provider
	 */
	protected IStructuredContentProvider getInterfaceContentProvider() {
		return new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				Object[] ret = new Object[0];
				if (inputElement instanceof ArrayList) {
					ret = ((ArrayList) inputElement).toArray();
				}
				return ret;
			}
			public void dispose() {
				//Do nothing
			}
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				//Default is nothing
			}
		};
	}

	/**
	 * Returns the Super Interface Label Provider
	 */
	protected ILabelProvider getInterfaceLabelProvider() {
		return new ILabelProvider() {
			public Image getImage(Object element) {
				return J2EEUIPlugin.getDefault().getImage("full/obj16/interface_obj"); //$NON-NLS-1$
			}

			public String getText(Object element) {
				String ret = IEJBAnnotationConstants.EMPTY_STRING;
				if (element instanceof String)
					ret = (String) element;
				return ret;
			}

			public void addListener(ILabelProviderListener listener) {
				//Do nothing
			}
			public void dispose() {
				//Do nothing
			}
			public boolean isLabelProperty(Object element, String property) {
				return true;
			}
			public void removeListener(ILabelProviderListener listener) {
				//Do nothing
			}
		};
	}

	/**
	 * Browse for a new Super Interface Class
	 */
	protected void handleInterfaceAddButtonSelected() {
		// TODO fix add interface dialog
		// IProject project = model.getTargetProject();
		// SuperInterfaceSelectionDialog dialog = new
		// SuperInterfaceSelectionDialog(
		// getShell(),
		// (IRunnableContext)Workbench.getInstance().getActiveWorkbenchWindow(),interfaceViewer,
		// ProjectUtilities.getJavaProject(project));
		// dialog.setTitle(IEJBAnnotationConstants.INTERFACE_SELECTION_DIALOG_TITLE);
		// dialog.open();
		// List valueList = (List)interfaceViewer.getInput();
		// model.setProperty(NewJavaClassDataModel.INTERFACES, valueList);
	}

	/**
	 * Remove an interface from the SuperInterface List
	 */
	protected void handleInterfaceRemoveButtonSelected() {
		IStructuredSelection selection = (IStructuredSelection) interfaceViewer.getSelection();
		if (!selection.isEmpty()) {
			List valueList = (List) interfaceViewer.getInput();
			Iterator iterator = selection.iterator();
			while (iterator.hasNext()) {
				Object next = iterator.next();
				valueList.remove(next);
			}
			interfaceViewer.setInput(valueList);
			model.setProperty(NewJavaClassDataModel.INTERFACES, valueList);
		}
	}
}