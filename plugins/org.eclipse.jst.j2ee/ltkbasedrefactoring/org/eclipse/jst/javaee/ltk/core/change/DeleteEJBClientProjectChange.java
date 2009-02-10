/*******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.javaee.ltk.core.change;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.DeleteResourceChange;

public class DeleteEJBClientProjectChange extends DeleteResourceChange {

	IProject ejbClientProject = null;
	
	public DeleteEJBClientProjectChange(IPath resourcePath, boolean forceOutOfSync, boolean forceDelete) {
		super(resourcePath, forceOutOfSync, forceDelete);
		}
	
	public DeleteEJBClientProjectChange(IProject clientProj, boolean forceOutOfSync, boolean forceDelete) {
		super(clientProj.getFullPath(), forceOutOfSync, forceDelete);
		ejbClientProject = clientProj;
	}
	
	public Change perform(IProgressMonitor pm) throws CoreException {
		if(!ejbClientProject.isAccessible())
			return null;
		return super.perform(pm);
	}
}
