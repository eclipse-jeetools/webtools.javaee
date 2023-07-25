/*******************************************************************************
 * Copyright (c) 2006, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences     
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jst.j2ee.internal.J2EEPropertiesConstants;
import org.eclipse.jst.j2ee.internal.ui.refactoring.RenameContextRootChange;
import org.eclipse.jst.j2ee.internal.ui.refactoring.RenameContextRootRefactoringProcessor;
import org.eclipse.jst.j2ee.internal.ui.refactoring.RenameContextRootWizard;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;

public class J2EEPropertiesPage extends PropertyPage implements J2EEPropertiesConstants {

	private Text contextRootNameField;
	private boolean dirty = false;

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite c = parent;
		IProject project = getProject();
		if (project != null
				&& JavaEEProjectUtilities.getJ2EEProjectType(project).equals(
						JavaEEProjectUtilities.DYNAMIC_WEB)
				|| JavaEEProjectUtilities.getJ2EEProjectType(project).equals(
						JavaEEProjectUtilities.STATIC_WEB)) {

			c = new Composite(parent, SWT.NONE);

			Label contextRootLabel = new Label(c, SWT.NONE);
			contextRootLabel.setText(J2EEPropertiesConstants.WEB_CONTEXT_ROOT);

			contextRootNameField = new Text(c, SWT.BORDER);
			contextRootNameField.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					dirty = !contextRootNameField.getText().equals(
							ComponentUtilities.getServerContextRoot(getProject()));
					validateText();
				}
			});
			GridLayoutFactory.fillDefaults().numColumns(2).applyTo(c);
			GridDataFactory.defaultsFor(contextRootLabel).applyTo(contextRootLabel);
			GridDataFactory.defaultsFor(contextRootNameField).grab(true, false).applyTo(
					contextRootNameField);
		}
		applyDialogFont(c);
		return c;
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		refresh();
		refreshApplyButton();
	}

	private void validateText() {
		IStatus status = RenameContextRootChange
				.validateContextRoot(contextRootNameField.getText());
		if (!status.isOK()) {
			setErrorMessage(status.getMessage());
			setValid(false);
		}
		else {
			setErrorMessage(null);
			setValid(true);
			refreshApplyButton();
		}
	}

	private void refreshApplyButton() {
		if (dirty) {
			if (getApplyButton() != null) {
				getApplyButton().setEnabled(true);
			}
		}
		else {
			if (getApplyButton() != null) {
				getApplyButton().setEnabled(false);
			}
		}
	}

	public void refresh() {
		if (contextRootNameField != null) {
			String s = ComponentUtilities.getServerContextRoot(getProject());
			if (s == null) {
				s = ""; //$NON-NLS-1$
			}
			contextRootNameField.setText(s);
			validateText();
		}
	}

	private IProject getProject() {

		Object element = getElement();
		if (element == null) {
			return null;
		}
		if (element instanceof IProject) {
			IProject project = (IProject) element;
			return project;
		}
		return null;

	}

	private int doRefactor() {
		int id = IDialogConstants.OK_ID;
		if (dirty) {
			RenameContextRootRefactoringProcessor processor = new RenameContextRootRefactoringProcessor();
			processor.setProject(getProject());
			processor.setNewName(contextRootNameField.getText());
			RenameContextRootWizard wizard = new RenameContextRootWizard(processor,
					RefactoringWizard.DIALOG_BASED_USER_INTERFACE);
			wizard.setPrompt(false);
			RefactoringWizardOpenOperation operation = new RefactoringWizardOpenOperation(wizard);

			try {
				id = operation.run(getShell(), ""); //$NON-NLS-1$
			}
			catch (InterruptedException ee) {

			}
			if (id == IDialogConstants.OK_ID) {
				dirty = false;
				refresh();
			}
		}

		return id;
	}

	@Override
	protected void performApply() {
		doRefactor();
	}

	@Override
	protected void performDefaults() {
		refresh();
	}

	@Override
	public boolean performOk() {
		return doRefactor() == IDialogConstants.OK_ID;
	}

}
