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
package org.eclipse.jst.j2ee.internal.ejb.dialogs;


import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.ejb.operations.DeleteEJBInfoProvider;
import org.eclipse.jst.j2ee.internal.ejb.ui.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIContextIds;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.help.WorkbenchHelp;


/**
 * Insert the type's description here. Creation date: (6/1/2001 10:51:36 AM)
 * 
 * @author: Administrator
 */
public class DeleteBeanDialog extends MessageDialog implements DeleteEJBInfoProvider, Listener {
	// //$NON-NLS-1$
	protected Button deleteBeanButton;
	protected Button deleteBeanClassesButton;
	protected Button deleteAccessBeanButton;
	protected Button deleteBeanDeployCodeButton;
	//capture values
	protected boolean isBeanDelete = false;
	protected boolean isBeanClassesDelete = false;
	protected boolean isAccessBeanDelete = false;
	protected boolean isBeanDeployDelete = false;

	/**
	 * DeleteBeanDialoge constructor comment.
	 * 
	 * @param parentShell
	 *            org.eclipse.swt.widgets.Shell
	 */
	public DeleteBeanDialog(Shell parentShell) {
		super(parentShell, EJBUIMessages.getResourceString(EJBUIMessages.Delete_Enterprise_Beans), null, EJBUIMessages.getResourceString(EJBUIMessages.Delete_Options_Query), QUESTION, new String[]{IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
	}

	/**
	 * Notifies that this dialog's button with the given id has been pressed.
	 * <p>
	 * The <code>Dialog</code> implementation of this framework method calls
	 * <code>okPressed</code> if the ok button is the pressed, and <code>cancelPressed</code> if
	 * the cancel button is the pressed. All other button presses are ignored. Subclasses may
	 * override to handle other buttons, but should call <code>super.buttonPressed</code> if the
	 * default handling of the ok and cancel buttons is desired.
	 * </p>
	 * 
	 * @param buttonId
	 *            the id of the button that was pressed (see <code>IDialogConstants.*_ID</code>
	 *            constants)
	 */
	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.SELECT_ALL_ID == buttonId)
			handleSelectAllButtonPressed();
		else if (IDialogConstants.DESELECT_ALL_ID == buttonId)
			handleDeselectAllButtonPressed();
		else if (IDialogConstants.OK_ID == buttonId)
			okPressed();
		else if (IDialogConstants.CANCEL_ID == buttonId)
			cancelPressed();
	}

	protected void captureSelections() {
		isBeanDelete = deleteBeanButton.getSelection();
		isBeanClassesDelete = deleteBeanClassesButton.getSelection();
		isAccessBeanDelete = deleteAccessBeanButton.getSelection();
		isBeanDeployDelete = deleteBeanDeployCodeButton.getSelection();
	}

	/**
	 * Create the dialog contents
	 * 
	 * @return org.eclipse.swt.widgets.Window
	 */
	public Control createCustomArea(Composite parent) {

		Composite deleteComposite = new Composite(parent, SWT.NONE);

		WorkbenchHelp.setHelp(deleteComposite, J2EEUIContextIds.DELETE_ENTERPRISE_BEAN_DIALOG);

		GridLayout lay = new GridLayout();
		lay.numColumns = 2;
		deleteComposite.setLayout(lay);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalIndent = 20;
		deleteComposite.setLayoutData(data);

		deleteBeanButton = new Button(deleteComposite, SWT.CHECK);
		deleteBeanButton.setText(EJBUIMessages.getResourceString(EJBUIMessages.DELETE_BEAN_ONLY)); //$NON-NLS-1$ = "Delete Bean Only"

		deleteBeanClassesButton = new Button(deleteComposite, SWT.CHECK);
		deleteBeanClassesButton.setText(EJBUIMessages.getResourceString(EJBUIMessages.Delete_Bean_Classes_UI)); //$NON-NLS-1$ = "Delete Bean Classes"
		deleteBeanClassesButton.addListener(SWT.Selection, this);

		deleteAccessBeanButton = new Button(deleteComposite, SWT.CHECK);
		deleteAccessBeanButton.setText(EJBUIMessages.getResourceString(EJBUIMessages.Delete_Access_Bean)); //$NON-NLS-1$ = "Delete Access Bean"

		deleteBeanDeployCodeButton = new Button(deleteComposite, SWT.CHECK);
		deleteBeanDeployCodeButton.setText(EJBUIMessages.getResourceString(EJBUIMessages.Delete_Deployed_Code)); //$NON-NLS-1$ = "Delete Deployed Code"

		createSelectButtonBar(deleteComposite);

		setInitialSelection();
		return deleteComposite;
	}

	protected Button createSelectButton(Composite parent, int id, String label) {
		Button button = new Button(parent, SWT.PUSH);

		button.setText(label);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		data.heightHint = convertVerticalDLUsToPixels(IDialogConstants.BUTTON_HEIGHT);
		int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		button.setLayoutData(data);

		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				buttonPressed(((Integer) event.widget.getData()).intValue());
			}
		});
		return button;
	}

	protected void createSelectButtonBar(Composite parent) {
		createSelectButton(parent, IDialogConstants.SELECT_ALL_ID, EJBUIMessages.getResourceString(EJBUIMessages.Select_All_UI)); //$NON-NLS-1$ = "Select All"
		createSelectButton(parent, IDialogConstants.DESELECT_ALL_ID, EJBUIMessages.getResourceString(EJBUIMessages.Deselect_All_UI)); //$NON-NLS-1$ = "Deselect All"
	}

	/**
	 * If the deletBeanClassesButton is selected, both the deleteAccessBeanButton and the
	 * deleteBeanDeployCodeButton should be selected and disabled.
	 */
	protected void handleDeleteBeanClassesButton() {
		if (deleteBeanClassesButton.getSelection()) {
			deleteAccessBeanButton.setSelection(true);
			deleteAccessBeanButton.setEnabled(false);
			deleteBeanDeployCodeButton.setSelection(true);
			deleteBeanDeployCodeButton.setEnabled(false);
		} else {
			deleteAccessBeanButton.setEnabled(true);
			deleteBeanDeployCodeButton.setEnabled(true);
		}
	}

	protected void handleDeselectAllButtonPressed() {
		deleteBeanButton.setSelection(false);
		deleteBeanClassesButton.setSelection(false);
		deleteAccessBeanButton.setSelection(false);
		deleteAccessBeanButton.setEnabled(true);
		deleteBeanDeployCodeButton.setSelection(false);
		deleteBeanDeployCodeButton.setEnabled(true);
	}

	/**
	 * Sent when an event that the receiver has registered for occurs.
	 * 
	 * @param event
	 *            the event which occurred
	 */
	public void handleEvent(org.eclipse.swt.widgets.Event event) {
		Widget widget = event.widget;
		if (widget == deleteBeanClassesButton)
			handleDeleteBeanClassesButton();
	}

	protected void handleSelectAllButtonPressed() {
		deleteBeanButton.setSelection(true);
		deleteBeanClassesButton.setSelection(true);
		deleteAccessBeanButton.setSelection(true);
		deleteAccessBeanButton.setEnabled(false);
		deleteBeanDeployCodeButton.setSelection(true);
		deleteBeanDeployCodeButton.setEnabled(false);
	}

	/**
	 * isAccessBeanDelete method comment.
	 */
	public boolean isAccessBeanDelete() {
		return isAccessBeanDelete;
	}

	/**
	 * isBeanClassesDelete method comment.
	 */
	public boolean isBeanClassesDelete() {
		return isBeanClassesDelete;
	}

	/**
	 * isBeanDelete method comment.
	 */
	public boolean isBeanDelete() {
		return isBeanDelete;
	}

	/**
	 * isBeanDeployDelete method comment.
	 */
	public boolean isBeanDeployDelete() {
		return isBeanDeployDelete;
	}

	/**
	 * Notifies that the ok button of this dialog has been pressed.
	 * <p>
	 * The <code>Dialog</code> implementation of this framework method sets this dialog's return
	 * code to <code>Window.OK</code> and closes the dialog. Subclasses may override.
	 * </p>
	 */
	protected void okPressed() {
		captureSelections();
		super.okPressed();
	}

	protected void setInitialSelection() {
		deleteBeanButton.setSelection(true);
		deleteBeanClassesButton.setSelection(true);
		deleteAccessBeanButton.setSelection(true);
		deleteAccessBeanButton.setEnabled(false);
		deleteBeanDeployCodeButton.setSelection(true);
		deleteBeanDeployCodeButton.setEnabled(false);
	}
}