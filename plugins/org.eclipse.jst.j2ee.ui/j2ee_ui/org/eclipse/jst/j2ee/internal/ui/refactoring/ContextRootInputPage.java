/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.refactoring;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ContextRootInputPage extends UserInputWizardPage {

	private final RenameContextRootRefactoringProcessor fProcessor;
	private Text fNameField;

	public ContextRootInputPage(String name, RenameContextRootRefactoringProcessor processor) {
		super(name);
		this.fProcessor = processor;
	}

	@Override
	public void createControl(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);

		Label label = new Label(c, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString("NewContextRoot")); //$NON-NLS-1$

		fNameField = new Text(c, SWT.BORDER);
		fNameField.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		fNameField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				validatePage();
			}

		});
		String oldName = fProcessor.getOldContextRoot();
		if (oldName != null && oldName.length() > 0) {
			fNameField.setText(oldName);
			fNameField.setSelection(0, oldName.length());
		}

		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(c);

		setControl(c);
		fNameField.setFocus();
		
		validatePage();
	}

	private void validatePage() {
		String text = fNameField.getText();
		RefactoringStatus status = fProcessor.validateNewElementName(text);
		setPageComplete(status);
	}

	@Override
	protected boolean performFinish() {
		initializeRefactoring();
		return super.performFinish();
	}

	@Override
	public IWizardPage getNextPage() {
		initializeRefactoring();
		return super.getNextPage();
	}

	private void initializeRefactoring() {
		fProcessor.setNewName(fNameField.getText());
	}
}
