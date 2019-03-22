/*******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.javaee.ltk.core.descriptor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

public class EARReferenceRemoveDescriptor extends RefactoringDescriptor {

	public EARReferenceRemoveDescriptor(){
		super (null, null, null, null, -1);
	}
	protected EARReferenceRemoveDescriptor(String id, String project,
			String description, String comment, int flags) {
		super(id, project, description, comment, flags);
		}

	@Override
	public Refactoring createRefactoring(RefactoringStatus status)
			throws CoreException {
		return null;
	}
	
	

}
