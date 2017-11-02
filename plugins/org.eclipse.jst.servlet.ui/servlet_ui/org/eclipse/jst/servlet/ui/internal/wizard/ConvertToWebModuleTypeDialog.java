/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ConvertToWebModuleTypeDialog extends Dialog {
	
	private String fShellTitle = IWebWizardConstants.CONVERT_TO_WEB_TITLE;
	private String fLabelText = IWebWizardConstants.SELECT_WEB_VERSION;
	private static String fSelection= null;
	private String[] fAllowedStrings = new String[] {J2EEVersionConstants.VERSION_2_2_TEXT, J2EEVersionConstants.VERSION_2_3_TEXT, J2EEVersionConstants.VERSION_2_4_TEXT, J2EEVersionConstants.VERSION_2_5_TEXT, J2EEVersionConstants.VERSION_3_0_TEXT, J2EEVersionConstants.VERSION_3_1_TEXT, J2EEVersionConstants.VERSION_4_0_TEXT};
	private int fInitialSelectionIndex = fAllowedStrings.length-1;
	
	public static String getSelectedVersion() {
		return fSelection;
	}

	public ConvertToWebModuleTypeDialog(IShellProvider parentShell) {
		super(parentShell);
	}

	public ConvertToWebModuleTypeDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setText(fShellTitle);
		
		Composite composite = (Composite)super.createDialogArea(parent);
		Composite innerComposite = new Composite(composite, SWT.NONE);
		innerComposite.setLayoutData(new GridData());
		GridLayout gl= new GridLayout();
		gl.numColumns= 2;
		innerComposite.setLayout(gl);
		
		Label label= new Label(innerComposite, SWT.NONE);
		label.setText(fLabelText);
		label.setLayoutData(new GridData());

		final Combo combo= new Combo(innerComposite, SWT.READ_ONLY);
		for (int i = 0; i < fAllowedStrings.length; i++) {
			combo.add(fAllowedStrings[i]);
		}
		combo.select(fInitialSelectionIndex);
		fSelection= combo.getItem(combo.getSelectionIndex());
		GridData gd= new GridData();
		combo.setLayoutData(gd);
		combo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				fSelection= combo.getItem(combo.getSelectionIndex());
			}
		});
		return composite;
	}
	
}
