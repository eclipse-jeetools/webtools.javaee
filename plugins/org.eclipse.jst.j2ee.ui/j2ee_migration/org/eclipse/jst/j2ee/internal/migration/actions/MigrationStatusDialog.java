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
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ibm.wtp.common.logger.proxy.Logger;


/**
 * A dialog to display the result of migration to the user, as contained in an
 * <code>J2EEMigrationStatus</code> object. If an status contains additional detailed information
 * then a Details button is automatically supplied, which shows or hides a status details viewer
 * when pressed by the user.
 * 
 * @see org.eclipse.core.runtime.J2EEMigrationStatus
 */
public class MigrationStatusDialog extends Dialog implements IJ2EEMigrationConstants {

	/**
	 * Reserve room for this many list items.
	 */
	private static final int LIST_ITEM_COUNT = 7;

	/**
	 * The Details button.
	 */
	private Button detailsButton;

	/**
	 * The title of the dialog.
	 */
	private String title;

	/**
	 * The message to display.
	 */
	private String message;

	/**
	 * The SWT list control that displays the error details.
	 */
	private Text text;

	/**
	 * Indicates whether the error details viewer is currently created.
	 */
	private boolean textCreated = false;

	/**
	 * The main status object.
	 */
	private J2EEMigrationStatus status;

	/**
	 * List of the main error object's detailed errors (element type:
	 * <code>J2EEMigrationStatus</code>).
	 */
	private java.util.List statusList;

	public MigrationStatusDialog(Shell parentShell, String dialogTitle, String message, J2EEMigrationStatus status) {
		super(parentShell);
		this.status = status;
		setTitleOrDefault(dialogTitle);
		setMessageOrDefault(message);
		statusList = Arrays.asList(status.getChildren());
		setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.APPLICATION_MODAL);
	}

	protected void setTitleOrDefault(String dialogTitle) {
		if (dialogTitle != null)
			title = dialogTitle;
		else {
			switch (status.getSeverity()) {
				case (J2EEMigrationStatus.ERROR) :
					title = MIGRATION_ERRORS;
					break;
				default :
					title = MIGRATION_COMPLETE;
					break;
			}
		}
	}

	protected void setMessageOrDefault(String dialogMessage) {
		if (dialogMessage != null)
			message = dialogMessage;
		else if (status.getMessage() != null)
			message = status.getMessage();
		else {
			switch (status.getSeverity()) {
				case J2EEMigrationStatus.ERROR :
					message = MIGRATION_ERRORS_REPORT;
					break;
				case J2EEMigrationStatus.WARNING :
					message = MIGRATION_WARNINGS_REPORT;
					break;
				case J2EEMigrationStatus.NOT_NEEDED :
					message = MIGRATION_NOT_NEEDED_REPORT;
					break;
				case J2EEMigrationStatus.NOT_POSSIBLE :
					message = MIGRATION_NOT_POSSIBLE_REPORT;
				default :
					message = MIGRATION_SUCCESS_REPORT;
					break;
			}
		}
	}

	/*
	 * (non-Javadoc) Method declared on Dialog. Handles the pressing of the Ok or Details button in
	 * this dialog. If the Ok button was pressed then close this dialog. If the Details button was
	 * pressed then toggle the displaying of the error details area. Note that the Details button
	 * will only be visible if the error being displayed specifies child details.
	 */
	protected void buttonPressed(int id) {
		if (id == IDialogConstants.DETAILS_ID) { // was the details button pressed?
			toggleDetailsArea();
		} else {
			logStatus();
			super.buttonPressed(id);

		}
	}

	/*
	 * (non-Javadoc) Method declared in Window.
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(title);
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// create OK and Details buttons
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		if (status.isMultiStatus()) {
			detailsButton = createButton(parent, IDialogConstants.DETAILS_ID, IDialogConstants.SHOW_DETAILS_LABEL, false);
		}
	}

	protected int getImageKey() {
		switch (status.getSeverity()) {
			case J2EEMigrationStatus.ERROR :
				return SWT.ICON_ERROR;
			case J2EEMigrationStatus.WARNING :
				return SWT.ICON_WARNING;
			default :
				return SWT.ICON_INFORMATION;
		}
	}

	/*
	 * (non-Javadoc) Method declared on Dialog. Creates and returns the contents of the upper part
	 * of the dialog (above the button bar).
	 */
	protected Control createDialogArea(Composite parent) {
		// create composite
		Composite composite = (Composite) super.createDialogArea(parent);
		((GridLayout) composite.getLayout()).numColumns = 2;

		// create image
		Image image = composite.getDisplay().getSystemImage(getImageKey());
		if (image != null) {
			Label label = new Label(composite, 0);
			image.setBackground(label.getBackground());
			label.setImage(image);
			label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_BEGINNING));
		}

		// create message
		if (message != null) {
			Label label = new Label(composite, SWT.WRAP);
			label.setText(message);
			GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
			data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
			label.setLayoutData(data);
			label.setFont(parent.getFont());
		}
		return composite;
	}

	protected Text createDropDownText(Composite parent) {
		// create the list
		text = new Text(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		// fill the list
		populateText(text);

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
		data.heightHint = text.getLineHeight() * LIST_ITEM_COUNT;
		text.setLayoutData(data);
		textCreated = true;
		return text;
	}


	public static int openDialog(Shell parentShell, String title, String message, J2EEMigrationStatus status) {
		MigrationStatusDialog dialog = new MigrationStatusDialog(parentShell, title, message, status);
		return dialog.open();
	}

	private void logStatus() {
		Iterator aenum = statusList.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append(message);
		while (aenum.hasNext()) {
			J2EEMigrationStatus childStatus = (J2EEMigrationStatus) aenum.next();
			populateText(sb, childStatus, 0);
		}
		Logger.getLogger().logInfo(sb.toString());

	}

	/**
	 * Populates the list using this error dialog's status object. This walks the child stati of the
	 * status object and displays them in a list. The format for each entry is status_path :
	 * status_message If the status's path was null then it (and the colon) are omitted.
	 */
	private void populateText(Text localText) {
		Iterator aenum = statusList.iterator();
		StringBuffer sb = new StringBuffer();
		while (aenum.hasNext()) {
			J2EEMigrationStatus childStatus = (J2EEMigrationStatus) aenum.next();
			populateText(sb, childStatus, 0);
		}
		localText.setText(sb.toString());

	}

	private void populateText(StringBuffer sb, J2EEMigrationStatus aStatus, int nesting) {
		for (int i = 0; i < nesting; i++) {
			sb.append("  "); //$NON-NLS-1$
		}
		sb.append(aStatus.getMessageForDisplay());
		sb.append('\n');
		J2EEMigrationStatus[] children = aStatus.getChildren();
		for (int i = 0; i < children.length; i++) {
			populateText(sb, children[i], nesting + 1);
		}
	}

	/**
	 * Toggles the unfolding of the details area. This is triggered by the user pressing the details
	 * button.
	 */
	private void toggleDetailsArea() {
		Point windowSize = getShell().getSize();
		Point oldSize = getContents().computeSize(SWT.DEFAULT, SWT.DEFAULT);

		if (textCreated) {
			text.dispose();
			textCreated = false;
			detailsButton.setText(IDialogConstants.SHOW_DETAILS_LABEL);
		} else {
			text = createDropDownText((Composite) getContents());
			detailsButton.setText(IDialogConstants.HIDE_DETAILS_LABEL);
		}

		Point newSize = getContents().computeSize(SWT.DEFAULT, SWT.DEFAULT);

		getShell().setSize(new Point(windowSize.x, windowSize.y + (newSize.y - oldSize.y)));
	}

}


