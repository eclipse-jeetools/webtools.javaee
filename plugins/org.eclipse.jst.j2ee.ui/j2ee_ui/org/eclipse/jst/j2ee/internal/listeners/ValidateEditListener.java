/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.listeners;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.dialogs.ListMessageDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateValidator;

public class ValidateEditListener extends ShellAdapter implements IValidateEditListener {
	protected ResourceStateValidator fValidator;
	private boolean fNeedsStateValidation = true;
	private Shell fShell;
	private IWorkbenchPart fPart;
	private boolean fHasReadOnlyFiles = false;
	private boolean firstReadOnlyFileAttempt = true;
	private boolean fMessageUp = false;
	private boolean fIsActivating = false;
	private boolean fIsDeactivating = false;

	/**
	 * Constructor for ValidateEditHandler.
	 */
	public ValidateEditListener(IWorkbenchPart part, ResourceStateValidator aValidator) {
		super();
		fPart = part;
		fValidator = aValidator;
		if (part != null)
			part.getSite().getPage().addPartListener(this);
		if (getShell() != null)
			getShell().addShellListener(this);
	}

	protected Shell getShell() {
		if (fShell == null) {
			if (fPart != null && fPart.getSite() != null)
				fShell = fPart.getSite().getShell();
		}
		return fShell;
	}

	/**
	 * @see IValidateEditListener#getValidator()
	 */
	public ResourceStateValidator getValidator() {
		return fValidator;
	}

	/**
	 * @see IValidateEditListener#getNeedsStateValidation()
	 */
	public boolean getNeedsStateValidation() {
		return fNeedsStateValidation;
	}

	/**
	 * @see IValidateEditListener#setNeedsStateValidation(boolean)
	 */
	public void setNeedsStateValidation(boolean needsStateValidation) {
		fNeedsStateValidation = needsStateValidation;
	}

	/**
	 * @see ResourceStateValidatorPresenter#promptForInconsistentFileRefresh(List)
	 */
	public boolean promptForInconsistentFileRefresh(List inconsistentFiles) {
		if (inconsistentFiles == null || inconsistentFiles.size() == 0) // this case should never
			// occur.
			return false;
		String title = null;
		String message = null;
		String[] fileNames = new String[inconsistentFiles.size()];
		for (int i = 0; inconsistentFiles.size() > i; i++) {
			Object file = inconsistentFiles.get(i);
			if (file instanceof Resource) {
				IFile aFile = WorkbenchResourceHelper.getFile((Resource) file);
				fileNames[i] = aFile.getFullPath().toOSString();
			} else if (file instanceof IResource) {
				IResource resfile = (IResource) file;
				if (!resfile.exists()) {
					return false;
				}
				fileNames[i] = resfile.getFullPath().toOSString();
			}
		}


		title = J2EEUIMessages.getResourceString("Inconsistent_Files_3"); //$NON-NLS-1$
		message = J2EEUIMessages.getResourceString("The_following_workspace_files_are_inconsistent_with_the_editor_4"); //$NON-NLS-1$
		message += J2EEUIMessages.getResourceString("Update_the_editor_with_the_workspace_contents__5"); //$NON-NLS-1$
		return ListMessageDialog.openQuestion(getShell(), title, message, fileNames);
	}

	/**
	 * @see ResourceStateValidatorPresenter#getValidateEditContext()
	 */
	public Object getValidateEditContext() {
		return getShell();
	}

	/**
	 * @see IPartListener#partActivated(IWorkbenchPart)
	 */
	public void partActivated(IWorkbenchPart part) {
		if (part == fPart) {
			handleActivation();
		}
	}

	protected void handleActivation() {
		if (fIsActivating)
			return;
		fIsActivating = true;
		try {
			fValidator.checkActivation(this);
			updatePartReadOnly();
		} catch (CoreException e) {
			// do nothing for now
		} finally {
			fIsActivating = false;
		}
	}

	/**
	 * @see org.eclipse.swt.events.ShellListener#shellActivated(ShellEvent)
	 */
	public void shellActivated(ShellEvent event) {
		handleActivation();
	}

	/**
	 * @see IPartListener#partBroughtToTop(IWorkbenchPart)
	 */
	public void partBroughtToTop(IWorkbenchPart part) {
		//do nothing
	}

	/**
	 * @see IPartListener#partClosed(IWorkbenchPart)
	 */
	public void partClosed(IWorkbenchPart part) {
		if (part == fPart)
			part.getSite().getPage().removePartListener(this);
		if (getShell() != null)
			getShell().removeShellListener(this);
	}

	/**
	 * @see IPartListener#partDeactivated(IWorkbenchPart)
	 */
	public void partDeactivated(IWorkbenchPart part) {
		if (part == fPart) {
			if (fIsDeactivating)
				return;
			fIsDeactivating = true;
			try {
				fValidator.lostActivation(this);
				updatePartReadOnly();
			} catch (CoreException e) {
				// do nothing for now
			} finally {
				fIsDeactivating = true;
			}
		}
	}

	/**
	 * @see IPartListener#partOpened(IWorkbenchPart)
	 */
	public void partOpened(IWorkbenchPart part) {
		//do nothing
	}

	public IStatus validateState() {
		if (fNeedsStateValidation) {
			setNeedsStateValidation(false);
			IStatus status = null;
			try {
				status = fValidator.validateState(this);
				if (status.getSeverity() == IStatus.ERROR) {
					setNeedsStateValidation(true);
					if (!fMessageUp) {
						fMessageUp = true;
						MessageDialog.openError(getShell(), J2EEUIMessages.getResourceString("Error_checking_out_files_10"), status.getMessage()); //$NON-NLS-1$
						fMessageUp = false;
					}
				}
				fValidator.checkActivation(this);
				updatePartReadOnly();
			} catch (CoreException e) {
				// do nothing for now
			}
			return status;
		}
		return ResourceStateValidator.OK_STATUS;
	}

	/**
	 * @see ResourceStateValidatorPresenter#promptForInconsistentFileOverwrite(List)
	 */
	public boolean promptForInconsistentFileOverwrite(List inconsistentFiles) {
		int size = inconsistentFiles.size();
		String[] items = new String[size];
		IFile file = null;
		for (int i = 0; i < size; i++) {
			file = (IFile) inconsistentFiles.get(i);
			items[i] = file.getFullPath().toString();
		}
		return ListMessageDialog.openQuestion(getShell(), J2EEUIMessages.getResourceString("Inconsistent_files_detected_11"), //$NON-NLS-1$
					J2EEUIMessages.getResourceString("The_following_files_are_inconsistent_with_the_file_system._Do_you_want_to_save_and_overwrite_these_files_on_the_file_system__12_WARN_"), //$NON-NLS-1$
					items);
	}

	protected boolean checkReadOnly() {
		fHasReadOnlyFiles = fValidator.checkReadOnly();
		return fHasReadOnlyFiles;
	}

	/**
	 * @see IValidateEditListener#hasReadOnlyFiles()
	 */
	public boolean hasReadOnlyFiles() {
		if (firstReadOnlyFileAttempt) {
			checkReadOnly();
			firstReadOnlyFileAttempt = false;
		}
		return fHasReadOnlyFiles;
	}

	/**
	 * Method updatePartReadOnly.
	 */
	protected void updatePartReadOnly() {
		if (!getNeedsStateValidation()) {
			checkReadOnly();
			setNeedsStateValidation(true);
		} else { //So that J2EEXMLActionBarContributor get updated info when editor Activated.
			firstReadOnlyFileAttempt = true;
		}
	}

	public boolean checkSave() throws CoreException {
		return validateState().isOK() && getValidator().checkSave(this);
	}

	public void setShell(Shell aShell) {
		fShell = aShell;
	}
}