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

package org.eclipse.jst.javaee.ltk.core.change;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jst.javaee.ltk.core.nls.RefactoringResourceHandler;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.osgi.util.NLS;

public class DeleteEJBClientProjectChange extends Change{

	IProject ejbClientProject = null;
	
	public DeleteEJBClientProjectChange(IProject clientProj, boolean forceOutOfSync, boolean forceDelete) {
		ejbClientProject = clientProj;
	}
	
	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		if(!ejbClientProject.isAccessible())
			return null;
		try{
			ejbClientProject.delete(true, true, new NullProgressMonitor());
		}catch(ResourceException e){
			ejbClientProject.delete(false, true, new NullProgressMonitor());
		}
		return null;
		
	}

	@Override
	public Object getModifiedElement() {
		return null;
	}

	@Override
	public String getName() {
		String name = NLS.bind(
				RefactoringResourceHandler.Delete_EJB_Client_Project,
				new Object[] { ejbClientProject.getName()});
		return name;	
	}

	@Override
	public void initializeValidationData(IProgressMonitor pm) {
	}

	@Override
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		return null;
	}
	
}
