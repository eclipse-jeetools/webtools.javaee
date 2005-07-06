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
/*
 * Created on May 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.dialogs;


import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author sagarwal
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class J2EEDeployStatusDialog extends ProgressMonitorDialog implements J2EEDeployUIConstants {

	/**
	 * Reserve room for this many list items.
	 */
	private static final int LIST_ITEM_COUNT = 7;
	private int severity = IStatus.OK;
	public Color DESC_COLOR = new Color(null, 255, 255, 255);
	/**
	 * The Details button.
	 */
	private Button detailsButton;

	/**
	 * The SWT list control that displays the error details.
	 */
	private Text text;

	/**
	 * Indicates whether the error details viewer is currently created.
	 */
	private boolean textCreated = false;

	/**
	 * List of the main error object's detailed errors (element type:
	 * <code>J2EEMigrationStatus</code>).
	 */
	private java.util.List statusList;

	/**
	 * @param parent
	 */
	public J2EEDeployStatusDialog(Shell parent, List status) {
		super(parent);
		setShellStyle(SWT.BORDER | SWT.TITLE | SWT.APPLICATION_MODAL | SWT.RESIZE); // no
		statusList = status;
	}

	/**
	 * Called just after the operation is run. Default behaviour is to decrement the nesting depth,
	 * and close the dialog.
	 * 
	 * @plannedfor 3.0
	 */
	protected void finishedRun() {
		decrementNestingDepth();
		clearCursors();
		cancel.setEnabled(true);
		detailsButton.setEnabled(true);
		computeSeverity();
		setDeploymentStatus();
	}

	/**
	 *  
	 */
	private void setDeploymentStatus() {
		setMessageOrDefault();
		imageLabel.setImage(getImage());
	}

	protected void setMessageOrDefault() {
		switch (getSeverity()) {
			case IStatus.ERROR :
				message = DEPLOY_ERRORS_REPORT;
				break;
			case IStatus.WARNING :
				message = DEPLOY_WARNINGS_REPORT;
				break;
			default :
				message = DEPLOY_SUCCESS_REPORT;
				break;
		}
		setMessage(message);
	}


	protected void createCancelButton(Composite parent) {
		super.createCancelButton(parent);
		cancel.setText(IDialogConstants.OK_LABEL);
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// cancel button
		createCancelButton(parent);
		detailsButton = createButton(parent, IDialogConstants.DETAILS_ID, IDialogConstants.SHOW_DETAILS_LABEL, false);
		detailsButton.setEnabled(false);
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
	 * @see org.eclipse.jface.dialogs.IconAndMessageDialog#getImage()
	 */


	protected Image getImage() {
		switch (getSeverity()) {
			case IStatus.ERROR :
				return this.getErrorImage();
			case IStatus.WARNING :
				return this.getWarningImage();
			default :
				return this.getInfoImage();
		}
	}

	protected Text createDropDownText(Composite parent) {
		// create the list
		text = new Text(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		// fill the list
		populateText(text);

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
		data.horizontalSpan = 2;
		data.heightHint = text.getLineHeight() * LIST_ITEM_COUNT;
		text.setLayoutData(data);
		textCreated = true;
		return text;
	}

	private void logStatus() {
		Iterator aenum = statusList.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append(message);
		while (aenum.hasNext()) {
			IStatus childStatus = (IStatus) aenum.next();
			populateText(sb, childStatus, 0);
		}
		Logger.getLogger().logInfo(sb.toString());

	}

	/**
	 * Populates the list using this error dialog's status object. This walks the child stati of the
	 * status object and displays them in a list. The format for each entry is status_path :
	 * status_message If the status's path was null then it (and the colon) are omitted.
	 */
	private void populateText(Text someText) {
		Iterator aenum = statusList.iterator();
		StringBuffer sb = new StringBuffer();
		while (aenum.hasNext()) {
			IStatus childStatus = (IStatus) aenum.next();
			//sb.append("Deployment results for " + childStatus.);
			populateText(sb, childStatus, 0);
		}
		someText.setText(sb.toString());

	}

	private void populateText(StringBuffer sb, IStatus status, int nesting) {
		for (int i = 0; i < nesting; i++) {
			sb.append("  "); //$NON-NLS-1$
		}
		sb.append(getMessageForDisplay(status));
		sb.append('\n');
		IStatus[] children = status.getChildren();
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
			text.setEditable(false);
			text.setBackground(DESC_COLOR);
		}

		Point newSize = getContents().computeSize(SWT.DEFAULT, SWT.DEFAULT);

		getShell().setSize(new Point(windowSize.x, windowSize.y + (newSize.y - oldSize.y)));
	}

	public String getMessageForDisplay(IStatus status) {
		String messageString = status.getMessage();
		return messageString;

	}

	protected String getSeverityText(IStatus status) {
		switch (status.getSeverity()) {
			case IStatus.ERROR :
				return ERROR_TEXT;
			case IStatus.WARNING :
				return WARNING_TEXT;
			case IStatus.INFO :
				return INFO_TEXT;
			case IStatus.OK :
				return OK_TEXT;
			default :
				return ""; //$NON-NLS-1$
		}
	}

	private void setSeverity(int sev) {
		if (severity == IStatus.ERROR)
			return;
		if (sev == IStatus.ERROR)
			severity = IStatus.ERROR;
		else if (sev == IStatus.WARNING) {
			severity = IStatus.WARNING;
		}
	}

	private int getSeverity() {
		return severity;
	}

	private int computeSeverity() {
		Iterator aenum = statusList.iterator();
		while (aenum.hasNext()) {
			IStatus childStatus = (IStatus) aenum.next();
			setSeverity(childStatus.getSeverity());
			if (getSeverity() == IStatus.ERROR)
				return getSeverity();
			computeSeverity(childStatus);
			if (getSeverity() == IStatus.ERROR)
				getSeverity();
		}
		return getSeverity();
	}

	private void computeSeverity(IStatus status) {
		IStatus[] children = status.getChildren();
		for (int i = 0; i < children.length; i++) {
			computeSeverity(children[i]);
			if (getSeverity() == IStatus.ERROR)
				return;
		}
	}

	/*
	 * (non-Javadoc) Method declared in Window.
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(DEPLOY_DIALOG_TITLE);
		//shell.setSize(600,200); don't set the size this breaks the dialog for linux.
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected Control createDialogArea(Composite parent) {
		Control control = super.createDialogArea(parent);
		setMessage(DEPLOYMENT_IN_PROGRESS);
		return control;
	}

	/**
	 * Set the message in the message label.
	 */
	private void setMessage(String messageString) {
		//must not set null text in a label
		message = messageString == null ? "" : messageString; //$NON-NLS-1$
		if (messageLabel == null || messageLabel.isDisposed())
			return;
		messageLabel.setText(message);
	}

}