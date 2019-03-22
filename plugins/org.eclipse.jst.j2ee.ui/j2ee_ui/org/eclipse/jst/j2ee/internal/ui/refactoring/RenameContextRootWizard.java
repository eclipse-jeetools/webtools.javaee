/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.refactoring;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

public class RenameContextRootWizard extends RefactoringWizard {

	private boolean prompt = true;

	public RenameContextRootWizard(RenameContextRootRefactoringProcessor processor, int flags) {
		super(new RenameRefactoring(processor), flags);
		setDefaultPageTitle(J2EEUIMessages.getResourceString("RenameContextRoot")); //$NON-NLS-1$

		setChangeCreationCancelable(true);
		setHelpAvailable(false);
		setNeedsProgressMonitor(true);
	}

	@Override
	protected void addUserInputPages() {
		if (prompt) {
			addPage(new ContextRootInputPage("", //$NON-NLS-1$
					getRefactoring().getAdapter(
							RenameContextRootRefactoringProcessor.class)));
		}
	}

	public void setPrompt(boolean prompt) {
		this.prompt = prompt;
	}

}
