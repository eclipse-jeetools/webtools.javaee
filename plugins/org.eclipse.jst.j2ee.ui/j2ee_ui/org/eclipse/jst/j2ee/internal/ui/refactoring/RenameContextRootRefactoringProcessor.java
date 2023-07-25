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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

public class RenameContextRootRefactoringProcessor extends RenameProcessor {

	private IProject fProject;
	private String fNewName;
	private boolean fUpdateReferences;

	@Override
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws CoreException, OperationCanceledException {
		if (!isApplicable())
			return RefactoringStatus.createErrorStatus(J2EEUIMessages
					.getResourceString("RefactoringNoInit")); //$NON-NLS-1$
		return validateNewElementName(fNewName);
	}

	@Override
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		if (isApplicable() && fNewName != null)
			return new RenameContextRootChange(getProject(), fNewName, getOldContextRoot());
		return null;
	}

	@Override
	public Object[] getElements() {
		if (getProject() == null)
			return new Object[0];
		return new Object[] {
			getProject()
		};
	}

	@Override
	public String getIdentifier() {
		return "org.eclipse.jst.j2ee.ChangeContextRootRefactoringProcessor"; //$NON-NLS-1$
	}

	@Override
	public String getProcessorName() {
		return J2EEUIMessages.getResourceString("RenameContextRoot"); //$NON-NLS-1$
	}

	public IProject getProject() {
		return fProject;
	}

	@Override
	public boolean isApplicable() throws CoreException {
		return getProject() != null;
	}

	private RenameArguments getRenameArguments() {
		return new RenameArguments(fNewName, fUpdateReferences);
	}

	@Override
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status,
			SharableParticipants sharedParticipants) throws CoreException {
		String[] natures = new String[0];
		IProject project = getProject();
		if (project != null && fNewName != null) {
			natures = project.getDescription().getNatureIds();
			return ParticipantManager.loadRenameParticipants(status, this, project,
					getRenameArguments(), natures, sharedParticipants);
		}
		return new RefactoringParticipant[0];
	}

	public void setProject(IProject project) {
		this.fProject = project;
	}

	public void setNewName(String newName) {
		this.fNewName = newName;
	}

	public void setUpdateReferences(boolean updateReferences) {
		this.fUpdateReferences = updateReferences;
	}

	public RefactoringStatus validateNewElementName(String newName) {
		return RefactoringStatus.create(RenameContextRootChange.validateContextRoot(newName));
	}

	public String getOldContextRoot() {
		return J2EEProjectUtilities.getServerContextRoot(getProject());
	}
}
