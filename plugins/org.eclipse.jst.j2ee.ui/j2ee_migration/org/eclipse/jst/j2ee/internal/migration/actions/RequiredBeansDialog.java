/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;


public class RequiredBeansDialog extends MessageDialog {

	protected Map configsToChildren;

	/**
	 * Constructor for RequiredBeansDialog.
	 * 
	 * @param parentShell
	 * @param dialogTitle
	 * @param dialogTitleImage
	 * @param dialogMessage
	 * @param dialogImageType
	 * @param dialogButtonLabels
	 * @param defaultIndex
	 */
	public RequiredBeansDialog(Shell parentShell, String dialogTitle, Image dialogTitleImage, String dialogMessage, int dialogImageType, String[] dialogButtonLabels, int defaultIndex) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
	}

	/**
	 * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(Composite)
	 */
	protected Control createCustomArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		createTree(composite);
		return composite;
	}

	/**
	 * Method createTree.
	 * 
	 * @param composite
	 */
	private void createTree(Composite composite) {
		TreeViewer viewer = new TreeViewer(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
		viewer.getControl().setLayoutData(data);
		viewer.setContentProvider(new BeanContentProvider(configsToChildren));
		viewer.setLabelProvider(new BeanLabelProvider());
		viewer.setInput(new ArrayList(configsToChildren.keySet()));
		data.heightHint = ((Tree) viewer.getControl()).getItemHeight() * 10;
	}

	/**
	 * Convenience method to open a simple confirm (OK/Cancel) dialog.
	 * 
	 * @param parent
	 *            the parent shell of the dialog, or <code>null</code> if none
	 * @param title
	 *            the dialog's title, or <code>null</code> if none
	 * @param message
	 *            the message
	 * @return <code>true</code> if the user presses the OK button, <code>false</code> otherwise
	 */
	public static boolean openConfirm(Shell parent, Map configs) {
		RequiredBeansDialog dialog = new RequiredBeansDialog(parent, IJ2EEMigrationConstants.REQUIRED_CLIENT_VIEW_TITLE, null, // accept
					// the
					// default
					// window
					// icon
					IJ2EEMigrationConstants.CONFIRM_CLIENT_VIEW_REQUIRED, QUESTION, new String[]{IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0); // OK
		// is
		// the
		// default
		dialog.setConfigsToChildren(configs);
		return dialog.open() == 0;
	}



	/**
	 * Sets the configsToChildren.
	 * 
	 * @param configsToChildren
	 *            The configsToChildren to set
	 */
	public void setConfigsToChildren(Map configsToChildren) {
		this.configsToChildren = configsToChildren;
	}

}