/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.internal.ui.wizards.TypedElementSelectionValidator;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class FilteredFileSelectionDialog extends ElementTreeSelectionDialog {
	protected String[] fExtensions;
	/**
	 * FilteredFileSelectionDialog constructor comment.
	 * 
	 * @param parent
	 *            Shell
	 * @parent extensions String[]
	 */
	public FilteredFileSelectionDialog(Shell parent, String[] extensions) {
		this(parent, null, null, extensions, false);
	}
	/**
	 * FilteredFileSelectionDialog constructor comment.
	 * 
	 * @param parent
	 *            Shell
	 * @param title
	 *            String
	 * @param message
	 *            String
	 * @parent extensions String[]
	 * @param allowMultiple
	 *            boolean
	 */
	public FilteredFileSelectionDialog(Shell parent, String title, String message, String[] extensions, boolean allowMultiple) {
		super(parent, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);

		setTitle(title);
		if (title == null)
			setTitle(J2EEUIMessages.getResourceString("File_Selection_UI_")); //$NON-NLS-1$
		if (message == null)
			message = J2EEUIMessages.getResourceString("Select_a_file__UI_"); //$NON-NLS-1$
		setMessage(message);
		setAllowMultiple(true);
		setExtensions(extensions);
		addFilter(new TypedFileViewerFilter(extensions));
		setValidator(new TypedElementSelectionValidator(new Class[]{IFile.class}, allowMultiple));

	}
	public String[] getExtensions() {
		return fExtensions;
	}
	public void setExtensions(String[] extensions) {
		fExtensions = extensions;
	}

	public void setHelp(String helpCode) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getParentShell(), helpCode); //$NON-NLS-1$
	}

}
