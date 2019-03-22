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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;

import com.ibm.icu.text.UTF16;
import com.ibm.icu.util.StringTokenizer;

public class RenameContextRootChange extends Change {

	private final IProject project;
	private final String newContextRoot;
	private final String oldContextRoot;

	public RenameContextRootChange(IProject project, String newContextRoot, String oldContextRoot) {
		this.project = project;
		this.oldContextRoot = oldContextRoot;
		this.newContextRoot = newContextRoot;
	}

	@Override
	public Object getModifiedElement() {
		return project;
	}

	@Override
	public String getName() {
		return J2EEUIMessages.getResourceString("RenameContextRootFromXtoY", new String[]{oldContextRoot, //$NON-NLS-1$
				newContextRoot});
	}

	@Override
	public void initializeValidationData(IProgressMonitor pm) {

	}

	public static IStatus validateContextRoot(String name) {
		IStatus status = Status.OK_STATUS;
		if (name == null || name.length() == 0) {
			// this was added because the error message shouldnt be shown
			// initially. It should be shown only if context
			// root field is edited to
			status = new Status(IStatus.ERROR, J2EEUIPlugin.PLUGIN_ID,
					ProjectSupportResourceHandler.getString(
							ProjectSupportResourceHandler.Context_Root_cannot_be_empty_2,
							new Object[0]));

		} else if (!(name.indexOf(' ') > -1)) {
			StringTokenizer stok = new StringTokenizer(name, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				int cp;
		        for (int i = 0; i < token.length(); i += UTF16.getCharCount(cp)) {
		            cp = UTF16.charAt(token, i);
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-')
							&& !(token.charAt(i) == '/')
							&& Character.isLetterOrDigit(token.charAt(i)) == false) {
						if (Character.isWhitespace(token.charAt(i)) == false) {
							String invalidCharString = null;
							if (UTF16.getCharCount(cp)>1)
							{
								invalidCharString = UTF16.valueOf(cp); 
							}
							else
							{
								invalidCharString = (new Character(token.charAt(i))).toString();
							}
							Object[] invalidChar = new Object[]{invalidCharString};
							status = new Status(
									IStatus.ERROR,
									J2EEUIPlugin.PLUGIN_ID,
									ProjectSupportResourceHandler
											.getString(
													ProjectSupportResourceHandler.The_character_is_invalid_in_a_context_root,
													invalidChar));

						}
					}
				}
			}
		} // en/ end of if(name.trim
		else {
			status = new Status(IStatus.ERROR, J2EEUIPlugin.PLUGIN_ID,
					ProjectSupportResourceHandler.getString(
							ProjectSupportResourceHandler.Names_cannot_contain_whitespace_,
							new Object[0]));

		}
		return status;

	}

	@Override
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		IStatus phase1 = validateContextRoot(newContextRoot);
		if (phase1.isOK())
			return new RefactoringStatus();
		return RefactoringStatus.create(phase1);
	}

	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {

		ComponentUtilities.setServerContextRoot(project, newContextRoot);
		
		return new RenameContextRootChange(project, oldContextRoot, newContextRoot);
	}

}
