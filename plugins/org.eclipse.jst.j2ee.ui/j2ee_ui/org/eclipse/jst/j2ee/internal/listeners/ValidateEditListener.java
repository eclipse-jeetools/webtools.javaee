/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.listeners;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.dialogs.ListMessageDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;
import org.eclipse.wst.common.internal.emfworkbench.validateedit.IValidateEditContext;
import org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateValidator;

public class ValidateEditListener extends ShellAdapter implements IValidateEditListener, IValidateEditContext {
	
	protected ResourceStateValidator fValidator;
	private boolean fNeedsStateValidation = true;
	private Shell fShell;
	private IWorkbenchPart fPart;
	private boolean fHasReadOnlyFiles = false;
	private boolean firstReadOnlyFileAttempt = true;
	private boolean fMessageUp = false;
	private boolean fIsActivating = false;
	private boolean fIsDeactivating = false;
	private boolean inconsistentResult;
	private boolean inconsistentOverwriteResult;
	
	public ValidateEditListener() {
		super();
		try {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					IWorkbench wb = PlatformUI.getWorkbench();
					if (wb == null) return;
					IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
					if (window == null && wb.getWorkbenchWindowCount()>0) {
						for (int i=0; i<wb.getWorkbenchWindows().length; i++) {
							window = wb.getWorkbenchWindows()[i];
							if (window != null)
								break;
						}
						
					}
					if (window!=null)
						setShell(window.getShell());
				}
			});
			
		} catch (Exception e) {
			J2EEUIPlugin.logError(e);
		}
	}
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
	 * @see org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateValidatorPresenter#promptForInconsistentFileRefresh(List)
	 */
	@Override
	public boolean promptForInconsistentFileRefresh(List inconsistentFiles) {
		if (inconsistentFiles == null || inconsistentFiles.size() == 0) // this case should never
			// occur.
			return false;

		List inconsistentFileNames = new ArrayList();
		for (int i = 0; inconsistentFiles.size() > i; i++) {
			Object file = inconsistentFiles.get(i);
			if (file instanceof Resource) {
				IFile aFile = WorkbenchResourceHelper.getFile((Resource) file);
				inconsistentFileNames.add(aFile.getFullPath().toOSString());
			} else if (file instanceof IResource) {
				IResource resfile = (IResource) file;
				if (!resfile.exists()) {
					return false;
				}
				inconsistentFileNames.add(resfile.getFullPath().toOSString());
			}
		}

		final String title = J2EEUIMessages.getResourceString("Inconsistent_Files_3"); //$NON-NLS-1$
		final String message = J2EEUIMessages.getResourceString("The_following_workspace_files_are_inconsistent_with_the_editor_4") + J2EEUIMessages.getResourceString("Update_the_editor_with_the_workspace_contents__5"); //$NON-NLS-1$ //$NON-NLS-2$
		final String[] fileNames = (String[])inconsistentFileNames.toArray(new String[inconsistentFileNames.size()]);
		
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				inconsistentResult = ListMessageDialog.openQuestion(getShell(), title, message, fileNames);
			}
		});
		return inconsistentResult;
	}

	/**
	 * @see org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateValidatorPresenter#getValidateEditContext()
	 */
	@Override
	public Object getValidateEditContext() {
		return getShell();
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partActivated(IWorkbenchPart)
	 */
	@Override
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
	@Override
	public void shellActivated(ShellEvent event) {
		handleActivation();
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(IWorkbenchPart)
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		//do nothing
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partClosed(IWorkbenchPart)
	 */
	@Override
	public void partClosed(IWorkbenchPart part) {
		if (part == fPart)
			part.getSite().getPage().removePartListener(this);
		if (getShell() != null)
			getShell().removeShellListener(this);
	}

	/**
	 * @see org.eclipse.ui.IPartListener#partDeactivated(IWorkbenchPart)
	 */
	@Override
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
	 * @see org.eclipse.ui.IPartListener#partOpened(IWorkbenchPart)
	 */
	@Override
	public void partOpened(IWorkbenchPart part) {
		//do nothing
	}

	@Override
	public IStatus validateState() {
		IWorkbench wb = PlatformUI.getWorkbench();
		if ((fShell==null) && (wb != null && (wb.getActiveWorkbenchWindow() != null)))
			fShell=wb.getActiveWorkbenchWindow().getShell();
		if (fNeedsStateValidation) {
			setNeedsStateValidation(false);
			try {
				final IStatus status = fValidator.validateState(this);
				if (status.getSeverity() == IStatus.ERROR) {
					setNeedsStateValidation(true);
					if (!fMessageUp) {
						fMessageUp = true;
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								MessageDialog.openError(getShell(), J2EEUIMessages.getResourceString("Error_checking_out_files_10"), status.getMessage()); //$NON-NLS-1$
							}
						});
						fMessageUp = false;
					}
				}
				fValidator.checkActivation(this);
				updatePartReadOnly();
				return status;
			} catch (CoreException e) {
				// do nothing for now
			}
		}
		return ResourceStateValidator.OK_STATUS;
	}

	/**
	 * @see org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateValidatorPresenter#promptForInconsistentFileOverwrite(List)
	 */
	@Override
	public boolean promptForInconsistentFileOverwrite(List inconsistentFiles) {
		int size = inconsistentFiles.size();
		List files = new ArrayList();
		IFile file = null;
		for (int i = 0; i < size; i++) {
			file = (IFile) inconsistentFiles.get(i);
			files.add(file.getFullPath().toString());
		}
		final String[] items = (String[])files.toArray(new String[files.size()]);
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				inconsistentOverwriteResult = ListMessageDialog.openQuestion(getShell(), J2EEUIMessages.getResourceString("Inconsistent_files_detected_11"), //$NON-NLS-1$
					J2EEUIMessages.getResourceString("The_following_files_are_inconsistent_with_the_file_system._Do_you_want_to_save_and_overwrite_these_files_on_the_file_system__12_WARN_"), //$NON-NLS-1$
					items);
			}
		});
		return inconsistentOverwriteResult;
	}

	protected boolean checkReadOnly() {
		fHasReadOnlyFiles = fValidator.checkReadOnly();
		return fHasReadOnlyFiles;
	}

	/**
	 * @see IValidateEditListener#hasReadOnlyFiles()
	 */
	@Override
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

	@Override
	public boolean checkSave() throws CoreException {
		return validateState().isOK() && getValidator().checkSave(this);
	}

	@Override
	public void setShell(Shell aShell) {
		fShell = aShell;
	}
	
	@Override
	public void setEditModel(EditModel anEditModel) {
		fValidator = anEditModel;
		
	}

	@Override
	public IStatus validateState(EditModel anEditModel) {
		setEditModel(anEditModel);
		return validateState();
	}
}
