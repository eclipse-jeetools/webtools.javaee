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
package org.eclipse.jst.j2ee.internal.ejb.actions;


import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * Insert the type's description here. Creation date: (9/7/2001 11:28:24 AM)
 * 
 * @author: Administrator
 */
public class EJBSelectiveImportDialog extends org.eclipse.jface.dialogs.MessageDialog {
	protected String[] ejbList;

	/**
	 * EJBSelectiveImportDialog constructor comment.
	 * 
	 * @param parentShell
	 *            org.eclipse.swt.widgets.Shell
	 * @param dialogTitle
	 *            java.lang.String
	 * @param dialogTitleImage
	 *            org.eclipse.swt.graphics.Image
	 * @param dialogMessage
	 *            java.lang.String
	 * @param dialogImageType
	 *            int
	 * @param dialogButtonLabels
	 *            java.lang.String[]
	 * @param defaultIndex
	 *            int
	 */
	public EJBSelectiveImportDialog(org.eclipse.swt.widgets.Shell parentShell, String dialogTitle, org.eclipse.swt.graphics.Image dialogTitleImage, String dialogMessage, int dialogImageType, java.lang.String[] dialogButtonLabels, int defaultIndex) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
	}

	/**
	 * EJBSelectiveImportDialog constructor comment.
	 * 
	 * @param parentShell
	 *            org.eclipse.swt.widgets.Shell
	 * @param dialogTitle
	 *            java.lang.String
	 * @param dialogTitleImage
	 *            org.eclipse.swt.graphics.Image
	 * @param dialogMessage
	 *            java.lang.String
	 * @param dialogImageType
	 *            int
	 * @param dialogButtonLabels
	 *            java.lang.String[]
	 * @param defaultIndex
	 *            int
	 */
	public EJBSelectiveImportDialog(org.eclipse.swt.widgets.Shell parentShell, String dialogTitle, org.eclipse.swt.graphics.Image dialogTitleImage, String dialogMessage, int dialogImageType, java.lang.String[] dialogButtonLabels, int defaultIndex, String[] ejbNames) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
		ejbList = ejbNames;
	}

	/**
	 * Creates and returns the contents of an area of the dialog which appears below the message and
	 * above the button bar.
	 * <p>
	 * The default implementation of this framework method returns <code>null</code>. Subclasses
	 * may override.
	 * </p>
	 * 
	 * @param the
	 *            parent composite to contain the custom area
	 * @return the custom area control, or <code>null</code>
	 */
	protected Control createCustomArea(Composite parent) {

		Composite composite = new Composite(parent, 0);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		if (ejbList != null) {
			List list = new List(composite, SWT.BORDER | SWT.HIDE_SELECTION);
			GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
			list.setLayoutData(data);
			list.setItems(ejbList);
			list.setForeground(new Color(null, 0, 0, 255));
		}

		return composite;

	}

	/**
	 * Convenience method to open a simple Yes/No question dialog.
	 * 
	 * @param parent
	 *            the parent shell of the dialog, or <code>null</code> if none
	 * @param title
	 *            the dialog's title, or <code>null</code> if none
	 * @param message
	 *            the message
	 * @return <code>true</code> if the user presses the OK button, <code>false</code> otherwise
	 */
	public static boolean openError(Shell parent, String title, String message, String[] names) {
		EJBSelectiveImportDialog dialog = new EJBSelectiveImportDialog(parent, title, null, // accept
					// the
					// default
					// window
					// icon
					message, ERROR, new String[]{IDialogConstants.OK_LABEL}, 0, names); // yes is
		// the
		// default
		return dialog.open() == 0;
	}

	/**
	 * Convenience method to open a simple Yes/No question dialog.
	 * 
	 * @param parent
	 *            the parent shell of the dialog, or <code>null</code> if none
	 * @param title
	 *            the dialog's title, or <code>null</code> if none
	 * @param message
	 *            the message
	 * @return <code>true</code> if the user presses the OK button, <code>false</code> otherwise
	 */
	public static boolean openQuestion(Shell parent, String title, String message, String[] ejbNames) {
		EJBSelectiveImportDialog dialog = new EJBSelectiveImportDialog(parent, title, null, // accept
					// the
					// default
					// window
					// icon
					message, QUESTION, new String[]{IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL}, 0, ejbNames); // yes
		// is
		// the
		// default
		return dialog.open() == 0;
	}
}